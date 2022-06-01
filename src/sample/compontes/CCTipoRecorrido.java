package sample.compontes;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableCell;
import sample.models.TipoRecoDAO;
import sample.tablas.TipoRecoForms;

import java.util.Optional;

public class CCTipoRecorrido extends TableCell<TipoRecoDAO, String> {
    private Button btnCelda;
    private  int opc;
    private TipoRecoDAO ObjDAO;

    public CCTipoRecorrido(int opc){
        this.opc = opc;
        if( opc == 1) {
            btnCelda = new Button("EDITAR");
            btnCelda.setOnAction(event -> {
                ObjDAO = CCTipoRecorrido.this.getTableView().getItems().get(CCTipoRecorrido.this.getIndex());
                new TipoRecoForms(CCTipoRecorrido.this.getTableView(), ObjDAO);
            });
        }else{
            btnCelda = new Button("BORRAR");
            btnCelda.setOnAction(event -> {
                Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
                alerta.setTitle("Mensaje del Sistema");
                alerta.setHeaderText("Confirmar De La Acción");
                alerta.setContentText("¿Realmente Deseas Borrar Este Tipo De Recorrido?");
                Optional<ButtonType> result = alerta.showAndWait();

                if(result.get() == ButtonType.OK ){
                    ObjDAO = CCTipoRecorrido.this.getTableView().getItems().get(CCTipoRecorrido.this.getIndex());
                    ObjDAO.ELIMINAR_TIPORECORRIDO();
                    CCTipoRecorrido.this.getTableView().setItems(ObjDAO.SELECCIONAR_TIPORECORRIDO());
                    CCTipoRecorrido.this.getTableView().refresh();
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