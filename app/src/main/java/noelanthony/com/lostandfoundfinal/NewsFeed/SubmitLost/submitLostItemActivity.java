package noelanthony.com.lostandfoundfinal.NewsFeed.SubmitLost;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
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

import noelanthony.com.lostandfoundfinal.HamburgerFIles.newsFeedActivity;
import noelanthony.com.lostandfoundfinal.R;

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
                showAlertDialog(v);
            }
        });

    }


/*private void submitlostItem(){
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

}*/

public void showAlertDialog(View v){
    final String itemName = itemnameEditText.getText().toString().trim();
    final String lastSeen = lastseenEditText.getText().toString().trim();
    final String description = descriptionEditText.getText().toString().trim();
    final String currentDateTimeString = DateFormat.getDateTimeInstance().format(new Date());


    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    final String currentUserName = user.getDisplayName();

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
    AlertDialog.Builder alert= new AlertDialog.Builder(this);
    alert.setTitle("Confirm");
    alert.setMessage("Item Name: " + itemnameEditText.getText() + "\n Last Seen in: " + lastseenEditText.getText() + "\n Description: " + descriptionEditText.getText());
    alert.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference().child("lostItems");
            DatabaseReference item = mDatabase.child(mAuth.getCurrentUser().getUid() + itemName);
            item.child("itemName").setValue(itemName);
            item.child("lastSeenLocation").setValue(lastSeen);
            item.child("description").setValue(description);
            item.child("poster").setValue(currentUserName);
            item.child("dateSubmitted").setValue(currentDateTimeString);
            item.child("status").setValue("Lost");
            Toast.makeText(getApplicationContext(), "Submission Successful", Toast.LENGTH_SHORT).show();
            Intent startIntent = new Intent(getApplicationContext(),newsFeedActivity.class);
            startActivity(startIntent);

        }
    });
    alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {

        }
    });
    alert.create().show();
}

} // END
