package learn.solar.domain;

import learn.solar.data.DataException;
import learn.solar.data.PanelFileRepository;
import learn.solar.data.PanelRepository;
import learn.solar.models.Material;
import learn.solar.models.Panel;

import java.util.List;

public class PanelService {

    private PanelRepository repository;

    public PanelService(PanelRepository repository) {
        this.repository = repository;
    }

    public List<Panel> findBySection(String section) throws DataException {
        return repository.findBySection(section);
    }

    public PanelResult add(Panel p) throws DataException {
        PanelResult result = validate(p);
        if (!result.isSuccess()) {
            return result;
        }
        result.setPanel(p);
        repository.add(p);
        return result;
    }

    public PanelResult update(Panel p) throws DataException {
        PanelResult result = validate(p);
        if(!result.isSuccess()){
            return  result;
        }
        if (!repository.update(p)) {
            result.addMessage("Failed to update Panel id: " + p.getId());
            return result;
        }
        if(result.isSuccess()){
            result.setPanel(p);
        }else {
            String message = String.format("Panel id %s was not found.", p.getId());
            result.addMessage(message);
        }
        return  result;
    }

    public PanelResult deleteById(int Id) throws DataException {
        PanelResult result = new PanelResult();
        if (!repository.deleteById(Id)) {
            result.addMessage("Failed to delete panel with id"+ Id);
            return result;
        }else {
            repository.deleteById(Id);
        }
         return result;
    }// general purpose validation

    private PanelResult validate(Panel p) {
        PanelResult result = new PanelResult();
        if(p== null){
            result.addMessage("Panel cannot be null.");
            return result;
        }
        if (p.getSection().isBlank()) {
            result.addMessage("Section name cannot be blank.");
            return result;
        }
        if (p.getId() <= 0 || p.getId() >= 250) {
            result.addMessage("ID invalid to place in solar panel array. ");
            return result;
        }
        if (p.getRow() <= 0 || p.getRow() >= 250) {
            result.addMessage(String.format("Row ", p.getRow(), " out of range"));
            return result;
        }
        if (p.getColumn() <= 0 || p.getColumn() >= 250) {
            result.addMessage(String.format("Column ", p.getColumn(), " out of range"));
            return result;
        }
        if (p.getInstallationYear() > 2022) {
            result.addMessage("Year cannot be in the future. ");
        }
        if (p.getMaterial() != Material.AMORPHOUS_SILICON
                && p.getMaterial() != Material.CADMIUM_TELLURIDE
                && p.getMaterial() != Material.COPPER_IRIDIUM_GALLIUM_SELENIDE
                && p.getMaterial() != Material.MONOCRYSTALINE_SILICON
                && p.getMaterial() != Material.MULTICRYSTALLINE_SILICON
                && p.getMaterial() == null) {
            result.addMessage(String.format("Material must be one of the required materials"));
            return result;
        }
        return result;
    }
}
