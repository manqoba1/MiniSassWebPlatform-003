/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.boha.minisass.util;

import com.boha.minisass.data.Errorstore;
import com.boha.minisass.transfer.RequestDTO;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author aubreyM
 */
@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class PlatformUtil {

    @PersistenceContext
    EntityManager em;

    public void addTimeElapsedWarning(long start, long end, RequestDTO dto, String origin) {
        if (dto == null) {
            dto = new RequestDTO();
        }
        if (end - start > (1000 * THRESHOLD_SECONDS)) {
            StringBuilder sb = new StringBuilder();
            sb.append("Servlet took more than ").append(THRESHOLD_SECONDS)
                    .append(" seconds to process request\nRequest type is ")
                    .append(dto.getRequestType()).append("\n");
            sb.append("Elapsed time in seconds: ").append(Elapsed.getElapsed(start, end));
            addErrorStore(111, sb.toString(), origin);
        }
    }

    public void addErrorStore(int statusCode, String message, String origin) {
        log.log(Level.OFF, "------ adding errorStore, message: {0} origin: {1}", new Object[]{message, origin});
        try {
            Errorstore t = new Errorstore();
            t.setDateOccured(new Date());
            t.setMessage(message);
            t.setStatusCode(statusCode);
            t.setOrigin(origin);
            em.persist(t);
            log.log(Level.INFO, "####### ErrorStore row added, origin {0} \nmessage: {1}",
                    new Object[]{origin, message});
        } catch (Exception e) {
            log.log(Level.SEVERE, "####### Failed to add errorStore from " + origin + "\n" + message, e);
        }
    }
    static final int THRESHOLD_SECONDS = 6;
    public static final int ERROR_DATABASE = 111, ERROR_SERVER = 112,
            SIGNIFICANT_EVENT = 0, ERROR_WEBSOCKET = 113, ERROR_UNKNOWN_REQUEST = 114;
    static final Logger log = Logger.getLogger(PlatformUtil.class.getName());
}
