package com.google.android.gms.internal.firebase_ml;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.eclipse.paho.client.mqttv3.MqttTopic;
/* loaded from: classes7.dex */
public class zzge {
    public static final Logger g = Logger.getLogger(zzge.class.getName());

    /* renamed from: a  reason: collision with root package name */
    public final zzhb f8760a;
    public final zzgj b;
    public final String c;
    public final String d;
    public final String e;
    public final zzjm f;

    /* loaded from: classes7.dex */
    public static abstract class zza {

        /* renamed from: a  reason: collision with root package name */
        public final zzhh f8761a;
        public zzgj b;
        public zzhe c;
        public final zzjm d;
        public String e;
        public String f;
        public String g;
        public String h;

        public zza(zzhh zzhhVar, String str, String str2, zzjm zzjmVar, zzhe zzheVar) {
            this.f8761a = (zzhh) zzml.checkNotNull(zzhhVar);
            this.d = zzjmVar;
            zzj(str);
            zzk(str2);
            this.c = zzheVar;
        }

        public zza zza(zzgj zzgjVar) {
            this.b = zzgjVar;
            return this;
        }

        public zza zzj(String str) {
            this.e = zzge.a(str);
            return this;
        }

        public zza zzk(String str) {
            this.f = zzge.b(str);
            return this;
        }

        public zza zzl(String str) {
            this.g = str;
            return this;
        }

        public zza zzm(String str) {
            this.h = str;
            return this;
        }
    }

    public zzge(zza zzaVar) {
        zzhb zza2;
        this.b = zzaVar.b;
        this.c = a(zzaVar.e);
        this.d = b(zzaVar.f);
        if (zzms.zzbc(zzaVar.h)) {
            g.logp(Level.WARNING, "com.google.api.client.googleapis.services.AbstractGoogleClient", "<init>", "Application name is not set. Call Builder#setApplicationName.");
        }
        this.e = zzaVar.h;
        zzhe zzheVar = zzaVar.c;
        if (zzheVar == null) {
            zza2 = zzaVar.f8761a.zza(null);
        } else {
            zza2 = zzaVar.f8761a.zza(zzheVar);
        }
        this.f8760a = zza2;
        this.f = zzaVar.d;
    }

    public static String a(String str) {
        zzml.checkNotNull(str, "root URL cannot be null.");
        return !str.endsWith(MqttTopic.TOPIC_LEVEL_SEPARATOR) ? str.concat(MqttTopic.TOPIC_LEVEL_SEPARATOR) : str;
    }

    public static String b(String str) {
        zzml.checkNotNull(str, "service path cannot be null");
        if (str.length() == 1) {
            zzml.checkArgument(MqttTopic.TOPIC_LEVEL_SEPARATOR.equals(str), "service path must equal \"/\" if it is of length 1.");
            return "";
        } else if (str.length() > 0) {
            if (!str.endsWith(MqttTopic.TOPIC_LEVEL_SEPARATOR)) {
                str = str.concat(MqttTopic.TOPIC_LEVEL_SEPARATOR);
            }
            return str.startsWith(MqttTopic.TOPIC_LEVEL_SEPARATOR) ? str.substring(1) : str;
        } else {
            return str;
        }
    }

    public void zza(zzgg<?> zzggVar) throws IOException {
        zzgj zzgjVar = this.b;
        if (zzgjVar != null) {
            zzgjVar.zza(zzggVar);
        }
    }

    public final String zzff() {
        String valueOf = String.valueOf(this.c);
        String valueOf2 = String.valueOf(this.d);
        return valueOf2.length() != 0 ? valueOf.concat(valueOf2) : new String(valueOf);
    }

    public final String zzfg() {
        return this.e;
    }

    public final zzhb zzfh() {
        return this.f8760a;
    }

    public zzjm zzfi() {
        return this.f;
    }
}
