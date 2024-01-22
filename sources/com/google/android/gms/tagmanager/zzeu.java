package com.google.android.gms.tagmanager;

import android.content.Context;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.internal.gtm.zzro;
import com.google.android.gms.internal.gtm.zzrs;
import com.google.android.gms.internal.gtm.zzrw;
import com.google.android.gms.internal.gtm.zzsa;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
@VisibleForTesting
/* loaded from: classes10.dex */
public final class zzeu {
    public static final zzds<com.google.android.gms.internal.gtm.zzak> zza = new zzds<>(zzfv.zzb(), true);
    public final zzrs zzb;
    public final Map<String, zzbu> zzc;
    public final Map<String, zzbu> zzd;
    public final Map<String, zzbu> zze;
    public final Set<zzrw> zzf;
    public final DataLayer zzg;
    public final Map<String, zzet> zzh;
    public volatile String zzi;
    public int zzj;
    public final zzdl zzk;
    public final zzdb zzl;
    public final zzdb zzm;

    public zzeu(Context context, zzrs zzrsVar, DataLayer dataLayer, zzaq zzaqVar, zzaq zzaqVar2, zzdl zzdlVar, byte[] bArr) {
        this.zzb = zzrsVar;
        HashSet<zzrw> hashSet = new HashSet(zzrsVar.zzc());
        this.zzf = hashSet;
        this.zzg = dataLayer;
        this.zzk = zzdlVar;
        zzen zzenVar = new zzen(this);
        new zzs();
        this.zzl = zzs.zza(1048576, zzenVar);
        zzeo zzeoVar = new zzeo(this);
        new zzs();
        this.zzm = zzs.zza(1048576, zzeoVar);
        HashMap hashMap = new HashMap();
        this.zzc = hashMap;
        zzj(hashMap, new zzo(context));
        zzj(hashMap, new zzar(zzaqVar2));
        zzj(hashMap, new zzbf(dataLayer));
        zzj(hashMap, new zzfw(context, dataLayer));
        HashMap hashMap2 = new HashMap();
        this.zzd = hashMap2;
        zzj(hashMap2, new zzao());
        zzj(hashMap2, new zzbr());
        zzj(hashMap2, new zzbs());
        zzj(hashMap2, new zzbw());
        zzj(hashMap2, new zzbx());
        zzj(hashMap2, new zzdd());
        zzj(hashMap2, new zzde());
        zzj(hashMap2, new zzee());
        zzj(hashMap2, new zzfk());
        HashMap hashMap3 = new HashMap();
        this.zze = hashMap3;
        zzj(hashMap3, new zze(zzd.zzb(context)));
        zzj(hashMap3, new zzf(zzd.zzb(context)));
        zzj(hashMap3, new zzh(context));
        zzj(hashMap3, new zzi(context));
        zzj(hashMap3, new zzj(context));
        zzj(hashMap3, new zzk(context));
        zzj(hashMap3, new zzl(context));
        zzj(hashMap3, new zzt());
        zzj(hashMap3, new zzan(zzrsVar.zzb()));
        zzj(hashMap3, new zzar(zzaqVar));
        zzj(hashMap3, new zzay(dataLayer));
        zzj(hashMap3, new zzbi(context));
        zzj(hashMap3, new zzbj());
        zzj(hashMap3, new zzbq());
        zzj(hashMap3, new zzbt(this));
        zzj(hashMap3, new zzby());
        zzj(hashMap3, new zzbz());
        zzj(hashMap3, new zzcw(context));
        zzj(hashMap3, new zzcy());
        zzj(hashMap3, new zzdc());
        zzj(hashMap3, new zzdi());
        zzj(hashMap3, new zzdj(context));
        zzj(hashMap3, new zzdt());
        zzj(hashMap3, new zzdx());
        zzj(hashMap3, new zzeb());
        zzj(hashMap3, new zzed());
        zzj(hashMap3, new zzef(context));
        zzj(hashMap3, new zzev());
        zzj(hashMap3, new zzew());
        zzj(hashMap3, new zzfq());
        zzj(hashMap3, new zzfx());
        this.zzh = new HashMap();
        for (zzrw zzrwVar : hashSet) {
            for (int i = 0; i < zzrwVar.zza().size(); i++) {
                zzro zzroVar = zzrwVar.zza().get(i);
                zzet zzg = zzg(this.zzh, zzh(zzroVar));
                zzg.zzk(zzrwVar);
                zzg.zzg(zzrwVar, zzroVar);
                zzg.zzh(zzrwVar, "Unknown");
            }
            for (int i2 = 0; i2 < zzrwVar.zzf().size(); i2++) {
                zzro zzroVar2 = zzrwVar.zzf().get(i2);
                zzet zzg2 = zzg(this.zzh, zzh(zzroVar2));
                zzg2.zzk(zzrwVar);
                zzg2.zzi(zzrwVar, zzroVar2);
                zzg2.zzj(zzrwVar, "Unknown");
            }
        }
        for (Map.Entry<String, List<zzro>> entry : this.zzb.zzd().entrySet()) {
            for (zzro zzroVar3 : entry.getValue()) {
                if (!zzfv.zzg(zzfv.zzl(zzroVar3.zzc().get(com.google.android.gms.internal.gtm.zzb.NOT_DEFAULT_MACRO.toString()))).booleanValue()) {
                    zzg(this.zzh, entry.getKey()).zzl(zzroVar3);
                }
            }
        }
    }

    public static zzet zzg(Map<String, zzet> map, String str) {
        zzet zzetVar = map.get(str);
        if (zzetVar == null) {
            zzet zzetVar2 = new zzet();
            map.put(str, zzetVar2);
            return zzetVar2;
        }
        return zzetVar;
    }

    public static String zzh(zzro zzroVar) {
        return zzfv.zzn(zzfv.zzl(zzroVar.zzc().get(com.google.android.gms.internal.gtm.zzb.INSTANCE_NAME.toString())));
    }

    public static void zzj(Map<String, zzbu> map, zzbu zzbuVar) {
        if (map.containsKey(zzbuVar.zze())) {
            String valueOf = String.valueOf(zzbuVar.zze());
            throw new IllegalArgumentException(valueOf.length() != 0 ? "Duplicate function type name: ".concat(valueOf) : new String("Duplicate function type name: "));
        } else {
            map.put(zzbuVar.zze(), zzbuVar);
        }
    }

    public final zzds<com.google.android.gms.internal.gtm.zzak> zza(String str) {
        this.zzj = 0;
        return zzm(str, new HashSet(), new zzdm());
    }

    public final synchronized String zzb() {
        return this.zzi;
    }

    public final synchronized void zzc(String str) {
        zzd(str);
        for (zzro zzroVar : zzl(this.zzf, new HashSet(), new zzeq(this), new zzdp()).zza()) {
            zzn(this.zzc, zzroVar, new HashSet(), new zzdn());
        }
        zzd(null);
    }

    @VisibleForTesting
    public final synchronized void zzd(String str) {
        this.zzi = str;
    }

    public final synchronized void zze(List<com.google.android.gms.internal.gtm.zzag> list) {
        for (com.google.android.gms.internal.gtm.zzag zzagVar : list) {
            if (zzagVar.zzf() && zzagVar.zzd().startsWith("gaExperiment:")) {
                DataLayer dataLayer = this.zzg;
                if (!zzagVar.zze()) {
                    zzdh.zzc("supplemental missing experimentSupplemental");
                } else {
                    for (com.google.android.gms.internal.gtm.zzak zzakVar : zzagVar.zza().zze()) {
                        dataLayer.zzd(zzfv.zzn(zzfv.zzl(zzakVar)));
                    }
                    Iterator<com.google.android.gms.internal.gtm.zzak> it = zzagVar.zza().zzf().iterator();
                    while (true) {
                        Map<String, Object> map = null;
                        if (!it.hasNext()) {
                            break;
                        }
                        Object zzl = zzfv.zzl(it.next());
                        if (!(zzl instanceof Map)) {
                            String valueOf = String.valueOf(zzl);
                            StringBuilder sb = new StringBuilder(valueOf.length() + 36);
                            sb.append("value: ");
                            sb.append(valueOf);
                            sb.append(" is not a map value, ignored.");
                            zzdh.zzc(sb.toString());
                        } else {
                            map = (Map) zzl;
                        }
                        if (map != null) {
                            dataLayer.push(map);
                        }
                    }
                    for (com.google.android.gms.internal.gtm.zzu zzuVar : zzagVar.zza().zzd()) {
                        if (!zzuVar.zzh()) {
                            zzdh.zzc("GaExperimentRandom: No key");
                        } else {
                            Object obj = dataLayer.get(zzuVar.zzf());
                            Long valueOf2 = !(obj instanceof Number) ? null : Long.valueOf(((Number) obj).longValue());
                            long zzd = zzuVar.zzd();
                            long zzc = zzuVar.zzc();
                            if (!zzuVar.zzg() || valueOf2 == null || valueOf2.longValue() < zzd || valueOf2.longValue() > zzc) {
                                if (zzd <= zzc) {
                                    obj = Long.valueOf(Math.round((Math.random() * (zzc - zzd)) + zzd));
                                } else {
                                    zzdh.zzc("GaExperimentRandom: random range invalid");
                                }
                            }
                            dataLayer.zzd(zzuVar.zzf());
                            Map<String, Object> zza2 = dataLayer.zza(zzuVar.zzf(), obj);
                            if (zzuVar.zza() > 0) {
                                if (zza2.containsKey("gtm")) {
                                    Object obj2 = zza2.get("gtm");
                                    if (obj2 instanceof Map) {
                                        ((Map) obj2).put("lifetime", Long.valueOf(zzuVar.zza()));
                                    } else {
                                        zzdh.zzc("GaExperimentRandom: gtm not a map");
                                    }
                                } else {
                                    zza2.put("gtm", DataLayer.mapOf("lifetime", Long.valueOf(zzuVar.zza())));
                                }
                            }
                            dataLayer.push(zza2);
                        }
                    }
                }
            }
            String valueOf3 = String.valueOf(zzagVar);
            StringBuilder sb2 = new StringBuilder(valueOf3.length() + 22);
            sb2.append("Ignored supplemental: ");
            sb2.append(valueOf3);
            zzdh.zzb.zzd(sb2.toString());
        }
    }

    @VisibleForTesting
    public final zzds<Boolean> zzf(zzro zzroVar, Set<String> set, zzdn zzdnVar) {
        zzds<com.google.android.gms.internal.gtm.zzak> zzn = zzn(this.zzd, zzroVar, set, zzdnVar);
        Boolean zzg = zzfv.zzg(zzfv.zzl(zzn.zza()));
        zzfv.zzc(zzg);
        return new zzds<>(zzg, zzn.zzb());
    }

    public final String zzi() {
        if (this.zzj <= 1) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        sb.append(Integer.toString(this.zzj));
        for (int i = 2; i < this.zzj; i++) {
            sb.append(' ');
        }
        sb.append(": ");
        return sb.toString();
    }

    public final void zzk(com.google.android.gms.internal.gtm.zzak zzakVar, Set<String> set) {
        zzds<com.google.android.gms.internal.gtm.zzak> zzo;
        if (zzakVar == null || (zzo = zzo(zzakVar, set, new zzdq())) == zza) {
            return;
        }
        Object zzl = zzfv.zzl(zzo.zza());
        if (zzl instanceof Map) {
            this.zzg.push((Map) zzl);
        } else if (zzl instanceof List) {
            for (Object obj : (List) zzl) {
                if (obj instanceof Map) {
                    this.zzg.push((Map) obj);
                } else {
                    zzdh.zzc("pushAfterEvaluate: value not a Map");
                }
            }
        } else {
            zzdh.zzc("pushAfterEvaluate: value not a Map or List");
        }
    }

    public final zzds<Set<zzro>> zzl(Set<zzrw> set, Set<String> set2, zzer zzerVar, zzdp zzdpVar) {
        boolean z;
        zzds zzdsVar;
        Set<zzro> hashSet = new HashSet<>();
        Set<zzro> hashSet2 = new HashSet<>();
        while (true) {
            for (zzrw zzrwVar : set) {
                zzdo zzdoVar = new zzdo();
                Iterator<zzro> it = zzrwVar.zzd().iterator();
                while (true) {
                    boolean z2 = true;
                    while (true) {
                        if (it.hasNext()) {
                            zzds<Boolean> zzf = zzf(it.next(), set2, new zzdn());
                            if (zzf.zza().booleanValue()) {
                                Boolean bool = Boolean.FALSE;
                                zzfv.zzc(bool);
                                zzdsVar = new zzds(bool, zzf.zzb());
                                break;
                            } else if (!z2 || !zzf.zzb()) {
                                z2 = false;
                            }
                        } else {
                            Iterator<zzro> it2 = zzrwVar.zze().iterator();
                            while (true) {
                                if (it2.hasNext()) {
                                    zzds<Boolean> zzf2 = zzf(it2.next(), set2, new zzdn());
                                    if (!zzf2.zza().booleanValue()) {
                                        Boolean bool2 = Boolean.FALSE;
                                        zzfv.zzc(bool2);
                                        zzdsVar = new zzds(bool2, zzf2.zzb());
                                        break;
                                    }
                                    z2 = z2 && zzf2.zzb();
                                } else {
                                    Boolean bool3 = Boolean.TRUE;
                                    zzfv.zzc(bool3);
                                    zzdsVar = new zzds(bool3, z2);
                                    break;
                                }
                            }
                        }
                    }
                }
                if (((Boolean) zzdsVar.zza()).booleanValue()) {
                    zzerVar.zza(zzrwVar, hashSet, hashSet2, zzdoVar);
                }
                z = z && zzdsVar.zzb();
            }
            hashSet.removeAll(hashSet2);
            return new zzds<>(hashSet, z);
        }
    }

    public final zzds<com.google.android.gms.internal.gtm.zzak> zzm(String str, Set<String> set, zzdm zzdmVar) {
        zzro next;
        boolean z = true;
        this.zzj++;
        zzes zzesVar = (zzes) this.zzm.zza(str);
        if (zzesVar == null) {
            zzet zzetVar = this.zzh.get(str);
            if (zzetVar == null) {
                String zzi = zzi();
                StringBuilder sb = new StringBuilder(String.valueOf(zzi).length() + 15 + String.valueOf(str).length());
                sb.append(zzi);
                sb.append("Invalid macro: ");
                sb.append(str);
                zzdh.zza(sb.toString());
                this.zzj--;
                return zza;
            }
            zzds<Set<zzro>> zzl = zzl(zzetVar.zzf(), set, new zzep(this, zzetVar.zzc(), zzetVar.zzb(), zzetVar.zze(), zzetVar.zzd()), new zzdp());
            if (zzl.zza().isEmpty()) {
                next = zzetVar.zza();
            } else {
                if (zzl.zza().size() > 1) {
                    String zzi2 = zzi();
                    StringBuilder sb2 = new StringBuilder(String.valueOf(zzi2).length() + 37 + String.valueOf(str).length());
                    sb2.append(zzi2);
                    sb2.append("Multiple macros active for macroName ");
                    sb2.append(str);
                    zzdh.zzc(sb2.toString());
                }
                next = zzl.zza().iterator().next();
            }
            if (next == null) {
                this.zzj--;
                return zza;
            }
            zzds<com.google.android.gms.internal.gtm.zzak> zzn = zzn(this.zze, next, set, new zzdn());
            if (!zzl.zzb() || !zzn.zzb()) {
                z = false;
            }
            zzds<com.google.android.gms.internal.gtm.zzak> zzdsVar = zza;
            if (zzn != zzdsVar) {
                zzdsVar = new zzds<>(zzn.zza(), z);
            }
            com.google.android.gms.internal.gtm.zzak zza2 = next.zza();
            if (zzdsVar.zzb()) {
                this.zzm.zzb(str, new zzes(zzdsVar, zza2));
            }
            zzk(zza2, set);
            this.zzj--;
            return zzdsVar;
        }
        zzk(zzesVar.zzb(), set);
        this.zzj--;
        return zzesVar.zzc();
    }

    public final zzds<com.google.android.gms.internal.gtm.zzak> zzn(Map<String, zzbu> map, zzro zzroVar, Set<String> set, zzdn zzdnVar) {
        com.google.android.gms.internal.gtm.zzak zzakVar = zzroVar.zzc().get(com.google.android.gms.internal.gtm.zzb.FUNCTION.toString());
        if (zzakVar == null) {
            zzdh.zza("No function id in properties");
            return zza;
        }
        String zzn = zzakVar.zzn();
        zzbu zzbuVar = map.get(zzn);
        if (zzbuVar == null) {
            zzdh.zza(String.valueOf(zzn).concat(" has no backing implementation."));
            return zza;
        }
        zzds<com.google.android.gms.internal.gtm.zzak> zzdsVar = (zzds) this.zzl.zza(zzroVar);
        if (zzdsVar == null) {
            HashMap hashMap = new HashMap();
            boolean z = true;
            boolean z2 = true;
            for (Map.Entry<String, com.google.android.gms.internal.gtm.zzak> entry : zzroVar.zzc().entrySet()) {
                entry.getKey();
                entry.getValue();
                zzds<com.google.android.gms.internal.gtm.zzak> zzo = zzo(entry.getValue(), set, new zzdq());
                zzds<com.google.android.gms.internal.gtm.zzak> zzdsVar2 = zza;
                if (zzo == zzdsVar2) {
                    return zzdsVar2;
                }
                if (zzo.zzb()) {
                    zzroVar.zzd(entry.getKey(), zzo.zza());
                } else {
                    z2 = false;
                }
                hashMap.put(entry.getKey(), zzo.zza());
            }
            if (!zzbuVar.zzg(hashMap.keySet())) {
                String valueOf = String.valueOf(zzbuVar.zzf());
                String valueOf2 = String.valueOf(hashMap.keySet());
                StringBuilder sb = new StringBuilder(String.valueOf(zzn).length() + 43 + valueOf.length() + valueOf2.length());
                sb.append("Incorrect keys for function ");
                sb.append(zzn);
                sb.append(" required ");
                sb.append(valueOf);
                sb.append(" had ");
                sb.append(valueOf2);
                zzdh.zza(sb.toString());
                return zza;
            }
            z = (z2 && zzbuVar.zzb()) ? false : false;
            zzds<com.google.android.gms.internal.gtm.zzak> zzdsVar3 = new zzds<>(zzbuVar.zza(hashMap), z);
            if (z) {
                this.zzl.zzb(zzroVar, zzdsVar3);
            }
            zzdsVar3.zza();
            return zzdsVar3;
        }
        return zzdsVar;
    }

    public final zzds<com.google.android.gms.internal.gtm.zzak> zzo(com.google.android.gms.internal.gtm.zzak zzakVar, Set<String> set, zzdq zzdqVar) {
        com.google.android.gms.internal.gtm.zzao[] zzaoVarArr;
        if (!zzakVar.zzN()) {
            return new zzds<>(zzakVar, true);
        }
        int zzO = zzakVar.zzO();
        if (zzO == 2) {
            com.google.android.gms.internal.gtm.zzal zzalVar = (com.google.android.gms.internal.gtm.zzal) zzsa.zza(zzakVar).zzZ();
            zzalVar.zzj();
            for (int i = 0; i < zzakVar.zza(); i++) {
                zzds<com.google.android.gms.internal.gtm.zzak> zzo = zzo(zzakVar.zzj(i), set, new zzdq());
                zzds<com.google.android.gms.internal.gtm.zzak> zzdsVar = zza;
                if (zzo == zzdsVar) {
                    return zzdsVar;
                }
                zzalVar.zze(zzo.zza());
            }
            return new zzds<>((com.google.android.gms.internal.gtm.zzak) zzalVar.zzC(), false);
        } else if (zzO == 3) {
            com.google.android.gms.internal.gtm.zzal zzalVar2 = (com.google.android.gms.internal.gtm.zzal) zzsa.zza(zzakVar).zzZ();
            if (zzakVar.zzc() != zzakVar.zzd()) {
                String valueOf = String.valueOf(zzakVar.toString());
                zzdh.zza(valueOf.length() != 0 ? "Invalid serving value: ".concat(valueOf) : new String("Invalid serving value: "));
                return zza;
            }
            zzalVar2.zzk();
            zzalVar2.zzl();
            for (int i2 = 0; i2 < zzakVar.zzc(); i2++) {
                zzds<com.google.android.gms.internal.gtm.zzak> zzo2 = zzo(zzakVar.zzk(i2), set, new zzdq());
                zzds<com.google.android.gms.internal.gtm.zzak> zzo3 = zzo(zzakVar.zzl(i2), set, new zzdq());
                zzds<com.google.android.gms.internal.gtm.zzak> zzdsVar2 = zza;
                if (zzo2 == zzdsVar2 || zzo3 == zzdsVar2) {
                    return zzdsVar2;
                }
                zzalVar2.zzf(zzo2.zza());
                zzalVar2.zzg(zzo3.zza());
            }
            return new zzds<>((com.google.android.gms.internal.gtm.zzak) zzalVar2.zzC(), false);
        } else if (zzO != 4) {
            if (zzO != 7) {
                String num = Integer.toString(zzakVar.zzO());
                StringBuilder sb = new StringBuilder(String.valueOf(num).length() + 14);
                sb.append("Unknown type: ");
                sb.append(num);
                zzdh.zza(sb.toString());
                return zza;
            }
            com.google.android.gms.internal.gtm.zzal zzalVar3 = (com.google.android.gms.internal.gtm.zzal) zzsa.zza(zzakVar).zzZ();
            zzalVar3.zzm();
            for (int i3 = 0; i3 < zzakVar.zze(); i3++) {
                zzds<com.google.android.gms.internal.gtm.zzak> zzo4 = zzo(zzakVar.zzm(i3), set, new zzdq());
                zzds<com.google.android.gms.internal.gtm.zzak> zzdsVar3 = zza;
                if (zzo4 == zzdsVar3) {
                    return zzdsVar3;
                }
                zzalVar3.zzh(zzo4.zza());
            }
            return new zzds<>((com.google.android.gms.internal.gtm.zzak) zzalVar3.zzC(), false);
        } else if (set.contains(zzakVar.zzo())) {
            String zzo5 = zzakVar.zzo();
            String obj = set.toString();
            StringBuilder sb2 = new StringBuilder(String.valueOf(zzo5).length() + 79 + String.valueOf(obj).length());
            sb2.append("Macro cycle detected.  Current macro reference: ");
            sb2.append(zzo5);
            sb2.append(".  Previous macro references: ");
            sb2.append(obj);
            sb2.append(".");
            zzdh.zza(sb2.toString());
            return zza;
        } else {
            set.add(zzakVar.zzo());
            zzds<com.google.android.gms.internal.gtm.zzak> zzm = zzm(zzakVar.zzo(), set, new zzdm());
            for (com.google.android.gms.internal.gtm.zzao zzaoVar : (com.google.android.gms.internal.gtm.zzao[]) zzakVar.zzq().toArray(new com.google.android.gms.internal.gtm.zzao[0])) {
                if (!(zzfv.zzl(zzm.zza()) instanceof String)) {
                    zzdh.zza("Escaping can only be applied to strings.");
                } else {
                    com.google.android.gms.internal.gtm.zzao zzaoVar2 = com.google.android.gms.internal.gtm.zzao.ESCAPE_HTML;
                    if (zzaoVar.ordinal() != 11) {
                        String valueOf2 = String.valueOf(zzaoVar);
                        StringBuilder sb3 = new StringBuilder(valueOf2.length() + 28);
                        sb3.append("Unsupported Value Escaping: ");
                        sb3.append(valueOf2);
                        zzdh.zza(sb3.toString());
                    } else {
                        try {
                            zzm = new zzds<>(zzfv.zzc(zzfy.zza(zzfv.zzn(zzfv.zzl(zzm.zza())))), zzm.zzb());
                        } catch (UnsupportedEncodingException e) {
                            zzdh.zzb("Escape URI: unsupported encoding", e);
                        }
                    }
                }
            }
            set.remove(zzakVar.zzo());
            return zzm;
        }
    }
}
