package ru.tsystems.logiweb.service.API;

import ru.tsystems.logiweb.entities.Good;
import ru.tsystems.logiweb.entities.Request;
import ru.tsystems.logiweb.entities.Rout;

import java.util.List;

public interface RequestService extends GenericService<Request,Integer> {

    List<Request> findRequestsWithSpecialRout(String routeLabel);

    void addNewRequest(Good good, Rout rout);
}
