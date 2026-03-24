import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.io.*;
import java.net.Socket;

/**
 * Chat Client: Giao dien JavaFX cho client chat
 */
public class bai2 extends Application {
    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;
    
    private TextArea chatArea;
    private TextField messageField;
    private TextField usernameField;
    private Button connectButton;
    private Button sendButton;
    private Label statusLabel;
    private boolean isConnected = false;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Chat Application - Local");
        primaryStage.setWidth(600);
        primaryStage.setHeight(500);

        // Main layout
        BorderPane root = new BorderPane();
        root.setPadding(new Insets(10));

        // Top: Connection panel
        VBox topPanel = createTopPanel();
        root.setTop(topPanel);

        // Center: Chat area
        chatArea = new TextArea();
        chatArea.setEditable(false);
        chatArea.setWrapText(true);
        chatArea.setPrefHeight(350);
        root.setCenter(new ScrollPane(chatArea));

        // Bottom: Message input
        HBox bottomPanel = createBottomPanel();
        root.setBottom(bottomPanel);

        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setOnCloseRequest(e -> disconnect());
        primaryStage.show();

        chatArea.appendText("===== BAI 2: CHAT APPLICATION =====\n");
        chatArea.appendText("Ung dung Chat - Nhap ten nguoi dung va ket noi\n\n");
    }

    /**
     * Tao panel ket noi phia tren
     */
    private VBox createTopPanel() {
        VBox panel = new VBox(8);
        panel.setStyle("-fx-border-color: #cccccc; -fx-border-width: 1; -fx-padding: 10;");

        Label titleLabel = new Label("Ket noi Server");
        titleLabel.setStyle("-fx-font-size: 14; -fx-font-weight: bold;");

        HBox connectionBox = new HBox(8);
        connectionBox.setPadding(new Insets(5));

        Label hostLabel = new Label("Host:");
        TextField hostField = new TextField("localhost");
        hostField.setPrefWidth(100);

        Label portLabel = new Label("Port:");
        TextField portField = new TextField("8888");
        portField.setPrefWidth(80);

        Label nameLabel = new Label("Ten:");
        usernameField = new TextField();
        usernameField.setPromptText("Nhap ten cua ban");
        usernameField.setPrefWidth(150);

        connectButton = new Button("Ket noi");
        connectButton.setStyle("-fx-font-size: 12; -fx-padding: 5 15;");
        connectButton.setOnAction(e -> connect(hostField.getText(), portField.getText(), usernameField.getText()));

        statusLabel = new Label("Chua ket noi");
        statusLabel.setStyle("-fx-text-fill: #ff0000;");

        connectionBox.getChildren().addAll(
            hostLabel, hostField,
            portLabel, portField,
            nameLabel, usernameField,
            connectButton,
            statusLabel
        );

        panel.getChildren().addAll(titleLabel, connectionBox);
        return panel;
    }

    /**
     * Tao panel nhap tin nhan phia duoi
     */
    private HBox createBottomPanel() {
        HBox panel = new HBox(8);
        panel.setPadding(new Insets(10));
        panel.setStyle("-fx-border-color: #cccccc; -fx-border-width: 1;");

        messageField = new TextField();
        messageField.setPromptText("Nhap tin nhan...");
        messageField.setDisable(true);
        messageField.setOnKeyPressed(e -> {
            if (e.getCode().toString().equals("ENTER")) {
                sendMessage();
            }
        });

        sendButton = new Button("Gui");
        sendButton.setStyle("-fx-font-size: 12; -fx-padding: 5 15;");
        sendButton.setDisable(true);
        sendButton.setOnAction(e -> sendMessage());

        Button disconnectButton = new Button("Ngat");
        disconnectButton.setStyle("-fx-font-size: 12; -fx-padding: 5 15;");
        disconnectButton.setOnAction(e -> disconnect());

        HBox.setHgrow(messageField, javafx.scene.layout.Priority.ALWAYS);
        panel.getChildren().addAll(messageField, sendButton, disconnectButton);

        return panel;
    }

    /**
     * Ket noi den server
     */
    private void connect(String host, String portStr, String username) {
        if (username.trim().isEmpty()) {
            showAlert("Thong bao", "Vui long nhap ten nguoi dung!");
            return;
        }

        try {
            int port = Integer.parseInt(portStr);
            socket = new Socket(host, port);
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            // Gui ten nguoi dung
            out.println(username);

            isConnected = true;
            updateUI(true);
            chatArea.appendText("Da ket noi den server!\n");

            // Lang nghe tin nhan tu server
            new Thread(this::listenForMessages).start();

        } catch (NumberFormatException e) {
            showAlert("Loi", "Port phai la so: " + e.getMessage());
            isConnected = false;
        } catch (IOException e) {
            showAlert("Loi", "Khong the ket noi: " + e.getMessage());
            isConnected = false;
        }
    }

    /**
     * Lang nghe tin nhan tu server
     */
    private void listenForMessages() {
        try {
            String message;
            while ((message = in.readLine()) != null && isConnected) {
                String finalMessage = message;
                Platform.runLater(() -> chatArea.appendText(finalMessage + "\n"));
            }
        } catch (IOException e) {
            if (isConnected) {
                Platform.runLater(() -> chatArea.appendText("Mat ket noi voi server!\n"));
            }
        }
    }

    /**
     * Gui tin nhan
     */
    private void sendMessage() {
        if (!messageField.getText().isEmpty() && isConnected) {
            String message = messageField.getText();
            out.println(message);
            chatArea.appendText("Ban: " + message + "\n");
            messageField.clear();
        }
    }

    /**
     * Ngat ket noi
     */
    private void disconnect() {
        try {
            if (isConnected && out != null) {
                out.println("EXIT");
                isConnected = false;
            }
            if (socket != null && !socket.isClosed()) {
                socket.close();
            }
            updateUI(false);
            chatArea.appendText("Da ngat ket noi!\n");
        } catch (IOException e) {
            System.err.println("Loi dong ket noi: " + e.getMessage());
        }
    }

    /**
     * Cap nhat giao dien
     */
    private void updateUI(boolean connected) {
        connectButton.setDisable(connected);
        messageField.setDisable(!connected);
        sendButton.setDisable(!connected);
        
        if (connected) {
            statusLabel.setText("Da ket noi");
            statusLabel.setStyle("-fx-text-fill: #00aa00;");
        } else {
            statusLabel.setText("Chua ket noi");
            statusLabel.setStyle("-fx-text-fill: #ff0000;");
        }
    }

    /**
     * Hien thi alert
     */
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
