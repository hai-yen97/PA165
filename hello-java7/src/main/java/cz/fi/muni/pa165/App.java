package cz.fi.muni.pa165;

import java.beans.JavaBean;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	List<String> messages = new ArrayList<>();
    	messages.add("Hello" );
    	messages.add("World" );
        System.out.println( messages);
    }
}

//          BEAN

@JavaBean
class B{

}

@JavaBean
class A{
    @Inject
    private B dependency;


    public A(cz.fi.muni.pa165.B dependency) {
        this.dependency = dependency;
    }
}
