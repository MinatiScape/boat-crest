package com.abupdate.http_libs.request.content;

import java.io.OutputStream;
/* loaded from: classes.dex */
public abstract class HttpBody {
    public static final int OUTPUT_BUFFER_SIZE = 4096;
    public String contentEncoding;
    public String contentType;

    public String getContentEncoding() {
        return this.contentEncoding;
    }

    public abstract long getContentLength();

    public String getContentType() {
        return this.contentType;
    }

    public HttpBody setContentEncoding(String str) {
        this.contentEncoding = str;
        return this;
    }

    public HttpBody setContentType(String str) {
        this.contentType = str;
        return this;
    }

    public abstract void writeTo(OutputStream outputStream);
}
