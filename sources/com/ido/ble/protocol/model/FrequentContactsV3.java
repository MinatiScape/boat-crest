package com.ido.ble.protocol.model;

import java.io.Serializable;
/* loaded from: classes11.dex */
public class FrequentContactsV3 implements Serializable {
    private static final long serialVersionUID = 1;
    public String name;
    public String phone;

    public String toString() {
        return "FrequentContactsV3{name='" + this.name + "', phone='" + this.phone + "'}";
    }
}
