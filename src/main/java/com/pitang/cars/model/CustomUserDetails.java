package com.pitang.cars.model;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Date;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = false)
@Data
public class CustomUserDetails implements UserDetails {
	  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String username;
	    private String password;
	    private String firstName;
	    private String lastName;
	    private String email;
	    private String phone;
	    private LocalDate createdAt;
	    private LocalDate lastLogin;
	    private boolean accountNonExpired;
	    private boolean accountNonLocked;
	    private boolean credentialsNonExpired;
	    private boolean enabled;
	    private Collection<? extends GrantedAuthority> authorities;

	    
	    
	    
	    
		@Override
		public Collection<? extends GrantedAuthority> getAuthorities() {
			return authorities;
		}
		@Override
		public boolean isAccountNonExpired() {
			return accountNonExpired;
		}
		@Override
		public boolean isAccountNonLocked() {
			return accountNonLocked;
		}
		@Override
		public boolean isCredentialsNonExpired() {
			return credentialsNonExpired;
		}
		@Override
		public boolean isEnabled() {
			return enabled;
		}
		public CustomUserDetails(String username, String password, String firstName, String lastName, String email,
				String phone, LocalDate createdAt, LocalDate lastLogin, boolean accountNonExpired,
				boolean accountNonLocked, boolean credentialsNonExpired, boolean enabled,
				Collection<? extends GrantedAuthority> authorities) {
			super();
			this.username = username;
			this.password = password;
			this.firstName = firstName;
			this.lastName = lastName;
			this.email = email;
			this.phone = phone;
			this.createdAt = createdAt;
			this.lastLogin = lastLogin;
			this.accountNonExpired = accountNonExpired;
			this.accountNonLocked = accountNonLocked;
			this.credentialsNonExpired = credentialsNonExpired;
			this.enabled = enabled;
			this.authorities = authorities;
		}
		 
		
}
