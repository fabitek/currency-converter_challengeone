package com.aluracursos.convertidormoneda.main;

import com.aluracursos.convertidormoneda.calculations.ClienteHttp;
import com.aluracursos.convertidormoneda.calculations.ExchangeRate;
import com.aluracursos.convertidormoneda.models.Divisa;
import com.aluracursos.convertidormoneda.models.DivisasManager;

import java.util.List;
import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Divisa> divisas = DivisasManager.getDivisas();

        int opcion = 0;

        System.out.println("*****************************************************************************************");
        System.out.println("-------------------💸Bienvenido/a al Convertidor de Moneda💸---------------------------");

        while (opcion != divisas.size() + 1) {
            System.out.println("\n**** Selecciona el número correspondiente a la moneda ****");
            for (int i = 0; i < divisas.size(); i++) {
                System.out.println((i + 1) + ") " + divisas.get(i));
            }
            System.out.println((divisas.size() + 1) + ") Salir");

            System.out.print("Elije la opcion de la moneda a convertir: ");
            opcion = scanner.nextInt();


            if (opcion < 1 || opcion > divisas.size() + 1) {
                System.out.println("Opción no válida. Por favor, intente de nuevo.");
                continue;
            }

            if (opcion == divisas.size() + 1) {
                System.out.println("Saliendo del programa... ¡Hasta luego!");
                break;
            }

            Divisa from = divisas.get(opcion - 1);

            System.out.println("Seleccionaste: " + from);
            System.out.println("\n**** Ahora seleccione el número correspondiente a la moneda hacia la cual desea convertir ****");
            for (int i = 0; i < divisas.size(); i++) {
                System.out.println((i + 1) + ") " + divisas.get(i));
            }

            System.out.print("Elija la opción de la moneda hacia la cual deseas convertir: ");
            int toIndex = scanner.nextInt() - 1;

            if (toIndex < 0 || toIndex >= divisas.size()) {
                System.out.println("Opción no válida. Por favor, intente de nuevo.");
                continue;
            }

            Divisa to = divisas.get(toIndex);
            System.out.println("Seleccionaste: " + to);

            System.out.print("Ingresa el monto a convertir: ");
            double amount = scanner.nextDouble();

            String resultado = ClienteHttp.realizaSolicitud(from.getBase_code(), to.getBase_code(), amount);
//            String formattedAmount = String.format("%.0f", amount);
            System.out.println(amount + " " + from.getBase_code() + from.getPais() + " es equivalente a: " +
                    resultado + " " + to.getBase_code() + to.getPais());
        }
        scanner.close();
    }

}

//        while (opcion != 7){
//            System.out.println(menu);
//            opcion = lectura.nextInt();
//
//            switch (opcion){
//                case 1:
//                    System.out.println("🇨🇴COP" + " son " + "🇺🇸USD");
//                    break;
//                case 2:
//                    System.out.println("🇺🇸USD" + " son " + "🇨🇴COP");
//                    break;
//                case 7:
//                    System.out.println("Saliendo del programa... ¡Hasta luego!");
//                    break;
//                default:
//                    System.out.println("Opción invalida. Intente nuevamente.");
//            }
//        }

