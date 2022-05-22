import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/feature",
        glue = {"StepCucumber"},
        plugin = {"pretty"},
        tags = "@TEST"
)

public class RunnerTest {
}
