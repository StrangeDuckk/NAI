import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Bayes {
    private ArrayList<Piwo> tablicaPiw;
    private HashMap<String, Integer> stylePiwa;
    private HashMap<String, HashMap<String, Integer>> barwy;
    private HashMap<String, HashMap<String, Integer>> chmiel;
    private HashMap<String, HashMap<String, Integer>> gorycze;

    public Bayes() {
        this.tablicaPiw = new ArrayList<>();
    }

    public void wypelnienieHashMap(){
        // ---------------- mapa klas styli i ilosci wystapien w treningowym ----------------
        //todo zmienic na zwykle intigery a nie na tablice
        this.stylePiwa = new HashMap<>();
        for (Piwo piwo : tablicaPiw) {
            if (!stylePiwa.containsKey(piwo.getStyl()))
                stylePiwa.put(piwo.getStyl(), 0);

            stylePiwa.put(piwo.getStyl(), stylePiwa.getOrDefault(piwo.getStyl(),0)+1);
        }

        // ---------------- zliczenie ilosci barw pod warunkiem stylu ----------------
        this.barwy = new HashMap<>();// dla danego stylu, wystapujace barwy i ile wystapien
        for (Piwo piwo : tablicaPiw) {
            if (!barwy.containsKey(piwo.getStyl()))
                barwy.put(piwo.getStyl(), new HashMap<>());

            Map<String, Integer> barwyDlaStylu = barwy.get(piwo.getStyl());
            barwyDlaStylu.put(piwo.getBarwa(), barwyDlaStylu.getOrDefault(piwo.getBarwa(), 0) + 1);
        }


        // ---------------- zliczenie ilosci chmieli pod warunkiem stylu ----------------
        this.chmiel = new HashMap<>();
        for (Piwo piwo : tablicaPiw) {
            if (!chmiel.containsKey(piwo.getStyl()))
                chmiel.put(piwo.getStyl(), new HashMap<>());

            Map<String, Integer> chmielDlaStylu = chmiel.get(piwo.getStyl());//todo w jaki sposob to dziala na oryginalnej mapie
            chmielDlaStylu.put(piwo.getChmiel(),chmielDlaStylu.getOrDefault(piwo.getChmiel(),0)+1);
        }

        // ---------------- zliczenie ilosci goryczy pod warunkiem stylu ----------------
        this.gorycze = new HashMap<>();
        for (Piwo piwo : tablicaPiw) {
            if (!gorycze.containsKey(piwo.getStyl()))
                gorycze.put(piwo.getStyl(), new HashMap<>());

            Map<String,Integer> goryczDlaStylu = gorycze.get(piwo.getStyl());
            goryczDlaStylu.put(piwo.getGoryczka(), goryczDlaStylu.getOrDefault(piwo.getGoryczka(),0)+1);
        }
    }

    public void zaklasyfikujPiwo(Piwo piwoDoKlasyfikacji){
        // ---------------- obliczenia dla kazdego stylu ----------------
        HashMap<String, Double> obliczoneWybory = new HashMap<>();
        for (String styl : stylePiwa.keySet()) {
            // ---------------- obliczenie dla barwy ----------------
            //todo obliczenia prawdopodobienstwa
            // barwa -> podana pod warunkiem stylu/ ilosc tych barw w stylu
            // chmiel -> podana pod warunkiem stylu/ ilosc tego chmielu w stylu
            // goryczka -> podana pod warunkiem stylu/ ilosc tej goryczy w stylu

            // ---------------- wygładzanie ----------------
            //todo w przypadku gdy podana pod warunkiem stylu
            // lub ilosc danego elementu w stylu jest rowna zero lub null, zrob wygladzenie, do licznika +1
            // do mianownika + jesli to byla barwa to ilosc barw
        }
        //todo wybor maxa
    }

    public ArrayList<Piwo> getTablicaPiw() {
        return tablicaPiw;
    }
}
