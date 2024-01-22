package com.abupdate.http_libs.request.multi;

import com.abupdate.http_libs.request.base.AbstractRequest;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;
import no.nordicsemi.android.dfu.DfuBaseService;
/* loaded from: classes.dex */
public abstract class AbstractPart {
    public static final byte[] CR_LF;
    public static final byte[] TRANSFER_ENCODING_8BIT;
    public static final byte[] TRANSFER_ENCODING_BINARY;
    public static final Charset infoCharset;
    public byte[] header;
    public String key;
    public String mimeType;
    public MultipartBody multipartBody;

    static {
        Charset charset = BoundaryCreater.charset;
        infoCharset = charset;
        CR_LF = "\r\n".getBytes(charset);
        TRANSFER_ENCODING_BINARY = AbstractRequest.TRANSFER_ENCODING_BINARY.getBytes(charset);
        TRANSFER_ENCODING_8BIT = "Content-Transfer-Encoding: 8bit\r\n".getBytes(charset);
    }

    public AbstractPart(String str, String str2) {
        this.mimeType = DfuBaseService.MIME_TYPE_OCTET_STREAM;
        this.key = str;
        if (str2 != null) {
            this.mimeType = str2;
        }
    }

    public abstract byte[] createContentDisposition();

    public abstract byte[] createContentType();

    public byte[] createHeader(byte[] bArr) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            byteArrayOutputStream.write(bArr);
            byteArrayOutputStream.write(createContentDisposition());
            byteArrayOutputStream.write(createContentType());
            byteArrayOutputStream.write(getTransferEncoding());
            byteArrayOutputStream.write(CR_LF);
            this.header = byteArrayOutputStream.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return this.header;
    }

    public MultipartBody getMultipartBody() {
        return this.multipartBody;
    }

    public abstract long getTotalLength();

    public abstract byte[] getTransferEncoding();

    public void setMultipartBody(MultipartBody multipartBody) {
        this.multipartBody = multipartBody;
    }

    public void updateProgress(int i) {
        MultipartBody multipartBody = this.multipartBody;
        if (multipartBody != null) {
            multipartBody.updateProgress(i);
        }
    }

    public abstract void writeTo(OutputStream outputStream);

    public void writeToServer(OutputStream outputStream) {
        byte[] bArr = this.header;
        if (bArr == null) {
            throw new RuntimeException("Not call createHeader()，未调用createHeader方法");
        }
        outputStream.write(bArr);
        updateProgress(this.header.length);
        writeTo(outputStream);
    }
}
