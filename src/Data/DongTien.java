package Data;

//[Mã câu hỏi (qCode): sD8np9CE].  Một chương trình (tạm gọi là RMI Server) cung cấp giao diện cho phép triệu gọi từ xa để xử lý dữ liệu.
//Giao diện từ xa:
//public interface DataService extends Remote {
//public Object requestData(String studentCode, String qCode) throws RemoteException;
//public void submitData(String studentCode, String qCode, Object data) throws RemoteException;
//}
//Trong đó:
//•	Interface DataService được viết trong package RMI.
//•	Đối tượng cài đặt giao diện từ xa DataService được đăng ký với RegistryServer với tên là: RMIDataService.
//Yêu cầu: Viết chương trình tại máy trạm (RMI client) để thực hiện các công việc sau với dữ liệu nhận được từ RMI Server:
//a. Triệu gọi phương thức requestData để nhận một số nguyên dương amount từ server, đại diện cho số tiền cần đạt được.
//b. Sử dụng thuật toán xếp đồng xu với các mệnh giá cố định [1, 2, 5, 10] để xác định số lượng đồng xu tối thiểu cần thiết để đạt được số tiền amount. Nếu không thể đạt được số tiền đó với các mệnh giá hiện có, trả về -1.
//Ví dụ: Với amount = 18 và mệnh giá đồng xu cố định [1, 2, 5, 10], kết quả là 4 (18 = 10 + 5 + 2 + 1). Chuỗi cần gửi lên server là: 4; 10,5,2,1
//c. Triệu gọi phương thức submitData để gửi chuỗi (kiểu String) chứa kết quả số lượng đồng xu tối thiểu và giá trị các đồng xu tương ứng  trở lại server.
//d. Kết thúc chương trình client.
import java.rmi.*;
import java.rmi.registry.*;
import java.util.ArrayList;
import java.util.List;
public class DongTien {
    public static void main(String[] args) throws Exception{
        String studentCode = "B21DCCN132", qCode = "sD8np9CE";
        Registry rg = LocateRegistry.getRegistry("203.162.10.109");
        DataService sv =(DataService) rg.lookup("RMIDataService");
        Object a = sv.requestData(studentCode, qCode);
        System.out.println(a);
        int amount = (int) a;
        int [] coins = {10,5,2,1};
        int cnt = 0 ;
        String xu ="";
        
        for(int x : coins){
            while(amount >=  x){
                amount -= x;
                cnt ++;
                xu += (x + ",");
            }
        }
        xu = xu.substring(0, xu.length()-1);
        System.out.println(xu);
        String kq = cnt + "; "+ xu;
        System.out.println(kq);
        sv.submitData(studentCode, qCode, kq);
        
        
    }
}
