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
import sample.compontes.CCUbicacion;
import sample.models.UbicacionDAO;

import java.io.File;

public class UbicacionBD extends Stage {

    private Scene escena;
    private TableView<UbicacionDAO> tbv;
    private Button btnAgregar;
    private VBox vBox;
    private UbicacionDAO objDAO;
    public static int ubi = 0;

    public UbicacionBD(){
        objDAO = new UbicacionDAO();
        CrearUI();
        this.setTitle("UBICACION DEL ANIMAL");
        this.setScene(escena);
        this.show();
    }

    private void CrearUI() {

        tbv = new TableView<>();
        btnAgregar = new Button("Agregar Ubicacion Del Animal");
        objDAO = new UbicacionDAO();
        btnAgregar.setOnAction(event -> {
            new UbicacionForms(tbv, null);
            ubi = 1;
        });

        vBox = new VBox();
        vBox.setSpacing(10.0);
        vBox.setPadding(new Insets(10.0));
        vBox.getChildren().addAll(tbv,btnAgregar);
        escena = new Scene(vBox,455,300);
        CrearTabla();
        btnAgregar.setId("btnGuardar");
        File Filecss = new File("src/sample/style/style2.css");
        escena.getStylesheets().add(Filecss.toURI().toString());
    }

    private void CrearTabla() {
        TableColumn<UbicacionDAO,Integer> tbcCveAnimal = new TableColumn<>("CVE ANIMAL");
        tbcCveAnimal.setCellValueFactory(new PropertyValueFactory<>("CveAnimal"));

        TableColumn<UbicacionDAO,Integer> tbcCveHabitat= new TableColumn<>("CVE HABITAT");
        tbcCveHabitat.setCellValueFactory(new PropertyValueFactory<>("CveHabitat"));

        TableColumn<UbicacionDAO, String> tbcEditar = new TableColumn<>("EDITAR");
        tbcEditar.setCellFactory(new Callback<TableColumn<UbicacionDAO, String>, TableCell<UbicacionDAO, String>>() {
            @Override
            public TableCell<UbicacionDAO, String> call(TableColumn<UbicacionDAO, String> param) {
                return new CCUbicacion(1);
            }
        });

        TableColumn<UbicacionDAO, String> tbcBorrar = new TableColumn<>("BORRAR");
        tbcBorrar.setCellFactory(new Callback<TableColumn<UbicacionDAO, String>, TableCell<UbicacionDAO, String>>() {
            @Override
            public TableCell<UbicacionDAO, String> call(TableColumn<UbicacionDAO, String> param) {
                return new CCUbicacion(2);
            }
        });

        tbv.getColumns().addAll(tbcCveAnimal,tbcCveHabitat,tbcEditar,tbcBorrar);
        tbv.setItems(objDAO.SELECCIONAR_UBICACION());
        tbcCveAnimal.setId("tbcBD");tbcCveHabitat.setId("tbcBD");
        tbcEditar.setId("tbcBD");tbcBorrar.setId("tbcBD");
        tbcCveAnimal.setMinWidth(115);tbcCveHabitat.setMinWidth(115);
    }
}