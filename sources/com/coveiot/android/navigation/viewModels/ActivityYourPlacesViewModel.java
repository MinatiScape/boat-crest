package com.coveiot.android.navigation.viewModels;

import android.content.Context;
import androidx.lifecycle.ViewModel;
import com.coveiot.coveaccess.CoveApiListener;
import com.coveiot.coveaccess.model.CoveApiErrorModel;
import com.coveiot.coveaccess.navigation.GetFavouritePlacesRes;
import com.coveiot.coveaccess.navigation.NavigationApi;
import com.coveiot.utils.utility.LogHelper;
import com.google.gson.Gson;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes5.dex */
public final class ActivityYourPlacesViewModel extends ViewModel {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f5548a;
    public final String b;

    public ActivityYourPlacesViewModel(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.f5548a = context;
        this.b = ActivityYourPlacesViewModel.class.getSimpleName();
    }

    @NotNull
    public final Context getContext() {
        return this.f5548a;
    }

    public final void getFavouritePlaces(@NotNull final Function2<? super Boolean, ? super GetFavouritePlacesRes, Unit> result) {
        Intrinsics.checkNotNullParameter(result, "result");
        NavigationApi.getFavouritePlaces(new CoveApiListener<GetFavouritePlacesRes, CoveApiErrorModel>() { // from class: com.coveiot.android.navigation.viewModels.ActivityYourPlacesViewModel$getFavouritePlaces$1
            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onError(@Nullable CoveApiErrorModel coveApiErrorModel) {
                String tag = ActivityYourPlacesViewModel.this.getTAG();
                LogHelper.d(tag, "getFavouritePlaces favPlacesData onError " + new Gson().toJson(coveApiErrorModel));
                result.invoke(Boolean.FALSE, null);
            }

            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onSuccess(@Nullable GetFavouritePlacesRes getFavouritePlacesRes) {
                String tag = ActivityYourPlacesViewModel.this.getTAG();
                LogHelper.d(tag, "getFavouritePlaces favPlacesData onSuccess " + new Gson().toJson(getFavouritePlacesRes));
                result.invoke(Boolean.TRUE, getFavouritePlacesRes);
            }
        });
    }

    public final String getTAG() {
        return this.b;
    }
}
