package com.example.demo.security;

import org.springframework.security.access.expression.SecurityExpressionRoot;
import org.springframework.security.access.expression.method.MethodSecurityExpressionOperations;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class CustomMethodSecurityExpressionRoot extends SecurityExpressionRoot
		implements MethodSecurityExpressionOperations {
	
	private Object filterObject;
    private Object returnObject;
    
    
	public CustomMethodSecurityExpressionRoot(Authentication authentication) {
		super(authentication);
	}

	public boolean hasAccess(String resource, String permission) {
		resource=resource.toUpperCase();
		permission=permission.toUpperCase();
		//System.out.println(resource+" "+permission);
		User user = ((User)this.getPrincipal());
		for(GrantedAuthority grantedAuth:user.getAuthorities()) {
			if (grantedAuth.getAuthority().startsWith(resource)) {
				if (grantedAuth.getAuthority().contains(permission)) {
					//System.out.println("true returned");
					return true;
				}
			}
		}
		return false;
	}
	
	//USER_WRITE

	@Override
    public Object getFilterObject() {
        return this.filterObject;
    }

    @Override
    public Object getReturnObject() {
        return this.returnObject;
    }

    @Override
    public Object getThis() {
        return this;
    }

    @Override
    public void setFilterObject(Object obj) {
        this.filterObject = obj;
    }

    @Override
    public void setReturnObject(Object obj) {
        this.returnObject = obj;
    }

}