package com.coveiot.android.activitymodes.activity1k.fragment;

import android.text.Editable;
import android.text.TextWatcher;
import androidx.fragment.app.FragmentActivity;
import com.coveiot.android.activitymodes.activity1k.adapter.ActivitiesSelectionAdapterNew;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes2.dex */
public final class FragmentActivitySelectionNew$onViewCreated$1 implements TextWatcher {
    public final /* synthetic */ FragmentActivitySelectionNew h;

    public FragmentActivitySelectionNew$onViewCreated$1(FragmentActivitySelectionNew fragmentActivitySelectionNew) {
        this.h = fragmentActivitySelectionNew;
    }

    public static final void b(FragmentActivitySelectionNew this$0, Editable s) {
        ActivitiesSelectionAdapterNew activitiesSelectionAdapterNew;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(s, "$s");
        activitiesSelectionAdapterNew = this$0.x;
        Intrinsics.checkNotNull(activitiesSelectionAdapterNew);
        activitiesSelectionAdapterNew.search(s.toString());
    }

    @Override // android.text.TextWatcher
    public void afterTextChanged(@NotNull final Editable s) {
        Intrinsics.checkNotNullParameter(s, "s");
        FragmentActivity activity = this.h.getActivity();
        if (activity != null) {
            final FragmentActivitySelectionNew fragmentActivitySelectionNew = this.h;
            activity.runOnUiThread(new Runnable() { // from class: com.coveiot.android.activitymodes.activity1k.fragment.m
                @Override // java.lang.Runnable
                public final void run() {
                    FragmentActivitySelectionNew$onViewCreated$1.b(FragmentActivitySelectionNew.this, s);
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
