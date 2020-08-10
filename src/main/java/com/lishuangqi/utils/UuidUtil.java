package com.lishuangqi.utils;

import java.util.UUID;

/**
 * Created by michael on 2018/8/29.
 */
public class UuidUtil {
    public UuidUtil() {
    }

    public static String generateUUID() {
        String uuid = UUID.randomUUID().toString();
        uuid = uuid.replace("-", "");
        return uuid;
    }
}
