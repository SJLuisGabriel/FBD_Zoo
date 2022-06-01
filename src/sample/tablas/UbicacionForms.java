package sample.tablas;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import sample.models.UbicacionDAO;
import java.io.File;

import static sample.tablas.UbicacionBD.ubi;

public class UbicacionForms extends Stage {

    private Scene escena;
    private VBox vBox;
    private TextField txtCveAnimal,txtCveHabitat;
    private Label lblCveAnimal,lblCveHabitat;
    private Button btnGuardar;
    private UbicacionDAO objDAO;
    private TableView<UbicacionDAO> tbv;

    public UbicacionForms(TableView<UbicacionDAO> tbv, UbicacionDAO objDAO){
        this.tbv = tbv;
        if( objDAO != null )
            this.objDAO = objDAO;
        else
            this.objDAO = new UbicacionDAO();
        CrearUI();
        this.setTitle("Formulario De Ubicacion");
        this.setScene(escena);
        this.show();
    }

    private void CrearUI() {

        txtCveAnimal = new TextField();
        lblCveAnimal = new Label("CVE Animal: ");lblCveAnimal.setId("lblForms");
        txtCveAnimal.setPromptText("Clave");
        txtCveAnimal.setText(String.valueOf(objDAO.getCveAnimal()));

        txtCveHabitat = new TextField();
        lblCveHabitat = new Label("CVE Habitat: ");lblCveHabitat.setId("lblForms");
        txtCveHabitat.setPromptText("Clave");
        txtCveHabitat.setText(String.valueOf(objDAO.getCveHabitat()));

        btnGuardar = new Button("GUARDAR");

        btnGuardar.setOnAction(event -> {
            objDAO.setCveAnimal(Integer.parseInt(txtCveAnimal.getText()));
            objDAO.setCveHabitat(Integer.parseInt(txtCveHabitat.getText()));

            if(ubi == 2 )
               objDAO.ACTUALIZAR_UBICACION();
            else
                objDAO.INSERTAR_UBICACION();
            ubi = 0;
            tbv.setItems(objDAO.SELECCIONAR_UBICACION());
            tbv.refresh();

            this.close();
        });

        HBox hBox1 = new HBox();
        VBox vBox2 = new VBox(); VBox vBox3 = new VBox();
        vBox2.getChildren().addAll(lblCveAnimal,lblCveHabitat);
        vBox3.getChildren().addAll(txtCveAnimal,txtCveHabitat);
        hBox1.getChildren().addAll(vBox2,vBox3);
        vBox2.setSpacing(14); vBox3.setSpacing(8); vBox3.setMinWidth(200);
        vBox2.setPadding(new Insets(4,0,0,0));
        btnGuardar.setId("btnGuardar");

        vBox = new VBox();
        vBox.setSpacing(15);
        vBox.setPadding(new Insets(25));
        vBox.getChildren().addAll(hBox1,btnGuardar);
        escena = new Scene(vBox,350,140);
        File Filecss = new File("src/sample/style/style2.css");
        escena.getStylesheets().add(Filecss.toURI().toString());
    }
}
