/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package net.itinajero.dao;

import net.itinajero.model.Usuario;

/**
 *
 * @author Orlando Barragan
 */
public class PruebaDB {
    public static void main(String[] args) {
        DbConnection conn = new DbConnection(); 
        UsuarioDao dao = new UsuarioDao(conn);
        //Usuario usuario = dao.login("orljando", "1234");
        //System.out.println(usuario);
        
    }
    
}
