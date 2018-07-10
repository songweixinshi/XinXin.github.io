package com.bettem;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class SystemConfigConstants {
	//Token过期时间  
    public static final int TOKENTIMEOUT;  
    //用于生成的Token长度  
    public static final int TOKEN_BYTE_LEN;  
      
    /* 
     * 取值过程 
     */  
    static{  
        //配置文件读取  
        InputStream stream = null;  
        //读取工具  
        Properties properties = null;  
          
        try {  
            stream = SystemConfigConstants.class.getResourceAsStream("/config.properties");
            properties = new Properties();  
            properties.load(stream);  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
  
        TOKENTIMEOUT = Integer.parseInt(properties.getProperty("TOKENTIMEOUT"));  
        TOKEN_BYTE_LEN = Integer.parseInt(properties.getProperty("TOKEN_BYTE_LEN"));  
    } 
    
}
