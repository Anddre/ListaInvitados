/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Datos.InvitadoDAO;
import Modelo.Invitado;
import Vista.JF_Invitado;

/**
 *
 * @author eibi_
 */
public class ProyFinal_Parlanti {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        JF_Invitado jFInvitado = new JF_Invitado();
        InvitadoDAO invitadoDAO = new InvitadoDAO();
        Invitado invitado = new Invitado();
        Controlador.ControladorInvitado ctrlInvitado = new Controlador.ControladorInvitado(jFInvitado, invitado, invitadoDAO);
    }

}
