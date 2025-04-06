import java.util.*;

//czas: do nastepnych zajec
public class Main {
    public static void main(String[] args) {
        int IloscCwiczen = 5;
        double prog = 0;
        double alpha = 0.1;

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
        System.out.print("wynik dla epoki 0: ");
        obliczanieDlaZbioru(pliki, znakiIlosciWystapien, perceptrons);

        // ------------- Faza 2 -> nauka na zbiorze treningowym -------------
        int iloscEpokBezBledu = 0;
        int iloscEpok = 1;
        while (iloscEpokBezBledu <= IloscCwiczen){
            iloscEpok++;
            double wynik = obliczanieDlaZbioru(pliki,znakiIlosciWystapien,perceptrons);
            if ( wynik == 1.0)
                iloscEpokBezBledu++;
            else
                iloscEpokBezBledu = 0;

            System.out.println("wynik dla epoki " + iloscEpok+" : " + wynik);
        }
        // ------------- Faza 3 -> przejscie po zbiorze testowym i obliczenie celnosci -------------
        System.out.print("wynik dla zbioru testowego: ");
        obliczanieDlaZbioru(Objects.requireNonNull(zbiorTestowy), znakiIlosciWystapien, perceptrons);

        // ------------- Faza 4 -> przejscie dla danych z konsoli -------------
        Scanner sc = new Scanner(System.in);
        System.out.println("podaj tekst do klasyfikacji lub sciezke do pliku do klasyfikacji: ");
        String odpowiedz = sc.nextLine();
        if (odpowiedz.contains("/") || odpowiedz.contains("\\"))
            obliczanieDlaZbioru(Objects.requireNonNull(Pliki.loadFiles(odpowiedz)), znakiIlosciWystapien,perceptrons);
        else {
            Map<String, ArrayList<String>> temp = new HashMap<>();
            ArrayList<String > aOdpowiedz = new ArrayList<>();
            aOdpowiedz.add(odpowiedz);
            temp.put("0",aOdpowiedz);
            obliczanieDlaZbioru(temp, znakiIlosciWystapien, perceptrons);
        }

        //todo i z pliku i z konsoli
        //todo zwraca jezyk w jakim jest napisany na podstawie porownania zgadniecia i klucza
    }

    private static Map<Character, Integer> zliczIloscWystapien(ArrayList<String> temp){
        Map<Character, Integer> mapaZnakow = new HashMap<>();
        //wypelnienie literami od A do Z z wartosciami 0, zeby kazdy perceptron mial 26 wag
        for (char c = 'A'; c <= 'Z'; c++) {
            mapaZnakow.put(c, 0);
        }

        for (String tekst : temp){
            for(int i = 0; i < tekst.length(); i++){
                mapaZnakow.put(tekst.charAt(i), mapaZnakow.get(tekst.charAt(i))+1);
            }
        }
        return mapaZnakow;
    }

    public static double obliczanieDlaZbioru(Map<String, ArrayList<String>> zbiorTestowy,
                                           Map<String, Map<Character, Integer>> znakiIlosciWystapien ,
                                           Map<String, Perceptron> perceptrons) {
        /*
        Napisać jednowarstwową sieć, która przyjmuje na wejście częstości poszczególnych liter w tekście,
        a na wyjściu zwraca język, w jakim tekst jest napisany.
        Program powinien również wczytać dane testowe na których zweryfikuje swoją poprawność.
        Program ma być w stanie przyjąć wejście zarówno w formie pliku tekstowego podanego przez użytkownika,
        jak i w formie tekstu wpisanego z konsoli.*/
        double celnosc = 0;
        int licznikPojedynczychJezykow = 0;

        for (String jezyk: zbiorTestowy.keySet()) {
            // ------------------ petla dla kazdego z jezykow -------------------
            for (String tekst: zbiorTestowy.get(jezyk)) {
                ArrayList<String> temp = new ArrayList<>();
                temp.add(tekst);
                Map<Character,Integer> iloscWystapienDlaTekstu = zliczIloscWystapien(temp);
                ArrayList<Double> literyWagi = UzupelnienieTabWag(iloscWystapienDlaTekstu);

                // ------------------- uczenie perceptronu -------------------
                if (perceptrons.get(jezyk).deltha(literyWagi, 1) == 1)
                    celnosc++;
                licznikPojedynczychJezykow++;
            }
        }
                    //perceptron.Klasyfikacja(/*tablica wag*/) == 1 ?  :
        return celnosc/licznikPojedynczychJezykow;
    }

    private static ArrayList<Double> UzupelnienieTabWag(Map<Character, Integer> iloscWystapienDlaTekstu) {
        ArrayList<Double> temp = new ArrayList<>();
        for (Character litera: iloscWystapienDlaTekstu.keySet()) {
            temp.add(Double.valueOf(iloscWystapienDlaTekstu.get(litera)));
        }
        return temp;
    }
}