package com.ideas2it.ecommerce.model;

import java.util.List;

import com.ideas2it.ecommerce.common.enums.USER_ROLES;

/**
 * <p>
 * User contains role of the user such as customer, seller, admin and their
 * login credentials
 * </p>
 * 
 * @author Anantharaj.S
 */
public class User {

    private Integer id;
    private String userName;
    private String password;
    private List<Address> addresses;
    private USER_ROLES role;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public List<Address> getAddresses() {
        return addresses;
    }
    
    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }

    public USER_ROLES getRole() {
        return role;
    }

    public void setRole(USER_ROLES role) {
        this.role = role;
    }
    
    /** 
     * <p>
     * Compares whether two users are similar. It checks for similarity in 
     * id.
     * </p>
     *
     * @param address
     *        An Object which has to be compared for checking it's similarity
     *
     * @return true   When a similar user is present
     *         false  When users are not similar
     *
     */
    @Override
    public boolean equals(Object user) {
        if (null == user) {
            return Boolean.FALSE;
        }

        if (!(user instanceof User)) {
            return Boolean.FALSE;
        }

        if (this == (User) user) {
            return Boolean.TRUE;
        }

        return ((this.id).equals(((User) user).id));
    }

}
