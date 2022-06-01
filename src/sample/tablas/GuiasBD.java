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
import sample.compontes.CCGuia;
import sample.models.GuiaDAO;

import java.io.File;

public class GuiasBD extends Stage {

    private Scene escena;
    private TableView<GuiaDAO> tbvGuia;
    private Button btnAgregar;
    private VBox vBox;
    private GuiaDAO guiaDAO;

    public GuiasBD(){
        guiaDAO = new GuiaDAO();
        CrearUI();
        this.setTitle("Empleados GUIA");
        this.setScene(escena);
        this.show();
    }

    private void CrearUI() {

        tbvGuia = new TableView<>();
        btnAgregar = new Button("Agregar Empleado Guia");
        guiaDAO = new GuiaDAO();
        btnAgregar.setOnAction(event -> {
            new GuiaForms(tbvGuia, null);
        });

        btnAgregar.setId("btnGuardar");
        vBox = new VBox();
        vBox.setSpacing(10.0);
        vBox.setPadding(new Insets(10.0));
        vBox.getChildren().addAll(tbvGuia,btnAgregar);
        escena = new Scene(vBox,590,300);
        CrearTabla();
        File Filecss = new File("src/sample/style/style2.css");
        escena.getStylesheets().add(Filecss.toURI().toString());
    }

    private void CrearTabla() {
        TableColumn<GuiaDAO,Integer> tbcCveGuia = new TableColumn<>("CLAVE");
        tbcCveGuia.setCellValueFactory(new PropertyValueFactory<>("CveGuia"));

        TableColumn<GuiaDAO,String> tbcNombre = new TableColumn<>("NOMBRE");
        tbcNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));

        TableColumn<GuiaDAO,String> tbcDireccion = new TableColumn<>("DIRECCION");
        tbcDireccion.setCellValueFactory(new PropertyValueFactory<>("direccion"));

        TableColumn<GuiaDAO,String> tbcTelefono= new TableColumn<>("TELEFONO");
        tbcTelefono.setCellValueFactory(new PropertyValueFactory<>("Telefono"));

        TableColumn<GuiaDAO, String> tbcEditar = new TableColumn<>("EDITAR");
        tbcEditar.setCellFactory(new Callback<TableColumn<GuiaDAO, String>, TableCell<GuiaDAO, String>>() {
            @Override
            public TableCell<GuiaDAO, String> call(TableColumn<GuiaDAO, String> param) {
                return new CCGuia(1);
            }
        });

        TableColumn<GuiaDAO, String> tbcBorrar = new TableColumn<>("BORRAR");
        tbcBorrar.setCellFactory(new Callback<TableColumn<GuiaDAO, String>, TableCell<GuiaDAO, String>>() {
            @Override
            public TableCell<GuiaDAO, String> call(TableColumn<GuiaDAO, String> param) {
                return new CCGuia(2);
            }
        });


        tbvGuia.getColumns().addAll(tbcCveGuia,tbcNombre,tbcDireccion,tbcTelefono,tbcEditar,tbcBorrar);
        tbvGuia.setItems(guiaDAO.SELECCIONAR_GUIA());
        tbcNombre.setId("tbcBD");tbcCveGuia.setId("tbcBD");tbcTelefono.setId("tbcBD");
        tbcDireccion.setId("tbcBD");tbcEditar.setId("tbcBD");tbcBorrar.setId("tbcBD");
        tbcCveGuia.setMinWidth(65);tbcNombre.setMinWidth(90);tbcDireccion.setMinWidth(120);
    }
}