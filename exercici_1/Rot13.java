// programa que xifra i desxifra textos


import java.util.Scanner;

public class Rot13 {
    public static final char [] lletresMin = {'a', 'á', 'b', 'c', 'd', 'e', 'é', 'f', 'g', 'h', 'i', 'í', 'j', 'k', 'l', 'm', 'n', 'ñ', 'o', 'ó', 'p', 'q', 'r', 's', 't', 'u', 'ú', 'v', 'w', 'x', 'y', 'z'};
    public static final char [] lletresMaj = {'A', 'Á', 'B', 'C', 'D', 'E', 'É', 'F', 'G', 'H', 'I', 'Í', 'J', 'K', 'L', 'M', 'N', 'Ñ', 'O', 'Ó', 'P', 'Q', 'R', 'S', 'T', 'U', 'Ú', 'V', 'W', 'X', 'Y', 'Z'};

    public static String xifraRot13 (String entrada) {  

        String cadena = "";   
         
            for (int i = 0; i <= entrada.length()-1; i++) {
                    if(Character.isLetter(entrada.charAt(i))) {
                        if(Character.isUpperCase(entrada.charAt(i))) {
                            for(int z = 0; z < lletresMaj.length; z++ ) {
                                if(entrada.charAt(i) == lletresMaj[z]) {
                                    if(( + z + 13) > lletresMaj.length) {
                                        int correcio = z + 13 - lletresMaj.length;
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
                                    if(( z + 13) > lletresMin.length) {
                                        int correcio = z + 13 - lletresMin.length;
                                        cadena = cadena + lletresMin[correcio];
                                    } else {
                                        int correcio2 = z + 13;
                                        cadena = cadena + lletresMin[correcio2];
                                    }
                                }
                            }
                        }
                    }
                    else {
                        cadena = cadena + entrada.charAt(i);
                    }
                    
            }
            return cadena;  
    } 
        
       
        // desxifraRot13
        public static String desxifraRot13 (String entrada) {

            String cadenaDesxifrat = "";

            for(int i = 0; i <= entrada.length()-1; i++) {
                if(Character.isLetter(entrada.charAt(i))) {
                    if(Character.isUpperCase(entrada.charAt(i))) {
                        for(int z = 0; z < lletresMaj.length; z++) {
                            if(entrada.charAt(i) == lletresMaj[z]) {
                                if(z - 13 < 0) {
                                    int correcio3 = z - 13 + lletresMaj.length;
                                    cadenaDesxifrat = cadenaDesxifrat + lletresMaj[correcio3];
                                } else {
                                    int correcio3 = z - 13;
                                    cadenaDesxifrat = cadenaDesxifrat + lletresMaj[correcio3];
                                }
                            }
                        }
                    } 
                    else {
                        if(Character.isLowerCase(entrada.charAt(i))) {
                            for(int z = 0; z < lletresMin.length; z++) {
                                if(entrada.charAt(i) == lletresMin[z]) {
                                    if(z - 13 < 0) {
                                        int correcio3 = z - 13 + lletresMin.length;
                                        cadenaDesxifrat = cadenaDesxifrat + lletresMin[correcio3];
                                    } 
                                    else {
                                        int correcio4 = z - 13;
                                        cadenaDesxifrat = cadenaDesxifrat + lletresMin[correcio4];
                                    }
                                }
                            }
                        }
                    }
                }
                else {
                    cadenaDesxifrat = cadenaDesxifrat + entrada.charAt(i);
                }
                    
            }
            return cadenaDesxifrat;  
        }


    public static void main (String[] args) {
        Scanner scanner = new Scanner(System.in);


        System.out.println("Que vols xifrar o desxifrar?");
        String opcio = scanner.nextLine();

        if (opcio.equals("xifrar")) {
            System.out.println("Text a xifrar?");
            String entrada = scanner.nextLine();
            String guardarXifrat = xifraRot13(entrada);
            System.out.println(guardarXifrat);
        }
        else if(opcio.equals("desxifrar")) {
            System.out.println("Text a desxifrar?");
            String entrada = scanner.nextLine();
            String guardarDesxifrat = desxifraRot13(entrada);
            System.out.println(guardarDesxifrat);
        }
        else {
            System.out.println("Només pots " + "'" + "xifrar" + "'" + " o " + "'" + "desxifrar" + "'" + ".");
        }

        scanner.close();

    }
}