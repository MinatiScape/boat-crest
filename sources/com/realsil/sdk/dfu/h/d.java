package com.realsil.sdk.dfu.h;

import androidx.core.view.PointerIconCompat;
import com.realsil.sdk.core.logger.ZLogger;
import com.realsil.sdk.dfu.image.stream.BaseBinInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Locale;
import kotlin.UShort;
/* loaded from: classes12.dex */
public class d extends BaseBinInputStream {
    public d(InputStream inputStream) throws IOException {
        super(inputStream);
    }

    @Override // com.realsil.sdk.dfu.image.stream.BaseBinInputStream
    public void e() throws IOException {
        super.e();
        ByteBuffer wrap = ByteBuffer.wrap(this.g, 0, 12);
        wrap.order(ByteOrder.LITTLE_ENDIAN);
        if (this.otaVersion == 0) {
            this.icType = wrap.get();
            this.p = wrap.get();
            wrap.getShort();
            this.h = wrap.getShort() & UShort.MAX_VALUE;
            this.r = wrap.getShort();
            if (this.k <= 0) {
                this.j = wrap.getInt() + PointerIconCompat.TYPE_NO_DROP;
            }
        } else {
            this.icType = wrap.get();
            wrap.get();
            wrap.getShort();
            if (!this.i) {
                this.h = wrap.getShort() & UShort.MAX_VALUE;
            }
            this.r = wrap.getShort();
            wrap.getInt();
        }
        ZLogger.v(String.format(Locale.US, "icType=0x%02X, secure_version=0x%02x, otaFlag=0x%02x, imageId=0x%04x, imageVersion=0x%08X, crc16=0x%04x, imageSize=0x%08X(%d)", Byte.valueOf(this.icType), Integer.valueOf(this.p), Byte.valueOf(this.q), Integer.valueOf(this.h), Integer.valueOf(this.imageVersion), Short.valueOf(this.r), Integer.valueOf(this.j), Integer.valueOf(this.j)));
    }
}
