package co.edu.uniquindio.proyectofinal.proyectofinal.model.observer;

public interface ISujeto {
    public void registrarObserver(IEnvioObserver observer);
    public void eliminarObserver(IEnvioObserver observer);
    public void notificarObservers();
}
