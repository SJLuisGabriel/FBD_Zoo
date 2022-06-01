package sample.compontes;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableCell;
import sample.models.AlimentacionDAO;
import sample.tablas.AlimentacionForms;

import java.util.Optional;

public class CCAlimentacion extends TableCell<AlimentacionDAO, String> {
    private Button btnCelda;
    private  int opc;
    private AlimentacionDAO ObjCuidDAO;

    public CCAlimentacion(int opc){
        this.opc = opc;
        if( opc == 1) {
            btnCelda = new Button("EDITAR");
            btnCelda.setOnAction(event -> {
                ObjCuidDAO = CCAlimentacion.this.getTableView().getItems().get(CCAlimentacion.this.getIndex());
                new AlimentacionForms(CCAlimentacion.this.getTableView(), ObjCuidDAO);
            });
        }else{
            btnCelda = new Button("BORRAR");
            btnCelda.setOnAction(event -> {
                Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
                alerta.setTitle("Mensaje del Sistema");
                alerta.setHeaderText("Confirmar De La Acción");
                alerta.setContentText("¿Realmente Deseas Borrar Esta Alimentacion?");
                Optional<ButtonType> result = alerta.showAndWait();

                if(result.get() == ButtonType.OK ){
                    ObjCuidDAO = CCAlimentacion.this.getTableView().getItems().get(CCAlimentacion.this.getIndex());
                    ObjCuidDAO.ELIMINAR_ALIMENTACION();
                    CCAlimentacion.this.getTableView().setItems(ObjCuidDAO.SELECCIONAR_ALIMENTACION());
                    CCAlimentacion.this.getTableView().refresh();
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