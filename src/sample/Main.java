package sample;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import sample.models.Conexion;
import sample.tablas.*;
import java.io.File;

public class Main extends Application{

    private Scene escena;
    private VBox vBox;
    private Button btnAnimales, btnClima, btnCuidadores,
            btnGuias, btnHabitat, btnRecorridos, btnUbicacion, btnVeterinario;
    private Label lblTitulo;
    private int height = 110, widht = 110;

    @Override
    public void start(Stage primaryStage) {

        CrearUI();
        primaryStage.setScene(escena);
        primaryStage.setTitle("Bases De Datos");
        primaryStage.show();
    }

    private void CrearUI() {

        vBox = new VBox();
        lblTitulo = new Label("MENU PRINCIPAL");
        btnCuidadores = new Button();
        btnAnimales = new Button();
        btnClima = new Button();
        btnGuias = new Button();
        btnHabitat = new Button();
        btnRecorridos = new Button();
        btnUbicacion = new Button();
        btnVeterinario = new Button();

        btnGuias.setOnAction(event -> new GuiasBD());
        btnCuidadores.setOnAction(event -> new CuidadoresBD());
        btnVeterinario.setOnAction(event -> new VeterinarioBD());
        btnAnimales.setOnAction(event -> new AnimalesBD());
        btnClima.setOnAction(event ->new ClimaBD());
        btnHabitat.setOnAction(event -> new HabitatBD());
        btnRecorridos.setOnAction(event -> new RecorridosBD());
        btnUbicacion.setOnAction(event -> new UbicacionBD());

        File fileGuia = new File("src/sample/images/guia.png");
        Image imgGuia = new Image(fileGuia.toURI().toString());
        ImageView ViewGuia = new ImageView(imgGuia);
        ViewGuia.setFitHeight(height);ViewGuia.setFitWidth(widht);
        btnGuias.setGraphic(ViewGuia); btnGuias.setId("btnMenu");
        VBox vBoxGuia = new VBox();
        Label lblGuia = new Label("Guias");lblGuia.setId("lblMenus");
        vBoxGuia.getChildren().addAll(btnGuias,lblGuia);
        vBoxGuia.setAlignment(Pos.CENTER);

        File fileHabitat = new File("src/sample/images/habitat.png");
        Image imgHabitat = new Image(fileHabitat.toURI().toString());
        ImageView ViewHabitat = new ImageView(imgHabitat);
        ViewHabitat.setFitHeight(height);ViewHabitat.setFitWidth(widht);
        btnHabitat.setGraphic(ViewHabitat); btnHabitat.setId("btnMenu");
        VBox vBoxHabitat = new VBox();
        Label lblHabitat= new Label("Habitat");lblHabitat.setId("lblMenus");
        vBoxHabitat.getChildren().addAll(btnHabitat,lblHabitat);
        vBoxHabitat.setAlignment(Pos.CENTER);

        File fileRecorridos = new File("src/sample/images/recorridos.png");
        Image imgRecorridos = new Image(fileRecorridos.toURI().toString());
        ImageView ViewRecorridos = new ImageView(imgRecorridos);
        ViewRecorridos.setFitHeight(height);ViewRecorridos.setFitWidth(widht);
        btnRecorridos.setGraphic(ViewRecorridos); btnRecorridos.setId("btnMenu");
        VBox vBoxRecorridos = new VBox();
        Label lblRecorridos= new Label("Recorridos");lblRecorridos.setId("lblMenus");
        vBoxRecorridos.getChildren().addAll(btnRecorridos,lblRecorridos);
        vBoxRecorridos.setAlignment(Pos.CENTER);

        File fileUbicacion = new File("src/sample/images/ubicacion.png");
        Image imgUbicacion = new Image(fileUbicacion.toURI().toString());
        ImageView ViewUbicacion = new ImageView(imgUbicacion);
        ViewUbicacion.setFitHeight(height);ViewUbicacion.setFitWidth(widht);
        btnUbicacion.setGraphic(ViewUbicacion); btnUbicacion.setId("btnMenu");
        VBox vBoxUbicacion = new VBox();
        Label lblUbicacion = new Label("Ubicación");lblUbicacion.setId("lblMenus");
        vBoxUbicacion.getChildren().addAll(btnUbicacion,lblUbicacion);
        vBoxUbicacion.setAlignment(Pos.CENTER);

        File fileCuidadores = new File("src/sample/images/cuidadores.png");
        Image imgCuidadores = new Image(fileCuidadores.toURI().toString());
        ImageView ViewCuidadores = new ImageView(imgCuidadores);
        ViewCuidadores.setFitHeight(height);ViewCuidadores.setFitWidth(widht);
        btnCuidadores.setGraphic(ViewCuidadores); btnCuidadores.setId("btnMenu");
        VBox vBoxCuidadores = new VBox();
        Label lblCuidadores = new Label("Cuidadores");lblCuidadores.setId("lblMenus");
        vBoxCuidadores.getChildren().addAll(btnCuidadores,lblCuidadores);
        vBoxCuidadores.setAlignment(Pos.CENTER);

        File fileAnimales = new File("src/sample/images/animales.png");
        Image imgAnimales = new Image(fileAnimales.toURI().toString());
        ImageView ViewAnimales = new ImageView(imgAnimales);
        ViewAnimales.setFitHeight(height);ViewAnimales.setFitWidth(widht);
        btnAnimales.setGraphic(ViewAnimales); btnAnimales.setId("btnMenu");
        VBox vBoxAnimales = new VBox();
        Label lblAnimales = new Label("Animales");lblAnimales.setId("lblMenus");
        vBoxAnimales.getChildren().addAll(btnAnimales,lblAnimales);
        vBoxAnimales.setAlignment(Pos.CENTER);

        File fileClima = new File("src/sample/images/clima.png");
        Image imgClima = new Image(fileClima.toURI().toString());
        ImageView ViewClima = new ImageView(imgClima);
        ViewClima.setFitHeight(height);ViewClima.setFitWidth(widht);
        btnClima.setGraphic(ViewClima); btnClima.setId("btnMenu");
        VBox vBoxClima = new VBox();
        Label lblClima = new Label("Clima");lblClima.setId("lblMenus");
        vBoxClima.getChildren().addAll(btnClima,lblClima);
        vBoxClima.setAlignment(Pos.CENTER);

        File fileVeterinario = new File("src/sample/images/veterinario.png");
        Image imgVeterinario = new Image(fileVeterinario.toURI().toString());
        ImageView ViewVeterinacio = new ImageView(imgVeterinario);
        ViewVeterinacio.setFitHeight(height);ViewVeterinacio.setFitWidth(widht);
        btnVeterinario.setGraphic(ViewVeterinacio); btnVeterinario.setId("btnMenu");
        VBox vBoxVeterinario = new VBox();
        Label lblVeterinario = new Label("Veterinario");lblVeterinario.setId("lblMenus");
        vBoxVeterinario.getChildren().addAll(btnVeterinario,lblVeterinario);
        vBoxVeterinario.setAlignment(Pos.CENTER);

        vBox.setPadding(new Insets(10));
        vBox.setSpacing(20);
        HBox hBox1 = new HBox(); hBox1.setSpacing(90);
        HBox hBox2 = new HBox(); hBox2.setSpacing(90);
        HBox hBox3 = new HBox(); hBox3.setSpacing(90);
        hBox1.setAlignment(Pos.CENTER);hBox2.setAlignment(Pos.CENTER);hBox3.setAlignment(Pos.CENTER);
        hBox1.getChildren().addAll(vBoxGuia,vBoxHabitat,vBoxRecorridos);
        hBox2.getChildren().addAll(vBoxUbicacion,vBoxAnimales,vBoxCuidadores);
        hBox3.getChildren().addAll(vBoxClima,vBoxVeterinario);
        Conexion.crearConexion();
        lblTitulo.setId("Titulo");
        lblTitulo.setPadding(new Insets(5, 5 , 20, 5));
        vBox.getChildren().addAll(lblTitulo,hBox1,hBox2,hBox3);
        escena = new Scene(vBox, 1000, 620);
        File Filecss = new File("src/sample/style/style.css");
        escena.getStylesheets().add(Filecss.toURI().toString());
    }

    public static void main(String[] args) {
        launch(args);
    }
}
