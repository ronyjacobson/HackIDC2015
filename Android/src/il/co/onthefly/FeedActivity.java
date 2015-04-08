package il.co.onthefly;

import il.co.onthefly.db.FeedEntry;
import il.co.onthefly.db.User;

import java.util.ArrayList;
import java.util.Random;

import com.wdullaer.swipeactionadapter.SwipeActionAdapter;
import com.wdullaer.swipeactionadapter.SwipeDirections;
import com.wdullaer.swipeactionadapter.SwipeActionAdapter.SwipeActionListener;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.os.DropBoxManager.Entry;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class FeedActivity extends Fragment {

	String[] meetText = new String[] { "You should meet!", "This sounds fun!",
			"You should join!" };

	public String getMeetText() {
		Random r = new Random();
		int i = r.nextInt(3);
		return meetText[i];
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View feed = inflater.inflate(R.layout.activity_feed, container, false);

		/* List View */
		ListView listView = (ListView) feed.findViewById(R.id.list_feed);
		FeedListAdapter feedListAdapter = new FeedListAdapter(getActivity(),
				getFeed());
		
		/* Swipe Adapter 
		 * Wrap list adapter with swipe 
		 */
		final SwipeActionAdapter swipeAdapter = new SwipeActionAdapter(feedListAdapter);
		swipeAdapter.setListView(listView);
		
		/* Set swipe adapter as list adapter */
		listView.setAdapter(swipeAdapter);
		
		// Set backgrounds for the swipe directions
				swipeAdapter
			            .addBackground(SwipeDirections.DIRECTION_FAR_RIGHT,R.layout.row_bg_right_far)
			            .addBackground(SwipeDirections.DIRECTION_NORMAL_RIGHT,R.layout.row_bg_right);

				// Listen to swipes
				swipeAdapter.setSwipeActionListener(new SwipeActionListener(){
			        @Override
			        public boolean hasActions(int position){
			            // All items can be swiped
			            return true;
			        }

			        @Override
			        public boolean shouldDismiss(int position, int direction){
			            // Only dismiss an item when swiping normal left
			            return true;
			        }

			        @Override
			        public void onSwipe(int[] positionList, int[] directionList){
			            for(int i=0;i<positionList.length;i++) {
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
			                        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity().getApplicationContext());
			                        builder.setTitle("Test Dialog").setMessage("You swiped right").create().show();
			                        dir = "Right";
			                        break;
			                }
			                if (dir.equals("Far left") || dir.equals("Left")){
			                	// Poke Disabled
			                } else {
			                	// Move to chat
			                	MainActivity.Tab.setCurrentItem(2, true);
			                }
			                
			                swipeAdapter.notifyDataSetChanged();
			            }
			        }
			    });
		
		/* Add new post*/
		ImageView addBtn= (ImageView) feed.findViewById(R.id.feed_fab);
		addBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
			}
		});

		return feed;
	}

	private class FeedListAdapter extends BaseAdapter {
		private ArrayList<FeedEntry> feedEntrysList;
		private LayoutInflater inflater;

		public FeedListAdapter(Context context,
				ArrayList<FeedEntry> feedEntrysList) {
			this.feedEntrysList = feedEntrysList;
			this.inflater = LayoutInflater.from(context);
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
				holder.userImage = (ImageView) convertView
						.findViewById(R.id.feed_item_user_img);

				convertView.setTag(holder);
			} else {
				holder = (ViewHolder) convertView.getTag();
			}

			FeedEntry feedEntry = feedEntrysList.get(position);

			// Set Feed:
			holder.userName.setText(feedEntry.getUser().getFirstName());
			holder.content.setText(feedEntry.getContent());
			holder.comments.setText(setCommentsText(feedEntry.getComments().size()));
			holder.meetText.setVisibility(View.GONE);
			int index = feedEntry.getEntryTypeCode();

			setContentValues(index, holder.status, holder.meetText,
					holder.statusImage);

			return convertView;
		}

		private String setCommentsText(int size) {
			if (size == 0) {
				return "No comments yet";
			} else {
				return (size+" comments");
			}
		}

		class ViewHolder {
			TextView userName, status, content, comments, meetText;
			ImageView userImage, statusImage;
		}
	}

	private void setContentValues(int i, TextView status, TextView meetText,
			ImageView detailImage) {
		switch (i) {
		case 0: // Pass Time
			status.setText("is looking to pass time");
			status.setTextColor(getResources().getColor(R.color.blue_text));
			meetText.setText(getMeetText());
			meetText.setVisibility(View.VISIBLE);
			if (detailImage != null)
				detailImage.setImageDrawable(getResources().getDrawable(
						R.drawable.ic_feedentry_time));
			break;
		case 1: // Eat
			status.setText("wants to eat something");
			status.setTextColor(getResources().getColor(R.color.green_text));
			meetText.setText(getMeetText());
			meetText.setVisibility(View.VISIBLE);
			if (detailImage != null)
				detailImage.setImageDrawable(getResources().getDrawable(
						R.drawable.ic_feedentry_food));
			break;
		case 2: // Cab
			status.setText("wants to share a cab");
			status.setTextColor(getResources().getColor(R.color.yellow_text));
			if (detailImage != null)
				detailImage.setImageDrawable(getResources().getDrawable(
						R.drawable.ic_feedentry_taxi));
			break;
		case 3: // Explore
			status.setText("is looking to explore the city");
			status.setTextColor(getResources().getColor(R.color.lilach_text));
			meetText.setText(getMeetText());
			meetText.setVisibility(View.VISIBLE);
			if (detailImage != null)
				detailImage.setImageDrawable(getResources().getDrawable(
						R.drawable.ic_feedentry_explore));
			break;
		case 5: // Advertisement 1
			status.setText("Starbuck cafe at terminal 3");
			status.setTextColor(getResources().getColor(R.color.pink_text));
			// if (detailImage != null)
			// detailImage.setImageDrawable(getResources().getDrawable(R.drawable.ic_detail_5));
			break;
		case 6: // Advertisement 1
			status.setText("10% of all gadgets");
			status.setTextColor(getResources().getColor(R.color.pink_text));
			// if (detailImage != null)
			// detailImage.setImageDrawable(getResources().getDrawable(R.drawable.ic_detail_5));
			break;
		}
	}

	public ArrayList<FeedEntry> getFeed() {

		/** Get Users from DB and parse them to an array **/
		ArrayList<FeedEntry> feedEntrysList = new ArrayList<FeedEntry>();

		/** MOCK FEED Entries **/
		
		User u1 = new User("Alon", "Grinshpoon");
		FeedEntry entry1 = new FeedEntry(u1, "This is the content of the status");
		feedEntrysList.add(entry1);
		
		u1 = new User("Rony", "Jacobson");
		entry1 = new FeedEntry(u1 , "This is the content of the status");
		feedEntrysList.add(entry1);
		
		u1 = new User("Alon", "Grinshpoon");
		entry1 = new FeedEntry(u1 , "This is the content of the status");
		feedEntrysList.add(entry1);
		
		u1 = new User("Idan", "Tsitaiat");
		entry1 = new FeedEntry(u1 , "This is the content of the status");
		feedEntrysList.add(entry1);
		
		
		u1 = new User("Aviad", "Levi");
		entry1 = new FeedEntry(u1 , "This is the content of the status");
		feedEntrysList.add(entry1);
		
		
		u1 = new User("Rony", "Jacobson");
		entry1 = new FeedEntry(u1 , "This is the content of the status");
		feedEntrysList.add(entry1);
		
		
		u1 = new User("Alon", "Grinshpoon");
		entry1 = new FeedEntry(u1 , "This is the content of the status");
		feedEntrysList.add(entry1);
		
		
		u1 = new User("Idan", "Tsitaiat");
		entry1 = new FeedEntry(u1 , "This is the content of the status");
		feedEntrysList.add(entry1);
		
		
		u1 = new User("Aviad", "Levi");
		entry1 = new FeedEntry(u1 , "This is the content of the status");
		feedEntrysList.add(entry1);
		
		
		u1 = new User("Rony", "Jacobson");
		entry1 = new FeedEntry(u1 , "This is the content of the status");
		feedEntrysList.add(entry1);
		
		
		u1 = new User("Alon", "Grinshpoon");
		entry1 = new FeedEntry(u1 , "This is the content of the status");
		feedEntrysList.add(entry1);
		
		
		u1 = new User("Idan", "Tsitaiat");
		entry1 = new FeedEntry(u1 , "This is the content of the status");
		feedEntrysList.add(entry1);
		
		
		u1 = new User("Aviad", "Levi");
		entry1 = new FeedEntry(u1 , "This is the content of the status");
		feedEntrysList.add(entry1);
		

		return feedEntrysList;
	}
}