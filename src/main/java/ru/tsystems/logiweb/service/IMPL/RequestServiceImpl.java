package ru.tsystems.logiweb.service.IMPL;

import ru.tsystems.logiweb.dao.API.RequestGenericDAO;
import ru.tsystems.logiweb.entities.Request;
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
     * Get list of required ru.tsystems.logiweb.entities.
     *
     * @return list of ru.tsystems.logiweb.entities
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
        ArrayList<Request> requests = (ArrayList<Request>) requestDAO.getAll();
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


}
