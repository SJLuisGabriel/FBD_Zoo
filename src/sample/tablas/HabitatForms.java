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
import sample.models.HabitatDAO;

import java.io.File;

public class HabitatForms extends Stage {

    private Scene escena;
    private VBox vBox,vBox1,vBox2;
    private HBox hBox1;
    private TextField txtDescipcion,txtCveClima;
    private Label lblDescipcion,lblCveClima;
    private Button btnGuardar;
    private HabitatDAO objDAO;
    private TableView<HabitatDAO> tbv;

    public HabitatForms(TableView<HabitatDAO> tbv, HabitatDAO objDAO){
        this.tbv = tbv;
        if( objDAO != null )
            this.objDAO = objDAO;
        else
            this.objDAO = new HabitatDAO();
        CrearUI();
        this.setTitle("Formulario Del Habitat");
        this.setScene(escena);
        this.show();
    }

    private void CrearUI() {

        txtDescipcion = new TextField();
        lblDescipcion = new Label("Descripcion: ");lblDescipcion.setId("lblForms");
        txtDescipcion.setPromptText("Descripcion C - M - G");
        txtDescipcion.setText(objDAO.getDescripcion());

        txtCveClima = new TextField();
        lblCveClima = new Label("CVE Clima: ");lblCveClima.setId("lblForms");
        txtCveClima.setPromptText("Clave");
        txtCveClima.setText(String.valueOf(objDAO.getCveClima()));

        btnGuardar = new Button("GUARDAR");

        btnGuardar.setOnAction(event -> {
            objDAO.setDescripcion(txtDescipcion.getText());
            objDAO.setCveClima(Integer.parseInt(txtCveClima.getText()));

            if(objDAO.getCveHabitat() > 0 )
                objDAO.ACTUALIZAR_HABITAT();
            else
                objDAO.INSERTAR_HABITAT();

            tbv.setItems(objDAO.SELECCIONAR_HABITAT());
            tbv.refresh();

            this.close();
        });

        HBox hBox1 = new HBox();
        VBox vBox2 = new VBox(); VBox vBox3 = new VBox();
        vBox2.getChildren().addAll(lblDescipcion,lblCveClima);
        vBox3.getChildren().addAll(txtDescipcion,txtCveClima);
        hBox1.getChildren().addAll(vBox2,vBox3);
        vBox2.setSpacing(14); vBox3.setSpacing(8); vBox3.setMinWidth(200);
        vBox2.setPadding(new Insets(4,0,0,0));
        btnGuardar.setId("btnGuardar");

        vBox = new VBox();
        vBox.setSpacing(15);
        vBox.setPadding(new Insets(25));
        vBox.getChildren().addAll(hBox1,btnGuardar);
        escena = new Scene(vBox,350 ,140);
        File Filecss = new File("src/sample/style/style2.css");
        escena.getStylesheets().add(Filecss.toURI().toString());
    }
}