package com.coveiot.android.leonardo.more.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.SpannableStringBuilder;
import android.text.style.ImageSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.coveiot.android.boat.R;
import com.coveiot.android.leonardo.model.MyWatchFeatureModel;
import com.coveiot.android.leonardo.model.MyWatchFeatureType;
import com.coveiot.android.leonardo.more.adapters.MyWatchFeaturesAdapter;
import com.coveiot.android.theme.databinding.ListItemDeviceFeatureMyWatchLayoutBinding;
import java.util.ArrayList;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt___StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes5.dex */
public final class MyWatchFeaturesAdapter extends RecyclerView.Adapter<ViewHolder> {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public Context f5062a;
    @NotNull
    public List<List<MyWatchFeatureModel>> b;
    @Nullable
    public OnFeatureClickListener c;

    /* loaded from: classes5.dex */
    public interface OnFeatureClickListener {
        void onFeatureClicked(@NotNull MyWatchFeatureType myWatchFeatureType);
    }

    /* loaded from: classes5.dex */
    public final class ViewHolder extends RecyclerView.ViewHolder {
        @NotNull

        /* renamed from: a  reason: collision with root package name */
        public final ListItemDeviceFeatureMyWatchLayoutBinding f5063a;
        public final /* synthetic */ MyWatchFeaturesAdapter b;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public ViewHolder(@NotNull MyWatchFeaturesAdapter myWatchFeaturesAdapter, ListItemDeviceFeatureMyWatchLayoutBinding binding) {
            super(binding.getRoot());
            Intrinsics.checkNotNullParameter(binding, "binding");
            this.b = myWatchFeaturesAdapter;
            this.f5063a = binding;
        }

        public static final void d(MyWatchFeaturesAdapter this$0, List myWatchFeatures, View view) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            Intrinsics.checkNotNullParameter(myWatchFeatures, "$myWatchFeatures");
            OnFeatureClickListener myFeatureClickedListener = this$0.getMyFeatureClickedListener();
            if (myFeatureClickedListener != null) {
                MyWatchFeatureType myWatchFeatureType = ((MyWatchFeatureModel) myWatchFeatures.get(0)).getMyWatchFeatureType();
                Intrinsics.checkNotNull(myWatchFeatureType);
                myFeatureClickedListener.onFeatureClicked(myWatchFeatureType);
            }
        }

        public static final void e(MyWatchFeaturesAdapter this$0, List myWatchFeatures, View view) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            Intrinsics.checkNotNullParameter(myWatchFeatures, "$myWatchFeatures");
            OnFeatureClickListener myFeatureClickedListener = this$0.getMyFeatureClickedListener();
            if (myFeatureClickedListener != null) {
                MyWatchFeatureType myWatchFeatureType = ((MyWatchFeatureModel) myWatchFeatures.get(0)).getMyWatchFeatureType();
                Intrinsics.checkNotNull(myWatchFeatureType);
                myFeatureClickedListener.onFeatureClicked(myWatchFeatureType);
            }
        }

        public static final void f(MyWatchFeaturesAdapter this$0, List myWatchFeatures, View view) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            Intrinsics.checkNotNullParameter(myWatchFeatures, "$myWatchFeatures");
            OnFeatureClickListener myFeatureClickedListener = this$0.getMyFeatureClickedListener();
            if (myFeatureClickedListener != null) {
                MyWatchFeatureType myWatchFeatureType = ((MyWatchFeatureModel) myWatchFeatures.get(1)).getMyWatchFeatureType();
                Intrinsics.checkNotNull(myWatchFeatureType);
                myFeatureClickedListener.onFeatureClicked(myWatchFeatureType);
            }
        }

        @SuppressLint({"SetTextI18n"})
        public final void bind(@NotNull final List<MyWatchFeatureModel> myWatchFeatures, int i) {
            Intrinsics.checkNotNullParameter(myWatchFeatures, "myWatchFeatures");
            ListItemDeviceFeatureMyWatchLayoutBinding listItemDeviceFeatureMyWatchLayoutBinding = this.f5063a;
            final MyWatchFeaturesAdapter myWatchFeaturesAdapter = this.b;
            if (myWatchFeatures.isEmpty()) {
                return;
            }
            if (myWatchFeatures.size() == 1) {
                if (myWatchFeatures.get(0).getShow()) {
                    listItemDeviceFeatureMyWatchLayoutBinding.clEven.setVisibility(8);
                    listItemDeviceFeatureMyWatchLayoutBinding.clOdd.setVisibility(0);
                    ImageView imageView = listItemDeviceFeatureMyWatchLayoutBinding.oddLayout.ivIcon;
                    Context context = myWatchFeaturesAdapter.getContext();
                    MyWatchFeatureType myWatchFeatureType = myWatchFeatures.get(0).getMyWatchFeatureType();
                    Intrinsics.checkNotNull(myWatchFeatureType);
                    imageView.setImageDrawable(myWatchFeaturesAdapter.getMyWatchFeatureDrawable(context, myWatchFeatureType.name()));
                    TextView textView = listItemDeviceFeatureMyWatchLayoutBinding.oddLayout.tvHeader;
                    Context context2 = myWatchFeaturesAdapter.getContext();
                    MyWatchFeatureType myWatchFeatureType2 = myWatchFeatures.get(0).getMyWatchFeatureType();
                    Intrinsics.checkNotNull(myWatchFeatureType2);
                    textView.setText(myWatchFeaturesAdapter.getMyWatchFeatureTitle(context2, myWatchFeatureType2.name()));
                    TextView textView2 = listItemDeviceFeatureMyWatchLayoutBinding.oddLayout.tvInfo;
                    Context context3 = myWatchFeaturesAdapter.getContext();
                    MyWatchFeatureType myWatchFeatureType3 = myWatchFeatures.get(0).getMyWatchFeatureType();
                    Intrinsics.checkNotNull(myWatchFeatureType3);
                    textView2.setText(myWatchFeaturesAdapter.getMyWatchFeatureInfoText(context3, myWatchFeatureType3.name()));
                    ConstraintLayout constraintLayout = listItemDeviceFeatureMyWatchLayoutBinding.oddLayout.cardBackground;
                    Context context4 = myWatchFeaturesAdapter.getContext();
                    MyWatchFeatureType myWatchFeatureType4 = myWatchFeatures.get(0).getMyWatchFeatureType();
                    Intrinsics.checkNotNull(myWatchFeatureType4);
                    constraintLayout.setBackground(myWatchFeaturesAdapter.getMyWatchFeatureBackgroundDrawable(context4, myWatchFeatureType4.name()));
                    listItemDeviceFeatureMyWatchLayoutBinding.clOdd.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.adapters.r
                        @Override // android.view.View.OnClickListener
                        public final void onClick(View view) {
                            MyWatchFeaturesAdapter.ViewHolder.d(MyWatchFeaturesAdapter.this, myWatchFeatures, view);
                        }
                    });
                    return;
                }
                listItemDeviceFeatureMyWatchLayoutBinding.clEven.setVisibility(8);
                listItemDeviceFeatureMyWatchLayoutBinding.clOdd.setVisibility(8);
            } else if (myWatchFeatures.get(0).getShow() && myWatchFeatures.get(1).getShow()) {
                listItemDeviceFeatureMyWatchLayoutBinding.clEven.setVisibility(0);
                listItemDeviceFeatureMyWatchLayoutBinding.clOdd.setVisibility(8);
                View findViewById = listItemDeviceFeatureMyWatchLayoutBinding.evenLayout1.findViewById(R.id.ivIcon);
                Intrinsics.checkNotNull(findViewById, "null cannot be cast to non-null type android.widget.ImageView");
                Context context5 = myWatchFeaturesAdapter.getContext();
                MyWatchFeatureType myWatchFeatureType5 = myWatchFeatures.get(0).getMyWatchFeatureType();
                Intrinsics.checkNotNull(myWatchFeatureType5);
                ((ImageView) findViewById).setImageDrawable(myWatchFeaturesAdapter.getMyWatchFeatureDrawable(context5, myWatchFeatureType5.name()));
                View findViewById2 = listItemDeviceFeatureMyWatchLayoutBinding.evenLayout1.findViewById(R.id.tvHeader);
                Intrinsics.checkNotNull(findViewById2, "null cannot be cast to non-null type android.widget.TextView");
                Context context6 = myWatchFeaturesAdapter.getContext();
                MyWatchFeatureType myWatchFeatureType6 = myWatchFeatures.get(0).getMyWatchFeatureType();
                Intrinsics.checkNotNull(myWatchFeatureType6);
                String myWatchFeatureTitle = myWatchFeaturesAdapter.getMyWatchFeatureTitle(context6, myWatchFeatureType6.name());
                Intrinsics.checkNotNull(myWatchFeatureTitle);
                ((TextView) findViewById2).setText(myWatchFeaturesAdapter.addImageToEndOfTheString(myWatchFeatureTitle, R.drawable.ic_right_arrow_white, myWatchFeaturesAdapter.getContext()));
                View findViewById3 = listItemDeviceFeatureMyWatchLayoutBinding.evenLayout1.findViewById(R.id.clMainSmallCard);
                Intrinsics.checkNotNull(findViewById3, "null cannot be cast to non-null type androidx.constraintlayout.widget.ConstraintLayout");
                Context context7 = myWatchFeaturesAdapter.getContext();
                MyWatchFeatureType myWatchFeatureType7 = myWatchFeatures.get(0).getMyWatchFeatureType();
                Intrinsics.checkNotNull(myWatchFeatureType7);
                ((ConstraintLayout) findViewById3).setBackground(myWatchFeaturesAdapter.getMyWatchFeatureSmallBackgroundDrawable(context7, myWatchFeatureType7.name()));
                View findViewById4 = listItemDeviceFeatureMyWatchLayoutBinding.evenLayout2.findViewById(R.id.ivIcon);
                Intrinsics.checkNotNull(findViewById4, "null cannot be cast to non-null type android.widget.ImageView");
                Context context8 = myWatchFeaturesAdapter.getContext();
                MyWatchFeatureType myWatchFeatureType8 = myWatchFeatures.get(1).getMyWatchFeatureType();
                Intrinsics.checkNotNull(myWatchFeatureType8);
                ((ImageView) findViewById4).setImageDrawable(myWatchFeaturesAdapter.getMyWatchFeatureDrawable(context8, myWatchFeatureType8.name()));
                View findViewById5 = listItemDeviceFeatureMyWatchLayoutBinding.evenLayout2.findViewById(R.id.tvHeader);
                Intrinsics.checkNotNull(findViewById5, "null cannot be cast to non-null type android.widget.TextView");
                Context context9 = myWatchFeaturesAdapter.getContext();
                MyWatchFeatureType myWatchFeatureType9 = myWatchFeatures.get(1).getMyWatchFeatureType();
                Intrinsics.checkNotNull(myWatchFeatureType9);
                String myWatchFeatureTitle2 = myWatchFeaturesAdapter.getMyWatchFeatureTitle(context9, myWatchFeatureType9.name());
                Intrinsics.checkNotNull(myWatchFeatureTitle2);
                ((TextView) findViewById5).setText(myWatchFeaturesAdapter.addImageToEndOfTheString(myWatchFeatureTitle2, R.drawable.ic_right_arrow_white, myWatchFeaturesAdapter.getContext()));
                View findViewById6 = listItemDeviceFeatureMyWatchLayoutBinding.evenLayout2.findViewById(R.id.clMainSmallCard);
                Intrinsics.checkNotNull(findViewById6, "null cannot be cast to non-null type androidx.constraintlayout.widget.ConstraintLayout");
                Context context10 = myWatchFeaturesAdapter.getContext();
                MyWatchFeatureType myWatchFeatureType10 = myWatchFeatures.get(1).getMyWatchFeatureType();
                Intrinsics.checkNotNull(myWatchFeatureType10);
                ((ConstraintLayout) findViewById6).setBackground(myWatchFeaturesAdapter.getMyWatchFeatureSmallBackgroundDrawable(context10, myWatchFeatureType10.name()));
                listItemDeviceFeatureMyWatchLayoutBinding.evenLayout1.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.adapters.s
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        MyWatchFeaturesAdapter.ViewHolder.e(MyWatchFeaturesAdapter.this, myWatchFeatures, view);
                    }
                });
                listItemDeviceFeatureMyWatchLayoutBinding.evenLayout2.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.adapters.q
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        MyWatchFeaturesAdapter.ViewHolder.f(MyWatchFeaturesAdapter.this, myWatchFeatures, view);
                    }
                });
            } else {
                listItemDeviceFeatureMyWatchLayoutBinding.clEven.setVisibility(8);
                listItemDeviceFeatureMyWatchLayoutBinding.clOdd.setVisibility(8);
            }
        }
    }

    public MyWatchFeaturesAdapter(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.f5062a = context;
        this.b = new ArrayList();
    }

    @NotNull
    public final SpannableStringBuilder addImageToEndOfTheString(@NotNull String text, int i, @NotNull Context context) {
        Intrinsics.checkNotNullParameter(text, "text");
        Intrinsics.checkNotNullParameter(context, "context");
        String str = text + StringsKt___StringsKt.last(text);
        Drawable drawable = ContextCompat.getDrawable(context, i);
        Intrinsics.checkNotNull(drawable);
        drawable.setBounds(14, 10, 64, 45);
        ImageSpan imageSpan = new ImageSpan(drawable, 1);
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(str);
        spannableStringBuilder.setSpan(imageSpan, str.length() - 1, str.length(), 34);
        return spannableStringBuilder;
    }

    @NotNull
    public final Context getContext() {
        return this.f5062a;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.b.size();
    }

    @Nullable
    public final OnFeatureClickListener getMyFeatureClickedListener() {
        return this.c;
    }

    @Nullable
    public final Drawable getMyWatchFeatureBackgroundDrawable(@NotNull Context context, @NotNull String watchFeatureType) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(watchFeatureType, "watchFeatureType");
        switch (watchFeatureType.hashCode()) {
            case -1852619265:
                if (watchFeatureType.equals("SENSAI")) {
                    return context.getDrawable(R.drawable.rounded_top_left_red_border_background_rectangle);
                }
                break;
            case -1842431105:
                if (watchFeatureType.equals("SPORTS")) {
                    return context.getDrawable(R.drawable.rounded_top_left_red_border_background_rectangle);
                }
                break;
            case -1155766972:
                if (watchFeatureType.equals("TAP_AND_PAY")) {
                    return context.getDrawable(R.drawable.rounded_top_left_yellow_border_background_rectangle);
                }
                break;
            case -543018624:
                if (watchFeatureType.equals("CUSTOM_REMINDER")) {
                    return context.getDrawable(R.drawable.rounded_top_left_red_border_background_rectangle);
                }
                break;
            case 62362083:
                if (watchFeatureType.equals("ALEXA")) {
                    return context.getDrawable(R.drawable.rounded_top_left_blue_border_background_rectangle);
                }
                break;
            case 328789393:
                if (watchFeatureType.equals("FEMALE_WELLNESS_TRACKER")) {
                    return context.getDrawable(R.drawable.rounded_top_left_red_border_background_rectangle);
                }
                break;
            case 1933100098:
                if (watchFeatureType.equals("ALARMS")) {
                    return context.getDrawable(R.drawable.rounded_top_left_blue_border_background_rectangle);
                }
                break;
        }
        return context.getDrawable(R.drawable.rounded_top_left_red_border_background_rectangle);
    }

    @Nullable
    public final Drawable getMyWatchFeatureDrawable(@NotNull Context context, @NotNull String watchFeatureType) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(watchFeatureType, "watchFeatureType");
        switch (watchFeatureType.hashCode()) {
            case -1852619265:
                if (watchFeatureType.equals("SENSAI")) {
                    return context.getDrawable(2131232413);
                }
                break;
            case -1842431105:
                if (watchFeatureType.equals("SPORTS")) {
                    return context.getDrawable(2131232490);
                }
                break;
            case -1155766972:
                if (watchFeatureType.equals("TAP_AND_PAY")) {
                    return context.getDrawable(2131232585);
                }
                break;
            case -543018624:
                if (watchFeatureType.equals("CUSTOM_REMINDER")) {
                    return context.getDrawable(2131232264);
                }
                break;
            case 62362083:
                if (watchFeatureType.equals("ALEXA")) {
                    return context.getDrawable(2131232423);
                }
                break;
            case 328789393:
                if (watchFeatureType.equals("FEMALE_WELLNESS_TRACKER")) {
                    return context.getDrawable(2131232265);
                }
                break;
            case 1933100098:
                if (watchFeatureType.equals("ALARMS")) {
                    return context.getDrawable(2131232263);
                }
                break;
        }
        return context.getDrawable(2131232263);
    }

    @Nullable
    public final String getMyWatchFeatureInfoText(@NotNull Context context, @NotNull String watchFeatureType) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(watchFeatureType, "watchFeatureType");
        switch (watchFeatureType.hashCode()) {
            case -1852619265:
                return watchFeatureType.equals("SENSAI") ? context.getString(R.string.train_and_ace_your_cricket_) : "";
            case -1842431105:
                return !watchFeatureType.equals("SPORTS") ? "" : context.getString(R.string.get_score_updates_on_your_watch_);
            case -1155766972:
                return !watchFeatureType.equals("TAP_AND_PAY") ? "" : context.getString(R.string.connect_your_nfc_strap_to_make_payment_);
            case -543018624:
                return !watchFeatureType.equals("CUSTOM_REMINDER") ? "" : context.getString(R.string.set_personalised_reminders_);
            case 62362083:
                return !watchFeatureType.equals("ALEXA") ? "" : context.getString(R.string.link_your_amazon);
            case 328789393:
                return !watchFeatureType.equals("FEMALE_WELLNESS_TRACKER") ? "" : context.getString(R.string.track_your_menstrual_);
            case 1933100098:
                return !watchFeatureType.equals("ALARMS") ? "" : context.getString(R.string.set_alarms_in_your_);
            default:
                return "";
        }
    }

    @Nullable
    public final Drawable getMyWatchFeatureSmallBackgroundDrawable(@NotNull Context context, @NotNull String watchFeatureType) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(watchFeatureType, "watchFeatureType");
        switch (watchFeatureType.hashCode()) {
            case -1852619265:
                if (watchFeatureType.equals("SENSAI")) {
                    return context.getDrawable(R.drawable.rounded_top_left_red_border_square_background);
                }
                break;
            case -1842431105:
                if (watchFeatureType.equals("SPORTS")) {
                    return context.getDrawable(R.drawable.rounded_top_left_red_border_square_background);
                }
                break;
            case -1155766972:
                if (watchFeatureType.equals("TAP_AND_PAY")) {
                    return context.getDrawable(R.drawable.rounded_top_left_yellow_border_square_background);
                }
                break;
            case -543018624:
                if (watchFeatureType.equals("CUSTOM_REMINDER")) {
                    return context.getDrawable(R.drawable.rounded_top_left_red_border_square_background);
                }
                break;
            case 62362083:
                if (watchFeatureType.equals("ALEXA")) {
                    return context.getDrawable(R.drawable.rounded_top_left_blue_border_square_background);
                }
                break;
            case 328789393:
                if (watchFeatureType.equals("FEMALE_WELLNESS_TRACKER")) {
                    return context.getDrawable(R.drawable.rounded_top_left_red_border_square_background);
                }
                break;
            case 1933100098:
                if (watchFeatureType.equals("ALARMS")) {
                    return context.getDrawable(R.drawable.rounded_top_left_blue_border_square_background);
                }
                break;
        }
        return context.getDrawable(R.drawable.rounded_top_left_red_border_square_background);
    }

    @Nullable
    public final String getMyWatchFeatureTitle(@NotNull Context context, @NotNull String watchFeatureType) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(watchFeatureType, "watchFeatureType");
        switch (watchFeatureType.hashCode()) {
            case -1852619265:
                return watchFeatureType.equals("SENSAI") ? context.getString(R.string.sens_ai) : "";
            case -1842431105:
                return !watchFeatureType.equals("SPORTS") ? "" : context.getString(R.string.sport_scores);
            case -1155766972:
                return !watchFeatureType.equals("TAP_AND_PAY") ? "" : context.getString(R.string.tap_and_pay);
            case -543018624:
                return !watchFeatureType.equals("CUSTOM_REMINDER") ? "" : context.getString(R.string.custome_reminder);
            case 62362083:
                return !watchFeatureType.equals("ALEXA") ? "" : context.getString(R.string.alexa_connect);
            case 328789393:
                return !watchFeatureType.equals("FEMALE_WELLNESS_TRACKER") ? "" : context.getString(R.string.female_wellness_tracker);
            case 1933100098:
                return !watchFeatureType.equals("ALARMS") ? "" : context.getString(R.string.alarms);
            default:
                return "";
        }
    }

    public final void setContext(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "<set-?>");
        this.f5062a = context;
    }

    public final void setMyFeatureClickedListener(@Nullable OnFeatureClickListener onFeatureClickListener) {
        this.c = onFeatureClickListener;
    }

    @SuppressLint({"NotifyDataSetChanged"})
    public final void setMyWatchFeatureList(@NotNull List<List<MyWatchFeatureModel>> featureData) {
        Intrinsics.checkNotNullParameter(featureData, "featureData");
        this.b = featureData;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(@NotNull ViewHolder holder, int i) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        holder.bind(this.b.get(i), i);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    @NotNull
    public ViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int i) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        ListItemDeviceFeatureMyWatchLayoutBinding inflate = ListItemDeviceFeatureMyWatchLayoutBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(\n               …      false\n            )");
        return new ViewHolder(this, inflate);
    }
}
