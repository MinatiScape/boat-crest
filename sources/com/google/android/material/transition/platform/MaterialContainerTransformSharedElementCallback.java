package com.google.android.material.transition.platform;

import android.app.Activity;
import android.app.SharedElementCallback;
import android.content.Context;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.os.Parcelable;
import android.transition.Transition;
import android.view.View;
import android.view.Window;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.graphics.BlendModeColorFilterCompat;
import androidx.core.graphics.BlendModeCompat;
import com.google.android.material.R;
import com.google.android.material.internal.ContextUtils;
import com.google.android.material.shape.ShapeAppearanceModel;
import com.google.android.material.shape.Shapeable;
import java.lang.ref.WeakReference;
import java.util.List;
import java.util.Map;
@RequiresApi(21)
/* loaded from: classes10.dex */
public class MaterialContainerTransformSharedElementCallback extends SharedElementCallback {
    @Nullable
    public static WeakReference<View> f;
    @Nullable
    public Rect d;

    /* renamed from: a  reason: collision with root package name */
    public boolean f10425a = true;
    public boolean b = true;
    public boolean c = false;
    @Nullable
    public ShapeProvider e = new ShapeableViewShapeProvider();

    /* loaded from: classes10.dex */
    public interface ShapeProvider {
        @Nullable
        ShapeAppearanceModel provideShape(@NonNull View view);
    }

    /* loaded from: classes10.dex */
    public static class ShapeableViewShapeProvider implements ShapeProvider {
        @Override // com.google.android.material.transition.platform.MaterialContainerTransformSharedElementCallback.ShapeProvider
        @Nullable
        public ShapeAppearanceModel provideShape(@NonNull View view) {
            if (view instanceof Shapeable) {
                return ((Shapeable) view).getShapeAppearanceModel();
            }
            return null;
        }
    }

    /* loaded from: classes10.dex */
    public class a extends i {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ Window f10426a;

        public a(MaterialContainerTransformSharedElementCallback materialContainerTransformSharedElementCallback, Window window) {
            this.f10426a = window;
        }

        @Override // com.google.android.material.transition.platform.i, android.transition.Transition.TransitionListener
        public void onTransitionEnd(Transition transition) {
            MaterialContainerTransformSharedElementCallback.g(this.f10426a);
        }

        @Override // com.google.android.material.transition.platform.i, android.transition.Transition.TransitionListener
        public void onTransitionStart(Transition transition) {
            MaterialContainerTransformSharedElementCallback.f(this.f10426a);
        }
    }

    /* loaded from: classes10.dex */
    public class b extends i {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ Activity f10427a;

        public b(MaterialContainerTransformSharedElementCallback materialContainerTransformSharedElementCallback, Activity activity) {
            this.f10427a = activity;
        }

        @Override // com.google.android.material.transition.platform.i, android.transition.Transition.TransitionListener
        public void onTransitionEnd(Transition transition) {
            View view;
            if (MaterialContainerTransformSharedElementCallback.f != null && (view = (View) MaterialContainerTransformSharedElementCallback.f.get()) != null) {
                view.setAlpha(1.0f);
                WeakReference unused = MaterialContainerTransformSharedElementCallback.f = null;
            }
            this.f10427a.finish();
            this.f10427a.overridePendingTransition(0, 0);
        }
    }

    /* loaded from: classes10.dex */
    public class c extends i {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ Window f10428a;

        public c(MaterialContainerTransformSharedElementCallback materialContainerTransformSharedElementCallback, Window window) {
            this.f10428a = window;
        }

        @Override // com.google.android.material.transition.platform.i, android.transition.Transition.TransitionListener
        public void onTransitionStart(Transition transition) {
            MaterialContainerTransformSharedElementCallback.f(this.f10428a);
        }
    }

    @Nullable
    public static Drawable e(Window window) {
        return window.getDecorView().getBackground();
    }

    public static void f(Window window) {
        Drawable e = e(window);
        if (e == null) {
            return;
        }
        e.mutate().setColorFilter(BlendModeColorFilterCompat.createBlendModeColorFilterCompat(0, BlendModeCompat.CLEAR));
    }

    public static void g(Window window) {
        Drawable e = e(window);
        if (e == null) {
            return;
        }
        e.mutate().clearColorFilter();
    }

    public static void j(Window window, MaterialContainerTransform materialContainerTransform) {
        if (materialContainerTransform.getDuration() >= 0) {
            window.setTransitionBackgroundFadeDuration(materialContainerTransform.getDuration());
        }
    }

    @Nullable
    public ShapeProvider getShapeProvider() {
        return this.e;
    }

    public final void h(Window window) {
        Transition sharedElementEnterTransition = window.getSharedElementEnterTransition();
        if (sharedElementEnterTransition instanceof MaterialContainerTransform) {
            MaterialContainerTransform materialContainerTransform = (MaterialContainerTransform) sharedElementEnterTransition;
            if (!this.c) {
                window.setSharedElementReenterTransition(null);
            }
            if (this.b) {
                j(window, materialContainerTransform);
                materialContainerTransform.addListener(new a(this, window));
            }
        }
    }

    public final void i(Activity activity, Window window) {
        Transition sharedElementReturnTransition = window.getSharedElementReturnTransition();
        if (sharedElementReturnTransition instanceof MaterialContainerTransform) {
            MaterialContainerTransform materialContainerTransform = (MaterialContainerTransform) sharedElementReturnTransition;
            materialContainerTransform.setHoldAtEndEnabled(true);
            materialContainerTransform.addListener(new b(this, activity));
            if (this.b) {
                j(window, materialContainerTransform);
                materialContainerTransform.addListener(new c(this, window));
            }
        }
    }

    public boolean isSharedElementReenterTransitionEnabled() {
        return this.c;
    }

    public boolean isTransparentWindowBackgroundEnabled() {
        return this.b;
    }

    @Override // android.app.SharedElementCallback
    @Nullable
    public Parcelable onCaptureSharedElementSnapshot(@NonNull View view, @NonNull Matrix matrix, @NonNull RectF rectF) {
        f = new WeakReference<>(view);
        return super.onCaptureSharedElementSnapshot(view, matrix, rectF);
    }

    @Override // android.app.SharedElementCallback
    @Nullable
    public View onCreateSnapshotView(@NonNull Context context, @Nullable Parcelable parcelable) {
        WeakReference<View> weakReference;
        View view;
        ShapeAppearanceModel provideShape;
        View onCreateSnapshotView = super.onCreateSnapshotView(context, parcelable);
        if (onCreateSnapshotView != null && (weakReference = f) != null && this.e != null && (view = weakReference.get()) != null && (provideShape = this.e.provideShape(view)) != null) {
            onCreateSnapshotView.setTag(R.id.mtrl_motion_snapshot_view, provideShape);
        }
        return onCreateSnapshotView;
    }

    @Override // android.app.SharedElementCallback
    public void onMapSharedElements(@NonNull List<String> list, @NonNull Map<String, View> map) {
        View view;
        Activity activity;
        if (list.isEmpty() || map.isEmpty() || (view = map.get(list.get(0))) == null || (activity = ContextUtils.getActivity(view.getContext())) == null) {
            return;
        }
        Window window = activity.getWindow();
        if (this.f10425a) {
            h(window);
        } else {
            i(activity, window);
        }
    }

    @Override // android.app.SharedElementCallback
    public void onSharedElementEnd(@NonNull List<String> list, @NonNull List<View> list2, @NonNull List<View> list3) {
        if (!list2.isEmpty()) {
            int i = R.id.mtrl_motion_snapshot_view;
            if (list2.get(0).getTag(i) instanceof View) {
                list2.get(0).setTag(i, null);
            }
        }
        if (!this.f10425a && !list2.isEmpty()) {
            this.d = j.i(list2.get(0));
        }
        this.f10425a = false;
    }

    @Override // android.app.SharedElementCallback
    public void onSharedElementStart(@NonNull List<String> list, @NonNull List<View> list2, @NonNull List<View> list3) {
        if (!list2.isEmpty() && !list3.isEmpty()) {
            list2.get(0).setTag(R.id.mtrl_motion_snapshot_view, list3.get(0));
        }
        if (this.f10425a || list2.isEmpty() || this.d == null) {
            return;
        }
        View view = list2.get(0);
        view.measure(View.MeasureSpec.makeMeasureSpec(this.d.width(), 1073741824), View.MeasureSpec.makeMeasureSpec(this.d.height(), 1073741824));
        Rect rect = this.d;
        view.layout(rect.left, rect.top, rect.right, rect.bottom);
    }

    public void setShapeProvider(@Nullable ShapeProvider shapeProvider) {
        this.e = shapeProvider;
    }

    public void setSharedElementReenterTransitionEnabled(boolean z) {
        this.c = z;
    }

    public void setTransparentWindowBackgroundEnabled(boolean z) {
        this.b = z;
    }
}
