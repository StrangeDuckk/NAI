import java.util.ArrayList;
import java.util.Map;

//czas: do nastepnych zajec
public class Main {
    public static void main(String[] args) {
        Map<String, ArrayList<String>> pliki = Pliki.loadFiles("Jezyki");//sciezka do folderu z plikami txt
        //folder powinien byc w tym samym folderze co main
        Map<String, ArrayList<String>> zbiorTestowy = Pliki.loadFiles("ZbiorTestowy");


        //todo podzielenie na zbior treningowy i testowy

        //todo dla kazdego jezyka jeden perceptron
        //todo uruchamianie sie tylko na swoj jezyk
        //todo dla kazdego jezyka uzyskac tablice znakow dlugosci 26(alfabet -> ilosc litery)
        //todo i z pliku i z konsoli
        //todo zwraca jezyk w jakim jest napisany

    }
}