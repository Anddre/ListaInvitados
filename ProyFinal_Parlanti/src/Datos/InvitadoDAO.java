package Datos;

import Modelo.Invitado;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class InvitadoDAO extends SQLQuery{

    public InvitadoDAO() {
        try {
            this.conectar("localhost:3306", "InvitadoBD", "root", "mysql");
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(InvitadoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     
    public ArrayList<Invitado> verTodos(){
        ArrayList<Invitado> inviTodos = new ArrayList();
        try {
            this.consulta = this.conn.prepareStatement("SELECT inv_Nombre, inv_Apellido, inv_Dni, inv_MenVeg FROM Invitados");
            this.datos = this.consulta.executeQuery();
            
            while (this.datos.next()) {
                Invitado inv1 = new Invitado();
                inv1.setNombre(this.datos.getString("inv_nombre"));
                inv1.setApellido(this.datos.getString("inv_apellido"));
                inv1.setDni(Integer.parseInt(this.datos.getString("inv_dni")));
                inv1.setMenVeg(this.datos.getString("inv_MenVeg"));
                inviTodos.add(inv1);
            }
        }catch (SQLException ex) {
            Logger.getLogger(InvitadoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return inviTodos; 
    }
    
    public void insertarInvitado(Invitado inv){
        try {
            this.s = this.conn.createStatement();
            s.executeUpdate("INSERT INTO Invitados (inv_Nombre, inv_Apellido, inv_Dni, inv_MenVeg) VALUES ('" + inv.getNombre() + "','" + inv.getApellido() + "','" + inv.getDni() + "','" + inv.getMenVeg() + "' )");
        } catch (SQLException ex) {
            Logger.getLogger(InvitadoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }  
    
    public boolean actualizarInvitado(Invitado inv){
               
        try {
            this.s = this.conn.createStatement();
            s.executeUpdate("UPDATE Invitados SET "
                    + "inv_Nombre  = '" +  inv.getNombre() + "', "
                    + "inv_Apellido = '" + inv.getApellido() + "', "
                    + "inv_MenVeg = '" + inv.getMenVeg() + "' WHERE inv_Dni =  '" + inv.getDni() + "';");
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(InvitadoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    } 

    public void eliminarInvitado(int dni){
        try {
            this.s = this.conn.createStatement();
            s.executeUpdate("DELETE FROM `Invitados` WHERE `inv_Dni`=" + dni + ";");
        } catch (SQLException ex) {
            Logger.getLogger(InvitadoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    } 
    
}
