package com.google.android.gms.internal.gtm;
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
public final class zzye {
    public static final zzye zza;
    public static final zzye zzb;
    public static final zzye zzc;
    public static final zzye zzd;
    public static final zzye zze;
    public static final zzye zzf;
    public static final zzye zzg;
    public static final zzye zzh;
    public static final zzye zzi;
    public static final zzye zzj;
    public static final zzye zzk;
    public static final zzye zzl;
    public static final zzye zzm;
    public static final zzye zzn;
    public static final zzye zzo;
    public static final zzye zzp;
    public static final zzye zzq;
    public static final zzye zzr;
    private static final /* synthetic */ zzye[] zzs;
    private final zzyf zzt;

    static {
        zzye zzyeVar = new zzye("DOUBLE", 0, zzyf.DOUBLE, 1);
        zza = zzyeVar;
        zzye zzyeVar2 = new zzye("FLOAT", 1, zzyf.FLOAT, 5);
        zzb = zzyeVar2;
        zzyf zzyfVar = zzyf.LONG;
        zzye zzyeVar3 = new zzye("INT64", 2, zzyfVar, 0);
        zzc = zzyeVar3;
        zzye zzyeVar4 = new zzye("UINT64", 3, zzyfVar, 0);
        zzd = zzyeVar4;
        zzyf zzyfVar2 = zzyf.INT;
        zzye zzyeVar5 = new zzye("INT32", 4, zzyfVar2, 0);
        zze = zzyeVar5;
        zzye zzyeVar6 = new zzye("FIXED64", 5, zzyfVar, 1);
        zzf = zzyeVar6;
        zzye zzyeVar7 = new zzye("FIXED32", 6, zzyfVar2, 5);
        zzg = zzyeVar7;
        zzye zzyeVar8 = new zzye("BOOL", 7, zzyf.BOOLEAN, 0);
        zzh = zzyeVar8;
        zzye zzyeVar9 = new zzye("STRING", 8, zzyf.STRING, 2);
        zzi = zzyeVar9;
        zzyf zzyfVar3 = zzyf.MESSAGE;
        zzye zzyeVar10 = new zzye("GROUP", 9, zzyfVar3, 3);
        zzj = zzyeVar10;
        zzye zzyeVar11 = new zzye("MESSAGE", 10, zzyfVar3, 2);
        zzk = zzyeVar11;
        zzye zzyeVar12 = new zzye("BYTES", 11, zzyf.BYTE_STRING, 2);
        zzl = zzyeVar12;
        zzye zzyeVar13 = new zzye("UINT32", 12, zzyfVar2, 0);
        zzm = zzyeVar13;
        zzye zzyeVar14 = new zzye("ENUM", 13, zzyf.ENUM, 0);
        zzn = zzyeVar14;
        zzye zzyeVar15 = new zzye("SFIXED32", 14, zzyfVar2, 5);
        zzo = zzyeVar15;
        zzye zzyeVar16 = new zzye("SFIXED64", 15, zzyfVar, 1);
        zzp = zzyeVar16;
        zzye zzyeVar17 = new zzye("SINT32", 16, zzyfVar2, 0);
        zzq = zzyeVar17;
        zzye zzyeVar18 = new zzye("SINT64", 17, zzyfVar, 0);
        zzr = zzyeVar18;
        zzs = new zzye[]{zzyeVar, zzyeVar2, zzyeVar3, zzyeVar4, zzyeVar5, zzyeVar6, zzyeVar7, zzyeVar8, zzyeVar9, zzyeVar10, zzyeVar11, zzyeVar12, zzyeVar13, zzyeVar14, zzyeVar15, zzyeVar16, zzyeVar17, zzyeVar18};
    }

    private zzye(String str, int i, zzyf zzyfVar, int i2) {
        this.zzt = zzyfVar;
    }

    public static zzye[] values() {
        return (zzye[]) zzs.clone();
    }

    public final zzyf zza() {
        return this.zzt;
    }
}
