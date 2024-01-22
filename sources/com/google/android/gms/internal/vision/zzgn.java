package com.google.android.gms.internal.vision;

import java.lang.reflect.Type;
/* JADX WARN: Enum visitor error
jadx.core.utils.exceptions.JadxRuntimeException: Init of enum zztq uses external variables
	at jadx.core.dex.visitors.EnumVisitor.createEnumFieldByConstructor(EnumVisitor.java:444)
	at jadx.core.dex.visitors.EnumVisitor.processEnumFieldByRegister(EnumVisitor.java:391)
	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromFilledArray(EnumVisitor.java:320)
	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromInsn(EnumVisitor.java:258)
	at jadx.core.dex.visitors.EnumVisitor.convertToEnum(EnumVisitor.java:151)
	at jadx.core.dex.visitors.EnumVisitor.visit(EnumVisitor.java:100)
 */
/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* loaded from: classes10.dex */
public final class zzgn {
    private static final zzgn zztq;
    private static final zzgn zztr;
    private static final zzgn zzts;
    private static final zzgn zztt;
    private static final zzgn zztu;
    private static final zzgn zztv;
    private static final zzgn zztw;
    private static final zzgn zztx;
    private static final zzgn zzty;
    private static final zzgn zztz;
    private static final zzgn zzua;
    private static final zzgn zzub;
    private static final zzgn zzuc;
    private static final zzgn zzud;
    private static final zzgn zzue;
    private static final zzgn zzuf;
    private static final zzgn zzug;
    private static final zzgn zzuh;
    private static final zzgn zzui;
    private static final zzgn zzuj;
    private static final zzgn zzuk;
    private static final zzgn zzul;
    private static final zzgn zzum;
    private static final zzgn zzun;
    private static final zzgn zzuo;
    private static final zzgn zzup;
    private static final zzgn zzuq;
    private static final zzgn zzur;
    private static final zzgn zzus;
    private static final zzgn zzut;
    private static final zzgn zzuu;
    private static final zzgn zzuv;
    private static final zzgn zzuw;
    private static final zzgn zzux;
    private static final zzgn zzuy;
    public static final zzgn zzuz;
    private static final zzgn zzva;
    private static final zzgn zzvb;
    private static final zzgn zzvc;
    private static final zzgn zzvd;
    private static final zzgn zzve;
    private static final zzgn zzvf;
    private static final zzgn zzvg;
    private static final zzgn zzvh;
    private static final zzgn zzvi;
    private static final zzgn zzvj;
    private static final zzgn zzvk;
    private static final zzgn zzvl;
    public static final zzgn zzvm;
    private static final zzgn zzvn;
    private static final zzgn zzvo;
    private static final zzgn[] zzvt;
    private static final Type[] zzvu;
    private static final /* synthetic */ zzgn[] zzvv;
    private final int id;
    private final zzhe zzvp;
    private final j2 zzvq;
    private final Class<?> zzvr;
    private final boolean zzvs;

    static {
        j2 j2Var = j2.SCALAR;
        zzhe zzheVar = zzhe.zzxs;
        zzgn zzgnVar = new zzgn("DOUBLE", 0, 0, j2Var, zzheVar);
        zztq = zzgnVar;
        zzhe zzheVar2 = zzhe.zzxr;
        zzgn zzgnVar2 = new zzgn("FLOAT", 1, 1, j2Var, zzheVar2);
        zztr = zzgnVar2;
        zzhe zzheVar3 = zzhe.zzxq;
        zzgn zzgnVar3 = new zzgn("INT64", 2, 2, j2Var, zzheVar3);
        zzts = zzgnVar3;
        zzgn zzgnVar4 = new zzgn("UINT64", 3, 3, j2Var, zzheVar3);
        zztt = zzgnVar4;
        zzhe zzheVar4 = zzhe.zzxp;
        zzgn zzgnVar5 = new zzgn("INT32", 4, 4, j2Var, zzheVar4);
        zztu = zzgnVar5;
        zzgn zzgnVar6 = new zzgn("FIXED64", 5, 5, j2Var, zzheVar3);
        zztv = zzgnVar6;
        zzgn zzgnVar7 = new zzgn("FIXED32", 6, 6, j2Var, zzheVar4);
        zztw = zzgnVar7;
        zzhe zzheVar5 = zzhe.zzxt;
        zzgn zzgnVar8 = new zzgn("BOOL", 7, 7, j2Var, zzheVar5);
        zztx = zzgnVar8;
        zzhe zzheVar6 = zzhe.zzxu;
        zzgn zzgnVar9 = new zzgn("STRING", 8, 8, j2Var, zzheVar6);
        zzty = zzgnVar9;
        zzhe zzheVar7 = zzhe.zzxx;
        zzgn zzgnVar10 = new zzgn("MESSAGE", 9, 9, j2Var, zzheVar7);
        zztz = zzgnVar10;
        zzhe zzheVar8 = zzhe.zzxv;
        zzgn zzgnVar11 = new zzgn("BYTES", 10, 10, j2Var, zzheVar8);
        zzua = zzgnVar11;
        zzgn zzgnVar12 = new zzgn("UINT32", 11, 11, j2Var, zzheVar4);
        zzub = zzgnVar12;
        zzhe zzheVar9 = zzhe.zzxw;
        zzgn zzgnVar13 = new zzgn("ENUM", 12, 12, j2Var, zzheVar9);
        zzuc = zzgnVar13;
        zzgn zzgnVar14 = new zzgn("SFIXED32", 13, 13, j2Var, zzheVar4);
        zzud = zzgnVar14;
        zzgn zzgnVar15 = new zzgn("SFIXED64", 14, 14, j2Var, zzheVar3);
        zzue = zzgnVar15;
        zzgn zzgnVar16 = new zzgn("SINT32", 15, 15, j2Var, zzheVar4);
        zzuf = zzgnVar16;
        zzgn zzgnVar17 = new zzgn("SINT64", 16, 16, j2Var, zzheVar3);
        zzug = zzgnVar17;
        zzgn zzgnVar18 = new zzgn("GROUP", 17, 17, j2Var, zzheVar7);
        zzuh = zzgnVar18;
        j2 j2Var2 = j2.VECTOR;
        zzgn zzgnVar19 = new zzgn("DOUBLE_LIST", 18, 18, j2Var2, zzheVar);
        zzui = zzgnVar19;
        zzgn zzgnVar20 = new zzgn("FLOAT_LIST", 19, 19, j2Var2, zzheVar2);
        zzuj = zzgnVar20;
        zzgn zzgnVar21 = new zzgn("INT64_LIST", 20, 20, j2Var2, zzheVar3);
        zzuk = zzgnVar21;
        zzgn zzgnVar22 = new zzgn("UINT64_LIST", 21, 21, j2Var2, zzheVar3);
        zzul = zzgnVar22;
        zzgn zzgnVar23 = new zzgn("INT32_LIST", 22, 22, j2Var2, zzheVar4);
        zzum = zzgnVar23;
        zzgn zzgnVar24 = new zzgn("FIXED64_LIST", 23, 23, j2Var2, zzheVar3);
        zzun = zzgnVar24;
        zzgn zzgnVar25 = new zzgn("FIXED32_LIST", 24, 24, j2Var2, zzheVar4);
        zzuo = zzgnVar25;
        zzgn zzgnVar26 = new zzgn("BOOL_LIST", 25, 25, j2Var2, zzheVar5);
        zzup = zzgnVar26;
        zzgn zzgnVar27 = new zzgn("STRING_LIST", 26, 26, j2Var2, zzheVar6);
        zzuq = zzgnVar27;
        zzgn zzgnVar28 = new zzgn("MESSAGE_LIST", 27, 27, j2Var2, zzheVar7);
        zzur = zzgnVar28;
        zzgn zzgnVar29 = new zzgn("BYTES_LIST", 28, 28, j2Var2, zzheVar8);
        zzus = zzgnVar29;
        zzgn zzgnVar30 = new zzgn("UINT32_LIST", 29, 29, j2Var2, zzheVar4);
        zzut = zzgnVar30;
        zzgn zzgnVar31 = new zzgn("ENUM_LIST", 30, 30, j2Var2, zzheVar9);
        zzuu = zzgnVar31;
        zzgn zzgnVar32 = new zzgn("SFIXED32_LIST", 31, 31, j2Var2, zzheVar4);
        zzuv = zzgnVar32;
        zzgn zzgnVar33 = new zzgn("SFIXED64_LIST", 32, 32, j2Var2, zzheVar3);
        zzuw = zzgnVar33;
        zzgn zzgnVar34 = new zzgn("SINT32_LIST", 33, 33, j2Var2, zzheVar4);
        zzux = zzgnVar34;
        zzgn zzgnVar35 = new zzgn("SINT64_LIST", 34, 34, j2Var2, zzheVar3);
        zzuy = zzgnVar35;
        j2 j2Var3 = j2.PACKED_VECTOR;
        zzgn zzgnVar36 = new zzgn("DOUBLE_LIST_PACKED", 35, 35, j2Var3, zzheVar);
        zzuz = zzgnVar36;
        zzgn zzgnVar37 = new zzgn("FLOAT_LIST_PACKED", 36, 36, j2Var3, zzheVar2);
        zzva = zzgnVar37;
        zzgn zzgnVar38 = new zzgn("INT64_LIST_PACKED", 37, 37, j2Var3, zzheVar3);
        zzvb = zzgnVar38;
        zzgn zzgnVar39 = new zzgn("UINT64_LIST_PACKED", 38, 38, j2Var3, zzheVar3);
        zzvc = zzgnVar39;
        zzgn zzgnVar40 = new zzgn("INT32_LIST_PACKED", 39, 39, j2Var3, zzheVar4);
        zzvd = zzgnVar40;
        zzgn zzgnVar41 = new zzgn("FIXED64_LIST_PACKED", 40, 40, j2Var3, zzheVar3);
        zzve = zzgnVar41;
        zzgn zzgnVar42 = new zzgn("FIXED32_LIST_PACKED", 41, 41, j2Var3, zzheVar4);
        zzvf = zzgnVar42;
        zzgn zzgnVar43 = new zzgn("BOOL_LIST_PACKED", 42, 42, j2Var3, zzheVar5);
        zzvg = zzgnVar43;
        zzgn zzgnVar44 = new zzgn("UINT32_LIST_PACKED", 43, 43, j2Var3, zzheVar4);
        zzvh = zzgnVar44;
        zzgn zzgnVar45 = new zzgn("ENUM_LIST_PACKED", 44, 44, j2Var3, zzheVar9);
        zzvi = zzgnVar45;
        zzgn zzgnVar46 = new zzgn("SFIXED32_LIST_PACKED", 45, 45, j2Var3, zzheVar4);
        zzvj = zzgnVar46;
        zzgn zzgnVar47 = new zzgn("SFIXED64_LIST_PACKED", 46, 46, j2Var3, zzheVar3);
        zzvk = zzgnVar47;
        zzgn zzgnVar48 = new zzgn("SINT32_LIST_PACKED", 47, 47, j2Var3, zzheVar4);
        zzvl = zzgnVar48;
        zzgn zzgnVar49 = new zzgn("SINT64_LIST_PACKED", 48, 48, j2Var3, zzheVar3);
        zzvm = zzgnVar49;
        zzgn zzgnVar50 = new zzgn("GROUP_LIST", 49, 49, j2Var2, zzheVar7);
        zzvn = zzgnVar50;
        zzgn zzgnVar51 = new zzgn("MAP", 50, 50, j2.MAP, zzhe.zzxo);
        zzvo = zzgnVar51;
        zzvv = new zzgn[]{zzgnVar, zzgnVar2, zzgnVar3, zzgnVar4, zzgnVar5, zzgnVar6, zzgnVar7, zzgnVar8, zzgnVar9, zzgnVar10, zzgnVar11, zzgnVar12, zzgnVar13, zzgnVar14, zzgnVar15, zzgnVar16, zzgnVar17, zzgnVar18, zzgnVar19, zzgnVar20, zzgnVar21, zzgnVar22, zzgnVar23, zzgnVar24, zzgnVar25, zzgnVar26, zzgnVar27, zzgnVar28, zzgnVar29, zzgnVar30, zzgnVar31, zzgnVar32, zzgnVar33, zzgnVar34, zzgnVar35, zzgnVar36, zzgnVar37, zzgnVar38, zzgnVar39, zzgnVar40, zzgnVar41, zzgnVar42, zzgnVar43, zzgnVar44, zzgnVar45, zzgnVar46, zzgnVar47, zzgnVar48, zzgnVar49, zzgnVar50, zzgnVar51};
        zzvu = new Type[0];
        zzgn[] values = values();
        zzvt = new zzgn[values.length];
        for (zzgn zzgnVar52 : values) {
            zzvt[zzgnVar52.id] = zzgnVar52;
        }
    }

    private zzgn(String str, int i, int i2, j2 j2Var, zzhe zzheVar) {
        int i3;
        this.id = i2;
        this.zzvq = j2Var;
        this.zzvp = zzheVar;
        int i4 = h2.f9980a[j2Var.ordinal()];
        boolean z = true;
        if (i4 == 1) {
            this.zzvr = zzheVar.zzgv();
        } else if (i4 != 2) {
            this.zzvr = null;
        } else {
            this.zzvr = zzheVar.zzgv();
        }
        this.zzvs = (j2Var != j2.SCALAR || (i3 = h2.b[zzheVar.ordinal()]) == 1 || i3 == 2 || i3 == 3) ? false : z;
    }

    public static zzgn[] values() {
        return (zzgn[]) zzvv.clone();
    }

    public final int id() {
        return this.id;
    }
}
