package com.google.android.gms.internal.firebase_ml;

import com.clevertap.android.sdk.Constants;
import kotlin.text.Typography;
import org.eclipse.paho.client.mqttv3.MqttTopic;
/* loaded from: classes7.dex */
public enum o0 {
    PLUS('+', "", Constants.SEPARATOR_COMMA, false, true),
    HASH('#', MqttTopic.MULTI_LEVEL_WILDCARD, Constants.SEPARATOR_COMMA, false, true),
    DOT('.', ".", ".", false, false),
    FORWARD_SLASH('/', MqttTopic.TOPIC_LEVEL_SEPARATOR, MqttTopic.TOPIC_LEVEL_SEPARATOR, false, false),
    SEMI_COLON(';', ";", ";", true, false),
    QUERY(Character.valueOf(org.apache.commons.codec.net.a.SEP), "?", "&", true, false),
    AMP(Character.valueOf(Typography.amp), "&", "&", true, false),
    SIMPLE(null, "", Constants.SEPARATOR_COMMA, false, false);
    
    private final Character zzadr;
    private final String zzads;
    private final String zzadt;
    private final boolean zzadu;
    private final boolean zzadv;

    o0(Character ch, String str, String str2, boolean z, boolean z2) {
        this.zzadr = ch;
        this.zzads = (String) zzml.checkNotNull(str);
        this.zzadt = (String) zzml.checkNotNull(str2);
        this.zzadu = z;
        this.zzadv = z2;
        if (ch != null) {
            zzhm.f8772a.put(ch, this);
        }
    }

    public final String zzak(String str) {
        if (this.zzadv) {
            return zzjw.zzas(str);
        }
        return zzjw.zzaq(str);
    }

    public final String zzgn() {
        return this.zzads;
    }

    public final String zzgo() {
        return this.zzadt;
    }

    public final boolean zzgp() {
        return this.zzadu;
    }

    public final int zzgq() {
        return this.zzadr == null ? 0 : 1;
    }

    public final boolean zzgr() {
        return this.zzadv;
    }
}
