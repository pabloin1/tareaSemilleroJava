package clases;
import java.util.Objects;

public record PushNotification(String token, String message) implements Notification {
    public PushNotification {
        Objects.requireNonNull(token, "token no puede ser nulo");
        Objects.requireNonNull(message, "message no puede ser nulo");

        if (token.isBlank()) {
            throw new IllegalArgumentException("Token inválido: no puede estar vacío");
        }
    }
}
