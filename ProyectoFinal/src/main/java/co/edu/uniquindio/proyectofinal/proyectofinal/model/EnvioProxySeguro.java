package co.edu.uniquindio.proyectofinal.proyectofinal.model;

import co.edu.uniquindio.proyectofinal.proyectofinal.services.IEnvioComponente;

public class EnvioProxySeguro implements IEnvioComponente {
    private Envio envio;
    private String rolUsuario;

    public EnvioProxySeguro(Envio envio, String rol) {
        this.envio = envio;
        this.rolUsuario = rol;
    }

    @Override
    public double calcularCosto() {
        if ("cliente".equals(rolUsuario) || "administrador".equals(rolUsuario)) {
            return envio.calcularCosto();
        }
        throw new SecurityException("Acceso denegado al costo");
    }

    @Override
    public String getDescripcion() {
        return envio.getDescripcion();
    }
}