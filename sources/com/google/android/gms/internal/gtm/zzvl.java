package com.google.android.gms.internal.gtm;
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
public final class zzvl {
    public static final zzvl zza;
    public static final zzvl zzb;
    public static final zzvl zzc;
    public static final zzvl zzd;
    public static final zzvl zze;
    public static final zzvl zzf;
    public static final zzvl zzg;
    public static final zzvl zzh;
    public static final zzvl zzi;
    public static final zzvl zzj;
    private static final /* synthetic */ zzvl[] zzk;
    private final Class<?> zzl;
    private final Class<?> zzm;
    private final Object zzn;

    static {
        zzvl zzvlVar = new zzvl("VOID", 0, Void.class, Void.class, null);
        zza = zzvlVar;
        Class cls = Integer.TYPE;
        zzvl zzvlVar2 = new zzvl("INT", 1, cls, Integer.class, 0);
        zzb = zzvlVar2;
        zzvl zzvlVar3 = new zzvl("LONG", 2, Long.TYPE, Long.class, 0L);
        zzc = zzvlVar3;
        zzvl zzvlVar4 = new zzvl("FLOAT", 3, Float.TYPE, Float.class, Float.valueOf(0.0f));
        zzd = zzvlVar4;
        zzvl zzvlVar5 = new zzvl("DOUBLE", 4, Double.TYPE, Double.class, Double.valueOf(0.0d));
        zze = zzvlVar5;
        zzvl zzvlVar6 = new zzvl("BOOLEAN", 5, Boolean.TYPE, Boolean.class, Boolean.FALSE);
        zzf = zzvlVar6;
        zzvl zzvlVar7 = new zzvl("STRING", 6, String.class, String.class, "");
        zzg = zzvlVar7;
        zzvl zzvlVar8 = new zzvl("BYTE_STRING", 7, zztd.class, zztd.class, zztd.zzb);
        zzh = zzvlVar8;
        zzvl zzvlVar9 = new zzvl("ENUM", 8, cls, Integer.class, null);
        zzi = zzvlVar9;
        zzvl zzvlVar10 = new zzvl("MESSAGE", 9, Object.class, Object.class, null);
        zzj = zzvlVar10;
        zzk = new zzvl[]{zzvlVar, zzvlVar2, zzvlVar3, zzvlVar4, zzvlVar5, zzvlVar6, zzvlVar7, zzvlVar8, zzvlVar9, zzvlVar10};
    }

    private zzvl(String str, int i, Class cls, Class cls2, Object obj) {
        this.zzl = cls;
        this.zzm = cls2;
        this.zzn = obj;
    }

    public static zzvl[] values() {
        return (zzvl[]) zzk.clone();
    }

    public final Class<?> zza() {
        return this.zzm;
    }
}
