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
import sample.models.ClimaDAO;

import java.io.File;

public class ClimaForms extends Stage {

    private Scene escena;
    private VBox vBox,vBox1,vBox2;
    private HBox hBox1;
    private TextField txtDescipcion;
    private Label lblDescipcion;
    private Button btnGuardar;
    private ClimaDAO objDAO;
    private TableView<ClimaDAO> tbv;

    public ClimaForms(TableView<ClimaDAO> tbv, ClimaDAO objDAO){
        this.tbv = tbv;
        if( objDAO != null )
            this.objDAO = objDAO;
        else
            this.objDAO = new ClimaDAO();
        CrearUI();
        this.setTitle("Formulario De Clima");
        this.setScene(escena);
        this.show();
    }

    private void CrearUI() {

        txtDescipcion = new TextField();
        lblDescipcion = new Label("Descripcion: ");lblDescipcion.setId("lblForms");
        txtDescipcion.setPromptText("Tamaño");
        txtDescipcion.setText(objDAO.getDescripcion());

        btnGuardar = new Button("GUARDAR");

        btnGuardar.setOnAction(event -> {
            objDAO.setDescripcion(txtDescipcion.getText());

            if(objDAO.getCveClima() > 0 )
                objDAO.ACTUALIZAR_CLIMA();
            else
                objDAO.INSERTAR_CLIMA();

            tbv.setItems(objDAO.SELECCIONAR_CLIMA());
            tbv.refresh();

            this.close();
        });

        HBox hBox1 = new HBox();
        VBox vBox2 = new VBox(); VBox vBox3 = new VBox();
        vBox2.getChildren().addAll(lblDescipcion);
        vBox3.getChildren().addAll(txtDescipcion);
        hBox1.getChildren().addAll(vBox2,vBox3);
        vBox2.setSpacing(14); vBox3.setSpacing(8); vBox3.setMinWidth(200);
        vBox2.setPadding(new Insets(4,0,0,0));
        btnGuardar.setId("btnGuardar");

        vBox = new VBox();
        vBox.setSpacing(15);
        vBox.setPadding(new Insets(25));
        vBox.getChildren().addAll(hBox1,btnGuardar);
        escena = new Scene(vBox,345 ,110);
        File Filecss = new File("src/sample/style/style2.css");
        escena.getStylesheets().add(Filecss.toURI().toString());
    }
}