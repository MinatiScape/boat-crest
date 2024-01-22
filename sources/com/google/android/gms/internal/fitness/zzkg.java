package com.google.android.gms.internal.fitness;
/* JADX WARN: Enum visitor error
jadx.core.utils.exceptions.JadxRuntimeException: Init of enum zzacx uses external variables
	at jadx.core.dex.visitors.EnumVisitor.createEnumFieldByConstructor(EnumVisitor.java:444)
	at jadx.core.dex.visitors.EnumVisitor.processEnumFieldByRegister(EnumVisitor.java:391)
	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromFilledArray(EnumVisitor.java:320)
	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromInsn(EnumVisitor.java:258)
	at jadx.core.dex.visitors.EnumVisitor.convertToEnum(EnumVisitor.java:151)
	at jadx.core.dex.visitors.EnumVisitor.visit(EnumVisitor.java:100)
 */
/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* loaded from: classes8.dex */
public class zzkg {
    public static final zzkg zzacv;
    public static final zzkg zzacw;
    public static final zzkg zzacx;
    public static final zzkg zzacy;
    public static final zzkg zzacz;
    public static final zzkg zzada;
    public static final zzkg zzadb;
    public static final zzkg zzadc;
    public static final zzkg zzadd;
    public static final zzkg zzade;
    public static final zzkg zzadf;
    public static final zzkg zzadg;
    public static final zzkg zzadh;
    public static final zzkg zzadi;
    public static final zzkg zzadj;
    public static final zzkg zzadk;
    public static final zzkg zzadl;
    public static final zzkg zzadm;
    private static final /* synthetic */ zzkg[] zzadp;
    private final zzkj zzadn;
    private final int zzado;

    static {
        zzkg zzkgVar = new zzkg("DOUBLE", 0, zzkj.DOUBLE, 1);
        zzacv = zzkgVar;
        zzkg zzkgVar2 = new zzkg("FLOAT", 1, zzkj.FLOAT, 5);
        zzacw = zzkgVar2;
        zzkj zzkjVar = zzkj.LONG;
        zzkg zzkgVar3 = new zzkg("INT64", 2, zzkjVar, 0);
        zzacx = zzkgVar3;
        zzkg zzkgVar4 = new zzkg("UINT64", 3, zzkjVar, 0);
        zzacy = zzkgVar4;
        zzkj zzkjVar2 = zzkj.INT;
        zzkg zzkgVar5 = new zzkg("INT32", 4, zzkjVar2, 0);
        zzacz = zzkgVar5;
        zzkg zzkgVar6 = new zzkg("FIXED64", 5, zzkjVar, 1);
        zzada = zzkgVar6;
        zzkg zzkgVar7 = new zzkg("FIXED32", 6, zzkjVar2, 5);
        zzadb = zzkgVar7;
        zzkg zzkgVar8 = new zzkg("BOOL", 7, zzkj.BOOLEAN, 0);
        zzadc = zzkgVar8;
        final zzkj zzkjVar3 = zzkj.STRING;
        zzkg zzkgVar9 = new zzkg("STRING", 8, zzkjVar3, 2) { // from class: com.google.android.gms.internal.fitness.z4
        };
        zzadd = zzkgVar9;
        final zzkj zzkjVar4 = zzkj.MESSAGE;
        zzkg zzkgVar10 = new zzkg("GROUP", 9, zzkjVar4, 3) { // from class: com.google.android.gms.internal.fitness.b5
        };
        zzade = zzkgVar10;
        zzkg zzkgVar11 = new zzkg("MESSAGE", 10, zzkjVar4, 2) { // from class: com.google.android.gms.internal.fitness.a5
        };
        zzadf = zzkgVar11;
        final zzkj zzkjVar5 = zzkj.BYTE_STRING;
        zzkg zzkgVar12 = new zzkg("BYTES", 11, zzkjVar5, 2) { // from class: com.google.android.gms.internal.fitness.c5
        };
        zzadg = zzkgVar12;
        zzkg zzkgVar13 = new zzkg("UINT32", 12, zzkjVar2, 0);
        zzadh = zzkgVar13;
        zzkg zzkgVar14 = new zzkg("ENUM", 13, zzkj.ENUM, 0);
        zzadi = zzkgVar14;
        zzkg zzkgVar15 = new zzkg("SFIXED32", 14, zzkjVar2, 5);
        zzadj = zzkgVar15;
        zzkg zzkgVar16 = new zzkg("SFIXED64", 15, zzkjVar, 1);
        zzadk = zzkgVar16;
        zzkg zzkgVar17 = new zzkg("SINT32", 16, zzkjVar2, 0);
        zzadl = zzkgVar17;
        zzkg zzkgVar18 = new zzkg("SINT64", 17, zzkjVar, 0);
        zzadm = zzkgVar18;
        zzadp = new zzkg[]{zzkgVar, zzkgVar2, zzkgVar3, zzkgVar4, zzkgVar5, zzkgVar6, zzkgVar7, zzkgVar8, zzkgVar9, zzkgVar10, zzkgVar11, zzkgVar12, zzkgVar13, zzkgVar14, zzkgVar15, zzkgVar16, zzkgVar17, zzkgVar18};
    }

    private zzkg(String str, int i, zzkj zzkjVar, int i2) {
        this.zzadn = zzkjVar;
        this.zzado = i2;
    }

    public static zzkg[] values() {
        return (zzkg[]) zzadp.clone();
    }

    public final zzkj zzdx() {
        return this.zzadn;
    }

    public final int zzdy() {
        return this.zzado;
    }
}
