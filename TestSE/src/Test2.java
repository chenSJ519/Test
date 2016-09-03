import java.io.*;

import org.junit.Test;
public class Test2 {

	public Test2() {
		// TODO Auto-generated constructor stub
	}
	@Test
	public void t1()
	{
		try {
			FileOutputStream fos=new FileOutputStream("f:\\1.txt");
			BufferedWriter bur=new BufferedWriter(new OutputStreamWriter(fos));
			fos.write("陈圣驹".getBytes("unicode"));
			System.out.println(new String("陈圣驹".getBytes(),"iso-8859-1"));
			fos.close();
			
			System.out.println(new String("éå£é©¹".getBytes("iso-8859-1"),"utf-8"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	@Test
	public void t2()
	{
//		this.getClass().getClassLoader().getResource("1.txt");
//		System.out.println(this.getClass().getClassLoader().getResource("1.txt"));
		String s="第一百零七章奖励";
		System.out.println(s.matches(".*\u7B2C.{1,7}\u7AE0.*"));
	}
}
