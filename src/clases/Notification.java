package clases;

public sealed interface Notification permits EmailNotification, SmsNotification, PushNotification {
}
