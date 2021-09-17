package petstore;

import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.contains;

public class Pet {
    //Swagger (Simples, direto) de como você pode fazer teste e integração.
    // É um padrão para estruturar uma API
    // Documentação

    // Atribuições
    String uri = "https://petstore.swagger.io/v2/pet";

    // Metodos e Funções
    public String lerJson(String caminhoJson) throws IOException {
        return new String(Files.readAllBytes(Paths.get(caminhoJson)));
    }

    // Incluir - Create - Post
    @Test // Identifica o método ou função como um teste para o TestNG
    public void incluirPet() throws IOException {
        String jsonBody = lerJson("data/createPet.json");

        // Sintaxe Gherkin
        // Dado - Quando - Então
        // Given - When - Then

        given() // Dado
                .contentType("application/json") // comum em API REST - antigas era "text/xml"
                .log().all()
                .body(jsonBody)
        .when() // Quando
                .post(uri)
        .then() // Então
                .log().all()
                .statusCode(200)
                .body("name",is("Husky Siberiano"))
                .body("status",is("available"))
                .body("category.name", is("dog"))
                .body("tags.name", contains("frio"))
        ;
    }

    // Incluir - Create - Post
    @Test // Identifica o método ou função como um teste para o TestNG
    public void consultarPet() throws IOException {
        String jsonBody = lerJson("data/readPet.json");
        System.out.println("Nosso Json ReadPet: "+jsonBody);

        // Sintaxe Gherkin
        // Dado - Quando - Então
        // Given - When - Then

        given() // Dado
                .contentType("application/json") // comum em API REST - antigas era "text/xml"
                .log().all()
                .body(jsonBody)
                .when() // Quando
                .post(uri)
                .then() // Então
                .log().all()
                .statusCode(200)
        ;
    }
}



