package androidx.core.view.inputmethod;

import android.content.ClipDescription;
import android.net.Uri;
import android.os.Build;
import android.view.inputmethod.InputContentInfo;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
/* loaded from: classes.dex */
public final class InputContentInfoCompat {

    /* renamed from: a  reason: collision with root package name */
    public final c f1196a;

    /* loaded from: classes.dex */
    public static final class b implements c {
        @NonNull

        /* renamed from: a  reason: collision with root package name */
        public final Uri f1198a;
        @NonNull
        public final ClipDescription b;
        @Nullable
        public final Uri c;

        public b(@NonNull Uri uri, @NonNull ClipDescription clipDescription, @Nullable Uri uri2) {
            this.f1198a = uri;
            this.b = clipDescription;
            this.c = uri2;
        }

        @Override // androidx.core.view.inputmethod.InputContentInfoCompat.c
        @Nullable
        public Uri a() {
            return this.c;
        }

        @Override // androidx.core.view.inputmethod.InputContentInfoCompat.c
        @Nullable
        public Object b() {
            return null;
        }

        @Override // androidx.core.view.inputmethod.InputContentInfoCompat.c
        @NonNull
        public Uri c() {
            return this.f1198a;
        }

        @Override // androidx.core.view.inputmethod.InputContentInfoCompat.c
        public void d() {
        }

        @Override // androidx.core.view.inputmethod.InputContentInfoCompat.c
        @NonNull
        public ClipDescription e() {
            return this.b;
        }

        @Override // androidx.core.view.inputmethod.InputContentInfoCompat.c
        public void f() {
        }
    }

    /* loaded from: classes.dex */
    public interface c {
        @Nullable
        Uri a();

        @Nullable
        Object b();

        @NonNull
        Uri c();

        void d();

        @NonNull
        ClipDescription e();

        void f();
    }

    public InputContentInfoCompat(@NonNull Uri uri, @NonNull ClipDescription clipDescription, @Nullable Uri uri2) {
        if (Build.VERSION.SDK_INT >= 25) {
            this.f1196a = new a(uri, clipDescription, uri2);
        } else {
            this.f1196a = new b(uri, clipDescription, uri2);
        }
    }

    @Nullable
    public static InputContentInfoCompat wrap(@Nullable Object obj) {
        if (obj != null && Build.VERSION.SDK_INT >= 25) {
            return new InputContentInfoCompat(new a(obj));
        }
        return null;
    }

    @NonNull
    public Uri getContentUri() {
        return this.f1196a.c();
    }

    @NonNull
    public ClipDescription getDescription() {
        return this.f1196a.e();
    }

    @Nullable
    public Uri getLinkUri() {
        return this.f1196a.a();
    }

    public void releasePermission() {
        this.f1196a.f();
    }

    public void requestPermission() {
        this.f1196a.d();
    }

    @Nullable
    public Object unwrap() {
        return this.f1196a.b();
    }

    @RequiresApi(25)
    /* loaded from: classes.dex */
    public static final class a implements c {
        @NonNull

        /* renamed from: a  reason: collision with root package name */
        public final InputContentInfo f1197a;

        public a(@NonNull Object obj) {
            this.f1197a = (InputContentInfo) obj;
        }

        @Override // androidx.core.view.inputmethod.InputContentInfoCompat.c
        @Nullable
        public Uri a() {
            return this.f1197a.getLinkUri();
        }

        @Override // androidx.core.view.inputmethod.InputContentInfoCompat.c
        @NonNull
        public Object b() {
            return this.f1197a;
        }

        @Override // androidx.core.view.inputmethod.InputContentInfoCompat.c
        @NonNull
        public Uri c() {
            return this.f1197a.getContentUri();
        }

        @Override // androidx.core.view.inputmethod.InputContentInfoCompat.c
        public void d() {
            this.f1197a.requestPermission();
        }

        @Override // androidx.core.view.inputmethod.InputContentInfoCompat.c
        @NonNull
        public ClipDescription e() {
            return this.f1197a.getDescription();
        }

        @Override // androidx.core.view.inputmethod.InputContentInfoCompat.c
        public void f() {
            this.f1197a.releasePermission();
        }

        public a(@NonNull Uri uri, @NonNull ClipDescription clipDescription, @Nullable Uri uri2) {
            this.f1197a = new InputContentInfo(uri, clipDescription, uri2);
        }
    }

    public InputContentInfoCompat(@NonNull c cVar) {
        this.f1196a = cVar;
    }
}
