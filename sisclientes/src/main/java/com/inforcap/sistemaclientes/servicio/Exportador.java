package com.inforcap.sistemaclientes.servicio;

import java.util.List;

import com.inforcap.sistemaclientes.modelo.IClasstoSave;

public abstract class  Exportador {

    public static final String PATH_CSV = "src/path_csv/";
    public static final String PATH_TXT = "src/path_txt/";
    public static final String MSGERRORARCHIVO = "Archivo vació u nulo";
    public static final String MSGERRORDIRECTORIO = "Error al crear archivo";
    public static final String MSGERRORDESCRIBIR = "Error al escribir archivo";
    public static final String MSGERRORDLISTA = "Lista vacía";
    public static final String MSGERRORCSV = "Error terminación nombre CSV...";
    public static final String MSGERRORIO_FILE = "Error (IO File) es directorio ó no puede ser creado o abierto...";

    public abstract  void exportar( String fileName, List<IClasstoSave> listaClientes);



}
