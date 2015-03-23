/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.boha.minisass.util;

import com.boha.minisass.data.Errorstore;
import com.boha.minisass.transfer.RequestDTO;
import com.boha.minisass.transfer.ResponseDTO;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author CodeTribe1
 */
@Stateless
public class TrafficCop {

    @EJB
    DataUtil dataUtil;

    @EJB
    ListUtil listUtil;

    public ResponseDTO processRequest(RequestDTO req,
            DataUtil dataUtil, ListUtil listUtil) {
        long start = System.currentTimeMillis();
        ResponseDTO ur = new ResponseDTO();
        try {
            switch (req.getRequestType()) {
                case RequestDTO.REGISTER_TEAM:
                    ur = dataUtil.registerTeam(req.getTeam());
                    break;
                case RequestDTO.REGISTER_TEAM_MEMBER:
                    ur = dataUtil.registerTeamMember(req.getTeamMember());
                    break;
                case RequestDTO.ADD_COMMENT:
                    ur = dataUtil.addComment(req.getComment());
                    break;
                case RequestDTO.ADD_COUNTRY:
                    ur = dataUtil.addCountry(req.getCountry());
                    break;
                case RequestDTO.ADD_EVALUATION:
                    ur = dataUtil.addEvaluation(req.getEvaluation(), req.getInsectImages());
                    break;
                case RequestDTO.ADD_EVALUATION_SITE:
                    ur = dataUtil.addEvaluationSite(req.getEvaluationSite());
                    break;
                case RequestDTO.ADD_INSECT_IMAGE:
                    ur = dataUtil.addInsertImage(req.getInsectImage());
                    break;
                case RequestDTO.ADD_PROVINCE:
                    ur = dataUtil.addProvince(req.getProvince());
                    break;
                case RequestDTO.ADD_RIVER:
                    //  ur = dataUtil.
                    break;
                case RequestDTO.ADD_RIVER_TOWN:
                    ur = dataUtil.addRiverTown(req.getRiverTown());
                    break;
                case RequestDTO.ADD_TOWN:
                    ur = dataUtil.addTown(req.getTown());
                    break;
                case RequestDTO.LIST_EVALUATION_SITES:
                    ur = listUtil.getEvaluationList();
                    break;
                case RequestDTO.LIST_INSECTS:
                    ur = listUtil.getInsectList();
                    break;
                case RequestDTO.LIST_RIVER_TOWNS:
                    ur = listUtil.getRiverTownList(req.getRiverTown().getRiverTownID());
                    break;
                case RequestDTO.LIST_TEAMS:
                    ur = listUtil.getTeamList(req.getTeam().getTeamID());
                    break;
                case RequestDTO.LIST_RIVERS_IN_COUNTRY:
                    ur = listUtil.getRiverInCountry(req.getCountryID());
                    break;

                case RequestDTO.ADD_EVALUATION_INSECT:
                    ur = dataUtil.addEvaluationInsect(req.getEvaluationInsect());
                    break;
                case RequestDTO.UPDATE_TOWN:
                    ur = dataUtil.updateTown(req.getTown());
                    break;
                case RequestDTO.UPDATE_TEAM:
                    ur = dataUtil.updateTeam(req.getTeam());
                    break;
                case RequestDTO.UPDATE_TEAM_MEMBER:
                    ur = dataUtil.updateTeamMember(req.getTeamMember());
                    break;
                case RequestDTO.UPDATE_COMMENT:
                    ur = dataUtil.updateComment(req.getComment());
                    break;
                case RequestDTO.UPDATE_CATEGORY:
                    ur = dataUtil.updateCategory(req.getCategory());
                    break;
                case RequestDTO.UPDATE_CONDITIONS:
                    ur = dataUtil.updateConditions(req.getConditions());
                    break;
                case RequestDTO.UPDATE_EVALUATION_IMAGE:
                    ur = dataUtil.updateEvaluationImage(req.getEvaluationImage());
                    break;

                case RequestDTO.LIST_EVALUATION_BY_TEAM_MEMBER:
                    ur = listUtil.getEvaluationByTeamMember(req.getTeamMemberID());
                    break;
                case RequestDTO.LIST_EVALUATION_BY_CONDITIONS:
                    ur = listUtil.getEvaluationByCondtions(req.getConditionsID());
                    break;
                case RequestDTO.LIST_EVALUATION_SITE_BY_CATEGORY:
                    ur = listUtil.getEvaluationSiteByCategory(req.getCategoryID());
                    break;
                case RequestDTO.LIST_EVALUATION_INSECT_BY_EVALUATION:
                    ur = listUtil.getEvaluationInsectByEvaluation(req.getEvaluationID());
                    break;
                case RequestDTO.LIST_TEAMS_BY_TOWN:
                    ur = listUtil.getTeamByTown(req.getTownID());
                    break;
                case RequestDTO.LIST_TEAM_MEMBERS:
                    ur = listUtil.getTeamMemberList();
                    break;
                case RequestDTO.LIST_TOWN_BY_PROVINCE:
                    ur = listUtil.getTownByProvince(req.getProvinceID());
                    break;
                case RequestDTO.LIST_CATEGORY:
                    ur = listUtil.getCategoryList();
                    break;
                case RequestDTO.LIST_COMMENTS:
                    ur = listUtil.getCommentList();
                    break;
                case RequestDTO.LIST_COUNTRYS:
                    ur = listUtil.getCountryList();
                    break;
                case RequestDTO.LIST_EVALUATIONS:
                    ur = listUtil.getEvaluationList();
                    break;
                case RequestDTO.LIST_RIVERS:
                    ur = listUtil.getRiverList();
                    break;
                case RequestDTO.GET_DATA:
                    ur = listUtil.getData(req.getCountryCode());
                    break;

                case RequestDTO.LIST_ALL_RIVER_TOWNS:
                    ur = listUtil.listRiverTownList();
                    break;
                case RequestDTO.LIST_ALL_TOWNS_BY_COUNTRY:
                    ur = listUtil.registrationData(req.getCountryCode());
                    break;
                default:
                    ur.setStatusCode(444);
                    ur.setMessage("#### Unknown Request");
                    logger.log(Level.SEVERE, "Couldn't find request,you fool");
                    break;
            }
            
            
        } catch (DataException e) {
            ur.setStatusCode(101);
            ur.setMessage("Data service failed to process your request");
            logger.log(Level.SEVERE, "Database related failure", e);
            addErrorStore(19, e.getDescription(), "TrafficCop");
        } catch (Exception e) {
            ur.setStatusCode(102);
            ur.setMessage("Server process failed to process your request");
            logger.log(Level.SEVERE, "Generic server related failure", e);
            addErrorStore(19, "Server Error \n" + dataUtil.getErrorString(e), "TrafficCop");
        }
        if (ur.getStatusCode() == null) {
            ur.setStatusCode(0);
        }
        long end = System.currentTimeMillis();
        double elapsed = Elapsed.getElapsed(start, end);
        ur.setElapsedRequestTimeInSeconds(elapsed);
        logger.log(Level.WARNING, "*********** request elapsed time: {0} seconds", elapsed);
        return ur;
    }

    public void addErrorStore(int statusCode, String message, String origin) {
        logger.log(Level.OFF, "------ adding errorStore, message: {0} origin: {1}", new Object[]{message, origin});
        try {
            Errorstore t = new Errorstore();
            t.setDateOccured(new Date());
            t.setMessage(message);
            t.setStatusCode(statusCode);
            t.setOrigin(origin);
            em.persist(t);
            logger.log(Level.INFO, "####### ErrorStore row added, origin {0} \nmessage: {1}",
                    new Object[]{origin, message});
        } catch (Exception e) {
            logger.log(Level.SEVERE, "####### Failed to add errorStore from " + origin + "\n" + message, e);
        }
    }
    @PersistenceContext
    EntityManager em;
    static final Logger logger = Logger.getLogger(TrafficCop.class.getSimpleName());
}
