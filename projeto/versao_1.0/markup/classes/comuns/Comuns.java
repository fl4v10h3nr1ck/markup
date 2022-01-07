
package classes.comuns;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.image.BufferedImage;
import java.lang.reflect.Field;
import java.util.List;
import java.util.regex.Pattern;

import javax.swing.JTextField;

import anotacoes.Anot_BD_Campo;
import anotacoes.Anot_BD_Tabela;
import classes.componentes.endereco.beans.Endereco;
import classes.componentes.endereco.beans.Municipio;
import classes.dao.DAO;
import classes.principal.config.Config_do_Sistema;
import classes.principal.empresa.Empresa;








public class Comuns {


	
/******************* comuns de formularios ***********************/

	
public static final String[] ufs = {"PA", "AC", "AL", "AP", "AM",  "BA", "CE",  "DF",  "ES",  "GO",  "MA",  "MT",  "MS",  "MG", "PB",  "PR",  "PE",  "RJ",  "RJ",  "RN",  "RS", "RO",  "RR",  "SC",  "SP",  "SE",  "TO"};
	


public static Empresa empresa;

public static Config_do_Sistema config_do_sistema;


/*
public static  String cidadePadrao;
public static  String paisPadrao;
public static Posto postoDeMovimentoPadrao; 
public static Resultado resultadoNumericoPadrao;

public static String descontoPadrao;
public static int prazoDeExamePadrao;
public static int numMaxDeItensPorPag;
*/



public static String num_compilacao;
public static String nome_sistema;
public static String versao_sistema;
public static String software_code;





public final static int TAM_DESCRICAO_ABREVIADA_PRODUTO = 25;





/******************* comuns de configuracao ***********************/


	public static void setInfos(){
	
	Comuns.setInfosDeConfiguracao();
	
	Comuns.setInfosDeEmpresa();
	
	Comuns.setInfosDePreferencias();
	}


	
	
	public static void setInfosDeConfiguracao(){

	Comuns.config_do_sistema= new DAO<Config_do_Sistema>(Config_do_Sistema.class).getPrimeiroOuNada(null, null, "config.id_configuracoes DESC");
		
		if(Comuns.config_do_sistema == null){
		
		Comuns.config_do_sistema = new Config_do_Sistema();
		Comuns.config_do_sistema.setPath_imgs_produtos("E:\\");
		}
	}





	public static void setInfosDeEmpresa(){
	
	Comuns.empresa= new DAO<Empresa>(Empresa.class).getPrimeiroOuNada(null, null, "emp.id_empresa DESC");
		
	if(empresa == null)
	empresa = new Empresa();
	}





	public static void setInfosDePreferencias(){
	
	Preferencias preferencias = new Preferencias();
	
	preferencias.cor_fundo_barra_info = new Color(20, 210, 150);	
	preferencias.cor_fonte_barra_info = Color.white;
	preferencias.cor_divisor_barra_info = Color.black;	
	
	preferencias.cor_area_inicial = Color.CYAN;
	preferencias.path_logo_area_inicial = "E:\\Google Drive\\projetos\\terminados\\DiaLab\\projeto\\versao_2.0\\diversos\\icons\\logo_principal2.png";
	
	preferencias.cor_fundo_barra_opcoes =Color.WHITE;	
	
	
	//preferencias.cor_fundo_janela = Color.gray;
	
	preferencias.cor_fundo_janela = new Color(236, 233, 216);
	
	
	preferencias.cor_fundo_painel_janela = new Color(236, 233, 216);
	preferencias.cor_fonte_janela = Color.black;		
	preferencias.fonte_janela = new Font("SansSerif", Font.PLAIN, 11);
	
	
		
	preferencias.tab_cor_fundo_cabecalho = new Color(201, 1, 42);
	
	preferencias.tab_cor_fonte_cabecalho = Color.white;
	preferencias.tab_cor_forte_fundo_linha = new Color(219, 229, 241);
	preferencias.tab_cor_fraca_fundo_linha = Color.white;	
	preferencias.tab_cor_fonte_linha = Color.BLACK;	
	preferencias.tab_cor_fundo_linha_selecione = new Color(255, 220, 152);	
	
	
	preferencias.outras_tab_cor_fundo_cabecalho = new Color(59, 89, 152);		
	preferencias.outras_tab_cor_fonte_cabecalho = Color.white;	
	preferencias.outras_tab_cor_forte_fundo_linha = new Color(219, 229, 241);
	preferencias.outras_tab_cor_fraca_fundo_linha = Color.white;
	preferencias.outras_tab_cor_fonte_linha = Color.BLACK;		
	preferencias.outras_tab_cor_fundo_linha_selecione = new Color(255, 220, 152);	
	
	preferencias.path_logo_relatorios = "E:\\Google Drive\\projetos\\terminados\\DiaLab\\projeto\\versao_2.0\\diversos\\icons\\logo_principal2.png";
	
	preferencias.path_assinatura_relatorios = "E:\\Google Drive\\projetos\\terminados\\DiaLab\\projeto\\versao_2.0\\diversos\\icons\\assinatura.jpg";
	
	preferencias.path_marcadagua_relatorios = "E:\\Google Drive\\projetos\\terminados\\DiaLab\\projeto\\versao_2.0\\diversos\\icons\\logo_principal_esmaecidoa.png";
	
	preferencias.cor_fundo_recibo  =new Color(206, 235, 243);
	
	
	
	/****************************** relatorios **************************************/
	
	preferencias.rel_cor_cabecalho_de_tabela = new Color(229, 229, 229);
	
	preferencias.rel_cor_linha_separadora_tabela = new Color(229, 229, 229);
	
	
	Configuracoes.preferencias  =preferencias;
	}



	
	
	
	
/******************* validation ***********************/

	

	public static boolean emailValidation(String email){

	if(email.length() == 0)
	return false;

	Pattern pattern = Pattern.compile(".+@.+\\.[a-z]+");  
	   
	if(pattern.matcher(email).matches())
	return true;
	else
	return false;
	}
	
	
	
	

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


	


	public static String getDescricaoEndereco(Endereco endereco){
		
	if(endereco ==null)
	return "";
		
	String retorno =  endereco.getLogradouro()
				+(endereco.getNum()!=null && endereco.getNum().length()>0? " "+endereco.getNum(): " S/N")
				+". "
				+(endereco.getBairro()!=null && endereco.getBairro().length()>0?endereco.getBairro():"")
				+(endereco.getCep()!=null &&endereco.getCep().length()>0?" CEP "+endereco.getCep():"")
				+" ";
	
		if(endereco.getFk_municipio()>0){
		
		Municipio municipio = new DAO<Municipio>(Municipio.class).get(endereco.getFk_municipio());
			
		if(municipio!=null)
		retorno +=  (municipio.getMunicipio()!=null && municipio.getMunicipio().length()>0?municipio.getMunicipio():"")
					+" - "
					+(municipio.getUf()!=null && municipio.getUf().length()>0?municipio.getUf():"");	
		
		}
			
	return retorno;
	}
		
	


	
	
	
	
	
	public static String addPaddingAEsquerda(String string, int comprimento_total, String string_add){
		
	if(string_add==null || string_add.length()==0)	
	return string;
	
	if(string==null)	
	string = "";
		
	for(int i  = string.length(); i < comprimento_total; i++)
	string = string_add+string;
			
	return string;	
	}

	
	
	
	
	

	
	
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
	
	
	
	
	
	
	
	

	public static int getComprimentoDeString(String valor, Font fonte){
		
	if(valor==null || valor.length()==0 || fonte==null)
	return 0;
	
	BufferedImage img = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
	FontMetrics fm = img.getGraphics().getFontMetrics(fonte);
	
	return fm.stringWidth(valor);
	}
	
	
	
	
}
