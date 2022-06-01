package sample.models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ClimaDAO {

    private int CveClima;
    private String descripcion;

    public int getCveClima() {return CveClima;}

    public void setCveClima(int cveClima) {CveClima = cveClima;}

    public String getDescripcion() {return descripcion;}

    public void setDescripcion(String descripcion) {this.descripcion = descripcion;}

    public void INSERTAR_CLIMA(){
        String query = "INSERT INTO clima (descripcion) " +
                "VALUES('"+this.descripcion+"')";
        try {
            Statement stmt = Conexion.conexion.createStatement();
            stmt.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void ACTUALIZAR_CLIMA(){
        String query = "UPDATE clima SET descripcion='"+this.descripcion+"' " +
                "WHERE CveClima = "+this.CveClima;
        try {
            Statement stmt = Conexion.conexion.createStatement();
            stmt.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void ELIMINAR_CLIMA(){
        String query = "DELETE FROM clima WHERE CveClima = "+this.CveClima;
        try {
            Statement stmt = Conexion.conexion.createStatement();
            stmt.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ObservableList<ClimaDAO> SELECCIONAR_CLIMA(){

        ObservableList<ClimaDAO> listaClima = FXCollections.observableArrayList();
        ClimaDAO objClima;
        String query = "SELECT * FROM clima ORDER BY CveClima";
        try {
            Statement stmt = Conexion.conexion.createStatement();
            ResultSet res = stmt.executeQuery(query);

            while( res.next() ){
                objClima = new ClimaDAO();
                objClima.setCveClima(res.getInt("CveClima"));
                objClima.setDescripcion(res.getString("descripcion"));
                listaClima.add(objClima);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaClima;
    }
}