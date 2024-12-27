package br.com.emanueldias01.pedidosbtg;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PedidosbtgApplication {

	public static void main(String[] args) {
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        SpringApplication.run(PedidosbtgApplication.class, args);
	}

}
