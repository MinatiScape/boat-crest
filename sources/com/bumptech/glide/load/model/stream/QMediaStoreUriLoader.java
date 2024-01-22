package com.bumptech.glide.load.model.stream;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.ParcelFileDescriptor;
import android.provider.MediaStore;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.Options;
import com.bumptech.glide.load.data.DataFetcher;
import com.bumptech.glide.load.data.mediastore.MediaStoreUtil;
import com.bumptech.glide.load.model.ModelLoader;
import com.bumptech.glide.load.model.ModelLoaderFactory;
import com.bumptech.glide.load.model.MultiModelLoaderFactory;
import com.bumptech.glide.signature.ObjectKey;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
@RequiresApi(29)
/* loaded from: classes2.dex */
public final class QMediaStoreUriLoader<DataT> implements ModelLoader<Uri, DataT> {

    /* renamed from: a  reason: collision with root package name */
    public final Context f2439a;
    public final ModelLoader<File, DataT> b;
    public final ModelLoader<Uri, DataT> c;
    public final Class<DataT> d;

    @RequiresApi(29)
    /* loaded from: classes2.dex */
    public static final class FileDescriptorFactory extends a<ParcelFileDescriptor> {
        public FileDescriptorFactory(Context context) {
            super(context, ParcelFileDescriptor.class);
        }
    }

    @RequiresApi(29)
    /* loaded from: classes2.dex */
    public static final class InputStreamFactory extends a<InputStream> {
        public InputStreamFactory(Context context) {
            super(context, InputStream.class);
        }
    }

    /* loaded from: classes2.dex */
    public static abstract class a<DataT> implements ModelLoaderFactory<Uri, DataT> {

        /* renamed from: a  reason: collision with root package name */
        public final Context f2440a;
        public final Class<DataT> b;

        public a(Context context, Class<DataT> cls) {
            this.f2440a = context;
            this.b = cls;
        }

        @Override // com.bumptech.glide.load.model.ModelLoaderFactory
        @NonNull
        public final ModelLoader<Uri, DataT> build(@NonNull MultiModelLoaderFactory multiModelLoaderFactory) {
            return new QMediaStoreUriLoader(this.f2440a, multiModelLoaderFactory.build(File.class, this.b), multiModelLoaderFactory.build(Uri.class, this.b), this.b);
        }

        @Override // com.bumptech.glide.load.model.ModelLoaderFactory
        public final void teardown() {
        }
    }

    /* loaded from: classes2.dex */
    public static final class b<DataT> implements DataFetcher<DataT> {
        public static final String[] r = {"_data"};
        public final Context h;
        public final ModelLoader<File, DataT> i;
        public final ModelLoader<Uri, DataT> j;
        public final Uri k;
        public final int l;
        public final int m;
        public final Options n;
        public final Class<DataT> o;
        public volatile boolean p;
        @Nullable
        public volatile DataFetcher<DataT> q;

        public b(Context context, ModelLoader<File, DataT> modelLoader, ModelLoader<Uri, DataT> modelLoader2, Uri uri, int i, int i2, Options options, Class<DataT> cls) {
            this.h = context.getApplicationContext();
            this.i = modelLoader;
            this.j = modelLoader2;
            this.k = uri;
            this.l = i;
            this.m = i2;
            this.n = options;
            this.o = cls;
        }

        @Nullable
        public final ModelLoader.LoadData<DataT> a() throws FileNotFoundException {
            if (Environment.isExternalStorageLegacy()) {
                return this.i.buildLoadData(d(this.k), this.l, this.m, this.n);
            }
            return this.j.buildLoadData(c() ? MediaStore.setRequireOriginal(this.k) : this.k, this.l, this.m, this.n);
        }

        @Nullable
        public final DataFetcher<DataT> b() throws FileNotFoundException {
            ModelLoader.LoadData<DataT> a2 = a();
            if (a2 != null) {
                return a2.fetcher;
            }
            return null;
        }

        public final boolean c() {
            return this.h.checkSelfPermission("android.permission.ACCESS_MEDIA_LOCATION") == 0;
        }

        @Override // com.bumptech.glide.load.data.DataFetcher
        public void cancel() {
            this.p = true;
            DataFetcher<DataT> dataFetcher = this.q;
            if (dataFetcher != null) {
                dataFetcher.cancel();
            }
        }

        @Override // com.bumptech.glide.load.data.DataFetcher
        public void cleanup() {
            DataFetcher<DataT> dataFetcher = this.q;
            if (dataFetcher != null) {
                dataFetcher.cleanup();
            }
        }

        @NonNull
        public final File d(Uri uri) throws FileNotFoundException {
            Cursor cursor = null;
            try {
                Cursor query = this.h.getContentResolver().query(uri, r, null, null, null);
                if (query != null && query.moveToFirst()) {
                    String string = query.getString(query.getColumnIndexOrThrow("_data"));
                    if (!TextUtils.isEmpty(string)) {
                        File file = new File(string);
                        query.close();
                        return file;
                    }
                    throw new FileNotFoundException("File path was empty in media store for: " + uri);
                }
                throw new FileNotFoundException("Failed to media store entry for: " + uri);
            } catch (Throwable th) {
                if (0 != 0) {
                    cursor.close();
                }
                throw th;
            }
        }

        @Override // com.bumptech.glide.load.data.DataFetcher
        @NonNull
        public Class<DataT> getDataClass() {
            return this.o;
        }

        @Override // com.bumptech.glide.load.data.DataFetcher
        @NonNull
        public DataSource getDataSource() {
            return DataSource.LOCAL;
        }

        @Override // com.bumptech.glide.load.data.DataFetcher
        public void loadData(@NonNull Priority priority, @NonNull DataFetcher.DataCallback<? super DataT> dataCallback) {
            try {
                DataFetcher<DataT> b = b();
                if (b == null) {
                    dataCallback.onLoadFailed(new IllegalArgumentException("Failed to build fetcher for: " + this.k));
                    return;
                }
                this.q = b;
                if (this.p) {
                    cancel();
                } else {
                    b.loadData(priority, dataCallback);
                }
            } catch (FileNotFoundException e) {
                dataCallback.onLoadFailed(e);
            }
        }
    }

    public QMediaStoreUriLoader(Context context, ModelLoader<File, DataT> modelLoader, ModelLoader<Uri, DataT> modelLoader2, Class<DataT> cls) {
        this.f2439a = context.getApplicationContext();
        this.b = modelLoader;
        this.c = modelLoader2;
        this.d = cls;
    }

    @Override // com.bumptech.glide.load.model.ModelLoader
    public ModelLoader.LoadData<DataT> buildLoadData(@NonNull Uri uri, int i, int i2, @NonNull Options options) {
        return new ModelLoader.LoadData<>(new ObjectKey(uri), new b(this.f2439a, this.b, this.c, uri, i, i2, options, this.d));
    }

    @Override // com.bumptech.glide.load.model.ModelLoader
    public boolean handles(@NonNull Uri uri) {
        return Build.VERSION.SDK_INT >= 29 && MediaStoreUtil.isMediaStoreUri(uri);
    }
}
