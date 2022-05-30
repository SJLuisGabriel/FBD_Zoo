package sample.models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class GuiaDAO {

    private int CveGuia;
    private String nombre;
    private String direccion;
    private String Telefono;

    public int getCveGuia() {return CveGuia;}

    public void setCveGuia(int cveGuia) {CveGuia = cveGuia;}

    public String getNombre() {return nombre;}

    public void setNombre(String nombre) {this.nombre = nombre;}

    public String getDireccion() {return direccion;}

    public void setDireccion(String direccion) {this.direccion = direccion;}

    public String getTelefono() {return Telefono;}

    public void setTelefono(String telefono) {Telefono = telefono;}

    public void INSERTAR_GUIA(){
        String query = "INSERT INTO guias (nombre,direccion,Telefono) " +
                "VALUES('"+this.nombre+"','"+this.direccion+"',"+this.Telefono+")";
        try {
            Statement stmt = Conexion.conexion.createStatement();
            stmt.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void ACTUALIZAR_GUIA(){
        String query = "UPDATE guias SET nombre='"+this.nombre+"',direccion='"+this.direccion+
                "',Telefono="+this.Telefono+" WHERE CveGuia = "+this.CveGuia;
        try {
            Statement stmt = Conexion.conexion.createStatement();
            stmt.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void ELIMINAR_GUIA(){
        String query = "DELETE FROM guias WHERE CveGuia = "+this.CveGuia;
        try {
            Statement stmt = Conexion.conexion.createStatement();
            stmt.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ObservableList<GuiaDAO> SELECCIONAR_GUIA(){

        ObservableList<GuiaDAO> listaGuia = FXCollections.observableArrayList();
        GuiaDAO objGuia;
        String query = "SELECT * FROM guias ORDER BY CveGuia";
        try {
            Statement stmt = Conexion.conexion.createStatement();
            ResultSet res = stmt.executeQuery(query);

            while( res.next() ){
                objGuia = new GuiaDAO();
                objGuia.setCveGuia(res.getInt("CveGuia"));
                objGuia.setNombre(res.getString("nombre"));
                objGuia.setDireccion(res.getString("direccion"));
                objGuia.setTelefono(res.getString("Telefono"));
                listaGuia.add(objGuia);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaGuia;
    }
}