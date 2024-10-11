package naji.ecole.TP23;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {

    Button envoyer;
    /** Bouton pour afficher la carte. */
    Button showmap;
    EditText nom;
    EditText prenom;
    EditText adresse;
    EditText codepostal;
    EditText phone;

    /**
     * Méthode appelée lors de la création de l'activité.
     * Cette méthode initialise l'interface utilisateur, configure les éléments
     * d'interaction et applique les insets de fenêtre.
     *
     * @param savedInstanceState Bundle contenant l'état de l'activité précédemment enregistrée.
     */
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

        envoyer = findViewById(R.id.envoyer);
        showmap = findViewById(R.id.showmap);
        nom = findViewById(R.id.nom);
        prenom = findViewById(R.id.prenom);
        adresse = findViewById(R.id.adresse);
        codepostal = findViewById(R.id.codepostal);
        phone = findViewById(R.id.phone);

        // Action à réaliser lors du clic sur le bouton "envoyer"
        envoyer.setOnClickListener((View v) -> {

        });

        // Action à réaliser lors du clic sur le bouton "showmap"
        showmap.setOnClickListener((View v) -> {
            startActivity(new Intent(this, MapsFragment.class));
        });
    }
}
