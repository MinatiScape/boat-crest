package androidx.emoji2.text;

import android.content.Context;
import android.os.Build;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.annotation.WorkerThread;
import androidx.core.os.TraceCompat;
import androidx.emoji2.text.EmojiCompat;
import androidx.emoji2.text.EmojiCompatInitializer;
import androidx.lifecycle.DefaultLifecycleObserver;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ProcessLifecycleInitializer;
import androidx.startup.AppInitializer;
import androidx.startup.Initializer;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ThreadPoolExecutor;
/* loaded from: classes.dex */
public class EmojiCompatInitializer implements Initializer<Boolean> {

    @RequiresApi(19)
    /* loaded from: classes.dex */
    public static class a extends EmojiCompat.Config {
        public a(Context context) {
            super(new b(context));
            setMetadataLoadStrategy(1);
        }
    }

    @RequiresApi(19)
    /* loaded from: classes.dex */
    public static class b implements EmojiCompat.MetadataRepoLoader {

        /* renamed from: a  reason: collision with root package name */
        public final Context f1255a;

        /* loaded from: classes.dex */
        public class a extends EmojiCompat.MetadataRepoLoaderCallback {

            /* renamed from: a  reason: collision with root package name */
            public final /* synthetic */ EmojiCompat.MetadataRepoLoaderCallback f1256a;
            public final /* synthetic */ ThreadPoolExecutor b;

            public a(b bVar, EmojiCompat.MetadataRepoLoaderCallback metadataRepoLoaderCallback, ThreadPoolExecutor threadPoolExecutor) {
                this.f1256a = metadataRepoLoaderCallback;
                this.b = threadPoolExecutor;
            }

            @Override // androidx.emoji2.text.EmojiCompat.MetadataRepoLoaderCallback
            public void onFailed(@Nullable Throwable th) {
                try {
                    this.f1256a.onFailed(th);
                } finally {
                    this.b.shutdown();
                }
            }

            @Override // androidx.emoji2.text.EmojiCompat.MetadataRepoLoaderCallback
            public void onLoaded(@NonNull MetadataRepo metadataRepo) {
                try {
                    this.f1256a.onLoaded(metadataRepo);
                } finally {
                    this.b.shutdown();
                }
            }
        }

        public b(Context context) {
            this.f1255a = context.getApplicationContext();
        }

        @WorkerThread
        /* renamed from: b */
        public void c(@NonNull EmojiCompat.MetadataRepoLoaderCallback metadataRepoLoaderCallback, @NonNull ThreadPoolExecutor threadPoolExecutor) {
            try {
                FontRequestEmojiCompatConfig create = DefaultEmojiCompatConfig.create(this.f1255a);
                if (create != null) {
                    create.setLoadingExecutor(threadPoolExecutor);
                    create.getMetadataRepoLoader().load(new a(this, metadataRepoLoaderCallback, threadPoolExecutor));
                    return;
                }
                throw new RuntimeException("EmojiCompat font provider not available on this device.");
            } catch (Throwable th) {
                metadataRepoLoaderCallback.onFailed(th);
                threadPoolExecutor.shutdown();
            }
        }

        @Override // androidx.emoji2.text.EmojiCompat.MetadataRepoLoader
        public void load(@NonNull final EmojiCompat.MetadataRepoLoaderCallback metadataRepoLoaderCallback) {
            final ThreadPoolExecutor c = androidx.emoji2.text.c.c("EmojiCompatInitializer");
            c.execute(new Runnable() { // from class: androidx.emoji2.text.e
                @Override // java.lang.Runnable
                public final void run() {
                    EmojiCompatInitializer.b.this.c(metadataRepoLoaderCallback, c);
                }
            });
        }
    }

    /* loaded from: classes.dex */
    public static class c implements Runnable {
        @Override // java.lang.Runnable
        public void run() {
            try {
                TraceCompat.beginSection("EmojiCompat.EmojiCompatInitializer.run");
                if (EmojiCompat.isConfigured()) {
                    EmojiCompat.get().load();
                }
            } finally {
                TraceCompat.endSection();
            }
        }
    }

    @RequiresApi(19)
    public void a(@NonNull Context context) {
        final Lifecycle lifecycle = ((LifecycleOwner) AppInitializer.getInstance(context).initializeComponent(ProcessLifecycleInitializer.class)).getLifecycle();
        lifecycle.addObserver(new DefaultLifecycleObserver() { // from class: androidx.emoji2.text.EmojiCompatInitializer.1
            @Override // androidx.lifecycle.DefaultLifecycleObserver, androidx.lifecycle.d
            public void onResume(@NonNull LifecycleOwner lifecycleOwner) {
                EmojiCompatInitializer.this.b();
                lifecycle.removeObserver(this);
            }
        });
    }

    @RequiresApi(19)
    public void b() {
        androidx.emoji2.text.c.e().postDelayed(new c(), 500L);
    }

    @Override // androidx.startup.Initializer
    @NonNull
    public List<Class<? extends Initializer<?>>> dependencies() {
        return Collections.singletonList(ProcessLifecycleInitializer.class);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // androidx.startup.Initializer
    @NonNull
    public Boolean create(@NonNull Context context) {
        if (Build.VERSION.SDK_INT >= 19) {
            EmojiCompat.init(new a(context));
            a(context);
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }
}
