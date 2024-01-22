package com.coveiot.android.leonardo.more.adapters;

import android.app.TimePickerDialog;
import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import androidx.recyclerview.widget.RecyclerView;
import com.clevertap.android.sdk.Constants;
import com.coveiot.android.boat.R;
import com.coveiot.android.boat.databinding.AlarmListRowNewBinding;
import com.coveiot.android.devicemodels.DeviceUtils;
import com.coveiot.android.leonardo.model.AlarmDataHolder;
import com.coveiot.android.leonardo.more.adapters.AlarmListAdapterNew;
import com.coveiot.android.leonardo.more.listeners.AlarmListener;
import com.coveiot.sdk.ble.api.model.AlarmInfo;
import com.coveiot.utils.utility.AppUtils;
import com.coveiot.utils.utility.LogHelper;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.text.StringsKt__StringsKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes5.dex */
public final class AlarmListAdapterNew extends RecyclerView.Adapter<ViewHolder> {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f5040a;
    @NotNull
    public List<AlarmDataHolder> b;
    @NotNull
    public final AlarmListener c;
    @Nullable
    public String[] d;
    @NotNull
    public final HashMap<Integer, CustomizedTextWatcher> e;

    /* loaded from: classes5.dex */
    public interface AlarmEventHandler {
        void onExpandCollapse(@NotNull View view);

        void onRepeatDaysSelected(@NotNull View view);
    }

    /* loaded from: classes5.dex */
    public final class CustomizedTextWatcher implements TextWatcher {
        @NotNull
        public final AlarmInfo h;
        public final int i;
        public final /* synthetic */ AlarmListAdapterNew j;

        public CustomizedTextWatcher(@NotNull AlarmListAdapterNew alarmListAdapterNew, AlarmInfo alarmInfo, int i) {
            Intrinsics.checkNotNullParameter(alarmInfo, "alarmInfo");
            this.j = alarmListAdapterNew;
            this.h = alarmInfo;
            this.i = i;
        }

        @Override // android.text.TextWatcher
        public void afterTextChanged(@Nullable Editable editable) {
        }

        @Override // android.text.TextWatcher
        public void beforeTextChanged(@Nullable CharSequence charSequence, int i, int i2, int i3) {
        }

        @NotNull
        public final AlarmInfo getAlarmInfo() {
            return this.h;
        }

        public final int getPosition() {
            return this.i;
        }

        @Override // android.text.TextWatcher
        public void onTextChanged(@Nullable CharSequence charSequence, int i, int i2, int i3) {
            this.h.setEventName(String.valueOf(charSequence));
            String simpleName = Reflection.getOrCreateKotlinClass(AlarmListAdapterNew.class).getSimpleName();
            StringBuilder sb = new StringBuilder();
            sb.append(this.i);
            sb.append(' ');
            sb.append((Object) charSequence);
            LogHelper.d(simpleName, sb.toString());
            this.j.b(this.i, this.h);
        }
    }

    /* loaded from: classes5.dex */
    public final class ViewHolder extends RecyclerView.ViewHolder {
        @NotNull

        /* renamed from: a  reason: collision with root package name */
        public final AlarmListRowNewBinding f5041a;
        public final /* synthetic */ AlarmListAdapterNew b;

        @DebugMetadata(c = "com.coveiot.android.leonardo.more.adapters.AlarmListAdapterNew$ViewHolder$bind$4", f = "AlarmListAdapterNew.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        /* loaded from: classes5.dex */
        public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            public final /* synthetic */ AlarmDataHolder $alarmDataHolder;
            public final /* synthetic */ AlarmInfo $alarmInfo;
            public int label;
            public final /* synthetic */ ViewHolder this$0;
            public final /* synthetic */ AlarmListAdapterNew this$1;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public a(AlarmDataHolder alarmDataHolder, ViewHolder viewHolder, AlarmListAdapterNew alarmListAdapterNew, AlarmInfo alarmInfo, Continuation<? super a> continuation) {
                super(2, continuation);
                this.$alarmDataHolder = alarmDataHolder;
                this.this$0 = viewHolder;
                this.this$1 = alarmListAdapterNew;
                this.$alarmInfo = alarmInfo;
            }

            /* JADX INFO: Access modifiers changed from: private */
            public static final void invokeSuspend$lambda$0(ViewHolder viewHolder, AlarmListAdapterNew alarmListAdapterNew, AlarmInfo alarmInfo, View view) {
                Boolean shouldEnableClick = viewHolder.getBinding().getShouldEnableClick();
                Intrinsics.checkNotNull(shouldEnableClick, "null cannot be cast to non-null type kotlin.Boolean");
                if (shouldEnableClick.booleanValue()) {
                    alarmListAdapterNew.c.onAlarmUpdated(alarmInfo);
                    return;
                }
                viewHolder.getBinding().alarmTime.performClick();
                viewHolder.getBinding().setShouldEnableClick(Boolean.TRUE);
                viewHolder.getBinding().saveAlarmBtn.setText(alarmListAdapterNew.f5040a.getString(R.string.save_alarm));
                viewHolder.getBinding().saveAlarmBtn.setBackground(alarmListAdapterNew.f5040a.getDrawable(R.drawable.button_selector));
            }

            /* JADX INFO: Access modifiers changed from: private */
            public static final void invokeSuspend$lambda$1(AlarmInfo alarmInfo, ViewHolder viewHolder, AlarmListAdapterNew alarmListAdapterNew, AlarmDataHolder alarmDataHolder, CompoundButton compoundButton, boolean z) {
                if (alarmInfo.isAlarmOn() != z) {
                    alarmInfo.setAlarmOn(z);
                    viewHolder.getBinding().setAlarmInfo(alarmInfo);
                    viewHolder.getBinding().setShouldEnableClick(Boolean.valueOf(z));
                    alarmListAdapterNew.c.visibleTextview();
                    if (alarmDataHolder.isAlreadySaved()) {
                        alarmListAdapterNew.c.onAlarmUpdated(alarmInfo);
                    }
                }
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @NotNull
            public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                return new a(this.$alarmDataHolder, this.this$0, this.this$1, this.$alarmInfo, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            @Nullable
            public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
                return ((a) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @Nullable
            public final Object invokeSuspend(@NotNull Object obj) {
                kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
                if (this.label == 0) {
                    ResultKt.throwOnFailure(obj);
                    if (!this.$alarmDataHolder.isAlreadySaved()) {
                        this.this$0.getBinding().saveAlarmBtn.setText(this.this$1.f5040a.getString(R.string.save_alarm));
                        this.this$0.getBinding().setShouldEnableClick(Boxing.boxBoolean(true));
                        this.this$0.getBinding().saveAlarmBtn.setBackground(this.this$1.f5040a.getDrawable(R.drawable.button_selector));
                    } else {
                        this.this$0.getBinding().setShouldEnableClick(Boxing.boxBoolean(false));
                        this.this$0.getBinding().saveAlarmBtn.setText(this.this$1.f5040a.getString(R.string.edit_alarm));
                        this.this$0.getBinding().saveAlarmBtn.setBackground(this.this$1.f5040a.getDrawable(R.drawable.enable_button_background_secondary));
                        Button button = this.this$0.getBinding().saveAlarmBtn;
                        final ViewHolder viewHolder = this.this$0;
                        final AlarmListAdapterNew alarmListAdapterNew = this.this$1;
                        final AlarmInfo alarmInfo = this.$alarmInfo;
                        button.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.adapters.f
                            @Override // android.view.View.OnClickListener
                            public final void onClick(View view) {
                                AlarmListAdapterNew.ViewHolder.a.invokeSuspend$lambda$0(AlarmListAdapterNew.ViewHolder.this, alarmListAdapterNew, alarmInfo, view);
                            }
                        });
                    }
                    Switch r6 = this.this$0.getBinding().alarmSwitch;
                    final AlarmInfo alarmInfo2 = this.$alarmInfo;
                    final ViewHolder viewHolder2 = this.this$0;
                    final AlarmListAdapterNew alarmListAdapterNew2 = this.this$1;
                    final AlarmDataHolder alarmDataHolder = this.$alarmDataHolder;
                    r6.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.coveiot.android.leonardo.more.adapters.g
                        @Override // android.widget.CompoundButton.OnCheckedChangeListener
                        public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                            AlarmListAdapterNew.ViewHolder.a.invokeSuspend$lambda$1(AlarmInfo.this, viewHolder2, alarmListAdapterNew2, alarmDataHolder, compoundButton, z);
                        }
                    });
                    return Unit.INSTANCE;
                }
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public ViewHolder(@NotNull AlarmListAdapterNew alarmListAdapterNew, AlarmListRowNewBinding binding) {
            super(binding.getRoot());
            Intrinsics.checkNotNullParameter(binding, "binding");
            this.b = alarmListAdapterNew;
            this.f5041a = binding;
        }

        public static final void d(final AlarmListAdapterNew this$0, final AlarmInfo alarmInfo, final int i, final ViewHolder this$1, View view) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            Intrinsics.checkNotNullParameter(alarmInfo, "$alarmInfo");
            Intrinsics.checkNotNullParameter(this$1, "this$1");
            new TimePickerDialog(this$0.f5040a, R.style.DialogThemeDarWindowBG, new TimePickerDialog.OnTimeSetListener() { // from class: com.coveiot.android.leonardo.more.adapters.c
                @Override // android.app.TimePickerDialog.OnTimeSetListener
                public final void onTimeSet(TimePicker timePicker, int i2, int i3) {
                    AlarmListAdapterNew.ViewHolder.e(AlarmInfo.this, this$0, i, this$1, timePicker, i2, i3);
                }
            }, alarmInfo.getHour(), alarmInfo.getMinute(), DateFormat.is24HourFormat(this$0.f5040a)).show();
        }

        public static final void e(AlarmInfo alarmInfo, AlarmListAdapterNew this$0, int i, ViewHolder this$1, TimePicker timePicker, int i2, int i3) {
            Intrinsics.checkNotNullParameter(alarmInfo, "$alarmInfo");
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            Intrinsics.checkNotNullParameter(this$1, "this$1");
            alarmInfo.setHour(i2);
            alarmInfo.setMinute(i3);
            this$0.b(i, alarmInfo);
            this$1.g(alarmInfo);
        }

        public static final void f(AlarmInfo alarmInfo, ViewHolder this$0, AlarmListAdapterNew this$1, int i, View view) {
            EditText editText;
            EditText editText2;
            Editable text;
            Intrinsics.checkNotNullParameter(alarmInfo, "$alarmInfo");
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            Intrinsics.checkNotNullParameter(this$1, "this$1");
            AlarmListRowNewBinding alarmListRowNewBinding = this$0.f5041a;
            alarmInfo.setEventName((alarmListRowNewBinding == null || (editText2 = alarmListRowNewBinding.alarmName) == null || (text = editText2.getText()) == null) ? null : text.toString());
            DeviceUtils.Companion companion = DeviceUtils.Companion;
            Context context = this$1.f5040a;
            Intrinsics.checkNotNull(context);
            if (companion.isCZDevice(context) || companion.isCADevice(this$1.f5040a) || companion.isCYDevice(this$1.f5040a) || companion.isPS1Device(this$1.f5040a) || companion.isTouchELXDevice(this$1.f5040a) || companion.isEastApexDevice(this$1.f5040a) || companion.isBESDevice(this$1.f5040a)) {
                this$1.c.onAlarmAdded(alarmInfo);
                this$0.f5041a.setShouldExpandAlarm(Boolean.FALSE);
                this$1.updateShouldExpandAlarm(true, i);
            } else if (!AppUtils.isEmpty(alarmInfo.getEventName())) {
                this$1.c.onAlarmAdded(alarmInfo);
                this$0.f5041a.setShouldExpandAlarm(Boolean.FALSE);
                this$1.updateShouldExpandAlarm(true, i);
            } else {
                AlarmListRowNewBinding alarmListRowNewBinding2 = this$0.f5041a;
                if (alarmListRowNewBinding2 == null || (editText = alarmListRowNewBinding2.alarmName) == null) {
                    return;
                }
                editText.setError("Please enter alarm label");
            }
        }

        public final void bind(@NotNull AlarmDataHolder alarmDataHolder, final int i) {
            EditText editText;
            AlarmListRowNewBinding alarmListRowNewBinding;
            EditText editText2;
            Intrinsics.checkNotNullParameter(alarmDataHolder, "alarmDataHolder");
            final AlarmInfo alarmInfo = alarmDataHolder.getAlarmInfo();
            this.f5041a.setAlarmInfo(alarmInfo);
            this.f5041a.alarmName.setText(alarmInfo.getEventName());
            this.f5041a.alarmSwitch.setOnCheckedChangeListener(null);
            this.f5041a.setShouldExpandAlarm(Boolean.valueOf(!alarmDataHolder.isAlreadySaved()));
            if (!alarmDataHolder.isAlreadySaved()) {
                this.f5041a.setShouldEnableClick(Boolean.valueOf(alarmInfo.isAlarmOn()));
            }
            DeviceUtils.Companion companion = DeviceUtils.Companion;
            Context context = this.b.f5040a;
            Intrinsics.checkNotNull(context);
            if (!companion.isCZDevice(context) && !companion.isCADevice(this.b.f5040a) && !companion.isCYDevice(this.b.f5040a) && !companion.isPS1Device(this.b.f5040a) && !companion.isTouchELXDevice(this.b.f5040a) && !companion.isEastApexDevice(this.b.f5040a) && !companion.isBESDevice(this.b.f5040a)) {
                this.f5041a.alarmName.setVisibility(0);
            } else {
                this.f5041a.alarmName.setVisibility(8);
            }
            g(alarmInfo);
            this.b.a(this, alarmInfo);
            AlarmListRowNewBinding alarmListRowNewBinding2 = this.f5041a;
            final AlarmListAdapterNew alarmListAdapterNew = this.b;
            alarmListRowNewBinding2.setListener(new AlarmEventHandler() { // from class: com.coveiot.android.leonardo.more.adapters.AlarmListAdapterNew$ViewHolder$bind$1
                @Override // com.coveiot.android.leonardo.more.adapters.AlarmListAdapterNew.AlarmEventHandler
                public void onExpandCollapse(@NotNull View view) {
                    Intrinsics.checkNotNullParameter(view, "view");
                    Boolean shouldExpandAlarm = this.getBinding().getShouldExpandAlarm();
                    Intrinsics.checkNotNull(shouldExpandAlarm, "null cannot be cast to non-null type kotlin.Boolean");
                    if (shouldExpandAlarm.booleanValue()) {
                        this.getBinding().setShouldExpandAlarm(Boolean.FALSE);
                    } else {
                        this.getBinding().setShouldExpandAlarm(Boolean.TRUE);
                    }
                }

                @Override // com.coveiot.android.leonardo.more.adapters.AlarmListAdapterNew.AlarmEventHandler
                public void onRepeatDaysSelected(@NotNull View view) {
                    Intrinsics.checkNotNullParameter(view, "view");
                    switch (view.getId()) {
                        case R.id.friday /* 2131363530 */:
                            AlarmInfo.this.getDaysSelected().getIsDaySelected()[5] = true ^ AlarmInfo.this.getDaysSelected().getIsDaySelected()[5];
                            break;
                        case R.id.monday /* 2131364631 */:
                            AlarmInfo.this.getDaysSelected().getIsDaySelected()[1] = !AlarmInfo.this.getDaysSelected().getIsDaySelected()[1];
                            break;
                        case R.id.saturday /* 2131365329 */:
                            AlarmInfo.this.getDaysSelected().getIsDaySelected()[6] = true ^ AlarmInfo.this.getDaysSelected().getIsDaySelected()[6];
                            break;
                        case R.id.sunday /* 2131365692 */:
                            AlarmInfo.this.getDaysSelected().getIsDaySelected()[0] = true ^ AlarmInfo.this.getDaysSelected().getIsDaySelected()[0];
                            break;
                        case R.id.thursday /* 2131365917 */:
                            AlarmInfo.this.getDaysSelected().getIsDaySelected()[4] = true ^ AlarmInfo.this.getDaysSelected().getIsDaySelected()[4];
                            break;
                        case R.id.tuesday /* 2131366038 */:
                            AlarmInfo.this.getDaysSelected().getIsDaySelected()[2] = true ^ AlarmInfo.this.getDaysSelected().getIsDaySelected()[2];
                            break;
                        case R.id.wednesday /* 2131367457 */:
                            AlarmInfo.this.getDaysSelected().getIsDaySelected()[3] = true ^ AlarmInfo.this.getDaysSelected().getIsDaySelected()[3];
                            break;
                    }
                    this.getBinding().setAlarmInfo(AlarmInfo.this);
                    alarmListAdapterNew.b(i, AlarmInfo.this);
                    alarmListAdapterNew.a(this, AlarmInfo.this);
                }
            });
            TextView textView = this.f5041a.alarmTime;
            final AlarmListAdapterNew alarmListAdapterNew2 = this.b;
            textView.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.adapters.d
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    AlarmListAdapterNew.ViewHolder.d(AlarmListAdapterNew.this, alarmInfo, i, this, view);
                }
            });
            Button button = this.f5041a.saveAlarmBtn;
            final AlarmListAdapterNew alarmListAdapterNew3 = this.b;
            button.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.adapters.e
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    AlarmListAdapterNew.ViewHolder.f(AlarmInfo.this, this, alarmListAdapterNew3, i, view);
                }
            });
            CustomizedTextWatcher customizedTextWatcher = new CustomizedTextWatcher(this.b, alarmInfo, i);
            if (this.b.getTextWatcherMap().containsKey(Integer.valueOf(i)) && this.b.getTextWatcherMap().get(Integer.valueOf(i)) != null && (alarmListRowNewBinding = this.f5041a) != null && (editText2 = alarmListRowNewBinding.alarmName) != null) {
                editText2.removeTextChangedListener(this.b.getTextWatcherMap().get(Integer.valueOf(i)));
            }
            this.b.getTextWatcherMap().put(Integer.valueOf(i), customizedTextWatcher);
            AlarmListRowNewBinding alarmListRowNewBinding3 = this.f5041a;
            if (alarmListRowNewBinding3 != null && (editText = alarmListRowNewBinding3.alarmName) != null) {
                editText.addTextChangedListener(customizedTextWatcher);
            }
            kotlinx.coroutines.e.e(GlobalScope.INSTANCE, Dispatchers.getMain(), null, new a(alarmDataHolder, this, this.b, alarmInfo, null), 2, null);
        }

        public final void g(AlarmInfo alarmInfo) {
            String string;
            String sb;
            Intrinsics.checkNotNullExpressionValue(this.b.f5040a.getResources().getString(R.string.AM), "mContext.resources.getString(R.string.AM)");
            if (alarmInfo.getHour() > 12) {
                string = this.b.f5040a.getResources().getString(R.string.PM);
                Intrinsics.checkNotNullExpressionValue(string, "mContext.resources.getString(R.string.PM)");
                StringBuilder sb2 = new StringBuilder();
                StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
                Locale locale = Locale.ENGLISH;
                String format = String.format(locale, "%02d", Arrays.copyOf(new Object[]{Integer.valueOf(alarmInfo.getHour() - 12)}, 1));
                Intrinsics.checkNotNullExpressionValue(format, "format(locale, format, *args)");
                sb2.append(format);
                sb2.append(':');
                String format2 = String.format(locale, "%02d", Arrays.copyOf(new Object[]{Integer.valueOf(alarmInfo.getMinute())}, 1));
                Intrinsics.checkNotNullExpressionValue(format2, "format(locale, format, *args)");
                sb2.append(format2);
                sb2.append(' ');
                sb = sb2.toString();
            } else if (alarmInfo.getHour() == 12) {
                string = this.b.f5040a.getResources().getString(R.string.PM);
                Intrinsics.checkNotNullExpressionValue(string, "mContext.resources.getString(R.string.PM)");
                StringBuilder sb3 = new StringBuilder();
                StringCompanionObject stringCompanionObject2 = StringCompanionObject.INSTANCE;
                Locale locale2 = Locale.ENGLISH;
                String format3 = String.format(locale2, "%02d", Arrays.copyOf(new Object[]{Integer.valueOf(alarmInfo.getHour())}, 1));
                Intrinsics.checkNotNullExpressionValue(format3, "format(locale, format, *args)");
                sb3.append(format3);
                sb3.append(':');
                String format4 = String.format(locale2, "%02d", Arrays.copyOf(new Object[]{Integer.valueOf(alarmInfo.getMinute())}, 1));
                Intrinsics.checkNotNullExpressionValue(format4, "format(locale, format, *args)");
                sb3.append(format4);
                sb = sb3.toString();
            } else {
                string = this.b.f5040a.getResources().getString(R.string.AM);
                Intrinsics.checkNotNullExpressionValue(string, "mContext.resources.getString(R.string.AM)");
                StringBuilder sb4 = new StringBuilder();
                StringCompanionObject stringCompanionObject3 = StringCompanionObject.INSTANCE;
                Locale locale3 = Locale.ENGLISH;
                String format5 = String.format(locale3, "%02d", Arrays.copyOf(new Object[]{Integer.valueOf(alarmInfo.getHour())}, 1));
                Intrinsics.checkNotNullExpressionValue(format5, "format(locale, format, *args)");
                sb4.append(format5);
                sb4.append(':');
                String format6 = String.format(locale3, "%02d", Arrays.copyOf(new Object[]{Integer.valueOf(alarmInfo.getMinute())}, 1));
                Intrinsics.checkNotNullExpressionValue(format6, "format(locale, format, *args)");
                sb4.append(format6);
                sb = sb4.toString();
            }
            this.f5041a.alarmTime.setText(sb);
            this.f5041a.alarmAmPm.setText(string);
        }

        @NotNull
        public final AlarmListRowNewBinding getBinding() {
            return this.f5041a;
        }

        public final void updateShouldExpandAlarm(boolean z) {
            this.f5041a.setShouldExpandAlarm(Boolean.valueOf(!z));
            this.b.notifyItemChanged(getPosition());
        }
    }

    public AlarmListAdapterNew(@NotNull Context mContext, @NotNull List<AlarmDataHolder> mAlarmInfo, @NotNull AlarmListener listener) {
        Intrinsics.checkNotNullParameter(mContext, "mContext");
        Intrinsics.checkNotNullParameter(mAlarmInfo, "mAlarmInfo");
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.f5040a = mContext;
        this.b = mAlarmInfo;
        this.c = listener;
        this.e = new HashMap<>();
    }

    public final void a(ViewHolder viewHolder, AlarmInfo alarmInfo) {
        String str;
        String removeSuffix;
        int i = 0;
        if (alarmInfo.getDaysSelected().getIsDaySelected()[0]) {
            str = "Sun,";
            i = 1;
        } else {
            str = "";
        }
        if (alarmInfo.getDaysSelected().getIsDaySelected()[1]) {
            str = str + "Mon,";
            i++;
        }
        if (alarmInfo.getDaysSelected().getIsDaySelected()[2]) {
            str = str + "Tue,";
            i++;
        }
        if (alarmInfo.getDaysSelected().getIsDaySelected()[3]) {
            str = str + "Wed,";
            i++;
        }
        if (alarmInfo.getDaysSelected().getIsDaySelected()[4]) {
            str = str + "Thu,";
            i++;
        }
        if (alarmInfo.getDaysSelected().getIsDaySelected()[5]) {
            str = str + "Fri,";
            i++;
        }
        if (alarmInfo.getDaysSelected().getIsDaySelected()[6]) {
            str = str + "Sat";
            i++;
        }
        if (str.equals("")) {
            removeSuffix = "Once";
        } else {
            removeSuffix = i == 7 ? "Everyday" : StringsKt__StringsKt.removeSuffix(str, (CharSequence) Constants.SEPARATOR_COMMA);
        }
        viewHolder.getBinding().alarmRepeat.setText(removeSuffix);
    }

    public final void addAlarmInfo(@NotNull AlarmDataHolder alarmDataHolder) {
        boolean z;
        Intrinsics.checkNotNullParameter(alarmDataHolder, "alarmDataHolder");
        List<AlarmDataHolder> list = this.b;
        boolean z2 = true;
        if (!(list instanceof Collection) || !list.isEmpty()) {
            for (AlarmDataHolder alarmDataHolder2 : list) {
                if (alarmDataHolder2.getAlarmInfo().getHour() == alarmDataHolder.getAlarmInfo().getHour() && alarmDataHolder2.getAlarmInfo().getMinute() == alarmDataHolder.getAlarmInfo().getMinute() && Arrays.equals(alarmDataHolder2.getAlarmInfo().getDaysSelected().getIsDaySelected(), alarmDataHolder.getAlarmInfo().getDaysSelected().getIsDaySelected())) {
                    z = true;
                    continue;
                } else {
                    z = false;
                    continue;
                }
                if (z) {
                    break;
                }
            }
        }
        z2 = false;
        if (!z2) {
            this.b.add(alarmDataHolder);
            return;
        }
        Context context = this.f5040a;
        Toast.makeText(context, context.getString(R.string.an_alarm_with_same_time_exists), 0).show();
    }

    public final void b(int i, AlarmInfo alarmInfo) {
        this.b.get(i).setAlarmInfo(alarmInfo);
    }

    @Nullable
    public final String[] getAlarmTypeArray() {
        return this.d;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        List<AlarmDataHolder> list = this.b;
        Intrinsics.checkNotNull(list);
        return list.size();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public long getItemId(int i) {
        return super.getItemId(i);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemViewType(int i) {
        return super.getItemViewType(i);
    }

    @NotNull
    public final List<AlarmDataHolder> getMAlarmInfo() {
        return this.b;
    }

    @NotNull
    public final HashMap<Integer, CustomizedTextWatcher> getTextWatcherMap() {
        return this.e;
    }

    public final void setAlarmInfo(@NotNull List<AlarmDataHolder> alarmInfos) {
        Intrinsics.checkNotNullParameter(alarmInfos, "alarmInfos");
        this.b = alarmInfos;
        notifyDataSetChanged();
    }

    public final void setAlarmTypeArray(@Nullable String[] strArr) {
        this.d = strArr;
    }

    public final void setMAlarmInfo(@NotNull List<AlarmDataHolder> list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        this.b = list;
    }

    public final void updateShouldExpandAlarm(boolean z, int i) {
        this.b.get(i).setAlreadySaved(z);
        notifyItemChanged(i);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(@NotNull ViewHolder holder, int i) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        List<AlarmDataHolder> list = this.b;
        AlarmDataHolder alarmDataHolder = list != null ? list.get(i) : null;
        Intrinsics.checkNotNull(alarmDataHolder);
        holder.bind(alarmDataHolder, i);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    @NotNull
    public ViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int i) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        AlarmListRowNewBinding inflate = AlarmListRowNewBinding.inflate(LayoutInflater.from(this.f5040a), parent, false);
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(LayoutInflater.f…mContext), parent, false)");
        return new ViewHolder(this, inflate);
    }
}
