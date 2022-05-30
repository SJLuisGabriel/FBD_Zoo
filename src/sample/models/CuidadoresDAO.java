package sample.models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CuidadoresDAO {

    private int CveCuidadores;
    private String nombre;
    private String direccion;
    private String Telefono;

    public int getCveCuidadores() {return CveCuidadores;}

    public void setCveCuidadores(int cveCuidadores) {CveCuidadores = cveCuidadores;}

    public String getNombre() {return nombre;}

    public void setNombre(String nombre) {this.nombre = nombre;}

    public String getDireccion() {return direccion;}

    public void setDireccion(String direccion) {this.direccion = direccion;}

    public String getTelefono() {return Telefono;}

    public void setTelefono(String telefono) {Telefono = telefono;}

    public void INSERTAR_CUIDADORES(){
        String query = "INSERT INTO cuidadores (nombre,direccion,Telefono) " +
                "VALUES('"+this.nombre+"','"+this.direccion+"',"+this.Telefono+")";
        try {
            Statement stmt = Conexion.conexion.createStatement();
            stmt.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void ACTUALIZAR_CUIDADORES(){
        String query = "UPDATE cuidadores SET nombre='"+this.nombre+"',direccion='"+this.direccion+
                "',Telefono="+this.Telefono+" WHERE CveCuidadores = "+this.CveCuidadores;
        try {
            Statement stmt = Conexion.conexion.createStatement();
            stmt.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void ELIMINAR_CUIDADORES(){
        String query = "DELETE FROM cuidadores WHERE CveCuidadores = "+this.CveCuidadores;
        try {
            Statement stmt = Conexion.conexion.createStatement();
            stmt.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ObservableList<CuidadoresDAO> SELECCIONAR_CUIDADORES(){

        ObservableList<CuidadoresDAO> listaCuidadores = FXCollections.observableArrayList();
        CuidadoresDAO objCuidadores;
        String query = "SELECT * FROM cuidadores ORDER BY CveCuidadores";
        try {
            Statement stmt = Conexion.conexion.createStatement();
            ResultSet res = stmt.executeQuery(query);

            while( res.next() ){
                objCuidadores = new CuidadoresDAO();
                objCuidadores.setCveCuidadores(res.getInt("CveCuidadores"));
                objCuidadores.setNombre(res.getString("nombre"));
                objCuidadores.setDireccion(res.getString("direccion"));
                objCuidadores.setTelefono(res.getString("Telefono"));
                listaCuidadores.add(objCuidadores);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaCuidadores;
    }
}
