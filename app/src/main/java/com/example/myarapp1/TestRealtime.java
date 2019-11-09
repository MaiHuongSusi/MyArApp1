package com.example.myarapp1;

import android.net.Uri;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
    private String ASSET_3D = "https://raw.githubusercontent.com/MaiHuongSusi/3dModel/master/ocean/crab/Mesh_Crab.gltf";

    // Access a Cloud Firestore instance from your Activity
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    ArrayList<Object> listObjects = new ArrayList<>();
    private ArrayList<String> listName = new ArrayList<>();
    private ArrayList<String> listImg = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_realtime);

        ArrayList<Object> listObjects = new ArrayList<>();
        addDataToFirebase();

        db.collection("ocean").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        Object object = document.toObject(Object.class);
                        listObjects.add(object);
                        listName.add(object.getName());
                        listImg.add(object.getImage());
                    }
                } else {
                    Toast.makeText(TestRealtime.this, "Fail to load data from Firebase", Toast.LENGTH_SHORT).show();
                }
                initRecyclerView();

            }
        });
        arFragment = (ArFragment) getSupportFragmentManager().findFragmentById(R.id.testRealtimeFragment);

        arFragment.setOnTapArPlaneListener((hitResult, plane, motionEvent) -> {

            placeModel(hitResult.createAnchor());
        });
    }
    private void initRecyclerView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        RecyclerView recyclerView = findViewById(R.id.recylerview);
        recyclerView.setLayoutManager(layoutManager);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this, listName, listImg);
        recyclerView.setAdapter(adapter);
    }

    private void addDataToFirebase() {
//        Object[] list = {
//                new Object("Crab", "https://raw.githubusercontent.com/MaiHuongSusi/3dModel/master/ocean/crab/Mesh_Crab.gltf", "https://raw.githubusercontent.com/MaiHuongSusi/3dModel/master/ocean/crab/crab.png"),
//                new Object("Crayfish", "https://raw.githubusercontent.com/MaiHuongSusi/3dModel/master/ocean/crayfish/crayfish.gltf", "https://raw.githubusercontent.com/MaiHuongSusi/3dModel/master/ocean/crayfish/crayfish.png"),
//                new Object("Dolphin", "https://raw.githubusercontent.com/MaiHuongSusi/3dModel/master/ocean/dolphin/NOVELO_DOLPHIN.gltf", "https://raw.githubusercontent.com/MaiHuongSusi/3dModel/master/ocean/dolphin/dolphin.png"),
//                new Object("Jellyfish", "https://raw.githubusercontent.com/MaiHuongSusi/3dModel/master/ocean/jellyfish/model.gltf", "https://raw.githubusercontent.com/MaiHuongSusi/3dModel/master/ocean/jellyfish/jellyfish.png"),
//                new Object("Octopus", "https://raw.githubusercontent.com/MaiHuongSusi/3dModel/master/ocean/octopus/octopus.gltf", "https://raw.githubusercontent.com/MaiHuongSusi/3dModel/master/ocean/octopus/octopus.png"),
//                new Object("Seahorse", "https://raw.githubusercontent.com/MaiHuongSusi/3dModel/master/ocean/seahorse/10044_SeaHorse_v1_iterations-2.gltf", "https://raw.githubusercontent.com/MaiHuongSusi/3dModel/master/ocean/seahorse/seahorse.png"),
//                new Object("Shark", "https://raw.githubusercontent.com/MaiHuongSusi/3dModel/master/ocean/shark/scene.gltf", "https://raw.githubusercontent.com/MaiHuongSusi/3dModel/master/ocean/shark/shark.png"),
//                new Object("Squid", "https://raw.githubusercontent.com/MaiHuongSusi/3dModel/master/ocean/squid/giant_squid.gltf", "https://raw.githubusercontent.com/MaiHuongSusi/3dModel/master/ocean/squid/squid.png"),
//                new Object("Starfish", "https://raw.githubusercontent.com/MaiHuongSusi/3dModel/master/ocean/starfish/starfish.gltf", "https://raw.githubusercontent.com/MaiHuongSusi/3dModel/master/ocean/starfish/starfish.png"),
//                new Object("Turtle", "https://raw.githubusercontent.com/MaiHuongSusi/3dModel/master/ocean/turtle/turtle.gltf", "https://raw.githubusercontent.com/MaiHuongSusi/3dModel/master/ocean/turtle/turtle.png")
//        };
//        for (int i=0; i<10; i++) {
//            Map<String, String> model = new HashMap<>();
//            model.put("link_gltf", list[i].getLinkGltf());
//            model.put("image", list[i].getImage());
//            model.put("name", list[i].getName());
//
//            db.collection("ocean")
//                    .add(model)
//                    .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
//                        @Override
//                        public void onSuccess(DocumentReference documentReference) {
//                            Toast.makeText(TestRealtime.this, "Success", Toast.LENGTH_SHORT).show();
//                        }
//                    })
//                    .addOnFailureListener(new OnFailureListener() {
//                        @Override
//                        public void onFailure(@NonNull Exception e) {
//                            Toast.makeText(TestRealtime.this, "Fail", Toast.LENGTH_SHORT).show();
//                        }
//                    });
//        }
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
