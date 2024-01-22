package com.coveiot.android.fitnesschallenges.adpter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import androidx.paging.PagingDataAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;
import com.coveiot.android.fitnessbuddies.R;
import com.coveiot.android.fitnesschallenges.adpter.ViewAllParticipantsDetailsPagingAdapter;
import com.coveiot.android.fitnesschallenges.databinding.ParticipantItemBinding;
import com.coveiot.coveaccess.fitnesschallenge.model.GetAllParticipantsFitnessChallengeRes;
import com.coveiot.covepreferences.SessionManager;
import com.coveiot.utils.utility.GlideUtils;
import com.coveiot.utils.utility.ImageLodeListener;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes2.dex */
public final class ViewAllParticipantsDetailsPagingAdapter extends PagingDataAdapter<GetAllParticipantsFitnessChallengeRes.ParticipantsDetails, FitnessViewHolder> {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    public static final ViewAllParticipantsDetailsPagingAdapter$Companion$COMPARATOR$1 h = new DiffUtil.ItemCallback<GetAllParticipantsFitnessChallengeRes.ParticipantsDetails>() { // from class: com.coveiot.android.fitnesschallenges.adpter.ViewAllParticipantsDetailsPagingAdapter$Companion$COMPARATOR$1
        @Override // androidx.recyclerview.widget.DiffUtil.ItemCallback
        @SuppressLint({"DiffUtilEquals"})
        public boolean areContentsTheSame(@NotNull GetAllParticipantsFitnessChallengeRes.ParticipantsDetails oldItem, @NotNull GetAllParticipantsFitnessChallengeRes.ParticipantsDetails newItem) {
            Intrinsics.checkNotNullParameter(oldItem, "oldItem");
            Intrinsics.checkNotNullParameter(newItem, "newItem");
            return Intrinsics.areEqual(oldItem, newItem);
        }

        @Override // androidx.recyclerview.widget.DiffUtil.ItemCallback
        public boolean areItemsTheSame(@NotNull GetAllParticipantsFitnessChallengeRes.ParticipantsDetails oldItem, @NotNull GetAllParticipantsFitnessChallengeRes.ParticipantsDetails newItem) {
            Intrinsics.checkNotNullParameter(oldItem, "oldItem");
            Intrinsics.checkNotNullParameter(newItem, "newItem");
            return Intrinsics.areEqual(oldItem.getName(), newItem.getName());
        }
    };
    @NotNull
    public final Context e;
    public final boolean f;
    @NotNull
    public final ChallengeClickListener g;

    /* loaded from: classes2.dex */
    public interface ChallengeClickListener {
        void isDataLoaded(boolean z);

        void isItemSelected(@NotNull GetAllParticipantsFitnessChallengeRes.ParticipantsDetails participantsDetails, boolean z);
    }

    /* loaded from: classes2.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    /* loaded from: classes2.dex */
    public final class FitnessViewHolder extends RecyclerView.ViewHolder {
        @NotNull

        /* renamed from: a  reason: collision with root package name */
        public final ParticipantItemBinding f4504a;
        public final /* synthetic */ ViewAllParticipantsDetailsPagingAdapter b;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public FitnessViewHolder(@NotNull ViewAllParticipantsDetailsPagingAdapter viewAllParticipantsDetailsPagingAdapter, ParticipantItemBinding binding) {
            super(binding.getRoot());
            Intrinsics.checkNotNullParameter(binding, "binding");
            this.b = viewAllParticipantsDetailsPagingAdapter;
            this.f4504a = binding;
        }

        public static final void f() {
        }

        public static final void g(FitnessViewHolder this$0, ViewAllParticipantsDetailsPagingAdapter this$1, GetAllParticipantsFitnessChallengeRes.ParticipantsDetails participant, CompoundButton compoundButton, boolean z) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            Intrinsics.checkNotNullParameter(this$1, "this$1");
            Intrinsics.checkNotNullParameter(participant, "$participant");
            this$0.getAbsoluteAdapterPosition();
            this$1.g.isItemSelected(participant, z);
        }

        public final void bind(@NotNull final GetAllParticipantsFitnessChallengeRes.ParticipantsDetails participant) {
            CheckBox checkBox;
            Intrinsics.checkNotNullParameter(participant, "participant");
            ParticipantItemBinding participantItemBinding = this.f4504a;
            final ViewAllParticipantsDetailsPagingAdapter viewAllParticipantsDetailsPagingAdapter = this.b;
            participantItemBinding.setParticipantItem(participant);
            int i = 8;
            if (participant.getUserId().equals(String.valueOf(SessionManager.getInstance(viewAllParticipantsDetailsPagingAdapter.getContext()).getUserDetails().getUserId()))) {
                TextView textView = participantItemBinding.displayName;
                textView.setText(participant.getName() + " (Myself)");
                participantItemBinding.cbParticipant.setVisibility(8);
            } else {
                if (viewAllParticipantsDetailsPagingAdapter.isViewAllParticipants()) {
                    checkBox = participantItemBinding.cbParticipant;
                } else {
                    checkBox = participantItemBinding.cbParticipant;
                    i = 0;
                }
                checkBox.setVisibility(i);
                participantItemBinding.displayName.setText(participant.getName());
            }
            if (participant.getDpUrl() != null) {
                GlideUtils.loadCircularImage(viewAllParticipantsDetailsPagingAdapter.getContext(), participant.getDpUrl(), participantItemBinding.buddiesDisplayPic, new ImageLodeListener() { // from class: com.coveiot.android.fitnesschallenges.adpter.k
                    @Override // com.coveiot.utils.utility.ImageLodeListener
                    public final void onImageLoaded() {
                        ViewAllParticipantsDetailsPagingAdapter.FitnessViewHolder.f();
                    }
                });
            } else {
                participantItemBinding.buddiesDisplayPic.setImageResource(R.drawable.default_avatar);
            }
            participantItemBinding.cbParticipant.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.coveiot.android.fitnesschallenges.adpter.j
                @Override // android.widget.CompoundButton.OnCheckedChangeListener
                public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                    ViewAllParticipantsDetailsPagingAdapter.FitnessViewHolder.g(ViewAllParticipantsDetailsPagingAdapter.FitnessViewHolder.this, viewAllParticipantsDetailsPagingAdapter, participant, compoundButton, z);
                }
            });
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ViewAllParticipantsDetailsPagingAdapter(@NotNull Context context, boolean z, @NotNull ChallengeClickListener listener) {
        super(h, null, null, 6, null);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.e = context;
        this.f = z;
        this.g = listener;
    }

    @NotNull
    public final Context getContext() {
        return this.e;
    }

    public final boolean isViewAllParticipants() {
        return this.f;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(@NotNull FitnessViewHolder holder, int i) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        GetAllParticipantsFitnessChallengeRes.ParticipantsDetails item = getItem(i);
        this.g.isDataLoaded(item != null);
        if (item != null) {
            holder.bind(item);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    @NotNull
    public FitnessViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int i) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        ParticipantItemBinding inflate = ParticipantItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(\n               …      false\n            )");
        return new FitnessViewHolder(this, inflate);
    }
}
