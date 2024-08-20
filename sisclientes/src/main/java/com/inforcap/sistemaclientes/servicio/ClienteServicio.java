package com.inforcap.sistemaclientes.servicio;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import com.inforcap.sistemaclientes.modelo.CategoriaEnum;
import com.inforcap.sistemaclientes.modelo.Cliente;
import com.inforcap.sistemaclientes.modelo.IClasstoSave;

public class ClienteServicio {

    List<IClasstoSave> listaClientes;
    private IClasstoSave clienteAntiguo;

    String msgError;
    

    public String getMsgError() {
        return this.msgError;
    }

    public IClasstoSave getClienteAntiguo() {
        return clienteAntiguo;
    }

    public List<IClasstoSave> getListaClientes() {
        return this.listaClientes;
    }

    public ClienteServicio() {
        listaClientes = new ArrayList<>();
    }

    public void retornolistarClientes() {

        if (listaClientes.isEmpty()) {
            System.out.println("No tiene Clientes...");
        }

        for (IClasstoSave cliente : listaClientes) {
            System.out.println("-----------------------------------");
            System.out.printf("RUN del Cliente: %s", cliente.getRunCliente() + "\n");
            System.out.printf("Nombre del Cliente: %s", cliente.getNombreCliente() + "\n");
            System.out.printf("Apellido del Cliente: %s", cliente.getApellidoCliente() + "\n");
            System.out.printf("Años como Cliente: %s", cliente.getAniosCliente() + "\n");
            System.out.printf("Categoría del Cliente: %s", cliente.getNombreCategoria() + "\n");
            System.out.println(" ");

            // cliente.toString();
        }

    }

    // Método auxiliar para validar que el string no sea nulo o vacío
    private String validarNoVacio(String valor, String campo) {
        Objects.requireNonNull(valor, "El campo " + campo + " no puede ser nulo");

        if (valor.trim().isEmpty()) {
            throw new IllegalArgumentException("El campo " + campo + " no puede estar vacío");
        }

        return valor;
    }

    public void agregarCliente(String runCliente, String nombreCliente, String apellidoCliente, String aniosCliente, String categoria) {

        String newRunCliente;
        String newNombreCliente;
        String newApellidoCliente;
        String newAniosCliente;

        String newCategoria = (categoria == null || categoria.isEmpty()) ? "ACTIVO" : categoria.toUpperCase();
        CategoriaEnum newcategoriaEnum;
        this.msgError = null;

        try {
            newRunCliente = validarNoVacio(runCliente, "runCliente");
            newNombreCliente = validarNoVacio(nombreCliente, "nombreCliente");
            newApellidoCliente = validarNoVacio(apellidoCliente, "apellidoCliente");
            newAniosCliente = validarNoVacio(aniosCliente, "aniosCliente");

            newcategoriaEnum = CategoriaEnum.valueOf(newCategoria);

            IClasstoSave cliente = new Cliente(newRunCliente, newNombreCliente, newApellidoCliente, newAniosCliente, newcategoriaEnum);
            listaClientes.add(cliente);
        } catch (IllegalArgumentException e) {
            this.msgError = e.getMessage();
        }

    }

    public void agregarCliente(IClasstoSave cliente) {
        listaClientes.add(cliente);
    }

    public void editarCliente(String runCliente, String nombreCliente, String apellidoCliente, String aniosCliente, String categoria) {

        IClasstoSave clienteNuevo = new Cliente(runCliente, nombreCliente, apellidoCliente, aniosCliente, CategoriaEnum.valueOf(categoria));

        int indiceClienteOld = listaClientes.indexOf(clienteAntiguo);
        listaClientes.set(indiceClienteOld, clienteNuevo);

    }

    public boolean buscarProductoCodigo(String rut) {

        List<IClasstoSave> productosFiltrado = listaClientes.stream()
                .filter(producto -> (producto.getRunCliente() == null ? rut == null : producto.getRunCliente().equals(rut)))
                .collect(Collectors.toList());

        if (!productosFiltrado.isEmpty()) {
            clienteAntiguo = productosFiltrado.get(0);
            return true;
        }
        return false;

    }

}
