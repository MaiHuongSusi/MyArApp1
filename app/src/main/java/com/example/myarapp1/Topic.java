package com.example.myarapp1;

public class Topic {

    public enum ETopic {
        Animal,
        Plant,
        Furniture
    }

    public int topicId;
    public String topicName;
    public int size;

    public Topic() {
    }

    public Topic(int topicId, String topicName, int size) {
        this.topicId = topicId;
        this.topicName = topicName;;
        this.size = size;
    }

    public int getTopicId() {
        return topicId;
    }

    public void setTopicId(int topicId) {
        this.topicId = topicId;
    }

    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
