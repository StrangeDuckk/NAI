package PerceptronDlaIrysowSetosaNieSetosa;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
/*
*Napisz klasę "Perceptron", będącą dyskretnym perceptronem unipolarnym, w konstruktorze przyjmującą długość wektora wag,
* próg i stałą uczenia alfa. Poza tym, przechowywać ma również sam wektor wag, a także dwie metody:
* int compute() - przyjmującą wektor wejść i zwracającą odpowiedź perceptrona, oraz
* void learn() przyjmującą wektor wejść i poprawną odpowiedź, realizującą uczenie perceptrona regułą
* delta z modyfikacją progu.

Następnie powyższy perceptron należy użyć do klasyfikacji zbioru Iris (podział jak w poprzednim projekcie)
* na dwie klasy - iris setosa i pozostałe. Na koniec program ma podać celność klasyfikatora.

Propozycje rozwinięcia dla chętnych:
porównać celność klasyfikatora w 3 przypadkach: podziały setosa-reszta, versicolor-reszta i virginica-reszta
wymyślić i zaimplementować sposób do zaklasyfikowania zbioru na 3 klasy zamiast dwó
* */

public class Main {
    public static void main(String[] args) {
        //na za dwa tygodnie, zrobic uczenie na treningowych na irysach nasz perceptron,
        //do okreslonej wartosci celnosci
        //przejsc przez test metoda calculate i sprawdzic na ile daje rade zaklasyfikowac kwiaty,
        //potrzebujemy zeby jeden mowil setosa albo nie setosa np. bo mozemy rozdzielic tylko dwa

        //rozwiniecie -> rozroznianie kwiatow, czy ktores wyraznie gorzej sei klasyfikuja,
        //przy uzyciu wiecej niz jednego perceptrona zaklasyfikowac caly zbior

        //na teraz -> wejsciowe podane

        //-----------------------------------wgranie danych z pliku-----------------------------------
        ArrayList<Irys> zbiorTreningowy = new ArrayList<>();
        ArrayList<Irys> zbiorTestowy = new ArrayList<>();

        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("iris.txt")));
            String linia = br.readLine(); //sciagniecie pierwszej linii bo zawiera naglowki
            int licznik = 0;

            while ((linia = br.readLine())!= null)
            {
                if (licznik % 5 == 0){ //co piaty element brany do testow
                    zbiorTestowy.add(new Irys(linia));
                }
                else
                    zbiorTreningowy.add(new Irys(linia));
                licznik++;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        //-----------------------------------klasyfikacja dla Setosa, pierwsza-----------------------------------
        Perceptron perceptronik = new Perceptron(zbiorTreningowy.get(0).getVektor().size(),0.0,0.1);

        obliczanieDlaZbioru(zbiorTreningowy,perceptronik);

        //-----------------------------------uczenie sie-----------------------------------
        int licznikEpok = 0;
        int licznikPoprawnychEpok = 0;
        while (true){
            double celnoscSetosa = 0;

            for (int i = 0; i < zbiorTreningowy.size(); i++){
                ArrayList<Double> pojedynczyIrys = zbiorTreningowy.get(i).getVektor();

                perceptronik.learn(
                        pojedynczyIrys, zbiorTreningowy.get(i).getKlasa()==KlasaIrysa.Iris_setosa?1:0
                );

                zbiorTreningowy.get(i).setStrzal(
                        perceptronik.compute(pojedynczyIrys) == 1 ? KlasaIrysa.Iris_setosa : KlasaIrysa.Nie_setosa
                );

                if (zbiorTreningowy.get(i).getStrzal() == zbiorTreningowy.get(i).getKlasa()) {
                    celnoscSetosa++;
                }
            }
            System.out.println("celnosc epoki " + (licznikEpok++)+" = " + celnoscSetosa/zbiorTreningowy.size());


            if ( (celnoscSetosa/ zbiorTreningowy.size())==1.0) {
                licznikPoprawnychEpok++;
                if (licznikPoprawnychEpok>=3) //dla douczenia sie zbioru treningowego, bez tego wychodzily kwiatki na testowym
                    break;
            }
            else{
                licznikPoprawnychEpok = 0;
            }
        }

        //-----------------------------------sprawdzenie dla zbioru testowego-----------------------------------

        System.out.println("wynik dla zbioru testowego: ");
        obliczanieDlaZbioru(zbiorTestowy,perceptronik);
    }
    public static void obliczanieDlaZbioru(ArrayList<Irys> zbior, Perceptron perceptron){
        double celnoscSetosa = 0;
        for (int i = 0; i < zbior.size(); i++){

            ArrayList<Double> pojedynczyIrys = zbior.get(i).getVektor();

            zbior.get(i).setStrzal(
                    perceptron.compute(pojedynczyIrys)==1?KlasaIrysa.Iris_setosa:KlasaIrysa.Nie_setosa
            );

            if (zbior.get(i).getStrzal()==zbior.get(i).getKlasa()) {
                celnoscSetosa++;
            }
        }
        System.out.println("celnosc = " + celnoscSetosa/ zbior.size());
    }
}