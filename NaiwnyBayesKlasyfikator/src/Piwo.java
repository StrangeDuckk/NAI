public class Piwo {
    private String barwa;
    private String chmiel;
    private String goryczka;
    private String styl;

    public Piwo(String barwa, String chmiel, String goryczka, String styl) {
        this.barwa = barwa;
        this.chmiel = chmiel;
        this.goryczka = goryczka;
        this.styl = styl;
    }

    public String getBarwa() {
        return barwa;
    }
    public String getGoryczka() {
        return goryczka;
    }
    public String getStyl() {
        return styl;
    }
    public String getChmiel() {
        return chmiel;
    }

    @Override
    public String toString() {
        return "\nPiwo{" +
                "barwa='" + barwa + '\'' +
                ", chmiel='" + chmiel + '\'' +
                ", goryczka='" + goryczka + '\'' +
                ", styl='" + styl + '\'' +
                "}";
    }
}
