package sample.compontes;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableCell;
import sample.models.EspecieDAO;
import sample.tablas.EspecieBD;
import sample.tablas.EspecieForms;

import java.util.Optional;

public class CCEspecie extends TableCell<EspecieDAO, String> {
    private Button btnCelda;
    private  int opc;
    private EspecieDAO ObjEspDAO;

    public CCEspecie(int opc){
        this.opc = opc;
        if( opc == 1) {
            btnCelda = new Button("EDITAR");
            btnCelda.setOnAction(event -> {
                ObjEspDAO = CCEspecie.this.getTableView().getItems().get(CCEspecie.this.getIndex());
                new EspecieForms(CCEspecie.this.getTableView(), ObjEspDAO);
            });
        }else{
            btnCelda = new Button("BORRAR");
            btnCelda.setOnAction(event -> {
                Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
                alerta.setTitle("Mensaje del Sistema");
                alerta.setHeaderText("Confirmar De La Acción");
                alerta.setContentText("¿Realmente Deseas Borrar La Especie?");
                Optional<ButtonType> result = alerta.showAndWait();

                if(result.get() == ButtonType.OK ){
                    ObjEspDAO = CCEspecie.this.getTableView().getItems().get(CCEspecie.this.getIndex());
                    ObjEspDAO.ELIMINAR_ESPECIE();
                    CCEspecie.this.getTableView().setItems(ObjEspDAO.SELECCIONAR_ESPECIE());
                    CCEspecie.this.getTableView().refresh();
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