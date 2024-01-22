package org.bouncycastle.cert.ocsp;

import java.util.Date;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.ocsp.ResponseData;
import org.bouncycastle.asn1.ocsp.SingleResponse;
import org.bouncycastle.asn1.x509.Extensions;
/* loaded from: classes12.dex */
public class RespData {

    /* renamed from: a  reason: collision with root package name */
    public ResponseData f14500a;

    public RespData(ResponseData responseData) {
        this.f14500a = responseData;
    }

    public Date getProducedAt() {
        return a.a(this.f14500a.getProducedAt());
    }

    public RespID getResponderId() {
        return new RespID(this.f14500a.getResponderID());
    }

    public Extensions getResponseExtensions() {
        return this.f14500a.getResponseExtensions();
    }

    public SingleResp[] getResponses() {
        ASN1Sequence responses = this.f14500a.getResponses();
        int size = responses.size();
        SingleResp[] singleRespArr = new SingleResp[size];
        for (int i = 0; i != size; i++) {
            singleRespArr[i] = new SingleResp(SingleResponse.getInstance(responses.getObjectAt(i)));
        }
        return singleRespArr;
    }

    public int getVersion() {
        return this.f14500a.getVersion().getValue().intValue() + 1;
    }
}
