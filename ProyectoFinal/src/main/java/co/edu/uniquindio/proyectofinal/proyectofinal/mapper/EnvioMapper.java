package co.edu.uniquindio.proyectofinal.proyectofinal.mapper;

import co.edu.uniquindio.proyectofinal.proyectofinal.dto.EnvioDTO;
import co.edu.uniquindio.proyectofinal.proyectofinal.model.Envio;
import co.edu.uniquindio.proyectofinal.proyectofinal.model.Paquete;

import java.util.List;
import java.util.stream.Collectors;

public class EnvioMapper {


    public static EnvioDTO toDTO(Envio envio) {
        if (envio == null) return null;

        return new EnvioDTO(
                envio.getIdEnvio(),
                envio.getDireccionOrigen(),
                envio.getDireccionDestino(),
                envio.getFechaCreacion(),
                envio.getFechaEntrega(),
                envio.getEstadoEnvio(),
                envio.calcularCosto(),
                envio.getPrioridad(),
                envio.getIdRepartidorAsignado(),
                envio.getIdCliente()
        );
    }


    public static List<EnvioDTO> toDTOList(List<Envio> envios) {
        if (envios == null) return List.of();
        return envios.stream()
                .map(EnvioMapper::toDTO)
                .collect(Collectors.toList());
    }


}
