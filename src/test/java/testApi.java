import org.junit.Test;

import static baseAPI.baseStep.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class testApi {
    @Test
    public void firstTest(){
        getInfoCharacter("2");
        String temp_1 = characterSpecies;
        String temp_2 = characterLocation;
        getInfoEpisode(lastEpisodeWithMortySmith);
        getInfoCharacter(idCharacter_2);
        assertEquals("Раса не совпадает", temp_1, characterSpecies);
        assertEquals("Локация не совпадает", temp_2, characterLocation);
    }
}
