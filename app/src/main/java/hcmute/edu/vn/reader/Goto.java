package hcmute.edu.vn.reader;

import hcmute.edu.vn.reader.model.BookTitle;
import hcmute.edu.vn.reader.model.Dish;
import hcmute.edu.vn.reader.model.Store;
import hcmute.edu.vn.reader.model.User;

public interface Goto {
    public void GotoHome();
    public void GotoDetail(BookTitle bookTitle);
    public void GotoBooking(Dish dish);
    public void GotoEditProfile(User user);
    public void GotoProfile();
    public void GotoLogin();
    public void GotoSignin();
}
