package componentes.anotacoes;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.lang.annotation.ElementType;
import java.lang.annotation.RetentionPolicy;


@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Anot_BD_Tabela {

	
String nome();		
String prefixo();		
String left_join() default "";	
	
}
