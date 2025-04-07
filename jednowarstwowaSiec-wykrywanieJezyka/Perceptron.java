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
            tablicaWag.add(/*Math.random()*/0.0); //losowe wartosci wag lub 0, przy 0 za kazdym razem wynik taki sam
        }
        this.prog = prog;
        this.alpha = alpha;
    }

    public double NetObliczenie(ArrayList<Double> inputVector) {
        double Net = 0;
        for (int i = 0; i < inputVector.size(); i++) {
            Net += inputVector.get(i)*this.tablicaWag.get(i);
        }
        return Net;
    }

    public void deltha(ArrayList<Double> inputVector, int answer) {
        int wynik = Klasyfikacja(inputVector);

        if (wynik != answer) {
            for (int i = 0; i < tablicaWag.size(); i++) {
                double w = tablicaWag.get(i);
                double x = inputVector.get(i);
                this.tablicaWag.set(i, w + alpha * (answer - wynik) * x);
            }

            // Aktualizacja progu
            this.prog = prog - alpha * (answer - wynik);
        }
    }

    public int Klasyfikacja(ArrayList<Double> inputVektor) {
        return NetObliczenie(inputVektor) >= this.prog?1:0;
    }
}