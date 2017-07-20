package club.fdr.paintpaint;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Display;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Display display = getWindowManager().getDefaultDisplay();

        int width = display.getWidth(), height = display.getHeight();
        Bitmap.Config conf = Bitmap.Config.ARGB_8888;
        Bitmap bm = Bitmap.createBitmap(width, height, conf);

        Canvas canvas = new Canvas(bm);
        Paint p = new Paint();
        p.setColor(0xff00ff);
        canvas.drawCircle(canvas.getWidth()/2, canvas.getHeight()/2, 50, p );


    }
}
