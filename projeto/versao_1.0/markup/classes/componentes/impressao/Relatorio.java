package classes.componentes.impressao;

import classes.componentes.impressao.util.itens.Item;
import classes.componentes.impressao.util.itens.ItemNovaPagina;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.print.Book;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.JobName;
import javax.print.attribute.standard.MediaSizeName;
import javax.print.attribute.standard.OrientationRequested;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.itextpdf.awt.PdfGraphics2D;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfTemplate;
import com.itextpdf.text.pdf.PdfWriter;





public abstract class Relatorio implements Printable{

	
public static final Font FONTE_PADRAO = 	new Font("Ms Sans Serif", Font.PLAIN, 10);

public static final Color COR_PADRAO = Color.black;

public static final  int ORIENTACAO_RETRATO  =1;

public static final  int ORIENTACAO_PAISAGEM  = 2;

public static final  int TIPO_DE_PAGINA_A4 = 1;

public static final  int TIPO_DE_PAGINA_CARTA = 2;

public static final  int AGRUPAMENTO_NORMAL = 1;

public static final  int AGRUPAMENTO_2_POR_PAGINA = 2;

public static final  int AGRUPAMENTO_4_POR_PAGINA = 3;

public static final  int PAPEL_DE_PAREDE_CENTRALIZADO = 0;

public static final  int PAPEL_DE_PAREDE_CENTRALIZADO_X = 1;

public static final  int PAPEL_DE_PAREDE_CENTRALIZADO_Y = 2;

public static final  int PAPEL_DE_PAREDE_PERSONALIZADO = 3;




private Font fonte_geral;

private Color cor_geral;


private int comprimento_total_pagina; 

private int altura_total_pagina;


private List<Item> itens;

private Grupo	cabecalho;

private Grupo	rodape;


private double escala;

private int x_margem; 

private int y_margem; 


private int altura_rodape;

private int altura_cabecalho;


public String titulo;

private int orientacao;

private int tipo_de_pagina;

private int agrupamento;


private BufferedImage papel_de_parede;

private int posicao_x_papel_de_parede;

private int posicao_y_papel_de_parede;

private int tipo_posicao_papel_de_parede;

private boolean mostrar_papel_de_parede;





	public Relatorio(){

	this("Relatório para Impressão");
	}
	
	
	
	
	public Relatorio(String titulo){
	
	this.titulo = titulo;	
		
	this.setFonteGeral(Relatorio.FONTE_PADRAO);	
	
	this.setCorGeral(Relatorio.COR_PADRAO);
	
	this.setTipo_de_pagina(Relatorio.TIPO_DE_PAGINA_A4);
	
	this.setComprimentoTotal(595); 
	
	this.setAlturaTotal(810); 

	this.setMargemX(20);
	
	this.setMargemY(20);
	
	this.setEscala(1.0);
	
	itens = new ArrayList<Item>();
	
	this.setOrientacao(Relatorio.ORIENTACAO_RETRATO);
	
	this.setAgrupamento(Relatorio.AGRUPAMENTO_NORMAL);
	
	this.setTipo_posicao_papel_de_parede(Relatorio.PAPEL_DE_PAREDE_CENTRALIZADO);
	
	this.setPosicao_x_papel_de_parede(0);
	
	this.setPosicao_y_papel_de_parede(0);
	
	this.setMostrar_papel_de_parede(true);
	}
	
	
	
	
	
	public void setFonteGeral(Font fonte){this.fonte_geral = fonte;	}
	
	
	public Font getFonteGeral(){return this.fonte_geral;}
	
	
	public void setCorGeral(Color cor){this.cor_geral = cor;}
	
	
	public Color getCorGeral(){return this.cor_geral;}
	
	
	public void setComprimentoTotal(int tamanho){this.comprimento_total_pagina = tamanho;}
	
	
	public int getComprimentoTotal(){return this.comprimento_total_pagina;}
	
	
	public void setAlturaTotal(int altura){this.altura_total_pagina = altura;}
	
	
	public int getAlturaTotal(){return this.altura_total_pagina;}
	

	public void setMargemX(int x){this.x_margem = x;}
	
	
	public int getMargemX(){return this.x_margem;}
	
	
	public void setMargemY(int y){this.y_margem = y;}
	
	
	public int getMargemY(){return this.y_margem;}
	
	
	public void setEscala(double escala){this.escala = escala;}
	
	
	public double getEscala(){return this.escala;}
	
	
	public int getOrientacao(){return this.orientacao;}
	

	public void setOrientacao(int orientacao){this.orientacao = orientacao;}
	
	
	public int getTipo_de_pagina(){return this.tipo_de_pagina;}
	

	public void setTipo_de_pagina(int tipo_de_pagina){this.tipo_de_pagina = tipo_de_pagina;}
	
	
	public int getAgrupamento() {	return agrupamento;}

	
	public void setAgrupamento(int agrupamento) {	this.agrupamento = agrupamento;}


	public BufferedImage getPapel_de_parede() {return papel_de_parede;}

	
	public void setPapel_de_parede(BufferedImage papel_de_parede) {this.papel_de_parede = papel_de_parede;}


	public int getTipo_posicao_papel_de_parede() {return tipo_posicao_papel_de_parede;}


	public void setTipo_posicao_papel_de_parede(int tipo_posicao_papel_de_parede) {this.tipo_posicao_papel_de_parede = tipo_posicao_papel_de_parede;}


	public int getPosicao_x_papel_de_parede() {		return posicao_x_papel_de_parede;}


	public void setPosicao_x_papel_de_parede(int posicao_x_papel_de_parede) {	this.posicao_x_papel_de_parede = posicao_x_papel_de_parede;}

	
	public int getPosicao_y_papel_de_parede() {	return posicao_y_papel_de_parede;}

	
	public void setPosicao_y_papel_de_parede(int posicao_y_papel_de_parede) {	this.posicao_y_papel_de_parede = posicao_y_papel_de_parede;}


	public boolean isMostrar_papel_de_parede() {	return mostrar_papel_de_parede;}


	public void setMostrar_papel_de_parede(boolean mostrar_papel_de_parede) {	this.mostrar_papel_de_parede = mostrar_papel_de_parede;}




/*******************************************************************************************/	
	

	public abstract Grupo cabecalho(); 
	
	
	
	private void configuraCabecalho(){
		
		if(cabecalho!=null){
			
			for(Item item: cabecalho.itens){
			
			item.setY(item.getY()+this.y_margem);
			item.setX(item.getX()+this.x_margem);
			}
		}	
	}
	
	
	
	public abstract Grupo rodape(); 
	
	
	
	
	private void configuraRodape(){
		
		if(this.rodape!=null){
			
			for(Item item: this.rodape.itens){
			
			item.setY((this.altura_total_pagina - this.altura_rodape - this.y_margem)+item.getY());
			item.setX(item.getX()+this.x_margem);
			}
		}	
	}
	
	
	
	
	public void preparaParaReceberRelatorio(){
		
	cabecalho = this.cabecalho();
		
	rodape = this.rodape();
		
	this.altura_cabecalho = this.cabecalho==null?0:this.cabecalho().getAltura();
		
	this.altura_rodape = this.rodape==null?0:this.rodape().getAltura();
		
	this.configuraCabecalho();
		
	this.configuraRodape();	
	}
	
	
	
	
	
	private void addPapelDeParede(Graphics2D g2d){
		
	BufferedImage papel_de_parede = getPapel_de_parede();
		
		if(papel_de_parede!=null){
		
		int x = 0;
		int y = 0;
			
			if(getTipo_posicao_papel_de_parede() == Relatorio.PAPEL_DE_PAREDE_CENTRALIZADO){
			
			if(papel_de_parede.getWidth()<getComprimentoTotal() )
			x = (int)((getComprimentoTotal() - papel_de_parede.getWidth())/2);	
			
			if(papel_de_parede.getHeight()<getAlturaTotal())
			y = (int)((getAlturaTotal() - papel_de_parede.getHeight())/2);	
			}
			else if(getTipo_posicao_papel_de_parede() == Relatorio.PAPEL_DE_PAREDE_CENTRALIZADO_X){
			
			if(papel_de_parede.getWidth()<getComprimentoTotal() )
			x = (int)((getComprimentoTotal() - papel_de_parede.getWidth())/2);	
			
			y = getPosicao_y_papel_de_parede();
			}
			else if(getTipo_posicao_papel_de_parede() == Relatorio.PAPEL_DE_PAREDE_CENTRALIZADO_Y){
				
			if(papel_de_parede.getHeight()<getAlturaTotal())
			y = (int)((getAlturaTotal() - papel_de_parede.getHeight())/2);
				
			x = getPosicao_x_papel_de_parede();
			}
			else if(getTipo_posicao_papel_de_parede() == Relatorio.PAPEL_DE_PAREDE_PERSONALIZADO){
				
			y = getPosicao_y_papel_de_parede();
			x = getPosicao_x_papel_de_parede();
			}	
			
		g2d.drawImage(papel_de_parede, x, y,  papel_de_parede.getWidth(), papel_de_parede.getHeight(), null);	
		}
	
		
	}
	
	
	
	
	
/*******************************************************************************************/	
	
	
	

	
	public void add(Grupo grupo){ 			 		
		
	int y = 0;
	int x = 0;
	int y_restante = 0;
	 	
		if(this.itens.size()==0 || 
	 			this.itens.get(this.itens.size()-1).getTipo() == Item.TIPO_QUEBRA){
	
		 
		y = this.y_margem+this.altura_cabecalho;
	 	
		}
		
		
		
		else{
		 
		Item item =  this.itens.get(this.itens.size()-1);
			 
			if(this.getAgrupamento() == Relatorio.AGRUPAMENTO_NORMAL){
			
				
			y = item.getY() + item.getAltura();
			
			}
		
		
		
			else if(this.getAgrupamento() == Relatorio.AGRUPAMENTO_2_POR_PAGINA){
				 
			if((item.getY() + item.getAltura())< ((int)(this.altura_total_pagina/2)))
			y  = ((int)(this.altura_total_pagina/2)) +this.y_margem;
				else{
		 		
				this.itens.add(new ItemNovaPagina());
				y = this.y_margem+this.altura_cabecalho;
			 
				}
			}
			
			
			
			else if(this.getAgrupamento() == Relatorio.AGRUPAMENTO_4_POR_PAGINA){
				
			int aux = (this.getComprimentoTotal() - 2*this.getMargemX())/2;
			
				if(item.getX() >= aux){	
				
				if((item.getY() + item.getAltura())< ((int)(this.altura_total_pagina/2)))
				y  = ((int)(this.altura_total_pagina/2)) +this.y_margem;
					else{
			 		
					this.itens.add(new ItemNovaPagina());
					y = this.y_margem+this.altura_cabecalho;
					}
				
				x = 0;	
				}
				else{
				
				if((item.getY() + item.getAltura())< ((int)(this.altura_total_pagina/2)))	
				y = this.y_margem+this.altura_cabecalho;		
				else
				y  = ((int)(this.altura_total_pagina/2)) +this.y_margem;		
	
				x = (this.getComprimentoTotal() - 2*this.getMargemX())/2+this.getMargemX();		
				}
			}
		}
		
		
		
	 
	 	if(!grupo.pode_ser_quebrado){
	 	
	 		
	 		if((y+grupo.getAltura()) > (this.altura_total_pagina - this.y_margem - this.altura_rodape)){
	 			
		 	this.itens.add(new ItemNovaPagina());
		 	y = this.y_margem+this.altura_cabecalho;
		 	y_restante = 0;
		 	}			
	 	}
	 	
	 	for(Item item: grupo.itens){
	 		
	 		if(item.getTipo() == Item.TIPO_QUEBRA){
	 		
	 		this.itens.add(new ItemNovaPagina());
	 		y = this.y_margem+this.altura_cabecalho;
			y_restante = 0;
			continue;
	 		}
	 		
	 		if((y+(item.getY() - y_restante)+item.getAltura()) > (this.altura_total_pagina - this.y_margem - this.altura_rodape)){
	 			
		 	this.itens.add(new ItemNovaPagina());
		 	y = this.y_margem+this.altura_cabecalho;
		 	y_restante  = item.getY();
		 	}	
	 			
	 	item.setY((item.getY() - y_restante) + y);	
	 	item.setX(item.getX()+this.x_margem);
	 	item.setX(item.getX()+x);
	 	
	 	this.itens.add(item);	
	 	}
	}
	
	

	
 	public int getNumeroDePaginas(){
 		
 	int num = 1;	
		for(Item item: this.itens){
			
		if(item.getTipo() == Item.TIPO_QUEBRA)
		num++;
		}		
		
	return num;	
 	}
 	
 	
 	
	
	
 	public JPanel getPagina(int pag_atual){

 	JPanel painel;
	
	
 		if(this.getNumeroDePaginas()>= pag_atual ){
		
			painel = new JPanel(){
	
			private static final long serialVersionUID = 1L;
						
						
				public void paintComponent(Graphics g) {
						   
				super.paintComponent(g);
					 	
					 	
				Graphics2D g2d = (Graphics2D) g.create();  
					 		 	
				g2d.scale(escala, escala);
				
				addPapelDeParede(g2d);
				
				
					if(cabecalho!=null){
					
					for(Item item: cabecalho.itens)
					item.get(g2d, getFonteGeral(), getCorGeral());
					}
				
				int aux = 1;	
					for(Item item: itens){
					
					if(item.getTipo() == Item.TIPO_QUEBRA)	
					aux++;
					
					if(aux>pag_atual)
					break;
					
					if(aux==pag_atual)
					item.get(g2d, getFonteGeral(), getCorGeral());
					}		 	
					
					
					if(rodape!=null){
						
					for(Item item: rodape.itens)
					item.get(g2d, getFonteGeral(), getCorGeral());
					}
				
				g2d.dispose();
				}
			};	
		}
		else		
		painel = new JPanel();	
		

 	painel.setBackground(Color.WHITE);			
	
	return painel;	
	}
	

 	
 	
 	
 	@Override
	public int print(Graphics graphics, 
						PageFormat pageFormat,
							int pageIndex) throws PrinterException {
	
	if (pageIndex > this.getNumeroDePaginas()-1)
    return Printable.NO_SUCH_PAGE;
		else {
       
		Graphics2D g2d = (Graphics2D) graphics;
       
			if(cabecalho!=null){
			
			for(Item item: cabecalho.itens)
			item.get(g2d, getFonteGeral(), getCorGeral());
			}
		
		int aux = 1;	
			for(Item item: itens){
			
			if(item.getTipo() == Item.TIPO_QUEBRA)	
			aux++;
			
			if((aux-1)>pageIndex)
			break;
			
			if(aux==(pageIndex+1))
			item.get(g2d, getFonteGeral(), getCorGeral());
				
			}		
					 	
			if(rodape!=null){
				
			for(Item item: rodape.itens)
			item.get(g2d, getFonteGeral(), getCorGeral());
			}
         
        g2d.dispose();
		}

	
    return Printable.PAGE_EXISTS;
	}
	
	
		
 
 	public boolean imprimir(){
		
	PrinterJob impressor = PrinterJob.getPrinterJob();
	PageFormat pf = impressor.defaultPage();
	Book book = new Book();
    book.append(this, pf, this.getNumeroDePaginas());
   
    Paper paper = new Paper();
    paper.setImageableArea(0, 0, paper.getWidth(), paper.getHeight());
    pf.setPaper(paper);
    
    impressor.setPageable(book);
	
	    try {
	   	
	    PrintRequestAttributeSet printerAttributes = new HashPrintRequestAttributeSet();  
	    printerAttributes.add(new JobName(this.titulo, null));   
	    
	    
	    if(this.getTipo_de_pagina()==Relatorio.TIPO_DE_PAGINA_CARTA)
	    printerAttributes.add(MediaSizeName.NA_LETTER);
	    else
		printerAttributes.add(MediaSizeName.ISO_A4);
		 
	    if(this.getOrientacao()==Relatorio.ORIENTACAO_PAISAGEM)
	    printerAttributes.add(OrientationRequested.LANDSCAPE); 
		else
		printerAttributes.add(OrientationRequested.PORTRAIT); 
			
	    
	    if(impressor.printDialog(printerAttributes))	
	    impressor.print();
	    
	    }catch (PrinterException e) { e.printStackTrace(); return false;}		
	
	return true;
 	}
 	
 	
 	
 	
 	public boolean salvar(){
 	
 	String path	 = "";
 	 		
 		
 	JFileChooser fc = new JFileChooser();
 		
 	fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
 			
 		fc.setFileFilter(new javax.swing.filechooser.FileFilter(){
 				      
 			public boolean accept(File f){
 				    
 			if (f.isDirectory()) {return true;}	
 				
 			return f.getName().toLowerCase().endsWith(".pdf");
 			}

 			
 			public String getDescription() {
 				
 			return "Arquivos PDF (.PDF)";
 			}
 		});
 				
 	int returnVal = fc.showOpenDialog(null);

 	if (returnVal == JFileChooser.APPROVE_OPTION)
 	path = fc.getSelectedFile().getAbsolutePath();		
 	else
 	return false;

 	if(path==null || path.length()==0)
 	return false;
 	
 	
 	if(!path.endsWith(".pdf"))
 	path +=	".pdf";
 	
 	Document doc = new Document(new Rectangle(this.comprimento_total_pagina, 
 												this.altura_total_pagina),
 												this.x_margem, 
 												this.y_margem, 
 												this.x_margem, 
 												this.y_margem); 	
	
	PdfWriter writer = null;
		try {writer = PdfWriter.getInstance(doc, new FileOutputStream(path));}
		catch (FileNotFoundException | DocumentException e) {
		e.printStackTrace();
		JOptionPane.showMessageDialog(null, "Um erro ocorreu ao gravar o relatório.", "ERRO", JOptionPane.ERROR_MESSAGE);	
		return false;
		}
	
	doc.open();
    PdfContentByte canvas = writer.getDirectContent();
    
	int num_de_pags = this.getNumeroDePaginas();
	
		for(int i = 0; i < num_de_pags; i++){	 	
		
		PdfTemplate template = canvas.createTemplate(this.comprimento_total_pagina, this.altura_total_pagina);
		Graphics2D g2d = new PdfGraphics2D(template, this.comprimento_total_pagina, this.altura_total_pagina);
				
			
			if(cabecalho!=null){
			
			for(Item item: cabecalho.itens)
			item.get(g2d, getFonteGeral(), getCorGeral());
			}
	
	
		int aux = 1;	
			for(Item item: itens){
			
			if(item.getTipo() == Item.TIPO_QUEBRA)	
			aux++;
			
			if((aux-1)>i)
			break;
			
				if(aux==(i+1)){
				
				item.setY(item.getY());
					
				item.get(g2d, getFonteGeral(), getCorGeral());
				}	
			}			
				 	
			if(rodape!=null){
				
			for(Item item: rodape.itens)
			item.get(g2d, getFonteGeral(), getCorGeral());
			}
		
			
		g2d.dispose();
		
		canvas.addTemplate(template, 0, 0);
	    doc.newPage();
		}
	
	if(doc!=null)
	doc.close();
	
 	return true;	
 	}
 	
 	
 	
 	
 	public static String removerEntidadeHTML(String item){
		
 	if(item==null || item.length()==0)
 	return "";
 				
 	return item.replace("&nbsp;", " ").replace("<html>", "").replace("</html>", "");
 	}
 		

}
