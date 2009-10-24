package org.googlecode.vkontakte_android;

import android.content.Context;
import android.database.Cursor;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.ResourceCursorAdapter;
import android.widget.TextView;
import org.googlecode.vkontakte_android.database.MessageDao;
import org.googlecode.vkontakte_android.database.UserDao;
import static org.googlecode.vkontakte_android.provider.UserapiDatabaseHelper.KEY_USER_USERID;
import org.googlecode.vkontakte_android.provider.UserapiProvider;


public class MessagesListAdapter extends ResourceCursorAdapter {
    private static final String TAG = "MessagesListAdapter";

    public MessagesListAdapter(Context context, int layout, Cursor cursor) {
        super(context, layout, cursor);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        MessageDao messageDao = new MessageDao(cursor);
        
        //TODO optimize
        String header = "From ";
        MessageDao md = new MessageDao(cursor);

        Long senderid = md.getSenderId();
        Long receiverid = md.getReceiverId();

        header += getNameById(context, senderid);
        header += " to ";
        header += getNameById(context, receiverid);


        TextView name = (TextView) view.findViewById(R.id.name);
        name.setText(header);
        TextView message = (TextView) view.findViewById(R.id.message);
        
        //warning! setting spanned text causes StackOverflow
        message.setText(Html.fromHtml(messageDao.text).toString());
        View indicator = view.findViewById(R.id.unread_indicator);
        if (!messageDao.read) indicator.setVisibility(View.VISIBLE);
        else indicator.setVisibility(View.INVISIBLE);
    }

    private String getNameById(Context context, Long userid) {
    	
    	String username = userid.toString(); 
    	if (userid.equals(CSettings.myId)) {
    		return "me";
    	}
    	
        Cursor sc = context.getContentResolver().query(
                UserapiProvider.USERS_URI, null, KEY_USER_USERID + "=?",
                new String[]{userid.toString()}, null);
        if (sc.moveToNext()) {
            UserDao ud = new UserDao(sc); 
            if (ud.userName != null) {
            	username = ud.userName;	
            } 
        } else {
            Log.e(TAG, "No such user in DB ");
            username = userid.toString();
        }
        sc.close();
        return username;
    }
}