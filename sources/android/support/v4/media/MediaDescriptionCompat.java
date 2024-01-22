package android.support.v4.media;

import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.media.d;
import android.support.v4.media.e;
import android.text.TextUtils;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
/* loaded from: classes.dex */
public final class MediaDescriptionCompat implements Parcelable {
    public static final long BT_FOLDER_TYPE_ALBUMS = 2;
    public static final long BT_FOLDER_TYPE_ARTISTS = 3;
    public static final long BT_FOLDER_TYPE_GENRES = 4;
    public static final long BT_FOLDER_TYPE_MIXED = 0;
    public static final long BT_FOLDER_TYPE_PLAYLISTS = 5;
    public static final long BT_FOLDER_TYPE_TITLES = 1;
    public static final long BT_FOLDER_TYPE_YEARS = 6;
    public static final Parcelable.Creator<MediaDescriptionCompat> CREATOR = new a();
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public static final String DESCRIPTION_KEY_MEDIA_URI = "android.support.v4.media.description.MEDIA_URI";
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public static final String DESCRIPTION_KEY_NULL_BUNDLE_FLAG = "android.support.v4.media.description.NULL_BUNDLE_FLAG";
    public static final String EXTRA_BT_FOLDER_TYPE = "android.media.extra.BT_FOLDER_TYPE";
    public static final String EXTRA_DOWNLOAD_STATUS = "android.media.extra.DOWNLOAD_STATUS";
    public static final long STATUS_DOWNLOADED = 2;
    public static final long STATUS_DOWNLOADING = 1;
    public static final long STATUS_NOT_DOWNLOADED = 0;
    public final String h;
    public final CharSequence i;
    public final CharSequence j;
    public final CharSequence k;
    public final Bitmap l;
    public final Uri m;
    public final Bundle n;
    public final Uri o;
    public Object p;

    /* loaded from: classes.dex */
    public static final class Builder {

        /* renamed from: a  reason: collision with root package name */
        public String f306a;
        public CharSequence b;
        public CharSequence c;
        public CharSequence d;
        public Bitmap e;
        public Uri f;
        public Bundle g;
        public Uri h;

        public MediaDescriptionCompat build() {
            return new MediaDescriptionCompat(this.f306a, this.b, this.c, this.d, this.e, this.f, this.g, this.h);
        }

        public Builder setDescription(@Nullable CharSequence charSequence) {
            this.d = charSequence;
            return this;
        }

        public Builder setExtras(@Nullable Bundle bundle) {
            this.g = bundle;
            return this;
        }

        public Builder setIconBitmap(@Nullable Bitmap bitmap) {
            this.e = bitmap;
            return this;
        }

        public Builder setIconUri(@Nullable Uri uri) {
            this.f = uri;
            return this;
        }

        public Builder setMediaId(@Nullable String str) {
            this.f306a = str;
            return this;
        }

        public Builder setMediaUri(@Nullable Uri uri) {
            this.h = uri;
            return this;
        }

        public Builder setSubtitle(@Nullable CharSequence charSequence) {
            this.c = charSequence;
            return this;
        }

        public Builder setTitle(@Nullable CharSequence charSequence) {
            this.b = charSequence;
            return this;
        }
    }

    /* loaded from: classes.dex */
    public static class a implements Parcelable.Creator<MediaDescriptionCompat> {
        @Override // android.os.Parcelable.Creator
        /* renamed from: a */
        public MediaDescriptionCompat createFromParcel(Parcel parcel) {
            if (Build.VERSION.SDK_INT < 21) {
                return new MediaDescriptionCompat(parcel);
            }
            return MediaDescriptionCompat.fromMediaDescription(d.a(parcel));
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: b */
        public MediaDescriptionCompat[] newArray(int i) {
            return new MediaDescriptionCompat[i];
        }
    }

    public MediaDescriptionCompat(String str, CharSequence charSequence, CharSequence charSequence2, CharSequence charSequence3, Bitmap bitmap, Uri uri, Bundle bundle, Uri uri2) {
        this.h = str;
        this.i = charSequence;
        this.j = charSequence2;
        this.k = charSequence3;
        this.l = bitmap;
        this.m = uri;
        this.n = bundle;
        this.o = uri2;
    }

    /* JADX WARN: Removed duplicated region for block: B:20:0x0069  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x006d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static android.support.v4.media.MediaDescriptionCompat fromMediaDescription(java.lang.Object r9) {
        /*
            r0 = 0
            if (r9 == 0) goto L7e
            int r1 = android.os.Build.VERSION.SDK_INT
            r2 = 21
            if (r1 < r2) goto L7e
            android.support.v4.media.MediaDescriptionCompat$Builder r2 = new android.support.v4.media.MediaDescriptionCompat$Builder
            r2.<init>()
            java.lang.String r3 = android.support.v4.media.d.f(r9)
            r2.setMediaId(r3)
            java.lang.CharSequence r3 = android.support.v4.media.d.h(r9)
            r2.setTitle(r3)
            java.lang.CharSequence r3 = android.support.v4.media.d.g(r9)
            r2.setSubtitle(r3)
            java.lang.CharSequence r3 = android.support.v4.media.d.b(r9)
            r2.setDescription(r3)
            android.graphics.Bitmap r3 = android.support.v4.media.d.d(r9)
            r2.setIconBitmap(r3)
            android.net.Uri r3 = android.support.v4.media.d.e(r9)
            r2.setIconUri(r3)
            android.os.Bundle r3 = android.support.v4.media.d.c(r9)
            java.lang.String r4 = "android.support.v4.media.description.MEDIA_URI"
            if (r3 == 0) goto L4a
            android.support.v4.media.session.MediaSessionCompat.ensureClassLoader(r3)
            android.os.Parcelable r5 = r3.getParcelable(r4)
            android.net.Uri r5 = (android.net.Uri) r5
            goto L4b
        L4a:
            r5 = r0
        L4b:
            if (r5 == 0) goto L63
            java.lang.String r6 = "android.support.v4.media.description.NULL_BUNDLE_FLAG"
            boolean r7 = r3.containsKey(r6)
            if (r7 == 0) goto L5d
            int r7 = r3.size()
            r8 = 2
            if (r7 != r8) goto L5d
            goto L64
        L5d:
            r3.remove(r4)
            r3.remove(r6)
        L63:
            r0 = r3
        L64:
            r2.setExtras(r0)
            if (r5 == 0) goto L6d
            r2.setMediaUri(r5)
            goto L78
        L6d:
            r0 = 23
            if (r1 < r0) goto L78
            android.net.Uri r0 = android.support.v4.media.e.a(r9)
            r2.setMediaUri(r0)
        L78:
            android.support.v4.media.MediaDescriptionCompat r0 = r2.build()
            r0.p = r9
        L7e:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.media.MediaDescriptionCompat.fromMediaDescription(java.lang.Object):android.support.v4.media.MediaDescriptionCompat");
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Nullable
    public CharSequence getDescription() {
        return this.k;
    }

    @Nullable
    public Bundle getExtras() {
        return this.n;
    }

    @Nullable
    public Bitmap getIconBitmap() {
        return this.l;
    }

    @Nullable
    public Uri getIconUri() {
        return this.m;
    }

    public Object getMediaDescription() {
        int i;
        Object obj = this.p;
        if (obj != null || (i = Build.VERSION.SDK_INT) < 21) {
            return obj;
        }
        Object b = d.a.b();
        d.a.g(b, this.h);
        d.a.i(b, this.i);
        d.a.h(b, this.j);
        d.a.c(b, this.k);
        d.a.e(b, this.l);
        d.a.f(b, this.m);
        Bundle bundle = this.n;
        if (i < 23 && this.o != null) {
            if (bundle == null) {
                bundle = new Bundle();
                bundle.putBoolean(DESCRIPTION_KEY_NULL_BUNDLE_FLAG, true);
            }
            bundle.putParcelable(DESCRIPTION_KEY_MEDIA_URI, this.o);
        }
        d.a.d(b, bundle);
        if (i >= 23) {
            e.a.a(b, this.o);
        }
        Object a2 = d.a.a(b);
        this.p = a2;
        return a2;
    }

    @Nullable
    public String getMediaId() {
        return this.h;
    }

    @Nullable
    public Uri getMediaUri() {
        return this.o;
    }

    @Nullable
    public CharSequence getSubtitle() {
        return this.j;
    }

    @Nullable
    public CharSequence getTitle() {
        return this.i;
    }

    public String toString() {
        return ((Object) this.i) + ", " + ((Object) this.j) + ", " + ((Object) this.k);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        if (Build.VERSION.SDK_INT < 21) {
            parcel.writeString(this.h);
            TextUtils.writeToParcel(this.i, parcel, i);
            TextUtils.writeToParcel(this.j, parcel, i);
            TextUtils.writeToParcel(this.k, parcel, i);
            parcel.writeParcelable(this.l, i);
            parcel.writeParcelable(this.m, i);
            parcel.writeBundle(this.n);
            parcel.writeParcelable(this.o, i);
            return;
        }
        d.i(getMediaDescription(), parcel, i);
    }

    public MediaDescriptionCompat(Parcel parcel) {
        this.h = parcel.readString();
        this.i = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
        this.j = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
        this.k = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
        ClassLoader classLoader = MediaDescriptionCompat.class.getClassLoader();
        this.l = (Bitmap) parcel.readParcelable(classLoader);
        this.m = (Uri) parcel.readParcelable(classLoader);
        this.n = parcel.readBundle(classLoader);
        this.o = (Uri) parcel.readParcelable(classLoader);
    }
}
