package sample.models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class VeterinarioDAO {

    private int CveVeterinario;
    private String nombre;
    private String direccion;
    private String Telefono;

    public int getCveVeterinario() {return CveVeterinario;}

    public void setCveVeterinario(int cveVeterinario) {CveVeterinario = cveVeterinario;}

    public String getNombre() {return nombre;}

    public void setNombre(String nombre) {this.nombre = nombre;}

    public String getDireccion() {return direccion;}

    public void setDireccion(String direccion) {this.direccion = direccion;}

    public String getTelefono() {return Telefono;}

    public void setTelefono(String telefono) {Telefono = telefono;}

    public void INSERTAR_VETERINARIO(){
        String query = "INSERT INTO veterinario (nombre,direccion,Telefono) " +
                "VALUES('"+this.nombre+"','"+this.direccion+"',"+this.Telefono+")";
        try {
            Statement stmt = Conexion.conexion.createStatement();
            stmt.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void ACTUALIZAR_VETERINARIO(){
        String query = "UPDATE veterinario SET nombre='"+this.nombre+"',direccion='"+this.direccion+
                "',Telefono="+this.Telefono+" WHERE CveVeterinario = "+this.CveVeterinario;
        try {
            Statement stmt = Conexion.conexion.createStatement();
            stmt.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void ELIMINAR_VETERINARIO(){
        String query = "DELETE FROM veterinario WHERE CveVeterinario = "+this.CveVeterinario;
        try {
            Statement stmt = Conexion.conexion.createStatement();
            stmt.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ObservableList<VeterinarioDAO> SELECCIONAR_VETERINARIO(){

        ObservableList<VeterinarioDAO> listaVeterinario = FXCollections.observableArrayList();
        VeterinarioDAO objVeterinario;
        String query = "SELECT * FROM veterinario ORDER BY CveVeterinario";
        try {
            Statement stmt = Conexion.conexion.createStatement();
            ResultSet res = stmt.executeQuery(query);

            while( res.next() ){
                objVeterinario = new VeterinarioDAO();
                objVeterinario.setCveVeterinario(res.getInt("CveCuidadores"));
                objVeterinario.setNombre(res.getString("nombre"));
                objVeterinario.setDireccion(res.getString("direccion"));
                objVeterinario.setTelefono(res.getString("Telefono"));
                listaVeterinario.add(objVeterinario);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaVeterinario;
    }
}