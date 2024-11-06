import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Base64;

public class XifradorAES {

    public static final String ALGORISME_XIFRAT = "AES";
    public static final String ALGORISME_HASH = "SHA-256";
    public static final String FORMAT_AES = "AES/CBC/PKCS5Padding";
    private static final int MIDA_IV = 16;
    private static final String CLAU = "123456";

    
    public static byte[] xifraAES(String msg, String clau) throws Exception {
        byte[] msgBytes = msg.getBytes("UTF-8");

        byte[] iv = new byte[MIDA_IV];
        SecureRandom random = new SecureRandom();
        random.nextBytes(iv);
        IvParameterSpec ivSpec = new IvParameterSpec(iv);

        MessageDigest digest = MessageDigest.getInstance(ALGORISME_HASH);
        byte[] clauBytes = digest.digest(clau.getBytes("UTF-8"));

        SecretKeySpec secretKey = new SecretKeySpec(clauBytes, ALGORISME_XIFRAT);

        Cipher cipher = Cipher.getInstance(FORMAT_AES);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivSpec);

        byte[] xifrat = cipher.doFinal(msgBytes);

        byte[] ivIMsgXifrat = new byte[iv.length + xifrat.length];
        System.arraycopy(iv, 0, ivIMsgXifrat, 0, iv.length);
        System.arraycopy(xifrat, 0, ivIMsgXifrat, iv.length, xifrat.length);

        return ivIMsgXifrat;
    }

    public static String desxifraAES(byte[] bIvIMsgXifrat, String clau) throws Exception {
        
        byte[] iv = Arrays.copyOfRange(bIvIMsgXifrat, 0, MIDA_IV);
        IvParameterSpec ivSpec = new IvParameterSpec(iv);

        byte[] xifrat = Arrays.copyOfRange(bIvIMsgXifrat, MIDA_IV, bIvIMsgXifrat.length);

        MessageDigest digest = MessageDigest.getInstance(ALGORISME_HASH);
        byte[] clauBytes = digest.digest(clau.getBytes("UTF-8"));

        
        SecretKeySpec secretKey = new SecretKeySpec(clauBytes, ALGORISME_XIFRAT);

        Cipher cipher = Cipher.getInstance(FORMAT_AES);
        cipher.init(Cipher.DECRYPT_MODE, secretKey, ivSpec);

        byte[] desxifrat = cipher.doFinal(xifrat);

        return new String(desxifrat, "UTF-8");
    }

    public static void main(String[] args) {
        String[] msgs = {
            "Bon dia no m'agrada programar",
            "Hola Andrés cómo está tu cuñado",
            "Ismael que fas??"
        };

        for (String msg : msgs) {
            byte[] bXifrats = null;
            String desxifrat = "";
            try {
                bXifrats = xifraAES(msg, CLAU);
                
                desxifrat = desxifraAES(bXifrats, CLAU);
            } catch (Exception e) {
                System.err.println("Error de xifrat: " + e.getLocalizedMessage());
            }

            System.out.println("--------------------");
            System.out.println("Msg: " + msg);
            System.out.println("Enc: " + new String(bXifrats)); 
            System.out.println("DEC: " + desxifrat);
        }
    }
}
