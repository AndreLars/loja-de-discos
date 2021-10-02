package br.gov.sp.fatec.lojadediscos.service;

import br.gov.sp.fatec.lojadediscos.controller.dto.PostFaixaDTO;
import br.gov.sp.fatec.lojadediscos.entity.Album;
import br.gov.sp.fatec.lojadediscos.entity.Artista;
import br.gov.sp.fatec.lojadediscos.entity.Faixa;
import br.gov.sp.fatec.lojadediscos.repository.AlbumRepository;
import br.gov.sp.fatec.lojadediscos.repository.ArtistaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class AlbumServiceImpl implements AlbumService {

    @Autowired
    private AlbumRepository albumRepository;

    @Autowired
    private ArtistaRepository artistaRepository;

    @Transactional
    @Override
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    public Album novoAlbum(String nomeAlbum, Integer anoAlbum, List<String> nomesArtistas, List<PostFaixaDTO> listaFaixas) {
        if(nomesArtistas.isEmpty()) {
            throw new IllegalArgumentException();
        }
        if(listaFaixas.isEmpty()) {
            throw new IllegalArgumentException();
        }
        final var novoAlbum = new Album();
        novoAlbum.setNome(nomeAlbum);
        novoAlbum.setAno(anoAlbum);
        for(String nomeArtista : nomesArtistas) {
            final var novoArtista = new Artista();
            novoArtista.setNome(nomeArtista);
            novoAlbum.addArtista(novoArtista);
            artistaRepository.save(novoArtista);
        }
        int i = 0;
        for(PostFaixaDTO faixa : listaFaixas) {
            final var novaFaixa = new Faixa();
            novaFaixa.setOrdem(i);
            novaFaixa.setNome(faixa.getNome());
            novaFaixa.setDuracao(faixa.getDuracao());
            novoAlbum.addFaixa(novaFaixa);
            i++;
        }
        return albumRepository.save(novoAlbum);
    }

    @Override
    public Album findAlbumById(long albumId) {
        return albumRepository.findById(albumId).orElseThrow();
    }

    @Override
    public Album findAlbumByNome(String nome) {
        return albumRepository.findByNome(nome).orElseThrow();
    }

    @Override
    public void removeAlbumById(long albumId) {
        albumRepository.deleteById(albumId);
    }

    @Override
    public Album putAlbum(Album album) {
        albumRepository.findById(album.getAlbumId()).orElseThrow();
        return albumRepository.save(album);
    }
}
