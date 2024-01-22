package org.bouncycastle.crypto.engines;

import com.coveiot.sdk.ble.api.BleUUID;
import com.crrepa.j.o;
import com.jieli.jl_rcsp.constant.Command;
import java.lang.reflect.Array;
import org.bouncycastle.crypto.BlockCipher;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.OutputLengthException;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.crypto.signers.PSSSigner;
import org.bouncycastle.crypto.tls.CipherSuite;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.Pack;
import org.jose4j.keys.AesKey;
/* loaded from: classes12.dex */
public class AESEngine implements BlockCipher {
    public static final byte[] i = {99, 124, 119, 123, com.crrepa.c.a.K1, 107, com.crrepa.c.a.Z0, -59, 48, 1, 103, 43, -2, -41, -85, com.htsmart.wristband2.a.a.a.R1, -54, -126, -55, com.crrepa.c.a.h1, -6, 89, 71, -16, BleUUID.CMD_ID_AD, -44, -94, BleUUID.CMD_ID_AF, -100, -92, 114, -64, BleUUID.CMD_ID_B7, -3, -109, 38, 54, 63, -9, -52, 52, -91, -27, com.crrepa.c.a.J1, 113, -40, 49, 21, 4, -57, 35, -61, 24, BleUUID.CMD_ID_96, 5, -102, 7, 18, Byte.MIN_VALUE, -30, -21, 39, -78, 117, 9, -125, 44, 26, 27, 110, 90, BleUUID.CMD_ID_A0, 82, 59, -42, BleUUID.CMD_ID_B3, 41, -29, 47, -124, 83, -47, 0, -19, 32, -4, -79, com.htsmart.wristband2.a.a.a.r1, 106, -53, -66, 57, com.htsmart.wristband2.a.a.a.Y0, com.htsmart.wristband2.a.a.a.a1, com.htsmart.wristband2.a.a.a.o1, -49, -48, -17, -86, -5, 67, 77, 51, -123, com.crrepa.c.a.E0, -7, 2, Byte.MAX_VALUE, com.htsmart.wristband2.a.a.a.d1, 60, -97, -88, 81, -93, 64, -113, -110, -99, 56, com.crrepa.c.a.N1, PSSSigner.TRAILER_IMPLICIT, BleUUID.CMD_ID_B6, -38, 33, 16, -1, com.crrepa.c.a.L1, -46, -51, 12, 19, -20, 95, BleUUID.CMD_ID_97, 68, 23, -60, -89, com.crrepa.c.a.l1, 61, 100, com.htsmart.wristband2.a.a.a.t1, 25, 115, 96, -127, 79, -36, 34, 42, BleUUID.CMD_ID_90, -120, com.htsmart.wristband2.a.a.a.U0, o.c, BleUUID.CMD_ID_B8, 20, -34, com.htsmart.wristband2.a.a.a.u1, 11, -37, -32, 50, 58, 10, 73, 6, 36, 92, -62, -45, -84, 98, -111, -107, -28, com.htsmart.wristband2.a.a.a.U1, -25, -56, 55, 109, -115, -43, com.htsmart.wristband2.a.a.a.c1, -87, 108, 86, com.crrepa.c.a.M1, com.crrepa.c.a.A, 101, com.htsmart.wristband2.a.a.a.V1, -82, 8, -70, 120, 37, 46, 28, -90, BleUUID.CMD_ID_B4, -58, -24, -35, 116, 31, 75, -67, -117, -118, com.htsmart.wristband2.a.a.a.J1, 62, BleUUID.CMD_ID_B5, 102, com.htsmart.wristband2.a.a.a.W0, 3, -10, 14, 97, 53, 87, -71, -122, -63, 29, -98, -31, -8, -104, 17, 105, -39, -114, -108, -101, 30, -121, -23, -50, 85, 40, -33, -116, -95, BleUUID.CMD_ID_89, 13, -65, -26, 66, 104, 65, -103, 45, 15, -80, 84, -69, 22};
    public static final byte[] j = {82, 9, 106, -43, 48, 54, -91, 56, -65, 64, -93, -98, -127, com.crrepa.c.a.L1, -41, -5, 124, -29, 57, -126, -101, 47, -1, -121, 52, -114, 67, 68, -60, -34, -23, -53, 84, 123, -108, 50, -90, -62, 35, 61, o.c, com.htsmart.wristband2.a.a.a.a1, -107, 11, 66, -6, -61, com.htsmart.wristband2.a.a.a.c1, 8, 46, -95, 102, 40, -39, 36, -78, com.htsmart.wristband2.a.a.a.R1, com.htsmart.wristband2.a.a.a.r1, -94, 73, 109, -117, -47, 37, 114, -8, -10, 100, -122, 104, -104, 22, -44, -92, 92, -52, com.htsmart.wristband2.a.a.a.t1, 101, BleUUID.CMD_ID_B6, -110, 108, com.htsmart.wristband2.a.a.a.J1, com.htsmart.wristband2.a.a.a.W0, com.htsmart.wristband2.a.a.a.d1, -3, -19, -71, -38, com.htsmart.wristband2.a.a.a.u1, 21, com.htsmart.wristband2.a.a.a.U0, 87, -89, -115, -99, -124, BleUUID.CMD_ID_90, -40, -85, 0, -116, PSSSigner.TRAILER_IMPLICIT, -45, 10, -9, -28, com.htsmart.wristband2.a.a.a.o1, 5, BleUUID.CMD_ID_B8, BleUUID.CMD_ID_B3, com.crrepa.c.a.E0, 6, -48, 44, 30, -113, -54, 63, 15, 2, -63, BleUUID.CMD_ID_AF, -67, 3, 1, 19, -118, 107, 58, -111, 17, 65, 79, 103, -36, com.crrepa.c.a.A, BleUUID.CMD_ID_97, com.crrepa.c.a.K1, -49, -50, -16, BleUUID.CMD_ID_B4, -26, 115, BleUUID.CMD_ID_96, -84, 116, 34, -25, BleUUID.CMD_ID_AD, 53, -123, -30, -7, 55, -24, 28, 117, -33, 110, 71, com.crrepa.c.a.J1, 26, 113, 29, 41, -59, BleUUID.CMD_ID_89, com.crrepa.c.a.Z0, BleUUID.CMD_ID_B7, 98, 14, -86, 24, -66, 27, -4, 86, 62, 75, -58, -46, com.htsmart.wristband2.a.a.a.U1, 32, -102, -37, -64, -2, 120, -51, 90, com.crrepa.c.a.M1, 31, -35, -88, 51, -120, 7, -57, 49, -79, 18, 16, 89, 39, Byte.MIN_VALUE, -20, 95, 96, 81, Byte.MAX_VALUE, -87, 25, BleUUID.CMD_ID_B5, com.htsmart.wristband2.a.a.a.Y0, 13, 45, -27, com.htsmart.wristband2.a.a.a.V1, -97, -109, -55, -100, -17, BleUUID.CMD_ID_A0, -32, 59, 77, -82, 42, com.crrepa.c.a.N1, -80, -56, -21, -69, 60, -125, 83, -103, 97, 23, 43, 4, com.crrepa.c.a.l1, -70, 119, -42, 38, -31, 105, 20, 99, 85, 33, 12, com.crrepa.c.a.h1};
    public static final int[] k = {1, 2, 4, 8, 16, 32, 64, 128, 27, 54, 108, Command.CMD_SET_DEVICE_STORAGE, 171, 77, 154, 47, 94, 188, 99, 198, 151, 53, 106, 212, 179, 125, 250, 239, CipherSuite.TLS_DH_anon_WITH_CAMELLIA_256_CBC_SHA256, 145};
    public static final int[] l = {-1520213050, -2072216328, -1720223762, -1921287178, 234025727, -1117033514, -1318096930, 1422247313, 1345335392, 50397442, -1452841010, 2099981142, 436141799, 1658312629, -424957107, -1703512340, 1170918031, -1652391393, 1086966153, -2021818886, 368769775, -346465870, -918075506, 200339707, -324162239, 1742001331, -39673249, -357585083, -1080255453, -140204973, -1770884380, 1539358875, -1028147339, 486407649, -1366060227, 1780885068, 1513502316, 1094664062, 49805301, 1338821763, 1546925160, -190470831, 887481809, 150073849, -1821281822, 1943591083, 1395732834, 1058346282, 201589768, 1388824469, 1696801606, 1589887901, 672667696, -1583966665, 251987210, -1248159185, 151455502, 907153956, -1686077413, 1038279391, 652995533, 1764173646, -843926913, -1619692054, 453576978, -1635548387, 1949051992, 773462580, 756751158, -1301385508, -296068428, -73359269, -162377052, 1295727478, 1641469623, -827083907, 2066295122, 1055122397, 1898917726, -1752923117, -179088474, 1758581177, 0, 753790401, 1612718144, 536673507, -927878791, -312779850, -1100322092, 1187761037, -641810841, 1262041458, -565556588, -733197160, -396863312, 1255133061, 1808847035, 720367557, -441800113, 385612781, -985447546, -682799718, 1429418854, -1803188975, -817543798, 284817897, 100794884, -2122350594, -263171936, 1144798328, -1163944155, -475486133, -212774494, -22830243, -1069531008, -1970303227, -1382903233, -1130521311, 1211644016, 83228145, -541279133, -1044990345, 1977277103, 1663115586, 806359072, 452984805, 250868733, 1842533055, 1288555905, 336333848, 890442534, 804056259, -513843266, -1567123659, -867941240, 957814574, 1472513171, -223893675, -2105639172, 1195195770, -1402706744, -413311558, 723065138, -1787595802, -1604296512, -1736343271, -783331426, 2145180835, 1713513028, 2116692564, -1416589253, -2088204277, -901364084, 703524551, -742868885, 1007948840, 2044649127, -497131844, 487262998, 1994120109, 1004593371, 1446130276, 1312438900, 503974420, -615954030, 168166924, 1814307912, -463709000, 1573044895, 1859376061, -273896381, -1503501628, -1466855111, -1533700815, 937747667, -1954973198, 854058965, 1137232011, 1496790894, -1217565222, -1936880383, 1691735473, -766620004, -525751991, -1267962664, -95005012, 133494003, 636152527, -1352309302, -1904575756, -374428089, 403179536, -709182865, -2005370640, 1864705354, 1915629148, 605822008, -240736681, -944458637, 1371981463, 602466507, 2094914977, -1670089496, 555687742, -582268010, -591544991, -2037675251, -2054518257, -1871679264, 1111375484, -994724495, -1436129588, -666351472, 84083462, 32962295, 302911004, -1553899070, 1597322602, -111716434, -793134743, -1853454825, 1489093017, 656219450, -1180787161, 954327513, 335083755, -1281845205, 856756514, -1150719534, 1893325225, -1987146233, -1483434957, -1231316179, 572399164, -1836611819, 552200649, 1238290055, -11184726, 2015897680, 2061492133, -1886614525, -123625127, -2138470135, 386731290, -624967835, 837215959, -968736124, -1201116976, -1019133566, -1332111063, 1999449434, 286199582, -877612933, -61582168, -692339859, 974525996};
    public static final int[] m = {1353184337, 1399144830, -1012656358, -1772214470, -882136261, -247096033, -1420232020, -1828461749, 1442459680, -160598355, -1854485368, 625738485, -52959921, -674551099, -2143013594, -1885117771, 1230680542, 1729870373, -1743852987, -507445667, 41234371, 317738113, -1550367091, -956705941, -413167869, -1784901099, -344298049, -631680363, 763608788, -752782248, 694804553, 1154009486, 1787413109, 2021232372, 1799248025, -579749593, -1236278850, 397248752, 1722556617, -1271214467, 407560035, -2110711067, 1613975959, 1165972322, -529046351, -2068943941, 480281086, -1809118983, 1483229296, 436028815, -2022908268, -1208452270, 601060267, -503166094, 1468997603, 715871590, 120122290, 63092015, -1703164538, -1526188077, -226023376, -1297760477, -1167457534, 1552029421, 723308426, -1833666137, -252573709, -1578997426, -839591323, -708967162, 526529745, -1963022652, -1655493068, -1604979806, 853641733, 1978398372, 971801355, -1427152832, 111112542, 1360031421, -108388034, 1023860118, -1375387939, 1186850381, -1249028975, 90031217, 1876166148, -15380384, 620468249, -1746289194, -868007799, 2006899047, -1119688528, -2004121337, 945494503, -605108103, 1191869601, -384875908, -920746760, 0, -2088337399, 1223502642, -1401941730, 1316117100, -67170563, 1446544655, 517320253, 658058550, 1691946762, 564550760, -783000677, 976107044, -1318647284, 266819475, -761860428, -1634624741, 1338359936, -1574904735, 1766553434, 370807324, 179999714, -450191168, 1138762300, 488053522, 185403662, -1379431438, -1180125651, -928440812, -2061897385, 1275557295, -1143105042, -44007517, -1624899081, -1124765092, -985962940, 880737115, 1982415755, -590994485, 1761406390, 1676797112, -891538985, 277177154, 1076008723, 538035844, 2099530373, -130171950, 288553390, 1839278535, 1261411869, -214912292, -330136051, -790380169, 1813426987, -1715900247, -95906799, 577038663, -997393240, 440397984, -668172970, -275762398, -951170681, -1043253031, -22885748, 906744984, -813566554, 685669029, 646887386, -1530942145, -459458004, 227702864, -1681105046, 1648787028, -1038905866, -390539120, 1593260334, -173030526, -1098883681, 2090061929, -1456614033, -1290656305, 999926984, -1484974064, 1852021992, 2075868123, 158869197, -199730834, 28809964, -1466282109, 1701746150, 2129067946, 147831841, -420997649, -644094022, -835293366, -737566742, -696471511, -1347247055, 824393514, 815048134, -1067015627, 935087732, -1496677636, -1328508704, 366520115, 1251476721, -136647615, 240176511, 804688151, -1915335306, 1303441219, 1414376140, -553347356, -474623586, 461924940, -1205916479, 2136040774, 82468509, 1563790337, 1937016826, 776014843, 1511876531, 1389550482, 861278441, 323475053, -1939744870, 2047648055, -1911228327, -1992551445, -299390514, 902390199, -303751967, 1018251130, 1507840668, 1064563285, 2043548696, -1086863501, -355600557, 1537932639, 342834655, -2032450440, -2114736182, 1053059257, 741614648, 1598071746, 1925389590, 203809468, -1958134744, 1100287487, 1895934009, -558691320, -1662733096, -1866377628, 1636092795, 1890988757, 1952214088, 1113045200};

    /* renamed from: a  reason: collision with root package name */
    public int f14663a;
    public int[][] b = null;
    public int c;
    public int d;
    public int e;
    public int f;
    public boolean g;
    public byte[] h;

    public static int a(int i2) {
        return (((i2 & (-2139062144)) >>> 7) * 27) ^ ((2139062143 & i2) << 1);
    }

    public static int b(int i2) {
        int i3 = i2 & (-1061109568);
        int i4 = i3 ^ (i3 >>> 1);
        return (i4 >>> 5) ^ (((1061109567 & i2) << 2) ^ (i4 >>> 2));
    }

    public static int f(int i2) {
        int h = h(i2, 8) ^ i2;
        int a2 = i2 ^ a(h);
        int b = h ^ b(a2);
        return a2 ^ (b ^ h(b, 16));
    }

    public static int h(int i2, int i3) {
        return (i2 << (-i3)) | (i2 >>> i3);
    }

    public static int i(int i2) {
        byte[] bArr = i;
        return (bArr[(i2 >> 24) & 255] << 24) | (bArr[i2 & 255] & 255) | ((bArr[(i2 >> 8) & 255] & 255) << 8) | ((bArr[(i2 >> 16) & 255] & 255) << 16);
    }

    public final void c(int[][] iArr) {
        int i2 = this.c;
        int i3 = this.f14663a;
        char c = 0;
        int i4 = i2 ^ iArr[i3][0];
        int i5 = 1;
        int i6 = this.d ^ iArr[i3][1];
        int i7 = this.e ^ iArr[i3][2];
        int i8 = i3 - 1;
        int i9 = iArr[i3][3] ^ this.f;
        while (i8 > i5) {
            int[] iArr2 = m;
            int h = (((iArr2[i4 & 255] ^ h(iArr2[(i9 >> 8) & 255], 24)) ^ h(iArr2[(i7 >> 16) & 255], 16)) ^ h(iArr2[(i6 >> 24) & 255], 8)) ^ iArr[i8][c];
            int h2 = (((h(iArr2[(i4 >> 8) & 255], 24) ^ iArr2[i6 & 255]) ^ h(iArr2[(i9 >> 16) & 255], 16)) ^ h(iArr2[(i7 >> 24) & 255], 8)) ^ iArr[i8][i5];
            int h3 = (((h(iArr2[(i6 >> 8) & 255], 24) ^ iArr2[i7 & 255]) ^ h(iArr2[(i4 >> 16) & 255], 16)) ^ h(iArr2[(i9 >> 24) & 255], 8)) ^ iArr[i8][2];
            int h4 = h(iArr2[(i4 >> 24) & 255], 8) ^ ((iArr2[i9 & 255] ^ h(iArr2[(i7 >> 8) & 255], 24)) ^ h(iArr2[(i6 >> 16) & 255], 16));
            int i10 = i8 - 1;
            int i11 = h4 ^ iArr[i8][3];
            int h5 = (((iArr2[h & 255] ^ h(iArr2[(i11 >> 8) & 255], 24)) ^ h(iArr2[(h3 >> 16) & 255], 16)) ^ h(iArr2[(h2 >> 24) & 255], 8)) ^ iArr[i10][0];
            int h6 = (((iArr2[h2 & 255] ^ h(iArr2[(h >> 8) & 255], 24)) ^ h(iArr2[(i11 >> 16) & 255], 16)) ^ h(iArr2[(h3 >> 24) & 255], 8)) ^ iArr[i10][1];
            int h7 = ((iArr2[i11 & 255] ^ h(iArr2[(h3 >> 8) & 255], 24)) ^ h(iArr2[(h2 >> 16) & 255], 16)) ^ h(iArr2[(h >> 24) & 255], 8);
            int i12 = i10 - 1;
            i9 = iArr[i10][3] ^ h7;
            i4 = h5;
            i6 = h6;
            i7 = (((iArr2[h3 & 255] ^ h(iArr2[(h2 >> 8) & 255], 24)) ^ h(iArr2[(h >> 16) & 255], 16)) ^ h(iArr2[(i11 >> 24) & 255], 8)) ^ iArr[i10][2];
            i5 = 1;
            i8 = i12;
            c = 0;
        }
        int[] iArr3 = m;
        int h8 = (((iArr3[i4 & 255] ^ h(iArr3[(i9 >> 8) & 255], 24)) ^ h(iArr3[(i7 >> 16) & 255], 16)) ^ h(iArr3[(i6 >> 24) & 255], 8)) ^ iArr[i8][0];
        int h9 = (((iArr3[i6 & 255] ^ h(iArr3[(i4 >> 8) & 255], 24)) ^ h(iArr3[(i9 >> 16) & 255], 16)) ^ h(iArr3[(i7 >> 24) & 255], 8)) ^ iArr[i8][1];
        int h10 = (((iArr3[i7 & 255] ^ h(iArr3[(i6 >> 8) & 255], 24)) ^ h(iArr3[(i4 >> 16) & 255], 16)) ^ h(iArr3[(i9 >> 24) & 255], 8)) ^ iArr[i8][2];
        int h11 = (h(iArr3[(i4 >> 24) & 255], 8) ^ ((iArr3[i9 & 255] ^ h(iArr3[(i7 >> 8) & 255], 24)) ^ h(iArr3[(i6 >> 16) & 255], 16))) ^ iArr[i8][3];
        byte[] bArr = j;
        byte[] bArr2 = this.h;
        this.c = ((((bArr[h8 & 255] & 255) ^ ((bArr2[(h11 >> 8) & 255] & 255) << 8)) ^ ((bArr2[(h10 >> 16) & 255] & 255) << 16)) ^ (bArr[(h9 >> 24) & 255] << 24)) ^ iArr[0][0];
        this.d = ((((bArr2[h9 & 255] & 255) ^ ((bArr2[(h8 >> 8) & 255] & 255) << 8)) ^ ((bArr[(h11 >> 16) & 255] & 255) << 16)) ^ (bArr2[(h10 >> 24) & 255] << 24)) ^ iArr[0][1];
        this.e = ((((bArr2[h10 & 255] & 255) ^ ((bArr[(h9 >> 8) & 255] & 255) << 8)) ^ ((bArr[(h8 >> 16) & 255] & 255) << 16)) ^ (bArr2[(h11 >> 24) & 255] << 24)) ^ iArr[0][2];
        this.f = ((((bArr[h11 & 255] & 255) ^ ((bArr2[(h10 >> 8) & 255] & 255) << 8)) ^ ((bArr2[(h9 >> 16) & 255] & 255) << 16)) ^ (bArr2[(h8 >> 24) & 255] << 24)) ^ iArr[0][3];
    }

    public final void d(int[][] iArr) {
        char c = 0;
        int i2 = this.c ^ iArr[0][0];
        int i3 = 1;
        int i4 = this.d ^ iArr[0][1];
        int i5 = this.e ^ iArr[0][2];
        int i6 = this.f ^ iArr[0][3];
        int i7 = 1;
        while (i7 < this.f14663a - i3) {
            int[] iArr2 = l;
            int h = (((iArr2[i2 & 255] ^ h(iArr2[(i4 >> 8) & 255], 24)) ^ h(iArr2[(i5 >> 16) & 255], 16)) ^ h(iArr2[(i6 >> 24) & 255], 8)) ^ iArr[i7][c];
            int h2 = (((h(iArr2[(i5 >> 8) & 255], 24) ^ iArr2[i4 & 255]) ^ h(iArr2[(i6 >> 16) & 255], 16)) ^ h(iArr2[(i2 >> 24) & 255], 8)) ^ iArr[i7][i3];
            int h3 = (((h(iArr2[(i6 >> 8) & 255], 24) ^ iArr2[i5 & 255]) ^ h(iArr2[(i2 >> 16) & 255], 16)) ^ h(iArr2[(i4 >> 24) & 255], 8)) ^ iArr[i7][2];
            int h4 = ((h(iArr2[(i2 >> 8) & 255], 24) ^ iArr2[i6 & 255]) ^ h(iArr2[(i4 >> 16) & 255], 16)) ^ h(iArr2[(i5 >> 24) & 255], 8);
            int i8 = i7 + 1;
            int i9 = h4 ^ iArr[i7][3];
            int h5 = (((iArr2[h & 255] ^ h(iArr2[(h2 >> 8) & 255], 24)) ^ h(iArr2[(h3 >> 16) & 255], 16)) ^ h(iArr2[(i9 >> 24) & 255], 8)) ^ iArr[i8][0];
            int h6 = (((iArr2[h2 & 255] ^ h(iArr2[(h3 >> 8) & 255], 24)) ^ h(iArr2[(i9 >> 16) & 255], 16)) ^ h(iArr2[(h >> 24) & 255], 8)) ^ iArr[i8][1];
            int h7 = ((iArr2[i9 & 255] ^ h(iArr2[(h >> 8) & 255], 24)) ^ h(iArr2[(h2 >> 16) & 255], 16)) ^ h(iArr2[(h3 >> 24) & 255], 8);
            int i10 = i8 + 1;
            int i11 = h7 ^ iArr[i8][3];
            i4 = h6;
            i3 = 1;
            i6 = i11;
            i2 = h5;
            i5 = (((iArr2[h3 & 255] ^ h(iArr2[(i9 >> 8) & 255], 24)) ^ h(iArr2[(h >> 16) & 255], 16)) ^ h(iArr2[(h2 >> 24) & 255], 8)) ^ iArr[i8][2];
            i7 = i10;
            c = 0;
        }
        int[] iArr3 = l;
        int h8 = (((iArr3[i2 & 255] ^ h(iArr3[(i4 >> 8) & 255], 24)) ^ h(iArr3[(i5 >> 16) & 255], 16)) ^ h(iArr3[(i6 >> 24) & 255], 8)) ^ iArr[i7][0];
        int h9 = (((iArr3[i4 & 255] ^ h(iArr3[(i5 >> 8) & 255], 24)) ^ h(iArr3[(i6 >> 16) & 255], 16)) ^ h(iArr3[(i2 >> 24) & 255], 8)) ^ iArr[i7][1];
        int h10 = (((iArr3[i5 & 255] ^ h(iArr3[(i6 >> 8) & 255], 24)) ^ h(iArr3[(i2 >> 16) & 255], 16)) ^ h(iArr3[(i4 >> 24) & 255], 8)) ^ iArr[i7][2];
        int h11 = ((h(iArr3[(i2 >> 8) & 255], 24) ^ iArr3[i6 & 255]) ^ h(iArr3[(i4 >> 16) & 255], 16)) ^ h(iArr3[(i5 >> 24) & 255], 8);
        int i12 = i7 + 1;
        int i13 = h11 ^ iArr[i7][3];
        byte[] bArr = i;
        int i14 = (bArr[h8 & 255] & 255) ^ ((bArr[(h9 >> 8) & 255] & 255) << 8);
        byte[] bArr2 = this.h;
        this.c = ((i14 ^ ((bArr2[(h10 >> 16) & 255] & 255) << 16)) ^ (bArr2[(i13 >> 24) & 255] << 24)) ^ iArr[i12][0];
        this.d = ((((bArr2[h9 & 255] & 255) ^ ((bArr[(h10 >> 8) & 255] & 255) << 8)) ^ ((bArr[(i13 >> 16) & 255] & 255) << 16)) ^ (bArr2[(h8 >> 24) & 255] << 24)) ^ iArr[i12][1];
        this.e = ((((bArr2[h10 & 255] & 255) ^ ((bArr[(i13 >> 8) & 255] & 255) << 8)) ^ ((bArr[(h8 >> 16) & 255] & 255) << 16)) ^ (bArr[(h9 >> 24) & 255] << 24)) ^ iArr[i12][2];
        this.f = ((((bArr2[i13 & 255] & 255) ^ ((bArr2[(h8 >> 8) & 255] & 255) << 8)) ^ ((bArr2[(h9 >> 16) & 255] & 255) << 16)) ^ (bArr[(h10 >> 24) & 255] << 24)) ^ iArr[i12][3];
    }

    public final int[][] e(byte[] bArr, boolean z) {
        int length = bArr.length;
        if (length < 16 || length > 32 || (length & 7) != 0) {
            throw new IllegalArgumentException("Key length not 128/192/256 bits.");
        }
        int i2 = length >>> 2;
        int i3 = i2 + 6;
        this.f14663a = i3;
        int[][] iArr = (int[][]) Array.newInstance(int.class, i3 + 1, 4);
        if (i2 == 4) {
            int littleEndianToInt = Pack.littleEndianToInt(bArr, 0);
            iArr[0][0] = littleEndianToInt;
            int littleEndianToInt2 = Pack.littleEndianToInt(bArr, 4);
            iArr[0][1] = littleEndianToInt2;
            int littleEndianToInt3 = Pack.littleEndianToInt(bArr, 8);
            iArr[0][2] = littleEndianToInt3;
            int littleEndianToInt4 = Pack.littleEndianToInt(bArr, 12);
            iArr[0][3] = littleEndianToInt4;
            for (int i4 = 1; i4 <= 10; i4++) {
                littleEndianToInt ^= i(h(littleEndianToInt4, 8)) ^ k[i4 - 1];
                iArr[i4][0] = littleEndianToInt;
                littleEndianToInt2 ^= littleEndianToInt;
                iArr[i4][1] = littleEndianToInt2;
                littleEndianToInt3 ^= littleEndianToInt2;
                iArr[i4][2] = littleEndianToInt3;
                littleEndianToInt4 ^= littleEndianToInt3;
                iArr[i4][3] = littleEndianToInt4;
            }
        } else if (i2 == 6) {
            int littleEndianToInt5 = Pack.littleEndianToInt(bArr, 0);
            iArr[0][0] = littleEndianToInt5;
            int littleEndianToInt6 = Pack.littleEndianToInt(bArr, 4);
            iArr[0][1] = littleEndianToInt6;
            int littleEndianToInt7 = Pack.littleEndianToInt(bArr, 8);
            iArr[0][2] = littleEndianToInt7;
            int littleEndianToInt8 = Pack.littleEndianToInt(bArr, 12);
            iArr[0][3] = littleEndianToInt8;
            int littleEndianToInt9 = Pack.littleEndianToInt(bArr, 16);
            iArr[1][0] = littleEndianToInt9;
            int littleEndianToInt10 = Pack.littleEndianToInt(bArr, 20);
            iArr[1][1] = littleEndianToInt10;
            int i5 = littleEndianToInt5 ^ (i(h(littleEndianToInt10, 8)) ^ 1);
            iArr[1][2] = i5;
            int i6 = littleEndianToInt6 ^ i5;
            iArr[1][3] = i6;
            int i7 = littleEndianToInt7 ^ i6;
            iArr[2][0] = i7;
            int i8 = littleEndianToInt8 ^ i7;
            iArr[2][1] = i8;
            int i9 = littleEndianToInt9 ^ i8;
            iArr[2][2] = i9;
            int i10 = littleEndianToInt10 ^ i9;
            iArr[2][3] = i10;
            int i11 = 2;
            for (int i12 = 3; i12 < 12; i12 += 3) {
                int i13 = i(h(i10, 8)) ^ i11;
                int i14 = i11 << 1;
                int i15 = i5 ^ i13;
                iArr[i12][0] = i15;
                int i16 = i6 ^ i15;
                iArr[i12][1] = i16;
                int i17 = i7 ^ i16;
                iArr[i12][2] = i17;
                int i18 = i8 ^ i17;
                iArr[i12][3] = i18;
                int i19 = i9 ^ i18;
                int i20 = i12 + 1;
                iArr[i20][0] = i19;
                int i21 = i10 ^ i19;
                iArr[i20][1] = i21;
                int i22 = i(h(i21, 8)) ^ i14;
                i11 = i14 << 1;
                i5 = i15 ^ i22;
                iArr[i20][2] = i5;
                i6 = i16 ^ i5;
                iArr[i20][3] = i6;
                i7 = i17 ^ i6;
                int i23 = i12 + 2;
                iArr[i23][0] = i7;
                i8 = i18 ^ i7;
                iArr[i23][1] = i8;
                i9 = i19 ^ i8;
                iArr[i23][2] = i9;
                i10 = i21 ^ i9;
                iArr[i23][3] = i10;
            }
            int i24 = (i(h(i10, 8)) ^ i11) ^ i5;
            iArr[12][0] = i24;
            int i25 = i24 ^ i6;
            iArr[12][1] = i25;
            int i26 = i25 ^ i7;
            iArr[12][2] = i26;
            iArr[12][3] = i26 ^ i8;
        } else if (i2 != 8) {
            throw new IllegalStateException("Should never get here");
        } else {
            int littleEndianToInt11 = Pack.littleEndianToInt(bArr, 0);
            iArr[0][0] = littleEndianToInt11;
            int littleEndianToInt12 = Pack.littleEndianToInt(bArr, 4);
            iArr[0][1] = littleEndianToInt12;
            int littleEndianToInt13 = Pack.littleEndianToInt(bArr, 8);
            iArr[0][2] = littleEndianToInt13;
            int littleEndianToInt14 = Pack.littleEndianToInt(bArr, 12);
            iArr[0][3] = littleEndianToInt14;
            int littleEndianToInt15 = Pack.littleEndianToInt(bArr, 16);
            iArr[1][0] = littleEndianToInt15;
            int littleEndianToInt16 = Pack.littleEndianToInt(bArr, 20);
            iArr[1][1] = littleEndianToInt16;
            int littleEndianToInt17 = Pack.littleEndianToInt(bArr, 24);
            iArr[1][2] = littleEndianToInt17;
            int littleEndianToInt18 = Pack.littleEndianToInt(bArr, 28);
            iArr[1][3] = littleEndianToInt18;
            int i27 = 1;
            for (int i28 = 2; i28 < 14; i28 += 2) {
                int i29 = i(h(littleEndianToInt18, 8)) ^ i27;
                i27 <<= 1;
                littleEndianToInt11 ^= i29;
                iArr[i28][0] = littleEndianToInt11;
                littleEndianToInt12 ^= littleEndianToInt11;
                iArr[i28][1] = littleEndianToInt12;
                littleEndianToInt13 ^= littleEndianToInt12;
                iArr[i28][2] = littleEndianToInt13;
                littleEndianToInt14 ^= littleEndianToInt13;
                iArr[i28][3] = littleEndianToInt14;
                littleEndianToInt15 ^= i(littleEndianToInt14);
                int i30 = i28 + 1;
                iArr[i30][0] = littleEndianToInt15;
                littleEndianToInt16 ^= littleEndianToInt15;
                iArr[i30][1] = littleEndianToInt16;
                littleEndianToInt17 ^= littleEndianToInt16;
                iArr[i30][2] = littleEndianToInt17;
                littleEndianToInt18 ^= littleEndianToInt17;
                iArr[i30][3] = littleEndianToInt18;
            }
            int i31 = (i(h(littleEndianToInt18, 8)) ^ i27) ^ littleEndianToInt11;
            iArr[14][0] = i31;
            int i32 = i31 ^ littleEndianToInt12;
            iArr[14][1] = i32;
            int i33 = i32 ^ littleEndianToInt13;
            iArr[14][2] = i33;
            iArr[14][3] = i33 ^ littleEndianToInt14;
        }
        if (!z) {
            for (int i34 = 1; i34 < this.f14663a; i34++) {
                for (int i35 = 0; i35 < 4; i35++) {
                    iArr[i34][i35] = f(iArr[i34][i35]);
                }
            }
        }
        return iArr;
    }

    public final void g(byte[] bArr, int i2) {
        int i3 = i2 + 1;
        int i4 = this.c;
        bArr[i2] = (byte) i4;
        int i5 = i3 + 1;
        bArr[i3] = (byte) (i4 >> 8);
        int i6 = i5 + 1;
        bArr[i5] = (byte) (i4 >> 16);
        int i7 = i6 + 1;
        bArr[i6] = (byte) (i4 >> 24);
        int i8 = i7 + 1;
        int i9 = this.d;
        bArr[i7] = (byte) i9;
        int i10 = i8 + 1;
        bArr[i8] = (byte) (i9 >> 8);
        int i11 = i10 + 1;
        bArr[i10] = (byte) (i9 >> 16);
        int i12 = i11 + 1;
        bArr[i11] = (byte) (i9 >> 24);
        int i13 = i12 + 1;
        int i14 = this.e;
        bArr[i12] = (byte) i14;
        int i15 = i13 + 1;
        bArr[i13] = (byte) (i14 >> 8);
        int i16 = i15 + 1;
        bArr[i15] = (byte) (i14 >> 16);
        int i17 = i16 + 1;
        bArr[i16] = (byte) (i14 >> 24);
        int i18 = i17 + 1;
        int i19 = this.f;
        bArr[i17] = (byte) i19;
        int i20 = i18 + 1;
        bArr[i18] = (byte) (i19 >> 8);
        bArr[i20] = (byte) (i19 >> 16);
        bArr[i20 + 1] = (byte) (i19 >> 24);
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public String getAlgorithmName() {
        return AesKey.ALGORITHM;
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public int getBlockSize() {
        return 16;
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public void init(boolean z, CipherParameters cipherParameters) {
        if (!(cipherParameters instanceof KeyParameter)) {
            throw new IllegalArgumentException("invalid parameter passed to AES init - " + cipherParameters.getClass().getName());
        }
        this.b = e(((KeyParameter) cipherParameters).getKey(), z);
        this.g = z;
        if (z) {
            this.h = Arrays.clone(i);
        } else {
            this.h = Arrays.clone(j);
        }
    }

    public final void j(byte[] bArr, int i2) {
        int i3 = i2 + 1;
        int i4 = bArr[i2] & 255;
        this.c = i4;
        int i5 = i3 + 1;
        int i6 = i4 | ((bArr[i3] & 255) << 8);
        this.c = i6;
        int i7 = i5 + 1;
        int i8 = i6 | ((bArr[i5] & 255) << 16);
        this.c = i8;
        int i9 = i7 + 1;
        this.c = i8 | (bArr[i7] << 24);
        int i10 = i9 + 1;
        int i11 = bArr[i9] & 255;
        this.d = i11;
        int i12 = i10 + 1;
        int i13 = ((bArr[i10] & 255) << 8) | i11;
        this.d = i13;
        int i14 = i12 + 1;
        int i15 = i13 | ((bArr[i12] & 255) << 16);
        this.d = i15;
        int i16 = i14 + 1;
        this.d = i15 | (bArr[i14] << 24);
        int i17 = i16 + 1;
        int i18 = bArr[i16] & 255;
        this.e = i18;
        int i19 = i17 + 1;
        int i20 = ((bArr[i17] & 255) << 8) | i18;
        this.e = i20;
        int i21 = i19 + 1;
        int i22 = i20 | ((bArr[i19] & 255) << 16);
        this.e = i22;
        int i23 = i21 + 1;
        this.e = i22 | (bArr[i21] << 24);
        int i24 = i23 + 1;
        int i25 = bArr[i23] & 255;
        this.f = i25;
        int i26 = i24 + 1;
        int i27 = ((bArr[i24] & 255) << 8) | i25;
        this.f = i27;
        int i28 = i27 | ((bArr[i26] & 255) << 16);
        this.f = i28;
        this.f = (bArr[i26 + 1] << 24) | i28;
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public int processBlock(byte[] bArr, int i2, byte[] bArr2, int i3) {
        if (this.b != null) {
            if (i2 + 16 <= bArr.length) {
                if (i3 + 16 <= bArr2.length) {
                    boolean z = this.g;
                    j(bArr, i2);
                    int[][] iArr = this.b;
                    if (z) {
                        d(iArr);
                    } else {
                        c(iArr);
                    }
                    g(bArr2, i3);
                    return 16;
                }
                throw new OutputLengthException("output buffer too short");
            }
            throw new DataLengthException("input buffer too short");
        }
        throw new IllegalStateException("AES engine not initialised");
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public void reset() {
    }
}
