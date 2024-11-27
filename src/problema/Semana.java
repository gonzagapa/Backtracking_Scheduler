
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
    private ArrayList<Periodo> periodosRestantesSemana = null;
    
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
    
    public void concatenarEventosSemana(Estado masEventosSemana){
        eventosSemana.putAll(masEventosSemana);
    }

    public String getNombreSemana() {
        return nombreSemana;
    }

    public Estado getEventosSemana() {
        return eventosSemana;
    }
    public boolean semanaSinEventos(){
        return eventosSemana == null;
    }
    
    //Metodo que comprueba si los eventos agendados en esa semana es igual a la cantidad de periodos totales del problema
    public boolean comprobarCantidadEventos(){
        System.out.println("Eventos agendados en " + nombreSemana + ":" + eventosSemana.size());
        return eventosSemana.size() >= Problema.NUM_PERIODOS; 
    }
    

    @Override
    public String toString() {
        return "\n" + nombreSemana + "\n=======Eventos======" + "\n"+eventosSemana +"\n===============\n";
    }
    
    
    
    
}
