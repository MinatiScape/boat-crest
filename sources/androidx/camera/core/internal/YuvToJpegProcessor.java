package androidx.camera.core.internal;

import android.graphics.Rect;
import android.media.ImageWriter;
import android.util.Size;
import android.view.Surface;
import androidx.annotation.GuardedBy;
import androidx.annotation.IntRange;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.camera.core.ImageProxy;
import androidx.camera.core.Logger;
import androidx.camera.core.impl.CaptureProcessor;
import androidx.camera.core.impl.utils.ExifData;
import androidx.camera.core.internal.compat.ImageWriterCompat;
import androidx.core.util.Preconditions;
import com.coveiot.android.camera.utils.ViewExtensionsKt;
import java.io.EOFException;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.util.Objects;
@RequiresApi(26)
/* loaded from: classes.dex */
public class YuvToJpegProcessor implements CaptureProcessor {
    public static final Rect h = new Rect(0, 0, 0, 0);
    @IntRange(from = 0, to = ViewExtensionsKt.ANIMATION_SLOW_MILLIS)

    /* renamed from: a  reason: collision with root package name */
    public final int f755a;
    public final int b;
    @GuardedBy("mLock")
    public ImageWriter f;
    public final Object c = new Object();
    @GuardedBy("mLock")
    public boolean d = false;
    @GuardedBy("mLock")
    public int e = 0;
    @GuardedBy("mLock")
    public Rect g = h;

    public YuvToJpegProcessor(@IntRange(from = 0, to = 100) int i, int i2) {
        this.f755a = i;
        this.b = i2;
    }

    @NonNull
    public static ExifData a(@NonNull ImageProxy imageProxy) {
        ExifData.Builder builderForDevice = ExifData.builderForDevice();
        imageProxy.getImageInfo().populateExifData(builderForDevice);
        return builderForDevice.setImageWidth(imageProxy.getWidth()).setImageHeight(imageProxy.getHeight()).build();
    }

    public void close() {
        synchronized (this.c) {
            if (!this.d) {
                this.d = true;
                if (this.e == 0 && this.f != null) {
                    Logger.d("YuvToJpegProcessor", "No processing in progress. Closing immediately.");
                    this.f.close();
                } else {
                    Logger.d("YuvToJpegProcessor", "close() called while processing. Will close after completion.");
                }
            }
        }
    }

    @Override // androidx.camera.core.impl.CaptureProcessor
    public void onOutputSurface(@NonNull Surface surface, int i) {
        Preconditions.checkState(i == 256, "YuvToJpegProcessor only supports JPEG output format.");
        synchronized (this.c) {
            if (!this.d) {
                if (this.f == null) {
                    this.f = ImageWriterCompat.newInstance(surface, this.b, i);
                } else {
                    throw new IllegalStateException("Output surface already set.");
                }
            } else {
                Logger.w("YuvToJpegProcessor", "Cannot set output surface. Processor is closed.");
            }
        }
    }

    @Override // androidx.camera.core.impl.CaptureProcessor
    public void onResolutionUpdate(@NonNull Size size) {
        synchronized (this.c) {
            this.g = new Rect(0, 0, size.getWidth(), size.getHeight());
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:118:0x0191  */
    /* JADX WARN: Removed duplicated region for block: B:90:0x013b A[Catch: all -> 0x015e, TRY_ENTER, TRY_LEAVE, TryCatch #18 {all -> 0x015e, blocks: (B:43:0x00e3, B:90:0x013b), top: B:151:0x0057 }] */
    /* JADX WARN: Removed duplicated region for block: B:98:0x0162  */
    @Override // androidx.camera.core.impl.CaptureProcessor
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void process(@androidx.annotation.NonNull androidx.camera.core.impl.ImageProxyBundle r18) {
        /*
            Method dump skipped, instructions count: 440
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.camera.core.internal.YuvToJpegProcessor.process(androidx.camera.core.impl.ImageProxyBundle):void");
    }

    /* loaded from: classes.dex */
    public static final class a extends OutputStream {
        public final ByteBuffer h;

        public a(@NonNull ByteBuffer byteBuffer) {
            this.h = byteBuffer;
        }

        @Override // java.io.OutputStream
        public void write(int i) throws IOException {
            if (this.h.hasRemaining()) {
                this.h.put((byte) i);
                return;
            }
            throw new EOFException("Output ByteBuffer has no bytes remaining.");
        }

        @Override // java.io.OutputStream
        public void write(byte[] bArr, int i, int i2) throws IOException {
            int i3;
            Objects.requireNonNull(bArr);
            if (i < 0 || i > bArr.length || i2 < 0 || (i3 = i + i2) > bArr.length || i3 < 0) {
                throw new IndexOutOfBoundsException();
            }
            if (i2 == 0) {
                return;
            }
            if (this.h.remaining() >= i2) {
                this.h.put(bArr, i, i2);
                return;
            }
            throw new EOFException("Output ByteBuffer has insufficient bytes remaining.");
        }
    }
}
