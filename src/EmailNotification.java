import java.util.Objects;

public record EmailNotification(String email, String subject, String body) implements Notification {
    private static boolean isValidEmail(String email) {
        if (email == null)
            return false;
        if (email.contains(" "))
            return false;
        int at = email.indexOf('@');
        int dot = email.lastIndexOf('.');
        return at > 0 && dot > at + 1 && dot < email.length() - 1;
    }

    public EmailNotification {
        Objects.requireNonNull(email, "email no puede ser nulo");
        Objects.requireNonNull(subject, "subject no puede ser nulo");
        Objects.requireNonNull(body, "body no puede ser nulo");

        if (!isValidEmail(email)) {
            throw new IllegalArgumentException("Dirección de correo inválida: " + email);
        }
    }
}
