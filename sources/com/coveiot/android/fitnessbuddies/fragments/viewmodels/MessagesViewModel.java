package com.coveiot.android.fitnessbuddies.fragments.viewmodels;

import android.app.Application;
import android.content.Context;
import android.widget.Toast;
import androidx.lifecycle.AndroidViewModel;
import com.coveiot.android.fitnessbuddies.R;
import com.coveiot.android.fitnessbuddies.fragments.contarctors.MessagesContractor;
import com.coveiot.android.fitnessbuddies.utils.CoveUtils;
import com.coveiot.android.fitnessbuddies.utils.PreferenceManager;
import com.coveiot.coveaccess.CoveApiListener;
import com.coveiot.coveaccess.CoveSocial;
import com.coveiot.coveaccess.fitnessbuddies.model.GetFitnessBuddiesMessagesResponse;
import com.coveiot.coveaccess.fitnessbuddies.model.common.Messages;
import com.coveiot.coveaccess.model.CoveApiErrorModel;
import java.util.ArrayList;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes4.dex */
public final class MessagesViewModel extends AndroidViewModel {
    @Nullable
    public MessagesContractor d;
    @Nullable
    public List<? extends Messages> e;
    @Nullable
    public Context f;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MessagesViewModel(@NotNull Application application, @NotNull MessagesContractor messagesContractor) {
        super(application);
        Intrinsics.checkNotNullParameter(application, "application");
        Intrinsics.checkNotNullParameter(messagesContractor, "messagesContractor");
        this.d = messagesContractor;
        this.e = new ArrayList();
        this.f = getApplication();
    }

    @Nullable
    public final Context getContext() {
        return this.f;
    }

    @Nullable
    public final MessagesContractor getMessagesContractor() {
        return this.d;
    }

    @Nullable
    public final List<Messages> getMesssages() {
        return this.e;
    }

    public final void loadFitneesMessages() {
        if (CoveUtils.INSTANCE.isNetConnected(getApplication())) {
            CoveSocial.getBuddyMessages(new CoveApiListener<GetFitnessBuddiesMessagesResponse, CoveApiErrorModel>() { // from class: com.coveiot.android.fitnessbuddies.fragments.viewmodels.MessagesViewModel$loadFitneesMessages$1
                @Override // com.coveiot.coveaccess.CoveApiListener
                public void onError(@Nullable CoveApiErrorModel coveApiErrorModel) {
                    if (MessagesViewModel.this.getMessagesContractor() != null) {
                        MessagesContractor messagesContractor = MessagesViewModel.this.getMessagesContractor();
                        Intrinsics.checkNotNull(messagesContractor);
                        messagesContractor.dismissProgress();
                        MessagesContractor messagesContractor2 = MessagesViewModel.this.getMessagesContractor();
                        Intrinsics.checkNotNull(messagesContractor2);
                        messagesContractor2.showEmptyLayout();
                    }
                    Intrinsics.checkNotNull(coveApiErrorModel);
                    String msg = coveApiErrorModel.getMsg();
                    Intrinsics.checkNotNullExpressionValue(msg, "p0!!.msg");
                    if (StringsKt__StringsKt.contains$default((CharSequence) msg, (CharSequence) "Unable to resolve host", false, 2, (Object) null)) {
                        Context context = MessagesViewModel.this.getContext();
                        Intrinsics.checkNotNull(context);
                        Context context2 = MessagesViewModel.this.getContext();
                        Intrinsics.checkNotNull(context2);
                        Toast.makeText(context, context2.getString(R.string.please_check_your_internet), 1).show();
                    }
                }

                @Override // com.coveiot.coveaccess.CoveApiListener
                public void onSuccess(@Nullable GetFitnessBuddiesMessagesResponse getFitnessBuddiesMessagesResponse) {
                    if (MessagesViewModel.this.getMessagesContractor() != null) {
                        MessagesContractor messagesContractor = MessagesViewModel.this.getMessagesContractor();
                        Intrinsics.checkNotNull(messagesContractor);
                        messagesContractor.dismissProgress();
                    }
                    if (MessagesViewModel.this.getContext() != null) {
                        PreferenceManager.Companion companion = PreferenceManager.Companion;
                        Context context = MessagesViewModel.this.getContext();
                        Intrinsics.checkNotNull(context);
                        Intrinsics.checkNotNull(getFitnessBuddiesMessagesResponse);
                        List<Messages> list = getFitnessBuddiesMessagesResponse.items;
                        Intrinsics.checkNotNullExpressionValue(list, "p0!!.items");
                        companion.saveFitnessMessages(context, list);
                    }
                    MessagesViewModel messagesViewModel = MessagesViewModel.this;
                    Intrinsics.checkNotNull(getFitnessBuddiesMessagesResponse);
                    messagesViewModel.setMesssages(getFitnessBuddiesMessagesResponse.items);
                    if (MessagesViewModel.this.getMessagesContractor() != null) {
                        MessagesContractor messagesContractor2 = MessagesViewModel.this.getMessagesContractor();
                        Intrinsics.checkNotNull(messagesContractor2);
                        List<Messages> messsages = MessagesViewModel.this.getMesssages();
                        Intrinsics.checkNotNull(messsages);
                        messagesContractor2.showContent(messsages);
                    }
                }
            });
            return;
        }
        MessagesContractor messagesContractor = this.d;
        if (messagesContractor != null) {
            Intrinsics.checkNotNull(messagesContractor);
            messagesContractor.dismissProgress();
        }
        PreferenceManager.Companion companion = PreferenceManager.Companion;
        Context context = this.f;
        Intrinsics.checkNotNull(context);
        List<Messages> fitnessMessages = companion.getFitnessMessages(context);
        if (fitnessMessages != null) {
            if (!fitnessMessages.isEmpty()) {
                MessagesContractor messagesContractor2 = this.d;
                if (messagesContractor2 != null) {
                    Intrinsics.checkNotNull(messagesContractor2);
                    messagesContractor2.showContent(fitnessMessages);
                    return;
                }
                return;
            }
            MessagesContractor messagesContractor3 = this.d;
            if (messagesContractor3 != null) {
                Intrinsics.checkNotNull(messagesContractor3);
                messagesContractor3.showEmptyLayout();
            }
        }
    }

    public final void setContext(@Nullable Context context) {
        this.f = context;
    }

    public final void setMessagesContractor(@Nullable MessagesContractor messagesContractor) {
        this.d = messagesContractor;
    }

    public final void setMesssages(@Nullable List<? extends Messages> list) {
        this.e = list;
    }
}
