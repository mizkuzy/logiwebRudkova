package dao.API;

import entities.Van;

public interface VanGenericDAO extends GenericDAO<Van, Integer> {
    Van getByNumber(String name);
}
