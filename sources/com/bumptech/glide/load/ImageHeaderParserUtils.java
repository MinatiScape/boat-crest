package com.bumptech.glide.load;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import com.bumptech.glide.load.ImageHeaderParser;
import com.bumptech.glide.load.data.ParcelFileDescriptorRewinder;
import com.bumptech.glide.load.engine.bitmap_recycle.ArrayPool;
import com.bumptech.glide.load.resource.bitmap.RecyclableBufferedInputStream;
import com.bumptech.glide.util.ByteBufferUtil;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.List;
/* loaded from: classes.dex */
public final class ImageHeaderParserUtils {

    /* loaded from: classes.dex */
    public class a implements h {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ InputStream f2323a;

        public a(InputStream inputStream) {
            this.f2323a = inputStream;
        }

        @Override // com.bumptech.glide.load.ImageHeaderParserUtils.h
        public ImageHeaderParser.ImageType a(ImageHeaderParser imageHeaderParser) throws IOException {
            try {
                return imageHeaderParser.getType(this.f2323a);
            } finally {
                this.f2323a.reset();
            }
        }
    }

    /* loaded from: classes.dex */
    public class b implements h {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ ByteBuffer f2324a;

        public b(ByteBuffer byteBuffer) {
            this.f2324a = byteBuffer;
        }

        @Override // com.bumptech.glide.load.ImageHeaderParserUtils.h
        public ImageHeaderParser.ImageType a(ImageHeaderParser imageHeaderParser) throws IOException {
            try {
                return imageHeaderParser.getType(this.f2324a);
            } finally {
                ByteBufferUtil.rewind(this.f2324a);
            }
        }
    }

    /* loaded from: classes.dex */
    public class c implements h {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ ParcelFileDescriptorRewinder f2325a;
        public final /* synthetic */ ArrayPool b;

        public c(ParcelFileDescriptorRewinder parcelFileDescriptorRewinder, ArrayPool arrayPool) {
            this.f2325a = parcelFileDescriptorRewinder;
            this.b = arrayPool;
        }

        @Override // com.bumptech.glide.load.ImageHeaderParserUtils.h
        public ImageHeaderParser.ImageType a(ImageHeaderParser imageHeaderParser) throws IOException {
            RecyclableBufferedInputStream recyclableBufferedInputStream = null;
            try {
                RecyclableBufferedInputStream recyclableBufferedInputStream2 = new RecyclableBufferedInputStream(new FileInputStream(this.f2325a.rewindAndGet().getFileDescriptor()), this.b);
                try {
                    ImageHeaderParser.ImageType type = imageHeaderParser.getType(recyclableBufferedInputStream2);
                    recyclableBufferedInputStream2.release();
                    this.f2325a.rewindAndGet();
                    return type;
                } catch (Throwable th) {
                    th = th;
                    recyclableBufferedInputStream = recyclableBufferedInputStream2;
                    if (recyclableBufferedInputStream != null) {
                        recyclableBufferedInputStream.release();
                    }
                    this.f2325a.rewindAndGet();
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
            }
        }
    }

    /* loaded from: classes.dex */
    public class d implements g {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ ByteBuffer f2326a;
        public final /* synthetic */ ArrayPool b;

        public d(ByteBuffer byteBuffer, ArrayPool arrayPool) {
            this.f2326a = byteBuffer;
            this.b = arrayPool;
        }

        @Override // com.bumptech.glide.load.ImageHeaderParserUtils.g
        public int a(ImageHeaderParser imageHeaderParser) throws IOException {
            try {
                return imageHeaderParser.getOrientation(this.f2326a, this.b);
            } finally {
                ByteBufferUtil.rewind(this.f2326a);
            }
        }
    }

    /* loaded from: classes.dex */
    public class e implements g {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ InputStream f2327a;
        public final /* synthetic */ ArrayPool b;

        public e(InputStream inputStream, ArrayPool arrayPool) {
            this.f2327a = inputStream;
            this.b = arrayPool;
        }

        @Override // com.bumptech.glide.load.ImageHeaderParserUtils.g
        public int a(ImageHeaderParser imageHeaderParser) throws IOException {
            try {
                return imageHeaderParser.getOrientation(this.f2327a, this.b);
            } finally {
                this.f2327a.reset();
            }
        }
    }

    /* loaded from: classes.dex */
    public class f implements g {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ ParcelFileDescriptorRewinder f2328a;
        public final /* synthetic */ ArrayPool b;

        public f(ParcelFileDescriptorRewinder parcelFileDescriptorRewinder, ArrayPool arrayPool) {
            this.f2328a = parcelFileDescriptorRewinder;
            this.b = arrayPool;
        }

        @Override // com.bumptech.glide.load.ImageHeaderParserUtils.g
        public int a(ImageHeaderParser imageHeaderParser) throws IOException {
            RecyclableBufferedInputStream recyclableBufferedInputStream = null;
            try {
                RecyclableBufferedInputStream recyclableBufferedInputStream2 = new RecyclableBufferedInputStream(new FileInputStream(this.f2328a.rewindAndGet().getFileDescriptor()), this.b);
                try {
                    int orientation = imageHeaderParser.getOrientation(recyclableBufferedInputStream2, this.b);
                    recyclableBufferedInputStream2.release();
                    this.f2328a.rewindAndGet();
                    return orientation;
                } catch (Throwable th) {
                    th = th;
                    recyclableBufferedInputStream = recyclableBufferedInputStream2;
                    if (recyclableBufferedInputStream != null) {
                        recyclableBufferedInputStream.release();
                    }
                    this.f2328a.rewindAndGet();
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
            }
        }
    }

    /* loaded from: classes.dex */
    public interface g {
        int a(ImageHeaderParser imageHeaderParser) throws IOException;
    }

    /* loaded from: classes.dex */
    public interface h {
        ImageHeaderParser.ImageType a(ImageHeaderParser imageHeaderParser) throws IOException;
    }

    public static int a(@NonNull List<ImageHeaderParser> list, g gVar) throws IOException {
        int size = list.size();
        for (int i = 0; i < size; i++) {
            int a2 = gVar.a(list.get(i));
            if (a2 != -1) {
                return a2;
            }
        }
        return -1;
    }

    @NonNull
    public static ImageHeaderParser.ImageType b(@NonNull List<ImageHeaderParser> list, h hVar) throws IOException {
        int size = list.size();
        for (int i = 0; i < size; i++) {
            ImageHeaderParser.ImageType a2 = hVar.a(list.get(i));
            if (a2 != ImageHeaderParser.ImageType.UNKNOWN) {
                return a2;
            }
        }
        return ImageHeaderParser.ImageType.UNKNOWN;
    }

    public static int getOrientation(@NonNull List<ImageHeaderParser> list, @Nullable ByteBuffer byteBuffer, @NonNull ArrayPool arrayPool) throws IOException {
        if (byteBuffer == null) {
            return -1;
        }
        return a(list, new d(byteBuffer, arrayPool));
    }

    @NonNull
    public static ImageHeaderParser.ImageType getType(@NonNull List<ImageHeaderParser> list, @Nullable InputStream inputStream, @NonNull ArrayPool arrayPool) throws IOException {
        if (inputStream == null) {
            return ImageHeaderParser.ImageType.UNKNOWN;
        }
        if (!inputStream.markSupported()) {
            inputStream = new RecyclableBufferedInputStream(inputStream, arrayPool);
        }
        inputStream.mark(5242880);
        return b(list, new a(inputStream));
    }

    public static int getOrientation(@NonNull List<ImageHeaderParser> list, @Nullable InputStream inputStream, @NonNull ArrayPool arrayPool) throws IOException {
        if (inputStream == null) {
            return -1;
        }
        if (!inputStream.markSupported()) {
            inputStream = new RecyclableBufferedInputStream(inputStream, arrayPool);
        }
        inputStream.mark(5242880);
        return a(list, new e(inputStream, arrayPool));
    }

    @RequiresApi(21)
    public static int getOrientation(@NonNull List<ImageHeaderParser> list, @NonNull ParcelFileDescriptorRewinder parcelFileDescriptorRewinder, @NonNull ArrayPool arrayPool) throws IOException {
        return a(list, new f(parcelFileDescriptorRewinder, arrayPool));
    }

    @NonNull
    public static ImageHeaderParser.ImageType getType(@NonNull List<ImageHeaderParser> list, @Nullable ByteBuffer byteBuffer) throws IOException {
        if (byteBuffer == null) {
            return ImageHeaderParser.ImageType.UNKNOWN;
        }
        return b(list, new b(byteBuffer));
    }

    @NonNull
    @RequiresApi(21)
    public static ImageHeaderParser.ImageType getType(@NonNull List<ImageHeaderParser> list, @NonNull ParcelFileDescriptorRewinder parcelFileDescriptorRewinder, @NonNull ArrayPool arrayPool) throws IOException {
        return b(list, new c(parcelFileDescriptorRewinder, arrayPool));
    }
}
