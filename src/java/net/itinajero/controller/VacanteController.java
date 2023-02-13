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
@WebServlet(name = "VacanteController", urlPatterns = {"/vacante"})
public class VacanteController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //processRequest(request, response);
        String action = request.getParameter("action");
        
        if(action.equals("ver")){
            this.verDetalle(request, response);
            //System.out.println(action+"dentro if");
            
        }
        if(action.equals("lista")){
            this.verTodas(request, response);
        }
    }
    protected void verTodas(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        DbConnection conn =new DbConnection();
        VacanteDao vacanteDao = new VacanteDao(conn);
        List<Vacante> lista = vacanteDao.getAll();
        conn.disconnect();
        
        request.setAttribute("vacantes",lista);
        RequestDispatcher rd = request.getRequestDispatcher("/vacantes.jsp");
        rd.forward(request, response);
       
        
    }
    protected void verDetalle(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        int idVacante = Integer.parseInt(request.getParameter("id"));
        DbConnection conn = new DbConnection();
        VacanteDao vacanteDao = new VacanteDao(conn);
        Vacante vacante = vacanteDao.getById(idVacante);
        conn.disconnect();
        
        request.setAttribute("vacante", vacante);
        RequestDispatcher rd;
        
        rd = request.getRequestDispatcher("detalle.jsp");
        rd.forward(request, response);
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //processRequest(request, response);
        System.out.println("doPost vacante.jsp");
        String nombre = request.getParameter("nombre");
        String descripcion = request.getParameter("descripcion");
        String detalle = request.getParameter("detalle");
        
        Vacante vacante = new Vacante(0);
        vacante.setNombre(nombre);
        vacante.setDescripcion(descripcion);
        vacante.setDetalle(detalle);
        System.out.println(vacante);
        
        // codigo para insertar el registro
        DbConnection conn = new DbConnection();
        VacanteDao vacanteDao = new VacanteDao(conn);
        boolean status = vacanteDao.insert(vacante);
        
        //reparamos un mensaje para el usuario
        String msg = "";
        if(status){
            msg = "La vacante fue guardada con exito";
        }else{
            msg="Ocurrio un error ...la vacante NO se creo";
        }
        conn.disconnect();
        request.setAttribute("message", msg);
        RequestDispatcher rd = request.getRequestDispatcher("mensaje.jsp");
        rd.forward(request, response);
    }

    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
