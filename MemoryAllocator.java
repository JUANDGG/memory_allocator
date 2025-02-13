import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/*
*
* Esto es un ejemplo muy simple de como mas o menos funciona la memoria
*
*/

public class GarbageCollectorTest {
    
    public static String getOptionMessage() {
        return "Ingrese una opción: ";
    }

    public static String getMainMenu() {
        return """
                1. Crear una variable numérica
                2. Crear una variable String
                3. Crear un objeto
                4. Salir
                """;
    }

    public static String getNumericMenu() {
        return """
                1. Ingresar un número
                2. Volver al menú principal
                """;
    }

    public static String getStringMenu() {
        return """
                1. Ingresar un String
                2. Volver al menú principal
                """;
    }

    public static String getObjectMenu() {
        return """
                1. Crear un objeto
                2. Volver al menú principal
                """;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        /*esto simularia 3 capas de la memoria aunque hay q recordar que existen varias
        y que la capa string pool iria dentro de la capa de la memoria heap
        ya que asi normalmente funciona la memoria 
        */
        int layers = 3;
        
        // simulación de la memoria dividida en capas stack y Heap
        List<List<?>> memory = new ArrayList<>(3);
        memory.add(new ArrayList<Number>()); // simula la memoria Stack
        memory.add(new ArrayList<Object>()); // simula la memoria Heap
        memory.add(new ArrayList<String>()); // simula el String Pool

        while (true) {
            System.out.println(getMainMenu());
            System.out.print(getOptionMessage());
            String mainOption = scanner.next();

            switch (mainOption) {
                case "1": 
                    System.out.println(getNumericMenu());
                    System.out.print(getOptionMessage());
                    String numericOption = scanner.next();

                    if ("1".equals(numericOption)) {
                        System.out.print("Ingrese un número: ");
                        try {
                            Number number = scanner.nextInt();
                            ((ArrayList<Number>) memory.get(0)).add(number);
                            System.out.println("Número guardado con éxito.");
                        } catch (Exception e) {
                            System.out.println("Entrada inválida. Intente nuevamente.");
                            scanner.nextLine(); // Limpia el buffer
                        }
                    }
                    break;
                
                case "2": 
                    System.out.println(getStringMenu());
                    System.out.print(getOptionMessage());
                    String stringOption = scanner.next();
                    
                    if ("1".equals(stringOption)) {
                        System.out.print("Ingrese un texto: ");
                        scanner.nextLine(); 
                        String text = scanner.nextLine();
                        ((ArrayList<String>) memory.get(2)).add(text);
                        System.out.println("Texto guardado con éxito.");
                    }
                    break;
                
                case "3":
                    System.out.println(getObjectMenu());
                    System.out.print(getOptionMessage());
                    String objectOption = scanner.next();

                    if ("1".equals(objectOption)) {
                        Object obj = new Object();
                        ((ArrayList<Object>) memory.get(1)).add(obj);
                        System.out.println("Objeto creado y almacenado.");
                    }
                    break;
                
                case "4": 
                    System.out.println("Saliendo del programa...");
                    scanner.close();
                    return;
                
                default:
                    System.out.println("Opción no válida. Intente nuevamente.");
            }
        }
    }
}
