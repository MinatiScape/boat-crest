package com.ido.ble.protocol.model;

import java.io.Serializable;
/* loaded from: classes11.dex */
public class BindPara implements Serializable {
    private static final long serialVersionUID = 1;
    public int bind_version;
    public int is_clean_data;
    public int os_type;
    public int os_version;

    public String toString() {
        return "BindPara{os_type=" + this.os_type + ", os_version=" + this.os_version + ", is_clean_data=" + this.is_clean_data + ", bind_version=" + this.bind_version + '}';
    }
}
