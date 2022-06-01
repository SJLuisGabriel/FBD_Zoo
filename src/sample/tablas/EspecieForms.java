package sample.tablas;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import sample.models.EspecieDAO;

import java.io.File;

public class EspecieForms extends Stage {

    private Scene escena;
    private VBox vBox;
    private TextField txtClasi;
    private Button btnGuardar;
    private EspecieDAO ObjEspDAO;
    private TableView<EspecieDAO> tbvEspecie;

    public EspecieForms(TableView<EspecieDAO> tbvEspecie, EspecieDAO ObjEspDAO){
        this.tbvEspecie = tbvEspecie;
        if( ObjEspDAO != null )
            this.ObjEspDAO = ObjEspDAO;
        else
            this.ObjEspDAO = new EspecieDAO();
        CrearUI();
        this.setTitle("Formulario De Especie");
        this.setScene(escena);
        this.show();
    }

    private void CrearUI() {

        Label lblClasi = new Label("Clasificacion: ");lblClasi.setId("lblForms");
        txtClasi = new TextField();
        txtClasi.setPromptText("Clasificacion");
        txtClasi.setText(ObjEspDAO.getClasificacion());

        btnGuardar = new Button("GUARDAR");

        btnGuardar.setOnAction(event -> {
            ObjEspDAO.setClasificacion(txtClasi.getText());

            if(ObjEspDAO.getCveEspecie() > 0 )
                ObjEspDAO.ACTUALIZAR_ESPECIE();
            else
                ObjEspDAO.INSERTAR_ESPECIE();

            tbvEspecie.setItems(ObjEspDAO.SELECCIONAR_ESPECIE());
            tbvEspecie.refresh();

            this.close();
        });

        HBox hBox1 = new HBox();
        VBox vBox2 = new VBox(); VBox vBox3 = new VBox();
        vBox2.getChildren().addAll(lblClasi);
        vBox3.getChildren().addAll(txtClasi);
        hBox1.getChildren().addAll(vBox2,vBox3);
        vBox2.setSpacing(14); vBox3.setSpacing(8); vBox3.setMinWidth(200);
        vBox2.setPadding(new Insets(4,0,0,0));
        btnGuardar.setId("btnGuardar");

        vBox = new VBox();
        vBox.setSpacing(15);
        vBox.setPadding(new Insets(25));
        vBox.getChildren().addAll(hBox1,btnGuardar);
        escena = new Scene(vBox,350 ,110);
        File Filecss = new File("src/sample/style/style2.css");
        escena.getStylesheets().add(Filecss.toURI().toString());
    }
}
