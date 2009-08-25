package ru.spb.vkontakte;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TabHost;

public class CGuiTest extends TabActivity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        
        // load icons from the files
        CImagesManager.loadImages(this);
        
        final TabHost tabHost = getTabHost();
            
        tabHost.addTab(tabHost.newTabSpec("I")
                .setIndicator(getResources().getString(R.string.i))
                .setContent(new Intent(this, CMeTab.class)));
        
        tabHost.addTab(tabHost.newTabSpec("Friends")
                .setIndicator(getResources().getString(R.string.friends))
                .setContent(new Intent(this, CFriendsTab.class)));

        tabHost.addTab(tabHost.newTabSpec("Messages")
                .setIndicator(getResources().getString(R.string.messages))
                .setContent(new Intent(this, CMessagesTab.class)));

    }
}