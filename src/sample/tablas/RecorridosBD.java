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
import sample.compontes.CCRecorridos;
import sample.models.RecorridosDAO;
import java.io.File;

public class RecorridosBD extends Stage {

    private Scene escena;
    private TableView<RecorridosDAO> tbv;
    private Button btnAgregar;
    private VBox vBox;
    private RecorridosDAO objDAO;

    public RecorridosBD(){
        objDAO = new RecorridosDAO();
        CrearUI();
        this.setTitle("RECORRIDOS");
        this.setScene(escena);
        this.show();
    }

    private void CrearUI() {

        tbv = new TableView<>();
        btnAgregar = new Button("Agregar Recorrido");
        objDAO = new RecorridosDAO();
        btnAgregar.setOnAction(event -> {
            new RecorridosForms(tbv, null);
        });

        Button btnTipo = new Button();
        Label lblTipo = new Label("Tipo de Recorrido");lblTipo.setId("lblMenus");
        File fileGuia = new File("src/sample/images/tiporeco.png");
        Image imgGuia = new Image(fileGuia.toURI().toString());
        ImageView ViewGuia = new ImageView(imgGuia);
        ViewGuia.setFitHeight(30);ViewGuia.setFitWidth(30);
        btnTipo.setGraphic(ViewGuia); btnTipo.setId("btnMenu");
        HBox hBox1 = new HBox();
        hBox1.getChildren().addAll(lblTipo,btnTipo);
        hBox1.setAlignment(Pos.CENTER_RIGHT);
        btnTipo.setOnAction(event -> new TipoRecoBD());

        vBox = new VBox();
        vBox.setSpacing(10.0);
        vBox.setPadding(new Insets(10.0));
        vBox.getChildren().addAll(hBox1,tbv,btnAgregar);
        escena = new Scene(vBox,855,300);
        CrearTabla();
        btnAgregar.setId("btnGuardar");
        File Filecss = new File("src/sample/style/style2.css");
        escena.getStylesheets().add(Filecss.toURI().toString());
    }

    private void CrearTabla() {
        TableColumn<RecorridosDAO,Integer> tbcCveRecorridos = new TableColumn<>("CLAVE");
        tbcCveRecorridos.setCellValueFactory(new PropertyValueFactory<>("CveRecorridos"));

        TableColumn<RecorridosDAO,String> tbcRecorrido = new TableColumn<>("RECORRIDO");
        tbcRecorrido.setCellValueFactory(new PropertyValueFactory<>("recorrido"));

        TableColumn<RecorridosDAO,String> tbcHora = new TableColumn<>("HORA DE SALIDA");
        tbcHora.setCellValueFactory(new PropertyValueFactory<>("HoraSalida"));

        TableColumn<RecorridosDAO,Integer> tbcCveTipoReco = new TableColumn<>("CVE TIPO");
        tbcCveTipoReco.setCellValueFactory(new PropertyValueFactory<>("CveTipoRecorrido"));

        TableColumn<RecorridosDAO,Integer> tbcCveGuia = new TableColumn<>("CVE GUIA");
        tbcCveGuia.setCellValueFactory(new PropertyValueFactory<>("CveGuia"));

        TableColumn<RecorridosDAO,Integer> tbcCveHabitat = new TableColumn<>("CVE HABITAT");
        tbcCveHabitat.setCellValueFactory(new PropertyValueFactory<>("CveHabitat"));

        TableColumn<RecorridosDAO, String> tbcEditar = new TableColumn<>("EDITAR");
        tbcEditar.setCellFactory(new Callback<TableColumn<RecorridosDAO, String>, TableCell<RecorridosDAO, String>>() {
            @Override
            public TableCell<RecorridosDAO, String> call(TableColumn<RecorridosDAO, String> param) {
                return new CCRecorridos(1);
            }
        });

        TableColumn<RecorridosDAO, String> tbcBorrar = new TableColumn<>("BORRAR");
        tbcBorrar.setCellFactory(new Callback<TableColumn<RecorridosDAO, String>, TableCell<RecorridosDAO, String>>() {
            @Override
            public TableCell<RecorridosDAO, String> call(TableColumn<RecorridosDAO, String> param) {
                return new CCRecorridos(2);
            }
        });

        tbv.getColumns().addAll(tbcCveRecorridos,tbcRecorrido,tbcHora,tbcCveTipoReco,tbcCveGuia,
                tbcCveHabitat,tbcEditar,tbcBorrar);
        tbv.setItems(objDAO.SELECCIONAR_RECORRIDOS());
        tbcCveRecorridos.setId("tbcBD");tbcRecorrido.setId("tbcBD");tbcHora.setId("tbcBD");
        tbcCveTipoReco.setId("tbcBD");tbcCveGuia.setId("tbcBD");tbcCveHabitat.setId("tbcBD");
        tbcEditar.setId("tbcBD");tbcBorrar.setId("tbcBD");
        tbcCveRecorridos.setMinWidth(65);tbcRecorrido.setMinWidth(130);tbcHora.setMinWidth(150);
        tbcCveTipoReco.setMinWidth(90);tbcCveGuia.setMinWidth(90);tbcCveHabitat.setMinWidth(120);
    }
}