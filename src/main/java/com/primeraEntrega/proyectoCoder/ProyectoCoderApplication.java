package com.primeraEntrega.proyectoCoder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// import com.primeraEntrega.proyectoCoder.model.Cliente;
// import com.primeraEntrega.proyectoCoder.services.ClienteService;

@SpringBootApplication
public class ProyectoCoderApplication implements CommandLineRunner {

	@Autowired
	// private ClienteService clienteService;


	public static void main(String[] args) {
		SpringApplication.run(ProyectoCoderApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		// // Crear Cliente
		// this.clienteService.creandoCliente(new Cliente("jesus", "Rivera", "usuario1@correo.com", "33-33-33-33-33"));

		// this.clienteService.creandoCliente(new Cliente("Alan", "Barajas", "Balan@correo.com", "22-22-22-22-22"));

		// this.clienteService.creandoCliente(new Cliente("Manuel", "Rivera", "river@correo.com", "44-44-44-44-44"));

		// // Actualizamos cliente
		// this.clienteService.actualizarCliente((long) 3, "Alejandro");

		// // Crear Cliente
		// this.clienteService.creandoCliente(new Cliente("Pedro", "Paramo", "paramore@corre.com", "12-34-56-78-90"));

		// // Eliminamos cliente
		// this.clienteService.deleteCliente((long) 1);

		// // Leer Clientes
		// System.out.println("Mostramos todos los clientes");
		// for (Cliente c : this.clienteService.mostrarClientes()) {
		// 	System.out.println(c);
		// }

	}
}
