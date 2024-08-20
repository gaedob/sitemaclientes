package com.inforcap.sistemaclientes.modelo;

public class Cliente implements IClasstoSave {

    private String runCliente;
    private String nombreCliente;
    private String apellidoCliente;
    private String aniosCliente;
    private CategoriaEnum nombreCategoria;

    public Cliente() { }

    public Cliente(String runCliente, String nombreCliente, String apellidoCliente, String aniosCliente, CategoriaEnum categoria) {
        this.runCliente = runCliente;
        this.nombreCliente = nombreCliente;
        this.apellidoCliente = apellidoCliente;
        this.aniosCliente = aniosCliente;
        this.nombreCategoria = (categoria != null) ? categoria : CategoriaEnum.ACTIVO;
    }

    @Override
    public String getRunCliente() {
        return this.runCliente;
    }

    public void setRunCliente(String runCliente) {
        this.runCliente = runCliente;
    }

    @Override
    public String getNombreCliente() {
        return this.nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    @Override
    public String getApellidoCliente() {
        return this.apellidoCliente;
    }

    public void setApellidoCliente(String apellidoCliente) {
        this.apellidoCliente = apellidoCliente;
    }

    @Override
    public String getAniosCliente() {
        return this.aniosCliente;
    }

    public void setAniosCliente(String aniosCliente) {
        this.aniosCliente = aniosCliente;
    }

    @Override
    public CategoriaEnum getNombreCategoria() {
        return this.nombreCategoria;
    }

    public void setNombreCategoria(CategoriaEnum nombreCategoria) {
        this.nombreCategoria = nombreCategoria;
    }

    @Override
    public String toString() {
        return "{"
                + " RUN del Cliente:'" + getRunCliente() + "'"
                + ", Nombre del Cliente:'" + getNombreCliente() + "'"
                + ", Apellido del Cliente:'" + getApellidoCliente() + "'"
                + ", Años como Cliente:'" + getAniosCliente() + "' años"
                + ", Categoría del Cliente:'" + getNombreCategoria() + "'"
                + "}";
    }

    @Override
    public String toStringSave() {
        return getRunCliente() + ","
                + getNombreCliente() + ","
                + getApellidoCliente() + ","
                + getAniosCliente() + ","
                + getNombreCategoria();
    }
}
