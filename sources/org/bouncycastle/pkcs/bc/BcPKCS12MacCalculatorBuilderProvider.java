package org.bouncycastle.pkcs.bc;

import org.bouncycastle.asn1.DERNull;
import org.bouncycastle.asn1.pkcs.PKCS12PBEParams;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.operator.MacCalculator;
import org.bouncycastle.operator.OperatorCreationException;
import org.bouncycastle.operator.bc.BcDigestProvider;
import org.bouncycastle.pkcs.PKCS12MacCalculatorBuilder;
import org.bouncycastle.pkcs.PKCS12MacCalculatorBuilderProvider;
/* loaded from: classes13.dex */
public class BcPKCS12MacCalculatorBuilderProvider implements PKCS12MacCalculatorBuilderProvider {

    /* renamed from: a  reason: collision with root package name */
    public BcDigestProvider f15264a;

    /* loaded from: classes13.dex */
    public class a implements PKCS12MacCalculatorBuilder {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ AlgorithmIdentifier f15265a;

        public a(AlgorithmIdentifier algorithmIdentifier) {
            this.f15265a = algorithmIdentifier;
        }

        @Override // org.bouncycastle.pkcs.PKCS12MacCalculatorBuilder
        public MacCalculator build(char[] cArr) throws OperatorCreationException {
            return org.bouncycastle.pkcs.bc.a.b(this.f15265a.getAlgorithm(), BcPKCS12MacCalculatorBuilderProvider.this.f15264a.get(this.f15265a), PKCS12PBEParams.getInstance(this.f15265a.getParameters()), cArr);
        }

        @Override // org.bouncycastle.pkcs.PKCS12MacCalculatorBuilder
        public AlgorithmIdentifier getDigestAlgorithmIdentifier() {
            return new AlgorithmIdentifier(this.f15265a.getAlgorithm(), DERNull.INSTANCE);
        }
    }

    public BcPKCS12MacCalculatorBuilderProvider(BcDigestProvider bcDigestProvider) {
        this.f15264a = bcDigestProvider;
    }

    @Override // org.bouncycastle.pkcs.PKCS12MacCalculatorBuilderProvider
    public PKCS12MacCalculatorBuilder get(AlgorithmIdentifier algorithmIdentifier) {
        return new a(algorithmIdentifier);
    }
}
