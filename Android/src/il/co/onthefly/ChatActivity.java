package il.co.onthefly;

import il.co.onthefly.db.User;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

import com.wdullaer.swipeactionadapter.SwipeActionAdapter;
import com.wdullaer.swipeactionadapter.SwipeDirections;
import com.wdullaer.swipeactionadapter.SwipeActionAdapter.SwipeActionListener;

import android.app.AlertDialog;
import android.content.Context;
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
		UsersListAdapter usersListAdapter = new UsersListAdapter(getActivity(),
				getUsers());
		listView.setAdapter(usersListAdapter);
		
		return chat;
	}

	private class UsersListAdapter extends BaseAdapter {
		private List<User> usersList;
		private LayoutInflater inflater;

		public UsersListAdapter(Context context, List<User> usersList) {
			this.usersList = usersList;
			this.inflater = LayoutInflater.from(context);
		}

		@Override
		public int getCount() {
			return usersList.size();
		}

		@Override
		public Object getItem(int i) {
			return usersList.get(i);
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

				convertView.setTag(holder);
			} else {
				holder = (ViewHolder) convertView.getTag();
			}

			User user = usersList.get(position);
			holder.name.setText(user.getFirstName());
			// holder.userImage.setImageResource(R.drawable.ic_launcher);
			HashSet<Integer> types = LoginActivity.currentUser
					.getDetailsTypes();

			int numOfDetails = (types.size() < 3 ? types.size() : 3);

			for (int detailNum = 0; detailNum < numOfDetails; detailNum++) {
				int randItem = new Random().nextInt(types.size());
				int i = 0;
				Integer type = 0;

				for (Integer set_type : types) {
					if (i == randItem) {
						type = set_type;
						types.remove(set_type);
					}
					i = i + 1;
				}
				
				setDetails(type, holder.msgText, holder.msgImage, user);

			}
			return convertView;
		}

		private void setDetails(int i, TextView detail, ImageView detailImage,
				User user) {
			detail.setVisibility(View.VISIBLE);
			detailImage.setVisibility(View.VISIBLE);

			switch (i) {
			case 0: // same destination
				detail.setText("Flying your way!");
				detail.setTextColor(getResources().getColor(R.color.blue_text));
				detailImage.setImageDrawable(getResources().getDrawable(
						R.drawable.ic_detail_0));
				break;
			case 1: // same age
				detail.setText("Here with kids!");
				detail.setTextColor(getResources().getColor(R.color.green_text));
				detailImage.setImageDrawable(getResources().getDrawable(
						R.drawable.ic_detail_1));
				break;
			case 2: // same country
				detail.setText("Also lives in " + user.getCountry() + "!");
				detail.setTextColor(getResources()
						.getColor(R.color.yellow_text));
				detailImage.setImageDrawable(getResources().getDrawable(
						R.drawable.ic_detail_2));
				break;
			case 3: // here with kids
				detail.setText("Same age as you!");
				detail.setTextColor(getResources().getColor(
						R.color.light_blue_text));
				detailImage.setImageDrawable(getResources().getDrawable(
						R.drawable.ic_detail_3));
				break;
			case 4: // same degree
				detail.setText("Studies " + user.getDegree() + ".");
				detail.setTextColor(getResources()
						.getColor(R.color.lilach_text));
				detailImage.setImageDrawable(getResources().getDrawable(
						R.drawable.ic_detail_4));
				break;
			case 5: // workplace
				detail.setText("Works at " + user.getWork() + ".");
				detail.setTextColor(getResources().getColor(R.color.pink_text));
				detailImage.setImageDrawable(getResources().getDrawable(
						R.drawable.ic_detail_5));
				break;
			case 6: // has a connection in TODO-Add COLOR,IC
				detail.setText("Will be in " + user.getConnectionAirport()
						+ " too!");
				detail.setTextColor(getResources().getColor(R.color.blue_text));
				detailImage.setImageDrawable(getResources().getDrawable(
						R.drawable.ic_detail_0));
				break;
			case 7:
				break;
			case 8:
				break;
			case 9:
				break;
			}
		}

		class ViewHolder {
			private TextView name, userText, time;
			private ImageView userImage;
			// Details Views
			private TextView msgText;
			private ImageView msgImage;
		}
	}

	public List<User> getUsers() {

		/** Get Users from DB and parse them to a list **/
		ArrayList<User> usersList = new ArrayList<User>();

		/** MOCK USERS **/

		User u1 = new User("Rony", "Jacobson");
		usersList.add(u1);
		u1 = new User("Alon", "Grinshpoon");
		usersList.add(u1);
		u1 = new User("Idan", "Tsitaiat");
		usersList.add(u1);
		u1 = new User("Aviad", "Levi");
		usersList.add(u1);
		u1 = new User("Rony", "Jacobson");
		usersList.add(u1);
		u1 = new User("Alon", "Grinshpoon");
		usersList.add(u1);
		u1 = new User("Idan", "Tsitaiat");
		usersList.add(u1);
		u1 = new User("Aviad", "Levi");
		usersList.add(u1);
		u1 = new User("Rony", "Jacobson");
		usersList.add(u1);
		u1 = new User("Alon", "Grinshpoon");
		usersList.add(u1);
		u1 = new User("Idan", "Tsitaiat");
		usersList.add(u1);
		u1 = new User("Aviad", "Levi");
		usersList.add(u1);

		return usersList;
	}
}