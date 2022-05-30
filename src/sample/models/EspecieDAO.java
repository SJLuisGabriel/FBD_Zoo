package sample.models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class EspecieDAO {

    private int CveEspecie;
    private String clasificacion;

    public int getCveEspecie() {return CveEspecie;}

    public void setCveEspecie(int cveEspecie) {CveEspecie = cveEspecie;}

    public String getClasificacion() {return clasificacion;}

    public void setClasificacion(String clasificacion) {this.clasificacion = clasificacion;}

    public void INSERTAR_ESPECIE(){
        String query = "INSERT INTO especie (clasificacion) " +
                "VALUES('"+this.clasificacion+"')";
        try {
                Statement stmt = Conexion.conexion.createStatement();
                stmt.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void ACTUALIZAR_ESPECIE(){
        String query = "UPDATE especie SET clasificacion='"+this.clasificacion+"' " +
                "WHERE CveEspecie = "+this.CveEspecie;
        try {
                Statement stmt = Conexion.conexion.createStatement();
                stmt.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void ELIMINAR_ESPECIE(){
        String query = "DELETE FROM especie WHERE CveEspecie = "+this.CveEspecie;
        try {
                Statement stmt = Conexion.conexion.createStatement();
                stmt.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ObservableList<EspecieDAO> SELECCIONAR_ESPECIE(){

        ObservableList<EspecieDAO> listaEsp = FXCollections.observableArrayList();
        EspecieDAO objEsp;
        String query = "SELECT * FROM especie ORDER BY CveEspecie";
        try {
            Statement stmt = Conexion.conexion.createStatement();
            ResultSet res = stmt.executeQuery(query);

            while( res.next() ){
                objEsp = new EspecieDAO();
                objEsp.setCveEspecie(res.getInt("CveEspecie"));
                objEsp.setClasificacion(res.getString("clasificacion"));
                listaEsp.add(objEsp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaEsp;
    }
}