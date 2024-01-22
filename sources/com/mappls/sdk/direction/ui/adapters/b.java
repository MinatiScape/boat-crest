package com.mappls.sdk.direction.ui.adapters;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.StyleRes;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;
import com.mappls.sdk.direction.ui.R;
import com.mappls.sdk.direction.ui.databinding.MapplsDirectionListHeaderBinding;
import com.mappls.sdk.direction.ui.databinding.MapplsDirectionStepAdapterBinding;
import com.mappls.sdk.direction.ui.model.StopModel;
import com.mappls.sdk.plugin.directions.DirectionsUtils;
import com.mappls.sdk.plugin.directions.view.ManeuverView;
import com.mappls.sdk.services.api.directions.models.LegStep;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
/* loaded from: classes11.dex */
public final class b extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    @StyleRes

    /* renamed from: a  reason: collision with root package name */
    public final int f12566a;
    public List<LegStep> b;
    public List<StopModel> c = new ArrayList();
    public Context d;
    public e e;

    /* loaded from: classes11.dex */
    public class a implements View.OnClickListener {
        public final /* synthetic */ RecyclerView.ViewHolder h;

        public a(RecyclerView.ViewHolder viewHolder) {
            this.h = viewHolder;
        }

        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            if (this.h.getAdapterPosition() == 0 || this.h.getAdapterPosition() == b.this.getItemCount() - 1) {
                return;
            }
            b.this.e.a(this.h.getAdapterPosition() - 1);
        }
    }

    /* renamed from: com.mappls.sdk.direction.ui.adapters.b$b  reason: collision with other inner class name */
    /* loaded from: classes11.dex */
    public static class C0613b extends RecyclerView.ViewHolder {

        /* renamed from: a  reason: collision with root package name */
        public MapplsDirectionListHeaderBinding f12567a;

        public C0613b(@NonNull MapplsDirectionListHeaderBinding mapplsDirectionListHeaderBinding) {
            super(mapplsDirectionListHeaderBinding.getRoot());
            this.f12567a = mapplsDirectionListHeaderBinding;
            mapplsDirectionListHeaderBinding.imageIcon.setImageDrawable(ContextCompat.getDrawable(mapplsDirectionListHeaderBinding.getRoot().getContext(), R.drawable.mappls_direction_baseline_stop));
        }
    }

    /* loaded from: classes11.dex */
    public static class c extends RecyclerView.ViewHolder {

        /* renamed from: a  reason: collision with root package name */
        public MapplsDirectionListHeaderBinding f12568a;

        public c(@NonNull MapplsDirectionListHeaderBinding mapplsDirectionListHeaderBinding) {
            super(mapplsDirectionListHeaderBinding.getRoot());
            this.f12568a = mapplsDirectionListHeaderBinding;
            mapplsDirectionListHeaderBinding.imageIcon.setImageDrawable(ContextCompat.getDrawable(mapplsDirectionListHeaderBinding.getRoot().getContext(), R.drawable.mappls_direction_play_arrow));
            mapplsDirectionListHeaderBinding.directionListDesc.setText(mapplsDirectionListHeaderBinding.getRoot().getContext().getResources().getString(R.string.mappls_direction_starting_text));
        }
    }

    /* loaded from: classes11.dex */
    public static class d extends RecyclerView.ViewHolder {

        /* renamed from: a  reason: collision with root package name */
        public MapplsDirectionStepAdapterBinding f12569a;

        public d(@NonNull MapplsDirectionStepAdapterBinding mapplsDirectionStepAdapterBinding) {
            super(mapplsDirectionStepAdapterBinding.getRoot());
            this.f12569a = mapplsDirectionStepAdapterBinding;
        }
    }

    /* loaded from: classes11.dex */
    public interface e {
        void a(int i);
    }

    public b(Context context, @StyleRes int i) {
        this.d = context;
        this.f12566a = i;
        setHasStableIds(true);
    }

    public final void a(e eVar) {
        this.e = eVar;
    }

    public final void a(List<LegStep> list, List<StopModel> list2) {
        if (list == null) {
            list = new ArrayList<>();
        }
        this.b = list;
        if (list2 == null) {
            list2 = new ArrayList<>();
        }
        this.c = list2;
        notifyDataSetChanged();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public final int getItemCount() {
        if (this.c.size() > 0) {
            List<LegStep> list = this.b;
            if (list == null) {
                return 1;
            }
            return 1 + list.size();
        }
        List<LegStep> list2 = this.b;
        if (list2 == null) {
            return 0;
        }
        return list2.size();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public final long getItemId(int i) {
        return i;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public final int getItemViewType(int i) {
        if (i == 0) {
            return 0;
        }
        return i == getItemCount() - 1 ? 2 : 1;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public final void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        TextView textView;
        String instruction;
        TextView textView2;
        TextView textView3;
        String textInstructions;
        ManeuverView maneuverView;
        float f;
        if (getItemViewType(viewHolder.getBindingAdapterPosition()) != 1) {
            String str = "Your Current Location";
            if (getItemViewType(viewHolder.getAdapterPosition()) == 0) {
                if (!(viewHolder instanceof c)) {
                    return;
                }
                c cVar = (c) viewHolder;
                Resources.Theme theme = this.d.getTheme();
                int[] iArr = R.styleable.mappls_direction;
                cVar.f12568a.imageIconContainer.setBackgroundResource(theme.obtainStyledAttributes(iArr).getResourceId(R.styleable.mappls_direction_directions_mv_bg_drawable, R.drawable.mappls_direction_circle_black));
                TypedArray obtainStyledAttributes = this.d.getTheme().obtainStyledAttributes(iArr);
                TextView textView4 = cVar.f12568a.directionListPlaceName;
                int i2 = R.styleable.mappls_direction_direction_list_header_place_name_tv_color;
                Context context = this.d;
                int i3 = R.color.mappls_directions_steps_text;
                textView4.setTextColor(obtainStyledAttributes.getColor(i2, ContextCompat.getColor(context, i3)));
                cVar.f12568a.directionListDesc.setTextColor(this.d.getTheme().obtainStyledAttributes(iArr).getColor(R.styleable.mappls_direction_direction_list_header_distance_tv_color, ContextCompat.getColor(this.d, i3)));
                if (this.c.size() <= 0) {
                    return;
                }
                StopModel stopModel = this.c.get(0);
                int locationType = stopModel.getLocationType();
                int i4 = StopModel.TYPE_CURRENT_LOCATION;
                textView = cVar.f12568a.directionListPlaceName;
                if (locationType == i4) {
                    textView.setText("Your Current Location");
                    return;
                }
                instruction = stopModel.getPlaceName();
            } else if (getItemViewType(viewHolder.getAdapterPosition()) != 2 || !(viewHolder instanceof C0613b)) {
                return;
            } else {
                C0613b c0613b = (C0613b) viewHolder;
                Resources.Theme theme2 = this.d.getTheme();
                int[] iArr2 = R.styleable.mappls_direction;
                c0613b.f12567a.imageIconContainer.setBackgroundResource(theme2.obtainStyledAttributes(iArr2).getResourceId(R.styleable.mappls_direction_directions_mv_bg_drawable, R.drawable.mappls_direction_circle_black));
                TypedArray obtainStyledAttributes2 = this.d.getTheme().obtainStyledAttributes(iArr2);
                TextView textView5 = c0613b.f12567a.directionListPlaceName;
                int i5 = R.styleable.mappls_direction_direction_list_header_place_name_tv_color;
                Context context2 = this.d;
                int i6 = R.color.mappls_directions_steps_text;
                textView5.setTextColor(obtainStyledAttributes2.getColor(i5, ContextCompat.getColor(context2, i6)));
                c0613b.f12567a.directionListDesc.setTextColor(this.d.getTheme().obtainStyledAttributes(iArr2).getColor(R.styleable.mappls_direction_direction_list_header_distance_tv_color, ContextCompat.getColor(this.d, i6)));
                if (this.c.size() > 0) {
                    List<StopModel> list = this.c;
                    StopModel stopModel2 = list.get(list.size() - 1);
                    if (stopModel2.getLocationType() == StopModel.TYPE_CURRENT_LOCATION) {
                        textView2 = c0613b.f12567a.directionListPlaceName;
                    } else if (stopModel2.getLocationType() == StopModel.TYPE_BLANK) {
                        TextView textView6 = c0613b.f12567a.directionListPlaceName;
                        List<StopModel> list2 = this.c;
                        textView6.setText(list2.get(list2.size() - 2).getPlaceName());
                    } else {
                        textView2 = c0613b.f12567a.directionListPlaceName;
                        str = stopModel2.getPlaceName();
                    }
                    textView2.setText(str);
                }
                if (this.b.get(c0613b.getBindingAdapterPosition() - 1).maneuver().instruction() == null) {
                    c0613b.f12567a.directionListDesc.setText(DirectionsUtils.getTextInstructions(this.b.get(viewHolder.getBindingAdapterPosition() - 1)));
                    return;
                } else {
                    textView = c0613b.f12567a.directionListDesc;
                    instruction = this.b.get(c0613b.getBindingAdapterPosition() - 1).maneuver().instruction();
                }
            }
        } else if (!(viewHolder instanceof d)) {
            return;
        } else {
            d dVar = (d) viewHolder;
            LegStep legStep = this.b.get(viewHolder.getBindingAdapterPosition() - 1);
            if (legStep.maneuver().instruction() != null) {
                textView3 = dVar.f12569a.stepsText;
                textInstructions = legStep.maneuver().instruction();
            } else {
                textView3 = dVar.f12569a.stepsText;
                textInstructions = DirectionsUtils.getTextInstructions(legStep);
            }
            textView3.setText(textInstructions);
            ManeuverView maneuverView2 = dVar.f12569a.navigateIcon;
            String type = legStep.maneuver().type();
            Objects.requireNonNull(type);
            maneuverView2.setManeuverTypeAndModifier(type, legStep.maneuver().modifier());
            Resources.Theme theme3 = this.d.getTheme();
            int[] iArr3 = R.styleable.mappls_direction;
            dVar.f12569a.stepsText.setTextColor(theme3.obtainStyledAttributes(iArr3).getColor(R.styleable.mappls_direction_directions_list_nextstep_distance_tv_color, ContextCompat.getColor(this.d, R.color.mappls_directions_steps_text)));
            dVar.f12569a.distanceText.setTextColor(this.d.getTheme().obtainStyledAttributes(iArr3).getColor(R.styleable.mappls_direction_directions_list_nextstep_tv_color, ContextCompat.getColor(this.d, R.color.mappls_directions_distance_text_Color)));
            TypedArray obtainStyledAttributes3 = this.d.getTheme().obtainStyledAttributes(iArr3);
            ManeuverView maneuverView3 = dVar.f12569a.navigateIcon;
            int i7 = R.styleable.mappls_direction_directions_mv_primary_color;
            Context context3 = this.d;
            int i8 = R.color.mappls_direction_colorBlack;
            maneuverView3.setPrimaryColor(obtainStyledAttributes3.getColor(i7, ContextCompat.getColor(context3, i8)));
            dVar.f12569a.navigateIcon.setSecondaryColor(this.d.getTheme().obtainStyledAttributes(iArr3).getColor(R.styleable.mappls_direction_direction_mv_secondary_color, ContextCompat.getColor(this.d, i8)));
            dVar.f12569a.navigateIcon.setSecondaryColor(this.d.getTheme().obtainStyledAttributes(iArr3).getColor(R.styleable.mappls_direction_directions_mv_bg_color, ContextCompat.getColor(this.d, i8)));
            dVar.f12569a.maneuverViewContainer.setBackgroundResource(this.d.getTheme().obtainStyledAttributes(iArr3).getResourceId(R.styleable.mappls_direction_directions_mv_bg_drawable, R.drawable.mappls_direction_circle_black));
            String type2 = legStep.maneuver().type();
            if (type2 != null && (type2.equalsIgnoreCase("roundabout") || type2.equalsIgnoreCase("rotary"))) {
                if (legStep.maneuver().degree() != null) {
                    maneuverView = dVar.f12569a.navigateIcon;
                    f = legStep.maneuver().degree().floatValue();
                } else {
                    maneuverView = dVar.f12569a.navigateIcon;
                    f = 180.0f;
                }
                maneuverView.setRoundaboutAngle(f);
            }
            dVar.f12569a.mapplsDirectionSteplistContainer.setOnClickListener(new a(viewHolder));
            textView = dVar.f12569a.distanceText;
            instruction = String.format("GO  %s", com.mappls.sdk.direction.ui.common.d.b(legStep.distance()));
        }
        textView.setText(instruction);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    @NonNull
    public final RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        this.d.setTheme(this.f12566a);
        return i == 0 ? new c((MapplsDirectionListHeaderBinding) DataBindingUtil.inflate(LayoutInflater.from(viewGroup.getContext()), R.layout.mappls_direction_list_header, viewGroup, false)) : i == 2 ? new C0613b((MapplsDirectionListHeaderBinding) DataBindingUtil.inflate(LayoutInflater.from(viewGroup.getContext()), R.layout.mappls_direction_list_header, viewGroup, false)) : new d((MapplsDirectionStepAdapterBinding) DataBindingUtil.inflate(LayoutInflater.from(viewGroup.getContext()), R.layout.mappls_direction_step_adapter, viewGroup, false));
    }
}
