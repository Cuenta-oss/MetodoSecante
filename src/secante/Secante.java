/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package secante;

import java.util.Scanner;
import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

/**
 *
 * @author Fernando
 * Clase para obtener la raíz de una función por el método de la secante
 */
public class Secante {
    
    public static double f(String funcion, double x) { //funcion
        Expression e = new ExpressionBuilder(funcion) //clase de la libreria exp4j que lee funciones
                .variables("x") //se establece la variable x
                .build() //se construye
                .setVariable("x", x); //establecer lo que vale x
        
        double resultado = e.evaluate(); //se evalua la función
        return resultado;
    }
    
    public static double errorRelativo(double actual, double anterior) { //formula error relativo
        double resultado = Math.abs( ( (actual-anterior) / actual )*100 );
        return resultado;
    }
    
    public static double secante(String funcion, double a, double b) { //formula secante (a = xi, b = xi-1)
        double resultado = a - ( f(funcion, a)*(b-a) ) / ( f(funcion, b)-f(funcion, a) ); //resultado (c) = xi+1
        return resultado;
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //leer funcion (-11-22x+17x^2-2.5x^3)
        String funcion;
        System.out.println("Escribe la función: ");
        Scanner leerFuncion = new Scanner(System.in);
        funcion = leerFuncion.next();
        
        //leer valores
        double b; // -> xi-1 (-1)       
        System.out.println("Primer límite: ");
        Scanner leerB = new Scanner(System.in);
        b = leerB.nextDouble();

        double a; // -> xi (0)
        System.out.println("Segundo límite: ");
        Scanner leerA = new Scanner(System.in);
        a = leerA.nextDouble();

        double c = 0; // -> xi+1
        
        //leer máximo error permitido (0.004)
        double ea;
        System.out.println("Máximo error permitido: ");
        Scanner leerEA = new Scanner(System.in);
        ea = leerEA.nextDouble();
        
        System.out.println("----------");
        
        double error = 0, errorAnterior = 0; //valores para guardar los errores
        double calculoError = 0; //valor para guadar el error relativo
        int iteraciones = 0; //número de iteraciones
        
        do {       
            errorAnterior = error; //guardar error anterior
            
            c = secante(funcion, a, b); //calcular xi+1
            error = c; //guardar error actual
            
            calculoError = errorRelativo(error, errorAnterior); //calcular error relativo
            
            iteraciones++;
            
            System.out.println(b+" // "+a);
            System.out.println("Error = "+calculoError+"%");
            System.out.println("----------");
            
            //cambio de columnas para la siguiente iteración
            b = a;
            a = c;
            
        } while (calculoError > ea && a != b);
        
        //imprimir resultados
        System.out.println("Iteraciones = "+iteraciones);
        System.out.println("Lo que vale la raiz = "+c);
        System.out.println("Valor de la raiz en f = "+f(funcion, c));
        
    }
    
}
