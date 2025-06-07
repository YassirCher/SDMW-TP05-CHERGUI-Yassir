Gestion de Produits

Description

Cette application web JEE, développée avec Spring Boot, Spring Data JPA, Hibernate, Thymeleaf, et Spring Security, permet de gérer des produits (ajout, suppression, mise à jour, recherche) via une interface utilisateur sécurisée. Le design est amélioré grâce à Tailwind CSS et Bootstrap intégré via des layouts Thymeleaf. L'application répond aux exigences d'un projet académique, incluant des fonctionnalités supplémentaires comme la recherche, l'édition, une page de gestion d'erreurs, et une interface moderne.



Langages et Technologies Utilisées





Java : Langage de programmation principal.



Spring Boot : Framework pour applications Spring autonomes.



Spring Web : Module pour applications web avec Spring MVC.



Spring Data JPA : Gestion simplifiée des accès aux données.



Hibernate : Implémentation JPA pour la persistance.



Thymeleaf : Moteur de templates pour interfaces web.



Spring Security : Sécurité avec authentification et autorisation.



Spring Validation : Validation des formulaires.



Lombok : Réduction du code répétitif via annotations.



H2 Database : Base de données en mémoire pour tests.



MySQL : Base de données pour production.



Tailwind CSS : Framework CSS utilitaire pour design moderne.



Bootstrap : Framework CSS pour layouts Thymeleaf.



Structure des Dossiers et Fichiers

Le projet suit la structure standard Maven/Spring Boot :

msdia-yassir-spring-mvc/
├── HELP.md
├── mvnw
├── mvnw.cmd
├── pom.xml
├── src/
│   ├── main/
│   │   ├── java/net/yassir/msdiayassirspringmvc/
│   │   │   ├── MsdiaYassirSpringMvcApplication.java
│   │   │   ├── entities/
│   │   │   │   └── Product.java
│   │   │   ├── repository/
│   │   │   │   └── ProductRepository.java
│   │   │   ├── sec/
│   │   │   │   └── SecurityConfig.java
│   │   │   ├── web/
│   │   │   │   ├── ErrorController.java
│   │   │   │   └── ProductController.java
│   │   ├── resources/
│   │   │   ├── application.properties
│   │   │   ├── static/
│   │   │   └── templates/
│   │   │       ├── editProduct.html
│   │   │       ├── error.html
│   │   │       ├── formProducts.html
│   │   │       ├── layout1.html
│   │   │       ├── login.html
│   │   │       ├── new-product.html
│   │   │       ├── notAuthorized.html
│   │   │       └── products.html
│   ├── test/
│   │   ├── java/net/yassir/msdiayassirspringmvc/
│   │   │   └── MsdiaYassirSpringMvcApplicationTests.java
└── target/
    └── ... (fichiers compilés)





src/main/java : Code source Java (entités, dépôts, contrôleurs, configuration).



src/main/resources : Fichiers de configuration et templates Thymeleaf.



src/main/resources/static : Ressources statiques (CSS, JS).



src/test/java : Tests unitaires et d'intégration.



pom.xml : Gestion des dépendances Maven.



Instructions d'Installation





Cloner le dépôt :

git clone <URL_DU_DEPOT>
cd msdia-yassir-spring-mvc



Prérequis :





Java Development Kit (JDK) 17+.



Maven installé.



Configuration de la base de données :





H2 : Utilisée par défaut pour les tests (aucune configuration requise).



MySQL : Pour la production, créer une base de données MySQL et mettre à jour src/main/resources/application.properties :

spring.datasource.url=jdbc:mysql://localhost:3306/nom_de_votre_base
spring.datasource.username=votre_utilisateur
spring.datasource.password=votre_mot_de_passe
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
server.port=8086



Lancer l'application :

mvn spring-boot:run

L'application sera accessible sur http://localhost:8086.



Utilisation





Accéder à l'application :





Ouvrir http://localhost:8086 dans un navigateur.



Connexion :





L'application est sécurisée avec Spring Security. Utiliser les identifiants définis dans SecurityConfig (par défaut : admin/password pour le rôle ADMIN, user/password pour le rôle USER).



Fonctionnalités :





Afficher la liste des produits : Page d'accueil avec liste paginée.



Ajouter un produit : Formulaire pour les utilisateurs ADMIN.



Supprimer un produit : Option pour les utilisateurs ADMIN.



Rechercher des produits : Filtrage par nom.



Éditer un produit : Modification des détails d'un produit (ADMIN).



Gestion des erreurs : Page dédiée pour erreurs HTTP.



Fonctionnalités Principales





Gestion des produits : Ajout, suppression, mise à jour, recherche.



Validation des formulaires : Vérification des données saisies.



Pagination et recherche : Liste paginée avec filtrage dynamique.



Gestion des erreurs : Page de redirection pour erreurs HTTP.



Design amélioré : Interface moderne avec Tailwind CSS et Bootstrap.



Sécurité : Authentification et autorisation basées sur rôles (USER, ADMIN).



Screenshots

Voici quelques captures d'écran de l'application :





Page d'accueil (Liste des produits) :





Formulaire d'ajout de produit :





Page de recherche :





Page d'erreur :





Page de connexion :



Note : Remplacez les chemins des images ci-dessus par les chemins réels de vos captures d'écran dans le dossier screenshots/ de votre dépôt GitHub.



Explication des Annotations

Les annotations Spring et Lombok simplifient le développement. Voici les principales utilisées :

Annotations JPA





@Entity : Marque la classe Product comme entité JPA, mappée à une table.

@Entity
public class Product { ... }



@Id : Indique la clé primaire.

@Id
private Long id;



@GeneratedValue : Génère automatiquement la valeur de la clé primaire.

@Id
@GeneratedValue
private Long id;

Annotations Spring Data JPA





@Repository : Marque ProductRepository comme dépôt Spring Data.

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> { ... }

Annotations Spring MVC





@Controller : Définit ProductController comme contrôleur MVC.

@Controller
public class ProductController { ... }



@GetMapping : Mappe les requêtes GET.

@GetMapping("/user/index")
public String index(Model model, ...) { ... }



@PostMapping : Mappe les requêtes POST.

@PostMapping("/admin/saveProduct")
public String saveProduct(@Valid Product product, ...) { ... }



@RequestParam : Extrait les paramètres d’URL (pagination, recherche).

@RequestParam(name = "page", defaultValue = "0") int p



@Autowired : Injecte ProductRepository dans le contrôleur.

@Autowired
private ProductRepository productRepository;

Annotations de Validation





@Valid : Valide l’objet Product dans les formulaires.

public String saveProduct(@Valid Product product, ...)



@NotEmpty : Vérifie que le champ n’est pas vide.

@NotEmpty
private String name;



@Size : Contrôle la longueur d’un champ.

@Size(min = 3, max = 50)
private String name;



@Min : Vérifie une valeur numérique minimale.

@Min(0)
private double price;



@Max : Vérifie une valeur numérique maximale.

@Max(100)
private int quantity;

Annotations Spring Security





@Configuration : Définit la classe SecurityConfig comme configuration.

@Configuration
public class SecurityConfig { ... }



@Bean : Crée des beans pour Spring Security.

@Bean
public UserDetailsService userDetailsService(...) { ... }



@PreAuthorize : Restreint l’accès basé sur les rôles.

@PreAuthorize("hasRole('ADMIN')")
public String delete(...) { ... }

Annotations Lombok





@NoArgsConstructor : Génère un constructeur vide.



@AllArgsConstructor : Génère un constructeur avec tous les champs.



@Getter : Génère des getters.



@Setter : Génère des setters.



@ToString : Génère la méthode toString().



@Builder : Fournit un pattern builder.

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class Product { ... }



Contribuer

Les contributions sont les bienvenues ! Suivez ces étapes :





Forker le dépôt.



Créer une branche : git checkout -b feature/nouvelle-fonctionnalite.



Commiter les modifications : git commit -m 'Ajout d'une fonctionnalité'.



Pousser la branche : git push origin feature/nouvelle-fonctionnalite.



Ouvrir une Pull Request.



Licence

Ce projet est sous licence MIT. Voir le fichier LICENSE pour plus de détails.



Contact

Pour toute question, contactez-moi via [ton email ou pseudo GitHub].
