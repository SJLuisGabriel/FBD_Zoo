package sample.tablas;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import sample.models.GuiaDAO;

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

        txtNombre = new TextField();
        txtNombre.setPromptText("Nombre Del Trabajador");
        txtNombre.setText(objGuiaDAO.getNombre());

        txtDireccion = new TextField();
        txtDireccion.setPromptText("Direccion");
        txtDireccion.setText(objGuiaDAO.getDireccion());

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

        vBox = new VBox();
        vBox.setSpacing(10.0);
        vBox.setPadding(new Insets(10.0));
        vBox.getChildren().addAll(txtNombre,txtDireccion,txtTelefono,btnGuardar);
        escena = new Scene(vBox,300 ,250);
    }
}
