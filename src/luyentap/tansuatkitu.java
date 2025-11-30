/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package luyentap;

import RMI.CharacterService;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 *
 * @author hoang
 */
public class tansuatkitu {
    public static void main(String[] args) throws Exception{
        String studentCode = "B21DCCN414", qCode = "NYipzc4v";
        Registry rg = LocateRegistry.getRegistry("203.162.10.109");
        CharacterService sv = (CharacterService) rg.lookup("RMICharacterService");
        String a = sv.requestCharacter(studentCode, qCode);
        System.out.println(a);
        int [] cnt = new int[256];
        String kq = "";
        for(int i = 0  ; i < a.length(); i++){
            char c = a.charAt(i);
            cnt[c] ++;
        }
        for(int i = 0  ; i < a.length(); i++){
            char c = a.charAt(i);
            if(cnt[c] > 0 ){
                kq += c + ""+ cnt[c] ;
                cnt[c] = 0;
            }
        }
        sv.submitCharacter(studentCode, qCode, kq);
    }
}
