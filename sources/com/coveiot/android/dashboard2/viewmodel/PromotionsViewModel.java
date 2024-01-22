package com.coveiot.android.dashboard2.viewmodel;

import android.content.Context;
import androidx.lifecycle.ViewModel;
import com.coveiot.android.dashboard2.model.BestOffers;
import com.coveiot.android.dashboard2.viewmodel.PromotionsViewModel;
import com.coveiot.coveaccess.CoveApiListener;
import com.coveiot.coveaccess.model.CoveApiErrorModel;
import com.coveiot.coveaccess.model.server.SPromotionalOffers;
import com.coveiot.coveaccess.promotions.PromotionsApiManager;
import com.coveiot.utils.utility.AppUtils;
import java.util.ArrayList;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes4.dex */
public final class PromotionsViewModel extends ViewModel {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f4280a;

    /* loaded from: classes4.dex */
    public interface BestOffersListeners {
        void onFailure(@Nullable String str);

        void onOffersLoaded(@Nullable List<BestOffers> list);
    }

    public PromotionsViewModel(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.f4280a = context;
    }

    public final void getBestOffersFromServer(@NotNull final BestOffersListeners listeners) {
        Intrinsics.checkNotNullParameter(listeners, "listeners");
        PromotionsApiManager.getPromotionsList(new CoveApiListener<SPromotionalOffers, CoveApiErrorModel>() { // from class: com.coveiot.android.dashboard2.viewmodel.PromotionsViewModel$getBestOffersFromServer$1
            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onError(@Nullable CoveApiErrorModel coveApiErrorModel) {
                PromotionsViewModel.BestOffersListeners.this.onFailure(coveApiErrorModel != null ? coveApiErrorModel.getMsg() : null);
            }

            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onSuccess(@Nullable SPromotionalOffers sPromotionalOffers) {
                if (sPromotionalOffers != null && sPromotionalOffers.getData() != null) {
                    SPromotionalOffers.DataDTO data = sPromotionalOffers.getData();
                    if (!AppUtils.isEmpty(data != null ? data.getItems() : null)) {
                        ArrayList arrayList = new ArrayList();
                        SPromotionalOffers.DataDTO data2 = sPromotionalOffers.getData();
                        List<SPromotionalOffers.DataDTO.ItemsDTO> items = data2 != null ? data2.getItems() : null;
                        Intrinsics.checkNotNull(items);
                        for (SPromotionalOffers.DataDTO.ItemsDTO itemsDTO : items) {
                            String bannerImageUrl = itemsDTO.getBannerImageUrl();
                            Intrinsics.checkNotNullExpressionValue(bannerImageUrl, "item.bannerImageUrl");
                            String redirectUrl = itemsDTO.getRedirectUrl();
                            Intrinsics.checkNotNullExpressionValue(redirectUrl, "item.redirectUrl");
                            String redirectionType = itemsDTO.getRedirectionType();
                            Intrinsics.checkNotNullExpressionValue(redirectionType, "item.redirectionType");
                            arrayList.add(new BestOffers(bannerImageUrl, redirectUrl, redirectionType));
                        }
                        PromotionsViewModel.BestOffersListeners.this.onOffersLoaded(arrayList);
                        return;
                    }
                }
                PromotionsViewModel.BestOffersListeners.this.onOffersLoaded(null);
            }
        });
    }

    @NotNull
    public final Context getContext() {
        return this.f4280a;
    }
}
