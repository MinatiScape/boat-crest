package com.coveiot.android.leonardo.remotecamera;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.Nullable;
import com.coveiot.android.boat.R;
/* loaded from: classes5.dex */
public class MessageView extends LinearLayout {
    public TextView h;
    public TextView i;

    public MessageView(Context context) {
        this(context, null);
    }

    public void setMessage(String str) {
        this.h.setText(str);
    }

    public void setTitle(String str) {
        this.i.setText(str);
    }

    public MessageView(Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public MessageView(Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        setOrientation(1);
        LinearLayout.inflate(context, R.layout.control_view, this);
        ViewGroup viewGroup = (ViewGroup) findViewById(R.id.content);
        LinearLayout.inflate(context, R.layout.spinner_text, viewGroup);
        this.i = (TextView) findViewById(R.id.title);
        this.h = (TextView) viewGroup.getChildAt(0);
    }
}
