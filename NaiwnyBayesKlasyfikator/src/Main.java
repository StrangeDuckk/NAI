import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // ---------------- zczytanie danych z pliku ----------------
        File file = new File("piwo.csv");
        ArrayList<String> daneZPliku = new ArrayList<>();
        try ( Scanner sc = new Scanner(file)){
            sc.nextLine();//sciagniecie pierwszego wiersza
           while(sc.hasNextLine()){
               daneZPliku.add(sc.nextLine());
           }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // ---------------- dodanie danych do tablicy piw ----------------
        Bayes bayes = new Bayes();
        for(String piwo : daneZPliku){
            String[] string = piwo.split(",");
            bayes.getTablicaPiw().add(new Piwo(string[0], string[1], string[2], string[3]));
        }
        bayes.wypelnienieHashMap();
        System.out.println(bayes.getTablicaPiw());
        System.out.println("-----------------------------------------------------------------------------------");

        // ---------------- klasyfikacja dwoch piw ----------------
        Piwo piwo1 = new Piwo("bursztynowa","citra","niska","marcowe");

        bayes.zaklasyfikujPiwo(piwo1);

        // ---------------- pytanie do uzytkownika ----------------
        Scanner sc = new Scanner(System.in);
        System.out.println("podaj barwę piwa (ciemna/bursztynowa/zlota): ");
        String barwa = sc.nextLine();
        System.out.println("podaj rodzaj chmielu (marynka,citra,lomik): ");
        String chmiel = sc.nextLine();
        System.out.println("podaj goryczkę (duza/niska): ");
        String goryczka = sc.nextLine();
        bayes.zaklasyfikujPiwo(new Piwo(barwa,chmiel,goryczka,"TO_FIND"));
    }
}