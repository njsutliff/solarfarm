package learn.solar.data;

import learn.solar.models.Material;
import learn.solar.models.Panel;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PanelFileRepository {
    private static final String DELIMITER = ",";
    private final String filePath;
    private static final String HEADER = "id,section,row,column,installationYear,material,tracking?";


    public PanelFileRepository(String filePath) {
        this.filePath = filePath;
    }

    /**
     * finds all Panels in the data source (file),
     * @return List of all Panels in file
     */
    public List<Panel> findAll() throws DataException {

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
     * finds all Panels in a section, uses the private findAll method
     * @param section to find Panels in
     * @return list of all Panels in a section
     */
    public List<Panel> findBySection(String section) throws DataException {
        ArrayList<Panel> sectionList = new ArrayList<Panel>();
        List<Panel> panel = findAll();
            for (Panel p : panel){
                if (Objects.equals(p.getSection(), section)){
                    sectionList.add(p);
                }
            }

        return sectionList;
    }

    /**
     * create a Panel
     * @return Panel created
     */
    public Panel add(Panel p) throws DataException {
        List<Panel> all = findBySection(p.getSection());

        all.add(p);
        writeAll(all);
        return p;
    }

    private void writeAll(List<Panel> panels) throws DataException {
        try (PrintWriter writer = new PrintWriter(filePath)) {
            writer.println(HEADER);
            for (Panel p : panels) {
                writer.println(serialize(p));
            }
        } catch (IOException ex) {
            throw new DataException(ex.getMessage(), ex);
        }
    }

    /**
     *update a Panel
     * @return Panel updated
     */
    public boolean update(Panel p) {
        return  false;
    }

    /**
     * Delete a panel by Id
     * @param Id to delete panel
     * @return true if deleted else false
     */
    public boolean deleteById(int Id) {
        return false;
    }



    /**
     * Convert a Panel into a String in the file.
     * @return String of the Panel
     */
    private String serialize(Panel p) {
        return String.format("%s,%s,%s,%s,%s,%s,%s",
                p.getId(),
                p.getSection(),
                p.getRow(),
                p.getColumn(),
                p.getInstallationYear(),
                p.getMaterial(),
                p.isTracking());
    }

    /**
     * Convert a String into a Panel.
     * @return
     */
    private Panel deserialize(String s) {
        String[] fields = s.split(DELIMITER, -1);
        if (fields.length == 7) {
            Panel panel = new Panel();
            panel.setId(Integer.parseInt(fields[0]));
            panel.setSection(fields[1]);
            panel.setRow(Integer.parseInt(fields[2]));
            panel.setColumn(Integer.parseInt((fields[3])));
            panel.setInstallationYear(Integer.parseInt(fields[4]));
            panel.setMaterial(Material.valueOf(fields[5]));
            panel.setTracking(Boolean.parseBoolean(fields[6]));
            return panel;
        }
        return null;
    }
}
