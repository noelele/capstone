package noelanthony.com.lostandfoundfinal.Profile;

/**
 * Created by Noel on 27/02/2018.
 */

public class UserInformation {
    public String name;
    public String image;
    public String email;
    public String datejoined;
    public int itemsreturned;
    public String idnumber;


    public UserInformation() {

    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDatejoined() {
        return datejoined;
    }

    public void setDatejoined(String datejoined) {
        this.datejoined = datejoined;
    }

    public int getItemsreturned() {
        return itemsreturned;
    }

    public void setItemsreturned(int itemsreturned) {
        this.itemsreturned = itemsreturned;
    }

    public String getIdnumber() {
        return idnumber;
    }

    public void setIdnumber(String idnumber) {
        this.idnumber = idnumber;
    }
}
