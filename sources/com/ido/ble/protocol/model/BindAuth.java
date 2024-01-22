package com.ido.ble.protocol.model;

import java.io.Serializable;
import java.util.Arrays;
/* loaded from: classes11.dex */
public class BindAuth implements Serializable {
    private static final long serialVersionUID = 1;
    public int[] auth_code;
    public int auth_length;
    public int os_type = 1;
    public int os_version = 2;
    public int is_clean_data = 1;

    public String toString() {
        return "BindAuth{os_type=" + this.os_type + ", os_version=" + this.os_version + ", is_clean_data=" + this.is_clean_data + ", auth_length=" + this.auth_length + ", auth_code=" + Arrays.toString(this.auth_code) + '}';
    }
}
