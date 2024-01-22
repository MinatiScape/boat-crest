package com.bumptech.glide.load.model;

import androidx.annotation.NonNull;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.Options;
import com.bumptech.glide.load.data.DataFetcher;
import com.bumptech.glide.load.model.ModelLoader;
import com.bumptech.glide.signature.ObjectKey;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.ByteBuffer;
/* loaded from: classes2.dex */
public class ByteArrayLoader<Data> implements ModelLoader<byte[], Data> {

    /* renamed from: a  reason: collision with root package name */
    public final Converter<Data> f2399a;

    /* loaded from: classes2.dex */
    public static class ByteBufferFactory implements ModelLoaderFactory<byte[], ByteBuffer> {

        /* loaded from: classes2.dex */
        public class a implements Converter<ByteBuffer> {
            public a(ByteBufferFactory byteBufferFactory) {
            }

            @Override // com.bumptech.glide.load.model.ByteArrayLoader.Converter
            /* renamed from: a */
            public ByteBuffer convert(byte[] bArr) {
                return ByteBuffer.wrap(bArr);
            }

            @Override // com.bumptech.glide.load.model.ByteArrayLoader.Converter
            public Class<ByteBuffer> getDataClass() {
                return ByteBuffer.class;
            }
        }

        @Override // com.bumptech.glide.load.model.ModelLoaderFactory
        @NonNull
        public ModelLoader<byte[], ByteBuffer> build(@NonNull MultiModelLoaderFactory multiModelLoaderFactory) {
            return new ByteArrayLoader(new a(this));
        }

        @Override // com.bumptech.glide.load.model.ModelLoaderFactory
        public void teardown() {
        }
    }

    /* loaded from: classes2.dex */
    public interface Converter<Data> {
        Data convert(byte[] bArr);

        Class<Data> getDataClass();
    }

    /* loaded from: classes2.dex */
    public static class StreamFactory implements ModelLoaderFactory<byte[], InputStream> {

        /* loaded from: classes2.dex */
        public class a implements Converter<InputStream> {
            public a(StreamFactory streamFactory) {
            }

            @Override // com.bumptech.glide.load.model.ByteArrayLoader.Converter
            /* renamed from: a */
            public InputStream convert(byte[] bArr) {
                return new ByteArrayInputStream(bArr);
            }

            @Override // com.bumptech.glide.load.model.ByteArrayLoader.Converter
            public Class<InputStream> getDataClass() {
                return InputStream.class;
            }
        }

        @Override // com.bumptech.glide.load.model.ModelLoaderFactory
        @NonNull
        public ModelLoader<byte[], InputStream> build(@NonNull MultiModelLoaderFactory multiModelLoaderFactory) {
            return new ByteArrayLoader(new a(this));
        }

        @Override // com.bumptech.glide.load.model.ModelLoaderFactory
        public void teardown() {
        }
    }

    /* loaded from: classes2.dex */
    public static class a<Data> implements DataFetcher<Data> {
        public final byte[] h;
        public final Converter<Data> i;

        public a(byte[] bArr, Converter<Data> converter) {
            this.h = bArr;
            this.i = converter;
        }

        @Override // com.bumptech.glide.load.data.DataFetcher
        public void cancel() {
        }

        @Override // com.bumptech.glide.load.data.DataFetcher
        public void cleanup() {
        }

        @Override // com.bumptech.glide.load.data.DataFetcher
        @NonNull
        public Class<Data> getDataClass() {
            return this.i.getDataClass();
        }

        @Override // com.bumptech.glide.load.data.DataFetcher
        @NonNull
        public DataSource getDataSource() {
            return DataSource.LOCAL;
        }

        @Override // com.bumptech.glide.load.data.DataFetcher
        public void loadData(@NonNull Priority priority, @NonNull DataFetcher.DataCallback<? super Data> dataCallback) {
            dataCallback.onDataReady((Data) this.i.convert(this.h));
        }
    }

    public ByteArrayLoader(Converter<Data> converter) {
        this.f2399a = converter;
    }

    @Override // com.bumptech.glide.load.model.ModelLoader
    public boolean handles(@NonNull byte[] bArr) {
        return true;
    }

    @Override // com.bumptech.glide.load.model.ModelLoader
    public ModelLoader.LoadData<Data> buildLoadData(@NonNull byte[] bArr, int i, int i2, @NonNull Options options) {
        return new ModelLoader.LoadData<>(new ObjectKey(bArr), new a(bArr, this.f2399a));
    }
}
