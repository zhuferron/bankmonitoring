package com.engineering.common.lang;

import java.util.UUID;

public class RandomID {

    public static String getUUID() {
        UUID uuid = UUID.randomUUID();
        String str = uuid.toString();
        String uuidStr = str.replace("-", "");
        return uuidStr;
    }


}
