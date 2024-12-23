package com.anikhil.scrumsphere.auth;

import com.anikhil.scrumsphere.users.models.User;
import org.apache.commons.lang3.StringUtils;

import java.util.Base64;

public interface AuthFilter {

	default User extractAndDecodeHeader(String header, String prefix) {

		// Remove the "Basic " prefix
		String base64Credentials = StringUtils.removeStart(header, prefix);

		// Decode the Base64 encoded credentials
		byte[] decoded = Base64.getDecoder().decode(base64Credentials);
		String credentials = new String(decoded);

		// Split the credentials into username and password
		String[] splitCreds = StringUtils.split(credentials, ":");
		User user = new User();
		user.setUserId(splitCreds[0]);
		user.setPassword(splitCreds[1]);
		return user;
	}
}
