public class WektoryRoznejDlugosciException extends Throwable {
    private int first;
    private int second;
    public WektoryRoznejDlugosciException(int first,int second){
        this.first=first;
        this.second=second;
    }

    @Override
    public String toString() {
        return "Długość pierwszego wektora to "+ first+" a drugiego to "+second;
    }
}
