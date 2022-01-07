package principal.empresa;


import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import DAO.DAO;
import principal.endereco.PainelDeEndereco;
import classes.CampoCNPJ;
import classes.CampoLimitado;
import classes.CampoLimitadoSoDigito;
import classes.CampoMoeda;
import classes.CampoTEL;
import componentes.FormDeSelecao;
import componentes.Rotulo;
import componentes.beans.Aliquota;
import componentes.beans.Empresa;
import componentes.beans.Endereco;
import componentes.beans.Municipio;
import componentes.janelas.Dialogo;
import comuns.Comuns;
import comuns.Mensagens;
import comuns.Preferencias;



public class FormEmpresa extends Dialogo{

	

private static final long serialVersionUID = 1L;


protected CampoLimitado tf_nome;
protected CampoLimitado tf_codigo;
protected CampoLimitado tf_subtitulo;
protected CampoLimitado tf_nome_completo;
protected CampoLimitado tf_frase;

protected CampoTEL tf_tel_1;
protected CampoTEL tf_tel_2;
protected CampoLimitado tf_site;
protected CampoLimitado tf_email;

protected CampoLimitadoSoDigito tf_itens_por_pagina;

protected int id_cidade_padrao;
protected CampoLimitado tf_cidade_padrao;

protected CampoLimitado tf_pais_padrao;
protected CampoMoeda tf_saldoPadraoInicialDeCaixa;

protected CampoMoeda tf_descontoPadrao;
protected CampoMoeda tf_lucroPadraoDeProduto;
protected CampoLimitadoSoDigito tf_quantMinPadraoEstoque;

protected CampoLimitadoSoDigito tf_quantMinPadraoInventario;
protected CampoMoeda tf_porcentMultaPadrao;
protected CampoMoeda tf_porcentjurosPadraoAoMes;
protected CampoLimitadoSoDigito tf_numDeDiasDeMesComercial;

protected CampoMoeda tf_porcentComissaoPadrao;


protected Empresa empresa;

protected Aliquota aliquota;

protected PainelDeEndereco p_endereco;


protected CampoCNPJ tf_cnpj;
protected CampoLimitadoSoDigito tf_cnae;
protected CampoLimitadoSoDigito tf_ie;
protected CampoLimitadoSoDigito tf_insc_municipal;
protected CampoLimitado tf_nome_fantasia;


protected JComboBox<String> regime_tributario;
private CampoMoeda valor_icms_porcento;
private CampoMoeda valor_pis_porcento;
private CampoMoeda valor_cofins_porcento;
private CampoMoeda valor_ipi_porcento;

private CampoMoeda valor_csll_porcento;
private CampoMoeda valor_irpj_porcento;
private CampoMoeda valor_issqn_porcento;
private CampoMoeda valor_comissao_porcento;
private CampoMoeda valor_administrativa_porcento;






	public FormEmpresa() {
		
	super("Informações Da Empresa", 770, 630);
	
	this.adicionarComponentes();
	
	List<Empresa> empresas= new DAO<Empresa>(Empresa.class).get(null, null, null);		
	
	if(empresas != null && empresas.size()>0)
	this.empresa = empresas.get(0);
	
	
		if(this.empresa != null ){
			
		tf_nome.setText(empresa.getNome());
		this.tf_codigo.setText(empresa.getCodigo());
		this.tf_codigo.setText(""+empresa.getCodigo());
		tf_subtitulo.setText(empresa.getSubtitulo());
		tf_nome_completo.setText(empresa.getNome_completo());
		tf_tel_1.setText(empresa.getTel_1());
		tf_tel_2.setText(empresa.getTel_2());
		tf_site.setText(empresa.getSite());
		tf_email.setText(empresa.getEmail());
		
		tf_pais_padrao.setText(empresa.getPais_padrao());
		tf_itens_por_pagina.setText(""+empresa.getItens_por_pagina());
		
		tf_saldoPadraoInicialDeCaixa.setText(empresa.getSaldo_inicial_caixa());
		tf_descontoPadrao.setText(empresa.getDesconto_padrao());
		tf_lucroPadraoDeProduto.setText(empresa.getPorcento_lucro_produto());
		tf_quantMinPadraoEstoque.setText(""+empresa.getQuant_min_estoque());
		tf_quantMinPadraoInventario.setText(""+empresa.getQuant_min_inventario());

		tf_porcentMultaPadrao.setText(empresa.getPorcento_multa_padrao());
		tf_porcentjurosPadraoAoMes.setText(empresa.getPorcento_juros_ao_mes_padrao());
		tf_numDeDiasDeMesComercial.setText(""+empresa.getNum_dias_mes_comercial());
		tf_porcentComissaoPadrao.setText(empresa.getPorcento_comissao_padrao());
		
		tf_cnpj.setText(empresa.getCnpj());
		tf_cnae.setText(empresa.getCnae());
		tf_ie.setText(empresa.getInscricao_estadual());
		tf_insc_municipal.setText(empresa.getInscricao_municipal());
		tf_nome_fantasia.setText(empresa.getNome_fantasia());
		this.tf_frase.setText(this.empresa.getFrase_lema());
		
		if(empresa.getFk_endereco()>0)
		p_endereco.setValores(new DAO<Endereco>(Endereco.class).get(empresa.getFk_endereco()));
		
		
			if(empresa.getCodigo_municipio_padrao()>0){
				
			Municipio municipio = new DAO<Municipio>(Municipio.class).get(empresa.getCodigo_municipio_padrao());
			
				if(municipio!=null){
				
				this.id_cidade_padrao = municipio.getCodigo();
				this.tf_cidade_padrao.setText(municipio.getMunicipio());
				}
			}
		}
	
	
	
	List<Aliquota> aliquotas = new DAO<Aliquota>(Aliquota.class).get(null, null, null);
	
	if(aliquotas != null && aliquotas.size()>0)
	this.aliquota = aliquotas.get(0);
		
		
		if(this.aliquota != null ){
		
		regime_tributario.setSelectedItem(this.aliquota.getRegime_tributario());
		valor_icms_porcento.setText(this.aliquota.getValor_icms_porcento());
		valor_pis_porcento.setText(this.aliquota.getValor_pis_porcento());
		valor_cofins_porcento.setText(this.aliquota.getValor_cofins_porcento());
		valor_ipi_porcento.setText(this.aliquota.getValor_ipi_porcento());
		valor_csll_porcento.setText(this.aliquota.getValor_csll_porcento());
		valor_irpj_porcento.setText(this.aliquota.getValor_irpj_porcento());
		valor_issqn_porcento.setText(this.aliquota.getValor_issqn_porcento());
		valor_comissao_porcento.setText(this.aliquota.getValor_comissao_porcento());
		valor_administrativa_porcento.setText(this.aliquota.getValor_administrativa_porcento());
		}
	}
	

	
	
	
	public void adicionarComponentes(){
	
	GridBagConstraints cons = new GridBagConstraints();  

	cons.fill = GridBagConstraints.HORIZONTAL;
	cons.gridwidth  = GridBagConstraints.REMAINDER;	
	cons.weighty  = 0;
	cons.weightx = 1;
	
	cons.insets = new Insets(2, 2, 0, 2);	
	JPanel p1 = new JPanel(new GridBagLayout());
	p1.setBackground(Preferencias.COR_DE_FUNDO_PANE);
	this.add(p1, cons);
	
	cons.insets = new Insets(0, 2, 0, 2);
	JPanel p4 = new JPanel(new GridBagLayout());
	p4.setBackground(Preferencias.COR_DE_FUNDO_PANE);
	this.add(p4, cons);
	
	this.p_endereco = new PainelDeEndereco();
	this.add(p_endereco, cons);
	
	JPanel p2 = new JPanel(new GridBagLayout());
	p2.setBackground(Preferencias.COR_DE_FUNDO_PANE);
	this.add(p2, cons);
	
	cons.insets = new Insets(0, 2, 2, 2);
	JPanel p3 = new JPanel(new GridBagLayout());
	p3.setBackground(Preferencias.COR_DE_FUNDO_PANE);
	this.add(p3, cons);
	
		
	cons.fill = GridBagConstraints.HORIZONTAL;
	cons.gridwidth  = 1;	
	cons.weighty  = 0;
	cons.weightx = 0.1;
	cons.insets = new Insets(2, 2, 0, 2);
	p1.add(new JLabel("<html>Código:<font color=red>*</font></html>"), cons);
	cons.weightx = 0.25;
	p1.add(new JLabel("<html>Nome/Razão Social:<font color=red>*</font></html>"), cons);
	p1.add(new JLabel("Subtítulo:"), cons);
	cons.weightx = 0.4;
	cons.gridwidth  = GridBagConstraints.REMAINDER;	
	p1.add(new JLabel("Nome Completo:"), cons);
	
	cons.gridwidth  = 1;	
	cons.weightx = 0.1;
	cons.insets = new Insets(2, 2, 2, 2);
	this.tf_codigo = new CampoLimitado(200);
	p1.add(this.tf_codigo, cons);
	
	cons.weightx = 0.25;
	this.tf_nome = new CampoLimitado(200);
	p1.add(this.tf_nome, cons);
	
	this.tf_subtitulo = new CampoLimitado(100);
	p1.add(this.tf_subtitulo, cons);
	
	cons.weightx = 0.4;
	cons.gridwidth  = GridBagConstraints.REMAINDER;	
	this.tf_nome_completo = new CampoLimitado(300);
	this.tf_nome_completo.setEditable(false);
	p1.add(this.tf_nome_completo, cons);
	
	
	
	cons.gridwidth  = 1;	
	cons.weightx = 1;
	cons.insets = new Insets(2, 2, 0, 2);
	p4.add(new JLabel("Insc. Estadual:"), cons);
	p4.add(new JLabel("<html>Insc. Municipal:<font color=red>*</font></html>"), cons);
	p4.add(new JLabel("<html>CNAE:<font color=red>*</font></html>"), cons);
	p4.add(new JLabel("<html>CNPJ:<font color=red>*</font></html>"), cons);
	cons.gridwidth  = GridBagConstraints.REMAINDER;	
	p4.add(new JLabel("<html>Nome Fantasia:<font color=red>*</font></html>"), cons);
	
	
	cons.gridwidth  = 1;	
	cons.insets = new Insets(2, 2, 2, 2);
	this.tf_ie = new CampoLimitadoSoDigito(16);
	p4.add(this.tf_ie, cons);
	

	this.tf_insc_municipal = new CampoLimitadoSoDigito(15);
	p4.add(this.tf_insc_municipal, cons);
	
	this.tf_cnae = new CampoLimitadoSoDigito(7);
	p4.add(this.tf_cnae, cons);
	
	this.tf_cnpj = new CampoCNPJ();
	p4.add(this.tf_cnpj, cons);
	
	cons.gridwidth  = GridBagConstraints.REMAINDER;	
	this.tf_nome_fantasia = new CampoLimitado(250);
	p4.add(this.tf_nome_fantasia, cons);
	
	
	

	cons.gridwidth  = 1;
	cons.insets = new Insets(2, 2, 0, 2);
	cons.weightx = 1;
	p2.add(new JLabel("<html>Tel. 1:<font color=red>*</font></html>"), cons);
	p2.add(new JLabel("Tel. 2:"), cons);
	p2.add(new JLabel("Site:"), cons);
	cons.gridwidth  = GridBagConstraints.REMAINDER;	
	p2.add(new JLabel("E-mail:"), cons);
	
	cons.gridwidth  = 1;
	cons.weightx = 1;
	cons.insets = new Insets(2, 2, 2, 2);
	this.tf_tel_1 = new CampoTEL();
	p2.add(this.tf_tel_1, cons);
		
	
	this.tf_tel_2 = new CampoTEL();
	p2.add(this.tf_tel_2, cons);
		
	this.tf_site = new CampoLimitado(150);
	p2.add(this.tf_site, cons);
	
	cons.gridwidth  = GridBagConstraints.REMAINDER;
	this.tf_email = new CampoLimitado(150);
	p2.add(this.tf_email, cons);	

	cons.gridwidth  = GridBagConstraints.REMAINDER;
	p3.add(new JLabel("Valores Padrão:"), cons);
	p3.add(new JSeparator(SwingConstants.HORIZONTAL), cons);
	
	cons.gridwidth  = 1;
	cons.insets = new Insets(2, 2, 0, 2);
	p3.add(new JLabel("Cidade:"), cons);
	p3.add(new JLabel("País:"), cons);
	p3.add(new JLabel("Itens por Página:"), cons);
	cons.gridwidth  = GridBagConstraints.REMAINDER;	
	p3.add(new JLabel("Saldo Inicial De Caixa (R$):"), cons);
	
	cons.gridwidth  = 1;
	cons.insets = new Insets(2, 2, 2, 2);
	this.tf_cidade_padrao = new CampoLimitado(150);
	p3.add(this.tf_cidade_padrao, cons);
	this.tf_cidade_padrao.setEditable(false);
	
	this.tf_pais_padrao = new CampoLimitado(150);
	p3.add(this.tf_pais_padrao, cons);
	this.tf_pais_padrao.setText("Brasil");
	
	this.tf_itens_por_pagina = new CampoLimitadoSoDigito(2);
	p3.add(this.tf_itens_por_pagina, cons);
	this.tf_itens_por_pagina.setText("15");

	cons.gridwidth  = GridBagConstraints.REMAINDER;	
	this.tf_saldoPadraoInicialDeCaixa = new CampoMoeda(6);
	p3.add(this.tf_saldoPadraoInicialDeCaixa, cons);
	this.tf_saldoPadraoInicialDeCaixa.setText("150,00");
	
	cons.gridwidth  = 1;
	cons.insets = new Insets(2, 2, 0, 2);
	p3.add(new JLabel("Desconto Padrão (%):"), cons);
	p3.add(new JLabel("Lucro Padrão - Produtos (%):"), cons);
	p3.add(new JLabel("Quant. Min. Estoque:"), cons);
	cons.gridwidth  = GridBagConstraints.REMAINDER;	
	p3.add(new JLabel("Quant. Min. Inventário:"), cons);
	
	
	cons.gridwidth  = 1;
	cons.insets = new Insets(2, 2, 2, 2);
	this.tf_descontoPadrao = new CampoMoeda(4);
	p3.add(this.tf_descontoPadrao, cons);
	this.tf_descontoPadrao.setText("0,00");
	
	this.tf_lucroPadraoDeProduto = new CampoMoeda(4);
	p3.add(this.tf_lucroPadraoDeProduto, cons);
	this.tf_lucroPadraoDeProduto.setText("70,00");
	
	this.tf_quantMinPadraoEstoque = new CampoLimitadoSoDigito(2);
	p3.add(this.tf_quantMinPadraoEstoque, cons);
	this.tf_quantMinPadraoEstoque.setText("1");
	
	
	cons.gridwidth  = GridBagConstraints.REMAINDER;	
	this.tf_quantMinPadraoInventario = new CampoLimitadoSoDigito(2);
	p3.add(this.tf_quantMinPadraoInventario, cons);
	this.tf_quantMinPadraoInventario.setText("1");
	
	
	cons.gridwidth  = 1;
	cons.insets = new Insets(2, 2, 0, 2);
	p3.add(new JLabel("Multa Padrão (%):"), cons);
	p3.add(new JLabel("Juros Padão a.m. (%):"), cons);
	cons.gridwidth  = GridBagConstraints.REMAINDER;	
	p3.add(new JLabel("Dias de Mês Comercial:"), cons);

	
	cons.gridwidth  = 1;
	cons.insets = new Insets(2, 2, 2, 2);
	this.tf_porcentMultaPadrao = new CampoMoeda(4);
	p3.add(this.tf_porcentMultaPadrao, cons);
	this.tf_porcentMultaPadrao.setText("2,00");
	
	this.tf_porcentjurosPadraoAoMes = new CampoMoeda(4);
	p3.add(this.tf_porcentjurosPadraoAoMes, cons);
	this.tf_porcentjurosPadraoAoMes.setText("1,00");
	
	cons.gridwidth  = GridBagConstraints.REMAINDER;	
	this.tf_numDeDiasDeMesComercial = new CampoLimitadoSoDigito(2);
	p3.add(this.tf_numDeDiasDeMesComercial, cons);
	this.tf_numDeDiasDeMesComercial.setText("28");
	
	cons.gridwidth  = 1;
	cons.insets = new Insets(2, 2, 0, 2);
	p3.add(new JLabel("Comissão de Venda (%):"), cons);
	cons.gridwidth  = GridBagConstraints.REMAINDER;	
	p3.add(new JLabel("Frase/Lema:"), cons);
	
	
	
	cons.gridwidth  = 1;
	cons.insets = new Insets(2, 2, 2, 2);
	this.tf_porcentComissaoPadrao = new CampoMoeda(4);
	p3.add(this.tf_porcentComissaoPadrao, cons);
	this.tf_porcentComissaoPadrao.setText("0,00");
	
	cons.gridwidth  = GridBagConstraints.REMAINDER;	
	this.tf_frase = new CampoLimitado(250);
	p3.add(this.tf_frase, cons);
	
	
	cons.gridwidth  = GridBagConstraints.REMAINDER;
	p3.add(new JLabel("Tributação:"), cons);
	p3.add(new JSeparator(SwingConstants.HORIZONTAL), cons);
	
	
	cons.fill = GridBagConstraints.HORIZONTAL;
	cons.weightx = 1;
	cons.weighty  = 0;
	cons.insets = new Insets(2, 2, 0, 2);
	cons.gridwidth  = 1;
	p3.add(new Rotulo("Regime Tributário:"), cons);
	p3.add(new Rotulo("ICMS (%):"), cons);
	p3.add(new Rotulo("PIS (%):"), cons);
	p3.add(new Rotulo("COFINS (%):"), cons);
	cons.gridwidth  = GridBagConstraints.REMAINDER;
	p3.add(new Rotulo("IPI (%):"), cons);
	
	
	cons.gridwidth  = 1;
	cons.insets = new Insets(2, 2, 2, 2);
	this.regime_tributario = new JComboBox<String>(new String[]{"LUCRO_PRESUMIDO", "LUCRO_REAL", "SIMPLES", "MEI"});
	p3.add(this.regime_tributario, cons);
	
	this.valor_icms_porcento = new CampoMoeda(4);
	p3.add(this.valor_icms_porcento, cons);
	
	this.valor_pis_porcento = new CampoMoeda(4);
	p3.add(this.valor_pis_porcento, cons);
	
	this.valor_cofins_porcento = new CampoMoeda(4);
	p3.add(this.valor_cofins_porcento, cons);
	
	cons.gridwidth  = GridBagConstraints.REMAINDER;
	this.valor_ipi_porcento = new CampoMoeda(4);
	p3.add(this.valor_ipi_porcento, cons);
	
	
	cons.fill = GridBagConstraints.HORIZONTAL;
	cons.gridwidth  = 1;		
	cons.insets = new Insets(2, 2, 0, 2);
	p3.add(new Rotulo("CSLL (%):"), cons);
	p3.add(new Rotulo("IRPJ (%):"), cons);
	p3.add(new Rotulo("ISSQN (%):"), cons);
	p3.add(new Rotulo("Comissão (%):"), cons);
	cons.gridwidth  = GridBagConstraints.REMAINDER;
	p3.add(new Rotulo("Custo Adm. (%):"), cons);
	
	cons.gridwidth  = 1;
	cons.insets = new Insets(2, 2, 2, 2);
	this.valor_csll_porcento = new CampoMoeda(4);
	p3.add(this.valor_csll_porcento, cons);
	
	this.valor_irpj_porcento = new CampoMoeda(4);
	p3.add(this.valor_irpj_porcento, cons);
	
	this.valor_issqn_porcento = new CampoMoeda(4);
	p3.add(this.valor_issqn_porcento, cons);
	
	this.valor_comissao_porcento = new CampoMoeda(4);
	p3.add(this.valor_comissao_porcento, cons);
	
	cons.gridwidth  = GridBagConstraints.REMAINDER;
	this.valor_administrativa_porcento = new CampoMoeda(4);
	p3.add(this.valor_administrativa_porcento, cons);
	
	
	
	cons.fill = GridBagConstraints.NONE;
	cons.anchor = GridBagConstraints.EAST;
	cons.weighty  = 0;
	cons.weightx = 0;
	cons.ipadx = 15;
	cons.gridwidth  = GridBagConstraints.REMAINDER;	
	JButton bt_salvar  = new JButton("Salvar", new ImageIcon(getClass().getResource("/icons/icon_save.png")));
	bt_salvar.setToolTipText("Salvar dados da empresa");
	this.add(bt_salvar, cons);
	
	
	
		bt_salvar.addActionListener( new ActionListener(){
		@Override
		public void actionPerformed( ActionEvent event ){
			    	
			if(acaoPrincipal()){	
				
			Mensagens.msgDeSucessoAoSalvar();
			dispose();
			}		
		}});
		

		
		this.tf_nome.addFocusListener( new FocusAdapter(){	
		@Override 
		public void focusLost(FocusEvent e) {
			
		setNomeCompleto();	
		}});
		
		
		this.tf_subtitulo.addFocusListener( new FocusAdapter(){		
		@Override 
		public void focusLost(FocusEvent e) {
			
		setNomeCompleto();
		}});
		
		
		
		this.tf_cidade_padrao.addFocusListener( new FocusAdapter(){		
		@Override 
		public void focusGained(FocusEvent e) {
				
		Comuns.removeIndicadoresDeErro(tf_cidade_padrao);	
		tf_pais_padrao.requestFocusInWindow();
		addCidadePadrao();
		}});
			
		
		
		
	List<JTextField> textFieldList = new ArrayList<JTextField>();
	
	textFieldList.add(tf_nome);
	textFieldList.add(tf_tel_1);
	textFieldList.add(tf_itens_por_pagina);
	textFieldList.add(tf_quantMinPadraoInventario);
	textFieldList.add(this.tf_quantMinPadraoEstoque);
	textFieldList.add(this.tf_numDeDiasDeMesComercial);
	
	
	textFieldList.add(tf_cnpj);
	textFieldList.add(tf_cnae);
	textFieldList.add(tf_ie);
	textFieldList.add(tf_insc_municipal);
	textFieldList.add(tf_nome_fantasia);
	
	Comuns.addEventoDeFoco(textFieldList);
	
	this.getRootPane().setDefaultButton(bt_salvar);
	}
	
	
	
	
	
	private void setNomeCompleto(){
	
	if(this.tf_nome.getText().length() == 0)
	this.tf_nome_completo.setText("");
	else
	this.tf_nome_completo.setText(this.tf_nome.getText()+(this.tf_subtitulo.getText().length()> 0?" - "+this.tf_subtitulo.getText():""));
	}
	
	


	
	@Override
	public boolean acaoPrincipal() {
		
	if(!this.validation())	
	return false;
	
	this.setNomeCompleto();	
	
	if(this.empresa == null)	
	this.empresa = new Empresa();
	
	empresa.setNome(this.tf_nome.getText());
	empresa.setCodigo(this.tf_codigo.getText());
	empresa.setSubtitulo(this.tf_subtitulo.getText());
	empresa.setNome_completo(this.tf_nome_completo.getText());
	empresa.setTel_1(this.tf_tel_1.getText());
	
	empresa.setTel_2(this.tf_tel_2.getText());
	empresa.setSite(this.tf_site.getText());
	empresa.setEmail(this.tf_email.getText());
	empresa.setPais_padrao(this.tf_pais_padrao.getText());
	
	empresa.setItens_por_pagina(Integer.parseInt(this.tf_itens_por_pagina.getText()));
	empresa.setSaldo_inicial_caixa(this.tf_saldoPadraoInicialDeCaixa.getText()); 
	empresa.setDesconto_padrao(this.tf_descontoPadrao.getText()); 
	empresa.setPorcento_lucro_produto(this.tf_lucroPadraoDeProduto.getText()); 
	
	empresa.setQuant_min_estoque(Integer.parseInt(this.tf_quantMinPadraoEstoque.getText())); 
	empresa.setQuant_min_inventario(Integer.parseInt(this.tf_quantMinPadraoInventario.getText())); 
	empresa.setPorcento_multa_padrao(this.tf_porcentMultaPadrao.getText()); 	
	empresa.setPorcento_juros_ao_mes_padrao(this.tf_porcentjurosPadraoAoMes.getText()); 
	empresa.setNum_dias_mes_comercial(Integer.parseInt(this.tf_numDeDiasDeMesComercial.getText())); 
	
	empresa.setPorcento_comissao_padrao(this.tf_porcentComissaoPadrao.getText()); 
	
	empresa.setCnpj(tf_cnpj.getText()); 
	empresa.setCnae(tf_cnae.getText()); 
	empresa.setInscricao_estadual(tf_ie.getText()); 
	empresa.setInscricao_municipal(tf_insc_municipal.getText()); 
	empresa.setNome_fantasia(tf_nome_fantasia.getText()); 
	empresa.setFrase_lema(this.tf_frase.getText());
	empresa.setCodigo_municipio_padrao(this.id_cidade_padrao);
	
	if(this.aliquota == null)	
	this.aliquota = new Aliquota();
	
	this.aliquota.setRegime_tributario(this.regime_tributario.getSelectedItem().toString());
	this.aliquota.setValor_icms_porcento(this.valor_icms_porcento.getText());
	this.aliquota.setValor_pis_porcento(this.valor_pis_porcento.getText());
	this.aliquota.setValor_cofins_porcento(this.valor_cofins_porcento.getText());
	this.aliquota.setValor_ipi_porcento(this.valor_ipi_porcento.getText());
	this.aliquota.setValor_csll_porcento(this.valor_csll_porcento.getText());
	this.aliquota.setValor_irpj_porcento(this.valor_irpj_porcento.getText());
	this.aliquota.setValor_issqn_porcento(this.valor_issqn_porcento.getText());
	this.aliquota.setValor_comissao_porcento(this.valor_comissao_porcento.getText());
	this.aliquota.setValor_administrativa_porcento(this.valor_administrativa_porcento.getText());

	
		if(this.empresa.getFk_endereco() == 0){
		
		int id = new DAO<Endereco>(Endereco.class).novo(this.p_endereco.getValores());
		
		if(id == 0)
		return false;
		
		this.empresa.setFk_endereco(id);
		}
		else{
	
		if(!new DAO<Endereco>(Endereco.class).altera(this.p_endereco.getValores()))
		return false;
		}
	

		
		
		if(this.aliquota.getId_aliquota() == 0){
			
		if(new DAO<Aliquota>(Aliquota.class).novo(this.aliquota)==0)
		return false;
		}
		else{
		
		if(!new DAO<Aliquota>(Aliquota.class).altera(this.aliquota))
		return false;
		}
		
		
		
		if(this.empresa.getId_unidade() == 0){
		
		int id = new DAO<Empresa>(Empresa.class).novo(empresa);
		
		if(id == 0)
		return false;
		
		this.empresa.setId_unidade(id);
		}
		else{
	
		if(!new DAO<Empresa>(Empresa.class).altera(empresa))
		return false;
		}


	return true;
	}
	
	
	
	
	

	protected boolean validation(){
	
		if(this.tf_codigo.getText().length() == 0){
			
		Mensagens.msgDeErro("Informe um código para o convênio.");
		Comuns.textFieldErroMode(this.tf_codigo);
		return false;	
		}
				
				
		if(!Comuns.codigoPermitido(new DAO<Empresa>(Empresa.class), this.tf_codigo.getText(), this.empresa!=null?this.empresa.getId_unidade():0)){
					
		Mensagens.msgDeErro("O código informado já está sendo usado por outro convênio.");
		Comuns.textFieldErroMode(this.tf_codigo);
		return false;	
		}
				
		
		if(this.tf_nome.getText().length() == 0){
		
		Mensagens.msgDeErro("Informe o nome da empresa.");
		Comuns.textFieldErroMode(this.tf_nome);
		return false;	
		}
		
		
		if(!this.tf_cnpj.validacao()){
			
		Mensagens.msgDeErro("Número de CNPJ inválido.");
		Comuns.textFieldErroMode(this.tf_cnpj);
		return false;	
		}
		
		
		if(this.tf_nome_fantasia.getText().length() == 0){
			
		Mensagens.msgDeErro("Informe o nome fantasia da empresa.");
		Comuns.textFieldErroMode(this.tf_nome_fantasia);
		return false;	
		}
		
		
		if(this.tf_cnae.getText().length() != 7){
			
		Mensagens.msgDeErro("Informe um código CNAE válido.");
		Comuns.textFieldErroMode(this.tf_cnae);
		return false;	
		}
		
		
		if(this.tf_insc_municipal.getText().length() ==0){
			
		Mensagens.msgDeErro("Informe uma inscrição municipal válida.");
		Comuns.textFieldErroMode(this.tf_insc_municipal);
		return false;	
		}
		
		
		if(this.tf_ie.getText().length() > 0){    
		final Matcher matcher = Pattern.compile("^(ISENTO|[0-9]{2,14}|)$").matcher(this.tf_ie.getText());
			if (!matcher.find()) {
	
			Mensagens.msgDeErro("Informe uma inscrição estadual válida.");
	        Comuns.textFieldErroMode(this.tf_ie);
	        return false;	
	        }
		}
				
		
		if(!this.tf_tel_1.validacao()){
			
		Mensagens.msgDeErro("Informe um telefone 1 válido.");
		Comuns.textFieldErroMode(this.tf_tel_1);
		return false;	
		}
		
		if(this.tf_tel_2.getText().length() > 0){
			if(!this.tf_tel_2.validacao()){
		
			Mensagens.msgDeErro("Telefone 2 é opcional, caso queira informá-lo, informe um número válido.");
			Comuns.textFieldErroMode(this.tf_tel_2);
			return false;
			}
		}						
		
		
		if(this.tf_itens_por_pagina.getText().length()==0 || Integer.parseInt(this.tf_itens_por_pagina.getText()) < 15){
		
		Mensagens.msgDeErro("O número mínimo de itens por página é 15.");
		Comuns.textFieldErroMode(this.tf_itens_por_pagina);
		return false;		
		}
		
		if(this.tf_quantMinPadraoEstoque.getText().length()==0 || Integer.parseInt(this.tf_quantMinPadraoEstoque.getText()) < 1){
			
		Mensagens.msgDeErro("O número mínimo de itens de estoque é 1.");
		Comuns.textFieldErroMode(this.tf_quantMinPadraoEstoque);
		return false;		
		}
		
		if(this.tf_quantMinPadraoInventario.getText().length()==0 || Integer.parseInt(this.tf_quantMinPadraoInventario.getText()) < 1){
			
		Mensagens.msgDeErro("O número mínimo de itens de inventário é 1.");
		Comuns.textFieldErroMode(this.tf_quantMinPadraoInventario);
		return false;		
		}
		
		if(this.tf_numDeDiasDeMesComercial.getText().length()==0 || Integer.parseInt(this.tf_numDeDiasDeMesComercial.getText()) < 28){
			
		Mensagens.msgDeErro("O número mínimo de dias de um mês comercial é 28.");
		Comuns.textFieldErroMode(this.tf_numDeDiasDeMesComercial);
		return false;		
		}
		
		
	return this.p_endereco.validacao();	
	}





	private void addCidadePadrao(){
		
	Municipio retorno = new Municipio();
		
	FormDeSelecao<Municipio> selectionItemForm = 
						new FormDeSelecao<Municipio>("Adicionar Município", retorno, Municipio.class, null);
	selectionItemForm.mostrar();
					
		if(retorno != null && retorno.getCodigo() > 0){
								
		this.id_cidade_padrao = retorno.getCodigo();
		this.tf_cidade_padrao.setText(retorno.getMunicipio());
		}
	}



}
