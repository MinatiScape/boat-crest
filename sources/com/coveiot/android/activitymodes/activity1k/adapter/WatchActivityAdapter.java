package com.coveiot.android.activitymodes.activity1k.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.coveiot.android.activitymodes.R;
import com.coveiot.android.activitymodes.activity1k.adapter.WatchActivityAdapter;
import com.coveiot.android.activitymodes.activity1k.model.CategoryAndActivityModel;
import com.coveiot.covepreferences.UserDataManager;
import com.coveiot.covepreferences.data.AutoRecognitionData;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.jvm.internal.TypeIntrinsics;
import kotlin.text.StringsKt__StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class WatchActivityAdapter extends RecyclerView.Adapter<ActivityViewHolder> {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public List<CategoryAndActivityModel> f2742a;
    @NotNull
    public final ItemClickListener b;
    @Nullable
    public List<CategoryAndActivityModel> c;

    /* loaded from: classes2.dex */
    public static final class ActivityViewHolder extends RecyclerView.ViewHolder {
        @Nullable

        /* renamed from: a  reason: collision with root package name */
        public TextView f2743a;
        @Nullable
        public TextView b;
        @Nullable
        public TextView c;
        @Nullable
        public ImageView d;
        @Nullable
        public ImageView e;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public ActivityViewHolder(@Nullable View view) {
            super(view);
            Intrinsics.checkNotNull(view);
            this.f2743a = (TextView) view.findViewById(R.id.txt_category);
            this.b = (TextView) view.findViewById(R.id.txt_activity);
            this.c = (TextView) view.findViewById(R.id.tv_auto_detected);
            this.d = (ImageView) view.findViewById(R.id.img_icon);
            this.e = (ImageView) view.findViewById(R.id.iv_info_icon);
        }

        public static final void e(View view) {
        }

        public static final boolean f(ActivityViewHolder this$0, View view) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            Context context = view.getContext();
            Toast.makeText(context, "Position is " + this$0.getAdapterPosition(), 0).show();
            return true;
        }

        public static final void g(ItemClickListener listener, CategoryAndActivityModel activityList, View view) {
            Intrinsics.checkNotNullParameter(listener, "$listener");
            Intrinsics.checkNotNullParameter(activityList, "$activityList");
            listener.onInfoClicked(activityList);
        }

        public static final void h(ItemClickListener listener, Ref.ObjectRef code, Ref.BooleanRef isSelectedForAutoDetection, CategoryAndActivityModel activityList, View view) {
            Intrinsics.checkNotNullParameter(listener, "$listener");
            Intrinsics.checkNotNullParameter(code, "$code");
            Intrinsics.checkNotNullParameter(isSelectedForAutoDetection, "$isSelectedForAutoDetection");
            Intrinsics.checkNotNullParameter(activityList, "$activityList");
            listener.onClicked((String) code.element, isSelectedForAutoDetection.element, activityList);
        }

        /* JADX WARN: Type inference failed for: r3v15, types: [T, java.lang.String] */
        public final void bindData(@NotNull final CategoryAndActivityModel activityList, @NotNull final ItemClickListener listener) {
            Intrinsics.checkNotNullParameter(activityList, "activityList");
            Intrinsics.checkNotNullParameter(listener, "listener");
            TextView textView = this.f2743a;
            if (textView != null) {
                textView.setText(activityList.getTitleInMetric());
            }
            TextView textView2 = this.f2743a;
            if (textView2 != null) {
                textView2.setVisibility(8);
            }
            if (activityList.getDefaultActivityName() != null) {
                TextView textView3 = this.b;
                if (textView3 != null) {
                    textView3.setText(activityList.getDefaultActivityName());
                }
            } else {
                TextView textView4 = this.b;
                if (textView4 != null) {
                    textView4.setText(activityList.getTitleInMetric());
                }
            }
            final Ref.BooleanRef booleanRef = new Ref.BooleanRef();
            final Ref.ObjectRef objectRef = new Ref.ObjectRef();
            Context context = this.itemView.getContext();
            Intrinsics.checkNotNull(context);
            List<AutoRecognitionData> autoRecognitionList = UserDataManager.getInstance(context).getAutoRecognitionList();
            if (autoRecognitionList != null) {
                Iterator<AutoRecognitionData> it = autoRecognitionList.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    AutoRecognitionData next = it.next();
                    if (next.isEnabled() && next.getFwActivityId() == activityList.getFwActId()) {
                        booleanRef.element = true;
                        objectRef.element = next.getActivityCode();
                        break;
                    }
                }
            }
            if (booleanRef.element) {
                TextView textView5 = this.c;
                if (textView5 != null) {
                    textView5.setVisibility(0);
                }
            } else {
                TextView textView6 = this.c;
                if (textView6 != null) {
                    textView6.setVisibility(8);
                }
            }
            RequestBuilder<Drawable> m30load = Glide.with(this.itemView.getContext()).m30load(activityList.getIconUrl());
            ImageView imageView = this.d;
            Intrinsics.checkNotNull(imageView);
            m30load.into(imageView);
            TextView textView7 = this.f2743a;
            if (textView7 != null) {
                textView7.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.activitymodes.activity1k.adapter.o
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        WatchActivityAdapter.ActivityViewHolder.e(view);
                    }
                });
            }
            TextView textView8 = this.f2743a;
            if (textView8 != null) {
                textView8.setOnLongClickListener(new View.OnLongClickListener() { // from class: com.coveiot.android.activitymodes.activity1k.adapter.p
                    @Override // android.view.View.OnLongClickListener
                    public final boolean onLongClick(View view) {
                        boolean f;
                        f = WatchActivityAdapter.ActivityViewHolder.f(WatchActivityAdapter.ActivityViewHolder.this, view);
                        return f;
                    }
                });
            }
            ImageView imageView2 = this.e;
            if (imageView2 != null) {
                imageView2.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.activitymodes.activity1k.adapter.m
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        WatchActivityAdapter.ActivityViewHolder.g(WatchActivityAdapter.ItemClickListener.this, activityList, view);
                    }
                });
            }
            this.itemView.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.activitymodes.activity1k.adapter.n
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    WatchActivityAdapter.ActivityViewHolder.h(WatchActivityAdapter.ItemClickListener.this, objectRef, booleanRef, activityList, view);
                }
            });
        }

        @Nullable
        public final ImageView getImg_icon() {
            return this.d;
        }

        @Nullable
        public final ImageView getInfo_icon() {
            return this.e;
        }

        @Nullable
        public final TextView getTxtActivity() {
            return this.b;
        }

        @Nullable
        public final TextView getTxtActivityType() {
            return this.f2743a;
        }

        @Nullable
        public final TextView getTxtAutoActivity() {
            return this.c;
        }

        public final void setImg_icon(@Nullable ImageView imageView) {
            this.d = imageView;
        }

        public final void setInfo_icon(@Nullable ImageView imageView) {
            this.e = imageView;
        }

        public final void setTxtActivity(@Nullable TextView textView) {
            this.b = textView;
        }

        public final void setTxtActivityType(@Nullable TextView textView) {
            this.f2743a = textView;
        }

        public final void setTxtAutoActivity(@Nullable TextView textView) {
            this.c = textView;
        }
    }

    /* loaded from: classes2.dex */
    public interface ItemClickListener {
        void onClicked(@Nullable String str, boolean z, @NotNull CategoryAndActivityModel categoryAndActivityModel);

        void onInfoClicked(@NotNull CategoryAndActivityModel categoryAndActivityModel);

        void onLongClicked(int i);
    }

    public WatchActivityAdapter(@NotNull List<CategoryAndActivityModel> originalList, @NotNull ItemClickListener listener) {
        Intrinsics.checkNotNullParameter(originalList, "originalList");
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.f2742a = originalList;
        this.b = listener;
        Intrinsics.checkNotNull(originalList, "null cannot be cast to non-null type java.util.ArrayList<com.coveiot.android.activitymodes.activity1k.model.CategoryAndActivityModel>");
        this.c = (ArrayList) originalList;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        List<CategoryAndActivityModel> list = this.c;
        Intrinsics.checkNotNull(list);
        return list.size();
    }

    @NotNull
    public final ItemClickListener getListener() {
        return this.b;
    }

    @NotNull
    public final List<CategoryAndActivityModel> getOriginalList() {
        return this.f2742a;
    }

    public final void notifyAdapter(@NotNull List<CategoryAndActivityModel> activityList) {
        Intrinsics.checkNotNullParameter(activityList, "activityList");
        this.c = TypeIntrinsics.asMutableList(activityList);
        notifyDataSetChanged();
    }

    public final void search(@NotNull String searchStr) {
        Intrinsics.checkNotNullParameter(searchStr, "searchStr");
        this.c = new ArrayList();
        for (CategoryAndActivityModel categoryAndActivityModel : this.f2742a) {
            if (searchStr != null && !Intrinsics.areEqual(searchStr, "")) {
                String shortTitle = categoryAndActivityModel.getShortTitle();
                Intrinsics.checkNotNull(shortTitle);
                String lowerCase = shortTitle.toLowerCase();
                Intrinsics.checkNotNullExpressionValue(lowerCase, "this as java.lang.String).toLowerCase()");
                String lowerCase2 = searchStr.toLowerCase();
                Intrinsics.checkNotNullExpressionValue(lowerCase2, "this as java.lang.String).toLowerCase()");
                if (StringsKt__StringsKt.contains$default((CharSequence) lowerCase, (CharSequence) lowerCase2, false, 2, (Object) null)) {
                    List<CategoryAndActivityModel> list = this.c;
                    Intrinsics.checkNotNull(list);
                    list.add(categoryAndActivityModel);
                }
            } else {
                List<CategoryAndActivityModel> list2 = this.c;
                Intrinsics.checkNotNull(list2);
                list2.add(categoryAndActivityModel);
            }
        }
        notifyDataSetChanged();
    }

    public final void setOriginalList(@NotNull List<CategoryAndActivityModel> list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        this.f2742a = list;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(@NotNull ActivityViewHolder holder, int i) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        List<CategoryAndActivityModel> list = this.c;
        Intrinsics.checkNotNull(list);
        holder.bindData(list.get(i), this.b);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    @NotNull
    public ActivityViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int i) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        return new ActivityViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_watch_activity, parent, false));
    }
}
