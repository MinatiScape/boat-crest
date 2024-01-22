package com.google.android.gms.internal.clearcut;

import android.content.Context;
import android.util.Log;
import com.google.android.gms.clearcut.ClearcutLogger;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.common.wrappers.Wrappers;
import com.google.android.gms.internal.clearcut.zzgw;
import com.google.android.gms.phenotype.Phenotype;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import org.eclipse.paho.client.mqttv3.MqttTopic;
/* loaded from: classes7.dex */
public final class zzp implements ClearcutLogger.zza {
    public static final Charset b = Charset.forName("UTF-8");
    public static final zzao c;
    public static final zzao d;
    public static final ConcurrentHashMap<String, zzae<zzgw.zza>> e;
    public static final HashMap<String, zzae<String>> f;
    @VisibleForTesting
    public static Boolean g;
    @VisibleForTesting
    public static Long h;
    @VisibleForTesting
    public static final zzae<Boolean> i;

    /* renamed from: a  reason: collision with root package name */
    public final Context f8622a;

    static {
        zzao zzd = new zzao(Phenotype.getContentProviderUri("com.google.android.gms.clearcut.public")).zzc("gms:playlog:service:samplingrules_").zzd("LogSamplingRules__");
        c = zzd;
        d = new zzao(Phenotype.getContentProviderUri("com.google.android.gms.clearcut.public")).zzc("gms:playlog:service:sampling_").zzd("LogSampling__");
        e = new ConcurrentHashMap<>();
        f = new HashMap<>();
        g = null;
        h = null;
        i = zzd.zzc("enable_log_sampling_rules", false);
    }

    public zzp(Context context) {
        this.f8622a = context;
        if (context != null) {
            zzae.maybeInit(context);
        }
    }

    @VisibleForTesting
    public static long a(String str, long j) {
        if (str == null || str.isEmpty()) {
            return zzk.zza(ByteBuffer.allocate(8).putLong(j).array());
        }
        byte[] bytes = str.getBytes(b);
        ByteBuffer allocate = ByteBuffer.allocate(bytes.length + 8);
        allocate.put(bytes);
        allocate.putLong(j);
        return zzk.zza(allocate.array());
    }

    @VisibleForTesting
    public static zzgw.zza.zzb b(String str) {
        String str2;
        if (str == null) {
            return null;
        }
        int indexOf = str.indexOf(44);
        int i2 = 0;
        if (indexOf >= 0) {
            i2 = indexOf + 1;
            str2 = str.substring(0, indexOf);
        } else {
            str2 = "";
        }
        int indexOf2 = str.indexOf(47, i2);
        if (indexOf2 <= 0) {
            Log.e("LogSamplerImpl", str.length() != 0 ? "Failed to parse the rule: ".concat(str) : new String("Failed to parse the rule: "));
            return null;
        }
        try {
            long parseLong = Long.parseLong(str.substring(i2, indexOf2));
            long parseLong2 = Long.parseLong(str.substring(indexOf2 + 1));
            if (parseLong < 0 || parseLong2 < 0) {
                StringBuilder sb = new StringBuilder(72);
                sb.append("negative values not supported: ");
                sb.append(parseLong);
                sb.append(MqttTopic.TOPIC_LEVEL_SEPARATOR);
                sb.append(parseLong2);
                Log.e("LogSamplerImpl", sb.toString());
                return null;
            }
            return zzgw.zza.zzb.zzfz().zzn(str2).zzr(parseLong).zzs(parseLong2).zzbh();
        } catch (NumberFormatException e2) {
            Log.e("LogSamplerImpl", str.length() != 0 ? "parseLong() failed while parsing: ".concat(str) : new String("parseLong() failed while parsing: "), e2);
            return null;
        }
    }

    @VisibleForTesting
    public static boolean c(long j, long j2, long j3) {
        if (j2 < 0 || j3 <= 0) {
            return true;
        }
        return ((j > 0L ? 1 : (j == 0L ? 0 : -1)) >= 0 ? j % j3 : (((Long.MAX_VALUE % j3) + 1) + ((j & Long.MAX_VALUE) % j3)) % j3) < j2;
    }

    public static boolean d(Context context) {
        if (g == null) {
            g = Boolean.valueOf(Wrappers.packageManager(context).checkCallingOrSelfPermission("com.google.android.providers.gsf.permission.READ_GSERVICES") == 0);
        }
        return g.booleanValue();
    }

    @VisibleForTesting
    public static long e(Context context) {
        if (h == null) {
            if (context == null) {
                return 0L;
            }
            h = Long.valueOf(d(context) ? zzy.getLong(context.getContentResolver(), "android_id", 0L) : 0L);
        }
        return h.longValue();
    }

    @Override // com.google.android.gms.clearcut.ClearcutLogger.zza
    public final boolean zza(com.google.android.gms.clearcut.zze zzeVar) {
        List<zzgw.zza.zzb> zzfs;
        zzae<zzgw.zza> putIfAbsent;
        zzr zzrVar = zzeVar.zzag;
        String str = zzrVar.zzj;
        int i2 = zzrVar.zzk;
        zzha zzhaVar = zzeVar.zzaa;
        int i3 = zzhaVar != null ? zzhaVar.zzbji : 0;
        String str2 = null;
        if (!i.get().booleanValue()) {
            if (str == null || str.isEmpty()) {
                str = i2 >= 0 ? String.valueOf(i2) : null;
            }
            if (str != null) {
                Context context = this.f8622a;
                if (context != null && d(context)) {
                    HashMap<String, zzae<String>> hashMap = f;
                    zzae<String> zzaeVar = hashMap.get(str);
                    if (zzaeVar == null) {
                        zzaeVar = d.zza(str, null);
                        hashMap.put(str, zzaeVar);
                    }
                    str2 = zzaeVar.get();
                }
                zzgw.zza.zzb b2 = b(str2);
                if (b2 != null) {
                    return c(a(b2.zzfw(), e(this.f8622a)), b2.zzfx(), b2.zzfy());
                }
                return true;
            }
            return true;
        }
        if (str == null || str.isEmpty()) {
            str = i2 >= 0 ? String.valueOf(i2) : null;
        }
        if (str != null) {
            if (this.f8622a == null) {
                zzfs = Collections.emptyList();
            } else {
                ConcurrentHashMap<String, zzae<zzgw.zza>> concurrentHashMap = e;
                zzae<zzgw.zza> zzaeVar2 = concurrentHashMap.get(str);
                if (zzaeVar2 == null && (putIfAbsent = concurrentHashMap.putIfAbsent(str, (zzaeVar2 = c.zza(str, zzgw.zza.zzft(), w3.f8603a)))) != null) {
                    zzaeVar2 = putIfAbsent;
                }
                zzfs = zzaeVar2.get().zzfs();
            }
            for (zzgw.zza.zzb zzbVar : zzfs) {
                if (!zzbVar.zzfv() || zzbVar.getEventCode() == 0 || zzbVar.getEventCode() == i3) {
                    if (!c(a(zzbVar.zzfw(), e(this.f8622a)), zzbVar.zzfx(), zzbVar.zzfy())) {
                        return false;
                    }
                }
            }
            return true;
        }
        return true;
    }
}
