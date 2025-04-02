import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.regex.Pattern;

public class Pliki {
    public static Map<String, ArrayList<String>> loadFiles(String path) {
        Map<String, ArrayList<String>> pliki = new HashMap<>();
        //pierwsze to jezyk, drugie to array z zawartoscia plikow

        File folderJezyki = new File(path);

        if (!folderJezyki.isDirectory() || !folderJezyki.exists()) {
            System.out.println("zla sciezka do folderu z plikami txt dla jezykow");
            return null;
        }
        //---------- utworzenie mapy z jezykami takimi jakie tytuly folderow ----------
        File[] tlumaczenia = folderJezyki.listFiles();
        for (File f : tlumaczenia) {
            pliki.put(f.getName(), new ArrayList<>());
            System.out.println("utworzono klucz " + f.getName() + " w mapie");
        }

        System.out.println(pliki);
        // ----------------------- dodanie zawartosci -----------------------
        for(File f : tlumaczenia) {
            pliki.get(f.getName()).add(zawartosc(f));
        }
        System.out.println(pliki);

        return pliki;
    }

    private static String zawartosc(File f) {
        StringBuilder sb = new StringBuilder();
        try {
            Scanner sc = new Scanner(f);//acces denied
            while (sc.hasNextLine()) {
                sb.append(sc.nextLine());
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return usuniecieSpecyficznychZnakow(sb.toString().toUpperCase());
    }

    private static String usuniecieSpecyficznychZnakow(String ciag) {
        //todo zrobic to pattern macherem
        StringBuilder temp = new StringBuilder();
        for (int i = 0; i < ciag.length(); i++) {
            if (Character.isLetter(ciag.charAt(i))) {
                temp.append(ciag.charAt(i));//zupelnie usuniecie znakow typu "ó"","
            }
        }
        return temp.toString();
    }
}
