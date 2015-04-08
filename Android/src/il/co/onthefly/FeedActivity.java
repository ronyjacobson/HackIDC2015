package il.co.onthefly;

import il.co.onthefly.db.FeedEntry;
import il.co.onthefly.db.User;

import java.util.ArrayList;

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

public class FeedActivity extends Fragment {
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View feed = inflater.inflate(R.layout.activity_feed, container, false);

		/* List View */
		ListView listView = (ListView) feed.findViewById(R.id.list_feed);
		FeedListAdapter feedListAdapter = new FeedListAdapter(getActivity(),getFeed());
		listView.setAdapter(feedListAdapter);
		
		return feed;
	}

	
	private class FeedListAdapter extends BaseAdapter {
		private ArrayList<FeedEntry> feedEntrysList;
		private LayoutInflater inflater;

		public FeedListAdapter(Context context, ArrayList<FeedEntry> feedEntrysList) {
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
			return Long.parseLong(feedEntrysList.get(i).getId());
		}

		public View getView(int position, View convertView, ViewGroup parent) {
			ViewHolder holder;
			if (convertView == null) {
				holder = new ViewHolder();

				convertView = inflater.inflate(R.layout.feed_entry_list_item, null);

				holder.userName = (TextView) convertView
						.findViewById(R.id.feed_item_user_name);
				holder.status = (TextView) convertView
						.findViewById(R.id.feed_item_status);
				holder.comments = (TextView) convertView
						.findViewById(R.id.feed_item_comments_text);
				holder.meetText = (TextView) convertView
						.findViewById(R.id.feed_item_meet_text);
				holder.content = (TextView) convertView
						.findViewById(R.id.feed_content);
				holder.userImage = (ImageView) convertView
						.findViewById(R.id.feed_item_user_img);
				holder.statusImage = (ImageView) convertView
						.findViewById(R.id.feed_item_status_img);

				convertView.setTag(holder);
			} else {
				holder = (ViewHolder) convertView.getTag();
			}

			holder.userName.setText(feedEntrysList.get(position).getUser().getFullName());
			
			// holder.details.setText(usersList.get(position).());

			return convertView;
		}

		class ViewHolder {
			TextView userName, content, status, comments, meetText;
			ImageView userImage, statusImage;
		}
	}

	public ArrayList<FeedEntry> getFeed() {

		/** Get Users from DB and parse them to an array **/
		ArrayList<FeedEntry> feedEntrysList = new ArrayList<FeedEntry>();

		/** MOCK FEED Entries **/
		User user1 = new User("rony", "jacobson");
		FeedEntry entry1= new FeedEntry(user1, "blah blah blah", "Taxi");
		feedEntrysList.add(entry1);

		return feedEntrysList;
	}
}