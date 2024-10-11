package naji.ecole.TP23;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
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

        // Initialise les TextViews pour afficher les détails du magasin.
        add = findViewById(R.id.succ);
        phone = findViewById(R.id.succNoTel);

        // Récupère le nom du magasin et le numéro de téléphone à partir des extras de l'intention.
        String addresseSucc = getIntent().getStringExtra("nomSucc");
        String notel = getIntent().getStringExtra("phoneSucc");

        // Affiche le nom du magasin et le numéro de téléphone dans les TextViews.
        add.setText(addresseSucc);
        phone.setText(notel);

        // Initialise le bouton retour pour naviguer vers l'activité principale.
        Button retour = findViewById(R.id.retour);
        retour.setOnClickListener((x) -> startActivity(new Intent(this, MainActivity.class)));
    }
}
