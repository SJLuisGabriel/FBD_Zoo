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
import sample.models.AnimalesDAO;

import java.io.File;

public class AnimalesForms extends Stage {

    private Scene escena;
    private VBox vBox;
    private TextField txtNombre,txtFecha,txtSexo,txtZona,txtNomCient,txtCveAlimentacion,
            txtCveVeterinario,txtCveEspeie;
    private Label lblNombre,lblFecha,lblSexo,lblZona,lblNomCient,lblCveAlimentacion,
            lblCveVeterinario,lblCveEspeie;
    private Button btnGuardar;
    private AnimalesDAO objDAO;
    private TableView<AnimalesDAO> tbv;

    public AnimalesForms(TableView<AnimalesDAO> tbv, AnimalesDAO objDAO){
        this.tbv = tbv;
        if( objDAO != null )
            this.objDAO = objDAO;
        else
            this.objDAO = new AnimalesDAO();
        CrearUI();
        this.setTitle("Formulario De Animales");
        this.setScene(escena);
        this.show();
    }

    private void CrearUI() {

        txtNombre = new TextField();
        lblNombre = new Label("Nombre: ");lblNombre.setId("lblForms");
        txtNombre.setPromptText("Nombre Del Animal");
        txtNombre.setText(objDAO.getNombre());

        txtFecha = new TextField();
        lblFecha = new Label("Fecha Nacimiento: ");lblFecha.setId("lblForms");
        txtFecha.setPromptText("YYYY-MM-DD");
        txtFecha.setText(objDAO.getFecNac());

        txtSexo = new TextField();
        lblSexo = new Label("Sexo: ");lblSexo.setId("lblForms");
        txtSexo.setPromptText("H (Hembra) - M (Macho)");
        txtSexo.setText(objDAO.getSexo());

        txtZona = new TextField();
        lblZona = new Label("Zona De Origen: ");lblZona.setId("lblForms");
        txtZona.setPromptText("Pais");
        txtZona.setText(objDAO.getZonaOrigen());

        txtNomCient = new TextField();
        lblNomCient = new Label("Nombre Cientifico: ");lblNomCient.setId("lblForms");
        txtNomCient.setPromptText("Nombre");
        txtNomCient.setText(objDAO.getNombreCientifico());

        txtCveAlimentacion = new TextField();
        lblCveAlimentacion = new Label("CVE Alimentacion: ");lblCveAlimentacion.setId("lblForms");
        txtCveAlimentacion.setPromptText("Clave");
        txtCveAlimentacion.setText(String.valueOf(objDAO.getCveAlimentacion()));

        txtCveVeterinario = new TextField();
        lblCveVeterinario = new Label("CVE Veterinario: ");lblCveVeterinario.setId("lblForms");
        txtCveVeterinario.setPromptText("Clave");
        txtCveVeterinario.setText(String.valueOf(objDAO.getCveVeterinario()));

        txtCveEspeie = new TextField();
        lblCveEspeie = new Label("CVE Especie: ");lblCveEspeie.setId("lblForms");
        txtCveEspeie.setPromptText("Clave");
        txtCveEspeie.setText(String.valueOf(objDAO.getCveEspecie()));

        btnGuardar = new Button("GUARDAR");

        btnGuardar.setOnAction(event -> {
            objDAO.setNombre(txtNombre.getText());
            objDAO.setFecNac(txtFecha.getText());
            objDAO.setSexo(txtSexo.getText());
            objDAO.setZonaOrigen(txtZona.getText());
            objDAO.setNombreCientifico(txtNomCient.getText());
            objDAO.setCveAlimentacion(Integer.parseInt(txtCveAlimentacion.getText()));
            objDAO.setCveVeterinario(Integer.parseInt(txtCveVeterinario.getText()));
            objDAO.setCveEspecie(Integer.parseInt(txtCveEspeie.getText()));

            if(objDAO.getCveAnimal() > 0 )
                objDAO.ACTUALIZAR_ANIMALES();
            else
                objDAO.INSERTAR_ANIMALES();

            tbv.setItems(objDAO.SELECCIONAR_ANIMALES());
            tbv.refresh();

            this.close();
        });

        HBox hBox1 = new HBox();
        VBox vBox2 = new VBox(); VBox vBox3 = new VBox();
        vBox2.getChildren().addAll(lblNombre,lblFecha,lblSexo,lblZona,lblNomCient,lblCveAlimentacion,
                lblCveVeterinario,lblCveEspeie);
        vBox3.getChildren().addAll(txtNombre,txtFecha,txtSexo,txtZona,txtNomCient,txtCveAlimentacion,
                txtCveVeterinario,txtCveEspeie);
        hBox1.getChildren().addAll(vBox2,vBox3);
        vBox2.setSpacing(14); vBox3.setSpacing(8); vBox3.setMinWidth(200);
        vBox2.setPadding(new Insets(4,0,0,0));
        btnGuardar.setId("btnGuardar");

        vBox = new VBox();
        vBox.setSpacing(15);
        vBox.setPadding(new Insets(25));
        vBox.getChildren().addAll(hBox1,btnGuardar);
        escena = new Scene(vBox,400 ,340);
        File Filecss = new File("src/sample/style/style2.css");
        escena.getStylesheets().add(Filecss.toURI().toString());
    }
}
