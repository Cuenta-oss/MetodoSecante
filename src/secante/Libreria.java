/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package secante;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;
import org.lsmp.djep.djep.DJep;
import org.nfunk.jep.Node;

/**
 * @author Fernando
 * Clase para probar las librerias de derivar y calcular una funcion
 */
public class Libreria {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        String funcion = "2*x^2";
        
        Expression e = new ExpressionBuilder(funcion)
                .variables("x")
                .build()
                .setVariable("x", 3);
        
        double resultado = e.evaluate();
        
        System.out.println(resultado);
        
        System.out.println("Funcion: "+funcion);
        String derivada = derivar(funcion);
        System.out.println("Derivada: "+derivada);
        
    }
    
    public static String derivar(String funcion) {
        String derivada = "";
        DJep resultado = new DJep();
        resultado.addStandardFunctions(); //permite agregar sin cos tan
        resultado.addStandardConstants(); //permite constantes e y pi
        resultado.addComplex(); //permite tener numeros complejos
        resultado.setAllowUndeclared(true); //permite variables no declarables
        resultado.setAllowAssignment(true); //permite asignaciones
        resultado.setImplicitMul(true); //permite añadir reglas de multiplicacion
        resultado.addStandardDiffRules(); //permite añadir reglas de diferenciacion
        
        try {
            //nodes que pertenecen a la libreria
            Node node = resultado.parse(funcion); //funcion establecida
            Node diff = resultado.differentiate(node, "x"); //crear respecto a la variable
            Node sim = resultado.simplify(diff); //simplificar la ecuacion
            derivada = resultado.toString(sim); //obtener resultado (convertirlo a String)
            
            
        } catch (Exception e) {
            System.out.println("Ocurrio un error al momento de derivar");
        }
        
        return derivada;
        
    }
    
}
