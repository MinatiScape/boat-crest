package com.coveiot.android.activitymodes.activity1k.fragment;

import android.text.Editable;
import android.text.TextWatcher;
import androidx.fragment.app.FragmentActivity;
import com.coveiot.android.activitymodes.activity1k.adapter.ActivityCategoryAdapterNew;
import com.coveiot.android.activitymodes.activity1k.model.ActivityCategoriesModel;
import java.util.ArrayList;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes2.dex */
public final class FragmentCategories$onActivityCreated$1 implements TextWatcher {
    public final /* synthetic */ FragmentCategories h;

    public FragmentCategories$onActivityCreated$1(FragmentCategories fragmentCategories) {
        this.h = fragmentCategories;
    }

    public static final void b(FragmentCategories this$0, Editable s) {
        ActivityCategoryAdapterNew activityCategoryAdapterNew;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(s, "$s");
        if (this$0.getExpandableListTitle() != null) {
            ArrayList<ActivityCategoriesModel> expandableListTitle = this$0.getExpandableListTitle();
            ActivityCategoryAdapterNew activityCategoryAdapterNew2 = null;
            Integer valueOf = expandableListTitle != null ? Integer.valueOf(expandableListTitle.size()) : null;
            Intrinsics.checkNotNull(valueOf);
            if (valueOf.intValue() > 0) {
                activityCategoryAdapterNew = this$0.r;
                if (activityCategoryAdapterNew == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("expandableListAdapter");
                } else {
                    activityCategoryAdapterNew2 = activityCategoryAdapterNew;
                }
                activityCategoryAdapterNew2.search(s.toString());
            }
        }
    }

    @Override // android.text.TextWatcher
    public void afterTextChanged(@NotNull final Editable s) {
        Intrinsics.checkNotNullParameter(s, "s");
        FragmentActivity activity = this.h.getActivity();
        if (activity != null) {
            final FragmentCategories fragmentCategories = this.h;
            activity.runOnUiThread(new Runnable() { // from class: com.coveiot.android.activitymodes.activity1k.fragment.u
                @Override // java.lang.Runnable
                public final void run() {
                    FragmentCategories$onActivityCreated$1.b(FragmentCategories.this, s);
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
