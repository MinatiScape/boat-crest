package com.google.android.gms.internal.measurement;

import java.util.List;
/* loaded from: classes8.dex */
public final class zzay extends zzaw {
    public zzay() {
        this.f8941a.add(zzbl.EQUALS);
        this.f8941a.add(zzbl.GREATER_THAN);
        this.f8941a.add(zzbl.GREATER_THAN_EQUALS);
        this.f8941a.add(zzbl.IDENTITY_EQUALS);
        this.f8941a.add(zzbl.IDENTITY_NOT_EQUALS);
        this.f8941a.add(zzbl.LESS_THAN);
        this.f8941a.add(zzbl.LESS_THAN_EQUALS);
        this.f8941a.add(zzbl.NOT_EQUALS);
    }

    public static boolean b(zzap zzapVar, zzap zzapVar2) {
        if (zzapVar.getClass().equals(zzapVar2.getClass())) {
            if ((zzapVar instanceof zzau) || (zzapVar instanceof zzan)) {
                return true;
            }
            if (zzapVar instanceof zzah) {
                if (Double.isNaN(zzapVar.zzh().doubleValue()) || Double.isNaN(zzapVar2.zzh().doubleValue())) {
                    return false;
                }
                return zzapVar.zzh().equals(zzapVar2.zzh());
            } else if (zzapVar instanceof zzat) {
                return zzapVar.zzi().equals(zzapVar2.zzi());
            } else {
                if (zzapVar instanceof zzaf) {
                    return zzapVar.zzg().equals(zzapVar2.zzg());
                }
                return zzapVar == zzapVar2;
            }
        } else if (((zzapVar instanceof zzau) || (zzapVar instanceof zzan)) && ((zzapVar2 instanceof zzau) || (zzapVar2 instanceof zzan))) {
            return true;
        } else {
            boolean z = zzapVar instanceof zzah;
            if (z && (zzapVar2 instanceof zzat)) {
                return b(zzapVar, new zzah(zzapVar2.zzh()));
            }
            boolean z2 = zzapVar instanceof zzat;
            if (z2 && (zzapVar2 instanceof zzah)) {
                return b(new zzah(zzapVar.zzh()), zzapVar2);
            }
            if (zzapVar instanceof zzaf) {
                return b(new zzah(zzapVar.zzh()), zzapVar2);
            }
            if (zzapVar2 instanceof zzaf) {
                return b(zzapVar, new zzah(zzapVar2.zzh()));
            }
            if ((!z2 && !z) || !(zzapVar2 instanceof zzal)) {
                if ((zzapVar instanceof zzal) && ((zzapVar2 instanceof zzat) || (zzapVar2 instanceof zzah))) {
                    return b(new zzat(zzapVar.zzi()), zzapVar2);
                }
                return false;
            }
            return b(zzapVar, new zzat(zzapVar2.zzi()));
        }
    }

    public static boolean c(zzap zzapVar, zzap zzapVar2) {
        if (zzapVar instanceof zzal) {
            zzapVar = new zzat(zzapVar.zzi());
        }
        if (zzapVar2 instanceof zzal) {
            zzapVar2 = new zzat(zzapVar2.zzi());
        }
        if ((zzapVar instanceof zzat) && (zzapVar2 instanceof zzat)) {
            return zzapVar.zzi().compareTo(zzapVar2.zzi()) < 0;
        }
        double doubleValue = zzapVar.zzh().doubleValue();
        double doubleValue2 = zzapVar2.zzh().doubleValue();
        return (Double.isNaN(doubleValue) || Double.isNaN(doubleValue2) || Double.compare(doubleValue, doubleValue2) >= 0) ? false : true;
    }

    public static boolean d(zzap zzapVar, zzap zzapVar2) {
        if (zzapVar instanceof zzal) {
            zzapVar = new zzat(zzapVar.zzi());
        }
        if (zzapVar2 instanceof zzal) {
            zzapVar2 = new zzat(zzapVar2.zzi());
        }
        return (((zzapVar instanceof zzat) && (zzapVar2 instanceof zzat)) || !(Double.isNaN(zzapVar.zzh().doubleValue()) || Double.isNaN(zzapVar2.zzh().doubleValue()))) && !c(zzapVar2, zzapVar);
    }

    @Override // com.google.android.gms.internal.measurement.zzaw
    public final zzap zza(String str, zzg zzgVar, List<zzap> list) {
        boolean b;
        boolean b2;
        zzh.zzh(zzh.zze(str).name(), 2, list);
        zzap zzb = zzgVar.zzb(list.get(0));
        zzap zzb2 = zzgVar.zzb(list.get(1));
        int ordinal = zzh.zze(str).ordinal();
        if (ordinal != 23) {
            if (ordinal == 48) {
                b2 = b(zzb, zzb2);
            } else if (ordinal == 42) {
                b = c(zzb, zzb2);
            } else if (ordinal != 43) {
                switch (ordinal) {
                    case 37:
                        b = c(zzb2, zzb);
                        break;
                    case 38:
                        b = d(zzb2, zzb);
                        break;
                    case 39:
                        b = zzh.zzl(zzb, zzb2);
                        break;
                    case 40:
                        b2 = zzh.zzl(zzb, zzb2);
                        break;
                    default:
                        return super.a(str);
                }
            } else {
                b = d(zzb, zzb2);
            }
            b = !b2;
        } else {
            b = b(zzb, zzb2);
        }
        return b ? zzap.zzk : zzap.zzl;
    }
}
