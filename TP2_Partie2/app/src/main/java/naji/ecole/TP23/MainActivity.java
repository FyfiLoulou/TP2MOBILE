package naji.ecole.TP23;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.textfield.TextInputEditText;

import java.util.List;

import naji.ecole.TP23.bd.ConnexionBD;
import naji.ecole.TP23.bd.Info;

public class MainActivity extends AppCompatActivity {

    Button envoyer;
    /** Bouton pour afficher la carte. */
    Button showmap;
    EditText nom;
    EditText prenom;
    EditText adresse;
    EditText codepostal;
    EditText phone;

    List<Info> succursalles;
    private ConnexionBD succursallesDBConnexionSource;

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
                                                                                                    // pas address a la fin
            String addresse = String.valueOf(adresse.getText());
            if (!addresse.isEmpty()) startActivity(new Intent(this, MapsFragment.class).putExtra("adresse", addresse));
        });


        // DATABASE
        succursallesDBConnexionSource = new ConnexionBD(this);
        succursallesDBConnexionSource.open();
        initBD();

        succursallesDBConnexionSource.getAllInfos().forEach(i->{
            Log.d("lol", i.getId()+" "+i.getAdresse()+" "+i.getPhone());
        });










    }

    private void initBD() {
        if (succursallesDBConnexionSource.getAllInfos().isEmpty()) {
            succursallesDBConnexionSource.createInfo("Domino's Pizza, 3400 1re Av., Québec, QC G1L 3R5", "549 067 2771");
            succursallesDBConnexionSource.createInfo("Domino's Pizza, 680 Bd Wilfrid-Hamel, Québec, QC G1M 2P9", "679 988 1965");
            succursallesDBConnexionSource.createInfo("Domino's Pizza, 2673 Ch Ste-Foy, Québec City, Quebec G1V 1V3", "579 888 4274");
            succursallesDBConnexionSource.createInfo("Domino's Pizza, 9065 Bd de l'Ormière, Québec, QC G2B 3K2", "579 344 4600");
            succursallesDBConnexionSource.createInfo("Domino's Pizza, 137 Main St E, North Bay, ON P1B 1A9", "417 999 4555");
        }
    }

}
