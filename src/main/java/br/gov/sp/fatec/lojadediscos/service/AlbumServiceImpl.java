package br.gov.sp.fatec.lojadediscos.service;

import br.gov.sp.fatec.lojadediscos.entity.Album;
import br.gov.sp.fatec.lojadediscos.entity.Artista;
import br.gov.sp.fatec.lojadediscos.entity.Faixa;
import br.gov.sp.fatec.lojadediscos.repository.AlbumRepository;
import br.gov.sp.fatec.lojadediscos.repository.ArtistaRepository;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.beans.factory.annotation.Autowired;
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
    public void novoAlbum(String nomeAlbum, Integer anoAlbum, List<String> nomesArtistas, List<Pair<String, Integer>> listaFaixas) {
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
        for(Pair<String, Integer> faixa : listaFaixas) {
            final var novaFaixa = new Faixa();
            novaFaixa.setOrdem(i);
            novaFaixa.setNome(faixa.getKey());
            novaFaixa.setDuracao(faixa.getValue());
            novoAlbum.addFaixa(novaFaixa);
            i++;
        }
        albumRepository.save(novoAlbum);
    }

    @Override
    public Album findAlbumById(long albumId) {
        return albumRepository.findById(albumId).orElseThrow();
    }
}
