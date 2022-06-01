package sample.models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AlimentacionDAO {

    private int CveAlimentacion;
    private String nombre,cantidad;

    public int getCveAlimentacion() {return CveAlimentacion;}

    public void setCveAlimentacion(int cveAlimentacion) {CveAlimentacion = cveAlimentacion;}

    public String getNombre() {return nombre;}

    public void setNombre(String nombre) {this.nombre = nombre;}

    public String getCantidad() {return cantidad;}

    public void setCantidad(String cantidad) {this.cantidad = cantidad;}

    public void INSERTAR_ALIMENTACION(){
        String query = "INSERT INTO alimentacion (nombre,Cantidad) " +
                "VALUES('"+this.nombre+"','"+this.cantidad+"')";
        try {
            Statement stmt = Conexion.conexion.createStatement();
            stmt.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void ACTUALIZAR_ALIMENTACION(){
        String query = "UPDATE alimentacion SET nombre='"+this.nombre+"',Cantidad='" +this.cantidad+"' "+
                "WHERE CveAlimentacion = "+this.CveAlimentacion;
        try {
            Statement stmt = Conexion.conexion.createStatement();
            stmt.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void ELIMINAR_ALIMENTACION(){
        String query = "DELETE FROM alimentacion WHERE CveAlimentacion = "+this.CveAlimentacion;
        try {
            Statement stmt = Conexion.conexion.createStatement();
            stmt.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ObservableList<AlimentacionDAO> SELECCIONAR_ALIMENTACION(){

        ObservableList<AlimentacionDAO> listaAlimentacion = FXCollections.observableArrayList();
        AlimentacionDAO objAlimentacion;
        String query = "SELECT * FROM alimentacion ORDER BY CveAlimentacion";
        try {
            Statement stmt = Conexion.conexion.createStatement();
            ResultSet res = stmt.executeQuery(query);

            while( res.next() ){
                objAlimentacion = new AlimentacionDAO();
                objAlimentacion.setCveAlimentacion(res.getInt("CveAlimentacion"));
                objAlimentacion.setNombre(res.getString("nombre"));
                objAlimentacion.setCantidad(res.getString("cantidad"));
                listaAlimentacion.add(objAlimentacion);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaAlimentacion;
    }
}