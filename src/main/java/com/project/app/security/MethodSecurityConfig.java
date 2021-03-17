package com.project.app.security;


import org.springframework.context.annotation.Configuration;
//import org.springframework.security.access.expression.method.DefaultMethodSecurityExpressionHandler;
import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;

//To enable CustomPermissionEvaluator
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class MethodSecurityConfig extends GlobalMethodSecurityConfiguration {

	@Override
	protected MethodSecurityExpressionHandler createExpressionHandler() {
//        DefaultMethodSecurityExpressionHandler expressionHandler = new DefaultMethodSecurityExpressionHandler();
		CustomMethodSecurityExpressionHandler expressionHandler = new CustomMethodSecurityExpressionHandler();
//      expressionHandler.setPermissionEvaluator(new CustomPermissionEvaluator());
		return expressionHandler;
	}
}


/*
class CustomPermissionEvaluator implements PermissionEvaluator {
@Override
public boolean hasPermission(Authentication auth, Object targetDomainObject, Object permission) {
	if ((auth == null) || (targetDomainObject == null) || !(permission instanceof String)) {
		return false;
	}
	String targetType = targetDomainObject.getClass().getSimpleName().toUpperCase();

	return hasPrivilege(auth, targetType, permission.toString().toUpperCase());
}

@Override
public boolean hasPermission(Authentication auth, Serializable targetId, String targetType, Object permission) {
	if ((auth == null) || (targetType == null) || !(permission instanceof String)) {
		return false;
	}
	return hasPrivilege(auth, targetType.toUpperCase(), permission.toString().toUpperCase());
}

private boolean hasPrivilege(Authentication auth, String targetType, String permission) {
	for (GrantedAuthority grantedAuth : auth.getAuthorities()) {
		if (grantedAuth.getAuthority().startsWith(targetType)) {
			if (grantedAuth.getAuthority().contains(permission)) {
				return true;
			}
		}
	}
	return false;
}

}
*/