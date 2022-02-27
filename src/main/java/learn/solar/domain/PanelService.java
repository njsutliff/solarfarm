package learn.solar.domain;

import learn.solar.data.DataException;
import learn.solar.data.PanelFileRepository;
import learn.solar.data.PanelRepository;
import learn.solar.models.Panel;

import java.util.List;

public class PanelService {

    private PanelRepository repository;

    public PanelService(PanelRepository repository){
        this.repository = repository;
    }
    public List<Panel> findBySection(String section) throws DataException {
        return repository.findBySection(section);
    }

    public  PanelResult add(Panel p) throws DataException{
        PanelResult result = validate(p);
        if(!result.isSuccess()){
            return result;
        }
        result.setPanel(p);
        repository.add(p);
        return  result;
    }
    public PanelResult update(Panel p) throws  DataException {
        PanelResult result = validate(p);
        if(p.getId()<=0 ||p.getId() >=250){
            result.addMessage("ID invalid to place in solar panel array. ");
            return result;
        }
        if(!repository.update(p)){
            result.addMessage(String.format("Failed to update Panel id row col: ", p.getId(), p.getRow(),p.getColumn()));
        }

        return  result;
    }
    public  PanelResult deleteById(int Id) throws  DataException {
        PanelResult result = new PanelResult();
        if(!repository.deleteById(Id)){
            result.addMessage(String.format("Failed to delete panel with id", Id));
            return result;
        }
        return  result;
    }
    private  PanelResult validate(Panel p) {
        PanelResult result = new PanelResult();
        return  result;
    }



}
