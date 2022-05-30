package sample.tablas;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import sample.models.VeterinarioDAO;

public class VeterinarioForms extends Stage {

    private Scene escena;
    private VBox vBox;
    private TextField txtNombre,txtDireccion,txtTelefono;
    private Button btnGuardar;
    private VeterinarioDAO objVeterinarioDAO;
    private TableView<VeterinarioDAO> tbvVet;

    public VeterinarioForms(TableView<VeterinarioDAO> tbvVet, VeterinarioDAO objVeterinarioDAO){
        this.tbvVet = tbvVet;
        if( objVeterinarioDAO != null )
            this.objVeterinarioDAO = objVeterinarioDAO;
        else
            this.objVeterinarioDAO = new VeterinarioDAO();
        CrearUI();
        this.setTitle("Formulario De Empleados Veterinario");
        this.setScene(escena);
        this.show();
    }

    private void CrearUI() {

        txtNombre = new TextField();
        txtNombre.setPromptText("Nombre Del Trabajador");
        txtNombre.setText(objVeterinarioDAO.getNombre());

        txtDireccion = new TextField();
        txtDireccion.setPromptText("Direccion");
        txtDireccion.setText(objVeterinarioDAO.getDireccion());

        txtTelefono = new TextField();
        txtTelefono.setPromptText("Telefono");
        txtTelefono.setText(objVeterinarioDAO.getTelefono());

        btnGuardar = new Button("GUARDAR");

        btnGuardar.setOnAction(event -> {
            objVeterinarioDAO.setNombre(txtNombre.getText());
            objVeterinarioDAO.setDireccion(txtDireccion.getText());
            objVeterinarioDAO.setTelefono(txtTelefono.getText());

            if(objVeterinarioDAO.getCveVeterinario() > 0 )
                objVeterinarioDAO.ACTUALIZAR_VETERINARIO();
            else
                objVeterinarioDAO.INSERTAR_VETERINARIO();

            tbvVet.setItems(objVeterinarioDAO.SELECCIONAR_VETERINARIO());
            tbvVet.refresh();

            this.close();
        });

        vBox = new VBox();
        vBox.setSpacing(10.0);
        vBox.setPadding(new Insets(10.0));
        vBox.getChildren().addAll(txtNombre,txtDireccion,txtTelefono,btnGuardar);
        escena = new Scene(vBox,300 ,250);
    }
}