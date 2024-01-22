package com.abupdate.http_libs.request;

import com.abupdate.http_libs.data.a;
import com.abupdate.http_libs.request.base.AbstractRequest;
import com.abupdate.http_libs.request.multi.FilePart;
import com.abupdate.http_libs.request.multi.MultipartBody;
import com.abupdate.http_libs.request.multi.StringPart;
import java.io.File;
import java.util.Map;
/* loaded from: classes.dex */
public class PostFileRequest extends AbstractRequest {
    public String m;
    public String n;
    public MultipartBody o = new MultipartBody();

    public PostFileRequest(String str) {
        setUrl(str);
        setMethod(a.Post);
    }

    public final void a(MultipartBody multipartBody, Map<String, String> map) {
        for (Map.Entry<String, String> entry : map.entrySet()) {
            multipartBody.addPart(new StringPart(entry.getKey(), entry.getValue()));
        }
    }

    public PostFileRequest addFile(String str, File file) {
        this.o.addPart(new FilePart(str, file));
        return (PostFileRequest) setHttpBody(this.o);
    }

    public String getDisName() {
        return this.n;
    }

    public String getFilePath() {
        return this.m;
    }

    public PostFileRequest map(Map<String, String> map) {
        a(this.o, map);
        return (PostFileRequest) setHttpBody(this.o);
    }

    public PostFileRequest setDisName(String str) {
        this.n = str;
        return this;
    }

    public void setFilePath(String str) {
        this.m = str;
    }
}
