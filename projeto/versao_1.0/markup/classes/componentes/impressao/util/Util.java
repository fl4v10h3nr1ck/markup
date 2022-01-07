package classes.componentes.impressao.util;

import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.image.BufferedImage;

import classes.componentes.impressao.Relatorio;





public class Util {

	
	
	public static int getPosicaoXCentralizado(Relatorio relatorio, BufferedImage img){
	
	if(relatorio ==null || img ==null)
	return 0;
		
	int posicao = (relatorio.getComprimentoTotal() - (2*relatorio.getMargemX())) - img.getWidth();
		
	return posicao<0?0:(int)(posicao/2);
	}
	
	
	
	
	public static int getPosicaoXCentralizado(Relatorio relatorio, String valor){
	
	return Util.getPosicaoXCentralizado(relatorio, relatorio.getFonteGeral(), valor);
	}
	
	

	
	public static int getPosicaoXCentralizado(Relatorio relatorio, Font fonte, String valor){
		
	if(valor==null || valor.length()==0 || fonte==null)
	return 0;
	
	BufferedImage img = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
	FontMetrics fm = img.getGraphics().getFontMetrics(fonte);
	
	int posicao = (relatorio.getComprimentoTotal() - (2*relatorio.getMargemX())) - fm.stringWidth(valor);
	
	return posicao<0?0:(int)(posicao/2);
	}
	
	
	
	
	
	

	public static int getPosicaoXCentralizado(Relatorio relatorio, int comprimento){
		
	if(relatorio ==null)
	return 0;
	
	comprimento = comprimento<0?0:comprimento;
	
	int posicao = (relatorio.getComprimentoTotal() - (2*relatorio.getMargemX())) - comprimento;
				
	return posicao<0?0:(int)(posicao/2);
	}
	
	
	
	
}
