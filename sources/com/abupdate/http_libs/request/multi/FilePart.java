package com.abupdate.http_libs.request.multi;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import no.nordicsemi.android.dfu.DfuBaseService;
/* loaded from: classes.dex */
public class FilePart extends AbstractPart {
    public static final String TAG = "FilePart";
    public File file;

    public FilePart(String str, File file) {
        this(str, file, DfuBaseService.MIME_TYPE_OCTET_STREAM);
    }

    public FilePart(String str, File file, String str2) {
        super(str, str2);
        this.file = file;
    }

    @Override // com.abupdate.http_libs.request.multi.AbstractPart
    public byte[] createContentDisposition() {
        return (("Content-Disposition: form-data; name=\"" + this.key) + "\"; filename=\"" + this.file.getName() + "\"\r\n").getBytes(AbstractPart.infoCharset);
    }

    @Override // com.abupdate.http_libs.request.multi.AbstractPart
    public byte[] createContentType() {
        return ("Content-Type: " + this.mimeType + "\r\n").getBytes(AbstractPart.infoCharset);
    }

    @Override // com.abupdate.http_libs.request.multi.AbstractPart
    public long getTotalLength() {
        return this.header.length + this.file.length() + AbstractPart.CR_LF.length;
    }

    @Override // com.abupdate.http_libs.request.multi.AbstractPart
    public byte[] getTransferEncoding() {
        return AbstractPart.TRANSFER_ENCODING_BINARY;
    }

    @Override // com.abupdate.http_libs.request.multi.AbstractPart
    public void writeTo(OutputStream outputStream) {
        FileInputStream fileInputStream = new FileInputStream(this.file);
        try {
            try {
                byte[] bArr = new byte[4096];
                while (true) {
                    int read = fileInputStream.read(bArr);
                    if (read == -1) {
                        break;
                    }
                    outputStream.write(bArr, 0, read);
                    updateProgress(read);
                }
                byte[] bArr2 = AbstractPart.CR_LF;
                outputStream.write(bArr2);
                updateProgress(bArr2.length);
                outputStream.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } finally {
            fileInputStream.close();
        }
    }
}
