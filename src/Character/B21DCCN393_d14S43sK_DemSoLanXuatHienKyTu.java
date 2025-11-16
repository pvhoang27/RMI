/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
//[Mã câu hỏi (qCode): d14S43sK].  Một chương trình (tạm gọi là RMI Server) cung cấp giao diện cho phép triệu gọi từ xa để xử lý chuỗi.
//Giao diện từ xa:
//public interface CharacterService extends Remote {
//public String requestCharacter(String studentCode, String qCode) throws RemoteException;
//public void submitCharacter(String studentCode, String qCode, String strSubmit) throws RemoteException;
//}
//Trong đó:
//•	Interface CharacterService được viết trong package RMI.
//•	Đối tượng cài đặt giao diện từ xa CharacterService được đăng ký với RegistryServer với tên là: RMICharacterService.
//Yêu cầu: Viết chương trình tại máy trạm (RMI client) để thực hiện các công việc sau với chuỗi được nhận từ RMI Server:
//a. Triệu gọi phương thức requestCharacter để nhận chuỗi ngẫu nhiên từ server với định dạng: "Chuỗi đầu vào".
//b. Thực hiện đếm tần số xuất hiện của mỗi ký tự trong chuỗi đầu vào và tạo ra chuỗi kết quả theo định dạng <Ký tự><Số lần xuất hiện>, sắp xếp theo thứ tự xuất hiện của các ký tự trong chuỗi.
//Ví dụ: Chuỗi đầu vào "AAABBC" -> Kết quả: "A3B2C1".
//c. Triệu gọi phương thức submitCharacter để gửi chuỗi kết quả trở lại server.
//d. Kết thúc chương trình client.
package Character;
import RMI.CharacterService;
import java.rmi.*;
import java.rmi.registry.*;
import java.util.*;

/**
 *
 * @author hoang
 */
public class B21DCCN393_d14S43sK_DemSoLanXuatHienKyTu {
    public static void main(String[] args) throws  Exception{
        // host + port ( bai nay k yeu cau port)
        Registry rg = LocateRegistry.getRegistry("203.162.10.109" );
        CharacterService sv = (CharacterService) rg.lookup("RMICharacterService");
        String data =sv.requestCharacter("B21DCCN393", "d14S43sK").trim();
        System.out.println(data);
        
        // xu ly bai toan
        int [] cnt = new int[256];
        for(int i = 0 ; i < data.length(); i++){
            char c = data.charAt(i);
            cnt[c] ++;
        }
        String kq = "";
        for(int i = 0 ; i < data.length();i++){
            char c = data.charAt(i);
            if(cnt[c] > 0){
                //ca 2 cai deu dung , deu dc nhe 
                kq += (c +""+ cnt[c]);
//              kq+= (""+c +cnt[c]);
                cnt[c] = 0;
            }
        }
        System.out.println(kq);
        
        // gui kq len sv
        sv.submitCharacter("B21DCCN393", "d14S43sK", kq);
    }
}
