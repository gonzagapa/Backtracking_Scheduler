
package csp;

import java.util.ArrayList;
import java.util.List;
import problema.Periodo;
import problema.Problema;
import problema.Tutorado;

/**
 *
 * @author gonza
 */
public class DominioEvento {
    
    private ArrayList<Periodo> periodosPorAsignar;
    private ArrayList<Periodo> periodosYaAsignados = new ArrayList<>();
    private List<Tutorado> tutorados; 
    private ArrayList<String> salones;
    
    public DominioEvento(ArrayList<Periodo> periodos,List<Tutorado> tutorados, ArrayList<String> salones){
      this.periodosPorAsignar = new ArrayList(periodos); 
      this.tutorados = tutorados;
      this.salones = salones;
  }

    public void setPeriodosPorAsignar(ArrayList<Periodo> periodosPorAsignar) {
        this.periodosPorAsignar = periodosPorAsignar;
    }
    
    

    public ArrayList<Periodo> getPeriodosPorAsignar() {
        return periodosPorAsignar;
    }

    public List<Tutorado> getTutorados() {
        return tutorados;
    }

    public ArrayList<String> getSalones() {
        return salones;
    }
    
    private void quitarPeriodoPorAsignar(Periodo periodo){
        periodosPorAsignar.remove(periodo);
    }
    private void anadirPeriodoPorAsignar(Periodo periodo){
        periodosPorAsignar.add(periodo);
    }
    
    //como ya no se encontrara asignado, lo agregamos a periodosPorAsignar
    public void quitarPeriodoAsignado(Periodo periodo){
        periodosYaAsignados.remove(periodo);
        anadirPeriodoPorAsignar(periodo);
    }   
    //Como ya lo asignaste, lo quitar de periodos por Asignar
    public void anadirPeriodosAsignados(Periodo periodo){
        periodosYaAsignados.add(periodo);
        quitarPeriodoPorAsignar(periodo);
    }

    public ArrayList<Periodo> getPeriodosYaAsignados() {
        return periodosYaAsignados;
    }
   
    //Si devuelve un valor mayor a 0, quiere decir que todavia tenemos periodos disponibles
    public  int existenciaPeriodosDisponibles(){
        return periodosPorAsignar.size();
    }
    //Reiniciar periodos 
    public void generarNuevosPeriodos(){
        periodosPorAsignar = new ArrayList(Problema.periodos);
        periodosYaAsignados.clear();
    }
    
    public void generarNuevosPeriodos(ArrayList<Periodo> periodos){
        periodosPorAsignar = new ArrayList(periodos);
        //periodosYaAsignados.clear();
    }
    //Obtener buscar un periodo donde se encuentre un profesor en particular
    public Periodo buscarPeriodoXTutor(String nombreTutor){
        Periodo aux = null;
        for(Periodo periodo: periodosPorAsignar){
              if(periodo.getTutor().getNombre().equals(nombreTutor)) {
                  aux= periodo;
                  break;
              }
        }  
        return aux;
    }
    
}
