package com.szakdolgozat.moviedb.Mappers;

import com.szakdolgozat.moviedb.DTO.UserDto;
import com.szakdolgozat.moviedb.Entities.User;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface UserMapper {
    User userDtoToUser(UserDto userDto);

    UserDto userToUserDto(User user);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    User updateUserFromUserDto(UserDto userDto, @MappingTarget User user);

    @AfterMapping
    default void linkMovieprogresses(@MappingTarget User user) {
        user.getMovieprogresses().forEach(movieprogress -> movieprogress.setUserid(user));
    }

    @AfterMapping
    default void linkPeople(@MappingTarget User user) {
        user.getPeople().forEach(person -> person.setUserid(user));
    }

    @AfterMapping
    default void linkTvprogresses(@MappingTarget User user) {
        user.getTvprogresses().forEach(tvprogress -> tvprogress.setUserid(user));
    }
}
