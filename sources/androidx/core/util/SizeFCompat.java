package androidx.core.util;

import android.util.SizeF;
import androidx.annotation.DoNotInline;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import com.mappls.sdk.maps.style.layers.Property;
/* loaded from: classes.dex */
public final class SizeFCompat {

    /* renamed from: a  reason: collision with root package name */
    public final float f1115a;
    public final float b;

    @RequiresApi(21)
    /* loaded from: classes.dex */
    public static final class a {
        @NonNull
        @DoNotInline
        public static SizeF a(@NonNull SizeFCompat sizeFCompat) {
            Preconditions.checkNotNull(sizeFCompat);
            return new SizeF(sizeFCompat.getWidth(), sizeFCompat.getHeight());
        }

        @NonNull
        @DoNotInline
        public static SizeFCompat b(@NonNull SizeF sizeF) {
            Preconditions.checkNotNull(sizeF);
            return new SizeFCompat(sizeF.getWidth(), sizeF.getHeight());
        }
    }

    public SizeFCompat(float f, float f2) {
        this.f1115a = Preconditions.checkArgumentFinite(f, Property.ICON_TEXT_FIT_WIDTH);
        this.b = Preconditions.checkArgumentFinite(f2, Property.ICON_TEXT_FIT_HEIGHT);
    }

    @NonNull
    @RequiresApi(21)
    public static SizeFCompat toSizeFCompat(@NonNull SizeF sizeF) {
        return a.b(sizeF);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof SizeFCompat) {
            SizeFCompat sizeFCompat = (SizeFCompat) obj;
            return sizeFCompat.f1115a == this.f1115a && sizeFCompat.b == this.b;
        }
        return false;
    }

    public float getHeight() {
        return this.b;
    }

    public float getWidth() {
        return this.f1115a;
    }

    public int hashCode() {
        return Float.floatToIntBits(this.f1115a) ^ Float.floatToIntBits(this.b);
    }

    @NonNull
    @RequiresApi(21)
    public SizeF toSizeF() {
        return a.a(this);
    }

    @NonNull
    public String toString() {
        return this.f1115a + "x" + this.b;
    }
}
