package ma.enset.productsapp.web;

import lombok.AllArgsConstructor;
import ma.enset.productsapp.entities.Supplier;
import org.keycloak.adapters.springsecurity.client.KeycloakRestTemplate;
import org.springframework.hateoas.PagedModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@AllArgsConstructor
public class SupplierController {

    private KeycloakRestTemplate keycloakRestTemplate;
    @GetMapping("/suppliers")
    public String suppliers(Model model){
        PagedModel<Supplier> pagedModel = keycloakRestTemplate.getForObject("http://localhost:8083/suppliers", PagedModel.class);
        model.addAttribute("suppliers", pagedModel);
        return "suppliers";
    }

}
