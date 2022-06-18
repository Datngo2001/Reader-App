package hcmute.edu.vn.reader.dtos;

import java.util.List;

public class CreateBooksRegisterDto {
    public String note; // note when you register for book in cart
    public String planReturnDate; // planning date to retrun book
    public List<Integer> bookTitileIds; // list of book title id
}
