package com.plusitsolution.testone.controller;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.plusitsolution.testone.entity.OnelinkEntity;
import com.plusitsolution.testone.service.oneLinkService;

@RestController 
public class oneLinkController {
	
	@Autowired
	private oneLinkService onelinkservice;
	
	@PostMapping("/generateLink")
	public String newlink(@RequestParam("name") String name
			,@RequestParam(value = "iosLink", required = false) String iosLink
			,@RequestParam(value ="andriodLink", required = false) String andriodLink
			,@RequestParam(value ="desktopLink", required = false) String desktopLink){
		return "http://localhost:8085/training-ws/redirect/" + onelinkservice.newlink(name,iosLink,andriodLink,desktopLink).getGenerateLink();
	}
	
    @GetMapping(value = "/qrCode")
   	public ResponseEntity<byte[]> getQRCode(@RequestParam("generateLink") String generateLink)
   		    throws Exception {
   		        return ResponseEntity.status(HttpStatus.OK).body(oneLinkService.getQRCodeImage(generateLink));
   		    }
	
	@GetMapping("/originalLink")
	public String originalLink(@RequestHeader HttpHeaders headers, @RequestParam String generateLink) {
		return onelinkservice.redirectLink(headers.get("User-Agent").get(0),generateLink);
	}
	
	@GetMapping("/redirect/{alias}")
	public ResponseEntity<?> redirectGeneratedLink(@RequestHeader HttpHeaders headers, 
												   @PathVariable String alias, 
												   HttpServletResponse response) 
	throws IOException {
		System.out.println(alias);
		System.out.println(onelinkservice.redirectLink(headers.get("User-Agent").get(0),alias));
		response.sendRedirect(onelinkservice.redirectLink(headers.get("User-Agent").get(0),alias));
		return null;
	}
	
	@PostMapping("/updateLink")
	public OnelinkEntity updateLink(@RequestParam("name") String name
			,@RequestParam(value = "iosLink", required = false) String iosLink
			,@RequestParam(value ="andriodLink", required = false) String andriodLink
			,@RequestParam(value ="desktopLink", required = false) String desktopLink) {
		return onelinkservice.updateLink(name,iosLink,andriodLink,desktopLink);
	}
	
	@PostMapping("/deleteLink")
	public void deleteLink(@RequestParam(value = "generateLink", required = false) String generateLink) {
		 onelinkservice.deleteLink(generateLink);
	}
	
   	@GetMapping("/allLinks")
	public List<OnelinkEntity> getLinks(){
		return onelinkservice.getLinks();
	}
	
}
