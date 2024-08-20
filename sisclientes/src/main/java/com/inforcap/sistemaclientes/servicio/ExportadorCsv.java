package com.inforcap.sistemaclientes.servicio;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.List;

import javax.imageio.IIOException;

import com.inforcap.sistemaclientes.modelo.IClasstoSave;

public class ExportadorCsv extends Exportador {

    private boolean statusExported = false;
    private String statusMessage = "OK";

    public ExportadorCsv(IClasstoSave classtoSave) {
    
    }

    public boolean getStatusExported() {
        return this.statusExported;
    }

    public String getStatusMessage() {
        return this.statusMessage;
    }

    private boolean checkedValues(String fileName, List<?> listaClientes) {

        if (listaClientes == null || listaClientes.isEmpty()) {
            this.statusMessage = MSGERRORDLISTA;
            return false;
        }

        if ((fileName == null || fileName.isEmpty() || fileName.isBlank())) {
            this.statusMessage = MSGERRORARCHIVO;
            return false;
        }

        if (!fileName.endsWith(".csv")) {
            this.statusMessage = MSGERRORCSV;
            return false;
        }

        return true;
    }

    @Override
    public void exportar(String fileName, List<IClasstoSave> listaClientes) {

        if (!checkedValues(fileName, listaClientes)) {
            return;
        }

        File fileDir = crearDirectorio();

        if (fileDir == null) {
            this.statusMessage = MSGERRORDIRECTORIO;
            return;
        }

        if (!escribirArchivo(fileDir, fileName, listaClientes)) {
            return;
        }
        this.statusExported = true;
    }

    private boolean escribirArchivo(File fileDir, String fileName, List<IClasstoSave> listaClientes) {

        try {
            FileWriter archivo;
            PrintWriter printWrite;
            File file = new File(fileDir, fileName);

            // Add data to Flie
            archivo = new FileWriter(file, false);
            printWrite = new PrintWriter(archivo);
            int i;
            for (i = 0; i < listaClientes.size(); i++) {
                printWrite.println(listaClientes.get(i).toStringSave());
            }
            printWrite.close();
            archivo.close();
        } catch (IIOException iOException) {
            this.statusMessage = MSGERRORIO_FILE;
            return false;
        } catch (Exception e) {
            this.statusMessage = MSGERRORDESCRIBIR;
            return false;
        }

        return true;
    }

    private File crearDirectorio() {

        File dirFile = new File(PATH_CSV);
        if (!dirFile.exists()) {
            if (dirFile.mkdirs()) {
                return dirFile;
            } else {
                return null;
            }
        }
        return dirFile;
    }

}
