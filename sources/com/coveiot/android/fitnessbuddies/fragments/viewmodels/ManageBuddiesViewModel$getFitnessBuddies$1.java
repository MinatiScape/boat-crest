package com.coveiot.android.fitnessbuddies.fragments.viewmodels;

import android.app.Application;
import android.content.Context;
import com.coveiot.android.fitnessbuddies.R;
import com.coveiot.android.fitnessbuddies.utils.PreferenceManager;
import com.coveiot.coveaccess.CoveApiListener;
import com.coveiot.coveaccess.fitnessbuddies.model.GetFitnessBuddiesResponse;
import com.coveiot.coveaccess.fitnessbuddies.model.common.Requests;
import com.coveiot.coveaccess.model.CoveApiErrorModel;
import java.util.LinkedHashMap;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes4.dex */
public final class ManageBuddiesViewModel$getFitnessBuddies$1 implements CoveApiListener<GetFitnessBuddiesResponse, CoveApiErrorModel> {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ ManageBuddiesViewModel f4460a;

    @Override // com.coveiot.coveaccess.CoveApiListener
    public void onError(@Nullable CoveApiErrorModel coveApiErrorModel) {
        this.f4460a.i();
    }

    @Override // com.coveiot.coveaccess.CoveApiListener
    public void onSuccess(@Nullable GetFitnessBuddiesResponse getFitnessBuddiesResponse) {
        List list;
        LinkedHashMap linkedHashMap;
        List list2;
        List list3;
        List list4;
        List list5;
        List list6;
        list = this.f4460a.f;
        if (list != null) {
            list5 = this.f4460a.f;
            Intrinsics.checkNotNull(list5);
            if (!list5.isEmpty()) {
                list6 = this.f4460a.f;
                Intrinsics.checkNotNull(list6);
                list6.clear();
            }
        }
        ManageBuddiesViewModel manageBuddiesViewModel = this.f4460a;
        Intrinsics.checkNotNull(getFitnessBuddiesResponse);
        manageBuddiesViewModel.f = getFitnessBuddiesResponse.fitnessBuddies;
        linkedHashMap = this.f4460a.l;
        Context context = this.f4460a.getContext();
        Intrinsics.checkNotNull(context);
        String string = context.getResources().getString(R.string.fitness_buddies);
        Intrinsics.checkNotNullExpressionValue(string, "context!!.resources.getS…R.string.fitness_buddies)");
        list2 = this.f4460a.f;
        linkedHashMap.put(string, list2);
        PreferenceManager.Companion companion = PreferenceManager.Companion;
        Application application = this.f4460a.getApplication();
        Intrinsics.checkNotNull(application);
        List<Requests> list7 = getFitnessBuddiesResponse.fitnessBuddies;
        Intrinsics.checkNotNullExpressionValue(list7, "p0!!.fitnessBuddies");
        companion.saveFitnessBuddies(application, list7);
        list3 = this.f4460a.d;
        Intrinsics.checkNotNull(list3);
        if (list3.size() <= 0) {
            list4 = this.f4460a.e;
            Intrinsics.checkNotNull(list4);
            if (list4.size() <= 0) {
                this.f4460a.i();
                return;
            }
        }
        this.f4460a.h();
    }
}
