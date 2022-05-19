package baseAPI;

import io.restassured.response.Response;
import org.json.JSONArray;
import org.json.JSONObject;

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

    public static void getInfoCharacter(String id) {
        Response getCharacter = given()
                .header("Content-type", "application/json")
                .baseUri("https://rickandmortyapi.com/api")
                .when()
                .get("/character/" + id)
                .then()
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
    public static void getInfoEpisode(String episodUri){
        Response getEpisode = given()
                .header("Content-type", "application/json")
                .baseUri(episodUri)
                .when()
                .get()
                .then()
                .extract()
                .response();
        arr_2 = new JSONObject(getEpisode.getBody().asString()).getJSONArray("characters");
        uriCharacter_2 = arr_2.get(arr_2.length() - 1).toString();
        idCharacter_2 = uriCharacter_2.replaceAll("\\D+", "");
        System.out.println("Идентификатор последнего персонажа в эпизоде: " + idCharacter_2);
        System.out.println();
    }

    public static void main(String[] args) {
        getInfoCharacter("2");
        getInfoEpisode(lastEpisodeWithMortySmith);
        getInfoCharacter(idCharacter_2);
    }
}
