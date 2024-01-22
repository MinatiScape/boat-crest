package com.mappls.sdk.maps.covid;

import androidx.annotation.Keep;
import java.util.List;
import okhttp3.ResponseBody;
@Keep
/* loaded from: classes11.dex */
class CombinedResponse {
    private List<ResponseBody> responseBodies;

    public CombinedResponse(List<ResponseBody> list) {
        this.responseBodies = list;
    }

    public List<ResponseBody> getResponseBodies() {
        return this.responseBodies;
    }
}
