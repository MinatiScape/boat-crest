package org.bouncycastle.est;
/* loaded from: classes13.dex */
public class CSRRequestResponse {

    /* renamed from: a  reason: collision with root package name */
    public final CSRAttributesResponse f14905a;
    public final Source b;

    public CSRRequestResponse(CSRAttributesResponse cSRAttributesResponse, Source source) {
        this.f14905a = cSRAttributesResponse;
        this.b = source;
    }

    public CSRAttributesResponse getAttributesResponse() {
        CSRAttributesResponse cSRAttributesResponse = this.f14905a;
        if (cSRAttributesResponse != null) {
            return cSRAttributesResponse;
        }
        throw new IllegalStateException("Response has no CSRAttributesResponse.");
    }

    public Object getSession() {
        return this.b.getSession();
    }

    public Source getSource() {
        return this.b;
    }

    public boolean hasAttributesResponse() {
        return this.f14905a != null;
    }
}
