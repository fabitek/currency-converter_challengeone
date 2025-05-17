package com.aluracursos.convertidormoneda.main;

import com.aluracursos.convertidormoneda.calculations.ClienteHttp;
import com.aluracursos.convertidormoneda.models.Divisa;
import com.aluracursos.convertidormoneda.models.DivisasManager;
import com.aluracursos.convertidormoneda.calculations.Conversion; // Importar la clase Conversion

import java.text.DecimalFormat;
import java.time.LocalDateTime; // Para el timestamp
import java.util.ArrayList; // Para la lista del historial
import java.util.Collections; // Para invertir la lista si se desea
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Principal {

    // Mover los formateadores y el historial para que sean accesibles por métodos auxiliares si es necesario,
    // o mantenerlos dentro de main si toda la lógica permanece allí.
    // Para este caso, los mantendremos en main y pasaremos como parámetros si creamos métodos auxiliares.
    // static List<Conversion> historialConversiones = new ArrayList<>(); // Si fueran métodos estáticos auxiliares

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Divisa> divisas = DivisasManager.getDivisas();
        List<Conversion> historialConversiones = new ArrayList<>(); // Aquí se guardará el historial

        DecimalFormat dfMonto = new DecimalFormat("###,##0.##"); // Un solo formateador para montos, ajusta el patrón como necesites
        // "###,##0" para enteros con comas
        // "###,##0.00" para dos decimales con comas
        DecimalFormat dfMontoOriginal = new DecimalFormat("###,##0"); // Para el monto original
        DecimalFormat dfResultadoConversion = new DecimalFormat("###,##0.00"); // Para el resultado de la conversión

        System.out.println("*****************************************************************************************");
        System.out.println("-------------------💸Bienvenido/a al Convertidor de Moneda💸---------------------------");

        cicloPrincipal:
        while (true) {
            System.out.println("*****************************************************************************************");
            System.out.println("\n********************************** MENÚ PRINCIPAL *************************************");
            System.out.println("1) Realizar conversión de moneda");
            System.out.println("2) Ver historial de conversiones");
            System.out.println("3) Salir");
            System.out.print("Seleccione una opción: ");

            int opcionMenu;
            try {
                opcionMenu = scanner.nextInt();
                scanner.nextLine(); // Consumir salto de línea
            } catch (InputMismatchException e) {
                System.out.println("❌ Error: Debes ingresar un número. Intenta de nuevo.");
                scanner.nextLine(); // Limpiar buffer
                continue;
            }

            switch (opcionMenu) {
                case 1:
                    // --- Lógica de Conversión ---
                    System.out.println("\n**** Moneda Base: Selecciona el número correspondiente a la moneda de ORIGEN ****");
                    for (int i = 0; i < divisas.size(); i++) {
                        System.out.println((i + 1) + ") " + divisas.get(i).toString());
                    }
                    System.out.print("Elije la moneda de origen (o 0 para volver al menú principal): ");
                    int opcionBaseNum;
                    try {
                        opcionBaseNum = scanner.nextInt();
                        scanner.nextLine();
                    } catch (InputMismatchException e) {
                        System.out.println("❌ Error: Debes ingresar un número. Intenta de nuevo.");
                        scanner.nextLine();
                        continue cicloPrincipal; // Volver al menú principal
                    }

                    if (opcionBaseNum == 0) continue cicloPrincipal; // Volver al menú principal
                    if (opcionBaseNum < 1 || opcionBaseNum > divisas.size()) {
                        System.out.println("❌ Opción no válida para moneda de origen.");
                        continue cicloPrincipal;
                    }
                    Divisa from = divisas.get(opcionBaseNum - 1);
                    System.out.println("➡️ Moneda de origen seleccionada: " + from);

                    System.out.println("\n**** Moneda Destino: Selecciona el número correspondiente a la moneda hacia la cual desea convertir ****");
                    for (int i = 0; i < divisas.size(); i++) {
                        System.out.println((i + 1) + ") " + divisas.get(i).toString() +
                                (divisas.get(i).getBase_code().equals(from.getBase_code()) ? " (misma que origen)" : ""));
                    }
                    System.out.print("Elije la moneda destino (o 0 para volver al menú principal): ");
                    int opcionTargetNum;
                    try {
                        opcionTargetNum = scanner.nextInt();
                        scanner.nextLine();
                    } catch (InputMismatchException e) {
                        System.out.println("❌ Error: Debes ingresar un número. Intenta de nuevo.");
                        scanner.nextLine();
                        continue cicloPrincipal;
                    }

                    if (opcionTargetNum == 0) continue cicloPrincipal;
                    if (opcionTargetNum < 1 || opcionTargetNum > divisas.size()) {
                        System.out.println("❌ Opción no válida para moneda de destino.");
                        continue cicloPrincipal;
                    }
                    Divisa to = divisas.get(opcionTargetNum - 1);
                    System.out.println("🎯 Moneda de destino seleccionada: " + to);

                    System.out.print("Ingresa el monto a convertir (ej: " + dfMontoOriginal.format(1000000) + "): ");
                    double amount;
                    try {
                        String amountStr = scanner.nextLine();
                        amount = Double.parseDouble(amountStr.replace(',', '.'));
                    } catch (NumberFormatException e) {
                        System.out.println("❌ Error: Monto no válido. Debes ingresar un número.");
                        continue cicloPrincipal;
                    }

                    if (amount < 0) {
                        System.out.println("❌ Error: El monto no puede ser negativo.");
                        continue cicloPrincipal;
                    }

                    String formattedAmountOriginal = dfMontoOriginal.format(amount);
                    System.out.println("\n🔄 Procesando conversión de " + formattedAmountOriginal + " " + from.getBase_code() + " a " + to.getBase_code() + "...");
                    String resultadoDesdeApi = ClienteHttp.realizaSolicitud(from.getBase_code(), to.getBase_code(), amount);

                    try {
                        double valorResultadoNumerico = Double.parseDouble(resultadoDesdeApi);
                        String resultadoFinalParaMostrar = dfResultadoConversion.format(valorResultadoNumerico);

                        System.out.println("\n✅ RESULTADO DE LA CONVERSIÓN:");
                        System.out.println(formattedAmountOriginal + " " + from.getBase_code() + " " + from.getCountryFlags() +
                                " es equivalente a: " + resultadoFinalParaMostrar + " " + to.getBase_code() + " " + to.getCountryFlags());

                        // --- Guardar en el historial ---
                        LocalDateTime timestampActual = LocalDateTime.now();
                        Conversion nuevaConversion = new Conversion(
                                from.getBase_code(),
                                to.getBase_code(),
                                amount, // Guardamos el double original
                                valorResultadoNumerico, // Guardamos el double resultado
                                timestampActual
                        );
                        historialConversiones.add(nuevaConversion);
                        System.out.println("ⓘ Conversión guardada en el historial.");

                    } catch (NumberFormatException e) {
                        System.out.println("❌ " + resultadoDesdeApi); // Mostrar error de la API
                    }
                    break; // Salir del switch y volver al bucle del menú principal

                case 2:
                    // --- Lógica para Mostrar Historial ---
                    System.out.println("\n****************************** HISTORIAL DE CONVERSIONES *****************************");
                    if (historialConversiones.isEmpty()) {
                        System.out.println("El historial de conversiones está vacío.");
                    } else {
                        // Opcional: Mostrar en orden inverso (más recientes primero)
                        // List<Conversion> historialInverso = new ArrayList<>(historialConversiones);
                        // Collections.reverse(historialInverso);
                        // for (Conversion conv : historialInverso) { ... }

                        System.out.println("------------------------------------------------------------------------------------");
                        System.out.printf("%-20s | %-15s | %-5s | %-15s | %-5s%n",
                                "Fecha y Hora", "Monto Origen", "De", "Monto Destino", "A");
                        System.out.println("------------------------------------------------------------------------------------");

                        for (Conversion conv : historialConversiones) {
                            String montoOrigenFormateado = dfMontoOriginal.format(conv.getAmount());
                            String montoDestinoFormateado = dfResultadoConversion.format(conv.getResult());
                            System.out.printf("%-20s | %15s | %-5s | %15s | %-5s%n",
                                    conv.getFormattedTimestamp(),
                                    montoOrigenFormateado,
                                    conv.getFromCode(),
                                    montoDestinoFormateado,
                                    conv.getToCode()
                            );
                        }
                        System.out.println("------------------------------------------------------------------------------------");
                    }
                    System.out.println("Presione Enter para volver al menú principal...");
                    scanner.nextLine(); // Esperar a que el usuario presione Enter
                    break; // Salir del switch y volver al bucle del menú principal

                case 3:
                    System.out.println("👋 Saliendo del programa... ¡Hasta luego!");
                    break cicloPrincipal; // Romper el bucle etiquetado para salir

                default:
                    System.out.println("❌ Opción de menú no válida. Por favor, intente de nuevo.");
                    break; // Salir del switch y volver al bucle del menú principal
            }
        }
        scanner.close();
        System.out.println("\n********************* Aplicación Convertidor de Moneda Finalizada *********************");
    }
}