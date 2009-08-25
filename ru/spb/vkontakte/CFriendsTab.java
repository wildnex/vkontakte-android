package ru.spb.vkontakte;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TableLayout;

public class CFriendsTab extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	
        setContentView(R.layout.friends);
        TableLayout table = (TableLayout) findViewById(R.id.friends_pane);
        
       
        
        //test
        View v1 = CFriendFactory.getFriendView(this, "Friend #1", true);
        View v2 = CFriendFactory.getFriendView(this, "Friend #2", true);
        View v3 = CFriendFactory.getFriendView(this, "Friend #3", true);
        View v4 = CFriendFactory.getFriendView(this, "Friend #4", false);
        View v5 = CFriendFactory.getFriendView(this, "Friend #5", true);
        table.addView(v1);
        table.addView(v2);
        table.addView(v3);
        table.addView(v4);
        table.addView(v5);
 
	
	}
	
}
