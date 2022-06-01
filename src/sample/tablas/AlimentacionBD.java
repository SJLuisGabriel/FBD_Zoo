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
import sample.compontes.CCAlimentacion;
import sample.models.AlimentacionDAO;

import java.io.File;

public class AlimentacionBD extends Stage {

    private Scene escena;
    private TableView<AlimentacionDAO> tbv;
    private Button btnAgregar;
    private VBox vBox;
    private AlimentacionDAO objDAO;

    public AlimentacionBD(){
        objDAO = new AlimentacionDAO();
        CrearUI();
        this.setTitle("Alimentacion del Animal");
        this.setScene(escena);
        this.show();
    }

    private void CrearUI() {

        tbv = new TableView<>();
        btnAgregar = new Button("Agregar Alimentacion Del Animal");
        objDAO = new AlimentacionDAO();
        btnAgregar.setOnAction(event -> {
            new AlimentacionForms(tbv, null);
        });

        vBox = new VBox();
        vBox.setSpacing(10.0);
        vBox.setPadding(new Insets(10.0));
        vBox.getChildren().addAll(tbv,btnAgregar);
        escena = new Scene(vBox,535,300);
        CrearTabla();
        btnAgregar.setId("btnGuardar");
        File Filecss = new File("src/sample/style/style2.css");
        escena.getStylesheets().add(Filecss.toURI().toString());
    }

    private void CrearTabla() {
        TableColumn<AlimentacionDAO,Integer> tbcCveAlimentacion = new TableColumn<>("CLAVE");
        tbcCveAlimentacion.setCellValueFactory(new PropertyValueFactory<>("CveAlimentacion"));

        TableColumn<AlimentacionDAO,String> tbcNombre = new TableColumn<>("NOMBRE");
        tbcNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));

        TableColumn<AlimentacionDAO,String> tbcCantidad = new TableColumn<>("CANTIDAD");
        tbcCantidad.setCellValueFactory(new PropertyValueFactory<>("Cantidad"));

        TableColumn<AlimentacionDAO, String> tbcEditar = new TableColumn<>("EDITAR");
        tbcEditar.setCellFactory(new Callback<TableColumn<AlimentacionDAO, String>, TableCell<AlimentacionDAO, String>>() {
            @Override
            public TableCell<AlimentacionDAO, String> call(TableColumn<AlimentacionDAO, String> param) {
                return new CCAlimentacion(1);
            }
        });

        TableColumn<AlimentacionDAO, String> tbcBorrar = new TableColumn<>("BORRAR");
        tbcBorrar.setCellFactory(new Callback<TableColumn<AlimentacionDAO, String>, TableCell<AlimentacionDAO, String>>() {
            @Override
            public TableCell<AlimentacionDAO, String> call(TableColumn<AlimentacionDAO, String> param) {
                return new CCAlimentacion(2);
            }
        });


        tbv.getColumns().addAll(tbcCveAlimentacion,tbcNombre,tbcCantidad,tbcEditar,tbcBorrar);
        tbv.setItems(objDAO.SELECCIONAR_ALIMENTACION());
        tbcCveAlimentacion.setId("tbcBD");tbcNombre.setId("tbcBD");tbcCantidad.setId("tbcBD");
        tbcEditar.setId("tbcBD");tbcBorrar.setId("tbcBD");
        tbcCveAlimentacion.setMinWidth(65);tbcNombre.setMinWidth(150);tbcCantidad.setMinWidth(100);
    }
}