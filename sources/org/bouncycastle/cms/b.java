package org.bouncycastle.cms;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1Set;
import org.bouncycastle.asn1.cms.KEKRecipientInfo;
import org.bouncycastle.asn1.cms.KeyAgreeRecipientInfo;
import org.bouncycastle.asn1.cms.KeyTransRecipientInfo;
import org.bouncycastle.asn1.cms.PasswordRecipientInfo;
import org.bouncycastle.asn1.cms.RecipientInfo;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.operator.DigestCalculator;
/* loaded from: classes12.dex */
public class b {

    /* loaded from: classes12.dex */
    public static class a implements e {

        /* renamed from: a  reason: collision with root package name */
        public d f14551a;

        public a(AlgorithmIdentifier algorithmIdentifier, d dVar) {
            this.f14551a = dVar;
        }

        @Override // org.bouncycastle.cms.e
        public InputStream getInputStream() throws IOException, CMSException {
            return this.f14551a.getInputStream();
        }
    }

    /* renamed from: org.bouncycastle.cms.b$b  reason: collision with other inner class name */
    /* loaded from: classes12.dex */
    public static class C0901b implements e {

        /* renamed from: a  reason: collision with root package name */
        public DigestCalculator f14552a;
        public d b;

        /* renamed from: org.bouncycastle.cms.b$b$a */
        /* loaded from: classes12.dex */
        public class a extends FilterInputStream {
            public a(InputStream inputStream) {
                super(inputStream);
            }

            @Override // java.io.FilterInputStream, java.io.InputStream
            public int read() throws IOException {
                int read = ((FilterInputStream) this).in.read();
                if (read >= 0) {
                    C0901b.this.f14552a.getOutputStream().write(read);
                }
                return read;
            }

            @Override // java.io.FilterInputStream, java.io.InputStream
            public int read(byte[] bArr, int i, int i2) throws IOException {
                int read = ((FilterInputStream) this).in.read(bArr, i, i2);
                if (read >= 0) {
                    C0901b.this.f14552a.getOutputStream().write(bArr, i, read);
                }
                return read;
            }
        }

        public C0901b(DigestCalculator digestCalculator, d dVar) {
            this.f14552a = digestCalculator;
            this.b = dVar;
        }

        public byte[] b() {
            return this.f14552a.getDigest();
        }

        @Override // org.bouncycastle.cms.e
        public InputStream getInputStream() throws IOException, CMSException {
            return new a(this.b.getInputStream());
        }
    }

    /* loaded from: classes12.dex */
    public static class c implements e {

        /* renamed from: a  reason: collision with root package name */
        public d f14553a;

        public c(AlgorithmIdentifier algorithmIdentifier, d dVar) {
            this.f14553a = dVar;
        }

        @Override // org.bouncycastle.cms.e
        public InputStream getInputStream() throws IOException, CMSException {
            return this.f14553a.getInputStream();
        }
    }

    public static RecipientInformationStore a(ASN1Set aSN1Set, AlgorithmIdentifier algorithmIdentifier, e eVar) {
        return b(aSN1Set, algorithmIdentifier, eVar, null);
    }

    public static RecipientInformationStore b(ASN1Set aSN1Set, AlgorithmIdentifier algorithmIdentifier, e eVar, org.bouncycastle.cms.a aVar) {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i != aSN1Set.size(); i++) {
            c(arrayList, RecipientInfo.getInstance(aSN1Set.getObjectAt(i)), algorithmIdentifier, eVar, aVar);
        }
        return new RecipientInformationStore(arrayList);
    }

    public static void c(List list, RecipientInfo recipientInfo, AlgorithmIdentifier algorithmIdentifier, e eVar, org.bouncycastle.cms.a aVar) {
        Object passwordRecipientInformation;
        ASN1Encodable info = recipientInfo.getInfo();
        if (info instanceof KeyTransRecipientInfo) {
            passwordRecipientInformation = new KeyTransRecipientInformation((KeyTransRecipientInfo) info, algorithmIdentifier, eVar, aVar);
        } else if (info instanceof KEKRecipientInfo) {
            passwordRecipientInformation = new KEKRecipientInformation((KEKRecipientInfo) info, algorithmIdentifier, eVar, aVar);
        } else if (info instanceof KeyAgreeRecipientInfo) {
            KeyAgreeRecipientInformation.e(list, (KeyAgreeRecipientInfo) info, algorithmIdentifier, eVar, aVar);
            return;
        } else if (!(info instanceof PasswordRecipientInfo)) {
            return;
        } else {
            passwordRecipientInformation = new PasswordRecipientInformation((PasswordRecipientInfo) info, algorithmIdentifier, eVar, aVar);
        }
        list.add(passwordRecipientInformation);
    }
}
