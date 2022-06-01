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
import sample.models.RecorridosDAO;

import java.io.File;

public class RecorridosForms extends Stage {

    private Scene escena;
    private VBox vBox;
    private TextField txtRecorrido,txtHoraS,txtCveTipoRec,txtCveGuia,txtCveHabitat;
    private Label lblRecorrido,lblHoraS,lblCveTipoRec,lblCveGuia,lblCveHabitat;
    private Button btnGuardar;
    private RecorridosDAO objDAO;
    private TableView<RecorridosDAO> tbv;

    public RecorridosForms(TableView<RecorridosDAO> tbv, RecorridosDAO objDAO){
        this.tbv = tbv;
        if( objDAO != null )
            this.objDAO = objDAO;
        else
            this.objDAO = new RecorridosDAO();
        CrearUI();
        this.setTitle("Formulario De Recorridos");
        this.setScene(escena);
        this.show();
    }

    private void CrearUI() {

        txtRecorrido = new TextField();
        lblRecorrido = new Label("Recorrido: ");lblRecorrido.setId("lblForms");
        txtRecorrido.setPromptText("Recorrido");
        txtRecorrido.setText(objDAO.getRecorrido());

        txtHoraS = new TextField();
        lblHoraS = new Label("HoraSalida: ");lblHoraS.setId("lblForms");
        txtHoraS.setPromptText("HH:MM:SS");
        txtHoraS.setText(objDAO.getHoraSalida());

        txtCveTipoRec = new TextField();
        lblCveTipoRec = new Label("CVE Tipo: ");lblCveTipoRec.setId("lblForms");
        txtCveTipoRec.setPromptText("Clave");
        txtCveTipoRec.setText(String.valueOf(objDAO.getCveTipoRecorrido()));

        txtCveGuia = new TextField();
        lblCveGuia = new Label("CVE Guia: ");lblCveGuia.setId("lblForms");
        txtCveGuia.setPromptText("Clave");
        txtCveGuia.setText(String.valueOf(objDAO.getCveGuia()));

        txtCveHabitat = new TextField();
        lblCveHabitat = new Label("CVE Habitat: ");lblCveHabitat.setId("lblForms");
        txtCveHabitat.setPromptText("Clave");
        txtCveHabitat.setText(String.valueOf(objDAO.getCveHabitat()));

        btnGuardar = new Button("GUARDAR");

        btnGuardar.setOnAction(event -> {
            objDAO.setRecorrido(txtRecorrido.getText());
            objDAO.setHoraSalida(txtHoraS.getText());
            objDAO.setCveTipoRecorrido(Integer.parseInt(txtCveTipoRec.getText()));
            objDAO.setCveGuia(Integer.parseInt(txtCveGuia.getText()));
            objDAO.setCveHabitat(Integer.parseInt(txtCveHabitat.getText()));

            if(objDAO.getCveRecorridos() > 0 )
                objDAO.ACTUALIZAR_RECORRIDOS();
            else
                objDAO.INSERTAR_RECORRIDOS();

            tbv.setItems(objDAO.SELECCIONAR_RECORRIDOS());
            tbv.refresh();

            this.close();
        });

        HBox hBox1 = new HBox();
        VBox vBox2 = new VBox(); VBox vBox3 = new VBox();
        vBox2.getChildren().addAll(lblRecorrido,lblHoraS,lblCveTipoRec,lblCveGuia,lblCveHabitat);
        vBox3.getChildren().addAll(txtRecorrido,txtHoraS,txtCveTipoRec,txtCveGuia,txtCveHabitat);
        hBox1.getChildren().addAll(vBox2,vBox3);
        vBox2.setSpacing(14); vBox3.setSpacing(8); vBox3.setMinWidth(200);
        vBox2.setPadding(new Insets(4,0,0,0));
        btnGuardar.setId("btnGuardar");

        vBox = new VBox();
        vBox.setSpacing(15);
        vBox.setPadding(new Insets(25));
        vBox.getChildren().addAll(hBox1,btnGuardar);
        escena = new Scene(vBox,350 ,250);
        File Filecss = new File("src/sample/style/style2.css");
        escena.getStylesheets().add(Filecss.toURI().toString());
    }
}