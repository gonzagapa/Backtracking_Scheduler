
package problema;

import java.util.ArrayList;
import java.util.List;
import satisfaccionderestricciones.Estado;

/**
 *
 * @author gonza
 */
public class Semana {
    private String nombreSemana; 
    private Estado eventosSemana = new Estado();
    
    public Semana(){}
    
    public Semana(String nombreSemana){
          this.nombreSemana = nombreSemana;
    }

    public void setNombreSemana(String nombreSemana) {
        this.nombreSemana = nombreSemana;
    }

    public void setEventosSemana(Estado eventosSemana) {
        this.eventosSemana = eventosSemana;
    }

    public String getNombreSemana() {
        return nombreSemana;
    }

    public Estado getEventosSemana() {
        return eventosSemana;
    }

    @Override
    public String toString() {
        return "\n" + nombreSemana + "\n=======Eventos======" + "\n"+eventosSemana +"\n===============\n";
    }
    
    
    
}
