package br.gov.sp.fatec.lojadediscos.service;

import br.gov.sp.fatec.lojadediscos.entity.Autorizacao;
import br.gov.sp.fatec.lojadediscos.entity.Usuario;
import br.gov.sp.fatec.lojadediscos.repository.AutorizacaoRepository;
import br.gov.sp.fatec.lojadediscos.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.stream.Collectors;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired private AutorizacaoRepository autorizacaoRepo;
    @Autowired private UsuarioRepository usuarioRepo;
    @Autowired private PasswordEncoder passEncoder;

    @Override
    @Transactional
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Usuario novoUsuario(String nome, String email, String senha, String nomeAutorizacao) {
        Autorizacao autorizacao = autorizacaoRepo.findByNome(nomeAutorizacao);
        if (autorizacao == null) {
            autorizacao = new Autorizacao();
            autorizacao.setNome(nomeAutorizacao);
            autorizacaoRepo.save(autorizacao);
        }
        Usuario usuario = new Usuario();
        usuario.setNome(nome);
        usuario.setEmail(email);
        usuario.setSenha(passEncoder.encode(senha));
        usuario.setAutorizacoes(new HashSet<>());
        usuario.getAutorizacoes().add(autorizacao);
        usuarioRepo.save(usuario);
        return usuario;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepo.findTop1ByNomeOrEmail(username, username);
        if (usuario == null) {
            throw new UsernameNotFoundException("Usuário " + username + " não encontrado");
        }
        return User.builder()
                .username(username)
                .password(usuario.getSenha())
                .authorities(
                        usuario.getAutorizacoes().stream()
                                .map(Autorizacao::getNome)
                                .collect(Collectors.toList())
                                .toArray(new String[usuario.getAutorizacoes().size()]))
                .build();
    }
}
