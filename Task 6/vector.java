import java.util.ArrayList;

public class vector {
    private int dlugosc_pierwszego;
    private int dlugosc_drugiego;
    private ArrayList<String> tab1 = new ArrayList<>();

    @Override
    public String toString() {
        return "dlugosc_pierwszego= " + dlugosc_pierwszego + ", dlugosc_drugiego= " + dlugosc_drugiego;
    }
    public void add_string_to_list_as_numbers(String zdanie) {
        tab1.clear();
        zdanie=zdanie.strip();
        String[] slowa = zdanie.split("\\s+");
        for(String slowo:slowa){
            tab1.add(slowo);
        }
    }
    public int czy_sa_liczby(){
        for(String slowo:tab1){
            for(int i=0;i<slowo.length();i++){
                if(Character.isDigit(slowo.charAt(i))){
                    continue;
                }
                tab1.clear();
                return 1;
            }
        }
        return 0;
    }
    public int lenght_of_vector(){
        return tab1.size();
    }

    public ArrayList<String> getTab1() {
        return tab1;
    }
}
