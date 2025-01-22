package com.example.softlearning.applicationcore.entity.sharedkernel.model.physics;

import com.example.softlearning.applicationcore.entity.book.model.Book;
import com.example.softlearning.applicationcore.entity.sharedkernel.domainservices.validations.DataCheck;
import com.example.softlearning.applicationcore.entity.sharedkernel.model.exceptions.BuildException;

public class PhysicalData  {
    protected double heigth, width, depth, weigth;
    protected boolean fragile;

    protected PhysicalData() {
    }

    public static PhysicalData getInstance(double heigth, double width, double depht, double weigth, boolean fragile) throws BuildException {
        
        String errorMessage = "";
        PhysicalData phy = new PhysicalData();

        if (phy.setHeigth(heigth) != 0){
            errorMessage += "Bad Heigth dimension;";
        }
        if (phy.setWidth(width) != 0){
            errorMessage += "Bad Width dimension;";
        }
        if (phy.setDepth(depht) != 0){
            errorMessage += "Bad Depth dimension;";
        }
        if (phy.setWeigth(weigth) != 0){
            errorMessage += "Bad Weigth;";
        }
        phy.setFragile(fragile);

        if (errorMessage.length()>0){
            phy = null;
            throw new BuildException(errorMessage);
        }
        return phy;
    }

    public double getHeigth() {
        return heigth;
    }

    public int setHeigth(double heigth) {
        if (DataCheck.checkNumber(heigth, 0.1) != 0) {
            return -1;
        }
        this.heigth = heigth;
        return 0;
    }

    public double getWidth() {
        return width;
    }

    public int setWidth(double width) {
        if (DataCheck.checkNumber(width, 1) != 0) {
            return -1;
        }
        this.width = width;

        return 0;

    }

    public double getDepth() {
        return depth;
    }

    public int setDepth(double depth) {
        if (DataCheck.checkNumber(depth, 1) != 0) {
            return -1;
        }
        this.depth = depth;
        return 0;

    }

    public double getWeigth() {
        return weigth;
    }

    public int setWeigth(double weigth) {
        if (DataCheck.checkNumber(weigth, 1) != 0) {
            return -1;
        }
        this.weigth = weigth;
        return 0;

    }

    public boolean getFragile() {
        return fragile;
    }

    public void setFragile(Boolean fragile) {
        this.fragile = fragile;
    }

    public String getSize() {
        return "heigth:" + this.heigth + ";width:" + this.width  + ";depth:" + this.depth;
    }

    public double getVolum() {
        return this.heigth * this.width * this.depth;
    }
  
}
