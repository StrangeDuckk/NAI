import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

//czas: do nastepnych zajec
public class Main {
    public static void main(String[] args) {
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
            Map<Character, Integer> mapaZnakow = new HashMap<>();

            for (String tekst : temp) {
                for (int i = 0; i < tekst.length(); i++) {
                    mapaZnakow.put(tekst.charAt(i),
                                   mapaZnakow.getOrDefault(tekst.charAt(i), 0) + 1);
                }
            }
            znakiIlosciWystapien.put(key, mapaZnakow);
        }

        // ------------- utworzenie perceptronow dla kazdego jezyka -------------
        Map<String, Perceptron> perceptrons = new HashMap<>();
        for (String klucz : Objects.requireNonNull(pliki).keySet())
        {
            perceptrons.put(klucz, new Perceptron(znakiIlosciWystapien.get("PL").size(),prog,alpha));//todo dodac dlugosc tablicy wag
        }


        // ------------- Faza 1 -> przejscie po zbiorze treningowym -------------

        // ------------- Faza 2 -> nauka na zbiorze treningowym -------------

        // ------------- Faza 3 -> przejscie po zbiorze testowym i obliczenie celnosci -------------

        // ------------- Faza 4 -> przejscie dla danych z konsoli -------------

        //todo uruchamianie sie tylko na swoj jezyk
        //todo i z pliku i z konsoli
        //todo zwraca jezyk w jakim jest napisany na podstawie porownania zgadniecia i klucza

    }
//    public static void obliczanieDlaZbioru(ArrayList<String> tablicaTekstow, Perceptron perceptron){
        /*
        Napisać jednowarstwową sieć, która przyjmuje na wejście częstości poszczególnych liter w tekście,
        a na wyjściu zwraca język, w jakim tekst jest napisany.
        Program powinien również wczytać dane testowe na których zweryfikuje swoją poprawność.
        Program ma być w stanie przyjąć wejście zarówno w formie pliku tekstowego podanego przez użytkownika,
        jak i w formie tekstu wpisanego z konsoli.*/
//        double celnoscSetosa = 0;
//        for (int i = 0; i < tablicaTekstow.size(); i++){
            //todo dla konkretnego tekstu pobrac czestosci liter


            //todo dla tych czestosci zrobic perceptron compute

//            zbior.get(i).setStrzal(
//                    perceptron.compute(pojedynczyIrys)==1?KlasaIrysa.Iris_setosa:KlasaIrysa.Nie_setosa
//            );
//            //todo obliczyc celnosc
//
//            if (zbior.get(i).getStrzal()==zbior.get(i).getKlasa()) {
//                celnoscSetosa++;
//            }
//        }
//        System.out.println("celnosc = " + celnoscSetosa/ zbior.size());
//    }
}