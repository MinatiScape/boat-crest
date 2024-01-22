package com.google.android.gms.internal.measurement;
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
/* loaded from: classes8.dex */
public final class zzkk {
    public static final zzkk zza;
    public static final zzkk zzb;
    public static final zzkk zzc;
    public static final zzkk zzd;
    public static final zzkk zze;
    public static final zzkk zzf;
    public static final zzkk zzg;
    public static final zzkk zzh;
    public static final zzkk zzi;
    public static final zzkk zzj;
    private static final /* synthetic */ zzkk[] zzk;
    private final Class<?> zzl;
    private final Class<?> zzm;
    private final Object zzn;

    static {
        zzkk zzkkVar = new zzkk("VOID", 0, Void.class, Void.class, null);
        zza = zzkkVar;
        Class cls = Integer.TYPE;
        zzkk zzkkVar2 = new zzkk("INT", 1, cls, Integer.class, 0);
        zzb = zzkkVar2;
        zzkk zzkkVar3 = new zzkk("LONG", 2, Long.TYPE, Long.class, 0L);
        zzc = zzkkVar3;
        zzkk zzkkVar4 = new zzkk("FLOAT", 3, Float.TYPE, Float.class, Float.valueOf(0.0f));
        zzd = zzkkVar4;
        zzkk zzkkVar5 = new zzkk("DOUBLE", 4, Double.TYPE, Double.class, Double.valueOf(0.0d));
        zze = zzkkVar5;
        zzkk zzkkVar6 = new zzkk("BOOLEAN", 5, Boolean.TYPE, Boolean.class, Boolean.FALSE);
        zzf = zzkkVar6;
        zzkk zzkkVar7 = new zzkk("STRING", 6, String.class, String.class, "");
        zzg = zzkkVar7;
        zzkk zzkkVar8 = new zzkk("BYTE_STRING", 7, zziy.class, zziy.class, zziy.zzb);
        zzh = zzkkVar8;
        zzkk zzkkVar9 = new zzkk("ENUM", 8, cls, Integer.class, null);
        zzi = zzkkVar9;
        zzkk zzkkVar10 = new zzkk("MESSAGE", 9, Object.class, Object.class, null);
        zzj = zzkkVar10;
        zzk = new zzkk[]{zzkkVar, zzkkVar2, zzkkVar3, zzkkVar4, zzkkVar5, zzkkVar6, zzkkVar7, zzkkVar8, zzkkVar9, zzkkVar10};
    }

    private zzkk(String str, int i, Class cls, Class cls2, Object obj) {
        this.zzl = cls;
        this.zzm = cls2;
        this.zzn = obj;
    }

    public static zzkk[] values() {
        return (zzkk[]) zzk.clone();
    }

    public final Class<?> zza() {
        return this.zzm;
    }
}
