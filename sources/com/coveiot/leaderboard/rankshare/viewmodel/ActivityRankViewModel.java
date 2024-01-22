package com.coveiot.leaderboard.rankshare.viewmodel;

import android.content.Context;
import androidx.lifecycle.ViewModel;
import com.coveiot.android.theme.SuccessResultListener;
import com.coveiot.android.theme.permissions.PermissionListener;
import com.coveiot.android.theme.permissions.PermissionType;
import com.coveiot.coveaccess.CoveApiListener;
import com.coveiot.coveaccess.leaderboard.CoveLeaderboardApi;
import com.coveiot.coveaccess.leaderboard.model.AddressModel;
import com.coveiot.coveaccess.leaderboard.model.AddressReq;
import com.coveiot.coveaccess.leaderboard.model.LocationType;
import com.coveiot.coveaccess.model.CoveApiErrorModel;
import com.coveiot.leaderboard.R;
import com.coveiot.leaderboard.utils.LeaderBoardDataUtiils;
import com.google.gson.JsonObject;
import com.mappls.sdk.services.api.geocoding.GeoCodingCriteria;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes9.dex */
public final class ActivityRankViewModel extends ViewModel {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f7210a;
    public PermissionListener mListener;
    public SuccessResultListener mViewModelListener;

    public ActivityRankViewModel(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.f7210a = context;
    }

    public final void checkStoragePermission() {
        getMListener().onPermissionSuccess(PermissionType.STORAGE);
    }

    @NotNull
    public final Context getContext() {
        return this.f7210a;
    }

    @NotNull
    public final PermissionListener getMListener() {
        PermissionListener permissionListener = this.mListener;
        if (permissionListener != null) {
            return permissionListener;
        }
        Intrinsics.throwUninitializedPropertyAccessException("mListener");
        return null;
    }

    @NotNull
    public final SuccessResultListener getMViewModelListener() {
        SuccessResultListener successResultListener = this.mViewModelListener;
        if (successResultListener != null) {
            return successResultListener;
        }
        Intrinsics.throwUninitializedPropertyAccessException("mViewModelListener");
        return null;
    }

    public final void postAddressToServer(@NotNull final Context context, @NotNull final JsonObject object) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(object, "object");
        AddressReq addressReq = new AddressReq();
        try {
            if (!object.get(GeoCodingCriteria.POD_CITY).isJsonNull()) {
                addressReq.setCity(object.get(GeoCodingCriteria.POD_CITY).getAsString());
            }
            addressReq.setLatitude(object.get("latitude").getAsString());
            addressReq.setLongitude(object.get("longitude").getAsString());
            if (!object.get("locality").isJsonNull()) {
                addressReq.setLocality(object.get("locality").getAsString());
            } else if (!object.get(GeoCodingCriteria.POD_CITY).isJsonNull()) {
                addressReq.setLocality(object.get(GeoCodingCriteria.POD_CITY).getAsString());
            }
            addressReq.setCountry(object.get("country").getAsString());
            if (!object.get("administrativeArea").isJsonNull()) {
                addressReq.setAdministrativeArea(object.get("administrativeArea").getAsString());
            }
            addressReq.setLocationType(LocationType.HOME);
            CoveLeaderboardApi.saveLocation(addressReq, new CoveApiListener<AddressModel, CoveApiErrorModel>() { // from class: com.coveiot.leaderboard.rankshare.viewmodel.ActivityRankViewModel$postAddressToServer$1
                @Override // com.coveiot.coveaccess.CoveApiListener
                public void onError(@Nullable CoveApiErrorModel coveApiErrorModel) {
                    this.getMViewModelListener().onError(context.getResources().getString(R.string.something_went_wrong));
                }

                @Override // com.coveiot.coveaccess.CoveApiListener
                public void onSuccess(@Nullable AddressModel addressModel) {
                    if (addressModel != null) {
                        LeaderBoardDataUtiils.saveAddressJson(context, object.toString());
                        LeaderBoardDataUtiils.saveLocationOnboarding(context, true);
                        this.getMViewModelListener().onSuccess();
                        return;
                    }
                    this.getMViewModelListener().onError(context.getResources().getString(R.string.something_went_wrong));
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public final void setMListener(@NotNull PermissionListener permissionListener) {
        Intrinsics.checkNotNullParameter(permissionListener, "<set-?>");
        this.mListener = permissionListener;
    }

    public final void setMViewModelListener(@NotNull SuccessResultListener successResultListener) {
        Intrinsics.checkNotNullParameter(successResultListener, "<set-?>");
        this.mViewModelListener = successResultListener;
    }
}
