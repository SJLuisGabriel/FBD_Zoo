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
import sample.models.VeterinarioDAO;

import java.io.File;

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
        Label lblNombre = new Label("Nombre: ");lblNombre.setId("lblForms");
        txtNombre = new TextField();
        txtNombre.setPromptText("Nombre Del Trabajador");
        txtNombre.setText(objVeterinarioDAO.getNombre());

        Label lblDireccion= new Label("Direccion: ");lblDireccion.setId("lblForms");
        txtDireccion = new TextField();
        txtDireccion.setPromptText("Direccion");
        txtDireccion.setText(objVeterinarioDAO.getDireccion());

        Label lblTelefono = new Label("Telefono: "); lblTelefono.setId("lblForms");
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