package com.google.crypto.tink.subtle;

import com.google.crypto.tink.prf.Prf;
import com.google.errorprone.annotations.Immutable;
import java.security.GeneralSecurityException;
import java.security.InvalidAlgorithmParameterException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import javax.crypto.Mac;
@Immutable
/* loaded from: classes10.dex */
public final class PrfHmacJce implements Prf {

    /* renamed from: a  reason: collision with root package name */
    public final ThreadLocal<Mac> f11038a;
    public final String b;
    public final Key c;
    public final int d;

    /* loaded from: classes10.dex */
    public class a extends ThreadLocal<Mac> {
        public a() {
        }

        @Override // java.lang.ThreadLocal
        /* renamed from: a */
        public Mac initialValue() {
            try {
                Mac engineFactory = EngineFactory.MAC.getInstance(PrfHmacJce.this.b);
                engineFactory.init(PrfHmacJce.this.c);
                return engineFactory;
            } catch (GeneralSecurityException e) {
                throw new IllegalStateException(e);
            }
        }
    }

    public PrfHmacJce(String str, Key key) throws GeneralSecurityException {
        a aVar = new a();
        this.f11038a = aVar;
        this.b = str;
        this.c = key;
        if (key.getEncoded().length >= 16) {
            str.hashCode();
            char c = 65535;
            switch (str.hashCode()) {
                case -1823053428:
                    if (str.equals("HMACSHA1")) {
                        c = 0;
                        break;
                    }
                    break;
                case 392315118:
                    if (str.equals("HMACSHA256")) {
                        c = 1;
                        break;
                    }
                    break;
                case 392316170:
                    if (str.equals("HMACSHA384")) {
                        c = 2;
                        break;
                    }
                    break;
                case 392317873:
                    if (str.equals("HMACSHA512")) {
                        c = 3;
                        break;
                    }
                    break;
            }
            switch (c) {
                case 0:
                    this.d = 20;
                    break;
                case 1:
                    this.d = 32;
                    break;
                case 2:
                    this.d = 48;
                    break;
                case 3:
                    this.d = 64;
                    break;
                default:
                    throw new NoSuchAlgorithmException("unknown Hmac algorithm: " + str);
            }
            aVar.get();
            return;
        }
        throw new InvalidAlgorithmParameterException("key size too small, need at least 16 bytes");
    }

    @Override // com.google.crypto.tink.prf.Prf
    public byte[] compute(byte[] bArr, int i) throws GeneralSecurityException {
        if (i <= this.d) {
            this.f11038a.get().update(bArr);
            return Arrays.copyOf(this.f11038a.get().doFinal(), i);
        }
        throw new InvalidAlgorithmParameterException("tag size too big");
    }

    public int getMaxOutputLength() {
        return this.d;
    }
}
