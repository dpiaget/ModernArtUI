package course.example.modernartui;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.graphics.Color;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.SeekBar;
import android.widget.TextView;


public class ModernArtUI extends AppCompatActivity {


    private DialogFragment mDialog;
    static private final String URL = "https://www.moma.org/";

    TextView mView1;
    TextView mView2;
    TextView mView3;
    TextView mView4;
    TextView mView5;
    TextView mView7;
    SeekBar seekBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modern_art_ui);

        mView1 = (TextView) findViewById(R.id.view_1);
        mView2 = (TextView) findViewById(R.id.view_2);
        mView3 = (TextView) findViewById(R.id.view_3);
        mView4 = (TextView) findViewById(R.id.view_4);
        mView5 = (TextView) findViewById(R.id.view_5);
        mView7 = (TextView) findViewById(R.id.view_7);

        seekBar = (SeekBar) findViewById(R.id.seekBar);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int increment, boolean fromUser) {

                mView1.setBackgroundColor(Color.rgb(0+increment*2,191-increment*2,255-increment*2));
                mView2.setBackgroundColor(Color.rgb(247-increment*2,0+increment*2,255-increment*2));
                mView3.setBackgroundColor(Color.rgb(255-increment*2,0+increment*2,0+increment*2));
                mView4.setBackgroundColor(Color.rgb(60+increment*2,255-increment*2,0+increment*2));
                mView5.setBackgroundColor(Color.rgb(78+increment*2,0+increment*2,247-increment*2));
                mView7.setBackgroundColor(Color.rgb(242-increment*2,255-increment*2,0+increment*2));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }
            //if you touch the seek bar what will happen??
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }



        });
    }
    // Create option menu More Information
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    // Selection item of menu
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            //launch dialog box
            case R.id.more_information:
                 mDialog = MoreInfoDialog.newInstance();
                 mDialog.show(getFragmentManager(), "Dialog More Information");
                return true;
            default:
                return false;
        }
    }

    //
    private void continueMoreInformation(boolean moreInformationRequired){
        if (moreInformationRequired){
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(URL));
            startActivity(intent);
        }

        else{
           mDialog.dismiss();
        }
    }



    public static class MoreInfoDialog extends DialogFragment{

        public static MoreInfoDialog newInstance(){ return new MoreInfoDialog(); }

        // Build MoreInfo using MoreInfoDialog.builder
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            return new AlertDialog.Builder(getActivity())
                    .setMessage("Inspired by the works of artists such as Piet Mondrian and Ben Nicholson.\n\nClick here to learn more" )

                    // User cannot dismiss dialog by hitting back button
                    .setCancelable(false)

                    // Set up No Button
                    .setNegativeButton("Visit MOMA",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog,
                                                    int id) {
                                    ((ModernArtUI) getActivity())
                                            .continueMoreInformation(true);
                                }
                            })

                    // Set up Yes Button
                    .setPositiveButton("Not NOW",
                            new DialogInterface.OnClickListener() {
                                public void onClick(
                                        final DialogInterface dialog, int id) {
                                    ((ModernArtUI) getActivity())
                                            .continueMoreInformation(false);
                                }
                            }).create();
        }
    }
}
