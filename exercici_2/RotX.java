public class RotX {

    public static final char [] lletresMaj = {'A', 'Á', 'B', 'C', 'D', 'E', 'É', 'F', 'G', 'H', 'I', 'Í', 'J', 'K', 'L', 'M', 'N', 'Ñ', 'O', 'Ó', 'P', 'Q', 'R', 'S', 'T', 'U', 'Ú', 'V', 'W', 'X', 'Y', 'Z'};
    public static final char [] lletresMin = {'a', 'á', 'b', 'c', 'd', 'e', 'é', 'f', 'g', 'h', 'i', 'í', 'j', 'k', 'l', 'm', 'n', 'ñ', 'o', 'ó', 'p', 'q', 'r', 's', 't', 'u', 'ú', 'v', 'w', 'x', 'y', 'z'};
    public static void main(String [] args) {
        String provesText [] = {"!!! abcABCxyzXYZ !!!", "!!!!BONdia???"};
        int provesPosicio [] = {10,100};

        String provesDesxifrar [] = {"!!! ijkIJKfghFGH !!!", "!!!!PDBrwo???"};
        int posicioDesxifra [] = {10,100}; 
        

        for(int i = 0; i < provesText.length; i ++) {
            
            //prova xifrat
            System.out.println("Text a xifrar:");
            System.out.println(provesText[i]);
            String guardarXifrat = xifraRotX(provesText[i], provesPosicio[i]);
            System.out.println("Text xifrat:");
            System.out.println(guardarXifrat);
            
            //prova desxifrat
            String guardarDesxifrat = desxifraRotX(provesDesxifrar[i], posicioDesxifra[i]);
            System.out.println("Text desxifrat:");
            System.out.println(guardarDesxifrat);
            System.out.println();  
        }

        System.out.println("forcaBrutaRotX:");
        for(int y = 0; y < provesDesxifrar.length; y++) {
            for(int x = 0; x < lletresMaj.length; x ++) {
                String guardarForcaBruta = desxifraRotX(provesDesxifrar[y], x);
                System.out.println(guardarForcaBruta);

            }
        }

    }

    public static String xifraRotX(String entrada, int posicio) {

        String cadenaXifrada = "";
    

        for(int i = 0; i <= entrada.length()-1; i++) {
            if(Character.isLetter(entrada.charAt(i))) {
                if(Character.isUpperCase(entrada.charAt(i))) {
                    for(int z = 0; z < lletresMaj.length; z++) {
                        if(entrada.charAt(i) == lletresMaj[z]) {
                            int posicioNova = posicio + z;
                            if(posicioNova >= lletresMaj.length) {
                                posicioNova = posicioNova % lletresMaj.length;
                                cadenaXifrada = cadenaXifrada + lletresMaj[posicioNova];
                            }
                            else {
                                cadenaXifrada = cadenaXifrada + lletresMaj[posicioNova];
                            }

                        }
                    }
                }
                else {
                    for(int z = 0; z < lletresMin.length; z++) {
                        if(entrada.charAt(i) == lletresMin[z]) {
                            int posicioNova = posicio + z;
                            if(posicioNova >= lletresMin.length) {
                                posicioNova = posicioNova % lletresMin.length;
                                cadenaXifrada = cadenaXifrada + lletresMin[posicioNova];
                            }
                            else {
                                cadenaXifrada = cadenaXifrada + lletresMin[posicioNova];
                            }

                        }
                    }
                }
                
            }
            else {
                cadenaXifrada = cadenaXifrada + entrada.charAt(i);
            }
        }

        return cadenaXifrada;
    }

    public static String desxifraRotX(String entrada, int posicio) {

        String cadenaDesxifrada = "";

        for(int i = 0; i <= entrada.length()-1; i++) {
            if(Character.isLetter(entrada.charAt(i))) {
                if(Character.isUpperCase(entrada.charAt(i))) {
                    for(int z = 0; z < lletresMaj.length; z++) {
                        if(entrada.charAt(i) == lletresMaj[z]) {
                            int posicioNova = z - posicio;
                            if(posicioNova < 0) {
                                posicioNova = Math.abs(posicioNova) % lletresMaj.length;
                                cadenaDesxifrada = cadenaDesxifrada + lletresMaj[lletresMaj.length-posicioNova];
                            }
                            else {
                                cadenaDesxifrada = cadenaDesxifrada + lletresMaj[posicioNova];
                            }
                            break;
                                
                        }

                        }
                }
                else {
                    for(int z = 0; z < lletresMin.length; z++) {
                        if(entrada.charAt(i) == lletresMin[z]) {
                            int posicioNova = z - posicio;
                            if(posicioNova < 0) {
                                posicioNova = Math.abs(posicioNova) % lletresMin.length;
                                cadenaDesxifrada = cadenaDesxifrada + lletresMin[lletresMin.length-posicioNova];
                            }
                            else {
                                cadenaDesxifrada = cadenaDesxifrada + lletresMin[posicioNova];
                            }
                            break;
                               
                        }

                    }
                }      
            }    
            else {
                cadenaDesxifrada = cadenaDesxifrada + entrada.charAt(i);
            }
        }
        return cadenaDesxifrada;
    }


}


