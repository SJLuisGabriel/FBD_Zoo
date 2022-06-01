package sample.models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AnimalesDAO {

    private int CveAnimal,CveAlimentacion,CveVeterinario,CveEspecie;
    private String nombre,FecNac,sexo,ZonaOrigen,nombreCientifico;

    public int getCveAnimal() {return CveAnimal;}

    public void setCveAnimal(int cveAnimal) {CveAnimal = cveAnimal;}

    public int getCveAlimentacion() {return CveAlimentacion;}

    public void setCveAlimentacion(int cveAlimentacion) {CveAlimentacion = cveAlimentacion;}

    public int getCveVeterinario() {return CveVeterinario;}

    public void setCveVeterinario(int cveVeterinario) {CveVeterinario = cveVeterinario;}

    public int getCveEspecie() {return CveEspecie;}

    public void setCveEspecie(int cveEspecie) {CveEspecie = cveEspecie;}

    public String getNombre() {return nombre;}

    public void setNombre(String nombre) {this.nombre = nombre;}

    public String getFecNac() {return FecNac;}

    public void setFecNac(String fecNac) {FecNac = fecNac;}

    public String getSexo() {return sexo;}

    public void setSexo(String sexo) {this.sexo = sexo;}

    public String getZonaOrigen() {return ZonaOrigen;}

    public void setZonaOrigen(String zonaOrigen) {ZonaOrigen = zonaOrigen;}

    public String getNombreCientifico() {return nombreCientifico;}

    public void setNombreCientifico(String nombreCientifico) {this.nombreCientifico = nombreCientifico;}

    public void INSERTAR_ANIMALES(){
        String query = "INSERT INTO animales (nombre,FecNac,sexo,ZonaOrigen,nombreCientifico," +
                "CveAlimentacion,CveVeterinario,CveEspecie) " +
                "VALUES('"+this.nombre+"','"+this.FecNac+"','"+this.sexo+"','"+this.ZonaOrigen+"'," +
                "'"+this.nombreCientifico+"',"+this.CveAlimentacion+","+this.CveVeterinario+"," +
                this.CveEspecie+")";
        try {
            Statement stmt = Conexion.conexion.createStatement();
            stmt.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void ACTUALIZAR_ANIMALES(){
        String query = "UPDATE animales SET nombre='"+this.nombre+"',FecNac='"+this.FecNac+"',sexo='"+this.sexo+"',ZonaOrigen='"+this.ZonaOrigen+"',nombreCientifico='"+
                this.nombreCientifico+"',CveAlimentacion="+this.CveAlimentacion+",CveVeterinario="+ this.CveVeterinario+",CveEspecie="+this.CveEspecie+
                " WHERE CveAnimal = "+this.CveAnimal;
        try {
            Statement stmt = Conexion.conexion.createStatement();
            stmt.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void ELIMINAR_ANIMALES(){
        String query = "DELETE FROM animales WHERE CveAnimal = "+this.CveAnimal;
        try {
            Statement stmt = Conexion.conexion.createStatement();
            stmt.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ObservableList<AnimalesDAO> SELECCIONAR_ANIMALES(){

        ObservableList<AnimalesDAO> listaAnimales = FXCollections.observableArrayList();
        AnimalesDAO objAnimales;
        String query = "SELECT * FROM animales ORDER BY CveAnimal";
        try {
            Statement stmt = Conexion.conexion.createStatement();
            ResultSet res = stmt.executeQuery(query);

            while( res.next() ){
                objAnimales = new AnimalesDAO();
                objAnimales.setCveAnimal(res.getInt("CveAnimal"));
                objAnimales.setNombre(res.getString("nombre"));
                objAnimales.setFecNac(res.getString("FecNac"));
                objAnimales.setSexo(res.getString("sexo"));
                objAnimales.setZonaOrigen(res.getString("ZonaOrigen"));
                objAnimales.setNombreCientifico(res.getString("nombreCientifico"));
                objAnimales.setCveAlimentacion(res.getInt("CveAlimentacion"));
                objAnimales.setCveVeterinario(res.getInt("CveVeterinario"));
                objAnimales.setCveEspecie(res.getInt("CveEspecie"));
                listaAnimales.add(objAnimales);
            }
        } catch (SQLException e) {;
            e.printStackTrace();
        }
        return listaAnimales;
    }
}