package br.gov.sp.fatec.lojadediscos.service;

import br.gov.sp.fatec.lojadediscos.entity.Usuario;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UsuarioService extends UserDetailsService {
    public Usuario novoUsuario(String nome, String email, String senha, String nomeAutorizacao);
}
