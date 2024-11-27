
package problema;

import java.util.ArrayList;
import satisfaccionderestricciones.BacktrackingCronologico;
import satisfaccionderestricciones.Estado;

/**
 *
 * @author gonza
 */
public class Horario {
    private ArrayList<Mes> meses = new ArrayList();
    //Aqui indicamos si queremos que nuestra solucion incluya la restriccion suave o no 
   private static final boolean ASIGNAR_MAXIMO_NUMERO_SESIONES = false; 

    public Horario() {
    }
    
    
    
    public void generarHorario(){
        //Ingresamos los meses 
        meses.add(new Mes("Septiembre"));
        //meses.add(new Mes("Octubre"));

        //Generamos la instancia del problema
       Problema problema = new Problema();
       problema.configurarProblema();
       Estado estadoFinal = ejecutarAlgoritmo(problema, ASIGNAR_MAXIMO_NUMERO_SESIONES);   
       
      
      
       //Si estado final es diferente de nulo, entonces todos los eventos han sido asignados correctamente
       if(estadoFinal != null){
         //ejecutarAlgoritmo(problema, true);
           mostrarResultados(problema);

       }
    }
    
    private void mostrarResultados(Problema problema){
        
        
        System.out.println("\nHorario Generado");
        for(Mes mes: meses){
                
            if(!mes.getSemanas().getFirst().semanaSinEventos()) {System.out.println(mes);}
        }
        //Checar la lista de Tutorados de cada tutor
        for(Tutor tutor: Tutor.tutores){
            System.out.println(tutor);
        }
        //Tutorados con sus respectivas sesiones
        System.out.println("\nTutorados:");
        for(Tutorado tutorado:problema.getListaTutorados()){
            System.out.println(tutorado);
        }
        
        //Mostrar los periodos disponibles 
        mostrarSesionesRestantes(problema);
//        for(Periodo periodosRestantes: problema.dominio.getPeriodosPorAsignar()){
//            System.out.println(periodosRestantes);
//        }
       
        System.out.println("\nAlumnos sin ninguna sesion");
        for(Tutorado tutorado: problema.getListaTutorados() ){
            if(tutorado.getSesiones() == 0){
                System.out.println(tutorado);
            }
        }
    }
    
    private void mostrarSesionesRestantes(Problema problema){
        
          for(Mes mes:meses){
              for(Semana semana:mes.getSemanas()){
                  if(semana.semanaSinEventos()){
                      System.out.println("\n=========="+ mes.getNombre() + "\n" + "Semana " + semana.getNombreSemana() +" periodos restantes" + "\n=========");
                      mostrarPeriodosRestantes(problema);
                  }
              }
          }
        
    }
    
    private void mostrarPeriodosRestantes(Problema problema){
        for(Periodo periodosRestantes: problema.dominio.getPeriodosPorAsignar()){
            System.out.println(periodosRestantes);
        }
    }
    
    private Estado ejecutarAlgoritmo(Problema problema, boolean asignarMaximoNumeroSesiones){
       
        BacktrackingCronologico algoritmo = new BacktrackingCronologico(problema);
        Estado estadoFinal = null;   
       
        
        
        //Si no quieres asignar el maximo numero de sesiones
        if(asignarMaximoNumeroSesiones == false){
             int i=1;
            for(Mes mes: meses){
                //Iteramos sobre las semanas
                for(Semana semana: mes.getSemanas()){
                    //Ejecutamos el algortimo por cada semana para tener los eventos de cada una de ellas. 
                     //Igual verificamos si no quedan mas alumnos por tener evento. 
                     if(problema.tutoradosSinMinimoNumeroSesion()){
                        System.out.println("\nSemana:" + i);
                        estadoFinal = algoritmo.buscarSolucion();
                        semana.setEventosSemana(estadoFinal);
                        i++;
                    }
                
                }
            }
        }
        else{
            int i=1;
            for(Mes mes: meses){
                //Iteramos sobre las semanas
                for(Semana semana: mes.getSemanas()){
                    //Si la semana ya tiene el maximo total de eventos asignados, pasamos a la siguiente
                     if(semana.comprobarCantidadEventos()) continue;
                     
                     if(problema.tutoradoSinMaximoNumSesion()){
                        System.out.println("\nSemana:" + i);
                        estadoFinal = algoritmo.buscarSolucion();
                        semana.setEventosSemana(estadoFinal);
                        i++;
                    }
                     
                    //Generar un ciclo que no termine hasta que la semana actual tenga el maximo numero de eventos asignados
//                    while(!semana.comprobarCantidadEventos() ){
//                        //CHECAR:si no genera un ciclo infinito
//                         if(!problema.tutoradoSinMaximoNumSesion()) break;
//                            System.out.println("\nSemana:" + i);
//                            estadoFinal = algoritmo.buscarSolucion();
//                            semana.concatenarEventosSemana(estadoFinal);
//                            
//                        
//                    }
//                    i++;
                }
            }
        }
      
       return estadoFinal;
    }
}
