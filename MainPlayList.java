import java.util.*;

public class MainPlayList {
    private static LinkedList<Cancion> canciones = new LinkedList<Cancion>();
    //private static Iterator<Cancion> it = canciones.iterator();
    private static Scanner sc = new Scanner(System.in);
    private static boolean diradelante = true;

    public static void main(String[] args) {


        ArrayList<Album> albumes = new ArrayList<Album>();
        albumes.add(new Album("After Hours","The Weeknd"));
        albumes.add(new Album("Estopa","Estopa"));

        //
        albumes.get(0).addSong("Save Your Tears",3.36);
        albumes.get(0).addSong("Blinding Lights",3.20);
        albumes.get(0).addSong("After Hours",6.01);
        albumes.get(0).addSong("Heartless",3.18);
        //
        albumes.get(1).addSong("La raja de tu falda",3.32);
        albumes.get(1).addSong("Me falta el aliento",3.51);
        albumes.get(1).addSong("Como camaron",3.31);
        albumes.get(1).addSong("Suma y sigue",3.23);
        //
        albumes.get(0).addToPlayList("Blinding Lights",canciones);
        albumes.get(0).addToPlayList(3,canciones);
        albumes.get(0).addToPlayList("After Hours",canciones);
        albumes.get(0).addToPlayList(0,canciones);
        //
        albumes.get(1).addToPlayList("Suma y sigue",canciones);
        albumes.get(1).addToPlayList(0,canciones);
        albumes.get(1).addToPlayList("Como camaron",canciones);
        albumes.get(1).addToPlayList(1,canciones);

        play();
    }

    public static void play() {
        ListIterator<Cancion> it = canciones.listIterator();
        int opcion = -1;
        menu();
        System.out.println("Iniciando la playlist con la cancion: " + it.next());
        while (opcion!=0){
            opcion=-1;
            try {
                System.out.println("Introduce la opcion: ");
                opcion = sc.nextInt();
            }catch (Exception e){
                sc.next();
            }

            switch (opcion){
                case 0:
                    System.out.println("Saliendo de la playlist...");
                    break;
                case 1:
                    sigCancion(it);
                    break;
                case 2:
                    antCancion(it);
                    break;
                case 3:
                    repCancion(it);
                    break;
                case 4:
                    listCanciones();
                    break;
                case 5:
                    menu();
                    break;
                case 6:
                    eliCancion(it);
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        }
    }

    public static void menu() {
        System.out.println("0 - Salir de la lista de reproducción");
        System.out.println("1 - Reproducir siguiente canción en la lista");
        System.out.println("2 - Reproducir la canción previa de la lista");
        System.out.println("3 - Repetir la cancion actual");
        System.out.println("4 - Imprimir la lista de canciones en la playlist");
        System.out.println("5 - Volver a imprimir el menú");
        System.out.println("6 - Eliminar la cancion actual de la playlist");
    }

    public static void sigCancion(ListIterator<Cancion> it) {
        if (it.hasNext() && diradelante){//Direccion hacia adelante
            System.out.println("Reproduciendo la siguiente cancion: " + it.next());
        }else if (it.hasNext() && !diradelante){//Direccion hacia atras, la cambia hacia delante
            it.next();
            System.out.println("Reproduciendo la siguiente cancion: " + it.next());
            diradelante = true;
        }else
            System.out.println("Estas al final de la playlist. No hay más canciones.");
    }

    public static void antCancion(ListIterator<Cancion> it) {
        try{
            if (it.hasPrevious() && !diradelante){//Direccion hacia atras
                System.out.println("Reproduciendo la cancion anterior: " + it.previous());
            }else if (it.hasPrevious() && diradelante){//Direccion hacia adelante, la cambia hacia atras
                it.previous();
                System.out.println("Reproduciendo la cancion anterior: " + it.previous());
                diradelante = false;
            }else
                System.out.println("Estas al principio de la playlist. No hay más canciones antes.");
        }catch (NoSuchElementException e){
            System.out.println("Estas al principio de la playlist. No hay más canciones antes.");
        }

    }

    public static void repCancion(ListIterator<Cancion> it) {
        if (it.hasPrevious() && diradelante){//Si la cancion esta hacia adelante, la cambia hacia atras y se queda en la misma cancion
            System.out.println("Repitiendo la cancion actual: " + it.previous());
            diradelante=false;
        } else {//Si la cancion esta hacia atras, la cambia hacia adelante y se queda en la misma cancion
            System.out.println("Repitiendo la cancion actual: " + it.next());
            diradelante=true;
        }
    }

    public static void listCanciones() {
        Iterator<Cancion> it = canciones.iterator();

        for (int i = 0; i < canciones.size(); i++) {
            System.out.println(it.next());
        }
    }

    public static void eliCancion(ListIterator<Cancion> it){

        if (it.hasPrevious() && diradelante) {
            System.out.println("Eliminando la cancion actual.");
            it.remove();
            if (it.hasNext()) {
                System.out.println("Pasando a la cancion: " + it.next());
                diradelante = true;
            } else {
                System.out.println("Pasando a la cancion: " + it.previous());
                diradelante = true;
                it.next();
            }
        }
        else {
            System.out.println("Eliminando la cancion actual.");
            it.remove();
            if (it.hasNext() && it.hasPrevious()){
                System.out.println("Pasando a la siguiente cancion " + it.next());
                diradelante = true;
            } else{
                System.out.println("No hay mas canciones.");
            }
        }
    }
}
