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
import sample.models.GuiaDAO;

import java.io.File;

public class GuiaForms extends Stage {

    private Scene escena;
    private VBox vBox;
    private TextField txtNombre,txtDireccion,txtTelefono;
    private Button btnGuardar;
    private GuiaDAO objGuiaDAO;
    private TableView<GuiaDAO> tbvGuia;

    public GuiaForms(TableView<GuiaDAO> tbvGuia, GuiaDAO objGuiaDAO){
        this.tbvGuia = tbvGuia;
        if( objGuiaDAO != null )
            this.objGuiaDAO = objGuiaDAO;
        else
            this.objGuiaDAO = new GuiaDAO();
        CrearUI();
        this.setTitle("Formulario De Empleados Guias");
        this.setScene(escena);
        this.show();
    }

    private void CrearUI() {

        Label lblNombre = new Label("Nombre: ");lblNombre.setId("lblForms");
        txtNombre = new TextField();
        txtNombre.setPromptText("Nombre Del Trabajador");
        txtNombre.setText(objGuiaDAO.getNombre());

        Label lblDireccion= new Label("Direccion: ");lblDireccion.setId("lblForms");
        txtDireccion = new TextField();
        txtDireccion.setPromptText("Direccion");
        txtDireccion.setText(objGuiaDAO.getDireccion());

        Label lblTelefono = new Label("Telefono: "); lblTelefono.setId("lblForms");
        txtTelefono = new TextField();
        txtTelefono.setPromptText("Telefono");
        txtTelefono.setText(objGuiaDAO.getTelefono());

        btnGuardar = new Button("GUARDAR");

        btnGuardar.setOnAction(event -> {
            objGuiaDAO.setNombre(txtNombre.getText());
            objGuiaDAO.setDireccion(txtDireccion.getText());
            objGuiaDAO.setTelefono(txtTelefono.getText());

            if(objGuiaDAO.getCveGuia() > 0 )
                objGuiaDAO.ACTUALIZAR_GUIA();
            else
                objGuiaDAO.INSERTAR_GUIA();

            tbvGuia.setItems(objGuiaDAO.SELECCIONAR_GUIA());
            tbvGuia.refresh();

            this.close();
        });

        HBox hBox1 = new HBox();
        VBox vBox2 = new VBox(); VBox vBox3 = new VBox();
        vBox2.getChildren().addAll(lblNombre,lblDireccion,lblTelefono);
        vBox3.getChildren().addAll(txtNombre,txtDireccion,txtTelefono);
        hBox1.getChildren().addAll(vBox2,vBox3);
        vBox2.setSpacing(14); vBox3.setSpacing(8); vBox3.setMinWidth(200);
        vBox2.setPadding(new Insets(4,0,0,0));
        btnGuardar.setId("btnGuardar");

        vBox = new VBox();
        vBox.setSpacing(15);
        vBox.setPadding(new Insets(25));
        vBox.getChildren().addAll(hBox1,btnGuardar);
        escena = new Scene(vBox,350 ,180);
        File Filecss = new File("src/sample/style/style2.css");
        escena.getStylesheets().add(Filecss.toURI().toString());
    }
}
