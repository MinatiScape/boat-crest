package com.google.android.gms.internal.fitness;
/* JADX WARN: Enum visitor error
jadx.core.utils.exceptions.JadxRuntimeException: Init of enum zzyq uses external variables
	at jadx.core.dex.visitors.EnumVisitor.createEnumFieldByConstructor(EnumVisitor.java:444)
	at jadx.core.dex.visitors.EnumVisitor.processEnumFieldByRegister(EnumVisitor.java:391)
	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromFilledArray(EnumVisitor.java:320)
	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromInsn(EnumVisitor.java:258)
	at jadx.core.dex.visitors.EnumVisitor.convertToEnum(EnumVisitor.java:151)
	at jadx.core.dex.visitors.EnumVisitor.visit(EnumVisitor.java:100)
 */
/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* loaded from: classes8.dex */
public final class zzhm {
    public static final zzhm zzyp;
    public static final zzhm zzyq;
    public static final zzhm zzyr;
    public static final zzhm zzys;
    public static final zzhm zzyt;
    public static final zzhm zzyu;
    public static final zzhm zzyv;
    public static final zzhm zzyw;
    public static final zzhm zzyx;
    public static final zzhm zzyy;
    private static final /* synthetic */ zzhm[] zzzc;
    private final Class<?> zzyz;
    private final Class<?> zzza;
    private final Object zzzb;

    static {
        zzhm zzhmVar = new zzhm("VOID", 0, Void.class, Void.class, null);
        zzyp = zzhmVar;
        Class cls = Integer.TYPE;
        zzhm zzhmVar2 = new zzhm("INT", 1, cls, Integer.class, 0);
        zzyq = zzhmVar2;
        zzhm zzhmVar3 = new zzhm("LONG", 2, Long.TYPE, Long.class, 0L);
        zzyr = zzhmVar3;
        zzhm zzhmVar4 = new zzhm("FLOAT", 3, Float.TYPE, Float.class, Float.valueOf(0.0f));
        zzys = zzhmVar4;
        zzhm zzhmVar5 = new zzhm("DOUBLE", 4, Double.TYPE, Double.class, Double.valueOf(0.0d));
        zzyt = zzhmVar5;
        zzhm zzhmVar6 = new zzhm("BOOLEAN", 5, Boolean.TYPE, Boolean.class, Boolean.FALSE);
        zzyu = zzhmVar6;
        zzhm zzhmVar7 = new zzhm("STRING", 6, String.class, String.class, "");
        zzyv = zzhmVar7;
        zzhm zzhmVar8 = new zzhm("BYTE_STRING", 7, zzfx.class, zzfx.class, zzfx.zzub);
        zzyw = zzhmVar8;
        zzhm zzhmVar9 = new zzhm("ENUM", 8, cls, Integer.class, null);
        zzyx = zzhmVar9;
        zzhm zzhmVar10 = new zzhm("MESSAGE", 9, Object.class, Object.class, null);
        zzyy = zzhmVar10;
        zzzc = new zzhm[]{zzhmVar, zzhmVar2, zzhmVar3, zzhmVar4, zzhmVar5, zzhmVar6, zzhmVar7, zzhmVar8, zzhmVar9, zzhmVar10};
    }

    private zzhm(String str, int i, Class cls, Class cls2, Object obj) {
        this.zzyz = cls;
        this.zzza = cls2;
        this.zzzb = obj;
    }

    public static zzhm[] values() {
        return (zzhm[]) zzzc.clone();
    }

    public final Class<?> zzcf() {
        return this.zzza;
    }
}
