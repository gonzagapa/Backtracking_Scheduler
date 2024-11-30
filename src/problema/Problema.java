
package problema;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import csp.CSP;
import csp.Dominio;
import csp.DominioEvento;
import csp.RestriccionEventoPeriodo;
import csp.RestriccionSesionTutorial;
import java.io.InputStream;
import util.Archivo;
 
/**
 *
 * @author gonza
 */
public class Problema extends CSP {
    
    private  List<Tutorado> listaTutorados = null;
    public static DominioEvento dominio; //Aqui contien los dominios de cada evento
    public static ArrayList<Periodo> periodos = new ArrayList();
    private static ArrayList<Periodo> periodosRestantesSemana = new ArrayList();
    public static ArrayList<String> variablesAsignadas = new ArrayList();
    private static int NUM_MINIMO_SESION = 1; //Numero de sesiones minimas por alumno
    private static  int NUM_MAX_SESIONES = 2; //numero de sesiones maxima por alumno
    private static  int NUM_ALUMNOS = 25; //Cantidad de alumnos que vamos agendar una sesion
    private static int NUM_PERIODOS = 0; //NO TOCAR
    private String nombreArchivo;
    private ArrayList<String> salones = new ArrayList<>();
    
    public Problema(){
        
    }

    public ArrayList<String> getSalones() {
        return salones;
    }
    
    
    public Problema(String nombreArchivo){
        this.nombreArchivo = nombreArchivo;
    }

    public static int getNUM_MINIMO_SESION() {
        return NUM_MINIMO_SESION;
    }

    public static void setNUM_MINIMO_SESION(int NUM_MINIMO_SESION) {
        
        Problema.NUM_MINIMO_SESION = NUM_MINIMO_SESION;
    }

    public static int getNUM_MAX_SESIONES() {
        return NUM_MAX_SESIONES;
    }

    public static void setNUM_MAX_SESIONES(int NUM_MAX_SESIONES) {
        Problema.NUM_MAX_SESIONES = NUM_MAX_SESIONES;
    }

    public static int getNUM_ALUMNOS() {
        return NUM_ALUMNOS;
    }

    public static void setNUM_ALUMNOS(int NUM_ALUMNOS) {
        Problema.NUM_ALUMNOS = NUM_ALUMNOS;
    }
    
   

    public static int getNUM_PERIODOS() {
        return NUM_PERIODOS;
    }

    public static void setNUM_PERIODOS(int NUM_PERIODOS) {
        Problema.NUM_PERIODOS = NUM_PERIODOS;
    }
    
    public void extrarDatos(InputStream flujo){
        ArrayList<String> lineas = Archivo.leerArchivo(flujo);
        
        for(String linea:lineas){
            linea = linea.trim();
            switch(linea.charAt(0)){
                case '$' ->{
                    return; //Fin del archivo
                }
                case '/' ->{ //Comentario
                     continue;
                    }
                case 'd' ->{ //Datos principales (alumnos,periodos,min-periodo y max_periodo)
                    String[] datos = linea.split("\\s+");
                    setNUM_ALUMNOS(Integer.parseInt(datos[1]));
                    setNUM_PERIODOS(Integer.parseInt(datos[2]));
                    setNUM_MINIMO_SESION(Integer.parseInt(datos[3]));
                    setNUM_MAX_SESIONES(Integer.parseInt(datos[4]));
                    System.out.println("alumnos" + getNUM_ALUMNOS());
                    System.out.println("periodos" + getNUM_PERIODOS());
                    System.out.println("minimo" + getNUM_MINIMO_SESION());
                    System.out.println("maximo" + getNUM_MAX_SESIONES());
                }
                case 'p' ->{
                    String[] datos = linea.substring(2).split(","); 
                    System.out.println(Arrays.toString(datos));
                    
                    
                    datos[0] = datos[0].substring(1);
                    datos[3] = datos[3].substring(0,datos[3].length()-1);
                    
                    System.out.println( datos[0]);
                    System.out.println( datos[3]);
                    
                 periodos.add(new Periodo(datos[0],
                            Integer.parseInt(datos[1]),
                         Integer.parseInt(datos[2]),
                            datos[3])
                    );
            
                }
                case 's' ->{
                    String[] datos = linea.split("\\s+");  
                    for(int i= 1; i<datos.length-1; i++){
                        salones.add(datos[i]);
                    }
                }
                    
            }
        }
    }
    
    
    @Override
    public void crearVariables(){
        //Leer los datos del archivo
        //extrarDatos();
        
        
        //Creamos la lista de alumnos
        crearAlumnos(getNUM_ALUMNOS());
        
        //Creamos a lista de eventos;
        List<String> eventos = crearEventos();
        
        //Guardar la cantidad de eventos generados en variable
        for(String evento:eventos){
            this.addVariable(evento);
        }
     }

    @Override
    public void crearDominios(){

       
        
//        Periodo p1 = new Periodo("vie",12,13,"Genis Triana");
//        Periodo p2 = new Periodo("vie",11,12,"Esteban Jesus");
//        Periodo p3 = new Periodo("vie",12,13,"Gabriel Antonio");
//        Periodo p4 = new Periodo("vie",11,12,"Berenice Padilla");
//        Periodo p5 = new Periodo("vie",17,18,"Rafael del Valle");
//        Periodo p6 = new Periodo("vie",18,19,"Rafael del Valle");
//        Periodo p7 = new Periodo("vie",17,18,"Jose Enrique");
//        Periodo p8 = new Periodo("mar",13,14,"Delio Camilo Coss");
//        Periodo p9 = new Periodo("vie",12,13,"Noemi del Carmen");
//        Periodo p10 = new Periodo("vie",8,9,"Silva Jose Hernandez");
//        Periodo p11 = new Periodo("mar",14,15,"Virgina Campos");
//        Periodo p12 = new Periodo("vie",12,13,"Ofelia Giraldi");
//        Periodo p13 = new Periodo("vie",16,17,"Hector Perez");
//        Periodo p14 = new Periodo("lun",13,14,"Arturo Perez Rendon");
//        Periodo p15 = new Periodo("vie",13,14,"Jesus Calleja");
//        Periodo p16 = new Periodo("vie",16,17,"Manuel Antonio");
//        
//        Periodo p17 = new Periodo("vie",13,14,"Genis Triana");
//        Periodo p18 = new Periodo("vie",12,13,"Esteban Jesus");
//        Periodo p19 = new Periodo("vie",13,14,"Gabriel Antonio");
//        Periodo p20 = new Periodo("vie",12,13,"Berenice Padilla");
//        Periodo p21 = new Periodo("jue",13,14,"Delio Camilo Coss");
//        Periodo p22 = new Periodo("vie",8,9,"Virgina Campos");
//        Periodo p23 = new Periodo("mar",10,11,"Ofelia Giraldi");
//        Periodo p24 = new Periodo("mie",13,14,"Arturo Perez Rendon");
//        Periodo p25 = new Periodo("mie",13,14,"Jesus Calleja");
//
//        periodos.add(p1);
//        periodos.add(p2);
//        periodos.add(p3); 
//        periodos.add(p4); 
//        periodos.add(p5); 
//        periodos.add(p6);
//        periodos.add(p7);
//        periodos.add(p8);
//        periodos.add(p9);
//        periodos.add(p10);
//        periodos.add(p11);
//        periodos.add(p12);
//        periodos.add(p13);
//        periodos.add(p14);
//        periodos.add(p15);
//        periodos.add(p16); 
//        periodos.add(p17);
//        periodos.add(p18);
//        periodos.add(p19);
//        periodos.add(p20);
//        periodos.add(p21);
//        periodos.add(p22);
//        periodos.add(p23);
//        periodos.add(p24);
//        periodos.add(p25); 
//        
//        salones.add("E001");
//        salones.add("E216");
//        salones.add("U004"); 
//        salones.add("E003"); 
//        salones.add("E202");
//        salones.add("U107");
//        salones.add("E104");
//        salones.add("O101"); 
        
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
            eventos[i] = "e" + (i+1);
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
    public Tutorado obtenerTutoradoSinMinimoNumeroSesion(){
        for(Tutorado tutorado:listaTutorados){
                if(tutorado.getSesiones() < NUM_MINIMO_SESION) return tutorado;
        }
        return null;
    }
    
    public boolean tutoradosSinMinimoNumeroSesion(){
        for(Tutorado tutorado: listaTutorados){
                if(tutorado.getSesiones() < NUM_MINIMO_SESION) return true;
        }
        return false;
    }
    
    //Devuelve el primer tutorado con una sola sesion
     public boolean tutoradoSinMaximoNumSesion(){
        for(Tutorado tutorado:listaTutorados){
                if(tutorado.getSesiones() < NUM_MAX_SESIONES) return true;
        }
        return false;
    }
    
    //Devuelve el primer tutorado con una sola sesion
     public Tutorado obtenerTutoradoSinMaximoNumSesion(){
        for(Tutorado tutorado:listaTutorados){
                if(tutorado.getSesiones() < NUM_MAX_SESIONES) return tutorado;
        }
        return null;
    }

    public static ArrayList<Periodo> getPeriodosRestantesSemana() {
        return periodosRestantesSemana;
    }

    public static void setPeriodosRestantesSemana(ArrayList<Periodo> periodosRestantesSemana) {
        Problema.periodosRestantesSemana = periodosRestantesSemana;
    }
    
}
