import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Base64;

public class AES {
    // Constants
    public static final String ALGORISME_XIFRAT = "AES";
    public static final String ALGORISME_HASH = "SHA-256";
    public static final String FORMAT_AES = "AES/CBC/PKCS5Padding";
    private static final int MIDA_IV = 16;
    private static final String CLAU = "LaClauSecretaQueVulguis";

    // Mètode per xifrar
    public static byte[] xifraAES(String msg, String clau) throws Exception {
        // Obtenir bytes del missatge
        byte[] msgBytes = msg.getBytes("UTF-8");

        // Generar un vector d'inicialització (IV)
        byte[] iv = new byte[MIDA_IV];
        SecureRandom random = new SecureRandom();
        random.nextBytes(iv);
        IvParameterSpec ivSpec = new IvParameterSpec(iv);

        // Fer hash de la clau amb SHA-256
        MessageDigest digest = MessageDigest.getInstance(ALGORISME_HASH);
        byte[] clauBytes = digest.digest(clau.getBytes("UTF-8"));

        // Crear SecretKeySpec amb la clau generada
        SecretKeySpec secretKey = new SecretKeySpec(clauBytes, ALGORISME_XIFRAT);

        // Inicialitzar el xifratge
        Cipher cipher = Cipher.getInstance(FORMAT_AES);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivSpec);

        // Xifrar el missatge
        byte[] xifrat = cipher.doFinal(msgBytes);

        // Combinar IV i el missatge xifrat
        byte[] ivIMsgXifrat = new byte[iv.length + xifrat.length];
        System.arraycopy(iv, 0, ivIMsgXifrat, 0, iv.length);
        System.arraycopy(xifrat, 0, ivIMsgXifrat, iv.length, xifrat.length);

        return ivIMsgXifrat;
    }

    // Mètode per desxifrar
    public static String desxifraAES(byte[] bIvIMsgXifrat, String clau) throws Exception {
        // Extreure l'IV
        byte[] iv = Arrays.copyOfRange(bIvIMsgXifrat, 0, MIDA_IV);
        IvParameterSpec ivSpec = new IvParameterSpec(iv);

        // Extreure la part xifrada del missatge
        byte[] xifrat = Arrays.copyOfRange(bIvIMsgXifrat, MIDA_IV, bIvIMsgXifrat.length);

        // Fer hash de la clau amb SHA-256
        MessageDigest digest = MessageDigest.getInstance(ALGORISME_HASH);
        byte[] clauBytes = digest.digest(clau.getBytes("UTF-8"));

        // Crear SecretKeySpec amb la clau generada
        SecretKeySpec secretKey = new SecretKeySpec(clauBytes, ALGORISME_XIFRAT);

        // Inicialitzar el desxifratge
        Cipher cipher = Cipher.getInstance(FORMAT_AES);
        cipher.init(Cipher.DECRYPT_MODE, secretKey, ivSpec);

        // Desxifrar el missatge
        byte[] desxifrat = cipher.doFinal(xifrat);

        return new String(desxifrat, "UTF-8");
    }

    // Mètode principal per provar el xifrat i desxifrat
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
                // Xifrar el missatge
                bXifrats = xifraAES(msg, CLAU);
                
                // Desxifrar el missatge
                desxifrat = desxifraAES(bXifrats, CLAU);
            } catch (Exception e) {
                System.err.println("Error de xifrat: " + e.getLocalizedMessage());
            }

            // Mostrar resultats
            System.out.println("--------------------");
            System.out.println("Msg: " + msg);
            System.out.println("Enc: " + Base64.getEncoder().encodeToString(bXifrats)); // Convertim a Base64 per mostrar en text
            System.out.println("DEC: " + desxifrat);
        }
    }
}
