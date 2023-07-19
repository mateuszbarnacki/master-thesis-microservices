package agh.wfiis.weather.principal.service;

import agh.wfiis.weather.principal.dto.ProjectDto;
import agh.wfiis.weather.principal.dto.UserDto;
import agh.wfiis.weather.principal.dto.UserInfoDto;

import java.util.Collection;

public interface UserService {
    /**
     * This method returns user list with objects contain user information.
     *
     * @return List<UserInfoDto> List of user objects which contains username, roles and projects in
     * which user is enrolled.
     */
    Collection<UserInfoDto> getUsers();

    /**
     * This method returns list of projects in which user with given username is enrolled.
     *
     * @return Collection<ProjectDto> Collection of projects.
     */
    Collection<ProjectDto> getUserProjects(String username);

    /**
     * This method is used to create new user.
     *
     * @param userDto Object which contains user account data.
     */
    void registerUser(UserDto userDto);

    /**
     * This method updates roles and actions for user with given username.
     *
     * @param userInfoDto Object which contains new data.
     * @return UserInfoDto Object with updated data.
     */
    UserInfoDto updateRolesAndActions(UserInfoDto userInfoDto);

    /**
     * This method add projects in which user is enrolled.
     *
     * @param userInfoDto Object which contains new data.
     * @return UserInfoDto Object with updated data.
     */
    UserInfoDto updateProjects(UserInfoDto userInfoDto);
}
