package csp;

import java.util.List;
import problema.Evento;

/**
 * @author Rafael Rivera-Lopez
 */

//Interfaz aplicada para una sola restriccion. 
public interface Restriccion {

  List<String> getVariables();

  boolean esSatisfecha(Estado asignacion);
}


