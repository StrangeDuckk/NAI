import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Map<Integer,Przedmiot> elementyMapa = new HashMap<>();
        elementyMapa.put(0,new Przedmiot(5,4));
        elementyMapa.put(1,new Przedmiot(4,3));
        elementyMapa.put(2,new Przedmiot(3,3));
        elementyMapa.put(3,new Przedmiot(6,7));

        // ------------ uzycie skanera ------------
//        elementyMapa.clear();
//        Scanner sc = new Scanner(System.in);
//        System.out.println("podaj pojemnosc plecaka");
//        int plecakPojemnosc = Integer.parseInt(sc.nextLine());
//
//        System.out.println("podaj ilsoc dostepnych elementow");
//        int iloscElementow = Integer.parseInt(sc.nextLine());
//
//        for (int i = 0; i < iloscElementow; i++) {
//            System.out.println("podaj wage elementu: "+i+": ");
//            int waga = Integer.parseInt(sc.nextLine());
//            System.out.println("podaj wartosc elementu: "+i+": ");
//            int wartosc = Integer.parseInt(sc.nextLine());
//
//            elementyMapa.put(i,new Przedmiot(waga, wartosc));
//        }

        System.out.println("najlepszy (najbardziej optymalny) zestaw elementow to: \n"+Przedmiot.obliczBruteForce(elementyMapa));
    }
}