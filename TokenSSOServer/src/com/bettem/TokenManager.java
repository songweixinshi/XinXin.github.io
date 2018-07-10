package com.bettem;

import java.util.Date;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentHashMap;

public class TokenManager {  
	  
    //计时器,间隔一定的时间执行一次,设置为守护线程设true  
    private static final Timer timer = new Timer(true);  
  
    //构造函数私有化防止静态类被实例化  使用这种方式保障全局唯一性  
    private TokenManager() {  
    }  
  
    //复合结构体，含登录的User和过期时间expried两个成员以及最后一次操作的时间
    @SuppressWarnings("unused")
    private static class Token {
		private String userName;//登录用户名  
        private Date expired; //过期时间  
        private Date lastOperate; // 最近一次操作的时间  
    }  
  
    //令牌存储结构 ConcurrentHashMap：支持同步的HashMap  
    public static final Map<String, Token> DATA_MAP = new ConcurrentHashMap<String, Token>();  
  
    /** 
     * 令牌有效性验证 
     */  
    public static boolean validate(String vt) {  
        System.out.println("验证Token是否有效.......");  
        boolean isValid = true;  
 
        System.out.println(DATA_MAP);
        if(DATA_MAP.containsKey(vt)){  
            Date expired = DATA_MAP.get(vt).expired;  
            Date now = new Date();  
            if(now.compareTo(expired) > 0){//已过期  
                isValid = false;  
                DATA_MAP.remove(vt);//移除  
            }  
        }else{  
            isValid = false;  
        }  
        return isValid;  
    }  
  
    /** 
     * 用户授权成功后存入授权信息 
     */  
    public static void addToken(String vt, String userName) {  
        Token token = new Token();  
  
        token.userName = userName;  
        token.lastOperate = new Date();  
        token.expired = new Date(token.lastOperate.getTime() + SystemConfigConstants.TOKENTIMEOUT * 60 * 1000);  
  
        DATA_MAP.put(vt, token);  
    }  
  
    /** 
     * 更新最近一次操作的时间 
     */  
    public static void updateLastOperate(String vt) {  
        Token token = DATA_MAP.get(vt);  
        token.lastOperate = new Date(new Date().getTime());//更新最近时间  
        token.expired = new Date(token.lastOperate.getTime() + SystemConfigConstants.TOKENTIMEOUT * 60 * 1000); //更新过期时间  
    }  
  
    /** 
     * 计时器实现 
     */  
    static {
        timer.schedule(new TimerTask() {
            @Override  
            public void run() {
                for (Entry<String, Token> entiy : DATA_MAP.entrySet()) {
                    String vt = entiy.getKey();  
                    Token token = entiy.getValue();  
  
                    Date expired = token.expired;  
                    Date now = new Date();  
                    //当前时间大于过期时间  
                    //当前时间大于过期时间  
                    //标识已经过期时间  
                    //将token移除  
                    if (now.compareTo(expired) > 0) {
                        //已过期，清除  
                        DATA_MAP.remove(vt);  
                    }
                }
            }
            //第一个为线程启动后间隔多久启动第一次执行，第二个是两次执行的间隔时间  
        }, 0 * 1000, 5 * 1000);  
    }  
  
    /** 
     * 在系统启动时启动管理工具 
     */  
    public static void init(){ }  
} 
