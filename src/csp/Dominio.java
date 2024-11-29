package csp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import problema.Evento;
import problema.Periodo;
import problema.Tutorado;

/**
 * @author Rafael Rivera-Lopez
 */

//Una lista de Objetos, eonde representa los diferentes valores de una variable
public class Dominio extends ArrayList<Evento> {
  
  public Dominio(Evento[] objetos){
    this.addAll(Arrays.asList(objetos));
  }

   public Dominio(){}
  
  
}


