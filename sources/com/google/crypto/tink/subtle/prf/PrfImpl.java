package com.google.crypto.tink.subtle.prf;

import com.google.crypto.tink.prf.Prf;
import com.google.errorprone.annotations.Immutable;
import java.io.IOException;
import java.io.InputStream;
import java.security.GeneralSecurityException;
@Immutable
/* loaded from: classes10.dex */
public class PrfImpl implements Prf {

    /* renamed from: a  reason: collision with root package name */
    public final StreamingPrf f11063a;

    public PrfImpl(StreamingPrf streamingPrf) {
        this.f11063a = streamingPrf;
    }

    public static byte[] a(InputStream inputStream, int i) throws GeneralSecurityException {
        try {
            byte[] bArr = new byte[i];
            int i2 = 0;
            while (i2 < i) {
                int read = inputStream.read(bArr, i2, i - i2);
                if (read <= 0) {
                    throw new GeneralSecurityException("Provided StreamingPrf terminated before providing requested number of bytes.");
                }
                i2 += read;
            }
            return bArr;
        } catch (IOException e) {
            throw new GeneralSecurityException(e);
        }
    }

    public static PrfImpl wrap(StreamingPrf streamingPrf) {
        return new PrfImpl(streamingPrf);
    }

    @Override // com.google.crypto.tink.prf.Prf
    public byte[] compute(byte[] bArr, int i) throws GeneralSecurityException {
        if (bArr != null) {
            if (i > 0) {
                return a(this.f11063a.computePrf(bArr), i);
            }
            throw new GeneralSecurityException("Invalid outputLength specified.");
        }
        throw new GeneralSecurityException("Invalid input provided.");
    }
}
