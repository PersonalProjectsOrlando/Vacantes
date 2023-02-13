/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package net.itinajero.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.itinajero.dao.DbConnection;
import net.itinajero.dao.VacanteDao;
import net.itinajero.model.Vacante;

/**
 *
 * @author Orlando Barragan
 */
@WebServlet(name = "BusquedaController", urlPatterns = {"/buscar"})
public class BusquedaController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String q = request.getParameter("query");
        List<Vacante> lista = null;
        DbConnection conn = new DbConnection();
        VacanteDao vacanteDao = new VacanteDao(conn);
        lista = vacanteDao.getByQuery(q);
        conn.disconnect();
        RequestDispatcher rd;
        request.setAttribute("vacantes", lista);
        rd =request.getRequestDispatcher("/vacantes.jsp");
        rd.forward(request, response);
        
    }
}
