
package problema;

import java.util.Arrays;
import java.util.List;
import satisfaccionderestricciones.CSP;

/**
 *
 * @author gonza
 */
public class Problema extends CSP {
    
    private Tutorado[] listaTutorados = null;
    
    @Override
    public void crearVariables(){
        //Creamos la lista de alumnos
        crearAlumnos(6);
        
        //Creamos a lista de eventos;
        List<Evento> eventos = crearEventos();
     }

    @Override
    public void crearDominios(){
        //Crear los periodos
        
        
    }

    @Override
    public void crearRestricciones(){
    
    } 
    
    public List crearAlumnos(int cantidad){
        this.listaTutorados  = new Tutorado[cantidad];
        
        //Ciclo que crea tutorados
        for(int i=0; i<listaTutorados.length; i++){
            listaTutorados[i] = new Tutorado();
        }
        
        
        return Arrays.asList(this.listaTutorados);
    }
    
    //Total de eventos: cantidad de alumnos * cantidad de sesiones.
    public List crearEventos(){
        
        //Primero checamos que se hayan generado la lista de alumnos. 
        if(listaTutorados.length != 0){
            Evento[] eventos = new Evento[listaTutorados.length];
            
            for(int i=0; i<eventos.length; i++){
            eventos[i] = new Evento();
        }
            return Arrays.asList(eventos);
        }
        return null;
    }
}
