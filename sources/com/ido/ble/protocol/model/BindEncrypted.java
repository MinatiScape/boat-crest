package com.ido.ble.protocol.model;

import java.io.Serializable;
import java.util.Arrays;
/* loaded from: classes11.dex */
public class BindEncrypted implements Serializable {
    private static final long serialVersionUID = 1;
    public int auth_length;
    public int[] autu_data;
    public int encrypted_version;

    public String toString() {
        return "BindEncrypted{auth_length=" + this.auth_length + ", encrypted_version=" + this.encrypted_version + ", autu_data=" + Arrays.toString(this.autu_data) + '}';
    }
}
