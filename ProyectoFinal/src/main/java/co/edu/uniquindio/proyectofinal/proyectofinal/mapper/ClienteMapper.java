package co.edu.uniquindio.proyectofinal.proyectofinal.mapper;

import co.edu.uniquindio.proyectofinal.proyectofinal.dto.ClienteDTO;
import co.edu.uniquindio.proyectofinal.proyectofinal.model.Cliente;

import java.util.List;
import java.util.stream.Collectors;

public class ClienteMapper {

    public static ClienteDTO toDTO(Cliente cliente) {
        if (cliente == null) return null;

        return new ClienteDTO(
                cliente.getIdCliente(),
                cliente.getNombre(),
                cliente.getCorreo(),
                cliente.getNumTelefono()
        );
    }

    public static List<ClienteDTO> toDTOList(List<Cliente> clientes) {
        if (clientes == null) return List.of();
        return clientes.stream()
                .map(ClienteMapper::toDTO)
                .collect(Collectors.toList());
    }
}