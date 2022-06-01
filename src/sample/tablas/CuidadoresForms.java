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
import sample.models.CuidadoresDAO;

import java.io.File;

public class CuidadoresForms extends Stage {

    private Scene escena;
    private VBox vBox;
    private TextField txtNombre,txtDireccion,txtTelefono;
    private Button btnGuardar;
    private CuidadoresDAO objCuidadoresDAO;
    private TableView<CuidadoresDAO> tbvCuidores;

    public CuidadoresForms(TableView<CuidadoresDAO> tbvCuidores, CuidadoresDAO objCuidadoresDAO){
        this.tbvCuidores = tbvCuidores;
        if( objCuidadoresDAO != null )
            this.objCuidadoresDAO = objCuidadoresDAO;
        else
            this.objCuidadoresDAO = new CuidadoresDAO();
        CrearUI();
        this.setTitle("Formulario De Empleados Cuidadores");
        this.setScene(escena);
        this.show();
    }

    private void CrearUI() {

        Label lblNombre = new Label("Nombre: ");lblNombre.setId("lblForms");
        txtNombre = new TextField();
        txtNombre.setPromptText("Nombre Del Trabajador");
        txtNombre.setText(objCuidadoresDAO.getNombre());

        Label lblDireccion= new Label("Direccion: ");lblDireccion.setId("lblForms");
        txtDireccion = new TextField();
        txtDireccion.setPromptText("Direccion");
        txtDireccion.setText(objCuidadoresDAO.getDireccion());

        Label lblTelefono = new Label("Telefono: "); lblTelefono.setId("lblForms");
        txtTelefono = new TextField();
        txtTelefono.setPromptText("Telefono");
        txtTelefono.setText(objCuidadoresDAO.getTelefono());

        btnGuardar = new Button("GUARDAR");

        btnGuardar.setOnAction(event -> {
            objCuidadoresDAO.setNombre(txtNombre.getText());
            objCuidadoresDAO.setDireccion(txtDireccion.getText());
            objCuidadoresDAO.setTelefono(txtTelefono.getText());

            if(objCuidadoresDAO.getCveCuidadores() > 0 )
                objCuidadoresDAO.ACTUALIZAR_CUIDADORES();
            else
                objCuidadoresDAO.INSERTAR_CUIDADORES();

            tbvCuidores.setItems(objCuidadoresDAO.SELECCIONAR_CUIDADORES());
            tbvCuidores.refresh();

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
