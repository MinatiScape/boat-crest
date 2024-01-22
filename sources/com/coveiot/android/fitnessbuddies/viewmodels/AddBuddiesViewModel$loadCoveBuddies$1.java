package com.coveiot.android.fitnessbuddies.viewmodels;

import com.coveiot.android.fitnessbuddies.R;
import com.coveiot.android.fitnessbuddies.utils.CoveUtils;
import com.coveiot.android.fitnessbuddies.utils.PreferenceManager;
import com.coveiot.coveaccess.CoveApiListener;
import com.coveiot.coveaccess.CoveSocial;
import com.coveiot.coveaccess.fitnessbuddies.model.common.Requests;
import com.coveiot.coveaccess.fitnessbuddies.model.lookup.GetBuddyLookUpResponse;
import com.coveiot.coveaccess.fitnessbuddies.model.lookup.SendFitnessBuddyLookUpRequest;
import com.coveiot.coveaccess.model.CoveApiErrorModel;
import com.coveiot.utils.model.CoveContact;
import java.util.ArrayList;
import java.util.List;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.a;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.text.m;
import kotlinx.coroutines.CoroutineScope;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@DebugMetadata(c = "com.coveiot.android.fitnessbuddies.viewmodels.AddBuddiesViewModel$loadCoveBuddies$1", f = "AddBuddiesViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes4.dex */
public final class AddBuddiesViewModel$loadCoveBuddies$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    public final /* synthetic */ List<CoveContact> $coveContactList;
    public int label;
    public final /* synthetic */ AddBuddiesViewModel this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AddBuddiesViewModel$loadCoveBuddies$1(List<CoveContact> list, AddBuddiesViewModel addBuddiesViewModel, Continuation<? super AddBuddiesViewModel$loadCoveBuddies$1> continuation) {
        super(2, continuation);
        this.$coveContactList = list;
        this.this$0 = addBuddiesViewModel;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        return new AddBuddiesViewModel$loadCoveBuddies$1(this.$coveContactList, this.this$0, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    @Nullable
    public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
        return ((AddBuddiesViewModel$loadCoveBuddies$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Type inference failed for: r0v2, types: [T, java.util.ArrayList] */
    /* JADX WARN: Type inference failed for: r1v0, types: [T, java.util.ArrayList] */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        a.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            final Ref.ObjectRef objectRef = new Ref.ObjectRef();
            objectRef.element = new ArrayList();
            final Ref.ObjectRef objectRef2 = new Ref.ObjectRef();
            objectRef2.element = new ArrayList();
            ArrayList arrayList = new ArrayList();
            for (CoveContact coveContact : this.$coveContactList) {
                arrayList.add(new SendFitnessBuddyLookUpRequest.BuddyContacts(coveContact.getPhoneNumber()));
            }
            SendFitnessBuddyLookUpRequest sendFitnessBuddyLookUpRequest = new SendFitnessBuddyLookUpRequest(arrayList);
            if (CoveUtils.INSTANCE.isNetConnected(this.this$0.getContext())) {
                final List<CoveContact> list = this.$coveContactList;
                final AddBuddiesViewModel addBuddiesViewModel = this.this$0;
                CoveSocial.getBuddiesLookUp(sendFitnessBuddyLookUpRequest, new CoveApiListener<GetBuddyLookUpResponse, CoveApiErrorModel>() { // from class: com.coveiot.android.fitnessbuddies.viewmodels.AddBuddiesViewModel$loadCoveBuddies$1.1
                    @Override // com.coveiot.coveaccess.CoveApiListener
                    public void onError(@Nullable CoveApiErrorModel coveApiErrorModel) {
                        addBuddiesViewModel.getMListener().onError(coveApiErrorModel != null ? coveApiErrorModel.getMsg() : null);
                    }

                    @Override // com.coveiot.coveaccess.CoveApiListener
                    public void onSuccess(@Nullable GetBuddyLookUpResponse getBuddyLookUpResponse) {
                        if (getBuddyLookUpResponse != null && getBuddyLookUpResponse.getData().getUsersLists() != null && getBuddyLookUpResponse.getData().getUsersLists().size() > 0) {
                            for (GetBuddyLookUpResponse.DataBean.UsersList usersList : getBuddyLookUpResponse.getData().getUsersLists()) {
                                for (CoveContact coveContact2 : list) {
                                    if (coveContact2.getPhoneNumber().equals(usersList.getMobileNumber())) {
                                        coveContact2.setDpUrl(usersList.getDpUrl());
                                        objectRef.element.add(coveContact2);
                                    }
                                }
                            }
                            objectRef2.element.addAll(list);
                            for (CoveContact coveContact3 : objectRef.element) {
                                if (!objectRef2.element.equals(coveContact3)) {
                                    objectRef2.element.remove(coveContact3);
                                }
                            }
                        } else {
                            objectRef2.element.addAll(list);
                        }
                        List<Requests> fitnessBuddies = PreferenceManager.Companion.getFitnessBuddies(addBuddiesViewModel.getContext());
                        if (fitnessBuddies != null) {
                            for (Requests requests : fitnessBuddies) {
                                List<CoveContact> list2 = objectRef.element;
                                Intrinsics.checkNotNull(list2);
                                for (CoveContact coveContact4 : list2) {
                                    if (m.equals(coveContact4.getPhoneNumber(), requests.mobileNumber, true) && !coveContact4.isFitCrew()) {
                                        coveContact4.setFitCrew(true);
                                    }
                                }
                            }
                        }
                        addBuddiesViewModel.getGetActiveContacts().postValue(objectRef.element);
                        addBuddiesViewModel.getGetInActiveContacts().postValue(objectRef2.element);
                    }
                });
            } else {
                this.this$0.getMListener().onError(this.this$0.getContext().getString(R.string.no_internet_connection));
            }
            return Unit.INSTANCE;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
