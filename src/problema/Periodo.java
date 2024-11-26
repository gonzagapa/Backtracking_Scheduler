
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
    
    public Periodo(){
        this.tutor = new Tutor();
    }
    
    public Periodo(String dia, int horaInicio, int horaFinal, String nombreTutor){
        this.dia = dia.toLowerCase();
        this.horaInicio = LocalTime.of(horaInicio, 0); 
        this.horaFinal = LocalTime.of(horaFinal, 0); 
        
        //Comprobacion de tutor, si ya existe entonces NO no crear un nuevo objeto Tutor
        Tutor aux = Tutor.buscarTutor(nombreTutor);
        this.tutor = (aux != null) ? aux: new Tutor(nombreTutor);
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
    
    public void quitarTutorado(Tutorado tutorado){
        tutor.getTutorados().remove(tutorado);
        tutorado.disminuirSesion();
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
