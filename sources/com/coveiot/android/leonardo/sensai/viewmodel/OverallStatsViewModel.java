package com.coveiot.android.leonardo.sensai.viewmodel;

import android.content.Context;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.coveiot.coveaccess.CoveApiListener;
import com.coveiot.coveaccess.activitysession.TraqConfigApi;
import com.coveiot.coveaccess.dailyfitnessdata.fitnessdata.GetOverallStatsRes;
import com.coveiot.coveaccess.dailyfitnessdata.fitnessdata.GetOverallStatsResponse;
import com.coveiot.coveaccess.model.CoveApiErrorModel;
import com.coveiot.utils.utility.LogHelper;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes5.dex */
public final class OverallStatsViewModel extends ViewModel {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f5392a;
    @NotNull
    public final String b;
    @NotNull
    public MutableLiveData<GetOverallStatsResponse> c;

    public OverallStatsViewModel(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.f5392a = context;
        this.b = "ViewModelOverallStats";
        this.c = new MutableLiveData<>();
    }

    @NotNull
    public final Context getContext() {
        return this.f5392a;
    }

    @NotNull
    public final MutableLiveData<GetOverallStatsResponse> getGetOverallStatsResponseLiveData() {
        return this.c;
    }

    /* JADX WARN: Type inference failed for: r1v0, types: [com.coveiot.coveaccess.dailyfitnessdata.fitnessdata.GetOverallStatsResponse, T] */
    @NotNull
    public final MutableLiveData<GetOverallStatsResponse> getSessionsListFromServer(@NotNull String range, @NotNull String sessionType) {
        Intrinsics.checkNotNullParameter(range, "range");
        Intrinsics.checkNotNullParameter(sessionType, "sessionType");
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        objectRef.element = new GetOverallStatsResponse();
        TraqConfigApi.getOverallStats(range, sessionType, new CoveApiListener<GetOverallStatsRes, CoveApiErrorModel>() { // from class: com.coveiot.android.leonardo.sensai.viewmodel.OverallStatsViewModel$getSessionsListFromServer$1
            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onError(@Nullable CoveApiErrorModel coveApiErrorModel) {
            }

            /* JADX WARN: Type inference failed for: r3v1, types: [com.coveiot.coveaccess.dailyfitnessdata.fitnessdata.GetOverallStatsResponse, T, java.lang.Object] */
            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onSuccess(@NotNull GetOverallStatsRes p0) {
                String str;
                Intrinsics.checkNotNullParameter(p0, "p0");
                Ref.ObjectRef<GetOverallStatsResponse> objectRef2 = objectRef;
                ?? getOverallStatsResponse = p0.getGetOverallStatsResponse();
                Intrinsics.checkNotNullExpressionValue(getOverallStatsResponse, "p0.getGetOverallStatsResponse()");
                objectRef2.element = getOverallStatsResponse;
                str = this.b;
                LogHelper.d(str, objectRef.element.toString());
                this.getGetOverallStatsResponseLiveData().postValue(objectRef.element);
            }
        });
        return this.c;
    }

    public final void setGetOverallStatsResponseLiveData(@NotNull MutableLiveData<GetOverallStatsResponse> mutableLiveData) {
        Intrinsics.checkNotNullParameter(mutableLiveData, "<set-?>");
        this.c = mutableLiveData;
    }
}
