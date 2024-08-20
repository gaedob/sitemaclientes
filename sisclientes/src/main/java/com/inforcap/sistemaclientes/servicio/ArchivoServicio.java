package com.inforcap.sistemaclientes.servicio;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import com.inforcap.sistemaclientes.modelo.IClasstoSave;
import com.inforcap.sistemaclientes.utilidades.Utilidad;

public class ArchivoServicio extends  Exportador {

    private  Exportador expotradorClass;
    private  ClienteServicio clienteServicio;
    private static final String PATH_RUTA = "src/path_bd/";

    public ArchivoServicio(Exportador exportador, ClienteServicio clienteServicio) {
        this.expotradorClass = exportador;
        this.clienteServicio = clienteServicio;
    }

    public ArchivoServicio(ClienteServicio clienteServicio) {
        this.clienteServicio = clienteServicio;
    }


    @Override
    public void exportar(String fileName, List<IClasstoSave> listaClientes) {

        expotradorClass.exportar(fileName, listaClientes);

    }

    private void parseCliente(String linea) {
        String[] partes = linea.split(",");

        if (partes.length == 5) {
            clienteServicio.agregarCliente(
                    partes[0].trim(),
                    partes[1].trim(),
                    partes[2].trim(),
                    partes[3].trim(),
                    partes[4].trim()

            );
        }
    }

    public void cargarDatos(String fileName) {

        if (Utilidad.validaArchivo(fileName)) {
            return;
        }

        File file = new File(PATH_RUTA + fileName);

        if (!file.exists()) {
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                parseCliente(line);
            }
            reader.close();

            
            // System.out.println("Datos cargados correctamente en la lista ");
        } catch (IOException e) {

            

            //System.out.println("Error al leer el archivo: " + e.getMessage());
        }

    }

}
