package com.google.android.libraries.places.internal;

import com.goodix.ble.libcomx.util.HexStringBuilder;
import java.lang.Comparable;
import org.eclipse.paho.client.mqttv3.MqttTopic;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes10.dex */
public final class zzgh<C extends Comparable> extends zzgd<C> {
    public zzgh(C c) {
        super((Comparable) zzft.zza(c));
    }

    @Override // com.google.android.libraries.places.internal.zzgd
    public final int hashCode() {
        return this.zza.hashCode();
    }

    public final String toString() {
        String valueOf = String.valueOf(this.zza);
        StringBuilder sb = new StringBuilder(valueOf.length() + 2);
        sb.append("\\");
        sb.append(valueOf);
        sb.append(MqttTopic.TOPIC_LEVEL_SEPARATOR);
        return sb.toString();
    }

    @Override // com.google.android.libraries.places.internal.zzgd
    public final boolean zza(C c) {
        return zzgr.zzc(this.zza, c) <= 0;
    }

    @Override // com.google.android.libraries.places.internal.zzgd
    public final void zzb(StringBuilder sb) {
        sb.append(this.zza);
        sb.append(HexStringBuilder.COMMENT_END_CHAR);
    }

    @Override // com.google.android.libraries.places.internal.zzgd
    public final void zza(StringBuilder sb) {
        sb.append('[');
        sb.append(this.zza);
    }
}
