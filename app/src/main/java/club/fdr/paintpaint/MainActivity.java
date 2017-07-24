package club.fdr.paintpaint;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    Paint p = new Paint();
    private Context context = this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Display display = getWindowManager().getDefaultDisplay();




    }
    public void changeBrushColor(String hex){
        int hexValue = Integer.decode(hex);
        Log.e("Psuedo Exception!", "DO NOT USE THIS METHOD!");
    }
    public void changeBrushColor(int a, int r, int g, int b){
        Easel easel = (Easel)findViewById(R.id.easel);
        easel.getDrawPaint().setARGB(a,r,g,b);
    }
    public void testClicked(View view){
        Log.e("testClicked", "it was clicked");

        LayoutInflater li = LayoutInflater.from(context);
        View promptsView = li.inflate(R.layout.prompt, null);

        AlertDialog.Builder aDB = new AlertDialog.Builder(context);
        aDB.setView(promptsView);
        final EditText userinput = (EditText)promptsView.findViewById(R.id.promptEditText);

        aDB.setCancelable(false);
        aDB.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                changeHexColorSubmitted();
            }
        })
        .setNegativeButton("Cancel", new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int id){
                dialog.cancel();
            }
        });

        AlertDialog alertDialog = aDB.create();

        alertDialog.show();


        changeBrushColor(0xff, 0x24, 0x55, 0x99);
        Log.e("testClicked","color s3et, method ending");
    }
    private void changeHexColorSubmitted(){
        EditText hexBox = (EditText)findViewById(R.id.promptEditText);
        String hex = hexBox.getText().toString();
        int a,r,g,b;
        a = Integer.decode(hex.substring(0,2));
        r = Integer.decode(hex.substring(2, 4));
        g = Integer.decode(hex.substring(4, 6));
        b = Integer.decode(hex.substring(6, 8));
        Log.e("changeHexColorSubmitted", "a: " + a + " r: " + r + " g: " + g + " b: " + b);
        changeBrushColor(a, r, g, b);
    }
}
