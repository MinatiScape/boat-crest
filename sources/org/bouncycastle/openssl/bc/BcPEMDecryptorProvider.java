package org.bouncycastle.openssl.bc;

import org.bouncycastle.openssl.PEMDecryptor;
import org.bouncycastle.openssl.PEMDecryptorProvider;
import org.bouncycastle.openssl.PEMException;
import org.bouncycastle.openssl.PasswordException;
/* loaded from: classes13.dex */
public class BcPEMDecryptorProvider implements PEMDecryptorProvider {

    /* renamed from: a  reason: collision with root package name */
    public final char[] f15203a;

    /* loaded from: classes13.dex */
    public class a implements PEMDecryptor {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ String f15204a;

        public a(String str) {
            this.f15204a = str;
        }

        @Override // org.bouncycastle.openssl.PEMDecryptor
        public byte[] decrypt(byte[] bArr, byte[] bArr2) throws PEMException {
            if (BcPEMDecryptorProvider.this.f15203a != null) {
                return org.bouncycastle.openssl.bc.a.a(false, bArr, BcPEMDecryptorProvider.this.f15203a, this.f15204a, bArr2);
            }
            throw new PasswordException("Password is null, but a password is required");
        }
    }

    public BcPEMDecryptorProvider(char[] cArr) {
        this.f15203a = cArr;
    }

    @Override // org.bouncycastle.openssl.PEMDecryptorProvider
    public PEMDecryptor get(String str) {
        return new a(str);
    }
}
