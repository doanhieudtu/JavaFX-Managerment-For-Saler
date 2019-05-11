package Entity;

import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

public class CategoryListViewCell extends ListCell<LoaiHang>{
	private HBox content;
	private ImageView image;
	private Text name;		
	
	public CategoryListViewCell() {
		this.image= new ImageView();
		this.name= new Text();
		this.content= new HBox(this.image, this.name);
	}

	@Override
	protected void updateItem(LoaiHang item, boolean empty) {
		// TODO Auto-generated method stub
		super.updateItem(item, empty);
		if(item!=null && !empty) {
			name.setText(item.getTenloai());
			Image image= new Image(getClass().getResourceAsStream("../Resoucre/image/category.png"));
			this.image.setImage(image);
			setGraphic(content);
		}else {
            setGraphic(null);
        }
	}
}
