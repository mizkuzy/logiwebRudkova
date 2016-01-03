package ru.tsystems.logiweb.service.API;

import ru.tsystems.logiweb.entities.Good;

import java.util.List;

public interface GoodService extends GenericService<Good,Integer> {

    Integer addNewGood(String goodsName, Integer mass);

    void deleteSomeGoods(List<Good> goodsToDelete);
}
