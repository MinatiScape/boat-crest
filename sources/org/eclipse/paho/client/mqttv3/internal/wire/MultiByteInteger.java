package org.eclipse.paho.client.mqttv3.internal.wire;
/* loaded from: classes13.dex */
public class MultiByteInteger {

    /* renamed from: a  reason: collision with root package name */
    public int f15461a;
    public int b;

    public MultiByteInteger(int i) {
        this(i, -1);
    }

    public int getEncodedLength() {
        return this.b;
    }

    public int getValue() {
        return this.f15461a;
    }

    public MultiByteInteger(int i, int i2) {
        this.f15461a = i;
        this.b = i2;
    }
}
