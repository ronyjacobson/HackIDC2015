package il.co.onthefly;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
public class FeedActivity extends Fragment {
  @Override
      public View onCreateView(LayoutInflater inflater, ViewGroup container,
              Bundle savedInstanceState) {
          View feed = inflater.inflate(R.layout.activity_chat, container, false);
          ((TextView)feed.findViewById(R.id.textView)).setText("Feed frag");
          return feed;
}}