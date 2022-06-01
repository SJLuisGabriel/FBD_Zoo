package sample.models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CuidadosDAO {

    private int CveAnimal,CveCuidadores;
    private String cuidados;

    public int getCveAnimal() {return CveAnimal;}

    public void setCveAnimal(int cveAnimal) {CveAnimal = cveAnimal;}

    public int getCveCuidadores() {return CveCuidadores;}

    public void setCveCuidadores(int cveCuidadores) {CveCuidadores = cveCuidadores;}

    public String getCuidados() {return cuidados;}

    public void setCuidados(String cuidados) {this.cuidados = cuidados;}

    public void INSERTAR_CUIDADOS(){
        String query = "INSERT INTO cuidados (CveAnimal,CveCuidadores,cuidados) " +
                "VALUES("+this.CveAnimal+","+this.CveCuidadores+",'"+this.cuidados+"')";
        try {
            Statement stmt = Conexion.conexion.createStatement();
            stmt.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void ACTUALIZAR_CUIDADOS(){
        String query = "UPDATE cuidados SET CveAnimal="+this.CveAnimal+",CveCuidadores="+
                this.CveCuidadores+",cuidados='"+this.cuidados+"'"+
                " WHERE CveAnimal = "+this.CveAnimal;
        try {
            Statement stmt = Conexion.conexion.createStatement();
            stmt.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void ELIMINAR_CUIDADOS(){
        String query = "DELETE FROM cuidados WHERE CveCuidadores = "+this.getCveCuidadores();
        try {
            Statement stmt = Conexion.conexion.createStatement();
            stmt.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ObservableList<CuidadosDAO> SELECCIONAR_CUIDADOS(){

        ObservableList<CuidadosDAO> listaCuidados = FXCollections.observableArrayList();
        CuidadosDAO objCuidados;
        String query = "SELECT * FROM cuidados ORDER BY CveAnimal";
        try {
            Statement stmt = Conexion.conexion.createStatement();
            ResultSet res = stmt.executeQuery(query);

            while( res.next() ){
                objCuidados = new CuidadosDAO();
                objCuidados.setCveAnimal(res.getInt("CveAnimal"));
                objCuidados.setCveCuidadores(res.getInt("CveCuidadores"));
                objCuidados.setCuidados(res.getString("cuidados"));
                listaCuidados.add(objCuidados);
            }
        } catch (SQLException e) {;
            e.printStackTrace();
        }
        return listaCuidados;
    }
}