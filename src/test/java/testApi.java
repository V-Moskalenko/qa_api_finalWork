import Utils.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.*;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;

import java.io.IOException;

import static StepJunit.baseStep.*;
import static org.junit.Assert.assertEquals;

/**
 * Класс для запуска тестов JUnit
 */
public class testApi {
    @BeforeEach
    public void setFormat() {
        SelenideLogger.addListener("AllureSelenide",
                new AllureSelenide().
                        screenshots(true).savePageSource(false));
    }

    @Epic(value = "Тест API")
    @Feature(value = "Получение и сравнение данных")
    @Description("Получение данных по заданному id персонажа, получение его локации, поиск последнего эпизода с ним и сравнение расы и локации последнего персонажа в этом эпизоде")
    @Owner(value = "Москаленко Вадим")
    @Test
    @Tag("1")
    @DisplayName("API Рик и Морти")
    public void firstTest() throws IOException {
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
    @Description("Создание данных по первому json файлу и обновление их по второму json файлу")
    @Owner(value = "Москаленко Вадим")
    @Test
    @Tag("2")
    @DisplayName("API reqres.in")
    public void secondTest() throws IOException {
        createPotato();
        assertEquals("Tomato", newName);
        assertEquals("Eat maket", newJob);
    }
}
