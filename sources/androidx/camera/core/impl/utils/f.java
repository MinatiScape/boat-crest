package androidx.camera.core.impl.utils;

import androidx.annotation.NonNull;
import org.eclipse.paho.client.mqttv3.MqttTopic;
/* loaded from: classes.dex */
public final class f {

    /* renamed from: a  reason: collision with root package name */
    public final long f745a;
    public final long b;

    public f(long j, long j2) {
        this.f745a = j;
        this.b = j2;
    }

    public long a() {
        return this.b;
    }

    public long b() {
        return this.f745a;
    }

    public double c() {
        return this.f745a / this.b;
    }

    @NonNull
    public String toString() {
        return this.f745a + MqttTopic.TOPIC_LEVEL_SEPARATOR + this.b;
    }

    public f(double d) {
        this((long) (d * 10000.0d), 10000L);
    }
}
