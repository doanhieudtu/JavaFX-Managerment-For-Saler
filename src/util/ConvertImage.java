package util;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.DataBuffer;
import java.awt.image.DataBufferByte;
import java.awt.image.Raster;
import java.awt.image.SampleModel;
import java.awt.image.WritableRaster;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;

import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;

import javafx.scene.image.Image;
import javassist.bytecode.Descriptor.Iterator;

public class ConvertImage {
	public static  byte[] ImagetoBytes (String pathname) throws IOException {
		File file= new File(pathname);
        FileInputStream fis = new FileInputStream(file);
        //create FileInputStream which obtains input bytes from a file in a file system
        //FileInputStream is meant for reading streams of raw bytes such as image data. For reading streams of characters, consider using FileReader.
 
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        byte[] buf = new byte[1024];
        try {
            for (int readNum; (readNum = fis.read(buf)) != -1;) {
                //Writes to this byte array output stream
                bos.write(buf, 0, readNum); 
                System.out.println("read " + readNum + " bytes,");
            }
        } catch (IOException ex) {
        }
 
        byte[] bytes = bos.toByteArray();
        return bytes;
		}
	public static void byteArrToImage( byte[] arr,String name) {
		try {
		InputStream in = new ByteArrayInputStream(arr);
		BufferedImage bImageFromConvert = ImageIO.read(in);

		ImageIO.write(bImageFromConvert, "jpg", new File(
				"E:/project/QuanLyBanHang/src/Resoucre/image/"+name+".jpg"));

	} catch (IOException e) {
		System.out.println(e.getMessage());
	}	 
	}
	
}
