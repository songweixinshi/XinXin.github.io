package com.bettem;

import java.util.UUID;

import org.apache.commons.codec.binary.Base64;

public class TokenGenerateUtil {
	//用于外部调用生成Token字符串的静态方法  
	public static String getToken(){
		UUID uuid = UUID.randomUUID();
		String token = compressedUUID(uuid);
		if(TokenManager.DATA_MAP.containsKey(token)){//服务端维护token，用于和客户端传回比对
            uuid = UUID.randomUUID();  
            token = compressedUUID(uuid);  
        } 
		return token;
	}
	//对UUID进行处理，形成想要的Token长度
    private static String compressedUUID(UUID uuid) {  
        byte[] byUuid = new byte[SystemConfigConstants.TOKEN_BYTE_LEN];  
        long least = uuid.getLeastSignificantBits();  
        long most = uuid.getMostSignificantBits();  
        long2bytes(most, byUuid, 0);  
        long2bytes(least, byUuid, SystemConfigConstants.TOKEN_BYTE_LEN/2);  
        String compressUUID = Base64.encodeBase64URLSafeString(byUuid);  
        return compressUUID;
    }
    //长度处理  
    private static void long2bytes(long value, byte[] bytes, int offset) {
        for (int i = SystemConfigConstants.TOKEN_BYTE_LEN/2-1; i > -1; i--) {
            bytes[offset++] = (byte) ((value >> 8 * i) & 0xFF); 
        }  
    } 
	public static void main(String[] args) {
		String uuid = getToken();
		System.out.println(uuid);
	}
}
