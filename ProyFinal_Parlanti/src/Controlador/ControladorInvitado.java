package Controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import Modelo.Invitado;
import Vista.JF_Invitado;
import Datos.InvitadoDAO;

public class ControladorInvitado implements ActionListener, MouseListener {

    private DefaultTableModel tInvitados;
    private ArrayList<Invitado> arrayListInvitado = new ArrayList<Invitado>();
    private JF_Invitado jFInvitado;
    private Invitado invitado;

    public ControladorInvitado(JF_Invitado jFInvitado, Invitado invitado, InvitadoDAO invitadoDAO) {
        this.invitado = invitado;
        this.jFInvitado = jFInvitado;
        invitadoDAO = this.invitado.getInvitadoDAO();
        this.jFInvitado.setVisible(true);
        this.jFInvitado.getBtn_GuardarCambios().setEnabled(false);
        

        this.escucharBotones();
        this.arrayListInvitado = this.invitado.verTodos();
        this.jFInvitado.getjT_Tabla().setModel(this.tablaInvitados(arrayListInvitado));
    }

    public void escucharBotones() {
        this.jFInvitado.getBtn_Agregar().addActionListener(this);
        this.jFInvitado.getBtn_Borrar().addActionListener(this);
        this.jFInvitado.getBtn_Editar().addActionListener(this);
        this.jFInvitado.getBtn_VerTodos().addActionListener(this);
        this.jFInvitado.getBtn_Limpiar().addActionListener(this);
        this.jFInvitado.getBtn_Buscar().addActionListener(this);
        this.jFInvitado.getBtn_GuardarCambios().addActionListener(this);
        this.jFInvitado.getjT_Tabla().addMouseListener(this);
    }

    public DefaultTableModel tablaInvitados(ArrayList<Invitado> arrayListInvitado) {
        int contadorTabla = 0;
        String x[][] = {};
        String nombresColumnas[] = {"Nombre", "Apellido", "Dni", "Menú Vegano"};
        tInvitados = new DefaultTableModel(x, nombresColumnas);
        if (arrayListInvitado != null) {
            for (int i = 0; i < arrayListInvitado.size(); i++) {
                tInvitados.insertRow(contadorTabla, new Object[]{});
                tInvitados.setValueAt(arrayListInvitado.get(i).getNombre(), contadorTabla, 0);
                tInvitados.setValueAt(arrayListInvitado.get(i).getApellido(), contadorTabla, 1);
                tInvitados.setValueAt(arrayListInvitado.get(i).getDni(), contadorTabla, 2);
                tInvitados.setValueAt(arrayListInvitado.get(i).getMenVeg(), contadorTabla, 3);
                contadorTabla++;
            }
        }
        return tInvitados;
    }

    public boolean agregarInvitado(String nombre, String apellido, int dni, String menVeg) {
        boolean existe = false;
        if (this.arrayListInvitado.size() != 0) {
            for (int i = 0; i < arrayListInvitado.size(); i++) {
                if (dni == (arrayListInvitado.get(i).getDni())){
                    JOptionPane.showMessageDialog(null, "Ese DNI corresponde a un invitado ya registrado");
                    i = arrayListInvitado.size();
                    existe = true;
                }
            }
        }
        if (existe == false) {
            this.invitado.setNombre(nombre);
            this.invitado.setApellido(apellido);
            this.invitado.setDni(dni);
            this.invitado.setMenVeg(menVeg);
        }
        return existe;
    }

    public String menu(boolean a) {
        if (a) {
            return "Sí";
        } else {
            return "No";
        }
    }

    public void borrarInvitado(int dni) {
        boolean existe = false;
        if (this.arrayListInvitado.size() != 0) {
            for (int i = 0; i < arrayListInvitado.size(); i++) {
                if (dni == (arrayListInvitado.get(i).getDni())) {
                    arrayListInvitado.remove(i);
                    existe = true;
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "No hay datos en la tabla a modificar");
        }
        if (existe == false) {
            JOptionPane.showMessageDialog(null, "DNI no registrado");
        }
    }
    
    public boolean buscarInvitado(int dni) {
        boolean existe = false;
        if (this.arrayListInvitado.size() != 0) {
            for (int i = 0; i < arrayListInvitado.size(); i++) {
                if (dni == (arrayListInvitado.get(i).getDni())){
                    this.invitado.setNombre(arrayListInvitado.get(i).getNombre());
                    this.invitado.setApellido(arrayListInvitado.get(i).getApellido());
                    this.invitado.setDni(arrayListInvitado.get(i).getDni());
                    this.invitado.setMenVeg(arrayListInvitado.get(i).getMenVeg());
                    i = arrayListInvitado.size();
                    existe = true;
                }
            }
        }
        if (existe == false) {
            JOptionPane.showMessageDialog(null, "No se han encontrado registros con ese DNI");
        }
        return existe;
    }

    public void limpiarTabla() {        
        if (arrayListInvitado != null) {
            this.jFInvitado.txt_Nombre.setText("");
            this.jFInvitado.txt_Apellido.setText("");
            this.jFInvitado.txt_DNI.setText("");
            this.jFInvitado.checkBox_MenVeg.setText("");
        }else {
            JOptionPane.showMessageDialog(null, "No hay datos disponibles en la tabla");
        }
        
    }
    
    public void cargaDatos(boolean e){
        //se verifica que exista un registro con esa PK
            if(e){
                //si existe, se cargan los demás datos en el formulario
                this.jFInvitado.txt_Nombre.setText(this.invitado.getNombre());
                this.jFInvitado.txt_Apellido.setText(this.invitado.getApellido());
                this.jFInvitado.txt_DNI.setText("" + this.invitado.getDni());
                this.jFInvitado.checkBox_MenVeg.setText(this.invitado.getMenVeg());
            } else {
                //si no existe, se muestra mensaje al usuario
                JOptionPane.showMessageDialog(null, "No se han encontrado registros con ese DNI");
              }
    }
    
    public boolean modificarInvitado(String nombre, String apellido, int dni, String menVeg){
        boolean existe = false;
        //recorre el array buscando el dni registrado
        for (int i = 0; i < this.arrayListInvitado.size(); i++) {
            if (dni == (this.arrayListInvitado.get(i).getDni())) {
                //cuando lo encuentra, le asigna al objeto invitado, los atributos de los jText
                this.invitado.setNombre(nombre);
                this.invitado.setApellido(apellido);
                this.invitado.setMenVeg(menVeg);
                //luego carga el invitado ya modificado en el arrayList
                this.arrayListInvitado.set(i, invitado);
                //le asigna a i el valor del size del arrayList para salir del bucle
                i = arrayListInvitado.size();
                //y declara true a la variable booleana para devolverla
                existe = true;
            }
        }
        return existe;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {

//cuando se presiona el botón Agregar
        if (e.getSource().equals(this.jFInvitado.getBtn_Agregar())) {
            //verifica que los datos notNull de la tabla se encuentren insertados en el formulario
            if (this.jFInvitado.getTxt_Nombre().getText().isEmpty() || this.jFInvitado.getTxt_Apellido().getText().isEmpty() || this.jFInvitado.getTxt_DNI().getText().isEmpty()) {
                //se solicita se ingresen todos los datos
                JOptionPane.showMessageDialog(null, "Debe completar todos los datos");
                //se verifica que el campo DNI sea numérico
            } else if (!(this.invitado.validarDato(this.jFInvitado.txt_DNI.getText()))) {
            //cuando no lo es, se informa al usuario
            JOptionPane.showMessageDialog(null, "Sólo ingrese números para el campo DNI");
            } else {
            //se le pasan los txtField como parámetros al método agregarInvitado
            //que carga los datos en un objeto de tipo Invitado y lo agrega al arrayListInvitado
            boolean existe = this.agregarInvitado(this.jFInvitado.getTxt_Nombre().getText(), this.jFInvitado.getTxt_Apellido().getText(), Integer.parseInt(this.jFInvitado.getTxt_DNI().getText()), this.menu(this.jFInvitado.getCheckBox_MenVeg().isSelected()));
            //si se verifica que no existe otro registro con igual PK, se realiza la instrucción en la BD
            if (existe == false) {
            //se accede al InvitadoDAO a través del modelo y se inserta el objeto en la BD            
            this.invitado.getInvitadoDAO().insertarInvitado(this.invitado);
            //confirmación al usuario
            JOptionPane.showMessageDialog(null, "Invitado registrado correctamente");
            }
            //selecciona los registros de la BD, y los almacena en un arrayList
            this.arrayListInvitado = this.invitado.verTodos();
            //toma los datos del arrayList actualizado y los muestra en la tabla
            this.jFInvitado.getjT_Tabla().setModel(this.tablaInvitados(arrayListInvitado));
            //limpia los campos
            this.limpiarTabla();
            }
//cuando se presiona el botón Borrar
        } else if (e.getSource().equals(this.jFInvitado.getBtn_Borrar())) {
            //verifica que los datos notNull de la tabla se encuentren insertados en el formulario
            if (this.jFInvitado.getTxt_DNI().getText().isEmpty()) {
                //se solicita se ingresen todos los datos
                JOptionPane.showMessageDialog(null, "Debe completar el campo DNI");
                //se verifica que el campo DNI sea numérico
            } else if (!(this.invitado.validarDato(this.jFInvitado.txt_DNI.getText()))) {
            //cuando no lo es, se informa al usuario
            JOptionPane.showMessageDialog(null, "Sólo ingrese números para el campo DNI");
            } else {
            //se le pasa el txtField de DNI como parámetro al método borrarInvitado
            //que elimina el registro del arrayList
            this.borrarInvitado(Integer.parseInt(this.jFInvitado.getTxt_DNI().getText()));
            //se accede al InvitadoDAO a través del modelo y se elimina el objeto en la BD   
            this.invitado.getInvitadoDAO().eliminarInvitado(Integer.parseInt(this.jFInvitado.getTxt_DNI().getText()));
            //confirmación al usuario
            JOptionPane.showMessageDialog(null, "Se ha borrado el registro correctamente");
            //selecciona los registros de la BD, y los almacena en un arrayList
            this.arrayListInvitado = this.invitado.verTodos();
            //toma los datos del arrayList actualizado y los muestra en la tabla
            this.jFInvitado.getjT_Tabla().setModel(this.tablaInvitados(arrayListInvitado));
            //limpia los campos
            this.limpiarTabla();
            }
//cuando se presiona el botón VerTodos
        } else if (e.getSource().equals(this.jFInvitado.getBtn_VerTodos())) {
            //toma los datos del arrayList actualizado y los muestra en la tabla
            this.jFInvitado.getjT_Tabla().setModel(this.tablaInvitados(arrayListInvitado));
        
//cuando se presiona el botón Buscar            
        } else if (e.getSource().equals(this.jFInvitado.getBtn_Buscar())) {
            //verifica que los datos notNull de la tabla se encuentren insertados en el formulario
            if (this.jFInvitado.getTxt_DNI().getText().isEmpty()) {
                //se solicita se ingresen todos los datos
                JOptionPane.showMessageDialog(null, "Debe completar el campo DNI");
                //se verifica que el campo DNI sea numérico
            } else if (!(this.invitado.validarDato(this.jFInvitado.txt_DNI.getText()))) {
            //cuando no lo es, se informa al usuario
            JOptionPane.showMessageDialog(null, "Sólo ingrese números para el campo DNI");
            } else {
            //se le pasa el txtField DNI como parámetro al método buscarInvitado
            //que toma los datos y los asigna a un objeto de tipo Invitado
            boolean existe = this.buscarInvitado(Integer.parseInt(this.jFInvitado.getTxt_DNI().getText()));
            //se verifica que exista un registro con esa PK
            //si existe, se cargan los datos en el formulario
            this.cargaDatos(existe);
            }
//cuando se presiona el botón Editar    
        } else if (e.getSource().equals(this.jFInvitado.getBtn_Editar())) {
            //verifica que los datos notNull de la tabla se encuentren insertados en el formulario
            if (this.jFInvitado.getTxt_DNI().getText().isEmpty()) {
                //se solicita se ingresen todos los datos
                JOptionPane.showMessageDialog(null, "Debe completar el campo DNI");
                //se verifica que el campo DNI sea numérico
            } else if (!(this.invitado.validarDato(this.jFInvitado.txt_DNI.getText()))) {
                //cuando no lo es, se informa al usuario
                JOptionPane.showMessageDialog(null, "Sólo ingrese números para el campo DNI");
            } else {
            //se deshabilita el txtField DNI ya que no se puede modificar la PK            
            this.jFInvitado.txt_DNI.setEnabled(false);
            //se le pasa el txtField DNI como parámetro al método buscarInvitado
            //que toma los datos y los asigna a un objeto de tipo Invitado
            boolean existe = this.buscarInvitado(Integer.parseInt(this.jFInvitado.getTxt_DNI().getText()));
                //se verifica que exista un registro con esa PK
                //si existe, se cargan los datos en el formulario
                if (existe) {
                    this.cargaDatos(existe);
                    //se habilita el botón de guardar cambios
                    this.jFInvitado.getBtn_GuardarCambios().setEnabled(existe);
                }
            }
//cuando se presiona el botón Guardar Cambios
        } else if (e.getSource().equals(this.jFInvitado.getBtn_GuardarCambios())) {
            //se le pasan los txtField como parámetros al método modificarInvitado
            //que cambia los datos en un objeto de tipo Invitado, que buscó por el dni en el arrayListInvitado
            boolean existe = this.modificarInvitado(this.jFInvitado.getTxt_Nombre().getText(), this.jFInvitado.getTxt_Apellido().getText(), Integer.parseInt(this.jFInvitado.getTxt_DNI().getText()), this.menu(this.jFInvitado.getCheckBox_MenVeg().isSelected()));
            //si se verifica que se modificó el registro, se realiza la instrucción en la BD
            if (existe) {
            //se accede al InvitadoDAO a través del modelo y se inserta el objeto en la BD            
            this.invitado.getInvitadoDAO().actualizarInvitado(this.invitado);
            //confirmación al usuario
            JOptionPane.showMessageDialog(null, "Invitado modificado correctamente");
            }
            //selecciona los registros de la BD, y los almacena en un arrayList
            this.arrayListInvitado = this.invitado.verTodos();
            //toma los datos del arrayList actualizado y los muestra en la tabla
            this.jFInvitado.getjT_Tabla().setModel(this.tablaInvitados(arrayListInvitado));
            //limpia los campos
            this.limpiarTabla();
            //se habilita nuevamente el txtField DNI            
            this.jFInvitado.txt_DNI.setEnabled(true);
            //se habilita el botón de guardar cambios
            this.jFInvitado.getBtn_GuardarCambios().setEnabled(false);
            
//cuando se presiona el botón Limpiar            
        } else if (e.getSource().equals(this.jFInvitado.getBtn_Limpiar())) {
            //limpia los campos
            this.limpiarTabla();
            //se habilita el txtField DNI en caso que haya quedado deshabilitado
            this.jFInvitado.txt_DNI.setEnabled(true);
            
        }
        }
    

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getButton() == 1) {
            int fila = this.jFInvitado.getjT_Tabla().rowAtPoint(e.getPoint());
            if (fila > -1) {
                this.jFInvitado.txt_DNI.setText(String.valueOf(this.jFInvitado.getjT_Tabla().getValueAt(fila, 2)));
                this.jFInvitado.txt_DNI.setEnabled(false);
                this.jFInvitado.txt_Nombre.setText(String.valueOf(this.jFInvitado.getjT_Tabla().getValueAt(fila, 0)));
                this.jFInvitado.txt_Apellido.setText(String.valueOf(this.jFInvitado.getjT_Tabla().getValueAt(fila, 1)));
                this.jFInvitado.checkBox_MenVeg.setText(String.valueOf(this.jFInvitado.getjT_Tabla().getValueAt(fila, 3)));

            }
        }
    }

    

    @Override
    public void mousePressed(MouseEvent me) {

    }

    @Override
    public void mouseReleased(MouseEvent me) {

    }

    @Override
    public void mouseEntered(MouseEvent me) {
    }

    @Override
    public void mouseExited(MouseEvent me) {

    }

    public DefaultTableModel gettInvitados() {
        return tInvitados;
    }

    public void settInvitados(DefaultTableModel tInvitados) {
        this.tInvitados = tInvitados;
    }

    public ArrayList<Invitado> getArrayListInvitado() {
        return arrayListInvitado;
    }

    public void setArrayListInvitado(ArrayList<Invitado> arrayListInvitado) {
        this.arrayListInvitado = arrayListInvitado;
    }

    public Invitado getInvitado() {
        return invitado;
    }

    public void setInvitado(Invitado invitado) {
        this.invitado = invitado;
    }

}
