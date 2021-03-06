package club.fdr.paintpaint;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    private final int minBrushSize = 10;
    private final int maxBrushSize = 10;
    private ImageButton clear;
    private ImageButton newFile;
    private ImageButton brushToggleColor;
    private Easel easel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        clear = (ImageButton)findViewById(R.id.eraser);
        newFile = (ImageButton)findViewById(R.id.newPaint);
        brushToggleColor = (ImageButton)findViewById(R.id.brushColorToggle);
        easel = (Easel)findViewById(R.id.easel);

    }

    public void brushSizeClicked(View view){
        float size = Float.parseFloat((String)(view.getTag()));
        Log.e("brush width", "" + size);
        easel.setBrushSize(size);
    }

    public void Clear(View view){

        easel.getDrawCanvas().drawColor(Color.WHITE);

    }
    public void Save(View view){
        storeImage(easel.getCanvasBitmap());
    }

    private void storeImage(Bitmap image) {
        File pictureFile = getOutputMediaFile();
        if (pictureFile == null) {
            Log.d("Error",
                    "Error creating media file, check storage permissions: ");// e.getMessage());
            return;
        }
        try {
            FileOutputStream fos = new FileOutputStream(pictureFile);
            image.compress(Bitmap.CompressFormat.PNG, 90, fos);
            fos.close();
        } catch (FileNotFoundException e) {
            Log.d("Error", "File not found: " + e.getMessage());
        } catch (IOException e) {
            Log.d("Error", "Error accessing file: " + e.getMessage());
        }
    }

    private  File getOutputMediaFile(){
        File mediaStorageDir = new File(getApplicationContext().getFilesDir().getPath()
                + "/Android/data/"
                + getApplicationContext().getPackageName()
                + "/Files");

        // This location works best if you want the created images to be shared
        // between applications and persist after your app has been uninstalled.

        // Create the storage directory if it does not exist
        if (! mediaStorageDir.exists()){
            if (! mediaStorageDir.mkdirs()){
                return null;
            }
        }
        // Create a media file name
        String timeStamp = new SimpleDateFormat("ddMMyyyy_HHmm").format(new Date());
        File mediaFile;
        String mImageName="MI_"+ timeStamp +".jpg";
        mediaFile = new File(mediaStorageDir.getPath() + File.separator + mImageName);
        return mediaFile;
    }



}
