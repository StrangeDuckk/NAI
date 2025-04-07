import java.text.Normalizer;
import java.util.*;

//czas: do nastepnych zajec
public class Main {
    public static void main(String[] args) {
        int IloscCwiczen = 2000; //2000
        double prog = 0.5; //0.4
        double alpha = 0.5; //0.5

        //foldery powinny byc w tym samym folderze co main
        System.out.println("------------zbior treningowy--------------");
        Map<String, ArrayList<String>> pliki = Pliki.loadFiles("Jezyki");//sciezka do folderu z plikami txt
        System.out.println("------------zbior testowy---------------");
        Map<String, ArrayList<String>> zbiorTestowy = Pliki.loadFiles("ZbiorTestowy");

        // ------------- uzupelnianie mapy iloscia wystapien liter -------------
        Map<String, Map<Character, Integer>> znakiIlosciWystapien = new HashMap<>();

        for (String key : Objects.requireNonNull(pliki).keySet()) {
            ArrayList<String> temp = pliki.get(key);
            znakiIlosciWystapien.put(key, zliczIloscWystapien(temp));
        }

        // ------------- utworzenie perceptronow dla kazdego jezyka -------------
        Map<String, Perceptron> perceptrons = new HashMap<>();
        for (String klucz : Objects.requireNonNull(pliki).keySet()) {
            perceptrons.put(klucz, new Perceptron(znakiIlosciWystapien.get("PL").size(), prog, alpha));
        }

        // ------------- Faza 1 -> przejscie po zbiorze treningowym -------------
        obliczanieDlaZbioru(pliki, perceptrons);

        // ------------- Faza 2 -> nauka na zbiorze treningowym -------------
        int iloscEpok = 1;
        while (iloscEpok <= IloscCwiczen){
            iloscEpok++;
            obliczanieDlaZbioru(pliki,perceptrons);
        }
        // ------------- Faza 3 -> przejscie po zbiorze testowym i obliczenie celnosci -------------
        System.out.println("wynik dla zbioru testowego: ");
        obliczanieDlaTestowego(Objects.requireNonNull(zbiorTestowy), perceptrons);

        // ------------- Faza 4 -> przejscie dla danych z konsoli -------------
        Scanner sc = new Scanner(System.in);
        System.out.println("podaj tekst do klasyfikacji lub sciezke do pliku do klasyfikacji (np. ZbiorTestowy\\PL\\JulianTuwim-PL.txt ): ");
        String odpowiedz = sc.nextLine();
        if (odpowiedz.contains("/") || odpowiedz.contains("\\")) {
            Map<String, ArrayList<String>> plikDane = Pliki.loadFiles(odpowiedz);
            if (plikDane == null) {
                System.out.println("Nie udało się wczytać pliku: " + odpowiedz);
            } else {
                obliczanieDlaTestowego(plikDane, perceptrons);
            }
        } else {
            Map<String, ArrayList<String>> temp = new HashMap<>();
            ArrayList<String> aOdpowiedz = new ArrayList<>();
            aOdpowiedz.add(odpowiedz);
            temp.put("0", aOdpowiedz);
            System.out.println();
            obliczanieDlaTestowego(temp, perceptrons);
        }
    }

    public static Map<Character, Integer> zliczIloscWystapien(ArrayList<String> temp){
        Map<Character, Integer> mapaZnakow = new HashMap<>();
        //wypelnienie literami od A do Z z wartosciami 0, zeby kazdy perceptron mial 26 wag
        for (char c = 'A'; c <= 'Z'; c++) {
            mapaZnakow.put(c, 0);
        }

        for (String tekst : temp){
            tekst = normalizacjaTekstu(tekst);
            for(int i = 0; i < tekst.length(); i++){
                mapaZnakow.put(tekst.charAt(i), mapaZnakow.get(tekst.charAt(i))+1);
            }
        }

        return mapaZnakow;
    }
    public static String normalizacjaTekstu(String tekst){
        tekst = tekst.toUpperCase(Locale.ROOT);
        tekst = java.text.Normalizer.normalize(tekst, Normalizer.Form.NFD);//nfd -> rozbije w znaku ę -> e i ,
        tekst = tekst.replaceAll("[^\\p{ASCII}]", "");//usuniecie wszystkich ciapek i spacji
        return tekst.replaceAll("[^A-Z]", "");
    }

    public static void obliczanieDlaZbioru(Map<String, ArrayList<String>> zbiorTestowy,
                                           Map<String, Perceptron> perceptrons) {

        for (String jezykPliku : zbiorTestowy.keySet()){

            Map<Character,Integer> iloscWystapienDlaTekstu = zliczIloscWystapien(zbiorTestowy.get(jezykPliku));
            ArrayList<Double> literyWagi = UzupelnienieTabWag(iloscWystapienDlaTekstu);

            Map<String, Integer> poprawneOdpowiedzi = new HashMap<>();
            for (String key : perceptrons.keySet()){
                if (jezykPliku.equals(key))
                    poprawneOdpowiedzi.put(key,1);
                else
                    poprawneOdpowiedzi.put(key,0);
            }

            for (String jezykPerceptronu: perceptrons.keySet()){
                perceptrons.get(jezykPerceptronu).deltha(literyWagi,poprawneOdpowiedzi.get(jezykPerceptronu));
            }

        }
    }
    private static void obliczanieDlaTestowego(Map<String, ArrayList<String>> plikDane,
                                               Map<String,Perceptron> perceptrons) {

        for (String jezyk: plikDane.keySet()) {
            // ------------------ petla dla kazdego z jezykow -------------------
            for (String tekst: plikDane.get(jezyk)) {
                ArrayList<String> temp = new ArrayList<>();
                temp.add(tekst);
                Map<Character, Integer> iloscWystapienDlaTekstu = zliczIloscWystapien(temp);
                ArrayList<Double> literyWagi = UzupelnienieTabWag(iloscWystapienDlaTekstu);
                System.out.println("-------------------------------------");
                for (String jezykiPerceptronow: perceptrons.keySet()) {
                    System.out.print("odpowiedz perceptrona dla jezyka: " + jezykiPerceptronow+": ");
                    System.out.println(perceptrons.get(jezykiPerceptronow).Klasyfikacja(literyWagi));
                }
            }
        }
    }

    private static ArrayList<Double> UzupelnienieTabWag(Map<Character, Integer> iloscWystapienDlaTekstu) {
        ArrayList<Double> temp = new ArrayList<>();
        int licznik = 0;

        for (char c = 'A'; c<='Z'; c++){
            licznik+=iloscWystapienDlaTekstu.getOrDefault(c,0);
        }

        for(char c = 'A'; c <= 'Z'; c++)
        {
            temp.add( (iloscWystapienDlaTekstu.getOrDefault(c,0)/ (double) licznik));
        }
        return temp;
    }
}