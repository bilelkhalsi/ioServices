package io.services.autoconfigure;

import org.immutables.value.Value;
import org.springframework.security.core.userdetails.UserDetails;

@Value.Immutable
public interface IoUserDetails extends UserDetails {

    String getId();

    String getEmail();

    String getFirstName();

    String getLastName();

    String getLocale();

}
