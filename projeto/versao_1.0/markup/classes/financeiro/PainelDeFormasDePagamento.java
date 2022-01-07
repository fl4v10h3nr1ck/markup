package classes.financeiro;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import util.Mensagens;
import classes.CampoData;
import classes.CampoLimitado;
import classes.CampoLimitadoSoDigito;
import classes.CampoMoeda;
import classes.componentes.BotaoDeCelulaDeTabela;
import classes.componentes.FormDeSelecao;
import classes.componentes.beans.Item;
import classes.compras.FormAlteraDadosDePagamento;
import classes.comuns.Calculo;
import classes.comuns.Comuns;
import classes.comuns.Data;
import classes.dao.DAO;
import classes.financeiro.beans.Entrada_Saida;
import classes.financeiro.beans.Parcela;
import classes.financeiro.formas_de_pagamento.beans.FormaDePagamento;




public class PainelDeFormasDePagamento extends JPanel{


private static final long serialVersionUID = 1L;

private CampoLimitado tf_pagamento;
private CampoLimitado tf_tipo;
private CampoLimitadoSoDigito tf_num_parcelas;
private CampoMoeda tf_valor_pagamento;
private CampoData tf_pri_venc;
private int id_pagamento;
private int num_max_parcela;

private JTable tb_pagamentos;		
private DefaultTableModel modelo_pagamento;	

private List<Item> listas_pagamentos;	
	
private JPanel painel_parcelas;	
private JTable tb_parcelas;		
private DefaultTableModel modelo_parcelas;	

private String valor_total;

private JButton bt_add_pag;

private JButton bt_remover_pag;

private JButton bt_alterar_pag;




	
	public PainelDeFormasDePagamento(){
	
	this.setLayout(new GridBagLayout());	
		
	this.adicionarComponentes();	
	}


	
	
	
	

	public void adicionarComponentes() {
	

	GridBagConstraints cons = new GridBagConstraints();  
	
	cons.fill = GridBagConstraints.HORIZONTAL;
	cons.gridwidth  = GridBagConstraints.REMAINDER;	
	cons.weighty  = 0;
	cons.weightx = 1;
	
	cons.insets = new Insets(2, 2, 2, 2);	
	JPanel p1 = new JPanel(new GridBagLayout());
	p1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
	this.add(p1, cons);
	
	
	cons.fill = GridBagConstraints.HORIZONTAL;
	cons.gridwidth  = 1;	
	cons.weighty  = 0;
	cons.insets = new Insets(2, 2, 0, 2);	
	cons.weightx = 0.8;
	p1.add(new JLabel("<html>Forma de Pagamento:<font color=red>*</font></html>"), cons);
	cons.weightx = 0.2;
	cons.gridwidth  = GridBagConstraints.REMAINDER;
	p1.add(new JLabel("Tipo:"), cons);
	
	cons.gridwidth  = 1;
	cons.insets = new Insets(2, 2, 2, 2);
	cons.weightx = 0.8;
	p1.add(this.tf_pagamento= new CampoLimitado(200), cons);
	cons.weightx = 0.2;
	cons.gridwidth  = GridBagConstraints.REMAINDER;
	p1.add(this.tf_tipo= new CampoLimitado(100), cons);
	
	this.tf_pagamento.setEditable(false);
	this.tf_tipo.setEditable(false);
	
	
	cons.weightx = 1;
	cons.insets = new Insets(0, 0, 0, 0);	
	JPanel p1_1 = new JPanel(new GridBagLayout());
	p1.add(p1_1, cons);

	
	cons.gridwidth  = 1;
	cons.insets = new Insets(2, 2, 0, 2);
	cons.weightx = 0.2;
	p1_1.add(new JLabel("<html>Primeiro Vencimento:<font color=red>*</font></html>"), cons);
	p1_1.add(new JLabel("Num. de Parcelas:"), cons);
	p1_1.add(new JLabel("Valor a Pagar (R$):"), cons);
	p1_1.add(new JLabel(""), cons);
	cons.gridwidth  = GridBagConstraints.REMAINDER;
	p1_1.add(new JLabel(""), cons);
	
	
	cons.gridwidth  = 1;
	cons.insets = new Insets(2, 2, 2, 2);
	cons.weightx = 0.2;
	p1_1.add(this.tf_pri_venc= new CampoData(), cons);
	p1_1.add(this.tf_num_parcelas= new CampoLimitadoSoDigito(100), cons);
	p1_1.add(this.tf_valor_pagamento= new CampoMoeda(8), cons);
	p1_1.add(new JLabel(""), cons);
	
	this.tf_pagamento.setEditable(false);
	this.tf_num_parcelas.setEnabled(false);

	
	cons.fill = GridBagConstraints.NONE;
	cons.weightx  = 0;
	cons.gridwidth  = GridBagConstraints.REMAINDER;
	cons.anchor = GridBagConstraints.EAST;
	cons.ipadx = 20;
	this.bt_add_pag = new JButton("Adicionar", new ImageIcon(getClass().getResource("/icons/add.png")));
	bt_add_pag.setToolTipText("Adicionar forma de pagamento");
	p1_1.add(bt_add_pag, cons);
	
	
	cons.fill = GridBagConstraints.BOTH;
	cons.weighty  = 1;
	cons.weightx  = 0.7;
	cons.ipadx = 0;
	cons.gridwidth  = 1;
	JPanel p2_1 = new JPanel(new GridBagLayout());
	this.add(p2_1, cons);
	
	cons.weightx  = 0.3;
	cons.gridwidth  = GridBagConstraints.REMAINDER;
	this.add(this.painel_parcelas = new JPanel(new GridBagLayout()), cons);
	this.painel_parcelas.setVisible(false);
	
	
		
	this.modelo_pagamento = new DefaultTableModel(null, 
												new String[]{"Forma de Pagamento", 
															 "Parcelas", 
															 "Parcela (R$)", 
															 "Total (R$)",
															 ""}){
	
		private static final long serialVersionUID = 1L;
		
		public boolean isCellEditable(int row, int col ){  
		
		if(col==4)	
		return true;
		
		return false;
		}}; 
	     
	this.listas_pagamentos = new ArrayList<Item>();
	
	this.tb_pagamentos = new JTable(this.modelo_pagamento);
	this.tb_pagamentos.setRowHeight(25); 
	this.tb_pagamentos.getColumnModel().getColumn(0).setPreferredWidth(310);
	this.tb_pagamentos.getColumnModel().getColumn(1).setPreferredWidth(120);	
	this.tb_pagamentos.getColumnModel().getColumn(2).setPreferredWidth(120);	
	this.tb_pagamentos.getColumnModel().getColumn(3).setPreferredWidth(120);	
	this.tb_pagamentos.getColumnModel().getColumn(4).setPreferredWidth(30);	
	
		@SuppressWarnings("unused")
		BotaoDeCelulaDeTabela bt_ver_parcelas = new BotaoDeCelulaDeTabela("", new ImageIcon(getClass().getResource("/icons/mostrar_mais.png")), this.tb_pagamentos, 4){
	
		private static final long serialVersionUID = 1L;
	
			@Override
			public void processa() {
			
			int linha = tb_pagamentos.getSelectedRow();
	
			if(linha >= 0 && linha < listas_pagamentos.size())
			verParcelas(listas_pagamentos.get(linha));	
			
			}

			
			@Override
			public boolean mostrarBotao(int linha) {
			
			if(linha >= 0 && 
					linha < listas_pagamentos.size() &&
						listas_pagamentos.get(linha).getValor("valor_parcela")!=null && 
							listas_pagamentos.get(linha).getValor("valor_parcela").length()>0)
			return true;	
						
			return false;
			}
		};
		
	
	cons.fill = GridBagConstraints.BOTH;
	cons.weighty  = 1;
	cons.weightx  = 1;
	cons.ipadx = 0;
	p2_1.add(new JScrollPane(this.tb_pagamentos), cons);
	
		
	cons.fill = GridBagConstraints.HORIZONTAL;
	cons.weighty  = 0;
	cons.weightx  = 1;
	cons.insets = new Insets(0, 2, 0, 2);	
	JPanel p2_2 = new JPanel(new GridBagLayout());
	p2_1.add(p2_2, cons);
	
	cons.fill = GridBagConstraints.NONE;
	cons.weighty  = 0;
	cons.weightx  = 0;
	cons.gridwidth  = 1;
	this.bt_remover_pag = new JButton(new ImageIcon(getClass().getResource("/icons/fechar.png")));
	bt_remover_pag.setToolTipText("Remover pagamento selecionado");
	p2_2.add(bt_remover_pag, cons);
	
	this.bt_alterar_pag = new JButton(new ImageIcon(getClass().getResource("/icons/alterar.png")));
	bt_alterar_pag.setToolTipText("Alterar dados do pagamento selecionado");
	p2_2.add(bt_alterar_pag, cons);
	
	cons.gridwidth  = GridBagConstraints.REMAINDER;
	cons.fill = GridBagConstraints.HORIZONTAL;
	cons.weighty  = 0;
	cons.weightx  = 1;	
	p2_2.add(new JLabel(""), cons);
	
	
	
	this.modelo_parcelas = new DefaultTableModel(null, 
												new String[]{"", "Valor (R$)", "Vencimento"}){

	private static final long serialVersionUID = 1L;

	public boolean isCellEditable(int row, int col ){  
	
	return false;
	}}; 

	
	this.tb_parcelas = new JTable(this.modelo_parcelas);
	this.tb_parcelas.setRowHeight(20); 
	this.tb_parcelas.getColumnModel().getColumn(0).setPreferredWidth(30);
	this.tb_parcelas.getColumnModel().getColumn(1).setPreferredWidth(90);	
	this.tb_parcelas.getColumnModel().getColumn(2).setPreferredWidth(90);		

	
	cons.fill = GridBagConstraints.BOTH;
	cons.weighty  = 1;
	cons.weightx  = 1;
	cons.ipadx = 0;
	this.painel_parcelas.add(new JScrollPane(this.tb_parcelas), cons);	

	cons.fill = GridBagConstraints.NONE;
	cons.weighty  = 0;
	cons.weightx  = 0;
	cons.gridwidth  = 1;
	JButton bt_esconder_painel_parcelas = new JButton(new ImageIcon(getClass().getResource("/icons/mostrar_menos.png")));
	bt_esconder_painel_parcelas.setToolTipText("Esconder tabela de parcelas");
	this.painel_parcelas.add(bt_esconder_painel_parcelas, cons);
	
	cons.gridwidth  = GridBagConstraints.REMAINDER;
	cons.fill = GridBagConstraints.HORIZONTAL;
	cons.weighty  = 0;
	cons.weightx  = 1;	
	this.painel_parcelas.add(new JLabel(""), cons);
	

	
	
	
		bt_esconder_painel_parcelas.addActionListener( new ActionListener(){
		@Override
		public void actionPerformed( ActionEvent event ){
								
		painel_parcelas.setVisible(false);	
		}});
		
		
		
		
	
	
	
		this.tf_pagamento.addMouseListener( new MouseAdapter(){			
		@Override 
		public void mouseClicked(MouseEvent e) {
						
		if(!tf_pagamento.isEnabled())
		return;	
				
		addFormaDePagamento();
		}});
	
	
		
		
	
		bt_remover_pag.addActionListener( new ActionListener(){
		@Override
		public void actionPerformed( ActionEvent event ){
								
		int index = tb_pagamentos.getSelectedRow();
	
			if(index >= 0){
						
			listas_pagamentos.remove(index);
			
			atualizaTabelaDePagamentos();
			}
			else
			Mensagens.msgDeErro("Selecione uma linha da tabela de pagamentos para remoção.");		
		}});
		
		
		

		
		bt_alterar_pag.addActionListener( new ActionListener(){
		@Override
		public void actionPerformed( ActionEvent event ){
		
		alterarPagamento();	
		}});
			
		
		
		
		
		bt_add_pag.addActionListener( new ActionListener(){
		@Override
		public void actionPerformed( ActionEvent event ){
							
		addNovaFormaDePagamento();	
		}});
			
		
	

		this.tb_pagamentos.addMouseListener(new MouseAdapter(){
		@Override
		public void mouseClicked(MouseEvent e) {
		
			if(e.getClickCount()==1){
			
				if(painel_parcelas.isVisible()){
				
				int linha = tb_pagamentos.getSelectedRow();

				if(linha >= 0 && linha < listas_pagamentos.size())		
				verParcelas(listas_pagamentos.get(linha));
				}
			}			
			else if(e.getClickCount()==2)	
			alterarPagamento();	
		}}); 	
		
			
		
	
	List<JTextField> textFieldList = new ArrayList<JTextField>();
	
	
	textFieldList.add(this.tf_pagamento);
	textFieldList.add(this.tf_pri_venc);
	textFieldList.add(this.tf_valor_pagamento);
	textFieldList.add(this.tf_num_parcelas);
	
	
	Comuns.addEventoDeFoco(textFieldList);
	}
	
	
		
	
	

	
	
	private void addFormaDePagamento(){
	
	FormaDePagamento retorno = new FormaDePagamento();
			
	FormDeSelecao<FormaDePagamento> selectionItemForm = 
								new FormDeSelecao<FormaDePagamento>("Adicionar Forma de Pagamento", 
										retorno, 
										FormaDePagamento.class, 
										"f_pag.ativo='S'");
	selectionItemForm.mostrar();
							
		if(retorno != null && retorno.getId_forma_pag() > 0){
				
		FormaDePagamento forma = new DAO<FormaDePagamento>(FormaDePagamento.class).get(retorno.getId_forma_pag());
				
		this.id_pagamento = forma.getId_forma_pag();	
		this.tf_pagamento.setText("("+forma.getCodigo()+") "+forma.getDescricao());
		this.num_max_parcela = forma.getNum_max_parcelas();
		this.tf_tipo.setText(forma.getTipo_parcela());
		
		
			if(forma.getTipo_parcela()!=null && forma.getTipo_parcela().compareTo("PARCELADO")==0){
			
			this.tf_num_parcelas.setText("");
			this.tf_num_parcelas.setEnabled(true);
			}
			else{
		
			this.tf_num_parcelas.setText("");
			this.tf_num_parcelas.setEnabled(false);
			}
		}
	}
	
	
	


	
	
	
	private void addNovaFormaDePagamento(){
		
		if(this.id_pagamento==0){
			
		Mensagens.msgDeErro("Nenhuma forma de pagamento selecionada.");
		Comuns.textFieldErroMode(this.tf_pagamento);
		return;
		}	
		
		if(!this.tf_pri_venc.validacao()){
			
		Mensagens.msgDeErro("Informe uma data válida para o primeiro vencimento (em caso de pagamento parcelado) \nou data de pagamento (em caso de pagamento único).");
		Comuns.textFieldErroMode(this.tf_pri_venc);
		return;	
		}
		
		
		
		if(this.tf_num_parcelas.isEnabled()){
			
			if(this.tf_num_parcelas.getText().length()==0 || 
					Calculo.stringZero(this.tf_num_parcelas.getText())){
				
			Mensagens.msgDeErro("Informe o número de parcelas.");
			Comuns.textFieldErroMode(this.tf_num_parcelas);
			return;
			}
			
			if(Integer.parseInt(this.tf_num_parcelas.getText())>this.num_max_parcela){
				
			Mensagens.msgDeErro("O número máximo de parcelas para esta forma de pagamento é "+this.num_max_parcela+".");
			Comuns.textFieldErroMode(this.tf_num_parcelas);
			return;
			}	
		}
			
		
	
		if(Calculo.stringZero(this.tf_valor_pagamento.getText())){
			
		Mensagens.msgDeErro("Informe o valor de pagamento.");
		Comuns.textFieldErroMode(this.tf_valor_pagamento);
		return;
		}	
		
	
	String aux = "0.00";

	for(Item item: this.listas_pagamentos)
	aux = Calculo.soma(aux, item.getValor("valor"));

	
		if(Calculo.stringParaDouble(aux)>
		Calculo.stringParaDouble(this.valor_total)){
		
		Mensagens.msgDeErro("A soma total dos valores de pagamento é maior que o valor total da compra. \nAjuste as formas de pagamento.");
		return;
		}
	
	aux = Calculo.soma(aux, this.tf_valor_pagamento.getText());	
		
		if(Calculo.stringParaDouble(aux)>
		Calculo.stringParaDouble(this.valor_total)){
		
		Mensagens.msgDeErro("A soma total dos valores de pagamento não deve ser maior que o valor total da compra.");
		return;
		}
		
		
		
	Item item = new Item();
	
	item.setValor("id_forma_pagamento", this.id_pagamento);	
	item.setValor("nome_forma_pagamento", this.tf_pagamento.getText());	
	item.setValor("num_parcelas", this.tf_num_parcelas.getText());	
	item.setValor("valor", this.tf_valor_pagamento.getText());	
	
		if(this.tf_num_parcelas.isEnabled()){
		
		item.setValor("valor_parcela", Calculo.formataValor(
												Calculo.dividi(this.tf_valor_pagamento.getText(), 
															   this.tf_num_parcelas.getText(),
															   3)));	
		item.setValor("vencimento", this.tf_pri_venc.getText());
		}
	
	this.listas_pagamentos.add(item);
	
	this.id_pagamento = 0;	
	this.tf_pagamento.setText("");	
	this.tf_num_parcelas.setText("");	
	this.tf_num_parcelas.setEnabled(false);
	this.tf_tipo.setText("");
	this.tf_pri_venc.setText("");
	
	this.atualizaTabelaDePagamentos();
	}
	
	
	
	

	
	
	private void alterarPagamento(){
		
	int linha = this.tb_pagamentos.getSelectedRow();

		if(linha >= 0 && linha < this.listas_pagamentos.size()){
		
		String valor_acumulado_pagamento = "0.00";

		for(Item item: this.listas_pagamentos)
		valor_acumulado_pagamento = Calculo.soma(valor_acumulado_pagamento, item.getValor("valor"));	
			
		
		FormAlteraDadosDePagamento	 form = new FormAlteraDadosDePagamento(
														listas_pagamentos.get(linha), 
														valor_acumulado_pagamento,
														this.valor_total);
		form.mostrar();
		
		atualizaTabelaDePagamentos();	
		}
		else
		Mensagens.msgDeErro("Selecione uma linha da tabela de pagamentos para alteração.");	
		
	}
	
	
	
	
	
	
	
	public List<Parcela> gerarParcelas(int id_entrada_saida){
	
	List<Parcela> parcelas  = new 	ArrayList<Parcela>();
	
	DAO<FormaDePagamento> dao_forma = new DAO<FormaDePagamento>(FormaDePagamento.class);	
	
	
		for(Item item: this.getItens()){
			
		FormaDePagamento forma = dao_forma.get(Integer.parseInt(item.getValor("id_forma_pagamento")));	
				
			if(forma!=null){
			
			int venc_dia = Integer.parseInt(item.getValor("vencimento").substring(0, 2));	
			int venc_mes = Integer.parseInt(item.getValor("vencimento").substring(3, 5));
			int venc_ano = Integer.parseInt(item.getValor("vencimento").substring(6, 10));	
				
				if(forma.getTipo_parcela()!=null && forma.getTipo_parcela().compareTo("PARCELADO")==0){
				
					for(int i =0 ; i < Integer.parseInt(item.getValor("num_parcelas"));i++){
						
					Parcela	parcela = new Parcela();
					parcela.setData_pagamento(null);
					parcela.setData_vencimento(Data.converteStringParaData(Comuns.addPaddingAEsquerda(""+venc_dia, 2, "0")+"/"+
																		   Comuns.addPaddingAEsquerda(""+venc_mes, 2, "0")+"/"+
																		   venc_ano));	
					parcela.setFk_entrada_saida(id_entrada_saida);
					parcela.setIndice(i+1);
					parcela.setStatus("ABERTO");
					parcela.setTipo_parcela("PARCELADO");
					parcela.setValor(item.getValor("valor_parcela"));
					parcela.setValor_final(item.getValor("valor_parcela"));
					parcela.setVencimento_dia(venc_dia);
					parcela.setVencimento_mes(venc_mes);
					parcela.setVencimento_ano(venc_ano);
					parcela.setFk_forma_de_pagamento(forma.getId_forma_pag());
					
					parcelas.add(parcela);
					
					venc_mes++;
						if(venc_mes>12){
						venc_mes = 1;	
						venc_ano++;
						}
					}
				}	
				else{
						
				Parcela	parcela = new Parcela();
				parcela.setData_pagamento(null);
				parcela.setData_vencimento(Data.converteStringParaData(Comuns.addPaddingAEsquerda(""+venc_dia, 2, "0")+"/"+
																	   Comuns.addPaddingAEsquerda(""+venc_mes, 2, "0")+"/"+
																	   venc_ano));	
				parcela.setFk_entrada_saida(id_entrada_saida);
				parcela.setIndice(1);
				parcela.setStatus("ABERTO");
				parcela.setTipo_parcela("UNICO");
				parcela.setValor(item.getValor("valor"));
				parcela.setValor_final(item.getValor("valor"));
				parcela.setVencimento_dia(venc_dia);
				parcela.setVencimento_mes(venc_mes);
				parcela.setVencimento_ano(venc_ano);
				parcela.setFk_forma_de_pagamento(forma.getId_forma_pag());
				parcelas.add(parcela);
				}
			}
		}

	return parcelas;
	}
	
	
	
	
	
	
	
	public static List<Item> getFormasDePagamentos(Entrada_Saida entrada_saida){
		
	DAO<FormaDePagamento> dao_forma =new DAO<FormaDePagamento>(FormaDePagamento.class);
				
			
	List<Parcela> parcelas = new DAO<Parcela>(Parcela.class).get(null, 
																"parc.fk_entrada_saida="+entrada_saida.getId_entrada_saida(), 
																"parc.fk_forma_de_pagamento ASC");
	List<Item> lista_temp = new ArrayList<Item>();
		
		if(parcelas.size()>0){
	
		List<Integer> formas = new ArrayList<Integer>();	
			
		
			for(Parcela parcela: parcelas){
			
			boolean ja_tem = false;
				for(Integer id: formas){
						
					if(id== parcela.getFk_forma_de_pagamento()){
					
					ja_tem = true;
					break;
					}
				}
				
			if(!ja_tem)
			formas.add(parcela.getFk_forma_de_pagamento());	
			}
			
		
			
			for(Integer id: formas){
				
			FormaDePagamento forma = dao_forma.get(id);
				
				if(forma!=null){	
			
				int quant = 0;	
				String valor = "0,00";
				
				String vencimento = "";
				
					for(Parcela parcela: parcelas){
			
						if(forma.getId_forma_pag() == parcela.getFk_forma_de_pagamento()){
						
						if(parcela.getIndice()==1)
						vencimento = Data.converteDataParaString(parcela.getData_vencimento());
							
						quant++;
						valor = parcela.getValor();
						}
					}
				
				Item item = new Item();
							
				item.setValor("id_forma_pagamento", forma.getId_forma_pag());	
				item.setValor("vencimento", vencimento);	
				item.setValor("nome_forma_pagamento", "("+forma.getCodigo()+") "+forma.getDescricao());	
				item.setValor("valor", Calculo.formataValor(Calculo.multiplica(""+quant, valor)));	
				
					if(forma.getTipo_parcela()!=null && forma.getTipo_parcela().compareTo("PARCELADO")==0){
					
					item.setValor("num_parcelas", ""+quant);	
					item.setValor("valor_parcela", Calculo.formataValor(valor));
					}
				
				lista_temp.add(item);
				}	
			}
		}
	
	return lista_temp;
	}
	
	
	
	
	
	
	public void setFormasDePagamento(Entrada_Saida entrada_saida){
		
	this.listas_pagamentos.addAll(PainelDeFormasDePagamento.getFormasDePagamentos(entrada_saida));	
	}
	
	
	
	
	
	
	
	
	public List<Item> getItens(){return this.listas_pagamentos;} 
	
	
	
	
	

	public void atualizaTabelaDePagamentos(){
		
	this.modelo_pagamento.setNumRows(0);
		
	String[] dados = new String[5];
	
		for(Item item: this.listas_pagamentos){
		
		dados[0] = item.getValor("nome_forma_pagamento");
		dados[1] = item.getValor("num_parcelas");
		dados[2] = item.getValor("valor_parcela");
		dados[3] = Calculo.formataValor(item.getValor("valor"));
			
		this.modelo_pagamento.addRow(dados);
		}
	this.setValorDePagamentoRestante();
	}
	
	
	
	
	
	
	
	
	
	
	private void verParcelas(Item item){
	
	this.modelo_parcelas.setNumRows(0);	
		
	if(item.getValor("valor_parcela")==null || item.getValor("valor_parcela").length()==0)
	return;
		
		
	if(!this.painel_parcelas.isVisible())
	this.painel_parcelas.setVisible(true);	
	
	String[] dados = new String[3];
	
	int venc_dia = Integer.parseInt(item.getValor("vencimento").substring(0, 2));	
	int venc_mes = Integer.parseInt(item.getValor("vencimento").substring(3, 5));
	int venc_ano = Integer.parseInt(item.getValor("vencimento").substring(6, 10));	
				
		for(int i =0 ; i < Integer.parseInt(item.getValor("num_parcelas"));i++){
		
		dados[0] = Comuns.addPaddingAEsquerda(""+(i+1), 2, "0");	
		dados[1] = item.getValor("valor_parcela");
		dados[2] = Comuns.addPaddingAEsquerda(""+venc_dia, 2, "0")+"/"+
				   Comuns.addPaddingAEsquerda(""+venc_mes, 2, "0")+"/"+
				   venc_ano;
		
		venc_mes++;
			if(venc_mes>12){
			venc_mes = 1;	
			venc_ano++;
			}
		
		this.modelo_parcelas.addRow(dados);
		}
	} 
	
	
	
	
	
	
	
	
	
	
	public void setValorDePagamentoRestante(){
		
	String aux = "0.00";
	for(Item item: this.listas_pagamentos)
	aux = Calculo.soma(aux, item.getValor("valor"));
	
	aux = Calculo.subtrai(this.valor_total, aux);
	
	if(aux.length()==0 || aux.charAt(0)=='-')
	this.tf_valor_pagamento.setText("0,00");	
	else
	this.tf_valor_pagamento.setText(Calculo.formataValor(aux));	
	}
	
	
	
	
	

	
	
	public void setValorTotal(String valor){this.valor_total = valor;}
	
	
	
	
	
	
	public void bloquearAlteracao(){
		
	bt_add_pag.setEnabled(false);

	bt_remover_pag.setEnabled(false);

	bt_alterar_pag.setEnabled(false);
	}
	
}
