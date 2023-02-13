/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package net.itinajero.model;

import java.util.Date;

/**
 *
 * @author Orlando Barragan
 */
public class Vacante {
    private int id;
    private Date fechaPublicacion;
    private String nombre;
    private String descripcion;
    private String detalle;
    
    public Vacante(int id) {
        this.id = id;
        this.fechaPublicacion = new Date();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getFechaPublicacion() {
        return fechaPublicacion;
    }

    public void setFechaPublicacion(Date fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    @Override
    public String toString() {
        return "Vacante{" + "id=" + id + ", fechaPublicacion=" + fechaPublicacion + ", nombre=" + nombre + ", descripcion=" + descripcion + ", detalle=" + detalle + '}';
    }
    
    
}
