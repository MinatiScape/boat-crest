package org.bouncycastle.dvcs;

import org.bouncycastle.asn1.dvcs.Data;
import org.bouncycastle.cms.CMSException;
import org.bouncycastle.cms.CMSSignedData;
/* loaded from: classes13.dex */
public class VSDRequestData extends DVCSRequestData {

    /* renamed from: a  reason: collision with root package name */
    public CMSSignedData f14892a;

    public VSDRequestData(Data data) throws DVCSConstructionException {
        super(data);
        a();
    }

    public final void a() throws DVCSConstructionException {
        if (this.f14892a == null) {
            if (this.data.getMessage() == null) {
                throw new DVCSConstructionException("DVCSRequest.data.message should be specified for VSD service");
            }
            try {
                this.f14892a = new CMSSignedData(this.data.getMessage().getOctets());
            } catch (CMSException e) {
                throw new DVCSConstructionException("Can't read CMS SignedData from input", e);
            }
        }
    }

    public byte[] getMessage() {
        return this.data.getMessage().getOctets();
    }

    public CMSSignedData getParsedMessage() {
        return this.f14892a;
    }
}
