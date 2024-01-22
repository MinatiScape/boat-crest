package com.google.android.gms.internal.firebase_ml;
/* JADX WARN: Enum visitor error
jadx.core.utils.exceptions.JadxRuntimeException: Init of enum zzcmm uses external variables
	at jadx.core.dex.visitors.EnumVisitor.createEnumFieldByConstructor(EnumVisitor.java:444)
	at jadx.core.dex.visitors.EnumVisitor.processEnumFieldByRegister(EnumVisitor.java:391)
	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromFilledArray(EnumVisitor.java:320)
	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromInsn(EnumVisitor.java:258)
	at jadx.core.dex.visitors.EnumVisitor.convertToEnum(EnumVisitor.java:151)
	at jadx.core.dex.visitors.EnumVisitor.visit(EnumVisitor.java:100)
 */
/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* loaded from: classes7.dex */
public final class zzxm {
    public static final zzxm zzcml;
    public static final zzxm zzcmm;
    public static final zzxm zzcmn;
    public static final zzxm zzcmo;
    public static final zzxm zzcmp;
    public static final zzxm zzcmq;
    public static final zzxm zzcmr;
    public static final zzxm zzcms;
    public static final zzxm zzcmt;
    public static final zzxm zzcmu;
    private static final /* synthetic */ zzxm[] zzcmy;
    private final Class<?> zzcmv;
    private final Class<?> zzcmw;
    private final Object zzcmx;

    static {
        zzxm zzxmVar = new zzxm("VOID", 0, Void.class, Void.class, null);
        zzcml = zzxmVar;
        Class cls = Integer.TYPE;
        zzxm zzxmVar2 = new zzxm("INT", 1, cls, Integer.class, 0);
        zzcmm = zzxmVar2;
        zzxm zzxmVar3 = new zzxm("LONG", 2, Long.TYPE, Long.class, 0L);
        zzcmn = zzxmVar3;
        zzxm zzxmVar4 = new zzxm("FLOAT", 3, Float.TYPE, Float.class, Float.valueOf(0.0f));
        zzcmo = zzxmVar4;
        zzxm zzxmVar5 = new zzxm("DOUBLE", 4, Double.TYPE, Double.class, Double.valueOf(0.0d));
        zzcmp = zzxmVar5;
        zzxm zzxmVar6 = new zzxm("BOOLEAN", 5, Boolean.TYPE, Boolean.class, Boolean.FALSE);
        zzcmq = zzxmVar6;
        zzxm zzxmVar7 = new zzxm("STRING", 6, String.class, String.class, "");
        zzcmr = zzxmVar7;
        zzxm zzxmVar8 = new zzxm("BYTE_STRING", 7, zzvv.class, zzvv.class, zzvv.zzchp);
        zzcms = zzxmVar8;
        zzxm zzxmVar9 = new zzxm("ENUM", 8, cls, Integer.class, null);
        zzcmt = zzxmVar9;
        zzxm zzxmVar10 = new zzxm("MESSAGE", 9, Object.class, Object.class, null);
        zzcmu = zzxmVar10;
        zzcmy = new zzxm[]{zzxmVar, zzxmVar2, zzxmVar3, zzxmVar4, zzxmVar5, zzxmVar6, zzxmVar7, zzxmVar8, zzxmVar9, zzxmVar10};
    }

    private zzxm(String str, int i, Class cls, Class cls2, Object obj) {
        this.zzcmv = cls;
        this.zzcmw = cls2;
        this.zzcmx = obj;
    }

    public static zzxm[] values() {
        return (zzxm[]) zzcmy.clone();
    }

    public final Class<?> zzvk() {
        return this.zzcmw;
    }
}
