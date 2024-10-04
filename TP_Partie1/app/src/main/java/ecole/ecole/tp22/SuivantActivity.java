package ecole.ecole.tp22;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class SuivantActivity extends AppCompatActivity {

    ImageView image;
    MediaPlayer mediaPlayer;

    int[] songs = {R.raw.song1, R.raw.song2, R.raw.song3};
    Button back;
    TextView selection;

    public static int random(int min, int max) {
        return new Random().nextInt(max - min + 1) + min;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.suivant_activity);
        image = findViewById(R.id.selectedThing);

        back = findViewById(R.id.retour);
        selection = findViewById(R.id.textView2);

        selection.setText(String.valueOf(getIntent().getIntExtra("index", -1)));

        byte[] byteArray = getIntent().getByteArrayExtra("bitmap");
        Bitmap bitmap = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
        image.setImageBitmap(bitmap);

        // MUSICAL MUSIC
        mediaPlayer = MediaPlayer.create(this, songs[random(0, songs.length-1)]);
        mediaPlayer.start();

        back.setOnClickListener((View v)->{
            mediaPlayer.stop();
            mediaPlayer.prepareAsync();
            startActivity(new Intent(this, MainActivity.class));
        });
    }
}
