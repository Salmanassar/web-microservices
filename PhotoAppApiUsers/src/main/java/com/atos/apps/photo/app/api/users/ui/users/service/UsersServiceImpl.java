package com.atos.apps.photo.app.api.users.ui.users.service;

import com.atos.apps.photo.app.api.users.shared.UserDto;
import com.atos.apps.photo.app.api.users.ui.model.AlbumResponseModel;
import com.atos.apps.photo.app.api.users.ui.users.data.UserEntity;
import com.atos.apps.photo.app.api.users.ui.users.data.UsersRepository;
import feign.FeignException;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
public class UsersServiceImpl implements UsersService {
    private UsersRepository usersRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private RestTemplate restTemplate;
    private Environment environment;
    private AlbumServiceClient albumServiceClient;
    private Logger logger = LoggerFactory.getLogger(UsersServiceImpl.class);

    @Autowired
    public UsersServiceImpl(UsersRepository usersRepository,
                            BCryptPasswordEncoder bCryptPasswordEncoder,
                            AlbumServiceClient albumServiceClient,
                            Environment environment,
                            RestTemplate restTemplate) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.usersRepository = usersRepository;
        this.albumServiceClient = albumServiceClient;
        this.environment = environment;
        this.restTemplate = restTemplate;
    }

    @Override
    public UserDto createUser(UserDto userDetails) {
        userDetails.setUserId(UUID.randomUUID().toString());
        userDetails.setEncryptedPassword(bCryptPasswordEncoder.encode(userDetails.getPassword()));
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        UserEntity userEntity = modelMapper.map(userDetails, UserEntity.class);
        usersRepository.save(userEntity);
        UserDto returnValue = modelMapper.map(userEntity, UserDto.class);
        return returnValue;
    }

    @Override
    public UserDto getUserDetailsByEmail(String email) {
        UserEntity userEntity = usersRepository.findUserEntitiesByEmail(email);
        if (userEntity == null) throw new UsernameNotFoundException(email);
        return new ModelMapper().map(userEntity, UserDto.class);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = usersRepository.findUserEntitiesByEmail(username);
        if (userEntity == null) throw new UsernameNotFoundException(username);
        return new User(userEntity.getEmail(), userEntity.getEncryptedPassword(),
                true, true, true, true, new ArrayList<>());
    }

    @Override
    public UserDto getUserById(String userId) {
        UserEntity userEntity = usersRepository.findByUserId(userId);
        if (userEntity == null) throw new UsernameNotFoundException("The user not found");
        UserDto userDto = new ModelMapper().map(userEntity, UserDto.class);
        List<AlbumResponseModel> list = null;
        try {
            list = albumServiceClient.getAlbums(userId);
        } catch (FeignException e) {
            logger.error(e.getLocalizedMessage());
        }
        userDto.setAlbums(list);
        return userDto;
    }

    /**
     * This private method for getting data from the microservice "user-ws" using RestTemplate
     * It is created like an example
     */
    private List<AlbumResponseModel> exchangeAsList(String uri) {
        String albumUrl = String.format(Objects.requireNonNull(environment.getProperty("albums.url")), uri);
        return restTemplate.exchange(
                        albumUrl,
                        HttpMethod.GET,
                        null,
                        new ParameterizedTypeReference<List<AlbumResponseModel>>() {
                        })
                .getBody();
    }
}
