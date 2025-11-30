/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package luyentap;
import RMI.ByteService;
import java.rmi.*;
import java.rmi.registry.*;
import java.util.ArrayList;
import java.util.List;
public class tpsang16 {
    public static void main(String[] args) throws Exception{
        String studentCode = "B21DCCN414", qCode = "PNkKHoB3";
        Registry rg = LocateRegistry.getRegistry("203.162.10.109");
        ByteService sv = (ByteService) rg.lookup("RMIByteService");
        byte []a = sv.requestData(studentCode, qCode);
        String kq = "";
        
        for(int i = 0 ; i < a.length; i++){
             String hex = Integer.toHexString(a[i]);
             kq += hex;
           
        }
        sv.submitData(studentCode, qCode, kq.getBytes());
    }
}
