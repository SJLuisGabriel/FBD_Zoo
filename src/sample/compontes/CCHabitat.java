package sample.compontes;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableCell;
import sample.models.HabitatDAO;
import sample.tablas.HabitatForms;

import java.util.Optional;

public class CCHabitat extends TableCell<HabitatDAO, String> {
    private Button btnCelda;
    private  int opc;
    private HabitatDAO ObjDAO;

    public CCHabitat(int opc){
        this.opc = opc;
        if( opc == 1) {
            btnCelda = new Button("EDITAR");
            btnCelda.setOnAction(event -> {
                ObjDAO = CCHabitat.this.getTableView().getItems().get(CCHabitat.this.getIndex());
                new HabitatForms(CCHabitat.this.getTableView(), ObjDAO);
            });
        }else{
            btnCelda = new Button("BORRAR");
            btnCelda.setOnAction(event -> {
                Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
                alerta.setTitle("Mensaje del Sistema");
                alerta.setHeaderText("Confirmar De La Acción");
                alerta.setContentText("¿Realmente Deseas Borrar Este Habitat?");
                Optional<ButtonType> result = alerta.showAndWait();

                if(result.get() == ButtonType.OK ){
                    ObjDAO = CCHabitat.this.getTableView().getItems().get(CCHabitat.this.getIndex());
                    ObjDAO.ELIMINAR_HABITAT();
                    CCHabitat.this.getTableView().setItems(ObjDAO.SELECCIONAR_HABITAT());
                    CCHabitat.this.getTableView().refresh();
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