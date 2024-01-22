package org.bouncycastle.dvcs;

import java.io.IOException;
import org.bouncycastle.asn1.ASN1Encoding;
import org.bouncycastle.cms.CMSException;
import org.bouncycastle.cms.CMSProcessableByteArray;
import org.bouncycastle.cms.CMSSignedData;
import org.bouncycastle.cms.CMSSignedDataGenerator;
/* loaded from: classes13.dex */
public class SignedDVCSMessageGenerator {

    /* renamed from: a  reason: collision with root package name */
    public final CMSSignedDataGenerator f14889a;

    public SignedDVCSMessageGenerator(CMSSignedDataGenerator cMSSignedDataGenerator) {
        this.f14889a = cMSSignedDataGenerator;
    }

    public CMSSignedData build(DVCSMessage dVCSMessage) throws DVCSException {
        try {
            return this.f14889a.generate(new CMSProcessableByteArray(dVCSMessage.getContentType(), dVCSMessage.getContent().toASN1Primitive().getEncoded(ASN1Encoding.DER)), true);
        } catch (IOException e) {
            throw new DVCSException("Could not encode DVCS request", e);
        } catch (CMSException e2) {
            throw new DVCSException("Could not sign DVCS request", e2);
        }
    }
}
