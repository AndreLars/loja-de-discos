package br.gov.sp.fatec.lojadediscos.controller;

import br.gov.sp.fatec.lojadediscos.controller.dto.PostAlbumDTO;
import br.gov.sp.fatec.lojadediscos.controller.dto.PutAlbumDTO;
import br.gov.sp.fatec.lojadediscos.controller.dto.View;
import br.gov.sp.fatec.lojadediscos.entity.Album;
import br.gov.sp.fatec.lojadediscos.service.AlbumService;
import com.fasterxml.jackson.annotation.JsonView;
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

    @Autowired private AlbumService albumService;

    @GetMapping(value = "/album/{albumId}", produces = "application/json")
    @JsonView(View.AlbumCompleto.class)
    public Album getAlbum(@PathVariable long albumId) {
        return albumService.findAlbumById(albumId);
    }

    @GetMapping(value = "/album", produces = "application/json")
    @JsonView(View.AlbumCompleto.class)
    public Album getAlbumByNome(@RequestParam(name = "nome") String nome) {
        return albumService.findAlbumByNome(nome);
    }

    @DeleteMapping(value = "/album/{albumId}", produces = "application/json")
    public void deleteAlbum(@PathVariable long albumId) {
        albumService.removeAlbumById(albumId);
    }

    @PutMapping(value = "/album", produces = "application/json")
    @JsonView(View.AlbumCompleto.class)
    public Album putAlbum(@RequestBody PutAlbumDTO album) throws Exception {
        final var convertedAlbum = fromPutAlbumDTO(album);
        return albumService.putAlbum(convertedAlbum);
    }

    @PostMapping(value = "/album", produces = "application/json")
    @JsonView(View.AlbumCompleto.class)
    public Album postAlbum(@RequestBody PostAlbumDTO album) {
        return albumService.novoAlbum(
                album.getNomeAlbum(),
                album.getAnoAlbum(),
                album.getNomesArtistas(),
                album.getListaFaixas());
    }
}
