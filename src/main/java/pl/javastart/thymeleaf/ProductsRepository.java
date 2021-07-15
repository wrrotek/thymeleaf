package pl.javastart.thymeleaf;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class ProductsRepository implements CommandLineRunner {

    private final List<Product> products = new ArrayList<>();

    @Override
    public void run(String... args) throws Exception {
        initProducts();
    }

    private void initProducts() {
        products.add(new Product("Szczoteczka do zebow", 10, Category.BEAUTY));
        products.add(new Product("domestos", 23, Category.DOMOWE));
        products.add(new Product("lody", 32, Category.SPOZYWCZE));
        products.add(new Product("lodowka", 1200, Category.DOMOWE));
        products.add(new Product("Ludwik", 7, Category.DOMOWE));
        products.add(new Product("Podklad", 76, Category.BEAUTY));
    }

    public List<Product> getAll() {
        return products;
    }

    public List<Product> findByCategory(Category category) {
        return products.stream().filter(product -> product.getCategory().equals(category)).collect(Collectors.toList());
    }

    public void add(Product product) {
        products.add(product);
    }

}
