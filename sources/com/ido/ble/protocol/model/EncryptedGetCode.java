package com.ido.ble.protocol.model;

import java.io.Serializable;
import java.util.Arrays;
/* loaded from: classes11.dex */
public class EncryptedGetCode implements Serializable {
    private static final long serialVersionUID = 1;
    public int auth_length;
    public int bind_ret_code;
    public int[] encrypted_data;
    public int encrypted_version;

    public String toString() {
        return "EncryptedGetCode{auth_length=" + this.auth_length + ", encrypted_version=" + this.encrypted_version + ", bind_ret_code=" + this.bind_ret_code + ", encrypted_data=" + Arrays.toString(this.encrypted_data) + '}';
    }
}
