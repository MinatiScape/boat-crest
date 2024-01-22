package com.google.android.gms.common.images;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.mappls.sdk.maps.style.layers.Property;
import java.util.Locale;
import org.json.JSONException;
import org.json.JSONObject;
@SafeParcelable.Class(creator = "WebImageCreator")
/* loaded from: classes6.dex */
public final class WebImage extends AbstractSafeParcelable {
    @NonNull
    public static final Parcelable.Creator<WebImage> CREATOR = new zah();
    @SafeParcelable.VersionField(id = 1)
    public final int h;
    @SafeParcelable.Field(getter = "getUrl", id = 2)
    public final Uri i;
    @SafeParcelable.Field(getter = "getWidth", id = 3)
    public final int j;
    @SafeParcelable.Field(getter = "getHeight", id = 4)
    public final int k;

    @SafeParcelable.Constructor
    public WebImage(@SafeParcelable.Param(id = 1) int i, @SafeParcelable.Param(id = 2) Uri uri, @SafeParcelable.Param(id = 3) int i2, @SafeParcelable.Param(id = 4) int i3) {
        this.h = i;
        this.i = uri;
        this.j = i2;
        this.k = i3;
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && (obj instanceof WebImage)) {
            WebImage webImage = (WebImage) obj;
            if (Objects.equal(this.i, webImage.i) && this.j == webImage.j && this.k == webImage.k) {
                return true;
            }
        }
        return false;
    }

    public int getHeight() {
        return this.k;
    }

    @NonNull
    public Uri getUrl() {
        return this.i;
    }

    public int getWidth() {
        return this.j;
    }

    public int hashCode() {
        return Objects.hashCode(this.i, Integer.valueOf(this.j), Integer.valueOf(this.k));
    }

    @NonNull
    @KeepForSdk
    public JSONObject toJson() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("url", this.i.toString());
            jSONObject.put(Property.ICON_TEXT_FIT_WIDTH, this.j);
            jSONObject.put(Property.ICON_TEXT_FIT_HEIGHT, this.k);
        } catch (JSONException unused) {
        }
        return jSONObject;
    }

    @NonNull
    public String toString() {
        return String.format(Locale.US, "Image %dx%d %s", Integer.valueOf(this.j), Integer.valueOf(this.k), this.i.toString());
    }

    @Override // android.os.Parcelable
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, this.h);
        SafeParcelWriter.writeParcelable(parcel, 2, getUrl(), i, false);
        SafeParcelWriter.writeInt(parcel, 3, getWidth());
        SafeParcelWriter.writeInt(parcel, 4, getHeight());
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    public WebImage(@NonNull Uri uri) throws IllegalArgumentException {
        this(uri, 0, 0);
    }

    public WebImage(@NonNull Uri uri, int i, int i2) throws IllegalArgumentException {
        this(1, uri, i, i2);
        if (uri == null) {
            throw new IllegalArgumentException("url cannot be null");
        }
        if (i < 0 || i2 < 0) {
            throw new IllegalArgumentException("width and height must not be negative");
        }
    }

    /* JADX WARN: Illegal instructions before constructor call */
    @com.google.android.gms.common.annotation.KeepForSdk
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public WebImage(@androidx.annotation.NonNull org.json.JSONObject r5) throws java.lang.IllegalArgumentException {
        /*
            r4 = this;
            android.net.Uri r0 = android.net.Uri.EMPTY
            java.lang.String r1 = "url"
            boolean r2 = r5.has(r1)
            if (r2 == 0) goto L12
            java.lang.String r1 = r5.getString(r1)     // Catch: org.json.JSONException -> L12
            android.net.Uri r0 = android.net.Uri.parse(r1)     // Catch: org.json.JSONException -> L12
        L12:
            java.lang.String r1 = "width"
            r2 = 0
            int r1 = r5.optInt(r1, r2)
            java.lang.String r3 = "height"
            int r5 = r5.optInt(r3, r2)
            r4.<init>(r0, r1, r5)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.images.WebImage.<init>(org.json.JSONObject):void");
    }
}
