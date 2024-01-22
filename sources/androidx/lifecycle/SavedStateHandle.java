package androidx.lifecycle;

import android.os.Binder;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Size;
import android.util.SizeF;
import android.util.SparseArray;
import androidx.annotation.MainThread;
import androidx.annotation.RestrictTo;
import androidx.core.os.BundleKt;
import androidx.savedstate.SavedStateRegistry;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import kotlin.TuplesKt;
import kotlin.collections.b0;
import kotlin.collections.s;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.MutableStateFlow;
import kotlinx.coroutines.flow.StateFlow;
import kotlinx.coroutines.flow.StateFlowKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jose4j.jwk.JsonWebKeySet;
/* loaded from: classes.dex */
public final class SavedStateHandle {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    public static final Class<? extends Object>[] f;
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Map<String, Object> f1361a;
    @NotNull
    public final Map<String, SavedStateRegistry.SavedStateProvider> b;
    @NotNull
    public final Map<String, SavingStateLiveData<?>> c;
    @NotNull
    public final Map<String, MutableStateFlow<Object>> d;
    @NotNull
    public final SavedStateRegistry.SavedStateProvider e;

    /* loaded from: classes.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
        @NotNull
        public final SavedStateHandle createHandle(@Nullable Bundle bundle, @Nullable Bundle bundle2) {
            if (bundle == null) {
                if (bundle2 == null) {
                    return new SavedStateHandle();
                }
                HashMap hashMap = new HashMap();
                for (String key : bundle2.keySet()) {
                    Intrinsics.checkNotNullExpressionValue(key, "key");
                    hashMap.put(key, bundle2.get(key));
                }
                return new SavedStateHandle(hashMap);
            }
            ArrayList parcelableArrayList = bundle.getParcelableArrayList(JsonWebKeySet.JWK_SET_MEMBER_NAME);
            ArrayList parcelableArrayList2 = bundle.getParcelableArrayList("values");
            if ((parcelableArrayList == null || parcelableArrayList2 == null || parcelableArrayList.size() != parcelableArrayList2.size()) ? false : true) {
                LinkedHashMap linkedHashMap = new LinkedHashMap();
                int size = parcelableArrayList.size();
                for (int i = 0; i < size; i++) {
                    Object obj = parcelableArrayList.get(i);
                    Objects.requireNonNull(obj, "null cannot be cast to non-null type kotlin.String");
                    linkedHashMap.put((String) obj, parcelableArrayList2.get(i));
                }
                return new SavedStateHandle(linkedHashMap);
            }
            throw new IllegalStateException("Invalid bundle passed as restored state".toString());
        }

        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
        public final boolean validateValue(@Nullable Object obj) {
            Class[] clsArr;
            if (obj == null) {
                return true;
            }
            for (Class cls : SavedStateHandle.f) {
                Intrinsics.checkNotNull(cls);
                if (cls.isInstance(obj)) {
                    return true;
                }
            }
            return false;
        }
    }

    static {
        Class<? extends Object>[] clsArr = new Class[29];
        clsArr[0] = Boolean.TYPE;
        clsArr[1] = boolean[].class;
        clsArr[2] = Double.TYPE;
        clsArr[3] = double[].class;
        Class<SizeF> cls = Integer.TYPE;
        clsArr[4] = cls;
        clsArr[5] = int[].class;
        clsArr[6] = Long.TYPE;
        clsArr[7] = long[].class;
        clsArr[8] = String.class;
        clsArr[9] = String[].class;
        clsArr[10] = Binder.class;
        clsArr[11] = Bundle.class;
        clsArr[12] = Byte.TYPE;
        clsArr[13] = byte[].class;
        clsArr[14] = Character.TYPE;
        clsArr[15] = char[].class;
        clsArr[16] = CharSequence.class;
        clsArr[17] = CharSequence[].class;
        clsArr[18] = ArrayList.class;
        clsArr[19] = Float.TYPE;
        clsArr[20] = float[].class;
        clsArr[21] = Parcelable.class;
        clsArr[22] = Parcelable[].class;
        clsArr[23] = Serializable.class;
        clsArr[24] = Short.TYPE;
        clsArr[25] = short[].class;
        clsArr[26] = SparseArray.class;
        int i = Build.VERSION.SDK_INT;
        clsArr[27] = i >= 21 ? Size.class : cls;
        if (i >= 21) {
            cls = SizeF.class;
        }
        clsArr[28] = cls;
        f = clsArr;
    }

    public SavedStateHandle(@NotNull Map<String, ? extends Object> initialState) {
        Intrinsics.checkNotNullParameter(initialState, "initialState");
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        this.f1361a = linkedHashMap;
        this.b = new LinkedHashMap();
        this.c = new LinkedHashMap();
        this.d = new LinkedHashMap();
        this.e = new SavedStateRegistry.SavedStateProvider() { // from class: androidx.lifecycle.g
            @Override // androidx.savedstate.SavedStateRegistry.SavedStateProvider
            public final Bundle saveState() {
                Bundle c;
                c = SavedStateHandle.c(SavedStateHandle.this);
                return c;
            }
        };
        linkedHashMap.putAll(initialState);
    }

    public static final Bundle c(SavedStateHandle this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        for (Map.Entry entry : s.toMap(this$0.b).entrySet()) {
            this$0.set((String) entry.getKey(), ((SavedStateRegistry.SavedStateProvider) entry.getValue()).saveState());
        }
        Set<String> keySet = this$0.f1361a.keySet();
        ArrayList arrayList = new ArrayList(keySet.size());
        ArrayList arrayList2 = new ArrayList(arrayList.size());
        for (String str : keySet) {
            arrayList.add(str);
            arrayList2.add(this$0.f1361a.get(str));
        }
        return BundleKt.bundleOf(TuplesKt.to(JsonWebKeySet.JWK_SET_MEMBER_NAME, arrayList), TuplesKt.to("values", arrayList2));
    }

    @JvmStatic
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    @NotNull
    public static final SavedStateHandle createHandle(@Nullable Bundle bundle, @Nullable Bundle bundle2) {
        return Companion.createHandle(bundle, bundle2);
    }

    public final <T> MutableLiveData<T> b(String str, boolean z, T t) {
        SavingStateLiveData<?> savingStateLiveData;
        SavingStateLiveData<?> savingStateLiveData2 = this.c.get(str);
        SavingStateLiveData<?> savingStateLiveData3 = savingStateLiveData2 instanceof MutableLiveData ? savingStateLiveData2 : null;
        if (savingStateLiveData3 != null) {
            return savingStateLiveData3;
        }
        if (this.f1361a.containsKey(str)) {
            savingStateLiveData = new SavingStateLiveData<>(this, str, this.f1361a.get(str));
        } else if (z) {
            this.f1361a.put(str, t);
            savingStateLiveData = new SavingStateLiveData<>(this, str, t);
        } else {
            savingStateLiveData = new SavingStateLiveData<>(this, str);
        }
        this.c.put(str, savingStateLiveData);
        return savingStateLiveData;
    }

    @MainThread
    public final void clearSavedStateProvider(@NotNull String key) {
        Intrinsics.checkNotNullParameter(key, "key");
        this.b.remove(key);
    }

    @MainThread
    public final boolean contains(@NotNull String key) {
        Intrinsics.checkNotNullParameter(key, "key");
        return this.f1361a.containsKey(key);
    }

    @MainThread
    @Nullable
    public final <T> T get(@NotNull String key) {
        Intrinsics.checkNotNullParameter(key, "key");
        return (T) this.f1361a.get(key);
    }

    @MainThread
    @NotNull
    public final <T> MutableLiveData<T> getLiveData(@NotNull String key) {
        Intrinsics.checkNotNullParameter(key, "key");
        return b(key, false, null);
    }

    @MainThread
    @NotNull
    public final <T> StateFlow<T> getStateFlow(@NotNull String key, T t) {
        Intrinsics.checkNotNullParameter(key, "key");
        Map<String, MutableStateFlow<Object>> map = this.d;
        MutableStateFlow<Object> mutableStateFlow = map.get(key);
        if (mutableStateFlow == null) {
            if (!this.f1361a.containsKey(key)) {
                this.f1361a.put(key, t);
            }
            mutableStateFlow = StateFlowKt.MutableStateFlow(this.f1361a.get(key));
            this.d.put(key, mutableStateFlow);
            map.put(key, mutableStateFlow);
        }
        return FlowKt.asStateFlow(mutableStateFlow);
    }

    @MainThread
    @NotNull
    public final Set<String> keys() {
        return b0.plus(b0.plus((Set) this.f1361a.keySet(), (Iterable) this.b.keySet()), (Iterable) this.c.keySet());
    }

    @MainThread
    @Nullable
    public final <T> T remove(@NotNull String key) {
        Intrinsics.checkNotNullParameter(key, "key");
        T t = (T) this.f1361a.remove(key);
        SavingStateLiveData<?> remove = this.c.remove(key);
        if (remove != null) {
            remove.detach();
        }
        this.d.remove(key);
        return t;
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    @NotNull
    public final SavedStateRegistry.SavedStateProvider savedStateProvider() {
        return this.e;
    }

    @MainThread
    public final <T> void set(@NotNull String key, @Nullable T t) {
        Intrinsics.checkNotNullParameter(key, "key");
        if (Companion.validateValue(t)) {
            SavingStateLiveData<?> savingStateLiveData = this.c.get(key);
            SavingStateLiveData<?> savingStateLiveData2 = savingStateLiveData instanceof MutableLiveData ? savingStateLiveData : null;
            if (savingStateLiveData2 != null) {
                savingStateLiveData2.setValue(t);
            } else {
                this.f1361a.put(key, t);
            }
            MutableStateFlow<Object> mutableStateFlow = this.d.get(key);
            if (mutableStateFlow == null) {
                return;
            }
            mutableStateFlow.setValue(t);
            return;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Can't put value with type ");
        Intrinsics.checkNotNull(t);
        sb.append(t.getClass());
        sb.append(" into saved state");
        throw new IllegalArgumentException(sb.toString());
    }

    @MainThread
    public final void setSavedStateProvider(@NotNull String key, @NotNull SavedStateRegistry.SavedStateProvider provider) {
        Intrinsics.checkNotNullParameter(key, "key");
        Intrinsics.checkNotNullParameter(provider, "provider");
        this.b.put(key, provider);
    }

    @MainThread
    @NotNull
    public final <T> MutableLiveData<T> getLiveData(@NotNull String key, T t) {
        Intrinsics.checkNotNullParameter(key, "key");
        return b(key, true, t);
    }

    /* loaded from: classes.dex */
    public static final class SavingStateLiveData<T> extends MutableLiveData<T> {
        @NotNull
        public String l;
        @Nullable
        public SavedStateHandle m;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public SavingStateLiveData(@Nullable SavedStateHandle savedStateHandle, @NotNull String key, T t) {
            super(t);
            Intrinsics.checkNotNullParameter(key, "key");
            this.l = key;
            this.m = savedStateHandle;
        }

        public final void detach() {
            this.m = null;
        }

        @Override // androidx.lifecycle.MutableLiveData, androidx.lifecycle.LiveData
        public void setValue(T t) {
            SavedStateHandle savedStateHandle = this.m;
            if (savedStateHandle != null) {
                savedStateHandle.f1361a.put(this.l, t);
                MutableStateFlow mutableStateFlow = (MutableStateFlow) savedStateHandle.d.get(this.l);
                if (mutableStateFlow != null) {
                    mutableStateFlow.setValue(t);
                }
            }
            super.setValue(t);
        }

        public SavingStateLiveData(@Nullable SavedStateHandle savedStateHandle, @NotNull String key) {
            Intrinsics.checkNotNullParameter(key, "key");
            this.l = key;
            this.m = savedStateHandle;
        }
    }

    public SavedStateHandle() {
        this.f1361a = new LinkedHashMap();
        this.b = new LinkedHashMap();
        this.c = new LinkedHashMap();
        this.d = new LinkedHashMap();
        this.e = new SavedStateRegistry.SavedStateProvider() { // from class: androidx.lifecycle.g
            @Override // androidx.savedstate.SavedStateRegistry.SavedStateProvider
            public final Bundle saveState() {
                Bundle c;
                c = SavedStateHandle.c(SavedStateHandle.this);
                return c;
            }
        };
    }
}
