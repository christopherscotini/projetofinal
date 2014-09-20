package br.com.mkoffice.utils;
public class ContadorTempoUtils {
	    
	    public static long tempInicial;
	    
	    public static long tempFinal;
	         
	    public static void comecar(){
	        tempInicial = System.nanoTime();  
	    }
	        
	    public static void parar(){
	        tempFinal = System.nanoTime();  
	        
	        long dif = (tempFinal - tempInicial);          
	       //status  um JLabel q tenho na minha aplicacao.
	        System.out.println(String.format("Tempo de execução: %04d segundos  e %04d milisegundos", dif/1000000000, dif%1000000000));        
	    }
}
