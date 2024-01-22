package com.abupdate.http_libs.request.multi;

import com.goodix.ble.libcomx.util.HexStringBuilder;
import java.io.UnsupportedEncodingException;
/* loaded from: classes.dex */
public class StringPart extends BytesPart {
    public String charset;
    public String mimeType;

    public StringPart(String str, String str2) {
        this(str, str2, "UTF-8", "text/plain");
    }

    public StringPart(String str, String str2, String str3, String str4) {
        super(str, getBytes(str2, str3), str4);
        this.mimeType = str4 == null ? "text/plain" : str4;
        this.charset = str3;
    }

    public static byte[] getBytes(String str, String str2) {
        try {
            return str.getBytes(str2);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override // com.abupdate.http_libs.request.multi.BytesPart, com.abupdate.http_libs.request.multi.AbstractPart
    public byte[] createContentType() {
        return ("Content-Type: " + this.mimeType + HexStringBuilder.DEFAULT_SEPARATOR + "; charset=" + this.charset + "\r\n").getBytes(AbstractPart.infoCharset);
    }

    @Override // com.abupdate.http_libs.request.multi.BytesPart, com.abupdate.http_libs.request.multi.AbstractPart
    public byte[] getTransferEncoding() {
        return AbstractPart.TRANSFER_ENCODING_8BIT;
    }
}
