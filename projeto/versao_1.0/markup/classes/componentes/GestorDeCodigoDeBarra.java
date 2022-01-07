package classes.componentes;

import java.awt.Color;
import java.awt.Image;

import com.itextpdf.text.pdf.Barcode;
import com.itextpdf.text.pdf.BarcodeEAN;

public class GestorDeCodigoDeBarra {


	
	
	
	
	public Image getCodEAN13(String codigo){
		
	BarcodeEAN codeEAN = new BarcodeEAN();
	  
	codeEAN.setCodeType(Barcode.EAN13);
	 
	codeEAN.setCode("9780201615883");
	
	Image imageEAN = codeEAN.createAwtImage(Color.BLACK, Color.WHITE);
	
	return imageEAN;
	}
	
	

	
}
