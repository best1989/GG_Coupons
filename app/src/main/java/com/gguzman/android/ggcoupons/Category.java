package com.gguzman.android.ggcoupons;

/**
 * Class that represents the categories provide by 8coupons.
 * <br><br>
 *     Each category have an ID ({@code catID}) and a name ({@code catName}).
 * @author Gabriel Guzmán
 * @version 1.0
 * @since v1.2016.03.22
 */
public class Category {

    private String catID="";
    private String catName="";

    /**
     * Public getter for the {@code catID}
     * @return A string with the {@code catID}
     */
    public String getCatID() {
        return catID;
    }

    /**
     * Public setter for the {@code catID}
     * @param catID The string ID to set.
     */
    public void setCatID(String catID) {
        this.catID = catID;
    }

    /**
     * Public getter for the {@code catName}
     * @return A string with the {@code catName}
     */
    public String getCatName() {
        return catName;
    }

    /**
     * Public setter for the {@code catName}
     * @param catName The string name to set.
     */
    public void setCatName(String catName) {
        this.catName = catName;
    }
}
