package com.example.myarapp1;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.ar.core.Anchor;
import com.google.ar.core.HitResult;
import com.google.ar.core.Plane;
import com.google.ar.sceneform.AnchorNode;
import com.google.ar.sceneform.math.Vector3;
import com.google.ar.sceneform.rendering.ModelRenderable;
import com.google.ar.sceneform.rendering.ViewRenderable;
import com.google.ar.sceneform.ux.ArFragment;
import com.google.ar.sceneform.ux.BaseArFragment;
import com.google.ar.sceneform.ux.TransformableNode;

public class ListObjectActivity extends AppCompatActivity {

    private static final String TAG = ListObjectActivity.class.getSimpleName();

    private ArFragment arFragment;
    private ModelRenderable foxRenderable, bikeRenderable, catRenderable, dogRenderable, knifeRenderable, mushroomRenderable, trainRenderable, zebraRenderable;
    ImageView fox, bike, cat, dog, knife, mushroom, train, zebra;
    View arrayView[];
    ViewRenderable name_object;
    int selected = 1;

    @Override
    @SuppressWarnings({"AndroidApiChecker", "FutureReturnValueIgnored"})
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_object);

        // Get topic name
        Intent intent = getIntent();
        String name = intent.getStringExtra(ListTopicActivity.TOPIC);
        Toast.makeText(this, name, Toast.LENGTH_SHORT).show();

        //Anh xa
        map();

        //Set ArrayView
        setArrayView();

        //click each item
        setClickListener();

        //Setup 3D model
        setupModel();

        arFragment.setOnTapArPlaneListener(new BaseArFragment.OnTapArPlaneListener() {
            @Override
            public void onTapPlane(HitResult hitResult, Plane plane, MotionEvent motionEvent) {
                    Anchor anchor = hitResult.createAnchor();
                    AnchorNode anchorNode = new AnchorNode(anchor);
                    anchorNode.setParent(arFragment.getArSceneView().getScene());

                    createModel(anchorNode, selected);
            }
        });
    }

    private void setupModel() {
        ViewRenderable.builder()
                .setView(this, R.layout.name_object)
                .build()
                .thenAccept(viewRenderable -> name_object = viewRenderable);
        ModelRenderable.builder()
                .setSource(this, R.raw.arctic_fox_posed)
                .build().thenAccept(modelRenderable -> foxRenderable = modelRenderable)
                .exceptionally(throwable -> {
                    Toast.makeText(this, "Unable to load fox model", Toast.LENGTH_SHORT).show();
                    return null;
                });
        ModelRenderable.builder()
                .setSource(this, R.raw.bike)
                .build().thenAccept(modelRenderable -> bikeRenderable = modelRenderable)
                .exceptionally(throwable -> {
                    Toast.makeText(this, "Unable to load bike model", Toast.LENGTH_SHORT).show();
                    return null;
                });
        ModelRenderable.builder()
                .setSource(this, R.raw.cat)
                .build().thenAccept(modelRenderable -> catRenderable = modelRenderable)
                .exceptionally(throwable -> {
                    Toast.makeText(this, "Unable to load cat model", Toast.LENGTH_SHORT).show();
                    return null;
                });
        ModelRenderable.builder()
                .setSource(this, R.raw.dog)
                .build().thenAccept(modelRenderable -> dogRenderable = modelRenderable)
                .exceptionally(throwable -> {
                    Toast.makeText(this, "Unable to load dog model", Toast.LENGTH_SHORT).show();
                    return null;
                });
        ModelRenderable.builder()
                .setSource(this, R.raw.knife)
                .build().thenAccept(modelRenderable -> knifeRenderable = modelRenderable)
                .exceptionally(throwable -> {
                    Toast.makeText(this, "Unable to load knife model", Toast.LENGTH_SHORT).show();
                    return null;
                });
        ModelRenderable.builder()
                .setSource(this, R.raw.mushroom)
                .build().thenAccept(modelRenderable -> mushroomRenderable = modelRenderable)
                .exceptionally(throwable -> {
                    Toast.makeText(this, "Unable to load mushroom model", Toast.LENGTH_SHORT).show();
                    return null;
                });
        ModelRenderable.builder()
                .setSource(this, R.raw.train)
                .build().thenAccept(modelRenderable -> trainRenderable = modelRenderable)
                .exceptionally(throwable -> {
                    Toast.makeText(this, "Unable to load train model", Toast.LENGTH_SHORT).show();
                    return null;
                });
        ModelRenderable.builder()
                .setSource(this, R.raw.zebra)
                .build().thenAccept(modelRenderable -> zebraRenderable = modelRenderable)
                .exceptionally(throwable -> {
                    Toast.makeText(this, "Unable to load zebra model", Toast.LENGTH_SHORT).show();
                    return null;
                });
    }

    private void map() {
        arFragment = (ArFragment) getSupportFragmentManager().findFragmentById(R.id.arFragment);
        fox = findViewById(R.id.fox);
        bike = findViewById(R.id.bike);
        cat = findViewById(R.id.cat);
        dog = findViewById(R.id.dog);
        knife = findViewById(R.id.knife);
        mushroom = findViewById(R.id.mushroom);
        train = findViewById(R.id.train);
        zebra = findViewById(R.id.zebra);
    }

    private void setArrayView() {
        arrayView = new View[]{
                fox, bike, cat, dog, knife, mushroom, zebra
        };
    }

    private void setClickListener() {
        for(int i=0; i<arrayView.length; i++){
            arrayView[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(v.getId() == R.id.fox){
                        selected = 1;
                        setBackground(v.getId());
                    } else if(v.getId() == R.id.bike) {
                        selected = 2;
                        setBackground(v.getId());
                    } else if(v.getId() == R.id.cat) {
                        selected = 3;
                        setBackground(v.getId());
                    } else if(v.getId() == R.id.dog) {
                        selected = 4;
                        setBackground(v.getId());
                    } else if(v.getId() == R.id.knife) {
                        selected = 5;
                        setBackground(v.getId());
                    } else if(v.getId() == R.id.mushroom) {
                        selected = 6;
                        setBackground(v.getId());
                    } else if(v.getId() == R.id.train) {
                        selected = 7;
                        setBackground(v.getId());
                    } else {
                        selected = 8;
                        setBackground(v.getId());
                    }
                }
            });
        }
    }

    private void createModel(AnchorNode anchorNode, int selected) {
        if(selected==1){
            TransformableNode fox = new TransformableNode(arFragment.getTransformationSystem());
            fox.setParent(anchorNode);
            fox.setRenderable(foxRenderable);
            fox.select();
            addName(anchorNode, fox, "Arctic Fox");
        }
        if(selected==2){
            TransformableNode fox = new TransformableNode(arFragment.getTransformationSystem());
            fox.setParent(anchorNode);
            fox.setRenderable(bikeRenderable);
            fox.select();
            addName(anchorNode, fox, "Bike");
        }
        if(selected==3){
            TransformableNode fox = new TransformableNode(arFragment.getTransformationSystem());
            fox.setParent(anchorNode);
            fox.setRenderable(catRenderable);
            fox.select();
            addName(anchorNode, fox, "Cat");
        }
        if(selected==4){
            TransformableNode fox = new TransformableNode(arFragment.getTransformationSystem());
            fox.setParent(anchorNode);
            fox.setRenderable(dogRenderable);
            fox.select();
            addName(anchorNode, fox, "Dog");
        }
        if(selected==5){
            TransformableNode fox = new TransformableNode(arFragment.getTransformationSystem());
            fox.setParent(anchorNode);
            fox.setRenderable(knifeRenderable);
            fox.select();
            addName(anchorNode, fox, "Knife");
        }
        if(selected==6){
            TransformableNode fox = new TransformableNode(arFragment.getTransformationSystem());
            fox.setParent(anchorNode);
            fox.setRenderable(mushroomRenderable);
            fox.select();
            addName(anchorNode, fox, "Mushroom");
        }
        if(selected==7){
            TransformableNode fox = new TransformableNode(arFragment.getTransformationSystem());
            fox.setParent(anchorNode);
            fox.setRenderable(trainRenderable);
            fox.select();
            addName(anchorNode, fox, "Train");
        }
        if(selected==8){
            TransformableNode fox = new TransformableNode(arFragment.getTransformationSystem());
            fox.setParent(anchorNode);
            fox.setRenderable(zebraRenderable);
            fox.select();
            addName(anchorNode, fox, "Zebra");
        }
    }

    private void addName(AnchorNode anchorNode, TransformableNode model, String name) {
        ViewRenderable.builder()
                .setView(this, R.layout.name_object)
                .build()
                .thenAccept(viewRenderable -> {
                    TransformableNode nameView = new TransformableNode(arFragment.getTransformationSystem());
                    nameView.setLocalPosition(new Vector3(0f, model.getLocalPosition().y+0.5f, 0));
                    nameView.setParent(anchorNode);
                    nameView.setRenderable(viewRenderable);
                    nameView.select();

                    //set text
                    TextView textView = (TextView) viewRenderable.getView();
                    textView.setText(name);
                    textView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            anchorNode.setParent(null);
                        }
                    });
                });

    }

    private void setBackground(int id) {
        for(int i =0; i<arrayView.length;i++){
            if(arrayView[i].getId() == id){
                arrayView[i].setBackgroundColor(Color.parseColor("#80333639"));
            } else {
                arrayView[i].setBackgroundColor(Color.TRANSPARENT);
            }
        }
    }


    /**
     * Returns false and displays an error message if Sceneform can not run, true if Sceneform can run
     * on this device.
     *
     * <p>Sceneform requires Android N on the device as well as OpenGL 3.0 capabilities.
     *
     * <p>Finishes the activity if Sceneform can not run
     */
//    public static boolean checkIsSupportedDeviceOrFinish(final Activity activity) {
//        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
//            Log.e(TAG, "Sceneform requires Android N or later");
//            Toast.makeText(activity, "Sceneform requires Android N or later", Toast.LENGTH_LONG).show();
//            activity.finish();
//            return false;
//        }
//        String openGlVersionString =
//                ((ActivityManager) activity.getSystemService(Context.ACTIVITY_SERVICE))
//                        .getDeviceConfigurationInfo()
//                        .getGlEsVersion();
//        if (Double.parseDouble(openGlVersionString) < MIN_OPENGL_VERSION) {
//            Log.e(TAG, "Sceneform requires OpenGL ES 3.0 later");
//            Toast.makeText(activity, "Sceneform requires OpenGL ES 3.0 or later", Toast.LENGTH_LONG)
//                    .show();
//            activity.finish();
//            return false;
//        }
//        return true;
//    }
}



