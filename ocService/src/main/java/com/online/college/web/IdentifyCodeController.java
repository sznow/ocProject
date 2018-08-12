package com.online.college.web;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.RandomStringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.online.college.common.web.SessionContext;

/**
 * 验证码生成器
 */
@Controller
@RequestMapping("/tools/identiry")
public class IdentifyCodeController {
	@RequestMapping("/code")
	public void init(HttpServletRequest request, HttpServletResponse response){
		//生成四位随机数
		String random=RandomStringUtils.randomAlphanumeric(4); 
		SessionContext.setAttribute(request, SessionContext.IDENTIFY_CODE_KEY, random);
		//通过java的awt类画图
		response.setContentType("image/jpeg"); 
		response.addHeader("pragma", "NO-cache"); 
		response.addHeader("Cache-Control","no-cache"); 
		response.addDateHeader("Expries",0); 
		int width=110, height=33; 
		//通过BufferedImage
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB); 
		Graphics g = image.getGraphics(); 
		//以下填充背景色 
		g.setColor(new Color(225,225,225)); 
		Font DeFont=new Font("SansSerif", Font.PLAIN, 26);   
		g.setFont(DeFont); 
		g.fillRect(0, 0, width, height); 
		//设置字体色 
		g.setColor(Color.BLACK); 
		g.drawString(random,20,25); 
		g.dispose(); 
		try {
			ServletOutputStream outStream = response.getOutputStream();
			ImageIO.write(image, "JPG", outStream);
			outStream.close(); 
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
}
