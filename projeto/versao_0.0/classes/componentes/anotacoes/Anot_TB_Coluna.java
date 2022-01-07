package componentes.anotacoes;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.lang.annotation.ElementType;
import java.lang.annotation.RetentionPolicy;



@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Anot_TB_Coluna {
	
int posicao();	
String rotulo();
int comprimento() default 0;
Class<?> tipo() default String.class;
}
