package com.atos.apps.photo.app.api.users.ui.users.service;

import com.atos.apps.photo.app.api.users.ui.model.AlbumResponseModel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(value = "albums-ws")
public interface AlbumServiceClient {

    @GetMapping(value = "/users/{id}/albumss", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    List<AlbumResponseModel> getAlbums(@PathVariable("id") final String id);
}
