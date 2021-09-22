package restaurant.model;

import java.io.InputStream;
import java.io.Serializable;

public class Image implements Serializable {
	private static final long serialVersionUID = 1L;
	private InputStream image;
	
	public Image() {}
	
	public Image(InputStream image) {
		this.image = image;
	}

	public InputStream getImage() {
		return image;
	}

	public void setImage(InputStream image) {
		this.image = image;
	}

	@Override
	public String toString() {
		return "Image [image=" + image + "]";
	}
}
