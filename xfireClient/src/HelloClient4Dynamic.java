package src;

import java.net.URL;
import java.util.List;

import org.codehaus.xfire.client.Client;
import org.codehaus.xfire.client.XFireProxyFactory;
import org.codehaus.xfire.service.Service;
import org.codehaus.xfire.service.binding.ObjectServiceFactory;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import com.xinxin.test.serverTest.entity.User;


public class HelloClient4Dynamic {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			/**
			 * 第一种方式，需要解析domcument，而且不能直接通过方法获取返回值
			 */
			//String wsURL = "http://localhost:8080/xfileService/services/MessageService?wsdl";

			/*System.out.println(" ----> 初始客户端 <---- ");
			Client client = new Client(new URL(wsURL));
			Object[] result1 = client.invoke("getName",new Object[] { "xinxin" });
			print(result1);
			Object[] result2 = client.invoke("getJson",new Object[]{});
			print(result2);*/
			/*Object[] result3 = client.invoke("getList", new Object[]{});
			Document d = (Document)result3[0];
            System.out.println(d.getFirstChild());*/
            //NodeList nl =d.getElementsByTagName("getTVchannelStringResult");
            //NodeList n2 = nl.item(0).getChildNodes();
           /* System.out.println(nl.getLength());
            for (int i=0;i<nl.getLength();i++){
                System.out.println(nl.item(i).getNodeName()+"::"+nl.item(i).getTextContent());
            }*/
			//print(result3);
			/*DocumentImpl dImpl = (DocumentImpl)result2[0];
			System.out.println(dImpl.getElementById("id"));*/
            
            /**
             * 第二种，推荐，需要客户端有服务端的接口对象，通过接口对象反射出来服务端那边的实现类
             */
            Service service=new ObjectServiceFactory().create(MessageService.class);
            MessageService usersService=(MessageService) new XFireProxyFactory().create(service, "http://localhost:8088/xfireService/services/MessageService");
            List<User> users=usersService.getList();
            System.out.println(usersService.getName("xinxin")+"----"+usersService.getJson()+"---"+usersService.getOne().toString());
            System.out.println(users.size());
            for(int i=0;i<users.size();i++){
            	System.out.println(users.get(i).toString());
            }
            
            
			System.out.println(" ----> 客户端调用结束 <---- ");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void print(Object[] obj){
		System.out.println("元素个数："+obj.length);
		for(int i=0;i<obj.length;i++){
			System.out.println("第"+(i+1)+"个元素："+obj[i]);
		}
	}
}