package com.example.lab8_threads_async_task;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    // Composants de l'interface utilisateur
    private TextView statusLabel;
    private ProgressBar taskProgressBar;
    private ImageView resultImage;

    // Gestionnaire pour communiquer avec le thread principal (UI)
    private Handler uiHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 1. Initialisation des composants (Liaison avec le XML)
        statusLabel = findViewById(R.id.status_label);
        taskProgressBar = findViewById(R.id.task_progress_bar);
        resultImage = findViewById(R.id.result_image);

        Button startThreadButton = findViewById(R.id.start_thread_button);
        Button startAsyncTaskButton = findViewById(R.id.start_async_task_button);
        Button showNotificationButton = findViewById(R.id.show_notification_button);

        // 2. Préparation du Handler pour les mises à jour UI
        uiHandler = new Handler(Looper.getMainLooper());

        // 3. Action : Afficher un message (Test de réactivité)
        showNotificationButton.setOnClickListener(v ->
                Toast.makeText(this, "L'interface est toujours réactive !", Toast.LENGTH_SHORT).show()
        );

        // 4. Action : Charger une image via un Thread séparé
        startThreadButton.setOnClickListener(v -> runImageLoadingThread());

        // 5. Action : Lancer un calcul lourd via AsyncTask
        startAsyncTaskButton.setOnClickListener(v -> new HeavyComputationTask().execute());
    }

    /**
     * Simule le chargement d'une image dans un thread de fond.
     */
    private void runImageLoadingThread() {
        // Préparation visuelle
        taskProgressBar.setVisibility(View.VISIBLE);
        taskProgressBar.setProgress(0);
        statusLabel.setText("Chargement de l'image en cours...");

        new Thread(() -> {
            try {
                // On simule une attente (ex: téléchargement)
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // On récupère l'icône de l'application
            Bitmap icon = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);

            // Retour impératif sur le thread principal pour modifier l'UI
            uiHandler.post(() -> {
                resultImage.setImageBitmap(icon);
                taskProgressBar.setVisibility(View.INVISIBLE);
                statusLabel.setText("Image chargée avec succès.");
            });
        }).start();
    }

    /**
     * Tâche asynchrone pour effectuer un calcul intensif sans bloquer l'application.
     */
    private class HeavyComputationTask extends AsyncTask<Void, Integer, Long> {

        @Override
        protected void onPreExecute() {
            taskProgressBar.setVisibility(View.VISIBLE);
            taskProgressBar.setProgress(0);
            statusLabel.setText("Calcul complexe en cours...");
        }

        @Override
        protected Long doInBackground(Void... params) {
            long totalResult = 0;

            for (int progress = 1; progress <= 100; progress++) {
                // Simulation d'une opération mathématique lourde
                for (int j = 0; j < 500000; j++) {
                    totalResult += (progress * j) % 13;
                }
                // Mise à jour de la barre de progression
                publishProgress(progress);
            }
            return totalResult;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            taskProgressBar.setProgress(values[0]);
        }

        @Override
        protected void onPostExecute(Long result) {
            taskProgressBar.setVisibility(View.INVISIBLE);
            statusLabel.setText("Calcul terminé. Résultat : " + result);
        }
    }
}