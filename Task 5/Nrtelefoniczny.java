public class Nrtelefoniczny implements Comparable<Nrtelefoniczny> {
    private int nrkierunkowy;
    private int nrTelefonu;
    public Nrtelefoniczny(int nrKierunkowy, int nrTelefonu) {
        this.nrkierunkowy = nrKierunkowy;
        this.nrTelefonu = nrTelefonu;
    }
    @Override
    public int compareTo(Nrtelefoniczny o) {
        if (this.nrTelefonu > o.nrTelefonu) return 1;
        else if (this.nrTelefonu < o.nrTelefonu) return -1;
        return 0;
    }
    @Override
    public String toString() {
        return "+" + nrkierunkowy + " " + nrTelefonu;
    }

}
