package androidx.navigation.ui;

import android.graphics.drawable.Drawable;
import androidx.annotation.NonNull;
import androidx.annotation.RestrictTo;
import androidx.annotation.StringRes;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
@RestrictTo({RestrictTo.Scope.LIBRARY})
/* loaded from: classes.dex */
public class b extends a {
    public final AppCompatActivity f;

    public b(@NonNull AppCompatActivity appCompatActivity, @NonNull AppBarConfiguration appBarConfiguration) {
        super(appCompatActivity.getDrawerToggleDelegate().getActionBarThemedContext(), appBarConfiguration);
        this.f = appCompatActivity;
    }

    @Override // androidx.navigation.ui.a
    public void b(Drawable drawable, @StringRes int i) {
        ActionBar supportActionBar = this.f.getSupportActionBar();
        if (drawable == null) {
            supportActionBar.setDisplayHomeAsUpEnabled(false);
            return;
        }
        supportActionBar.setDisplayHomeAsUpEnabled(true);
        this.f.getDrawerToggleDelegate().setActionBarUpIndicator(drawable, i);
    }

    @Override // androidx.navigation.ui.a
    public void c(CharSequence charSequence) {
        this.f.getSupportActionBar().setTitle(charSequence);
    }
}
