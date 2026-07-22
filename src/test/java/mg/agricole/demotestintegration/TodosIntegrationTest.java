package mg.agricole.demotestintegration;

import mg.agricole.demotestintegration.model.Todos;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
class TodosIntegrationTest {

    static {
        // Force Testcontainers à utiliser le socket standard de Docker Desktop sous Windows
        System.setProperty("DOCKER_HOST", "npipe:////./pipe/docker_engine");
        System.setProperty("api.version", "1.43");
    }
    // Démarre un vrai conteneur PostgreSQL en arrière-plan pour le test
    @Container
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:15-alpine");

    // Connecte automatiquement Spring Boot à ce conteneur Docker
    @DynamicPropertySource
    static void configureProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgres::getJdbcUrl);
        registry.add("spring.datasource.username", postgres::getUsername);
        registry.add("spring.datasource.password", postgres::getPassword);
    }

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void test_get_todos() {
        // Fait une requête HTTP sur ton API pour vérifier qu'elle répond bien avec le conteneur
        Todos[] todos = restTemplate.getForObject("/todos", Todos[].class);

        // Vérifie que le résultat n'est pas null (la base est vide au départ, mais l'API répond 200 OK)
        assertThat(todos).isNotNull();
    }
}
