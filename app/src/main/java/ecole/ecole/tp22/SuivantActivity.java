package ecole.ecole.tp22;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SuivantActivity extends AppCompatActivity {

    ImageView selectedThing;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.suivant_activity);
        selectedThing = findViewById(R.id.selectedThing);


        byte[] byteArray = getIntent().getByteArrayExtra("bitmap");
        if (byteArray != null) {
            Bitmap bitmap = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
            selectedThing.setImageBitmap(bitmap);
        }
    }
}
