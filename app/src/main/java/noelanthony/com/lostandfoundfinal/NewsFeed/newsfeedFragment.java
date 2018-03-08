package noelanthony.com.lostandfoundfinal.NewsFeed;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.firebase.client.Firebase;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseException;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;

import noelanthony.com.lostandfoundfinal.R;
import noelanthony.com.lostandfoundfinal.loginANDregister.MainActivity;
import noelanthony.com.lostandfoundfinal.NewsFeed.SubmitLost.submitLostItemActivity;

/**
 * Created by Noel on 16/02/2018.
 */

public class newsfeedFragment extends Fragment {

    Context applicationContext = MainActivity.getContextOfApplication();
    ListView itemListView;
    ArrayList<items> list = new ArrayList<>();
    View myView;
    private Button submitLostBtn;
    private Button foundItemBtn;
    Firebase myFirebase;
    DatabaseReference db;
    boolean saved;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        Firebase.setAndroidContext(applicationContext);
        myFirebase = new Firebase("https://lostandfoundfinal.firebaseio.com/");
        myView = inflater.inflate(R.layout.newsfeed_layout, container, false);
        itemListView = (ListView) myView.findViewById(R.id.itemListView);;
        submitLostBtn = (Button)myView.findViewById(R.id.submitLostBtn);
        foundItemBtn = (Button)myView.findViewById(R.id.foundItemBtn);
        //itemListView = getActivity().findViewById(R.id.itemListView);
        list = new ArrayList<items>();

        //items item1 = new items("Wallet", "03-20-2018", "LB443", "Posted by "+"Noel Arandilla");
       // items item2 = new items("Bag", "03-22-2018", "LB445", "Posted by "+"Jake Condrillon");

        //list.add(item1);
       // list.add(item2);
       // populateList();
        submitLostBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent(applicationContext,submitLostItemActivity.class);
                startActivity(startIntent);
            }
        });
        return myView;
    }


    public Boolean save(items list){
            if(list == null){
                saved=false;
            }else{
                try{
                    db.child("lostItems").push().setValue(list);
                    saved=true;
                }catch(DatabaseException e){
                    e.printStackTrace();
                    saved = false;
                }
            }
            return saved;
    }

    private void fetchData(DataSnapshot dataSnapshot){
        list.clear();
        for (DataSnapshot ds : dataSnapshot.getChildren()){
            items item = ds.getValue(items.class);
            list.add(item);

        }
    }
    //RETRIEVE DATA
    public ArrayList<items> retrieve()
    {
        db.addChildEventListener(new com.google.firebase.database.ChildEventListener() {
            @Override
            public void onChildAdded(com.google.firebase.database.DataSnapshot dataSnapshot, String s) {
                fetchData(dataSnapshot);
            }

            @Override
            public void onChildChanged(com.google.firebase.database.DataSnapshot dataSnapshot, String s) {
                fetchData(dataSnapshot);
            }

            @Override
            public void onChildRemoved(com.google.firebase.database.DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(com.google.firebase.database.DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        return list;
    }
}//END