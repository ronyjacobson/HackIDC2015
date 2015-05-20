package il.co.onthefly;

import il.co.onthefly.db.AsyncResponse;
import il.co.onthefly.db.FeedEntry;
import il.co.onthefly.db.QueryManager;
import il.co.onthefly.db.User;
import il.co.onthefly.util.ExpandableListAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.facebook.widget.ProfilePictureView;
import com.wdullaer.swipeactionadapter.SwipeActionAdapter;
import com.wdullaer.swipeactionadapter.SwipeActionAdapter.SwipeActionListener;
import com.wdullaer.swipeactionadapter.SwipeDirections;

public class FeedActivity extends Fragment implements AsyncResponse {

	ExpandableListAdapter listAdapter;
	ExpandableListView expListView;
	List<String> listDataHeader;
	HashMap<String, List<String>> listDataChild;
	String[] meetTextArr = new String[] {"This sounds fun!", "Why not join?", "Great option!","","",""};
	EditText editText;

	public String getMeetText() {
		Random r = new Random();
		int i = r.nextInt(meetTextArr.length);
		return meetTextArr[i];
	}

	ArrayList<FeedEntry> feedEntrysList;
	QueryManager qm;
	View rootView;
	

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		qm = new QueryManager(this);
		qm.expectResolt = true;
		qm.execute("http://192.185.24.123/~otf/feed.php");

		View feed = inflater.inflate(R.layout.activity_feed, container, false);
		rootView = feed;
		
		
		/////////////////////////////////////////////////////////////////////////////
		// Add Feeds - run this one time
		String id="";
		String imgURL="";
		String userName="";
		String status="";
		String type="";
		//String query= qm.addFeedQuery(id,imgURL,userName,status,type);
		//qm.execute(query);
		/////////////////////////////////////////////////////////////////////////////
		
		return feed;

	}

	public void processFinish(String result) {

		// Disable feed from server
		// feedEntrysList = qm.parseFeedResponse(result);
		// Enable mock feed
		feedEntrysList = getFeed();
		
		((ProgressBar) rootView.findViewById(R.id.progress_feed))
				.setVisibility(View.GONE);

		/* List View */
		ListView listView = (ListView) rootView.findViewById(R.id.list_feed);
		FeedListAdapter feedListAdapter = new FeedListAdapter(getActivity(),
				feedEntrysList);

		/*
		 * Swipe Adapter Wrap list adapter with swipe
		 */
		final SwipeActionAdapter swipeAdapter = new SwipeActionAdapter(
				feedListAdapter);
		swipeAdapter.setListView(listView);

		/* Set swipe adapter as list adapter */
		listView.setAdapter(swipeAdapter);

		// Set backgrounds for the swipe directions
		swipeAdapter.addBackground(SwipeDirections.DIRECTION_FAR_RIGHT,
				R.layout.row_bg_right_far).addBackground(
				SwipeDirections.DIRECTION_NORMAL_RIGHT, R.layout.row_bg_right);

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
					if (dir.equals("Far left") || dir.equals("Left")) {
						// Poke Disabled
					} else {
						// Move to chat
						MainActivity.Tab.setCurrentItem(2, true);
					}

					swipeAdapter.notifyDataSetChanged();
				}
			}
		});

		/* Add new post */
		ImageView addBtn = (ImageView) rootView.findViewById(R.id.feed_fab);
		addBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				final Dialog dialog = new Dialog(FeedActivity.this
						.getActivity());
				dialog.setContentView(R.layout.activity_add_post);
				dialog.setTitle("I want to...");

				// get the listview
				expListView = (ExpandableListView) dialog
						.findViewById(R.id.expandableListView);
				editText = (EditText) dialog
						.findViewById(R.id.addPosttextBox);
				
				// preparing list data
				prepareListData();

				listAdapter = new ExpandableListAdapter(getActivity()
						.getApplicationContext(), listDataHeader, listDataChild);

				// setting list adapter
				expListView.setAdapter(listAdapter);

				Button okButton = (Button) dialog
						.findViewById(R.id.dialogButtonOK);
				// if button is clicked, post
				okButton.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						qm=new QueryManager(FeedActivity.this);
						qm.expectResolt=false;
						String id=LoginActivity.currentUser.getFacebookID();
						String img="https://graph.facebook.com/"+id+"/picture";
						String name=LoginActivity.currentUser.getFirstName();
						String status=editText.getText().toString();
						String type=String.valueOf((new Random().nextInt(4)));
						String query= qm.addFeedQuery(id,img,name,status,type);
						qm.execute(query);
						dialog.dismiss();
					}
				});

				Button cancelButton = (Button) dialog
						.findViewById(R.id.dialogButtonCancel);
				// if button is clicked, post
				cancelButton.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						dialog.dismiss();
					}
				});

				dialog.show();
			}
		});

	}

	private class FeedListAdapter extends BaseAdapter {
		private ArrayList<FeedEntry> feedEntrysList;
		private LayoutInflater inflater;

		public FeedListAdapter(Context context,
				ArrayList<FeedEntry> feedEntrysList) {
			this.feedEntrysList = feedEntrysList;
			this.inflater = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		}

		@Override
		public int getCount() {
			return feedEntrysList.size();
		}

		@Override
		public Object getItem(int i) {
			return feedEntrysList.get(i);
		}

		@Override
		public long getItemId(int i) {
			return (long) i;
		}

		public View getView(int position, View convertView, ViewGroup parent) {
			ViewHolder holder;
			if (convertView == null) {
				holder = new ViewHolder();

				convertView = inflater.inflate(R.layout.feed_entry_list_item,
						null);

				holder.userName = (TextView) convertView
						.findViewById(R.id.feed_item_user_name);
				holder.status = (TextView) convertView
						.findViewById(R.id.feed_item_status);
				holder.statusImage = (ImageView) convertView
						.findViewById(R.id.feed_item_status_img);
				holder.comments = (TextView) convertView
						.findViewById(R.id.feed_item_comments_text);
				holder.meetText = (TextView) convertView
						.findViewById(R.id.feed_item_meet_text);
				holder.content = (TextView) convertView
						.findViewById(R.id.feed_item_status_text);
				holder.userImage = (ProfilePictureView) convertView
						.findViewById(R.id.feed_item_user_img);

				// Set Font
				Typeface type = Typeface.createFromAsset(getActivity()
						.getAssets(), "fonts/GOTHIC.TTF");
				holder.userName.setTypeface(type);
				holder.status.setTypeface(type);
				holder.comments.setTypeface(type);
				holder.meetText.setTypeface(type);
				holder.content.setTypeface(type);

				convertView.setTag(holder);
			} else {
				holder = (ViewHolder) convertView.getTag();
			}

			FeedEntry feedEntry = feedEntrysList.get(position);

			// Set Feed:
			holder.userName.setText(feedEntry.getUserName());
			holder.content.setText(feedEntry.getContent());
			if (position != 2 && position != 5){
				holder.comments.setText(setCommentsText(feedEntry.getComments()
						.size()));
			} else {
				holder.comments.setText("");
			}
			holder.userImage.setProfileId(feedEntry.getUser().getId());
			holder.meetText.setVisibility(View.GONE);
			// Disabled for mock
			// int index = feedEntry.getEntryTypeCode();

			setContentValues(position, holder.status, holder.meetText,
					holder.statusImage);

			return convertView;
		}

		private String setCommentsText(int size) {
			if (size == 0) {
				return "No comments yet";
			} else {
				return (size + " comments");
			}
		}

		class ViewHolder {
			TextView userName, status, content, comments, meetText;
			ImageView statusImage;
			ProfilePictureView userImage;
		}
	}

	private void setContentValues(int i, TextView status, TextView meetText,
			ImageView detailImage) {
		switch (i) {
		case 0: // Pass Time
			status.setText("is looking to pass time");
			status.setTextColor(getResources().getColor(R.color.blue_text));
			String meet = getMeetText();
			if (!("").equals(meet)) {
				meetText.setText(meet);
				meetText.setVisibility(View.VISIBLE);
				meetText.setBackgroundColor(Color.parseColor("#c00000"));
			}
			if (detailImage != null)
				detailImage.setImageDrawable(getResources().getDrawable(
						R.drawable.ic_feedentry_time));
			break;
		case 1: // Eat
			status.setText("wants to eat something");
			status.setTextColor(getResources().getColor(R.color.green_text));
			meet = getMeetText();
			if (!("").equals(meet)) {
				meetText.setText(meet);
				meetText.setVisibility(View.VISIBLE);
				meetText.setBackgroundColor(Color.parseColor("#c00000"));
			}
			if (detailImage != null)
				detailImage.setImageDrawable(getResources().getDrawable(
						R.drawable.ic_feedentry_food));
			break;
		case 2: // Advertisement 1
			status.setVisibility(View.GONE);
			detailImage.setVisibility(View.GONE);
			break;
		case 3: // Cab
			status.setText("wants to share a cab");
			status.setTextColor(getResources().getColor(R.color.yellow_text));
			if (detailImage != null)
				detailImage.setImageDrawable(getResources().getDrawable(
						R.drawable.ic_feedentry_taxi));
			break;
		case 4: // Explore
			status.setText("is looking to explore the city");
			status.setTextColor(getResources().getColor(R.color.lilach_text));
			meet = getMeetText();
			if (!("").equals(meet)) {
				meetText.setText(meet);
				meetText.setVisibility(View.VISIBLE);
				meetText.setBackgroundColor(Color.parseColor("#c00000"));
			}
			if (detailImage != null)
				detailImage.setImageDrawable(getResources().getDrawable(
						R.drawable.ic_feedentry_explore));
			break;
		case 5: // Advertisement 2
			status.setVisibility(View.GONE);
			detailImage.setVisibility(View.GONE);
			break;
		case 6: // Pass Time
			status.setText("is looking to pass time");
			status.setTextColor(getResources().getColor(R.color.blue_text));
			meet = getMeetText();
			if (!("").equals(meet)) {
				meetText.setText(meet);
				meetText.setVisibility(View.VISIBLE);
				meetText.setBackgroundColor(Color.parseColor("#c00000"));
			}
			if (detailImage != null)
				detailImage.setImageDrawable(getResources().getDrawable(
						R.drawable.ic_feedentry_time));
			break;
		}
	}

	public ArrayList<FeedEntry> getFeed() {

		/** Get Users from DB and parse them to an array **/
		ArrayList<FeedEntry> feedEntrysList = new ArrayList<FeedEntry>();

		// QueryManager qm = new QueryManager();
		// if (qm.DB) {
		// String query = "http://192.185.24.123/~otf/feed.php";
		// String response = qm.sendQueryToDB(query);
		// feedEntrysList = qm.parseFeedResponse(response);
		// }

		/** MOCK FEED Entries **/
		if (true) {// !qm.DB) {
			
			User u1 = new User("100003220909039", "Kevin", "28", "6", "Computer Science", "BlogMe", "UK", 0, 4);
			FeedEntry entry1 = new FeedEntry(u1,
					"Hey! How wants to hang out?? I’m in terminal 5 right next to starbucks…");
			feedEntrysList.add(entry1);
			
			
			u1 = new User("672712500", "Tomer", "26", "5", "Economics", "HackIDC", "Israel", 1, 3, 2);
			entry1 = new FeedEntry(u1,
					"Who’s hungry…? In the mood for Chinese!");
			feedEntrysList.add(entry1);
			
			u1 = new User("22092443056", "Starbuck cafe at terminal 3", "25", "3", "Law", "HackIDC", "Israel", 0, 5, 2);
			entry1 = new FeedEntry(u1,
					"OnTheFly users enjoy a free cup of coffee with a purchase of a regular sized coffee. Visit us at terminal 3.");
			feedEntrysList.add(entry1);
			
			u1 = new User("100002716947692", "Catherina", "56", "3", "Computer Science", "RedHat", "USA", 4, 5);
			entry1 = new FeedEntry(u1,
					"Going to the town’s central area, anyone up for sharing a taxi?");
			feedEntrysList.add(entry1);
			
			u1 = new User("592593574", "Ofer", "56", "3", "Computer Science", "RedHat", "USA", 4, 5);
			entry1 = new FeedEntry(u1,
					"Long wait and thought of exploring the city. Who wants to come?");
			feedEntrysList.add(entry1);
			
			u1 = new User("68120344353", "10% of all gadgets", "56", "3", "Computer Science", "RedHat", "USA", 4, 5);
			entry1 = new FeedEntry(u1,
					"Beats by Dr. Dre® Solo 2 Headphones for just 199.99$. Only for OnTheFly users! Visit us a terminal 2.");
			feedEntrysList.add(entry1);
			
			u1 = new User("542737197", "Gal", "56", "3", "Computer Science", "RedHat", "USA", 4, 5);
			entry1 = new FeedEntry(u1,
					"10 hours to burn. Though of watching a long movie. Any LOTR fans?");
			feedEntrysList.add(entry1);
		}

		return feedEntrysList;
	}

	/*
	 * Preparing the list data
	 */
	private void prepareListData() {
		listDataHeader = new ArrayList<String>();
		listDataChild = new HashMap<String, List<String>>();

		// Adding child data
		listDataHeader.add("pass time");
		listDataHeader.add("to eat something");
		listDataHeader.add("share a cab");
		listDataHeader.add("explore the city");

		// Adding child data
		List<String> passTime = new ArrayList<String>();
		passTime.add("by talking together.");
		passTime.add("by telling jokes.");
		passTime.add("by doing business.");
		passTime.add("by playing a game.");
		passTime.add("by watching a movie.");

		List<String> eat = new ArrayList<String>();
		eat.add("at a coffee shop.");
		eat.add("at a resturant.");

		List<String> cab = new ArrayList<String>();
		cab.add("to the city.");
		cab.add("to the seaport.");
		cab.add("to the suburbs.");

		List<String> explore = new ArrayList<String>();
		explore.add("by walking.");
		explore.add("by bus.");
		explore.add("by tour.");

		listDataChild.put(listDataHeader.get(0), passTime); // Header, Child
															// data
		listDataChild.put(listDataHeader.get(1), eat);
		listDataChild.put(listDataHeader.get(2), cab);
		listDataChild.put(listDataHeader.get(3), explore);
	}
}
