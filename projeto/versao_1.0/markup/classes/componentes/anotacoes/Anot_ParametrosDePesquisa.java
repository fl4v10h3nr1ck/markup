package classes.componentes.anotacoes;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Anot_ParametrosDePesquisa {

	
boolean pesquisaComoNumero() default false;	
boolean pesquisaComoString() default false;	
	
}
