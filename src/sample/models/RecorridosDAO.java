package sample.models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class RecorridosDAO {

    private int CveRecorridos,CveTipoRecorrido,CveGuia,CveHabitat;
    private String Recorrido,HoraSalida;

    public int getCveRecorridos() {return CveRecorridos;}

    public void setCveRecorridos(int cveRecorridos) {CveRecorridos = cveRecorridos;}

    public int getCveTipoRecorrido() {return CveTipoRecorrido;}

    public void setCveTipoRecorrido(int cveTipoRecorrido) {CveTipoRecorrido = cveTipoRecorrido;}

    public int getCveGuia() {return CveGuia;}

    public void setCveGuia(int cveGuia) {CveGuia = cveGuia;}

    public int getCveHabitat() {return CveHabitat;}

    public void setCveHabitat(int cveHabitat) {CveHabitat = cveHabitat;}

    public String getRecorrido() {return Recorrido;}

    public void setRecorrido(String recorrido) {Recorrido = recorrido;}

    public String getHoraSalida() {return HoraSalida;}

    public void setHoraSalida(String horaSalida) {HoraSalida = horaSalida;}

    public void INSERTAR_RECORRIDOS(){
        String query = "INSERT INTO recorridos (Recorrido,HoraSalida,CveTipoRecorrido," +
                "CveGuia,CveHabitat) " +
                "VALUES('"+this.Recorrido+"','"+this.HoraSalida+"',"+this.CveTipoRecorrido+","+
                this.CveGuia+","+this.CveHabitat+")";
        try {
            Statement stmt = Conexion.conexion.createStatement();
            stmt.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void ACTUALIZAR_RECORRIDOS(){
        String query = "UPDATE recorridos SET Recorrido='"+this.Recorrido+"',HoraSalida='"+this.HoraSalida+
                "',CveGuia="+this.CveGuia+",CveTipoRecorrido="+this.CveTipoRecorrido+",CveHabitat="+
                this.CveHabitat+" WHERE CveRecorridos = "+this.CveRecorridos;
        try {
            Statement stmt = Conexion.conexion.createStatement();
            stmt.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void ELIMINAR_RECORRIDOS(){
        String query = "DELETE FROM recorridos WHERE CveRecorridos = "+this.CveRecorridos;
        try {
            Statement stmt = Conexion.conexion.createStatement();
            stmt.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ObservableList<RecorridosDAO> SELECCIONAR_RECORRIDOS(){

        ObservableList<RecorridosDAO> listaRecorridos = FXCollections.observableArrayList();
        RecorridosDAO objRecorridos;
        String query = "SELECT * FROM recorridos ORDER BY CveRecorridos";
        try {
            Statement stmt = Conexion.conexion.createStatement();
            ResultSet res = stmt.executeQuery(query);

            while( res.next() ){
                objRecorridos = new RecorridosDAO();
                objRecorridos.setCveRecorridos(res.getInt("CveRecorridos"));
                objRecorridos.setRecorrido(res.getString("Recorrido"));
                objRecorridos.setHoraSalida(res.getString("HoraSalida"));
                objRecorridos.setCveTipoRecorrido(res.getInt("CveTipoRecorrido"));
                objRecorridos.setCveGuia(res.getInt("CveGuia"));
                objRecorridos.setCveHabitat(res.getInt("CveHabitat"));
                listaRecorridos.add(objRecorridos);
            }
        } catch (SQLException e) {;
            e.printStackTrace();
        }
        return listaRecorridos;
    }
}