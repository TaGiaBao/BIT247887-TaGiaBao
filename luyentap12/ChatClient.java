import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 * Chat Client - Console Version (Phiên bản console, chạy ngay được)
 */
public class ChatClient {
    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;
    private String username;
    private volatile boolean isConnected = false;

    public static void main(String[] args) {
        ChatClient client = new ChatClient();
        client.start();
    }

    public void start() {
        System.out.println("===== BAI 2: CHAT APPLICATION (CONSOLE VERSION) =====\n");
        
        Scanner scanner = new Scanner(System.in);

        // Nhap thong tin ket noi
        System.out.print("Nhap host (mac dinh: localhost): ");
        String host = scanner.nextLine().trim();
        if (host.isEmpty()) host = "localhost";

        System.out.print("Nhap port (mac dinh: 8888): ");
        String portStr = scanner.nextLine().trim();
        int port = 8888;
        try {
            if (!portStr.isEmpty()) port = Integer.parseInt(portStr);
        } catch (NumberFormatException e) {
            System.out.println("Port khong hop le, su dung 8888");
        }

        System.out.print("Nhap ten (username): ");
        username = scanner.nextLine().trim();
        if (username.isEmpty()) {
            System.out.println("Ten khong the trong!");
            scanner.close();
            return;
        }

        // Ket noi den server
        if (!connect(host, port)) {
            System.out.println("Ket noi that bai!");
            scanner.close();
            return;
        }

        System.out.println("\nDa ket noi. Goi 'quit' de thoat.\n");

        // Thread lang nghe tin nhan
        Thread listenerThread = new Thread(this::listenForMessages);
        listenerThread.setDaemon(true);
        listenerThread.start();

        // Loop nhan tin nhan tu user
        while (isConnected) {
            System.out.print("Ban: ");
            String message = scanner.nextLine();

            if (message.equalsIgnoreCase("quit")) {
                break;
            }

            if (message.isEmpty()) continue;

            out.println(message);
        }

        disconnect();
        scanner.close();
    }

    private boolean connect(String host, int port) {
        try {
            socket = new Socket(host, port);
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            // Gui ten
            out.println(username);

            isConnected = true;
            return true;
        } catch (IOException e) {
            System.out.println("Loi ket noi: " + e.getMessage());
            return false;
        }
    }

    private void listenForMessages() {
        try {
            String message;
            while ((message = in.readLine()) != null && isConnected) {
                System.out.println(message);
                System.out.print("Ban: ");
            }
        } catch (IOException e) {
            if (isConnected) {
                System.out.println("\nMat ket noi voi server!");
            }
        } finally {
            isConnected = false;
        }
    }

    private void disconnect() {
        try {
            isConnected = false;
            if (out != null) {
                out.println("EXIT");
            }
            if (socket != null && !socket.isClosed()) {
                socket.close();
            }
            System.out.println("Da ngat ket noi.");
        } catch (IOException e) {
            System.err.println("Loi: " + e.getMessage());
        }
    }
}
