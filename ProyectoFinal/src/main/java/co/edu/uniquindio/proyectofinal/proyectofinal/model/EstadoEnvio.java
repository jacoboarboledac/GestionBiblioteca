package co.edu.uniquindio.proyectofinal.proyectofinal.model;

public enum EstadoEnvio {
    PENDIENTE{
        public EstadoEnvio siguienteEstado() {
            return EstadoEnvio.ASIGNADO;
        }
    },
    ASIGNADO{
        public EstadoEnvio siguienteEstado() {
            return EstadoEnvio.EN_RUTA;
        }
    },
    EN_RUTA{
        public EstadoEnvio siguienteEstado() {
            return EstadoEnvio.ENTREGADO;
        }
    },
    ENTREGADO{
        public EstadoEnvio siguienteEstado() {
            return EstadoEnvio.ENTREGADO;
        }
    },
    CANCELADO{
        public EstadoEnvio siguienteEstado() {
            return EstadoEnvio.CANCELADO;
        }
    },
    INCIDENCIA{
        public EstadoEnvio siguienteEstado() {
            return EstadoEnvio.INCIDENCIA;
        }
    };

    public abstract EstadoEnvio siguienteEstado();

}
