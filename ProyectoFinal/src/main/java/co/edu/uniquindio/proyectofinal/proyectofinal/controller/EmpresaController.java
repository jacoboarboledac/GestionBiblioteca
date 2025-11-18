package co.edu.uniquindio.proyectofinal.proyectofinal.controller;

import co.edu.uniquindio.proyectofinal.proyectofinal.dto.EnvioDTO;
import co.edu.uniquindio.proyectofinal.proyectofinal.mapper.EnvioMapper;
import co.edu.uniquindio.proyectofinal.proyectofinal.model.*;

import java.util.Arrays;
import java.util.List;

public class EmpresaController {
    private EmpresaLogistica empresaLogistica;
    private static EmpresaController empresaController;

    private EmpresaController() {
        empresaLogistica = inicializarDatos();
    }

    public static EmpresaController getInstance() {
        if (empresaController == null) {
            empresaController = new EmpresaController();
        }
        return empresaController;
    }

    public EmpresaLogistica getEmpresaLogistica() {
        return empresaLogistica;
    }

    public EmpresaLogistica inicializarDatos() {
        EmpresaLogistica nuevaEmpresa = new EmpresaLogistica("LogisticaExpress", "1234");


        nuevaEmpresa.crearCliente("CLI-001", "Juan Perez", "juan@email.com", "3101234567", "1234");
        nuevaEmpresa.crearCliente("CLI-002", "María Gomez", "maria@email.com", "3207654321", "5678");


        nuevaEmpresa.crearRepartidor(
                "REP-001", "Carlos Ramirez", "3151112233", "1023456789",
                "Centro", DisponibilidadRepartidor.ACTIVO, "pass123"
        );
        nuevaEmpresa.crearRepartidor(
                "REP-002", "Ana Martínez", "3162223344", "1098765432",
                "Norte", DisponibilidadRepartidor.ACTIVO, "pass456"
        );


        Administrador admin = new Administrador(
                "ADM-001", "admin@logistica.com", "Admin", "3000000000", "admin123"
        );
        nuevaEmpresa.agregarAdministrador(admin, "ADM-001");


        Paquete paq1 = new Paquete(2.5, 5000);
        Paquete paq2 = new Paquete(1.0, 2000);
        Paquete paq3 = new Paquete(3.0, 7000);
        Paquete paq4 = new Paquete(0.5, 1000);


        Envio env1 = nuevaEmpresa.crearSolicitud(
                "CLI-001", "Armenia", "Cali",
                Arrays.asList(paq1), Prioridad.ESTANDAR, "Norte"
        );
        env1.agregarServicioAdicional(ServicioAdicional.SEGURO);
        env1.agregarServicioAdicional(ServicioAdicional.FIRMA);
        env1.setFechaEntrega("2025-12-15");

        Envio env2 = nuevaEmpresa.crearSolicitud(
                "CLI-001", "Cali", "Medellín",
                Arrays.asList(paq2, paq3), Prioridad.EXPRESS, "Sur"
        );
        env2.agregarServicioAdicional(ServicioAdicional.FRAGIL);
        env2.setFechaEntrega("2025-12-20");
        env2.setEstadoEnvio(EstadoEnvio.INCIDENCIA);

        Envio env3 = nuevaEmpresa.crearSolicitud(
                "CLI-002", "Bogotá", "Bucaramanga",
                Arrays.asList(paq4), Prioridad.PRIORITARIO, "Centro"
        );
        env3.agregarServicioAdicional(ServicioAdicional.SEGURO);
        env3.setFechaEntrega("2025-12-25");

        Envio env4 = nuevaEmpresa.crearSolicitud(
                "CLI-002", "Tunja", "Duitama",
                Arrays.asList(paq2), Prioridad.ESTANDAR, "Rural"
        );
        env4.setFechaEntrega("2026-01-05");


        nuevaEmpresa.asignarEnvioRepartidor("ENV-1", "REP-001");
        nuevaEmpresa.asignarEnvioRepartidor("ENV-2", "REP-002");
        nuevaEmpresa.asignarEnvioRepartidor("ENV-3", "REP-001");


        return nuevaEmpresa;
    }
    public List<EnvioDTO> obtenerEnviosDelCliente(String idCliente) {
        Cliente cliente = empresaLogistica.buscarCliente(idCliente);
        if (cliente == null) return List.of();

        List<Envio> envios = cliente.consultarEnviosAsociados();
        return EnvioMapper.toDTOList(envios);
    }
}