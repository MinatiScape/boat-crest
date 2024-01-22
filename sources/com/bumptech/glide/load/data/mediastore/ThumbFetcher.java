package com.bumptech.glide.load.data.mediastore;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.data.DataFetcher;
import com.bumptech.glide.load.data.ExifOrientationStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
/* loaded from: classes2.dex */
public class ThumbFetcher implements DataFetcher<InputStream> {
    public final Uri h;
    public final c i;
    public InputStream j;

    /* loaded from: classes2.dex */
    public static class a implements com.bumptech.glide.load.data.mediastore.b {
        public static final String[] b = {"_data"};

        /* renamed from: a  reason: collision with root package name */
        public final ContentResolver f2338a;

        public a(ContentResolver contentResolver) {
            this.f2338a = contentResolver;
        }

        @Override // com.bumptech.glide.load.data.mediastore.b
        public Cursor a(Uri uri) {
            return this.f2338a.query(MediaStore.Images.Thumbnails.EXTERNAL_CONTENT_URI, b, "kind = 1 AND image_id = ?", new String[]{uri.getLastPathSegment()}, null);
        }
    }

    /* loaded from: classes2.dex */
    public static class b implements com.bumptech.glide.load.data.mediastore.b {
        public static final String[] b = {"_data"};

        /* renamed from: a  reason: collision with root package name */
        public final ContentResolver f2339a;

        public b(ContentResolver contentResolver) {
            this.f2339a = contentResolver;
        }

        @Override // com.bumptech.glide.load.data.mediastore.b
        public Cursor a(Uri uri) {
            return this.f2339a.query(MediaStore.Video.Thumbnails.EXTERNAL_CONTENT_URI, b, "kind = 1 AND video_id = ?", new String[]{uri.getLastPathSegment()}, null);
        }
    }

    @VisibleForTesting
    public ThumbFetcher(Uri uri, c cVar) {
        this.h = uri;
        this.i = cVar;
    }

    public static ThumbFetcher a(Context context, Uri uri, com.bumptech.glide.load.data.mediastore.b bVar) {
        return new ThumbFetcher(uri, new c(Glide.get(context).getRegistry().getImageHeaderParsers(), bVar, Glide.get(context).getArrayPool(), context.getContentResolver()));
    }

    public static ThumbFetcher buildImageFetcher(Context context, Uri uri) {
        return a(context, uri, new a(context.getContentResolver()));
    }

    public static ThumbFetcher buildVideoFetcher(Context context, Uri uri) {
        return a(context, uri, new b(context.getContentResolver()));
    }

    public final InputStream b() throws FileNotFoundException {
        InputStream d = this.i.d(this.h);
        int a2 = d != null ? this.i.a(this.h) : -1;
        return a2 != -1 ? new ExifOrientationStream(d, a2) : d;
    }

    @Override // com.bumptech.glide.load.data.DataFetcher
    public void cancel() {
    }

    @Override // com.bumptech.glide.load.data.DataFetcher
    public void cleanup() {
        InputStream inputStream = this.j;
        if (inputStream != null) {
            try {
                inputStream.close();
            } catch (IOException unused) {
            }
        }
    }

    @Override // com.bumptech.glide.load.data.DataFetcher
    @NonNull
    public Class<InputStream> getDataClass() {
        return InputStream.class;
    }

    @Override // com.bumptech.glide.load.data.DataFetcher
    @NonNull
    public DataSource getDataSource() {
        return DataSource.LOCAL;
    }

    @Override // com.bumptech.glide.load.data.DataFetcher
    public void loadData(@NonNull Priority priority, @NonNull DataFetcher.DataCallback<? super InputStream> dataCallback) {
        try {
            InputStream b2 = b();
            this.j = b2;
            dataCallback.onDataReady(b2);
        } catch (FileNotFoundException e) {
            if (Log.isLoggable("MediaStoreThumbFetcher", 3)) {
                Log.d("MediaStoreThumbFetcher", "Failed to find thumbnail file", e);
            }
            dataCallback.onLoadFailed(e);
        }
    }
}
