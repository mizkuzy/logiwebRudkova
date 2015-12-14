package ru.tsystems.logiweb.service.API;

import ru.tsystems.logiweb.entities.Request;

import java.util.List;

public interface RequestService extends GenericService<Request,Integer> {

    List<Request> findRequestsWithSpecialRout(String routeLabel);

}
