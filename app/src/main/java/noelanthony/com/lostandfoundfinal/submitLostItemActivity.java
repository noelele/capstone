package noelanthony.com.lostandfoundfinal;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.util.Date;

public class submitLostItemActivity extends AppCompatActivity {

    private EditText itemnameEditText;
    private EditText lastseenEditText;
    private EditText descriptionEditText;
    private ImageButton uploadImageButton;
    private Button submitLostButton;
    private ProgressBar progressBar;
    private DatabaseReference mDatabase;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit_lost_item);

        itemnameEditText = findViewById(R.id.itemnameEditText);
        lastseenEditText = findViewById(R.id.lastseenEditText);
        descriptionEditText = findViewById(R.id.descriptionEditText);
        uploadImageButton = findViewById(R.id.uploadImageButton);
        submitLostButton = findViewById(R.id.submitLostBtn);
        progressBar = findViewById(R.id.progressbar);
        mAuth = FirebaseAuth.getInstance();

        submitLostButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitlostItem();
            }
        });

    }


private void submitlostItem(){

    String itemName = itemnameEditText.getText().toString().trim();
    String lastSeen = lastseenEditText.getText().toString().trim();
    String description = descriptionEditText.getText().toString().trim();
    String currentDateTimeString = DateFormat.getDateTimeInstance().format(new Date());


    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    String currentUserName = user.getDisplayName();

    //check if name is empty
    if(itemName.isEmpty()){
        itemnameEditText.setError("Item name is required");
        itemnameEditText.requestFocus();
        return;
    }

    if(description.isEmpty()){
        descriptionEditText.setError("Description is required");
        descriptionEditText.requestFocus();
        return;
    }

   DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference().child("lostItems");
    DatabaseReference item = mDatabase.child(mAuth.getCurrentUser().getUid() + itemName);
    item.child("itemName").setValue(itemName);
    item.child("lastSeenLocation").setValue(lastSeen);
    item.child("description").setValue(description);
    item.child("poster").setValue(currentUserName);
    item.child("dateSubmitted").setValue(currentDateTimeString);
    item.child("status").setValue("Lost");
    Toast.makeText(this, "Submit Successful", Toast.LENGTH_SHORT).show();

    //Intent intent = new Intent(getApplicationContext(), mySubmissionsActivity.class);
    //startActivity(intent);


}

} // END
