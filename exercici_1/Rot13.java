// programa que xifra i desxifra cadenes de string

public class Rot13 {
    public static final char [] lletres = {'a', 'b', 'c', 'ç', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'ñ', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};

    public static void main(String[] args) {
       
        String cadena = ""; 

        System.out.println("Text a xifrar?");
        String entrada = Entrada.readLine();   // sola

        for (int i = 0; i <= entrada.length()-1; i++) {
             for(int z = 0; z < lletres.length; z++ ) {
                if(entrada.charAt(i) == lletres[z]) {
                    if((lletres[z] + 13) > lletres.length) {
                       int correcio = ((lletres[z+ 13]) - lletres.length);
                       cadena = cadena + lletres[correcio];
                    } else {
                        cadena = cadena + lletres[z + 13];
                    }
                }
             }

        }
        System.out.println(cadena);  
        System.out.println("");
    }

}
