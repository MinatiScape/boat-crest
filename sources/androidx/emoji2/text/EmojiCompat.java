package androidx.emoji2.text;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.Editable;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import androidx.annotation.AnyThread;
import androidx.annotation.CheckResult;
import androidx.annotation.ColorInt;
import androidx.annotation.GuardedBy;
import androidx.annotation.IntRange;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.annotation.RestrictTo;
import androidx.collection.ArraySet;
import androidx.core.util.Preconditions;
import androidx.emoji2.text.DefaultEmojiCompatConfig;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
@AnyThread
/* loaded from: classes.dex */
public class EmojiCompat {
    public static final String EDITOR_INFO_METAVERSION_KEY = "android.support.text.emoji.emojiCompat_metadataVersion";
    public static final String EDITOR_INFO_REPLACE_ALL_KEY = "android.support.text.emoji.emojiCompat_replaceAll";
    public static final int EMOJI_FALLBACK = 2;
    public static final int EMOJI_SUPPORTED = 1;
    public static final int EMOJI_UNSUPPORTED = 0;
    public static final int LOAD_STATE_DEFAULT = 3;
    public static final int LOAD_STATE_FAILED = 2;
    public static final int LOAD_STATE_LOADING = 0;
    public static final int LOAD_STATE_SUCCEEDED = 1;
    public static final int LOAD_STRATEGY_DEFAULT = 0;
    public static final int LOAD_STRATEGY_MANUAL = 1;
    public static final int REPLACE_STRATEGY_ALL = 1;
    public static final int REPLACE_STRATEGY_DEFAULT = 0;
    public static final int REPLACE_STRATEGY_NON_EXISTENT = 2;
    public static final Object n = new Object();
    public static final Object o = new Object();
    @Nullable
    @GuardedBy("INSTANCE_LOCK")
    public static volatile EmojiCompat p;
    @GuardedBy("CONFIG_LOCK")
    public static volatile boolean q;
    @NonNull
    @GuardedBy("mInitLock")
    public final Set<InitCallback> b;
    @NonNull
    public final b e;
    @NonNull
    public final MetadataRepoLoader f;
    public final boolean g;
    public final boolean h;
    @Nullable
    public final int[] i;
    public final boolean j;
    public final int k;
    public final int l;
    public final GlyphChecker m;
    @NonNull

    /* renamed from: a  reason: collision with root package name */
    public final ReadWriteLock f1251a = new ReentrantReadWriteLock();
    @GuardedBy("mInitLock")
    public volatile int c = 3;
    @NonNull
    public final Handler d = new Handler(Looper.getMainLooper());

    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo({RestrictTo.Scope.LIBRARY})
    /* loaded from: classes.dex */
    public @interface CodepointSequenceMatchResult {
    }

    /* loaded from: classes.dex */
    public static abstract class Config {
        @NonNull

        /* renamed from: a  reason: collision with root package name */
        public final MetadataRepoLoader f1252a;
        public boolean b;
        public boolean c;
        @Nullable
        public int[] d;
        @Nullable
        public Set<InitCallback> e;
        public boolean f;
        public int g = -16711936;
        public int h = 0;
        @NonNull
        public GlyphChecker i = new androidx.emoji2.text.d();

        public Config(@NonNull MetadataRepoLoader metadataRepoLoader) {
            Preconditions.checkNotNull(metadataRepoLoader, "metadataLoader cannot be null.");
            this.f1252a = metadataRepoLoader;
        }

        @NonNull
        public final MetadataRepoLoader getMetadataRepoLoader() {
            return this.f1252a;
        }

        @NonNull
        public Config registerInitCallback(@NonNull InitCallback initCallback) {
            Preconditions.checkNotNull(initCallback, "initCallback cannot be null");
            if (this.e == null) {
                this.e = new ArraySet();
            }
            this.e.add(initCallback);
            return this;
        }

        @NonNull
        public Config setEmojiSpanIndicatorColor(@ColorInt int i) {
            this.g = i;
            return this;
        }

        @NonNull
        public Config setEmojiSpanIndicatorEnabled(boolean z) {
            this.f = z;
            return this;
        }

        @NonNull
        public Config setGlyphChecker(@NonNull GlyphChecker glyphChecker) {
            Preconditions.checkNotNull(glyphChecker, "GlyphChecker cannot be null");
            this.i = glyphChecker;
            return this;
        }

        @NonNull
        public Config setMetadataLoadStrategy(int i) {
            this.h = i;
            return this;
        }

        @NonNull
        public Config setReplaceAll(boolean z) {
            this.b = z;
            return this;
        }

        @NonNull
        public Config setUseEmojiAsDefaultStyle(boolean z) {
            return setUseEmojiAsDefaultStyle(z, null);
        }

        @NonNull
        public Config unregisterInitCallback(@NonNull InitCallback initCallback) {
            Preconditions.checkNotNull(initCallback, "initCallback cannot be null");
            Set<InitCallback> set = this.e;
            if (set != null) {
                set.remove(initCallback);
            }
            return this;
        }

        @NonNull
        public Config setUseEmojiAsDefaultStyle(boolean z, @Nullable List<Integer> list) {
            this.c = z;
            if (z && list != null) {
                this.d = new int[list.size()];
                int i = 0;
                for (Integer num : list) {
                    this.d[i] = num.intValue();
                    i++;
                }
                Arrays.sort(this.d);
            } else {
                this.d = null;
            }
            return this;
        }
    }

    /* loaded from: classes.dex */
    public interface GlyphChecker {
        boolean hasGlyph(@NonNull CharSequence charSequence, @IntRange(from = 0) int i, @IntRange(from = 0) int i2, @IntRange(from = 0) int i3);
    }

    /* loaded from: classes.dex */
    public static abstract class InitCallback {
        public void onFailed(@Nullable Throwable th) {
        }

        public void onInitialized() {
        }
    }

    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo({RestrictTo.Scope.LIBRARY})
    /* loaded from: classes.dex */
    public @interface LoadStrategy {
    }

    /* loaded from: classes.dex */
    public interface MetadataRepoLoader {
        void load(@NonNull MetadataRepoLoaderCallback metadataRepoLoaderCallback);
    }

    /* loaded from: classes.dex */
    public static abstract class MetadataRepoLoaderCallback {
        public abstract void onFailed(@Nullable Throwable th);

        public abstract void onLoaded(@NonNull MetadataRepo metadataRepo);
    }

    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo({RestrictTo.Scope.LIBRARY})
    /* loaded from: classes.dex */
    public @interface ReplaceStrategy {
    }

    @RequiresApi(19)
    /* loaded from: classes.dex */
    public static final class a extends b {
        public volatile f b;
        public volatile MetadataRepo c;

        /* renamed from: androidx.emoji2.text.EmojiCompat$a$a  reason: collision with other inner class name */
        /* loaded from: classes.dex */
        public class C0143a extends MetadataRepoLoaderCallback {
            public C0143a() {
            }

            @Override // androidx.emoji2.text.EmojiCompat.MetadataRepoLoaderCallback
            public void onFailed(@Nullable Throwable th) {
                a.this.f1254a.d(th);
            }

            @Override // androidx.emoji2.text.EmojiCompat.MetadataRepoLoaderCallback
            public void onLoaded(@NonNull MetadataRepo metadataRepo) {
                a.this.h(metadataRepo);
            }
        }

        public a(EmojiCompat emojiCompat) {
            super(emojiCompat);
        }

        @Override // androidx.emoji2.text.EmojiCompat.b
        public String a() {
            String sourceSha = this.c.getMetadataList().sourceSha();
            return sourceSha == null ? "" : sourceSha;
        }

        @Override // androidx.emoji2.text.EmojiCompat.b
        public int b(CharSequence charSequence, int i) {
            return this.b.d(charSequence, i);
        }

        @Override // androidx.emoji2.text.EmojiCompat.b
        public boolean c(@NonNull CharSequence charSequence) {
            return this.b.c(charSequence) == 1;
        }

        @Override // androidx.emoji2.text.EmojiCompat.b
        public boolean d(@NonNull CharSequence charSequence, int i) {
            return this.b.d(charSequence, i) == 1;
        }

        @Override // androidx.emoji2.text.EmojiCompat.b
        public void e() {
            try {
                this.f1254a.f.load(new C0143a());
            } catch (Throwable th) {
                this.f1254a.d(th);
            }
        }

        @Override // androidx.emoji2.text.EmojiCompat.b
        public CharSequence f(@NonNull CharSequence charSequence, int i, int i2, int i3, boolean z) {
            return this.b.j(charSequence, i, i2, i3, z);
        }

        @Override // androidx.emoji2.text.EmojiCompat.b
        public void g(@NonNull EditorInfo editorInfo) {
            editorInfo.extras.putInt(EmojiCompat.EDITOR_INFO_METAVERSION_KEY, this.c.b());
            editorInfo.extras.putBoolean(EmojiCompat.EDITOR_INFO_REPLACE_ALL_KEY, this.f1254a.g);
        }

        public void h(@NonNull MetadataRepo metadataRepo) {
            if (metadataRepo == null) {
                this.f1254a.d(new IllegalArgumentException("metadataRepo cannot be null"));
                return;
            }
            this.c = metadataRepo;
            MetadataRepo metadataRepo2 = this.c;
            d dVar = new d();
            GlyphChecker glyphChecker = this.f1254a.m;
            EmojiCompat emojiCompat = this.f1254a;
            this.b = new f(metadataRepo2, dVar, glyphChecker, emojiCompat.h, emojiCompat.i);
            this.f1254a.e();
        }
    }

    /* loaded from: classes.dex */
    public static class b {

        /* renamed from: a  reason: collision with root package name */
        public final EmojiCompat f1254a;

        public b(EmojiCompat emojiCompat) {
            this.f1254a = emojiCompat;
        }

        public String a() {
            return "";
        }

        public int b(CharSequence charSequence, int i) {
            return 0;
        }

        public boolean c(@NonNull CharSequence charSequence) {
            return false;
        }

        public boolean d(@NonNull CharSequence charSequence, int i) {
            return false;
        }

        public void e() {
            this.f1254a.e();
        }

        public CharSequence f(@NonNull CharSequence charSequence, @IntRange(from = 0) int i, @IntRange(from = 0) int i2, @IntRange(from = 0) int i3, boolean z) {
            return charSequence;
        }

        public void g(@NonNull EditorInfo editorInfo) {
        }
    }

    /* loaded from: classes.dex */
    public static class c implements Runnable {
        public final List<InitCallback> h;
        public final Throwable i;
        public final int j;

        public c(@NonNull InitCallback initCallback, int i) {
            this(Arrays.asList((InitCallback) Preconditions.checkNotNull(initCallback, "initCallback cannot be null")), i, null);
        }

        @Override // java.lang.Runnable
        public void run() {
            int size = this.h.size();
            int i = 0;
            if (this.j != 1) {
                while (i < size) {
                    this.h.get(i).onFailed(this.i);
                    i++;
                }
                return;
            }
            while (i < size) {
                this.h.get(i).onInitialized();
                i++;
            }
        }

        public c(@NonNull Collection<InitCallback> collection, int i) {
            this(collection, i, null);
        }

        public c(@NonNull Collection<InitCallback> collection, int i, @Nullable Throwable th) {
            Preconditions.checkNotNull(collection, "initCallbacks cannot be null");
            this.h = new ArrayList(collection);
            this.j = i;
            this.i = th;
        }
    }

    @RequiresApi(19)
    @RestrictTo({RestrictTo.Scope.LIBRARY})
    /* loaded from: classes.dex */
    public static class d {
        public EmojiSpan a(@NonNull EmojiMetadata emojiMetadata) {
            return new TypefaceEmojiSpan(emojiMetadata);
        }
    }

    public EmojiCompat(@NonNull Config config) {
        this.g = config.b;
        this.h = config.c;
        this.i = config.d;
        this.j = config.f;
        this.k = config.g;
        this.f = config.f1252a;
        this.l = config.h;
        this.m = config.i;
        ArraySet arraySet = new ArraySet();
        this.b = arraySet;
        Set<InitCallback> set = config.e;
        if (set != null && !set.isEmpty()) {
            arraySet.addAll(config.e);
        }
        this.e = Build.VERSION.SDK_INT < 19 ? new b(this) : new a(this);
        c();
    }

    @NonNull
    public static EmojiCompat get() {
        EmojiCompat emojiCompat;
        synchronized (n) {
            emojiCompat = p;
            Preconditions.checkState(emojiCompat != null, "EmojiCompat is not initialized.\n\nYou must initialize EmojiCompat prior to referencing the EmojiCompat instance.\n\nThe most likely cause of this error is disabling the EmojiCompatInitializer\neither explicitly in AndroidManifest.xml, or by including\nandroidx.emoji2:emoji2-bundled.\n\nAutomatic initialization is typically performed by EmojiCompatInitializer. If\nyou are not expecting to initialize EmojiCompat manually in your application,\nplease check to ensure it has not been removed from your APK's manifest. You can\ndo this in Android Studio using Build > Analyze APK.\n\nIn the APK Analyzer, ensure that the startup entry for\nEmojiCompatInitializer and InitializationProvider is present in\n AndroidManifest.xml. If it is missing or contains tools:node=\"remove\", and you\nintend to use automatic configuration, verify:\n\n  1. Your application does not include emoji2-bundled\n  2. All modules do not contain an exclusion manifest rule for\n     EmojiCompatInitializer or InitializationProvider. For more information\n     about manifest exclusions see the documentation for the androidx startup\n     library.\n\nIf you intend to use emoji2-bundled, please call EmojiCompat.init. You can\nlearn more in the documentation for BundledEmojiCompatConfig.\n\nIf you intended to perform manual configuration, it is recommended that you call\nEmojiCompat.init immediately on application startup.\n\nIf you still cannot resolve this issue, please open a bug with your specific\nconfiguration to help improve error message.");
        }
        return emojiCompat;
    }

    public static boolean handleDeleteSurroundingText(@NonNull InputConnection inputConnection, @NonNull Editable editable, @IntRange(from = 0) int i, @IntRange(from = 0) int i2, boolean z) {
        if (Build.VERSION.SDK_INT >= 19) {
            return f.e(inputConnection, editable, i, i2, z);
        }
        return false;
    }

    public static boolean handleOnKeyDown(@NonNull Editable editable, int i, @NonNull KeyEvent keyEvent) {
        if (Build.VERSION.SDK_INT >= 19) {
            return f.f(editable, i, keyEvent);
        }
        return false;
    }

    @Nullable
    public static EmojiCompat init(@NonNull Context context) {
        return init(context, null);
    }

    public static boolean isConfigured() {
        return p != null;
    }

    @NonNull
    public static EmojiCompat reset(@NonNull Config config) {
        EmojiCompat emojiCompat;
        synchronized (n) {
            emojiCompat = new EmojiCompat(config);
            p = emojiCompat;
        }
        return emojiCompat;
    }

    @RestrictTo({RestrictTo.Scope.TESTS})
    public static void skipDefaultConfigurationLookup(boolean z) {
        synchronized (o) {
            q = z;
        }
    }

    public final boolean b() {
        return getLoadState() == 1;
    }

    public final void c() {
        this.f1251a.writeLock().lock();
        try {
            if (this.l == 0) {
                this.c = 0;
            }
            this.f1251a.writeLock().unlock();
            if (getLoadState() == 0) {
                this.e.e();
            }
        } catch (Throwable th) {
            this.f1251a.writeLock().unlock();
            throw th;
        }
    }

    public void d(@Nullable Throwable th) {
        ArrayList arrayList = new ArrayList();
        this.f1251a.writeLock().lock();
        try {
            this.c = 2;
            arrayList.addAll(this.b);
            this.b.clear();
            this.f1251a.writeLock().unlock();
            this.d.post(new c(arrayList, this.c, th));
        } catch (Throwable th2) {
            this.f1251a.writeLock().unlock();
            throw th2;
        }
    }

    public void e() {
        ArrayList arrayList = new ArrayList();
        this.f1251a.writeLock().lock();
        try {
            this.c = 1;
            arrayList.addAll(this.b);
            this.b.clear();
            this.f1251a.writeLock().unlock();
            this.d.post(new c(arrayList, this.c));
        } catch (Throwable th) {
            this.f1251a.writeLock().unlock();
            throw th;
        }
    }

    @NonNull
    public String getAssetSignature() {
        Preconditions.checkState(b(), "Not initialized yet");
        return this.e.a();
    }

    public int getEmojiMatch(@NonNull CharSequence charSequence, @IntRange(from = 0) int i) {
        Preconditions.checkState(b(), "Not initialized yet");
        Preconditions.checkNotNull(charSequence, "sequence cannot be null");
        return this.e.b(charSequence, i);
    }

    @ColorInt
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public int getEmojiSpanIndicatorColor() {
        return this.k;
    }

    public int getLoadState() {
        this.f1251a.readLock().lock();
        try {
            return this.c;
        } finally {
            this.f1251a.readLock().unlock();
        }
    }

    @Deprecated
    public boolean hasEmojiGlyph(@NonNull CharSequence charSequence) {
        Preconditions.checkState(b(), "Not initialized yet");
        Preconditions.checkNotNull(charSequence, "sequence cannot be null");
        return this.e.c(charSequence);
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public boolean isEmojiSpanIndicatorEnabled() {
        return this.j;
    }

    public void load() {
        Preconditions.checkState(this.l == 1, "Set metadataLoadStrategy to LOAD_STRATEGY_MANUAL to execute manual loading");
        if (b()) {
            return;
        }
        this.f1251a.writeLock().lock();
        try {
            if (this.c == 0) {
                return;
            }
            this.c = 0;
            this.f1251a.writeLock().unlock();
            this.e.e();
        } finally {
            this.f1251a.writeLock().unlock();
        }
    }

    @Nullable
    @CheckResult
    public CharSequence process(@Nullable CharSequence charSequence) {
        return process(charSequence, 0, charSequence == null ? 0 : charSequence.length());
    }

    public void registerInitCallback(@NonNull InitCallback initCallback) {
        Preconditions.checkNotNull(initCallback, "initCallback cannot be null");
        this.f1251a.writeLock().lock();
        try {
            if (this.c != 1 && this.c != 2) {
                this.b.add(initCallback);
            }
            this.d.post(new c(initCallback, this.c));
        } finally {
            this.f1251a.writeLock().unlock();
        }
    }

    public void unregisterInitCallback(@NonNull InitCallback initCallback) {
        Preconditions.checkNotNull(initCallback, "initCallback cannot be null");
        this.f1251a.writeLock().lock();
        try {
            this.b.remove(initCallback);
        } finally {
            this.f1251a.writeLock().unlock();
        }
    }

    public void updateEditorInfo(@NonNull EditorInfo editorInfo) {
        if (!b() || editorInfo == null) {
            return;
        }
        if (editorInfo.extras == null) {
            editorInfo.extras = new Bundle();
        }
        this.e.g(editorInfo);
    }

    @Nullable
    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public static EmojiCompat init(@NonNull Context context, @Nullable DefaultEmojiCompatConfig.DefaultEmojiCompatConfigFactory defaultEmojiCompatConfigFactory) {
        EmojiCompat emojiCompat;
        if (q) {
            return p;
        }
        if (defaultEmojiCompatConfigFactory == null) {
            defaultEmojiCompatConfigFactory = new DefaultEmojiCompatConfig.DefaultEmojiCompatConfigFactory(null);
        }
        Config create = defaultEmojiCompatConfigFactory.create(context);
        synchronized (o) {
            if (!q) {
                if (create != null) {
                    init(create);
                }
                q = true;
            }
            emojiCompat = p;
        }
        return emojiCompat;
    }

    @Nullable
    @CheckResult
    public CharSequence process(@Nullable CharSequence charSequence, @IntRange(from = 0) int i, @IntRange(from = 0) int i2) {
        return process(charSequence, i, i2, Integer.MAX_VALUE);
    }

    @Deprecated
    public boolean hasEmojiGlyph(@NonNull CharSequence charSequence, @IntRange(from = 0) int i) {
        Preconditions.checkState(b(), "Not initialized yet");
        Preconditions.checkNotNull(charSequence, "sequence cannot be null");
        return this.e.d(charSequence, i);
    }

    @Nullable
    @CheckResult
    public CharSequence process(@Nullable CharSequence charSequence, @IntRange(from = 0) int i, @IntRange(from = 0) int i2, @IntRange(from = 0) int i3) {
        return process(charSequence, i, i2, i3, 0);
    }

    @Nullable
    @CheckResult
    public CharSequence process(@Nullable CharSequence charSequence, @IntRange(from = 0) int i, @IntRange(from = 0) int i2, @IntRange(from = 0) int i3, int i4) {
        boolean z;
        Preconditions.checkState(b(), "Not initialized yet");
        Preconditions.checkArgumentNonnegative(i, "start cannot be negative");
        Preconditions.checkArgumentNonnegative(i2, "end cannot be negative");
        Preconditions.checkArgumentNonnegative(i3, "maxEmojiCount cannot be negative");
        Preconditions.checkArgument(i <= i2, "start should be <= than end");
        if (charSequence == null) {
            return null;
        }
        Preconditions.checkArgument(i <= charSequence.length(), "start should be < than charSequence length");
        Preconditions.checkArgument(i2 <= charSequence.length(), "end should be < than charSequence length");
        if (charSequence.length() == 0 || i == i2) {
            return charSequence;
        }
        if (i4 != 1) {
            z = i4 != 2 ? this.g : false;
        } else {
            z = true;
        }
        return this.e.f(charSequence, i, i2, i3, z);
    }

    @Nullable
    @RestrictTo({RestrictTo.Scope.TESTS})
    public static EmojiCompat reset(@Nullable EmojiCompat emojiCompat) {
        EmojiCompat emojiCompat2;
        synchronized (n) {
            p = emojiCompat;
            emojiCompat2 = p;
        }
        return emojiCompat2;
    }

    @NonNull
    public static EmojiCompat init(@NonNull Config config) {
        EmojiCompat emojiCompat = p;
        if (emojiCompat == null) {
            synchronized (n) {
                emojiCompat = p;
                if (emojiCompat == null) {
                    emojiCompat = new EmojiCompat(config);
                    p = emojiCompat;
                }
            }
        }
        return emojiCompat;
    }
}
