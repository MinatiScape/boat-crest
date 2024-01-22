package com.coveiot.android.fitnessbuddies.activities;

import android.os.Handler;
import com.coveiot.android.fitnessbuddies.R;
import com.coveiot.android.fitnessbuddies.adapters.ShowContactsAdapterNew;
import com.coveiot.android.theme.LoadingDialog;
import com.coveiot.coveaccess.CoveApiListener;
import com.coveiot.coveaccess.fitnessbuddies.model.SendFitnessBuddyRequestResponse;
import com.coveiot.coveaccess.model.CoveApiErrorModel;
import com.coveiot.utils.model.CoveContact;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes4.dex */
public final class AddBuddiesActivityNew$onSubmitClicked$1 implements CoveApiListener<SendFitnessBuddyRequestResponse, CoveApiErrorModel> {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ AddBuddiesActivityNew f4424a;
    public final /* synthetic */ CoveContact b;

    public AddBuddiesActivityNew$onSubmitClicked$1(AddBuddiesActivityNew addBuddiesActivityNew, CoveContact coveContact) {
        this.f4424a = addBuddiesActivityNew;
        this.b = coveContact;
    }

    public static final void b(CoveContact contact, AddBuddiesActivityNew this$0) {
        ShowContactsAdapterNew showContactsAdapterNew;
        ShowContactsAdapterNew showContactsAdapterNew2;
        Intrinsics.checkNotNullParameter(contact, "$contact");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        contact.setRequestSent(true);
        showContactsAdapterNew = this$0.u;
        if (showContactsAdapterNew != null) {
            showContactsAdapterNew.notifyDataSetChanged();
        }
        showContactsAdapterNew2 = this$0.v;
        if (showContactsAdapterNew2 != null) {
            showContactsAdapterNew2.notifyDataSetChanged();
        }
    }

    @Override // com.coveiot.coveaccess.CoveApiListener
    public void onError(@Nullable CoveApiErrorModel coveApiErrorModel) {
        if (this.f4424a.isFinishing() || this.f4424a.q == null) {
            return;
        }
        LoadingDialog loadingDialog = this.f4424a.q;
        Intrinsics.checkNotNull(loadingDialog);
        if (loadingDialog.isShowing()) {
            LoadingDialog loadingDialog2 = this.f4424a.q;
            Intrinsics.checkNotNull(loadingDialog2);
            loadingDialog2.dismiss();
        }
        AddBuddiesActivityNew addBuddiesActivityNew = this.f4424a;
        String string = addBuddiesActivityNew.getResources().getString(R.string.something_went_wrong);
        Intrinsics.checkNotNullExpressionValue(string, "resources.getString(R.string.something_went_wrong)");
        addBuddiesActivityNew.P(string);
    }

    @Override // com.coveiot.coveaccess.CoveApiListener
    public void onSuccess(@Nullable SendFitnessBuddyRequestResponse sendFitnessBuddyRequestResponse) {
        Handler handler;
        if (this.f4424a.isFinishing() || this.f4424a.q == null) {
            return;
        }
        AddBuddiesActivityNew addBuddiesActivityNew = this.f4424a;
        String string = addBuddiesActivityNew.getString(R.string.request_has_been_sent);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.request_has_been_sent)");
        addBuddiesActivityNew.P(string);
        LoadingDialog loadingDialog = this.f4424a.q;
        Intrinsics.checkNotNull(loadingDialog);
        if (loadingDialog.isShowing()) {
            LoadingDialog loadingDialog2 = this.f4424a.q;
            Intrinsics.checkNotNull(loadingDialog2);
            loadingDialog2.dismiss();
        }
        handler = this.f4424a.w;
        final CoveContact coveContact = this.b;
        final AddBuddiesActivityNew addBuddiesActivityNew2 = this.f4424a;
        handler.postDelayed(new Runnable() { // from class: com.coveiot.android.fitnessbuddies.activities.c0
            @Override // java.lang.Runnable
            public final void run() {
                AddBuddiesActivityNew$onSubmitClicked$1.b(CoveContact.this, addBuddiesActivityNew2);
            }
        }, 500L);
    }
}
