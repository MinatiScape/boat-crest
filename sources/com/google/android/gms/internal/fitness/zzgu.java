package com.google.android.gms.internal.fitness;

import java.lang.reflect.Type;
/* JADX WARN: Enum visitor error
jadx.core.utils.exceptions.JadxRuntimeException: Init of enum zzvf uses external variables
	at jadx.core.dex.visitors.EnumVisitor.createEnumFieldByConstructor(EnumVisitor.java:444)
	at jadx.core.dex.visitors.EnumVisitor.processEnumFieldByRegister(EnumVisitor.java:391)
	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromFilledArray(EnumVisitor.java:320)
	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromInsn(EnumVisitor.java:258)
	at jadx.core.dex.visitors.EnumVisitor.convertToEnum(EnumVisitor.java:151)
	at jadx.core.dex.visitors.EnumVisitor.visit(EnumVisitor.java:100)
 */
/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* loaded from: classes8.dex */
public final class zzgu {
    private static final zzgu zzvf;
    private static final zzgu zzvg;
    private static final zzgu zzvh;
    private static final zzgu zzvi;
    private static final zzgu zzvj;
    private static final zzgu zzvk;
    private static final zzgu zzvl;
    private static final zzgu zzvm;
    private static final zzgu zzvn;
    private static final zzgu zzvo;
    private static final zzgu zzvp;
    private static final zzgu zzvq;
    private static final zzgu zzvr;
    private static final zzgu zzvs;
    private static final zzgu zzvt;
    private static final zzgu zzvu;
    private static final zzgu zzvv;
    private static final zzgu zzvw;
    private static final zzgu zzvx;
    private static final zzgu zzvy;
    private static final zzgu zzvz;
    private static final zzgu zzwa;
    private static final zzgu zzwb;
    private static final zzgu zzwc;
    private static final zzgu zzwd;
    private static final zzgu zzwe;
    private static final zzgu zzwf;
    private static final zzgu zzwg;
    private static final zzgu zzwh;
    private static final zzgu zzwi;
    private static final zzgu zzwj;
    private static final zzgu zzwk;
    private static final zzgu zzwl;
    private static final zzgu zzwm;
    private static final zzgu zzwn;
    public static final zzgu zzwo;
    private static final zzgu zzwp;
    private static final zzgu zzwq;
    private static final zzgu zzwr;
    private static final zzgu zzws;
    private static final zzgu zzwt;
    private static final zzgu zzwu;
    private static final zzgu zzwv;
    private static final zzgu zzww;
    private static final zzgu zzwx;
    private static final zzgu zzwy;
    private static final zzgu zzwz;
    private static final zzgu zzxa;
    public static final zzgu zzxb;
    private static final zzgu zzxc;
    private static final zzgu zzxd;
    private static final zzgu[] zzxi;
    private static final Type[] zzxj;
    private static final /* synthetic */ zzgu[] zzxk;
    private final int id;
    private final zzhm zzxe;
    private final r2 zzxf;
    private final Class<?> zzxg;
    private final boolean zzxh;

    static {
        r2 r2Var = r2.SCALAR;
        zzhm zzhmVar = zzhm.zzyt;
        zzgu zzguVar = new zzgu("DOUBLE", 0, 0, r2Var, zzhmVar);
        zzvf = zzguVar;
        zzhm zzhmVar2 = zzhm.zzys;
        zzgu zzguVar2 = new zzgu("FLOAT", 1, 1, r2Var, zzhmVar2);
        zzvg = zzguVar2;
        zzhm zzhmVar3 = zzhm.zzyr;
        zzgu zzguVar3 = new zzgu("INT64", 2, 2, r2Var, zzhmVar3);
        zzvh = zzguVar3;
        zzgu zzguVar4 = new zzgu("UINT64", 3, 3, r2Var, zzhmVar3);
        zzvi = zzguVar4;
        zzhm zzhmVar4 = zzhm.zzyq;
        zzgu zzguVar5 = new zzgu("INT32", 4, 4, r2Var, zzhmVar4);
        zzvj = zzguVar5;
        zzgu zzguVar6 = new zzgu("FIXED64", 5, 5, r2Var, zzhmVar3);
        zzvk = zzguVar6;
        zzgu zzguVar7 = new zzgu("FIXED32", 6, 6, r2Var, zzhmVar4);
        zzvl = zzguVar7;
        zzhm zzhmVar5 = zzhm.zzyu;
        zzgu zzguVar8 = new zzgu("BOOL", 7, 7, r2Var, zzhmVar5);
        zzvm = zzguVar8;
        zzhm zzhmVar6 = zzhm.zzyv;
        zzgu zzguVar9 = new zzgu("STRING", 8, 8, r2Var, zzhmVar6);
        zzvn = zzguVar9;
        zzhm zzhmVar7 = zzhm.zzyy;
        zzgu zzguVar10 = new zzgu("MESSAGE", 9, 9, r2Var, zzhmVar7);
        zzvo = zzguVar10;
        zzhm zzhmVar8 = zzhm.zzyw;
        zzgu zzguVar11 = new zzgu("BYTES", 10, 10, r2Var, zzhmVar8);
        zzvp = zzguVar11;
        zzgu zzguVar12 = new zzgu("UINT32", 11, 11, r2Var, zzhmVar4);
        zzvq = zzguVar12;
        zzhm zzhmVar9 = zzhm.zzyx;
        zzgu zzguVar13 = new zzgu("ENUM", 12, 12, r2Var, zzhmVar9);
        zzvr = zzguVar13;
        zzgu zzguVar14 = new zzgu("SFIXED32", 13, 13, r2Var, zzhmVar4);
        zzvs = zzguVar14;
        zzgu zzguVar15 = new zzgu("SFIXED64", 14, 14, r2Var, zzhmVar3);
        zzvt = zzguVar15;
        zzgu zzguVar16 = new zzgu("SINT32", 15, 15, r2Var, zzhmVar4);
        zzvu = zzguVar16;
        zzgu zzguVar17 = new zzgu("SINT64", 16, 16, r2Var, zzhmVar3);
        zzvv = zzguVar17;
        zzgu zzguVar18 = new zzgu("GROUP", 17, 17, r2Var, zzhmVar7);
        zzvw = zzguVar18;
        r2 r2Var2 = r2.VECTOR;
        zzgu zzguVar19 = new zzgu("DOUBLE_LIST", 18, 18, r2Var2, zzhmVar);
        zzvx = zzguVar19;
        zzgu zzguVar20 = new zzgu("FLOAT_LIST", 19, 19, r2Var2, zzhmVar2);
        zzvy = zzguVar20;
        zzgu zzguVar21 = new zzgu("INT64_LIST", 20, 20, r2Var2, zzhmVar3);
        zzvz = zzguVar21;
        zzgu zzguVar22 = new zzgu("UINT64_LIST", 21, 21, r2Var2, zzhmVar3);
        zzwa = zzguVar22;
        zzgu zzguVar23 = new zzgu("INT32_LIST", 22, 22, r2Var2, zzhmVar4);
        zzwb = zzguVar23;
        zzgu zzguVar24 = new zzgu("FIXED64_LIST", 23, 23, r2Var2, zzhmVar3);
        zzwc = zzguVar24;
        zzgu zzguVar25 = new zzgu("FIXED32_LIST", 24, 24, r2Var2, zzhmVar4);
        zzwd = zzguVar25;
        zzgu zzguVar26 = new zzgu("BOOL_LIST", 25, 25, r2Var2, zzhmVar5);
        zzwe = zzguVar26;
        zzgu zzguVar27 = new zzgu("STRING_LIST", 26, 26, r2Var2, zzhmVar6);
        zzwf = zzguVar27;
        zzgu zzguVar28 = new zzgu("MESSAGE_LIST", 27, 27, r2Var2, zzhmVar7);
        zzwg = zzguVar28;
        zzgu zzguVar29 = new zzgu("BYTES_LIST", 28, 28, r2Var2, zzhmVar8);
        zzwh = zzguVar29;
        zzgu zzguVar30 = new zzgu("UINT32_LIST", 29, 29, r2Var2, zzhmVar4);
        zzwi = zzguVar30;
        zzgu zzguVar31 = new zzgu("ENUM_LIST", 30, 30, r2Var2, zzhmVar9);
        zzwj = zzguVar31;
        zzgu zzguVar32 = new zzgu("SFIXED32_LIST", 31, 31, r2Var2, zzhmVar4);
        zzwk = zzguVar32;
        zzgu zzguVar33 = new zzgu("SFIXED64_LIST", 32, 32, r2Var2, zzhmVar3);
        zzwl = zzguVar33;
        zzgu zzguVar34 = new zzgu("SINT32_LIST", 33, 33, r2Var2, zzhmVar4);
        zzwm = zzguVar34;
        zzgu zzguVar35 = new zzgu("SINT64_LIST", 34, 34, r2Var2, zzhmVar3);
        zzwn = zzguVar35;
        r2 r2Var3 = r2.PACKED_VECTOR;
        zzgu zzguVar36 = new zzgu("DOUBLE_LIST_PACKED", 35, 35, r2Var3, zzhmVar);
        zzwo = zzguVar36;
        zzgu zzguVar37 = new zzgu("FLOAT_LIST_PACKED", 36, 36, r2Var3, zzhmVar2);
        zzwp = zzguVar37;
        zzgu zzguVar38 = new zzgu("INT64_LIST_PACKED", 37, 37, r2Var3, zzhmVar3);
        zzwq = zzguVar38;
        zzgu zzguVar39 = new zzgu("UINT64_LIST_PACKED", 38, 38, r2Var3, zzhmVar3);
        zzwr = zzguVar39;
        zzgu zzguVar40 = new zzgu("INT32_LIST_PACKED", 39, 39, r2Var3, zzhmVar4);
        zzws = zzguVar40;
        zzgu zzguVar41 = new zzgu("FIXED64_LIST_PACKED", 40, 40, r2Var3, zzhmVar3);
        zzwt = zzguVar41;
        zzgu zzguVar42 = new zzgu("FIXED32_LIST_PACKED", 41, 41, r2Var3, zzhmVar4);
        zzwu = zzguVar42;
        zzgu zzguVar43 = new zzgu("BOOL_LIST_PACKED", 42, 42, r2Var3, zzhmVar5);
        zzwv = zzguVar43;
        zzgu zzguVar44 = new zzgu("UINT32_LIST_PACKED", 43, 43, r2Var3, zzhmVar4);
        zzww = zzguVar44;
        zzgu zzguVar45 = new zzgu("ENUM_LIST_PACKED", 44, 44, r2Var3, zzhmVar9);
        zzwx = zzguVar45;
        zzgu zzguVar46 = new zzgu("SFIXED32_LIST_PACKED", 45, 45, r2Var3, zzhmVar4);
        zzwy = zzguVar46;
        zzgu zzguVar47 = new zzgu("SFIXED64_LIST_PACKED", 46, 46, r2Var3, zzhmVar3);
        zzwz = zzguVar47;
        zzgu zzguVar48 = new zzgu("SINT32_LIST_PACKED", 47, 47, r2Var3, zzhmVar4);
        zzxa = zzguVar48;
        zzgu zzguVar49 = new zzgu("SINT64_LIST_PACKED", 48, 48, r2Var3, zzhmVar3);
        zzxb = zzguVar49;
        zzgu zzguVar50 = new zzgu("GROUP_LIST", 49, 49, r2Var2, zzhmVar7);
        zzxc = zzguVar50;
        zzgu zzguVar51 = new zzgu("MAP", 50, 50, r2.MAP, zzhm.zzyp);
        zzxd = zzguVar51;
        zzxk = new zzgu[]{zzguVar, zzguVar2, zzguVar3, zzguVar4, zzguVar5, zzguVar6, zzguVar7, zzguVar8, zzguVar9, zzguVar10, zzguVar11, zzguVar12, zzguVar13, zzguVar14, zzguVar15, zzguVar16, zzguVar17, zzguVar18, zzguVar19, zzguVar20, zzguVar21, zzguVar22, zzguVar23, zzguVar24, zzguVar25, zzguVar26, zzguVar27, zzguVar28, zzguVar29, zzguVar30, zzguVar31, zzguVar32, zzguVar33, zzguVar34, zzguVar35, zzguVar36, zzguVar37, zzguVar38, zzguVar39, zzguVar40, zzguVar41, zzguVar42, zzguVar43, zzguVar44, zzguVar45, zzguVar46, zzguVar47, zzguVar48, zzguVar49, zzguVar50, zzguVar51};
        zzxj = new Type[0];
        zzgu[] values = values();
        zzxi = new zzgu[values.length];
        for (zzgu zzguVar52 : values) {
            zzxi[zzguVar52.id] = zzguVar52;
        }
    }

    private zzgu(String str, int i, int i2, r2 r2Var, zzhm zzhmVar) {
        int i3;
        this.id = i2;
        this.zzxf = r2Var;
        this.zzxe = zzhmVar;
        int i4 = s2.f8848a[r2Var.ordinal()];
        boolean z = true;
        if (i4 == 1) {
            this.zzxg = zzhmVar.zzcf();
        } else if (i4 != 2) {
            this.zzxg = null;
        } else {
            this.zzxg = zzhmVar.zzcf();
        }
        this.zzxh = (r2Var != r2.SCALAR || (i3 = s2.b[zzhmVar.ordinal()]) == 1 || i3 == 2 || i3 == 3) ? false : z;
    }

    public static zzgu[] values() {
        return (zzgu[]) zzxk.clone();
    }

    public final int id() {
        return this.id;
    }
}
