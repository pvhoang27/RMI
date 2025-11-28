/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
//[Mã câu hỏi (qCode): qaFQfIxY].  Một chương trình (tạm gọi là RMI server) cung cấp giao diện cho phép triệu gọi từ xa với thông tin như sau:
//-	Giao diện từ xa
//    public interface ObjectService extends Remote {
//        public Serializable requestObject(String studentCode, String qAlias) throws RemoteException;
//
//        public void submitObject(String studentCode, String qAlias, Serializable object) throws RemoteException;
//    }
//-	Lớp Product gồm các thông tin: id String, code String, importPrice double, exportPrice double.
//    Trường dữ liệu: private static final long serialVersionUID = 20151107L;
//    02 hàm khởi dựng 
//        public Product()
//        public Product(id String, String code,double ImportPrice, double ExportPrice)
//Trong đó:
//-	interface ObjectService và lớp Product được viết trong package RMI
//-	Đối tượng cài đặt giao diện từ xa ObjectService được đăng ký với RegistryServer: RMIObjectService
//
//Yêu cầu yêu cầu viết chương trình tại máy trạm (RMI client) thực hiện chuẩn hóa sản phẩm theo thứ tự:
//a.	Triệu gọi phương thức requestObject để lấy về đối tượng sản phẩm cần chuẩn hóa.
//b.	Thực hiện chuẩn hóa đối tượng nhận được theo nguyên tắc:
//        - Chuyển mã sản phẩm thành in hoa.
//        - Cập nhật giá xuất (exportPrice) bằng giá nhập (importPrice) + 20%
//
//c.  Triệu gọi phương thức submitObject để gửi dữ liệu đã chuẩn hóa
//d.  Kết thúc chương trình client
// phải nói là ai làm ra cái đề này , thực sự là tệ luôn , đề bài ko rõ ràng ,phần class thì ghi sai chữ hoa chữ thường
// câu này thứ nhất chuyển mã thành chữ hoa là chuyển code chứ ko phải chuyển id
// thứ 2 , *0,2 là 0,2 của importPrice
// thu 3 la  cau nay de y de bai la chu i vs e  trong importPrice vs exportPricela viet thuong nhe , de bai .. 

package luyentap;
import RMI.ObjectService;
import RMI.Product;
import java.rmi.*;
import java.rmi.registry.*;

/**
 *
 * @author hoang
 */
public class B21DCCN550_qaFQfIxY_CapNhatGiaProduct {
    public static void main(String[] args) throws Exception{
        String studentCode ="B21DCCN550", qCode = "qaFQfIxY";
        Registry rg = LocateRegistry.getRegistry("203.162.10.109");
        ObjectService sv = (ObjectService) rg.lookup("RMIObjectService");
        Product product = (Product) sv.requestObject(studentCode, qCode);
        System.out.println(product);
        // thu 3 la  cau nay de y de bai la chu i vs e  trong importPrice vs exportPricela viet thuong nhe , de bai ..
        double exportP = product.getExportPrice();
        double importP = product.getImportPrice();
        // thứ 2 , *0,2 là 0,2 của importPrice
        double newExport =  (double) importP + 0.2 * importP ;
        // nhớ là phải làm tròn 2 chữ số sau số thập phân
        newExport = (double) Math.round(newExport * 100) / 100;
        System.out.println(newExport);
        product.setExportPrice(newExport);
        // câu này thứ nhất chuyển mã thành chữ hoa là chuyển code chứ ko phải chuyển id
        String code = product.getCode();
        String newCode = code.toUpperCase();
        System.out.println(newCode);
        product.setCode(newCode);
        
        sv.submitObject(studentCode, qCode, product);
        
    }
}
