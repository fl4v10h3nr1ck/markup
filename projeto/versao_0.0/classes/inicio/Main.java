package inicio;

import java.util.ArrayList;
import java.util.List;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import pagueOaluguel.PagueOAluguel;
import pdv.FormPDVPrincipal;
import principal.ConfigDAO;
import voceNaoVaiPassar.VoceNaoVaiPassar;
import voceNaoVaiPassar.beans.ListaDeRecursosDoSistema;
import voceNaoVaiPassar.beans.Recurso;
import voceNaoVaiPassar.beans.UsuarioAtual;
import comuns.Comuns;
import comuns.Configuracao;




public class Main {

	
	
	
	public static void main(String[] args){
		
	
	try {UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");}
    catch (ClassNotFoundException e) {e.printStackTrace();}
    catch (InstantiationException e) {e.printStackTrace();}
    catch (IllegalAccessException e) {e.printStackTrace();}
    catch (UnsupportedLookAndFeelException e) {e.printStackTrace();}	
	
	
	Configuracao.connector = new ConfigDAO("bdmarkup").getConexao();
	
	if(Configuracao.connector==null)
	return;
	
	
	Configuracao.num_compilacao = "2012201500001";

	
	PagueOAluguel registro = new PagueOAluguel();
	
	PagueOAluguel.insertRegisterKeyEnabled = true;
	
	PagueOAluguel.SYSTEM_NAME = "sisMarkup";
	PagueOAluguel.SYSTEM_VERSION = "1.0.0";
	
	PagueOAluguel.serverURL = "http://www.mscsolucoes.com.br/util/licenseServer/";
	PagueOAluguel.accessKey2Server = "61fFndxo4s1Z0x00ad2c7gC9sAw5rmNH";

	PagueOAluguel.tipo_de_licenca = 1;
	
	Configuracao.currentRecord= registro.getCurrentRecord();

		//if(registro.licenseIsValid(Configuracao.currentRecord)){
		
	
			ListaDeRecursosDoSistema lista = new ListaDeRecursosDoSistema(){
		
				@Override
				public List<Recurso> getRecursos() {
					
				List<Recurso> lista = new ArrayList<Recurso>();	
						
				Recurso aux = new Recurso("Acesso à interface de gestão", Recurso.SIM_NAO, "INTERGEST");
				lista.add(aux);
					
				return lista;
				}	
			};
	
	
	
		VoceNaoVaiPassar voceNaoVaiPassar = new VoceNaoVaiPassar(Configuracao.connector, lista);
			
			if(voceNaoVaiPassar.prepara()){
		
			UsuarioAtual usuarioAtual =	null;
			boolean login  = false;	
				
			if(login)
			usuarioAtual= voceNaoVaiPassar.login();
				else{
				
				usuarioAtual = new 	UsuarioAtual();
				usuarioAtual.setId(1);
				usuarioAtual.setNome("admin");	
				}
			
				if(usuarioAtual !=null && usuarioAtual.getId()>0){
				
				Configuracao.usuarioAtual = usuarioAtual;	
				
				Comuns.setInfosPadroes();
				
					if(Configuracao.usuarioAtual.temPermissao(Configuracao.connector, "INTERGEST", "SIM")){
					
					FormHome form = new FormHome();
					form.mostrar();	
					}
					else{
	
						if(Comuns.validaParaAbrirCaixa()){	
						
						FormPDVPrincipal form = new FormPDVPrincipal();	
						form.mostrar();
						}
					}
						
				}
			}

		//}
	}	










}
