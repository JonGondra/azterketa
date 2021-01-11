package ehu.isad.controllers.ui;

import ehu.isad.model.ProbaModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class ProbaController implements Initializable {

    ObservableList<ProbaModel> lista = FXCollections.observableArrayList();

    private static ProbaController instance=new ProbaController();

    private ProbaController() {}

    public static ProbaController getInstance() {
        return instance;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        lista = this.getLista();
        this.loadTabla();
    }
    private void gehituListara()  {
        //https://github.com/Kerman-Sanjuan/CaptchaAzterketa2021/blob/main/src/main/java/ehu/isad/controllers/ui/ProbaController.java
    }
    public ObservableList<ProbaModel>getLista(){
        //return ProbaDB.getInstance().getFromDB();
        return null;
    }
    public void loadTabla(){
        //tabla.setEditable(true);
        //columna.setCellValueFactory(new PropertyValueFactory<>("atributoObjeto"));
        //columna.setCellValueFactory(new PropertyValueFactory<>("atributoObjeto"));
        // columna.setCellValueFactory(new PropertyValueFactory<>("atributoObjeto"));

        //Como hacer que las columnas sean editables
        //Esto en caso de text field.
        //toColumb.setCellFactory(TextFieldTableCell.forTableColumn());
        //Asi es como se guarda en el objeto.
        //        toColumb.setOnEditCommit((TableColumn.CellEditEvent<MezuaModel, String> event) -> {
        //            TablePosition<MezuaModel, String> pos = event.getTablePosition();
        //            int row = pos.getRow();
        //            MezuaModel captchaModel = event.getTableView().getItems().get(row);
        //            String content = event.getNewValue();
        //            captchaModel.setNori(content);
        //
        //        });
        //Si es una foto, asi se carga el display
        //irudiaTable.setCellValueFactory(new PropertyValueFactory<>("image"));
        // irudiaTable.setCellFactory(p -> new TableCell<>() {
        //            public void updateItem(Image image, boolean empty) {
        //                if (image != null && !empty){
        //                    final ImageView imageview = new ImageView();
        //                    imageview.setImage(image);
        //                    setGraphic(imageview);
        //                    setAlignment(Pos.CENTER);
        //                }else{
        //                    setGraphic(null);
        //                    setText(null);
        //                }
        //            };
        //        });
        //tabla.setItems(lista);

    }
}
