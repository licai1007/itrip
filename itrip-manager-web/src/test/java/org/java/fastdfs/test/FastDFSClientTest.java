package org.java.fastdfs.test;


import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient;
import org.csource.fastdfs.StorageServer;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;
import org.java.common.util.FastDFSClient;
import org.junit.Test;

public class FastDFSClientTest {
	@Test
	public void testFastDFSUp() throws Exception{
		//导入fastDFS的jar包
		//1、加载配置文件，配置文件中的内容就是tracker服务的地址。
		ClientGlobal.init("F:\\itrip\\itripworkspace\\itrip-manager-web\\src\\main\\resources\\resource\\fdfs_client.properties");
		//2、创建一个TrackerClient对象。直接new一个。
		TrackerClient trackerClient = new TrackerClient();
		//3、使用TrackerClient对象创建连接，获得一个TrackerServer对象。
		TrackerServer trackerServer = trackerClient.getConnection();
		//4、创建一个StorageServer的引用，值为null
		StorageServer storageServer = null;
		//5、创建一个StorageClient对象，需要两个参数TrackerServer对象、StorageServer的引用
		StorageClient storageClient = new StorageClient(trackerServer, storageServer);
		//6、使用StorageClient对象上传图片。
		String[] file = storageClient.upload_file("C:\\Users\\Public\\Pictures\\Sample Pictures\\Koala.jpg","jpg",null);
		//7、返回数组。包含组名和图片的路径。
		for (String filepath : file) {
			System.out.println(filepath);
		}
		
	}
	@Test
	public void testFastDFSUp2() throws Exception{
		FastDFSClient client = new FastDFSClient("F:\\itrip\\itripworkspace\\itrip-manager-web\\src\\main\\resources\\resource\\fdfs_client.properties");
		String str = client.uploadFile("C:\\Users\\Public\\Pictures\\Sample Pictures\\Penguins.jpg");
		System.out.println(str);
	}
	
}
