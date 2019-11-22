package com.keshihao.gmall.manage;

import org.csource.common.MyException;
import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GmallManageWebApplicationTests {



    @Test
    public void contextLoads() throws IOException, MyException {

        // 获得配置文件的路径,配置fdfs的全局链接地址
        String tracker = GmallManageWebApplicationTests.class.getResource("/tracker.conf").getPath();

        ClientGlobal.init(tracker);

        TrackerClient trackerClient = new TrackerClient();
        // 获得一个trackerServer的实例
        TrackerServer trackerServer = trackerClient.getConnection();
        // 通过trackerServer获得一个Storage连接客户端
        StorageClient storageClient = new StorageClient(trackerServer,null);

        String[] uploadInfos = storageClient.upload_file("C:\\Users\\keshi\\Desktop\\测试图片\\d459ecb9664c7a18.jpg", "jpg", null);
        for (String uploadInfo : uploadInfos) {
            System.out.println(uploadInfo);
        }
    }

}
