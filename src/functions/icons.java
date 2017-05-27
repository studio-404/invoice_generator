package functions;

import javafx.scene.Cursor;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class icons {
	
	public static ImageView getIcon(String source){
		ImageView image = new ImageView();
		final Image image_resource = new Image("file:" + source);
		image.setImage(image_resource);
		image.autosize();
		image.setCursor(Cursor.HAND);
		image.setPickOnBounds(true);
		return image;
	}

}
