package sample.compontes;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableCell;
import sample.models.ClimaDAO;
import sample.tablas.ClimaForms;

import java.util.Optional;

public class CCClima extends TableCell<ClimaDAO, String> {
    private Button btnCelda;
    private  int opc;
    private ClimaDAO ObjDAO;

    public CCClima(int opc){
        this.opc = opc;
        if( opc == 1) {
            btnCelda = new Button("EDITAR");
            btnCelda.setOnAction(event -> {
                ObjDAO = CCClima.this.getTableView().getItems().get(CCClima.this.getIndex());
                new ClimaForms(CCClima.this.getTableView(), ObjDAO);
            });
        }else{
            btnCelda = new Button("BORRAR");
            btnCelda.setOnAction(event -> {
                Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
                alerta.setTitle("Mensaje del Sistema");
                alerta.setHeaderText("Confirmar De La Acción");
                alerta.setContentText("¿Realmente Deseas Borrar Este Clima?");
                Optional<ButtonType> result = alerta.showAndWait();

                if(result.get() == ButtonType.OK ){
                    ObjDAO = CCClima.this.getTableView().getItems().get(CCClima.this.getIndex());
                    ObjDAO.ELIMINAR_CLIMA();
                    CCClima.this.getTableView().setItems(ObjDAO.SELECCIONAR_CLIMA());
                    CCClima.this.getTableView().refresh();
                }
            });
        }
    }

    @Override
    protected void updateItem(String item,boolean empty){
        super.updateItem(item,empty);
        if(!empty){
            setGraphic(btnCelda);
        }
    }
}