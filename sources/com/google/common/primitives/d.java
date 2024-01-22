package com.google.common.primitives;

import com.goodix.ble.libcomx.util.HexStringBuilder;
import com.google.common.annotations.GwtCompatible;
@GwtCompatible
/* loaded from: classes10.dex */
public final class d {

    /* renamed from: a  reason: collision with root package name */
    public final String f10724a;
    public final int b;

    public d(String str, int i) {
        this.f10724a = str;
        this.b = i;
    }

    public static d a(String str) {
        if (str.length() != 0) {
            char charAt = str.charAt(0);
            int i = 16;
            if (str.startsWith(HexStringBuilder.DEFAULT_PREFIX) || str.startsWith("0X")) {
                str = str.substring(2);
            } else if (charAt == '#') {
                str = str.substring(1);
            } else if (charAt != '0' || str.length() <= 1) {
                i = 10;
            } else {
                str = str.substring(1);
                i = 8;
            }
            return new d(str, i);
        }
        throw new NumberFormatException("empty string");
    }
}
