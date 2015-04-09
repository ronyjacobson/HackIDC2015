package il.co.onthefly;

import il.co.onthefly.db.Message;
import il.co.onthefly.db.User;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

import com.google.android.gms.internal.me;
import com.wdullaer.swipeactionadapter.SwipeActionAdapter;
import com.wdullaer.swipeactionadapter.SwipeDirections;
import com.wdullaer.swipeactionadapter.SwipeActionAdapter.SwipeActionListener;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class ChatActivity extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View chat = inflater.inflate(R.layout.activity_chat, container, false);

		/* List View */
		ListView listView = (ListView) chat.findViewById(R.id.list_chat);
		MessagesListAdapter usersListAdapter = new MessagesListAdapter(
				getActivity(), getMessages());
		listView.setAdapter(usersListAdapter);

		return chat;
	}

	private class MessagesListAdapter extends BaseAdapter {
		private List<Message> messageList;
		private LayoutInflater inflater;

		public MessagesListAdapter(Context context, List<Message> messageList) {
			this.messageList = messageList;
			this.inflater = LayoutInflater.from(context);
		}

		@Override
		public int getCount() {
			return messageList.size();
		}

		@Override
		public Object getItem(int i) {
			return messageList.get(i);
		}

		@Override
		public long getItemId(int i) {
			return (long) i;
		}

		public View getView(int position, View convertView, ViewGroup parent) {
			ViewHolder holder;
			if (convertView == null) {
				holder = new ViewHolder();
				convertView = inflater.inflate(R.layout.chat_list_item, null);

				holder.name = (TextView) convertView
						.findViewById(R.id.user_item_name);
				holder.userImage = (ImageView) convertView
						.findViewById(R.id.user_img);
				holder.time = (TextView) convertView
						.findViewById(R.id.user_item_time);
				holder.msgText = (TextView) convertView
						.findViewById(R.id.msg_text0);
				holder.msgImage = (ImageView) convertView
						.findViewById(R.id.msg_image0);

				// Set Font
				Typeface type = Typeface.createFromAsset(getActivity()
						.getAssets(), "fonts/GOTHIC.TTF");
				holder.name.setTypeface(type);
				holder.time.setTypeface(type);
				holder.msgText.setTypeface(type);

				convertView.setTag(holder);
			} else {
				holder = (ViewHolder) convertView.getTag();
			}

			Message message = messageList.get(position);
			holder.name.setText(message.getUser().getFirstName());
			
			if (position == 0) {
				holder.userImage.setImageDrawable(getResources().getDrawable(
						R.drawable.tomer));
			}
			if (position == 1) {
			holder.userImage.setImageDrawable(getResources().getDrawable(
					R.drawable.matan));
			}
			if (position == 2) {
			holder.userImage.setImageDrawable(getResources().getDrawable(
					R.drawable.emily));
			}

			setMessage(holder.msgText, holder.msgImage, holder.time, message);

			return convertView;
		}

		private void setMessage(TextView msgText, ImageView msgImage,
				TextView time, Message message) {
			msgText.setVisibility(View.VISIBLE);
			msgImage.setVisibility(View.VISIBLE);

			if (!message.isSentMessage()) {
				msgImage.setImageDrawable(getResources().getDrawable(
						R.drawable.ic_arrow_received));
			} else {
				msgImage.setImageDrawable(getResources().getDrawable(
						R.drawable.ic_sent));
			}

			msgText.setText(message.getText());
			time.setText(message.getTime());
		}

		class ViewHolder {
			private TextView name, userText, time;
			private ImageView userImage;
			// Details Views
			private TextView msgText;
			private ImageView msgImage;
		}
	}

	public List<Message> getMessages() {

		/** Get Messages from DB and parse them to a list **/
		List<Message> messagesList = new ArrayList<Message>();
		List<User> usersList = new ArrayList<User>();

		/** MOCK USERS **/

		User u1 = new User("Tomer", "Tomer");
		usersList.add(u1);
		u1 = new User("Matan", "Matan");
		usersList.add(u1);
		u1 = new User("Emily", "Emily");
		usersList.add(u1);

		/** MOCK MESSAGES **/
		Message m1 = new Message(usersList.get(0), null, false,
				"Great! :) See you there!", "seen 10 minutes ago");
		messagesList.add(m1);
		m1 = new Message(usersList.get(1), null, true, "Hey dude!",
				"seen 1 hour ago");
		messagesList.add(m1);
		m1 = new Message(usersList.get(2), null, true,
				"I’ll will be at FCO at 11am.", "seen 1 hour and 20 minuts ago");
		messagesList.add(m1);

		return messagesList;
	}
}