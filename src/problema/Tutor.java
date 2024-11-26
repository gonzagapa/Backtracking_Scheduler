
package problema;

import java.util.ArrayList;

/**
 *
 * @author gonza
 */
public class Tutor {
    
    public static int totalTutores=0;
    public static ArrayList<Tutor> tutores = new ArrayList();
    
    private int id; 
    private String nombre; 
    private ArrayList<Tutorado> tutorados  = new ArrayList();

    public Tutor(String nombre) {
        this.id = ++totalTutores;
        this.nombre = nombre;
        tutorados = new ArrayList<>();
       tutores.add(this);
    }
    
    public Tutor(int id) {
        this.id = id;
        tutorados = new ArrayList<>();
    }
    
    public Tutor(){}

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

    public ArrayList<Tutorado> getTutorados() {
        return tutorados;
    }

    public void añadirTutorado(Tutorado tutorado){
        tutorados.add(tutorado);
        tutorado.aumentarSesion();
    }

    public static Tutor buscarTutor(String nombreTutor){
        for(Tutor tutor: tutores){
            if(tutor.nombre.equalsIgnoreCase(nombreTutor)) return tutor;
        }
        return null;
    }
    @Override
    public String toString() {
        return "Tutor{" + "id=" + id + ", nombre=" + nombre +" tutorados: " + tutorados.toString()+'}';
    }
    
    
    
    
}
