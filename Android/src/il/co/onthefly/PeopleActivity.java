package il.co.onthefly;

import il.co.onthefly.db.AsyncResponse;
import il.co.onthefly.db.FeedEntry;
import il.co.onthefly.db.QueryManager;
import il.co.onthefly.db.User;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

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
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.widget.ProfilePictureView;
import com.wdullaer.swipeactionadapter.SwipeActionAdapter;
import com.wdullaer.swipeactionadapter.SwipeActionAdapter.SwipeActionListener;
import com.wdullaer.swipeactionadapter.SwipeDirections;

public class PeopleActivity extends Fragment implements AsyncResponse {

	ArrayList<User> usersList;
	QueryManager qm;
	View rootView;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		qm = new QueryManager(this);
		qm.expectResolt = true;
		qm.execute("http://192.185.24.123/~otf/match.php");
		View people = inflater.inflate(R.layout.activity_people, container,
				false);
		rootView = people;
		return people;

	}

	private class UsersListAdapter extends BaseAdapter {
		private List<User> usersList;
		private LayoutInflater inflater;

		public UsersListAdapter(Context context, List<User> usersList) {
			this.usersList = usersList;
			this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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
				holder.userImage = (ProfilePictureView) convertView
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

				// Set Font
				Typeface type = Typeface.createFromAsset(getActivity()
						.getAssets(), "fonts/GOTHIC.TTF");
				holder.name.setTypeface(type);
				holder.detail0.setTypeface(type);
				holder.detail1.setTypeface(type);
				holder.detail2.setTypeface(type);
				holder.timeLeft.setTypeface(type);

				convertView.setTag(holder);
			} else {
				holder = (ViewHolder) convertView.getTag();
			}

			User user = usersList.get(position);
			holder.name.setText(user.getFirstName() + ", " + user.getAge());
			holder.timeLeft.setText(user.getWatingTime());
			holder.userImage.setProfileId(user.getId());

			/**
			 * setDetails(new Random().nextInt(7), holder.detail0,
			 * holder.detailImage0, user); setDetails(new Random().nextInt(7),
			 * holder.detail1, holder.detailImage1, user); setDetails(new
			 * Random().nextInt(7), holder.detail2, holder.detailImage2, user);
			 **/

			/**
			 * When DB is Integrated
			 **/
			HashSet<Integer> types = user.getDetailsTypes();

			int numOfDetails = (types.size() < 3 ? types.size() : 3);

			for (int detailNum = 0; detailNum < numOfDetails; detailNum++) {
				Integer type = 0;

				int randItemNum = new Random().nextInt(types.size());
				int i = 0;

				for (Integer set_type : types) {
					if (i == randItemNum) {
						type = set_type;
						types.remove(set_type);
						break;
					}
					i = i + 1;
				}
				if (detailNum == 0) {
					setDetails(type, holder.detail0, holder.detailImage0, user);
				} else if (detailNum == 1) {
					setDetails(type, holder.detail1, holder.detailImage1, user);
				} else {
					setDetails(type, holder.detail2, holder.detailImage2, user);
				}
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
			private TextView name, details, timeLeft;
			private ProfilePictureView userImage;
			// Details Views
			private TextView detail0, detail1, detail2;
			private ImageView detailImage0, detailImage1, detailImage2;
		}
	}

	public ArrayList<User> getUsers() {

		/** Get Users from DB and parse them to a list **/
		ArrayList<User> usersList = new ArrayList<User>();
		// QueryManager qm = new QueryManager();
		//
		// if (qm.DB) {
		// String query = "http://192.185.24.123/~otf/match.php";
		// String response = qm.sendQueryToDB(query);
		// usersList = qm.parsePeopleResponse(response);
		// }

		/** MOCK USERS **/
		if (true) {// !qm.DB) {
					
			User u1 = new User("557593671", "Matan", "26", "8", "Law", "HackIDC", "Israel", 2, 3, 0);
			usersList.add(u1);
			u1 = new User("100003220909039", "Kevin", "28", "6", "Computer Science", "BlogMe", "UK", 0, 4);
			usersList.add(u1);
			u1 = new User("810389724", "Aviya", "26", "5", "Economics", "HackIDC", "Israel", 1, 3, 2);
			usersList.add(u1);
			u1 = new User("542737197", "Gal", "25", "3", "Law", "HackIDC", "Israel", 0, 5, 2);
			usersList.add(u1);
			u1 = new User("894145242", "Donnie", "56", "3", "Computer Science", "RedHat", "USA", 4, 5);
			usersList.add(u1);

			//FeedEntry entry1 = new FeedEntry(u1,"This is the content of the status");
			//usersList.add(u1);
		}

		return usersList;
	}

	public void processFinish(String result) {

		// Disable users from server
		// usersList = qm.parsePeopleResponse(result);
		// Enable mock users
		usersList = getUsers();
		
		((ProgressBar) rootView.findViewById(R.id.progress))
				.setVisibility(View.GONE);

		/* List View */
		ListView listView = (ListView) rootView.findViewById(R.id.list_people);
		UsersListAdapter usersListAdapter = new UsersListAdapter(getActivity(),	usersList);

		/*
		 * Swipe Adapter Wrap list adapter with swipe
		 */
		final SwipeActionAdapter swipeAdapter = new SwipeActionAdapter(
				usersListAdapter);
		swipeAdapter.setListView(listView);

		/* Set swipe adapter as list adapter */
		listView.setAdapter(swipeAdapter);

		// Set backgrounds for the swipe directions
		swipeAdapter
				.addBackground(SwipeDirections.DIRECTION_FAR_LEFT,
						R.layout.row_bg_left_far)
				.addBackground(SwipeDirections.DIRECTION_NORMAL_LEFT,
						R.layout.row_bg_left)
				.addBackground(SwipeDirections.DIRECTION_FAR_RIGHT,
						R.layout.row_bg_right_far)
				.addBackground(SwipeDirections.DIRECTION_NORMAL_RIGHT,
						R.layout.row_bg_right);

		// Listen to swipes
		swipeAdapter.setSwipeActionListener(new SwipeActionListener() {
			@Override
			public boolean hasActions(int position) {
				// All items can be swiped
				return true;
			}

			@Override
			public boolean shouldDismiss(int position, int direction) {
				// Only dismiss an item when swiping normal left
				return true;
			}

			@Override
			public void onSwipe(int[] positionList, int[] directionList) {
				for (int i = 0; i < positionList.length; i++) {
					int direction = directionList[i];
					int position = positionList[i];
					String dir = "";

					switch (direction) {
					case SwipeDirections.DIRECTION_FAR_LEFT:
						dir = "Far left";
						break;
					case SwipeDirections.DIRECTION_NORMAL_LEFT:
						dir = "Left";
						break;
					case SwipeDirections.DIRECTION_FAR_RIGHT:
						dir = "Far right";
						break;
					case SwipeDirections.DIRECTION_NORMAL_RIGHT:
						dir = "Right";
						break;
					}
					
					if (dir.equals("Far left") || dir.equals("Left")){
	                	// Poke
	                	Toast.makeText(
		                		getActivity().getApplicationContext(),
		                        "You poked " + ((User) swipeAdapter.getItem(position)).getFirstName() + "!",
		                        Toast.LENGTH_SHORT
		                ).show();
	                } else {
	                	// Move to chat
	                	MainActivity.Tab.setCurrentItem(2, true);
	                }
					swipeAdapter.notifyDataSetChanged();
				}
			}
		});

	}

}