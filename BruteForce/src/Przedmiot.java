import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Przedmiot {
    private int waga;
    private int wartosc;

    public Przedmiot(int waga, int wartosc) {
        this.waga = waga;
        this.wartosc = wartosc;
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
         System.out.println(sumyWagIWartosci);

         //--------- walidacja czy suma wag sie zgadza ---------
//        ArrayList<ArrayList>


        return null;
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

    public int getWartosc() {
        return wartosc;
    }

    public int getWaga() {
        return waga;
    }
}
