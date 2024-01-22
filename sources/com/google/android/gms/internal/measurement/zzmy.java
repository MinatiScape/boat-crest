package com.google.android.gms.internal.measurement;
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
/* loaded from: classes8.dex */
public final class zzmy {
    public static final zzmy zza;
    public static final zzmy zzb;
    public static final zzmy zzc;
    public static final zzmy zzd;
    public static final zzmy zze;
    public static final zzmy zzf;
    public static final zzmy zzg;
    public static final zzmy zzh;
    public static final zzmy zzi;
    public static final zzmy zzj;
    public static final zzmy zzk;
    public static final zzmy zzl;
    public static final zzmy zzm;
    public static final zzmy zzn;
    public static final zzmy zzo;
    public static final zzmy zzp;
    public static final zzmy zzq;
    public static final zzmy zzr;
    private static final /* synthetic */ zzmy[] zzs;
    private final zzmz zzt;

    static {
        zzmy zzmyVar = new zzmy("DOUBLE", 0, zzmz.DOUBLE, 1);
        zza = zzmyVar;
        zzmy zzmyVar2 = new zzmy("FLOAT", 1, zzmz.FLOAT, 5);
        zzb = zzmyVar2;
        zzmz zzmzVar = zzmz.LONG;
        zzmy zzmyVar3 = new zzmy("INT64", 2, zzmzVar, 0);
        zzc = zzmyVar3;
        zzmy zzmyVar4 = new zzmy("UINT64", 3, zzmzVar, 0);
        zzd = zzmyVar4;
        zzmz zzmzVar2 = zzmz.INT;
        zzmy zzmyVar5 = new zzmy("INT32", 4, zzmzVar2, 0);
        zze = zzmyVar5;
        zzmy zzmyVar6 = new zzmy("FIXED64", 5, zzmzVar, 1);
        zzf = zzmyVar6;
        zzmy zzmyVar7 = new zzmy("FIXED32", 6, zzmzVar2, 5);
        zzg = zzmyVar7;
        zzmy zzmyVar8 = new zzmy("BOOL", 7, zzmz.BOOLEAN, 0);
        zzh = zzmyVar8;
        zzmy zzmyVar9 = new zzmy("STRING", 8, zzmz.STRING, 2);
        zzi = zzmyVar9;
        zzmz zzmzVar3 = zzmz.MESSAGE;
        zzmy zzmyVar10 = new zzmy("GROUP", 9, zzmzVar3, 3);
        zzj = zzmyVar10;
        zzmy zzmyVar11 = new zzmy("MESSAGE", 10, zzmzVar3, 2);
        zzk = zzmyVar11;
        zzmy zzmyVar12 = new zzmy("BYTES", 11, zzmz.BYTE_STRING, 2);
        zzl = zzmyVar12;
        zzmy zzmyVar13 = new zzmy("UINT32", 12, zzmzVar2, 0);
        zzm = zzmyVar13;
        zzmy zzmyVar14 = new zzmy("ENUM", 13, zzmz.ENUM, 0);
        zzn = zzmyVar14;
        zzmy zzmyVar15 = new zzmy("SFIXED32", 14, zzmzVar2, 5);
        zzo = zzmyVar15;
        zzmy zzmyVar16 = new zzmy("SFIXED64", 15, zzmzVar, 1);
        zzp = zzmyVar16;
        zzmy zzmyVar17 = new zzmy("SINT32", 16, zzmzVar2, 0);
        zzq = zzmyVar17;
        zzmy zzmyVar18 = new zzmy("SINT64", 17, zzmzVar, 0);
        zzr = zzmyVar18;
        zzs = new zzmy[]{zzmyVar, zzmyVar2, zzmyVar3, zzmyVar4, zzmyVar5, zzmyVar6, zzmyVar7, zzmyVar8, zzmyVar9, zzmyVar10, zzmyVar11, zzmyVar12, zzmyVar13, zzmyVar14, zzmyVar15, zzmyVar16, zzmyVar17, zzmyVar18};
    }

    private zzmy(String str, int i, zzmz zzmzVar, int i2) {
        this.zzt = zzmzVar;
    }

    public static zzmy[] values() {
        return (zzmy[]) zzs.clone();
    }

    public final zzmz zza() {
        return this.zzt;
    }
}
