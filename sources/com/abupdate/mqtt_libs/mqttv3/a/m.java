package com.abupdate.mqtt_libs.mqttv3.a;
/* loaded from: classes.dex */
public class m implements com.abupdate.mqtt_libs.mqttv3.j {

    /* renamed from: a  reason: collision with root package name */
    public byte[] f1957a;
    public int b;
    public int c;
    public byte[] d;
    public int e;
    public int f;

    public m(String str, byte[] bArr, int i, int i2, byte[] bArr2, int i3, int i4) {
        this.f1957a = null;
        this.b = 0;
        this.c = 0;
        this.d = null;
        this.e = 0;
        this.f = 0;
        this.f1957a = bArr;
        this.b = i;
        this.c = i2;
        this.d = bArr2;
        this.e = i3;
        this.f = i4;
    }

    @Override // com.abupdate.mqtt_libs.mqttv3.j
    public byte[] a() {
        return this.f1957a;
    }

    @Override // com.abupdate.mqtt_libs.mqttv3.j
    public int b() {
        return this.c;
    }

    @Override // com.abupdate.mqtt_libs.mqttv3.j
    public int b_() {
        if (this.d == null) {
            return 0;
        }
        return this.f;
    }

    @Override // com.abupdate.mqtt_libs.mqttv3.j
    public int c() {
        return this.b;
    }

    @Override // com.abupdate.mqtt_libs.mqttv3.j
    public byte[] d() {
        return this.d;
    }

    @Override // com.abupdate.mqtt_libs.mqttv3.j
    public int f() {
        return this.e;
    }
}
