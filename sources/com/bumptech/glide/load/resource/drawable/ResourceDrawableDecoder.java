package com.bumptech.glide.load.resource.drawable;

import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.bumptech.glide.load.Option;
import com.bumptech.glide.load.Options;
import com.bumptech.glide.load.ResourceDecoder;
import com.bumptech.glide.load.engine.Resource;
import com.bumptech.glide.util.Preconditions;
import com.clevertap.android.sdk.Constants;
import java.util.List;
/* loaded from: classes2.dex */
public class ResourceDrawableDecoder implements ResourceDecoder<Uri, Drawable> {
    public static final Option<Resources.Theme> THEME = Option.memory("com.bumptech.glide.load.resource.bitmap.Downsampler.Theme");

    /* renamed from: a  reason: collision with root package name */
    public final Context f2488a;

    public ResourceDrawableDecoder(Context context) {
        this.f2488a = context.getApplicationContext();
    }

    @NonNull
    public final Context a(Uri uri, String str) {
        if (str.equals(this.f2488a.getPackageName())) {
            return this.f2488a;
        }
        try {
            return this.f2488a.createPackageContext(str, 0);
        } catch (PackageManager.NameNotFoundException e) {
            if (str.contains(this.f2488a.getPackageName())) {
                return this.f2488a;
            }
            throw new IllegalArgumentException("Failed to obtain context or unrecognized Uri format for: " + uri, e);
        }
    }

    @DrawableRes
    public final int b(Uri uri) {
        try {
            return Integer.parseInt(uri.getPathSegments().get(0));
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Unrecognized Uri format: " + uri, e);
        }
    }

    @DrawableRes
    public final int c(Context context, Uri uri) {
        List<String> pathSegments = uri.getPathSegments();
        String authority = uri.getAuthority();
        String str = pathSegments.get(0);
        String str2 = pathSegments.get(1);
        int identifier = context.getResources().getIdentifier(str2, str, authority);
        if (identifier == 0) {
            identifier = Resources.getSystem().getIdentifier(str2, str, Constants.KEY_ANDROID);
        }
        if (identifier != 0) {
            return identifier;
        }
        throw new IllegalArgumentException("Failed to find resource id for: " + uri);
    }

    @DrawableRes
    public final int d(Context context, Uri uri) {
        List<String> pathSegments = uri.getPathSegments();
        if (pathSegments.size() == 2) {
            return c(context, uri);
        }
        if (pathSegments.size() == 1) {
            return b(uri);
        }
        throw new IllegalArgumentException("Unrecognized Uri format: " + uri);
    }

    @Override // com.bumptech.glide.load.ResourceDecoder
    @Nullable
    public Resource<Drawable> decode(@NonNull Uri uri, int i, int i2, @NonNull Options options) {
        Drawable drawable;
        String authority = uri.getAuthority();
        Context a2 = a(uri, authority);
        int d = d(a2, uri);
        Resources.Theme theme = (Resources.Theme) options.get(THEME);
        Preconditions.checkArgument(a2.getPackageName().equals(authority) || theme == null, "Can't get a theme from another package");
        if (theme == null) {
            drawable = DrawableDecoderCompat.getDrawable(this.f2488a, a2, d);
        } else {
            drawable = DrawableDecoderCompat.getDrawable(this.f2488a, d, theme);
        }
        return a.a(drawable);
    }

    @Override // com.bumptech.glide.load.ResourceDecoder
    public boolean handles(@NonNull Uri uri, @NonNull Options options) {
        return uri.getScheme().equals("android.resource");
    }
}
