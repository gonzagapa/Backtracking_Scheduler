
package csp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import problema.Evento;
import problema.Problema;

/**
 *
 * @author gonza
 */
public class RestriccionEventoPeriodo implements Restriccion {

    List<String> variables = null;

    public RestriccionEventoPeriodo() {
    }
    
    
    public RestriccionEventoPeriodo(List<String> variables){
        this.variables =  variables;
    }
    
    @Override
    public List<String> getVariables() {
        return variables;
    }

    public void setVariable1(String variable1) {
    }

    public void setVariable2(String variable2) {
    }
    
    

    @Override
    public boolean esSatisfecha(Estado asignacion) {
       
        
        //Si solo hay un estado, no se puede hacer la comprobacion
        if(asignacion.size() == 1){
            return true;
        }else{
             //Obtenemos el ultimo el evento del ultimo estado
            String variableActual = Problema.variablesAsignadas.getLast();
            //String variableActual = variables.get(asignacion.size() -1);
            //System.out.println("Nombre variable:" + variableActual);
            Evento eventoActual = (Evento) asignacion.getAsignacion(variableActual);
            for (Evento ev : asignacion.values()) {
                if(ev.equals(eventoActual)) break;
                
                //Verifica que no se esten dando al mismo tiempo
                if(eventoActual.alMismoTiempo(ev)) return false;
            }
            return true;
        }
    }
    
}
