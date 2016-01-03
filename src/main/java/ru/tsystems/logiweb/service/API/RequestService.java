package ru.tsystems.logiweb.service.API;

import ru.tsystems.logiweb.entities.Good;
import ru.tsystems.logiweb.entities.Order;
import ru.tsystems.logiweb.entities.Request;
import ru.tsystems.logiweb.entities.Rout;

import java.util.List;

public interface RequestService extends GenericService<Request,Integer> {

    List<Request> findRequestsWithSpecialRout(String routeLabel);

    void addNewRequest(Good good, Rout rout);

    int getTotalTimeRequests(List<Request> currentRoutLabel);

    void changeRequestsStatuses(String currentRoutLabel);

    List<Request> breakLinks(Order order);

    void deleteSomeRequests(List<Request> requestsToDelete);

    List<Good> breakLinksWithGoods(List<Request> requestsToDelete);
}
