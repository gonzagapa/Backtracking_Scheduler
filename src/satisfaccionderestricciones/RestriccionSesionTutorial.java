
package satisfaccionderestricciones;

import java.util.Iterator;
import java.util.List;
import problema.Evento;

/**
 *
 * @author gonza
 */
public class RestriccionSesionTutorial implements Restriccion{
    List<String> variables = null;

    @Override
    public List<String> getVariables() {
        return variables;
    }

    public RestriccionSesionTutorial() {
    }
    

    //Es satisfecha si todos los alumnos tiene una sesion de tutorias.
    @Override
    public boolean esSatisfecha(Estado asignacion) {
         List<Evento> listaEventos = (List<Evento>) asignacion.values();
         for(Evento evento: listaEventos){
             if(evento.getTutorado().getSesiones() != 1 ) return false;
         }
        return true;
    }
    
}
