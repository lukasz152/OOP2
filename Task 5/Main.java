import javax.print.DocFlavor;
import java.util.TreeMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Iterator;

public class Main {
    public static void main(String[] args) {
        TreeMap<Nrtelefoniczny, Wpis> tree = new TreeMap<>();
        tree.put(new Nrtelefoniczny(48,123456789),new Osoba("Łukasz","Milowski","ul konwaliowa",new Nrtelefoniczny(48, 123456789)));
        tree.put(new Nrtelefoniczny(48,123456788),new Osoba("Jan","Kowalski","ul łaska",new Nrtelefoniczny(48, 123456788)));
        tree.put(new Nrtelefoniczny(48,111122223),new Osoba("Marek","Markowski","ul drewniana",new Nrtelefoniczny(48, 111122223)));

        tree.put(new Nrtelefoniczny(48, 444555666), new Firma("Kowlascy Sp zoo", "ul. wojska polskiego", new Nrtelefoniczny(48, 444555666)));
        tree.put(new Nrtelefoniczny(48, 777788889), new Firma("Orlen ", "ul. wojska polskiego", new Nrtelefoniczny(48, 777788889)));


        System.out.println("Książka telefoniczna:");
        List<String> TABLICA = new ArrayList<>();
        //usuwanie powtarzajacych sie ulic

        for (Nrtelefoniczny key : tree.keySet()) {
            if(TABLICA.contains(tree.get(key).get())){
                tree.remove(key);
            }
            else{
                TABLICA.add(tree.get(key).get());
            }
        }
        Iterator<Nrtelefoniczny> iterator = tree.keySet().iterator();
        while (iterator.hasNext()) {
            Nrtelefoniczny key = iterator.next();
            tree.get(key).opis();
        }
    }
}