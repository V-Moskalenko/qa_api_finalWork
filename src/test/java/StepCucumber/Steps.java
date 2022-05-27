package StepCucumber;

import io.cucumber.java.ru.И;
import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.То;
import io.restassured.response.Response;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Steps {

    public static String idCharacter;
    public static String nameCharacter;
    public static String characterSpecies;
    public static String characterLocation;
    public static JSONArray arr_1;
    public static JSONArray arr_2;
    public static String lastEpisodeWithMortySmith;
    public static String uriCharacter_2;
    public static String idCharacter_2;
    public static String temp_1;
    public static String temp_2;
    public static String idPotato;
    public static JSONObject arr_3;
    public static String newName;
    public static String newJob;

    //Сценарий: 001 Тестирование API Рик и Морти
    @Когда("^переходим на Uri, со следующим endpoint и указываем id персонажа$")
    public static void test_1(List<String> testdata) {
        Response getCharacter = given()
                .header("Content-type", "application/json")
                .baseUri(testdata.get(0))
                .when()
                .get(testdata.get(1) + testdata.get(2))
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

    @И("^получив информацию о персонаже, получаем информацию о последнем его эпизоде$")
    public void Test_2 () {
        Response getEpisode = given()
                .header("Content-type", "application/json")
                .header("charset", "UTF-8")
                .baseUri(lastEpisodeWithMortySmith)
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

    @То("^можем получить информацию о последнем персонаже, в списке последнего эпизода$")
    public void Test_3 () {
        temp_1 = characterSpecies;
        temp_2 = characterLocation;
        Response getCharacter = given()
                .header("Content-type", "application/json")
                .baseUri("https://rickandmortyapi.com/api")
                .when()
                .get("/character/" + idCharacter_2)
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

    @И("^проверить совпадение расы и локации персонажей$")
    public void Test_4 () {
        assertEquals(temp_1, characterSpecies, "Раса не совпадает");
        assertEquals(temp_2, characterLocation, "Локация не совпадает");
    }

    @Когда("^создаем элемент с именем Potato$")
    public void Test_5 () throws IOException {
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
    }

    @И("^обновляем его имя на Tomato и работу на Eat maket$")
    public void Test_6 () throws IOException {
        JSONObject data_2 = new JSONObject(new String(Files.readAllBytes(Paths.get("src/test/resources/json/2.json"))));
        Response updatePotato = given()
                .header("Content-type", "application/json")
                .body(data_2.toString())
                .baseUri("https://reqres.in/api")
                .when()
                .put("/users/" + idPotato)
                .then()
                .statusCode(200)
                .extract()
                .response();
        arr_3 = new JSONObject(updatePotato.getBody().asString());
        newName = arr_3.get("name").toString();
        newJob = arr_3.get("job").toString();
    }
    @То("^можем проверить валидные данные$")
    public void Test_7 () {
        assertEquals("Tomato", newName);
        assertEquals("Eat maket", newJob);
    }
}
