import java.util.ArrayList;
import java.util.Scanner;

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
            System.out.println("podaj "+ (dlugosc-i) +" element vektora: ");
            input.add(Double.parseDouble(scanner.nextLine()));
        }

        Perceptron p = new Perceptron(3,2,1);
        int znanaOdpowiedz = 1;

        while(p.Klasyfikacja(input)!=znanaOdpowiedz){
            System.out.println(p.Klasyfikacja(input));
            p.deltha(input,znanaOdpowiedz);
        }
        System.out.println(p.Klasyfikacja(input));

    }
}