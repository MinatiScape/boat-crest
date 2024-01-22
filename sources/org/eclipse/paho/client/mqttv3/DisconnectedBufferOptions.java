package org.eclipse.paho.client.mqttv3;
/* loaded from: classes13.dex */
public class DisconnectedBufferOptions {
    public static final boolean DELETE_OLDEST_MESSAGES_DEFAULT = false;
    public static final boolean DISCONNECTED_BUFFER_ENABLED_DEFAULT = false;
    public static final int DISCONNECTED_BUFFER_SIZE_DEFAULT = 5000;
    public static final boolean PERSIST_DISCONNECTED_BUFFER_DEFAULT = false;

    /* renamed from: a  reason: collision with root package name */
    public int f15435a = 5000;
    public boolean b = false;
    public boolean c = false;
    public boolean d = false;

    public int getBufferSize() {
        return this.f15435a;
    }

    public boolean isBufferEnabled() {
        return this.b;
    }

    public boolean isDeleteOldestMessages() {
        return this.d;
    }

    public boolean isPersistBuffer() {
        return this.c;
    }

    public void setBufferEnabled(boolean z) {
        this.b = z;
    }

    public void setBufferSize(int i) {
        if (i >= 1) {
            this.f15435a = i;
            return;
        }
        throw new IllegalArgumentException();
    }

    public void setDeleteOldestMessages(boolean z) {
        this.d = z;
    }

    public void setPersistBuffer(boolean z) {
        this.c = z;
    }
}
