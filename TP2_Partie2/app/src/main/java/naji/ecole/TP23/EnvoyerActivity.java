package naji.ecole.TP23;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class EnvoyerActivity extends AppCompatActivity {
    String ville;
    String adresse;
    String codePostal;
    TextView add;
    TextView phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.envoyer_activity);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        add = findViewById(R.id.succ);
        phone = findViewById(R.id.succNoTel);

        String addresseSucc = getIntent().getStringExtra("nomSucc");
        String notel = getIntent().getStringExtra("phoneSucc");

        add.setText(addresseSucc);
        phone.setText(notel);
    }
}
