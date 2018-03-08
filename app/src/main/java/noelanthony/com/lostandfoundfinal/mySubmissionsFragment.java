package noelanthony.com.lostandfoundfinal;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import noelanthony.com.lostandfoundfinal.loginANDregister.MainActivity;

/**
 * Created by Noel on 08/03/2018.
 */

public class mySubmissionsFragment extends Fragment{

    Context applicationContext = MainActivity.getContextOfApplication();
    View myView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.mysubmissions_layout,container,false);
        return myView;

    }
}
