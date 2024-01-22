package com.google.android.gms.internal.measurement;

import com.clevertap.android.sdk.variables.CTVariableUtils;
import java.util.List;
/* loaded from: classes8.dex */
public final class zzbk extends zzaw {
    public zzbk() {
        this.f8941a.add(zzbl.ASSIGN);
        this.f8941a.add(zzbl.CONST);
        this.f8941a.add(zzbl.CREATE_ARRAY);
        this.f8941a.add(zzbl.CREATE_OBJECT);
        this.f8941a.add(zzbl.EXPRESSION_LIST);
        this.f8941a.add(zzbl.GET);
        this.f8941a.add(zzbl.GET_INDEX);
        this.f8941a.add(zzbl.GET_PROPERTY);
        this.f8941a.add(zzbl.NULL);
        this.f8941a.add(zzbl.SET_PROPERTY);
        this.f8941a.add(zzbl.TYPEOF);
        this.f8941a.add(zzbl.UNDEFINED);
        this.f8941a.add(zzbl.VAR);
    }

    @Override // com.google.android.gms.internal.measurement.zzaw
    public final zzap zza(String str, zzg zzgVar, List<zzap> list) {
        String str2;
        zzbl zzblVar = zzbl.ADD;
        int ordinal = zzh.zze(str).ordinal();
        int i = 0;
        if (ordinal == 3) {
            zzh.zzh(zzbl.ASSIGN.name(), 2, list);
            zzap zzb = zzgVar.zzb(list.get(0));
            if (zzb instanceof zzat) {
                if (zzgVar.zzh(zzb.zzi())) {
                    zzap zzb2 = zzgVar.zzb(list.get(1));
                    zzgVar.zzg(zzb.zzi(), zzb2);
                    return zzb2;
                }
                throw new IllegalArgumentException(String.format("Attempting to assign undefined value %s", zzb.zzi()));
            }
            throw new IllegalArgumentException(String.format("Expected string for assign var. got %s", zzb.getClass().getCanonicalName()));
        } else if (ordinal == 14) {
            zzh.zzi(zzbl.CONST.name(), 2, list);
            if (list.size() % 2 == 0) {
                for (int i2 = 0; i2 < list.size() - 1; i2 += 2) {
                    zzap zzb3 = zzgVar.zzb(list.get(i2));
                    if (zzb3 instanceof zzat) {
                        zzgVar.zzf(zzb3.zzi(), zzgVar.zzb(list.get(i2 + 1)));
                    } else {
                        throw new IllegalArgumentException(String.format("Expected string for const name. got %s", zzb3.getClass().getCanonicalName()));
                    }
                }
                return zzap.zzf;
            }
            throw new IllegalArgumentException(String.format("CONST requires an even number of arguments, found %s", Integer.valueOf(list.size())));
        } else if (ordinal == 24) {
            zzh.zzi(zzbl.EXPRESSION_LIST.name(), 1, list);
            zzap zzapVar = zzap.zzf;
            while (i < list.size()) {
                zzapVar = zzgVar.zzb(list.get(i));
                if (zzapVar instanceof zzag) {
                    throw new IllegalStateException("ControlValue cannot be in an expression list");
                }
                i++;
            }
            return zzapVar;
        } else if (ordinal == 33) {
            zzh.zzh(zzbl.GET.name(), 1, list);
            zzap zzb4 = zzgVar.zzb(list.get(0));
            if (zzb4 instanceof zzat) {
                return zzgVar.zzd(zzb4.zzi());
            }
            throw new IllegalArgumentException(String.format("Expected string for get var. got %s", zzb4.getClass().getCanonicalName()));
        } else if (ordinal == 49) {
            zzh.zzh(zzbl.NULL.name(), 0, list);
            return zzap.zzg;
        } else if (ordinal == 58) {
            zzh.zzh(zzbl.SET_PROPERTY.name(), 3, list);
            zzap zzb5 = zzgVar.zzb(list.get(0));
            zzap zzb6 = zzgVar.zzb(list.get(1));
            zzap zzb7 = zzgVar.zzb(list.get(2));
            if (zzb5 != zzap.zzf && zzb5 != zzap.zzg) {
                if ((zzb5 instanceof zzae) && (zzb6 instanceof zzah)) {
                    ((zzae) zzb5).zzq(zzb6.zzh().intValue(), zzb7);
                } else if (zzb5 instanceof zzal) {
                    ((zzal) zzb5).zzr(zzb6.zzi(), zzb7);
                }
                return zzb7;
            }
            throw new IllegalStateException(String.format("Can't set property %s of %s", zzb6.zzi(), zzb5.zzi()));
        } else if (ordinal == 17) {
            if (list.isEmpty()) {
                return new zzae();
            }
            zzae zzaeVar = new zzae();
            for (zzap zzapVar2 : list) {
                zzap zzb8 = zzgVar.zzb(zzapVar2);
                if (!(zzb8 instanceof zzag)) {
                    zzaeVar.zzq(i, zzb8);
                    i++;
                } else {
                    throw new IllegalStateException("Failed to evaluate array element");
                }
            }
            return zzaeVar;
        } else if (ordinal == 18) {
            if (list.isEmpty()) {
                return new zzam();
            }
            if (list.size() % 2 == 0) {
                zzam zzamVar = new zzam();
                while (i < list.size() - 1) {
                    zzap zzb9 = zzgVar.zzb(list.get(i));
                    zzap zzb10 = zzgVar.zzb(list.get(i + 1));
                    if (!(zzb9 instanceof zzag) && !(zzb10 instanceof zzag)) {
                        zzamVar.zzr(zzb9.zzi(), zzb10);
                        i += 2;
                    } else {
                        throw new IllegalStateException("Failed to evaluate map entry");
                    }
                }
                return zzamVar;
            }
            throw new IllegalArgumentException(String.format("CREATE_OBJECT requires an even number of arguments, found %s", Integer.valueOf(list.size())));
        } else if (ordinal != 35 && ordinal != 36) {
            switch (ordinal) {
                case 62:
                    zzh.zzh(zzbl.TYPEOF.name(), 1, list);
                    zzap zzb11 = zzgVar.zzb(list.get(0));
                    if (zzb11 instanceof zzau) {
                        str2 = "undefined";
                    } else if (zzb11 instanceof zzaf) {
                        str2 = "boolean";
                    } else if (zzb11 instanceof zzah) {
                        str2 = CTVariableUtils.NUMBER;
                    } else if (zzb11 instanceof zzat) {
                        str2 = "string";
                    } else if (zzb11 instanceof zzao) {
                        str2 = "function";
                    } else if ((zzb11 instanceof zzaq) || (zzb11 instanceof zzag)) {
                        throw new IllegalArgumentException(String.format("Unsupported value type %s in typeof", zzb11));
                    } else {
                        str2 = "object";
                    }
                    return new zzat(str2);
                case 63:
                    zzh.zzh(zzbl.UNDEFINED.name(), 0, list);
                    return zzap.zzf;
                case 64:
                    zzh.zzi(zzbl.VAR.name(), 1, list);
                    for (zzap zzapVar3 : list) {
                        zzap zzb12 = zzgVar.zzb(zzapVar3);
                        if (zzb12 instanceof zzat) {
                            zzgVar.zze(zzb12.zzi(), zzap.zzf);
                        } else {
                            throw new IllegalArgumentException(String.format("Expected string for var name. got %s", zzb12.getClass().getCanonicalName()));
                        }
                    }
                    return zzap.zzf;
                default:
                    return super.a(str);
            }
        } else {
            zzh.zzh(zzbl.GET_PROPERTY.name(), 2, list);
            zzap zzb13 = zzgVar.zzb(list.get(0));
            zzap zzb14 = zzgVar.zzb(list.get(1));
            if ((zzb13 instanceof zzae) && zzh.zzk(zzb14)) {
                return ((zzae) zzb13).zze(zzb14.zzh().intValue());
            }
            if (zzb13 instanceof zzal) {
                return ((zzal) zzb13).zzf(zzb14.zzi());
            }
            if (zzb13 instanceof zzat) {
                if ("length".equals(zzb14.zzi())) {
                    return new zzah(Double.valueOf(zzb13.zzi().length()));
                }
                if (zzh.zzk(zzb14) && zzb14.zzh().doubleValue() < zzb13.zzi().length()) {
                    return new zzat(String.valueOf(zzb13.zzi().charAt(zzb14.zzh().intValue())));
                }
            }
            return zzap.zzf;
        }
    }
}
