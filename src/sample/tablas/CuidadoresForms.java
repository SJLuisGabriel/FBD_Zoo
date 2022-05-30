package sample.tablas;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import sample.models.CuidadoresDAO;

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

        txtNombre = new TextField();
        txtNombre.setPromptText("Nombre Del Trabajador");
        txtNombre.setText(objCuidadoresDAO.getNombre());

        txtDireccion = new TextField();
        txtDireccion.setPromptText("Direccion");
        txtDireccion.setText(objCuidadoresDAO.getDireccion());

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

        vBox = new VBox();
        vBox.setSpacing(10.0);
        vBox.setPadding(new Insets(10.0));
        vBox.getChildren().addAll(txtNombre,txtDireccion,txtTelefono,btnGuardar);
        escena = new Scene(vBox,300 ,250);
    }
}
