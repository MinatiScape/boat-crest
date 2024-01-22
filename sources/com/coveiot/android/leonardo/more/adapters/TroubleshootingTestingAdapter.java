package com.coveiot.android.leonardo.more.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.coveiot.android.boat.R;
import com.coveiot.android.boat.databinding.ListItemTroubleshootTestingBinding;
import com.coveiot.android.leonardo.model.TroubleshootingTestModel;
import com.coveiot.android.leonardo.more.adapters.TroubleshootingTestingAdapter;
import com.coveiot.android.leonardo.more.models.TestingStatus;
import com.coveiot.android.leonardo.more.models.TroubleshootTestType;
import com.coveiot.android.leonardo.more.viewmodel.TroubleshootTestingViewModel;
import com.coveiot.android.leonardo.utils.ViewUtilsKt;
import com.coveiot.covepreferences.UserDataManager;
import com.coveiot.covepreferences.data.AppNotificationData;
import java.util.ArrayList;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes5.dex */
public final class TroubleshootingTestingAdapter extends RecyclerView.Adapter<ViewHolder> {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f5095a;
    @NotNull
    public final List<TroubleshootingTestModel> b;
    @Nullable
    public OnItemClickListener c;
    @Nullable
    public TroubleshootingAppNotificationAdapter d;
    @NotNull
    public List<AppNotificationData> e;

    /* loaded from: classes5.dex */
    public interface OnItemClickListener {
        void onItemClick(@NotNull TroubleshootingTestModel troubleshootingTestModel);
    }

    /* loaded from: classes5.dex */
    public final class ViewHolder extends RecyclerView.ViewHolder {
        @NotNull

        /* renamed from: a  reason: collision with root package name */
        public final ListItemTroubleshootTestingBinding f5096a;
        public final /* synthetic */ TroubleshootingTestingAdapter b;

        /* loaded from: classes5.dex */
        public /* synthetic */ class WhenMappings {
            public static final /* synthetic */ int[] $EnumSwitchMapping$0;

            static {
                int[] iArr = new int[TestingStatus.values().length];
                try {
                    iArr[TestingStatus.NOT_STARTED.ordinal()] = 1;
                } catch (NoSuchFieldError unused) {
                }
                try {
                    iArr[TestingStatus.IN_PROGRESS.ordinal()] = 2;
                } catch (NoSuchFieldError unused2) {
                }
                try {
                    iArr[TestingStatus.PASSED.ordinal()] = 3;
                } catch (NoSuchFieldError unused3) {
                }
                try {
                    iArr[TestingStatus.FAILED.ordinal()] = 4;
                } catch (NoSuchFieldError unused4) {
                }
                $EnumSwitchMapping$0 = iArr;
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public ViewHolder(@NotNull TroubleshootingTestingAdapter troubleshootingTestingAdapter, ListItemTroubleshootTestingBinding binding) {
            super(binding.getRoot());
            Intrinsics.checkNotNullParameter(binding, "binding");
            this.b = troubleshootingTestingAdapter;
            this.f5096a = binding;
        }

        public static final void b(TroubleshootingTestingAdapter this$0, TroubleshootingTestModel troubleshootingTestModel, View view) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            Intrinsics.checkNotNullParameter(troubleshootingTestModel, "$troubleshootingTestModel");
            OnItemClickListener onItemClickListener = this$0.c;
            if (onItemClickListener != null) {
                onItemClickListener.onItemClick(troubleshootingTestModel);
            }
        }

        @SuppressLint({"SetTextI18n"})
        public final void bind(@NotNull final TroubleshootingTestModel troubleshootingTestModel) {
            Intrinsics.checkNotNullParameter(troubleshootingTestModel, "troubleshootingTestModel");
            ListItemTroubleshootTestingBinding listItemTroubleshootTestingBinding = this.f5096a;
            final TroubleshootingTestingAdapter troubleshootingTestingAdapter = this.b;
            TextView textView = listItemTroubleshootTestingBinding.troubleShootItemNo;
            StringBuilder sb = new StringBuilder();
            sb.append(troubleshootingTestModel.getSerialNumber());
            sb.append('.');
            textView.setText(sb.toString());
            listItemTroubleshootTestingBinding.troubleShootTitleTv.setText(troubleshootingTestModel.getTestName());
            listItemTroubleshootTestingBinding.troubleShootProgressTv.setVisibility(0);
            listItemTroubleshootTestingBinding.troubleShootProgressTv.setText(troubleshootingTestingAdapter.getContext().getString(R.string.not_started));
            listItemTroubleshootTestingBinding.fixThisTv.setVisibility(8);
            listItemTroubleshootTestingBinding.troubleShootFailureMsg.setVisibility(8);
            ConstraintLayout clTroubleShootProgress = listItemTroubleshootTestingBinding.clTroubleShootProgress;
            Intrinsics.checkNotNullExpressionValue(clTroubleShootProgress, "clTroubleShootProgress");
            ViewUtilsKt.gone(clTroubleShootProgress);
            listItemTroubleshootTestingBinding.clAppNotificationList.setVisibility(8);
            listItemTroubleshootTestingBinding.clMainLayout.setAlpha(0.5f);
            int i = WhenMappings.$EnumSwitchMapping$0[troubleshootingTestModel.getTestingStatus().ordinal()];
            boolean z = true;
            if (i == 1) {
                listItemTroubleshootTestingBinding.clMainLayout.setAlpha(0.5f);
                listItemTroubleshootTestingBinding.troubleShootProgressTv.setVisibility(0);
                listItemTroubleshootTestingBinding.troubleShootProgressTv.setText(troubleshootingTestingAdapter.getContext().getString(R.string.not_started));
                listItemTroubleshootTestingBinding.fixThisTv.setVisibility(8);
                listItemTroubleshootTestingBinding.troubleShootFailureMsg.setVisibility(8);
                listItemTroubleshootTestingBinding.troubleShootProgressImg.setVisibility(4);
                ConstraintLayout clTroubleShootProgress2 = listItemTroubleshootTestingBinding.clTroubleShootProgress;
                Intrinsics.checkNotNullExpressionValue(clTroubleShootProgress2, "clTroubleShootProgress");
                ViewUtilsKt.gone(clTroubleShootProgress2);
            } else if (i == 2) {
                listItemTroubleshootTestingBinding.clMainLayout.setAlpha(1.0f);
                listItemTroubleshootTestingBinding.troubleShootProgressTv.setVisibility(0);
                listItemTroubleshootTestingBinding.troubleShootProgressTv.setText(troubleshootingTestingAdapter.getContext().getString(R.string.in_progress_please_wait));
                listItemTroubleshootTestingBinding.fixThisTv.setVisibility(8);
                listItemTroubleshootTestingBinding.troubleShootFailureMsg.setVisibility(8);
                listItemTroubleshootTestingBinding.troubleShootProgressImg.setVisibility(4);
                ConstraintLayout clTroubleShootProgress3 = listItemTroubleshootTestingBinding.clTroubleShootProgress;
                Intrinsics.checkNotNullExpressionValue(clTroubleShootProgress3, "clTroubleShootProgress");
                ViewUtilsKt.visible(clTroubleShootProgress3);
            } else if (i == 3) {
                listItemTroubleshootTestingBinding.clMainLayout.setAlpha(1.0f);
                listItemTroubleshootTestingBinding.troubleShootProgressTv.setVisibility(0);
                listItemTroubleshootTestingBinding.troubleShootProgressTv.setText(troubleshootingTestingAdapter.getContext().getString(R.string.completed));
                listItemTroubleshootTestingBinding.fixThisTv.setVisibility(8);
                listItemTroubleshootTestingBinding.troubleShootFailureMsg.setVisibility(8);
                listItemTroubleshootTestingBinding.troubleShootProgressImg.setVisibility(0);
                listItemTroubleshootTestingBinding.troubleShootProgressImg.setImageDrawable(troubleshootingTestingAdapter.getContext().getDrawable(2131232603));
                ConstraintLayout clTroubleShootProgress4 = listItemTroubleshootTestingBinding.clTroubleShootProgress;
                Intrinsics.checkNotNullExpressionValue(clTroubleShootProgress4, "clTroubleShootProgress");
                ViewUtilsKt.gone(clTroubleShootProgress4);
                if (troubleshootingTestModel.getTestType() == TroubleshootTestType.APP_NOTIFICATION_SETTINGS_ON_APP) {
                    List<AppNotificationData> appNotificationsData = UserDataManager.getInstance(troubleshootingTestingAdapter.getContext()).getAppNotificationsData();
                    if (!(appNotificationsData == null || appNotificationsData.isEmpty())) {
                        troubleshootingTestingAdapter.getAppNotificationData().clear();
                        for (AppNotificationData appN : appNotificationsData) {
                            String appName = appN.getAppName();
                            if (!(appName == null || appName.length() == 0)) {
                                String packageName = appN.getPackageName();
                                if (!(packageName == null || packageName.length() == 0) && appN.getChecked()) {
                                    List<AppNotificationData> appNotificationData = troubleshootingTestingAdapter.getAppNotificationData();
                                    Intrinsics.checkNotNullExpressionValue(appN, "appN");
                                    appNotificationData.add(appN);
                                }
                            }
                        }
                        troubleshootingTestingAdapter.d = new TroubleshootingAppNotificationAdapter(troubleshootingTestingAdapter.getContext(), troubleshootingTestingAdapter.getAppNotificationData());
                        listItemTroubleshootTestingBinding.clAppNotificationList.setVisibility(0);
                        listItemTroubleshootTestingBinding.notificationSettingList.setLayoutManager(new LinearLayoutManager(troubleshootingTestingAdapter.getContext()));
                        listItemTroubleshootTestingBinding.notificationSettingList.setAdapter(troubleshootingTestingAdapter.d);
                    }
                }
            } else if (i != 4) {
                ConstraintLayout clTroubleShootProgress5 = listItemTroubleshootTestingBinding.clTroubleShootProgress;
                Intrinsics.checkNotNullExpressionValue(clTroubleShootProgress5, "clTroubleShootProgress");
                ViewUtilsKt.gone(clTroubleShootProgress5);
            } else {
                listItemTroubleshootTestingBinding.clMainLayout.setAlpha(1.0f);
                listItemTroubleshootTestingBinding.troubleShootProgressTv.setVisibility(8);
                String failureMessage = troubleshootingTestModel.getFailureMessage();
                if (!(failureMessage == null || failureMessage.length() == 0)) {
                    listItemTroubleshootTestingBinding.troubleShootFailureMsg.setText(troubleshootingTestModel.getFailureMessage());
                    listItemTroubleshootTestingBinding.troubleShootFailureMsg.setVisibility(0);
                }
                listItemTroubleshootTestingBinding.fixThisTv.setVisibility(0);
                if (troubleshootingTestModel.getTestType() == TroubleshootTestType.BT3_CONNECTIVITY_STATUS) {
                    String failureMessage2 = troubleshootingTestModel.getFailureMessage();
                    if (failureMessage2 != null && failureMessage2.length() != 0) {
                        z = false;
                    }
                    if (!z) {
                        String failureMessage3 = troubleshootingTestModel.getFailureMessage();
                        TroubleshootTestingViewModel.Companion companion = TroubleshootTestingViewModel.Companion;
                        if (kotlin.text.m.equals$default(failureMessage3, companion.getWATCH_BT3_PAIRED_NOT_CONNECTED(), false, 2, null)) {
                            listItemTroubleshootTestingBinding.fixThisTv.setText(troubleshootingTestingAdapter.getContext().getString(R.string.connect_now));
                        } else if (kotlin.text.m.equals$default(troubleshootingTestModel.getFailureMessage(), companion.getWATCH_BT3_NOT_PAIRED(), false, 2, null)) {
                            listItemTroubleshootTestingBinding.fixThisTv.setText(troubleshootingTestingAdapter.getContext().getString(R.string.pair_now));
                        } else {
                            listItemTroubleshootTestingBinding.fixThisTv.setText(troubleshootingTestingAdapter.getContext().getString(R.string.pair_now));
                        }
                    } else {
                        listItemTroubleshootTestingBinding.fixThisTv.setText(troubleshootingTestingAdapter.getContext().getString(R.string.pair_now));
                    }
                } else {
                    listItemTroubleshootTestingBinding.fixThisTv.setText(troubleshootingTestingAdapter.getContext().getString(R.string.fix_this));
                }
                listItemTroubleshootTestingBinding.troubleShootProgressImg.setVisibility(0);
                listItemTroubleshootTestingBinding.troubleShootProgressImg.setImageDrawable(troubleshootingTestingAdapter.getContext().getDrawable(2131232602));
                ConstraintLayout clTroubleShootProgress6 = listItemTroubleshootTestingBinding.clTroubleShootProgress;
                Intrinsics.checkNotNullExpressionValue(clTroubleShootProgress6, "clTroubleShootProgress");
                ViewUtilsKt.gone(clTroubleShootProgress6);
            }
            listItemTroubleshootTestingBinding.fixThisTv.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.adapters.m0
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    TroubleshootingTestingAdapter.ViewHolder.b(TroubleshootingTestingAdapter.this, troubleshootingTestModel, view);
                }
            });
        }
    }

    public TroubleshootingTestingAdapter(@NotNull Context context, @NotNull List<TroubleshootingTestModel> dataList) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(dataList, "dataList");
        this.f5095a = context;
        this.b = dataList;
        this.e = new ArrayList();
    }

    @NotNull
    public final List<AppNotificationData> getAppNotificationData() {
        return this.e;
    }

    @NotNull
    public final Context getContext() {
        return this.f5095a;
    }

    @NotNull
    public final List<TroubleshootingTestModel> getDataList() {
        return this.b;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.b.size();
    }

    public final void setAppNotificationData(@NotNull List<AppNotificationData> list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        this.e = list;
    }

    public final void setOnItemClickListener(@NotNull OnItemClickListener listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.c = listener;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(@NotNull ViewHolder holder, int i) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        holder.bind(this.b.get(i));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    @NotNull
    public ViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int i) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        ListItemTroubleshootTestingBinding inflate = ListItemTroubleshootTestingBinding.inflate(LayoutInflater.from(this.f5095a), parent, false);
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(LayoutInflater.f…(context), parent, false)");
        return new ViewHolder(this, inflate);
    }
}
