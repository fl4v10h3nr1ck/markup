package classes.estoque.cadastro.contas_bancarias;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import classes.CampoLimitado;
import classes.estoque.beans.ContaBancaria;




public class PainelDeContasBancarias  extends JPanel{


private static final long serialVersionUID = 1L;



public List<CampoLimitado> contas;
public List<CampoLimitado> agencias;
public List<CampoLimitado> bancos;
public List<CampoLimitado> titulares;


public JScrollPane scroll;
public JPanel fundo;

private int  num_max_de_contas;



	public PainelDeContasBancarias(){

		
	this(0);
	}


	
	
	
	
	public PainelDeContasBancarias(int num_max_de_contas){
		
	this.num_max_de_contas = num_max_de_contas;	
		
	this.setLayout(new GridBagLayout());	
	
	GridBagConstraints cons = new GridBagConstraints();  	
	cons.fill = GridBagConstraints.BOTH;
	cons.weightx = 1;
	cons.weighty = 1;
	cons.gridwidth  = GridBagConstraints.REMAINDER;
	this.add(this.scroll = new JScrollPane(fundo=new JPanel(new GridBagLayout())), cons);
	fundo.setBackground(Color.WHITE);

	this.contas = new ArrayList<CampoLimitado>();
	this.agencias = new ArrayList<CampoLimitado>();
	this.bancos = new ArrayList<CampoLimitado>();
	this.titulares = new ArrayList<CampoLimitado>();
	}
	
	
	
	
	
	public void addContas(List<ContaBancaria> contasBancarias){
	
		if(contasBancarias== null || contasBancarias.size() == 0){
		
		contasBancarias	 = new ArrayList<ContaBancaria>();
		contasBancarias.add(new ContaBancaria());
		}
		
		for(ContaBancaria aux: contasBancarias){
			
		this.bancos.add(new CampoLimitado(200, aux.getBanco()));
		this.contas.add(new CampoLimitado(20, aux.getConta()));
		this.agencias.add(new CampoLimitado(20, aux.getAgencia()));
		this.titulares.add(new CampoLimitado(200, aux.getTitular()));
		}
		
	addForms();
	}
	
	
	
	
	
	public void addConta(ContaBancaria contaBancaria){
		
	if(contaBancaria== null)
	return;
	
	this.bancos.add(new CampoLimitado(200, contaBancaria.getBanco()));
	this.contas.add(new CampoLimitado(20, contaBancaria.getConta()));
	this.agencias.add(new CampoLimitado(20, contaBancaria.getAgencia()));
	this.titulares.add(new CampoLimitado(200, contaBancaria.getTitular()));

	addForms();
	}
	
	
	
	
	
	
	public void addForms(){
	
	fundo.removeAll();
	fundo.repaint();
		
	GridBagConstraints cons = new GridBagConstraints();  	
	cons.fill = GridBagConstraints.HORIZONTAL;
	cons.gridwidth  = GridBagConstraints.REMAINDER;
	fundo.add(new JLabel("<html><b>Dados Bancários</b></html>"), cons);
	cons.insets = new Insets(2, 0, 7, 0);
	fundo.add(new JSeparator(SwingConstants.HORIZONTAL), cons);
	
	
		for(int i = 0; i < this.bancos.size(); i++){
		
		JTextField	aux  = this.bancos.get(i);
			
		cons.gridwidth  = 1;	
		cons.insets = new Insets(2, 2, 0, 2);
		cons.fill = GridBagConstraints.NONE;
		cons.weightx = 0;
		fundo.add(new JLabel(""), cons);
		
		cons.fill = GridBagConstraints.HORIZONTAL;
		cons.gridwidth  = 1;
		cons.weightx = 0.3;
		fundo.add(new JLabel("Banco:"), cons);
		cons.weightx = 0.2;
		fundo.add(new JLabel("Num. Conta:"), cons);
		cons.weightx = 0.2;
		fundo.add(new JLabel("Agência:"), cons);
		cons.weightx = 0.3;
		fundo.add(new JLabel("Titular:"), cons);
		
		cons.gridwidth  = GridBagConstraints.REMAINDER;
		cons.fill = GridBagConstraints.NONE;
		cons.weightx = 0;
		fundo.add(new JLabel(""), cons);
		
		cons.gridwidth  = 1;	
			if(i>0){
		
			JButton bt_remove  = new JButton(new ImageIcon(getClass().getResource("/icons/fechar.png")));
			bt_remove.setToolTipText("Excluir esta conta bancária");
			fundo.add(bt_remove, cons);
						
			bt_remove.addActionListener( new ActionListener(){
				@Override
				public void actionPerformed( ActionEvent event ){
				
				int index = bancos.indexOf(aux);
				bancos.remove(index);
				contas.remove(index);
				agencias.remove(index);
				titulares.remove(index);
					
				addForms();
				}});	
			
			}
			else
			fundo.add(new JLabel(""), cons);	
	
		cons.fill = GridBagConstraints.HORIZONTAL;
		cons.insets = new Insets(2, 2, 2, 2);
		cons.weightx = 0.3;
		fundo.add(this.bancos.get(i), cons);
		cons.weightx = 0.2;
		fundo.add(this.contas.get(i), cons);
		cons.weightx = 0.2;
		fundo.add(this.agencias.get(i), cons);
		cons.weightx = 0.3;	
		fundo.add(this.titulares.get(i), cons);	
		
		cons.gridwidth  = GridBagConstraints.REMAINDER;	
		cons.fill = GridBagConstraints.NONE;
		cons.weightx = 0;
		
			if(i==0){
			
			JButton bt_add  = new JButton(new ImageIcon(getClass().getResource("/icons/add.png")));
			bt_add.setToolTipText("Adicionar nova conta bancária");
			fundo.add(bt_add, cons);
			
				bt_add.addActionListener( new ActionListener(){
				@Override
				public void actionPerformed( ActionEvent event ){
				
				int num_max = num_max_de_contas>0?num_max_de_contas:10;
					if(bancos.size() < num_max){	
					
					bancos.add(new CampoLimitado(200));
					contas.add(new CampoLimitado(20));
					agencias.add(new CampoLimitado(20));
					titulares.add(new CampoLimitado(200));
	
					addForms();
					}
				}});	
			}
			else
			fundo.add(new JLabel(""), cons);	
		}
	
	cons.fill = GridBagConstraints.BOTH;
	cons.weighty  = 1;
	cons.weightx = 1;
	fundo.add(new JLabel(""), cons);		
		
		
	fundo.revalidate();
	}
	
	
	
	
	
	public List<ContaBancaria> getContas(){
	
	List<ContaBancaria> lista = new ArrayList<ContaBancaria>();	
	
		for(int i = 0; i < this.bancos.size(); i++){
		
		ContaBancaria conta = new ContaBancaria();
		conta.setBanco(this.bancos.get(i).getText());
		conta.setAgencia(this.agencias.get(i).getText());
		conta.setConta(this.contas.get(i).getText());
		conta.setTitular(this.titulares.get(i).getText());
			
		lista.add(conta);
		}
		
	return lista;	
	}
	
	
}
