package com.google.android.libraries.places.internal;
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
public final class zzsq {
    public static final zzsq zza;
    public static final zzsq zzb;
    public static final zzsq zzc;
    public static final zzsq zzd;
    public static final zzsq zze;
    public static final zzsq zzf;
    public static final zzsq zzg;
    public static final zzsq zzh;
    public static final zzsq zzi;
    public static final zzsq zzj;
    private static final /* synthetic */ zzsq[] zzn;
    private final Class<?> zzk;
    private final Class<?> zzl;
    private final Object zzm;

    static {
        zzsq zzsqVar = new zzsq("VOID", 0, Void.class, Void.class, null);
        zza = zzsqVar;
        Class cls = Integer.TYPE;
        zzsq zzsqVar2 = new zzsq("INT", 1, cls, Integer.class, 0);
        zzb = zzsqVar2;
        zzsq zzsqVar3 = new zzsq("LONG", 2, Long.TYPE, Long.class, 0L);
        zzc = zzsqVar3;
        zzsq zzsqVar4 = new zzsq("FLOAT", 3, Float.TYPE, Float.class, Float.valueOf(0.0f));
        zzd = zzsqVar4;
        zzsq zzsqVar5 = new zzsq("DOUBLE", 4, Double.TYPE, Double.class, Double.valueOf(0.0d));
        zze = zzsqVar5;
        zzsq zzsqVar6 = new zzsq("BOOLEAN", 5, Boolean.TYPE, Boolean.class, Boolean.FALSE);
        zzf = zzsqVar6;
        zzsq zzsqVar7 = new zzsq("STRING", 6, String.class, String.class, "");
        zzg = zzsqVar7;
        zzsq zzsqVar8 = new zzsq("BYTE_STRING", 7, zzrb.class, zzrb.class, zzrb.zza);
        zzh = zzsqVar8;
        zzsq zzsqVar9 = new zzsq("ENUM", 8, cls, Integer.class, null);
        zzi = zzsqVar9;
        zzsq zzsqVar10 = new zzsq("MESSAGE", 9, Object.class, Object.class, null);
        zzj = zzsqVar10;
        zzn = new zzsq[]{zzsqVar, zzsqVar2, zzsqVar3, zzsqVar4, zzsqVar5, zzsqVar6, zzsqVar7, zzsqVar8, zzsqVar9, zzsqVar10};
    }

    private zzsq(String str, int i, Class cls, Class cls2, Object obj) {
        this.zzk = cls;
        this.zzl = cls2;
        this.zzm = obj;
    }

    public static zzsq[] values() {
        return (zzsq[]) zzn.clone();
    }

    public final Class<?> zza() {
        return this.zzl;
    }
}
