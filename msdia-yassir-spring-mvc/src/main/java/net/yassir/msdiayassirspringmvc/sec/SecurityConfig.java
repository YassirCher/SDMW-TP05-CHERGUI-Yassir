package net.yassir.msdiayassirspringmvc.sec;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.EnableGlobalAuthentication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public InMemoryUserDetailsManager inMemoryUserDetailsManager() {
        PasswordEncoder encoder= passwordEncoder();
        String encodedPWD = encoder.encode("1234");
        System.out.println("===========");
        System.out.println(encodedPWD);
        System.out.println("===========");
        return new InMemoryUserDetailsManager(
                User.withUsername("user1").password(passwordEncoder().encode("1234")).roles("USER").build(),
                User.withUsername("user2").password(passwordEncoder().encode("1234")).roles("USER").build(),
                User.withUsername("admin").password(passwordEncoder().encode("1234")).roles("USER", "ADMIN").build()
        );
    }
    //    @Bean
    public UserDetailsService userDetailsService() {
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                // à compléter
                return null;
            }
        };
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .formLogin(fl -> fl
                        .loginPage("/login")
                        .defaultSuccessUrl("/user/index")
                        .permitAll())
                .csrf(csrf -> csrf
                        .ignoringRequestMatchers("/admin/delete")) // Désactive CSRF pour /admin/delete
                .authorizeHttpRequests(ar -> ar
                        .requestMatchers("/", "/login", "/error", "/public/**", "/webjars/**", "/h2-console/**").permitAll())
                .authorizeHttpRequests(ar -> ar
                        .requestMatchers("/user/**").hasRole("USER"))
                .authorizeHttpRequests(ar -> ar
                        .requestMatchers(HttpMethod.GET, "/admin/newProduct", "/admin/editProduct").hasRole("ADMIN"))
                .authorizeHttpRequests(ar -> ar
                        .requestMatchers(HttpMethod.POST, "/admin/saveProduct", "/admin/updateProduct", "/admin/delete").hasRole("ADMIN"))
                .authorizeHttpRequests(ar -> ar
                        .anyRequest().authenticated())
                .exceptionHandling(eh -> eh
                        .accessDeniedPage("/notAuthorized"))
                .logout(logout -> logout
                        .logoutRequestMatcher(new AntPathRequestMatcher("/logout", "POST"))
                        .logoutSuccessUrl("/login?logout")
                        .permitAll())
                .build();
    }
}