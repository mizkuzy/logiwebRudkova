package ru.tsystems.logiweb.service.IMPL;

import org.apache.log4j.Logger;
import ru.tsystems.logiweb.dao.API.RequestGenericDAO;
import ru.tsystems.logiweb.entities.Good;
import ru.tsystems.logiweb.entities.Order;
import ru.tsystems.logiweb.entities.Request;
import ru.tsystems.logiweb.entities.Rout;
import ru.tsystems.logiweb.entities.statusesAndStates.RequestStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.tsystems.logiweb.service.API.RequestService;

import java.util.ArrayList;
import java.util.List;


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

    /**
     * Counts total time requests.
     *
     * @param requests
     * @return time
     */
    @Override
    public int getTotalTimeRequests(List<Request> requests) {

        int time = 0;

        if (requests != null) {
            for (Request r : requests) {
                time += r.getRoutForRequest().getTimeDistance();
            }
        }

        return time;
    }

    /**
     * Changes requests statuses to FINISH.
     * @param routLabel
     */
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

    /**
     * Sets link to order to null and delete requests which were related
     * with order.
     *
     * @param order
     */
    @Override
    @Transactional
    public List<Request> breakLinks(Order order) {
        List<Request> requests = getAll();
        List<Request> requestsToDelete = new ArrayList<>(order.getRequests().size());

        logger.info("Incoming order: " + order.getNumber());

        if (requests != null) {
            for (Request r : requests) {
                Order currentOrder = r.getCurrentOrder();
                if (currentOrder != null) {
                    if (currentOrder.getNumber().equals(order.getNumber())) {
                        logger.info("Founded request " + r.getIdRequest() + " with orderNumber: " + r.getCurrentOrder().getNumber());
                        r.setCurrentOrder(null);
                        requestsToDelete.add(r);
                        update(r);
                    }
                }
            }
        }
        logger.info("All links with order " + order.getNumber() + " were deleted");
        return requestsToDelete;
    }

    /**
     * Deletes requests.
     *
     * @param requestsToDelete
     */
    @Override
    @Transactional
    public void deleteSomeRequests(List<Request> requestsToDelete) {
        for (Request r : requestsToDelete) {
            logger.info("Request " + r.getIdRequest() + " is going to be deleted.");
            delete(r);
        }
        logger.info("All requests mentioned above were deleted");
    }

    /**
     * Deletes links between chosen request and relative good.
     * Then return list of goods which have to be deleted.
     *
     * @param requestsToDelete
     * @return list of goods
     */
    @Override
    @Transactional
    public List<Good> breakLinksWithGoods(List<Request> requestsToDelete) {
        List<Good> goodsToDelete = new ArrayList<>(requestsToDelete.size());
        if (requestsToDelete != null) {
            for (Request r : requestsToDelete) {
                logger.info(r.getGoodForRequest().getName() + " is added to list to be deleted soon.");
                goodsToDelete.add(r.getGoodForRequest());
                r.setGoodForRequest(null);
                update(r);
            }
            logger.info("All goods mentioned above were deleted");
        }
        return goodsToDelete;
    }
}
