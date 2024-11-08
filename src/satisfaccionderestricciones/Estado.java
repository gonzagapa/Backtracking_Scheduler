package satisfaccionderestricciones;

import java.util.*;
/**
 * @author Rafael Rivera-López
 */

//Tienes que String es una key y Object su respectivo valor. 
public class Estado extends HashMap<String, Object> {

  public List<String> getVariables() {
    String[] variables = (String[]) this.keySet().toArray();
    return Arrays.asList(variables);
  }

  public Object getAsignacion(String var) {
    return this.get(var);
  }

  //Agregar valores al Estado o cambiar el valor de una respectiva key
  public void setAsignacion(String key, Object valor) {
     this.put(key, valor);
  }
  
  public void eliminarAsignacion(String var) {
    this.remove(var);
  }

  //Verificamos que si la variable var tiene un valor asignado. 
  //true: si tiene un valor, false: caso contrario. 
  public boolean tieneAsignacion(String var) {
    return this.get(var)!=null;
  }

  @Override
  public String toString() {
    Iterator<String> iterador = this.keySet().iterator();
    String result = "{";
    while (iterador.hasNext()) {
      String key = iterador.next();
      Object valor = this.get(key);
      if (valor != null) {
        result += key + " = " + valor + ", ";
      }
    }
    result += "}";
    return result.replace(", }", "}");
  }
}
