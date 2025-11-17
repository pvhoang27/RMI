/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
//[Mã câu hỏi (qCode): rfX6FSCd].  Một chương trình (tạm gọi là RMI Server) cung cấp giao diện cho phép triệu gọi từ xa để xử lý dữ liệu nhị phân.
//Giao diện từ xa:
//public interface ByteService extends Remote {
//public byte[] requestData(String studentCode, String qCode) throws RemoteException;
//public void submitData(String studentCode, String qCode, byte[] data) throws RemoteException;
//}
//Trong đó:
//•	Interface ByteService được viết trong package RMI.
//•	Đối tượng cài đặt giao diện từ xa ByteService được đăng ký với RegistryServer với tên là: RMIByteService.
//Yêu cầu: Viết chương trình tại máy trạm (RMI client) để thực hiện các công việc sau với dữ liệu nhị phân nhận được từ RMI Server:
//a. Triệu gọi phương thức requestData để nhận một mảng dữ liệu nhị phân (byte[]) từ server, đại diện cho một chuỗi văn bản ASCII.
//b. Thực hiện mã hóa Caesar cho mảng dữ liệu nhị phân bằng cách dịch chuyển mỗi byte trong mảng đi một số bước cố định trong bảng mã ASCII. Số bước dịch chuyển là số ký tự ASCII trong mảng dữ liệu.
//    Ví dụ: Nếu dữ liệu nhị phân nhận được là [72, 101, 108, 108, 111] (tương ứng với chuỗi "Hello"), chương trình sẽ thực hiện mã hóa Caesar với độ dịch là 5. Kết quả mã hóa là mảng [77, 108, 113, 113, 116], tương ứng với chuỗi "Mlqqt".
//c. Triệu gọi phương thức submitData để gửi mảng dữ liệu đã được mã hóa bằng Caesar trở lại server.
//d. Kết thúc chương trình client.
package Byte;
import RMI.ByteService;
import java.lang.reflect.Array;
import java.rmi.*;
import java.rmi.registry.*;
import java.util.Arrays;
/**
 *
 * @author hoang
 */
public class B21DCCN393_rfX6FSCd_MaHoaCaesar {
    public static void main(String[] args) throws Exception{
        String studentCode = "B21DCCN393";
        String qCode = "rfX6FSCd";
        Registry rg = LocateRegistry.getRegistry("203.162.10.109");
        ByteService sv = (ByteService) rg.lookup("RMIByteService");
        byte [] a = sv.requestData(studentCode, qCode);
        System.out.println(a);
        System.out.println(Arrays.toString(a));
        int n = a.length;
        System.out.println(n);
        // cach nao cung dc 
        // cach nay thi ngan gon
        String data = new String(a);
////    String data =new String(a,"ASCII"); // nma cach nay dung hon khi chi dinh han ascii , nho may bi loi
        System.out.println(data);
        
        // nhớ đoạn này xử lý này là dc 
        byte[] result = new byte[n];
        for(int i = 0 ; i < n; i++){
           result[i] += (byte) (a[i] + n);    
        }   
        System.out.println(result);
        String h = new String(result);
        System.out.println(h);
        System.out.println(Arrays.toString(result));
        sv.submitData(studentCode, qCode, result);
    }
}
