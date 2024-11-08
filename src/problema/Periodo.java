
package problema;
import java.time.LocalTime;
/**
 *
 * @author gonza
 */
public class Periodo {
    private String dia; 
    private LocalTime horaInicio; 
    private LocalTime horaFinal; 
    private Tutor tutor; 
    
    public Periodo(String dia, int horaInicio, int horaFinal, String nombreTutor){
        this.dia = dia;
        this.horaInicio = LocalTime.of(horaInicio, 0); 
        this.horaFinal = LocalTime.of(horaFinal, 0); 
        this.tutor = new Tutor(nombreTutor);
    }
    

    public String getDia() {
        return dia;
    }

    public LocalTime getHora() {
        return horaInicio;
    }

    public Tutor getTutor() {
        return tutor;
    }
    
    public void AsignarTutorado(Tutorado tutorado){
        tutor.añadirTutorado(tutorado);
    }
    
    
    //Metodos
    public Object[] obtenerDiaHora(){
         Object[] aux = {this.dia, this.horaInicio}; 
         return aux;
    }
    
    //Metodo que comprueba que no se esten realizando al mismo tiempo
    public boolean alMismoTiempo(Periodo otro){
        return this.dia.equalsIgnoreCase(otro.getDia()) &&
                this.horaInicio.equals(otro.horaInicio); 
    }

    @Override
    public String toString() {
        return  dia +":"+ " " + horaInicio +" - " + horaFinal  +" tutor:" + tutor.getNombre()+';';
    }
    
    
    
}
