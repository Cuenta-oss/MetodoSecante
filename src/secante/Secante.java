/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package secante;

/**
 *
 * @author Fernando
 */
public class Secante {

    public static double f(double x) { //funcion
        double resultado = - 11 - (22*x) + (17*Math.pow(x, 2)) - (2.5*Math.pow(x, 3));
        return resultado;
    }
    
    public static double errorRelativo(double a, double b) { //formula error relativo
        double resultado = Math.abs( ( (a-b) / a )*100 );
        return resultado;
    }
    
    public static double secante(double a, double b) { //formula secante (a = xi, b = xi-1)
        double resultado = a - ( f(a)*(b-a) ) / ( f(b)-f(a) ); //resultado = xi+1
        return resultado;
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        //valores
        double a = 0; // -> xi
        double b = -1; // -> xi-1
        double c = 0; // -> xi+1
        
        double ea = 0.004; //máximo error permitido
        double error = 0, errorAnterior = 0; //valores para guardar los errores
        double calculoError = 0; //valor para guadar el error relativo
        int iteraciones = 0; //número de iteraciones
        
        do {       
            errorAnterior = error; //guardar error anterior
            
            c = secante(a, b); //calcular xi+1
            error = c; //guardar error actual
            
            calculoError = errorRelativo(error, errorAnterior); //calcular error relativo
            
            
            iteraciones++;
            
            System.out.println(b+" - "+a);
            
            //cambio de columnas para la siguiente iteración
            b = a;
            a = c;
            
            
        } while (calculoError > ea && a != b);
        
        //imprimir resultados
        System.out.println("Iteraciones = "+iteraciones);
        System.out.println("Lo que vale c = "+c);
        System.out.println("Valor de f en c = "+f(c));
        
        
    }
    
}
