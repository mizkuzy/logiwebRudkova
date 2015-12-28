package ru.tsystems.logiweb.service.IMPL;

import org.apache.log4j.Logger;
import ru.tsystems.logiweb.dao.API.RequestGenericDAO;
import ru.tsystems.logiweb.entities.Good;
import ru.tsystems.logiweb.entities.Request;
import ru.tsystems.logiweb.entities.Rout;
import ru.tsystems.logiweb.entities.statusesAndStates.RequestStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.tsystems.logiweb.service.API.RequestService;

import java.util.ArrayList;
import java.util.List;

//TODO Герман. сервисами и ДАО лучше всё покрывать или только то, что используется. Или лучше добавлять по ходу? Нужно ли удалить сейчас то, что не используется?

/**
 * An implementation of RequestService API.
 */
@Service("requestService")
public class RequestServiceImpl implements RequestService {

    private Logger logger = Logger.getLogger(RequestServiceImpl.class);

    @Autowired
    private RequestGenericDAO requestDAO;

    /**
     * Create required entity.
     *
     * @param entity
     */
    @Override
    @Transactional
    public void create(Request entity) {
        requestDAO.create(entity);
    }

    /**
     * Read required entity.
     *
     * @param id
     * @return
     */
    @Override
    @Transactional
    public Request read(Integer id) {
        return requestDAO.read(id);
    }

    /**
     * Update required entity.
     *
     * @param entity
     */
    @Override
    @Transactional
    public void update(Request entity) {
        requestDAO.update(entity);
    }

    /**
     * Delete required entity.
     *
     * @param entity
     */
    @Override
    @Transactional
    public void delete(Request entity) {
        requestDAO.delete(entity);
    }

    /**
     * Get list of required entities.
     *
     * @return list of entities
     */
    @Override
    @Transactional
    public List getAll() {
        return requestDAO.getAll();
    }

    /**
     * Finds id of requests with special RouteLabel.
     *
     * @param routeLabelSpecial
     * @return specialRequests
     */
    @Override
    public List<Request> findRequestsWithSpecialRout(String routeLabelSpecial) {
        List<Request> requests = getAll();
        //System.err.println("requests= " + requests);
        ArrayList<Request> specialRequests = new ArrayList<>();
        //System.err.println("specialRequests= " + specialRequests);
        if (requests != null) {
            for (Request request : requests) {
                if ((request.getRoutForRequest().getRouteLabelFK().getLabel().equals(routeLabelSpecial)) & (request.getStatusRequest().equals(RequestStatus.NO))) {
                    specialRequests.add(request);
                }
            }
        }
        return specialRequests;
    }

    @Override
    @Transactional
    public void addNewRequest(Good good, Rout rout) {
        Request request = new Request();
        request.setGoodForRequest(good);
        request.setRoutForRequest(rout);
        request.setStatusRequest(RequestStatus.NO);
        create(request);//TODO EXCEPTION?
        logger.info("Adding of new request is finished. Number of request: " + request.getIdRequest());
    }

    @Override
    public int getTotalRequestsAmount(List<Request> requests) {

        int time = 0;

        if (requests != null) {
            for (Request r : requests) {
                time += r.getRoutForRequest().getTimeDistance();
            }
        }

        return time;
    }

    @Override
    @Transactional
    public void changeRequestsStatuses(String routLabel) {
        List<Request> requests = findRequestsWithSpecialRout(routLabel);
        if (requests != null) {
            for (Request r : requests) {
                r.setStatusRequest(RequestStatus.FINISHED);
                update(r);
            }
        }
    }
}
