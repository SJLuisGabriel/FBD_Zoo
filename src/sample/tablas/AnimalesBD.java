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
import sample.compontes.CCAnimales;
import sample.models.AnimalesDAO;

import java.io.File;

public class AnimalesBD extends Stage {

    private Scene escena;
    private TableView<AnimalesDAO> tbv;
    private Button btnAgregar;
    private VBox vBox;
    private AnimalesDAO objDAO;

    public AnimalesBD(){
        objDAO = new AnimalesDAO();
        CrearUI();
        this.setTitle("Animales");
        this.setScene(escena);
        this.show();
    }

    private void CrearUI() {

        tbv = new TableView<>();
        btnAgregar = new Button("Agregar Animal");
        objDAO = new AnimalesDAO();
        btnAgregar.setOnAction(event -> {
            new AnimalesForms(tbv, null);
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

        Button btnEspecie = new Button();
        Label lblEspecie = new Label("Especie");lblEspecie.setId("lblMenus");
        File fileEspecie = new File("src/sample/images/especie.png");
        Image imgEspecie = new Image(fileEspecie.toURI().toString());
        ImageView ViewEspecie = new ImageView(imgEspecie);
        ViewEspecie.setFitHeight(30);ViewEspecie.setFitWidth(30);
        btnEspecie.setGraphic(ViewEspecie); btnEspecie.setId("btnMenu");
        HBox hBox2 = new HBox(); hBox2.setAlignment(Pos.CENTER_RIGHT);
        hBox2.getChildren().addAll(lblEspecie,btnEspecie);
        btnEspecie.setOnAction(event -> new EspecieBD());

        Button btnAlimentacion = new Button();
        Label lblAlimentacion = new Label("Alimentacion");lblAlimentacion.setId("lblMenus");
        File fileAlimentacion= new File("src/sample/images/alimentacion.png");
        Image imgAlimentacion= new Image(fileAlimentacion.toURI().toString());
        ImageView ViewAlimentacion = new ImageView(imgAlimentacion);
        ViewAlimentacion.setFitHeight(30);ViewAlimentacion.setFitWidth(30);
        btnAlimentacion.setGraphic(ViewAlimentacion); btnAlimentacion.setId("btnMenu");
        HBox hBox3 = new HBox(); hBox3.setAlignment(Pos.CENTER_RIGHT);
        hBox3.getChildren().addAll(lblAlimentacion,btnAlimentacion);
        btnAlimentacion.setOnAction(event -> new AlimentacionBD());

        HBox hBox4 = new HBox();hBox4.setSpacing(25); hBox4.setAlignment(Pos.CENTER_RIGHT);
        hBox4.getChildren().addAll(hBox1,hBox2,hBox3);

        vBox = new VBox();
        vBox.setSpacing(10.0);
        vBox.setPadding(new Insets(10.0));
        vBox.getChildren().addAll(hBox4,tbv,btnAgregar);
        escena = new Scene(vBox,1250,300);
        CrearTabla();
        btnAgregar.setId("btnGuardar");
        File Filecss = new File("src/sample/style/style2.css");
        escena.getStylesheets().add(Filecss.toURI().toString());
    }

    private void CrearTabla() {
        TableColumn<AnimalesDAO,Integer> tbcCveAnimal = new TableColumn<>("CLAVE");
        tbcCveAnimal.setCellValueFactory(new PropertyValueFactory<>("CveAnimal"));

        TableColumn<AnimalesDAO,String> tbcNombre = new TableColumn<>("NOMBRE");
        tbcNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));

        TableColumn<AnimalesDAO,String> tbcFecha = new TableColumn<>("NACIMIENTO");
        tbcFecha.setCellValueFactory(new PropertyValueFactory<>("FecNac"));

        TableColumn<AnimalesDAO,String> tbcSexo = new TableColumn<>("SEXO");
        tbcSexo.setCellValueFactory(new PropertyValueFactory<>("sexo"));

        TableColumn<AnimalesDAO,String> tbcZonaOrigen = new TableColumn<>("ORIGEN");
        tbcZonaOrigen.setCellValueFactory(new PropertyValueFactory<>("ZonaOrigen"));

        TableColumn<AnimalesDAO,String> tbcNomCientifico = new TableColumn<>("NOMBRE CIENTIFICO");
        tbcNomCientifico.setCellValueFactory(new PropertyValueFactory<>("nombreCientifico"));

        TableColumn<AnimalesDAO,Integer> tbcCveAlimentacion = new TableColumn<>("CVE ALIMENTACION");
        tbcCveAlimentacion.setCellValueFactory(new PropertyValueFactory<>("CveAlimentacion"));

        TableColumn<AnimalesDAO,Integer> tbcCveVeterinario = new TableColumn<>("CVE VETERINARIO");
        tbcCveVeterinario.setCellValueFactory(new PropertyValueFactory<>("CveVeterinario"));

        TableColumn<AnimalesDAO,Integer> tbcCveEspecie = new TableColumn<>("CVE ESPECIE");
        tbcCveEspecie.setCellValueFactory(new PropertyValueFactory<>("CveEspecie"));

        TableColumn<AnimalesDAO, String> tbcEditar = new TableColumn<>("EDITAR");
        tbcEditar.setCellFactory(new Callback<TableColumn<AnimalesDAO, String>, TableCell<AnimalesDAO, String>>() {
            @Override
            public TableCell<AnimalesDAO, String> call(TableColumn<AnimalesDAO, String> param) {
                return new CCAnimales(1);
            }
        });

        TableColumn<AnimalesDAO, String> tbcBorrar = new TableColumn<>("BORRAR");
        tbcBorrar.setCellFactory(new Callback<TableColumn<AnimalesDAO, String>, TableCell<AnimalesDAO, String>>() {
            @Override
            public TableCell<AnimalesDAO, String> call(TableColumn<AnimalesDAO, String> param) {
                return new CCAnimales(2);
            }
        });


        tbv.getColumns().addAll(tbcCveAnimal,tbcNombre,tbcFecha,tbcSexo,tbcZonaOrigen,tbcNomCientifico,
                tbcCveAlimentacion,tbcCveVeterinario,tbcCveEspecie,tbcEditar,tbcBorrar);
        tbv.setItems(objDAO.SELECCIONAR_ANIMALES());
        tbcCveAnimal.setId("tbcBD");tbcNombre.setId("tbcBD");tbcFecha.setId("tbcBD");
        tbcSexo.setId("tbcBD");tbcZonaOrigen.setId("tbcBD");tbcNomCientifico.setId("tbcBD");
        tbcCveAlimentacion.setId("tbcBD");tbcCveVeterinario.setId("tbcBD");
        tbcEditar.setId("tbcBD");tbcBorrar.setId("tbcBD");tbcCveEspecie.setId("tbcBD");
        tbcCveAnimal.setMinWidth(65);tbcNombre.setMinWidth(90);tbcFecha.setMinWidth(120);
        tbcSexo.setMinWidth(55);tbcZonaOrigen.setMinWidth(100);tbcNomCientifico.setMinWidth(170);
        tbcCveEspecie.setMinWidth(120);
        tbcCveAlimentacion.setMinWidth(160);tbcCveVeterinario.setMinWidth(145);
    }
}