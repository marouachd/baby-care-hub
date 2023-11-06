package co.simplon.babycarehub.dtos;

public class SignInCredentials {

    private String mailAdress;

    private String password;

    public SignInCredentials() {
    }

    public String getMailAdress() {
	return mailAdress;
    }

    public void setMailAdress(String emails) {
	this.mailAdress = mailAdress;
    }

    public String getPassword() {
	return password;
    }

    public void setPassword(String password) {
	this.password = password;
    }

    @Override
    public String toString() {
	return "{mailAdress=" + mailAdress + ", password="
		+ password + "}";
    }

}
