package co.edu.uniquindio.proyectofinal.proyectofinal.mapper;

import co.edu.uniquindio.proyectofinal.proyectofinal.dto.RepartidorDTO;
import co.edu.uniquindio.proyectofinal.proyectofinal.model.Repartidor;

import java.util.List;
import java.util.stream.Collectors;

public class RepartidorMapper {

    public static RepartidorDTO toDTO(Repartidor repartidor) {
        if (repartidor == null) return null;

        return new RepartidorDTO(
                repartidor.getIdRepartidor(),
                repartidor.getNombre(),
                repartidor.getNumTelefono(),
                repartidor.getZonaCobertura(),
                repartidor.getDisponibilidadRepartidor()
        );
    }

    public static List<RepartidorDTO> toDTOList(List<Repartidor> repartidores) {
        if (repartidores == null) return List.of();
        return repartidores.stream()
                .map(RepartidorMapper::toDTO)
                .collect(Collectors.toList());
    }
}