package com.inforcap.sistemaclientes.utilidades;

import java.util.ArrayList;
import java.util.List;

import com.inforcap.sistemaclientes.modelo.IClasstoSave;

public class Utilidad {

    public static boolean validaArchivo(String filename) {

        return !(!filename.endsWith(".csv") || !filename.endsWith(".txt"));
    }

    public static void limpiezaPantalla() {
        try {
            final String os = System.getProperty("os.name");

            if (os.contains("Windows")) {
                // Método para limpiar consola en Windows
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                // Método para limpiar consola en Unix/Linux/MacOS
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (Exception e) {
            // Manejo de excepciones
            e.printStackTrace();
        }
    }

    public static void tiempoEspera(int seconds) {

        for (int i = seconds; i >= 0; i--) {
            try {
                System.out.print("\r" + i + " Segundo de espera ...");
                System.out.flush(); // Asegura que el texto se imprima inmediatamente
                Thread.sleep(1000); // Espera 1 segundo
            } catch (InterruptedException e) {
                System.err.print("Cuenta Interrumpida");
                Thread.currentThread().interrupt(); // Vuelve a establecer el estado de interrupción
                return;
            }
        }
    }

    public static List<IClasstoSave> getListToSave(List<?> lista) {
        List<IClasstoSave> lClientesToSave = new ArrayList<>();
        for (Object elem : lista) {
            lClientesToSave.add((IClasstoSave) elem);
        }
        return lClientesToSave;
    }
}
