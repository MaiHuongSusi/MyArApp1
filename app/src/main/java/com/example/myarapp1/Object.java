package com.example.myarapp1;

public class Object {
    String name;
    String link_gltf;
    String image;

    public Object() {
    }

    public Object(String name, String link_gltf, String image) {
        this.name = name;
        this.link_gltf = link_gltf;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLinkGltf() {
        return link_gltf;
    }

    public void setLinkGltf(String link_gltf) {
        this.link_gltf = link_gltf;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
