package hcmute.edu.vn.reader;

import hcmute.edu.vn.reader.model.Dish;
import hcmute.edu.vn.reader.model.Store;
import hcmute.edu.vn.reader.model.User;

public interface Goto {
    public void GotoMenu(Store store);
    public void GotoBooking(Dish dish);
    public void GotoEditProfile(User user);
    public void GotoProfile();
}