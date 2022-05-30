package sample.compontes;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableCell;
import sample.models.CuidadoresDAO;
import sample.models.EspecieDAO;
import sample.tablas.CuidadoresForms;
import sample.tablas.EspecieForms;

import java.util.Optional;

public class CCCuidadores extends TableCell<CuidadoresDAO, String> {
    private Button btnCelda;
    private  int opc;
    private CuidadoresDAO ObjCuidDAO;

    public CCCuidadores(int opc){
        this.opc = opc;
        if( opc == 1) {
            btnCelda = new Button("EDITAR");
            btnCelda.setOnAction(event -> {
                ObjCuidDAO = CCCuidadores.this.getTableView().getItems().get(CCCuidadores.this.getIndex());
                new CuidadoresForms(CCCuidadores.this.getTableView(), ObjCuidDAO);
            });
        }else{
            btnCelda = new Button("BORRAR");
            btnCelda.setOnAction(event -> {
                Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
                alerta.setTitle("Mensaje del Sistema");
                alerta.setHeaderText("Confirmar De La Acción");
                alerta.setContentText("¿Realmente Deseas Borrar Al Empleado?");
                Optional<ButtonType> result = alerta.showAndWait();

                if(result.get() == ButtonType.OK ){
                    ObjCuidDAO = CCCuidadores.this.getTableView().getItems().get(CCCuidadores.this.getIndex());
                    ObjCuidDAO.ELIMINAR_CUIDADORES();
                    CCCuidadores.this.getTableView().setItems(ObjCuidDAO.SELECCIONAR_CUIDADORES());
                    CCCuidadores.this.getTableView().refresh();
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