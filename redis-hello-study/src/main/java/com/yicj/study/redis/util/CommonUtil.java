package com.yicj.study.redis.util;

import sun.misc.BASE64Decoder;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class CommonUtil {

    public static String decode(String content)  {
        try {
            BASE64Decoder decoder = new BASE64Decoder();
            byte[] bytes = decoder.decodeBuffer(content);
            return new String(bytes, StandardCharsets.UTF_8) ;
        }catch (IOException e){
            throw new RuntimeException(e) ;
        }
    }

}
