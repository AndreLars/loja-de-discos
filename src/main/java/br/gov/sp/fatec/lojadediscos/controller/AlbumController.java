package br.gov.sp.fatec.lojadediscos.controller;

import br.gov.sp.fatec.lojadediscos.service.AlbumService;
import br.gov.sp.fatec.lojadediscos.util.Views;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AlbumController {

    @Autowired
    private AlbumService albumService;

    @GetMapping(value = "/{albumId}", produces = "application/json")
    public String getAlbum(@PathVariable long albumId) throws JsonProcessingException {
        final var mapper = new ObjectMapper();
        mapper.disable(MapperFeature.DEFAULT_VIEW_INCLUSION);
        final var album = albumService.findAlbumById(albumId);
        return mapper.writerWithView(Views.Public.class).writeValueAsString(album);
    }
}
