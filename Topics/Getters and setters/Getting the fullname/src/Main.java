class User {
    private String firstName;
    private String lastName;

    public User() {
        this.firstName = "";
        this.lastName = "";
    }

    public void setFirstName(String firstName) {
        if (firstName != null && !firstName.equals("")) {
            this.firstName = firstName;
        }
    }

    public void setLastName(String lastName) {
        if (lastName != null && !lastName.equals("")) {
            this.lastName = lastName;
        }
    }

    public String getFullName() {
        if (!lastName.equals("") && !firstName.equals("")) {
            return this.firstName + " " + this.lastName;
        } else if (this.lastName.equals("") && !this.firstName.equals("")) {
            return this.firstName;
        } else if (this.firstName.equals("") && !this.lastName.equals("")){
            return this.lastName;
        } else {
            return "Unknown";
        }
    }
}