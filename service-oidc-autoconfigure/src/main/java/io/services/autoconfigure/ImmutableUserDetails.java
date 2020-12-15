package io.services.autoconfigure;

import org.springframework.security.core.userdetails.UserDetails;


public interface ImmutableUserDetails extends UserDetails {

    String getId();

    String getEmail();

    String getFirstName();

    String getLastName();

    String getLocale();

}
