import java.io.*;
import java.net.*;
import java.util.concurrent.*;

public class server {
    public static void main(String[] args) {
        final int serverPort = 12345;

        try (ServerSocket serverSocket = new ServerSocket(serverPort)) {
            System.out.println("Server is running on port " + serverPort);

            ExecutorService executorService = Executors.newCachedThreadPool();

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("New client connected: " + clientSocket.getInetAddress());
                // Obsługa klienta w oddzielnym wątku
                executorService.submit(new ClientHandler(clientSocket));
            }
        } catch (IOException e) {
            System.err.println("Error while running the server.");
            e.printStackTrace();
        }
    }
}

class ClientHandler implements Runnable {
    private final Socket clientSocket;

    public ClientHandler(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
             PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)) {
            // Odczytanie notyfikacji od klienta
            String notificationMessage = in.readLine();
            String notificationTime = in.readLine();
            System.out.println("Received from client " + clientSocket.getInetAddress() + ": " + notificationMessage);

            //czas
            int timeInSeconds = Integer.parseInt(notificationTime);
            // Uśpienie wątku
            TimeUnit.SECONDS.sleep(timeInSeconds);

            // Wysłanie potwierdzenia do klienta
            out.println("Received notification successfully.");

        } catch (IOException | InterruptedException e) {
            System.err.println("Error while handling client request.");
            e.printStackTrace();
        } finally {
            try {
                clientSocket.close();
            } catch (IOException e) {
                System.err.println("Error while closing client socket.");
                e.printStackTrace();
            }
        }
    }
}
