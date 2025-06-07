package net.yassir.msdiayassirspringmvc.web;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ErrorController implements org.springframework.boot.web.servlet.error.ErrorController {

    @RequestMapping("/error")
    public String handleError(HttpServletRequest request, Model model) {
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        String errorMessage = "Une erreur inattendue s'est produite.";

        if (statusCode != null) {
            switch (statusCode) {
                case 404:
                    errorMessage = "La page demandée n'a pas été trouvée.";
                    break;
                case 403:
                    errorMessage = "Accès refusé : vous n'avez pas les autorisations nécessaires.";
                    break;
                case 500:
                    errorMessage = "Erreur interne du serveur.";
                    break;
                default:
                    errorMessage = "Erreur : " + statusCode;
            }
        }

        model.addAttribute("errorCode", statusCode != null ? statusCode : "Inconnu");
        model.addAttribute("errorMessage", errorMessage);
        System.out.println("Status Code: " + statusCode);
        return "error";
    }
}