package com.example.myarapp1;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.ar.core.AugmentedFace;
import com.google.ar.core.Frame;
import com.google.ar.sceneform.rendering.ModelRenderable;
import com.google.ar.sceneform.rendering.Renderable;
import com.google.ar.sceneform.rendering.Texture;
import com.google.ar.sceneform.ux.AugmentedFaceNode;

import java.util.Collection;

public class StickerActivity extends AppCompatActivity {
    private ModelRenderable modelRenderable;
    private Texture texture;
    private boolean isAdded = false;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sticker);

        CustomArFragment customArFragment = (CustomArFragment) getSupportFragmentManager().findFragmentById(R.id.stickerFragment);
        ModelRenderable.builder().setSource(StickerActivity.this, R.raw.fox_face)
                .build().thenAccept(renderable -> {
                    modelRenderable = renderable;
                    modelRenderable.setShadowCaster(false);
                    modelRenderable.setShadowReceiver(false);
        });
        Texture.builder().setSource(StickerActivity.this, R.drawable.fox_face_mesh_texture)
                .build().thenAccept(texture -> {
                    this.texture = texture;
        });
        customArFragment.getArSceneView().setCameraStreamRenderPriority(Renderable.RENDER_PRIORITY_FIRST);
        customArFragment.getArSceneView().getScene().addOnUpdateListener(frameTime -> {
            if (modelRenderable == null || texture == null)
                return;
            Frame frame = customArFragment.getArSceneView().getArFrame();
            Collection<AugmentedFace> augmentedFaces = frame.getUpdatedTrackables(AugmentedFace.class);
            for (AugmentedFace augmentedFace: augmentedFaces) {
                if (isAdded) {
                    return;
                }
                AugmentedFaceNode augmentedFaceNode = new AugmentedFaceNode(augmentedFace);
                augmentedFaceNode.setParent(customArFragment.getArSceneView().getScene());
                augmentedFaceNode.setFaceRegionsRenderable(modelRenderable);
                augmentedFaceNode.setFaceMeshTexture(texture);
                isAdded = true;
            }
        });























    }
}