package sample;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import sample.models.Conexion;
import sample.tablas.EspecieBD;

public class Main extends Application{

    private Scene escena;
    private VBox vBox;
    private Button btnAlimentacion, btnAnimales, bntAseo, btnClima, btnCuidadores, btnCuidados,
            btnEspecie, btnGuias, btnHabitat, btnMedicina, btnProveedor, btnRecorridos,
            btnTipoRecorridos, btnUbicacion, btnVeterinario;

    @Override
    public void start(Stage primaryStage) {

        CrearUI();
        primaryStage.setScene(escena);
        primaryStage.setTitle("Bases De Datos");
        primaryStage.show();
    }

    private void CrearUI() {

        vBox = new VBox();
        btnAlimentacion = new Button("ALIMENTACIÓN");
        bntAseo = new Button("ASEO");
        btnCuidadores = new Button("CUIDADORES");
        btnAnimales = new Button("ANIMAES");
        btnClima = new Button("CLIMA");
        btnCuidados = new Button("CUIDADOS ESPECIALES");
        btnEspecie = new Button("ESPECIE");
        btnGuias = new Button("GUIAS");
        btnHabitat = new Button("HABITAT");
        btnMedicina = new Button("MEDICINA");
        btnProveedor = new Button("PROVEEDOR");
        btnRecorridos = new Button("RECORRIDOS");
        btnTipoRecorridos = new Button("TIPOS DE RECORRIDOS");
        btnUbicacion = new Button("UBICACION DEL ANIMAL");
        btnVeterinario = new Button("VETERINARIO");

        btnEspecie.setOnAction(event -> new EspecieBD());

        Conexion.crearConexion();
        vBox.getChildren().addAll(btnGuias, btnTipoRecorridos, btnRecorridos, btnClima,
                btnHabitat, btnUbicacion, btnAnimales, bntAseo, btnEspecie, btnCuidadores,
                btnCuidados, btnAlimentacion, btnVeterinario, btnMedicina, btnProveedor);
        escena = new Scene(vBox, 420, 150);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
