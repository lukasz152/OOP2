import java.io.*;
import java.net.*;

public class Client {
    public static void main(String[] args) {
        final String serverAddress = "localhost";
        final int serverPort = 12345;

        try (Socket socket = new Socket(serverAddress, serverPort);
             BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
            // Odczytanie mess od użytkownika
            System.out.println("Enter your notification message:");
            String notificationMessage = userInput.readLine();
            System.out.println("Enter the time after which you want to receive message (in seconds):");
            int notificationTime;
            while (true) {
                System.out.println("Enter the time (in seconds):");
                String timeInput = userInput.readLine();
                try {
                    notificationTime = Integer.parseInt(timeInput);
                    if (notificationTime < 0) {
                        throw new NegativeTimeException();
                    }
                    break;
                } catch (NumberFormatException | NegativeTimeException e) {
                    System.out.println(e);
                }
            }
            // Wysłanie notyfikacji i czasu do serwera
            out.println(notificationMessage);
            out.println(notificationTime);
            // Odczytanie potwierdzenia od serwera
            String response = in.readLine();
            System.out.println("Server response: " + response);

        } catch (IOException e) {
            System.err.println("Error.");
            e.printStackTrace();
        }
    }
}
