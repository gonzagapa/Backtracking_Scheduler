
package problema;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import csp.Estado;

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
        return "\n" + nombreSemana + "\n=======Eventos======" + "\n"+plantillaHorario(eventosSemana) +"\n===============\n";
    }
    
    private String plantillaHorario(Estado eventosSemana){
        Collection<Evento> eventos = eventosSemana.values();
        String aux = "";
        //Arreglo de dias de la semana
        String[] dias = {"lun","mar","mie","jue","vie"};
        Set<String> tutores =  new LinkedHashSet<>();
        
        //Creamos la lista de tutores
        for(Evento evento:eventos){
            tutores.add(evento.getPeriodo().getTutor().getNombre());
        }
        
        //Creamos la tabla
        Map<String, Map<String, List<String>>> tabla = new LinkedHashMap<>();
            for (String tutor : tutores) {
                tabla.put(tutor, new LinkedHashMap<>());
                for (String dia : dias) {
                    tabla.get(tutor).put(dia, new ArrayList<>());
                }
            }
            
          // Llenar tabla con eventos
        for (Map.Entry<String,Evento> entrada:eventosSemana.entrySet()){
            String tutor = entrada.getValue().getPeriodo().getTutor().getNombre();
            String dia = entrada.getValue().getPeriodo().getDia();
            tabla.get(tutor).get(dia).add(entrada.getKey());
        }
        
         
         aux = String.format("%-20s", "Tutor") + String.join("   ", dias) + "\n";
        for (String tutor : tutores) {
            aux += String.format("%-20s", tutor);
            for (String dia : dias) {
                List<String> eventosDia = tabla.get(tutor).get(dia);
                if (eventosDia.isEmpty()) {
                   aux += "|---|";
                } else {
                    for (String evento : eventosDia) {
                        aux += "|"+evento + "|";
                    }
                }
                aux +=" ";
            }
            aux+="\n";
        }
        
        return aux;
    }
    
    
    
}
