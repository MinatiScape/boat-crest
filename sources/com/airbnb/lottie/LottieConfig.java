package com.airbnb.lottie;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.airbnb.lottie.network.LottieNetworkCacheProvider;
import com.airbnb.lottie.network.LottieNetworkFetcher;
import java.io.File;
/* loaded from: classes.dex */
public class LottieConfig {
    @Nullable

    /* renamed from: a  reason: collision with root package name */
    public final LottieNetworkFetcher f1979a;
    @Nullable
    public final LottieNetworkCacheProvider b;
    public final boolean c;
    public final boolean d;
    public final boolean e;

    /* loaded from: classes.dex */
    public static final class Builder {
        @Nullable

        /* renamed from: a  reason: collision with root package name */
        public LottieNetworkFetcher f1980a;
        @Nullable
        public LottieNetworkCacheProvider b;
        public boolean c = false;
        public boolean d = true;
        public boolean e = true;

        /* loaded from: classes.dex */
        public class a implements LottieNetworkCacheProvider {

            /* renamed from: a  reason: collision with root package name */
            public final /* synthetic */ File f1981a;

            public a(Builder builder, File file) {
                this.f1981a = file;
            }

            @Override // com.airbnb.lottie.network.LottieNetworkCacheProvider
            @NonNull
            public File getCacheDir() {
                if (this.f1981a.isDirectory()) {
                    return this.f1981a;
                }
                throw new IllegalArgumentException("cache file must be a directory");
            }
        }

        /* loaded from: classes.dex */
        public class b implements LottieNetworkCacheProvider {

            /* renamed from: a  reason: collision with root package name */
            public final /* synthetic */ LottieNetworkCacheProvider f1982a;

            public b(Builder builder, LottieNetworkCacheProvider lottieNetworkCacheProvider) {
                this.f1982a = lottieNetworkCacheProvider;
            }

            @Override // com.airbnb.lottie.network.LottieNetworkCacheProvider
            @NonNull
            public File getCacheDir() {
                File cacheDir = this.f1982a.getCacheDir();
                if (cacheDir.isDirectory()) {
                    return cacheDir;
                }
                throw new IllegalArgumentException("cache file must be a directory");
            }
        }

        @NonNull
        public LottieConfig build() {
            return new LottieConfig(this.f1980a, this.b, this.c, this.d, this.e);
        }

        @NonNull
        public Builder setDisablePathInterpolatorCache(boolean z) {
            this.e = z;
            return this;
        }

        @NonNull
        public Builder setEnableNetworkCache(boolean z) {
            this.d = z;
            return this;
        }

        @NonNull
        public Builder setEnableSystraceMarkers(boolean z) {
            this.c = z;
            return this;
        }

        @NonNull
        public Builder setNetworkCacheDir(@NonNull File file) {
            if (this.b == null) {
                this.b = new a(this, file);
                return this;
            }
            throw new IllegalStateException("There is already a cache provider!");
        }

        @NonNull
        public Builder setNetworkCacheProvider(@NonNull LottieNetworkCacheProvider lottieNetworkCacheProvider) {
            if (this.b == null) {
                this.b = new b(this, lottieNetworkCacheProvider);
                return this;
            }
            throw new IllegalStateException("There is already a cache provider!");
        }

        @NonNull
        public Builder setNetworkFetcher(@NonNull LottieNetworkFetcher lottieNetworkFetcher) {
            this.f1980a = lottieNetworkFetcher;
            return this;
        }
    }

    public LottieConfig(@Nullable LottieNetworkFetcher lottieNetworkFetcher, @Nullable LottieNetworkCacheProvider lottieNetworkCacheProvider, boolean z, boolean z2, boolean z3) {
        this.f1979a = lottieNetworkFetcher;
        this.b = lottieNetworkCacheProvider;
        this.c = z;
        this.d = z2;
        this.e = z3;
    }
}
