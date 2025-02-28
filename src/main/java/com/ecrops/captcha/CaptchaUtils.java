//package com.ecrops.captcha;
//
//import java.awt.image.RenderedImage;
//
//import java.io.ByteArrayOutputStream;
//import java.io.IOException;
//import java.io.UncheckedIOException;
//import java.util.Base64;
//
//import javax.imageio.ImageIO;
//import javax.xml.bind.DatatypeConverter;
//
//import cn.apiclub.captcha.Captcha;
//import cn.apiclub.captcha.backgrounds.GradiatedBackgroundProducer;
//import cn.apiclub.captcha.noise.CurvedLineNoiseProducer;
//import cn.apiclub.captcha.text.producer.DefaultTextProducer;
//import cn.apiclub.captcha.text.renderer.DefaultWordRenderer;
//
//public  class CaptchaUtils {
//	
//	
//public static Captcha createCaptcha(Integer width, Integer height) {
//		
//		return new Captcha.Builder(width, height)
//				.addBackground(new GradiatedBackgroundProducer())
//				.addText(new DefaultTextProducer(), new DefaultWordRenderer())
//				.addNoise(new CurvedLineNoiseProducer())
//				.build();
//	}
//	
//	//Converting to binary String
//	public static String encodeCaptcha(Captcha captcha) {
//		String image = null;
//		try {
//			ByteArrayOutputStream bos= new ByteArrayOutputStream();
//			ImageIO.write(captcha.getImage(),"jpg", bos);
//			byte[] byteArray= Base64.getEncoder().encode(bos.toByteArray());
//			image = new String(byteArray);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return image;
//	}
//
//}
