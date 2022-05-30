package sample.tablas;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import sample.models.EspecieDAO;

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

        vBox = new VBox();
        vBox.setSpacing(10.0);
        vBox.setPadding(new Insets(10.0));
        vBox.getChildren().addAll(txtClasi,btnGuardar);
        escena = new Scene(vBox,300 ,250);
    }
}
