/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package net.itinajero.controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import net.itinajero.dao.DbConnection;
import net.itinajero.dao.UsuarioDao;
import net.itinajero.dao.VacanteDao;
import net.itinajero.model.Usuario;

/**
 *
 * @author Orlando Barragan
 */
@WebServlet(name = "AdminController", urlPatterns = {"/admin"})
public class AdminController extends HttpServlet {
 
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        HttpSession session = request.getSession();
        RequestDispatcher rd;
        String msg="";
        switch(action){
            case "login":
                if(session.getAttribute("usuario")==null){
                    request.setAttribute("message",msg);
                    rd=request.getRequestDispatcher("/login.jsp");
                    rd.forward(request, response);
                }else{
                    rd=request.getRequestDispatcher("/admin.jsp");
                    rd.forward(request, response);
                }
                break;
            case "logout":
                session.invalidate();
                response.sendRedirect(request.getContextPath()+"/homepage");
                break;
            case "crear":
                if(session.getAttribute("usuario")==null){
                    msg = "Acceso denegado";
                    request.setAttribute("message", msg);
                    rd = request.getRequestDispatcher("/login.jsp");
                    rd.forward(request, response);
                }else{
                    rd=request.getRequestDispatcher("/frmvacante.jsp");
                    rd.forward(request, response);
                }
                break;
            case "eliminar":
                if(session.getAttribute("usuario")==null){
                    msg = "Acceso denegado";
                    request.setAttribute("message", msg);
                    rd = request.getRequestDispatcher("/login.jsp");
                    rd.forward(request, response);
                }else{
                    this.eliminarVacante(request, response);
                }
        }
        
                
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String userParam = request.getParameter("user");
        String passParam = request.getParameter("pass");
        String msg = "";
        
        HttpSession session = request.getSession();
        
        DbConnection conn = new DbConnection();
        UsuarioDao usuarioDao = new UsuarioDao(conn);
        
        Usuario usuario = usuarioDao.login(userParam, passParam);
        conn.disconnect();
        RequestDispatcher rd;
        System.out.println("id: "+usuario.getId());
        if(usuario.getId()>0){
            session.setAttribute("usuario", usuario);
            //rd=request.getRequestDispatcher("/admin.jsp");
            rd=request.getRequestDispatcher("/index.jsp");
            rd.forward(request, response);
            
        }
        else{
            msg="usuario y/o password incorectos";
            request.setAttribute("message", msg);
            rd=request.getRequestDispatcher("/login.jsp");
            rd.forward(request, response);
        }
        
    }

   
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
    private void eliminarVacante(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
            int idVacanteParam = Integer.parseInt(request.getParameter("idVacante")); 
            DbConnection conn = new DbConnection();
            VacanteDao vacanteDao = new VacanteDao(conn);
            int respuesta = vacanteDao.delete((idVacanteParam));
            String msg ="";
            if(respuesta == 1){ // fue afectado un registro al eliminar, esto significa que si borró
                msg = "La vacante fue eliminada";
            }
            else{
                msg = "Ocurrio un error. La vacante No fue eliminada";
            }
            conn.disconnect();
            request.setAttribute("message", msg);
            RequestDispatcher rd;
            rd = request.getRequestDispatcher("/mensaje.jsp");
            rd.forward(request, response);
    }
}
