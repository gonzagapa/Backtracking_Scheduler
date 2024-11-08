package satisfaccionderestricciones;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author Rafael Rivera-Lopez
 */

//Una lista de Objetos, eonde representa los diferentes valores de una variable
public class Dominio extends ArrayList<Object> {
  
  public Dominio(Object[] objetos){
    this.addAll(Arrays.asList(objetos));
  }

}


