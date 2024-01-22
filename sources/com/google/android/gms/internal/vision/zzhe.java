package com.google.android.gms.internal.vision;
/* JADX WARN: Enum visitor error
jadx.core.utils.exceptions.JadxRuntimeException: Init of enum zzxp uses external variables
	at jadx.core.dex.visitors.EnumVisitor.createEnumFieldByConstructor(EnumVisitor.java:444)
	at jadx.core.dex.visitors.EnumVisitor.processEnumFieldByRegister(EnumVisitor.java:391)
	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromFilledArray(EnumVisitor.java:320)
	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromInsn(EnumVisitor.java:258)
	at jadx.core.dex.visitors.EnumVisitor.convertToEnum(EnumVisitor.java:151)
	at jadx.core.dex.visitors.EnumVisitor.visit(EnumVisitor.java:100)
 */
/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* loaded from: classes10.dex */
public final class zzhe {
    public static final zzhe zzxo;
    public static final zzhe zzxp;
    public static final zzhe zzxq;
    public static final zzhe zzxr;
    public static final zzhe zzxs;
    public static final zzhe zzxt;
    public static final zzhe zzxu;
    public static final zzhe zzxv;
    public static final zzhe zzxw;
    public static final zzhe zzxx;
    private static final /* synthetic */ zzhe[] zzyb;
    private final Class<?> zzxy;
    private final Class<?> zzxz;
    private final Object zzya;

    static {
        zzhe zzheVar = new zzhe("VOID", 0, Void.class, Void.class, null);
        zzxo = zzheVar;
        Class cls = Integer.TYPE;
        zzhe zzheVar2 = new zzhe("INT", 1, cls, Integer.class, 0);
        zzxp = zzheVar2;
        zzhe zzheVar3 = new zzhe("LONG", 2, Long.TYPE, Long.class, 0L);
        zzxq = zzheVar3;
        zzhe zzheVar4 = new zzhe("FLOAT", 3, Float.TYPE, Float.class, Float.valueOf(0.0f));
        zzxr = zzheVar4;
        zzhe zzheVar5 = new zzhe("DOUBLE", 4, Double.TYPE, Double.class, Double.valueOf(0.0d));
        zzxs = zzheVar5;
        zzhe zzheVar6 = new zzhe("BOOLEAN", 5, Boolean.TYPE, Boolean.class, Boolean.FALSE);
        zzxt = zzheVar6;
        zzhe zzheVar7 = new zzhe("STRING", 6, String.class, String.class, "");
        zzxu = zzheVar7;
        zzhe zzheVar8 = new zzhe("BYTE_STRING", 7, zzfh.class, zzfh.class, zzfh.zzsd);
        zzxv = zzheVar8;
        zzhe zzheVar9 = new zzhe("ENUM", 8, cls, Integer.class, null);
        zzxw = zzheVar9;
        zzhe zzheVar10 = new zzhe("MESSAGE", 9, Object.class, Object.class, null);
        zzxx = zzheVar10;
        zzyb = new zzhe[]{zzheVar, zzheVar2, zzheVar3, zzheVar4, zzheVar5, zzheVar6, zzheVar7, zzheVar8, zzheVar9, zzheVar10};
    }

    private zzhe(String str, int i, Class cls, Class cls2, Object obj) {
        this.zzxy = cls;
        this.zzxz = cls2;
        this.zzya = obj;
    }

    public static zzhe[] values() {
        return (zzhe[]) zzyb.clone();
    }

    public final Class<?> zzgv() {
        return this.zzxz;
    }
}
