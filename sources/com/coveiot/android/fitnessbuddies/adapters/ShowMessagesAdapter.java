package com.coveiot.android.fitnessbuddies.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.coveiot.android.fitnessbuddies.R;
import com.coveiot.coveaccess.fitnessbuddies.model.common.Messages;
import com.coveiot.coveaccess.prefs.PreferenceManager;
import com.coveiot.covepreferences.SessionManager;
import com.coveiot.utils.utility.AppUtils;
import com.coveiot.utils.utility.GlideUtils;
import com.coveiot.utils.utility.ImageLodeListener;
import com.coveiot.utils.utility.UtilConstants;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
/* loaded from: classes4.dex */
public class ShowMessagesAdapter extends RecyclerView.Adapter<ViewHolder> {

    /* renamed from: a  reason: collision with root package name */
    public Context f4445a;
    public List<Messages> b;

    /* loaded from: classes4.dex */
    public class ViewHolder extends RecyclerView.ViewHolder {

        /* renamed from: a  reason: collision with root package name */
        public ImageView f4446a;
        public TextView b;
        public TextView c;
        public TextView d;
        public ImageView e;

        public ViewHolder(@NonNull ShowMessagesAdapter showMessagesAdapter, View view) {
            super(view);
            this.f4446a = (ImageView) view.findViewById(R.id.image);
            this.b = (TextView) view.findViewById(R.id.name);
            this.c = (TextView) view.findViewById(R.id.date);
            this.d = (TextView) view.findViewById(R.id.content);
            this.e = (ImageView) view.findViewById(R.id.messageStatus);
        }
    }

    /* loaded from: classes4.dex */
    public class a implements ImageLodeListener {
        public a(ShowMessagesAdapter showMessagesAdapter) {
        }

        @Override // com.coveiot.utils.utility.ImageLodeListener
        public void onImageLoaded() {
        }
    }

    /* loaded from: classes4.dex */
    public class b implements ImageLodeListener {
        public b(ShowMessagesAdapter showMessagesAdapter) {
        }

        @Override // com.coveiot.utils.utility.ImageLodeListener
        public void onImageLoaded() {
        }
    }

    public ShowMessagesAdapter(Context context, List<Messages> list) {
        this.f4445a = context;
        this.b = list;
    }

    public final Date a(String str) {
        try {
            SimpleDateFormat simpleDateFormat = AppUtils.getSimpleDateFormat(UtilConstants.SERVER_TIME_FORMAT);
            simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
            Date parse = simpleDateFormat.parse(str);
            simpleDateFormat.setTimeZone(TimeZone.getDefault());
            return AppUtils.getSimpleDateFormat(UtilConstants.SERVER_TIME_FORMAT).parse(simpleDateFormat.format(parse));
        } catch (Exception unused) {
            return null;
        }
    }

    public final String b(Date date) {
        return date == null ? "" : AppUtils.getSimpleDateFormat("dd MMM, hh:mm aa").format(date);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.b.size();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.b.setText(this.b.get(i).fromUserName);
        if (this.b.get(i).fromUserId == PreferenceManager.getInstance().getUserId().intValue()) {
            viewHolder.e.setImageResource(R.drawable.outgoing_message);
            SessionManager.getInstance(this.f4445a).getUserDetails();
            viewHolder.b.setText(this.b.get(i).toUserName);
            GlideUtils.loadCircularImage(this.f4445a, this.b.get(i).toUserDpUrl, viewHolder.f4446a, new a(this));
        } else {
            viewHolder.e.setImageResource(R.drawable.incoming_message);
            viewHolder.b.setText(this.b.get(i).fromUserName);
            GlideUtils.loadCircularImage(this.f4445a, this.b.get(i).fromUserDpUrl, viewHolder.f4446a, new b(this));
        }
        viewHolder.d.setText(this.b.get(i).message);
        viewHolder.c.setText(b(a(this.b.get(i).createdDate)));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    @NonNull
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(this, LayoutInflater.from(this.f4445a).inflate(R.layout.messages_list_row, viewGroup, false));
    }
}
