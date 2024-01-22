package com.android.volley;

import android.text.TextUtils;
/* loaded from: classes.dex */
public final class Header {

    /* renamed from: a  reason: collision with root package name */
    public final String f2146a;
    public final String b;

    public Header(String str, String str2) {
        this.f2146a = str;
        this.b = str2;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || Header.class != obj.getClass()) {
            return false;
        }
        Header header = (Header) obj;
        return TextUtils.equals(this.f2146a, header.f2146a) && TextUtils.equals(this.b, header.b);
    }

    public final String getName() {
        return this.f2146a;
    }

    public final String getValue() {
        return this.b;
    }

    public int hashCode() {
        return (this.f2146a.hashCode() * 31) + this.b.hashCode();
    }

    public String toString() {
        return "Header[name=" + this.f2146a + ",value=" + this.b + "]";
    }
}
