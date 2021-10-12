package restaurant.model;

import java.io.InputStream;
import java.io.Serializable;
import java.util.Arrays;

public class Image implements Serializable {
	private static final long serialVersionUID = 1L;
	private InputStream image;
	private byte[] imageBytes;
	
	public Image() {}
	
	public Image(InputStream image) {
		this.image = image;
	}
	
	public Image(byte [] imageBytes) {
		this.imageBytes = imageBytes;
	}

	public InputStream getImage() {
		return image;
	}

	public void setImage(InputStream image) {
		this.image = image;
	}

	public byte[] getImageBytes() {
		return imageBytes;
	}

	public void setImageBytes(byte[] imageBytes) {
		this.imageBytes = imageBytes;
	}

	@Override
	public String toString() {
		return "Image [image=" + image +
			", imageBytes=" + Arrays.toString(imageBytes) + "]";
	}

	
}
