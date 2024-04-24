import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        vector wektor_pierwszy = new vector();
        vector wektor_drugi = new vector();
        while (true) {
            System.out.println("Podaj pierwszy wektor");

            String zdanie;
            zdanie = scanner.nextLine();

            wektor_pierwszy.add_string_to_list_as_numbers(zdanie);
            int res = wektor_pierwszy.czy_sa_liczby();
            while (res == 1||zdanie.isEmpty()) {
                System.out.println("Podaj poprawne liczby do pierwszego wektor_pierwszy!");
                zdanie = scanner.nextLine();
                wektor_pierwszy.add_string_to_list_as_numbers(zdanie);
                res = wektor_pierwszy.czy_sa_liczby();
            }
            System.out.println("Podaj wektor drugi");
            zdanie = scanner.nextLine();
            wektor_drugi.add_string_to_list_as_numbers(zdanie);
            int res2 = wektor_drugi.czy_sa_liczby();
            while (res2 == 1||zdanie.isEmpty()) {
                System.out.println("Podaj poprawne liczby do drugiego wektora!");
                zdanie = scanner.nextLine();
                wektor_drugi.add_string_to_list_as_numbers(zdanie);
                res2 = wektor_drugi.czy_sa_liczby();
            }
            int len_of_1_wektor = wektor_pierwszy.lenght_of_vector();
            int len_of_2_wektor = wektor_drugi.lenght_of_vector();
            try {
                if (len_of_2_wektor != len_of_1_wektor) {
                    throw new WektoryRoznejDlugosciException(len_of_1_wektor, len_of_2_wektor);
                }
            } catch (WektoryRoznejDlugosciException e) {
                System.out.println(e.toString());
               continue;
            }
            break;
        }
        File file;
        try {
            file = new File("wektory");
        } catch (NullPointerException e) {
            System.out.println("Blad pliku");
            return;
        }
        try {
            file.createNewFile();
        } catch (IOException e) {
            System.out.println("Blad tworzenia pliku");
            return;
        }
        FileWriter writer;
        try {
            writer = new FileWriter("wektory");
        } catch (IOException e) {
            System.out.println("Blad pisania w pliku");
            return;
        }
        ArrayList<String> num = wektor_pierwszy.getTab1();
        ArrayList<String> num2 = wektor_drugi.getTab1();
        int len_of_1_wektor = wektor_pierwszy.lenght_of_vector();
        for (int i = 0; i < len_of_1_wektor; i++) {

            int resa = Integer.parseInt(num.get(i));
            int resu = Integer.parseInt(num2.get(i));
            try {
                writer.write(resa + resu + " ");
            } catch (IOException e) {
                System.out.println("Blad pisania");
                return;
            }
        }
        try {
            writer.close();
        } catch (IOException e) {
            System.out.println("Blad zamkniecia pliku");
            return;
        }

    }
}