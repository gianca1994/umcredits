package com.gianca1994.umcredits.functions;

import org.mindrot.jbcrypt.BCrypt;

public class EncryptData {

    public static String encryptPassword(String value) {
        return BCrypt.hashpw(value, BCrypt.gensalt(12));
    }
}
