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
public class a extends BaseBinInputStream {
    public a(InputStream inputStream) throws IOException {
        super(inputStream);
    }

    @Override // com.realsil.sdk.dfu.image.stream.BaseBinInputStream
    public void c() throws IOException {
        super.c();
        ByteBuffer wrap = ByteBuffer.wrap(this.s, 0, 12);
        wrap.order(ByteOrder.LITTLE_ENDIAN);
        if (this.otaVersion == 0) {
            this.icType = wrap.get();
            this.q = wrap.get();
            this.h = wrap.getShort() & UShort.MAX_VALUE;
            this.imageVersion = wrap.getShort() & UShort.MAX_VALUE;
            this.r = wrap.getShort();
            if (this.k <= 0) {
                this.j = wrap.getInt();
            }
        } else {
            this.icType = wrap.get(2);
            this.q = wrap.get();
            if (!this.i) {
                this.h = wrap.getShort(6) & UShort.MAX_VALUE;
            }
            wrap.getShort();
            this.r = wrap.getShort();
            wrap.getInt();
        }
        if (this.f13606a) {
            ZLogger.d(String.format(Locale.US, "binHeader: icType=0x%02X, otaFlag=0x%02X, imageId=0x%04X, imageVersion=0x%08X, crc16=0x%04X, imageSize(exclude image header)=0x%08X(%d)", Byte.valueOf(this.icType), Byte.valueOf(this.q), Integer.valueOf(this.h), Integer.valueOf(this.imageVersion), Short.valueOf(this.r), Integer.valueOf(this.j), Integer.valueOf(this.j)));
        }
    }

    @Override // com.realsil.sdk.dfu.image.stream.BaseBinInputStream
    public void e() throws IOException {
        super.e();
    }

    @Override // com.realsil.sdk.dfu.image.stream.BaseBinInputStream
    public boolean isNeedReadDfuHeader() {
        return true;
    }
}
