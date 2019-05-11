package Entity;

import java.text.SimpleDateFormat;

import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class BillListCell extends ListCell<DonHang> {	
	 private HBox content;
     private Text time;
     private Text id;
     private VBox vBox;
     private ImageView imageView;
     
     public BillListCell() {
    	 this.vBox= new VBox();
    	 this.time= new Text();
    	 this.id= new Text();
    	 this.imageView= new ImageView();
    	 this.vBox.getChildren().add(this.id);
    	 this.vBox.getChildren().add(this.time);
    	 this.content= new HBox(this.imageView, this.vBox);
     }
	@Override
	protected void updateItem(DonHang item, boolean empty) {
		// TODO Auto-generated method stub
		super.updateItem(item, empty);
        if (item != null && !empty) { // <== test for null item and empty parameter
            this.id.setText("Số: "+String.valueOf(item.getMadonhang()));
            SimpleDateFormat sdf= new SimpleDateFormat("dd/MM/yyy HH:mm:ss");
            this.time.setText("Thời gian lập: "+String.format(sdf.format(item.getNgaylap()).toString()));
            Image imgeCreate= new Image(getClass()
      				.getResourceAsStream("../Resoucre/image/bill.png")
      				,50,50, false,false);
            imageView.setImage(imgeCreate);
            setGraphic(content);
        } else {
            setGraphic(null);
        }
	}
     
     
}
