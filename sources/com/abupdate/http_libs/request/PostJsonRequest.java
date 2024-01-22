package com.abupdate.http_libs.request;

import com.abupdate.http_libs.data.a;
import com.abupdate.http_libs.request.base.AbstractRequest;
import com.abupdate.http_libs.request.content.JsonBody;
import org.json.JSONObject;
/* loaded from: classes.dex */
public class PostJsonRequest extends AbstractRequest {
    public PostJsonRequest(String str) {
        setUrl(str);
        setMethod(a.Post);
        setHeaderContentType("application/json");
    }

    public PostJsonRequest json(JSONObject jSONObject) {
        return (PostJsonRequest) setHttpBody(new JsonBody(jSONObject.toString()));
    }
}
