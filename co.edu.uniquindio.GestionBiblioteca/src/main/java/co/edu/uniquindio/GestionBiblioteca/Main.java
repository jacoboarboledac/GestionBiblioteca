package co.edu.uniquindio.GestionBiblioteca;

import co.edu.uniquindio.GestionBiblioteca.factory.ModelFactory;
import co.edu.uniquindio.GestionBiblioteca.model.Bibliotecario;
import co.edu.uniquindio.GestionBiblioteca.model.Libro;
import co.edu.uniquindio.GestionBiblioteca.model.Prestamo;
import co.edu.uniquindio.GestionBiblioteca.model.Usuario;

import java.util.*;

public class Main {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        Bibliotecario bibliotecario = Bibliotecario.getInstancia("Ana", "EMP001");

        System.out.println("=== Gestión de Biblioteca ===");


        System.out.print("¿Cuántos libros desea agregar al inventario? ");
        int numLibros = leerEntero();

        for (int i = 0; i < numLibros; i++) {
            System.out.println("Ingrese datos para libro #" + (i + 1));
            System.out.print("Título: ");
            String titulo = scanner.nextLine();
            System.out.print("Autor: ");
            String autor = scanner.nextLine();
            System.out.print("ISBN: ");
            String isbn = scanner.nextLine();

            Libro libro = ModelFactory.crearLibro(titulo, autor, isbn);
            bibliotecario.gestionarItem(libro);
        }

        // Listar libros disponibles
        bibliotecario.listarLibrosDisponibles();

        // Crear usuario
        System.out.println("\nIngrese datos del usuario:");
        System.out.print("Nombre: ");
        String nombreUsuario = scanner.nextLine();
        System.out.print("ID Usuario: ");
        String idUsuario = scanner.nextLine();

        Usuario usuario = ModelFactory.crearUsuario(nombreUsuario, idUsuario);

        // Préstamo de libro
        System.out.print("\nIngrese el título del libro que desea prestar: ");
        String tituloPrestamo = scanner.nextLine();

        Libro libroParaPrestar = buscarLibroPorTitulo(bibliotecario, tituloPrestamo);

        if (libroParaPrestar != null) {
            Prestamo prestamo = ModelFactory.crearPrestamo(libroParaPrestar, usuario);
            bibliotecario.gestionarPréstamos(prestamo);
        } else {
            System.out.println("Libro no encontrado en inventario.");
        }

        // Listar libros disponibles después del préstamo
        bibliotecario.listarLibrosDisponibles();

        // Devolución de libro
        System.out.print("\n¿Desea devolver un libro? (s/n): ");
        String respuesta = scanner.nextLine().trim().toLowerCase();
        if (respuesta.equals("s")) {
            System.out.print("Ingrese el título del libro a devolver: ");
            String tituloDevolucion = scanner.nextLine();

            Prestamo prestamoADevolver = buscarPrestamoPorTitulo(usuario, tituloDevolucion);

            if (prestamoADevolver != null) {
                bibliotecario.gestionarDevolucion(prestamoADevolver, new Date());
            } else {
                System.out.println("No se encontró préstamo activo para ese libro.");
            }
        }

        // Listar libros disponibles después de la devolución
        bibliotecario.listarLibrosDisponibles();

        // Eliminar libro del inventario
        System.out.print("\n¿Desea eliminar un libro del inventario? (s/n): ");
        respuesta = scanner.nextLine().trim().toLowerCase();
        if (respuesta.equals("s")) {
            System.out.print("Ingrese el título del libro a eliminar: ");
            String tituloEliminar = scanner.nextLine();

            Libro libroAEliminar = buscarLibroPorTitulo(bibliotecario, tituloEliminar);

            if (libroAEliminar != null) {
                bibliotecario.eliminarLibro(libroAEliminar);
            } else {
                System.out.println("Libro no encontrado en inventario.");
            }
        }

        // Listar libros disponibles al final
        bibliotecario.listarLibrosDisponibles();

        System.out.println("\nSe cierra el sistema...");
    }

    private static int leerEntero() {
        while (true) {
            try {
                String linea = scanner.nextLine();
                int valor = Integer.parseInt(linea);
                if (valor >= 0) {
                    return valor;
                } else {
                    System.out.print("Por favor ingrese un número entero positivo: ");
                }
            } catch (NumberFormatException e) {
                System.out.print("Entrada inválida. Ingrese un número entero: ");
            }
        }
    }

    private static Libro buscarLibroPorTitulo(Bibliotecario bibliotecario, String titulo) {
        for (Libro libro : bibliotecario.inventarioLibros) {
            if (libro.getTitulo().equalsIgnoreCase(titulo)) {
                return libro;
            }
        }
        return null;
    }

    private static Prestamo buscarPrestamoPorTitulo(Usuario usuario, String tituloLibro) {
        for (Prestamo prestamo : usuario.getPrestamosActivos()) {
            if (prestamo.getLibro().getTitulo().equalsIgnoreCase(tituloLibro) && prestamo.getFechaDevolucion() == null) {
                return prestamo;
            }
        }
        return null;
    }
}
