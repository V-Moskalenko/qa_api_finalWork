package baseAPI;

import Utils.Configuration;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.given;

public class baseStep {
    public static String idCharacter;
    public static String nameCharacter;
    public static String characterSpecies;
    public static String characterLocation;
    public static JSONArray arr_1;
    public static JSONArray arr_2;
    public static String lastEpisodeWithMortySmith;
    public static String uriCharacter_2;
    public static String idCharacter_2;
    public static String idPotato;
    public static JSONObject arr_3;
    public static String newName;
    public static String newJob;

    @Step("Получение информации о персонаже под id {id}")
    public static void getInfoCharacter(String id) {
        Response getCharacter = given()
                .header("Content-type", "application/json")
                .baseUri(Configuration.getValue("Uri"))
                .when()
                .get(Configuration.getValue("endpoint") + id)
                .then()
                .statusCode(200)
                .extract()
                .response();

        idCharacter = new JSONObject(getCharacter.getBody().asString()).get("id").toString();
        nameCharacter = new JSONObject(getCharacter.getBody().asString()).get("name").toString();
        characterSpecies = new JSONObject(getCharacter.getBody().asString()).get("species").toString();
        characterLocation = new JSONObject(getCharacter.getBody().asString()).getJSONObject("location").get("name").toString();
        arr_1 = new JSONObject(getCharacter.getBody().asString()).getJSONArray("episode");
        lastEpisodeWithMortySmith = arr_1.get(arr_1.length() - 1).toString();
        System.out.println("Индентификатор персонажа " + nameCharacter + ": " + idCharacter);
        System.out.println("Раса персонажа " + nameCharacter + ": " + characterSpecies);
        System.out.println("Локация персонажа " + nameCharacter + ": " + characterLocation);
        System.out.println("Последний эпизод с " + nameCharacter + " : " + lastEpisodeWithMortySmith);
        System.out.println();
    }
    @Step("Получение информации о последнем эпизоде по полученному URI {episodUri}")
    public static void getInfoEpisode(String episodUri){
        Response getEpisode = given()
                .header("Content-type", "application/json")
                .baseUri(episodUri)
                .when()
                .get()
                .then()
                .statusCode(200)
                .extract()
                .response();
        arr_2 = new JSONObject(getEpisode.getBody().asString()).getJSONArray("characters");
        uriCharacter_2 = arr_2.get(arr_2.length() - 1).toString();
        idCharacter_2 = uriCharacter_2.replaceAll("\\D+", "");
        System.out.println("Идентификатор последнего персонажа в эпизоде: " + idCharacter_2);
        System.out.println();
    }

    @Step("POST объекта по JSON файлу 1 и PUT объекта по JSON файлу 2")
    public static void createPotato() throws IOException {
        JSONObject data = new JSONObject(new String(Files.readAllBytes(Paths.get("src/test/resources/json/1.json"))));
        Response postPotato = given()
                .header("Content-type", "application/json")
                .body(data.toString())
                .baseUri("https://reqres.in/api")
                .when()
                .post("/users")
                .then()
                .statusCode(201)
                .extract()
                .response();
        idPotato = new JSONObject(postPotato.getBody().asString()).get("id").toString();

        JSONObject data_2 = new JSONObject(new String(Files.readAllBytes(Paths.get("src/test/resources/json/2.json"))));
        Response updatePotato = given()
                .header("Content-type", "application/json")
                .body(data_2.toString())
                .baseUri(Configuration.getValue("Uri2"))
                .when()
                .put("/users/" + idPotato)
                .then()
                .statusCode(200)
                .extract()
                .response();
        arr_3 = new JSONObject(updatePotato.getBody().asString());
        newName = arr_3.get("name").toString();
        newJob = arr_3.get("job").toString();
        System.out.println(arr_3);
    }

    public static void main(String[] args) throws IOException {
        getInfoCharacter("2");
        getInfoEpisode(lastEpisodeWithMortySmith);
        getInfoCharacter(idCharacter_2);
        createPotato();
    }
}
