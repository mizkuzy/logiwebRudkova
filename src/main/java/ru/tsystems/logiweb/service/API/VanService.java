package ru.tsystems.logiweb.service.API;

import ru.tsystems.logiweb.entities.Van;

import java.util.List;

public interface VanService extends GenericService<Van,Integer>{
    List<Van> getAppropriateVans(String routLabelType);

}
