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
import sample.compontes.CCCuidados;
import sample.models.CuidadosDAO;

import java.io.File;

public class CuidadosBD extends Stage {

    private Scene escena;
    private TableView<CuidadosDAO> tbv;
    private Button btnAgregar;
    private VBox vBox;
    private CuidadosDAO objDAO;
    public static int cui = 0;

    public CuidadosBD(){
        objDAO = new CuidadosDAO();
        CrearUI();
        this.setTitle("Cuidados del Animal");
        this.setScene(escena);
        this.show();
    }

    private void CrearUI() {

        tbv = new TableView<>();
        btnAgregar = new Button("Agregar Cuidados Del Animal");
        objDAO = new CuidadosDAO();
        btnAgregar.setOnAction(event -> {
            new CuidadosForms(tbv, null);
            cui = 1;
        });

        vBox = new VBox();
        vBox.setSpacing(10.0);
        vBox.setPadding(new Insets(10.0));
        vBox.getChildren().addAll(tbv,btnAgregar);
        escena = new Scene(vBox,700,300);
        CrearTabla();
        btnAgregar.setId("btnGuardar");
        File Filecss = new File("src/sample/style/style2.css");
        escena.getStylesheets().add(Filecss.toURI().toString());
    }

    private void CrearTabla() {
        TableColumn<CuidadosDAO,Integer> tbcCveAnimal = new TableColumn<>("CVE ANIMAL");
        tbcCveAnimal.setCellValueFactory(new PropertyValueFactory<>("CveAnimal"));

        TableColumn<CuidadosDAO,Integer> tbcCveCuidadores = new TableColumn<>("CVE CUIDADORES");
        tbcCveCuidadores.setCellValueFactory(new PropertyValueFactory<>("CveCuidadores"));

        TableColumn<CuidadosDAO,String> tbcCuidados = new TableColumn<>("CUIDADOS");
        tbcCuidados.setCellValueFactory(new PropertyValueFactory<>("cuidados"));

        TableColumn<CuidadosDAO, String> tbcEditar = new TableColumn<>("EDITAR");
        tbcEditar.setCellFactory(new Callback<TableColumn<CuidadosDAO, String>, TableCell<CuidadosDAO, String>>() {
            @Override
            public TableCell<CuidadosDAO, String> call(TableColumn<CuidadosDAO, String> param) {
                return new CCCuidados(1);
            }
        });

        TableColumn<CuidadosDAO, String> tbcBorrar = new TableColumn<>("BORRAR");
        tbcBorrar.setCellFactory(new Callback<TableColumn<CuidadosDAO, String>, TableCell<CuidadosDAO, String>>() {
            @Override
            public TableCell<CuidadosDAO, String> call(TableColumn<CuidadosDAO, String> param) {
                return new CCCuidados(2);
            }
        });


        tbv.getColumns().addAll(tbcCveAnimal,tbcCveCuidadores,tbcCuidados,tbcEditar,tbcBorrar);
        tbv.setItems(objDAO.SELECCIONAR_CUIDADOS());
        tbcCveAnimal.setId("tbcBD");tbcCveCuidadores.setId("tbcBD");tbcCuidados.setId("tbcBD");
        tbcEditar.setId("tbcBD");tbcBorrar.setId("tbcBD");
        tbcCveAnimal.setMinWidth(110);tbcCveCuidadores.setMinWidth(150);tbcCuidados.setMinWidth(200);
    }
}