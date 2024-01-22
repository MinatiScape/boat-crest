package com.google.android.gms.internal.vision;
/* JADX WARN: Enum visitor error
jadx.core.utils.exceptions.JadxRuntimeException: Init of enum zzabu uses external variables
	at jadx.core.dex.visitors.EnumVisitor.createEnumFieldByConstructor(EnumVisitor.java:444)
	at jadx.core.dex.visitors.EnumVisitor.processEnumFieldByRegister(EnumVisitor.java:391)
	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromFilledArray(EnumVisitor.java:320)
	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromInsn(EnumVisitor.java:258)
	at jadx.core.dex.visitors.EnumVisitor.convertToEnum(EnumVisitor.java:151)
	at jadx.core.dex.visitors.EnumVisitor.visit(EnumVisitor.java:100)
 */
/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* loaded from: classes10.dex */
public class zzka {
    public static final zzka zzabs;
    public static final zzka zzabt;
    public static final zzka zzabu;
    public static final zzka zzabv;
    public static final zzka zzabw;
    public static final zzka zzabx;
    public static final zzka zzaby;
    public static final zzka zzabz;
    public static final zzka zzaca;
    public static final zzka zzacb;
    public static final zzka zzacc;
    public static final zzka zzacd;
    public static final zzka zzace;
    public static final zzka zzacf;
    public static final zzka zzacg;
    public static final zzka zzach;
    public static final zzka zzaci;
    public static final zzka zzacj;
    private static final /* synthetic */ zzka[] zzacm;
    private final zzkd zzack;
    private final int zzacl;

    static {
        zzka zzkaVar = new zzka("DOUBLE", 0, zzkd.DOUBLE, 1);
        zzabs = zzkaVar;
        zzka zzkaVar2 = new zzka("FLOAT", 1, zzkd.FLOAT, 5);
        zzabt = zzkaVar2;
        zzkd zzkdVar = zzkd.LONG;
        zzka zzkaVar3 = new zzka("INT64", 2, zzkdVar, 0);
        zzabu = zzkaVar3;
        zzka zzkaVar4 = new zzka("UINT64", 3, zzkdVar, 0);
        zzabv = zzkaVar4;
        zzkd zzkdVar2 = zzkd.INT;
        zzka zzkaVar5 = new zzka("INT32", 4, zzkdVar2, 0);
        zzabw = zzkaVar5;
        zzka zzkaVar6 = new zzka("FIXED64", 5, zzkdVar, 1);
        zzabx = zzkaVar6;
        zzka zzkaVar7 = new zzka("FIXED32", 6, zzkdVar2, 5);
        zzaby = zzkaVar7;
        zzka zzkaVar8 = new zzka("BOOL", 7, zzkd.BOOLEAN, 0);
        zzabz = zzkaVar8;
        final zzkd zzkdVar3 = zzkd.STRING;
        zzka zzkaVar9 = new zzka("STRING", 8, zzkdVar3, 2) { // from class: com.google.android.gms.internal.vision.t4
        };
        zzaca = zzkaVar9;
        final zzkd zzkdVar4 = zzkd.MESSAGE;
        zzka zzkaVar10 = new zzka("GROUP", 9, zzkdVar4, 3) { // from class: com.google.android.gms.internal.vision.v4
        };
        zzacb = zzkaVar10;
        zzka zzkaVar11 = new zzka("MESSAGE", 10, zzkdVar4, 2) { // from class: com.google.android.gms.internal.vision.u4
        };
        zzacc = zzkaVar11;
        final zzkd zzkdVar5 = zzkd.BYTE_STRING;
        zzka zzkaVar12 = new zzka("BYTES", 11, zzkdVar5, 2) { // from class: com.google.android.gms.internal.vision.w4
        };
        zzacd = zzkaVar12;
        zzka zzkaVar13 = new zzka("UINT32", 12, zzkdVar2, 0);
        zzace = zzkaVar13;
        zzka zzkaVar14 = new zzka("ENUM", 13, zzkd.ENUM, 0);
        zzacf = zzkaVar14;
        zzka zzkaVar15 = new zzka("SFIXED32", 14, zzkdVar2, 5);
        zzacg = zzkaVar15;
        zzka zzkaVar16 = new zzka("SFIXED64", 15, zzkdVar, 1);
        zzach = zzkaVar16;
        zzka zzkaVar17 = new zzka("SINT32", 16, zzkdVar2, 0);
        zzaci = zzkaVar17;
        zzka zzkaVar18 = new zzka("SINT64", 17, zzkdVar, 0);
        zzacj = zzkaVar18;
        zzacm = new zzka[]{zzkaVar, zzkaVar2, zzkaVar3, zzkaVar4, zzkaVar5, zzkaVar6, zzkaVar7, zzkaVar8, zzkaVar9, zzkaVar10, zzkaVar11, zzkaVar12, zzkaVar13, zzkaVar14, zzkaVar15, zzkaVar16, zzkaVar17, zzkaVar18};
    }

    private zzka(String str, int i, zzkd zzkdVar, int i2) {
        this.zzack = zzkdVar;
        this.zzacl = i2;
    }

    public static zzka[] values() {
        return (zzka[]) zzacm.clone();
    }

    public final zzkd zzip() {
        return this.zzack;
    }

    public final int zziq() {
        return this.zzacl;
    }
}
