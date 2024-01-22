package androidx.emoji2.text;

import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.util.SparseArray;
import androidx.annotation.AnyThread;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.annotation.RestrictTo;
import androidx.annotation.VisibleForTesting;
import androidx.core.os.TraceCompat;
import androidx.core.util.Preconditions;
import androidx.emoji2.text.flatbuffer.MetadataList;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
@AnyThread
@RequiresApi(19)
/* loaded from: classes.dex */
public final class MetadataRepo {
    @NonNull

    /* renamed from: a  reason: collision with root package name */
    public final MetadataList f1261a;
    @NonNull
    public final char[] b;
    @NonNull
    public final a c = new a(1024);
    @NonNull
    public final Typeface d;

    @RestrictTo({RestrictTo.Scope.LIBRARY})
    /* loaded from: classes.dex */
    public static class a {

        /* renamed from: a  reason: collision with root package name */
        public final SparseArray<a> f1262a;
        public EmojiMetadata b;

        public a() {
            this(1);
        }

        public a a(int i) {
            SparseArray<a> sparseArray = this.f1262a;
            if (sparseArray == null) {
                return null;
            }
            return sparseArray.get(i);
        }

        public final EmojiMetadata b() {
            return this.b;
        }

        public void c(@NonNull EmojiMetadata emojiMetadata, int i, int i2) {
            a a2 = a(emojiMetadata.getCodepointAt(i));
            if (a2 == null) {
                a2 = new a();
                this.f1262a.put(emojiMetadata.getCodepointAt(i), a2);
            }
            if (i2 > i) {
                a2.c(emojiMetadata, i + 1, i2);
            } else {
                a2.b = emojiMetadata;
            }
        }

        public a(int i) {
            this.f1262a = new SparseArray<>(i);
        }
    }

    public MetadataRepo(@NonNull Typeface typeface, @NonNull MetadataList metadataList) {
        this.d = typeface;
        this.f1261a = metadataList;
        this.b = new char[metadataList.listLength() * 2];
        a(metadataList);
    }

    @NonNull
    @RestrictTo({RestrictTo.Scope.TESTS})
    public static MetadataRepo create(@NonNull Typeface typeface) {
        try {
            TraceCompat.beginSection("EmojiCompat.MetadataRepo.create");
            return new MetadataRepo(typeface, new MetadataList());
        } finally {
            TraceCompat.endSection();
        }
    }

    public final void a(MetadataList metadataList) {
        int listLength = metadataList.listLength();
        for (int i = 0; i < listLength; i++) {
            EmojiMetadata emojiMetadata = new EmojiMetadata(this, i);
            Character.toChars(emojiMetadata.getId(), this.b, i * 2);
            e(emojiMetadata);
        }
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public int b() {
        return this.f1261a.version();
    }

    @NonNull
    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public a c() {
        return this.c;
    }

    @NonNull
    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public Typeface d() {
        return this.d;
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY})
    @VisibleForTesting
    public void e(@NonNull EmojiMetadata emojiMetadata) {
        Preconditions.checkNotNull(emojiMetadata, "emoji metadata cannot be null");
        Preconditions.checkArgument(emojiMetadata.getCodepointsLength() > 0, "invalid metadata codepoint length");
        this.c.c(emojiMetadata, 0, emojiMetadata.getCodepointsLength() - 1);
    }

    @NonNull
    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public char[] getEmojiCharArray() {
        return this.b;
    }

    @NonNull
    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public MetadataList getMetadataList() {
        return this.f1261a;
    }

    @NonNull
    public static MetadataRepo create(@NonNull Typeface typeface, @NonNull InputStream inputStream) throws IOException {
        try {
            TraceCompat.beginSection("EmojiCompat.MetadataRepo.create");
            return new MetadataRepo(typeface, i.c(inputStream));
        } finally {
            TraceCompat.endSection();
        }
    }

    @NonNull
    public static MetadataRepo create(@NonNull Typeface typeface, @NonNull ByteBuffer byteBuffer) throws IOException {
        try {
            TraceCompat.beginSection("EmojiCompat.MetadataRepo.create");
            return new MetadataRepo(typeface, i.d(byteBuffer));
        } finally {
            TraceCompat.endSection();
        }
    }

    @NonNull
    public static MetadataRepo create(@NonNull AssetManager assetManager, @NonNull String str) throws IOException {
        try {
            TraceCompat.beginSection("EmojiCompat.MetadataRepo.create");
            return new MetadataRepo(Typeface.createFromAsset(assetManager, str), i.b(assetManager, str));
        } finally {
            TraceCompat.endSection();
        }
    }
}
