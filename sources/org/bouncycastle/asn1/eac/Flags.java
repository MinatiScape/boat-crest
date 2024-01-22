package org.bouncycastle.asn1.eac;
/* loaded from: classes12.dex */
public class Flags {

    /* renamed from: a  reason: collision with root package name */
    public int f14413a;

    public Flags() {
        this.f14413a = 0;
    }

    public Flags(int i) {
        this.f14413a = 0;
        this.f14413a = i;
    }

    public int getFlags() {
        return this.f14413a;
    }

    public boolean isSet(int i) {
        return (i & this.f14413a) != 0;
    }

    public void set(int i) {
        this.f14413a = i | this.f14413a;
    }
}
