
package problema;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author gonza
 */
public class Mes {
    private String nombre; 
    private List<Semana> semanas = new ArrayList<>();
    
    public Mes(String nombre){
        this.nombre = nombre;
        generarSemanas();
    }
    
    
    private void generarSemanas(){
        String aux;
        for(int i=0; i<4; i++){
            aux = "semana-" + (i+1);
            semanas.add(new Semana(aux));
        }
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Semana> getSemanas() {
        return semanas;
    }

    public void setSemanas(List<Semana> semanas) {
        this.semanas = semanas;
    }

    @Override
    public String toString() {
        String aux = "";
        for(Semana semana: semanas){
            if(!semana.getEventosSemana().isEmpty()){
                aux += semana;
            }
            
        }
        return "Mes:" + nombre +" "+ aux;
    }
    
    
}
