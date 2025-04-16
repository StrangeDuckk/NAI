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

            // prawdopodobienstwo na dana barwe pod warunkiem stylu / ilosc stylu
            double pracwBarwa = 0.0;
            int temp = this.barwy.get(styl).getOrDefault(piwoDoKlasyfikacji.getBarwa(),0);
            if (temp != 0)
               pracwBarwa = (double) temp/this.stylePiwa.get(styl);
            else
                pracwBarwa = ((double) temp + 1) / (this.stylePiwa.get(styl) + this.barwy.get(styl).size());//todo sprawdzenie czy po barwy dodawanie

            System.out.println(pracwBarwa);
            // chmiel -> podana pod warunkiem stylu/ ilosc tego chmielu w stylu

            // goryczka -> podana pod warunkiem stylu/ ilosc tej goryczy w stylu

            // prawdopodobienstwo na dany styl // tutaj nigdy nie bedzie potrzebne wygladzanie
            double prawdStyl = (double) this.stylePiwa.get(styl) / this.tablicaPiw.size();
        }
        //todo wybor maxa
    }
    public double wygladzenie(String styl, String atrybut)
    {
        // ---------------- wygładzanie ----------------
        //todo w przypadku gdy podana pod warunkiem stylu
        // lub ilosc danego elementu w stylu jest rowna zero lub null, zrob wygladzenie, do licznika +1
        // do mianownika + jesli to byla barwa to ilosc barw
        return 0.0;
    }

    public ArrayList<Piwo> getTablicaPiw() {
        return tablicaPiw;
    }
}
