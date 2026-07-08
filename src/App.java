import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("¿Cuántas notificaciones desea ingresar? ");
        int count;
        while (true) {
            String line = sc.nextLine().trim();
            try {
                count = Integer.parseInt(line);
                if (count < 0) {
                    System.out.print("Ingrese un número no negativo: ");
                    continue;
                }
                break;
            } catch (NumberFormatException ex) {
                System.out.print("Entrada inválida. Ingrese un número entero: ");
            }
        }

        Notification[] notifications = new Notification[count];

        for (int i = 0; i < count;) {
            System.out.println();
            System.out.println("Notificación " + (i + 1) + " de " + count + ":");
            System.out.print("Tipo (E=Email, S=SMS, P=Push): ");
            String tipo = sc.nextLine().trim().toUpperCase();

            try {
                switch (tipo) {
                    case "E", "EMAIL" -> {
                        System.out.print("Correo destinatario: ");
                        String email = sc.nextLine().trim();
                        System.out.print("Asunto: ");
                        String subject = sc.nextLine().trim();
                        System.out.print("Contenido: ");
                        String body = sc.nextLine().trim();

                        notifications[i] = new EmailNotification(email, subject, body);
                        i++;
                    }
                    case "S", "SMS" -> {
                        System.out.print("Número telefónico (10 dígitos): ");
                        String phone = sc.nextLine().trim();
                        System.out.print("Mensaje: ");
                        String msg = sc.nextLine().trim();

                        notifications[i] = new SmsNotification(phone, msg);
                        i++;
                    }
                    case "P", "PUSH" -> {
                        System.out.print("Token del dispositivo: ");
                        String token = sc.nextLine().trim();
                        System.out.print("Mensaje: ");
                        String pmsg = sc.nextLine().trim();

                        notifications[i] = new PushNotification(token, pmsg);
                        i++;
                    }
                    default -> System.out.println("Tipo no reconocido. Use E, S o P.");
                }
            } catch (IllegalArgumentException | NullPointerException ex) {
                System.out.println("Error al crear la notificación: " + ex.getMessage());
                System.out.println("Intente ingresar la notificación nuevamente.");
            }
        }

        sc.close();

        int emailCount = 0;
        int smsCount = 0;
        int pushCount = 0;

        for (Notification n : notifications) {
            switch (n) {
                case EmailNotification e -> emailCount++;
                case SmsNotification s -> smsCount++;
                case PushNotification p -> pushCount++;
            }
        }

        int total = emailCount + smsCount + pushCount;

        System.out.println();
        System.out.println("========= RESUMEN =========");
        System.out.println();
        System.out.println("Correos enviados:  " + emailCount);
        System.out.println("SMS enviados: " + smsCount);
        System.out.println("Push enviados: " + pushCount);
        System.out.println("Total: " + total);
    }
}
