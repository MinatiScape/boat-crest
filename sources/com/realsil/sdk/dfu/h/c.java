package com.realsil.sdk.dfu.h;

import com.realsil.sdk.core.logger.ZLogger;
import com.realsil.sdk.dfu.image.stream.BaseBinInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Locale;
import kotlin.UShort;
/* loaded from: classes12.dex */
public class c extends BaseBinInputStream {
    public c(InputStream inputStream) throws IOException {
        super(inputStream);
    }

    @Override // com.realsil.sdk.dfu.image.stream.BaseBinInputStream
    public void e() throws IOException {
        super.e();
        ByteBuffer wrap = ByteBuffer.wrap(this.g, 0, 12);
        wrap.order(ByteOrder.LITTLE_ENDIAN);
        this.icType = (byte) 3;
        this.o = wrap.getShort() & UShort.MAX_VALUE;
        this.h = wrap.getShort() & UShort.MAX_VALUE;
        this.imageVersion = wrap.getShort() & UShort.MAX_VALUE;
        this.r = wrap.getShort();
        this.j = wrap.getShort() & UShort.MAX_VALUE;
        byte[] bArr = this.g;
        this.q = bArr[10];
        byte b = bArr[11];
        ZLogger.v(String.format(Locale.US, "flashAddr=0x%04X, imageId=0x%04X, imageVersion=0x%08X(%d), crc16=0x%04X, imageSize=0x%04X(%d), otaFlag=0x%02X", Integer.valueOf(this.o), Integer.valueOf(this.h), Integer.valueOf(this.imageVersion), Integer.valueOf(this.imageVersion), Short.valueOf(this.r), Integer.valueOf(this.j), Integer.valueOf(this.j), Byte.valueOf(this.q)));
    }

    @Override // com.realsil.sdk.dfu.image.stream.BaseBinInputStream
    public int remainSizeInBytes() {
        return (this.j * 4) - this.w;
    }
}
