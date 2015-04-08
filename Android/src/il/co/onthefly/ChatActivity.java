package il.co.onthefly;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
public class ChatActivity extends Fragment {
  @Override
      public View onCreateView(LayoutInflater inflater, ViewGroup container,
              Bundle savedInstanceState) {
          View chat = inflater.inflate(R.layout.activity_chat, container, false);
          ((TextView)chat.findViewById(R.id.textView)).setText("Coming Soon...");
          return chat;
}}