package noelanthony.com.lostandfoundfinal;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by Noel on 16/02/2018.
 */

public class newsfeedFragment extends Fragment {

    ListView itemListView;
    ArrayList<items> list;
    View myView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        myView = inflater.inflate(R.layout.newsfeed_layout, container, false);
        itemListView = (ListView) myView.findViewById(R.id.itemListView);
        //itemListView = getActivity().findViewById(R.id.itemListView);
        list = new ArrayList<items>();

        items item1 = new items("Wallet", "03-20-2018", "LB443", "Posted by "+"Noel Arandilla");
        items item2 = new items("Bag", "03-22-2018", "LB445", "Posted by "+"Jake Condrillon");

        list.add(item1);
        list.add(item2);
        populateList();
        return myView;
    }
        private void populateList(){
        itemAdapter adapter = new itemAdapter(getActivity(), list);
        itemListView.setAdapter(adapter);
    }

}//END