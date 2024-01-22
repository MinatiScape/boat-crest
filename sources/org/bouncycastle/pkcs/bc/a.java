package org.bouncycastle.pkcs.bc;

import java.io.OutputStream;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.pkcs.PKCS12PBEParams;
import org.bouncycastle.asn1.pkcs.PKCSObjectIdentifiers;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.crypto.BlockCipher;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.ExtendedDigest;
import org.bouncycastle.crypto.PBEParametersGenerator;
import org.bouncycastle.crypto.engines.DESedeEngine;
import org.bouncycastle.crypto.engines.RC2Engine;
import org.bouncycastle.crypto.generators.PKCS12ParametersGenerator;
import org.bouncycastle.crypto.io.MacOutputStream;
import org.bouncycastle.crypto.macs.HMac;
import org.bouncycastle.crypto.modes.CBCBlockCipher;
import org.bouncycastle.crypto.paddings.PKCS7Padding;
import org.bouncycastle.crypto.paddings.PaddedBufferedBlockCipher;
import org.bouncycastle.crypto.params.DESParameters;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.crypto.params.ParametersWithIV;
import org.bouncycastle.operator.GenericKey;
import org.bouncycastle.operator.MacCalculator;
import org.bouncycastle.util.Integers;
/* loaded from: classes13.dex */
public class a {

    /* renamed from: a  reason: collision with root package name */
    public static Map f15271a = new HashMap();
    public static Set b = new HashSet();
    public static Set c = new HashSet();

    /* renamed from: org.bouncycastle.pkcs.bc.a$a  reason: collision with other inner class name */
    /* loaded from: classes13.dex */
    public static class C0913a implements MacCalculator {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ ASN1ObjectIdentifier f15272a;
        public final /* synthetic */ PKCS12PBEParams b;
        public final /* synthetic */ HMac c;
        public final /* synthetic */ char[] d;

        public C0913a(ASN1ObjectIdentifier aSN1ObjectIdentifier, PKCS12PBEParams pKCS12PBEParams, HMac hMac, char[] cArr) {
            this.f15272a = aSN1ObjectIdentifier;
            this.b = pKCS12PBEParams;
            this.c = hMac;
            this.d = cArr;
        }

        @Override // org.bouncycastle.operator.MacCalculator
        public AlgorithmIdentifier getAlgorithmIdentifier() {
            return new AlgorithmIdentifier(this.f15272a, this.b);
        }

        @Override // org.bouncycastle.operator.MacCalculator
        public GenericKey getKey() {
            return new GenericKey(getAlgorithmIdentifier(), PBEParametersGenerator.PKCS12PasswordToBytes(this.d));
        }

        @Override // org.bouncycastle.operator.MacCalculator
        public byte[] getMac() {
            byte[] bArr = new byte[this.c.getMacSize()];
            this.c.doFinal(bArr, 0);
            return bArr;
        }

        @Override // org.bouncycastle.operator.MacCalculator
        public OutputStream getOutputStream() {
            return new MacOutputStream(this.c);
        }
    }

    static {
        Map map = f15271a;
        ASN1ObjectIdentifier aSN1ObjectIdentifier = PKCSObjectIdentifiers.pbeWithSHAAnd128BitRC4;
        map.put(aSN1ObjectIdentifier, Integers.valueOf(128));
        Map map2 = f15271a;
        ASN1ObjectIdentifier aSN1ObjectIdentifier2 = PKCSObjectIdentifiers.pbeWithSHAAnd40BitRC4;
        map2.put(aSN1ObjectIdentifier2, Integers.valueOf(40));
        Map map3 = f15271a;
        ASN1ObjectIdentifier aSN1ObjectIdentifier3 = PKCSObjectIdentifiers.pbeWithSHAAnd3_KeyTripleDES_CBC;
        map3.put(aSN1ObjectIdentifier3, Integers.valueOf(192));
        f15271a.put(PKCSObjectIdentifiers.pbeWithSHAAnd2_KeyTripleDES_CBC, Integers.valueOf(128));
        f15271a.put(PKCSObjectIdentifiers.pbeWithSHAAnd128BitRC2_CBC, Integers.valueOf(128));
        f15271a.put(PKCSObjectIdentifiers.pbeWithSHAAnd40BitRC2_CBC, Integers.valueOf(40));
        b.add(aSN1ObjectIdentifier);
        b.add(aSN1ObjectIdentifier2);
        c.add(aSN1ObjectIdentifier3);
        c.add(aSN1ObjectIdentifier3);
    }

    public static CipherParameters a(ASN1ObjectIdentifier aSN1ObjectIdentifier, ExtendedDigest extendedDigest, int i, PKCS12PBEParams pKCS12PBEParams, char[] cArr) {
        PKCS12ParametersGenerator pKCS12ParametersGenerator = new PKCS12ParametersGenerator(extendedDigest);
        pKCS12ParametersGenerator.init(PBEParametersGenerator.PKCS12PasswordToBytes(cArr), pKCS12PBEParams.getIV(), pKCS12PBEParams.getIterations().intValue());
        if (e(aSN1ObjectIdentifier)) {
            return pKCS12ParametersGenerator.generateDerivedParameters(d(aSN1ObjectIdentifier));
        }
        CipherParameters generateDerivedParameters = pKCS12ParametersGenerator.generateDerivedParameters(d(aSN1ObjectIdentifier), i * 8);
        if (f(aSN1ObjectIdentifier)) {
            DESParameters.setOddParity(((KeyParameter) ((ParametersWithIV) generateDerivedParameters).getParameters()).getKey());
        }
        return generateDerivedParameters;
    }

    public static MacCalculator b(ASN1ObjectIdentifier aSN1ObjectIdentifier, ExtendedDigest extendedDigest, PKCS12PBEParams pKCS12PBEParams, char[] cArr) {
        PKCS12ParametersGenerator pKCS12ParametersGenerator = new PKCS12ParametersGenerator(extendedDigest);
        pKCS12ParametersGenerator.init(PBEParametersGenerator.PKCS12PasswordToBytes(cArr), pKCS12PBEParams.getIV(), pKCS12PBEParams.getIterations().intValue());
        HMac hMac = new HMac(extendedDigest);
        hMac.init((KeyParameter) pKCS12ParametersGenerator.generateDerivedMacParameters(extendedDigest.getDigestSize() * 8));
        return new C0913a(aSN1ObjectIdentifier, pKCS12PBEParams, hMac, cArr);
    }

    public static PaddedBufferedBlockCipher c(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        BlockCipher dESedeEngine;
        if (aSN1ObjectIdentifier.equals(PKCSObjectIdentifiers.pbeWithSHAAnd3_KeyTripleDES_CBC) || aSN1ObjectIdentifier.equals(PKCSObjectIdentifiers.pbeWithSHAAnd2_KeyTripleDES_CBC)) {
            dESedeEngine = new DESedeEngine();
        } else if (!aSN1ObjectIdentifier.equals(PKCSObjectIdentifiers.pbeWithSHAAnd128BitRC2_CBC) && !aSN1ObjectIdentifier.equals(PKCSObjectIdentifiers.pbeWithSHAAnd40BitRC2_CBC)) {
            throw new IllegalStateException("unknown algorithm");
        } else {
            dESedeEngine = new RC2Engine();
        }
        return new PaddedBufferedBlockCipher(new CBCBlockCipher(dESedeEngine), new PKCS7Padding());
    }

    public static int d(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        return ((Integer) f15271a.get(aSN1ObjectIdentifier)).intValue();
    }

    public static boolean e(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        return b.contains(aSN1ObjectIdentifier);
    }

    public static boolean f(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        return c.contains(aSN1ObjectIdentifier);
    }
}
