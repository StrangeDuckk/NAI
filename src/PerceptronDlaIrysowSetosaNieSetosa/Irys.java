package PerceptronDlaIrysowSetosaNieSetosa;

public class Irys {
    //pola:
    //sepal length in cm,sepal width in cm,petal length in cm,petal width in cm,class
    private double sepalLengthInCm;
    private double sepalWidthInCm;
    private double petalLengthInCm;
    private double petalWidthInCm;
    private KlasaIrysa klasa;
    private KlasaIrysa strzal;

    public Irys(String linia) {
        String[] tablica = linia.split(",");
        sepalLengthInCm = Double.parseDouble(tablica[0]);
        sepalWidthInCm = Double.parseDouble(tablica[1]);
        petalLengthInCm = Double.parseDouble(tablica[2]);
        petalWidthInCm = Double.parseDouble(tablica[3]);
        switch (tablica[4]) {
            case "Iris-setosa": {
                klasa = KlasaIrysa.Iris_setosa;
                break;
            }
            case "Iris-versicolor": {
                klasa = KlasaIrysa.Iris_versicolor;
                break;
            }
            case "Iris-virginica": {
                klasa = KlasaIrysa.Iris_virginica;
                break;
            }
        }
    }

    //----------------- gettery i settery -------------------
    public double getSepalLengthInCm() {
        return sepalLengthInCm;
    }

    public double getSepalWidthInCm() {
        return sepalWidthInCm;
    }

    public double getPetalLengthInCm() {
        return petalLengthInCm;
    }

    public double getPetalWidthInCm() {
        return petalWidthInCm;
    }

    public KlasaIrysa getKlasa() {
        return klasa;
    }

    public KlasaIrysa getStrzal() {
        return strzal;
    }

    public void setStrzal(KlasaIrysa strzal) {
        this.strzal = strzal;
    }

    @Override
    public String toString() {
        return "\n"+sepalLengthInCm+", " +
                sepalWidthInCm + ", " +
                petalLengthInCm + ", " +
                petalWidthInCm +
                ", nasza klasyfikacja: " + strzal +
                ", rzeczywista klasa: "+klasa;
    }
}
