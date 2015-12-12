package service.IMPL;

import dao.API.RequestGenericDAO;
import dao.IMPL.RequestGenericDAOImpl;
import entities.Request;
import entities.statusesAndStates.RequestStatus;
import service.API.RequestService;

import java.util.ArrayList;
import java.util.List;

public class RequestServiceImpl implements RequestService {

    private RequestGenericDAO requestDAO = new RequestGenericDAOImpl();

    @Override
    public void create(Request entity) {
        requestDAO.create(entity);
    }

    @Override
    public Request read(Integer id) {
        return requestDAO.read(id);
    }

    @Override
    public void update(Request entity) {
        requestDAO.update(entity);
    }

    @Override
    public void delete(Request entity) {
        requestDAO.delete(entity);
    }

    @Override
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
