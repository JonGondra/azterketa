package ehu.isad.controllers.ui;

import ehu.isad.controllers.db.CMSDB;
import ehu.isad.model.CMSModel;
import ehu.isad.utils.Utils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class CMSController implements Initializable {

    private ArrayList<CMSModel> lista = new ArrayList<CMSModel>();

    private static CMSController instance=new CMSController();

    CMSDB cmsDB = CMSDB.getInstance();

    private CMSController() {}

    public static CMSController getInstance() {
        return instance;
    }

    @FXML
    private TableView<CMSModel> table;

    @FXML
    private TableColumn<CMSModel, String> URLColumn;

    @FXML
    private TableColumn<CMSModel, String> md5Column;

    @FXML
    private TableColumn<CMSModel, String> versionColumn;

    @FXML
    private Button CheckBTN;

    @FXML
    private Label InfoLBL;

    @FXML
    private TextField URLField;

    @FXML
    void onClick(ActionEvent event) throws IOException, NoSuchAlgorithmException {
        Utils utils = new Utils();
        if (!(URLField.getText()=="")){
            String url = URLField.getText();
            System.out.println(url);
            String md5 = Utils.getmd5(url);
            System.out.println(md5);
            Boolean dago = cmsDB.begiratuDBan(md5);
            System.out.println(dago);
            if(dago){
                CMSModel cmsModel = cmsDB.getFromDB(url,md5);
                lista.add(cmsModel);
                InfoLBL.setText("Datubasean zegoen");
            }
            else{
                CMSModel cmsModel = new CMSModel(url,"",md5);
                lista.add(cmsModel);
                InfoLBL.setText("Ez da DBn aurkitu");
            }

        }
        loadtable();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.loadTabla();
    }
    private void loadtable(){
        ObservableList<CMSModel> cmsmodel = FXCollections.observableArrayList(lista);
        table.setItems(cmsmodel);
    }
    public void loadTabla(){
        table.setEditable(true);
        URLColumn.setCellValueFactory(new PropertyValueFactory<>("URL"));
        md5Column.setCellValueFactory(new PropertyValueFactory<>("md5"));
        versionColumn.setCellValueFactory(new PropertyValueFactory<>("version"));

        versionColumn.setOnEditCommit(t->{
            if(t.getOldValue()==null&&t.getNewValue()!=null){
                t.getTableView().getItems().get(t.getTablePosition().getRow())
                        .setVersion(t.getNewValue());
                CMSDB.getInstance().addToDB(t.getTableView().getItems().get(t.getTablePosition().getRow()));
                InfoLBL.setText("md5 eta bertsio berria datubasean sartu dira");
            }

        });

        //Como hacer que las columnas sean editables
        //Esto en caso de text field.
        //Asi es como se guarda en el objeto.
        versionColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        Callback<TableColumn<CMSModel, String>, TableCell<CMSModel, String >> defaultTextFieldCellFactoryVERSION
                = TextFieldTableCell.forTableColumn();
        versionColumn.setCellFactory(col -> {
            TableCell<CMSModel, String> cell = defaultTextFieldCellFactoryVERSION.call(col);
            return cell ;
        });

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
       //table.setItems(lista);

        //checkbox

        //columna.setCellValueFactory(new PropertyValueFactory<>("atributoObjeto"));
        //column.setEditable(true);
        //column.setCellFactory(p -> {
        //    CheckBoxTableCell<KirolariaModel, Boolean> cell = new CheckBoxTableCell<KirolariaModel, Boolean>();
        //    cell.setAlignment(Pos.CENTER);
        //    return cell;
        //});

        //column.setCellValueFactory(new Callback<>() {

        //    @Override
        //    public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<KirolariaModel, Boolean> param) {
        //        KirolariaModel person = param.getValue();
        //        SimpleBooleanProperty booleanProp = new SimpleBooleanProperty(person.isVegetarian());
        //        // When "active?" column change.
        //        booleanProp.addListener(new ChangeListener<Boolean>() {
        //            @Override
        //            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue,                                                                               Boolean newValue) {
        //                person.setVegetarian(newValue);
        //            }
        //        });
        //        return booleanProp;
        //    }
        //});


        //onclick if (event.getSource().equals(BTN)){

    }
}
