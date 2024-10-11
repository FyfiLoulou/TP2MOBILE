package naji.ecole.TP23;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Button envoyer;
    /**
     * Bouton pour afficher la carte.
     */
    Button showmap;
    EditText nom;
    EditText prenom;
    EditText adresse;
    EditText codepostal;
    EditText ville;
    EditText phone;
    List<Pizza> pidz = new ArrayList<>();
    Spinner hand;
    Pizza select;
    TextView perice;


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
        ville = findViewById(R.id.ville);
        phone = findViewById(R.id.phone);
        hand = findViewById(R.id.hand);
        perice = findViewById(R.id.price);
        pidz.addAll(Arrays.asList(
                new Pizza("Peppe From", 1.50),
                new Pizza("Vege", 150),
                new Pizza("Super Sausages' Special 😉", 3.50),
                new Pizza("Mustard Saucer", 35),
                new Pizza("Naji's Special", 200)
        ));
        // Action à réaliser lors du clic sur le bouton "envoyer"
        envoyer.setOnClickListener((View v) -> {
            startActivity(new Intent(this, EnvoyerActivity.class).putExtra("adresse", String.valueOf(adresse.getText())).putExtra("codePostal", String.valueOf(codepostal.getText())).putExtra("ville", String.valueOf(ville.getText())));
        });

        // Action à réaliser lors du clic sur le bouton "showmap"
        showmap.setOnClickListener((View v) -> {
            startActivity(new Intent(this, MapsFragment.class));
        });

        ArrayAdapter<Pizza> audioJackToHdmi = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, pidz);
        audioJackToHdmi.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        hand.setAdapter(audioJackToHdmi);

        hand.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                select = (Pizza) adapterView.getItemAtPosition(i);
                perice.setText(String.valueOf(select.getPrice()));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
}
