package naji.ecole.TP23;

import android.content.Intent;
import android.location.Geocoder;
import android.os.Bundle;
import android.util.Log;
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

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import naji.ecole.TP23.bd.ConnexionBD;
import naji.ecole.TP23.bd.Info;
public class MainActivity extends AppCompatActivity {
    Button envoyer;
    Button showmap;
    EditText nom;
    EditText prenom;
    EditText adresse;
    EditText codepostal;
    EditText ville;
    EditText phone;
    List<Pidz> pidz = new ArrayList<>();
    Spinner hand;
    Pidz select;
    TextView perice;
    List<Info> succursalles;
    private ConnexionBD succursallesDBConnexionSource;
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

        Geocoder gc = new Geocoder(this);
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

        // Ajout des produits disponibles
        pidz.addAll(Arrays.asList(
                new Pidz("Peppe From", 1.50),
                new Pidz("Vege", 150),
                new Pidz("Super Sausages' Special 😉", 3.50),
                new Pidz("Mustard Saucer", 35),
                new Pidz("Naji's Special", 200)
        ));

        // Action à réaliser lors du clic sur le bouton "envoyer"
        envoyer.setOnClickListener((View v) -> {
            // Obtenir la succursale la plus proche
            LatLng clienteAd = MapsFragment.getAddressLatLng(gc, String.valueOf(adresse.getText()) + ville.getText() + codepostal.getText());
            if (clienteAd != null) {
                Optional<Info> succursalleCooletProche = succursallesDBConnexionSource.getAllInfos().stream().min((a, b) -> (int) (MapsFragment.getDist(clienteAd, MapsFragment.getAddressLatLng(gc, a.getAdresse())) - MapsFragment.getDist(clienteAd, MapsFragment.getAddressLatLng(gc, b.getAdresse()))));
                startActivity(new Intent(this, EnvoyerActivity.class)
                        .putExtra("nomSucc", succursalleCooletProche.get().getAdresse())
                        .putExtra("phoneSucc", succursalleCooletProche.get().getPhone()));
            }
        });

        // Action à réaliser lors du clic sur le bouton "showmap"
        showmap.setOnClickListener((View v) -> {
            LatLng clienteAd = MapsFragment.getAddressLatLng(gc, String.valueOf(adresse.getText()) + ville.getText() + codepostal.getText());
            if (clienteAd != null) {
                Optional<Info> succursalleCooletProche = succursallesDBConnexionSource.getAllInfos().stream().min((a, b) -> (int) (MapsFragment.getDist(clienteAd, MapsFragment.getAddressLatLng(gc, a.getAdresse())) - MapsFragment.getDist(clienteAd, MapsFragment.getAddressLatLng(gc, b.getAdresse()))));
                startActivity(new Intent(this, MapsFragment.class).putExtra("adresse", succursalleCooletProche.get().getAdresse()));
            }
        });

        // Initialisation de la base de données
        succursallesDBConnexionSource = new ConnexionBD(this);
        succursallesDBConnexionSource.open();
        initBD();

        // Journaliser les informations sur les succursales
        succursallesDBConnexionSource.getAllInfos().forEach(i -> {
            Log.d("lol", i.getId() + " " + i.getAdresse() + " " + i.getPhone());
        });

        // Configurer le Spinner avec l'adaptateur
        ArrayAdapter<Pidz> audioJackToHdmi = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, pidz);
        audioJackToHdmi.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        hand.setAdapter(audioJackToHdmi);

        // Écouteur pour la sélection d'un produit dans le Spinner
        hand.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                select = (Pidz) adapterView.getItemAtPosition(i);
                perice.setText(String.valueOf(select.getPrice()));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                // Aucune action nécessaire
            }
        });
    }

    /**
     * Initialise la base de données avec des informations de succursales par défaut si elle est vide.
     */
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
