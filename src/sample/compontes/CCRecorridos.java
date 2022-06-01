package sample.compontes;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableCell;
import sample.models.RecorridosDAO;
import sample.tablas.RecorridosForms;

import java.util.Optional;

public class CCRecorridos extends TableCell<RecorridosDAO, String> {
    private Button btnCelda;
    private  int opc;
    private RecorridosDAO ObjDAO;

    public CCRecorridos(int opc){
        this.opc = opc;
        if( opc == 1) {
            btnCelda = new Button("EDITAR");
            btnCelda.setOnAction(event -> {
                ObjDAO = CCRecorridos.this.getTableView().getItems().get(CCRecorridos.this.getIndex());
                new RecorridosForms(CCRecorridos.this.getTableView(), ObjDAO);
            });
        }else{
            btnCelda = new Button("BORRAR");
            btnCelda.setOnAction(event -> {
                Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
                alerta.setTitle("Mensaje del Sistema");
                alerta.setHeaderText("Confirmar De La Acción");
                alerta.setContentText("¿Realmente Deseas Borrar Este Recorrido?");
                Optional<ButtonType> result = alerta.showAndWait();

                if(result.get() == ButtonType.OK ){
                    ObjDAO = CCRecorridos.this.getTableView().getItems().get(CCRecorridos.this.getIndex());
                    ObjDAO.ELIMINAR_RECORRIDOS();
                    CCRecorridos.this.getTableView().setItems(ObjDAO.SELECCIONAR_RECORRIDOS());
                    CCRecorridos.this.getTableView().refresh();
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