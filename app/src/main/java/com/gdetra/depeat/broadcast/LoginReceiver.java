package com.gdetra.depeat.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;

import com.gdetra.depeat.R;
import com.gdetra.depeat.ui.activities.ProfileActivity;

public class LoginReceiver extends BroadcastReceiver {
    private Menu menu;

    public LoginReceiver(Menu menu){
        this.menu = menu;
    }

    @Override
    public void onReceive(final Context context, Intent intent) {
        menu.findItem(R.id.login_menu)
                .setTitle(context.getString(R.string.profile_fixed))
                .setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        context.startActivity(new Intent(context, ProfileActivity.class));
                        return true;
                    }
                });

    }
}
