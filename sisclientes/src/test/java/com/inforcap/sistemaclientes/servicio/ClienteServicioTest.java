package com.inforcap.sistemaclientes.servicio;

import java.util.List;
import java.util.logging.Logger;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.inforcap.sistemaclientes.modelo.IClasstoSave;

@DisplayName("Test Clase ClienteServicio")
class ClienteServicioTest {

    private ClienteServicio clienteServicio = new ClienteServicio();
    private static Logger logger = Logger.getLogger("ClienteServicioTest.class");

    @BeforeAll
    static void setup() {
        logger.info("Inicio pruebas Clase ClienteServicio");
    }

    @AfterAll
    static void Finish() {
        logger.info("Fin pruebas Clase ClienteServicio");
    }

    @Test
    void agrearClienteTest() {

        clienteServicio.agregarCliente("11965671-0", "Gio", "Aedo", "47 años", "ACTIVO");
        List<IClasstoSave> clientes = clienteServicio.getListaClientes();

        Assertions.assertEquals(1, clienteServicio.getListaClientes().size());

        IClasstoSave cliente  =  clientes.get(0);

       assertTrue(clienteServicio.getListaClientes().contains(cliente ));

    }

    @Test
    void agrearClienteNullTest() {


        clienteServicio.agregarCliente("", "", "", "", "");
        Assertions.assertEquals(false, clienteServicio.getMsgError().isEmpty());


    }
    

}
