package com.sh.config;

import com.sh.entity.Admin;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

/**
 * Created By Sunhu At 2020/6/5 9:25
 *
 * @author Sun
 */
public class SecurityAdmin extends User {


    private Admin originalAdmin;


    public SecurityAdmin(Admin originalAdmin ,Collection<? extends GrantedAuthority> authorities) {
        super(originalAdmin.getUserName(), originalAdmin.getUserPswd(), authorities);
        this.originalAdmin=originalAdmin;

    }

    public Admin getOriginalAdmin() {
        return originalAdmin;
    }
}
