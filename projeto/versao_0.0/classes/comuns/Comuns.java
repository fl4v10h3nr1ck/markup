
package comuns;

import java.awt.Color;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.lang.reflect.Field;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.regex.Pattern;

import javax.swing.JTextField;

import pdv.terminal.ConfigTerminal;
import pdv.terminal.FormConfiguracaoDeTerminal;
import principal.caixa.FormAbreCaixa;
import DAO.DAO;
import componentes.anotacoes.Anot_BD_Campo;
import componentes.anotacoes.Anot_BD_Tabela;
import componentes.beans.Aliquota;
import componentes.beans.Caixa;
import componentes.beans.Cliente;
import componentes.beans.Colaborador;
import componentes.beans.Compra;
import componentes.beans.Credor;
import componentes.beans.Despesa;
import componentes.beans.Empresa;
import componentes.beans.FormaDePagamento;
import componentes.beans.Fornecedor;
import componentes.beans.Inventario;
import componentes.beans.Item;
import componentes.beans.Movimento;
import componentes.beans.Produto;



public class Comuns {



public static String cod_terminal;
public static String descricao_terminal;
public static int    iDCaixa;
public static int    iDVendedor;
public static String porcentoComissaoVendedor;

public static String porcentComissaoPadrao;	
public static String porcentMultaPadrao;
public static String porcentjurosPadraoAoMes;
public static int numDeDiasDeMesComercial;
public static String saldoPadraoInicialDeCaixa;
public static String lucroPadraoDeProduto;
public static String lucroPadraoDeServico;
public static int quantMinPadraoEstoque;
public static int quantMinPadraoInventario;	
public static String aliquota_ICMS;


public static int id_municipio_padrao;
public static String paisPadrao;
public static int numMaxDeItensPorPag;

public static String frase;


	
public static final String UNIDADE_MEDIDA_PADRAO = "UN";
public static final String UNIDADE_MEDIDA_FRAC_UNIDADE = "UN";
public static final String UNIDADE_MEDIDA_FRAC_KILO = "Kg";
public static final String UNIDADE_MEDIDA_FRAC_LITRO = "L";
	
	
public static int tam_descrica_abreviada_produto;
	
	
	
/******************* comuns de formularios ***********************/

	
//public static final String[] ufs = {"PA", "AC", "AL", "AP", "AM",  "BA", "CE",  "DF",  "ES",  "GO",  "MA",  "MT",  "MS",  "MG", "PB",  "PR",  "PE",  "RJ",  "RJ",  "RN",  "RS", "RO",  "RR",  "SC",  "SP",  "SE",  "TO"};
	



/******************* comuns de configuracao ***********************/





	public static void setInfosPadroes(){
	
	// limite ECF bematech é 29	
	Comuns.tam_descrica_abreviada_produto = 25;		
		
	
	List<Empresa> empresas= new DAO<Empresa>(Empresa.class).get(null, null, null);		
		
		if(empresas != null && empresas.size()>0){
		
		Comuns.id_municipio_padrao = empresas.get(0).getCodigo_municipio_padrao();
		Comuns.paisPadrao = empresas.get(0).getPais_padrao()==null?"":empresas.get(0).getPais_padrao();
		Comuns.numMaxDeItensPorPag = empresas.get(0).getItens_por_pagina()==0?15:empresas.get(0).getItens_por_pagina();	
		
		Comuns.porcentMultaPadrao = empresas.get(0).getPorcento_multa_padrao()==null?"0.00":empresas.get(0).getPorcento_multa_padrao();
		Comuns.porcentjurosPadraoAoMes = empresas.get(0).getPorcento_juros_ao_mes_padrao()==null?"0.00":empresas.get(0).getPorcento_juros_ao_mes_padrao();
		Comuns.numDeDiasDeMesComercial = empresas.get(0).getNum_dias_mes_comercial()==0?28:empresas.get(0).getNum_dias_mes_comercial();
		Comuns.saldoPadraoInicialDeCaixa = empresas.get(0).getSaldo_inicial_caixa()==null?"0.00":empresas.get(0).getSaldo_inicial_caixa();
		Comuns.porcentComissaoPadrao = empresas.get(0).getPorcento_comissao_padrao()==null?"0.00":empresas.get(0).getPorcento_comissao_padrao();
		Comuns.lucroPadraoDeProduto = empresas.get(0).getPorcento_lucro_produto()==null?"0.00":empresas.get(0).getPorcento_lucro_produto();
		
		Comuns.quantMinPadraoEstoque = empresas.get(0).getQuant_min_estoque()==0?1:empresas.get(0).getQuant_min_estoque();
		Comuns.quantMinPadraoInventario = empresas.get(0).getQuant_min_inventario()==0?1:empresas.get(0).getQuant_min_inventario();
		
		Comuns.frase = empresas.get(0).getFrase_lema()==null?"":empresas.get(0).getFrase_lema();
		}
		else{
			
		Comuns.id_municipio_padrao = 0;
		Comuns.paisPadrao = "";
		Comuns.numMaxDeItensPorPag = 15;	
			
		Comuns.porcentMultaPadrao = "0.00";
		Comuns.porcentjurosPadraoAoMes = "0.00";
		Comuns.numDeDiasDeMesComercial = 28;
		Comuns.saldoPadraoInicialDeCaixa = "0.00";
		Comuns.porcentComissaoPadrao = "0.00";
		Comuns.lucroPadraoDeProduto = "0.00";
			
		Comuns.quantMinPadraoEstoque = 1;
		Comuns.quantMinPadraoInventario = 1;
		
		Comuns.frase = "";
		}
	
	
	Comuns.configuraCaixa();
	}



	
	
	public static void configuraCaixa(){
		
	ConfigTerminal config = 	new ConfigTerminal();	
		
	Item credenciais = config.getCredenciais();	
		
	if(credenciais==null)
	return;
		
	Comuns.cod_terminal= credenciais.getParamentro(config.COD_TERMINAL_CHAVE).toString();
	Comuns.descricao_terminal= credenciais.getParamentro(config.COD_TERMINAL_NOME).toString();	
	
	List<Caixa> caixa_aberto = new DAO<Caixa>(Caixa.class).get(null, 
			"cai.status='ABERTO' and cai.codigo_terminal='"+Comuns.cod_terminal+"'", null);
	
	if(caixa_aberto.size()>0)
	Comuns.iDCaixa = caixa_aberto.get(0).getId_caixa();
	
	
	List<Colaborador> colaborador = new DAO<Colaborador>(Colaborador.class).get(null, 
			"cola.status='ATIVO' and cola.fk_usuario="+Configuracao.usuarioAtual.getId(), null);
	
		if(colaborador.size()>0){
		
		Comuns.iDVendedor = colaborador.get(0).getId_colaborador();
		Comuns.porcentoComissaoVendedor =colaborador.get(0).getValor_comissao(); 
		}
		else
		Comuns.porcentoComissaoVendedor =Comuns.porcentComissaoPadrao;
	
	
	List<Aliquota> aliquotas = new DAO<Aliquota>(Aliquota.class).get(null, "", null);
	
	if(aliquotas.size()>0)
	Comuns.aliquota_ICMS = aliquotas.get(0).getValor_icms_porcento()==null?"0000":Comuns.addPaddingAEsquerda(aliquotas.get(0).getValor_icms_porcento().replace(".", "").replace(",", ""), 4, "0");
	
	System.out.println(Comuns.aliquota_ICMS);
	}
	
	

	
	
	
	public static boolean validaParaAbrirCaixa(){
		
	
		if(Comuns.cod_terminal== null || Comuns.cod_terminal.length()==0){
		
		Mensagens.msgDeErro("O código ID deste terminal não está definido.");	
				
		FormConfiguracaoDeTerminal form = new FormConfiguracaoDeTerminal();	
		form.mostrar();
		
		if(Comuns.cod_terminal== null || Comuns.cod_terminal.length()==0)
		return false;
		
		}
		
		
		if(Comuns.iDVendedor==0){
			
		Mensagens.msgDeErro("O usuário atual não possui colaborador vinculado.");	
		return false;		
		}
		
		
		if(Comuns.iDCaixa==0){
		
		Item item = new Item();	
			
		FormAbreCaixa form = new FormAbreCaixa(item);	
		form.mostrar();
		
		if(item.getParamentro("id")==null || item.getParamentro("id").toString().length()==0)
		return false;
		
		Comuns.iDCaixa = Integer.parseInt(item.getParamentro("id").toString());
		}
	
		
	return true;	
	}
	
	
	
	
	
	
	public static String getCod(Class<?> tipo, int id){
		
		if(id > 0 && tipo != null){
		
		if(tipo== FormaDePagamento.class)	
		return "FPAG"+String.format("%06d", id);
		
		if(tipo== Movimento.class)	
		return "MOV"+String.format("%06d", id);
		
		if(tipo== Cliente.class)	
		return "CLT"+String.format("%06d", id);
		
		if(tipo== Credor.class)	
		return "CRED"+String.format("%06d", id);
		
		if(tipo== Fornecedor.class)	
		return "FORN"+String.format("%06d", id);
		
		if(tipo== Produto.class)	
		return "PROD"+String.format("%06d", id);
		
		if(tipo== Inventario.class)	
		return "INV"+String.format("%06d", id);
			
		if(tipo== Despesa.class)	
		return "DESP"+String.format("%06d", id);
		
		if(tipo== Compra.class)	
		return "COMP"+String.format("%06d", id);
		
		if(tipo== Colaborador.class)	
		return "COLA"+String.format("%06d", id);
		
		if(tipo== Caixa.class)	
		return "CAI"+String.format("%06d", id);
		}
	
	return "";
	}
	
	
	
	

	public static String getUnidadeDeMedidaDeProduto(Produto produto){
		
		if(produto != null){
			
		if(produto.getVenda_fracionada()==null || produto.getVenda_fracionada().compareTo("NAO")==0)
		return Comuns.UNIDADE_MEDIDA_PADRAO;
			else{
			
			if(produto.getTipo_venda_fracionada().compareTo("UNIDADE")== 0)
			return Comuns.UNIDADE_MEDIDA_FRAC_UNIDADE;	
			
			if(produto.getTipo_venda_fracionada().compareTo("KILO")== 0)
			return Comuns.UNIDADE_MEDIDA_FRAC_KILO;	
			
			if(produto.getTipo_venda_fracionada().compareTo("LITRO")== 0)
			return Comuns.UNIDADE_MEDIDA_FRAC_LITRO;	
			}
		}

	return Comuns.UNIDADE_MEDIDA_PADRAO;
	}
	
	
	

	
	/******************* validacao ***********************/


	
	

	public static void textFieldErroMode(JTextField field){	
	
	if(field == null)
	return;
	
	if(field.getText().length() == 0)
	field.setBackground(Color.red);
	else
	field.setForeground(Color.red);	
		
	}
	
	
	
	
	
	public static void addEventoDeFoco(List<JTextField> fields){
		
	if(fields == null)
	return;
	
		for( final JTextField field : fields){
	
			field.addFocusListener(new FocusAdapter() {  
			
				@Override
				public void focusGained(FocusEvent e) {
		   
				if(field.getText().length() == 0)
				field.setBackground(Color.white);
				else
				field.setForeground(Color.black);	
				}
			});
		}
	}


	
	

	public static void removeIndicadoresDeErro(JTextField field){
		
	if(field == null)
	return;
			
	if(field.getText().length() == 0)
	field.setBackground(Color.white);
	else
	field.setForeground(Color.black);	
		
	}

	
	
	


	public static boolean emailValidation(String email){

	if(email.length() == 0)
	return false;

	Pattern pattern = Pattern.compile(".+@.+\\.[a-z]+");  
	   
	if(pattern.matcher(email).matches())
	return true;
	else
	return false;
	}

	
	
	
	
	
	
	/******************* search ***********************/

	
	
	public static String searchSubQuery(String search, String locale){
		
	
	if(search == null || locale == null || search.length() == 0 || locale.length() == 0) 
	return "";
						
	StringBuilder subquery = new StringBuilder();	
			
	String[] tokens = search.split("\\s");
	String[] fields = locale.split("\\s");
			
		for (int i=0; i<fields.length; i++){
			    
		subquery.append(" ( ");	 
			    
		for (int j=0; j<tokens.length; j++){
		subquery.append(fields[i]+ " like '%"+tokens[j]+"%' ");
				    			 
		if(j< tokens.length-1)
		subquery.append(" AND ");	
		}
				
	subquery.append(" ) ");	
				
		if(i< fields.length-1)
		subquery.append(" OR ");
		}
	
	return subquery.toString();
	}
	



	
	
	

	/******************* outros ***********************/
	
	

	
	
	@SuppressWarnings("rawtypes")
	public static boolean codigoPermitido(DAO dao, String codigo, int id_remove){
		
	if(dao==null)
	return false;
		
	Class<?> tipo = dao.getTipo();
	
	String prefixo = tipo.getAnnotation(Anot_BD_Tabela.class).prefixo();

	
	String id = "";
	
		if(id_remove>0){

			for (Field field : tipo.getDeclaredFields()) {
				
				if (field.getAnnotation(Anot_BD_Campo.class).ehId()){
				id +=" AND "+field.getAnnotation(Anot_BD_Campo.class).nome()+"<>"+id_remove;
				break;
				}
			}
		}
		
	return dao.get(null, prefixo+".codigo='"+codigo+"'"+id, null).size()>0?false:true;
	}
	
	
	
	
	
	

	public static String addPaddingAEsquerda(String valor, int num, String add){
		
	
	for(int i  = valor.length(); i < num; i++)
	valor = add+valor;
		
	return valor;	
	}
	
	
	
	
	
	
	
	/******************* matematica ***********************/
	
	
	
	
	
	public static int comparaValorMonetario(String valor1, String valor2){
		
	double val1 = Calculo.stringParaDouble(valor1);
	double val2 = Calculo.stringParaDouble(valor2);
		

	if(val1>val2)
	return 1;
	
	if(val1==val2)
	return 0;
	
	return -1;	
	}
	
	
	
	
	public static int calculaIdadeRetornaInteiro(Date nascimento){
	
	if(nascimento == null)
	return 0;
		
	Calendar dateOfBirth = new GregorianCalendar();
	dateOfBirth.setTime(nascimento);
	
	Calendar today = Calendar.getInstance();

	int age = today.get(Calendar.YEAR) - dateOfBirth.get(Calendar.YEAR);
	dateOfBirth.add(Calendar.YEAR, age);
	
	if (today.before(dateOfBirth))
	age--;

	return age;
	}
	
	
	

	
	public static String calculaIdade(Date nascimento){
		
	if(nascimento == null)
	return "";
			
	Calendar dateOfBirth = new GregorianCalendar();
	dateOfBirth.setTime(nascimento);
		
	Calendar today = Calendar.getInstance();

	int age = today.get(Calendar.YEAR) - dateOfBirth.get(Calendar.YEAR);
	
		if(age == 0){
		
		int meses = today.get(Calendar.MONTH) - dateOfBirth.get(Calendar.MONTH);	
		
			if(meses > 0){
			
			if(today.get(Calendar.DAY_OF_MONTH) - dateOfBirth.get(Calendar.DAY_OF_MONTH)<0	)
			meses--;
			}
			else if(meses==0){
					
			int dias =today.get(Calendar.DAY_OF_MONTH) - dateOfBirth.get(Calendar.DAY_OF_MONTH); 	
			
			return dias+" dia(s)";
			}
			
		
		return meses +" mes(es)";
		}
		else{
		
		if (today.before(dateOfBirth))
		age--;
		}
		
	return age+" ano(s)";
	}
		
		
	

	
	/******************* data ***********************/
	
	
	
	
	
	public static Date converteStringParaData(String String_data){
		
	return Comuns.converteStringParaData(String_data, "dd/MM/yyyy");	
	}
	
	
	
	
	
	
	public static Date converteStringParaData(String String_data, String padrao){
		
	if(String_data ==null || padrao==null || String_data.length()==0 || padrao.length()==0)
	return null;		
	
	Date data= null;
	
	try {data = new SimpleDateFormat(padrao).parse(String_data);}
		catch (ParseException e) {
		e.printStackTrace();
		return null;
		} 
	
	return data;
	}
	
	
}	