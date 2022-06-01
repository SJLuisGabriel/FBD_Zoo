package sample.compontes;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableCell;
import sample.models.UbicacionDAO;
import sample.tablas.UbicacionForms;
import java.util.Optional;

import static sample.tablas.UbicacionBD.ubi;

public class CCUbicacion extends TableCell<UbicacionDAO, String> {
    private Button btnCelda;
    private  int opc;
    private UbicacionDAO ObjDAO;

    public CCUbicacion(int opc){
        this.opc = opc;
        if( opc == 1) {
            btnCelda = new Button("EDITAR");
            btnCelda.setOnAction(event -> {
                ObjDAO = CCUbicacion.this.getTableView().getItems().get(CCUbicacion.this.getIndex());
                new UbicacionForms(CCUbicacion.this.getTableView(), ObjDAO);
                ubi = 2;
            });
        }else{
            btnCelda = new Button("BORRAR");
            btnCelda.setOnAction(event -> {
                Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
                alerta.setTitle("Mensaje del Sistema");
                alerta.setHeaderText("Confirmar De La Acción");
                alerta.setContentText("¿Realmente Deseas Borrar Esta Ubicacion?");
                Optional<ButtonType> result = alerta.showAndWait();

                if(result.get() == ButtonType.OK ){
                    ObjDAO = CCUbicacion.this.getTableView().getItems().get(CCUbicacion.this.getIndex());
                    ObjDAO.ELIMINAR_UBICACION();
                    CCUbicacion.this.getTableView().setItems(ObjDAO.SELECCIONAR_UBICACION());
                    CCUbicacion.this.getTableView().refresh();
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