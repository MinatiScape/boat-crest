package com.google.android.gms.internal.firebase_ml;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
/* loaded from: classes7.dex */
public final class zzjw {

    /* renamed from: a  reason: collision with root package name */
    public static final zzjz f8783a = new zzjy("-_.*", true);
    public static final zzjz b = new zzjy("-_.!~*'()@:$&,;=", false);
    public static final zzjz c = new zzjy("-_.!~*'()@:$&,;=+/?", false);
    public static final zzjz d = new zzjy("-_.!~*'():$&,;=", false);
    public static final zzjz e = new zzjy("-_.!~*'()@:$,;/?:", false);

    public static String zzaq(String str) {
        return f8783a.zzaw(str);
    }

    public static String zzar(String str) {
        try {
            return URLDecoder.decode(str, "UTF-8");
        } catch (UnsupportedEncodingException e2) {
            throw new RuntimeException(e2);
        }
    }

    public static String zzas(String str) {
        return b.zzaw(str);
    }

    public static String zzat(String str) {
        return c.zzaw(str);
    }

    public static String zzau(String str) {
        return d.zzaw(str);
    }

    public static String zzav(String str) {
        return e.zzaw(str);
    }
}
