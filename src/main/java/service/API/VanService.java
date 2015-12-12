package service.API;

import entities.Van;

import java.util.List;

public interface VanService extends GenericService<Van,Integer>{
    List<Van> getAppropriateVans(String routLabelType);

}
