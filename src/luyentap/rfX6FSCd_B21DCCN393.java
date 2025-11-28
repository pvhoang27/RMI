package luyentap;
import java.rmi.*;
import java.rmi.registry.*;
import RMI.ByteService;

public class rfX6FSCd_B21DCCN393 {
    public static void main(String[] args) throws Exception {
        // a. Nhận dữ liệu
        Registry rg = LocateRegistry.getRegistry("203.162.10.109", 1099);
        ByteService sv = (ByteService) rg.lookup("RMIByteService");
        byte[] a = sv.requestData("B21DCCN393", "rfX6FSCd");
        for (byte x : a) System.out.print(x + " ");
        System.out.println();

        // b. Mã hóa Caesar (dịch mỗi byte theo độ dài mảng)
        for (int i = 0, n = a.length; i < n; i++) a[i] = (byte) (((a[i] & 0xFF) + n) & 0xFF);
        for (byte x : a) System.out.print(x + " ");
        System.out.println();

        // c. Gửi dữ liệu mã hóa về server
        sv.submitData("B21DCCN393", "rfX6FSCd", a);
    }
}
