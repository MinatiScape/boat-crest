package com.abupdate.http_libs.request;

import com.abupdate.http_libs.data.a;
import com.abupdate.http_libs.request.base.AbstractRequest;
/* loaded from: classes.dex */
public class GetRequest extends AbstractRequest {
    public GetRequest(String str) {
        setUrl(str);
        setMethod(a.Get);
    }
}
