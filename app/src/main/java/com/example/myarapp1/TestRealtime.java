package com.example.myarapp1;

import android.net.Uri;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.ar.core.Anchor;
import com.google.ar.sceneform.AnchorNode;
import com.google.ar.sceneform.assets.RenderableSource;
import com.google.ar.sceneform.rendering.ModelRenderable;
import com.google.ar.sceneform.ux.ArFragment;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class TestRealtime extends AppCompatActivity {

    private ArFragment arFragment;
    private String ASSET_3D = "https://raw.githubusercontent.com/MaiHuongSusi/3dModel/master/model.gltf";

    // Access a Cloud Firestore instance from your Activity
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    ArrayList<Object> listObjects = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_realtime);

//        Map<String, Object> model = new HashMap<>();
//        model.put("link_gltf", "https://raw.githubusercontent.com/MaiHuongSusi/3dModel/master/crayfish/crayfish.gltf");
//        model.put("image", "https://github.com/MaiHuongSusi/3dModel/blob/master/crayfish/crayfish.png");
//        model.put("name", "Crayfish");
//
//// Add a new document with a generated ID
//        db.collection("3d_model")
//                .add(model)
//                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
//                    @Override
//                    public void onSuccess(DocumentReference documentReference) {
//                        Toast.makeText(TestRealtime.this, "Success", Toast.LENGTH_SHORT).show();
//                    }
//                })
//                .addOnFailureListener(new OnFailureListener() {
//                    @Override
//                    public void onFailure(@NonNull Exception e) {
//                        Toast.makeText(TestRealtime.this, "Fail", Toast.LENGTH_SHORT).show();
//                    }
//                });

//        DocumentReference docRef = db.collection("3d_model").document("LBczEWxCHNNDfCkgmLrh");
//        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
//            @Override
//            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
//                if (task.isSuccessful()) {
//                    DocumentSnapshot document = task.getResult();
//                    if (document.exists()) {
//                        Toast.makeText(TestRealtime.this, "documentdata: " + document.getData(), Toast.LENGTH_SHORT).show();
//                    } else {
//                        Toast.makeText(TestRealtime.this, "No search document", Toast.LENGTH_SHORT).show();
//                    }
//                } else {
//                    Toast.makeText(TestRealtime.this, "get fail with " + task.getException(), Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
        db.collection("3d_model").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        Object object = document.toObject(Object.class);
                        listObjects.add(object);
                    }
                } else {
                    Toast.makeText(TestRealtime.this, "Fail to load data from Firebase", Toast.LENGTH_SHORT).show();
                }
            }
        });
        arFragment = (ArFragment) getSupportFragmentManager().findFragmentById(R.id.testRealtimeFragment);

        arFragment.setOnTapArPlaneListener((hitResult, plane, motionEvent) -> {

            placeModel(hitResult.createAnchor());
        });
    }

    private void placeModel(Anchor anchor) {
        ModelRenderable
                .builder()
                .setSource(
                        this, RenderableSource
                                .builder()
                                .setSource(this, Uri.parse(ASSET_3D), RenderableSource.SourceType.GLTF2)
                                .setScale(0.2f)
                                .setRecenterMode(RenderableSource.RecenterMode.ROOT)
                                .build()
                ).setRegistryId(ASSET_3D)
                .build()
                .thenAccept(modelRenderable -> addNodeToScene(modelRenderable, anchor))
                .exceptionally(throwable -> {
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setMessage(throwable.getMessage()).show();
                    Toast.makeText(this, "fail", Toast.LENGTH_SHORT).show();
                    return null;
                });
    }

    private void addNodeToScene(ModelRenderable modelRenderable, Anchor anchor) {
        AnchorNode anchorNode = new AnchorNode(anchor);
        anchorNode.setRenderable(modelRenderable);
//        arFragment.getArSceneView().getScene().addChild(anchorNode);
        anchorNode.setParent(arFragment.getArSceneView().getScene());
    }
}
