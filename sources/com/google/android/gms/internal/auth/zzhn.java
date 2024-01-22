package com.google.android.gms.internal.auth;
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
/* loaded from: classes6.dex */
public final class zzhn {
    public static final zzhn zza;
    public static final zzhn zzb;
    public static final zzhn zzc;
    public static final zzhn zzd;
    public static final zzhn zze;
    public static final zzhn zzf;
    public static final zzhn zzg;
    public static final zzhn zzh;
    public static final zzhn zzi;
    public static final zzhn zzj;
    public static final zzhn zzk;
    public static final zzhn zzl;
    public static final zzhn zzm;
    public static final zzhn zzn;
    public static final zzhn zzo;
    public static final zzhn zzp;
    public static final zzhn zzq;
    public static final zzhn zzr;
    private static final /* synthetic */ zzhn[] zzs;
    private final zzho zzt;

    static {
        zzhn zzhnVar = new zzhn("DOUBLE", 0, zzho.DOUBLE, 1);
        zza = zzhnVar;
        zzhn zzhnVar2 = new zzhn("FLOAT", 1, zzho.FLOAT, 5);
        zzb = zzhnVar2;
        zzho zzhoVar = zzho.LONG;
        zzhn zzhnVar3 = new zzhn("INT64", 2, zzhoVar, 0);
        zzc = zzhnVar3;
        zzhn zzhnVar4 = new zzhn("UINT64", 3, zzhoVar, 0);
        zzd = zzhnVar4;
        zzho zzhoVar2 = zzho.INT;
        zzhn zzhnVar5 = new zzhn("INT32", 4, zzhoVar2, 0);
        zze = zzhnVar5;
        zzhn zzhnVar6 = new zzhn("FIXED64", 5, zzhoVar, 1);
        zzf = zzhnVar6;
        zzhn zzhnVar7 = new zzhn("FIXED32", 6, zzhoVar2, 5);
        zzg = zzhnVar7;
        zzhn zzhnVar8 = new zzhn("BOOL", 7, zzho.BOOLEAN, 0);
        zzh = zzhnVar8;
        zzhn zzhnVar9 = new zzhn("STRING", 8, zzho.STRING, 2);
        zzi = zzhnVar9;
        zzho zzhoVar3 = zzho.MESSAGE;
        zzhn zzhnVar10 = new zzhn("GROUP", 9, zzhoVar3, 3);
        zzj = zzhnVar10;
        zzhn zzhnVar11 = new zzhn("MESSAGE", 10, zzhoVar3, 2);
        zzk = zzhnVar11;
        zzhn zzhnVar12 = new zzhn("BYTES", 11, zzho.BYTE_STRING, 2);
        zzl = zzhnVar12;
        zzhn zzhnVar13 = new zzhn("UINT32", 12, zzhoVar2, 0);
        zzm = zzhnVar13;
        zzhn zzhnVar14 = new zzhn("ENUM", 13, zzho.ENUM, 0);
        zzn = zzhnVar14;
        zzhn zzhnVar15 = new zzhn("SFIXED32", 14, zzhoVar2, 5);
        zzo = zzhnVar15;
        zzhn zzhnVar16 = new zzhn("SFIXED64", 15, zzhoVar, 1);
        zzp = zzhnVar16;
        zzhn zzhnVar17 = new zzhn("SINT32", 16, zzhoVar2, 0);
        zzq = zzhnVar17;
        zzhn zzhnVar18 = new zzhn("SINT64", 17, zzhoVar, 0);
        zzr = zzhnVar18;
        zzs = new zzhn[]{zzhnVar, zzhnVar2, zzhnVar3, zzhnVar4, zzhnVar5, zzhnVar6, zzhnVar7, zzhnVar8, zzhnVar9, zzhnVar10, zzhnVar11, zzhnVar12, zzhnVar13, zzhnVar14, zzhnVar15, zzhnVar16, zzhnVar17, zzhnVar18};
    }

    private zzhn(String str, int i, zzho zzhoVar, int i2) {
        this.zzt = zzhoVar;
    }

    public static zzhn[] values() {
        return (zzhn[]) zzs.clone();
    }

    public final zzho zza() {
        return this.zzt;
    }
}
