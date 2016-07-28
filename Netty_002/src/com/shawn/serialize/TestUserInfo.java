/**
 * Project Name:Netty_002
 * File Name:TestUserInfo.java
 * Package Name:com.shawn.serialize
 * Date:2016年7月28日上午11:17:05
 *
 */


package com.shawn.serialize;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;

/**
 * ClassName: TestUserInfo <br/>
 * Date: 2016年7月28日 上午11:17:05 <br/>
 * Description: TODO 
 *
 * @author luxf
 * @version 
 * @see
 */
public class TestUserInfo {
    public static void main(String[] args) throws IOException{
        UserInfo info = new UserInfo();
        info.buildUserID(100).buildUserName("Welcome to Netty");
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream os = new ObjectOutputStream(bos);
        os.writeObject(info);
        os.flush();
        os.close();
        byte[] b = bos.toByteArray();
        System.out.println("the jdk serializable length is : "+ b.length);
        bos.close();
        System.out.println("-------------------------------------");
        System.out.println("the byte array serializable length is :" + info.codeC().length);
    }
}

