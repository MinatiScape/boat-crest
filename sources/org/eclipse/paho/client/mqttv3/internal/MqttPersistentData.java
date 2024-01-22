package org.eclipse.paho.client.mqttv3.internal;

import org.eclipse.paho.client.mqttv3.MqttPersistable;
/* loaded from: classes13.dex */
public class MqttPersistentData implements MqttPersistable {

    /* renamed from: a  reason: collision with root package name */
    public String f15450a;
    public byte[] b;
    public int c;
    public int d;
    public byte[] e;
    public int f;
    public int g;

    public MqttPersistentData(String str, byte[] bArr, int i, int i2, byte[] bArr2, int i3, int i4) {
        this.f15450a = null;
        this.b = null;
        this.c = 0;
        this.d = 0;
        this.e = null;
        this.f = 0;
        this.g = 0;
        this.f15450a = str;
        this.b = (byte[]) bArr.clone();
        this.c = i;
        this.d = i2;
        this.e = bArr2 != null ? (byte[]) bArr2.clone() : null;
        this.f = i3;
        this.g = i4;
    }

    @Override // org.eclipse.paho.client.mqttv3.MqttPersistable
    public byte[] getHeaderBytes() {
        return this.b;
    }

    @Override // org.eclipse.paho.client.mqttv3.MqttPersistable
    public int getHeaderLength() {
        return this.d;
    }

    @Override // org.eclipse.paho.client.mqttv3.MqttPersistable
    public int getHeaderOffset() {
        return this.c;
    }

    public String getKey() {
        return this.f15450a;
    }

    @Override // org.eclipse.paho.client.mqttv3.MqttPersistable
    public byte[] getPayloadBytes() {
        return this.e;
    }

    @Override // org.eclipse.paho.client.mqttv3.MqttPersistable
    public int getPayloadLength() {
        if (this.e == null) {
            return 0;
        }
        return this.g;
    }

    @Override // org.eclipse.paho.client.mqttv3.MqttPersistable
    public int getPayloadOffset() {
        return this.f;
    }
}
