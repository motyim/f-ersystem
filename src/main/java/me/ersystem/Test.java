package me.ersystem;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

/**
 * @author MotYim mohamed.motyim@gmail.com
 * @since 19-Mar-2019
 */
public class Test {

    public static void main(String[] args) {

        try {
            BufferedImage image = ImageIO.read(new File("C:\\Users\\m.elmotayam\\Desktop\\download.png"));

            String s = encodeToString(image);
            decodeToImage(s);
            System.out.println(s);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static String encodeToString(BufferedImage image) {
        String imageString = null;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();

        try {
            ImageIO.write(image, "png", bos);
            byte[] imageBytes = bos.toByteArray();

            BASE64Encoder encoder = new BASE64Encoder();
            imageString = encoder.encode(imageBytes);

            bos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return imageString;
    }

    public static BufferedImage decodeToImage(String imageString) {

        BufferedImage image = null;
        byte[] imageByte;
        try {
            BASE64Decoder decoder = new BASE64Decoder();
            imageByte = decoder.decodeBuffer(imageString);
            ByteArrayInputStream bis = new ByteArrayInputStream(imageByte);
            image = ImageIO.read(bis);
            File outputfile = new File("image.png");
            ImageIO.write(image, "png", outputfile);
            bis.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return image;
    }
}
