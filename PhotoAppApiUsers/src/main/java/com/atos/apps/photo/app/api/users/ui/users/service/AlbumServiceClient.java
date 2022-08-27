package com.atos.apps.photo.app.api.users.ui.users.service;

import com.atos.apps.photo.app.api.users.ui.model.AlbumResponseModel;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

@FeignClient(value = "albums-ws")
public interface AlbumServiceClient {

    @GetMapping(value = "/users/{id}/albums", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @Retry(name = "albums-ws")
    @CircuitBreaker(name = "albums-ws", fallbackMethod = "getAlbumsFallback")
    List<AlbumResponseModel> getAlbums(@PathVariable("id") final String id);

    default List<AlbumResponseModel> getAlbumsFallback(String id, Throwable throwable) {
        System.out.println("Param " + id);
        System.out.println("Exception took place " + throwable.getLocalizedMessage());
        return new ArrayList<>();
    }
}
