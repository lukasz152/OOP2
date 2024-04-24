class Osoba extends Wpis{
    private String imie;
    private String nazwisko;
    private String Adres;
    private Nrtelefoniczny nrtelefonu;
    public Osoba(String imie,String nazwisko,String adres,Nrtelefoniczny nrtelefonu){
        this.imie=imie;
        this.nazwisko=nazwisko;
        this.Adres=adres;
        this.nrtelefonu=nrtelefonu;
    }

    public String get() {
        return Adres;
    }

    @Override
    void opis(){
        System.out.println("Osoba: " + imie + " " + nazwisko + ", adres: " + Adres + ", telefon: " + nrtelefonu);
    }

}
