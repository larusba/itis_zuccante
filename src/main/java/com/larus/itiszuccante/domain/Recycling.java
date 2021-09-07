package com.larus.itiszuccante.domain;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "recycling")
public class Recycling {

    @Field("plastic_waste")
    private int plasticWaste;

    @Field("organic_waste")
    private int organicWaste;

    @Field("non_recycable_waste")
    private int paperWaste;

    @Field("paper_waste")
    private int nonRecyclableWaste;

    public int getPlasticWaste() {
        return plasticWaste;
    }

    public void setPlasticWaste(int plasticWaste) {
        this.plasticWaste = plasticWaste;
    }

    public int getOrganicWaste() {
        return organicWaste;
    }

    public void setOrganicWaste(int organicWaste) {
        this.organicWaste = organicWaste;
    }

    public int getPaperWaste() {
        return paperWaste;
    }

    public void setPaperWaste(int paperWaste) {
        this.paperWaste = paperWaste;
    }

    public int getNonRecyclableWaste() {
        return nonRecyclableWaste;
    }

    public void setNonRecyclableWaste(int nonRecyclableWaste) {
        this.nonRecyclableWaste = nonRecyclableWaste;
    }

    @Override
    public String toString() {
        return "Recycling{" +
            "plasticWaste=" + plasticWaste +
            ", organicWaste=" + organicWaste +
            ", paperWaste=" + paperWaste +
            ", nonRecyclableWaste=" + nonRecyclableWaste +
            '}';
    }
}
