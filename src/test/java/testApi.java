import Utils.Configuration;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import java.io.IOException;

import static baseAPI.baseStep.*;
import static org.junit.Assert.assertEquals;

public class testApi {
    @Epic(value = "Тест API")
    @Feature(value = "Получение и сравнение данных")
    @Test
    @DisplayName("API Рик и Морти")
    public void firstTest(){
        getInfoCharacter(Configuration.getValue("id"));
        String temp_1 = characterSpecies;
        String temp_2 = characterLocation;
        getInfoEpisode(lastEpisodeWithMortySmith);
        getInfoCharacter(idCharacter_2);
        assertEquals("Раса не совпадает", temp_1, characterSpecies);
        assertEquals("Локация не совпадает", temp_2, characterLocation);
    }

    @Epic(value = "Тест API")
    @Feature(value = "POST и PUT данных")
    @Test
    @DisplayName("API reqres.in")
    public void secondTest() throws IOException {
        createPotato();
        assertEquals("Tomato", newName);
        assertEquals("Eat maket", newJob);
    }


}
