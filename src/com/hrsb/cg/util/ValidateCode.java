package com.hrsb.cg.util;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
/**
 * 验证码工具类
 * 
 * @author WQJ - V 1.0.1
 * 
 * 保存在session范围 
 * 	key:checkCode
 * 页面调用：
 * 	<%=ValidateCode.getValidateCode() %>
 * XML 配置：
  <servlet>
	<servlet-name>code</servlet-name>
			<servlet-class>com.wing.util.ValidateCode</servlet-class>
  </servlet>
  <servlet-mapping>
			<servlet-name>code</servlet-name>
			<url-pattern>/wing_validate</url-pattern>
  </servlet-mapping>
 */
public final class ValidateCode extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int width = 100;// 验证码图片宽度
		int height = 30;// 验证码图片高度
		BufferedImage image = new BufferedImage(width, height,
				BufferedImage.TYPE_3BYTE_BGR);
		Graphics g = image.getGraphics();
		Random random = new Random();// 创建一个随机类
		g.setColor(getRandColor(200, 250));// 背景颜色要偏淡
		g.fillRect(0, 0, width, height);// 画背景
		g.setColor(getRandColor(0, 255));// 边框颜色
		g.drawRect(0, 0, width - 1, height - 1);// 画边框
		g.setColor(getRandColor(160, 200));// 随机产生5条干扰线，使图象中的认证码不易被其它程序探测到
		int x=0;
		int y=0;
		int x1=0;
		int y1=0;
		for (int i = 0; i < 8; i++) {
			x = random.nextInt(width);
			y = random.nextInt(height);
			x1 = random.nextInt(width);
			y1 = random.nextInt(height);
			g.drawLine(x, y, x1, y1);
		}
		g.setColor(getRandColor(160, 200));// 随机产生100点，使图象中的认证码不易被其它程序探测到
		for (int i = 0; i < 100; i++) {
			x = random.nextInt(width);
			y = random.nextInt(height);
			g.drawLine(x, y, x, y);
		}
		Font font = new Font("Times New Roman", Font.BOLD, 20); // 创建字体，字体的大小应该根据图片的高度来定。
		g.setFont(font);// 设置字体
		int length = 5; // 设置默认生成4个验证码
		String s = "0123456789"; // 设置备选验证码:包括"a-z"和数字"0-9"
		String sRand = "";

		// 用随机产生的颜色将验证码绘制到图像中。
		// 生成随机颜色(因为是做前景，所以偏深)
		// 调用函数出来的颜色相同，可能是因为种子太接近，所以只能直接生成
		g.setColor(new Color(20 + random.nextInt(110),
				20 + random.nextInt(110), 20 + random.nextInt(110)));
		String ch=null;
		for (int i = 0; i < length; i++) {
			ch = String.valueOf(s.charAt(random.nextInt(s.length())));
			sRand += ch;
			g.drawString(ch, 20 * i + 2, (random.nextInt(5) - 2) * i + 21);//调节随机数距离边框的长度
		}
		// 将生成的字符串存储在session中
		HttpSession session = request.getSession();
		session.setAttribute("checkCode", sRand);
		g.dispose();// 图像生效
		// 禁止图像缓存
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		response.setContentType("image/jpeg");
		// 创建二进制的输出流
		ServletOutputStream sos = response.getOutputStream();
		// 将图像输出到Servlet输出流中。
		ImageIO.write(image, "jpeg", sos);
		sos.flush();
		sos.close();
	}

	/**
	 * 产生随机颜色
	 * @param lower
	 * @param upper
	 * @return
	 */
	public Color getRandColor(int lower, int upper) {
		Random random = new Random();
		if (upper > 255)
			upper = 255;
		if (upper < 1)
			upper = 1;
		if (lower < 1)
			lower = 1;
		if (lower > 255)
			lower = 255;
		int r = lower + random.nextInt(upper - lower);
		int g = lower + random.nextInt(upper - lower);
		int b = lower + random.nextInt(upper - lower);
		return new Color(r, g, b);
	}
	/**
	 * 获得验证的代码
	 * @return
	 */
	public static String getValidateCode()
	{
		return "<img style='cursor: pointer;' src='wing_validate' id='wing_pic' onclick='wing_change();' alt='看不清,点击换一张'><script type='text/javascript'> function wing_change(){document.getElementById('wing_pic').src='wing_validate?'+Math.random();}</script>";
	}
}

