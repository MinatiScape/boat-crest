package com.google.android.gms.internal.measurement;
/* JADX WARN: Enum visitor error
jadx.core.utils.exceptions.JadxRuntimeException: Init of enum zza uses external variables
	at jadx.core.dex.visitors.EnumVisitor.createEnumFieldByConstructor(EnumVisitor.java:444)
	at jadx.core.dex.visitors.EnumVisitor.processEnumFieldByRegister(EnumVisitor.java:391)
	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromFilledArray(EnumVisitor.java:320)
	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromInsn(EnumVisitor.java:258)
	at jadx.core.dex.visitors.EnumVisitor.convertToEnum(EnumVisitor.java:151)
	at jadx.core.dex.visitors.EnumVisitor.visit(EnumVisitor.java:100)
 */
/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* loaded from: classes8.dex */
public final class zzjr {
    public static final zzjr zzA;
    public static final zzjr zzB;
    public static final zzjr zzC;
    public static final zzjr zzD;
    public static final zzjr zzE;
    public static final zzjr zzF;
    public static final zzjr zzG;
    public static final zzjr zzH;
    public static final zzjr zzI;
    public static final zzjr zzJ;
    public static final zzjr zzK;
    public static final zzjr zzL;
    public static final zzjr zzM;
    public static final zzjr zzN;
    public static final zzjr zzO;
    public static final zzjr zzP;
    public static final zzjr zzQ;
    public static final zzjr zzR;
    public static final zzjr zzS;
    public static final zzjr zzT;
    public static final zzjr zzU;
    public static final zzjr zzV;
    public static final zzjr zzW;
    public static final zzjr zzX;
    public static final zzjr zzY;
    private static final zzjr[] zzZ;
    public static final zzjr zza;
    private static final /* synthetic */ zzjr[] zzaa;
    public static final zzjr zzb;
    public static final zzjr zzc;
    public static final zzjr zzd;
    public static final zzjr zze;
    public static final zzjr zzf;
    public static final zzjr zzg;
    public static final zzjr zzh;
    public static final zzjr zzi;
    public static final zzjr zzj;
    public static final zzjr zzk;
    public static final zzjr zzl;
    public static final zzjr zzm;
    public static final zzjr zzn;
    public static final zzjr zzo;
    public static final zzjr zzp;
    public static final zzjr zzq;
    public static final zzjr zzr;
    public static final zzjr zzs;
    public static final zzjr zzt;
    public static final zzjr zzu;
    public static final zzjr zzv;
    public static final zzjr zzw;
    public static final zzjr zzx;
    public static final zzjr zzy;
    public static final zzjr zzz;
    private final zzkk zzab;
    private final int zzac;
    private final Class<?> zzad;

    static {
        zzkk zzkkVar = zzkk.zze;
        zzjr zzjrVar = new zzjr("DOUBLE", 0, 0, 1, zzkkVar);
        zza = zzjrVar;
        zzkk zzkkVar2 = zzkk.zzd;
        zzjr zzjrVar2 = new zzjr("FLOAT", 1, 1, 1, zzkkVar2);
        zzb = zzjrVar2;
        zzkk zzkkVar3 = zzkk.zzc;
        zzjr zzjrVar3 = new zzjr("INT64", 2, 2, 1, zzkkVar3);
        zzc = zzjrVar3;
        zzjr zzjrVar4 = new zzjr("UINT64", 3, 3, 1, zzkkVar3);
        zzd = zzjrVar4;
        zzkk zzkkVar4 = zzkk.zzb;
        zzjr zzjrVar5 = new zzjr("INT32", 4, 4, 1, zzkkVar4);
        zze = zzjrVar5;
        zzjr zzjrVar6 = new zzjr("FIXED64", 5, 5, 1, zzkkVar3);
        zzf = zzjrVar6;
        zzjr zzjrVar7 = new zzjr("FIXED32", 6, 6, 1, zzkkVar4);
        zzg = zzjrVar7;
        zzkk zzkkVar5 = zzkk.zzf;
        zzjr zzjrVar8 = new zzjr("BOOL", 7, 7, 1, zzkkVar5);
        zzh = zzjrVar8;
        zzkk zzkkVar6 = zzkk.zzg;
        zzjr zzjrVar9 = new zzjr("STRING", 8, 8, 1, zzkkVar6);
        zzi = zzjrVar9;
        zzkk zzkkVar7 = zzkk.zzj;
        zzjr zzjrVar10 = new zzjr("MESSAGE", 9, 9, 1, zzkkVar7);
        zzj = zzjrVar10;
        zzkk zzkkVar8 = zzkk.zzh;
        zzjr zzjrVar11 = new zzjr("BYTES", 10, 10, 1, zzkkVar8);
        zzk = zzjrVar11;
        zzjr zzjrVar12 = new zzjr("UINT32", 11, 11, 1, zzkkVar4);
        zzl = zzjrVar12;
        zzkk zzkkVar9 = zzkk.zzi;
        zzjr zzjrVar13 = new zzjr("ENUM", 12, 12, 1, zzkkVar9);
        zzm = zzjrVar13;
        zzjr zzjrVar14 = new zzjr("SFIXED32", 13, 13, 1, zzkkVar4);
        zzn = zzjrVar14;
        zzjr zzjrVar15 = new zzjr("SFIXED64", 14, 14, 1, zzkkVar3);
        zzo = zzjrVar15;
        zzjr zzjrVar16 = new zzjr("SINT32", 15, 15, 1, zzkkVar4);
        zzp = zzjrVar16;
        zzjr zzjrVar17 = new zzjr("SINT64", 16, 16, 1, zzkkVar3);
        zzq = zzjrVar17;
        zzjr zzjrVar18 = new zzjr("GROUP", 17, 17, 1, zzkkVar7);
        zzr = zzjrVar18;
        zzjr zzjrVar19 = new zzjr("DOUBLE_LIST", 18, 18, 2, zzkkVar);
        zzs = zzjrVar19;
        zzjr zzjrVar20 = new zzjr("FLOAT_LIST", 19, 19, 2, zzkkVar2);
        zzt = zzjrVar20;
        zzjr zzjrVar21 = new zzjr("INT64_LIST", 20, 20, 2, zzkkVar3);
        zzu = zzjrVar21;
        zzjr zzjrVar22 = new zzjr("UINT64_LIST", 21, 21, 2, zzkkVar3);
        zzv = zzjrVar22;
        zzjr zzjrVar23 = new zzjr("INT32_LIST", 22, 22, 2, zzkkVar4);
        zzw = zzjrVar23;
        zzjr zzjrVar24 = new zzjr("FIXED64_LIST", 23, 23, 2, zzkkVar3);
        zzx = zzjrVar24;
        zzjr zzjrVar25 = new zzjr("FIXED32_LIST", 24, 24, 2, zzkkVar4);
        zzy = zzjrVar25;
        zzjr zzjrVar26 = new zzjr("BOOL_LIST", 25, 25, 2, zzkkVar5);
        zzz = zzjrVar26;
        zzjr zzjrVar27 = new zzjr("STRING_LIST", 26, 26, 2, zzkkVar6);
        zzA = zzjrVar27;
        zzjr zzjrVar28 = new zzjr("MESSAGE_LIST", 27, 27, 2, zzkkVar7);
        zzB = zzjrVar28;
        zzjr zzjrVar29 = new zzjr("BYTES_LIST", 28, 28, 2, zzkkVar8);
        zzC = zzjrVar29;
        zzjr zzjrVar30 = new zzjr("UINT32_LIST", 29, 29, 2, zzkkVar4);
        zzD = zzjrVar30;
        zzjr zzjrVar31 = new zzjr("ENUM_LIST", 30, 30, 2, zzkkVar9);
        zzE = zzjrVar31;
        zzjr zzjrVar32 = new zzjr("SFIXED32_LIST", 31, 31, 2, zzkkVar4);
        zzF = zzjrVar32;
        zzjr zzjrVar33 = new zzjr("SFIXED64_LIST", 32, 32, 2, zzkkVar3);
        zzG = zzjrVar33;
        zzjr zzjrVar34 = new zzjr("SINT32_LIST", 33, 33, 2, zzkkVar4);
        zzH = zzjrVar34;
        zzjr zzjrVar35 = new zzjr("SINT64_LIST", 34, 34, 2, zzkkVar3);
        zzI = zzjrVar35;
        zzjr zzjrVar36 = new zzjr("DOUBLE_LIST_PACKED", 35, 35, 3, zzkkVar);
        zzJ = zzjrVar36;
        zzjr zzjrVar37 = new zzjr("FLOAT_LIST_PACKED", 36, 36, 3, zzkkVar2);
        zzK = zzjrVar37;
        zzjr zzjrVar38 = new zzjr("INT64_LIST_PACKED", 37, 37, 3, zzkkVar3);
        zzL = zzjrVar38;
        zzjr zzjrVar39 = new zzjr("UINT64_LIST_PACKED", 38, 38, 3, zzkkVar3);
        zzM = zzjrVar39;
        zzjr zzjrVar40 = new zzjr("INT32_LIST_PACKED", 39, 39, 3, zzkkVar4);
        zzN = zzjrVar40;
        zzjr zzjrVar41 = new zzjr("FIXED64_LIST_PACKED", 40, 40, 3, zzkkVar3);
        zzO = zzjrVar41;
        zzjr zzjrVar42 = new zzjr("FIXED32_LIST_PACKED", 41, 41, 3, zzkkVar4);
        zzP = zzjrVar42;
        zzjr zzjrVar43 = new zzjr("BOOL_LIST_PACKED", 42, 42, 3, zzkkVar5);
        zzQ = zzjrVar43;
        zzjr zzjrVar44 = new zzjr("UINT32_LIST_PACKED", 43, 43, 3, zzkkVar4);
        zzR = zzjrVar44;
        zzjr zzjrVar45 = new zzjr("ENUM_LIST_PACKED", 44, 44, 3, zzkkVar9);
        zzS = zzjrVar45;
        zzjr zzjrVar46 = new zzjr("SFIXED32_LIST_PACKED", 45, 45, 3, zzkkVar4);
        zzT = zzjrVar46;
        zzjr zzjrVar47 = new zzjr("SFIXED64_LIST_PACKED", 46, 46, 3, zzkkVar3);
        zzU = zzjrVar47;
        zzjr zzjrVar48 = new zzjr("SINT32_LIST_PACKED", 47, 47, 3, zzkkVar4);
        zzV = zzjrVar48;
        zzjr zzjrVar49 = new zzjr("SINT64_LIST_PACKED", 48, 48, 3, zzkkVar3);
        zzW = zzjrVar49;
        zzjr zzjrVar50 = new zzjr("GROUP_LIST", 49, 49, 2, zzkkVar7);
        zzX = zzjrVar50;
        zzjr zzjrVar51 = new zzjr("MAP", 50, 50, 4, zzkk.zza);
        zzY = zzjrVar51;
        zzaa = new zzjr[]{zzjrVar, zzjrVar2, zzjrVar3, zzjrVar4, zzjrVar5, zzjrVar6, zzjrVar7, zzjrVar8, zzjrVar9, zzjrVar10, zzjrVar11, zzjrVar12, zzjrVar13, zzjrVar14, zzjrVar15, zzjrVar16, zzjrVar17, zzjrVar18, zzjrVar19, zzjrVar20, zzjrVar21, zzjrVar22, zzjrVar23, zzjrVar24, zzjrVar25, zzjrVar26, zzjrVar27, zzjrVar28, zzjrVar29, zzjrVar30, zzjrVar31, zzjrVar32, zzjrVar33, zzjrVar34, zzjrVar35, zzjrVar36, zzjrVar37, zzjrVar38, zzjrVar39, zzjrVar40, zzjrVar41, zzjrVar42, zzjrVar43, zzjrVar44, zzjrVar45, zzjrVar46, zzjrVar47, zzjrVar48, zzjrVar49, zzjrVar50, zzjrVar51};
        zzjr[] values = values();
        zzZ = new zzjr[values.length];
        for (zzjr zzjrVar52 : values) {
            zzZ[zzjrVar52.zzac] = zzjrVar52;
        }
    }

    private zzjr(String str, int i, int i2, int i3, zzkk zzkkVar) {
        this.zzac = i2;
        this.zzab = zzkkVar;
        zzkk zzkkVar2 = zzkk.zza;
        int i4 = i3 - 1;
        if (i4 == 1) {
            this.zzad = zzkkVar.zza();
        } else if (i4 != 3) {
            this.zzad = null;
        } else {
            this.zzad = zzkkVar.zza();
        }
        if (i3 == 1) {
            zzkkVar.ordinal();
        }
    }

    public static zzjr[] values() {
        return (zzjr[]) zzaa.clone();
    }

    public final int zza() {
        return this.zzac;
    }
}
