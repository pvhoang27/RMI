package luyentap;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import RMI.ByteService;
import java.rmi.*;
import java.rmi.registry.*;

/**
 *
 * @author hoang
 */
public class B21DCCN550_FQonQwVi_ChiaMangChanLe_LamLaiLanNua {
    public static void main(String[] args) throws Exception{
        String studentCode = "B21DCCN550",qCode = "FQonQwVi";
        Registry rg = LocateRegistry.getRegistry("203.162.10.109");
        ByteService sv = (ByteService) rg.lookup("RMIByteService");
        byte[] a = sv.requestData(studentCode, qCode);
//        System.out.println(a);
        int index = 0;
        byte[] kq = new byte[a.length];
        // dùng thử kiểu này thấy cũng hay hay mà ngắn gọn
        for(var x : a ){
            if( x % 2 == 0 ){
                kq[index] = x;
                index ++;
            }
        }
        System.out.println(index);
        for(var x : a ){
            if( x % 2 != 0 ){
                kq[index] = x;
                index ++;
            }
        }
        System.out.println(index);
        sv.submitData(studentCode, qCode, kq);
        
    }
}
