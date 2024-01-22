package androidx.databinding;

import android.annotation.TargetApi;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Log;
import android.util.LongSparseArray;
import android.util.SparseArray;
import android.util.SparseBooleanArray;
import android.util.SparseIntArray;
import android.util.SparseLongArray;
import android.view.Choreographer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.databinding.CallbackRegistry;
import androidx.databinding.Observable;
import androidx.databinding.ObservableList;
import androidx.databinding.ObservableMap;
import androidx.databinding.library.R;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.OnLifecycleEvent;
import androidx.viewbinding.ViewBinding;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.List;
import java.util.Map;
/* loaded from: classes.dex */
public abstract class ViewDataBinding extends BaseObservable implements ViewBinding {
    private static final int BINDING_NUMBER_START = 8;
    public static final String BINDING_TAG_PREFIX = "binding_";
    private static final androidx.databinding.b CREATE_LIST_LISTENER;
    private static final androidx.databinding.b CREATE_LIVE_DATA_LISTENER;
    private static final androidx.databinding.b CREATE_MAP_LISTENER;
    private static final androidx.databinding.b CREATE_PROPERTY_LISTENER;
    private static final int HALTED = 2;
    private static final int REBIND = 1;
    private static final CallbackRegistry.NotifierCallback<OnRebindCallback, ViewDataBinding, Void> REBIND_NOTIFIER;
    private static final int REBOUND = 3;
    private static final View.OnAttachStateChangeListener ROOT_REATTACHED_LISTENER;
    public static int SDK_INT;
    private static final boolean USE_CHOREOGRAPHER;
    private static final ReferenceQueue<ViewDataBinding> sReferenceQueue;
    public final DataBindingComponent mBindingComponent;
    private Choreographer mChoreographer;
    private ViewDataBinding mContainingBinding;
    private final Choreographer.FrameCallback mFrameCallback;
    private boolean mInLiveDataRegisterObserver;
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public boolean mInStateFlowRegisterObserver;
    private boolean mIsExecutingPendingBindings;
    private LifecycleOwner mLifecycleOwner;
    private androidx.databinding.d[] mLocalFieldObservers;
    private OnStartListener mOnStartListener;
    private boolean mPendingRebind;
    private CallbackRegistry<OnRebindCallback, ViewDataBinding, Void> mRebindCallbacks;
    private boolean mRebindHalted;
    private final Runnable mRebindRunnable;
    private final View mRoot;
    private Handler mUIThreadHandler;

    /* loaded from: classes.dex */
    public static class IncludedLayouts {
        public final int[][] indexes;
        public final int[][] layoutIds;
        public final String[][] layouts;

        public IncludedLayouts(int i) {
            this.layouts = new String[i];
            this.indexes = new int[i];
            this.layoutIds = new int[i];
        }

        public void setIncludes(int i, String[] strArr, int[] iArr, int[] iArr2) {
            this.layouts[i] = strArr;
            this.indexes[i] = iArr;
            this.layoutIds[i] = iArr2;
        }
    }

    /* loaded from: classes.dex */
    public static class OnStartListener implements LifecycleObserver {
        public final WeakReference<ViewDataBinding> h;

        public /* synthetic */ OnStartListener(ViewDataBinding viewDataBinding, a aVar) {
            this(viewDataBinding);
        }

        @OnLifecycleEvent(Lifecycle.Event.ON_START)
        public void onStart() {
            ViewDataBinding viewDataBinding = this.h.get();
            if (viewDataBinding != null) {
                viewDataBinding.executePendingBindings();
            }
        }

        public OnStartListener(ViewDataBinding viewDataBinding) {
            this.h = new WeakReference<>(viewDataBinding);
        }
    }

    /* loaded from: classes.dex */
    public static abstract class PropertyChangedInverseListener extends Observable.OnPropertyChangedCallback implements InverseBindingListener {
        public final int h;

        public PropertyChangedInverseListener(int i) {
            this.h = i;
        }

        @Override // androidx.databinding.Observable.OnPropertyChangedCallback
        public void onPropertyChanged(Observable observable, int i) {
            if (i == this.h || i == 0) {
                onChange();
            }
        }
    }

    /* loaded from: classes.dex */
    public class a implements androidx.databinding.b {
        @Override // androidx.databinding.b
        public androidx.databinding.d a(ViewDataBinding viewDataBinding, int i, ReferenceQueue<ViewDataBinding> referenceQueue) {
            return new l(viewDataBinding, i, referenceQueue).b();
        }
    }

    /* loaded from: classes.dex */
    public class b implements androidx.databinding.b {
        @Override // androidx.databinding.b
        public androidx.databinding.d a(ViewDataBinding viewDataBinding, int i, ReferenceQueue<ViewDataBinding> referenceQueue) {
            return new j(viewDataBinding, i, referenceQueue).b();
        }
    }

    /* loaded from: classes.dex */
    public class c implements androidx.databinding.b {
        @Override // androidx.databinding.b
        public androidx.databinding.d a(ViewDataBinding viewDataBinding, int i, ReferenceQueue<ViewDataBinding> referenceQueue) {
            return new k(viewDataBinding, i, referenceQueue).b();
        }
    }

    /* loaded from: classes.dex */
    public class d implements androidx.databinding.b {
        @Override // androidx.databinding.b
        public androidx.databinding.d a(ViewDataBinding viewDataBinding, int i, ReferenceQueue<ViewDataBinding> referenceQueue) {
            return new i(viewDataBinding, i, referenceQueue).c();
        }
    }

    /* loaded from: classes.dex */
    public class e extends CallbackRegistry.NotifierCallback<OnRebindCallback, ViewDataBinding, Void> {
        @Override // androidx.databinding.CallbackRegistry.NotifierCallback
        /* renamed from: a */
        public void onNotifyCallback(OnRebindCallback onRebindCallback, ViewDataBinding viewDataBinding, int i, Void r4) {
            if (i == 1) {
                if (onRebindCallback.onPreBind(viewDataBinding)) {
                    return;
                }
                viewDataBinding.mRebindHalted = true;
            } else if (i == 2) {
                onRebindCallback.onCanceled(viewDataBinding);
            } else if (i != 3) {
            } else {
                onRebindCallback.onBound(viewDataBinding);
            }
        }
    }

    /* loaded from: classes.dex */
    public class f implements View.OnAttachStateChangeListener {
        @Override // android.view.View.OnAttachStateChangeListener
        @TargetApi(19)
        public void onViewAttachedToWindow(View view) {
            ViewDataBinding.getBinding(view).mRebindRunnable.run();
            view.removeOnAttachStateChangeListener(this);
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public void onViewDetachedFromWindow(View view) {
        }
    }

    /* loaded from: classes.dex */
    public class g implements Runnable {
        public g() {
        }

        @Override // java.lang.Runnable
        public void run() {
            synchronized (this) {
                ViewDataBinding.this.mPendingRebind = false;
            }
            ViewDataBinding.processReferenceQueue();
            if (Build.VERSION.SDK_INT >= 19 && !ViewDataBinding.this.mRoot.isAttachedToWindow()) {
                ViewDataBinding.this.mRoot.removeOnAttachStateChangeListener(ViewDataBinding.ROOT_REATTACHED_LISTENER);
                ViewDataBinding.this.mRoot.addOnAttachStateChangeListener(ViewDataBinding.ROOT_REATTACHED_LISTENER);
                return;
            }
            ViewDataBinding.this.executePendingBindings();
        }
    }

    /* loaded from: classes.dex */
    public class h implements Choreographer.FrameCallback {
        public h() {
        }

        @Override // android.view.Choreographer.FrameCallback
        public void doFrame(long j) {
            ViewDataBinding.this.mRebindRunnable.run();
        }
    }

    /* loaded from: classes.dex */
    public static class i implements Observer, androidx.databinding.c<LiveData<?>> {
        public final androidx.databinding.d<LiveData<?>> h;
        @Nullable
        public WeakReference<LifecycleOwner> i = null;

        public i(ViewDataBinding viewDataBinding, int i, ReferenceQueue<ViewDataBinding> referenceQueue) {
            this.h = new androidx.databinding.d<>(viewDataBinding, i, this, referenceQueue);
        }

        @Override // androidx.databinding.c
        /* renamed from: a */
        public void addListener(LiveData<?> liveData) {
            LifecycleOwner b = b();
            if (b != null) {
                liveData.observe(b, this);
            }
        }

        @Nullable
        public final LifecycleOwner b() {
            WeakReference<LifecycleOwner> weakReference = this.i;
            if (weakReference == null) {
                return null;
            }
            return weakReference.get();
        }

        public androidx.databinding.d<LiveData<?>> c() {
            return this.h;
        }

        @Override // androidx.databinding.c
        /* renamed from: d */
        public void removeListener(LiveData<?> liveData) {
            liveData.removeObserver(this);
        }

        @Override // androidx.lifecycle.Observer
        public void onChanged(@Nullable Object obj) {
            ViewDataBinding a2 = this.h.a();
            if (a2 != null) {
                androidx.databinding.d<LiveData<?>> dVar = this.h;
                a2.handleFieldChange(dVar.b, dVar.b(), 0);
            }
        }

        @Override // androidx.databinding.c
        public void setLifecycleOwner(@Nullable LifecycleOwner lifecycleOwner) {
            LifecycleOwner b = b();
            LiveData<?> b2 = this.h.b();
            if (b2 != null) {
                if (b != null) {
                    b2.removeObserver(this);
                }
                if (lifecycleOwner != null) {
                    b2.observe(lifecycleOwner, this);
                }
            }
            if (lifecycleOwner != null) {
                this.i = new WeakReference<>(lifecycleOwner);
            }
        }
    }

    /* loaded from: classes.dex */
    public static class j extends ObservableList.OnListChangedCallback implements androidx.databinding.c<ObservableList> {
        public final androidx.databinding.d<ObservableList> h;

        public j(ViewDataBinding viewDataBinding, int i, ReferenceQueue<ViewDataBinding> referenceQueue) {
            this.h = new androidx.databinding.d<>(viewDataBinding, i, this, referenceQueue);
        }

        @Override // androidx.databinding.c
        /* renamed from: a */
        public void addListener(ObservableList observableList) {
            observableList.addOnListChangedCallback(this);
        }

        public androidx.databinding.d<ObservableList> b() {
            return this.h;
        }

        @Override // androidx.databinding.c
        /* renamed from: c */
        public void removeListener(ObservableList observableList) {
            observableList.removeOnListChangedCallback(this);
        }

        @Override // androidx.databinding.ObservableList.OnListChangedCallback
        public void onChanged(ObservableList observableList) {
            ObservableList b;
            ViewDataBinding a2 = this.h.a();
            if (a2 != null && (b = this.h.b()) == observableList) {
                a2.handleFieldChange(this.h.b, b, 0);
            }
        }

        @Override // androidx.databinding.ObservableList.OnListChangedCallback
        public void onItemRangeChanged(ObservableList observableList, int i, int i2) {
            onChanged(observableList);
        }

        @Override // androidx.databinding.ObservableList.OnListChangedCallback
        public void onItemRangeInserted(ObservableList observableList, int i, int i2) {
            onChanged(observableList);
        }

        @Override // androidx.databinding.ObservableList.OnListChangedCallback
        public void onItemRangeMoved(ObservableList observableList, int i, int i2, int i3) {
            onChanged(observableList);
        }

        @Override // androidx.databinding.ObservableList.OnListChangedCallback
        public void onItemRangeRemoved(ObservableList observableList, int i, int i2) {
            onChanged(observableList);
        }

        @Override // androidx.databinding.c
        public void setLifecycleOwner(LifecycleOwner lifecycleOwner) {
        }
    }

    /* loaded from: classes.dex */
    public static class k extends ObservableMap.OnMapChangedCallback implements androidx.databinding.c<ObservableMap> {
        public final androidx.databinding.d<ObservableMap> h;

        public k(ViewDataBinding viewDataBinding, int i, ReferenceQueue<ViewDataBinding> referenceQueue) {
            this.h = new androidx.databinding.d<>(viewDataBinding, i, this, referenceQueue);
        }

        @Override // androidx.databinding.c
        /* renamed from: a */
        public void addListener(ObservableMap observableMap) {
            observableMap.addOnMapChangedCallback(this);
        }

        public androidx.databinding.d<ObservableMap> b() {
            return this.h;
        }

        @Override // androidx.databinding.c
        /* renamed from: c */
        public void removeListener(ObservableMap observableMap) {
            observableMap.removeOnMapChangedCallback(this);
        }

        @Override // androidx.databinding.ObservableMap.OnMapChangedCallback
        public void onMapChanged(ObservableMap observableMap, Object obj) {
            ViewDataBinding a2 = this.h.a();
            if (a2 == null || observableMap != this.h.b()) {
                return;
            }
            a2.handleFieldChange(this.h.b, observableMap, 0);
        }

        @Override // androidx.databinding.c
        public void setLifecycleOwner(LifecycleOwner lifecycleOwner) {
        }
    }

    /* loaded from: classes.dex */
    public static class l extends Observable.OnPropertyChangedCallback implements androidx.databinding.c<Observable> {
        public final androidx.databinding.d<Observable> h;

        public l(ViewDataBinding viewDataBinding, int i, ReferenceQueue<ViewDataBinding> referenceQueue) {
            this.h = new androidx.databinding.d<>(viewDataBinding, i, this, referenceQueue);
        }

        @Override // androidx.databinding.c
        /* renamed from: a */
        public void addListener(Observable observable) {
            observable.addOnPropertyChangedCallback(this);
        }

        public androidx.databinding.d<Observable> b() {
            return this.h;
        }

        @Override // androidx.databinding.c
        /* renamed from: c */
        public void removeListener(Observable observable) {
            observable.removeOnPropertyChangedCallback(this);
        }

        @Override // androidx.databinding.Observable.OnPropertyChangedCallback
        public void onPropertyChanged(Observable observable, int i) {
            ViewDataBinding a2 = this.h.a();
            if (a2 != null && this.h.b() == observable) {
                a2.handleFieldChange(this.h.b, observable, i);
            }
        }

        @Override // androidx.databinding.c
        public void setLifecycleOwner(LifecycleOwner lifecycleOwner) {
        }
    }

    static {
        int i2 = Build.VERSION.SDK_INT;
        SDK_INT = i2;
        USE_CHOREOGRAPHER = i2 >= 16;
        CREATE_PROPERTY_LISTENER = new a();
        CREATE_LIST_LISTENER = new b();
        CREATE_MAP_LISTENER = new c();
        CREATE_LIVE_DATA_LISTENER = new d();
        REBIND_NOTIFIER = new e();
        sReferenceQueue = new ReferenceQueue<>();
        if (i2 < 19) {
            ROOT_REATTACHED_LISTENER = null;
        } else {
            ROOT_REATTACHED_LISTENER = new f();
        }
    }

    public ViewDataBinding(DataBindingComponent dataBindingComponent, View view, int i2) {
        this.mRebindRunnable = new g();
        this.mPendingRebind = false;
        this.mRebindHalted = false;
        this.mBindingComponent = dataBindingComponent;
        this.mLocalFieldObservers = new androidx.databinding.d[i2];
        this.mRoot = view;
        if (Looper.myLooper() != null) {
            if (USE_CHOREOGRAPHER) {
                this.mChoreographer = Choreographer.getInstance();
                this.mFrameCallback = new h();
                return;
            }
            this.mFrameCallback = null;
            this.mUIThreadHandler = new Handler(Looper.myLooper());
            return;
        }
        throw new IllegalStateException("DataBinding must be created in view's UI Thread");
    }

    public static ViewDataBinding bind(Object obj, View view, int i2) {
        return DataBindingUtil.a(checkAndCastToBindingComponent(obj), view, i2);
    }

    private static DataBindingComponent checkAndCastToBindingComponent(Object obj) {
        if (obj == null) {
            return null;
        }
        if (obj instanceof DataBindingComponent) {
            return (DataBindingComponent) obj;
        }
        throw new IllegalArgumentException("The provided bindingComponent parameter must be an instance of DataBindingComponent. See  https://issuetracker.google.com/issues/116541301 for details of why this parameter is not defined as DataBindingComponent");
    }

    private void executeBindingsInternal() {
        if (this.mIsExecutingPendingBindings) {
            requestRebind();
        } else if (hasPendingBindings()) {
            this.mIsExecutingPendingBindings = true;
            this.mRebindHalted = false;
            CallbackRegistry<OnRebindCallback, ViewDataBinding, Void> callbackRegistry = this.mRebindCallbacks;
            if (callbackRegistry != null) {
                callbackRegistry.notifyCallbacks(this, 1, null);
                if (this.mRebindHalted) {
                    this.mRebindCallbacks.notifyCallbacks(this, 2, null);
                }
            }
            if (!this.mRebindHalted) {
                executeBindings();
                CallbackRegistry<OnRebindCallback, ViewDataBinding, Void> callbackRegistry2 = this.mRebindCallbacks;
                if (callbackRegistry2 != null) {
                    callbackRegistry2.notifyCallbacks(this, 3, null);
                }
            }
            this.mIsExecutingPendingBindings = false;
        }
    }

    public static void executeBindingsOn(ViewDataBinding viewDataBinding) {
        viewDataBinding.executeBindingsInternal();
    }

    private static int findIncludeIndex(String str, int i2, IncludedLayouts includedLayouts, int i3) {
        CharSequence subSequence = str.subSequence(str.indexOf(47) + 1, str.length() - 2);
        String[] strArr = includedLayouts.layouts[i3];
        int length = strArr.length;
        while (i2 < length) {
            if (TextUtils.equals(subSequence, strArr[i2])) {
                return i2;
            }
            i2++;
        }
        return -1;
    }

    private static int findLastMatching(ViewGroup viewGroup, int i2) {
        String str = (String) viewGroup.getChildAt(i2).getTag();
        String substring = str.substring(0, str.length() - 1);
        int length = substring.length();
        int childCount = viewGroup.getChildCount();
        for (int i3 = i2 + 1; i3 < childCount; i3++) {
            View childAt = viewGroup.getChildAt(i3);
            String str2 = childAt.getTag() instanceof String ? (String) childAt.getTag() : null;
            if (str2 != null && str2.startsWith(substring)) {
                if (str2.length() == str.length() && str2.charAt(str2.length() - 1) == '0') {
                    return i2;
                }
                if (isNumeric(str2, length)) {
                    i2 = i3;
                }
            }
        }
        return i2;
    }

    public static ViewDataBinding getBinding(View view) {
        if (view != null) {
            return (ViewDataBinding) view.getTag(R.id.dataBinding);
        }
        return null;
    }

    public static int getBuildSdkInt() {
        return SDK_INT;
    }

    public static int getColorFromResource(View view, int i2) {
        if (Build.VERSION.SDK_INT >= 23) {
            return view.getContext().getColor(i2);
        }
        return view.getResources().getColor(i2);
    }

    public static ColorStateList getColorStateListFromResource(View view, int i2) {
        if (Build.VERSION.SDK_INT >= 23) {
            return view.getContext().getColorStateList(i2);
        }
        return view.getResources().getColorStateList(i2);
    }

    public static Drawable getDrawableFromResource(View view, int i2) {
        if (Build.VERSION.SDK_INT >= 21) {
            return view.getContext().getDrawable(i2);
        }
        return view.getResources().getDrawable(i2);
    }

    public static <K, T> T getFrom(Map<K, T> map, K k2) {
        if (map == null) {
            return null;
        }
        return map.get(k2);
    }

    public static <T> T getFromArray(T[] tArr, int i2) {
        if (tArr == null || i2 < 0 || i2 >= tArr.length) {
            return null;
        }
        return tArr[i2];
    }

    public static <T> T getFromList(List<T> list, int i2) {
        if (list == null || i2 < 0 || i2 >= list.size()) {
            return null;
        }
        return list.get(i2);
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public static <T extends ViewDataBinding> T inflateInternal(@NonNull LayoutInflater layoutInflater, int i2, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (T) DataBindingUtil.inflate(layoutInflater, i2, viewGroup, z, checkAndCastToBindingComponent(obj));
    }

    private static boolean isNumeric(String str, int i2) {
        int length = str.length();
        if (length == i2) {
            return false;
        }
        while (i2 < length) {
            if (!Character.isDigit(str.charAt(i2))) {
                return false;
            }
            i2++;
        }
        return true;
    }

    public static Object[] mapBindings(DataBindingComponent dataBindingComponent, View view, int i2, IncludedLayouts includedLayouts, SparseIntArray sparseIntArray) {
        Object[] objArr = new Object[i2];
        mapBindings(dataBindingComponent, view, objArr, includedLayouts, sparseIntArray, true);
        return objArr;
    }

    public static boolean parse(String str, boolean z) {
        return str == null ? z : Boolean.parseBoolean(str);
    }

    private static int parseTagInt(String str, int i2) {
        int length = str.length();
        int i3 = 0;
        while (i2 < length) {
            i3 = (i3 * 10) + (str.charAt(i2) - '0');
            i2++;
        }
        return i3;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void processReferenceQueue() {
        while (true) {
            Reference<? extends ViewDataBinding> poll = sReferenceQueue.poll();
            if (poll == null) {
                return;
            }
            if (poll instanceof androidx.databinding.d) {
                ((androidx.databinding.d) poll).e();
            }
        }
    }

    public static int safeUnbox(Integer num) {
        if (num == null) {
            return 0;
        }
        return num.intValue();
    }

    public static void setBindingInverseListener(ViewDataBinding viewDataBinding, InverseBindingListener inverseBindingListener, PropertyChangedInverseListener propertyChangedInverseListener) {
        if (inverseBindingListener != propertyChangedInverseListener) {
            if (inverseBindingListener != null) {
                viewDataBinding.removeOnPropertyChangedCallback((PropertyChangedInverseListener) inverseBindingListener);
            }
            if (propertyChangedInverseListener != null) {
                viewDataBinding.addOnPropertyChangedCallback(propertyChangedInverseListener);
            }
        }
    }

    public static <T> void setTo(T[] tArr, int i2, T t) {
        if (tArr == null || i2 < 0 || i2 >= tArr.length) {
            return;
        }
        tArr[i2] = t;
    }

    public void addOnRebindCallback(@NonNull OnRebindCallback onRebindCallback) {
        if (this.mRebindCallbacks == null) {
            this.mRebindCallbacks = new CallbackRegistry<>(REBIND_NOTIFIER);
        }
        this.mRebindCallbacks.add(onRebindCallback);
    }

    public void ensureBindingComponentIsNotNull(Class<?> cls) {
        if (this.mBindingComponent != null) {
            return;
        }
        throw new IllegalStateException("Required DataBindingComponent is null in class " + getClass().getSimpleName() + ". A BindingAdapter in " + cls.getCanonicalName() + " is not static and requires an object to use, retrieved from the DataBindingComponent. If you don't use an inflation method taking a DataBindingComponent, use DataBindingUtil.setDefaultComponent or make all BindingAdapter methods static.");
    }

    public abstract void executeBindings();

    public void executePendingBindings() {
        ViewDataBinding viewDataBinding = this.mContainingBinding;
        if (viewDataBinding == null) {
            executeBindingsInternal();
        } else {
            viewDataBinding.executePendingBindings();
        }
    }

    public void forceExecuteBindings() {
        executeBindings();
    }

    @Nullable
    public LifecycleOwner getLifecycleOwner() {
        return this.mLifecycleOwner;
    }

    public Object getObservedField(int i2) {
        androidx.databinding.d dVar = this.mLocalFieldObservers[i2];
        if (dVar == null) {
            return null;
        }
        return dVar.b();
    }

    @Override // androidx.viewbinding.ViewBinding
    @NonNull
    public View getRoot() {
        return this.mRoot;
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public void handleFieldChange(int i2, Object obj, int i3) {
        if (this.mInLiveDataRegisterObserver || this.mInStateFlowRegisterObserver || !onFieldChange(i2, obj, i3)) {
            return;
        }
        requestRebind();
    }

    public abstract boolean hasPendingBindings();

    public abstract void invalidateAll();

    public abstract boolean onFieldChange(int i2, Object obj, int i3);

    public void registerTo(int i2, Object obj, androidx.databinding.b bVar) {
        if (obj == null) {
            return;
        }
        androidx.databinding.d dVar = this.mLocalFieldObservers[i2];
        if (dVar == null) {
            dVar = bVar.a(this, i2, sReferenceQueue);
            this.mLocalFieldObservers[i2] = dVar;
            LifecycleOwner lifecycleOwner = this.mLifecycleOwner;
            if (lifecycleOwner != null) {
                dVar.c(lifecycleOwner);
            }
        }
        dVar.d(obj);
    }

    public void removeOnRebindCallback(@NonNull OnRebindCallback onRebindCallback) {
        CallbackRegistry<OnRebindCallback, ViewDataBinding, Void> callbackRegistry = this.mRebindCallbacks;
        if (callbackRegistry != null) {
            callbackRegistry.remove(onRebindCallback);
        }
    }

    public void requestRebind() {
        ViewDataBinding viewDataBinding = this.mContainingBinding;
        if (viewDataBinding != null) {
            viewDataBinding.requestRebind();
            return;
        }
        LifecycleOwner lifecycleOwner = this.mLifecycleOwner;
        if (lifecycleOwner == null || lifecycleOwner.getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.STARTED)) {
            synchronized (this) {
                if (this.mPendingRebind) {
                    return;
                }
                this.mPendingRebind = true;
                if (USE_CHOREOGRAPHER) {
                    this.mChoreographer.postFrameCallback(this.mFrameCallback);
                } else {
                    this.mUIThreadHandler.post(this.mRebindRunnable);
                }
            }
        }
    }

    public void setContainedBinding(ViewDataBinding viewDataBinding) {
        if (viewDataBinding != null) {
            viewDataBinding.mContainingBinding = this;
        }
    }

    @MainThread
    public void setLifecycleOwner(@Nullable LifecycleOwner lifecycleOwner) {
        androidx.databinding.d[] dVarArr;
        if (lifecycleOwner instanceof Fragment) {
            Log.w("DataBinding", "Setting the fragment as the LifecycleOwner might cause memory leaks because views lives shorter than the Fragment. Consider using Fragment's view lifecycle");
        }
        LifecycleOwner lifecycleOwner2 = this.mLifecycleOwner;
        if (lifecycleOwner2 == lifecycleOwner) {
            return;
        }
        if (lifecycleOwner2 != null) {
            lifecycleOwner2.getLifecycle().removeObserver(this.mOnStartListener);
        }
        this.mLifecycleOwner = lifecycleOwner;
        if (lifecycleOwner != null) {
            if (this.mOnStartListener == null) {
                this.mOnStartListener = new OnStartListener(this, null);
            }
            lifecycleOwner.getLifecycle().addObserver(this.mOnStartListener);
        }
        for (androidx.databinding.d dVar : this.mLocalFieldObservers) {
            if (dVar != null) {
                dVar.c(lifecycleOwner);
            }
        }
    }

    public void setRootTag(View view) {
        view.setTag(R.id.dataBinding, this);
    }

    public abstract boolean setVariable(int i2, @Nullable Object obj);

    public void unbind() {
        androidx.databinding.d[] dVarArr;
        for (androidx.databinding.d dVar : this.mLocalFieldObservers) {
            if (dVar != null) {
                dVar.e();
            }
        }
    }

    public boolean unregisterFrom(int i2) {
        androidx.databinding.d dVar = this.mLocalFieldObservers[i2];
        if (dVar != null) {
            return dVar.e();
        }
        return false;
    }

    public boolean updateLiveDataRegistration(int i2, LiveData<?> liveData) {
        this.mInLiveDataRegisterObserver = true;
        try {
            return updateRegistration(i2, liveData, CREATE_LIVE_DATA_LISTENER);
        } finally {
            this.mInLiveDataRegisterObserver = false;
        }
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public boolean updateRegistration(int i2, Object obj, androidx.databinding.b bVar) {
        if (obj == null) {
            return unregisterFrom(i2);
        }
        androidx.databinding.d dVar = this.mLocalFieldObservers[i2];
        if (dVar == null) {
            registerTo(i2, obj, bVar);
            return true;
        } else if (dVar.b() == obj) {
            return false;
        } else {
            unregisterFrom(i2);
            registerTo(i2, obj, bVar);
            return true;
        }
    }

    public static byte parse(String str, byte b2) {
        try {
            return Byte.parseByte(str);
        } catch (NumberFormatException unused) {
            return b2;
        }
    }

    public static long safeUnbox(Long l2) {
        if (l2 == null) {
            return 0L;
        }
        return l2.longValue();
    }

    public void setRootTag(View[] viewArr) {
        for (View view : viewArr) {
            view.setTag(R.id.dataBinding, this);
        }
    }

    public static boolean getFromArray(boolean[] zArr, int i2) {
        if (zArr == null || i2 < 0 || i2 >= zArr.length) {
            return false;
        }
        return zArr[i2];
    }

    public static <T> T getFromList(SparseArray<T> sparseArray, int i2) {
        if (sparseArray == null || i2 < 0) {
            return null;
        }
        return sparseArray.get(i2);
    }

    public static Object[] mapBindings(DataBindingComponent dataBindingComponent, View[] viewArr, int i2, IncludedLayouts includedLayouts, SparseIntArray sparseIntArray) {
        Object[] objArr = new Object[i2];
        for (View view : viewArr) {
            mapBindings(dataBindingComponent, view, objArr, includedLayouts, sparseIntArray, true);
        }
        return objArr;
    }

    public static short parse(String str, short s) {
        try {
            return Short.parseShort(str);
        } catch (NumberFormatException unused) {
            return s;
        }
    }

    public static short safeUnbox(Short sh) {
        if (sh == null) {
            return (short) 0;
        }
        return sh.shortValue();
    }

    public static void setTo(boolean[] zArr, int i2, boolean z) {
        if (zArr == null || i2 < 0 || i2 >= zArr.length) {
            return;
        }
        zArr[i2] = z;
    }

    @TargetApi(16)
    public static <T> T getFromList(LongSparseArray<T> longSparseArray, int i2) {
        if (longSparseArray == null || i2 < 0) {
            return null;
        }
        return longSparseArray.get(i2);
    }

    public static int parse(String str, int i2) {
        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException unused) {
            return i2;
        }
    }

    public static byte safeUnbox(Byte b2) {
        if (b2 == null) {
            return (byte) 0;
        }
        return b2.byteValue();
    }

    public static byte getFromArray(byte[] bArr, int i2) {
        if (bArr == null || i2 < 0 || i2 >= bArr.length) {
            return (byte) 0;
        }
        return bArr[i2];
    }

    public static <T> T getFromList(androidx.collection.LongSparseArray<T> longSparseArray, int i2) {
        if (longSparseArray == null || i2 < 0) {
            return null;
        }
        return longSparseArray.get(i2);
    }

    public static long parse(String str, long j2) {
        try {
            return Long.parseLong(str);
        } catch (NumberFormatException unused) {
            return j2;
        }
    }

    public static char safeUnbox(Character ch) {
        if (ch == null) {
            return (char) 0;
        }
        return ch.charValue();
    }

    public static void setTo(byte[] bArr, int i2, byte b2) {
        if (bArr == null || i2 < 0 || i2 >= bArr.length) {
            return;
        }
        bArr[i2] = b2;
    }

    public static boolean getFromList(SparseBooleanArray sparseBooleanArray, int i2) {
        if (sparseBooleanArray == null || i2 < 0) {
            return false;
        }
        return sparseBooleanArray.get(i2);
    }

    /* JADX WARN: Removed duplicated region for block: B:70:0x0101  */
    /* JADX WARN: Removed duplicated region for block: B:75:0x010e A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static void mapBindings(androidx.databinding.DataBindingComponent r17, android.view.View r18, java.lang.Object[] r19, androidx.databinding.ViewDataBinding.IncludedLayouts r20, android.util.SparseIntArray r21, boolean r22) {
        /*
            Method dump skipped, instructions count: 282
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.databinding.ViewDataBinding.mapBindings(androidx.databinding.DataBindingComponent, android.view.View, java.lang.Object[], androidx.databinding.ViewDataBinding$IncludedLayouts, android.util.SparseIntArray, boolean):void");
    }

    public static float parse(String str, float f2) {
        try {
            return Float.parseFloat(str);
        } catch (NumberFormatException unused) {
            return f2;
        }
    }

    public static double safeUnbox(Double d2) {
        if (d2 == null) {
            return 0.0d;
        }
        return d2.doubleValue();
    }

    public static short getFromArray(short[] sArr, int i2) {
        if (sArr == null || i2 < 0 || i2 >= sArr.length) {
            return (short) 0;
        }
        return sArr[i2];
    }

    public static int getFromList(SparseIntArray sparseIntArray, int i2) {
        if (sparseIntArray == null || i2 < 0) {
            return 0;
        }
        return sparseIntArray.get(i2);
    }

    public static double parse(String str, double d2) {
        try {
            return Double.parseDouble(str);
        } catch (NumberFormatException unused) {
            return d2;
        }
    }

    public static float safeUnbox(Float f2) {
        if (f2 == null) {
            return 0.0f;
        }
        return f2.floatValue();
    }

    public static void setTo(short[] sArr, int i2, short s) {
        if (sArr == null || i2 < 0 || i2 >= sArr.length) {
            return;
        }
        sArr[i2] = s;
    }

    public boolean updateRegistration(int i2, Observable observable) {
        return updateRegistration(i2, observable, CREATE_PROPERTY_LISTENER);
    }

    @TargetApi(18)
    public static long getFromList(SparseLongArray sparseLongArray, int i2) {
        if (sparseLongArray == null || i2 < 0) {
            return 0L;
        }
        return sparseLongArray.get(i2);
    }

    public static char parse(String str, char c2) {
        return (str == null || str.isEmpty()) ? c2 : str.charAt(0);
    }

    public static boolean safeUnbox(Boolean bool) {
        if (bool == null) {
            return false;
        }
        return bool.booleanValue();
    }

    public boolean updateRegistration(int i2, ObservableList observableList) {
        return updateRegistration(i2, observableList, CREATE_LIST_LISTENER);
    }

    public static char getFromArray(char[] cArr, int i2) {
        if (cArr == null || i2 < 0 || i2 >= cArr.length) {
            return (char) 0;
        }
        return cArr[i2];
    }

    public static void setTo(char[] cArr, int i2, char c2) {
        if (cArr == null || i2 < 0 || i2 >= cArr.length) {
            return;
        }
        cArr[i2] = c2;
    }

    public boolean updateRegistration(int i2, ObservableMap observableMap) {
        return updateRegistration(i2, observableMap, CREATE_MAP_LISTENER);
    }

    public static int getFromArray(int[] iArr, int i2) {
        if (iArr == null || i2 < 0 || i2 >= iArr.length) {
            return 0;
        }
        return iArr[i2];
    }

    public static void setTo(int[] iArr, int i2, int i3) {
        if (iArr == null || i2 < 0 || i2 >= iArr.length) {
            return;
        }
        iArr[i2] = i3;
    }

    public static long getFromArray(long[] jArr, int i2) {
        if (jArr == null || i2 < 0 || i2 >= jArr.length) {
            return 0L;
        }
        return jArr[i2];
    }

    public static void setTo(long[] jArr, int i2, long j2) {
        if (jArr == null || i2 < 0 || i2 >= jArr.length) {
            return;
        }
        jArr[i2] = j2;
    }

    public ViewDataBinding(Object obj, View view, int i2) {
        this(checkAndCastToBindingComponent(obj), view, i2);
    }

    public static float getFromArray(float[] fArr, int i2) {
        if (fArr == null || i2 < 0 || i2 >= fArr.length) {
            return 0.0f;
        }
        return fArr[i2];
    }

    public static void setTo(float[] fArr, int i2, float f2) {
        if (fArr == null || i2 < 0 || i2 >= fArr.length) {
            return;
        }
        fArr[i2] = f2;
    }

    public static double getFromArray(double[] dArr, int i2) {
        if (dArr == null || i2 < 0 || i2 >= dArr.length) {
            return 0.0d;
        }
        return dArr[i2];
    }

    public static void setTo(double[] dArr, int i2, double d2) {
        if (dArr == null || i2 < 0 || i2 >= dArr.length) {
            return;
        }
        dArr[i2] = d2;
    }

    public static <T> void setTo(List<T> list, int i2, T t) {
        if (list == null || i2 < 0 || i2 >= list.size()) {
            return;
        }
        list.set(i2, t);
    }

    public static <T> void setTo(SparseArray<T> sparseArray, int i2, T t) {
        if (sparseArray == null || i2 < 0 || i2 >= sparseArray.size()) {
            return;
        }
        sparseArray.put(i2, t);
    }

    @TargetApi(16)
    public static <T> void setTo(LongSparseArray<T> longSparseArray, int i2, T t) {
        if (longSparseArray == null || i2 < 0 || i2 >= longSparseArray.size()) {
            return;
        }
        longSparseArray.put(i2, t);
    }

    public static <T> void setTo(androidx.collection.LongSparseArray<T> longSparseArray, int i2, T t) {
        if (longSparseArray == null || i2 < 0 || i2 >= longSparseArray.size()) {
            return;
        }
        longSparseArray.put(i2, t);
    }

    public static void setTo(SparseBooleanArray sparseBooleanArray, int i2, boolean z) {
        if (sparseBooleanArray == null || i2 < 0 || i2 >= sparseBooleanArray.size()) {
            return;
        }
        sparseBooleanArray.put(i2, z);
    }

    public static void setTo(SparseIntArray sparseIntArray, int i2, int i3) {
        if (sparseIntArray == null || i2 < 0 || i2 >= sparseIntArray.size()) {
            return;
        }
        sparseIntArray.put(i2, i3);
    }

    @TargetApi(18)
    public static void setTo(SparseLongArray sparseLongArray, int i2, long j2) {
        if (sparseLongArray == null || i2 < 0 || i2 >= sparseLongArray.size()) {
            return;
        }
        sparseLongArray.put(i2, j2);
    }

    public static <K, T> void setTo(Map<K, T> map, K k2, T t) {
        if (map == null) {
            return;
        }
        map.put(k2, t);
    }
}
