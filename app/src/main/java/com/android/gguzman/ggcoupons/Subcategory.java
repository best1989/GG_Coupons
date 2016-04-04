package com.android.gguzman.ggcoupons;

/**
 * Class that represents the subcategories provide by 8coupons.
 * <br><br>
 *     Each subcategory have an ID ({@code subcatID}) and a name ({@code subcatName}), as well
 *     as the name and ID of the parent category.
 * @author Gabriel Guzmán
 * @version 1.0
 * @since v1.2016.04.03
 */
public class Subcategory extends Category{

    private String subcatID="";
    private String subcatName="";

    /**
     * Public getter for the {@code subcatID}
     * @return A string with the {@code subcatID}
     */
    public String getSubcatID() {
        return subcatID;
    }

    /**
     * Public setter for the {@code subcatID}
     * @param subcatID The string ID to set.
     */
    public void setSubcatID(String subcatID) {
        this.subcatID = subcatID;
    }

    /**
     * Public getter for the {@code subcatName}
     * @return A string with the {@code subcatName}
     */
    public String getSubcatName() {
        return subcatName;
    }

    /**
     * Public setter for the {@code subcatName}
     * @param subcatName The string name to set.
     */
    public void setSubcatName(String subcatName) {
        this.subcatName = subcatName;
    }
}
