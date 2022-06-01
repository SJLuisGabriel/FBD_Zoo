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
import sample.compontes.CCTipoRecorrido;
import sample.models.TipoRecoDAO;

import java.io.File;

public class TipoRecoBD extends Stage {

    private Scene escena;
    private TableView<TipoRecoDAO> tbv;
    private Button btnAgregar;
    private VBox vBox;
    private TipoRecoDAO objDAO;

    public TipoRecoBD(){
        objDAO = new TipoRecoDAO();
        CrearUI();
        this.setTitle("TIPO DE RECORRIDO");
        this.setScene(escena);
        this.show();
    }

    private void CrearUI() {

        tbv = new TableView<>();
        btnAgregar = new Button("Agregar Un Tipo De Recorrido");
        objDAO = new TipoRecoDAO();
        btnAgregar.setOnAction(event -> {
            new TipoRecoForms(tbv, null);
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
        TableColumn<TipoRecoDAO,Integer> tbcCveTipoRecorrido = new TableColumn<>("CLAVE");
        tbcCveTipoRecorrido.setCellValueFactory(new PropertyValueFactory<>("CveTipoRecorrido"));

        TableColumn<TipoRecoDAO,String> tbcDescripcion= new TableColumn<>("DESCRIPCION");
        tbcDescripcion.setCellValueFactory(new PropertyValueFactory<>("descripcion"));

        TableColumn<TipoRecoDAO, String> tbcEditar = new TableColumn<>("EDITAR");
        tbcEditar.setCellFactory(new Callback<TableColumn<TipoRecoDAO, String>, TableCell<TipoRecoDAO, String>>() {
            @Override
            public TableCell<TipoRecoDAO, String> call(TableColumn<TipoRecoDAO, String> param) {
                return new CCTipoRecorrido(1);
            }
        });

        TableColumn<TipoRecoDAO, String> tbcBorrar = new TableColumn<>("BORRAR");
        tbcBorrar.setCellFactory(new Callback<TableColumn<TipoRecoDAO, String>, TableCell<TipoRecoDAO, String>>() {
            @Override
            public TableCell<TipoRecoDAO, String> call(TableColumn<TipoRecoDAO, String> param) {
                return new CCTipoRecorrido(2);
            }
        });


        tbv.getColumns().addAll(tbcCveTipoRecorrido,tbcDescripcion,tbcEditar,tbcBorrar);
        tbv.setItems(objDAO.SELECCIONAR_TIPORECORRIDO());
        tbcCveTipoRecorrido.setId("tbcBD");tbcDescripcion.setId("tbcBD");
        tbcEditar.setId("tbcBD");tbcBorrar.setId("tbcBD");
        tbcCveTipoRecorrido.setMinWidth(65);tbcDescripcion.setMinWidth(130);
    }
}