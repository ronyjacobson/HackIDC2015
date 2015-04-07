package il.co.onthefly;

import il.co.onthefly.db.User;

import java.util.ArrayList;
import java.util.List;

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

public class PeopleActivity extends Fragment {
	
	

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View people = inflater.inflate(R.layout.activity_people, container, false);
		
		/* List View */
		ListView listView = (ListView) people.findViewById(R.id.list_people);
		UsersListAdapter usersListAdapter = new UsersListAdapter(getActivity() , getUsers());
		listView.setAdapter(usersListAdapter);
		
		return people;
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
			return Long.parseLong(usersList.get(i).getId());
		}

		public View getView(int position, View convertView, ViewGroup parent) {
			ViewHolder holder;
			if (convertView == null) {
				holder = new ViewHolder();

				convertView = inflater.inflate(R.layout.user_list_item, null);

				holder.name = (TextView) convertView
						.findViewById(R.id.user_item_name);
				holder.details = (TextView) convertView
						.findViewById(R.id.user_item_details);
				holder.img = (ImageView) convertView
						.findViewById(R.id.user_img);

				convertView.setTag(holder);
			} else {
				holder = (ViewHolder) convertView.getTag();
			}

			holder.name.setText(usersList.get(position).getFullName());
			// holder.details.setText(usersList.get(position).());

			return convertView;
		}

		class ViewHolder {
			TextView name, details;
			ImageView img;
		}
	}

	public List<User> getUsers() {

		/** Get Users from DB and parse them to a list **/
		ArrayList<User> usersList = new ArrayList<User>();

		/** MOCK USERS **/

		User u1 = new User("Rony", "Jacobson");
		usersList.add(u1);
		User u2 = new User("Alon", "Grinshpoon");
		usersList.add(u2);
		User u3 = new User("Idan", "Tsitaiat");
		usersList.add(u3);

		return usersList;
	}
}