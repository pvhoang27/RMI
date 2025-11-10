/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kiem_tra;
import java.rmi.*;
import java.rmi.registry.*;
import RMI.ByteService;
import java.util.Arrays;
/**
 *
 * @author hoang
 */
public class B21DCCN393_9f2ErAGc {
    public static void main(String[] args) throws Exception {
        //  Triệu gọi phương thức requestData
        Registry rg = LocateRegistry.getRegistry("203.162.10.109", 1099);
        ByteService sv = (ByteService) rg.lookup("RMIByteService");

        // Nhận dữ liệu (MSV và qCode truyền trực tiếp)
        byte[] data = sv.requestData("B21DCCN393", "9f2ErAGc");
        
         System.out.println( Arrays.toString(data));

        // Thực hiện phân chia chẵn-lẻ
        int n = data.length;
        byte[] dataPhanChia = new byte[n];
        int index = 0; // Biến vị trí cho mảng kết quả

        // Dua cac byte chan vao truoc
        for (int i = 0; i < n; i++) {
            if (data[i] % 2 == 0) {
                dataPhanChia[index++] = data[i];
            }
        }

        // Dua cac byte le vao sau
        for (int i = 0; i < n; i++) {
            if (data[i] % 2 != 0) {
                dataPhanChia[index++] = data[i];
            }
        }
        
         System.out.println(Arrays.toString(dataPhanChia));

        // gui ket qua len sv
        sv.submitData("B21DCCN393", "9f2ErAGc", dataPhanChia);
    }   
}
