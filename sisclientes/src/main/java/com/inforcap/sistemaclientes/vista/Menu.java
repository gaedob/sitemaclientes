package com.inforcap.sistemaclientes.vista;

import java.util.List;
import java.util.Scanner;

import com.inforcap.sistemaclientes.modelo.Cliente;
import com.inforcap.sistemaclientes.modelo.IClasstoSave;
import com.inforcap.sistemaclientes.servicio.ArchivoServicio;
import com.inforcap.sistemaclientes.servicio.ClienteServicio;
import com.inforcap.sistemaclientes.servicio.ExportadorCsv;
import com.inforcap.sistemaclientes.servicio.ExportadorTxt;
import com.inforcap.sistemaclientes.servicio.IMenu;
import com.inforcap.sistemaclientes.utilidades.Utilidad;

public class Menu implements IMenu {

    private final ClienteServicio clienteServicio;
    private ArchivoServicio archivoServicio;
    private ExportadorCsv exportarCsv;
    private ExportadorTxt exportarTxt;
    private static Scanner scanner = new Scanner(System.in);

    private static final String fileName = "Clientes";
    private static final String fileName1 = "DBClientes.csv";

    private static final String reset = "\033[0m";  // Resetear color
    private static final String rojo = "\033[31m";
    private static final String verde = "\033[32m";
    private static final String amarillo = "\033[33m";
    private static final String azul = "\033[34m";
    private static final String magenta = "\033[35m";
    private static final String cian = "\033[36m";
    private static final String negrita = "\033[1m";

    public Menu() {
        clienteServicio = new ClienteServicio();
        exportarCsv = new ExportadorCsv(new Cliente());
        exportarTxt = new ExportadorTxt();

    }

    private String menuColorAzul(String tituloMenu) {
        String newTitulo = azul + tituloMenu + reset;
        return newTitulo;
    }

    private String menuColorVerde(String tituloMenu) {
        String newTitulo = verde + tituloMenu + reset;
        return newTitulo;
    }

    private String menuColorRojo(String tituloMenu) {
        String newTitulo = rojo + tituloMenu + reset;
        return newTitulo;
    }

    private String menuColorAmarillo(String tituloMenu) {
        String newTitulo = amarillo + tituloMenu + reset;
        return newTitulo;
    }

    private void principal() {

        imprimirTextLn(menuColorAzul(negrita + " -- Menu Principal -- "));
        imprimirTextLn(menuColorAzul("[1] Listar Clientes"));
        imprimirTextLn(menuColorAzul("[2] Agregar Cliente"));
        imprimirTextLn(menuColorAzul("[3] Editar Cliente"));
        imprimirTextLn(menuColorAzul("[4] Cargar Datos"));
        imprimirTextLn(menuColorAzul("[5] Exportar Datos"));
        imprimirTextLn(menuColorAzul("[6] Salir"));
        imprimirText(menuColorVerde(negrita + "Ingrese una opción:"));

    }

    private void clearScreen() {
        imprimirTextLn("");
        Utilidad.tiempoEspera(2);
        Utilidad.limpiezaPantalla();
        principal();
    }

    @Override
    public void listarClientes() {

        imprimirTextLn(menuColorAzul(negrita + "------------Listar Clientes------------"));
        clienteServicio.retornolistarClientes();
        clearScreen();
    }

    @Override
    public void agregarCliente() {
        imprimirTextLn(menuColorAzul(negrita + "------------Agregar Cliente------------"));

        if (scanner.hasNextLine()) {
            // Consume el carácter de nueva línea si existe
            scanner.nextLine();
        }

        imprimirText(menuColorVerde(negrita + "Ingresa RUN del Cliente: "));
        String rutCliente = scanner.nextLine();

        imprimirText(menuColorVerde(negrita + "Ingresa Nombre del Cliente: "));
        String nombreCliente = scanner.nextLine();

        imprimirText(menuColorVerde(negrita + "Ingresa Apellido del Cliente: "));
        String apellidoCliente = scanner.nextLine();

        imprimirText(menuColorVerde(negrita + "Ingresa años como Cliente: "));
        String aniosCliente = scanner.nextLine();

        clienteServicio.agregarCliente(rutCliente, nombreCliente, apellidoCliente, aniosCliente, null);
        if (clienteServicio.getMsgError() != null){
            imprimirTextLn(menuColorRojo(negrita + clienteServicio.getMsgError()));
        }


        clearScreen();
    }

    private void getTituloEditar() {
        imprimirTextLn("");
        imprimirTextLn(menuColorAzul(negrita + "-------------Editar Cliente------------- "));
        imprimirTextLn("Seleccione qué desea hacer:");
        imprimirTextLn("1.-Cambiar el estado del Cliente");
        imprimirTextLn("2.-Editar los datos ingresados del Cliente");
        imprimirTextLn("3.-Salir de Edición ...");
        imprimirText(menuColorVerde(negrita + "Ingrese opción: "));

    }

    private void opcionEstadoDeCliente() {
        Cliente clienteOld = (Cliente) clienteServicio.getClienteAntiguo();
        imprimirTextLn("");
        imprimirTextLn(menuColorAzul(negrita + "-----Actualizando estado del Cliente----"));
        imprimirTextLn("El estado actual es: " + clienteOld.getNombreCategoria().toString());
        imprimirTextLn("1.- Si desea cambiar el estado del Cliente a Inactivo");
        imprimirTextLn("2.- Si desea mantener el estado del cliente Activo");
        imprimirText(menuColorVerde(negrita + "Ingrese opcion: "));

        int statusEstado;
        statusEstado = scanner.nextInt();

        String categoria = statusEstado == 1 ? "INACTIVO" : "ACTIVO";

        clienteServicio.editarCliente(
                clienteOld.getRunCliente(),
                clienteOld.getNombreCliente(),
                clienteOld.getApellidoCliente(),
                clienteOld.getAniosCliente(),
                categoria
        );
    }

    private void opcionDatosDeCliente() {
        Cliente clienteOld = (Cliente) clienteServicio.getClienteAntiguo();
        imprimirTextLn("");
        imprimirTextLn(menuColorAzul(negrita + "-----Actualizando Datos del Cliente----"));
        imprimirTextLn("1.- El RUN del Cliente es: " + clienteOld.getRunCliente());
        imprimirTextLn("2.- El Nombre del Cliente es: " + clienteOld.getNombreCliente());
        imprimirTextLn("3.- El Apellido del Cliente es: " + clienteOld.getApellidoCliente());
        imprimirTextLn("4.- Los años como Cliente son: " + clienteOld.getAniosCliente());
        imprimirText(menuColorVerde(negrita + "Ingrese opcion a editar de los datos del cliente: "));
        //int statusEstado = scanner.nextInt();

        int opcionEditar = 0;

        int[] option = {1, 2, 3, 4};
        opcionEditar = getValueOpcion(option);
        if (scanner.hasNextLine()) {
            // Consume el carácter de nueva línea si existe
            scanner.nextLine();
        }

        switch (opcionEditar) {
            case 1 -> {
                imprimirTextLn("----------------------------------------");
                imprimirText(menuColorAzul(negrita + "1 .-Ingrese nuevo RUN del Cliente: "));
                String rut = scanner.nextLine();

                clienteServicio.editarCliente(
                        rut,
                        clienteOld.getNombreCliente(),
                        clienteOld.getApellidoCliente(),
                        clienteOld.getAniosCliente(),
                        clienteOld.getNombreCategoria().toString()
                );

                break;
            }

            case 2 -> {
                imprimirTextLn("----------------------------------------");
                imprimirText(menuColorAzul(negrita + "2.- Ingrese el Nombre del Cliente: "));
                String nombre = scanner.nextLine();

                clienteServicio.editarCliente(
                        clienteOld.getRunCliente(),
                        nombre,
                        clienteOld.getApellidoCliente(),
                        clienteOld.getAniosCliente(),
                        clienteOld.getNombreCategoria().toString()
                );

                break;
            }

            case 3 -> {
                imprimirTextLn("----------------------------------------");
                imprimirText(menuColorAzul(negrita + "2.- Ingrese el Apellido del Cliente: "));
                String apellido = scanner.nextLine();
                clienteServicio.editarCliente(
                        clienteOld.getRunCliente(),
                        clienteOld.getNombreCliente(),
                        apellido,
                        clienteOld.getAniosCliente(),
                        clienteOld.getNombreCategoria().toString()
                );

                break;
            }

            case 4 -> {
                imprimirTextLn("----------------------------------------");
                imprimirText(menuColorAzul(negrita + "2.- Ingrese los años del Cliente: "));
                String anios = scanner.nextLine();

                clienteServicio.editarCliente(
                        clienteOld.getRunCliente(),
                        clienteOld.getNombreCliente(),
                        clienteOld.getApellidoCliente(),
                        anios,
                        clienteOld.getNombreCategoria().toString()
                );

                break;
            }

        }

    }

    private String getTextOpciones(int[] opciones){
        StringBuilder bld = new StringBuilder();
        for (int i = 0; i < opciones.length; ++i)
            bld.append(opciones[i]).append(" - ");

        return bld.substring(0, bld.length() - 3);
    }

    private int getValueOpcion(int[] opciones) {

        String opcionString = getTextOpciones(opciones);
        int opcion;
        while (true) {
            // Verificar si la entrada es un entero
            boolean flag = false;
            
            if (scanner.hasNextInt()) {
                opcion = scanner.nextInt();
                // Validar si el entero está dentro del rango permitido
                for (int elem : opciones) {
                    if (opcion == elem) {
                        flag = true;
                        break;
                    }

                }
                if (flag) {
                    break;  // Salir del bucle si la opción es válida
                } else {
                    imprimirTextLn(menuColorRojo(negrita + "Error: El número debe estar en el Rango [" + opcionString + "]"));
                    imprimirText(menuColorVerde(negrita + "Ingrese opcion: "));
                }
            } else {
                imprimirTextLn(menuColorRojo(negrita + "Error: Debes introducir un número..."));
                imprimirText(menuColorVerde(negrita + "Ingrese opcion: "));

                scanner.next(); // Descartar la entrada no válida
            }
        }
        return opcion;
    }

    private boolean buscarClienteRut() {
        imprimirTextLn(" ");
        imprimirTextLn("----------------------------------------");
        if (scanner.hasNextLine()) {
            // Consume el carácter de nueva línea si existe
            scanner.nextLine();
        }
        imprimirText(menuColorVerde(negrita + " Ingrese RUN del Cliente a editar: "));
        String rut = scanner.nextLine();

        return clienteServicio.buscarProductoCodigo(rut);
    }

    @Override
    public void editarCliente() {

        try {
            getTituloEditar();
            int[] option = {1, 2, 3};
            int opcionEditar = getValueOpcion(option);
            switch (opcionEditar) {
                // Caso Actualizar Estado del Cliente
                case 1 -> {
                    if (buscarClienteRut()) {
                        Utilidad.limpiezaPantalla();
                        opcionEstadoDeCliente();
                        imprimirTextLn("----------------------------------------");
                        imprimirTextLn(menuColorVerde("Datos cambiados con éxito"));
                    } else {
                        imprimirTextLn("Cliente No Encontado ...");
                    }
                    break;
                }
                // Caso Actualizar Datos del Cliente
                case 2 -> {
                    if (buscarClienteRut()) {
                        Utilidad.limpiezaPantalla();
                        opcionDatosDeCliente();
                        imprimirTextLn("----------------------------------------");
                        imprimirTextLn(menuColorVerde("Datos cambiados con éxito"));
                    } else {
                        imprimirTextLn("Cliente No Encontado ...");
                    }

                    break;
                }
                case 3 -> {
                    imprimirTextLn(menuColorAmarillo(negrita + "Salide de Edición..."));

                    break;
                }

            }

        } catch (Exception e) {
            imprimirText(menuColorRojo(negrita + "Opción debe ser 1, 2 o 3 !!"));
        }
        clearScreen();
    }

    private void imprimirTextLn(String texto) {
        System.out.println(texto);
    }

    private void imprimirText(String texto) {
        System.out.print(texto);
    }

    @Override
    public void importarDatos() {

        imprimirTextLn("");
        imprimirTextLn(menuColorAzul(negrita + "-------------Importar Datos------------- "));

        archivoServicio = new ArchivoServicio(clienteServicio);

        archivoServicio.cargarDatos(fileName1);

        imprimirTextLn(menuColorVerde(negrita + "Datos cargados correctamente en la lista..."));

        clearScreen();
    }

    @Override
    public void exportarDatos() {
        imprimirTextLn("");
        imprimirTextLn(menuColorAzul(negrita + "-------------Exportarr Datos------------- "));

        imprimirTextLn("Seleccione el formato a exportar:");
        imprimirTextLn("1.- Formato csv");
        imprimirTextLn("2.- Formato txt");

        if (scanner.hasNextLine()) {
            // Consume el carácter de nueva línea si existe
            scanner.nextLine();
        }
        imprimirText(menuColorVerde(negrita + "Ingrese una opción para exportar: "));

        int[] option = {1, 2};
        int opcion = getValueOpcion(option);

        switch (opcion) {
            case 1:
                archivoServicio = new ArchivoServicio(exportarCsv, clienteServicio);

                List<IClasstoSave> listaClientesToCsv = Utilidad.getListToSave(clienteServicio.getListaClientes());

                archivoServicio.exportar(fileName + ".csv", listaClientesToCsv);
                if (exportarCsv.getStatusExported()) {
                    imprimirTextLn(menuColorVerde(negrita + "Expotado OK"));
                } else {
                    imprimirTextLn(menuColorRojo(negrita + exportarCsv.getStatusMessage()));
                }
                break;
            case 2:
                archivoServicio = new ArchivoServicio(exportarTxt, clienteServicio);
                List<IClasstoSave> listaClientestoTxt = Utilidad.getListToSave(clienteServicio.getListaClientes());
                archivoServicio.exportar(fileName + ".txt", listaClientestoTxt);
                if (exportarCsv.getStatusExported()) {
                    imprimirTextLn(menuColorVerde(negrita + "Expotado OK"));
                } else {
                    imprimirTextLn(menuColorRojo(negrita + exportarCsv.getStatusMessage()));
                }
                break;
            default:
                break;
        }

        clearScreen();
    }

    @Override
    public void terminarPrograma() {
        imprimirTextLn("");
        imprimirTextLn(menuColorAzul(negrita + "-------------Terminar Programa------------- "));
        clearScreen();
    }

    public void iniciarMenu() {
        principal();
        try {
            int[] option = {1, 2, 3, 4, 5, 6};
            int opcion = getValueOpcion(option);
            while (opcion != 6) {
                Utilidad.limpiezaPantalla();
                switch (opcion) {
                    case 1:
                        listarClientes();
                        break;
                    case 2:
                        agregarCliente();
                        break;
                    case 3:
                        editarCliente();
                        break;
                    case 4:
                        importarDatos();
                        break;
                    case 5:
                        exportarDatos();
                        break;
                    default:
                        principal();
                        break;

                }
                opcion = scanner.nextInt();
            }
            if (opcion == 6) {
                terminarPrograma();
            }
        } catch (Exception e) {
            imprimirTextLn("");
            imprimirTextLn(menuColorRojo(negrita + "Debe ingresa un número (1 - 6) ..."));
        } finally {
            scanner.close();
        }

    }
}
