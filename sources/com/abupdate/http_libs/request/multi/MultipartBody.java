package com.abupdate.http_libs.request.multi;

import com.abupdate.http_libs.request.content.HttpBody;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.LinkedList;
/* loaded from: classes.dex */
public class MultipartBody extends HttpBody {
    public long b;

    /* renamed from: a  reason: collision with root package name */
    public LinkedList<AbstractPart> f1875a = new LinkedList<>();
    public BoundaryCreater c = new BoundaryCreater();

    public MultipartBody() {
        this.contentType = "multipart/form-data; boundary=" + this.c.getBoundary();
    }

    public MultipartBody addPart(AbstractPart abstractPart) {
        if (abstractPart == null) {
            return this;
        }
        abstractPart.setMultipartBody(this);
        abstractPart.createHeader(this.c.getBoundaryLine());
        this.f1875a.add(abstractPart);
        return this;
    }

    public MultipartBody addPart(String str, File file, String str2) {
        return addPart(new FilePart(str, file, str2));
    }

    public MultipartBody addPart(String str, String str2, String str3, String str4) {
        return addPart(new StringPart(str, str2, str3, str4));
    }

    public MultipartBody addPart(String str, byte[] bArr, String str2) {
        return addPart(new BytesPart(str, bArr, str2));
    }

    public BoundaryCreater getBoundary() {
        return this.c;
    }

    @Override // com.abupdate.http_libs.request.content.HttpBody
    public long getContentLength() {
        long j;
        IOException e;
        try {
            Iterator<AbstractPart> it = this.f1875a.iterator();
            j = -1;
            while (it.hasNext()) {
                try {
                    long totalLength = it.next().getTotalLength();
                    if (totalLength < 0) {
                        return -1L;
                    }
                    j += totalLength;
                } catch (IOException e2) {
                    e = e2;
                    e.printStackTrace();
                    return j;
                }
            }
            return j + this.c.getBoundaryEnd().length;
        } catch (IOException e3) {
            j = -1;
            e = e3;
        }
    }

    public LinkedList<AbstractPart> getHttpParts() {
        return this.f1875a;
    }

    public MultipartBody setHttpParts(LinkedList<AbstractPart> linkedList) {
        this.f1875a = linkedList;
        return this;
    }

    public void updateProgress(long j) {
        this.b += j;
    }

    @Override // com.abupdate.http_libs.request.content.HttpBody
    public void writeTo(OutputStream outputStream) {
        this.b = 0L;
        getContentLength();
        Iterator<AbstractPart> it = this.f1875a.iterator();
        while (it.hasNext()) {
            it.next().writeToServer(outputStream);
        }
        outputStream.write(this.c.getBoundaryEnd());
        updateProgress(this.c.getBoundaryEnd().length);
    }
}
