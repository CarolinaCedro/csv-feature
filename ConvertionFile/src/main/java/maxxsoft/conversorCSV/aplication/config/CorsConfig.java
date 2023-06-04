package maxxsoft.conversorCSV.aplication.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.config.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Classe de configuração para configurar o CORS (Cross-Origin Resource Sharing).
 */

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    /**
     * Configura as definições de CORS para todas as rotas.
     * Permite todas as origens, todos os métodos HTTP e todos os headers.
     *
     * @param registry O objeto CorsRegistry usado para configurar o CORS.
     */

    @Override
    public void addCorsMappings(org.springframework.web.servlet.config.annotation.CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("*")
                .allowedHeaders("*");
    }

    /**
     * Para adicionar rotas específicas como rotas privadas, você pode estender a configuração CORS
     * usando o método abaixo e adicionar as configurações desejadas para essas rotas.
     *
     * @param registry O objeto CorsRegistry usado para configurar o CORS.
     */
    /* Exemplo de rota privada */
    /*
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/private-route")
                .allowedOrigins("http://localhost:8080")  // Permitir apenas a origem específica
                .allowedMethods("GET", "POST")  // Permitir apenas os métodos GET e POST
                .allowedHeaders("Authorization");  // Permitir apenas o header "Authorization"
    }
    */

}
