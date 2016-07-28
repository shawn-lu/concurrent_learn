/**
 * Project Name:Netty_002
 * File Name:PerformTestUserInfo.java
 * Package Name:com.shawn.serialize
 * Date:2016年7月28日上午11:32:27
 *
 */


package com.shawn.serialize;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.ByteBuffer;

/**
 * ClassName: PerformTestUserInfo <br/>
 * Date: 2016年7月28日 上午11:32:27 <br/>
 * Description: TODO 
 *
 * @author luxf
 * @version 
 * @see
 */
public class PerformTestUserInfo {
    public static void main(String[] args) throws IOException {
        UserInfo info = new UserInfo();
        info.buildUserID(100).buildUserName("Welcome to Netty");
        int loop = 1000000;
        ByteArrayOutputStream bos = null;
        ObjectOutputStream os =  null;
        long startTime = System.currentTimeMillis();
        for(int i=0;i<loop;i++){
            bos = new ByteArrayOutputStream();
            os = new ObjectOutputStream(bos);
            os.writeObject(info);
           os.flush();
           os.close();
           byte[] b = bos.toByteArray();
           bos.close();
        }
        long endTime = System.currentTimeMillis();
        System.out.println("jdk serializable cost time is :" + (endTime - startTime) + " ms");
        System.out.println("-----------------------------------------");
        startTime = System.currentTimeMillis();
        for(int i=0;i<loop;i++){
            byte[] b = info.codeC();
        }
        endTime = System.currentTimeMillis();
        System.out.println("the byte array serializable cost time is :" + (endTime - startTime) + " ms");
    }
}

