package reflect;

import java.io.File ;
import java.net.URL ;
import org.junit.Test ;
import reflect.bean.Person ;

public class ReflectTest {
	
	@Test
	public void testNewObjectByReflect() throws Exception{
		Class<Person> clazz = Person.class;
		clazz.newInstance();
		
	}
	
	/**
	 * 获取一个包下面的所有类
	 * @throws Exception
	 */
	@Test
	public void getAllClassInPath() throws Exception{
		// 获取类加载器
		ClassLoader loader = this.getClass().getClassLoader();
		URL url = loader.getResource("socket/");
		System.out.println("url:" + url) ;
		File pathFile = new File(url.toURI());
		System.out.println("uri:" + url.toURI()) ;
		File [] fileList = pathFile.listFiles();
		for (File file : fileList) {
			if (file.isDirectory()) {
				continue;
			}
			String fileName = file.getName();
			String className = "socket." + fileName.substring(0, fileName.lastIndexOf("."));
			Class clazz = loader.loadClass(className);
			System.out.println(clazz.getName()) ;
		}
		
	}
	
	
}
