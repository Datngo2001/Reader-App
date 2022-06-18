package hcmute.edu.vn.reader.dtos;

public class LoginDto {
    public String username; // user name
    public String password; // user password

    // method to get the user name and password
    public LoginDto(String username, String password) {
        this.username = username; // assign the user name
        this.password = password; // assign the password
    }
}
