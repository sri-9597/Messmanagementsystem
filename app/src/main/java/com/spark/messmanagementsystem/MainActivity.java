package com.spark.messmanagementsystem;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private static final String AUTHOR_TEXT = "author";
    private static final String QUOTE_TEXT = "quote";
    private static final String TAG = "firestore";

    private DocumentReference mDocRef = FirebaseFirestore.getInstance().document("sampleData/inspiration");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onSave(View view) {
        EditText quoteView = findViewById(R.id.quoteField);
        EditText authorView = findViewById(R.id.authorField);
        String quoteText = quoteView.toString();
        String authorText = authorView.toString();

        Map<String, Object> dataToSave = new HashMap<String ,Object>();
        dataToSave.put(QUOTE_TEXT, quoteText);
        dataToSave.put(AUTHOR_TEXT, authorText);
        mDocRef.set(dataToSave).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Log.d(TAG, "Document inserted successfuly");
                }
                else
                    Log.d(TAG, "Oh! No " + task.getException());
            }
        });
    }
}
