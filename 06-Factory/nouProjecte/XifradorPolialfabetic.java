import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
import java.util.Random;

public class XifradorPolialfabetic {
    public static final char[] abecedariMaj = {
            'A', 'Á', 'À', 'B', 'C', 'Ç', 'D', 'E', 'É', 'È', 'F', 'G', 'H', 'I', 'Í', 'Ì', 'Ï', 'J', 'K', 'L', 'M',
            'N', 'Ñ', 'O', 'Ó', 'Ò', 'Ö', 'P', 'Q', 'R', 'S', 'T', 'U', 'Ú', 'Ù', 'Ü', 'V', 'X', 'Y', 'Z'
    };
    public static final String[] proves = { "!!!!Bon DIaaaaaa??", "M'agrada programaarRRRRR??  ", "BONA niiit???" };

    private static Random random;

    
    public static char[] permutaAlfabet() {
        List<Character> lletresList = new ArrayList<>();
        for (char c : abecedariMaj) {
            lletresList.add(c);
        }
        Collections.shuffle(lletresList, random); 
        char[] lletresPermutades = new char[lletresList.size()];
        for (int i = 0; i < lletresList.size(); i++) {
            lletresPermutades[i] = lletresList.get(i);
        }
        return lletresPermutades;
    }

    
    public static String xifraPoliAlfa(String cadena, String password) {
        random = new Random(password.hashCode()); 
        StringBuilder nCadena = new StringBuilder();
        for (int z = 0; z < cadena.length(); z++) {
            char[] alfabetNou = permutaAlfabet(); 
            char c = cadena.charAt(z);
            if (Character.isLetter(c)) {
                if (Character.isUpperCase(c)) {
                    for (int i = 0; i < abecedariMaj.length; i++) {
                        if (c == abecedariMaj[i]) {
                            nCadena.append(alfabetNou[i]);
                            break;
                        }
                    }
                } else {
                    for (int i = 0; i < abecedariMaj.length; i++) {
                        if (Character.toUpperCase(c) == abecedariMaj[i]) {
                            nCadena.append(Character.toLowerCase(alfabetNou[i]));
                            break;
                        }
                    }
                }
            } else {
                nCadena.append(c);
            }
        }
        return nCadena.toString();
    }

    
    public static String desxifraPoliAlfa(String cadena, String password) {
        random = new Random(password.hashCode()); 
        StringBuilder nCadena = new StringBuilder();
        for (int z = 0; z < cadena.length(); z++) {
            char[] alfabetNou = permutaAlfabet(); 
            char c = cadena.charAt(z);
            if (Character.isLetter(c)) {
                if (Character.isUpperCase(c)) {
                    for (int i = 0; i < alfabetNou.length; i++) {
                        if (c == alfabetNou[i]) {
                            nCadena.append(abecedariMaj[i]);
                            break;
                        }
                    }
                } else {
                    for (int i = 0; i < alfabetNou.length; i++) {
                        if (Character.toUpperCase(c) == alfabetNou[i]) {
                            nCadena.append(Character.toLowerCase(abecedariMaj[i]));
                            break;
                        }
                    }
                }
            } else {
                nCadena.append(c);
            }
        }
        return nCadena.toString();
    }

    public static void main(String[] args) {
        String password = "4567654"; 
        System.out.println("Text a xifrar: ");
        for (String cadena : proves) {
            String xifrat = xifraPoliAlfa(cadena, password);
            System.out.println(cadena + " --> " + xifrat);
        }
        System.out.println("\nText per desxifrar: ");
        for (String cadena : proves) {
            String xifrat = xifraPoliAlfa(cadena, password);
            String desxifrat = desxifraPoliAlfa(xifrat, password);
            System.out.println(xifrat + " --> " + desxifrat);
        }
    }
}