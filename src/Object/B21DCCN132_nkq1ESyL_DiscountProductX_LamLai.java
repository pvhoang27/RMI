/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
//[Mã câu hỏi (qCode): nkq1ESyL].  Một chương trình (tạm gọi là RMI Server) cung cấp các mã khuyến mãi sản phẩm ngẫu nhiên cho sinh viên, được mô tả như sau:
//•	Giao diện từ xa
//    public interface ObjectService extends Remote {
//        public Serializable requestObject(String studentCode, String qAlias) throws RemoteException;
//
//        public void submitObject(String studentCode, String qAlias, Serializable object) throws RemoteException;
//    }
//•	Lớp ProductX gồm các thuộc tính id String, code String, discountCode String, discount int.
//o	Một hàm khởi dựng với đầy đủ các thuộc tính liệt kê ở trên
//o	Trường dữ liệu: private static final long serialVersionUID = 20171107; 
//•	Đối tượng triệu gọi từ xa được đăng ký RegistryServer với tên: RMIObjectService
//•	Tất cả các lớp được viết trong package RMI
//
//Yêu cầu là xây dựng một chương trình client thực hiện các tương tác với hệ thống phần mềm ở trên theo kịch bản dưới đây:
//1.	  Triệu gọi phương thức từ xa requestObject từ RMI Server với tham số đầu vào là mã sinh viên, mã câu để nhận về đối tượng ProductX
//2.    Nhận về đối tượng ProductX từ RMI Server với giá trị ban đầu đã được thiết lập. Tính tổng các chữ số nằm trong chuỗi mã giảm giá (discountCode) để ra giá trị được khuyến mãi của sản phẩm và cập nhật giá trị của khuyến mãi (discount)
//3.	Triệu gọi phương thức từ xa submitObject với tham số đầu vào là đối tượng Product đã được cập nhật đầy đủ thông tin giá trị khuyến mãi
//4.	Kết thúc chương trình
package Object;
import RMI.ObjectService;
import RMI.ProductX;
import java.rmi.*;
import java.rmi.registry.*;

/**
 *
 * @author hoang
 */
public class B21DCCN132_nkq1ESyL_DiscountProductX_LamLai {
    public static void main(String[] args) throws Exception{
        String studentCode = "B21DCCN132", qCode= "nkq1ESyL";
        Registry rg = LocateRegistry.getRegistry("203.162.10.109");
        ObjectService sv = (ObjectService) rg.lookup("RMIObjectService");
        ProductX product = (ProductX) sv.requestObject(studentCode, qCode);
        System.out.println(product);
        
        String disCode = product.getDiscountCode();
        int sum = 0 ;
        for(int i = 0 ; i < disCode.length();i++){
            char c = disCode.charAt(i);
            if(Character.isDigit(c)) sum += (c-'0');
        }
        System.out.println(sum);
        product.setDiscount(sum);
        sv.submitObject(studentCode, qCode, product);
    }
}
