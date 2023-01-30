package ma.enset.productsapp.web;

import lombok.AllArgsConstructor;
import ma.enset.productsapp.repositories.ProductRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@AllArgsConstructor
public class ProductController{
    private ProductRepository productRepository;

    @GetMapping("/")
    public String index(){
        return "index";
    }

    @GetMapping("/products")
    public String products(Model model){
        model.addAttribute("products",productRepository.findAll());
        return "products";
    }

    @ExceptionHandler(Exception.class)
    public String handleException(Exception e,  Model model){
        model.addAttribute("errorMessage","You're not authorized to access this page");
        return "errors";
    }
}
