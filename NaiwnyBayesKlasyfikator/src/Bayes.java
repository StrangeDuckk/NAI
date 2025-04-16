import java.util.ArrayList;
import java.util.HashMap;

public class Bayes {
    private ArrayList<Piwo> tablicaPiw;
    private HashMap<String, ArrayList<Double>> stylePiwa;
    private HashMap<String, Integer> barwy;
    private HashMap<String, Integer> chmiel;
    private HashMap<String, Integer> gorycze;

    public Bayes() {
        this.tablicaPiw = new ArrayList<>();
    }

    public void wypelnienieHashMap(){
        // ---------------- mapa klas styli i ilosci wystapien w treningowym ----------------
        this.stylePiwa = new HashMap<>();
        for (Piwo piwo : tablicaPiw) {
            if (!stylePiwa.containsKey(piwo.getStyl())) {
                stylePiwa.put(piwo.getStyl(), new ArrayList<>(0));
                stylePiwa.get(piwo.getStyl()).add(0.0);
            }
            stylePiwa.get(piwo.getStyl()).set(0,stylePiwa.get(piwo.getStyl()).getFirst()+1);
        }

        // ---------------- zliczenie ilosci roznych barw ----------------
        this.barwy = new HashMap<>();
        for (Piwo piwo : tablicaPiw) {
            if (!barwy.containsKey(piwo.getBarwa())) {
                barwy.put(piwo.getBarwa(), 0);
            }
            barwy.put(piwo.getBarwa(),barwy.get(piwo.getBarwa())+1);
        }

        // ---------------- zliczenie ilosci roznych chmieli ----------------
        this.chmiel = new HashMap<>();
        for (Piwo piwo : tablicaPiw) {
            if (!chmiel.containsKey(piwo.getBarwa())) {
                chmiel.put(piwo.getBarwa(), 0);
            }
            chmiel.put(piwo.getBarwa(),chmiel.get(piwo.getBarwa())+1);
        }
        // ---------------- zliczenie ilosci roznych goryczy ----------------
        this.gorycze = new HashMap<>();
        for (Piwo piwo : tablicaPiw) {
            if (!gorycze.containsKey(piwo.getBarwa())) {
                gorycze.put(piwo.getBarwa(), 0);
            }
            gorycze.put(piwo.getBarwa(),gorycze.get(piwo.getBarwa())+1);
        }
    }

    public void zaklasyfikujPiwo(Piwo piwoDoKlasyfikacji){
        // ---------------- obliczenia dla kazdego stylu ----------------
        for (String styl : stylePiwa.keySet()) {
            //todo obliczenia prawdopodobienstwa
            // barwa -> podana/ podana i styl
            // chmiel -> podana/ podana i styl
            // goryczka -> podana/ podana i styl

            // ---------------- wygładzanie ----------------
            //todo wygladzanie
        }
        //todo wybor maxa
    }

    public ArrayList<Piwo> getTablicaPiw() {
        return tablicaPiw;
    }
}
