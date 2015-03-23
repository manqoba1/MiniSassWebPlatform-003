/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.boha.minisass.gate;

import com.boha.minisass.transfer.RequestDTO;
import com.boha.minisass.transfer.ResponseDTO;
import com.boha.minisass.util.DataUtil;
import com.boha.minisass.util.GeneratorUtil;
import com.boha.minisass.util.ListUtil;
import com.boha.minisass.util.PlatformUtil;
import com.boha.minisass.util.TrafficCop;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author CodeTribe1
 */
@WebServlet(name = "GenServlet", urlPatterns = {"/gen1"})
public class GenServlet extends HttpServlet {

    @EJB
    GeneratorUtil generateUtil;
    @EJB
    ListUtil listUtil;
    @EJB
    DataUtil dataUtil;

    @EJB
    TrafficCop trafficCop;
    @EJB
    PlatformUtil platformUtil;
    Gson gson = new Gson();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");
        log.log(Level.OFF, "Generator Servlet starting ...");
        PrintWriter out = response.getWriter();
        String json;
        ResponseDTO ur = new ResponseDTO();
        try {
            RequestDTO req = getRequest(request);

            switch (req.getRequestType()) {
                case RequestDTO.GENERATE_TEAM:
                    ur = generateUtil.generateTeam(listUtil, req.getTeam(), req.getTeamMember());
                    break;
                case RequestDTO.GENERATE_RIVER:
                    ur = generateUtil.generateRiver(req.getRiver());
                    break;
                case RequestDTO.GET_DATA:
                    ur = listUtil.getData(req.getCountryCode());
                    break;
                default:
                    ur.setStatusCode(444);
                    ur.setMessage("#### Unknown Request");
                    log.log(Level.SEVERE, "Couldn't find request");
                    break;

            }
        } catch (Exception ex) {
            log.log(Level.OFF, "Failed.....{0}", ex);
            ur.setStatusCode(10);
            ur.setMessage("Things fall apart....Keep digging and pay attention to detail");
        } finally {
            json = gson.toJson(ur);
            out.println(json);
            out.close();
            log.log(Level.OFF, "Servlet ending");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private RequestDTO getRequest(HttpServletRequest req) {
        String json = req.getParameter("JSON");
        RequestDTO re = null;
        if (json == null) {
            log.log(Level.OFF, "Json parameter not found...");
            re = new RequestDTO();
            re.setRequestType(0);
            return re;
        }

        try {
            re = gson.fromJson(json, RequestDTO.class);
            log.log(Level.INFO, "JSON okay. ...");
        } catch (Exception e) {
            log.log(Level.OFF, "JSON is not okay. ...");
            re = new RequestDTO();
            re.setRequestType(0);
        }
        return re;
    }

    static final Logger log = Logger.getLogger(Test.class.getSimpleName());
}
