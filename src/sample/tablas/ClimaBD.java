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
import sample.compontes.CCClima;
import sample.models.ClimaDAO;
import java.io.File;


public class ClimaBD extends Stage {

    private Scene escena;
    private TableView<ClimaDAO> tbv;
    private Button btnAgregar;
    private VBox vBox;
    private ClimaDAO objDAO;

    public ClimaBD(){
        objDAO = new ClimaDAO();
        CrearUI();
        this.setTitle("CLIMA DEL HABITAT");
        this.setScene(escena);
        this.show();
    }

    private void CrearUI() {

        tbv = new TableView<>();
        btnAgregar = new Button("Agregar Un Clima");
        objDAO = new ClimaDAO();
        btnAgregar.setOnAction(event -> {
            new ClimaForms(tbv, null);
        });

        vBox = new VBox();
        vBox.setSpacing(10.0);
        vBox.setPadding(new Insets(10.0));
        vBox.getChildren().addAll(tbv,btnAgregar);
        escena = new Scene(vBox,408,300);
        CrearTabla();
        btnAgregar.setId("btnGuardar");
        File Filecss = new File("src/sample/style/style2.css");
        escena.getStylesheets().add(Filecss.toURI().toString());
    }

    private void CrearTabla() {
        TableColumn<ClimaDAO,Integer> tbcCveClima = new TableColumn<>("CLAVE");
        tbcCveClima.setCellValueFactory(new PropertyValueFactory<>("CveClima"));

        TableColumn<ClimaDAO,String> tbcDescripcion = new TableColumn<>("DESCRIPCION");
        tbcDescripcion.setCellValueFactory(new PropertyValueFactory<>("descripcion"));

        TableColumn<ClimaDAO, String> tbcEditar = new TableColumn<>("EDITAR");
        tbcEditar.setCellFactory(new Callback<TableColumn<ClimaDAO, String>, TableCell<ClimaDAO, String>>() {
            @Override
            public TableCell<ClimaDAO, String> call(TableColumn<ClimaDAO, String> param) {
                return new CCClima(1);
            }
        });

        TableColumn<ClimaDAO, String> tbcBorrar = new TableColumn<>("BORRAR");
        tbcBorrar.setCellFactory(new Callback<TableColumn<ClimaDAO, String>, TableCell<ClimaDAO, String>>() {
            @Override
            public TableCell<ClimaDAO, String> call(TableColumn<ClimaDAO, String> param) {
                return new CCClima(2);
            }
        });

        tbv.getColumns().addAll(tbcCveClima,tbcDescripcion,tbcEditar,tbcBorrar);
        tbv.setItems(objDAO.SELECCIONAR_CLIMA());
        tbcCveClima.setId("tbcBD");tbcDescripcion.setId("tbcBD");tbcEditar.setId("tbcBD");
        tbcBorrar.setId("tbcBD");
        tbcCveClima.setMinWidth(65);tbcDescripcion.setMinWidth(120);

    }
}