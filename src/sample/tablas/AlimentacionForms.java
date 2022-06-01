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
import sample.models.AlimentacionDAO;

import java.io.File;

public class AlimentacionForms extends Stage {

    private Scene escena;
    private VBox vBox,vBox1,vBox2;
    private HBox hBox1;
    private TextField txtNombre,txtCantidad;
    private Label lblNombre,lblCantidad;
    private Button btnGuardar;
    private AlimentacionDAO objDAO;
    private TableView<AlimentacionDAO> tbv;

    public AlimentacionForms(TableView<AlimentacionDAO> tbv, AlimentacionDAO objDAO){
        this.tbv = tbv;
        if( objDAO != null )
            this.objDAO = objDAO;
        else
            this.objDAO = new AlimentacionDAO();
        CrearUI();
        this.setTitle("Formulario De Alimentacion De Los Animales");
        this.setScene(escena);
        this.show();
    }

    private void CrearUI() {

        txtNombre = new TextField();
        lblNombre = new Label("Nombre: ");lblNombre.setId("lblForms");
        txtNombre.setPromptText("Nombre Del Alimento");
        txtNombre.setText(objDAO.getNombre());

        txtCantidad = new TextField();
        lblCantidad = new Label("Cantidad: ");lblCantidad.setId("lblForms");
        txtCantidad.setPromptText("Cantidad Kg");
        txtCantidad.setText(objDAO.getCantidad());

        btnGuardar = new Button("GUARDAR");

        btnGuardar.setOnAction(event -> {
            objDAO.setNombre(txtNombre.getText());
            objDAO.setCantidad(txtCantidad.getText());

            if(objDAO.getCveAlimentacion() > 0 )
                objDAO.ACTUALIZAR_ALIMENTACION();
            else
                objDAO.INSERTAR_ALIMENTACION();

            tbv.setItems(objDAO.SELECCIONAR_ALIMENTACION());
            tbv.refresh();

            this.close();
        });

        HBox hBox1 = new HBox();
        VBox vBox2 = new VBox(); VBox vBox3 = new VBox();
        vBox2.getChildren().addAll(lblNombre,lblCantidad);
        vBox3.getChildren().addAll(txtNombre,txtCantidad);
        hBox1.getChildren().addAll(vBox2,vBox3);
        vBox2.setSpacing(14); vBox3.setSpacing(8); vBox3.setMinWidth(200);
        vBox2.setPadding(new Insets(4,0,0,0));
        btnGuardar.setId("btnGuardar");

        vBox = new VBox();
        vBox.setSpacing(15);
        vBox.setPadding(new Insets(25));
        vBox.getChildren().addAll(hBox1,btnGuardar);
        escena = new Scene(vBox,350 ,150);
        File Filecss = new File("src/sample/style/style2.css");
        escena.getStylesheets().add(Filecss.toURI().toString());
    }
}