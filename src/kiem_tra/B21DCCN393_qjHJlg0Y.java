/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
//[Mã câu hỏi (qCode): qjHJlg0Y].  Một chương trình (tạm gọi là RMI Server) cung cấp giao diện cho phép triệu gọi từ xa để xử lý chuỗi.
//Giao diện từ xa:
//public interface CharacterService extends Remote {
//public String requestCharacter(String studentCode, String qCode) throws RemoteException;
//public void submitCharacter(String studentCode, String qCode, String strSubmit) throws RemoteException;
//}
//Trong đó:
//•	Interface CharacterService được viết trong package RMI.
//•	Đối tượng cài đặt giao diện từ xa CharacterService được đăng ký với RegistryServer với tên là: RMICharacterService.
//Yêu cầu: Viết chương trình tại máy trạm (RMI client) để thực hiện các công việc sau với chuỗi được nhận từ RMI Server:
//a. Triệu gọi phương thức requestCharacter để nhận chuỗi JSON ngẫu nhiên từ server với định dạng: "Chuỗi JSON đầu vào".
//b. Phân tích cú pháp chuỗi JSON nhận được và trích xuất các cặp key: value dựa trên vị trí của chúng:
//•	Các cặp key: value ở vị trí chẵn sẽ được đưa vào chuỗi đầu tiên.
//•	Các cặp key: value ở vị trí lẻ sẽ được đưa vào chuỗi thứ hai.
//•	Hai chuỗi kết quả sẽ được nối với nhau và phân tách bởi dấu ;
//Ví dụ: Chuỗi JSON ban đầu {"name": "Alice", "age": 25, "city": "Wonderland", "country": "Fictionland"} -> Kết quả trích xuất: "name: Alice, city: Wonderland; age: 25, country: Fictionland".
//c. Triệu gọi phương thức submitCharacter để gửi chuỗi kết quả trích xuất đã được định dạng trở lại server.
//d. Kết thúc chương trình client.
package kiem_tra;

import java.rmi.*;
import java.rmi.registry.*;
import RMI.CharacterService; // Import interface o buoc 1
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author hoang
 */
public class B21DCCN393_qjHJlg0Y { 
    public static void main(String[] args) throws Exception {
        // a. Triệu gọi phương thức requestCharacter
        Registry rg = LocateRegistry.getRegistry("203.162.10.109", 1099);
        CharacterService sv = (CharacterService) rg.lookup("RMICharacterService");

        // Nhận chuỗi JSON
        String data = sv.requestCharacter("B21DCCN393", "qjHJlg0Y");
        System.out.println( data);

        // b. Phân tích cú pháp chuỗi JSON (Xu ly chuoi)
        // Loai bo { va } o dau va cuoi
        String content = data.substring(1, data.length() - 1);

        // Tach cac cap key:value
        String[] pairs = content.split(", ");

        // Dung List de luu
        List<String> chan = new ArrayList<>();
        List<String> le = new ArrayList<>();

        for (int i = 0; i < pairs.length; i++) {
            // Xoa bo dau nhay ""
            String item = pairs[i].replace("\"", "");

            if (i % 2 == 0) { // vi tri chan
                chan.add(item);
            } else { // vi tri le
                le.add(item);
            }
        }

        // Ghep cac chuoi lai
        String resChan = String.join(", ", chan);
        String resLe = String.join(", ", le);

        // Ket qua cuoi cung
        String ketQua = resChan + "; " + resLe; 

        System.out.println( ketQua);

        // c. Triệu gọi phươngS thức submitCharacter
        sv.submitCharacter("B21DCCN393", "qjHJlg0Y", ketQua);
 
    }
}