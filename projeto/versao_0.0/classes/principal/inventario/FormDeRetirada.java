package principal.inventario;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import DAO.DAO;
import classes.CampoLimitado;
import classes.CampoLimitadoSoDigito;
import componentes.FormDeSelecao;
import componentes.Rotulo;
import componentes.beans.Colaborador;
import componentes.beans.Inventario;
import componentes.beans.Retirada;
import componentes.janelas.Dialogo;
import comuns.Comuns;
import comuns.Configuracao;
import comuns.Mensagens;
import comuns.Preferencias;




public class FormDeRetirada extends Dialogo{

	

private static final long serialVersionUID = 1L;

protected CampoLimitado tf_colaborador;
private int idColaborador;


protected JTable tb_itens;		
private DefaultTableModel modelo;		

protected CampoLimitado tf_item;
protected CampoLimitadoSoDigito tf_quant;
private JButton bt_add_item;
private JButton bt_remover;
protected int idItemTemp;
	
protected List<Inventario> lista_itens;





	public FormDeRetirada() {
		
	super("Retirada do Inventário", 650, 500);
	
	this.modelo = new DefaultTableModel(null, new String[]{"Código", "Item", "QTDe"}){

		private static final long serialVersionUID = 1L;
		
		public boolean isCellEditable(int row, int col ){  
		            
		return false;
	   }}; 
	
	this.lista_itens = new ArrayList<Inventario>();
			
	
	this.tb_itens = new JTable(this.modelo);
	this.tb_itens.setRowHeight(20); 
	
	this.tb_itens.getColumnModel().getColumn(0).setPreferredWidth(110);
	this.tb_itens.getColumnModel().getColumn(1).setPreferredWidth(400);
	this.tb_itens.getColumnModel().getColumn(2).setPreferredWidth(120);	
	
	this.adicionarComponentes();
	
	
	List<Colaborador> colaborador = new DAO<Colaborador>(Colaborador.class).get(null, 
			"cola.status='ATIVO' and cola.fk_usuario="+Configuracao.usuarioAtual.getId(), null);
	
		if(colaborador.size()>0){
		
		this.idColaborador = colaborador.get(0).getId_colaborador();
		this.tf_colaborador.setText(colaborador.get(0).getNome());
		}
	}
	

	
	

	
	
	public void adicionarComponentes(){
	
	GridBagConstraints cons = new GridBagConstraints();  

	cons.fill = GridBagConstraints.HORIZONTAL;
	cons.gridwidth  = GridBagConstraints.REMAINDER;	
	cons.weighty  = 0;
	cons.weightx = 1;
	cons.insets = new Insets(2, 2, 2, 2);
		
	JPanel p1 = new JPanel(new GridBagLayout());
	p1.setBackground(Preferencias.COR_DE_FUNDO_PANE);
	this.add(p1, cons);
	
	cons.fill = GridBagConstraints.BOTH;
	cons.weighty  = 1;
	cons.weightx = 1;
	JPanel p2 = new JPanel(new GridBagLayout());
	p2.setBackground(Preferencias.COR_DE_FUNDO_PANE);
	this.add(p2, cons);	
		
	cons.fill = GridBagConstraints.HORIZONTAL;
	cons.weighty  = 0;
	cons.weightx = 1;
	cons.insets = new Insets(2, 2, 0, 2);
	cons.gridwidth  = GridBagConstraints.REMAINDER;	
	p1.add(new JLabel("<html>Responsável pela retirada:<font color=red>*</font></html>"), cons);
	
	cons.insets = new Insets(2, 2, 2, 2);
	this.tf_colaborador = new CampoLimitado(200);
	this.tf_colaborador.setEditable(false);
	p1.add(this.tf_colaborador, cons);
		
	
	cons.fill = GridBagConstraints.HORIZONTAL;
	cons.weightx = 0.55;
	cons.insets = new Insets(2, 2, 0, 2);
	cons.gridwidth  = 1;
	p2.add(new Rotulo("<html>Item:<font color=red>*</font></html>"), cons);
	cons.weightx = 0.15;
	p2.add(new Rotulo("<html>Quantidade:<font color=red>*</font></html>"), cons);
	
	cons.fill = GridBagConstraints.NONE;
	cons.weightx = 0;
	cons.gridwidth  = GridBagConstraints.REMAINDER;	
	p2.add(new Rotulo(""), cons);
	
	cons.fill = GridBagConstraints.HORIZONTAL;
	cons.weightx = 0.55;
	cons.gridwidth  = 1;
	cons.insets = new Insets(2, 2, 2, 2);
	this.tf_item = new CampoLimitado(200);
	this.tf_item.setEditable(false);
	p2.add(this.tf_item, cons);
	
	cons.fill = GridBagConstraints.HORIZONTAL;
	cons.weightx = 0.15;
	this.tf_quant = new CampoLimitadoSoDigito(4);
	p2.add(this.tf_quant, cons);
	
	cons.fill = GridBagConstraints.NONE;
	cons.anchor = GridBagConstraints.WEST;
	cons.weightx = 0;
	cons.ipadx=30;
	cons.gridwidth  = GridBagConstraints.REMAINDER;	
	this.bt_add_item  = new JButton("Adicionar", new ImageIcon(getClass().getResource("/icons/up.png")));
	bt_add_item.setToolTipText("Adicionar item escolhido");
	p2.add(bt_add_item, cons);

	cons.fill = GridBagConstraints.BOTH;
	cons.weighty  = 1;
	cons.weightx  = 1;
	cons.ipadx = 0;
	cons.insets = new Insets(1, 1, 1, 1);
	this.tb_itens.setBackground(Color.white); 
	p2.add(new JScrollPane(this.tb_itens, 
				JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED), cons);
		
	 
	cons.fill = GridBagConstraints.NONE;
	cons.anchor = GridBagConstraints.WEST;
	cons.ipadx = 15;
	cons.weighty  = 0;
	cons.weightx  = 0;
	cons.insets = new Insets(2, 2, 2, 2);
	this.bt_remover  = new JButton(new ImageIcon(getClass().getResource("/icons/icon_remove_mini.png")));
	bt_remover.setToolTipText("Remover item selecionado");
	p2.add(bt_remover, cons);
	
	
	cons.anchor = GridBagConstraints.EAST;
	cons.ipadx = 25;
	JButton bt_save  = new JButton("Salvar", new ImageIcon(getClass().getResource("/icons/icon_save.png")));
	bt_save.setToolTipText("Salvar retirada");
	this.add(bt_save, cons);
		
		

		bt_save.addActionListener( new ActionListener(){
		@Override
		public void actionPerformed( ActionEvent event ){
			    	
			if(acaoPrincipal()){	
					
			Mensagens.msgDeSucessoAoSalvar();
			dispose();
			}	
		}});
		


	
		
		this.tf_item.addFocusListener( new FocusAdapter(){
				
		@Override 
		public void focusGained(FocusEvent e) {
		
		Comuns.removeIndicadoresDeErro(tf_item);	
		tf_quant.requestFocusInWindow();
		addItem();
		}});
			
		
		
		bt_add_item.addActionListener( new ActionListener(){
		@Override
		public void actionPerformed( ActionEvent event ){
					    	
		addNovoItem();
		}});
		
		
	
		
		bt_remover.addActionListener( new ActionListener(){
		@Override
		public void actionPerformed( ActionEvent event ){
				
		int index = tb_itens	.getSelectedRow();
			
		
			if(index >= 0){
			
			lista_itens.remove(index);
			atualizaTabelaDeItens();
			}
			else
			Mensagens.msgDeErro("Selecione uma linha da tabela de itens para remoção.");

			
		}});
		
		

		
	List<JTextField> textFieldList = new ArrayList<JTextField>();
	
	textFieldList.add(this.tf_colaborador);
	textFieldList.add(this.tf_item);
	textFieldList.add(this.tf_quant);

	Comuns.addEventoDeFoco(textFieldList);
	
	this.getRootPane().setDefaultButton(bt_save);
	}
	
	
	
	
	
	
	@Override
	public boolean acaoPrincipal() {
		
	if(!validacao())
	return false;
	
	
	DAO<Inventario> inv_DAO = new DAO<Inventario>(Inventario.class);
		for(Inventario aux: this.lista_itens){
	
		Inventario inventario = inv_DAO.get(aux.getId_inventario());	
	
			if(inventario !=null){
	
				if(inventario.getQuantidade() < aux.getQuantidade()){
		
				Mensagens.msgDeErro("Há menos unidades do item "+Comuns.getCod(Inventario.class, aux.getId_inventario())+" no inventário que a quantidade solicitada.");
				return false;
				}
			}
		}
		
	
	DAO<Retirada> retirada_DAO = new DAO<Retirada>(Retirada.class);
	GregorianCalendar calendar = new GregorianCalendar();
	
	int hora = calendar.get(Calendar.HOUR_OF_DAY);
	int min = calendar.get(Calendar.MINUTE);
	Date data = new Date();
	
		for(Inventario aux: this.lista_itens){
	
		Inventario inventario = inv_DAO.get(aux.getId_inventario());	
			
			if(inventario !=null){
		
			inventario.setQuantidade(inventario.getQuantidade() - aux.getQuantidade());
			
			if(!inv_DAO.altera(inventario))	
			return false;
			
			
			Retirada retirada = new Retirada();
			retirada.setFk_colaborador(this.idColaborador);
			retirada.setData(data);
			retirada.setHora(hora);
			retirada.setMin(min);
			retirada.setQuant_total(aux.getQuantidade());
			retirada_DAO.novo(retirada);
			}
		}

	return true;
	}
	
	



	

	
	private void addItem(){

	Inventario retorno = new Inventario();
		
	FormDeSelecao<Inventario> selectionItemForm = 
							new FormDeSelecao<Inventario>("Adicionar Item", retorno, Inventario.class, "inv.status='ATIVO' and inv.quantidade>0");
		selectionItemForm.mostrar();
						
		if(retorno != null && retorno.getId_inventario() > 0){
		
			for(Inventario aux: this.lista_itens){	
			
				if(aux.getId_inventario() == retorno.getId_inventario() ){
					
				Mensagens.msgDeErro("Você já adicionou este item.");
				return;
				}	
			}
			
		this.idItemTemp = retorno.getId_inventario();
		this.tf_item.setText(retorno.getNome());	
		}			
	}	

		

	
	
	protected void addNovoItem(){
	
		if(this.idItemTemp == 0){
		
		Mensagens.msgDeErro("Nenhum item selecionado.");
		Comuns.textFieldErroMode(this.tf_item);
		return;	
		}
	
		
		if(this.tf_quant.getText().length() == 0 || Integer.parseInt(this.tf_quant.getText()) == 0){
			
		Mensagens.msgDeErro("Informe a quantidade de itens a serem retirados.");
		Comuns.textFieldErroMode(this.tf_quant);
		return;	
		}
	
	Inventario inventario = new DAO<Inventario>(Inventario.class).get(this.idItemTemp);	
	
		if(inventario !=null){
		
			if(inventario.getQuantidade() < Integer.parseInt(this.tf_quant.getText())){
			
			Mensagens.msgDeErro("Há menos unidades no inventário que a quantidade solicitada.");
			Comuns.textFieldErroMode(this.tf_quant);
			return;
			}
	
	
		inventario.setQuantidade( Integer.parseInt(this.tf_quant.getText()));		
		this.lista_itens.add(inventario);	
		
		this.atualizaTabelaDeItens();
		
		this.idItemTemp = 0;
		this.tf_item.setText("");
		this.tf_quant.setText("");
		}
	}		
				
	
	
	

	
	protected void atualizaTabelaDeItens(){
		
	this.modelo.setNumRows(0);
	String[] dados = new String[3];
	
		for(Inventario aux: this.lista_itens){
	
		dados[0] = Comuns.getCod(Inventario.class, aux.getId_inventario());
		dados[1] = ""+aux.getNome();
		dados[2] = ""+aux.getQuantidade();
		
		this.modelo.addRow(dados);	
		}
	}		
			
	
	
	
	
	
	protected boolean validacao(){
		
		if(this.idColaborador == 0){
			
		Mensagens.msgDeErro("Nenhum colaborador vinculado ao usuário atual.");
		Comuns.textFieldErroMode(this.tf_colaborador);
		return false;	
		}
		

		if(this.lista_itens.size() == 0){
			
		Mensagens.msgDeErro("Escolha ao menos um item para retirada.");
		return false;	
		}	
		
		
	return true;
	}





}
