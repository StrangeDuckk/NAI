import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Przedmiot {
    private static int pojemnoscPlecaka;
    private int waga;
    private int wartosc;

    public Przedmiot(int waga, int wartosc) {
        this.waga = waga;
        this.wartosc = wartosc;
    }
    public static void SetPojemnoscPlecaka(int pojemnosc){
        pojemnoscPlecaka = pojemnosc;
    }

     public static List<Przedmiot> obliczBruteForce(Map<Integer, Przedmiot> przedmiotyLista){
        // --------------- obliczenie tablicy binarnej ------------------
        List<List<Integer>> wynikiBin = new ArrayList<>();

        for (int i = 0; i < (int)(Math.pow(przedmiotyLista.size(), 2)); i++) {
            ArrayList<Integer> wynik = zamienNaBin(i);

            while(wynik.size() != przedmiotyLista.size()) {
                wynik.add(0);
            }

            List<Integer> temp = wynik.reversed();
            wynikiBin.add(temp);
        }

        // ----------- obliczenie sumy wag i wartosci i zapamietanie ich --------------
         ArrayList<ArrayList<Integer>> sumyWagIWartosci = new ArrayList<>();

         int licznik= 0;
         for (List<Integer> wynik: wynikiBin) {
             int sumaWag=0;
             int sumaWartosc=0;

             for (int i = 0; i < wynik.size(); i++) {
                 if (wynik.get(i) != 0){
                     sumaWag += przedmiotyLista.get(i).waga;
                     sumaWartosc += przedmiotyLista.get(i).wartosc;
                 }
             }
             sumyWagIWartosci.add(new ArrayList<>());
             sumyWagIWartosci.get(licznik).add(sumaWag);
             sumyWagIWartosci.get(licznik).add(sumaWartosc);
             licznik++;
         }

         //--------- walidacja czy suma wag sie zgadza ---------
        ArrayList<ArrayList<Integer>> spelniajaceWarunkiSumyWagIWartosci = new ArrayList<>();
        licznik = 0;
        int staryIndex = 0;
        for (ArrayList<Integer> sumyWiW : sumyWagIWartosci)
        {
            if(sumyWiW.get(0) <= pojemnoscPlecaka){
                spelniajaceWarunkiSumyWagIWartosci.add( new ArrayList<>());
                spelniajaceWarunkiSumyWagIWartosci.get(licznik).add(staryIndex);
                spelniajaceWarunkiSumyWagIWartosci.get(licznik).add(sumyWiW.get(0));
                spelniajaceWarunkiSumyWagIWartosci.get(licznik).add(sumyWiW.get(1));
                licznik++;
            }
            staryIndex++;
        }

        //zwrocenie maxa
        int max = FindMax(spelniajaceWarunkiSumyWagIWartosci);
        int indexMax;
        for (indexMax = 0; indexMax < spelniajaceWarunkiSumyWagIWartosci.size(); indexMax++){
            if (max == spelniajaceWarunkiSumyWagIWartosci.get(indexMax).get(2))
                break;
        }

        List<Przedmiot> przedmiotOutput = new ArrayList<>();
        for (int i = 0; i < wynikiBin.get(indexMax).size(); i++){
            if (wynikiBin.get(indexMax).get(i) == 1){
                przedmiotOutput.add(przedmiotyLista.get(i));
            }
        }

        return przedmiotOutput;
     }

    private static int FindMax(ArrayList<ArrayList<Integer>> spelniajaceWarunkiSumyWagIWartosci) {
        int max = 0;
        for (ArrayList<Integer> sumy : spelniajaceWarunkiSumyWagIWartosci){
            if (sumy.get(1) >max){
                max = sumy.get(1);
            }
        }
        return max;
    }

    private static ArrayList<Integer> zamienNaBin(int i) {
        ArrayList<Integer> wynik = new ArrayList<>();
        if (i == 0){
            wynik.add(0);
            return wynik;
        }

        while(i>0){
            wynik.add(i%2);
            i=i/2;
        }

        return wynik;
    }

    @Override
    public String toString() {
        return "\nPrzedmiot\n{" +
                "\n\twaga=" + waga +
                ", \n\twartosc=" + wartosc +"\n}";
    }
}
