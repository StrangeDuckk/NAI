package PerceptronDlaIrysowSetosaNieSetosa;

import java.util.ArrayList;

public class Perceptron {
    /*
    taki jak na zajeciach
    3 pola:
        tablicaWag
        prog
        alpha - stala uczaca
        opcjonalnie beta, jak nie -> uznajemy ze jest wielosci alfy

    metody ->
        konstruktor (dlugosc tablicy wag(same zera), prog, alpha)
        klasyfikacja (int calculate)(input[] vektorXwejsciowy) -> zwraca 0 lub 1
            return input*wagi >=prog? 1 : 0
        uczenie (learn/delta) (input[], poprawnaOdpowiedz)
            uczenei regula delta
     */

    private ArrayList<Double> tablicaWag;
    private double prog;
    private double alpha;

    public Perceptron(int dlugoscTablicaWag, double prog, double alpha) {
        tablicaWag = new ArrayList<>();
        for (int i = 0; i < dlugoscTablicaWag; i++) {
            tablicaWag.add(Math.random()); //losowe wartosci wag
        }
        this.prog = prog;
        this.alpha = alpha;
    }

    public double NetObliczenie(ArrayList<Double> tablicaWag, ArrayList<Double> inputVector) {
        double Net = 0;
        for (int i = 0; i < inputVector.size(); i++) {
            Net += inputVector.get(i)*tablicaWag.get(i);
        }
        return Net;
    }
    public int Klasyfikacja/*compute*/(ArrayList<Double> inputVektor) {//dla uczenia
        return NetObliczenie(tablicaWag, inputVektor) >= prog?1:0;
    }
    public double deltha/*learn*/(ArrayList<Double> inputVector, int answer) {//dla kazdego w train jako pierwsza -> uczenie
        int wynik = Klasyfikacja(inputVector);
        if (wynik==answer){
            return answer; //potem sprawdzac czy wagi sie zmienily
        }
        else//odpowiedz jest inna, wracamy do nauki
        {
            ArrayList<Double> newWagi = new ArrayList<>();
            double Net = NetObliczenie(tablicaWag, newWagi);
            this.prog = prog - (answer-wynik)*alpha;//sprawdzic czy to bedzie dzialac

            return Net>=prog?1:0;
        }
    }
}