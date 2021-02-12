package io.bootify.rest_api_example.rest;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import io.bootify.rest_api_example.model.ProfileDTO;
import io.bootify.rest_api_example.service.ProfileService;
import java.util.List;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;


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
    @ResponseStatus(HttpStatus.CREATED)
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
