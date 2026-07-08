import java.util.Objects;

public record SmsNotification(String phoneNumber, String message) implements Notification {
    private static boolean isValidPhone(String phone) {
        if (phone == null || phone.length() != 10)
            return false;
        for (int i = 0; i < phone.length(); i++) {
            if (!Character.isDigit(phone.charAt(i)))
                return false;
        }
        return true;
    }

    public SmsNotification {
        Objects.requireNonNull(phoneNumber, "phoneNumber no puede ser nulo");
        Objects.requireNonNull(message, "message no puede ser nulo");

        if (!isValidPhone(phoneNumber)) {
            throw new IllegalArgumentException(
                    "Número telefónico inválido (debe tener exactamente 10 dígitos): " + phoneNumber);
        }
    }
}
