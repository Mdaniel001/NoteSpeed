package co.edu.uniminuto.entity;

public class User {
    private String names;
    private String lastNames;
    private String email;
    private String password;
    private String passwordConfirm;

    public User(String names, String lastNames, String email, String password, String passwordConfirm) {
        this.names = names;
        this.lastNames = lastNames;
        this.email = email;
        this.password = password;
        this.passwordConfirm = passwordConfirm;
    }

    public User() {
    }

    public String getNames() {
        return names;
    }

    public void setNames(String names) {
        this.names = names;
    }

    public String getLastNames() {
        return lastNames;
    }

    public void setLastNames(String lastNames) {
        this.lastNames = lastNames;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("User{");
        sb.append("names='").append(names).append('\'');
        sb.append(", lastNames='").append(lastNames).append('\'');
        sb.append(", email='").append(email).append('\'');
        sb.append(", password='").append(password).append('\'');
        sb.append(", passwordConfirm='").append(passwordConfirm).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
