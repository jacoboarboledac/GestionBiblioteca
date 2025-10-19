package co.edu.uniquindio.proyectofinal.proyectofinal.model;

import java.util.LinkedList;

public class Cliente extends Usuario{
    private String idCliente;
    private String Correo;
    LinkedList<Envio> envios;
    public Cliente(String idCliente, String Correo, String nombre, String numTelefono) {
        super(nombre, numTelefono);
        this.idCliente = idCliente;
        this.Correo = Correo;
        envios = new LinkedList<>();
    }
    public String getIdCliente() {
        return idCliente;
    }
    public void setIdCliente(String idCliente) {
        this.idCliente = idCliente;
    }
    public String getCorreo() {
        return Correo;
    }
    public void setCorreo(String Correo) {
        this.Correo = Correo;
    }

    public LinkedList<Envio> getEnvios() {
        return envios;
    }

    public void setEnvios(LinkedList<Envio> envios) {
        this.envios = envios;
    }
    public boolean agregarServiciosEnvio(String idEnvioActualizar, Prioridad prioridad,
                                         ServicioAdicional servicioAdicional) {

        for (Envio envio : envios) {
            if (envio.getIdEnvio().equals(idEnvioActualizar)) {
                envio.setPrioridad(prioridad);
                envio.setServicioAdicional(servicioAdicional);
                return true;
            }

        }
        return false;

    }
}
