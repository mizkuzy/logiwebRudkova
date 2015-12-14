package ru.tsystems.logiweb.dao.API;

import ru.tsystems.logiweb.entities.Van;

public interface VanGenericDAO extends GenericDAO<Van, Integer> {
    Van getByNumber(String name);
}
