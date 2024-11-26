
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

    public Horario() {
    }
    
    
    
    public void generarHorario(){
        //Ingresamos los meses 
        meses.add(new Mes("Septienbre"));
        meses.add(new Mes("Octubre"));
        //System.out.println("semanas de " + meses.get(0).getNombre() + ":" + meses.get(0).getSemanas()  );
        //Generamos la instancia del problema
       Problema problema = new Problema();
       problema.configurarProblema();
       BacktrackingCronologico algoritmo = new BacktrackingCronologico(problema);
       Estado estadoFinal = null;   
       
       int i=1;
       for(Mes mes: meses){
           //Iteramos sobre las semanas
           for(Semana semana: mes.getSemanas()){
           //Ejecutamos el algortimo por cada semana para tener los eventos de cada una de ellas. 
            //Igual verificamos si no quedan mas alumnos por tener evento. 
                if(problema.tutoradosSinSesion()){
                    System.out.println("\nSemana:" + i);
                    estadoFinal = algoritmo.buscarSolucion();
                    semana.setEventosSemana(estadoFinal);
                    i++;
                }
                
           }
       }
      
       //Si estado final es diferente de nulo, entonces todos los eventos han sido asignados correctamente
       if(estadoFinal != null){
           
           mostrarResultados(problema);

       }
    }
    
    private void mostrarResultados(Problema problema){
        
        
        System.out.println("\nHorario Generado");
        for(Mes mes: meses){
            System.out.println(mes);
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
        System.out.println("\nSesiones restantes");
        for(Periodo periodosRestantes: problema.dominio.getPeriodosPorAsignar()){
            System.out.println(periodosRestantes);
        }
       
        System.out.println("\nAlumnos sin ninguna sesion");
        for(Tutorado tutorado: problema.getListaTutorados() ){
            if(tutorado.getSesiones() == 0){
                System.out.println(tutorado);
            }
        }
    }
}
