// Proxy Pattern - Spam Filtering
interface EmailServices {
    void sendEmail(String message);
}

class RealEmailService implements EmailServices {
    @Override
    public void sendEmail(String message) {
        System.out.println("Sending email: " + message);
    }
}

class SpamFilterProxy implements EmailServices {
    private RealEmailService realEmailService;

    public SpamFilterProxy() {
        this.realEmailService = new RealEmailService();
    }

    @Override
    public void sendEmail(String message) {
        if (isSpam(message)) {
            System.out.println("Email blocked due to spam content.");
        } else {
            realEmailService.sendEmail(message);
        }
    }

    private boolean isSpam(String message) {
        return message.toLowerCase().contains("buy now") || message.toLowerCase().contains("free");
    }
}

// Decorator Pattern - Adding Custom Email Signatures
abstract class EmailDecorator implements EmailServices {
    protected EmailServices decoratedEmailService;

    public EmailDecorator(EmailServices emailService) {
        this.decoratedEmailService = emailService;
    }

    @Override
    public void sendEmail(String message) {
        decoratedEmailService.sendEmail(message);
    }
}

class SignatureDecorator extends EmailDecorator {
    private String signature;

    public SignatureDecorator(EmailServices emailService, String signature) {
        super(emailService);
        this.signature = signature;
    }

    @Override
    public void sendEmail(String message) {
        super.sendEmail(message + "\n\n" + signature);
    }
}

// Client Code
public class EmailClientApp {
    public static void main(String[] args) {
        // Proxy Pattern - Spam Filtering
        EmailServices emailService = new SpamFilterProxy();

        System.out.println("Attempting to send regular email:");
        emailService.sendEmail("Hello, this is a regular email.");

        System.out.println("\nAttempting to send spam email:");
        emailService.sendEmail("Buy now and get free products!");

        // Decorator Pattern - Adding Custom Email Signatures
        System.out.println("\nSending email with custom signature:");
        EmailServices signedEmailService = new SignatureDecorator(emailService, "Best regards,\nJohn Doe");
        signedEmailService.sendEmail("Hello, this is an important update.");
    }
}

/**
Explanation:
1. `EmailService` is the subject interface for sending emails.
2. `RealEmailService` is the real subject that handles email sending.
3. `SpamFilterProxy` is the proxy class that adds spam filtering before forwarding the email to `RealEmailService`.
4. `EmailDecorator` is the abstract base class for adding features to email sending, such as custom signatures.
5. `SignatureDecorator` is a concrete decorator that adds a custom signature to the email message.
6. `EmailClientApp` is the client code that demonstrates using both the Proxy pattern for spam filtering and the Decorator pattern for adding custom signatures to emails.
*/
