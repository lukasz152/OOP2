class Firma extends Wpis{
    private String nazwa;
    private String adres;
    private Nrtelefoniczny nrtelefonu;
    public Firma(String nazwa,String adres,Nrtelefoniczny nrtelefonu){
        this.nazwa=nazwa;
        this.adres=adres;
        this.nrtelefonu=nrtelefonu;
    }
    public String get() {
        return adres;
    }
    @Override
    void opis(){
        System.out.println("Firma: " + nazwa + ", adres: " + adres + ", telefon: " + nrtelefonu);
    }
}
