package sample.models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UbicacionDAO {

    private int CveAnimal,CveHabitat;

    public int getCveAnimal() {return CveAnimal;}

    public void setCveAnimal(int cveAnimal) {CveAnimal = cveAnimal;}

    public int getCveHabitat() {return CveHabitat;}

    public void setCveHabitat(int cveHabitat) {CveHabitat = cveHabitat;}

    public void INSERTAR_UBICACION(){
        String query = "INSERT INTO ubicacion (CveAnimal,CveHabitat) " +
                "VALUES("+this.CveAnimal+","+this.CveHabitat+")";
        try {
            Statement stmt = Conexion.conexion.createStatement();
            stmt.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void ACTUALIZAR_UBICACION(){
        String query = "UPDATE ubicacion SET CveAnimal="+this.CveAnimal+",CveHabitat="+this.CveHabitat+
                " WHERE CveAnimal = "+this.CveAnimal;
        try {
            Statement stmt = Conexion.conexion.createStatement();
            stmt.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void ELIMINAR_UBICACION(){
        String query = "DELETE FROM ubicacion WHERE CveAnimal = "+this.CveAnimal;
        try {
            Statement stmt = Conexion.conexion.createStatement();
            stmt.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ObservableList<UbicacionDAO> SELECCIONAR_UBICACION(){

        ObservableList<UbicacionDAO> listaUbicacion = FXCollections.observableArrayList();
        UbicacionDAO objUbicacion;
        String query = "SELECT * FROM ubicacion ORDER BY CveAnimal";
        try {
            Statement stmt = Conexion.conexion.createStatement();
            ResultSet res = stmt.executeQuery(query);

            while( res.next() ){
                objUbicacion = new UbicacionDAO();
                objUbicacion.setCveAnimal(res.getInt("CveAnimal"));
                objUbicacion.setCveHabitat(res.getInt("CveHabitat"));
                listaUbicacion.add(objUbicacion);
            }
        } catch (SQLException e) {;
            e.printStackTrace();
        }
        return listaUbicacion;
    }
}