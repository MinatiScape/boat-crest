package com.abupdate.http_libs;

import com.abupdate.http_libs.engine.HttpManager;
import com.abupdate.http_libs.request.GetRequest;
import com.abupdate.http_libs.request.PostFileRequest;
import com.abupdate.http_libs.request.PostFormRequest;
import com.abupdate.http_libs.request.PostJsonRequest;
import com.abupdate.http_libs.request.base.Request;
import com.abupdate.http_libs.response.Response;
/* loaded from: classes.dex */
public class HttpIotUtils {

    /* renamed from: a  reason: collision with root package name */
    public static HttpIotUtils f1862a;
    public static HttpManager b;

    public static GetRequest get(String str) {
        return new GetRequest(str);
    }

    public static HttpIotUtils getInstance() {
        if (f1862a == null) {
            synchronized (HttpIotUtils.class) {
                f1862a = new HttpIotUtils();
            }
        }
        return f1862a;
    }

    public static void init(HttpManager httpManager) {
        b = httpManager;
    }

    public static PostFileRequest postFile(String str) {
        return new PostFileRequest(str);
    }

    public static PostFormRequest postForm(String str) {
        return new PostFormRequest(str);
    }

    public static PostJsonRequest postJson(String str) {
        return new PostJsonRequest(str);
    }

    public Response exec(Request request) {
        if (request.getHttpListener() != null) {
            b.enqueue(request);
            return null;
        }
        return b.execute(request);
    }
}
