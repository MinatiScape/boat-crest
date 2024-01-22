package com.coveiot.android.remotecommandframework.alexa.request;

import android.content.Context;
import com.coveiot.android.bleabstract.api.BleApi;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.bleabstract.error.Error;
import com.coveiot.android.bleabstract.listeners.ConnectionResultListener;
import com.coveiot.android.bleabstract.models.ConnectionStatus;
import com.coveiot.android.remotecommandframework.SingletonHolder;
import com.coveiot.android.remotecommandframework.alexa.ble.AlarmHandler;
import com.coveiot.android.remotecommandframework.alexa.ble.AutoHrHandler;
import com.coveiot.android.remotecommandframework.alexa.ble.BatteryHandler;
import com.coveiot.android.remotecommandframework.alexa.ble.CommandHandler;
import com.coveiot.android.remotecommandframework.alexa.ble.CommandHandlerFactory;
import com.coveiot.android.remotecommandframework.alexa.ble.DNDHandler;
import com.coveiot.android.remotecommandframework.alexa.ble.FitnessConfigHandler;
import com.coveiot.android.remotecommandframework.alexa.ble.LiftWristToViewHandler;
import com.coveiot.android.remotecommandframework.alexa.ble.MeasurementUnitHandler;
import com.coveiot.android.remotecommandframework.alexa.ble.NotificationHandler;
import com.coveiot.android.remotecommandframework.alexa.ble.SedentaryHandler;
import com.coveiot.android.remotecommandframework.alexa.ble.SleepTargetHandler;
import com.coveiot.android.remotecommandframework.alexa.ble.StepsTargetHandler;
import com.coveiot.android.remotecommandframework.alexa.ble.SyncHandler;
import com.coveiot.android.remotecommandframework.alexa.ble.TimeSettingHandler;
import com.coveiot.android.remotecommandframework.alexa.handler.IPreferenceHandlerProvider;
import com.coveiot.android.remotecommandframework.alexa.model.CommandNames;
import com.coveiot.android.remotecommandframework.alexa.request.model.Command;
import com.coveiot.android.remotecommandframework.alexa.response.listener.CommandResponseListener;
import com.coveiot.android.remotecommandframework.alexa.utils.ErrorType;
import com.coveiot.android.remotecommandframework.alexa.utils.ResponseType;
import com.coveiot.android.remotecommandframeworksdk.listener.SuccessListener;
import com.coveiot.utils.utility.LogHelper;
import java.util.Collection;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import kotlin.Unit;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.comparisons.f;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.TypeIntrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes6.dex */
public final class CommandManager implements CommandResponseListener {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f5632a;
    @NotNull
    public final LinkedList<Command> b;
    @Nullable
    public SuccessListener c;
    @Nullable
    public Command d;
    @Nullable
    public IPreferenceHandlerProvider e;
    @NotNull
    public final String f;

    /* loaded from: classes6.dex */
    public static final class Companion extends SingletonHolder<CommandManager, Context> {

        /* loaded from: classes6.dex */
        public /* synthetic */ class a extends FunctionReferenceImpl implements Function1<Context, CommandManager> {
            public static final a INSTANCE = new a();

            public a() {
                super(1, CommandManager.class, "<init>", "<init>(Landroid/content/Context;)V", 0);
            }

            @Override // kotlin.jvm.functions.Function1
            @NotNull
            public final CommandManager invoke(@NotNull Context p0) {
                Intrinsics.checkNotNullParameter(p0, "p0");
                return new CommandManager(p0, null);
            }
        }

        public Companion() {
            super(a.INSTANCE);
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public CommandManager(Context context) {
        BleApi bleApi;
        this.f5632a = context;
        this.b = new LinkedList<>();
        this.f = "CommandHandler";
        BleApiManager bleApiManager = BleApiManager.getInstance(context);
        if (bleApiManager == null || (bleApi = bleApiManager.getBleApi()) == null) {
            return;
        }
        bleApi.setConnectionChangeListener(new ConnectionResultListener() { // from class: com.coveiot.android.remotecommandframework.alexa.request.CommandManager.1
            @Override // com.coveiot.android.bleabstract.listeners.ConnectionResultListener
            public void onConnectionResponse(@NotNull ConnectionStatus status) {
                Intrinsics.checkNotNullParameter(status, "status");
                if (status == ConnectionStatus.DISCONNECTED) {
                    CommandManager.this.getCommandQueue().clear();
                }
            }

            @Override // com.coveiot.android.bleabstract.listeners.ConnectionResultListener
            public void onError(@NotNull Error error) {
                Intrinsics.checkNotNullParameter(error, "error");
                CommandManager.this.getCommandQueue().clear();
            }
        });
    }

    public /* synthetic */ CommandManager(Context context, DefaultConstructorMarker defaultConstructorMarker) {
        this(context);
    }

    public final void a() {
        Unit unit = null;
        if (!this.b.isEmpty()) {
            Command first = this.b.getFirst();
            this.d = first;
            if (first != null) {
                Intrinsics.checkNotNull(first);
                if (first.getName() != null) {
                    Command command = this.d;
                    Intrinsics.checkNotNull(command);
                    String name = command.getName();
                    Intrinsics.checkNotNull(name);
                    if (name.length() > 0) {
                        IPreferenceHandlerProvider iPreferenceHandlerProvider = this.e;
                        if (iPreferenceHandlerProvider != null) {
                            CommandHandlerFactory commandHandlerFactory = CommandHandlerFactory.INSTANCE;
                            Context context = this.f5632a;
                            Command command2 = this.d;
                            Intrinsics.checkNotNull(command2);
                            CommandHandler commandHandler = commandHandlerFactory.getCommandHandler(context, command2, this, iPreferenceHandlerProvider);
                            if (commandHandler != null) {
                                Command command3 = this.d;
                                Intrinsics.checkNotNull(command3);
                                String name2 = command3.getName();
                                if (Intrinsics.areEqual(name2, CommandNames.SET_ALARM.getValue())) {
                                    ((AlarmHandler) commandHandler).setAlarm();
                                } else if (Intrinsics.areEqual(name2, CommandNames.GET_BATTERY.getValue())) {
                                    ((BatteryHandler) commandHandler).getBattery();
                                } else if (Intrinsics.areEqual(name2, CommandNames.GET_USER_DAY_SUMMARY.getValue())) {
                                    ((SyncHandler) commandHandler).syncData();
                                } else if (Intrinsics.areEqual(name2, CommandNames.SET_DND.getValue())) {
                                    ((DNDHandler) commandHandler).setDND();
                                } else if (Intrinsics.areEqual(name2, CommandNames.SET_LIFT_WRIST_TO_VIEW.getValue())) {
                                    ((LiftWristToViewHandler) commandHandler).setLiftWristToView();
                                } else if (Intrinsics.areEqual(name2, CommandNames.SET_TIME_FORMAT.getValue())) {
                                    ((TimeSettingHandler) commandHandler).setTimeFormat();
                                } else if (Intrinsics.areEqual(name2, CommandNames.SET_SEDENTARY_REMINDER.getValue())) {
                                    ((SedentaryHandler) commandHandler).setSedentaryReminder();
                                } else if (Intrinsics.areEqual(name2, CommandNames.SET_AUTO_HR.getValue())) {
                                    ((AutoHrHandler) commandHandler).setAutoHr();
                                } else if (Intrinsics.areEqual(name2, CommandNames.SET_STEP_TARGET.getValue())) {
                                    ((StepsTargetHandler) commandHandler).setStepsTarget();
                                } else if (Intrinsics.areEqual(name2, CommandNames.SET_SLEEP_TARGET.getValue())) {
                                    ((SleepTargetHandler) commandHandler).setSleepTarget();
                                } else if (Intrinsics.areEqual(name2, CommandNames.SET_FITNESS_CONFIG_INFO.getValue())) {
                                    ((FitnessConfigHandler) commandHandler).setFitnessConfig();
                                } else if (Intrinsics.areEqual(name2, CommandNames.SET_MEASUREMENT_UNIT.getValue())) {
                                    ((MeasurementUnitHandler) commandHandler).setMeasurementUnit();
                                } else if (Intrinsics.areEqual(name2, CommandNames.SET_NOTIFICATION.getValue())) {
                                    ((NotificationHandler) commandHandler).setNotification();
                                } else {
                                    TypeIntrinsics.asMutableCollection(this.b).remove(this.d);
                                    Command command4 = this.d;
                                    Intrinsics.checkNotNull(command4);
                                    command4.setStatus(ResponseType.ERROR.getStatus());
                                    Command command5 = this.d;
                                    Intrinsics.checkNotNull(command5);
                                    command5.setMessage(ErrorType.COMMAND_NOT_SUPPORTED.getType());
                                    a();
                                }
                            } else {
                                TypeIntrinsics.asMutableCollection(this.b).remove(this.d);
                                Command command6 = this.d;
                                Intrinsics.checkNotNull(command6);
                                command6.setStatus(ResponseType.ERROR.getStatus());
                                Command command7 = this.d;
                                Intrinsics.checkNotNull(command7);
                                command7.setMessage(ErrorType.COMMAND_NOT_SUPPORTED.getType());
                                a();
                            }
                            unit = Unit.INSTANCE;
                        }
                        if (unit == null) {
                            TypeIntrinsics.asMutableCollection(this.b).remove(this.d);
                            Command command8 = this.d;
                            Intrinsics.checkNotNull(command8);
                            command8.setStatus(ResponseType.ERROR.getStatus());
                            Command command9 = this.d;
                            Intrinsics.checkNotNull(command9);
                            command9.setMessage(ErrorType.PREFERENCE_HANDLER_PROVIDER_NOT_FOUND.getType());
                            a();
                            return;
                        }
                        return;
                    }
                }
            }
            TypeIntrinsics.asMutableCollection(this.b).remove(this.d);
            Command command10 = this.d;
            Intrinsics.checkNotNull(command10);
            command10.setStatus(ResponseType.ERROR.getStatus());
            Command command11 = this.d;
            Intrinsics.checkNotNull(command11);
            command11.setMessage(ErrorType.COMMAND_NOT_SUPPORTED.getType());
            a();
            return;
        }
        SuccessListener successListener = this.c;
        if (successListener != null) {
            successListener.onSuccess();
        }
        this.c = null;
    }

    @NotNull
    public final LinkedList<Command> getCommandQueue() {
        return this.b;
    }

    @NotNull
    public final Context getContext() {
        return this.f5632a;
    }

    @Nullable
    public final Command getCurrentCommand() {
        return this.d;
    }

    @Nullable
    public final IPreferenceHandlerProvider getPreferenceHandlerProvider() {
        return this.e;
    }

    @Nullable
    public final SuccessListener getRequestCompletionListener() {
        return this.c;
    }

    @Override // com.coveiot.android.remotecommandframework.alexa.response.listener.CommandResponseListener
    public void onResponse(@NotNull Command command) {
        Intrinsics.checkNotNullParameter(command, "command");
        this.b.remove(command);
        a();
    }

    public final void onSyncCompleted() {
        LogHelper.d(this.f, "onSyncCompleted called.");
        a();
    }

    public final void setCurrentCommand(@Nullable Command command) {
        this.d = command;
    }

    public final void setPreferenceHandlerProvider(@Nullable IPreferenceHandlerProvider iPreferenceHandlerProvider) {
        this.e = iPreferenceHandlerProvider;
    }

    public final void setProviderPreference(@Nullable IPreferenceHandlerProvider iPreferenceHandlerProvider) {
        if (this.e == null) {
            this.e = iPreferenceHandlerProvider;
        }
    }

    public final void setRequestCompletionListener(@Nullable SuccessListener successListener) {
        this.c = successListener;
    }

    public final void startCommandProcessing(@NotNull List<? extends Command> commands, @NotNull SuccessListener listener) {
        Intrinsics.checkNotNullParameter(commands, "commands");
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.c = listener;
        this.b.clear();
        this.b.addAll(CollectionsKt___CollectionsKt.toMutableList((Collection) CollectionsKt___CollectionsKt.sortedWith(commands, new Comparator() { // from class: com.coveiot.android.remotecommandframework.alexa.request.CommandManager$startCommandProcessing$$inlined$sortedBy$1
            @Override // java.util.Comparator
            public final int compare(T t, T t2) {
                return f.compareValues(Integer.valueOf(((Command) t).getPriority()), Integer.valueOf(((Command) t2).getPriority()));
            }
        })));
        a();
    }
}
