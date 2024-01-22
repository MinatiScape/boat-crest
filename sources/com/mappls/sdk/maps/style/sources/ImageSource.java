package com.mappls.sdk.maps.style.sources;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import androidx.annotation.DrawableRes;
import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.UiThread;
import com.mappls.sdk.maps.Mappls;
import com.mappls.sdk.maps.geometry.LatLngQuad;
import com.mappls.sdk.maps.utils.BitmapUtils;
import java.net.URI;
import java.net.URL;
@UiThread
/* loaded from: classes11.dex */
public class ImageSource extends Source {
    @Keep
    public ImageSource(long j) {
        super(j);
    }

    @Keep
    public native void finalize() throws Throwable;

    @Nullable
    public String getUri() {
        checkThread();
        return nativeGetUrl();
    }

    @Nullable
    @Deprecated
    public String getUrl() {
        checkThread();
        return nativeGetUrl();
    }

    @Keep
    public native void initialize(String str, LatLngQuad latLngQuad);

    @NonNull
    @Keep
    public native String nativeGetUrl();

    @Keep
    public native void nativeSetCoordinates(LatLngQuad latLngQuad);

    @Keep
    public native void nativeSetImage(Bitmap bitmap);

    @Keep
    public native void nativeSetUrl(String str);

    public void setCoordinates(LatLngQuad latLngQuad) {
        checkThread();
        nativeSetCoordinates(latLngQuad);
    }

    public void setImage(@NonNull Bitmap bitmap) {
        checkThread();
        nativeSetImage(bitmap);
    }

    public void setUri(@NonNull URI uri) {
        checkThread();
        nativeSetUrl(uri.toString());
    }

    @Deprecated
    public void setUrl(@NonNull URL url) {
        setUrl(url.toExternalForm());
    }

    @Deprecated
    public ImageSource(String str, LatLngQuad latLngQuad, @NonNull URL url) {
        initialize(str, latLngQuad);
        setUrl(url);
    }

    @Deprecated
    public void setUrl(String str) {
        checkThread();
        nativeSetUrl(str);
    }

    public void setImage(@DrawableRes int i) throws IllegalArgumentException {
        checkThread();
        Drawable drawableFromRes = BitmapUtils.getDrawableFromRes(Mappls.getApplicationContext(), i);
        if (drawableFromRes instanceof BitmapDrawable) {
            nativeSetImage(((BitmapDrawable) drawableFromRes).getBitmap());
            return;
        }
        throw new IllegalArgumentException("Failed to decode image. The resource provided must be a Bitmap.");
    }

    public void setUri(String str) {
        checkThread();
        nativeSetUrl(str);
    }

    public ImageSource(String str, LatLngQuad latLngQuad, @NonNull URI uri) {
        initialize(str, latLngQuad);
        setUri(uri);
    }

    public ImageSource(String str, LatLngQuad latLngQuad, @NonNull Bitmap bitmap) {
        initialize(str, latLngQuad);
        setImage(bitmap);
    }

    public ImageSource(String str, LatLngQuad latLngQuad, @DrawableRes int i) {
        initialize(str, latLngQuad);
        setImage(i);
    }
}
