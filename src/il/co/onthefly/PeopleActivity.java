package il.co.onthefly;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
public class PeopleActivity extends Fragment {
  @Override
      public View onCreateView(LayoutInflater inflater, ViewGroup container,
              Bundle savedInstanceState) {
          View people = inflater.inflate(R.layout.activity_chat, container, false);
          ((TextView)people.findViewById(R.id.textView)).setText("People frag");
          return people;
}}