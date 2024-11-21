import java.security.MessageDigest;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.util.HexFormat;

public class Hashes {

    public int npass = 0; // Variable para contar los intentos de fuerza bruta

    // Método SHA-512 con salt
    public String getSHA512AmbSalt(String pw, String salt) throws Exception {
        MessageDigest md = MessageDigest.getInstance("SHA-512");
        md.update(salt.getBytes());
        byte[] hash = md.digest(pw.getBytes());
        HexFormat hex = HexFormat.of();
        return hex.formatHex(hash);
    }

    // Método PBKDF2 con salt
    public String getPBKDF2AmbSalt(String pw, String salt) throws Exception {
        PBEKeySpec spec = new PBEKeySpec(pw.toCharArray(), salt.getBytes(), 65536, 128);
        SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        byte[] hash = skf.generateSecret(spec).getEncoded();
        HexFormat hex = HexFormat.of();
        return hex.formatHex(hash);
    }

    // Método de fuerza bruta con 6 bucles for anidados
    public String forcaBruta(String alg, String hash, String salt) throws Exception {
        char[] charset = "abcdefABCDEF1234567890!".toCharArray();
        int charsetLength = charset.length;
        this.npass = 0;

        for (int i = 0; i < charsetLength; i++) {
            for (int j = 0; j < charsetLength; j++) {
                for (int k = 0; k < charsetLength; k++) {
                    for (int l = 0; l < charsetLength; l++) {
                        for (int m = 0; m < charsetLength; m++) {
                            for (int n = 0; n < charsetLength; n++) {
                                this.npass++;

                                // Construir la combinación actual de la contraseña
                                String candidate = "" + charset[i] + charset[j] + charset[k] + charset[l] + charset[m] + charset[n];
                                candidate = candidate.trim(); // Eliminar espacios en blanco para longitudes menores

                                // Generar el hash de la combinación actual
                                String generatedHash;
                                if (alg.equals("SHA-512")) {
                                    generatedHash = getSHA512AmbSalt(candidate, salt);
                                } else if (alg.equals("PBKDF2")) {
                                    generatedHash = getPBKDF2AmbSalt(candidate, salt);
                                } else {
                                    throw new IllegalArgumentException("Algoritmo desconocido: " + alg);
                                }

                                // Comparar el hash generado con el hash objetivo
                                if (generatedHash.equals(hash)) {
                                    return candidate;
                                }
                            }
                        }
                    }
                }
            }
        }
        return null; // No se encontró la contraseña
    }

    // Método para calcular el intervalo de tiempo
    public String getInterval(long t1, long t2) {
        long millis = t2 - t1;
        long seconds = millis / 1000 % 60;
        long minutes = millis / (1000 * 60) % 60;
        long hours = millis / (1000 * 60 * 60) % 24;
        long days = millis / (1000 * 60 * 60 * 24);
        return String.format("%d dies / %d hores / %d minuts / %d segons / %d millis",
                days, hours, minutes, seconds, millis % 1000);
    }

    // Método main
    public static void main(String[] args) throws Exception {
        String salt = "qpoweiruañslkdfjz";
        String pw = "aaabF!";
        Hashes h = new Hashes();

        String[] aHashes = { h.getSHA512AmbSalt(pw, salt), h.getPBKDF2AmbSalt(pw, salt) };
        String[] algorismes = { "SHA-512", "PBKDF2" };

        for (int i = 0; i < aHashes.length; i++) {
            System.out.printf("===========================\n");
            System.out.printf("Algorisme: %s\n", algorismes[i]);
            System.out.printf("Hash: %s\n", aHashes[i]);
            System.out.printf("---------------------------\n");
            System.out.printf("-- Inici de força bruta ---\n");

            long t1 = System.currentTimeMillis();
            String pwTrobat = h.forcaBruta(algorismes[i], aHashes[i], salt);
            long t2 = System.currentTimeMillis();

            System.out.printf("Pass   : %s\n", pwTrobat);
            System.out.printf("Provats: %d\n", h.npass);
            System.out.printf("Temps  : %s\n", h.getInterval(t1, t2));
            System.out.printf("---------------------------\n\n");
        }
    }
}