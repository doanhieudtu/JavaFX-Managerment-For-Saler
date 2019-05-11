package Controller;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
public class ScreenFramework extends Application{

	public static String screenIDlogin = "Login";
    public static String screenLogin = "../FXML/Login.fxml";
    public static String screenIDindex = "Index";
    public static String screenIndex = "../FXML/Index.fxml";
    public static String screenIDSearchProduct = "SearchProduct";
    public static String screenSearchProduct= "../FXML/SearchProduct.fxml";
    public static String screenIDListBill = "ListBill";
    public static String screenListBill= "../FXML/ListBill.fxml";
    public static String screenDetailProduct= "../FXML/DetailProduct.fxml";
    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage primaryStage)  {
        ScreenController mainContainer = new ScreenController();
   //     mainContainer.loadScreen(ScreenFramework.screenIDindex, ScreenFramework.screenIndex);
     //   mainContainer.loadScreen(ScreenFramework.screenIDListBill,ScreenFramework.screenListBill);
        mainContainer.loadScreen(ScreenFramework.screenIDlogin, ScreenFramework.screenLogin);
        mainContainer.setScreen(ScreenFramework.screenIDlogin);
        Group root = new Group();
        root.getChildren().addAll(mainContainer);
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("/Resoucre/css/General.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.show();
    }

  
}
