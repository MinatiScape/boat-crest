package com.mappls.sdk.navigation.ui.views.turnlane;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.PointF;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.clevertap.android.sdk.Constants;
import com.mappls.sdk.services.api.directions.models.IntersectionLanes;
import java.util.Iterator;
/* loaded from: classes11.dex */
public class TurnLaneView extends View {
    private TurnLaneViewData drawData;
    private boolean isValid;
    private int primaryColor;
    private int secondaryColor;
    private PointF size;

    public TurnLaneView(Context context) {
        super(context);
    }

    public TurnLaneView(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public TurnLaneView(Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    private void initManeuverColor() {
        this.primaryColor = Color.parseColor(Constants.WHITE);
        this.secondaryColor = Color.parseColor("#AAFFFFFF");
    }

    @Override // android.view.View
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (isInEditMode()) {
            LanesStyleKit.drawLaneStraight(canvas, this.primaryColor, this.size);
            return;
        }
        TurnLaneViewData turnLaneViewData = this.drawData;
        if (turnLaneViewData == null || TextUtils.isEmpty(turnLaneViewData.b())) {
            return;
        }
        String b = this.drawData.b();
        b.getClass();
        char c = 65535;
        switch (b.hashCode()) {
            case -1838029873:
                if (b.equals("draw_lane_straight_right")) {
                    c = 0;
                    break;
                }
                break;
            case -1315366105:
                if (b.equals("draw_lane_right_only")) {
                    c = 1;
                    break;
                }
                break;
            case -1167754343:
                if (b.equals("draw_lane_straight_only")) {
                    c = 2;
                    break;
                }
                break;
            case 665526424:
                if (b.equals("draw_lane_slight_right")) {
                    c = 3;
                    break;
                }
                break;
            case 1235586020:
                if (b.equals("draw_lane_right")) {
                    c = 4;
                    break;
                }
                break;
            case 1238698042:
                if (b.equals("draw_lane_uturn")) {
                    c = 5;
                    break;
                }
                break;
        }
        int i = this.primaryColor;
        switch (c) {
            case 0:
                LanesStyleKit.drawLaneStraightRight(canvas, i, this.size);
                break;
            case 1:
                LanesStyleKit.drawLaneRightOnly(canvas, i, this.secondaryColor, this.size);
                break;
            case 2:
                LanesStyleKit.drawLaneStraightOnly(canvas, i, this.secondaryColor, this.size);
                break;
            case 3:
                LanesStyleKit.drawLaneSlightRight(canvas, i, this.size);
                break;
            case 4:
                LanesStyleKit.drawLaneRight(canvas, i, this.size);
                break;
            case 5:
                LanesStyleKit.drawLaneUturn(canvas, i, this.size);
                break;
            default:
                LanesStyleKit.drawLaneStraight(canvas, i, this.size);
                break;
        }
        setAlpha(!this.isValid ? 0.4f : 1.0f);
        setScaleX(this.drawData.e() ? -1.0f : 1.0f);
    }

    @Override // android.view.View
    public void onFinishInflate() {
        super.onFinishInflate();
        setLayerType(1, null);
        initManeuverColor();
    }

    @Override // android.view.View
    public void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        if (this.size == null) {
            this.size = new PointF(getMeasuredWidth(), getMeasuredHeight());
        }
    }

    public void updateLaneView(@NonNull IntersectionLanes intersectionLanes, @NonNull String str) {
        StringBuilder sb = new StringBuilder();
        Iterator<String> it = intersectionLanes.indications().iterator();
        if (it.hasNext()) {
            sb.append(it.next());
        }
        this.drawData = new TurnLaneViewData(sb.toString(), str);
        this.isValid = intersectionLanes.valid().booleanValue();
        invalidate();
    }
}
