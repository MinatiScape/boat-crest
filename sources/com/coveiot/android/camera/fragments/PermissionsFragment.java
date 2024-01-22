package com.coveiot.android.camera.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import com.coveiot.android.camera.R;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.collections.ArraysKt___ArraysKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes3.dex */
public final class PermissionsFragment extends Fragment {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();

    /* loaded from: classes3.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final boolean hasPermissions(@NotNull Context context) {
            String[] strArr;
            Intrinsics.checkNotNullParameter(context, "context");
            strArr = PermissionsFragmentKt.f4097a;
            int length = strArr.length;
            int i = 0;
            while (true) {
                if (i >= length) {
                    return true;
                }
                if (!(ContextCompat.checkSelfPermission(context, strArr[i]) == 0)) {
                    return false;
                }
                i++;
            }
        }
    }

    public void _$_clearFindViewByIdCache() {
        this._$_findViewCache.clear();
    }

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

    @Override // androidx.fragment.app.Fragment
    public void onCreate(@Nullable Bundle bundle) {
        String[] strArr;
        super.onCreate(bundle);
        Companion companion = Companion;
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        if (!companion.hasPermissions(requireContext)) {
            strArr = PermissionsFragmentKt.f4097a;
            requestPermissions(strArr, 10);
            return;
        }
        Navigation.findNavController(requireActivity(), R.id.fragment_container).navigate(PermissionsFragmentDirections.actionPermissionsToCamera());
    }

    @Override // androidx.fragment.app.Fragment
    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    @Override // androidx.fragment.app.Fragment
    public void onRequestPermissionsResult(int i, @NotNull String[] permissions, @NotNull int[] grantResults) {
        Intrinsics.checkNotNullParameter(permissions, "permissions");
        Intrinsics.checkNotNullParameter(grantResults, "grantResults");
        super.onRequestPermissionsResult(i, permissions, grantResults);
        if (i == 10) {
            Integer firstOrNull = ArraysKt___ArraysKt.firstOrNull(grantResults);
            if (firstOrNull != null && firstOrNull.intValue() == 0) {
                Toast.makeText(getContext(), "Permission request granted", 1).show();
                Navigation.findNavController(requireActivity(), R.id.fragment_container).navigate(PermissionsFragmentDirections.actionPermissionsToCamera());
                return;
            }
            Toast.makeText(getContext(), "Permission request denied", 1).show();
        }
    }
}
