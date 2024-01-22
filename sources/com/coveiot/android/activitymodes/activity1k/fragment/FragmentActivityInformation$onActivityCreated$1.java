package com.coveiot.android.activitymodes.activity1k.fragment;

import android.text.Editable;
import android.text.TextWatcher;
import androidx.fragment.app.FragmentActivity;
import com.coveiot.android.activitymodes.activity1k.adapter.ActivityInformationAdapter;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes2.dex */
public final class FragmentActivityInformation$onActivityCreated$1 implements TextWatcher {
    public final /* synthetic */ FragmentActivityInformation h;

    public FragmentActivityInformation$onActivityCreated$1(FragmentActivityInformation fragmentActivityInformation) {
        this.h = fragmentActivityInformation;
    }

    public static final void b(FragmentActivityInformation this$0, Editable s) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(s, "$s");
        ActivityInformationAdapter expandableListAdapter = this$0.getExpandableListAdapter();
        Intrinsics.checkNotNull(expandableListAdapter);
        expandableListAdapter.search(s.toString());
    }

    @Override // android.text.TextWatcher
    public void afterTextChanged(@NotNull final Editable s) {
        Intrinsics.checkNotNullParameter(s, "s");
        FragmentActivity activity = this.h.getActivity();
        if (activity != null) {
            final FragmentActivityInformation fragmentActivityInformation = this.h;
            activity.runOnUiThread(new Runnable() { // from class: com.coveiot.android.activitymodes.activity1k.fragment.d
                @Override // java.lang.Runnable
                public final void run() {
                    FragmentActivityInformation$onActivityCreated$1.b(FragmentActivityInformation.this, s);
                }
            });
        }
    }

    @Override // android.text.TextWatcher
    public void beforeTextChanged(@NotNull CharSequence s, int i, int i2, int i3) {
        Intrinsics.checkNotNullParameter(s, "s");
    }

    @Override // android.text.TextWatcher
    public void onTextChanged(@NotNull CharSequence s, int i, int i2, int i3) {
        Intrinsics.checkNotNullParameter(s, "s");
    }
}
