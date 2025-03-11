import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        //-------------------------------wgrywanie danych---------------------------------------
        ArrayList<Irys> zbiorTreningowy = new ArrayList<>();
        ArrayList<Irys> zbiorTestowy = new ArrayList<>();
        int k;
        Scanner sc = new Scanner(System.in);
        System.out.println("podaj k do kNN: ");
        k = Integer.parseInt(sc.nextLine());

        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("iris.txt")));
            String linia = br.readLine(); //sciagniecie pierwszej linii bo zawiera naglowki
            int licznik = 0;

            while ((linia = br.readLine())!= null)
            {
                if (licznik % k == 0){ //co piaty element brany do testow
                    zbiorTestowy.add(new Irys(linia));
                }
                else
                    zbiorTreningowy.add(new Irys(linia));
                licznik++;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        // --------------- chodzenie po tablicy zbioru testowego i porownywanie z treningowym---------
        for (int i = 0; i < zbiorTestowy.size(); i++){
            int licznik=0;
            ArrayList<OdlegloscIrysa> odlegloscIrysa = new ArrayList<>();
            double wynik = 0.0;
            for (int j = 0; j < zbiorTreningowy.size(); j++){
                wynik = ObliczanieWyniku(zbiorTestowy.get(i), zbiorTreningowy.get(j));
                odlegloscIrysa.add(new OdlegloscIrysa(wynik, zbiorTreningowy.get(j).getKlasa()));

            }
            odlegloscIrysa.sort((m,n)->{ return -1 * m.compareTo(n);});

            ArrayList<OdlegloscIrysa> temp = new ArrayList<>();
            for (int m = 0;  m < k; m++){
                temp.add(odlegloscIrysa.get(odlegloscIrysa.size()-1-m));
            }
            zbiorTestowy.get(i).setStrzal(DecyzjaKlasa(temp));


        }

        System.out.println(zbiorTestowy);

        // ----------------------------- celnosc ----------------------------
        int iloscPoprawnieZaklasyfikowanych = 0;
        int wszystkieZaklasyfiwkowane = zbiorTestowy.size();
        for (int i = 0;  i < zbiorTestowy.size(); i++){
            if (zbiorTestowy.get(i).getKlasa() == zbiorTestowy.get(i).getStrzal())
                iloscPoprawnieZaklasyfikowanych++;
        }

        System.out.println("-------------------------------------------------");
        System.out.println("celnosc: "+iloscPoprawnieZaklasyfikowanych+"/"+wszystkieZaklasyfiwkowane+" = " +
                ((double)iloscPoprawnieZaklasyfikowanych/wszystkieZaklasyfiwkowane));
    }

    private static KlasaIrysa DecyzjaKlasa(ArrayList<OdlegloscIrysa> temp) {
        int iloscSetosa = 0, iloscVersicolar = 0, iloscVirginica = 0;
        for (int i = 0; i < temp.size(); i++){
            switch(temp.get(i).getKlasa()){
                case Iris_setosa -> iloscSetosa++;
                case Iris_versicolor -> iloscVersicolar++;
                case Iris_virginica -> iloscVirginica++;
            }
        }

        if (iloscSetosa>=3)
            return KlasaIrysa.Iris_setosa;
        else if (iloscVersicolar>=3)
            return KlasaIrysa.Iris_versicolor;
        else if (iloscVirginica>=3)
            return KlasaIrysa.Iris_virginica;
        else
            return temp.get(0).getKlasa(); //gdy rowne wartosci i nie mozna stwierdzic przyjmuje
        // ze ta najblizsza klasa jest najodpowiedniejsza
    }

    public static double ObliczanieWyniku(Irys test, Irys znany){
        return Math.pow((test.getPetalLengthInCm() - znany.getPetalLengthInCm()),2) +
                Math.pow((test.getPetalWidthInCm()) - znany.getPetalWidthInCm(),2) +
                Math.pow((test.getSepalLengthInCm()) - znany.getSepalLengthInCm(),2) +
                Math.pow((test.getSepalWidthInCm() - znany.getSepalWidthInCm()),2);
    }
}
