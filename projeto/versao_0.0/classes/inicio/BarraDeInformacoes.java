package inicio;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;


import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import pagueOaluguel.PagueOAluguel;
import componentes.Relogio;
import comuns.Configuracao;
import comuns.Data;




public class BarraDeInformacoes extends JPanel{

	

private static final long serialVersionUID = 1L;


private JLabel licenca_info;
private JLabel icon_licenca_info;

private Relogio relogio;




	public BarraDeInformacoes() {
	
	this.setLayout(new GridBagLayout());	
	
	this.relogio = new Relogio();	
	
	this.adicionarComponentes();
	}

	
	
	
	public void paintComponent(Graphics g){
		
	super.paintComponent(g);
			
	Graphics2D g2 = (Graphics2D) g.create();	

	//GradientPaint paint = new GradientPaint(  getWidth()/2, 0, Color.black,  getWidth()/2,  getHeight(), new Color(38, 56, 37));					
	//g2.setPaint( new Color(255, 204, 41));
	g2.setPaint( Color.red);
	g2.fillRect( 0 , 0 ,  getWidth(),  getHeight());		
	}
	
	
	
	
	public void adicionarComponentes() {	
	
	GridBagConstraints cons = new GridBagConstraints();  

	cons.fill = GridBagConstraints.NONE;
	cons.gridwidth  = 1;	
	cons.weightx = 0;
	cons.insets = new Insets(1,5,1,5);
	this.add(new JLabel("<html><font color=black><b>Usuário Atual: </b>"+Configuracao.usuarioAtual.getNome().toUpperCase()+"</font></html>"), cons);				
	
	cons.fill = GridBagConstraints.HORIZONTAL;
	cons.weightx = 1;
	this.add(new JLabel(""), cons);		

	
	cons.fill = GridBagConstraints.NONE;
	cons.gridwidth  = 1;	
	cons.weightx = 0;
	this.add(this.licenca_info = new JLabel(""), cons);
	
	cons.insets = new Insets(0,10,0,0);
	this.add(this.icon_licenca_info = new JLabel(""), cons);
	
	cons.insets = new Insets(0,0,0,0);
	cons.fill = GridBagConstraints.HORIZONTAL;
	cons.weightx = 1;
	this.add(new JLabel(""), cons);		
	
	
	cons.fill = GridBagConstraints.NONE;
	cons.gridwidth  = 1;	
	cons.weightx = 0;
	this.add(new JLabel("<html><font color=black><b>Data do Sistema: </b>"+Data.converteDataParaString(new Date())+"</font></html>"), cons);				
		
	cons.insets = new Insets(0,10,0,10);
	this.add(relogio, cons);	
	
	this.relogioStart();
	
	this.atualizaInfo();
	}

	
	
	
	
	public void atualizaInfo(){
	
		
	this.licenca_info.setText("<html><font color=black><b>Status de Licença: </b>"+Configuracao.currentRecord.getStatus()+"</font></html>");				

		switch(Configuracao.currentRecord.getError()){
		
		case PagueOAluguel.SUCCESS:
		this.icon_licenca_info.setIcon(new ImageIcon(getClass().getResource("/icons/icon_confirma.png")));				
		break;
		
		case PagueOAluguel.LICENSE_TERMINATION:
		this.icon_licenca_info.setIcon(new ImageIcon(getClass().getResource("/icons/icon_atencao.png")));				
		break;
		
		default:
		this.icon_licenca_info.setIcon(new ImageIcon(getClass().getResource("/icons/icon_erro.png")));					
		}
	}
	
	
	
	

	public void relogioStart(){
		
	this.relogio.start();	
	}
	
	
	
	
	
	
	public void relogioStop(){
	
	this.relogio.stop();	
	}
	
	
	
}
