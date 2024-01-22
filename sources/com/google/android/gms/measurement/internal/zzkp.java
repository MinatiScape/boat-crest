package com.google.android.gms.measurement.internal;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import androidx.annotation.WorkerThread;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.internal.measurement.zzeq;
import com.google.android.gms.internal.measurement.zzex;
import com.google.android.gms.internal.measurement.zzfm;
import com.google.android.gms.internal.measurement.zzfn;
import com.google.android.gms.internal.measurement.zzfo;
import com.google.android.gms.internal.measurement.zzfr;
import com.google.android.gms.internal.measurement.zzfw;
import com.google.android.gms.internal.measurement.zzfx;
import com.google.android.gms.internal.measurement.zzfy;
import com.google.android.gms.internal.measurement.zzgd;
import com.google.android.gms.internal.measurement.zzgf;
import com.google.android.gms.internal.measurement.zzgg;
import com.google.android.gms.internal.measurement.zzgh;
import com.google.android.gms.internal.measurement.zzkj;
import com.google.android.gms.internal.measurement.zzlf;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;
import java.util.Map;
import java.util.zip.GZIPOutputStream;
import org.apache.commons.codec.digest.MessageDigestAlgorithms;
/* loaded from: classes10.dex */
public final class zzkp extends r3 {
    public zzkp(zzkn zzknVar) {
        super(zzknVar);
    }

    public static boolean C(List<Long> list, int i) {
        if (i < list.size() * 64) {
            return ((1 << (i % 64)) & list.get(i / 64).longValue()) != 0;
        }
        return false;
    }

    public static boolean E(String str) {
        return str != null && str.matches("([+-])?([0-9]+\\.?[0-9]*|[0-9]*\\.?[0-9]+)") && str.length() <= 310;
    }

    public static final void b(zzfn zzfnVar, String str, Object obj) {
        List<com.google.android.gms.internal.measurement.zzfs> zzp = zzfnVar.zzp();
        int i = 0;
        while (true) {
            if (i >= zzp.size()) {
                i = -1;
                break;
            } else if (str.equals(zzp.get(i).zzg())) {
                break;
            } else {
                i++;
            }
        }
        zzfr zze = com.google.android.gms.internal.measurement.zzfs.zze();
        zze.zzj(str);
        if (obj instanceof Long) {
            zze.zzi(((Long) obj).longValue());
        } else if (obj instanceof String) {
            zze.zzk((String) obj);
        } else if (obj instanceof Double) {
            zze.zzh(((Double) obj).doubleValue());
        } else if (obj instanceof Bundle[]) {
            zze.zzb(w((Bundle[]) obj));
        }
        if (i >= 0) {
            zzfnVar.zzj(i, zze);
        } else {
            zzfnVar.zze(zze);
        }
    }

    @WorkerThread
    public static final boolean c(zzat zzatVar, zzp zzpVar) {
        Preconditions.checkNotNull(zzatVar);
        Preconditions.checkNotNull(zzpVar);
        return (TextUtils.isEmpty(zzpVar.zzb) && TextUtils.isEmpty(zzpVar.zzq)) ? false : true;
    }

    public static final com.google.android.gms.internal.measurement.zzfs d(zzfo zzfoVar, String str) {
        for (com.google.android.gms.internal.measurement.zzfs zzfsVar : zzfoVar.zzi()) {
            if (zzfsVar.zzg().equals(str)) {
                return zzfsVar;
            }
        }
        return null;
    }

    public static final Object e(zzfo zzfoVar, String str) {
        com.google.android.gms.internal.measurement.zzfs d = d(zzfoVar, str);
        if (d != null) {
            if (d.zzy()) {
                return d.zzh();
            }
            if (d.zzw()) {
                return Long.valueOf(d.zzd());
            }
            if (d.zzu()) {
                return Double.valueOf(d.zza());
            }
            if (d.zzc() > 0) {
                List<com.google.android.gms.internal.measurement.zzfs> zzi = d.zzi();
                ArrayList arrayList = new ArrayList();
                for (com.google.android.gms.internal.measurement.zzfs zzfsVar : zzi) {
                    if (zzfsVar != null) {
                        Bundle bundle = new Bundle();
                        for (com.google.android.gms.internal.measurement.zzfs zzfsVar2 : zzfsVar.zzi()) {
                            if (zzfsVar2.zzy()) {
                                bundle.putString(zzfsVar2.zzg(), zzfsVar2.zzh());
                            } else if (zzfsVar2.zzw()) {
                                bundle.putLong(zzfsVar2.zzg(), zzfsVar2.zzd());
                            } else if (zzfsVar2.zzu()) {
                                bundle.putDouble(zzfsVar2.zzg(), zzfsVar2.zza());
                            }
                        }
                        if (!bundle.isEmpty()) {
                            arrayList.add(bundle);
                        }
                    }
                }
                return (Bundle[]) arrayList.toArray(new Bundle[arrayList.size()]);
            }
            return null;
        }
        return null;
    }

    public static final void h(StringBuilder sb, int i) {
        for (int i2 = 0; i2 < i; i2++) {
            sb.append("  ");
        }
    }

    public static final String i(boolean z, boolean z2, boolean z3) {
        StringBuilder sb = new StringBuilder();
        if (z) {
            sb.append("Dynamic ");
        }
        if (z2) {
            sb.append("Sequence ");
        }
        if (z3) {
            sb.append("Session-Scoped ");
        }
        return sb.toString();
    }

    public static final void j(StringBuilder sb, int i, String str, zzgd zzgdVar) {
        if (zzgdVar == null) {
            return;
        }
        h(sb, 3);
        sb.append(str);
        sb.append(" {\n");
        if (zzgdVar.zzb() != 0) {
            h(sb, 4);
            sb.append("results: ");
            int i2 = 0;
            for (Long l : zzgdVar.zzk()) {
                int i3 = i2 + 1;
                if (i2 != 0) {
                    sb.append(", ");
                }
                sb.append(l);
                i2 = i3;
            }
            sb.append('\n');
        }
        if (zzgdVar.zzd() != 0) {
            h(sb, 4);
            sb.append("status: ");
            int i4 = 0;
            for (Long l2 : zzgdVar.zzn()) {
                int i5 = i4 + 1;
                if (i4 != 0) {
                    sb.append(", ");
                }
                sb.append(l2);
                i4 = i5;
            }
            sb.append('\n');
        }
        if (zzgdVar.zza() != 0) {
            h(sb, 4);
            sb.append("dynamic_filter_timestamps: {");
            int i6 = 0;
            for (zzfm zzfmVar : zzgdVar.zzj()) {
                int i7 = i6 + 1;
                if (i6 != 0) {
                    sb.append(", ");
                }
                sb.append(zzfmVar.zzh() ? Integer.valueOf(zzfmVar.zza()) : null);
                sb.append(":");
                sb.append(zzfmVar.zzg() ? Long.valueOf(zzfmVar.zzb()) : null);
                i6 = i7;
            }
            sb.append("}\n");
        }
        if (zzgdVar.zzc() != 0) {
            h(sb, 4);
            sb.append("sequence_filter_timestamps: {");
            int i8 = 0;
            for (zzgf zzgfVar : zzgdVar.zzm()) {
                int i9 = i8 + 1;
                if (i8 != 0) {
                    sb.append(", ");
                }
                sb.append(zzgfVar.zzi() ? Integer.valueOf(zzgfVar.zzb()) : null);
                sb.append(": [");
                int i10 = 0;
                for (Long l3 : zzgfVar.zzf()) {
                    long longValue = l3.longValue();
                    int i11 = i10 + 1;
                    if (i10 != 0) {
                        sb.append(", ");
                    }
                    sb.append(longValue);
                    i10 = i11;
                }
                sb.append("]");
                i8 = i9;
            }
            sb.append("}\n");
        }
        h(sb, 3);
        sb.append("}\n");
    }

    public static final void k(StringBuilder sb, int i, String str, Object obj) {
        if (obj == null) {
            return;
        }
        h(sb, i + 1);
        sb.append(str);
        sb.append(": ");
        sb.append(obj);
        sb.append('\n');
    }

    public static final void l(StringBuilder sb, int i, String str, zzeq zzeqVar) {
        if (zzeqVar == null) {
            return;
        }
        h(sb, i);
        sb.append(str);
        sb.append(" {\n");
        if (zzeqVar.zzg()) {
            int zzm = zzeqVar.zzm();
            k(sb, i, "comparison_type", zzm != 1 ? zzm != 2 ? zzm != 3 ? zzm != 4 ? "BETWEEN" : "EQUAL" : "GREATER_THAN" : "LESS_THAN" : "UNKNOWN_COMPARISON_TYPE");
        }
        if (zzeqVar.zzi()) {
            k(sb, i, "match_as_float", Boolean.valueOf(zzeqVar.zzf()));
        }
        if (zzeqVar.zzh()) {
            k(sb, i, "comparison_value", zzeqVar.zzc());
        }
        if (zzeqVar.zzk()) {
            k(sb, i, "min_comparison_value", zzeqVar.zze());
        }
        if (zzeqVar.zzj()) {
            k(sb, i, "max_comparison_value", zzeqVar.zzd());
        }
        h(sb, i);
        sb.append("}\n");
    }

    public static int m(zzfx zzfxVar, String str) {
        for (int i = 0; i < zzfxVar.zzb(); i++) {
            if (str.equals(zzfxVar.zzak(i).zzf())) {
                return i;
            }
        }
        return -1;
    }

    public static Bundle o(Map<String, Object> map, boolean z) {
        Bundle bundle = new Bundle();
        for (String str : map.keySet()) {
            Object obj = map.get(str);
            if (obj == null) {
                bundle.putString(str, null);
            } else if (obj instanceof Long) {
                bundle.putLong(str, ((Long) obj).longValue());
            } else if (obj instanceof Double) {
                bundle.putDouble(str, ((Double) obj).doubleValue());
            } else if (!(obj instanceof ArrayList)) {
                bundle.putString(str, obj.toString());
            } else if (z) {
                ArrayList arrayList = (ArrayList) obj;
                ArrayList<? extends Parcelable> arrayList2 = new ArrayList<>();
                int size = arrayList.size();
                for (int i = 0; i < size; i++) {
                    arrayList2.add(o((Map) arrayList.get(i), false));
                }
                bundle.putParcelableArrayList(str, arrayList2);
            }
        }
        return bundle;
    }

    public static zzat q(com.google.android.gms.internal.measurement.zzaa zzaaVar) {
        Object obj;
        Bundle o = o(zzaaVar.zze(), true);
        String obj2 = (!o.containsKey("_o") || (obj = o.get("_o")) == null) ? "app" : obj.toString();
        String zzb = zzgp.zzb(zzaaVar.zzd());
        if (zzb == null) {
            zzb = zzaaVar.zzd();
        }
        return new zzat(zzb, new zzar(o), obj2, zzaaVar.zza());
    }

    public static <Builder extends zzlf> Builder s(Builder builder, byte[] bArr) throws zzkj {
        com.google.android.gms.internal.measurement.zzjl zzb = com.google.android.gms.internal.measurement.zzjl.zzb();
        if (zzb != null) {
            return (Builder) builder.zzaw(bArr, zzb);
        }
        return (Builder) builder.zzav(bArr);
    }

    public static List<com.google.android.gms.internal.measurement.zzfs> w(Bundle[] bundleArr) {
        ArrayList arrayList = new ArrayList();
        for (Bundle bundle : bundleArr) {
            if (bundle != null) {
                zzfr zze = com.google.android.gms.internal.measurement.zzfs.zze();
                for (String str : bundle.keySet()) {
                    zzfr zze2 = com.google.android.gms.internal.measurement.zzfs.zze();
                    zze2.zzj(str);
                    Object obj = bundle.get(str);
                    if (obj instanceof Long) {
                        zze2.zzi(((Long) obj).longValue());
                    } else if (obj instanceof String) {
                        zze2.zzk((String) obj);
                    } else if (obj instanceof Double) {
                        zze2.zzh(((Double) obj).doubleValue());
                    }
                    zze.zzc(zze2);
                }
                if (zze.zza() > 0) {
                    arrayList.add(zze.zzaA());
                }
            }
        }
        return arrayList;
    }

    public static List<Long> y(BitSet bitSet) {
        int length = (bitSet.length() + 63) / 64;
        ArrayList arrayList = new ArrayList(length);
        for (int i = 0; i < length; i++) {
            long j = 0;
            for (int i2 = 0; i2 < 64; i2++) {
                int i3 = (i * 64) + i2;
                if (i3 >= bitSet.length()) {
                    break;
                }
                if (bitSet.get(i3)) {
                    j |= 1 << i2;
                }
            }
            arrayList.add(Long.valueOf(j));
        }
        return arrayList;
    }

    /* JADX WARN: Code restructure failed: missing block: B:15:0x0032, code lost:
        r4 = new java.util.ArrayList();
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x003a, code lost:
        if ((r3 instanceof android.os.Parcelable[]) == false) goto L27;
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x003c, code lost:
        r3 = (android.os.Parcelable[]) r3;
        r5 = r3.length;
        r7 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x0040, code lost:
        if (r7 >= r5) goto L23;
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x0042, code lost:
        r8 = r3[r7];
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x0046, code lost:
        if ((r8 instanceof android.os.Bundle) == false) goto L22;
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x0048, code lost:
        r4.add(z((android.os.Bundle) r8, false));
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x0051, code lost:
        r7 = r7 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x0056, code lost:
        if ((r3 instanceof java.util.ArrayList) == false) goto L38;
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x0058, code lost:
        r3 = (java.util.ArrayList) r3;
        r5 = r3.size();
        r7 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x005f, code lost:
        if (r7 >= r5) goto L37;
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x0061, code lost:
        r8 = r3.get(r7);
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x0067, code lost:
        if ((r8 instanceof android.os.Bundle) == false) goto L36;
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x0069, code lost:
        r4.add(z((android.os.Bundle) r8, false));
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x0072, code lost:
        r7 = r7 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x0077, code lost:
        if ((r3 instanceof android.os.Bundle) == false) goto L24;
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x0079, code lost:
        r4.add(z((android.os.Bundle) r3, false));
     */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x0082, code lost:
        r0.put(r2, r4);
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.util.Map<java.lang.String, java.lang.Object> z(android.os.Bundle r10, boolean r11) {
        /*
            java.util.HashMap r0 = new java.util.HashMap
            r0.<init>()
            java.util.Set r1 = r10.keySet()
            java.util.Iterator r1 = r1.iterator()
        Ld:
            boolean r2 = r1.hasNext()
            if (r2 == 0) goto L86
            java.lang.Object r2 = r1.next()
            java.lang.String r2 = (java.lang.String) r2
            java.lang.Object r3 = r10.get(r2)
            boolean r4 = r3 instanceof android.os.Bundle[]
            if (r4 != 0) goto L30
            boolean r4 = r3 instanceof java.util.ArrayList
            if (r4 != 0) goto L30
            boolean r4 = r3 instanceof android.os.Bundle
            if (r4 == 0) goto L2a
            goto L30
        L2a:
            if (r3 == 0) goto Ld
            r0.put(r2, r3)
            goto Ld
        L30:
            if (r11 == 0) goto Ld
            java.util.ArrayList r4 = new java.util.ArrayList
            r4.<init>()
            boolean r5 = r3 instanceof android.os.Parcelable[]
            r6 = 0
            if (r5 == 0) goto L54
            android.os.Parcelable[] r3 = (android.os.Parcelable[]) r3
            int r5 = r3.length
            r7 = r6
        L40:
            if (r7 >= r5) goto L82
            r8 = r3[r7]
            boolean r9 = r8 instanceof android.os.Bundle
            if (r9 == 0) goto L51
            android.os.Bundle r8 = (android.os.Bundle) r8
            java.util.Map r8 = z(r8, r6)
            r4.add(r8)
        L51:
            int r7 = r7 + 1
            goto L40
        L54:
            boolean r5 = r3 instanceof java.util.ArrayList
            if (r5 == 0) goto L75
            java.util.ArrayList r3 = (java.util.ArrayList) r3
            int r5 = r3.size()
            r7 = r6
        L5f:
            if (r7 >= r5) goto L82
            java.lang.Object r8 = r3.get(r7)
            boolean r9 = r8 instanceof android.os.Bundle
            if (r9 == 0) goto L72
            android.os.Bundle r8 = (android.os.Bundle) r8
            java.util.Map r8 = z(r8, r6)
            r4.add(r8)
        L72:
            int r7 = r7 + 1
            goto L5f
        L75:
            boolean r5 = r3 instanceof android.os.Bundle
            if (r5 == 0) goto L82
            android.os.Bundle r3 = (android.os.Bundle) r3
            java.util.Map r3 = z(r3, r6)
            r4.add(r3)
        L82:
            r0.put(r2, r4)
            goto Ld
        L86:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzkp.z(android.os.Bundle, boolean):java.util.Map");
    }

    public final void A(zzfr zzfrVar, Object obj) {
        Preconditions.checkNotNull(obj);
        zzfrVar.zzg();
        zzfrVar.zze();
        zzfrVar.zzd();
        zzfrVar.zzf();
        if (obj instanceof String) {
            zzfrVar.zzk((String) obj);
        } else if (obj instanceof Long) {
            zzfrVar.zzi(((Long) obj).longValue());
        } else if (obj instanceof Double) {
            zzfrVar.zzh(((Double) obj).doubleValue());
        } else if (obj instanceof Bundle[]) {
            zzfrVar.zzb(w((Bundle[]) obj));
        } else {
            this.zzs.zzay().zzd().zzb("Ignoring invalid (type) event param value", obj);
        }
    }

    public final void B(zzgg zzggVar, Object obj) {
        Preconditions.checkNotNull(obj);
        zzggVar.zzc();
        zzggVar.zzb();
        zzggVar.zza();
        if (obj instanceof String) {
            zzggVar.zzh((String) obj);
        } else if (obj instanceof Long) {
            zzggVar.zze(((Long) obj).longValue());
        } else if (obj instanceof Double) {
            zzggVar.zzd(((Double) obj).doubleValue());
        } else {
            this.zzs.zzay().zzd().zzb("Ignoring invalid (type) user attribute value", obj);
        }
    }

    public final boolean D(long j, long j2) {
        return j == 0 || j2 <= 0 || Math.abs(this.zzs.zzav().currentTimeMillis() - j) > j2;
    }

    public final byte[] F(byte[] bArr) throws IOException {
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            GZIPOutputStream gZIPOutputStream = new GZIPOutputStream(byteArrayOutputStream);
            gZIPOutputStream.write(bArr);
            gZIPOutputStream.close();
            byteArrayOutputStream.close();
            return byteArrayOutputStream.toByteArray();
        } catch (IOException e) {
            this.zzs.zzay().zzd().zzb("Failed to gzip content", e);
            throw e;
        }
    }

    public final void f(StringBuilder sb, int i, List<com.google.android.gms.internal.measurement.zzfs> list) {
        if (list == null) {
            return;
        }
        int i2 = i + 1;
        for (com.google.android.gms.internal.measurement.zzfs zzfsVar : list) {
            if (zzfsVar != null) {
                h(sb, i2);
                sb.append("param {\n");
                k(sb, i2, AppMeasurementSdk.ConditionalUserProperty.NAME, zzfsVar.zzx() ? this.zzs.zzj().zzd(zzfsVar.zzg()) : null);
                k(sb, i2, "string_value", zzfsVar.zzy() ? zzfsVar.zzh() : null);
                k(sb, i2, "int_value", zzfsVar.zzw() ? Long.valueOf(zzfsVar.zzd()) : null);
                k(sb, i2, "double_value", zzfsVar.zzu() ? Double.valueOf(zzfsVar.zza()) : null);
                if (zzfsVar.zzc() > 0) {
                    f(sb, i2, zzfsVar.zzi());
                }
                h(sb, i2);
                sb.append("}\n");
            }
        }
    }

    public final void g(StringBuilder sb, int i, com.google.android.gms.internal.measurement.zzel zzelVar) {
        String str;
        if (zzelVar == null) {
            return;
        }
        h(sb, i);
        sb.append("filter {\n");
        if (zzelVar.zzh()) {
            k(sb, i, "complement", Boolean.valueOf(zzelVar.zzg()));
        }
        if (zzelVar.zzj()) {
            k(sb, i, "param_name", this.zzs.zzj().zzd(zzelVar.zze()));
        }
        if (zzelVar.zzk()) {
            int i2 = i + 1;
            zzex zzd = zzelVar.zzd();
            if (zzd != null) {
                h(sb, i2);
                sb.append("string_filter {\n");
                if (zzd.zzi()) {
                    switch (zzd.zzj()) {
                        case 1:
                            str = "UNKNOWN_MATCH_TYPE";
                            break;
                        case 2:
                            str = "REGEXP";
                            break;
                        case 3:
                            str = "BEGINS_WITH";
                            break;
                        case 4:
                            str = "ENDS_WITH";
                            break;
                        case 5:
                            str = "PARTIAL";
                            break;
                        case 6:
                            str = "EXACT";
                            break;
                        default:
                            str = "IN_LIST";
                            break;
                    }
                    k(sb, i2, "match_type", str);
                }
                if (zzd.zzh()) {
                    k(sb, i2, "expression", zzd.zzd());
                }
                if (zzd.zzg()) {
                    k(sb, i2, "case_sensitive", Boolean.valueOf(zzd.zzf()));
                }
                if (zzd.zza() > 0) {
                    h(sb, i2 + 1);
                    sb.append("expression_list {\n");
                    for (String str2 : zzd.zze()) {
                        h(sb, i2 + 2);
                        sb.append(str2);
                        sb.append("\n");
                    }
                    sb.append("}\n");
                }
                h(sb, i2);
                sb.append("}\n");
            }
        }
        if (zzelVar.zzi()) {
            l(sb, i + 1, "number_filter", zzelVar.zzc());
        }
        h(sb, i);
        sb.append("}\n");
    }

    @WorkerThread
    public final long n(byte[] bArr) {
        Preconditions.checkNotNull(bArr);
        this.zzs.zzv().zzg();
        MessageDigest d = zzku.d(MessageDigestAlgorithms.MD5);
        if (d == null) {
            this.zzs.zzay().zzd().zza("Failed to get MD5");
            return 0L;
        }
        return zzku.O(d.digest(bArr));
    }

    public final <T extends Parcelable> T p(byte[] bArr, Parcelable.Creator<T> creator) {
        if (bArr == null) {
            return null;
        }
        Parcel obtain = Parcel.obtain();
        try {
            obtain.unmarshall(bArr, 0, bArr.length);
            obtain.setDataPosition(0);
            return creator.createFromParcel(obtain);
        } catch (SafeParcelReader.ParseException unused) {
            this.zzs.zzay().zzd().zza("Failed to load parcelable from buffer");
            return null;
        } finally {
            obtain.recycle();
        }
    }

    public final zzfo r(zzao zzaoVar) {
        zzfn zze = zzfo.zze();
        zze.zzl(zzaoVar.e);
        h hVar = new h(zzaoVar.f);
        while (hVar.hasNext()) {
            String next = hVar.next();
            zzfr zze2 = com.google.android.gms.internal.measurement.zzfs.zze();
            zze2.zzj(next);
            Object d = zzaoVar.f.d(next);
            Preconditions.checkNotNull(d);
            A(zze2, d);
            zze.zze(zze2);
        }
        return zze.zzaA();
    }

    public final String t(zzfw zzfwVar) {
        if (zzfwVar == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("\nbatch {\n");
        for (zzfy zzfyVar : zzfwVar.zzd()) {
            if (zzfyVar != null) {
                h(sb, 1);
                sb.append("bundle {\n");
                if (zzfyVar.zzbh()) {
                    k(sb, 1, "protocol_version", Integer.valueOf(zzfyVar.zzd()));
                }
                k(sb, 1, "platform", zzfyVar.zzK());
                if (zzfyVar.zzbd()) {
                    k(sb, 1, "gmp_version", Long.valueOf(zzfyVar.zzn()));
                }
                if (zzfyVar.zzbn()) {
                    k(sb, 1, "uploading_gmp_version", Long.valueOf(zzfyVar.zzs()));
                }
                if (zzfyVar.zzbb()) {
                    k(sb, 1, "dynamite_version", Long.valueOf(zzfyVar.zzk()));
                }
                if (zzfyVar.zzaY()) {
                    k(sb, 1, "config_version", Long.valueOf(zzfyVar.zzi()));
                }
                k(sb, 1, "gmp_app_id", zzfyVar.zzH());
                k(sb, 1, "admob_app_id", zzfyVar.zzx());
                k(sb, 1, "app_id", zzfyVar.zzy());
                k(sb, 1, "app_version", zzfyVar.zzB());
                if (zzfyVar.zzaW()) {
                    k(sb, 1, "app_version_major", Integer.valueOf(zzfyVar.zza()));
                }
                k(sb, 1, "firebase_instance_id", zzfyVar.zzF());
                if (zzfyVar.zzba()) {
                    k(sb, 1, "dev_cert_hash", Long.valueOf(zzfyVar.zzj()));
                }
                k(sb, 1, "app_store", zzfyVar.zzA());
                if (zzfyVar.zzbm()) {
                    k(sb, 1, "upload_timestamp_millis", Long.valueOf(zzfyVar.zzr()));
                }
                if (zzfyVar.zzbk()) {
                    k(sb, 1, "start_timestamp_millis", Long.valueOf(zzfyVar.zzq()));
                }
                if (zzfyVar.zzbc()) {
                    k(sb, 1, "end_timestamp_millis", Long.valueOf(zzfyVar.zzm()));
                }
                if (zzfyVar.zzbg()) {
                    k(sb, 1, "previous_bundle_start_timestamp_millis", Long.valueOf(zzfyVar.zzp()));
                }
                if (zzfyVar.zzbf()) {
                    k(sb, 1, "previous_bundle_end_timestamp_millis", Long.valueOf(zzfyVar.zzo()));
                }
                k(sb, 1, "app_instance_id", zzfyVar.zzz());
                k(sb, 1, "resettable_device_id", zzfyVar.zzL());
                k(sb, 1, "ds_id", zzfyVar.zzE());
                if (zzfyVar.zzbe()) {
                    k(sb, 1, "limited_ad_tracking", Boolean.valueOf(zzfyVar.zzaT()));
                }
                k(sb, 1, "os_version", zzfyVar.zzJ());
                k(sb, 1, "device_model", zzfyVar.zzD());
                k(sb, 1, "user_default_language", zzfyVar.zzM());
                if (zzfyVar.zzbl()) {
                    k(sb, 1, "time_zone_offset_minutes", Integer.valueOf(zzfyVar.zzf()));
                }
                if (zzfyVar.zzaX()) {
                    k(sb, 1, "bundle_sequential_index", Integer.valueOf(zzfyVar.zzb()));
                }
                if (zzfyVar.zzbj()) {
                    k(sb, 1, "service_upload", Boolean.valueOf(zzfyVar.zzaU()));
                }
                k(sb, 1, "health_monitor", zzfyVar.zzI());
                if (!this.zzs.zzf().zzs(null, zzdw.zzan) && zzfyVar.zzaV() && zzfyVar.zzh() != 0) {
                    k(sb, 1, "android_id", Long.valueOf(zzfyVar.zzh()));
                }
                if (zzfyVar.zzbi()) {
                    k(sb, 1, "retry_counter", Integer.valueOf(zzfyVar.zze()));
                }
                if (zzfyVar.zzaZ()) {
                    k(sb, 1, "consent_signals", zzfyVar.zzC());
                }
                List<zzgh> zzP = zzfyVar.zzP();
                if (zzP != null) {
                    for (zzgh zzghVar : zzP) {
                        if (zzghVar != null) {
                            h(sb, 2);
                            sb.append("user_property {\n");
                            k(sb, 2, "set_timestamp_millis", zzghVar.zzs() ? Long.valueOf(zzghVar.zzc()) : null);
                            k(sb, 2, AppMeasurementSdk.ConditionalUserProperty.NAME, this.zzs.zzj().zze(zzghVar.zzf()));
                            k(sb, 2, "string_value", zzghVar.zzg());
                            k(sb, 2, "int_value", zzghVar.zzr() ? Long.valueOf(zzghVar.zzb()) : null);
                            k(sb, 2, "double_value", zzghVar.zzq() ? Double.valueOf(zzghVar.zza()) : null);
                            h(sb, 2);
                            sb.append("}\n");
                        }
                    }
                }
                List<com.google.android.gms.internal.measurement.zzfk> zzN = zzfyVar.zzN();
                if (zzN != null) {
                    for (com.google.android.gms.internal.measurement.zzfk zzfkVar : zzN) {
                        if (zzfkVar != null) {
                            h(sb, 2);
                            sb.append("audience_membership {\n");
                            if (zzfkVar.zzk()) {
                                k(sb, 2, "audience_id", Integer.valueOf(zzfkVar.zza()));
                            }
                            if (zzfkVar.zzm()) {
                                k(sb, 2, "new_audience", Boolean.valueOf(zzfkVar.zzj()));
                            }
                            j(sb, 2, "current_data", zzfkVar.zzd());
                            if (zzfkVar.zzn()) {
                                j(sb, 2, "previous_data", zzfkVar.zze());
                            }
                            h(sb, 2);
                            sb.append("}\n");
                        }
                    }
                }
                List<zzfo> zzO = zzfyVar.zzO();
                if (zzO != null) {
                    for (zzfo zzfoVar : zzO) {
                        if (zzfoVar != null) {
                            h(sb, 2);
                            sb.append("event {\n");
                            k(sb, 2, AppMeasurementSdk.ConditionalUserProperty.NAME, this.zzs.zzj().zzc(zzfoVar.zzh()));
                            if (zzfoVar.zzu()) {
                                k(sb, 2, "timestamp_millis", Long.valueOf(zzfoVar.zzd()));
                            }
                            if (zzfoVar.zzt()) {
                                k(sb, 2, "previous_timestamp_millis", Long.valueOf(zzfoVar.zzc()));
                            }
                            if (zzfoVar.zzs()) {
                                k(sb, 2, "count", Integer.valueOf(zzfoVar.zza()));
                            }
                            if (zzfoVar.zzb() != 0) {
                                f(sb, 2, zzfoVar.zzi());
                            }
                            h(sb, 2);
                            sb.append("}\n");
                        }
                    }
                }
                h(sb, 1);
                sb.append("}\n");
            }
        }
        sb.append("}\n");
        return sb.toString();
    }

    public final String u(com.google.android.gms.internal.measurement.zzej zzejVar) {
        if (zzejVar == null) {
            return "null";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("\nevent_filter {\n");
        if (zzejVar.zzp()) {
            k(sb, 0, "filter_id", Integer.valueOf(zzejVar.zzb()));
        }
        k(sb, 0, "event_name", this.zzs.zzj().zzc(zzejVar.zzg()));
        String i = i(zzejVar.zzk(), zzejVar.zzm(), zzejVar.zzn());
        if (!i.isEmpty()) {
            k(sb, 0, "filter_type", i);
        }
        if (zzejVar.zzo()) {
            l(sb, 1, "event_count_filter", zzejVar.zzf());
        }
        if (zzejVar.zza() > 0) {
            sb.append("  filters {\n");
            for (com.google.android.gms.internal.measurement.zzel zzelVar : zzejVar.zzh()) {
                g(sb, 2, zzelVar);
            }
        }
        h(sb, 1);
        sb.append("}\n}\n");
        return sb.toString();
    }

    public final String v(com.google.android.gms.internal.measurement.zzes zzesVar) {
        if (zzesVar == null) {
            return "null";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("\nproperty_filter {\n");
        if (zzesVar.zzj()) {
            k(sb, 0, "filter_id", Integer.valueOf(zzesVar.zza()));
        }
        k(sb, 0, "property_name", this.zzs.zzj().zze(zzesVar.zze()));
        String i = i(zzesVar.zzg(), zzesVar.zzh(), zzesVar.zzi());
        if (!i.isEmpty()) {
            k(sb, 0, "filter_type", i);
        }
        g(sb, 1, zzesVar.zzb());
        sb.append("}\n");
        return sb.toString();
    }

    public final List<Long> x(List<Long> list, List<Integer> list2) {
        int i;
        ArrayList arrayList = new ArrayList(list);
        for (Integer num : list2) {
            if (num.intValue() < 0) {
                this.zzs.zzay().zzk().zzb("Ignoring negative bit index to be cleared", num);
            } else {
                int intValue = num.intValue() / 64;
                if (intValue >= arrayList.size()) {
                    this.zzs.zzay().zzk().zzc("Ignoring bit index greater than bitSet size", num, Integer.valueOf(arrayList.size()));
                } else {
                    arrayList.set(intValue, Long.valueOf(((Long) arrayList.get(intValue)).longValue() & (~(1 << (num.intValue() % 64)))));
                }
            }
        }
        int size = arrayList.size();
        int size2 = arrayList.size() - 1;
        while (true) {
            int i2 = size2;
            i = size;
            size = i2;
            if (size < 0 || ((Long) arrayList.get(size)).longValue() != 0) {
                break;
            }
            size2 = size - 1;
        }
        return arrayList.subList(0, i);
    }

    @Override // com.google.android.gms.measurement.internal.r3
    public final boolean zzb() {
        return false;
    }
}
