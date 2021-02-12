package io.bootify.rest_api_example.rest;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.bootify.rest_api_example.model.ProfileDTO;
import io.bootify.rest_api_example.service.ProfileService;


@RestController
@RequestMapping(value = "/api/profiles", produces = MediaType.APPLICATION_JSON_VALUE)
public class ProfileController {

    private final ProfileService profileService;

    public ProfileController(final ProfileService profileService) {
        this.profileService = profileService;
    }

    @GetMapping
    public List<ProfileDTO> getAllProfiles() {
        return profileService.findAll();
    }

    @GetMapping("/{profileId}")
    public ProfileDTO getProfile(@PathVariable final Long profileId) {
        return profileService.get(profileId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public Long createProfile(@RequestBody @Valid final ProfileDTO profileDTO) {
        return profileService.create(profileDTO);
    }

    @PutMapping("/{profileId}")
    public void updateProfile(@PathVariable final Long profileId, @RequestBody @Valid final ProfileDTO profileDTO) {
        profileService.update(profileId, profileDTO);
    }

    @DeleteMapping("/{profileId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProfile(@PathVariable final Long profileId) {
        profileService.delete(profileId);
    }

}
