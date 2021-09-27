package com.plusitsolution.testone.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.hash.Hashing;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.plusitsolution.testone.domain.oneLinkDomain;
import com.plusitsolution.testone.entity.OnelinkEntity;
import com.plusitsolution.testone.repository.oneLinkRepo;

@Service
public class oneLinkService {
	
	@Autowired
	private oneLinkRepo onelinkrepo;
	
	public OnelinkEntity newlink(String name,String iosLink, String andriodLink,String desktopLink) {
		oneLinkDomain domain = new oneLinkDomain();
		
		if (iosLink != null)
		{
			try {
			    URL url = new URL(iosLink);
			    URLConnection conn = url.openConnection();
			    conn.connect();
			} catch (MalformedURLException e) {
				throw new RuntimeException("Unvalid Format IOS Link");
			} catch (IOException e) {
				throw new RuntimeException("Connect Error");
			}
		}
		
		if (andriodLink != null)
		{
			try {
			    URL url = new URL(andriodLink);
			    URLConnection conn = url.openConnection();
			    conn.connect();
			} catch (MalformedURLException e) {
				throw new RuntimeException("Unvalid Format Andriod Link");
			} catch (IOException e) {
				throw new RuntimeException("Connect Error");
			}
		}
		
		if (desktopLink != null)
		{
			try {
			    URL url = new URL(desktopLink);
			    URLConnection conn = url.openConnection();
			    conn.connect();
			} catch (MalformedURLException e) {
				throw new RuntimeException("Unvalid Format Desktop Link");
			} catch (IOException e) {
				throw new RuntimeException("Connect Error");
			}
		}
		
		List<String> inputList = new ArrayList<String>();
		List<String> trueList = new ArrayList<String>();
		inputList.add(iosLink);
		inputList.add(andriodLink);
        inputList.add(desktopLink);
        for (String Url : inputList)
        {
            if (Url != null)
            {
            	trueList.add(Url);
            }
        }
		LocalDateTime time =  LocalDateTime.now();
		String oriUrl = trueList.get(0);
		System.out.println(oriUrl);
		String shortUrl = "";
		
		shortUrl = Hashing.murmur3_32().hashString(oriUrl.concat(time.toString()), 
		StandardCharsets.UTF_8).toString()
;		System.out.println(shortUrl);

		if (onelinkrepo.findByName(name) != null) {
			
			throw new RuntimeException("This name already exist");
		}
		else if (onelinkrepo.findByGenerateLink(shortUrl) != null) {
			
			throw new RuntimeException("This link already exist");
		}

		domain.setName(name);
		domain.setGenerateLink(shortUrl);
		domain.setIosLink(iosLink);
		domain.setAndriodLink(andriodLink);
		domain.setDesktopLink(desktopLink);
		return onelinkrepo.save(domain.toEntity());
	}
	
	public static byte[] getQRCodeImage(String generateLink) throws WriterException, IOException {
	    QRCodeWriter qrCodeWriter = new QRCodeWriter();
	    if(generateLink == null)
		{
			throw new RuntimeException("Generate Link doesn't exist. Cannot create QR Code");
		}
	    BitMatrix bitMatrix = qrCodeWriter.encode(generateLink, BarcodeFormat.QR_CODE, 350, 350);
	    ByteArrayOutputStream pngOutputStream = new ByteArrayOutputStream();
	    MatrixToImageWriter.writeToStream(bitMatrix, "PNG", pngOutputStream);
	    byte[] pngData = pngOutputStream.toByteArray(); 
	    return pngData;
	}
	
	public OnelinkEntity updateLink(String name,String iosLink, String andriodLink,String desktopLink) {
		
		if (name == null)
		{
			throw new RuntimeException("This Link's name doesn't exist");
		}
		
		if (iosLink != null)
		{
			try {
			    URL url = new URL(iosLink);
			    URLConnection conn = url.openConnection();
			    conn.connect();
			} catch (MalformedURLException e) {
				throw new RuntimeException("Unvalid Format IOS Link");
			} catch (IOException e) {
				throw new RuntimeException("Connect Error");
			}
		}
		
		if (andriodLink != null)
		{
			try {
			    URL url = new URL(andriodLink);
			    URLConnection conn = url.openConnection();
			    conn.connect();
			} catch (MalformedURLException e) {
				throw new RuntimeException("Unvalid Format Andriod Link");
			} catch (IOException e) {
				throw new RuntimeException("Connect Error");
			}
		}
		
		if (desktopLink != null)
		{
			try {
			    URL url = new URL(desktopLink);
			    URLConnection conn = url.openConnection();
			    conn.connect();
			} catch (MalformedURLException e) {
				throw new RuntimeException("Unvalid Format Desktop Link");
			} catch (IOException e) {
				throw new RuntimeException("Connect Error");
			}
		}
		
		OnelinkEntity entity = onelinkrepo.findByName(name);
		if(entity == null) {
			throw new RuntimeException("This Link's name doesn't exist");
		}
		entity.setName(name);
		entity.setIosLink(iosLink);
		entity.setAndriodLink(andriodLink);
		entity.setDesktopLink(desktopLink);
		return onelinkrepo.save(entity);
	}
	
	public String redirectLink(String platform, String generateLink) {
		String os = "";
		
		if (platform.toLowerCase().indexOf("windows") >= 0 )
	    {
			os = "Windows";
	    } 
		else if(platform.toLowerCase().indexOf("iphone") >= 0)
	    {
			os = "IPhone";
	    }
		else if(platform.toLowerCase().indexOf("ipad") >= 0)
	    {
			os = "iPad";
	    } 
		else if(platform.toLowerCase().indexOf("mac") >= 0)
	    {
			os = "Mac";
	    } 
		else if(platform.toLowerCase().indexOf("x11") >= 0)
	    {
			os = "Unix";
	    } 
		else if(platform.toLowerCase().indexOf("android") >= 0)
	    {
			os = "Android";
	    } 
		
		System.out.println(os);
		
		if (os == "Windows" || os == "Unix") 
		{
			return onelinkrepo.findByGenerateLink(generateLink).getDesktopLink();
	    } 
		else if(os == "IPhone" || os == "iPad" || os == "Mac" )
	    {
			return onelinkrepo.findByGenerateLink(generateLink).getIosLink();
	    } 
		else if(os == "Android")
	    {
			return onelinkrepo.findByGenerateLink(generateLink).getAndriodLink();
	    }
		
		return null;
	}
	
	public OnelinkEntity getQRByName(String name) {
		OnelinkEntity sds = onelinkrepo.findByName(name);
		return sds;
	}
	
	public void deleteLink(String generateLink) {
		
		if(generateLink != null)
		{
			onelinkrepo.deleteByGenerateLink(generateLink);
		}
		else
		{
			throw new RuntimeException("Please insert either Link name or Generate Link");
		}
	}
	
	public List<OnelinkEntity> getLinks(){
		return onelinkrepo.findAll();
	}
	
}
