package pl.sii.eu.spockdemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pl.sii.eu.spockdemo.product.Product;
import pl.sii.eu.spockdemo.product.ProductRepository;

import java.math.BigDecimal;

@SpringBootApplication
public class SpockDemoApplication implements CommandLineRunner {

	@Autowired
    private ProductRepository productRepository;

	public static void main(String[] args) {
		SpringApplication.run(SpockDemoApplication.class, args);
	}

	@Override
	public void run(String... strings) throws Exception {
        Product stock = Product.ofNamePriceAndQuantity("Stock", BigDecimal.valueOf(30.5), 100);
        Product herring = Product.ofNamePriceAndQuantity("Herring", BigDecimal.valueOf(1.5), 10);
        Product orangeJuice = Product.ofNamePriceAndQuantity("Orange Juice", BigDecimal.valueOf(5.99), 30);
        productRepository.save(stock);
        productRepository.save(herring);
        productRepository.save(orangeJuice);
	}
}
