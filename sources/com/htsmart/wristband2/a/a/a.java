package com.htsmart.wristband2.a.a;

import com.htsmart.wristband2.a.e.d;
import com.htsmart.wristband2.bean.ConnectionState;
import com.htsmart.wristband2.bean.HealthyDataResult;
import com.htsmart.wristband2.bean.WristbandContacts;
import com.htsmart.wristband2.bean.WristbandHabit;
import com.htsmart.wristband2.bean.WristbandVersion;
import com.htsmart.wristband2.packet.PacketData;
import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.Single;
import java.util.List;
/* loaded from: classes11.dex */
public abstract class a extends com.htsmart.wristband2.a.d.c {
    public static final byte A0 = 43;
    public static final byte A1 = 100;
    public static final byte A2 = 34;
    public static final byte B0 = 44;
    public static final byte B1 = 101;
    public static final byte B2 = 35;
    public static final byte C0 = 45;
    public static final byte C1 = 102;
    public static final byte C2 = 36;
    public static final byte D0 = 46;
    public static final byte D1 = 103;
    public static final byte D2 = 65;
    public static final byte E0 = 47;
    public static final byte E1 = 104;
    public static final byte E2 = 48;
    public static final byte F0 = 48;
    public static final byte F1 = 105;
    public static final byte F2 = 49;
    public static final byte G0 = 53;
    public static final byte G1 = 106;
    public static final byte G2 = 50;
    public static final byte H0 = 54;
    public static final byte H1 = 107;
    public static final byte H2 = 51;
    public static final byte I0 = 55;
    public static final byte I1 = 108;
    public static final byte I2 = 66;
    public static final byte J0 = 56;
    public static final byte J1 = 112;
    public static final byte J2 = 67;
    public static final byte K0 = 57;
    public static final byte K1 = 109;
    public static final byte K2 = 1;
    public static final byte L0 = 58;
    public static final byte L1 = 110;
    public static final byte L2 = 2;
    public static final byte M0 = 59;
    public static final byte M1 = 113;
    public static final byte M2 = 3;
    public static final byte N0 = 60;
    public static final byte N1 = 114;
    public static final byte N2 = 4;
    public static final byte O = 1;
    public static final byte O0 = 61;
    public static final byte O1 = 115;
    public static final byte O2 = 5;
    public static final byte P = 2;
    public static final byte P0 = 64;
    public static final byte P1 = 116;
    public static final byte P2 = 8;
    public static final byte Q = 3;
    public static final byte Q0 = 65;
    public static final byte Q1 = 117;
    public static final byte Q2 = 9;
    public static final byte R = 4;
    public static final byte R0 = 66;
    public static final byte R1 = 118;
    public static final byte R2 = 10;
    public static final byte S = 5;
    public static final byte S0 = 67;
    public static final byte S1 = 119;
    public static final byte T = 7;
    public static final byte T0 = 68;
    public static final byte T1 = 120;
    public static final byte U = 1;
    public static final byte U0 = 70;
    public static final byte U1 = 121;
    public static final byte V = 2;
    public static final byte V0 = 71;
    public static final byte V1 = 122;
    public static final byte W = 1;
    public static final byte W0 = 72;
    public static final byte W1 = 123;
    public static final byte X = 2;
    public static final byte X0 = 73;
    public static final byte X1 = 124;
    public static final byte Y = 3;
    public static final byte Y0 = 74;
    public static final byte Y1 = -122;
    public static final byte Z = 4;
    public static final byte Z0 = 75;
    public static final byte Z1 = -124;
    public static final byte a0 = 5;
    public static final byte a1 = 76;
    public static final byte a2 = -121;
    public static final byte b0 = 6;
    public static final byte b1 = 77;
    public static final byte b2 = -110;
    public static final byte c0 = 16;
    public static final byte c1 = 78;
    public static final byte c2 = -109;
    public static final byte d0 = 17;
    public static final byte d1 = 80;
    public static final byte d2 = -108;
    public static final byte e0 = 18;
    public static final byte e1 = 81;
    public static final byte e2 = -118;
    public static final byte f0 = 19;
    public static final byte f1 = 82;
    public static final byte f2 = -117;
    public static final byte g0 = 20;
    public static final byte g1 = 83;
    public static final byte g2 = -116;
    public static final byte h0 = 21;
    public static final byte h1 = 84;
    public static final byte h2 = -115;
    public static final byte i0 = 22;
    public static final byte i1 = 85;
    public static final byte i2 = -114;
    public static final byte j0 = 23;
    public static final byte j1 = 86;
    public static final byte j2 = -89;
    public static final byte k0 = 24;
    public static final byte k1 = -94;
    public static final byte k2 = -88;
    public static final byte l0 = 25;
    public static final byte l1 = -93;
    public static final byte l2 = -87;
    public static final byte m0 = 26;
    public static final byte m1 = -92;
    public static final byte m2 = 17;
    public static final byte n0 = 27;
    public static final byte n1 = 87;
    public static final byte n2 = 18;
    public static final byte o0 = 28;
    public static final byte o1 = 88;
    public static final byte o2 = 19;
    public static final byte p0 = 32;
    public static final byte p1 = 89;
    public static final byte p2 = 20;
    public static final byte q0 = 33;
    public static final byte q1 = 90;
    public static final byte q2 = 21;
    public static final byte r0 = 34;
    public static final byte r1 = 91;
    public static final byte r2 = 22;
    public static final byte s0 = 35;
    public static final byte s1 = 92;
    public static final byte s2 = 32;
    public static final byte t0 = 36;
    public static final byte t1 = 93;
    public static final byte t2 = 1;
    public static final byte u0 = 37;
    public static final byte u1 = 94;
    public static final byte u2 = 5;
    public static final byte v0 = 38;
    public static final byte v1 = 95;
    public static final byte v2 = 6;
    public static final byte w0 = 39;
    public static final byte w1 = 96;
    public static final byte w2 = 7;
    public static final byte x0 = 40;
    public static final byte x1 = 97;
    public static final byte x2 = 8;
    public static final byte y0 = 41;
    public static final byte y1 = 98;
    public static final byte y2 = 32;
    public static final byte z0 = 42;
    public static final byte z1 = 99;
    public static final byte z2 = 33;
    public d I = new e();

    public Completable a(PacketData packetData) {
        return this.I.a(new l(this, packetData)).ignoreElements();
    }

    public Completable a(List<PacketData> list) {
        return this.I.a(new j(this, list)).ignoreElements();
    }

    public Observable<HealthyDataResult> a(int i, int i3, WristbandVersion wristbandVersion) {
        return this.I.a(new k(this, i, i3, wristbandVersion));
    }

    public Single<List<byte[]>> a(byte b, d.s1 s1Var) {
        return this.I.a(new w(this, b, s1Var)).singleOrError();
    }

    public Single<List<byte[]>> a(d.s1 s1Var) {
        return this.I.a(new v(this, s1Var)).singleOrError();
    }

    public Single<PacketData> a(PacketData packetData, PacketData packetData2) {
        return this.I.a(new s(this, packetData, packetData2)).singleOrError();
    }

    @Override // com.htsmart.wristband2.a.d.c, com.htsmart.wristband2.a.b.a
    public void a(ConnectionState connectionState) {
        super.a(connectionState);
        if (connectionState == ConnectionState.CONNECTED) {
            this.I.a(getConnectedAddress());
        } else if (connectionState == ConnectionState.DISCONNECTED) {
            this.I.a();
        }
    }

    public d p() {
        return this.I;
    }

    public Observable<int[]> q() {
        return this.I.a(new g(this));
    }

    public Observable<byte[]> r() {
        return this.I.a(new h(this));
    }

    public Single<List<WristbandContacts>> s() {
        return this.I.a(new p(this)).singleOrError();
    }

    public Single<List<WristbandHabit>> t() {
        return this.I.a(new q(this)).singleOrError();
    }
}
