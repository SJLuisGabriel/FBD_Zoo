package sample.tablas;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import sample.compontes.CCVeterinario;
import sample.models.VeterinarioDAO;

import java.io.File;

public class VeterinarioBD extends Stage {

    private Scene escena;
    private TableView<VeterinarioDAO> tbvVeterinario;
    private Button btnAgregar;
    private VBox vBox;
    private VeterinarioDAO vetDAO;

    public VeterinarioBD(){
        vetDAO = new VeterinarioDAO();
        CrearUI();
        this.setTitle("Empleados Veterinarios");
        this.setScene(escena);
        this.show();
    }

    private void CrearUI() {

        tbvVeterinario = new TableView<>();
        btnAgregar = new Button("Agregar Empleado Veterinario");
        vetDAO = new VeterinarioDAO();
        btnAgregar.setOnAction(event -> {
            new VeterinarioForms(tbvVeterinario, null);
        });

        btnAgregar.setId("btnGuardar");
        vBox = new VBox();
        vBox.setSpacing(10.0);
        vBox.setPadding(new Insets(10.0));
        vBox.getChildren().addAll(tbvVeterinario,btnAgregar);
        escena = new Scene(vBox,590,300);
        CrearTabla();
        File Filecss = new File("src/sample/style/style2.css");
        escena.getStylesheets().add(Filecss.toURI().toString());
    }

    private void CrearTabla() {
        TableColumn<VeterinarioDAO,Integer> tbcCveVeterinario = new TableColumn<>("CLAVE");
        tbcCveVeterinario.setCellValueFactory(new PropertyValueFactory<>("CveVeterinario"));

        TableColumn<VeterinarioDAO,String> tbcNombre = new TableColumn<>("NOMBRE");
        tbcNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));

        TableColumn<VeterinarioDAO,String> tbcDireccion = new TableColumn<>("DIRECCION");
        tbcDireccion.setCellValueFactory(new PropertyValueFactory<>("Direccion"));

        TableColumn<VeterinarioDAO,String> tbcTelefono= new TableColumn<>("TELEFONO");
        tbcTelefono.setCellValueFactory(new PropertyValueFactory<>("Telefono"));

        TableColumn<VeterinarioDAO, String> tbcEditar = new TableColumn<>("EDITAR");
        tbcEditar.setCellFactory(new Callback<TableColumn<VeterinarioDAO, String>, TableCell<VeterinarioDAO, String>>() {
            @Override
            public TableCell<VeterinarioDAO, String> call(TableColumn<VeterinarioDAO, String> param) {
                return new CCVeterinario(1);
            }
        });

        TableColumn<VeterinarioDAO, String> tbcBorrar = new TableColumn<>("BORRAR");
        tbcBorrar.setCellFactory(new Callback<TableColumn<VeterinarioDAO, String>, TableCell<VeterinarioDAO, String>>() {
            @Override
            public TableCell<VeterinarioDAO, String> call(TableColumn<VeterinarioDAO, String> param) {
                return new CCVeterinario(2);
            }
        });


        tbvVeterinario.getColumns().addAll(tbcCveVeterinario,tbcNombre,tbcDireccion,tbcTelefono,tbcEditar,tbcBorrar);
        tbvVeterinario.setItems(vetDAO.SELECCIONAR_VETERINARIO());
        tbcNombre.setId("tbcBD");tbcCveVeterinario.setId("tbcBD");tbcTelefono.setId("tbcBD");
        tbcDireccion.setId("tbcBD");tbcEditar.setId("tbcBD");tbcBorrar.setId("tbcBD");
        tbcCveVeterinario.setMinWidth(65);tbcNombre.setMinWidth(90);tbcDireccion.setMinWidth(120);
    }
}