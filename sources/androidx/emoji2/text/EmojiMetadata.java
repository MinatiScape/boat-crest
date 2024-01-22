package androidx.emoji2.text;

import android.annotation.SuppressLint;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import androidx.annotation.AnyThread;
import androidx.annotation.IntRange;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.annotation.RestrictTo;
import androidx.emoji2.text.flatbuffer.MetadataItem;
import com.goodix.ble.libcomx.util.HexStringBuilder;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
@AnyThread
@RequiresApi(19)
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
/* loaded from: classes.dex */
public class EmojiMetadata {
    public static final int HAS_GLYPH_ABSENT = 1;
    public static final int HAS_GLYPH_EXISTS = 2;
    public static final int HAS_GLYPH_UNKNOWN = 0;
    public static final ThreadLocal<MetadataItem> d = new ThreadLocal<>();

    /* renamed from: a  reason: collision with root package name */
    public final int f1257a;
    @NonNull
    public final MetadataRepo b;
    public volatile int c = 0;

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface HasGlyph {
    }

    public EmojiMetadata(@NonNull MetadataRepo metadataRepo, @IntRange(from = 0) int i) {
        this.b = metadataRepo;
        this.f1257a = i;
    }

    public final MetadataItem a() {
        ThreadLocal<MetadataItem> threadLocal = d;
        MetadataItem metadataItem = threadLocal.get();
        if (metadataItem == null) {
            metadataItem = new MetadataItem();
            threadLocal.set(metadataItem);
        }
        this.b.getMetadataList().list(metadataItem, this.f1257a);
        return metadataItem;
    }

    public void draw(@NonNull Canvas canvas, float f, float f2, @NonNull Paint paint) {
        Typeface d2 = this.b.d();
        Typeface typeface = paint.getTypeface();
        paint.setTypeface(d2);
        canvas.drawText(this.b.getEmojiCharArray(), this.f1257a * 2, 2, f, f2, paint);
        paint.setTypeface(typeface);
    }

    public int getCodepointAt(int i) {
        return a().codepoints(i);
    }

    public int getCodepointsLength() {
        return a().codepointsLength();
    }

    public short getCompatAdded() {
        return a().compatAdded();
    }

    @SuppressLint({"KotlinPropertyAccess"})
    public int getHasGlyph() {
        return this.c;
    }

    public short getHeight() {
        return a().height();
    }

    public int getId() {
        return a().id();
    }

    public short getSdkAdded() {
        return a().sdkAdded();
    }

    @NonNull
    public Typeface getTypeface() {
        return this.b.d();
    }

    public short getWidth() {
        return a().width();
    }

    public boolean isDefaultEmoji() {
        return a().emojiStyle();
    }

    @RestrictTo({RestrictTo.Scope.TESTS})
    public void resetHasGlyphCache() {
        this.c = 0;
    }

    @SuppressLint({"KotlinPropertyAccess"})
    public void setHasGlyph(boolean z) {
        this.c = z ? 2 : 1;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        sb.append(", id:");
        sb.append(Integer.toHexString(getId()));
        sb.append(", codepoints:");
        int codepointsLength = getCodepointsLength();
        for (int i = 0; i < codepointsLength; i++) {
            sb.append(Integer.toHexString(getCodepointAt(i)));
            sb.append(HexStringBuilder.DEFAULT_SEPARATOR);
        }
        return sb.toString();
    }
}
