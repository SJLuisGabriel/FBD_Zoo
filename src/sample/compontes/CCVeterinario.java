package sample.compontes;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableCell;
import sample.models.VeterinarioDAO;
import sample.tablas.VeterinarioForms;

import java.util.Optional;

public class CCVeterinario extends TableCell<VeterinarioDAO, String> {
    private Button btnCelda;
    private  int opc;
    private VeterinarioDAO ObjVetDAO;

    public CCVeterinario(int opc){
        this.opc = opc;
        if( opc == 1) {
            btnCelda = new Button("EDITAR");
            btnCelda.setOnAction(event -> {
                ObjVetDAO = CCVeterinario.this.getTableView().getItems().get(CCVeterinario.this.getIndex());
                new VeterinarioForms(CCVeterinario.this.getTableView(), ObjVetDAO);
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
                    ObjVetDAO = CCVeterinario.this.getTableView().getItems().get(CCVeterinario.this.getIndex());
                    ObjVetDAO.ELIMINAR_VETERINARIO();
                    CCVeterinario.this.getTableView().setItems(ObjVetDAO.SELECCIONAR_VETERINARIO());
                    CCVeterinario.this.getTableView().refresh();
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