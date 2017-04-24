package com.stashcity.www.stashedphotos.model;

/**
 * Created by sravan on 10/10/16.
 */

public class User {
    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getFName() {
        return FName;
    }

    public void setFName(String FName) {
        this.FName = FName;
    }

    public String getLName() {
        return LName;
    }


    public void setLName(String LName) {
        this.LName = LName;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "Email='" + Email + '\'' +
                ", FName='" + FName + '\'' +
                ", LName='" + LName + '\'' +
                ", Password='" + Password + '\'' +
                ", ProfilePic='" + ProfilePic + '\'' +
                ", AboutMe='" + AboutMe + '\'' +
                ", Facebook='" + Facebook + '\'' +
                ", Instagram='" + Instagram + '\'' +
                ", Twitter='" + Twitter + '\'' +
                ", Website='" + Website + '\'' +
                ", Birthday='" + Birthday + '\'' +
                ", ApprovedFlag=" + ApprovedFlag +
                ", DeleteFlag=" + DeleteFlag +
                ", PhotographerId=" + PhotographerId +
                ", Views=" + Views +
                ", Response=" + Response +
                '}';
    }

    public String getProfilePic() {
        return ProfilePic;
    }

    public void setProfilePic(String profilePic) {
        ProfilePic = profilePic;
    }

    public String getAboutMe() {
        return AboutMe;
    }

    public void setAboutMe(String aboutMe) {
        AboutMe = aboutMe;
    }

    public String getFacebook() {
        return Facebook;
    }

    public void setFacebook(String facebook) {
        Facebook = facebook;
    }

    public String getInstagram() {
        return Instagram;
    }

    public void setInstagram(String instagram) {
        Instagram = instagram;
    }

    public String getTwitter() {
        return Twitter;
    }

    public void setTwitter(String twitter) {
        Twitter = twitter;
    }

    public String getWebsite() {
        return Website;
    }

    public void setWebsite(String website) {
        Website = website;
    }

    public int getApprovedFlag() {
        return ApprovedFlag;
    }

    public void setApprovedFlag(int approvedFlag) {
        ApprovedFlag = approvedFlag;
    }

    public int getDeleteFlag() {
        return DeleteFlag;
    }

    public void setDeleteFlag(int deleteFlag) {
        DeleteFlag = deleteFlag;
    }

    public int getPhotographerId() {
        return PhotographerId;
    }

    public void setPhotographerId(int photographerId) {
        PhotographerId = photographerId;
    }

    public int getViews() {
        return Views;
    }

    public void setViews(int views) {
        Views = views;
    }

    String Email;
    String FName;
    String LName;
    String Password;
    String ProfilePic;
    String AboutMe;
    String Facebook;
    String Instagram;
    String Twitter;
    String Website;

    public String getBirthday() {
        return Birthday;
    }

    public void setBirthday(String birthday) {
        Birthday = birthday;
    }

    String Birthday;
    int ApprovedFlag,DeleteFlag,PhotographerId,Views;
    Response Response;

    public Response getResponse() {
        return Response;
    }

}
