package satisfaccionderestricciones;

import java.util.*;
import problema.Evento;
import problema.Periodo;
import problema.Problema;
import problema.Tutorado;
/**
 * @author Rafael Rivera-López
 */

//Tienes que String es una key y Object su respectivo valor. 
//
public class Estado extends HashMap<String, Evento> {

  public List<String> getVariables() {
    String[] variables = (String[]) this.keySet().toArray();
    return Arrays.asList(variables);
  }

  public Evento getAsignacion(String var) {
    return this.get(var);
  }

  
  //La variable sig nos indica si estamos haciendo una reasigancion de valores a un evento que ya tenia valor asignado. 
  public void setAsignacion(String key, Evento valor, int pos, boolean cambio) {
      if(valor != null){
          //Caso que se este reasigando al mismo alumno un nuevo periodo 
          if(valor.getTutorado().getSesiones() > 0 && cambio == true) valor.getTutorado().disminuirSesion();
          
          //Con sig, nos movemos un periodo hacia adelante para asignarle a valor otro periodo
        Periodo periodo = Problema.dominio.getPeriodosPorAsignar().getFirst();
        
        Tutorado tutorado = Problema.dominio.getTutorados().get(pos);
        
        //Esto procura que el pos se mantenga en un rango de valores existentes. 
        String salon = Problema.dominio.getSalones().get(pos % Problema.dominio.getSalones().size() );
        // ======
       
        
        //Asignar los valores al evento
        valor.setPeriodo(periodo);
        valor.setTutorado(tutorado);
        valor.setSalon(salon);
        
        //Una vez que ya colocamos un periodo al evento, lo agregamos al lista de periodos ya asignados
        Problema.dominio.anadirPeriodosAsignados(periodo);
      }
      
        this.put(key, valor);
  }
  
  //Modificar 
  //Cuando se quiera quitar la asignacion, tambien deber quitarse el tutor asignado al tutorado
  public void eliminarAsignacion(String var) {

        if(this.get(var) != null){
             this.get(var).getPeriodo().quitarTutorado(this.getAsignacion(var).getTutorado());
                Problema.dominio.quitarPeriodoAsignado(this.get(var).getPeriodo());
        }
        System.out.println("Eliminamos el evento:" + this.remove(var));
        
  }

  //Verificamos que si la variable var tiene un valor asignado. 
  //true: si tiene un valor, false: caso contrario. 
  public boolean tieneAsignacion(String var) {
    return this.get(var)!=null;
  }
  
  public void accederUltimaVariable(){
      
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
