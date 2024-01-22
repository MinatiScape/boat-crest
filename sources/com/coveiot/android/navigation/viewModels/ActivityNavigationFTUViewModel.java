package com.coveiot.android.navigation.viewModels;

import android.content.Context;
import androidx.lifecycle.ViewModel;
import com.coveiot.android.navigation.models.NavigationDisclaimerData;
import com.coveiot.android.navigation.utils.NavigationConstants;
import com.coveiot.android.theme.R;
import com.coveiot.coveaccess.CoveApiListener;
import com.coveiot.coveaccess.CoveOnboarding;
import com.coveiot.coveaccess.constants.CoveApiConstants;
import com.coveiot.coveaccess.model.CoveApiErrorModel;
import com.coveiot.coveaccess.model.server.SRemoteConfigResponse;
import com.coveiot.coveaccess.onboarding.model.AcceptLegalTermsReq;
import com.coveiot.coveaccess.onboarding.model.AcceptLegalTermsRes;
import com.coveiot.covepreferences.SessionManager;
import com.google.gson.Gson;
import java.util.List;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes5.dex */
public final class ActivityNavigationFTUViewModel extends ViewModel {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f5535a;

    public ActivityNavigationFTUViewModel(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.f5535a = context;
    }

    public final void acceptDisclaimer(@NotNull AcceptLegalTermsReq acceptLegalTermsReq, @NotNull final Function2<? super Boolean, ? super String, Unit> result) {
        Intrinsics.checkNotNullParameter(acceptLegalTermsReq, "acceptLegalTermsReq");
        Intrinsics.checkNotNullParameter(result, "result");
        CoveOnboarding.acceptTermsAndConditions(acceptLegalTermsReq, new CoveApiListener<AcceptLegalTermsRes, CoveApiErrorModel>() { // from class: com.coveiot.android.navigation.viewModels.ActivityNavigationFTUViewModel$acceptDisclaimer$1
            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onError(@Nullable CoveApiErrorModel coveApiErrorModel) {
                if (coveApiErrorModel != null) {
                    if (coveApiErrorModel.getMsg() != null) {
                        Function2<Boolean, String, Unit> function2 = result;
                        Boolean bool = Boolean.FALSE;
                        String msg = coveApiErrorModel.getMsg();
                        Intrinsics.checkNotNullExpressionValue(msg, "`object`.msg");
                        function2.invoke(bool, msg);
                        return;
                    }
                    Function2<Boolean, String, Unit> function22 = result;
                    Boolean bool2 = Boolean.FALSE;
                    String string = this.getContext().getString(R.string.some_thing_went_wrong);
                    Intrinsics.checkNotNullExpressionValue(string, "context.getString(R.string.some_thing_went_wrong)");
                    function22.invoke(bool2, string);
                    return;
                }
                Function2<Boolean, String, Unit> function23 = result;
                Boolean bool3 = Boolean.FALSE;
                String string2 = this.getContext().getString(R.string.some_thing_went_wrong);
                Intrinsics.checkNotNullExpressionValue(string2, "context.getString(R.string.some_thing_went_wrong)");
                function23.invoke(bool3, string2);
            }

            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onSuccess(@Nullable AcceptLegalTermsRes acceptLegalTermsRes) {
                if (acceptLegalTermsRes != null) {
                    if (acceptLegalTermsRes.getStatus().equals(CoveApiConstants.RESPONSE_STATUS_VALUE_OK)) {
                        result.invoke(Boolean.TRUE, "");
                        return;
                    } else {
                        result.invoke(Boolean.FALSE, "");
                        return;
                    }
                }
                result.invoke(Boolean.FALSE, "");
            }
        });
    }

    public final void getAppDisclaimer(@NotNull final Function2<? super Boolean, ? super NavigationDisclaimerData, Unit> result) {
        Intrinsics.checkNotNullParameter(result, "result");
        CoveOnboarding.getV2RemoteConfiguration("1", new CoveApiListener<SRemoteConfigResponse, CoveApiErrorModel>() { // from class: com.coveiot.android.navigation.viewModels.ActivityNavigationFTUViewModel$getAppDisclaimer$1
            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onError(@Nullable CoveApiErrorModel coveApiErrorModel) {
                result.invoke(Boolean.FALSE, null);
            }

            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onSuccess(@Nullable SRemoteConfigResponse sRemoteConfigResponse) {
                List<SRemoteConfigResponse.DataBean.LegalBean.Doc> doc;
                if (sRemoteConfigResponse != null) {
                    if (sRemoteConfigResponse.getData().getLegalBean() == null || (doc = sRemoteConfigResponse.getData().getLegalBean().getDoc()) == null) {
                        return;
                    }
                    int size = doc.size();
                    for (int i = 0; i < size; i++) {
                        if (doc.get(i).getType().equals(NavigationConstants.MAP_NAV_DISCLAIMER)) {
                            String version = doc.get(i).getVersion();
                            Intrinsics.checkNotNullExpressionValue(version, "docBean[i].version");
                            String htmlUrl = doc.get(i).getHtmlUrl();
                            Intrinsics.checkNotNullExpressionValue(htmlUrl, "docBean[i].htmlUrl");
                            String dvcText = doc.get(i).getDvcText();
                            Intrinsics.checkNotNullExpressionValue(dvcText, "docBean[i].dvcText");
                            NavigationDisclaimerData navigationDisclaimerData = new NavigationDisclaimerData(version, htmlUrl, dvcText);
                            SessionManager.getInstance(ActivityNavigationFTUViewModel.this.getContext()).saveNavigationDiscliamerData(new Gson().toJson(navigationDisclaimerData));
                            result.invoke(Boolean.TRUE, navigationDisclaimerData);
                            return;
                        }
                    }
                    return;
                }
                result.invoke(Boolean.FALSE, null);
            }
        });
    }

    @NotNull
    public final Context getContext() {
        return this.f5535a;
    }
}
