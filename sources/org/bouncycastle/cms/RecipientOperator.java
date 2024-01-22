package org.bouncycastle.cms;

import java.io.InputStream;
import org.bouncycastle.operator.InputDecryptor;
import org.bouncycastle.operator.MacCalculator;
import org.bouncycastle.util.io.TeeInputStream;
/* loaded from: classes12.dex */
public class RecipientOperator {

    /* renamed from: a  reason: collision with root package name */
    public final Object f14545a;

    public RecipientOperator(InputDecryptor inputDecryptor) {
        inputDecryptor.getAlgorithmIdentifier();
        this.f14545a = inputDecryptor;
    }

    public RecipientOperator(MacCalculator macCalculator) {
        macCalculator.getAlgorithmIdentifier();
        this.f14545a = macCalculator;
    }

    public InputStream getInputStream(InputStream inputStream) {
        Object obj = this.f14545a;
        return obj instanceof InputDecryptor ? ((InputDecryptor) obj).getInputStream(inputStream) : new TeeInputStream(inputStream, ((MacCalculator) this.f14545a).getOutputStream());
    }

    public byte[] getMac() {
        return ((MacCalculator) this.f14545a).getMac();
    }

    public boolean isMacBased() {
        return this.f14545a instanceof MacCalculator;
    }
}
