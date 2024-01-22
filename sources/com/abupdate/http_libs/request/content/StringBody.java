package com.abupdate.http_libs.request.content;

import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
/* loaded from: classes.dex */
public class StringBody extends HttpBody {
    public String charset;
    public byte[] content;
    public String mimeType;
    public String string;

    public StringBody(String str) {
        this(str, null, null);
    }

    public StringBody(String str, String str2, String str3) {
        str2 = str2 == null ? "text/plain" : str2;
        str3 = str3 == null ? "UTF-8" : str3;
        this.charset = str3;
        this.string = str;
        this.mimeType = str2;
        try {
            this.content = str.getBytes(str3);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        this.contentType = str2 + "; charset=" + str3;
    }

    public String getCharset() {
        return this.charset;
    }

    public byte[] getContent() {
        return this.content;
    }

    @Override // com.abupdate.http_libs.request.content.HttpBody
    public long getContentLength() {
        return this.content.length;
    }

    public String getMimeType() {
        return this.mimeType;
    }

    public String getString() {
        return this.string;
    }

    public String toString() {
        return "StringBody{charset='" + this.charset + "', mimeType='" + this.mimeType + "', string='" + this.string + "'} ";
    }

    @Override // com.abupdate.http_libs.request.content.HttpBody
    public void writeTo(OutputStream outputStream) {
        outputStream.write(this.content);
        outputStream.flush();
    }
}
