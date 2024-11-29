package csp;

import problema.Evento;
import problema.Problema;

/*
    X = {1,2,3,4}
    Y = {1,2,3,4}
    X != y
    X = 1, Y = 2
    
    
*/


/**
 * @author Rafael Rivera-Lopez
 */
public class BacktrackingCronologico {

  //private final CSP problema; //Objeto con la definicion y elementos del problema
  //Varibales, dominios y restricciones. 
  private Problema problema = null;

//  public BacktrackingCronologico(CSP problema) {
//    this.problema = problema;
//    
//  }
  public BacktrackingCronologico(Problema p){
      this.problema = p;
  }

  public Estado buscarSolucion() {
    Estado asignacion = new Estado();
    
    //Si nos falta alumnos que no tiene el minimo numero de sesion, k tomara la posicion de alumno por asignar
    int k = problema.getListaTutorados().indexOf(problema.obtenerTutoradoSinMinimoNumeroSesion());
    
    //Si k tiene -1, quiere decir que no hay alumnos que les haga falta una sesion
    //Por lo que asignamos el indice del primer tutorado que solo tenga una sola sesion, para que vuelva a iniciar la busqueda
    k = (k == -1) ? problema.getListaTutorados().indexOf(problema.obtenerTutoradoSinMaximoNumSesion()): k;
    System.out.println("Valor de k:" + k);
      System.out.println("Periodos restantes pasados:" + Problema.getPeriodosRestantesSemana().size());
    
    return backtrackingRecursivo(k, asignacion);
  }
  
  
  //k:lleva el control de las asignaciones hechas. Valor inicial = 1
  //Estado: tupla de asignaciones {(xi,vi),(xj,vj)} = (e1,(period,salon,tutorado)) (e2,periodo,salon,tutorado)
  private Estado backtrackingRecursivo(int k, Estado estado) {
    seleccionar(k, estado);  
   
    //Si las asignaciones satisfacen las restricciones 
    if (comprobar(k, estado)) { 
        //Evaluar la cantidad de periodos ya asignados 
        //Evaluar si ya llegamos a la cantidad maxima de periodos por semana  O si ya no tenemos mas variables disponibles
        if(Problema.dominio.getPeriodosYaAsignados().size()-1 == Problema.periodos.size() -1){
            System.out.println("EXITO!");
            
            //Limpiamos los periodos restantes
             Problema.getPeriodosRestantesSemana().clear();

            
            //Si ya terminamos de asignar los periodos de este mes, generamos nuevos para el siguiente 
            Problema.dominio.generarNuevosPeriodos();
            
           
            return estado; //FIN   
            //Volver a generar periodos
           
        }
        else if(k == problema.getVariables().size() - 1){
            System.out.println("EXITO!");
            //Asignamos periodos que sobraron al problema
            Problema.setPeriodosRestantesSemana(Problema.dominio.getPeriodosPorAsignar());
            
            //En dando caso que tengamos periodos sobrantes, esto se agregan a los periodos del dominio
            //if(!Problema.getPeriodosRestantesSemana().isEmpty()) Problema.dominio.generarNuevosPeriodos(Problema.getPeriodosRestantesSemana());
            
            //Generamos nuevos periodos para las futuras iteraciones
            Problema.dominio.generarNuevosPeriodos();
            System.out.println("Periodos ya asignados " + Problema.dominio.getPeriodosYaAsignados());
            return estado; //FIN  
        }
        
//        if (k == problema.getVariables().size() - 1) {
//              System.out.println("EXITO!");
//                return estado; //FIN          
//        } 
        else {
        return backtrackingRecursivo(k + 1, estado); //Si nos quedan asignaciones pendietes
      }
        
    } 
    
    //Caso que una asignacion parcial viole alguna restriccion. 
    else {
      if (quedanValores(k, estado)) {
        System.out.println("Otro valor para k=" + k + " (" + problema.getVariable(k) + ")");
        return backtrackingRecursivo(k, estado); // Intentamos con otra asignacion
      } 
      //Si estamos en el estado inicial y ninguno asignacion satisface las restricciones
      else if (k == 0) {
        System.out.println("No hay solución!!");
        return null;
      } 
      
      else { //Probamos con cambiando la asignacion de la variable anterior. 
        estado.eliminarAsignacion(problema.getVariable(k));
        System.out.println("Regresa a la variable " + (k - 1));
        return backtrackingRecursivo(k - 1, estado);
      }
    }
  }

  //Selecciona un valor inicial o cambia el valor de una variable k
  public void seleccionar(int k, Estado estado) {
    //?:Al momento de generar las variables y dominion, estos deben estar en orden
    String key = problema.getVariable(k); //Obtienes una variable K ----- Ej: evento1
    
    Dominio dominio = problema.getDominio(k); //Obtienes el dominiok la varibale k
    
    Evento value = estado.get(key); //Obtienes el valor del estado
    
    
    //Modificar
    boolean cambioLugar = false;
    //Si value no es null quiere decir que la variable ya tiene un valor asignado
    if (value != null) { 
      //lugar = dominio.indexOf(value) + 1; //Cambiamos de posicion 
        System.out.println("Entro aqui cuando ya tiene un valor asignado");
       
        
        //Indicamos que queremos asignar otro periodo al mismo evento.
        cambioLugar =  true; //Posible Error, en cuanto a los salones. 
        
        
    }
    //Comprobamos si todavia hay valores en el dominio de la variable
    //Entra si hay valores en el dominio o si es nuestra primera asignacion
    
    //Checar si todavia tenemos periodos disponibles 
    if(problema.getDominioEvento().existenciaPeriodosDisponibles() !=0) {
        estado.setAsignacion(key, dominio.get(0),k,cambioLugar);//Le asignamos un nuevo valor
        
        //Esto nos ayudara para acceder a la ultima variable asignada de Estado
        problema.variablesAsignadas.add(key);
    }
    else {
        
        estado.setAsignacion(key,null,0, cambioLugar);
    }
    
    
//    if (lugar < dominio.size()) {
//      estado.setAsignacion(key, dominio.get(lugar)); //Le asignamos un nuevo valor 
//    } else {
//      estado.setAsignacion(key, null);  //Si ya no hay valores en el dominio de la variable, le coloca null. 
//    }
  }

  
  private boolean comprobar(int k, Estado estado) {
     //Si el estado k cotieneua asignacion
    if (estado.tieneAsignacion(problema.getVariable(k))) {
      for (int i = 0; i < k; i++) {
         
        //String var = problema.getVariable(i); //Comprobamos la variable 
        //Comprobamos  que cada variable cumpla con sus restricciones 
        
        //Aqui se puede comprobar la restriccionEventoPeriodo: H1
        Restriccion rest = problema.getRestricciones().getFirst();
        if (!rest.esSatisfecha(estado)) {
              System.out.println("No se satisface la restriccion");
            return false; //Si una variable no cumple con restriccion, se sale del ciclo
          }
//        for (Restriccion rest : problema.getRestricciones()) {
//          if (!rest.esSatisfecha(estado)) {
//              System.out.println("No se satisface la restriccion");
//            return false; //Si una variable no cumple con restriccion, se sale del ciclo
//          }
//        }
      }
        System.out.println("Es satisfecha");
      return true; //Cada una de las variables del problema cumplen con sus restricciones
    } else {
      
        //Agregar una restriccion que compruebe la cantidad de tutorados por tutor, al terminal la calendarizacion.
      return false;
    }
  }

  //Comprueba si quedan valores en la variable, en nuestro si todavia quedan periodos
  private boolean quedanValores(int k, Estado asignacion) {
    String var = problema.getVariable(k);
    //Dominio dominio = problema.getDominio(k);
    if (asignacion.tieneAsignacion(var)) { //Si tiene un valor asignado la variable k
      //Evento valor = asignacion.getAsignacion(var); //Obtenemos el valor asignado a variable k
      //valor.quitarValores();
      
      return problema.getDominioEvento().existenciaPeriodosDisponibles() !=0;
      //return dominio.indexOf(valor) < dominio.size() - 1; //Comprobamos que todavia quedan valores asignados
    }
    return false; //Caso que no tenga un valor asignado
  }


 
}

