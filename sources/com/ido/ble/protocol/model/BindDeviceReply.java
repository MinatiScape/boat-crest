package com.ido.ble.protocol.model;

import java.io.Serializable;
import java.util.Arrays;
/* loaded from: classes11.dex */
public class BindDeviceReply implements Serializable {
    public static final int BIND_AUTH_CODE_LOST = 2;
    public static final int FAIL = 1;
    public static final int SUCCESS = 0;
    private static final long serialVersionUID = 1;
    public int auth_length;
    public int bind_ret_code;
    public int[] encrypted_data;
    public int encrypted_version;

    public String toString() {
        return "BindDeviceReply{bind_ret_code=" + this.bind_ret_code + ", auth_length=" + this.auth_length + ", encrypted_version=" + this.encrypted_version + ", encrypted_data=" + Arrays.toString(this.encrypted_data) + '}';
    }
}
