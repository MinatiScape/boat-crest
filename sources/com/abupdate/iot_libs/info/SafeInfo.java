package com.abupdate.iot_libs.info;
/* loaded from: classes.dex */
public class SafeInfo {

    /* renamed from: a  reason: collision with root package name */
    public static SafeInfo f1899a;
    public String encKey;
    public int isEncrypt;

    public static SafeInfo getInstance() {
        if (f1899a == null) {
            synchronized (SafeInfo.class) {
                if (f1899a == null) {
                    f1899a = new SafeInfo();
                }
            }
        }
        return f1899a;
    }
}
