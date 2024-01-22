package com.google.android.recaptcha.internal;
/* JADX WARN: Enum visitor error
jadx.core.utils.exceptions.JadxRuntimeException: Init of enum zzb uses external variables
	at jadx.core.dex.visitors.EnumVisitor.createEnumFieldByConstructor(EnumVisitor.java:444)
	at jadx.core.dex.visitors.EnumVisitor.processEnumFieldByRegister(EnumVisitor.java:391)
	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromFilledArray(EnumVisitor.java:320)
	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromInsn(EnumVisitor.java:258)
	at jadx.core.dex.visitors.EnumVisitor.convertToEnum(EnumVisitor.java:151)
	at jadx.core.dex.visitors.EnumVisitor.visit(EnumVisitor.java:100)
 */
/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* loaded from: classes10.dex */
public final class zzhq {
    public static final zzhq zza;
    public static final zzhq zzb;
    public static final zzhq zzc;
    public static final zzhq zzd;
    public static final zzhq zze;
    public static final zzhq zzf;
    public static final zzhq zzg;
    public static final zzhq zzh;
    public static final zzhq zzi;
    public static final zzhq zzj;
    private static final /* synthetic */ zzhq[] zzk;
    private final Class zzl;
    private final Class zzm;
    private final Object zzn;

    static {
        zzhq zzhqVar = new zzhq("VOID", 0, Void.class, Void.class, null);
        zza = zzhqVar;
        Class cls = Integer.TYPE;
        zzhq zzhqVar2 = new zzhq("INT", 1, cls, Integer.class, 0);
        zzb = zzhqVar2;
        zzhq zzhqVar3 = new zzhq("LONG", 2, Long.TYPE, Long.class, 0L);
        zzc = zzhqVar3;
        zzhq zzhqVar4 = new zzhq("FLOAT", 3, Float.TYPE, Float.class, Float.valueOf(0.0f));
        zzd = zzhqVar4;
        zzhq zzhqVar5 = new zzhq("DOUBLE", 4, Double.TYPE, Double.class, Double.valueOf(0.0d));
        zze = zzhqVar5;
        zzhq zzhqVar6 = new zzhq("BOOLEAN", 5, Boolean.TYPE, Boolean.class, Boolean.FALSE);
        zzf = zzhqVar6;
        zzhq zzhqVar7 = new zzhq("STRING", 6, String.class, String.class, "");
        zzg = zzhqVar7;
        zzhq zzhqVar8 = new zzhq("BYTE_STRING", 7, zzfi.class, zzfi.class, zzfi.zzb);
        zzh = zzhqVar8;
        zzhq zzhqVar9 = new zzhq("ENUM", 8, cls, Integer.class, null);
        zzi = zzhqVar9;
        zzhq zzhqVar10 = new zzhq("MESSAGE", 9, Object.class, Object.class, null);
        zzj = zzhqVar10;
        zzk = new zzhq[]{zzhqVar, zzhqVar2, zzhqVar3, zzhqVar4, zzhqVar5, zzhqVar6, zzhqVar7, zzhqVar8, zzhqVar9, zzhqVar10};
    }

    private zzhq(String str, int i, Class cls, Class cls2, Object obj) {
        this.zzl = cls;
        this.zzm = cls2;
        this.zzn = obj;
    }

    public static zzhq[] values() {
        return (zzhq[]) zzk.clone();
    }

    public final Class zza() {
        return this.zzm;
    }
}
