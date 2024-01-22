package com.abupdate.http_libs.request.multi;

import java.io.OutputStream;
/* loaded from: classes.dex */
public class BytesPart extends AbstractPart {
    public static final String TAG = "BytesPart";
    public byte[] bytes;

    public BytesPart(String str, byte[] bArr) {
        this(str, bArr, null);
        this.bytes = bArr;
    }

    public BytesPart(String str, byte[] bArr, String str2) {
        super(str, str2);
        this.bytes = bArr;
    }

    @Override // com.abupdate.http_libs.request.multi.AbstractPart
    public byte[] createContentDisposition() {
        return ("Content-Disposition: form-data; name=\"" + this.key + "\"\r\n").getBytes(AbstractPart.infoCharset);
    }

    @Override // com.abupdate.http_libs.request.multi.AbstractPart
    public byte[] createContentType() {
        return ("Content-Type: " + this.mimeType + "\r\n").getBytes(AbstractPart.infoCharset);
    }

    @Override // com.abupdate.http_libs.request.multi.AbstractPart
    public long getTotalLength() {
        return this.header.length + this.bytes.length + AbstractPart.CR_LF.length;
    }

    @Override // com.abupdate.http_libs.request.multi.AbstractPart
    public byte[] getTransferEncoding() {
        return AbstractPart.TRANSFER_ENCODING_BINARY;
    }

    @Override // com.abupdate.http_libs.request.multi.AbstractPart
    public void writeTo(OutputStream outputStream) {
        outputStream.write(this.bytes);
        byte[] bArr = AbstractPart.CR_LF;
        outputStream.write(bArr);
        updateProgress(this.bytes.length + bArr.length);
    }
}
