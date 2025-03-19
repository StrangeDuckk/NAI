package podanieDanychZKlawiatury;

import java.util.ArrayList;
import java.util.Scanner;
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

        System.out.println("podaj dlugosc vektora: ");
        Scanner scanner = new Scanner(System.in);
        int dlugosc = Integer.parseInt(scanner.nextLine());
        ArrayList<Double> input =new ArrayList<>();

        for (int i = 0; i < dlugosc; i++){
            System.out.println("podaj x"+ i +" element vektora: ");
            input.add(Double.parseDouble(scanner.nextLine()));
        }

        Perceptron p = new Perceptron(dlugosc,2,1);
        System.out.println("podaj odpowiedz na ktora liczysz (0 albo 1):");
        int znanaOdpowiedz=Integer.parseInt(scanner.nextLine());


        while(p.Klasyfikacja(input)!=znanaOdpowiedz){
            System.out.println(p.Klasyfikacja(input));
            p.deltha(input,znanaOdpowiedz);
        }
        System.out.println(p.Klasyfikacja(input));

    }
}