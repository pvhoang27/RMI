/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
//[Mã câu hỏi (qCode): 6vc43pX2].  Một chương trình (tạm gọi là RMI Server) cung cấp giao diện cho phép triệu gọi từ xa để quản lý thông tin đơn hàng trong hệ thống thương mại điện tử. Chương trình sẽ ngẫu nhiên tạo ra đối tượng Order với các giá trị ban đầu và cung cấp cho RMI client như sau:
//    Giao diện từ xa:
//public interface ObjectService extends Remote {
//    public Serializable requestObject(String studentCode, String qCode) throws RemoteException;
//    public void submitObject(String studentCode, String qCode, Serializable object) throws RemoteException;
//}
//Lớp Order gồm các thuộc tính: id String, customerCode String, orderDate String, shippingType String, orderCode String.
//•	Trường dữ liệu: private static final long serialVersionUID = 20241132L;
//•	02 hàm khởi dựng:
//    public Order()
//    public Order(String id, String customerCode, String orderDate, String shippingType)
//Trong đó:
//•	Interface ObjectService và lớp Order được viết trong package RMI.
//•	Đối tượng cài đặt giao diện từ xa ObjectService được đăng ký với RegistryServer: RMIObjectService.
//Yêu cầu: Viết chương trình tại máy trạm (RMI client) để thực hiện các công việc sau với đối tượng đơn hàng được nhận từ RMI Server:
//a. Triệu gọi phương thức requestObject để nhận đối tượng Order ngẫu nhiên từ server.
//b. Tạo mã orderCode cho đơn hàng dựa trên các quy tắc sau:
//•	Bắt đầu bằng hai ký tự đầu của shippingType, viết in hoa.
//•	Kế đến là ba ký tự cuối của customerCode.
//•	Cuối cùng là ngày và tháng từ orderDate (theo định dạng "ddMM").
//Ví dụ: Nếu đơn hàng có mã khách hàng là "C123456", ngày đặt hàng là "2023-10-05", và loại giao hàng là "Express", thì mã orderCode sẽ là "EX4560510".
//c. Cập nhật giá trị orderCode trong đối tượng Order.
//d. Triệu gọi phương thức submitObject để gửi đối tượng Order đã được xử lý trở lại server.
//e. Kết thúc chương trình client.
package kiem_tra;

import java.rmi.*;
import java.rmi.registry.*;
import RMI.ObjectService; 
import RMI.Order;         
import java.io.Serializable; 

/**
 *
 * @author hoang
 */
public class B21DCCN393_6vc43pX2 { // Ten class: MSV_qCode

    public static void main(String[] args) throws Exception {
        // a. Triệu gọi phương thức requestObject
        Registry rg = LocateRegistry.getRegistry("203.162.10.109", 1099);
        ObjectService sv = (ObjectService) rg.lookup("RMIObjectService");

        // Nhan doi tuong (Phai ep kieu sang Order)
        Order donHang = (Order) sv.requestObject("B21DCCN393", "6vc43pX2");
        System.out.println( donHang);

        // b. Tao ma orderCode
        String code = "";

        // 1. Hai ký tự đầu của shippingType, viết in hoa
        // Vi du: "Express" -> "EX"
        code += donHang.getShippingType().substring(0, 2).toUpperCase();

        // 2. Ba ký tự cuối của customerCode
        // Vi du: "C123456" -> "456"
        String maKH = donHang.getCustomerCode();
        code += maKH.substring(maKH.length() - 3);

        // 3. Ngay va thang tu orderDate (dinh dang "ddMM")
        // Vi du: "2023-10-05" -> parts = ["2023", "10", "05"]
        String ngayDat = donHang.getOrderDate();
        String[] parts = ngayDat.split("-");
        code += parts[2]; // Ngay "05"
        code += parts[1]; // Thang "10"
        
        // Ket qua vi du: "EX" + "456" + "05" + "10" -> "EX4560510"

        // c. Cập nhật giá trị orderCode trong đối tượng Order
        donHang.setOrderCode(code);
        
        System.out.println(donHang);

        // d. Triệu gọi phương thức submitObject
        sv.submitObject("B21DCCN393", "6vc43pX2", donHang);

    }
}