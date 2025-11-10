/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
//[Mã câu hỏi (qCode): G9u8ZACq].  Một chương trình (tạm gọi là RMI Server) cung cấp giao diện cho phép triệu gọi từ xa để xử lý dữ liệu.
//Giao diện từ xa:
//public interface DataService extends Remote {
//public Object requestData(String studentCode, String qCode) throws RemoteException;
//public void submitData(String studentCode, String qCode, Object data) throws RemoteException;
//}
//Trong đó:
//•	Interface DataService được viết trong package RMI.
//•	Đối tượng cài đặt giao diện từ xa DataService được đăng ký với RegistryServer với tên là: RMIDataService.
//Yêu cầu: Viết chương trình tại máy trạm (RMI client) để thực hiện các công việc sau với dữ liệu nhận được từ RMI Server:
//a. Triệu gọi phương thức requestData để nhận một chuỗi các số nguyên.
//b. Sử dụng thuật toán sinh tổ hợp kế tiếp để tìm tổ hợp kế tiếp của chuỗi số này theo thứ tự từ điển. Nếu chuỗi đã là tổ hợp lớn nhất, trả về tổ hợp nhỏ nhất (sắp xếp lại từ đầu theo thứ tự từ điển).
//Ví dụ: Với chuỗi 1, 2, 3 tổ hợp kế tiếp là 1, 3, 2. Nếu chuỗi là 3, 2, 1 (tổ hợp lớn nhất), kết quả sẽ là 1, 2, 3 (tổ hợp nhỏ nhất).
//c. Triệu gọi phương thức submitData để gửi chuỗi (String) chứa tổ hợp kế tiếp đã tìm được trở lại server.
//d. Kết thúc chương trình client.
package kiem_tra;

import java.rmi.*;
import java.rmi.registry.*;
import RMI.DataService; 
import java.util.Arrays; 

/**
 *
 * @author hoang
 */
public class B21DCCN393_G9u8ZACq { // Ten class: MSV_qCode

    public static void main(String[] args) throws Exception {
        // a. Triệu gọi phương thức requestData
        Registry rg = LocateRegistry.getRegistry("203.162.10.109", 1099);
        DataService sv = (DataService) rg.lookup("RMIDataService");

        // Nhan du lieu (Server tra ve Object, nhung ta biet no la String)
        String dataStr = (String) sv.requestData("B21DCCN393", "G9u8ZACq");
        System.out.println(dataStr);

        // b. Xu ly sinh hoan vi ke tiep (vi du la hoan vi)
        
        // Chuyen chuoi "1, 2, 3" thanh mang int[] {1, 2, 3}
        String[] parts = dataStr.split(", ");
        int n = parts.length;
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(parts[i]);
        }

        // Thuat toan sinh hoan vi ke tiep
        int i = n - 2;
        // 1. Tim i lon nhat tu phai sang trai ma a[i] < a[i+1]
        while (i >= 0 && a[i] >= a[i + 1]) {
            i--;
        }

        if (i < 0) {
            // Day la hoan vi lon nhat (vi du: 3, 2, 1)
            // Sap xep lai tu dau (vi du: 1, 2, 3)
            Arrays.sort(a);
        } else {
            // 2. Tim j > i lon nhat ma a[j] > a[i]
            int j = n - 1;
            while (a[j] <= a[i]) {
                j--;
            }

            // 3. Doi cho a[i] va a[j]
            int temp = a[i];
            a[i] = a[j];
            a[j] = temp;

            // 4. Dao nguoc phan con lai (tu i + 1 den het)
            int left = i + 1;
            int right = n - 1;
            while (left < right) {
                temp = a[left];
                a[left] = a[right];
                a[right] = temp;
                left++;
                right--;
            }
        }
        
        // Chuyen mang int[] ket qua thanh String "1, 3, 2"
        StringBuilder sb = new StringBuilder();
        for (int k = 0; k < n; k++) {
            sb.append(a[k]);
            if (k < n - 1) {
                sb.append(",");
            }
        }
String ketQua = sb.toString();

        System.out.println( ketQua);

        // c. Triệu gọi phương thức submitData (Gui di String)
        sv.submitData("B21DCCN393", "G9u8ZACq", ketQua);

    }
}