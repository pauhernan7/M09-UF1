import java.util.Collections;
import java.util.ArrayList;
import java.util.List;

public class Monoalfabetic {

    public static final char [] abecedariOriginal = {'A','À', 'Á', 'B', 'C', 'Ç', 'D', 'E', 'È', 'É', 'F', 'G', 'H', 'I', 'Ì', 'Í', 'Ï', 'J', 'K', 'L', 'M', 'N', 'Ñ', 'O', 'Ò', 'Ó', 'P', 'Q', 'R', 'S', 'T', 'U', 'Ú', 'Ù', 'Ü', 'V', 'W', 'X', 'Y', 'Z'}; 
    
    public static char abecedariPermutat [] = new char [abecedariOriginal.length];
    

    public static void permutaAlfabet(char [] abecedari) {
        
        List<Character> llistaCharacters = new ArrayList<>();
        for (int i = 0; i < abecedariOriginal.length; i++) {
            llistaCharacters.add(abecedariOriginal[i]);
        }
        Collections.shuffle(llistaCharacters);
        
        char abecedariPermutat [] = new char [llistaCharacters.size()];

        for (int i = 0; i < llistaCharacters.size(); i++) {
            abecedariPermutat[i] = llistaCharacters.get(i);
        }

    }

    



    
    public static void main(String[]args) {
        permutaAlfabet(abecedariOriginal);
        
    }
}









//colletions.shuffle     list de characters