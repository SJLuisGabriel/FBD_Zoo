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
import sample.compontes.CCEspecie;
import sample.models.EspecieDAO;

public class EspecieBD extends Stage {

    private Scene escena;
    private TableView<EspecieDAO> tbvEspecie;
    private Button btnAgregar;
    private VBox vBox;
    private EspecieDAO espDAO;

    public EspecieBD(){
        espDAO = new EspecieDAO();
        CrearUI();
        this.setTitle("ESPECIE");
        this.setScene(escena);
        this.show();
    }

    private void CrearUI() {

        tbvEspecie = new TableView<>();
        btnAgregar = new Button("Agregar Especie");
        espDAO = new EspecieDAO();
        btnAgregar.setOnAction(event -> {
            new EspecieForms(tbvEspecie, null);
        });

        vBox = new VBox();
        vBox.setSpacing(10.0);
        vBox.setPadding(new Insets(10.0));
        vBox.getChildren().addAll(tbvEspecie,btnAgregar);
        escena = new Scene(vBox,408,300);
        CrearTabla();
    }

    private void CrearTabla() {
        TableColumn<EspecieDAO,Integer> tbcCveEspecie = new TableColumn<>("CLAVE");
        tbcCveEspecie.setCellValueFactory(new PropertyValueFactory<>("CveEspecie"));

        TableColumn<EspecieDAO,String> tbcClasificacion = new TableColumn<>("CLASIFICACION");
        tbcClasificacion.setCellValueFactory(new PropertyValueFactory<>("clasificacion"));

        TableColumn<EspecieDAO, String> tbcEditar = new TableColumn<>("EDITAR");
        tbcEditar.setCellFactory(new Callback<TableColumn<EspecieDAO, String>, TableCell<EspecieDAO, String>>() {
            @Override
            public TableCell<EspecieDAO, String> call(TableColumn<EspecieDAO, String> param) {
                return new CCEspecie(1);
            }
        });

        TableColumn<EspecieDAO, String> tbcBorrar = new TableColumn<>("BORRAR");
        tbcBorrar.setCellFactory(new Callback<TableColumn<EspecieDAO, String>, TableCell<EspecieDAO, String>>() {
            @Override
            public TableCell<EspecieDAO, String> call(TableColumn<EspecieDAO, String> param) {
                return new CCEspecie(2);
            }
        });


        tbvEspecie.getColumns().addAll(tbcCveEspecie,tbcClasificacion,tbcEditar,tbcBorrar);
        tbvEspecie.setItems(espDAO.SELECCIONAR_ESPECIE());

    }
}