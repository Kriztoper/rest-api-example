package io.bootify.rest_api_example.service;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import io.bootify.rest_api_example.domain.Profile;
import io.bootify.rest_api_example.model.ProfileDTO;
import io.bootify.rest_api_example.repos.ProfileRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;


@Service
public class ProfileService {

    private final ProfileRepository profileRepository;

    public ProfileService(final ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    public List<ProfileDTO> findAll() {
        return profileRepository.findAll()
                .stream()
                .map(profile -> mapToDTO(profile, new ProfileDTO()))
                .collect(Collectors.toList());
    }

    public ProfileDTO get(final Long profileId) {
        return profileRepository.findById(profileId)
                .map(profile -> mapToDTO(profile, new ProfileDTO()))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public Long create(final ProfileDTO profileDTO) {
        final Profile profile = new Profile();
        mapToEntity(profileDTO, profile);
        return profileRepository.save(profile).getProfileId();
    }

    public void update(final Long profileId, final ProfileDTO profileDTO) {
        final Profile profile = profileRepository.findById(profileId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        mapToEntity(profileDTO, profile);
        profileRepository.save(profile);
    }

    public void delete(final Long profileId) {
        profileRepository.deleteById(profileId);
    }

    private ProfileDTO mapToDTO(final Profile profile, final ProfileDTO profileDTO) {
        profileDTO.setProfileId(profile.getProfileId());
        profileDTO.setFullName(profile.getFullName());
        profileDTO.setEmail(profile.getEmail());
        profileDTO.setMobileNumber(profile.getMobileNumber());
        profileDTO.setDateOfBirth(profile.getDateOfBirth());
        profileDTO.setAge(profile.getAge());
        profileDTO.setGender(profile.getGender());
        return profileDTO;
    }

    private Profile mapToEntity(final ProfileDTO profileDTO, final Profile profile) {
        profile.setFullName(profileDTO.getFullName());
        profile.setEmail(profileDTO.getEmail());
        profile.setMobileNumber(profileDTO.getMobileNumber());
        profile.setDateOfBirth(profileDTO.getDateOfBirth());
        profile.setAge(profileDTO.getAge());
        profile.setGender(profileDTO.getGender());
        return profile;
    }

}
