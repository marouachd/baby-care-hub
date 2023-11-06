package co.simplon.babycarehub.dtos;

public class TokenInfo {

    private String token;

    public TokenInfo() {

    }

    public String getToken() {
	return token;
    }

    public void setToken(String token) {
	this.token = token;
    }

    @Override
    public String toString() {
	return "{token=" + token + "}";
    }

}
