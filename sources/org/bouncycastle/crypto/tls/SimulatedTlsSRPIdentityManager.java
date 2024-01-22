package org.bouncycastle.crypto.tls;

import org.bouncycastle.crypto.Mac;
import org.bouncycastle.crypto.agreement.srp.SRP6VerifierGenerator;
import org.bouncycastle.crypto.macs.HMac;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.crypto.params.SRP6GroupParameters;
import org.bouncycastle.util.Strings;
/* loaded from: classes13.dex */
public class SimulatedTlsSRPIdentityManager implements TlsSRPIdentityManager {

    /* renamed from: a  reason: collision with root package name */
    public static final byte[] f14856a = Strings.toByteArray("password");
    public static final byte[] b = Strings.toByteArray("salt");
    public SRP6GroupParameters group;
    public Mac mac;
    public SRP6VerifierGenerator verifierGenerator;

    public SimulatedTlsSRPIdentityManager(SRP6GroupParameters sRP6GroupParameters, SRP6VerifierGenerator sRP6VerifierGenerator, Mac mac) {
        this.group = sRP6GroupParameters;
        this.verifierGenerator = sRP6VerifierGenerator;
        this.mac = mac;
    }

    public static SimulatedTlsSRPIdentityManager getRFC5054Default(SRP6GroupParameters sRP6GroupParameters, byte[] bArr) {
        SRP6VerifierGenerator sRP6VerifierGenerator = new SRP6VerifierGenerator();
        sRP6VerifierGenerator.init(sRP6GroupParameters, TlsUtils.createHash((short) 2));
        HMac hMac = new HMac(TlsUtils.createHash((short) 2));
        hMac.init(new KeyParameter(bArr));
        return new SimulatedTlsSRPIdentityManager(sRP6GroupParameters, sRP6VerifierGenerator, hMac);
    }

    @Override // org.bouncycastle.crypto.tls.TlsSRPIdentityManager
    public TlsSRPLoginParameters getLoginParameters(byte[] bArr) {
        Mac mac = this.mac;
        byte[] bArr2 = b;
        mac.update(bArr2, 0, bArr2.length);
        this.mac.update(bArr, 0, bArr.length);
        byte[] bArr3 = new byte[this.mac.getMacSize()];
        this.mac.doFinal(bArr3, 0);
        Mac mac2 = this.mac;
        byte[] bArr4 = f14856a;
        mac2.update(bArr4, 0, bArr4.length);
        this.mac.update(bArr, 0, bArr.length);
        byte[] bArr5 = new byte[this.mac.getMacSize()];
        this.mac.doFinal(bArr5, 0);
        return new TlsSRPLoginParameters(this.group, this.verifierGenerator.generateVerifier(bArr3, bArr, bArr5), bArr3);
    }
}
