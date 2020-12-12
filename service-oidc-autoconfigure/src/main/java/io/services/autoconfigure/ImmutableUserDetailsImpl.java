package io.services.autoconfigure;

import org.springframework.security.core.GrantedAuthority;

import java.util.*;

public class ImmutableUserDetailsImpl implements ImmutableUserDetails {

    private final String id;
    private final String email;
    private final String username;
    private final String password;
    private final boolean accountNonExpired;
    private final boolean accountNonLocked;
    private final boolean credentialsNonExpired;
    private final boolean enabled;
    private final String firstName;
    private final String lastName;
    private final String locale;
    private final List<GrantedAuthority> authorities;

    private ImmutableUserDetailsImpl(
            String id,
            String email,
            String username,
            String password,
            boolean accountNonExpired,
            boolean accountNonLocked,
            boolean credentialsNonExpired,
            boolean enabled,
            String firstName,
            String lastName,
            String locale,
            List<GrantedAuthority> authorities) {
        this.id = id;
        this.email = email;
        this.username = username;
        this.password = password;
        this.accountNonExpired = accountNonExpired;
        this.accountNonLocked = accountNonLocked;
        this.credentialsNonExpired = credentialsNonExpired;
        this.enabled = enabled;
        this.firstName = firstName;
        this.lastName = lastName;
        this.locale = locale;
        this.authorities = List.copyOf(authorities);
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public String getFirstName() {
        return firstName;
    }

    @Override
    public String getLastName() {
        return lastName;
    }

    @Override
    public String getLocale() {
        return locale;
    }

    @Override
    public Collection<GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ImmutableUserDetailsImpl that = (ImmutableUserDetailsImpl) o;
        return id.equals(that.getId()) &&
                email.equals(that.getEmail());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email);
    }

    @Override
    public String toString() {
        return "ImmutableUserDetails {" +
                "id='" + id + '\'' +
                ", email='" + email + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", accountNonExpired=" + accountNonExpired +
                ", accountNonLocked=" + accountNonLocked +
                ", credentialsNonExpired=" + credentialsNonExpired +
                ", enabled=" + enabled +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", locale='" + locale + '\'' +
                ", authorities=" + authorities +
                '}';
    }

    public static Builder builder() {
        return new Builder();
    }


    public static final class Builder {

        private String id;
        private String email;
        private String username;
        private String password;
        private boolean accountNonExpired = true;
        private boolean accountNonLocked = true;
        private boolean credentialsNonExpired = true;
        private boolean enabled = true;
        private String firstName;
        private String lastName;
        private String locale;
        private List<GrantedAuthority> authorities = Collections.emptyList();

        public Builder() {
        }

        public final Builder id(String id) {
            this.id = Objects.requireNonNull(id, "id");
            return this;
        }

        public final Builder id(Optional<String> id) {
            this.id = id.orElse(null);
            return this;
        }

        public final Builder email(String email) {
            this.email = Objects.requireNonNull(email, "email");
            return this;
        }

        public final Builder username(String username) {
            this.username = Objects.requireNonNull(username, "username");
            return this;
        }

        public final Builder password(String password) {
            this.password = Objects.requireNonNull(password, "password");
            return this;
        }

        public final Builder enabled(boolean enabled) {
            this.enabled = enabled;
            return this;
        }

        public final Builder accountNonLocked(boolean accountNonLocked) {
            this.accountNonLocked = accountNonLocked;
            return this;
        }

        public final Builder accountNonExpired(boolean accountNonExpired) {
            this.accountNonExpired = accountNonExpired;
            return this;
        }

        public final Builder credentialsNonExpired(boolean credentialsNonExpired) {
            this.credentialsNonExpired = credentialsNonExpired;
            return this;
        }


        public final Builder firstName(String firstName) {
            this.firstName = Objects.requireNonNull(firstName, "firstName");
            return this;
        }


        public final Builder lastName(String lastName) {
            this.lastName = Objects.requireNonNull(lastName, "lastName");
            return this;
        }

        public final Builder locale(String locale) {
            this.locale = Objects.requireNonNull(locale, "locale");
            return this;
        }


        public final Builder addAuthorities(GrantedAuthority element) {
            this.authorities.add(element);
            return this;
        }


        public final Builder addAuthorities(Collection<GrantedAuthority> elements) {
            this.authorities.addAll(elements);
            return this;
        }


        public final Builder authorities(Collection<GrantedAuthority> elements) {
            this.authorities = Collections.emptyList();
            return addAuthorities(elements);
        }


        public ImmutableUserDetails build() {
            return new ImmutableUserDetailsImpl(
                    id,
                    email,
                    username,
                    password,
                    accountNonExpired,
                    accountNonLocked,
                    credentialsNonExpired,
                    enabled,
                    firstName,
                    lastName,
                    locale,
                    Collections.unmodifiableList(authorities)
            );
        }
    }

}


