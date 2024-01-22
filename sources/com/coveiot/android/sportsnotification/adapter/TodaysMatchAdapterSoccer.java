package com.coveiot.android.sportsnotification.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.coveiot.android.sportsnotification.R;
import com.coveiot.android.sportsnotification.adapter.TodaysMatchAdapterSoccer;
import com.coveiot.android.sportsnotification.model.MatchListModel;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes7.dex */
public final class TodaysMatchAdapterSoccer extends RecyclerView.Adapter<MatchListViewHolder> {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public ArrayList<MatchListModel> f5840a;
    public int b;
    @Nullable
    public Context c;
    @NotNull
    public ItemClickListener d;
    @NotNull
    public List<MatchListModel> e;

    /* loaded from: classes7.dex */
    public interface ItemClickListener {
        void onItemClick(@NotNull MatchListModel matchListModel, int i);
    }

    /* loaded from: classes7.dex */
    public final class MatchListViewHolder extends RecyclerView.ViewHolder {
        @Nullable

        /* renamed from: a  reason: collision with root package name */
        public TextView f5841a;
        @Nullable
        public TextView b;
        @Nullable
        public ImageView c;
        @Nullable
        public ImageView d;
        @Nullable
        public ImageView e;
        @Nullable
        public TextView f;
        @Nullable
        public TextView g;
        @Nullable
        public CardView h;
        @Nullable
        public TextView i;
        @Nullable
        public ConstraintLayout j;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public MatchListViewHolder(@Nullable View view) {
            super(view);
            Intrinsics.checkNotNull(view);
            this.f5841a = (TextView) view.findViewById(R.id.teama_name);
            this.b = (TextView) view.findViewById(R.id.teamb_name);
            this.c = (ImageView) view.findViewById(R.id.img_watch_icon);
            this.d = (ImageView) view.findViewById(R.id.img_team_icon);
            this.e = (ImageView) view.findViewById(R.id.img_teamb_icon);
            this.f = (TextView) view.findViewById(R.id.txt_title);
            this.g = (TextView) view.findViewById(R.id.txt_date);
            this.h = (CardView) view.findViewById(R.id.card_view);
            this.i = (TextView) view.findViewById(R.id.match_league_name);
            this.j = (ConstraintLayout) view.findViewById(R.id.cl_soccer_team);
        }

        public static final void b(MatchListModel matchListModel, ItemClickListener listener, int i, View view) {
            Intrinsics.checkNotNullParameter(matchListModel, "$matchListModel");
            Intrinsics.checkNotNullParameter(listener, "$listener");
            Integer matchStatus = matchListModel.getMatchStatus();
            if (matchStatus != null && matchStatus.intValue() == 2) {
                return;
            }
            listener.onItemClick(matchListModel, i);
        }

        public final void bindData(@NotNull Context context, @NotNull final MatchListModel matchListModel, @NotNull final ItemClickListener listener, final int i) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(matchListModel, "matchListModel");
            Intrinsics.checkNotNullParameter(listener, "listener");
            TextView textView = this.f;
            if (textView != null) {
                textView.setText(matchListModel.getLeagueName());
            }
            TextView textView2 = this.f5841a;
            if (textView2 != null) {
                textView2.setText(matchListModel.getTeamAShortName());
            }
            TextView textView3 = this.b;
            if (textView3 != null) {
                textView3.setText(matchListModel.getTeamBShortName());
            }
            TextView textView4 = this.i;
            if (textView4 != null) {
                textView4.setText(matchListModel.getLeagueName());
            }
            Integer matchStatus = matchListModel.getMatchStatus();
            if (matchStatus != null && matchStatus.intValue() == 2) {
                TextView textView5 = this.g;
                if (textView5 != null) {
                    textView5.setText(context.getString(R.string.match_completed));
                }
                TextView textView6 = this.g;
                if (textView6 != null) {
                    textView6.setTextColor(context.getColor(R.color.white));
                }
                ((CardView) this.itemView.findViewById(R.id.card_view)).setAlpha(0.5f);
            } else {
                ((CardView) this.itemView.findViewById(R.id.card_view)).setAlpha(1.0f);
                TextView textView7 = this.g;
                if (textView7 != null) {
                    textView7.setTextColor(context.getColor(R.color.white));
                }
                if (i == TodaysMatchAdapterSoccer.this.getSelectedPosition()) {
                    TextView textView8 = this.g;
                    if (textView8 != null) {
                        textView8.setText(context.getString(R.string.view_match_on_watch));
                    }
                    TextView textView9 = this.g;
                    if (textView9 != null) {
                        textView9.setCompoundDrawablesWithIntrinsicBounds(context.getDrawable(R.drawable.watch_icon), (Drawable) null, (Drawable) null, (Drawable) null);
                    }
                } else {
                    TextView textView10 = this.g;
                    if (textView10 != null) {
                        textView10.setText(context.getString(R.string.starting_at) + ' ' + matchListModel.getFormattedTime());
                    }
                    TextView textView11 = this.g;
                    if (textView11 != null) {
                        textView11.setCompoundDrawables(null, null, null, null);
                    }
                }
            }
            if (this.d != null && matchListModel.getIconTeamA() != null) {
                RequestBuilder<Drawable> m30load = Glide.with(this.itemView.getContext()).m30load(matchListModel.getIconTeamA());
                ImageView imageView = this.d;
                Intrinsics.checkNotNull(imageView);
                m30load.into(imageView);
            }
            if (this.e != null && matchListModel.getIconTeamB() != null) {
                RequestBuilder<Drawable> m30load2 = Glide.with(this.itemView.getContext()).m30load(matchListModel.getIconTeamB());
                ImageView imageView2 = this.e;
                Intrinsics.checkNotNull(imageView2);
                m30load2.into(imageView2);
            }
            CardView cardView = this.h;
            if (cardView != null) {
                cardView.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.sportsnotification.adapter.d
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        TodaysMatchAdapterSoccer.MatchListViewHolder.b(MatchListModel.this, listener, i, view);
                    }
                });
            }
        }

        @Nullable
        public final CardView getCardView() {
            return this.h;
        }

        @Nullable
        public final TextView getDate() {
            return this.g;
        }

        @Nullable
        public final ImageView getImgTeamA() {
            return this.d;
        }

        @Nullable
        public final ImageView getImgTeamB() {
            return this.e;
        }

        @Nullable
        public final ImageView getImgWatchIcon() {
            return this.c;
        }

        @Nullable
        public final TextView getLeagueName() {
            return this.i;
        }

        @Nullable
        public final ConstraintLayout getMainLayout() {
            return this.j;
        }

        @Nullable
        public final TextView getTitle() {
            return this.f;
        }

        @Nullable
        public final TextView getTvTeamAName() {
            return this.f5841a;
        }

        @Nullable
        public final TextView getTvTeamBName() {
            return this.b;
        }

        public final void setCardView(@Nullable CardView cardView) {
            this.h = cardView;
        }

        public final void setDate(@Nullable TextView textView) {
            this.g = textView;
        }

        public final void setImgTeamA(@Nullable ImageView imageView) {
            this.d = imageView;
        }

        public final void setImgTeamB(@Nullable ImageView imageView) {
            this.e = imageView;
        }

        public final void setImgWatchIcon(@Nullable ImageView imageView) {
            this.c = imageView;
        }

        public final void setLeagueName(@Nullable TextView textView) {
            this.i = textView;
        }

        public final void setMainLayout(@Nullable ConstraintLayout constraintLayout) {
            this.j = constraintLayout;
        }

        public final void setTitle(@Nullable TextView textView) {
            this.f = textView;
        }

        public final void setTvTeamAName(@Nullable TextView textView) {
            this.f5841a = textView;
        }

        public final void setTvTeamBName(@Nullable TextView textView) {
            this.b = textView;
        }
    }

    public TodaysMatchAdapterSoccer(@Nullable Context context, @NotNull ArrayList<MatchListModel> originalList, @NotNull ItemClickListener listener) {
        Intrinsics.checkNotNullParameter(originalList, "originalList");
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.f5840a = originalList;
        this.b = -1;
        this.c = context;
        this.d = listener;
        this.e = originalList;
    }

    public final void filterMatchFormat(int i) {
        if (i == 0) {
            Intrinsics.areEqual(this.e, this.f5840a);
        }
        notifyDataSetChanged();
    }

    @Nullable
    public final Context getContext() {
        return this.c;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.e.size();
    }

    @NotNull
    public final ItemClickListener getListener() {
        return this.d;
    }

    public final int getSelectedPosition() {
        return this.b;
    }

    public final void notifyAdapter(int i) {
        this.b = i;
        notifyDataSetChanged();
    }

    public final void notifyData(@NotNull ArrayList<MatchListModel> matchList) {
        Intrinsics.checkNotNullParameter(matchList, "matchList");
        this.f5840a = matchList;
        this.e = matchList;
        notifyDataSetChanged();
    }

    public final void search(@NotNull String searchStr) {
        Intrinsics.checkNotNullParameter(searchStr, "searchStr");
        this.e = new ArrayList();
        Iterator<MatchListModel> it = this.f5840a.iterator();
        while (it.hasNext()) {
            MatchListModel item = it.next();
            if (searchStr != null && !Intrinsics.areEqual(searchStr, "")) {
                String title = item.getTitle();
                Intrinsics.checkNotNull(title);
                String lowerCase = title.toLowerCase();
                Intrinsics.checkNotNullExpressionValue(lowerCase, "this as java.lang.String).toLowerCase()");
                String lowerCase2 = searchStr.toLowerCase();
                Intrinsics.checkNotNullExpressionValue(lowerCase2, "this as java.lang.String).toLowerCase()");
                if (StringsKt__StringsKt.contains$default((CharSequence) lowerCase, (CharSequence) lowerCase2, false, 2, (Object) null)) {
                    List<MatchListModel> list = this.e;
                    Intrinsics.checkNotNull(list);
                    Intrinsics.checkNotNullExpressionValue(item, "item");
                    list.add(item);
                }
            } else {
                List<MatchListModel> list2 = this.e;
                Intrinsics.checkNotNull(list2);
                Intrinsics.checkNotNullExpressionValue(item, "item");
                list2.add(item);
            }
        }
        notifyDataSetChanged();
    }

    public final void setContext(@Nullable Context context) {
        this.c = context;
    }

    public final void setListener(@NotNull ItemClickListener itemClickListener) {
        Intrinsics.checkNotNullParameter(itemClickListener, "<set-?>");
        this.d = itemClickListener;
    }

    public final void setSelectedPosition(int i) {
        this.b = i;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(@NotNull MatchListViewHolder holder, int i) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        if (this.b == i) {
            ConstraintLayout mainLayout = holder.getMainLayout();
            if (mainLayout != null) {
                Context context = this.c;
                Intrinsics.checkNotNull(context);
                mainLayout.setBackground(context.getDrawable(R.drawable.sported_selected_state_img));
            }
        } else {
            ConstraintLayout mainLayout2 = holder.getMainLayout();
            if (mainLayout2 != null) {
                Context context2 = this.c;
                Intrinsics.checkNotNull(context2);
                mainLayout2.setBackground(context2.getDrawable(R.drawable.sported_unselected_state_img));
            }
        }
        Context context3 = this.c;
        Intrinsics.checkNotNull(context3);
        holder.bindData(context3, this.e.get(i), this.d, i);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    @NotNull
    public MatchListViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int i) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        return new MatchListViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_todays_match_soccer, parent, false));
    }
}
