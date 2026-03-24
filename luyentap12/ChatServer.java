import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Chat Server: Quan ly ket noi giua 2 nguoi dung
 * Su dung Socket TCP/IP
 */
public class ChatServer {
    private static final int PORT = 8888;
    private static final List<ClientHandler> clients = Collections.synchronizedList(new ArrayList<>());

    public static void main(String[] args) {
        System.out.println("===== BAI 2: CHAT SERVER =====\n");
        System.out.println("Chat Server dang chay tren port " + PORT + "...");
        System.out.println("Cho ket noi tu client...\n");
        
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Nguoi dung moi ket noi tu: " + clientSocket.getInetAddress());
                
                ClientHandler handler = new ClientHandler(clientSocket);
                clients.add(handler);
                new Thread(handler).start();
            }
        } catch (IOException e) {
            System.err.println("Loi server: " + e.getMessage());
        }
    }

    /**
     * Gui tin nhan den tat ca cac client khac
     */
    public static void broadcastMessage(String message, ClientHandler sender) {
        synchronized (clients) {
            for (ClientHandler client : clients) {
                if (client != sender) {
                    client.sendMessage(message);
                }
            }
        }
    }

    /**
     * Xoa client khoi danh sach
     */
    public static void removeClient(ClientHandler client) {
        clients.remove(client);
        System.out.println("Mot nguoi dung da ngat ket noi. Tong so nguoi dung: " + clients.size());
    }

    /**
     * Lop xu ly moi ket noi client
     */
    static class ClientHandler implements Runnable {
        private final Socket socket;
        private PrintWriter out;
        private BufferedReader in;
        private String username;

        public ClientHandler(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            try {
                out = new PrintWriter(socket.getOutputStream(), true);
                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                // Nhan ten nguoi dung
                String line;
                if ((line = in.readLine()) != null) {
                    username = line;
                    System.out.println("Nguoi dung da dat ten: " + username);
                    broadcastMessage("[" + username + " da vao phong chat]", this);
                } else {
                    return;
                }

                // Lang nghe tin nhan tu client
                while ((line = in.readLine()) != null) {
                    if (line.equalsIgnoreCase("EXIT")) {
                        break;
                    }
                    System.out.println(username + ": " + line);
                    broadcastMessage(username + ": " + line, this);
                }

            } catch (IOException e) {
                System.err.println("Loi ket noi client: " + e.getMessage());
            } finally {
                try {
                    socket.close();
                    removeClient(this);
                    if (username != null) {
                        broadcastMessage("[" + username + " da roi phong chat]", this);
                    }
                } catch (IOException e) {
                    System.err.println("Loi dong socket: " + e.getMessage());
                }
            }
        }

        public void sendMessage(String message) {
            if (out != null) {
                out.println(message);
            }
        }
    }
}
