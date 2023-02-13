/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package net.itinajero.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import static java.util.Collections.list;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.itinajero.model.Vacante;

/**
 *
 * @author Orlando Barragan
 */
public class VacanteDao {

    DbConnection conn;

    public VacanteDao(DbConnection conn) {
        this.conn = conn;
    }

    public boolean insert(Vacante vacante) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String sql = "insert into Vacante values(?,?,?,?,?)";
        try {
            PreparedStatement ps = conn.getConnection().prepareStatement(sql);
            ps.setInt(1, vacante.getId());
            ps.setString(2, format.format(vacante.getFechaPublicacion()));
            ps.setString(3, vacante.getNombre());
            ps.setString(4, vacante.getDescripcion());
            ps.setString(5, vacante.getDetalle());
            ps.executeUpdate();
            return true;

        } catch (SQLException ex) {
            return false;
        }
    }

    public List<Vacante> getUltimas() {
        try {
            String sql = "select * from Vacante order by id desc limit 3";
            PreparedStatement prepareStatement = conn.getConnection().prepareStatement(sql);
            ResultSet rs = prepareStatement.executeQuery();
            List<Vacante> list = new LinkedList<>();
            Vacante vacante;
            while (rs.next()) {
                vacante = new Vacante(rs.getInt("id"));
                vacante.setFechaPublicacion(rs.getDate("fechaPublicacion"));
                vacante.setNombre(rs.getString("nombre"));
                vacante.setDescripcion(rs.getString("descripcion"));
                vacante.setDetalle(rs.getString("detalle"));
                list.add(vacante);
            }
            return list;
        } catch (SQLException e) {
            System.out.println("Error VacanteDAO.getUltimas: " + e.getMessage());
            return null;
        }
    }

    public List<Vacante> getAll() {
        try {
            String sql = "select * from Vacante order by id desc";
            PreparedStatement prepareStatement = conn.getConnection().prepareStatement(sql);
            ResultSet rs = prepareStatement.executeQuery();
            List<Vacante> list = new LinkedList<>();
            Vacante vacante;
            while (rs.next()) {
                vacante = new Vacante(rs.getInt("id"));
                vacante.setFechaPublicacion(rs.getDate("fechaPublicacion"));
                vacante.setNombre(rs.getString("nombre"));
                vacante.setDescripcion(rs.getString("descripcion"));
                vacante.setDetalle(rs.getString("detalle"));
                list.add(vacante);
            }
            return list;
        } catch (SQLException e) {
            System.out.println("Error VacanteDAO.getAll: " + e.getMessage());
            return null;
        }
    }

    public Vacante getById(int idVacante) {
        try {
            String sql = "select * from Vacante where id=? limit 1";
            PreparedStatement preparedStatement = conn.getConnection().prepareStatement(sql);
            preparedStatement.setInt(1, idVacante);
            ResultSet rs = preparedStatement.executeQuery();
            Vacante vacante = new Vacante(0);
            while (rs.next()) {
                vacante.setId(rs.getInt("id"));
                vacante.setFechaPublicacion(rs.getDate("fechaPublicacion"));
                vacante.setNombre(rs.getString("nombre"));
                vacante.setDescripcion(rs.getString("descripcion"));
                vacante.setDetalle(rs.getString("detalle"));
            }
            return vacante;
        } catch (SQLException e) {
            System.out.println("Error VacantesDao.getById" + e.getMessage());
        }
        return null;
    }

    public List<Vacante> getByQuery(String query) {
        try {
            String sql = "select * from Vacante where (descripcion like ? or nombre like ?) order by id desc";
            PreparedStatement preparedStatement = conn.getConnection().prepareStatement(sql);
            preparedStatement.setString(1, "%" + query + "%");
            preparedStatement.setString(2, "%" + query + "%");
            ResultSet rs = preparedStatement.executeQuery();
            List<Vacante> list = new LinkedList<>();
            Vacante vacante;
            while (rs.next()) {
                vacante = new Vacante(rs.getInt("id"));
                vacante.setFechaPublicacion(rs.getDate("fechaPublicacion"));
                vacante.setNombre(rs.getString("nombre"));
                vacante.setDescripcion(rs.getString("descripcion"));
                vacante.setDetalle(rs.getString("detalle"));
                list.add(vacante);
            }
            return list;
        } catch (SQLException e) {
            System.out.println("Error VacanteDao.byQuery:" + e.getMessage());
            return null;
        }

    }
    public int delete(int idVacante){
        try{
            String sql = "delete from Vacante where id=?";
            PreparedStatement preparedStatement = conn.getConnection().prepareStatement(sql);
            preparedStatement.setInt(1, idVacante);
            int rows = preparedStatement.executeUpdate();
            return rows;
        }
        catch(SQLException e){
            System.out.println("Error vacanteDao.eliminar"+e.getMessage());
            return 0;
        }
        
    }
}
