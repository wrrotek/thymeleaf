package pl.javastart.thymeleaf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/")
public class ProductsController {

    private final ProductsRepository productsRepository;

    @Autowired
    public ProductsController(ProductsRepository productsRepository) {
        this.productsRepository = productsRepository;
    }

    @GetMapping("")
    public String home() {
        return "home";
    }

    @GetMapping("lista")//lista?kategoria=spozywcze
    public String showAllProducts(@RequestParam(value = "kategoria", required = false) Category category, Model model) {

        List<Product> products;


        if (category != null) {
            products = productsRepository.findByCategory(category);
        } else {
            products = productsRepository.getAll();
        }
        model.addAttribute("productList", products);
        double sum = 0;
        for (Product product : products) {
            sum += product.getPrice();
        }
        model.addAttribute("priceSum", sum);
        return "productView";
    }

    @PostMapping("add")
    public String addProduct(@RequestParam String name, @RequestParam int price, @RequestParam Category category) {
        Product product = new Product(name, price, category);
        productsRepository.add(product);
        return "redirect:/lista";
    }
}
