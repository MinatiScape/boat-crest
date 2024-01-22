package com.mappls.sdk.maps.annotations;

import android.graphics.Bitmap;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.nio.ByteBuffer;
@Deprecated
/* loaded from: classes11.dex */
public class Icon {

    /* renamed from: a  reason: collision with root package name */
    public Bitmap f12666a;
    public String b;

    public Icon(String str, Bitmap bitmap) {
        this.b = str;
        this.f12666a = bitmap;
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Icon icon = (Icon) obj;
        return this.f12666a.equals(icon.f12666a) && this.b.equals(icon.b);
    }

    public Bitmap getBitmap() {
        Bitmap bitmap = this.f12666a;
        if (bitmap != null) {
            Bitmap.Config config = bitmap.getConfig();
            Bitmap.Config config2 = Bitmap.Config.ARGB_8888;
            if (config != config2) {
                this.f12666a = this.f12666a.copy(config2, false);
            }
        }
        return this.f12666a;
    }

    public String getId() {
        return this.b;
    }

    public float getScale() {
        Bitmap bitmap = this.f12666a;
        if (bitmap != null) {
            float density = bitmap.getDensity();
            if (density == 0.0f) {
                density = 160.0f;
            }
            return density / 160.0f;
        }
        throw new IllegalStateException("Required to set a Icon before calling getScale");
    }

    public int hashCode() {
        Bitmap bitmap = this.f12666a;
        int hashCode = bitmap != null ? bitmap.hashCode() : 0;
        String str = this.b;
        return str != null ? (hashCode * 31) + str.hashCode() : hashCode;
    }

    @NonNull
    public byte[] toBytes() {
        Bitmap bitmap = this.f12666a;
        if (bitmap != null) {
            ByteBuffer allocate = ByteBuffer.allocate(bitmap.getRowBytes() * this.f12666a.getHeight());
            this.f12666a.copyPixelsToBuffer(allocate);
            return allocate.array();
        }
        throw new IllegalStateException("Required to set a Icon before calling toBytes");
    }
}
