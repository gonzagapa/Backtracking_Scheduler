
package problema;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import satisfaccionderestricciones.CSP;
import satisfaccionderestricciones.Dominio;
import satisfaccionderestricciones.DominioEvento;
import satisfaccionderestricciones.RestriccionEventoPeriodo;
import satisfaccionderestricciones.RestriccionSesionTutorial;

/**
 *
 * @author gonza
 */
public class Problema extends CSP {
    
    private  List<Tutorado> listaTutorados = null;
    public static DominioEvento dominio; //Aqui contien los dominios de cada evento
    public static ArrayList<Periodo> periodos = new ArrayList();
    public static ArrayList<String> variablesAsignadas = new ArrayList();
    
    @Override
    public void crearVariables(){
        //Creamos la lista de alumnos
        crearAlumnos(50);
        
        //Creamos a lista de eventos;
        List<String> eventos = crearEventos();
        
        //Guardar la cantidad de eventos generados en variable
        for(String evento:eventos){
            this.addVariable(evento);
        }
     }

    @Override
    public void crearDominios(){

        //Creamos los salones
        ArrayList<String> salones = new ArrayList();
        
        Periodo p1 = new Periodo("Viernes",12,13,"Genis Triana");
        Periodo p2 = new Periodo("Viernes",11,12,"Esteban Jesus");
        Periodo p3 = new Periodo("Viernes",12,13,"Gabriel Antonio");
        Periodo p4 = new Periodo("Viernes",11,12,"Berenice Padilla");
        Periodo p5 = new Periodo("Viernes",17,18,"Rafael del Valle");
        Periodo p6 = new Periodo("Viernes",18,19,"Rafael del Valle");
        Periodo p7 = new Periodo("Viernes",17,18,"Jose Enrique");
        Periodo p8 = new Periodo("Martes",13,14,"Delio Camilo Coss");
        Periodo p9 = new Periodo("Viernes",12,13,"Noemi del Carmen");
        Periodo p10 = new Periodo("Viernes",8,9,"Silva Jose Hernandez");
        Periodo p11 = new Periodo("Martes",14,15,"Virgina Campos");
        Periodo p12 = new Periodo("Viernes",12,13,"Ofelia Giraldi");
        Periodo p13 = new Periodo("Viernes",16,17,"Hector Perez");
        Periodo p14 = new Periodo("Lunes",13,14,"Arturo Perez Rendon");
        Periodo p15 = new Periodo("Viernes",13,14,"Jesus Calleja");
        Periodo p16 = new Periodo("Viernes",16,17,"Manuel Antonio");
        
        Periodo p17 = new Periodo("Viernes",13,14,"Genis Triana");
        Periodo p18 = new Periodo("Viernes",12,13,"Esteban Jesus");
        Periodo p19 = new Periodo("Viernes",13,14,"Gabriel Antonio");
        Periodo p20 = new Periodo("Viernes",12,13,"Berenice Padilla");
        Periodo p21 = new Periodo("Jueves",13,14,"Delio Camilo Coss");
        Periodo p22 = new Periodo("Viernes",8,9,"Virgina Campos");
        Periodo p23 = new Periodo("Martes",10,11,"Ofelia Giraldi");
        Periodo p24 = new Periodo("Miercoles",13,14,"Arturo Perez Rendon");
        Periodo p25 = new Periodo("Miercoles",13,14,"Jesus Calleja");

        periodos.add(p1);
        periodos.add(p2);
        periodos.add(p3); 
        periodos.add(p4); 
        periodos.add(p5); 
        periodos.add(p6);
        periodos.add(p7);
        periodos.add(p8);
        periodos.add(p9);
        periodos.add(p10);
        periodos.add(p11);
        periodos.add(p12);
        periodos.add(p13);
        periodos.add(p14);
        periodos.add(p15);
        periodos.add(p16); 
        periodos.add(p17);
        periodos.add(p18);
        periodos.add(p19);
        periodos.add(p20);
        periodos.add(p21);
        periodos.add(p22);
        periodos.add(p23);
        periodos.add(p24);
        periodos.add(p25); 
        
        salones.add("E001");
        salones.add("E216");
        salones.add("U004"); 
        salones.add("E003"); 
        salones.add("E202");
        salones.add("U107");
        salones.add("E104");
        salones.add("O101"); 
        
        //Crear un dominio que contenga los 3 valores. 
        DominioEvento dominioEvento = new DominioEvento(periodos,listaTutorados,salones);
        dominio = dominioEvento;
        
        //Asignar un objeto Evento vacio a cada variable
        
        ArrayList<String> var = (ArrayList<String>) getVariables();
        for(String v:var){
            Dominio dom = new Dominio();
            dom.add(new Evento());
            setDominio(v, dom);
        }
        
    }
    
    public DominioEvento getDominioEvento(){
        return dominio;
    }

    @Override
    public void crearRestricciones(){
        RestriccionEventoPeriodo r1 = new RestriccionEventoPeriodo(getVariables()); 
        RestriccionSesionTutorial r2 = new RestriccionSesionTutorial();
        addRestriccion(r1);
        addRestriccion(r2);
    } 
    
    public List crearAlumnos(int cantidad){
        Tutorado[] lista  = new Tutorado[cantidad];
        
        //Ciclo que crea tutorados
        for(int i=0; i<lista.length; i++){
            lista[i] = new Tutorado();
        }
        
        this.listaTutorados = Arrays.asList(lista);
        return Arrays.asList(lista);
    }
    
    //Total de eventos: cantidad de alumnos * cantidad de sesiones.
    public List crearEventos(){
        
        //Primero checamos que se hayan generado la lista de alumnos. 
        if(!listaTutorados.isEmpty()){
            String[] eventos = new String[listaTutorados.size()];
            
            for(int i=0; i<eventos.length; i++){
            eventos[i] = "evento" + (i+1);
        }
            return Arrays.asList(eventos);
        }
        return null;
    } 

    public List<Tutorado> getListaTutorados() {
        return listaTutorados;
    }
    
    //Devuelve verdadero si algun tutorado no tiene una sesion asignada.
    public boolean tutoradosSinSesion(){
        for(Tutorado tutorado: listaTutorados){
                if(tutorado.getSesiones() == 0) return true;
        }
        return false;
    }
    //Devuelve el primer alumno sin sesion asignada
    public Tutorado obtenerTutoradoSinSesion(){
        for(Tutorado tutorado:listaTutorados){
                if(tutorado.getSesiones() == 0) return tutorado;
        }
        return null;
    }
    
    
    
}
