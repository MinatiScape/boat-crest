package org.bouncycastle.dvcs;

import java.io.IOException;
import java.math.BigInteger;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.cms.ContentInfo;
import org.bouncycastle.asn1.dvcs.DVCSObjectIdentifiers;
import org.bouncycastle.asn1.dvcs.DVCSRequestInformationBuilder;
import org.bouncycastle.asn1.dvcs.Data;
import org.bouncycastle.asn1.x509.ExtensionsGenerator;
import org.bouncycastle.asn1.x509.GeneralName;
import org.bouncycastle.asn1.x509.GeneralNames;
import org.bouncycastle.cms.CMSSignedDataGenerator;
/* loaded from: classes13.dex */
public abstract class DVCSRequestBuilder {

    /* renamed from: a  reason: collision with root package name */
    public final ExtensionsGenerator f14885a = new ExtensionsGenerator();
    public final DVCSRequestInformationBuilder requestInformationBuilder;

    public DVCSRequestBuilder(DVCSRequestInformationBuilder dVCSRequestInformationBuilder) {
        new CMSSignedDataGenerator();
        this.requestInformationBuilder = dVCSRequestInformationBuilder;
    }

    public void addExtension(ASN1ObjectIdentifier aSN1ObjectIdentifier, boolean z, ASN1Encodable aSN1Encodable) throws DVCSException {
        try {
            this.f14885a.addExtension(aSN1ObjectIdentifier, z, aSN1Encodable);
        } catch (IOException e) {
            throw new DVCSException("cannot encode extension: " + e.getMessage(), e);
        }
    }

    public DVCSRequest createDVCRequest(Data data) throws DVCSException {
        if (!this.f14885a.isEmpty()) {
            this.requestInformationBuilder.setExtensions(this.f14885a.generate());
        }
        return new DVCSRequest(new ContentInfo(DVCSObjectIdentifiers.id_ct_DVCSRequestData, new org.bouncycastle.asn1.dvcs.DVCSRequest(this.requestInformationBuilder.build(), data)));
    }

    public void setDVCS(GeneralName generalName) {
        this.requestInformationBuilder.setDVCS(generalName);
    }

    public void setDVCS(GeneralNames generalNames) {
        this.requestInformationBuilder.setDVCS(generalNames);
    }

    public void setDataLocations(GeneralName generalName) {
        this.requestInformationBuilder.setDataLocations(generalName);
    }

    public void setDataLocations(GeneralNames generalNames) {
        this.requestInformationBuilder.setDataLocations(generalNames);
    }

    public void setNonce(BigInteger bigInteger) {
        this.requestInformationBuilder.setNonce(bigInteger);
    }

    public void setRequester(GeneralName generalName) {
        this.requestInformationBuilder.setRequester(generalName);
    }
}
