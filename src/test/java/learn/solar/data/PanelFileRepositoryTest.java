package learn.solar.data;

import learn.solar.models.Material;
import learn.solar.models.Panel;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.xml.crypto.Data;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PanelFileRepositoryTest {
    static final String SEED_FILE_PATH = "./data/panel-seed.csv";
    static final String TEST_FILE_PATH = "./data/panel-test.csv";

    PanelFileRepository panelRepository = new PanelFileRepository(TEST_FILE_PATH);


    @BeforeAll
    static void setupTest() throws DataException {
        Path seedPath = Paths.get(SEED_FILE_PATH);
        Path testPath = Paths.get(TEST_FILE_PATH);

        try {
            Files.copy(seedPath, testPath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw  new DataException(e.getMessage(),e);
        }
    }



    @Test
    void findBySection() throws DataException {
        List<Panel> p = panelRepository.findBySection("test-section");
        assertEquals("test-section", p.get(0).getSection());
    }

    @Test
    void ShouldAdd() throws DataException {
        Panel p = new Panel(1,"test-section",1, 1,2014, Material.AMORPHOUS_SILICON,false);
        panelRepository.add(p);
        boolean test = panelRepository.findAll().contains(p);
        assertEquals(test,true);
    }

    @Test
    void update() {
    }

    @Test
    void deleteById() {
    }

    @Test
    void findAll() throws DataException {
    List<Panel> panels = panelRepository.findAll();


    }
}