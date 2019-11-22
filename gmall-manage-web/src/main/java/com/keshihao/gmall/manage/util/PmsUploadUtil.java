package com.keshihao.gmall.manage.util;

import org.csource.common.MyException;
import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @author Mr.Ke
 * @create 2019-10-16-17:10
 */
public class PmsUploadUtil {


    public static String uploadImage(MultipartFile multipartFile) {
        String imgUrl ="http://192.168.74.128";

        // 上传图片到服务器
        // 获得配置文件的路径,配置fdfs的全局链接地址
        String tracker = PmsUploadUtil.class.getResource("/tracker.conf").getPath();

        try {
            ClientGlobal.init(tracker);
        } catch (Exception e) {
            e.printStackTrace();
        }

        TrackerClient trackerClient = new TrackerClient();
        // 获得一个trackerServer的实例
        TrackerServer trackerServer = null;
        try {
            trackerServer = trackerClient.getConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 通过trackerServer获得一个Storage连接客户端
        StorageClient storageClient = new StorageClient(trackerServer,null);

        try {
            byte[] bytes = multipartFile.getBytes();// 获得上传的二进制对象
            String originalFilename = multipartFile.getOriginalFilename();// 获得上传文件的全名
            int i = originalFilename.lastIndexOf(".");// 获取最后一个点的下标位置信息
            String extName = originalFilename.substring(i+1);// 获得文件后缀名
            String[] uploadInfos = storageClient.upload_file(bytes, extName, null);
            for (String uploadInfo : uploadInfos) {
                imgUrl += "/" + uploadInfo;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }


        return imgUrl;
    }
}
