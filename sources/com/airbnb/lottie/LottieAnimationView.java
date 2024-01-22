package com.airbnb.lottie;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import androidx.annotation.AttrRes;
import androidx.annotation.DrawableRes;
import androidx.annotation.FloatRange;
import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RawRes;
import androidx.annotation.RequiresApi;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.appcompat.widget.AppCompatImageView;
import com.airbnb.lottie.model.KeyPath;
import com.airbnb.lottie.utils.Logger;
import com.airbnb.lottie.utils.Utils;
import com.airbnb.lottie.value.LottieFrameInfo;
import com.airbnb.lottie.value.LottieValueCallback;
import com.airbnb.lottie.value.SimpleLottieValueCallback;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Callable;
/* loaded from: classes.dex */
public class LottieAnimationView extends AppCompatImageView {
    public static final String y = LottieAnimationView.class.getSimpleName();
    public static final LottieListener<Throwable> z = new LottieListener() { // from class: com.airbnb.lottie.b
        @Override // com.airbnb.lottie.LottieListener
        public final void onResult(Object obj) {
            LottieAnimationView.n((Throwable) obj);
        }
    };
    public final LottieListener<LottieComposition> k;
    public final LottieListener<Throwable> l;
    @Nullable
    public LottieListener<Throwable> m;
    @DrawableRes
    public int n;
    public final LottieDrawable o;
    public String p;
    @RawRes
    public int q;
    public boolean r;
    public boolean s;
    public boolean t;
    public final Set<c> u;
    public final Set<LottieOnCompositionLoadedListener> v;
    @Nullable
    public LottieTask<LottieComposition> w;
    @Nullable
    public LottieComposition x;

    /* loaded from: classes.dex */
    public static class SavedState extends View.BaseSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new a();
        public String h;
        public int i;
        public float j;
        public boolean k;
        public String l;
        public int m;
        public int n;

        /* loaded from: classes.dex */
        public class a implements Parcelable.Creator<SavedState> {
            @Override // android.os.Parcelable.Creator
            /* renamed from: a */
            public SavedState createFromParcel(Parcel parcel) {
                return new SavedState(parcel, null);
            }

            @Override // android.os.Parcelable.Creator
            /* renamed from: b */
            public SavedState[] newArray(int i) {
                return new SavedState[i];
            }
        }

        public /* synthetic */ SavedState(Parcel parcel, a aVar) {
            this(parcel);
        }

        @Override // android.view.View.BaseSavedState, android.view.AbsSavedState, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeString(this.h);
            parcel.writeFloat(this.j);
            parcel.writeInt(this.k ? 1 : 0);
            parcel.writeString(this.l);
            parcel.writeInt(this.m);
            parcel.writeInt(this.n);
        }

        public SavedState(Parcelable parcelable) {
            super(parcelable);
        }

        public SavedState(Parcel parcel) {
            super(parcel);
            this.h = parcel.readString();
            this.j = parcel.readFloat();
            this.k = parcel.readInt() == 1;
            this.l = parcel.readString();
            this.m = parcel.readInt();
            this.n = parcel.readInt();
        }
    }

    /* loaded from: classes.dex */
    public class a implements LottieListener<Throwable> {
        public a() {
        }

        @Override // com.airbnb.lottie.LottieListener
        /* renamed from: a */
        public void onResult(Throwable th) {
            if (LottieAnimationView.this.n != 0) {
                LottieAnimationView lottieAnimationView = LottieAnimationView.this;
                lottieAnimationView.setImageResource(lottieAnimationView.n);
            }
            (LottieAnimationView.this.m == null ? LottieAnimationView.z : LottieAnimationView.this.m).onResult(th);
        }
    }

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* loaded from: classes.dex */
    public class b<T> extends LottieValueCallback<T> {
        public final /* synthetic */ SimpleLottieValueCallback c;

        public b(LottieAnimationView lottieAnimationView, SimpleLottieValueCallback simpleLottieValueCallback) {
            this.c = simpleLottieValueCallback;
        }

        @Override // com.airbnb.lottie.value.LottieValueCallback
        public T getValue(LottieFrameInfo<T> lottieFrameInfo) {
            return (T) this.c.getValue(lottieFrameInfo);
        }
    }

    /* loaded from: classes.dex */
    public enum c {
        SET_ANIMATION,
        SET_PROGRESS,
        SET_REPEAT_MODE,
        SET_REPEAT_COUNT,
        SET_IMAGE_ASSETS,
        PLAY_OPTION
    }

    public LottieAnimationView(Context context) {
        super(context);
        this.k = new LottieListener() { // from class: com.airbnb.lottie.a
            @Override // com.airbnb.lottie.LottieListener
            public final void onResult(Object obj) {
                LottieAnimationView.this.setComposition((LottieComposition) obj);
            }
        };
        this.l = new a();
        this.n = 0;
        this.o = new LottieDrawable();
        this.r = false;
        this.s = false;
        this.t = true;
        this.u = new HashSet();
        this.v = new HashSet();
        k(null, R.attr.lottieAnimationViewStyle);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ LottieResult l(String str) throws Exception {
        return this.t ? LottieCompositionFactory.fromAssetSync(getContext(), str) : LottieCompositionFactory.fromAssetSync(getContext(), str, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ LottieResult m(int i) throws Exception {
        return this.t ? LottieCompositionFactory.fromRawResSync(getContext(), i) : LottieCompositionFactory.fromRawResSync(getContext(), i, null);
    }

    public static /* synthetic */ void n(Throwable th) {
        if (Utils.isNetworkException(th)) {
            Logger.warning("Unable to load composition.", th);
            return;
        }
        throw new IllegalStateException("Unable to parse composition", th);
    }

    private void setCompositionTask(LottieTask<LottieComposition> lottieTask) {
        this.u.add(c.SET_ANIMATION);
        h();
        g();
        this.w = lottieTask.addListener(this.k).addFailureListener(this.l);
    }

    public void addAnimatorListener(Animator.AnimatorListener animatorListener) {
        this.o.addAnimatorListener(animatorListener);
    }

    @RequiresApi(api = 19)
    public void addAnimatorPauseListener(Animator.AnimatorPauseListener animatorPauseListener) {
        this.o.addAnimatorPauseListener(animatorPauseListener);
    }

    public void addAnimatorUpdateListener(ValueAnimator.AnimatorUpdateListener animatorUpdateListener) {
        this.o.addAnimatorUpdateListener(animatorUpdateListener);
    }

    public boolean addLottieOnCompositionLoadedListener(@NonNull LottieOnCompositionLoadedListener lottieOnCompositionLoadedListener) {
        LottieComposition lottieComposition = this.x;
        if (lottieComposition != null) {
            lottieOnCompositionLoadedListener.onCompositionLoaded(lottieComposition);
        }
        return this.v.add(lottieOnCompositionLoadedListener);
    }

    public <T> void addValueCallback(KeyPath keyPath, T t, LottieValueCallback<T> lottieValueCallback) {
        this.o.addValueCallback(keyPath, (KeyPath) t, (LottieValueCallback<KeyPath>) lottieValueCallback);
    }

    @MainThread
    public void cancelAnimation() {
        this.u.add(c.PLAY_OPTION);
        this.o.cancelAnimation();
    }

    @Deprecated
    public void disableExtraScaleModeInFitXY() {
        this.o.disableExtraScaleModeInFitXY();
    }

    public void enableMergePathsForKitKatAndAbove(boolean z2) {
        this.o.enableMergePathsForKitKatAndAbove(z2);
    }

    public final void g() {
        LottieTask<LottieComposition> lottieTask = this.w;
        if (lottieTask != null) {
            lottieTask.removeListener(this.k);
            this.w.removeFailureListener(this.l);
        }
    }

    public boolean getClipToCompositionBounds() {
        return this.o.getClipToCompositionBounds();
    }

    @Nullable
    public LottieComposition getComposition() {
        return this.x;
    }

    public long getDuration() {
        LottieComposition lottieComposition = this.x;
        if (lottieComposition != null) {
            return lottieComposition.getDuration();
        }
        return 0L;
    }

    public int getFrame() {
        return this.o.getFrame();
    }

    @Nullable
    public String getImageAssetsFolder() {
        return this.o.getImageAssetsFolder();
    }

    public boolean getMaintainOriginalImageBounds() {
        return this.o.getMaintainOriginalImageBounds();
    }

    public float getMaxFrame() {
        return this.o.getMaxFrame();
    }

    public float getMinFrame() {
        return this.o.getMinFrame();
    }

    @Nullable
    public PerformanceTracker getPerformanceTracker() {
        return this.o.getPerformanceTracker();
    }

    @FloatRange(from = 0.0d, to = 1.0d)
    public float getProgress() {
        return this.o.getProgress();
    }

    public RenderMode getRenderMode() {
        return this.o.getRenderMode();
    }

    public int getRepeatCount() {
        return this.o.getRepeatCount();
    }

    public int getRepeatMode() {
        return this.o.getRepeatMode();
    }

    public float getSpeed() {
        return this.o.getSpeed();
    }

    public final void h() {
        this.x = null;
        this.o.clearComposition();
    }

    public boolean hasMasks() {
        return this.o.hasMasks();
    }

    public boolean hasMatte() {
        return this.o.hasMatte();
    }

    public final LottieTask<LottieComposition> i(final String str) {
        if (isInEditMode()) {
            return new LottieTask<>(new Callable() { // from class: com.airbnb.lottie.d
                @Override // java.util.concurrent.Callable
                public final Object call() {
                    LottieResult l;
                    l = LottieAnimationView.this.l(str);
                    return l;
                }
            }, true);
        }
        return this.t ? LottieCompositionFactory.fromAsset(getContext(), str) : LottieCompositionFactory.fromAsset(getContext(), str, null);
    }

    @Override // android.view.View
    public void invalidate() {
        super.invalidate();
        Drawable drawable = getDrawable();
        if ((drawable instanceof LottieDrawable) && ((LottieDrawable) drawable).getRenderMode() == RenderMode.SOFTWARE) {
            this.o.invalidateSelf();
        }
    }

    @Override // android.widget.ImageView, android.view.View, android.graphics.drawable.Drawable.Callback
    public void invalidateDrawable(@NonNull Drawable drawable) {
        Drawable drawable2 = getDrawable();
        LottieDrawable lottieDrawable = this.o;
        if (drawable2 == lottieDrawable) {
            super.invalidateDrawable(lottieDrawable);
        } else {
            super.invalidateDrawable(drawable);
        }
    }

    public boolean isAnimating() {
        return this.o.isAnimating();
    }

    public boolean isMergePathsEnabledForKitKatAndAbove() {
        return this.o.isMergePathsEnabledForKitKatAndAbove();
    }

    public final LottieTask<LottieComposition> j(@RawRes final int i) {
        if (isInEditMode()) {
            return new LottieTask<>(new Callable() { // from class: com.airbnb.lottie.c
                @Override // java.util.concurrent.Callable
                public final Object call() {
                    LottieResult m;
                    m = LottieAnimationView.this.m(i);
                    return m;
                }
            }, true);
        }
        return this.t ? LottieCompositionFactory.fromRawRes(getContext(), i) : LottieCompositionFactory.fromRawRes(getContext(), i, null);
    }

    public final void k(@Nullable AttributeSet attributeSet, @AttrRes int i) {
        String string;
        TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R.styleable.LottieAnimationView, i, 0);
        this.t = obtainStyledAttributes.getBoolean(R.styleable.LottieAnimationView_lottie_cacheComposition, true);
        int i2 = R.styleable.LottieAnimationView_lottie_rawRes;
        boolean hasValue = obtainStyledAttributes.hasValue(i2);
        int i3 = R.styleable.LottieAnimationView_lottie_fileName;
        boolean hasValue2 = obtainStyledAttributes.hasValue(i3);
        int i4 = R.styleable.LottieAnimationView_lottie_url;
        boolean hasValue3 = obtainStyledAttributes.hasValue(i4);
        if (hasValue && hasValue2) {
            throw new IllegalArgumentException("lottie_rawRes and lottie_fileName cannot be used at the same time. Please use only one at once.");
        }
        if (hasValue) {
            int resourceId = obtainStyledAttributes.getResourceId(i2, 0);
            if (resourceId != 0) {
                setAnimation(resourceId);
            }
        } else if (hasValue2) {
            String string2 = obtainStyledAttributes.getString(i3);
            if (string2 != null) {
                setAnimation(string2);
            }
        } else if (hasValue3 && (string = obtainStyledAttributes.getString(i4)) != null) {
            setAnimationFromUrl(string);
        }
        setFallbackResource(obtainStyledAttributes.getResourceId(R.styleable.LottieAnimationView_lottie_fallbackRes, 0));
        if (obtainStyledAttributes.getBoolean(R.styleable.LottieAnimationView_lottie_autoPlay, false)) {
            this.s = true;
        }
        if (obtainStyledAttributes.getBoolean(R.styleable.LottieAnimationView_lottie_loop, false)) {
            this.o.setRepeatCount(-1);
        }
        int i5 = R.styleable.LottieAnimationView_lottie_repeatMode;
        if (obtainStyledAttributes.hasValue(i5)) {
            setRepeatMode(obtainStyledAttributes.getInt(i5, 1));
        }
        int i6 = R.styleable.LottieAnimationView_lottie_repeatCount;
        if (obtainStyledAttributes.hasValue(i6)) {
            setRepeatCount(obtainStyledAttributes.getInt(i6, -1));
        }
        int i7 = R.styleable.LottieAnimationView_lottie_speed;
        if (obtainStyledAttributes.hasValue(i7)) {
            setSpeed(obtainStyledAttributes.getFloat(i7, 1.0f));
        }
        int i8 = R.styleable.LottieAnimationView_lottie_clipToCompositionBounds;
        if (obtainStyledAttributes.hasValue(i8)) {
            setClipToCompositionBounds(obtainStyledAttributes.getBoolean(i8, true));
        }
        int i9 = R.styleable.LottieAnimationView_lottie_defaultFontFileExtension;
        if (obtainStyledAttributes.hasValue(i9)) {
            setDefaultFontFileExtension(obtainStyledAttributes.getString(i9));
        }
        setImageAssetsFolder(obtainStyledAttributes.getString(R.styleable.LottieAnimationView_lottie_imageAssetsFolder));
        int i10 = R.styleable.LottieAnimationView_lottie_progress;
        p(obtainStyledAttributes.getFloat(i10, 0.0f), obtainStyledAttributes.hasValue(i10));
        enableMergePathsForKitKatAndAbove(obtainStyledAttributes.getBoolean(R.styleable.LottieAnimationView_lottie_enableMergePathsForKitKatAndAbove, false));
        int i11 = R.styleable.LottieAnimationView_lottie_colorFilter;
        if (obtainStyledAttributes.hasValue(i11)) {
            addValueCallback(new KeyPath("**"), (KeyPath) LottieProperty.COLOR_FILTER, (LottieValueCallback<KeyPath>) new LottieValueCallback(new SimpleColorFilter(AppCompatResources.getColorStateList(getContext(), obtainStyledAttributes.getResourceId(i11, -1)).getDefaultColor())));
        }
        int i12 = R.styleable.LottieAnimationView_lottie_renderMode;
        if (obtainStyledAttributes.hasValue(i12)) {
            RenderMode renderMode = RenderMode.AUTOMATIC;
            int i13 = obtainStyledAttributes.getInt(i12, renderMode.ordinal());
            if (i13 >= RenderMode.values().length) {
                i13 = renderMode.ordinal();
            }
            setRenderMode(RenderMode.values()[i13]);
        }
        setIgnoreDisabledSystemAnimations(obtainStyledAttributes.getBoolean(R.styleable.LottieAnimationView_lottie_ignoreDisabledSystemAnimations, false));
        int i14 = R.styleable.LottieAnimationView_lottie_useCompositionFrameRate;
        if (obtainStyledAttributes.hasValue(i14)) {
            setUseCompositionFrameRate(obtainStyledAttributes.getBoolean(i14, false));
        }
        obtainStyledAttributes.recycle();
        this.o.setSystemAnimationsAreEnabled(Boolean.valueOf(Utils.getAnimationScale(getContext()) != 0.0f));
    }

    @Deprecated
    public void loop(boolean z2) {
        this.o.setRepeatCount(z2 ? -1 : 0);
    }

    public final void o() {
        boolean isAnimating = isAnimating();
        setImageDrawable(null);
        setImageDrawable(this.o);
        if (isAnimating) {
            this.o.resumeAnimation();
        }
    }

    @Override // android.widget.ImageView, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (isInEditMode() || !this.s) {
            return;
        }
        this.o.playAnimation();
    }

    @Override // android.view.View
    public void onRestoreInstanceState(Parcelable parcelable) {
        int i;
        if (!(parcelable instanceof SavedState)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.getSuperState());
        this.p = savedState.h;
        Set<c> set = this.u;
        c cVar = c.SET_ANIMATION;
        if (!set.contains(cVar) && !TextUtils.isEmpty(this.p)) {
            setAnimation(this.p);
        }
        this.q = savedState.i;
        if (!this.u.contains(cVar) && (i = this.q) != 0) {
            setAnimation(i);
        }
        if (!this.u.contains(c.SET_PROGRESS)) {
            p(savedState.j, false);
        }
        if (!this.u.contains(c.PLAY_OPTION) && savedState.k) {
            playAnimation();
        }
        if (!this.u.contains(c.SET_IMAGE_ASSETS)) {
            setImageAssetsFolder(savedState.l);
        }
        if (!this.u.contains(c.SET_REPEAT_MODE)) {
            setRepeatMode(savedState.m);
        }
        if (this.u.contains(c.SET_REPEAT_COUNT)) {
            return;
        }
        setRepeatCount(savedState.n);
    }

    @Override // android.view.View
    public Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        savedState.h = this.p;
        savedState.i = this.q;
        savedState.j = this.o.getProgress();
        savedState.k = this.o.D();
        savedState.l = this.o.getImageAssetsFolder();
        savedState.m = this.o.getRepeatMode();
        savedState.n = this.o.getRepeatCount();
        return savedState;
    }

    public final void p(@FloatRange(from = 0.0d, to = 1.0d) float f, boolean z2) {
        if (z2) {
            this.u.add(c.SET_PROGRESS);
        }
        this.o.setProgress(f);
    }

    @MainThread
    public void pauseAnimation() {
        this.s = false;
        this.o.pauseAnimation();
    }

    @MainThread
    public void playAnimation() {
        this.u.add(c.PLAY_OPTION);
        this.o.playAnimation();
    }

    public void removeAllAnimatorListeners() {
        this.o.removeAllAnimatorListeners();
    }

    public void removeAllLottieOnCompositionLoadedListener() {
        this.v.clear();
    }

    public void removeAllUpdateListeners() {
        this.o.removeAllUpdateListeners();
    }

    public void removeAnimatorListener(Animator.AnimatorListener animatorListener) {
        this.o.removeAnimatorListener(animatorListener);
    }

    @RequiresApi(api = 19)
    public void removeAnimatorPauseListener(Animator.AnimatorPauseListener animatorPauseListener) {
        this.o.removeAnimatorPauseListener(animatorPauseListener);
    }

    public boolean removeLottieOnCompositionLoadedListener(@NonNull LottieOnCompositionLoadedListener lottieOnCompositionLoadedListener) {
        return this.v.remove(lottieOnCompositionLoadedListener);
    }

    public void removeUpdateListener(ValueAnimator.AnimatorUpdateListener animatorUpdateListener) {
        this.o.removeAnimatorUpdateListener(animatorUpdateListener);
    }

    public List<KeyPath> resolveKeyPath(KeyPath keyPath) {
        return this.o.resolveKeyPath(keyPath);
    }

    @MainThread
    public void resumeAnimation() {
        this.u.add(c.PLAY_OPTION);
        this.o.resumeAnimation();
    }

    public void reverseAnimationSpeed() {
        this.o.reverseAnimationSpeed();
    }

    public void setAnimation(@RawRes int i) {
        this.q = i;
        this.p = null;
        setCompositionTask(j(i));
    }

    @Deprecated
    public void setAnimationFromJson(String str) {
        setAnimationFromJson(str, null);
    }

    public void setAnimationFromUrl(String str) {
        setCompositionTask(this.t ? LottieCompositionFactory.fromUrl(getContext(), str) : LottieCompositionFactory.fromUrl(getContext(), str, null));
    }

    public void setApplyingOpacityToLayersEnabled(boolean z2) {
        this.o.setApplyingOpacityToLayersEnabled(z2);
    }

    public void setCacheComposition(boolean z2) {
        this.t = z2;
    }

    public void setClipToCompositionBounds(boolean z2) {
        this.o.setClipToCompositionBounds(z2);
    }

    public void setComposition(@NonNull LottieComposition lottieComposition) {
        if (L.DBG) {
            String str = y;
            Log.v(str, "Set Composition \n" + lottieComposition);
        }
        this.o.setCallback(this);
        this.x = lottieComposition;
        this.r = true;
        boolean composition = this.o.setComposition(lottieComposition);
        this.r = false;
        if (getDrawable() != this.o || composition) {
            if (!composition) {
                o();
            }
            onVisibilityChanged(this, getVisibility());
            requestLayout();
            for (LottieOnCompositionLoadedListener lottieOnCompositionLoadedListener : this.v) {
                lottieOnCompositionLoadedListener.onCompositionLoaded(lottieComposition);
            }
        }
    }

    public void setDefaultFontFileExtension(String str) {
        this.o.setDefaultFontFileExtension(str);
    }

    public void setFailureListener(@Nullable LottieListener<Throwable> lottieListener) {
        this.m = lottieListener;
    }

    public void setFallbackResource(@DrawableRes int i) {
        this.n = i;
    }

    public void setFontAssetDelegate(FontAssetDelegate fontAssetDelegate) {
        this.o.setFontAssetDelegate(fontAssetDelegate);
    }

    public void setFontMap(@Nullable Map<String, Typeface> map) {
        this.o.setFontMap(map);
    }

    public void setFrame(int i) {
        this.o.setFrame(i);
    }

    public void setIgnoreDisabledSystemAnimations(boolean z2) {
        this.o.setIgnoreDisabledSystemAnimations(z2);
    }

    public void setImageAssetDelegate(ImageAssetDelegate imageAssetDelegate) {
        this.o.setImageAssetDelegate(imageAssetDelegate);
    }

    public void setImageAssetsFolder(String str) {
        this.o.setImagesAssetsFolder(str);
    }

    @Override // androidx.appcompat.widget.AppCompatImageView, android.widget.ImageView
    public void setImageBitmap(Bitmap bitmap) {
        g();
        super.setImageBitmap(bitmap);
    }

    @Override // androidx.appcompat.widget.AppCompatImageView, android.widget.ImageView
    public void setImageDrawable(Drawable drawable) {
        g();
        super.setImageDrawable(drawable);
    }

    @Override // androidx.appcompat.widget.AppCompatImageView, android.widget.ImageView
    public void setImageResource(int i) {
        g();
        super.setImageResource(i);
    }

    public void setMaintainOriginalImageBounds(boolean z2) {
        this.o.setMaintainOriginalImageBounds(z2);
    }

    public void setMaxFrame(int i) {
        this.o.setMaxFrame(i);
    }

    public void setMaxProgress(@FloatRange(from = 0.0d, to = 1.0d) float f) {
        this.o.setMaxProgress(f);
    }

    public void setMinAndMaxFrame(String str) {
        this.o.setMinAndMaxFrame(str);
    }

    public void setMinAndMaxProgress(@FloatRange(from = 0.0d, to = 1.0d) float f, @FloatRange(from = 0.0d, to = 1.0d) float f2) {
        this.o.setMinAndMaxProgress(f, f2);
    }

    public void setMinFrame(int i) {
        this.o.setMinFrame(i);
    }

    public void setMinProgress(float f) {
        this.o.setMinProgress(f);
    }

    public void setOutlineMasksAndMattes(boolean z2) {
        this.o.setOutlineMasksAndMattes(z2);
    }

    public void setPerformanceTrackingEnabled(boolean z2) {
        this.o.setPerformanceTrackingEnabled(z2);
    }

    public void setProgress(@FloatRange(from = 0.0d, to = 1.0d) float f) {
        p(f, true);
    }

    public void setRenderMode(RenderMode renderMode) {
        this.o.setRenderMode(renderMode);
    }

    public void setRepeatCount(int i) {
        this.u.add(c.SET_REPEAT_COUNT);
        this.o.setRepeatCount(i);
    }

    public void setRepeatMode(int i) {
        this.u.add(c.SET_REPEAT_MODE);
        this.o.setRepeatMode(i);
    }

    public void setSafeMode(boolean z2) {
        this.o.setSafeMode(z2);
    }

    public void setSpeed(float f) {
        this.o.setSpeed(f);
    }

    public void setTextDelegate(TextDelegate textDelegate) {
        this.o.setTextDelegate(textDelegate);
    }

    public void setUseCompositionFrameRate(boolean z2) {
        this.o.setUseCompositionFrameRate(z2);
    }

    @Override // android.view.View
    public void unscheduleDrawable(Drawable drawable) {
        LottieDrawable lottieDrawable;
        if (!this.r && drawable == (lottieDrawable = this.o) && lottieDrawable.isAnimating()) {
            pauseAnimation();
        } else if (!this.r && (drawable instanceof LottieDrawable)) {
            LottieDrawable lottieDrawable2 = (LottieDrawable) drawable;
            if (lottieDrawable2.isAnimating()) {
                lottieDrawable2.pauseAnimation();
            }
        }
        super.unscheduleDrawable(drawable);
    }

    @Nullable
    public Bitmap updateBitmap(String str, @Nullable Bitmap bitmap) {
        return this.o.updateBitmap(str, bitmap);
    }

    public <T> void addValueCallback(KeyPath keyPath, T t, SimpleLottieValueCallback<T> simpleLottieValueCallback) {
        this.o.addValueCallback(keyPath, (KeyPath) t, (LottieValueCallback<KeyPath>) new b(this, simpleLottieValueCallback));
    }

    public void setAnimationFromJson(String str, @Nullable String str2) {
        setAnimation(new ByteArrayInputStream(str.getBytes()), str2);
    }

    public void setMaxFrame(String str) {
        this.o.setMaxFrame(str);
    }

    public void setMinAndMaxFrame(String str, String str2, boolean z2) {
        this.o.setMinAndMaxFrame(str, str2, z2);
    }

    public void setMinFrame(String str) {
        this.o.setMinFrame(str);
    }

    public void setMinAndMaxFrame(int i, int i2) {
        this.o.setMinAndMaxFrame(i, i2);
    }

    public void setAnimation(String str) {
        this.p = str;
        this.q = 0;
        setCompositionTask(i(str));
    }

    public void setAnimationFromUrl(String str, @Nullable String str2) {
        setCompositionTask(LottieCompositionFactory.fromUrl(getContext(), str, str2));
    }

    public void setAnimation(InputStream inputStream, @Nullable String str) {
        setCompositionTask(LottieCompositionFactory.fromJsonInputStream(inputStream, str));
    }

    public LottieAnimationView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.k = new LottieListener() { // from class: com.airbnb.lottie.a
            @Override // com.airbnb.lottie.LottieListener
            public final void onResult(Object obj) {
                LottieAnimationView.this.setComposition((LottieComposition) obj);
            }
        };
        this.l = new a();
        this.n = 0;
        this.o = new LottieDrawable();
        this.r = false;
        this.s = false;
        this.t = true;
        this.u = new HashSet();
        this.v = new HashSet();
        k(attributeSet, R.attr.lottieAnimationViewStyle);
    }

    public LottieAnimationView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.k = new LottieListener() { // from class: com.airbnb.lottie.a
            @Override // com.airbnb.lottie.LottieListener
            public final void onResult(Object obj) {
                LottieAnimationView.this.setComposition((LottieComposition) obj);
            }
        };
        this.l = new a();
        this.n = 0;
        this.o = new LottieDrawable();
        this.r = false;
        this.s = false;
        this.t = true;
        this.u = new HashSet();
        this.v = new HashSet();
        k(attributeSet, i);
    }
}
