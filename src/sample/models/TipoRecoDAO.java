package sample.models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TipoRecoDAO {

    private int CveTipoRecorrido;
    private String descripcion;

    public int getCveTipoRecorrido() {return CveTipoRecorrido;}

    public void setCveTipoRecorrido(int cveTipoRecorrido) {CveTipoRecorrido = cveTipoRecorrido;}

    public String getDescripcion() {return descripcion;}

    public void setDescripcion(String descripcion) {this.descripcion = descripcion;}

    public void INSERTAR_TIPORECORRIDO(){
        String query = "INSERT INTO tiporecorrido (descripcion) " +
                "VALUES('"+this.descripcion+"')";
        try {
            Statement stmt = Conexion.conexion.createStatement();
            stmt.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void ACTUALIZAR_TIPORECORRIDO(){
        String query = "UPDATE tiporecorrido SET descripcion='"+this.descripcion+"' " +
                "WHERE CveTipoRecorrido = "+this.CveTipoRecorrido;
        try {
            Statement stmt = Conexion.conexion.createStatement();
            stmt.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void ELIMINAR_TIPORECORRIDO(){
        String query = "DELETE FROM tiporecorrido WHERE CveTipoRecorrido = "+this.CveTipoRecorrido;
        try {
            Statement stmt = Conexion.conexion.createStatement();
            stmt.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ObservableList<TipoRecoDAO> SELECCIONAR_TIPORECORRIDO(){

        ObservableList<TipoRecoDAO> listaTipoRecorrido = FXCollections.observableArrayList();
        TipoRecoDAO objTipoRecorrido;
        String query = "SELECT * FROM tiporecorrido ORDER BY CveTipoRecorrido";
        try {
            Statement stmt = Conexion.conexion.createStatement();
            ResultSet res = stmt.executeQuery(query);

            while( res.next() ){
                objTipoRecorrido = new TipoRecoDAO();
                objTipoRecorrido.setCveTipoRecorrido(res.getInt("CveTipoRecorrido"));
                objTipoRecorrido.setDescripcion(res.getString("descripcion"));
                listaTipoRecorrido.add(objTipoRecorrido);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaTipoRecorrido;
    }
}