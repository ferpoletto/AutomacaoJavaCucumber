
package utils;

import io.restassured.response.Response;
import static io.restassured.RestAssured.*;
import io.restassured.http.ContentType;
import java.util.Map;

public class APIUtils {
    private static Response ultimaResposta;

    public static void get(String endpoint) {
        ultimaResposta = given().when().get(System.getenv("API_BASE_URL") + endpoint);
    }

    public static void post(String endpoint, String body) {
        ultimaResposta = given().contentType(ContentType.JSON).body(body).when().post(System.getenv("API_BASE_URL") + endpoint);
    }

    public static void put(String endpoint, String body) {
        ultimaResposta = given().contentType(ContentType.JSON).body(body).when().put(System.getenv("API_BASE_URL") + endpoint);
    }

    public static void delete(String endpoint) {
        ultimaResposta = given().when().delete(System.getenv("API_BASE_URL") + endpoint);
    }

    public static Map<String, Object> getUltimaResposta() {
        return ultimaResposta.jsonPath().getMap("$");
    }

    public static int getStatusCode() {
        return ultimaResposta.getStatusCode();
    }
}
