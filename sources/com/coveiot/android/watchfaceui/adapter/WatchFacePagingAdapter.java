package com.coveiot.android.watchfaceui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;
import androidx.paging.PagingDataAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;
import com.coveiot.android.watchfacecore.model.WatchFaceBean;
import com.coveiot.android.watchfaceui.R;
import java.util.ArrayList;
import java.util.List;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes8.dex */
public final class WatchFacePagingAdapter extends PagingDataAdapter<WatchFaceBean, WatchFacePagingViewHolder> {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    public static final WatchFacePagingAdapter$Companion$WATCH_FACE_COMPARATOR$1 u = new DiffUtil.ItemCallback<WatchFaceBean>() { // from class: com.coveiot.android.watchfaceui.adapter.WatchFacePagingAdapter$Companion$WATCH_FACE_COMPARATOR$1
        @Override // androidx.recyclerview.widget.DiffUtil.ItemCallback
        public boolean areContentsTheSame(@NotNull WatchFaceBean oldItem, @NotNull WatchFaceBean newItem) {
            Intrinsics.checkNotNullParameter(oldItem, "oldItem");
            Intrinsics.checkNotNullParameter(newItem, "newItem");
            return Intrinsics.areEqual(oldItem, newItem);
        }

        @Override // androidx.recyclerview.widget.DiffUtil.ItemCallback
        public boolean areItemsTheSame(@NotNull WatchFaceBean oldItem, @NotNull WatchFaceBean newItem) {
            Intrinsics.checkNotNullParameter(oldItem, "oldItem");
            Intrinsics.checkNotNullParameter(newItem, "newItem");
            return Intrinsics.areEqual(oldItem.getFaceId(), newItem.getFaceId());
        }
    };
    @NotNull
    public final Context e;
    public final int f;
    @Nullable
    public final OnCheckBoxListener g;
    @Nullable
    public WatchFaceBean h;
    @NotNull
    public List<WatchFaceBean> i;
    @NotNull
    public List<WatchFaceBean> j;
    @Nullable
    public Integer k;
    public boolean l;
    public boolean m;
    @Nullable
    public OnWatchFaceItemClickListener n;
    @Nullable
    public OnInitiateLongPress o;
    @Nullable
    public OnApplyNowClicked p;
    public boolean q;
    public boolean r;
    @NotNull
    public int[] s;
    @NotNull
    public int[] t;

    /* loaded from: classes8.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    /* loaded from: classes8.dex */
    public interface OnApplyNowClicked {
        void onClick(@NotNull WatchFaceBean watchFaceBean);
    }

    /* loaded from: classes8.dex */
    public interface OnCheckBoxListener {
        void onChecked(@NotNull WatchFaceBean watchFaceBean, boolean z);
    }

    /* loaded from: classes8.dex */
    public interface OnInitiateLongPress {
        void onLongPressed(@NotNull WatchFaceBean watchFaceBean);
    }

    /* loaded from: classes8.dex */
    public interface OnWatchFaceItemClickListener {
        void onItemClick(@NotNull WatchFaceBean watchFaceBean);

        void onSelectionFound(@NotNull WatchFaceBean watchFaceBean);
    }

    /* loaded from: classes8.dex */
    public static final class WatchFacePagingViewHolder extends RecyclerView.ViewHolder {
        @NotNull

        /* renamed from: a  reason: collision with root package name */
        public final ImageView f6122a;
        @NotNull
        public final ImageView b;
        @NotNull
        public final LinearLayout c;
        @NotNull
        public final RelativeLayout d;
        @NotNull
        public final CheckBox e;
        @NotNull
        public final ImageView f;
        @NotNull
        public final ImageView g;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public WatchFacePagingViewHolder(@NotNull View view) {
            super(view);
            Intrinsics.checkNotNullParameter(view, "view");
            ImageView imageView = (ImageView) view.findViewById(R.id.iv_watchimage);
            Intrinsics.checkNotNullExpressionValue(imageView, "view.iv_watchimage");
            this.f6122a = imageView;
            ImageView imageView2 = (ImageView) view.findViewById(R.id.iv_watchimage_selected);
            Intrinsics.checkNotNullExpressionValue(imageView2, "view.iv_watchimage_selected");
            this.b = imageView2;
            LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.watch_item_view);
            Intrinsics.checkNotNullExpressionValue(linearLayout, "view.watch_item_view");
            this.c = linearLayout;
            RelativeLayout relativeLayout = (RelativeLayout) view.findViewById(R.id.watch_item_view_rel);
            Intrinsics.checkNotNullExpressionValue(relativeLayout, "view.watch_item_view_rel");
            this.d = relativeLayout;
            CheckBox checkBox = (CheckBox) view.findViewById(R.id.deleteCheckBox);
            Intrinsics.checkNotNullExpressionValue(checkBox, "view.deleteCheckBox");
            this.e = checkBox;
            ImageView imageView3 = (ImageView) view.findViewById(R.id.ivAppliedWatchFace);
            Intrinsics.checkNotNullExpressionValue(imageView3, "view.ivAppliedWatchFace");
            this.f = imageView3;
            ImageView imageView4 = (ImageView) view.findViewById(R.id.newWatchFaceTag);
            Intrinsics.checkNotNullExpressionValue(imageView4, "view.newWatchFaceTag");
            this.g = imageView4;
        }

        @NotNull
        public final ImageView getAppliedWatchFace() {
            return this.f;
        }

        @NotNull
        public final CheckBox getCheckBox() {
            return this.e;
        }

        @NotNull
        public final ImageView getNewWatchFace() {
            return this.g;
        }

        @NotNull
        public final ImageView getWatchImage() {
            return this.f6122a;
        }

        @NotNull
        public final ImageView getWatchImageSelected() {
            return this.b;
        }

        @NotNull
        public final LinearLayout getWatchItemParentView() {
            return this.c;
        }

        @NotNull
        public final RelativeLayout getWatchItemParentView1() {
            return this.d;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public WatchFacePagingAdapter(@NotNull Context mContext, int i, @Nullable OnCheckBoxListener onCheckBoxListener) {
        super(u, null, null, 6, null);
        Intrinsics.checkNotNullParameter(mContext, "mContext");
        this.e = mContext;
        this.f = i;
        this.g = onCheckBoxListener;
        this.i = new ArrayList();
        this.j = new ArrayList();
        this.s = new int[]{R.drawable.beat_call_plus_gt_default_1, R.drawable.beat_call_plus_gt_default_2, R.drawable.beat_call_plus_gt_default_3, R.drawable.beat_call_plus_gt_default_4};
        this.t = new int[]{R.drawable.wave_style_call_tce_default_1, R.drawable.wave_style_call_tce_default_2, R.drawable.wave_style_call_tce_default_3, R.drawable.wave_style_call_tce_default_4};
    }

    public static final void h(WatchFacePagingAdapter this$0, Ref.ObjectRef watchFaceItem, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(watchFaceItem, "$watchFaceItem");
        T t = watchFaceItem.element;
        this$0.h = (WatchFaceBean) t;
        this$0.k = ((WatchFaceBean) t).getFacePosition();
        this$0.notifyDataSetChanged();
        OnWatchFaceItemClickListener onWatchFaceItemClickListener = this$0.n;
        if (onWatchFaceItemClickListener != null) {
            Intrinsics.checkNotNull(onWatchFaceItemClickListener);
            onWatchFaceItemClickListener.onItemClick((WatchFaceBean) watchFaceItem.element);
        }
    }

    public static final void i(WatchFacePagingAdapter this$0, Ref.ObjectRef watchFaceItem, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(watchFaceItem, "$watchFaceItem");
        T t = watchFaceItem.element;
        this$0.h = (WatchFaceBean) t;
        this$0.k = ((WatchFaceBean) t).getFacePosition();
        this$0.notifyDataSetChanged();
        OnWatchFaceItemClickListener onWatchFaceItemClickListener = this$0.n;
        if (onWatchFaceItemClickListener != null) {
            Intrinsics.checkNotNull(onWatchFaceItemClickListener);
            onWatchFaceItemClickListener.onItemClick((WatchFaceBean) watchFaceItem.element);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static final void j(WatchFacePagingAdapter this$0, Ref.ObjectRef watchFaceItem, WatchFacePagingViewHolder holder, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(watchFaceItem, "$watchFaceItem");
        Intrinsics.checkNotNullParameter(holder, "$holder");
        if (!this$0.r) {
            T t = watchFaceItem.element;
            this$0.h = (WatchFaceBean) t;
            this$0.k = ((WatchFaceBean) t).getFacePosition();
            this$0.notifyDataSetChanged();
            OnWatchFaceItemClickListener onWatchFaceItemClickListener = this$0.n;
            if (onWatchFaceItemClickListener != null) {
                Intrinsics.checkNotNull(onWatchFaceItemClickListener);
                onWatchFaceItemClickListener.onItemClick((WatchFaceBean) watchFaceItem.element);
                return;
            }
            return;
        }
        holder.getCheckBox().setChecked(!holder.getCheckBox().isChecked());
        if (!holder.getCheckBox().isChecked()) {
            if (!this$0.i.contains(watchFaceItem.element)) {
                this$0.i.add(watchFaceItem.element);
            }
            if (this$0.j.contains(watchFaceItem.element)) {
                this$0.j.remove(watchFaceItem.element);
            }
        } else {
            if (this$0.i.contains(watchFaceItem.element)) {
                this$0.i.remove(watchFaceItem.element);
            }
            if (!this$0.j.contains(watchFaceItem.element)) {
                this$0.j.add(watchFaceItem.element);
            }
        }
        OnCheckBoxListener onCheckBoxListener = this$0.g;
        if (onCheckBoxListener != null) {
            onCheckBoxListener.onChecked((WatchFaceBean) watchFaceItem.element, holder.getCheckBox().isChecked());
        }
    }

    public static final boolean k(Ref.ObjectRef lastApplied, Ref.ObjectRef watchFaceItem, WatchFacePagingAdapter this$0, View view) {
        Intrinsics.checkNotNullParameter(lastApplied, "$lastApplied");
        Intrinsics.checkNotNullParameter(watchFaceItem, "$watchFaceItem");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        WatchFaceBean watchFaceBean = (WatchFaceBean) lastApplied.element;
        String uid = watchFaceBean != null ? watchFaceBean.getUid() : null;
        WatchFaceBean watchFaceBean2 = (WatchFaceBean) watchFaceItem.element;
        if (Intrinsics.areEqual(uid, watchFaceBean2 != null ? watchFaceBean2.getUid() : null)) {
            Context context = this$0.e;
            Toast.makeText(context, context.getString(R.string.long_press_is_disabled_), 0).show();
        } else {
            OnInitiateLongPress onInitiateLongPress = this$0.o;
            if (onInitiateLongPress != null) {
                Intrinsics.checkNotNull(onInitiateLongPress);
                onInitiateLongPress.onLongPressed((WatchFaceBean) watchFaceItem.element);
            }
            this$0.r = true;
        }
        return true;
    }

    public static final void l(WatchFacePagingAdapter this$0, Ref.ObjectRef watchFaceItem, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(watchFaceItem, "$watchFaceItem");
        T t = watchFaceItem.element;
        this$0.h = (WatchFaceBean) t;
        this$0.k = ((WatchFaceBean) t).getFacePosition();
        this$0.notifyDataSetChanged();
        OnWatchFaceItemClickListener onWatchFaceItemClickListener = this$0.n;
        if (onWatchFaceItemClickListener != null) {
            Intrinsics.checkNotNull(onWatchFaceItemClickListener);
            onWatchFaceItemClickListener.onItemClick((WatchFaceBean) watchFaceItem.element);
        }
    }

    public static final void m(WatchFacePagingAdapter this$0, Ref.ObjectRef watchFaceItem, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(watchFaceItem, "$watchFaceItem");
        T t = watchFaceItem.element;
        this$0.h = (WatchFaceBean) t;
        this$0.k = ((WatchFaceBean) t).getFacePosition();
        this$0.notifyDataSetChanged();
        OnWatchFaceItemClickListener onWatchFaceItemClickListener = this$0.n;
        if (onWatchFaceItemClickListener != null) {
            Intrinsics.checkNotNull(onWatchFaceItemClickListener);
            onWatchFaceItemClickListener.onItemClick((WatchFaceBean) watchFaceItem.element);
        }
    }

    public final void clearChangesWatchfaceListByOnItemClick() {
        this.j.clear();
        this.i.clear();
    }

    public final void clearCheckBox(boolean z) {
        this.m = !z;
        notifyDataSetChanged();
    }

    @Nullable
    public final OnApplyNowClicked getApplyClickListener() {
        return this.p;
    }

    @NotNull
    public final int[] getBeatCallPlusGujaratTitansImageArray() {
        return this.s;
    }

    @Override // androidx.paging.PagingDataAdapter, androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return super.getItemCount();
    }

    @Nullable
    public final OnWatchFaceItemClickListener getListener() {
        return this.n;
    }

    @Nullable
    public final OnInitiateLongPress getLongPressListener() {
        return this.o;
    }

    @Nullable
    public final Integer getMSelectedPosition() {
        return this.k;
    }

    @Nullable
    public final WatchFaceBean getMSelectedWatchFace() {
        return this.h;
    }

    @Nullable
    public final OnCheckBoxListener getOnCheckBoxListener() {
        return this.g;
    }

    @Nullable
    public final WatchFaceBean getSelectedWatchFace() {
        return this.h;
    }

    public final boolean getSetAlpha() {
        return this.q;
    }

    public final boolean getShowCheckBox() {
        return this.l;
    }

    @NotNull
    public final int[] getWaveStyleCallTCEImageArray() {
        return this.t;
    }

    public final boolean isAvailableInSelectedWatchfaceItems(@NotNull WatchFaceBean watchFaceBean) {
        Intrinsics.checkNotNullParameter(watchFaceBean, "watchFaceBean");
        List<WatchFaceBean> list = this.j;
        if (!(list == null || list.isEmpty())) {
            for (WatchFaceBean watchFaceBean2 : this.j) {
                if (Intrinsics.areEqual(watchFaceBean2.getUid(), watchFaceBean.getUid())) {
                    return true;
                }
            }
        }
        return false;
    }

    public final boolean isAvailableInUnSelectedWatchfaceItems(@NotNull WatchFaceBean watchFaceBean) {
        Intrinsics.checkNotNullParameter(watchFaceBean, "watchFaceBean");
        List<WatchFaceBean> list = this.i;
        if (!(list == null || list.isEmpty())) {
            for (WatchFaceBean watchFaceBean2 : this.i) {
                if (Intrinsics.areEqual(watchFaceBean2.getUid(), watchFaceBean.getUid())) {
                    return true;
                }
            }
        }
        return false;
    }

    public final boolean isLongPressed() {
        return this.r;
    }

    public final boolean isSelectedAll() {
        return this.m;
    }

    public final void setAlpha(boolean z) {
        this.q = z;
    }

    public final void setApplyClickListener(@Nullable OnApplyNowClicked onApplyNowClicked) {
        this.p = onApplyNowClicked;
    }

    public final void setBeatCallPlusGujaratTitansImageArray(@NotNull int[] iArr) {
        Intrinsics.checkNotNullParameter(iArr, "<set-?>");
        this.s = iArr;
    }

    public final void setListener(@Nullable OnWatchFaceItemClickListener onWatchFaceItemClickListener) {
        this.n = onWatchFaceItemClickListener;
    }

    public final void setLongPressListener(@Nullable OnInitiateLongPress onInitiateLongPress) {
        this.o = onInitiateLongPress;
    }

    public final void setLongPressed(boolean z) {
        this.r = z;
    }

    public final void setMSelectedPosition(@Nullable Integer num) {
        this.k = num;
    }

    public final void setMSelectedWatchFace(@Nullable WatchFaceBean watchFaceBean) {
        this.h = watchFaceBean;
    }

    public final void setOnWatchFaceClickListener(@Nullable OnWatchFaceItemClickListener onWatchFaceItemClickListener, @Nullable OnInitiateLongPress onInitiateLongPress) {
        this.n = onWatchFaceItemClickListener;
        this.o = onInitiateLongPress;
    }

    public final void setSelectedAll(boolean z) {
        this.m = z;
    }

    public final void setSelectedPosition(int i) {
        notifyDataSetChanged();
    }

    public final void setSetAlpha(boolean z) {
        this.q = z;
    }

    public final void setShowCheckBox(boolean z) {
        this.l = z;
    }

    public final void setWaveStyleCallTCEImageArray(@NotNull int[] iArr) {
        Intrinsics.checkNotNullParameter(iArr, "<set-?>");
        this.t = iArr;
    }

    public final void showCheckBox(boolean z) {
        this.l = z;
        notifyDataSetChanged();
    }

    /* JADX WARN: Removed duplicated region for block: B:91:0x02a9  */
    /* JADX WARN: Removed duplicated region for block: B:92:0x02b2  */
    /* JADX WARN: Type inference failed for: r1v0, types: [T, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r2v3, types: [T, com.coveiot.android.watchfacecore.model.WatchFaceBean] */
    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void onBindViewHolder(@org.jetbrains.annotations.NotNull final com.coveiot.android.watchfaceui.adapter.WatchFacePagingAdapter.WatchFacePagingViewHolder r10, int r11) {
        /*
            Method dump skipped, instructions count: 1011
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.watchfaceui.adapter.WatchFacePagingAdapter.onBindViewHolder(com.coveiot.android.watchfaceui.adapter.WatchFacePagingAdapter$WatchFacePagingViewHolder, int):void");
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    @NotNull
    public WatchFacePagingViewHolder onCreateViewHolder(@NotNull ViewGroup viewGroup, int i) {
        Intrinsics.checkNotNullParameter(viewGroup, "viewGroup");
        View inflate = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cloud_watch_face_item, (ViewGroup) null);
        Intrinsics.checkNotNullExpressionValue(inflate, "from(viewGroup.context)\n…ud_watch_face_item, null)");
        return new WatchFacePagingViewHolder(inflate);
    }
}
