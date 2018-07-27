package com.hrsb.cg.util;



import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;

import org.codehaus.plexus.util.FileUtils;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;
public class ImageUtils{   
	/**
	 * 截图
	 * @param input 图片输入流
	 * @param out 图片输出流
	 * @param type 图片源类型
	 * @param x 截取位置X
	 * @param y 截取位置Y
	 * @param width 截取宽度
	 * @param height 截取高度
	 * @throws IOException
	 */
	public static void cutImage(InputStream input, OutputStream out, String type,int x,  
			int y, int width, int height) throws IOException {  
		ImageInputStream imageStream = null;  
		try {  
			String imageType=(null==type||"".equals(type))?"jpg":type;  
			Iterator<ImageReader> readers = ImageIO.getImageReadersByFormatName(imageType);  
			ImageReader reader = readers.next();  
			imageStream = ImageIO.createImageInputStream(input);  
			reader.setInput(imageStream, true);  
			ImageReadParam param = reader.getDefaultReadParam();  
			Rectangle rect = new Rectangle(x, y, width, height);  
			param.setSourceRegion(rect);  
			BufferedImage bi = reader.read(0, param);  
			ImageIO.write(bi, imageType, out);  
		} finally {  
			imageStream.close();  
		}  
	}  
	/**
	 * 把图片印刷到图片上
	 * @param pressImg -- 水印文件
	 * @param targetImg -- 目标文件
	 * @param x
	 * @param y
	 */
	@SuppressWarnings("restriction")
	public final static void pressImage(String pressImg, String targetImg, int x, int y) {
		try {
			File _file = new File(targetImg);
			Image src = ImageIO.read(_file);
			int wideth = src.getWidth(null);
			int height = src.getHeight(null);
			BufferedImage image = new BufferedImage(wideth, height,
					BufferedImage.TYPE_INT_RGB);
			Graphics g = image.createGraphics();
			g.drawImage(src, 0, 0, wideth, height, null);
			// 水印文件
			File _filebiao = new File(pressImg);
			Image src_biao = ImageIO.read(_filebiao);
			int wideth_biao = src_biao.getWidth(null);
			int height_biao = src_biao.getHeight(null);
			g.drawImage(src_biao, wideth - wideth_biao - x, height - height_biao -y, wideth_biao,
					height_biao, null);
			// /
			g.dispose();
			FileOutputStream out = new FileOutputStream(targetImg);
			JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
			encoder.encode(image);
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 打印文字水印图片
	 * @param pressText --文字
	 * @param targetImg -- 目标图片
	 * @param fontName -- 字体名
	 * @param fontStyle -- 字体样式
	 * @param color -- 字体颜色
	 * @param fontSize -- 字体大小
	 * @param x -- 偏移量
	 * @param y
	 */
	@SuppressWarnings("restriction")
	public static void pressText(String pressText, String targetImg, String fontName,int fontStyle, int color, int fontSize, int x, int y) {
		try {
			File _file = new File(targetImg);
			Image src = ImageIO.read(_file);
			int wideth = src.getWidth(null);
			int height = src.getHeight(null);
			BufferedImage image = new BufferedImage(wideth, height,
					BufferedImage.TYPE_INT_RGB);
			Graphics g = image.createGraphics();
			g.drawImage(src, 0, 0, wideth, height, null);
			// String s="www.qhd.com.cn";
			g.setColor(Color.RED);
			g.setFont(new Font(fontName, fontStyle, fontSize));
			g.drawString(pressText, wideth - fontSize - x, height - fontSize/2 - y);
			g.dispose();
			FileOutputStream out = new FileOutputStream(targetImg);
			JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
			encoder.encode(image);
			out.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	/**
	 * 指定大小进行缩放（是否按比例）
	 * @param oldfile 源文件路径
	 * @param fileExt 新文件后缀
	 * @param x 
	 * @param y
	 * 若图片横比x小，高比y大，高缩小到y，图片比例不变 若图片横比x大，高比y小，横缩小到x，图片比例不变
	 * 若图片横比x大，高比y大，图片按比例缩小，横为x或高为y
	 * @param keepAspectRatio 是否按比例缩放
	 * @return
	 * @throws IOException
	 */
	public static String zoomImageByNum(String oldfile,String fileExt,Integer x,Integer y,boolean keepAspectRatio) throws IOException{
		String path = FileUtils.catPath(oldfile.replace("\\","/"), "");
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
		String newFileName = df.format(new Date()) + "_"
				+ new Random().nextInt(1000) + "." + fileExt;
		File f = new File(path+newFileName);
		Thumbnails.of(oldfile).size(x, y).keepAspectRatio(keepAspectRatio).toFile(
				f.getPath());
		return f.getPath();
	}
	/**
	 * 
	 * @param oldfile
	 * @param fileExt
	 * @param x
	 * @param y
	 * @param rotate
	 * @param quality 图片质量
	 * @return
	 * @throws IOException
	 */
	public static String zoomImageByNum(String oldfile,String fileExt,Integer x,Integer y,double rotate,double quality) throws IOException{
		String path = FileUtils.catPath(oldfile.replace("\\","/"), "");
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
		String newFileName = df.format(new Date()) + "_"
				+ new Random().nextInt(1000) + "." + fileExt;
		File f = new File(path+newFileName);
		Thumbnails.of(oldfile).size(x, y).outputQuality(quality).rotate(rotate).toFile(
				f.getPath());
		return f.getPath();
	}
	/**
	 * 指定大小缩放
	 * @param oldfile
	 * @param fileExt
	 * @param x
	 * @param y
	 * @return
	 * @throws IOException
	 */
	public static String zoomImageByNum(String oldfile,String fileExt,Integer x,Integer y) throws IOException{
		String path = FileUtils.catPath(oldfile.replace("\\","/"), "");
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
		String newFileName = df.format(new Date()) + "_"
				+ new Random().nextInt(1000) + "." + fileExt;
		File f = new File(path+newFileName);
		Thumbnails.of(oldfile).size(x, y).toFile(
				f.getPath());
		return f.getPath();
	}
	/**
	 * 指定大小缩放（旋转）
	 * @param oldfile
	 * @param fileExt
	 * @param x
	 * @param y
	 * @param rotate 旋转角度,正数：顺时针 负数：逆时针
	 * @return
	 * @throws IOException
	 */
	public static String zoomImageByNum(String oldfile,String fileExt,Integer x,Integer y,double rotate) throws IOException{
		String path = FileUtils.catPath(oldfile.replace("\\","/"), "");
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
		String newFileName = df.format(new Date()) + "_"
				+ new Random().nextInt(1000) + "." + fileExt;
		File f = new File(path+newFileName);
		Thumbnails.of(oldfile).size(x, y).rotate(rotate).toFile(
				f.getPath());
		return f.getPath();
	}
	/**
	 * 指定比例缩放（是否按比例缩放）
	 * @param oldfile
	 * @param fileExt
	 * @param x
	 * @param y
	 * @param keepAspectRatio
	 * @return
	 * @throws IOException
	 */
	public static String zoomImageByScale(String oldfile,String fileExt,double x,double y,boolean keepAspectRatio) throws IOException{
		String path = FileUtils.catPath(oldfile.replace("\\","/"), "");
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
		String newFileName = df.format(new Date()) + "_"
				+ new Random().nextInt(1000) + "." + fileExt;
		File f = new File(path+newFileName);
		Thumbnails.of(oldfile).scale(x, y).keepAspectRatio(keepAspectRatio).toFile(
				f.getPath());
		return f.getPath(); 
	}
	/**
	 * 指定比例缩放
	 * @param oldfile
	 * @param fileExt
	 * @param x
	 * @param y
	 * @return
	 * @throws IOException
	 */
	public static String zoomImageByScale(String oldfile,String fileExt,double x,double y) throws IOException{
		String path = FileUtils.catPath(oldfile.replace("\\","/"), "");
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
		String newFileName = df.format(new Date()) + "_"
				+ new Random().nextInt(1000) + "." + fileExt;
		File f = new File(path+newFileName);
		Thumbnails.of(oldfile).scale(x, y).toFile(
				f.getPath());
		return f.getPath(); 
	}
	/**
	 * 指定比例缩放（旋转）
	 * @param oldfile
	 * @param fileExt
	 * @param x
	 * @param y
	 * @param rotate 旋转角度,正数：顺时针 负数：逆时针
	 * @return
	 * @throws IOException
	 */
	public static String zoomImageByScale(String oldfile,String fileExt,double x,double y,double rotate) throws IOException{
		String path = FileUtils.catPath(oldfile.replace("\\","/"), "");
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
		String newFileName = df.format(new Date()) + "_"
				+ new Random().nextInt(1000) + "." + fileExt;
		File f = new File(path+newFileName);
		Thumbnails.of(oldfile).scale(x, y).rotate(rotate).toFile(
				f.getPath());
		return f.getPath(); 
	}
	/**
	 * 指定比例缩放（旋转）
	 * @param oldfile
	 * @param fileExt
	 * @param x
	 * @param y
	 * @param rotate 旋转角度,正数：顺时针 负数：逆时针
	 * @param quality 图片质量
	 * @return
	 * @throws IOException
	 */
	public static String zoomImageByScale(String oldfile,String fileExt,double x,double y,double rotate,double quality) throws IOException{
		String path = FileUtils.catPath(oldfile.replace("\\","/"), "");
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
		String newFileName = df.format(new Date()) + "_"
				+ new Random().nextInt(1000) + "." + fileExt;
		File f = new File(path+newFileName);
		Thumbnails.of(oldfile).scale(x, y).outputQuality(quality).rotate(rotate).toFile(
				f.getPath());
		return f.getPath(); 
	}
	/**
	 * 按大小缩放，压水印
	 * @param oldfile源文件
	 * @param waterimg 水印文件
	 * @param fileExt 新文件后缀
	 * @param x 
	 * @param y
	 * @param p Positions水印位置
	 * @param markOpacity 水印透明度
	 * @param quanlity 图片质量0.0-1.0
	 * @return
	 * @throws IOException
	 */
	public static String watermarkImage(String oldfile,String waterimg,String fileExt,int x,int y,Positions p,float markOpacity ,double quanlity) throws IOException{
		String path = FileUtils.catPath(oldfile.replace("\\","/"), "");
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
		String newFileName = df.format(new Date()) + "_"
				+ new Random().nextInt(1000) + "." + fileExt;
		File f = new File(path+newFileName);
		Thumbnails.of(oldfile).size(x, y).watermark(p, ImageIO.read(new File(waterimg)) , markOpacity).outputQuality(quanlity).toFile(
				f.getPath());
		return f.getPath(); 
	}
	/**
	 * 按比例缩放，压水印
	 * @param oldfile源文件
	 * @param waterimg 水印文件
	 * @param fileExt 新文件后缀
	 * @param x 
	 * @param y
	 * @param p Positions水印位置
	 * @param markOpacity 水印透明度
	 * @param quanlity 图片质量0.0-1.0
	 * @return
	 * @throws IOException
	 */
	public static String watermarkImage(String oldfile,String waterimg,String fileExt,double x,double y,Positions p,float markOpacity ,double quanlity) throws IOException{
		String path = FileUtils.catPath(oldfile.replace("\\","/"), "");
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
		String newFileName = df.format(new Date()) + "_"
				+ new Random().nextInt(1000) + "." + fileExt;
		File f = new File(path+newFileName);
		Thumbnails.of(oldfile).scale(x, y).watermark(p, ImageIO.read(new File(waterimg)) , markOpacity).outputQuality(quanlity).toFile(
				f.getPath());
		return f.getPath(); 
	}
	/**
	 * 按比例缩放裁剪
	 * @param oldfile
	 * @param fileExt
	 * @param x 比例
	 * @param y 比例
	 * @param keepAspectRatio 是否按比例
	 * @param width 宽度
	 * @param height 高度
	 * @param positionx X轴
	 * @param positiony Y轴
	 * @return
	 * @throws IOException
	 */
	public static String cutImage(String oldfile,String fileExt,int x,int y,boolean keepAspectRatio,int width,int height,int positionx,int positiony) throws IOException{
		String path = FileUtils.catPath(oldfile.replace("\\","/"), "");
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
		String newFileName = df.format(new Date()) + "_"
				+ new Random().nextInt(1000) + "." + fileExt;
		File f = new File(path+newFileName);
		Thumbnails.of(oldfile).sourceRegion(positionx, positiony, width, height).size(x, y).keepAspectRatio(keepAspectRatio).toFile(
				f.getPath());
		String newUrl = "";
		if(f.getPath() != null && !f.getPath().equals("")){
			String newpath = f.getPath().replace("\\","/");
			newUrl = newpath.substring(newpath.indexOf("upload"), newpath.length());
		}
		
		return newUrl; 
	}
	/**
	 * 按比例缩放裁剪 
	 * @param oldfile
	 * @param p 位置
	 * @param fileExt
	 * @param x
	 * @param y
	 * @param keepAspectRatio 是否按比例
	 * @param width 宽度
	 * @param height 高度
	 * @return
	 * @throws IOException
	 */
	public static String cutImage(String oldfile,Positions p ,String fileExt,int x,int y,boolean keepAspectRatio,int width,int height) throws IOException{
		String path = FileUtils.catPath(oldfile.replace("\\","/"), "");
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
		String newFileName = df.format(new Date()) + "_"
				+ new Random().nextInt(1000) + "." + fileExt;
		File f = new File(path+newFileName);
		Thumbnails.of(oldfile).sourceRegion(p,width, height).size(x, y).keepAspectRatio(keepAspectRatio).toFile(
				f.getPath());
		return f.getPath(); 
	}
	public static void scaleImage(InputStream imgInputStream,  
            OutputStream imgOutputStream, int scale)  
    {  
        try  
        {  
         //获得图片  
            Image src = javax.imageio.ImageIO.read(imgInputStream);  
            //获得设置后的宽度  
            int width = (int) (src.getWidth(null) * scale / 100.0);  
            //获得设置后的高度  
            int height = (int) (src.getHeight(null) * scale / 100.0);  
            //将设置好的图片追加到BufferedImage中  
            BufferedImage bufferedImage = new BufferedImage(width, height,  
                    BufferedImage.TYPE_INT_RGB);  
            //重构图片  
             bufferedImage.getGraphics().drawImage(  
                    src.getScaledInstance(width, height, Image.SCALE_SMOOTH),  
                    0, 0, null);  
             //创建重构后的图片，然后保存到相应的地方  
            JPEGImageEncoder encoder = JPEGCodec  
                    .createJPEGEncoder(imgOutputStream);  
              
            encoder.encode( bufferedImage);  
        }  
        catch (IOException e)  
        {  
            e.printStackTrace();  
        }  
    }
	/** 
	  * 重构方法二 
	  * @param imgSrc 
	  * @param imgDist 
	  * @param scale 
	  */  
	 public static String scaleImage(String imgSrc, String fileExt, int scale)  
	    {  
	        try  
	        {  
	        	String path = FileUtils.catPath(imgSrc.replace("\\","/"), "");
	    		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
	    		String newFileName = df.format(new Date()) + "_"
	    				+ new Random().nextInt(1000) + "." + fileExt;
	    		File f = new File(path+newFileName);
	            File file = new File(imgSrc);  
	            if (!file.exists())  
	            {  
	                return "";  
	            }  
	            InputStream is = new FileInputStream(file);  
	            OutputStream os = new FileOutputStream(f);  
	            scaleImage(is, os, scale);  
	            is.close();  
	            os.close();  
	            return f.getPath();
	        }  
	        catch (Exception e)  
	        {  
	        	e.printStackTrace();
	        	return "";
	        }  
	    }
	
	
	
	
	public static void main(String[] args) throws IOException {
//		pressImage("H:/t1.png", "H:/test.gif", 20 ,20);
//		pressText("CG", "H:/test.gif", "微软雅黑", Font.ITALIC, 0, 14, 20, 20);
//		FileUtils.mkdir("H:/file");
//		File f = new File("H:/file/image_200x300.jpg");
//		//System.out.println(f.createNewFile());
//		Thumbnails.of("C:/Users/risenb-java001/Pictures/ziye75.jpg").size(200, 300).toFile(
//				"H:/file/image_200x300.jpg");
//		System.out.println(cutImage("H:/file/2012071413165511396.jpg", "jpg", 200,200, true,200, 200,0,0));
//		String filename = "H:/file/Desert.jpg";
//		System.out.println(DateUtil.formatDate(new Date(), "yyyyMMddHHmmss"));
//		for (int i = 0; i < 1; i++) {
//			String fn = zoomImageByScale(filename, "jpg", 1, 1,0,0.5);
//			if(new File(filename).delete())filename = fn;
//		}
//		System.out.println(DateUtil.formatDate(new Date(), "yyyyMMddHHmmss"));
		//System.out.println(scaleImage("H:/file/IMG_20150113_172533.jpg", "jpg", 100));
		
		String s = watermarkImage("F:/banjia4.jpg", "F:/cgcg.png", "jpg", 500, 331, Positions.CENTER, 1f, 1.0);
		System.out.println(s);
	}  
}  