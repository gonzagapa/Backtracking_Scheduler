package csp;

import java.util.*;
import problema.Evento;
/**
 * @author Rafael Rivera-Lopez
 */

//Clase que representa la definicion de un problema CSP
public abstract class CSP {
  //Modificar la declaracion de variables y dominios.
  private List<String> variables;
  private HashMap<String, Dominio> dominios; //Guarda los dominios de las diferentes variables
  private List<Restriccion> restricciones;
  private HashMap<String, List<Restriccion>> restriccionesDeVariable; //Lista de restricciones por Variable

  public abstract void crearVariables();

  public abstract void crearDominios();

  public abstract void crearRestricciones();

  public final void configurarProblema() {
    variables = new ArrayList();
    //dominio es una Lista [v1,v2,v3] de una sola variable
    dominios = new HashMap(); //dominios: Guardar la relacion variable y sus valores {x1=dominio,x2,=dominio}
   
    
    restricciones = new ArrayList();
    restriccionesDeVariable = new HashMap();
    crearVariables();
    crearDominios();
//    for (String var : variables) {
//      restriccionesDeVariable.put(var, new ArrayList());
//    }
    crearRestricciones();
  }

  public List<String> getVariables() {
    return variables;
  }

  //Cambiarlo por un objeto tipo evento*
  public void addVariable(String variable){
    variables.add(variable);
  } 
  

  //Obtenemos una varibale 
  public String getVariable(int k){
    return variables.get(k);
  }

  
  //Cambiar esta variable
  public Dominio getDominio(String var) {
    return dominios.get(var);
  }
  
  //Obtiene los valores de una respectiva variable 
  public Dominio getDominio(int k){
    return dominios.get(variables.get(k));
  }
  
  

  //Probablemente no ocupemos este dominio
  public void setDominio(String var, Dominio dominio) {
    dominios.put(var, dominio);
  }

  public List<Restriccion> getRestricciones() {
    return restricciones;
  }

  public List<Restriccion> getRestricciones(String variable) {
    return restriccionesDeVariable.get(variable);
  }

  public void addRestriccion(Restriccion restriccion) {
    restricciones.add(restriccion);
//    for (String var : restriccion.getVariables()) {
//      restriccionesDeVariable.get(var).add(restriccion);
//    }
  }  
}
