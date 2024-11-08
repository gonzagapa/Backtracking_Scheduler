package satisfaccionderestricciones;

/**
 * @author Rafael Rivera-Lopez
 */
public class BacktrackingCronologico {

  private final CSP problema; //Objeto con la definicion y elementos del problema
  //Varibales, dominios y restricciones. 

  public BacktrackingCronologico(CSP problema) {
    this.problema = problema;
  }

  public Estado buscarSolucion() {
    Estado asignacion = new Estado();
    return backtrackingRecursivo(0, asignacion);
  }
  
  
  //k:lleva el control de las asignaciones hechas. Valor inicial = 1
  //Estado: tupla de asignaciones {(xi,vi),(xj,vj)} 
  private Estado backtrackingRecursivo(int k, Estado estado) {
    seleccionar(k, estado);  // selecciona un valork del dominio con la variable k
    //System.out.println(estado);
    if (comprobar(k, estado)) { //Si las asignaciones satisfacen las restricciones 
      if (k == problema.getVariables().size() - 1) { //Si ya no quedan asignaciones pendientes
        System.out.println("EXITO!");
        return estado; //FIN
      } else {
        return backtrackingRecursivo(k + 1, estado); //Si nos quedan asignaciones pendietes
      }
    } 
    
    //Caso que una asignacion parcial viole alguna restriccion. 
    else {
      if (quedanValores(k, estado)) {
        //System.out.println("Otro valor para k=" + k + " (" + problema.getVariable(k) + ")");
        return backtrackingRecursivo(k, estado); // Intentamos con otra asignacion
      } 
      //Si estamos en el estado inicial y ninguno asignacion satisface las restricciones
      else if (k == 0) {
        System.out.println("No hay solución!!");
        return null;
      } 
      
      else { //Probamos con cambiando la asignacion de la variable anterior. 
        estado.eliminarAsignacion(problema.getVariable(k));
        //System.out.println("Regresa a la variable " + (k - 1));
        return backtrackingRecursivo(k - 1, estado);
      }
    }
  }

  //Selecciona un valor inicial o cambia el valor de una variable k
  public void seleccionar(int k, Estado estado) {
    //?:Al momento de generar las variables y dominion, estos deben estar en orden
    String key = problema.getVariable(k); //Obtienes una variable K
    Dominio dominio = problema.getDominio(k); //Obtienes el dominiok la varibale k
    Object value = estado.get(key); //Obtienes el valor del estado
    
    int lugar = 0;
    if (value != null) { //Si value no es null quiere decir que la variable ya tiene un valor asignado
      lugar = dominio.indexOf(value) + 1; //Cambiamos de posicion 
    }
    //Comprobamos si todavia hay valores en el dominio de la variable
    //Entra si hay valores en el dominio o si es nuestra primera asignacion
    if (lugar < dominio.size()) {
      estado.setAsignacion(key, dominio.get(lugar)); //Le asignamos un nuevo valor 
    } else {
      estado.setAsignacion(key, null);  //Si ya valores en el dominio de la variable, le coloca null. 
    }
  }

  
  private boolean comprobar(int k, Estado estado) {
     //Si el estado k cotieneua asignacion
    if (estado.tieneAsignacion(problema.getVariable(k))) {
      for (int i = 0; i < k; i++) {
         
        String var = problema.getVariable(i); //Comprobamos la variable 
        //Comprobamos  que cada variable cumpla con sus restricciones 
        for (Restriccion rest : problema.getRestricciones(var)) {
          if (!rest.esSatisfecha(estado)) {
            return false; //Si una variable no cumple con restriccion, se sale del ciclo
          }
        }
      }
      return true; //Cada una de las variables del problema cumplen con sus restricciones
    } else {
      
        //Agregar una restriccion que compruebe la cantidad de tutorados por tutor, al terminal la calendarizacion.
      return false;
    }
  }

  //Comprueba si quedan valores en la variable
  private boolean quedanValores(int k, Estado asignacion) {
    String var = problema.getVariable(k);
    Dominio dominio = problema.getDominio(k);
    if (asignacion.tieneAsignacion(var)) { //Si tiene un valor asignado la variable k
      Object valor = asignacion.getAsignacion(var); //Obtenemos el valor asignado a variable k
      return dominio.indexOf(valor) < dominio.size() - 1; //Comprobamos que todavia quedan valores asignados
    }
    return false; //Caso que no tenga un valor asignado
  }
}
