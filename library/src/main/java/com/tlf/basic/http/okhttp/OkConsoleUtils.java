package com.tlf.basic.http.okhttp;

import java.util.Random;

/**
 * Created by tanlifei on 16/11/24.
 */

public class OkConsoleUtils {


    public static String randomRequest(String url) {
        try {
            if (null == OkHttpUtils.okHttpConsole) {
                return url;
            }
            if (OkHttpUtils.okHttpConsole.isOn_of_level() && OkHttpUtils.okHttpConsole.is_random()) {//开始
                int random = getRandom(0, OkHttpUtils.okHttpConsole.getRandom_max());
                if (random == 0) {
                    return insertStringInParticularPosition(url);
                } else {
                    return url;
                }
            }
            if(OkHttpUtils.okHttpConsole.isOn_of_level()){
                return "";
            }
            return url;
        } catch (Exception e) {
            e.printStackTrace();
            return url;
        }
    }

    /**
     * 在一串字符串中随便插入一个字符
     *
     * @param url
     * @return
     */
    public static String insertStringInParticularPosition(String url) {
        try {
            StringBuffer stringBuffer = new StringBuffer(url);
            int position = getRandom(0, url.length());
            return stringBuffer.insert(position, (char) (int) (Math.random() * 26 + 97)).toString();
        } catch (Exception e) {
            e.printStackTrace();
            return url;
        }
    }


    /**
     * 随机生成数
     *
     * @param min
     * @param max
     * @return
     */
    public static int getRandom(int min, int max) {
        if (min > max) {
            return 0;
        }
        if (min == max) {
            return min;
        }
        return min + new Random().nextInt(max - min);
    }

}
