package androidx.emoji2.text;

import android.content.Context;
import android.content.pm.PackageManager;
import android.database.ContentObserver;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Handler;
import android.os.SystemClock;
import androidx.annotation.GuardedBy;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.annotation.RestrictTo;
import androidx.annotation.WorkerThread;
import androidx.core.graphics.TypefaceCompatUtil;
import androidx.core.os.TraceCompat;
import androidx.core.provider.FontRequest;
import androidx.core.provider.FontsContractCompat;
import androidx.core.util.Preconditions;
import androidx.emoji2.text.EmojiCompat;
import androidx.emoji2.text.FontRequestEmojiCompatConfig;
import java.nio.ByteBuffer;
import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;
/* loaded from: classes.dex */
public class FontRequestEmojiCompatConfig extends EmojiCompat.Config {
    public static final FontProviderHelper j = new FontProviderHelper();

    /* loaded from: classes.dex */
    public static class ExponentialBackoffRetryPolicy extends RetryPolicy {

        /* renamed from: a  reason: collision with root package name */
        public final long f1258a;
        public long b;

        public ExponentialBackoffRetryPolicy(long j) {
            this.f1258a = j;
        }

        @Override // androidx.emoji2.text.FontRequestEmojiCompatConfig.RetryPolicy
        public long getRetryDelay() {
            if (this.b == 0) {
                this.b = SystemClock.uptimeMillis();
                return 0L;
            }
            long uptimeMillis = SystemClock.uptimeMillis() - this.b;
            if (uptimeMillis > this.f1258a) {
                return -1L;
            }
            return Math.min(Math.max(uptimeMillis, 1000L), this.f1258a - uptimeMillis);
        }
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY})
    /* loaded from: classes.dex */
    public static class FontProviderHelper {
        @Nullable
        public Typeface buildTypeface(@NonNull Context context, @NonNull FontsContractCompat.FontInfo fontInfo) throws PackageManager.NameNotFoundException {
            return FontsContractCompat.buildTypeface(context, null, new FontsContractCompat.FontInfo[]{fontInfo});
        }

        @NonNull
        public FontsContractCompat.FontFamilyResult fetchFonts(@NonNull Context context, @NonNull FontRequest fontRequest) throws PackageManager.NameNotFoundException {
            return FontsContractCompat.fetchFonts(context, null, fontRequest);
        }

        public void registerObserver(@NonNull Context context, @NonNull Uri uri, @NonNull ContentObserver contentObserver) {
            context.getContentResolver().registerContentObserver(uri, false, contentObserver);
        }

        public void unregisterObserver(@NonNull Context context, @NonNull ContentObserver contentObserver) {
            context.getContentResolver().unregisterContentObserver(contentObserver);
        }
    }

    /* loaded from: classes.dex */
    public static abstract class RetryPolicy {
        public abstract long getRetryDelay();
    }

    /* loaded from: classes.dex */
    public static class a implements EmojiCompat.MetadataRepoLoader {
        @NonNull

        /* renamed from: a  reason: collision with root package name */
        public final Context f1259a;
        @NonNull
        public final FontRequest b;
        @NonNull
        public final FontProviderHelper c;
        @NonNull
        public final Object d = new Object();
        @Nullable
        @GuardedBy("mLock")
        public Handler e;
        @Nullable
        @GuardedBy("mLock")
        public Executor f;
        @Nullable
        @GuardedBy("mLock")
        public ThreadPoolExecutor g;
        @Nullable
        @GuardedBy("mLock")
        public RetryPolicy h;
        @Nullable
        @GuardedBy("mLock")
        public EmojiCompat.MetadataRepoLoaderCallback i;
        @Nullable
        @GuardedBy("mLock")
        public ContentObserver j;
        @Nullable
        @GuardedBy("mLock")
        public Runnable k;

        /* renamed from: androidx.emoji2.text.FontRequestEmojiCompatConfig$a$a  reason: collision with other inner class name */
        /* loaded from: classes.dex */
        public class C0144a extends ContentObserver {
            public C0144a(Handler handler) {
                super(handler);
            }

            @Override // android.database.ContentObserver
            public void onChange(boolean z, Uri uri) {
                a.this.c();
            }
        }

        public a(@NonNull Context context, @NonNull FontRequest fontRequest, @NonNull FontProviderHelper fontProviderHelper) {
            Preconditions.checkNotNull(context, "Context cannot be null");
            Preconditions.checkNotNull(fontRequest, "FontRequest cannot be null");
            this.f1259a = context.getApplicationContext();
            this.b = fontRequest;
            this.c = fontProviderHelper;
        }

        public final void a() {
            synchronized (this.d) {
                this.i = null;
                ContentObserver contentObserver = this.j;
                if (contentObserver != null) {
                    this.c.unregisterObserver(this.f1259a, contentObserver);
                    this.j = null;
                }
                Handler handler = this.e;
                if (handler != null) {
                    handler.removeCallbacks(this.k);
                }
                this.e = null;
                ThreadPoolExecutor threadPoolExecutor = this.g;
                if (threadPoolExecutor != null) {
                    threadPoolExecutor.shutdown();
                }
                this.f = null;
                this.g = null;
            }
        }

        @RequiresApi(19)
        @WorkerThread
        public void b() {
            synchronized (this.d) {
                if (this.i == null) {
                    return;
                }
                try {
                    FontsContractCompat.FontInfo d = d();
                    int resultCode = d.getResultCode();
                    if (resultCode == 2) {
                        synchronized (this.d) {
                            RetryPolicy retryPolicy = this.h;
                            if (retryPolicy != null) {
                                long retryDelay = retryPolicy.getRetryDelay();
                                if (retryDelay >= 0) {
                                    e(d.getUri(), retryDelay);
                                    return;
                                }
                            }
                        }
                    }
                    if (resultCode == 0) {
                        TraceCompat.beginSection("EmojiCompat.FontRequestEmojiCompatConfig.buildTypeface");
                        Typeface buildTypeface = this.c.buildTypeface(this.f1259a, d);
                        ByteBuffer mmap = TypefaceCompatUtil.mmap(this.f1259a, null, d.getUri());
                        if (mmap != null && buildTypeface != null) {
                            MetadataRepo create = MetadataRepo.create(buildTypeface, mmap);
                            TraceCompat.endSection();
                            synchronized (this.d) {
                                EmojiCompat.MetadataRepoLoaderCallback metadataRepoLoaderCallback = this.i;
                                if (metadataRepoLoaderCallback != null) {
                                    metadataRepoLoaderCallback.onLoaded(create);
                                }
                            }
                            a();
                            return;
                        }
                        throw new RuntimeException("Unable to open file.");
                    }
                    throw new RuntimeException("fetchFonts result is not OK. (" + resultCode + ")");
                } catch (Throwable th) {
                    synchronized (this.d) {
                        EmojiCompat.MetadataRepoLoaderCallback metadataRepoLoaderCallback2 = this.i;
                        if (metadataRepoLoaderCallback2 != null) {
                            metadataRepoLoaderCallback2.onFailed(th);
                        }
                        a();
                    }
                }
            }
        }

        @RequiresApi(19)
        public void c() {
            synchronized (this.d) {
                if (this.i == null) {
                    return;
                }
                if (this.f == null) {
                    ThreadPoolExecutor c = c.c("emojiCompat");
                    this.g = c;
                    this.f = c;
                }
                this.f.execute(new Runnable() { // from class: androidx.emoji2.text.g
                    @Override // java.lang.Runnable
                    public final void run() {
                        FontRequestEmojiCompatConfig.a.this.b();
                    }
                });
            }
        }

        @WorkerThread
        public final FontsContractCompat.FontInfo d() {
            try {
                FontsContractCompat.FontFamilyResult fetchFonts = this.c.fetchFonts(this.f1259a, this.b);
                if (fetchFonts.getStatusCode() == 0) {
                    FontsContractCompat.FontInfo[] fonts = fetchFonts.getFonts();
                    if (fonts != null && fonts.length != 0) {
                        return fonts[0];
                    }
                    throw new RuntimeException("fetchFonts failed (empty result)");
                }
                throw new RuntimeException("fetchFonts failed (" + fetchFonts.getStatusCode() + ")");
            } catch (PackageManager.NameNotFoundException e) {
                throw new RuntimeException("provider not found", e);
            }
        }

        @RequiresApi(19)
        @WorkerThread
        public final void e(Uri uri, long j) {
            synchronized (this.d) {
                Handler handler = this.e;
                if (handler == null) {
                    handler = c.e();
                    this.e = handler;
                }
                if (this.j == null) {
                    C0144a c0144a = new C0144a(handler);
                    this.j = c0144a;
                    this.c.registerObserver(this.f1259a, uri, c0144a);
                }
                if (this.k == null) {
                    this.k = new Runnable() { // from class: androidx.emoji2.text.h
                        @Override // java.lang.Runnable
                        public final void run() {
                            FontRequestEmojiCompatConfig.a.this.c();
                        }
                    };
                }
                handler.postDelayed(this.k, j);
            }
        }

        public void f(@NonNull Executor executor) {
            synchronized (this.d) {
                this.f = executor;
            }
        }

        public void g(@Nullable RetryPolicy retryPolicy) {
            synchronized (this.d) {
                this.h = retryPolicy;
            }
        }

        @Override // androidx.emoji2.text.EmojiCompat.MetadataRepoLoader
        @RequiresApi(19)
        public void load(@NonNull EmojiCompat.MetadataRepoLoaderCallback metadataRepoLoaderCallback) {
            Preconditions.checkNotNull(metadataRepoLoaderCallback, "LoaderCallback cannot be null");
            synchronized (this.d) {
                this.i = metadataRepoLoaderCallback;
            }
            c();
        }
    }

    public FontRequestEmojiCompatConfig(@NonNull Context context, @NonNull FontRequest fontRequest) {
        super(new a(context, fontRequest, j));
    }

    @NonNull
    @Deprecated
    public FontRequestEmojiCompatConfig setHandler(@Nullable Handler handler) {
        if (handler == null) {
            return this;
        }
        setLoadingExecutor(c.b(handler));
        return this;
    }

    @NonNull
    public FontRequestEmojiCompatConfig setLoadingExecutor(@NonNull Executor executor) {
        ((a) getMetadataRepoLoader()).f(executor);
        return this;
    }

    @NonNull
    public FontRequestEmojiCompatConfig setRetryPolicy(@Nullable RetryPolicy retryPolicy) {
        ((a) getMetadataRepoLoader()).g(retryPolicy);
        return this;
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public FontRequestEmojiCompatConfig(@NonNull Context context, @NonNull FontRequest fontRequest, @NonNull FontProviderHelper fontProviderHelper) {
        super(new a(context, fontRequest, fontProviderHelper));
    }
}
