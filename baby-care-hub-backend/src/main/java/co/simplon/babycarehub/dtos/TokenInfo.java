package co.simplon.babycarehub.dtos;

public class TokenInfo {

    private String token;

    private Long userId;

    private Long roleId;

    public TokenInfo() {

    }

    public String getToken() {
	return token;
    }

    public void setToken(String token) {
	this.token = token;
    }

    public Long getUserId() {
	return userId;
    }

    public void setUserId(Long userId) {
	this.userId = userId;
    }

    public Long getRoleId() {
	return roleId;
    }

    public void setRoleId(Long roleId) {
	this.roleId = roleId;
    }

    @Override
    public String toString() {
	return "{token=" + token + ", userId=" + userId
		+ ", roleId=" + roleId + "}";
    }

}
