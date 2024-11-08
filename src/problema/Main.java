
package problema;

import java.util.List;

/**
 *
 * @author gonza
 */
public class Main {
    
    public static void main(String[] args) {
         
        Problema problema = new Problema();
        
        List<Tutorado> listaAlumnos = problema.crearAlumnos(6);
        for(Tutorado alumno: listaAlumnos){
            System.out.println(alumno);
        }
        
        
        List<Evento> eventos = problema.crearEventos();
        System.out.println("eventos creados:" + eventos.size());
        
        
        Periodo p1 = new Periodo("Lunes",8,9,"Delio Coss");
        Periodo p2 = new Periodo("Lunes",8,9,"Hector Perez");
        Periodo p3 = new Periodo("Lunes",8,9,"Rafael Rivera");
        
        for(Tutor tutor:Tutor.tutores){
            System.out.println(tutor);
        }
        System.out.println(p1);
        
        
    }
}
