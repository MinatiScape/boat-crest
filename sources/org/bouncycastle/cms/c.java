package org.bouncycastle.cms;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.bouncycastle.util.io.Streams;
/* loaded from: classes12.dex */
public class c implements CMSProcessable, d {

    /* renamed from: a  reason: collision with root package name */
    public InputStream f14564a;
    public boolean b = false;

    public c(InputStream inputStream) {
        this.f14564a = inputStream;
    }

    public final synchronized void a() {
        if (this.b) {
            throw new IllegalStateException("CMSProcessableInputStream can only be used once");
        }
        this.b = true;
    }

    @Override // org.bouncycastle.cms.CMSProcessable
    public Object getContent() {
        return getInputStream();
    }

    @Override // org.bouncycastle.cms.d
    public InputStream getInputStream() {
        a();
        return this.f14564a;
    }

    @Override // org.bouncycastle.cms.CMSProcessable
    public void write(OutputStream outputStream) throws IOException, CMSException {
        a();
        Streams.pipeAll(this.f14564a, outputStream);
        this.f14564a.close();
    }
}
