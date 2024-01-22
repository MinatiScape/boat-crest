package com.coveiot.android.tappy.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.clevertap.android.sdk.Constants;
import com.coveiot.android.tappy.R;
import com.coveiot.android.theme.BaseFragment;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes7.dex */
public final class FragmentAddNfcStrap extends BaseFragment {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    public int[] imagesArray;
    @Nullable
    public ImageView m;
    public int n;
    public int o;
    public int p;

    /* loaded from: classes7.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        @NotNull
        public final FragmentAddNfcStrap newInstance(@NotNull int[] images, int i) {
            Intrinsics.checkNotNullParameter(images, "images");
            FragmentAddNfcStrap fragmentAddNfcStrap = new FragmentAddNfcStrap();
            fragmentAddNfcStrap.setImagesArray(images);
            if (!(fragmentAddNfcStrap.getImagesArray().length == 0)) {
                fragmentAddNfcStrap.o = fragmentAddNfcStrap.getImagesArray().length - 1;
            }
            fragmentAddNfcStrap.p = i;
            return fragmentAddNfcStrap;
        }
    }

    public static final void m(FragmentAddNfcStrap this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (this$0.n > this$0.o) {
            this$0.n = 0;
        }
        this$0.l();
    }

    @JvmStatic
    @NotNull
    public static final FragmentAddNfcStrap newInstance(@NotNull int[] iArr, int i) {
        return Companion.newInstance(iArr, i);
    }

    @Override // com.coveiot.android.theme.BaseFragment
    public void _$_clearFindViewByIdCache() {
        this._$_findViewCache.clear();
    }

    @Override // com.coveiot.android.theme.BaseFragment
    @Nullable
    public View _$_findCachedViewById(int i) {
        View findViewById;
        Map<Integer, View> map = this._$_findViewCache;
        View view = map.get(Integer.valueOf(i));
        if (view == null) {
            View view2 = getView();
            if (view2 == null || (findViewById = view2.findViewById(i)) == null) {
                return null;
            }
            map.put(Integer.valueOf(i), findViewById);
            return findViewById;
        }
        return view;
    }

    @NotNull
    public final int[] getImagesArray() {
        int[] iArr = this.imagesArray;
        if (iArr != null) {
            return iArr;
        }
        Intrinsics.throwUninitializedPropertyAccessException("imagesArray");
        return null;
    }

    public final void l() {
        ImageView imageView = this.m;
        if (imageView != null) {
            imageView.setImageResource(getImagesArray()[this.n]);
            int i = this.n + 1;
            this.n = i;
            if (i > this.o) {
                this.n = 0;
            }
            new Handler(Looper.getMainLooper()).postDelayed(new Runnable() { // from class: com.coveiot.android.tappy.fragment.m
                @Override // java.lang.Runnable
                public final void run() {
                    FragmentAddNfcStrap.m(FragmentAddNfcStrap.this);
                }
            }, Constants.PN_LARGE_ICON_DOWNLOAD_TIMEOUT_IN_MILLIS);
        }
    }

    @Override // androidx.fragment.app.Fragment
    @Nullable
    public View onCreateView(@NotNull LayoutInflater inflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        View inflate = inflater.inflate(R.layout.fragment_add_nfc_strap, viewGroup, false);
        this.m = (ImageView) inflate.findViewById(R.id.imageViewHolder);
        l();
        return inflate;
    }

    @Override // com.coveiot.android.theme.BaseFragment, androidx.fragment.app.Fragment
    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    public final void setImagesArray(@NotNull int[] iArr) {
        Intrinsics.checkNotNullParameter(iArr, "<set-?>");
        this.imagesArray = iArr;
    }
}
