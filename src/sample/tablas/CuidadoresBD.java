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
import sample.compontes.CCCuidadores;
import sample.models.CuidadoresDAO;

public class CuidadoresBD extends Stage {

    private Scene escena;
    private TableView<CuidadoresDAO> tbvCuidores;
    private Button btnAgregar;
    private VBox vBox;
    private CuidadoresDAO cuidDAO;

    public CuidadoresBD(){
        cuidDAO = new CuidadoresDAO();
        CrearUI();
        this.setTitle("Empleados Cuidadores");
        this.setScene(escena);
        this.show();
    }

    private void CrearUI() {

        tbvCuidores = new TableView<>();
        btnAgregar = new Button("Agregar Empleado Cuidador");
        cuidDAO = new CuidadoresDAO();
        btnAgregar.setOnAction(event -> {
            new CuidadoresForms(tbvCuidores, null);
        });

        vBox = new VBox();
        vBox.setSpacing(10.0);
        vBox.setPadding(new Insets(10.0));
        vBox.getChildren().addAll(tbvCuidores,btnAgregar);
        escena = new Scene(vBox,408,300);
        CrearTabla();
    }

    private void CrearTabla() {
        TableColumn<CuidadoresDAO,Integer> tbcCveCuidador = new TableColumn<>("CLAVE");
        tbcCveCuidador.setCellValueFactory(new PropertyValueFactory<>("CveCuidadores"));

        TableColumn<CuidadoresDAO,String> tbcNombre = new TableColumn<>("NOMBRE");
        tbcNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));

        TableColumn<CuidadoresDAO,String> tbcDireccion = new TableColumn<>("DIRECCION");
        tbcDireccion.setCellValueFactory(new PropertyValueFactory<>("Direccion"));

        TableColumn<CuidadoresDAO,String> tbcTelefono= new TableColumn<>("TELEFONO");
        tbcTelefono.setCellValueFactory(new PropertyValueFactory<>("Telefono"));

        TableColumn<CuidadoresDAO, String> tbcEditar = new TableColumn<>("EDITAR");
        tbcEditar.setCellFactory(new Callback<TableColumn<CuidadoresDAO, String>, TableCell<CuidadoresDAO, String>>() {
            @Override
            public TableCell<CuidadoresDAO, String> call(TableColumn<CuidadoresDAO, String> param) {
                return new CCCuidadores(1);
            }
        });

        TableColumn<CuidadoresDAO, String> tbcBorrar = new TableColumn<>("BORRAR");
        tbcBorrar.setCellFactory(new Callback<TableColumn<CuidadoresDAO, String>, TableCell<CuidadoresDAO, String>>() {
            @Override
            public TableCell<CuidadoresDAO, String> call(TableColumn<CuidadoresDAO, String> param) {
                return new CCCuidadores(2);
            }
        });


        tbvCuidores.getColumns().addAll(tbcCveCuidador,tbcNombre,tbcDireccion,tbcTelefono,tbcEditar,tbcBorrar);
        tbvCuidores.setItems(cuidDAO.SELECCIONAR_CUIDADORES());

    }
}