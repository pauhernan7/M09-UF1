// programa que xifra i desxifra cadenes de string

public class Rot13 {
    public static final char [] lletresMin = {'a', 'á', 'b', 'c', 'd', 'e', 'é', 'f', 'g', 'h', 'i', 'í', 'j', 'k', 'l', 'm', 'n', 'ñ', 'o', 'ó', 'p', 'q', 'r', 's', 't', 'u', 'ú', 'v', 'w', 'x', 'y', 'z'};
    public static final char [] lletresMaj = {'A', 'Á', 'B', 'C', 'D', 'E', 'É', 'F', 'G', 'H', 'I', 'Í', 'J', 'K', 'L', 'M', 'N', 'Ñ', 'O', 'Ó', 'P', 'Q', 'R', 'S', 'T', 'U', 'Ú', 'V', 'W', 'X', 'Y', 'Z'};
    public static void main(String[] args) {
       
        String cadena = "";
        String cadena2 = "";
        int posicio = 0; 
        boolean tipusLletra;

        System.out.println("Text a xifrar?");
        String entrada = Entrada.readLine();   // sola

        for (int i = 0; i <= entrada.length()-1; i++) {
            if(Character.isUpperCase(entrada.charAt(i))) {
                for(int z = 0; z < lletresMaj.length; z++ ) {
                    if(entrada.charAt(i) == lletresMaj[z]) {
                        if((posicio + z + 13) > lletresMaj.length) {
                            int correcio = posicio + z + 13 - lletresMaj.length;
                           cadena = cadena + lletresMaj[correcio];
                        } 
                        else {
                            int correcio2 = z + 13;
                            cadena = cadena + lletresMaj[correcio2];
                        }
                    }
                 }
            }
            else {
                for(int z = 0; z < lletresMin.length; z++ ) {
                    if(entrada.charAt(i) == lletresMin[z]) {
                        if((posicio + z + 13) > lletresMin.length) {
                            int correcio = posicio + z + 13 - lletresMin.length;
                           cadena = cadena + lletresMin[correcio];
                        } 
                        else {
                            int correcio2 = z + 13;
                            cadena = cadena + lletresMin[correcio2];
                        }
                    }
                }
            }
            

        }
        System.out.println(cadena);  
        System.out.println("");
    }

}
