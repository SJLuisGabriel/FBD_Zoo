package sample.compontes;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableCell;
import sample.models.AnimalesDAO;
import sample.tablas.AnimalesForms;

import java.util.Optional;

public class CCAnimales extends TableCell<AnimalesDAO, String> {
    private Button btnCelda;
    private  int opc;
    private AnimalesDAO ObjDAO;

    public CCAnimales(int opc){
        this.opc = opc;
        if( opc == 1) {
            btnCelda = new Button("EDITAR");
            btnCelda.setOnAction(event -> {
                ObjDAO = CCAnimales.this.getTableView().getItems().get(CCAnimales.this.getIndex());
                new AnimalesForms(CCAnimales.this.getTableView(), ObjDAO);
            });
        }else{
            btnCelda = new Button("BORRAR");
            btnCelda.setOnAction(event -> {
                Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
                alerta.setTitle("Mensaje del Sistema");
                alerta.setHeaderText("Confirmar De La Acción");
                alerta.setContentText("¿Realmente Deseas Borrar Este Animal?");
                Optional<ButtonType> result = alerta.showAndWait();

                if(result.get() == ButtonType.OK ){
                    ObjDAO = CCAnimales.this.getTableView().getItems().get(CCAnimales.this.getIndex());
                    ObjDAO.ELIMINAR_ANIMALES();
                    CCAnimales.this.getTableView().setItems(ObjDAO.SELECCIONAR_ANIMALES());
                    CCAnimales.this.getTableView().refresh();
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