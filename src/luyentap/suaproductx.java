/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package luyentap;

import RMI.ObjectService;
import RMI.ProductX;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 *
 * @author hoang
 */
public class suaproductx {
    public static void main(String[] args) throws Exception{
        String studentCode = "B21DCCN414", qCode = "UTza8yby";
        Registry rg = LocateRegistry.getRegistry("203.162.10.109");
        ObjectService sv = (ObjectService) rg.lookup("RMIObjectService");
        ProductX a  = (ProductX) sv.requestObject(studentCode, qCode);
        System.out.println(a);
        String b = a.getDiscountCode();
        int sum = 0;
        for(int i = 0 ; i < b.length(); i++){
            char c = b.charAt(i);
            if(Character.isDigit(c)){
                sum += (c - '0');
            }
        }
        a.setDiscount(sum);
        sv.submitObject(studentCode, qCode, a);
    }
}
