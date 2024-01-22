package org.eclipse.paho.client.mqttv3;

import java.util.Objects;
/* loaded from: classes13.dex */
public class MqttMessage {
    public byte[] i;
    public int m;
    public boolean h = true;
    public int j = 1;
    public boolean k = false;
    public boolean l = false;

    public MqttMessage() {
        setPayload(new byte[0]);
    }

    public static void validateQos(int i) {
        if (i < 0 || i > 2) {
            throw new IllegalArgumentException();
        }
    }

    public void checkMutable() throws IllegalStateException {
        if (!this.h) {
            throw new IllegalStateException();
        }
    }

    public void clearPayload() {
        checkMutable();
        this.i = new byte[0];
    }

    public int getId() {
        return this.m;
    }

    public byte[] getPayload() {
        return this.i;
    }

    public int getQos() {
        return this.j;
    }

    public boolean isDuplicate() {
        return this.l;
    }

    public boolean isRetained() {
        return this.k;
    }

    public void setDuplicate(boolean z) {
        this.l = z;
    }

    public void setId(int i) {
        this.m = i;
    }

    public void setMutable(boolean z) {
        this.h = z;
    }

    public void setPayload(byte[] bArr) {
        checkMutable();
        Objects.requireNonNull(bArr);
        this.i = (byte[]) bArr.clone();
    }

    public void setQos(int i) {
        checkMutable();
        validateQos(i);
        this.j = i;
    }

    public void setRetained(boolean z) {
        checkMutable();
        this.k = z;
    }

    public String toString() {
        return new String(this.i);
    }

    public MqttMessage(byte[] bArr) {
        setPayload(bArr);
    }
}
