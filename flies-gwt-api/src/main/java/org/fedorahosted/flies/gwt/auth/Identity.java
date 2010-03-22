package org.fedorahosted.flies.gwt.auth;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import org.fedorahosted.flies.gwt.model.Person;

import com.google.gwt.user.client.Cookies;

public class Identity implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private SessionId sessionId;
	private Set<Permission> permissions = new HashSet<Permission>();
	private Set<Role> roles = new HashSet<Role>();
	private Person person;

	
	@SuppressWarnings("unused")
	private Identity() {
	}
	
	public Identity(SessionId sessionId, Person person, Set<Permission> permissions, Set<Role> roles) {
		this.sessionId = sessionId;
		this.person = person;
		this.permissions = permissions;
		this.roles = roles;
		
	}
	
	public boolean hasRole(Role role) {
		return roles.contains(role);
	}
	
	public boolean hasPermission(Permission permission) {
		return permissions.contains(permission);
	}
	
	public Person getPerson() {
		return person;
	}

	public SessionId getSessionId() {
		return sessionId;
	}
}
