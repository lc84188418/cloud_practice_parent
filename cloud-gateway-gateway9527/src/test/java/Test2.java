package java;

import java.time.ZonedDateTime;

/**
 * @author liuchun
 * @Package java
 * @date 2021/2/22 15:10
 * description:
 */
public class Test2 {

    public static void main(String[] args) {
        //默认时区
        ZonedDateTime zonedDateTime = ZonedDateTime.now();
        System.err.println(zonedDateTime);
    }
}
