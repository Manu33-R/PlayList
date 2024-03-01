import java.util.ArrayList;
import java.util.LinkedList;

public class Album {
    private String nombre;
    private String artista;
    private ArrayList<Cancion> canciones = new ArrayList<Cancion>();

    public Album(String nombre, String artista) {
        this.nombre = nombre;
        this.artista = artista;
    }

    private Cancion findSong(String titulo){
        int res = -1;
        for (int i = 0; i < canciones.size(); i++) {
            if (canciones.get(i).getTitulo().equalsIgnoreCase(titulo)){
                res = i;
                i = canciones.size();
            }
        }
        if (res >= 0){
            return canciones.get(res);
        }else
            return null;
    }


    public boolean addSong(String titulo, double duracion){
        if (findSong(titulo) == null){
            canciones.add(new Cancion(titulo,duracion));
            return true;
        }else
            return false;
    }

    public boolean addToPlayList(String titulo, LinkedList<Cancion> listarepr){
        if (findSong(titulo) == null) {
            return false;
        } else{
            listarepr.add(findSong(titulo));
            return true;
        }

    }

    public boolean addToPlayList(int numpista, LinkedList<Cancion> listarepr){
        if (canciones.size() > numpista){
            listarepr.add(canciones.get(numpista));
            return true;
        }else
            return false;
    }
}
