
package problema;

/**
 *
 * @author gonza
 */
public class Tutorado {
    
    public static int totalTutorados = 1; 
    
    private int id; 
    private String nombre; 
    private int sesiones; 
    
    public Tutorado(){
        this.id = totalTutorados;
        totalTutorados +=1;
        this.sesiones =0;   
    } 

    public Tutorado(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
        this.sesiones =0;   
    }
    
    
    
    public Tutorado(int id){
        this.id = id; 
        totalTutorados +=1;
        this.sesiones = 0; 
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getSesiones() {
        return sesiones;
    }

    public void setSesiones(int sesiones) {
        this.sesiones = sesiones;
    }
    public void aumentarSesion(){
        this.sesiones += 1;
    }
    public void disminuirSesion(){
        this.sesiones = (sesiones != 0) ? sesiones - 1 : 0;
    }
    @Override
    public String toString() {
        return "Tutorado{" + "id=" + id + ", sesiones=" + sesiones + '}';
    }
    
    
}
