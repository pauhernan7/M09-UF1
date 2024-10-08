import java.util.Collections;
import java.util.ArrayList;
import java.util.List;

public class Monoalfabetic {

    public static final char [] abecedariOriginalMaj = {'A','À', 'Á', 'B', 'C', 'Ç', 'D', 'E', 'È', 'É', 'F', 'G', 'H', 'I', 'Ì', 'Í', 'Ï', 'J', 'K', 'L', 'M', 'N', 'Ñ', 'O', 'Ò', 'Ó', 'P', 'Q', 'R', 'S', 'T', 'U', 'Ú', 'Ù', 'Ü', 'V', 'W', 'X', 'Y', 'Z'};
    
    public static final char [] abecedariOriginalMin = new char[abecedariOriginalMaj.length];
    
    public static char abecedariPermutat [] = new char [abecedariOriginalMaj.length];
    

    public static char [] permutaAlfabet(char [] abecedari) {
        
        List<Character> llistaCharacters = new ArrayList<>();
        for (int i = 0; i < abecedari.length; i++) {
            llistaCharacters.add(abecedari[i]);
        }
        Collections.shuffle(llistaCharacters);
        
        char abecedariPermutat [] = new char [llistaCharacters.size()];

        for (int i = 0; i < llistaCharacters.size(); i++) {
            abecedariPermutat[i] = llistaCharacters.get(i);
        }
        return abecedariPermutat;

    }

    
    public static String xifraMonoAlfa(String entrada) {
        String cadenaXifrada = "";

        for(int i = 0; i < abecedariOriginalMaj.length;i++) {
            abecedariOriginalMin[i] = Character.toLowerCase(abecedariOriginalMaj[i]);
        }

        for(int i = 0; i <= entrada.length()-1; i ++) {
            for(int z = 0; z < abecedariOriginalMaj.length; z++) {
                if(Character.isUpperCase(entrada.charAt(i))) {
                    if(entrada.charAt(i) == abecedariOriginalMaj[z]) {
                        int posicio = z;
                        cadenaXifrada = cadenaXifrada + abecedariPermutat[posicio];
                    }
                }
                else {
                    if(entrada.charAt(i) == abecedariOriginalMin[z]) {
                        int posicio = z;
                        cadenaXifrada = cadenaXifrada + Character.toLowerCase(abecedariPermutat[posicio]);
                    }
                }
                
            }
        }
        return cadenaXifrada;
    }



    
    public static void main(String[]args) {
        String proves [] = {"aBCXYZGÚÚaghfjsz"};
        
        abecedariPermutat = permutaAlfabet(abecedariOriginalMaj);
        for (int i = 0; i < proves.length; i++) {
        String guardarXifrat = xifraMonoAlfa(proves[i]);
        System.out.println(guardarXifrat);  
        
        }
        

    }
}









//colletions.shuffle     list de characters