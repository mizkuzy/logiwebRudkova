package ru.tsystems.logiweb.service.API;

import ru.tsystems.logiweb.entities.Rout;

import javax.persistence.PersistenceException;
import java.util.List;

public interface RoutService extends GenericService<Rout, Integer> {

    Rout getByCities(String city1, String city2) throws PersistenceException;

    List<String> getCities();

    List<String> getCitiesForSPb();

    List<String> getCitiesForVelikyNovgorod();

    List<String> getCitiesForPskov();

    List<String> getCitiesForPetrozavodsk();

    List<String> getCitiesForArhangelsk();

    List<String> getCitiesForVologda();

    List<String> getCitiesForSiktivkar();

    List<String> getCitiesForNaryanMar();

    List<String> getCitiesForMurmansk();

    List<String> getCitiesForKaliningrad();

    List<String> getCitiesForCherepovec();
}
