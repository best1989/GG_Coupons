package com.gguzman.android.ggcoupons;

/**
 * Class that represents the chain stores provide by 8coupons.
 * <br><br>
 *     Each chain store have an ID ({@code csID}) and a name ({@code csName}), as well
 *     as four URLs associated with them.
 * @author Gabriel Guzmán
 * @version 1.0
 * @since v1.2016.04.12
 */
public class ChainStore {

    private String csID="";
    private String csName="";
    private String csCoupPageURL="";
    private String csComPageURL="";
    private String csSmallImgURL="";
    private String csBigImgURL="";

    public String getCsID() {
        return csID;
    }

    public void setCsID(String csID) {
        this.csID = csID;
    }

    public String getCsName() {
        return csName;
    }

    public void setCsName(String csName) {
        this.csName = csName;
    }

    public String getCsCoupPageURL() {
        return csCoupPageURL;
    }

    public void setCsCoupPageURL(String csCoupPageURL) {
        this.csCoupPageURL = csCoupPageURL;
    }

    public String getCsComPageURL() {
        return csComPageURL;
    }

    public void setCsComPageURL(String csComPageURL) {
        this.csComPageURL = csComPageURL;
    }

    public String getCsSmallImgURL() {
        return csSmallImgURL;
    }

    public void setCsSmallImgURL(String csSmallImgURL) {
        this.csSmallImgURL = csSmallImgURL;
    }

    public String getCsBigImgURL() {
        return csBigImgURL;
    }

    public void setCsBigImgURL(String csBigImgURL) {
        this.csBigImgURL = csBigImgURL;
    }
}
