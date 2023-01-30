package ma.enset.productsapp.web;

import lombok.AllArgsConstructor;
import org.keycloak.adapters.AdapterDeploymentContext;
import org.keycloak.adapters.KeycloakDeployment;
import org.keycloak.adapters.spi.HttpFacade;
import org.keycloak.adapters.springsecurity.facade.SimpleHttpFacade;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@AllArgsConstructor
public class SecurityController {

    AdapterDeploymentContext adapterDeploymentContext;

    @GetMapping("/logout")
    public String logout(HttpServletRequest request) throws ServletException {
        request.logout();
        return "redirect:/";
    }


    @GetMapping("profile")
    public String profile(HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes){
        HttpFacade facade = new SimpleHttpFacade(request, response);
        KeycloakDeployment keycloakDeployment = adapterDeploymentContext.resolveDeployment(facade);
        String accountUrl = keycloakDeployment.getAccountUrl();
        redirectAttributes.addAttribute("referrer", "products-app");
        redirectAttributes.addAttribute("referrer_uri", request.getHeader("referer"));
        return "redirect:" + accountUrl;
    }
}
