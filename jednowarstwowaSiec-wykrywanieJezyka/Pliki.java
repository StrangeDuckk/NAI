import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.regex.Pattern;

public class Pliki {
    public static Map<String, ArrayList<String>> loadFiles(String path) {
        Map<String, ArrayList<String>> pliki = new HashMap<>();
        //pierwsze to jezyk, drugie to array z zawartoscia plikow

        File folderJezyki = new File(path);

        if (!folderJezyki.exists()) {
            System.out.println("zla sciezka do folderu z plikami txt dla jezykow");
            return null;
        }

        // ------------------ dla pojedynczego pliku ---------------------
        if (folderJezyki.isFile() && path.endsWith(".txt")) {
            ArrayList<String> zawartosci = new ArrayList<>();
            zawartosci.add(zawartosc(folderJezyki));
            pliki.put("INPUT", zawartosci); // klucz "INPUT"
            return pliki;
        }

        // ---------- utworzenie mapy z jezykami takimi jakie tytuly folderow ----------
        File[] tlumaczenia = folderJezyki.listFiles();
        for (File f : tlumaczenia) {
            pliki.put(f.getName(), new ArrayList<>());
            System.out.println("utworzono klucz " + f.getName() + " w mapie");
        }

        // ----------------------- dodanie zawartosci -----------------------
        for(File f : tlumaczenia) {
            if (f.isDirectory()) {
                File[] plikiTxt = f.listFiles(((dir, name) -> name.endsWith(".txt"))); //dodanie nowych plikow txt do kolejki
                if(!pliki.isEmpty())
                    for(File plik: plikiTxt)
                        pliki.get(f.getName()).add(zawartosc(plik));
            }
        }

        return pliki;
    }
    private static String zawartosc(File f) {
        StringBuilder sb = new StringBuilder();
        try {
            Scanner sc = new Scanner(f);
            while (sc.hasNextLine()) {
                sb.append(sc.nextLine());
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return Main.normalizacjaTekstu(sb.toString().toUpperCase());
    }
}
