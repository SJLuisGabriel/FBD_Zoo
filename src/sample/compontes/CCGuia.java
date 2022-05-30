package sample.compontes;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableCell;
import sample.models.GuiaDAO;
import sample.tablas.GuiaForms;

import java.util.Optional;

public class CCGuia extends TableCell<GuiaDAO, String> {
    private Button btnCelda;
    private  int opc;
    private GuiaDAO ObjGuiaDAO;

    public CCGuia(int opc){
        this.opc = opc;
        if( opc == 1) {
            btnCelda = new Button("EDITAR");
            btnCelda.setOnAction(event -> {
                ObjGuiaDAO = CCGuia.this.getTableView().getItems().get(CCGuia.this.getIndex());
                new GuiaForms(CCGuia.this.getTableView(), ObjGuiaDAO);
            });
        }else{
            btnCelda = new Button("BORRAR");
            btnCelda.setOnAction(event -> {
                Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
                alerta.setTitle("Mensaje del Sistema");
                alerta.setHeaderText("Confirmar De La Acción");
                alerta.setContentText("¿Realmente Deseas Borrar Empleado Guia?");
                Optional<ButtonType> result = alerta.showAndWait();

                if(result.get() == ButtonType.OK ){
                    ObjGuiaDAO = CCGuia.this.getTableView().getItems().get(CCGuia.this.getIndex());
                    ObjGuiaDAO.ELIMINAR_GUIA();
                    CCGuia.this.getTableView().setItems(ObjGuiaDAO.SELECCIONAR_GUIA());
                    CCGuia.this.getTableView().refresh();
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