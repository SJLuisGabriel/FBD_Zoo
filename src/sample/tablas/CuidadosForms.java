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
import sample.models.CuidadosDAO;
import static sample.tablas.CuidadosBD.cui;

import java.io.File;

public class CuidadosForms extends Stage {

    private Scene escena;
    private VBox vBox;
    private TextField txtCveAnimal,txtCveCuidadores,txtCuidados;
    private Label lblCveAnimal,lblCveCuidadores,lblCuidados;
    private Button btnGuardar;
    private CuidadosDAO objDAO;
    private TableView<CuidadosDAO> tbv;

    public CuidadosForms(TableView<CuidadosDAO> tbv, CuidadosDAO objDAO){
        this.tbv = tbv;
        if( objDAO != null )
            this.objDAO = objDAO;
        else
            this.objDAO = new CuidadosDAO();
        CrearUI();
        this.setTitle("Formulario De Cuidados");
        this.setScene(escena);
        this.show();
    }

    private void CrearUI() {

        txtCveAnimal = new TextField();
        lblCveAnimal = new Label("CVE Animal: ");lblCveAnimal.setId("lblForms");
        txtCveAnimal.setPromptText("Clave");
        txtCveAnimal.setText(String.valueOf(objDAO.getCveAnimal()));

        txtCveCuidadores = new TextField();
        lblCveCuidadores = new Label("CVE Cuidadores: ");lblCveCuidadores.setId("lblForms");
        txtCveCuidadores.setPromptText("Clave");
        txtCveCuidadores.setText(String.valueOf(objDAO.getCveCuidadores()));

        txtCuidados = new TextField();
        lblCuidados = new Label("Cuidados: ");lblCuidados.setId("lblForms");
        txtCuidados.setPromptText("Descripcion");
        txtCuidados.setText(objDAO.getCuidados());

        btnGuardar = new Button("GUARDAR");

        btnGuardar.setOnAction(event -> {
            objDAO.setCveAnimal(Integer.parseInt(txtCveAnimal.getText()));
            objDAO.setCveCuidadores(Integer.parseInt(txtCveCuidadores.getText()));
            objDAO.setCuidados(txtCuidados.getText());
            System.out.println(cui);
            if(cui == 2)
                objDAO.ACTUALIZAR_CUIDADOS();
            else
                objDAO.INSERTAR_CUIDADOS();
            cui = 0;
            tbv.setItems(objDAO.SELECCIONAR_CUIDADOS());
            tbv.refresh();

            this.close();
        });

        HBox hBox1 = new HBox();
        VBox vBox2 = new VBox(); VBox vBox3 = new VBox();
        vBox2.getChildren().addAll(lblCveAnimal,lblCveCuidadores,lblCuidados);
        vBox3.getChildren().addAll(txtCveAnimal,txtCveCuidadores,txtCuidados);
        hBox1.getChildren().addAll(vBox2,vBox3);
        vBox2.setSpacing(14); vBox3.setSpacing(8); vBox3.setMinWidth(200);
        vBox2.setPadding(new Insets(4,0,0,0));
        btnGuardar.setId("btnGuardar");

        vBox = new VBox();
        vBox.setSpacing(15);
        vBox.setPadding(new Insets(25));
        vBox.getChildren().addAll(hBox1,btnGuardar);
        escena = new Scene(vBox,380 ,180);
        File Filecss = new File("src/sample/style/style2.css");
        escena.getStylesheets().add(Filecss.toURI().toString());
    }
}