package componentes.anotacoes;



import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;




@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Anot_BD_Campo{

String nome();
String prefixo() default "";
Class<?> tipo() default String.class;
String set();
String get();
String getBD() default "";
String getTab() default "";
boolean ehId() default false;
boolean select_apenas() default false;
}
