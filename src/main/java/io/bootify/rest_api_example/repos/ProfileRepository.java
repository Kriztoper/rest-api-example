package io.bootify.rest_api_example.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import io.bootify.rest_api_example.domain.Profile;


public interface ProfileRepository extends JpaRepository<Profile, Long> {
    // add custom queries here
}
