package Modelo;

import Datos.InvitadoDAO;
import java.util.ArrayList;

public class Invitado {
    private String nombre;
    private String apellido;
    private int dni;
    private String menVeg;
    private InvitadoDAO invitadoDAO;

    public Invitado() {
        this.invitadoDAO = new InvitadoDAO();
    }

    public Invitado(String nombre, String apellido, int dni, String menVeg) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.menVeg = menVeg;
        this.invitadoDAO = new InvitadoDAO();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public int getDni() {
        return dni;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }

    public String getMenVeg() {
        return menVeg;
    }

    public void setMenVeg(String menVeg) {
        this.menVeg = menVeg;
    }

    public InvitadoDAO getInvitadoDAO() {
        return this.invitadoDAO;
    }

    public void setInvitadoDAO(InvitadoDAO invitadoDAO) {
        this.invitadoDAO = invitadoDAO;
    } 
    
    public ArrayList<Invitado> verTodos(){
          return this.invitadoDAO.verTodos();
    }
    
    public boolean validarDato(String dato) {
        try {
            Integer.parseInt(dato);
            return true;
        } catch (NumberFormatException nfe) {
            return false;
        }
    }
}
