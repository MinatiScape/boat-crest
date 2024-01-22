package com.abupdate.http_libs.request.content;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import no.nordicsemi.android.dfu.DfuBaseService;
/* loaded from: classes.dex */
public class FormFileBody extends HttpBody {

    /* renamed from: a  reason: collision with root package name */
    public File f1873a;

    public FormFileBody(File file) {
        this(file, DfuBaseService.MIME_TYPE_OCTET_STREAM);
    }

    public FormFileBody(File file, String str) {
        this.f1873a = file;
        this.contentType = str;
    }

    @Override // com.abupdate.http_libs.request.content.HttpBody
    public long getContentLength() {
        return this.f1873a.length();
    }

    public File getFile() {
        return this.f1873a;
    }

    @Override // com.abupdate.http_libs.request.content.HttpBody
    public void writeTo(OutputStream outputStream) {
        FileInputStream fileInputStream = new FileInputStream(this.f1873a);
        try {
            byte[] bArr = new byte[4096];
            while (true) {
                int read = fileInputStream.read(bArr);
                if (read == -1) {
                    outputStream.flush();
                    return;
                }
                outputStream.write(bArr, 0, read);
            }
        } finally {
            fileInputStream.close();
        }
    }
}
