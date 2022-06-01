package sample.models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class HabitatDAO {

    private int CveHabitat;
    private String descripcion;
    private int CveClima;

    public int getCveHabitat() {return CveHabitat;}

    public void setCveHabitat(int cveHabitat) {CveHabitat = cveHabitat;}

    public String getDescripcion() {return descripcion;}

    public void setDescripcion(String descripcion) {this.descripcion = descripcion;}

    public int getCveClima() {return CveClima;}

    public void setCveClima(int cveClima) {CveClima = cveClima;}

    public void INSERTAR_HABITAT(){
        String query = "INSERT INTO habitat (descripcion,CveClima) " +
                "VALUES('"+this.descripcion+"',"+this.CveClima+")";
        try {
            Statement stmt = Conexion.conexion.createStatement();
            stmt.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void ACTUALIZAR_HABITAT(){
        String query = "UPDATE habitat SET descripcion='"+this.descripcion+"',CveClima="+this.CveClima+
                " WHERE CveHabitat = "+this.CveHabitat;
        try {
            Statement stmt = Conexion.conexion.createStatement();
            stmt.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void ELIMINAR_HABITAT(){
        String query = "DELETE FROM habitat WHERE CveHabitat = "+this.CveHabitat;
        try {
            Statement stmt = Conexion.conexion.createStatement();
            stmt.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ObservableList<HabitatDAO> SELECCIONAR_HABITAT(){

        ObservableList<HabitatDAO> listaHabitat = FXCollections.observableArrayList();
        HabitatDAO objHabitat;
        String query = "SELECT * FROM habitat ORDER BY CveHabitat";
        try {
            Statement stmt = Conexion.conexion.createStatement();
            ResultSet res = stmt.executeQuery(query);

            while( res.next() ){
                objHabitat = new HabitatDAO();
                objHabitat.setCveHabitat(res.getInt("CveHabitat"));
                objHabitat.setDescripcion(res.getString("descripcion"));
                objHabitat.setCveClima(res.getInt("CveClima"));
                listaHabitat.add(objHabitat);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaHabitat;
    }
}