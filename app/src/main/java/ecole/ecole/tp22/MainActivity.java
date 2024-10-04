package ecole.ecole.tp22;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.PorterDuff;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    List<ImageView> listElImg;
    int[] listImg = new int[]{-1, -1, -1, -1};

    ImageView selectedImg;
    int selectedImgIndex = -1;

    int[] imgSrc = new int[]{R.drawable.img1, R.drawable.img2, R.drawable.img3, R.drawable.img4, R.drawable.img5, R.drawable.img6, R.drawable.img7, R.drawable.img8, R.drawable.img9, R.drawable.img10};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        listElImg = new ArrayList<>(Arrays.asList(
                findViewById(R.id.image1),
                findViewById(R.id.image2),
                findViewById(R.id.image3),
                findViewById(R.id.image4)
        ));

        ConstraintLayout myLayout = findViewById(R.id.main);
        myLayout.setOnClickListener(v -> {
            listElImg.forEach((x) -> {
                int index = random(0, imgSrc.length - 1);
                x.setImageResource(imgSrc[index]);
                listImg[listElImg.indexOf(x)] = imgSrc[index];
                Log.w("lol", listElImg.indexOf(x)+" "+index);
            });
            //for (int i = 0; i < listImg.length; i++) listImg[i] = index;

        });

        listElImg.forEach(img->{
            img.setOnClickListener(v->{
                selectedImgIndex = listImg[listElImg.indexOf(img)];
                Log.w("lol", String.valueOf(selectedImgIndex));
                if (selectedImgIndex > -1) {
                    selectedImg = img;

                    listElImg.forEach(x->x.clearColorFilter());
                    img.setColorFilter(0x80FF0000, PorterDuff.Mode.SRC_ATOP);
                }
            });
        });


        findViewById(R.id.suivant).setOnClickListener(v->{
            if (selectedImg != null && selectedImgIndex > -1) startActivity(putImageInIntent(new Intent(this, SuivantActivity.class).putExtra("selectedImgIndex", selectedImgIndex), selectedImg, "captionTestImgidk"));
        });
    }

    public static int random(int min, int max) {
        return new Random().nextInt(max - min + 1) + min;
    }

    private Intent putImageInIntent(Intent intent, ImageView image, String caption){
        Bitmap bitmap = ((BitmapDrawable) image.getDrawable()).getBitmap();
        ByteArrayOutputStream bs = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 50, bs);
        intent.putExtra("bitmap", bs.toByteArray());
        intent.putExtra("caption", caption);
        return intent;
    }


}