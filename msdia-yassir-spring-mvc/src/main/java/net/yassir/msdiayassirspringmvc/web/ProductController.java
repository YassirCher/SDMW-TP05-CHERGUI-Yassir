package net.yassir.msdiayassirspringmvc.web;

import jakarta.validation.Valid;
import net.yassir.msdiayassirspringmvc.entities.Product;
import net.yassir.msdiayassirspringmvc.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.UriComponentsBuilder;

@Controller
public class ProductController {
    @Autowired
    private ProductRepository productRepository;

    // Affiche la liste des produits avec pagination et recherche
    @GetMapping("/user/index")
    @PreAuthorize("hasRole('USER')")
    public String index(Model model,
                        @RequestParam(name = "page", defaultValue = "0") int p,
                        @RequestParam(name = "size", defaultValue = "4") int s,
                        @RequestParam(name = "keyword", defaultValue = "") String kw) {
        // Validation des paramètres de pagination
        if (p < 0) p = 0;
        if (s <= 0) s = 4;
        String keyword = kw != null ? kw.trim() : ""; // Nettoie keyword
        if (keyword.equals(",")) keyword = ""; // Si keyword est ",", le rendre vide
        if (keyword.endsWith(",")) keyword = keyword.substring(0, keyword.length() - 1); // Supprime la virgule en fin
        Page<Product> pageProducts = productRepository.findByNameContains(keyword, PageRequest.of(p, s));
        model.addAttribute("ListProducts", pageProducts.getContent());
        model.addAttribute("pages", new int[pageProducts.getTotalPages()]);
        model.addAttribute("currentPage", p);
        model.addAttribute("keyword", keyword);
        return "products";
    }

    // Supprime un produit
    @PostMapping("/admin/delete")
    @PreAuthorize("hasRole('ADMIN')")
    public String delete(@RequestParam(name = "id") Long id,
                         @RequestParam(name = "page", defaultValue = "0") int page,
                         @RequestParam(name = "keyword", defaultValue = "") String keyword) {
        productRepository.deleteById(id);
        keyword = keyword != null ? keyword.trim() : ""; // Nettoie keyword
        if (keyword.equals(",")) keyword = ""; // Si keyword est ",", le rendre vide
        if (keyword.endsWith(",")) keyword = keyword.substring(0, keyword.length() - 1); // Supprime la virgule en fin
        return "redirect:" + UriComponentsBuilder.fromPath("/user/index")
                .queryParam("page", page)
                .queryParam("keyword", keyword)
                .build().toUriString();
    }

    // Redirige vers la page principale
    @GetMapping("/")
    public String home() {
        return "redirect:/user/index";
    }

    // Affiche le formulaire pour ajouter un nouveau produit
    @GetMapping("/admin/newProduct")
    @PreAuthorize("hasRole('ADMIN')")
    public String newProduct(Model model) {
        model.addAttribute("product", new Product());
        model.addAttribute("page", 0);
        model.addAttribute("keyword", "");
        return "new-product";
    }

    // Sauvegarde un nouveau produit
    @PostMapping("/admin/saveProduct")
    @PreAuthorize("hasRole('ADMIN')")
    public String saveProduct(@Valid Product product,
                              BindingResult bindingResult,
                              Model model,
                              @RequestParam(defaultValue = "0") int page,
                              @RequestParam(defaultValue = "") String keyword) {
        keyword = keyword != null ? keyword.trim() : ""; // Nettoie keyword
        if (keyword.equals(",")) keyword = ""; // Si keyword est ",", le rendre vide
        if (keyword.endsWith(",")) keyword = keyword.substring(0, keyword.length() - 1); // Supprime la virgule en fin
        if (bindingResult.hasErrors()) {
            model.addAttribute("page", page);
            model.addAttribute("keyword", keyword);
            return "new-product";
        }
        productRepository.save(product);
        return "redirect:" + UriComponentsBuilder.fromPath("/user/index")
                .queryParam("page", page)
                .queryParam("keyword", keyword)
                .build().toUriString();
    }

    // Affiche le formulaire pour éditer un produit existant
    @GetMapping("/admin/editProduct")
    @PreAuthorize("hasRole('ADMIN')")
    public String editProduct(Model model,
                              @RequestParam Long id,
                              @RequestParam(defaultValue = "0") int page,
                              @RequestParam(defaultValue = "") String keyword) {
        Product product = productRepository.findById(id).orElse(null);
        if (product == null) {
            return "redirect:/notAuthorized";
        }
        keyword = keyword != null ? keyword.trim() : ""; // Nettoie keyword
        if (keyword.equals(",")) keyword = ""; // Si keyword est ",", le rendre vide
        if (keyword.endsWith(",")) keyword = keyword.substring(0, keyword.length() - 1); // Supprime la virgule en fin
        model.addAttribute("product", product);
        model.addAttribute("page", page);
        model.addAttribute("keyword", keyword);
        return "editProduct";
    }

    // Sauvegarde les modifications d'un produit
    @PostMapping("/admin/updateProduct")
    @PreAuthorize("hasRole('ADMIN')")
    public String updateProduct(@Valid Product product,
                                BindingResult bindingResult,
                                Model model,
                                @RequestParam(defaultValue = "0") int page,
                                @RequestParam(defaultValue = "") String keyword) {
        keyword = keyword != null ? keyword.trim() : ""; // Nettoie keyword
        if (keyword.equals(",")) keyword = ""; // Si keyword est ",", le rendre vide
        if (keyword.endsWith(",")) keyword = keyword.substring(0, keyword.length() - 1); // Supprime la virgule en fin
        if (bindingResult.hasErrors()) {
            model.addAttribute("page", page);
            model.addAttribute("keyword", keyword);
            return "editProduct";
        }
        productRepository.save(product);
        return "redirect:" + UriComponentsBuilder.fromPath("/user/index")
                .queryParam("page", page)
                .queryParam("keyword", keyword)
                .build().toUriString();
    }

    // Page pour les accès non autorisés
    @GetMapping("/notAuthorized")
    public String notAuthorized() {
        return "notAuthorized";
    }

    // Page de connexion
    @GetMapping("/login")
    public String login() {
        return "login";
    }
}