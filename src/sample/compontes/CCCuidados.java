package sample.compontes;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableCell;
import sample.models.CuidadosDAO;
import sample.tablas.CuidadosForms;

import java.util.Optional;

import static sample.tablas.CuidadosBD.cui;

public class CCCuidados extends TableCell<CuidadosDAO, String> {
    private Button btnCelda;
    private  int opc;
    private CuidadosDAO ObjDAO;

    public CCCuidados(int opc){
        this.opc = opc;
        if( opc == 1) {
            btnCelda = new Button("EDITAR");
            btnCelda.setOnAction(event -> {
                ObjDAO = CCCuidados.this.getTableView().getItems().get(CCCuidados.this.getIndex());
                new CuidadosForms(CCCuidados.this.getTableView(), ObjDAO);
                cui = 2;
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
                    ObjDAO = CCCuidados.this.getTableView().getItems().get(CCCuidados.this.getIndex());
                    ObjDAO.ELIMINAR_CUIDADOS();
                    CCCuidados.this.getTableView().setItems(ObjDAO.SELECCIONAR_CUIDADOS());
                    CCCuidados.this.getTableView().refresh();
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
