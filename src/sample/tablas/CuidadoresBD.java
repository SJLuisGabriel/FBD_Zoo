package sample.tablas;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import sample.compontes.CCCuidadores;
import sample.models.CuidadoresDAO;

import java.io.File;

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

        Button btnCuidados = new Button();
        Label lblCuidados = new Label("Cuidados");lblCuidados.setId("lblMenus");
        File fileCuidados= new File("src/sample/images/cuidados.png");
        Image imgCuidados = new Image(fileCuidados.toURI().toString());
        ImageView ViewCuidados = new ImageView(imgCuidados);
        ViewCuidados.setFitHeight(30);ViewCuidados.setFitWidth(30);
        btnCuidados.setGraphic(ViewCuidados); btnCuidados.setId("btnMenu");
        HBox hBox1 = new HBox();
        hBox1.getChildren().addAll(lblCuidados,btnCuidados);
        hBox1.setAlignment(Pos.CENTER_RIGHT);
        btnCuidados.setOnAction(event -> new CuidadosBD());

        vBox = new VBox();
        vBox.setSpacing(10.0);
        vBox.setPadding(new Insets(10.0));
        vBox.getChildren().addAll(hBox1,tbvCuidores,btnAgregar);
        escena = new Scene(vBox,590,300);
        CrearTabla();
        btnAgregar.setId("btnGuardar");
        File Filecss = new File("src/sample/style/style2.css");
        escena.getStylesheets().add(Filecss.toURI().toString());
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
        tbcNombre.setId("tbcBD");tbcCveCuidador.setId("tbcBD");tbcTelefono.setId("tbcBD");
        tbcDireccion.setId("tbcBD");
        tbcEditar.setId("tbcBD");tbcBorrar.setId("tbcBD");
        tbcCveCuidador.setMinWidth(65);tbcNombre.setMinWidth(90);tbcDireccion.setMinWidth(120);

    }
}