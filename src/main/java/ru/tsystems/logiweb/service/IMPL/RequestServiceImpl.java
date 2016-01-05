package ru.tsystems.logiweb.service.IMPL;

import org.apache.log4j.Logger;
import ru.tsystems.logiweb.dao.API.RequestGenericDAO;
import ru.tsystems.logiweb.entities.*;
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

    /**
     * This constant needs to compare hours from requests.
     * This number is arbitrary, but bigger then all possible values hours in routs.
     */
    private static final int HOURS = 100;
    private Logger logger = Logger.getLogger(RequestServiceImpl.class);

    @Autowired
    private RequestGenericDAO requestDAO;
    private int requestHoursForYellowRout;

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
        logger.info("Adding of new request is finished.ID Number of request: " + request.getIdRequest());
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

        time += countHours(requests);
        logger.info("Total time=" + time);
        return time;
    }

    /**
     * Sums hours for yellow rout.
     *
     * @param requests
     * @return hours.
     */
    private int countHours(List<Request> requests) {

        String routLabel = requests.get(0).getRoutForRequest().getRouteLabelFK().getLabel();

        int timeToFirstCity1 = addHoursTo(requests, routLabel);
        logger.info("timeToFirstCity1=" + timeToFirstCity1);
        int timeFromLastCity2 = addHoursFrom(requests, routLabel);
        logger.info("timeFromLastCity2" + timeFromLastCity2);
        return timeToFirstCity1 + timeFromLastCity2;
    }


    /**
     * Counts hours need to reach first city on the yellow rout of order from list of request.
     *
     * @param requests
     * @param routLabel
     * @return hours
     */
    private int addHoursTo(List<Request> requests, String routLabel) {

        int hours = HOURS;
        int requestHours = 0;

        for (Request r : requests) {

            String city1 = r.getRoutForRequest().getCity1();

            if (city1.equals("Saint-Petersburg")) {
                logger.info("First city is Saint-Petersburg");
                return 0;
            }

            switch (routLabel) {
                case "yellow":
                    requestHours = getRequestHoursForYellowRout(city1);
                    break;
                case "green":
                    requestHours = getRequestHoursForGreenRout(city1);
                    break;
                case "purple":
                    requestHours = getRequestHoursForPurpleRout(city1);
                    break;
                case "blue":
                    requestHours = getRequestHoursForBlueRout(city1);
            }

            if (requestHours < hours) {
                hours = requestHours;
            }
        }

        return hours;
    }

    /**
     * Counts hours need to reach Saint-Petersburg from the last city on the yellow rout of order from list of request.
     *
     * @param requests
     * @param routLabel
     * @return hours
     */
    private int addHoursFrom(List<Request> requests, String routLabel) {

        int hours = HOURS;
        int requestHours = 0;

        for (Request r : requests) {

            String city2 = r.getRoutForRequest().getCity2();

            if (city2.equals("Saint-Petersburg")) {
                logger.info("Last city is Saint-Petersburg");
                return 0;
            }

            switch (routLabel) {
                case "yellow":
                    requestHours = getRequestHoursForYellowRout(city2);
                    break;
                case "green":
                    requestHours = getRequestHoursForGreenRout(city2);
                    break;
                case "purple":
                    requestHours = getRequestHoursForPurpleRout(city2);
                    break;
                case "blue":
                    requestHours = getRequestHoursForBlueRout(city2);
            }

            if (requestHours < hours) {
                hours = requestHours;
            }
        }

        return hours;
    }


    public int getRequestHoursForYellowRout(String city) {

        switch (city) {
            case "Veliky_Novgorod":
                return 3;

            case "Pskov":
                return 5;

            case "Kaliningrad":
                return 14;
        }
        //this line will be never reach if all cities are correct
        return 0;
    }

    public int getRequestHoursForGreenRout(String city) {

        switch (city) {
            case "Petrozavodsk":
                return 5;

            case "Murmansk":
                return 17;

        }
        //this line will be never reach if all cities are correct
        return 0;
    }

    public int getRequestHoursForPurpleRout(String city) {

        switch (city) {
            case "Cherepovec":
                return 6;

            case "Arhangelsk":
                return 18;

            case "Naryan-Mar":
                return 45;

        }
        //this line will be never reach if all cities are correct
        return 0;
    }

    public int getRequestHoursForBlueRout(String city) {

        switch (city) {
            case "Vologda":
                return 8;

            case "Siktivkar":
                return 19;
        }
        //this line will be never reach if all cities are correct
        return 0;
    }

    /**
     * Changes requests statuses to FINISH.
     *
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
