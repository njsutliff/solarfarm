package learn.solar.data;

import learn.solar.models.Panel;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PanelFileRepository {

    private String filePath;

    public PanelFileRepository(String filePath) {
        this.filePath = filePath;
    }

    /**
     * finds all Panels in a section, uses the private findAll method
     * @param section to find Panels in
     * @return list of all Panels in a section
     */
    public List<Panel> findBySection(String section) {

    }

    /**
     * create a Panel
     * @return Panel created
     */
    public Panel add(Panel p) {

    }

    /**
     *update a Panel
     * @return Panel updated
     */
    public boolean update(Panel p) {

    }

    /**
     * Delete a panel by Id
     * @param Id to delete panel
     * @return true if deleted else false
     */
    public boolean deleteById(int Id) {

    }

    /**
     * finds all Panels in the data source (file),
     * @return List of all Panels in file
     */
    private List<Panel> findAll() throws DataException {

        ArrayList<Panel> result = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            reader.readLine(); // skip header
            for (String line = reader.readLine(); line != null; line = reader.readLine()) {
                Panel panel = deserialize(line);
                if (panel != null) {
                    result.add(panel);
                }
            }
        }
        catch (IOException ex) {
            throw new DataException(ex.getMessage(), ex);
        }

        return result;
    }

    /**
     * Convert a Panel into a String in the file.
     * @return String of the Panel
     */
    private String serialize(Panel p) {

    }

    /**
     * Convert a String into a Panel.
     * @return
     */
    private Panel deserialize(String s) {
        String[] fields = line.split(DELIMITER, -1);
        if (fields.length == 5) {
            Panel panel = new Panel();
            panel.setEncounterId(Integer.parseInt(fields[0]));
            panel.setType(EncounterType.valueOf(fields[1]));
            panel.setWhen(restore(fields[2]));
            panel.setDescription(restore(fields[3]));
            panel.setOccurrences(Integer.parseInt(fields[4]));
            return panel;
        }
        return null;
    }
}
