package com.android.volley.toolbox;

import com.android.volley.Header;
import java.io.InputStream;
import java.util.Collections;
import java.util.List;
/* loaded from: classes.dex */
public final class HttpResponse {

    /* renamed from: a  reason: collision with root package name */
    public final int f2157a;
    public final List<Header> b;
    public final int c;
    public final InputStream d;

    public HttpResponse(int i, List<Header> list) {
        this(i, list, -1, null);
    }

    public final InputStream getContent() {
        return this.d;
    }

    public final int getContentLength() {
        return this.c;
    }

    public final List<Header> getHeaders() {
        return Collections.unmodifiableList(this.b);
    }

    public final int getStatusCode() {
        return this.f2157a;
    }

    public HttpResponse(int i, List<Header> list, int i2, InputStream inputStream) {
        this.f2157a = i;
        this.b = list;
        this.c = i2;
        this.d = inputStream;
    }
}
