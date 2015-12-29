package ru.tsystems.logiweb.service.API;

import ru.tsystems.logiweb.entities.RouteLabel;

public interface RoutLabelService extends GenericService<RouteLabel,Integer> {

    RouteLabel getByName(String routLabel);

}
