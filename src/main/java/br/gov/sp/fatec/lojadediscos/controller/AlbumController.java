package br.gov.sp.fatec.lojadediscos.controller;

import br.gov.sp.fatec.lojadediscos.service.AlbumService;
import br.gov.sp.fatec.lojadediscos.util.Views;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static br.gov.sp.fatec.lojadediscos.entity.Album.fromPutAlbumDTO;

@CrossOrigin
@RestController
public class AlbumController {

    @Autowired
    private AlbumService albumService;

    @GetMapping(value = "/album/{albumId}", produces = "application/json")
    public String getAlbum(@PathVariable long albumId) throws JsonProcessingException {
        final var mapper = new ObjectMapper();
        mapper.disable(MapperFeature.DEFAULT_VIEW_INCLUSION);
        final var album = albumService.findAlbumById(albumId);
        return mapper.writerWithView(Views.Public.class).writeValueAsString(album);
    }

    @GetMapping(value = "/album", produces = "application/json")
    public String getAlbumByNome(@RequestParam(name = "nome") String nome) throws JsonProcessingException {
        final var mapper = new ObjectMapper();
        mapper.disable(MapperFeature.DEFAULT_VIEW_INCLUSION);
        final var album = albumService.findAlbumByNome(nome);
        return mapper.writerWithView(Views.Public.class).writeValueAsString(album);
    }

    @DeleteMapping(value = "/album/{albumId}", produces = "application/json")
    public void deleteAlbum(@PathVariable long albumId) {
        albumService.removeAlbumById(albumId);
    }

    @PutMapping(value = "/album", produces = "application/json")
    public void putAlbum(@RequestBody PutAlbumDTO album) {
        final var convertedAlbum = fromPutAlbumDTO(album);
        albumService.putAlbum(convertedAlbum);
    }

    @PostMapping(value = "/album", produces = "application/json")
    public void postAlbum(@RequestBody PostAlbumDTO album) {
        albumService.novoAlbum(album.getNomeAlbum(), album.getAnoAlbum(), album.getNomesArtistas(), album.getListaFaixas());
    }
}
