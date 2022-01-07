package classes.componentes.impressao.util.itens;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;




public class ItemRetangulo extends Item{

	
private int comprimento;

private Color cor;




	public ItemRetangulo(int x, int y, int altura, int comprimento, Color cor){
		
	super(x, y, altura);
			
	this.comprimento = comprimento<0?0:comprimento;
	
	this.cor = cor;
	
	this.setTipo(Item.TIPO_RETANGULO);		
	}



	

	public void get(Graphics2D g, Font fonte, Color cor){
	
	if(g==null)
	return;
	
	if(this.cor !=null)
	g.setColor(this.cor);	
	else
	g.setColor(Color.white);
	
	g.fill3DRect(this.getX(), this.getY(), this.comprimento, this.getAltura(),true);
	}
	
	
	
}
