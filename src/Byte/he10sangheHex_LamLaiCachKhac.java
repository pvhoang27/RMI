/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Byte;

import RMI.ByteService;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 *
 * @author hoang
 */
public class he10sangheHex_LamLaiCachKhac {
    public static void main(String[] args) throws Exception{
        String studentCode = "B21DCCN633", qCode = "lqBueT9h";
        Registry rg = LocateRegistry.getRegistry("203.162.10.109");
        ByteService sv = (ByteService) rg.lookup("RMIByteService");
        byte [] a = sv.requestData(studentCode, qCode);
        byte [] kq = new byte[a.length];
        String kq1 = "";
        for(int i = 0 ; i < a.length; i++){
            int n = a[i];
            String h = Integer.toHexString(n);
            kq1 += h;
        }
        kq = kq1.getBytes();
        sv.submitData(studentCode, qCode, kq);
    }
}
