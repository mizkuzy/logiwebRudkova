package service.API;

import entities.Request;

import java.util.List;

public interface RequestService extends GenericService<Request,Integer> {

    List<Request> findRequestsWithSpecialRout(String routeLabel);

}
