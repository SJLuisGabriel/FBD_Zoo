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
import sample.models.TipoRecoDAO;

import java.io.File;

public class TipoRecoForms extends Stage {

    private Scene escena;
    private VBox vBox;
    private TextField txtDescipcion;
    private Label lblDescripcion;
    private Button btnGuardar;
    private TipoRecoDAO objDAO;
    private TableView<TipoRecoDAO> tbv;

    public TipoRecoForms(TableView<TipoRecoDAO> tbv, TipoRecoDAO objDAO){
        this.tbv = tbv;
        if( objDAO != null )
            this.objDAO = objDAO;
        else
            this.objDAO = new TipoRecoDAO();
        CrearUI();
        this.setTitle("Formulario De Tipo de Recorrido");
        this.setScene(escena);
        this.show();
    }

    private void CrearUI() {

        txtDescipcion = new TextField();
        lblDescripcion = new Label("Descripcion: ");lblDescripcion.setId("lblForms");
        txtDescipcion.setPromptText("Descripcion");
        txtDescipcion.setText(objDAO.getDescripcion());

        btnGuardar = new Button("GUARDAR");

        btnGuardar.setOnAction(event -> {
            objDAO.setDescripcion(txtDescipcion.getText());

            if(objDAO.getCveTipoRecorrido() > 0 )
                objDAO.ACTUALIZAR_TIPORECORRIDO();
            else
                objDAO.INSERTAR_TIPORECORRIDO();

            tbv.setItems(objDAO.SELECCIONAR_TIPORECORRIDO());
            tbv.refresh();

            this.close();
        });

        HBox hBox1 = new HBox();
        VBox vBox2 = new VBox(); VBox vBox3 = new VBox();
        vBox2.getChildren().addAll(lblDescripcion);
        vBox3.getChildren().addAll(txtDescipcion);
        hBox1.getChildren().addAll(vBox2,vBox3);
        vBox2.setSpacing(14); vBox3.setSpacing(8); vBox3.setMinWidth(200);
        vBox2.setPadding(new Insets(4,0,0,0));
        btnGuardar.setId("btnGuardar");

        vBox = new VBox();
        vBox.setSpacing(15);
        vBox.setPadding(new Insets(25));
        vBox.getChildren().addAll(hBox1,btnGuardar);
        escena = new Scene(vBox,350 ,110);
        File Filecss = new File("src/sample/style/style2.css");
        escena.getStylesheets().add(Filecss.toURI().toString());
    }
}