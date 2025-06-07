# Gestion de Produits

## Description

Cette application web JEE, développée avec **Spring Boot**, **Spring Data JPA**, **Hibernate**, **Thymeleaf**, et **Spring Security**, permet de gérer des produits (ajout, suppression, mise à jour, recherche) via une interface utilisateur sécurisée. Le design est amélioré grâce à **Tailwind CSS** et **Bootstrap** intégré via des layouts Thymeleaf. L'application répond aux exigences d'un projet académique, incluant des fonctionnalités supplémentaires comme la recherche, l'édition, une page de gestion d'erreurs, et une interface moderne.

---

## Langages et Technologies Utilisées

- **Java** : Langage de programmation principal.
- **Spring Boot** : Framework pour applications Spring autonomes.
- **Spring Web** : Module pour applications web avec Spring MVC.
- **Spring Data JPA** : Gestion simplifiée des accès aux données.
- **Hibernate** : Implémentation JPA pour la persistance.
- **Thymeleaf** : Moteur de templates pour interfaces web.
- **Spring Security** : Sécurité avec authentification et autorisation.
- **Spring Validation** : Validation des formulaires.
- **Lombok** : Réduction du code répétitif via annotations.
- **H2 Database** : Base de données en mémoire pour tests.
- **MySQL** : Base de données pour production.
- **Tailwind CSS** : Framework CSS utilitaire pour design moderne.
- **Bootstrap** : Framework CSS pour layouts Thymeleaf.

---

## Structure des Dossiers et Fichiers

Le projet suit la structure standard Maven/Spring Boot :

```
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
```

- `src/main/java` : Code source Java (entités, dépôts, contrôleurs, configuration).
- `src/main/resources` : Fichiers de configuration et templates Thymeleaf.
- `src/main/resources/static` : Ressources statiques (CSS, JS).
- `src/test/java` : Tests unitaires et d'intégration.
- `pom.xml` : Gestion des dépendances Maven.

---

## Instructions d'Installation

1. **Cloner le dépôt** :

   ```bash
   git clone <URL_DU_DEPOT>
   cd msdia-yassir-spring-mvc
   ```

2. **Prérequis** :

   - Java Development Kit (JDK) 17+.
   - Maven installé.

3. **Configuration de la base de données** :

   - **H2** : Utilisée par défaut pour les tests (aucune configuration requise).
   - **MySQL** : Pour la production, créer une base de données MySQL et mettre à jour `src/main/resources/application.properties` :

     ```properties
     spring.datasource.url=jdbc:mysql://localhost:3306/nom_de_votre_base
     spring.datasource.username=votre_utilisateur
     spring.datasource.password=votre_mot_de_passe
     spring.jpa.hibernate.ddl-auto=update
     spring.jpa.show-sql=true
     server.port=8086
     ```

4. **Lancer l'application** :

   ```bash
   mvn spring-boot:run
   ```

   L'application sera accessible sur `http://localhost:8086`.

---

## Utilisation

1. **Accéder à l'application** :

   - Ouvrir `http://localhost:8086` dans un navigateur.

2. **Connexion** :

   - L'application est sécurisée avec Spring Security. Utiliser les identifiants définis dans `SecurityConfig` (par défaut : `admin/password` pour le rôle ADMIN, `user/password` pour le rôle USER).

3. **Fonctionnalités** :

   - **Afficher la liste des produits** : Page d'accueil avec liste paginée.
   - **Ajouter un produit** : Formulaire pour les utilisateurs ADMIN.
   - **Supprimer un produit** : Option pour les utilisateurs ADMIN.
   - **Rechercher des produits** : Filtrage par nom.
   - **Éditer un produit** : Modification des détails d'un produit (ADMIN).
   - **Gestion des erreurs** : Page dédiée pour erreurs HTTP.

---

## Fonctionnalités Principales

- **Gestion des produits** : Ajout, suppression, mise à jour, recherche.
- **Validation des formulaires** : Vérification des données saisies.
- **Pagination et recherche** : Liste paginée avec filtrage dynamique.
- **Gestion des erreurs** : Page de redirection pour erreurs HTTP.
- **Design amélioré** : Interface moderne avec Tailwind CSS et Bootstrap.
- **Sécurité** : Authentification et autorisation basées sur rôles (USER, ADMIN).

---

## Screenshots

Voici quelques captures d'écran de l'application :
### connexion http://localhost:8086/ :
 #### utilisateurs user1/admin  --- pwd : 1234
-- si il entre des infos errones :
![image](https://github.com/user-attachments/assets/e6786e1f-5463-46f1-be05-b7db97f3fa85)
sinon il se connecte

### Page d'accueil (Liste des produits)
--- pour l'admin :
![image](https://github.com/user-attachments/assets/bea79181-21db-42d6-9f70-b9a1e42ae910)

-- Ajout d'un produit :
 ![image](https://github.com/user-attachments/assets/a9ef37ec-7fa6-4c76-b074-a231c70f378d)
 ceci contient aussi la verification de chaque champ pour qu'il soit conforme
 ![image](https://github.com/user-attachments/assets/02a03d31-f795-4c9d-8007-68e48f60147a)

-- modification d'un produit :
![image](https://github.com/user-attachments/assets/7541cbdf-568e-47f6-befe-7b670b89b0eb)
![image](https://github.com/user-attachments/assets/277e2c16-14a5-4d84-9529-f4ecee6fbe6c)
![image](https://github.com/user-attachments/assets/c7918c15-d5ca-4afb-ba84-c454201784f5)

-- supression d'un produit:
![image](https://github.com/user-attachments/assets/f9ea9ae8-1395-41a9-88be-c4eed198ea5d)
![image](https://github.com/user-attachments/assets/52aac9fd-46dc-4dc9-a907-78a527f27a5c)

 -- deconnexion :
 ![image](https://github.com/user-attachments/assets/c3b8a989-47b1-4196-ba0e-27866064fbbc)
 ![image](https://github.com/user-attachments/assets/59bdecde-fed8-427e-8a42-58d8de7df810)
 
 -- connexion tant q'utilisateur simple:
![image](https://github.com/user-attachments/assets/39ad046e-3cc9-464b-a08b-8b89e39a3395)

-- si l'utilisateur essaye de faire une fonctionalite qu'il na pas le droit a faire :
link : http://localhost:8086/admin/editProduct?id=5&keyword=mouse&page=0
![image](https://github.com/user-attachments/assets/4bbb4403-22d5-4313-a42e-1e2a99fa6cea)

-- si l'utilisateur entre a un lien errone:
![image](https://github.com/user-attachments/assets/30c7a839-34cd-40ba-9963-1251c688aca9)










## Explication des Annotations

Les annotations Spring et Lombok simplifient le développement. Voici les principales utilisées :

### Annotations JPA

- `@Entity` : Marque la classe `Product` comme entité JPA, mappée à une table.

  ```java
  @Entity
  public class Product { ... }
  ```
- `@Id` : Indique la clé primaire.

  ```java
  @Id
  private Long id;
  ```
- `@GeneratedValue` : Génère automatiquement la valeur de la clé primaire.

  ```java
  @Id
  @GeneratedValue
  private Long id;
  ```

### Annotations Spring Data JPA

- `@Repository` : Marque `ProductRepository` comme dépôt Spring Data.

  ```java
  @Repository
  public interface ProductRepository extends JpaRepository<Product, Long> { ... }
  ```

### Annotations Spring MVC

- `@Controller` : Définit `ProductController` comme contrôleur MVC.

  ```java
  @Controller
  public class ProductController { ... }
  ```
- `@GetMapping` : Mappe les requêtes GET.

  ```java
  @GetMapping("/user/index")
  public String index(Model model, ...) { ... }
  ```
- `@PostMapping` : Mappe les requêtes POST.

  ```java
  @PostMapping("/admin/saveProduct")
  public String saveProduct(@Valid Product product, ...) { ... }
  ```
- `@RequestParam` : Extrait les paramètres d’URL (pagination, recherche).

  ```java
  @RequestParam(name = "page", defaultValue = "0") int p
  ```
- `@Autowired` : Injecte `ProductRepository` dans le contrôleur.

  ```java
  @Autowired
  private ProductRepository productRepository;
  ```

### Annotations de Validation

- `@Valid` : Valide l’objet `Product` dans les formulaires.

  ```java
  public String saveProduct(@Valid Product product, ...)
  ```
- `@NotEmpty` : Vérifie que le champ n’est pas vide.

  ```java
  @NotEmpty
  private String name;
  ```
- `@Size` : Contrôle la longueur d’un champ.

  ```java
  @Size(min = 3, max = 50)
  private String name;
  ```
- `@Min` : Vérifie une valeur numérique minimale.

  ```java
  @Min(0)
  private double price;
  ```
- `@Max` : Vérifie une valeur numérique maximale.

  ```java
  @Max(100)
  private int quantity;
  ```

### Annotations Spring Security

- `@Configuration` : Définit la classe `SecurityConfig` comme configuration.

  ```java
  @Configuration
  public class SecurityConfig { ... }
  ```
- `@Bean` : Crée des beans pour Spring Security.

  ```java
  @Bean
  public UserDetailsService userDetailsService(...) { ... }
  ```
- `@PreAuthorize` : Restreint l’accès basé sur les rôles.

  ```java
  @PreAuthorize("hasRole('ADMIN')")
  public String delete(...) { ... }
  ```

### Annotations Lombok

- `@NoArgsConstructor` : Génère un constructeur vide.
- `@AllArgsConstructor` : Génère un constructeur avec tous les champs.
- `@Getter` : Génère des getters.
- `@Setter` : Génère des setters.
- `@ToString` : Génère la méthode `toString()`.
- `@Builder` : Fournit un pattern builder.

  ```java
  @NoArgsConstructor
  @AllArgsConstructor
  @Getter
  @Setter
  @ToString
  @Builder
  public class Product { ... }
  ```

---

## Contribuer

Les contributions sont les bienvenues ! Suivez ces étapes :

1. Forker le dépôt.
2. Créer une branche : `git checkout -b feature/nouvelle-fonctionnalite`.
3. Commiter les modifications : `git commit -m 'Ajout d'une fonctionnalité'`.
4. Pousser la branche : `git push origin feature/nouvelle-fonctionnalite`.
5. Ouvrir une Pull Request.

---

## Licence

Ce projet est sous licence **MIT**. Voir le fichier `LICENSE` pour plus de détails.

---

## Contact

Pour toute question, contactez-moi via \[ton email ou pseudo GitHub\].
