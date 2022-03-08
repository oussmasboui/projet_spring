package tn.esprit.spring.QRCode;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import com.google.zxing.WriterException;

import tn.esprit.spring.entities.Travel;
import tn.esprit.spring.service.ITravelService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;


import java.util.Base64;

@RestController
public class QRCodeController {

	@Autowired
	private ITravelService travelService;
	
	private static final String QR_CODE_IMAGE_PATH = "./src/main/resources/QRCode.png";

	@GetMapping(value = "/genrateQRCode/{id}")
	public String getQRCode(Model model, @PathVariable("id") Long idTravel) {
		Travel t = travelService.retrieveTravelById(idTravel);
		
		String medium =" destination  :"+ t.getDestination() +"\n start date :" + t.getStartDate()+ "\n end date :"+t.getEndDate();
		String github = "destination=" + t.getDestination()+ "start date:" + t.getStartDate();

		byte[] image = new byte[0];
		try {

			// Generate and Return Qr Code in Byte Array
			image = QRCodeGenerator.getQRCodeImage(medium, 250, 250);

			// Generate and Save Qr Code Image in static/image folder
			QRCodeGenerator.generateQRCodeImage(medium, 250, 250, QR_CODE_IMAGE_PATH);

		} catch (WriterException | IOException e) {
			e.printStackTrace();
		}
		// Convert Byte Array into Base64 Encode String
		String qrcode = Base64.getEncoder().encodeToString(image);

		model.addAttribute("medium", medium);
		model.addAttribute("github", github);
		model.addAttribute("qrcode", qrcode);

		return "medium";
	}
}
