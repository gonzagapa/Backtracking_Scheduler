
package problema;

/**
 *
 * @author gonza
 */

//Los eventos serian las variables de mi problema
public class Evento {
    private Tutorado tutorado; 
    private String salon; 
    private Periodo periodo; 
    
   

    public Evento(){
        this.tutorado = new Tutorado();
        this.periodo = new Periodo();
        this.salon = "";
    }

    public Evento(Tutorado tutorado,String salon, Periodo periodo) {
        
        //this.tutor = tutor;
        this.salon = salon;
        this.periodo = periodo;
        this.tutorado = tutorado;
    } 

    public String getSalon() {
        return salon;
    }

    public Periodo getPeriodo() {
        return periodo;
    }
    

    public void setTutorado(Tutorado tutorado) {
        this.tutorado = tutorado;
        periodo.AsignarTutorado(tutorado);
    }

    public void setSalon(String salon) {
        this.salon = salon;
    }

    public void setPeriodo(Periodo periodo) {
        this.periodo = periodo;
    } 

    public Tutorado getTutorado() {
        return tutorado;
    }
    //Metodos
    
    //Comparar si tenemos dos eventos que anda ocupando el mismo espacio y tiempo. 
    public boolean alMismoTiempo(Evento otro){
        //Comprobamos que no se esten dando en el mismo periodo
        return this.periodo.alMismoTiempo(otro.periodo) && this.salon.equals(otro.salon);
    }

    @Override
    public String toString() {
        return "Evento{" + periodo + " cita con alumno:" + tutorado.getId()+ " Salon:" +salon+ '}';
    }
    
    
    public void quitarValores(){
        this.periodo = null;
        this.salon = null;
        this.tutorado = null;
    }
    
} 


