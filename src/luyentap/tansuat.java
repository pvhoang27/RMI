/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package luyentap;
import RMI.CharacterService;
import java.rmi.*;
import java.rmi.registry.*;
public class tansuat {
    public static void main(String[] args)  throws  Exception{
        String studentCode = "B22DCCN019",qCode = "CccXt7wV";
        Registry rg = LocateRegistry.getRegistry("203.162.10.109");
        CharacterService sv = (CharacterService) rg.lookup("RMICharacterService");
        String a  = sv.requestCharacter(studentCode, qCode);
        System.out.println(a);
        String kq ="{";
        int [] cnt = new int[256];
        for(int i = 0 ; i < a.length(); i++){
            char c = a.charAt(i);
            cnt[c] ++;
        }
        for(int i = 0 ; i < a.length(); i++){
            char c = a.charAt(i);
            if(cnt[c] > 0 ){
                kq += ( "\"" + c + "\"" + ": "  + cnt[c]+", ");
                cnt[c] = 0 ;
            }
        }
        System.out.println(kq);
        kq = kq.substring(0, kq.length()-2);
        kq = kq + "}";
        System.out.println(kq);
        sv.submitCharacter(studentCode, qCode, kq);
    }
}
