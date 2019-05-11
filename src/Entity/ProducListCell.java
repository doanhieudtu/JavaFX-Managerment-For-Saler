package Entity;

import javafx.geometry.Insets;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class ProducListCell extends ListCell<MatHang_ChiNhanh> {
	  private HBox content;
      private Text name;
      private Text dvt;
      private VBox vBox;
      private ImageView imageView;
      public ProducListCell() {
          super();
          name = new Text();
          dvt = new Text();
          vBox = new VBox();
          vBox.getChildren().add(name);
          vBox.getChildren().add(dvt);
          content= new HBox();
          content.setMargin(vBox, new Insets(2,2,2,2));
          content.setSpacing(10);
          imageView= new ImageView();
          content = new HBox(imageView,vBox);
      }
      @Override
      protected void updateItem(MatHang_ChiNhanh item, boolean empty) {
          super.updateItem(item, empty);
          if (item != null && !empty) { // <== test for null item and empty parameter
              name.setText(item.getMatHang().getTenhang());
              dvt.setText("Đơn vị tính: "+String.format(item.getMatHang().getDonvitinh()));
              Image imgeCreate= new Image(getClass()
        				.getResourceAsStream("../Resoucre/image/"+item.getMatHang().getHinhAnh())
        				,50,50, false,false);
              imageView.setImage(imgeCreate);
              setGraphic(content);
          } else {
              setGraphic(null);
          }
      }
	public HBox getContent() {
		return content;
	}
	public void setContent(HBox content) {
		this.content = content;
	}
	public Text getName() {
		return name;
	}
	public void setName(Text name) {
		this.name = name;
	}
	public Text getDvt() {
		return dvt;
	}
	public void setDvt(Text dvt) {
		this.dvt = dvt;
	}
	public VBox getvBox() {
		return vBox;
	}
	public void setvBox(VBox vBox) {
		this.vBox = vBox;
	}
	public ImageView getImageView() {
		return imageView;
	}
	public void setImageView(ImageView imageView) {
		this.imageView = imageView;
	}
	
}
