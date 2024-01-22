package com.google.android.gms.internal.firebase_ml;
/* JADX WARN: Enum visitor error
jadx.core.utils.exceptions.JadxRuntimeException: Init of enum zzcrg uses external variables
	at jadx.core.dex.visitors.EnumVisitor.createEnumFieldByConstructor(EnumVisitor.java:444)
	at jadx.core.dex.visitors.EnumVisitor.processEnumFieldByRegister(EnumVisitor.java:391)
	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromFilledArray(EnumVisitor.java:320)
	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromInsn(EnumVisitor.java:258)
	at jadx.core.dex.visitors.EnumVisitor.convertToEnum(EnumVisitor.java:151)
	at jadx.core.dex.visitors.EnumVisitor.visit(EnumVisitor.java:100)
 */
/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* loaded from: classes7.dex */
public class zzaan {
    public static final zzaan zzcre;
    public static final zzaan zzcrf;
    public static final zzaan zzcrg;
    public static final zzaan zzcrh;
    public static final zzaan zzcri;
    public static final zzaan zzcrj;
    public static final zzaan zzcrk;
    public static final zzaan zzcrl;
    public static final zzaan zzcrm;
    public static final zzaan zzcrn;
    public static final zzaan zzcro;
    public static final zzaan zzcrp;
    public static final zzaan zzcrq;
    public static final zzaan zzcrr;
    public static final zzaan zzcrs;
    public static final zzaan zzcrt;
    public static final zzaan zzcru;
    public static final zzaan zzcrv;
    private static final /* synthetic */ zzaan[] zzcry;
    private final zzaaq zzcrw;
    private final int zzcrx;

    static {
        zzaan zzaanVar = new zzaan("DOUBLE", 0, zzaaq.DOUBLE, 1);
        zzcre = zzaanVar;
        zzaan zzaanVar2 = new zzaan("FLOAT", 1, zzaaq.FLOAT, 5);
        zzcrf = zzaanVar2;
        zzaaq zzaaqVar = zzaaq.LONG;
        zzaan zzaanVar3 = new zzaan("INT64", 2, zzaaqVar, 0);
        zzcrg = zzaanVar3;
        zzaan zzaanVar4 = new zzaan("UINT64", 3, zzaaqVar, 0);
        zzcrh = zzaanVar4;
        zzaaq zzaaqVar2 = zzaaq.INT;
        zzaan zzaanVar5 = new zzaan("INT32", 4, zzaaqVar2, 0);
        zzcri = zzaanVar5;
        zzaan zzaanVar6 = new zzaan("FIXED64", 5, zzaaqVar, 1);
        zzcrj = zzaanVar6;
        zzaan zzaanVar7 = new zzaan("FIXED32", 6, zzaaqVar2, 5);
        zzcrk = zzaanVar7;
        zzaan zzaanVar8 = new zzaan("BOOL", 7, zzaaq.BOOLEAN, 0);
        zzcrl = zzaanVar8;
        final zzaaq zzaaqVar3 = zzaaq.STRING;
        zzaan zzaanVar9 = new zzaan("STRING", 8, zzaaqVar3, 2) { // from class: com.google.android.gms.internal.firebase_ml.l
        };
        zzcrm = zzaanVar9;
        final zzaaq zzaaqVar4 = zzaaq.MESSAGE;
        zzaan zzaanVar10 = new zzaan("GROUP", 9, zzaaqVar4, 3) { // from class: com.google.android.gms.internal.firebase_ml.n
        };
        zzcrn = zzaanVar10;
        zzaan zzaanVar11 = new zzaan("MESSAGE", 10, zzaaqVar4, 2) { // from class: com.google.android.gms.internal.firebase_ml.m
        };
        zzcro = zzaanVar11;
        final zzaaq zzaaqVar5 = zzaaq.BYTE_STRING;
        zzaan zzaanVar12 = new zzaan("BYTES", 11, zzaaqVar5, 2) { // from class: com.google.android.gms.internal.firebase_ml.o
        };
        zzcrp = zzaanVar12;
        zzaan zzaanVar13 = new zzaan("UINT32", 12, zzaaqVar2, 0);
        zzcrq = zzaanVar13;
        zzaan zzaanVar14 = new zzaan("ENUM", 13, zzaaq.ENUM, 0);
        zzcrr = zzaanVar14;
        zzaan zzaanVar15 = new zzaan("SFIXED32", 14, zzaaqVar2, 5);
        zzcrs = zzaanVar15;
        zzaan zzaanVar16 = new zzaan("SFIXED64", 15, zzaaqVar, 1);
        zzcrt = zzaanVar16;
        zzaan zzaanVar17 = new zzaan("SINT32", 16, zzaaqVar2, 0);
        zzcru = zzaanVar17;
        zzaan zzaanVar18 = new zzaan("SINT64", 17, zzaaqVar, 0);
        zzcrv = zzaanVar18;
        zzcry = new zzaan[]{zzaanVar, zzaanVar2, zzaanVar3, zzaanVar4, zzaanVar5, zzaanVar6, zzaanVar7, zzaanVar8, zzaanVar9, zzaanVar10, zzaanVar11, zzaanVar12, zzaanVar13, zzaanVar14, zzaanVar15, zzaanVar16, zzaanVar17, zzaanVar18};
    }

    private zzaan(String str, int i, zzaaq zzaaqVar, int i2) {
        this.zzcrw = zzaaqVar;
        this.zzcrx = i2;
    }

    public static zzaan[] values() {
        return (zzaan[]) zzcry.clone();
    }

    public final zzaaq zzxi() {
        return this.zzcrw;
    }

    public final int zzxj() {
        return this.zzcrx;
    }
}
