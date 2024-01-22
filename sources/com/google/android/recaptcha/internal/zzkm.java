package com.google.android.recaptcha.internal;
/* JADX WARN: Enum visitor error
jadx.core.utils.exceptions.JadxRuntimeException: Init of enum zzc uses external variables
	at jadx.core.dex.visitors.EnumVisitor.createEnumFieldByConstructor(EnumVisitor.java:444)
	at jadx.core.dex.visitors.EnumVisitor.processEnumFieldByRegister(EnumVisitor.java:391)
	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromFilledArray(EnumVisitor.java:320)
	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromInsn(EnumVisitor.java:258)
	at jadx.core.dex.visitors.EnumVisitor.convertToEnum(EnumVisitor.java:151)
	at jadx.core.dex.visitors.EnumVisitor.visit(EnumVisitor.java:100)
 */
/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* loaded from: classes10.dex */
public final class zzkm {
    public static final zzkm zza;
    public static final zzkm zzb;
    public static final zzkm zzc;
    public static final zzkm zzd;
    public static final zzkm zze;
    public static final zzkm zzf;
    public static final zzkm zzg;
    public static final zzkm zzh;
    public static final zzkm zzi;
    public static final zzkm zzj;
    public static final zzkm zzk;
    public static final zzkm zzl;
    public static final zzkm zzm;
    public static final zzkm zzn;
    public static final zzkm zzo;
    public static final zzkm zzp;
    public static final zzkm zzq;
    public static final zzkm zzr;
    private static final /* synthetic */ zzkm[] zzs;
    private final zzkn zzt;

    static {
        zzkm zzkmVar = new zzkm("DOUBLE", 0, zzkn.DOUBLE, 1);
        zza = zzkmVar;
        zzkm zzkmVar2 = new zzkm("FLOAT", 1, zzkn.FLOAT, 5);
        zzb = zzkmVar2;
        zzkn zzknVar = zzkn.LONG;
        zzkm zzkmVar3 = new zzkm("INT64", 2, zzknVar, 0);
        zzc = zzkmVar3;
        zzkm zzkmVar4 = new zzkm("UINT64", 3, zzknVar, 0);
        zzd = zzkmVar4;
        zzkn zzknVar2 = zzkn.INT;
        zzkm zzkmVar5 = new zzkm("INT32", 4, zzknVar2, 0);
        zze = zzkmVar5;
        zzkm zzkmVar6 = new zzkm("FIXED64", 5, zzknVar, 1);
        zzf = zzkmVar6;
        zzkm zzkmVar7 = new zzkm("FIXED32", 6, zzknVar2, 5);
        zzg = zzkmVar7;
        zzkm zzkmVar8 = new zzkm("BOOL", 7, zzkn.BOOLEAN, 0);
        zzh = zzkmVar8;
        zzkm zzkmVar9 = new zzkm("STRING", 8, zzkn.STRING, 2);
        zzi = zzkmVar9;
        zzkn zzknVar3 = zzkn.MESSAGE;
        zzkm zzkmVar10 = new zzkm("GROUP", 9, zzknVar3, 3);
        zzj = zzkmVar10;
        zzkm zzkmVar11 = new zzkm("MESSAGE", 10, zzknVar3, 2);
        zzk = zzkmVar11;
        zzkm zzkmVar12 = new zzkm("BYTES", 11, zzkn.BYTE_STRING, 2);
        zzl = zzkmVar12;
        zzkm zzkmVar13 = new zzkm("UINT32", 12, zzknVar2, 0);
        zzm = zzkmVar13;
        zzkm zzkmVar14 = new zzkm("ENUM", 13, zzkn.ENUM, 0);
        zzn = zzkmVar14;
        zzkm zzkmVar15 = new zzkm("SFIXED32", 14, zzknVar2, 5);
        zzo = zzkmVar15;
        zzkm zzkmVar16 = new zzkm("SFIXED64", 15, zzknVar, 1);
        zzp = zzkmVar16;
        zzkm zzkmVar17 = new zzkm("SINT32", 16, zzknVar2, 0);
        zzq = zzkmVar17;
        zzkm zzkmVar18 = new zzkm("SINT64", 17, zzknVar, 0);
        zzr = zzkmVar18;
        zzs = new zzkm[]{zzkmVar, zzkmVar2, zzkmVar3, zzkmVar4, zzkmVar5, zzkmVar6, zzkmVar7, zzkmVar8, zzkmVar9, zzkmVar10, zzkmVar11, zzkmVar12, zzkmVar13, zzkmVar14, zzkmVar15, zzkmVar16, zzkmVar17, zzkmVar18};
    }

    private zzkm(String str, int i, zzkn zzknVar, int i2) {
        this.zzt = zzknVar;
    }

    public static zzkm[] values() {
        return (zzkm[]) zzs.clone();
    }

    public final zzkn zza() {
        return this.zzt;
    }
}
