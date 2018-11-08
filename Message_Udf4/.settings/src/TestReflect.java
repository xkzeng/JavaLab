
import com.wrapper.Print;
import java.lang.Class;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class TestReflect
{
	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, InterruptedException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException
	{
//		Class c1 = Class.forName("TestMessageO");
//		
//		TestMessageO tm = (TestMessageO)c1.newInstance();
//		tm.sendMessage(1000);
		
		Class clazz = Class.forName("TestMessageO");
		Constructor c = clazz.getConstructor(int.class, int.class);
		TestMessageO tm = (TestMessageO)c.newInstance(123, 456);
		tm.sendMessage(1000);
		try
		{
			Thread.sleep(10000000000L);
		}
		catch(Exception e)
		{}
		
		Print.info("exit");
	}
}
