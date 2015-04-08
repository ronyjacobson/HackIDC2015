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
		View people = inflater.inflate(R.layout.activity_people, container,
				false);

		/* List View */
		ListView listView = (ListView) people.findViewById(R.id.list_people);
		UsersListAdapter usersListAdapter = new UsersListAdapter(getActivity(),
				getUsers());
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
			return (long) i;
		}

		public View getView(int position, View convertView, ViewGroup parent) {
			ViewHolder holder;
			if (convertView == null) {
				holder = new ViewHolder();
				convertView = inflater.inflate(R.layout.user_list_item, null);

				holder.name = (TextView) convertView
						.findViewById(R.id.user_item_name);
				holder.userImage = (ImageView) convertView
						.findViewById(R.id.user_img);
				holder.timeLeft = (TextView) convertView
						.findViewById(R.id.user_item_timeLeft);

				holder.detail0 = (TextView) convertView
						.findViewById(R.id.detail_text0);
				holder.detail1 = (TextView) convertView
						.findViewById(R.id.detail_text1);
				holder.detail2 = (TextView) convertView
						.findViewById(R.id.detail_text2);

				holder.detailImage0 = (ImageView) convertView
						.findViewById(R.id.detail_image0);
				holder.detailImage1 = (ImageView) convertView
						.findViewById(R.id.detail_image1);
				holder.detailImage2 = (ImageView) convertView
						.findViewById(R.id.detail_image2);

				convertView.setTag(holder);
			} else {
				holder = (ViewHolder) convertView.getTag();
			}

			User user = usersList.get(position);
			holder.name.setText(user.getFirstName() + " ," + user.getAge());
			// holder.userImage.setImageResource(R.drawable.ic_launcher);

			setDetails(user.getDetailCode(0), holder.detail0,
					holder.detailImage0, user);
			setDetails(user.getDetailCode(1), holder.detail1,
					holder.detailImage1, user);
			setDetails(user.getDetailCode(2), holder.detail2,
					holder.detailImage2, user);

			return convertView;
		}

		private void setDetails(int i, TextView detail, ImageView detailImage, User  user) {
			switch (i) {
			case 0:	//same destination
				detail.setText("Flying your way!");
				detail.setTextColor(getResources().getColor(R.color.blue_text));
				detailImage.setImageDrawable(getResources().getDrawable(R.drawable.ic_detail_0));
				break;
			case 1:	//same age
				detail.setText("Here with kids!");
				detail.setTextColor(getResources().getColor(R.color.green_text));
				detailImage.setImageDrawable(getResources().getDrawable(R.drawable.ic_detail_1));
				break;
			case 2: //same country
				detail.setText("Also lives in "+user.getCountry()+"!");
				detail.setTextColor(getResources().getColor(R.color.yellow_text));
				detailImage.setImageDrawable(getResources().getDrawable(R.drawable.ic_detail_2));
				break;
			case 3: //here with kids
				detail.setText("Same age as you!");
				detail.setTextColor(getResources().getColor(R.color.light_blue_text));
				detailImage.setImageDrawable(getResources().getDrawable(R.drawable.ic_detail_3));
				break;
			case 4: //same degree
				detail.setText("Studies "+user.getDegree()+".");
				detail.setTextColor(getResources().getColor(R.color.lilach_text));
				detailImage.setImageDrawable(getResources().getDrawable(R.drawable.ic_detail_4));
				break;
			case 5: //workplace
				detail.setText("Works at "+user.getWork()+".");
				detail.setTextColor(getResources().getColor(R.color.pink_text));
				detailImage.setImageDrawable(getResources().getDrawable(R.drawable.ic_detail_5));
				break;
			case 6: 
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
			private TextView name, details, timeLeft;
			private ImageView userImage;
			// Details Views
			private TextView detail0, detail1, detail2;
			private ImageView detailImage0, detailImage1, detailImage2;
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