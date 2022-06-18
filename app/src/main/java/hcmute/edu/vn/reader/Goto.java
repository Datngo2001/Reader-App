package hcmute.edu.vn.reader;

import java.util.List;

import hcmute.edu.vn.reader.model.BookTitle;
import hcmute.edu.vn.reader.model.User;

public interface Goto {
    // go to home fragment
    public void GotoHome();
    // go to detail fragment
    public void GotoDetail(BookTitle bookTitle);
    // go to booking fragment
    public void GotoBooking(List<BookTitle> bookTitles);
    // go to edit profile fragment
    public void GotoEditProfile(User user);
    // go to profile fragment
    public void GotoProfile();
    // go to login fragment
    public void GotoLogin();
    // go to signin fragment
    public void GotoSignin();
}
