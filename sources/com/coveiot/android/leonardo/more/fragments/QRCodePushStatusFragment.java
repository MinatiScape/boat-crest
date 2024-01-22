package com.coveiot.android.leonardo.more.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.coveiot.android.boat.R;
import com.coveiot.android.theme.BaseFragment;
import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes5.dex */
public final class QRCodePushStatusFragment extends BaseFragment {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    @Nullable
    public String m;

    /* loaded from: classes5.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        @NotNull
        public final QRCodePushStatusFragment newInstance(@NotNull String param1) {
            Intrinsics.checkNotNullParameter(param1, "param1");
            QRCodePushStatusFragment qRCodePushStatusFragment = new QRCodePushStatusFragment();
            Bundle bundle = new Bundle();
            bundle.putString("param1", param1);
            qRCodePushStatusFragment.setArguments(bundle);
            return qRCodePushStatusFragment;
        }
    }

    public static final void m(QRCodePushStatusFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.requireActivity().finish();
    }

    @JvmStatic
    @NotNull
    public static final QRCodePushStatusFragment newInstance(@NotNull String str) {
        return Companion.newInstance(str);
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

    public final void l() {
        String str = this.m;
        if (str != null && kotlin.text.m.equals$default(str, getString(R.string.success), false, 2, null)) {
            ((ConstraintLayout) _$_findCachedViewById(R.id.clSuccess)).setVisibility(0);
            ((ConstraintLayout) _$_findCachedViewById(R.id.clFailure)).setVisibility(8);
        } else {
            ((ConstraintLayout) _$_findCachedViewById(R.id.clFailure)).setVisibility(0);
            ((ConstraintLayout) _$_findCachedViewById(R.id.clSuccess)).setVisibility(8);
        }
        ((Button) _$_findCachedViewById(R.id.btnOk)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.fragments.e1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                QRCodePushStatusFragment.m(QRCodePushStatusFragment.this, view);
            }
        });
    }

    @Override // com.coveiot.android.theme.BaseFragment, androidx.fragment.app.Fragment
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        Bundle arguments = getArguments();
        if (arguments != null) {
            arguments.getString("param1");
        }
    }

    @Override // androidx.fragment.app.Fragment
    @Nullable
    public View onCreateView(@NotNull LayoutInflater inflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        return inflater.inflate(R.layout.fragment_q_r_code_push_status, viewGroup, false);
    }

    @Override // com.coveiot.android.theme.BaseFragment, androidx.fragment.app.Fragment
    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(@NotNull View view, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, bundle);
        Bundle arguments = getArguments();
        if (arguments != null) {
            Serializable serializable = arguments.getSerializable("param1");
            Intrinsics.checkNotNull(serializable, "null cannot be cast to non-null type kotlin.String");
            this.m = (String) serializable;
        }
        l();
    }
}
