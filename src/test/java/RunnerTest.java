import com.codeborne.selenide.logevents.SelenideLogger;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import io.qameta.allure.restassured.AllureRestAssured;
import io.qameta.allure.selenide.AllureSelenide;
import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/feature",
        glue = {"StepCucumber"},
        plugin = {"pretty"},
        tags = "@TEST"
)

public class RunnerTest {
    @BeforeClass
    public static void before(){
        RestAssured.filters(new AllureRestAssured()) ;
        SelenideLogger.addListener("AllureSelenide",
                new AllureSelenide().
                        screenshots(true).savePageSource(false));
    }
}
