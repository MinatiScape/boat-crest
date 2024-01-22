package com.google.android.gms.tagmanager;

import androidx.annotation.RecentlyNonNull;
import com.clevertap.android.sdk.Constants;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.VisibleForTesting;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.ReentrantLock;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.checkerframework.dataflow.qual.SideEffectFree;
@VisibleForTesting
/* loaded from: classes10.dex */
public class DataLayer {
    @RecentlyNonNull
    public static final String EVENT_KEY = "event";
    @RecentlyNonNull
    public static final Object OBJECT_NOT_PRESENT = new Object();
    public static final String[] zza = "gtm.lifetime".split("\\.");
    public static final Pattern zzb = Pattern.compile("(\\d+)\\s*([smhd]?)");
    public final ConcurrentHashMap<zzav, Integer> zzc;
    public final Map<String, Object> zzd;
    public final ReentrantLock zze;
    public final LinkedList<Map<String, Object>> zzf;
    public final zzax zzg;
    public final CountDownLatch zzh;

    @VisibleForTesting
    public DataLayer() {
        this(new zzas());
    }

    @RecentlyNonNull
    @VisibleForTesting
    public static List<Object> listOf(@RecentlyNonNull Object... objArr) {
        ArrayList arrayList = new ArrayList();
        for (Object obj : objArr) {
            arrayList.add(obj);
        }
        return arrayList;
    }

    @RecentlyNonNull
    @VisibleForTesting
    public static Map<String, Object> mapOf(@RecentlyNonNull Object... objArr) {
        if ((objArr.length & 1) == 0) {
            HashMap hashMap = new HashMap();
            for (int i = 0; i < objArr.length; i += 2) {
                Object obj = objArr[i];
                if (obj instanceof String) {
                    hashMap.put((String) obj, objArr[i + 1]);
                } else {
                    String valueOf = String.valueOf(obj);
                    StringBuilder sb = new StringBuilder(valueOf.length() + 21);
                    sb.append("key is not a string: ");
                    sb.append(valueOf);
                    throw new IllegalArgumentException(sb.toString());
                }
            }
            return hashMap;
        }
        throw new IllegalArgumentException("expected even number of key-value pairs");
    }

    @RecentlyNonNull
    public Object get(@RecentlyNonNull String str) {
        String[] split;
        synchronized (this.zzd) {
            Object obj = this.zzd;
            for (String str2 : str.split("\\.")) {
                if (!(obj instanceof Map)) {
                    return null;
                }
                obj = ((Map) obj).get(str2);
                if (obj == null) {
                    return null;
                }
            }
            return obj;
        }
    }

    public void push(@RecentlyNonNull String str, @RecentlyNonNull Object obj) {
        push(zza(str, obj));
    }

    public void pushEvent(@RecentlyNonNull String str, @RecentlyNonNull Map<String, Object> map) {
        HashMap hashMap = new HashMap(map);
        hashMap.put("event", str);
        push(hashMap);
    }

    @RecentlyNonNull
    public String toString() {
        String sb;
        synchronized (this.zzd) {
            StringBuilder sb2 = new StringBuilder();
            for (Map.Entry<String, Object> entry : this.zzd.entrySet()) {
                sb2.append(String.format("{\n\tKey: %s\n\tValue: %s\n}\n", entry.getKey(), entry.getValue()));
            }
            sb = sb2.toString();
        }
        return sb;
    }

    public final Map<String, Object> zza(String str, Object obj) {
        HashMap hashMap = new HashMap();
        String[] split = str.toString().split("\\.");
        int i = 0;
        HashMap hashMap2 = hashMap;
        while (true) {
            int length = split.length - 1;
            if (i < length) {
                HashMap hashMap3 = new HashMap();
                hashMap2.put(split[i], hashMap3);
                i++;
                hashMap2 = hashMap3;
            } else {
                hashMap2.put(split[length], obj);
                return hashMap;
            }
        }
    }

    public final void zzd(String str) {
        push(str, null);
        this.zzg.zza(str);
    }

    @VisibleForTesting
    public final void zze(List<Object> list, List<Object> list2) {
        while (list2.size() < list.size()) {
            list2.add(null);
        }
        for (int i = 0; i < list.size(); i++) {
            Object obj = list.get(i);
            if (obj instanceof List) {
                if (!(list2.get(i) instanceof List)) {
                    list2.set(i, new ArrayList());
                }
                Object obj2 = list2.get(i);
                Preconditions.checkNotNull(obj2);
                zze((List) obj, (List) obj2);
            } else if (obj instanceof Map) {
                if (!(list2.get(i) instanceof Map)) {
                    list2.set(i, new HashMap());
                }
                Object obj3 = list2.get(i);
                Preconditions.checkNotNull(obj3);
                zzf((Map) obj, (Map) obj3);
            } else if (obj != OBJECT_NOT_PRESENT) {
                list2.set(i, obj);
            }
        }
    }

    @VisibleForTesting
    public final void zzf(Map<String, Object> map, Map<String, Object> map2) {
        for (String str : map.keySet()) {
            Object obj = map.get(str);
            if (obj instanceof List) {
                if (!(map2.get(str) instanceof List)) {
                    map2.put(str, new ArrayList());
                }
                Object obj2 = map2.get(str);
                Preconditions.checkNotNull(obj2);
                zze((List) obj, (List) obj2);
            } else if (obj instanceof Map) {
                if (!(map2.get(str) instanceof Map)) {
                    map2.put(str, new HashMap());
                }
                Object obj3 = map2.get(str);
                Preconditions.checkNotNull(obj3);
                zzf((Map) obj, (Map) obj3);
            } else {
                map2.put(str, obj);
            }
        }
    }

    public final void zzg(zzav zzavVar) {
        this.zzc.put(zzavVar, 0);
    }

    public final void zzh(Map<String, Object> map, String str, Collection<zzau> collection) {
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            String str2 = str.length() == 0 ? "" : ".";
            String key = entry.getKey();
            StringBuilder sb = new StringBuilder(str.length() + str2.length() + String.valueOf(key).length());
            sb.append(str);
            sb.append(str2);
            sb.append(key);
            String sb2 = sb.toString();
            if (entry.getValue() instanceof Map) {
                zzh((Map) entry.getValue(), sb2, collection);
            } else if (!sb2.equals("gtm.lifetime")) {
                collection.add(new zzau(sb2, entry.getValue()));
            }
        }
    }

    public final void zzi(Map<String, Object> map) {
        Long l;
        long j;
        this.zze.lock();
        try {
            this.zzf.offer(map);
            if (this.zze.getHoldCount() == 1) {
                int i = 0;
                while (true) {
                    Map<String, Object> poll = this.zzf.poll();
                    if (poll == null) {
                        break;
                    }
                    synchronized (this.zzd) {
                        for (String str : poll.keySet()) {
                            zzf(zza(str, poll.get(str)), this.zzd);
                        }
                    }
                    for (zzav zzavVar : this.zzc.keySet()) {
                        zzavVar.zza(poll);
                    }
                    i++;
                    if (i > 500) {
                        this.zzf.clear();
                        throw new RuntimeException("Seems like an infinite loop of pushing to the data layer");
                    }
                }
            }
            String[] strArr = zza;
            int length = strArr.length;
            Object obj = map;
            int i2 = 0;
            while (true) {
                l = null;
                if (i2 >= length) {
                    break;
                }
                String str2 = strArr[i2];
                if (!(obj instanceof Map)) {
                    obj = null;
                    break;
                } else {
                    obj = ((Map) obj).get(str2);
                    i2++;
                }
            }
            if (obj != null) {
                String obj2 = obj.toString();
                Matcher matcher = zzb.matcher(obj2);
                if (!matcher.matches()) {
                    String valueOf = String.valueOf(obj2);
                    zzdh.zzb.zzb(valueOf.length() != 0 ? "unknown _lifetime: ".concat(valueOf) : new String("unknown _lifetime: "));
                } else {
                    try {
                        String group = matcher.group(1);
                        Preconditions.checkNotNull(group);
                        j = Long.parseLong(group);
                    } catch (NumberFormatException unused) {
                        String valueOf2 = String.valueOf(obj2);
                        zzdh.zzc(valueOf2.length() != 0 ? "illegal number in _lifetime value: ".concat(valueOf2) : new String("illegal number in _lifetime value: "));
                        j = 0;
                    }
                    if (j <= 0) {
                        String valueOf3 = String.valueOf(obj2);
                        zzdh.zzb.zzb(valueOf3.length() != 0 ? "non-positive _lifetime: ".concat(valueOf3) : new String("non-positive _lifetime: "));
                    } else {
                        String group2 = matcher.group(2);
                        Preconditions.checkNotNull(group2);
                        if (group2.length() == 0) {
                            l = Long.valueOf(j);
                        } else {
                            char charAt = group2.charAt(0);
                            if (charAt == 'd') {
                                l = Long.valueOf(j * 86400000);
                            } else if (charAt == 'h') {
                                l = Long.valueOf(j * 3600000);
                            } else if (charAt == 'm') {
                                l = Long.valueOf(j * Constants.ONE_MIN_IN_MILLIS);
                            } else if (charAt != 's') {
                                String valueOf4 = String.valueOf(obj2);
                                zzdh.zzc(valueOf4.length() != 0 ? "unknown units in _lifetime: ".concat(valueOf4) : new String("unknown units in _lifetime: "));
                            } else {
                                l = Long.valueOf(j * 1000);
                            }
                        }
                    }
                }
            }
            if (l != null) {
                ArrayList arrayList = new ArrayList();
                zzh(map, "", arrayList);
                this.zzg.zzc(arrayList, l.longValue());
            }
        } finally {
            this.zze.unlock();
        }
    }

    public DataLayer(zzax zzaxVar) {
        this.zzg = zzaxVar;
        this.zzc = new ConcurrentHashMap<>();
        this.zzd = new HashMap();
        this.zze = new ReentrantLock();
        this.zzf = new LinkedList<>();
        this.zzh = new CountDownLatch(1);
        zzaxVar.zzb(new zzat(this));
    }

    @SideEffectFree
    public void push(@RecentlyNonNull Map<String, Object> map) {
        try {
            this.zzh.await();
        } catch (InterruptedException unused) {
            zzdh.zzc("DataLayer.push: unexpected InterruptedException");
        }
        zzi(map);
    }
}
