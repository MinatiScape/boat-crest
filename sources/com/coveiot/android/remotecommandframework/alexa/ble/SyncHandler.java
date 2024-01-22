package com.coveiot.android.remotecommandframework.alexa.ble;

import android.content.Context;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.remotecommandframework.alexa.handler.ISyncPreferenceHandler;
import com.coveiot.android.remotecommandframework.alexa.model.SCommandInfo;
import com.coveiot.android.remotecommandframework.alexa.request.model.Command;
import com.coveiot.android.remotecommandframework.alexa.response.LastDataHelper;
import com.coveiot.android.remotecommandframework.alexa.response.listener.CommandResponseListener;
import com.coveiot.android.remotecommandframework.alexa.response.model.CommandSuccess;
import com.coveiot.android.remotecommandframework.alexa.utils.ResponseType;
import com.coveiot.android.remotecommandframework.alexa.utils.Util;
import com.coveiot.repository.Error;
import com.coveiot.repository.datasync.ProgressDataBean;
import com.coveiot.repository.datasync.domainlogic.SyncCompleteListner;
import com.coveiot.repository.datasync.domainlogic.SyncManager;
import com.coveiot.utils.utility.LogHelper;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.m;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;
import kotlinx.coroutines.e;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes6.dex */
public final class SyncHandler extends CommandHandler {
    @NotNull
    public final Context c;
    @NotNull
    public final ISyncPreferenceHandler d;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SyncHandler(@NotNull Context context, @NotNull Command command, @NotNull CommandResponseListener commandResponseListener, @NotNull ISyncPreferenceHandler preferenceHandler) {
        super(command, commandResponseListener);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(command, "command");
        Intrinsics.checkNotNullParameter(commandResponseListener, "commandResponseListener");
        Intrinsics.checkNotNullParameter(preferenceHandler, "preferenceHandler");
        this.c = context;
        this.d = preferenceHandler;
    }

    @NotNull
    public final Context getContext() {
        return this.c;
    }

    @NotNull
    public final ISyncPreferenceHandler getPreferenceHandler() {
        return this.d;
    }

    @Override // com.coveiot.android.remotecommandframework.alexa.ble.CommandHandler
    @Nullable
    public SCommandInfo getSCommandInfo(@NotNull Command command) {
        Intrinsics.checkNotNullParameter(command, "command");
        return null;
    }

    @Override // com.coveiot.android.remotecommandframework.alexa.ble.CommandHandler
    public void syncData() {
        Util.Companion companion = Util.Companion;
        if (companion.isDeviceConnected(this.c)) {
            if (!companion.isSyncInProgress()) {
                SyncManager.getInstance().syncData(new SyncCompleteListner() { // from class: com.coveiot.android.remotecommandframework.alexa.ble.SyncHandler$syncData$1

                    @DebugMetadata(c = "com.coveiot.android.remotecommandframework.alexa.ble.SyncHandler$syncData$1$onDataSyncComplete$1", f = "SyncHandler.kt", i = {0}, l = {37}, m = "invokeSuspend", n = {"commandSuccess"}, s = {"L$0"})
                    /* loaded from: classes6.dex */
                    public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                        public Object L$0;
                        public Object L$1;
                        public int label;
                        public final /* synthetic */ SyncHandler this$0;

                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        public a(SyncHandler syncHandler, Continuation<? super a> continuation) {
                            super(2, continuation);
                            this.this$0 = syncHandler;
                        }

                        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                        @NotNull
                        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                            return new a(this.this$0, continuation);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        @Nullable
                        public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
                            return ((a) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                        }

                        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                        @Nullable
                        public final Object invokeSuspend(@NotNull Object obj) {
                            CommandSuccess commandSuccess;
                            CommandSuccess commandSuccess2;
                            Object coroutine_suspended = kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
                            int i = this.label;
                            if (i == 0) {
                                ResultKt.throwOnFailure(obj);
                                CommandSuccess commandSuccess3 = new CommandSuccess();
                                this.L$0 = commandSuccess3;
                                this.L$1 = commandSuccess3;
                                this.label = 1;
                                Object lastMeasuredData = LastDataHelper.Companion.getInstance(this.this$0.getContext()).getLastMeasuredData(this);
                                if (lastMeasuredData == coroutine_suspended) {
                                    return coroutine_suspended;
                                }
                                commandSuccess = commandSuccess3;
                                obj = lastMeasuredData;
                                commandSuccess2 = commandSuccess;
                            } else if (i != 1) {
                                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                            } else {
                                commandSuccess = (CommandSuccess) this.L$1;
                                commandSuccess2 = (CommandSuccess) this.L$0;
                                ResultKt.throwOnFailure(obj);
                            }
                            commandSuccess.setData(obj);
                            this.this$0.getCommand().setRemoteBaseResponse(commandSuccess2);
                            this.this$0.getCommand().setStatus(ResponseType.SUCCESS.getStatus());
                            this.this$0.getCommand().setData(null);
                            this.this$0.getCommandResponseListener().onResponse(this.this$0.getCommand());
                            return Unit.INSTANCE;
                        }
                    }

                    @Override // com.coveiot.repository.datasync.domainlogic.SyncCompleteListner
                    public void onDataSyncComplete() {
                        if (Intrinsics.areEqual(SyncHandler.this.getCommand().getName(), "GET_USER_DAY_SUMMARY")) {
                            e.e(GlobalScope.INSTANCE, Dispatchers.getIO(), null, new a(SyncHandler.this, null), 2, null);
                            return;
                        }
                        SyncHandler.this.getCommand().setStatus(ResponseType.SUCCESS.getStatus());
                        SyncHandler.this.getCommand().setData(null);
                        SyncHandler.this.getCommandResponseListener().onResponse(SyncHandler.this.getCommand());
                    }

                    @Override // com.coveiot.repository.datasync.domainlogic.SyncCompleteListner
                    public void onFailure(@Nullable String str, @Nullable Error error) {
                        LogHelper.e("SyncHandler", str != null ? str : null);
                        if (!m.equals(str, "PPG operation in progress", true)) {
                            BleApiManager.getInstance(SyncHandler.this.getContext()).getBleApi().restartService();
                        }
                        SyncHandler.this.getCommand().setStatus(ResponseType.ERROR.getStatus());
                        SyncHandler.this.getCommand().setMessage(str);
                        SyncHandler.this.getCommand().setData(null);
                        SyncHandler.this.getCommandResponseListener().onResponse(SyncHandler.this.getCommand());
                    }

                    @Override // com.coveiot.repository.datasync.domainlogic.SyncCompleteListner
                    public void onProgressUpdate(@Nullable ProgressDataBean progressDataBean) {
                    }
                }, true);
                return;
            } else {
                sendSyncIsInProgress();
                return;
            }
        }
        sendDeviceNotConnected();
    }
}
