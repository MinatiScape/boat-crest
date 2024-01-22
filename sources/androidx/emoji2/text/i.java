package androidx.emoji2.text;

import android.content.res.AssetManager;
import androidx.annotation.AnyThread;
import androidx.annotation.IntRange;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.annotation.RestrictTo;
import androidx.emoji2.text.flatbuffer.MetadataList;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import kotlin.UShort;
@AnyThread
@RequiresApi(19)
@RestrictTo({RestrictTo.Scope.LIBRARY})
/* loaded from: classes.dex */
public class i {

    /* loaded from: classes.dex */
    public static class a implements d {
        @NonNull

        /* renamed from: a  reason: collision with root package name */
        public final ByteBuffer f1280a;

        public a(@NonNull ByteBuffer byteBuffer) {
            this.f1280a = byteBuffer;
            byteBuffer.order(ByteOrder.BIG_ENDIAN);
        }

        @Override // androidx.emoji2.text.i.d
        public int a() throws IOException {
            return this.f1280a.getInt();
        }

        @Override // androidx.emoji2.text.i.d
        public void b(int i) throws IOException {
            ByteBuffer byteBuffer = this.f1280a;
            byteBuffer.position(byteBuffer.position() + i);
        }

        @Override // androidx.emoji2.text.i.d
        public long c() throws IOException {
            return i.e(this.f1280a.getInt());
        }

        @Override // androidx.emoji2.text.i.d
        public long getPosition() {
            return this.f1280a.position();
        }

        @Override // androidx.emoji2.text.i.d
        public int readUnsignedShort() throws IOException {
            return i.f(this.f1280a.getShort());
        }
    }

    /* loaded from: classes.dex */
    public static class b implements d {
        @NonNull

        /* renamed from: a  reason: collision with root package name */
        public final byte[] f1281a;
        @NonNull
        public final ByteBuffer b;
        @NonNull
        public final InputStream c;
        public long d = 0;

        public b(@NonNull InputStream inputStream) {
            this.c = inputStream;
            byte[] bArr = new byte[4];
            this.f1281a = bArr;
            ByteBuffer wrap = ByteBuffer.wrap(bArr);
            this.b = wrap;
            wrap.order(ByteOrder.BIG_ENDIAN);
        }

        @Override // androidx.emoji2.text.i.d
        public int a() throws IOException {
            this.b.position(0);
            d(4);
            return this.b.getInt();
        }

        @Override // androidx.emoji2.text.i.d
        public void b(int i) throws IOException {
            while (i > 0) {
                int skip = (int) this.c.skip(i);
                if (skip >= 1) {
                    i -= skip;
                    this.d += skip;
                } else {
                    throw new IOException("Skip didn't move at least 1 byte forward");
                }
            }
        }

        @Override // androidx.emoji2.text.i.d
        public long c() throws IOException {
            this.b.position(0);
            d(4);
            return i.e(this.b.getInt());
        }

        public final void d(@IntRange(from = 0, to = 4) int i) throws IOException {
            if (this.c.read(this.f1281a, 0, i) == i) {
                this.d += i;
                return;
            }
            throw new IOException("read failed");
        }

        @Override // androidx.emoji2.text.i.d
        public long getPosition() {
            return this.d;
        }

        @Override // androidx.emoji2.text.i.d
        public int readUnsignedShort() throws IOException {
            this.b.position(0);
            d(2);
            return i.f(this.b.getShort());
        }
    }

    /* loaded from: classes.dex */
    public static class c {

        /* renamed from: a  reason: collision with root package name */
        public final long f1282a;
        public final long b;

        public c(long j, long j2) {
            this.f1282a = j;
            this.b = j2;
        }

        public long a() {
            return this.b;
        }

        public long b() {
            return this.f1282a;
        }
    }

    /* loaded from: classes.dex */
    public interface d {
        int a() throws IOException;

        void b(int i) throws IOException;

        long c() throws IOException;

        long getPosition();

        int readUnsignedShort() throws IOException;
    }

    public static c a(d dVar) throws IOException {
        long j;
        dVar.b(4);
        int readUnsignedShort = dVar.readUnsignedShort();
        if (readUnsignedShort <= 100) {
            dVar.b(6);
            int i = 0;
            while (true) {
                if (i >= readUnsignedShort) {
                    j = -1;
                    break;
                }
                int a2 = dVar.a();
                dVar.b(4);
                j = dVar.c();
                dVar.b(4);
                if (1835365473 == a2) {
                    break;
                }
                i++;
            }
            if (j != -1) {
                dVar.b((int) (j - dVar.getPosition()));
                dVar.b(12);
                long c2 = dVar.c();
                for (int i2 = 0; i2 < c2; i2++) {
                    int a3 = dVar.a();
                    long c3 = dVar.c();
                    long c4 = dVar.c();
                    if (1164798569 == a3 || 1701669481 == a3) {
                        return new c(c3 + j, c4);
                    }
                }
            }
            throw new IOException("Cannot read metadata.");
        }
        throw new IOException("Cannot read metadata.");
    }

    public static MetadataList b(AssetManager assetManager, String str) throws IOException {
        InputStream open = assetManager.open(str);
        try {
            MetadataList c2 = c(open);
            if (open != null) {
                open.close();
            }
            return c2;
        } catch (Throwable th) {
            if (open != null) {
                try {
                    open.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
            }
            throw th;
        }
    }

    public static MetadataList c(InputStream inputStream) throws IOException {
        b bVar = new b(inputStream);
        c a2 = a(bVar);
        bVar.b((int) (a2.b() - bVar.getPosition()));
        ByteBuffer allocate = ByteBuffer.allocate((int) a2.a());
        int read = inputStream.read(allocate.array());
        if (read == a2.a()) {
            return MetadataList.getRootAsMetadataList(allocate);
        }
        throw new IOException("Needed " + a2.a() + " bytes, got " + read);
    }

    public static MetadataList d(ByteBuffer byteBuffer) throws IOException {
        ByteBuffer duplicate = byteBuffer.duplicate();
        duplicate.position((int) a(new a(duplicate)).b());
        return MetadataList.getRootAsMetadataList(duplicate);
    }

    public static long e(int i) {
        return i & 4294967295L;
    }

    public static int f(short s) {
        return s & UShort.MAX_VALUE;
    }
}
