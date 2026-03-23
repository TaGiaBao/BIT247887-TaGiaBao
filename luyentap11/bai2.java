// Bài 2: Setter Injection

// 1. Interface MessageService
interface MessageService {
    void sendMessage(String message);
}

// 2. Lớp EmailService implement MessageService
class EmailService implements MessageService {
    @Override
    public void sendMessage(String message) {
        System.out.println("Gui Email: " + message);
    }
}

// 3. Lớp SMSService implement MessageService
class SMSService implements MessageService {
    @Override
    public void sendMessage(String message) {
        System.out.println("Gui SMS: " + message);
    }
}

class Notification {
    private MessageService messageService;

    // Setter Injection - inject dependency thông qua setter
    public void setMessageService(MessageService messageService) {
        this.messageService = messageService;
    }

    // Method gửi thông báo
    public void notify(String message) {
        if (messageService == null) {
            System.out.println("Loi!");
            return;
        }
        messageService.sendMessage(message);
    }
}

// 5. Chương trình demo
public class bai2 {
    public static void main(String[] args) {
        System.out.println("========== DEMO SETTER INJECTION ==========\n");

        // Tạo đối tượng Notification
        Notification notification = new Notification();

        // Tạo instance của EmailService
        EmailService emailService = new EmailService();

        // Inject EmailService thông qua setter
        System.out.println("--- Su dung EmailService ---");
        notification.setMessageService(emailService);
        notification.notify("welcome!");
        notification.notify("Thong Bao");

        // Tạo instance của SMSService
        SMSService smsService = new SMSService();

        // Inject SMSService thông qua setter (thay thế EmailService)
        System.out.println("\n--- Chuyen qua SMSService ---");
        notification.setMessageService(smsService);
        notification.notify("Ban co tin nhan moi!");
        notification.notify("Ma xac thuc la: 12344");

        // Quay lại EmailService
        System.out.println("\n--- Quay lai EmailService ---");
        notification.setMessageService(emailService);
        notification.notify("Gui qua thong bao Email");

        System.out.println("\n========== End ==========");
    }
}
