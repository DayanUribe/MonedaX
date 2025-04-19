import java.util.Scanner;

public class ConversorMoneda {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ClienteApi clienteApi = new ClienteApi();

        while (true) {
            System.out.println("🌍 Conversor de Monedas");
            System.out.println("1. MXN a USD");
            System.out.println("2. MXN a EUR");
            System.out.println("3. USD a MXN");
            System.out.println("4. Salir");
            System.out.print("Elige una opción: ");
            int opcion = scanner.nextInt();

            if (opcion == 4) {
                System.out.println("👋 ¡Gracias por usar el conversor!");
                break;
            }

            System.out.print("Ingresa la cantidad a convertir: ");
            double cantidad = scanner.nextDouble();
            String de = "", a = "";

            switch (opcion) {
                case 1 -> { de = "MXN"; a = "USD"; }
                case 2 -> { de = "MXN"; a = "EUR"; }
                case 3 -> { de = "USD"; a = "MXN"; }
                default -> System.out.println("❌ Opción no válida");
            }

            ResultadoCambio resultado = clienteApi.convertirMoneda(de, a, cantidad);
            if (resultado != null) {
                System.out.printf("💰 %.2f %s = %.2f %s%n", cantidad, de, resultado.resultado(), a);
            }
        }
    }
}