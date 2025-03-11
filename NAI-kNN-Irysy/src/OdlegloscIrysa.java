public class OdlegloscIrysa {
    private double odleglosc;
    private KlasaIrysa klasa;

    public OdlegloscIrysa(double wynik, KlasaIrysa klasa) {
        this.odleglosc = wynik;
        this.klasa = klasa;
    }

    public void setOdleglosc(double odleglosc) {
        this.odleglosc = odleglosc;
    }

    public void setKlasa(KlasaIrysa klasa) {
        this.klasa = klasa;
    }

    public double getOdleglosc() {
        return odleglosc;
    }

    public KlasaIrysa getKlasa() {
        return klasa;
    }

    public int compareTo(OdlegloscIrysa j) {
        return Double.compare(this.odleglosc, j.odleglosc);
    }
}
