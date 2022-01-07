
package comuns;


import inicio.BarraDeInformacoes;

import java.sql.Connection;

import pagueOaluguel.CurrentRecord;
import voceNaoVaiPassar.beans.UsuarioAtual;






public class Configuracao {

	
	
public static  Connection connector = null;	
	
public static UsuarioAtual usuarioAtual;

public static CurrentRecord currentRecord;

public static boolean desligar;

public static String num_compilacao;


public static BarraDeInformacoes barraDeInformacoes;

}
