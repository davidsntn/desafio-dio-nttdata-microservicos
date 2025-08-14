package com.nttdata.challenge.davidsantana.project.products_service;

import com.nttdata.challenge.davidsantana.project.products_service.model.Product;
import com.nttdata.challenge.davidsantana.project.products_service.service.ProductService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProductsServiceApplication implements CommandLineRunner {

	private final ProductService productService;

	public ProductsServiceApplication(ProductService productService) {
		this.productService = productService;
	}

	public static void main(String[] args) {
		SpringApplication.run(ProductsServiceApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("========== INÍCIO ==========");

		System.out.println("Criando produtos...");
		Product p1 = new Product("Samsung Galaxy Watch8", "Smartwatch com Gemini integrado", 2879.10);
		Product p2 = new Product("Apple Iphone 16", "Pro Max (256GB)", 8699.00);
		Product p3 = new Product("JBL Wave Beam 2", "Fone bluetooth com cancelamento de ruído", 277.00);
		Product p4 = new Product("Notebook Dell Inspiron 15", "Full HD 12ª Geração Intel Core i5 16GB 1TB SSSD Windows 11", 3782.00);
		Product p5 = new Product("Kindle Colorsoft", "16 GB - Com tela colorida e temperatura de luz ajustável", 1424.05);

		productService.save(p1);
		productService.save(p2);
		productService.save(p3);
		productService.save(p4);
		productService.save(p5);

		System.out.println("Produtos criados com sucesso!");

		System.out.println("========== LISTA COM TODOS OS PRODUTOS ==========");
		productService.listAll().forEach(p ->
				System.out.println(p.getId() + ": " + p.getName() + " - R$" + p.getPrice()));

		System.out.println("========== LISTA DE PRODUTOS COM 'BLUETOOTH' NO NOME ==========");
		productService.findByName("Bluetooth").forEach(p ->
				System.out.println(p.getId() + ": " + p.getName()));

		System.out.println("========== ATUALIZANDO PRODUTO DA LISTA ==========");
		Product productToUpdate = productService.findById(5L);
		System.out.println("Antes: " + productToUpdate);

		productToUpdate.setPrice(1566.55);
		productToUpdate.setDescription("32 GB - Com tela colorida e luz frontal autoadaptável");
		Product updated = productService.save(productToUpdate);

		System.out.println("Depois: " + updated);

		/*System.out.println("========== EXCLUINDO PRODUTO DA LISTA ==========");
		System.out.println("Removendo produto com ID 3...");
		productService.delete(3L);

		System.out.println("========== LISTA COM PRODUTO EXCLUÍDO ==========");
		productService.listAll().forEach(p ->
				System.out.println(p.getId() + ": " + p.getName()));*/

		System.out.println("========== PRODUTOS COM PREÇO ATÉ R$3000 ==========");
		productService.findByMaxPrice(3000.00).forEach(p ->
				System.out.println(p.getId() + ": " + p.getName() + " - R$" + p.getPrice()));

		System.out.println("\n========== FIM ==========");
		System.out.println("\nAcesse o console H2: http://localhost:8100/h2-console");
	}
}