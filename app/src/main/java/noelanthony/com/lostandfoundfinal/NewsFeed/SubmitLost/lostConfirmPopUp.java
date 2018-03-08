package noelanthony.com.lostandfoundfinal.NewsFeed.SubmitLost;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import noelanthony.com.lostandfoundfinal.R;

public class lostConfirmPopUp extends Activity {

    private TextView itemnameTextView;
    private TextView lastseenTextView;
    private TextView descriptionTextView;
    private Button cancelBtn;
    private Button confirmBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lost_confirm_pop_up);

        itemnameTextView = findViewById(R.id.itemnameTextView);
        lastseenTextView = findViewById(R.id.lastseenTextView);
        descriptionTextView = findViewById(R.id.descriptionTextView);
        cancelBtn = findViewById(R.id.cancelBtn);
        confirmBtn = findViewById(R.id.confirmBtn);
       // itemnameTextView = itemnameEditText.getText();

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width*.8), (int)(height*0.5));

        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.gravity = Gravity.CENTER;
        params.x = 0;
        params.y = -20;

        getWindow().setAttributes(params);

        confirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             /*   DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference().child("lostItems");
                DatabaseReference item = mDatabase.child(mAuth.getCurrentUser().getUid() + itemName);
                item.child("itemName").setValue(itemName);
                item.child("lastSeenLocation").setValue(lastSeen);
                item.child("description").setValue(description);
                item.child("poster").setValue(currentUserName);
                item.child("dateSubmitted").setValue(currentDateTimeString);
                item.child("status").setValue("Lost");
                Toast.makeText(this, "Submit Successful", Toast.LENGTH_SHORT).show();*/
            }
        });
    }
}
