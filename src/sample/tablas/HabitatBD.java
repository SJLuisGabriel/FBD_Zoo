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
import sample.compontes.CCHabitat;
import sample.models.HabitatDAO;

import java.io.File;

public class HabitatBD extends Stage {

    private Scene escena;
    private TableView<HabitatDAO> tbv;
    private Button btnAgregar;
    private VBox vBox;
    private HabitatDAO objDAO;

    public HabitatBD(){
        objDAO = new HabitatDAO();
        CrearUI();
        this.setTitle("Habitat");
        this.setScene(escena);
        this.show();
    }

    private void CrearUI() {

        tbv = new TableView<>();
        btnAgregar = new Button("Agregar Habitat");
        objDAO = new HabitatDAO();
        btnAgregar.setOnAction(event -> {
            new HabitatForms(tbv, null);
        });

        vBox = new VBox();
        vBox.setSpacing(10.0);
        vBox.setPadding(new Insets(10.0));
        vBox.getChildren().addAll(tbv,btnAgregar);
        escena = new Scene(vBox,520,300);
        CrearTabla();
        btnAgregar.setId("btnGuardar");
        File Filecss = new File("src/sample/style/style2.css");
        escena.getStylesheets().add(Filecss.toURI().toString());
    }

    private void CrearTabla() {
        TableColumn<HabitatDAO,Integer> tbcCveHabitat = new TableColumn<>("CLAVE");
        tbcCveHabitat.setCellValueFactory(new PropertyValueFactory<>("CveHabitat"));

        TableColumn<HabitatDAO,String> tbcDescripcion = new TableColumn<>("DESCRIPCION");
        tbcDescripcion.setCellValueFactory(new PropertyValueFactory<>("descripcion"));

        TableColumn<HabitatDAO,Integer> tbcCveClima = new TableColumn<>("CVE CLIMA");
        tbcCveClima.setCellValueFactory(new PropertyValueFactory<>("CveClima"));

        TableColumn<HabitatDAO, String> tbcEditar = new TableColumn<>("EDITAR");
        tbcEditar.setCellFactory(new Callback<TableColumn<HabitatDAO, String>, TableCell<HabitatDAO, String>>() {
            @Override
            public TableCell<HabitatDAO, String> call(TableColumn<HabitatDAO, String> param) {
                return new CCHabitat(1);
            }
        });

        TableColumn<HabitatDAO, String> tbcBorrar = new TableColumn<>("BORRAR");
        tbcBorrar.setCellFactory(new Callback<TableColumn<HabitatDAO, String>, TableCell<HabitatDAO, String>>() {
            @Override
            public TableCell<HabitatDAO, String> call(TableColumn<HabitatDAO, String> param) {
                return new CCHabitat(2);
            }
        });


        tbv.getColumns().addAll(tbcCveHabitat,tbcDescripcion,tbcCveClima,tbcEditar,tbcBorrar);
        tbv.setItems(objDAO.SELECCIONAR_HABITAT());
        tbcCveHabitat.setId("tbcBD");tbcDescripcion.setId("tbcBD");tbcCveClima.setId("tbcBD");
        tbcEditar.setId("tbcBD");tbcBorrar.setId("tbcBD");
        tbcCveHabitat.setMinWidth(65);tbcDescripcion.setMinWidth(125);tbcCveClima.setMinWidth(110);
    }
}