package com.javanauta.usuario.business.converter;

import com.javanauta.usuario.business.dto.EnderecoDTO;
import com.javanauta.usuario.business.dto.TelefoneDTO;
import com.javanauta.usuario.business.dto.UsuarioDTO;
import com.javanauta.usuario.infrastructure.entity.Endereco;
import com.javanauta.usuario.infrastructure.entity.Telefone;
import com.javanauta.usuario.infrastructure.entity.Usuario;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class UsuarioConverter {
    public Usuario paraUsuario(UsuarioDTO usuarioDTO){
        return Usuario.builder()
                .nome(usuarioDTO.getNome())
                .email(usuarioDTO.getEmail())
                .senha(usuarioDTO.getSenha())
                .enderecos(paraListaEndereco(usuarioDTO.getEnderecos()))
                .telefones(paraListaTelefone(usuarioDTO.getTelefones()))
                .build();
    }

    public List<Endereco> paraListaEndereco(List<EnderecoDTO>enderecoDTOS){
        return enderecoDTOS.stream().map(this::paraEndereco).toList();
    }

    public Endereco paraEndereco(EnderecoDTO enderecoDTO){
        return Endereco.builder()
                .rua(enderecoDTO.getRua())
                .cep(enderecoDTO.getCep())
                .cidade(enderecoDTO.getCidade())
                .estado(enderecoDTO.getEstado())
                .complemento(enderecoDTO.getComplemento())
                .numero(enderecoDTO.getNumero()).build();

    }
    public List<Telefone> paraListaTelefone(List<TelefoneDTO> telefoneDTOS){
        return telefoneDTOS.stream().map(this::paraTelefone).toList();
    }
    public Telefone paraTelefone(TelefoneDTO telefoneDTO){
        return Telefone.builder()
                .numero(telefoneDTO.getNumero())
                .ddd(telefoneDTO.getDdd()).build();
    }
    public UsuarioDTO paraUsuarioDTO(Usuario usuario){
        return UsuarioDTO.builder()
                .nome(usuario.getNome())
                .email(usuario.getEmail())
                .senha(usuario.getSenha())
                .enderecos(paraListaEnderecoDTO(usuario.getEnderecos()))
                .telefones(paraListaTelefoneDTO(usuario.getTelefones()))
                .build();
    }

    public List<EnderecoDTO> paraListaEnderecoDTO(List<Endereco>enderecos){
        return enderecos.stream().map(this::paraEnderecoDTO).toList();
    }

    public EnderecoDTO paraEnderecoDTO(Endereco endereco){
        return EnderecoDTO.builder()
                .id(endereco.getId())
                .rua(endereco.getRua())
                .cep(endereco.getCep())
                .cidade(endereco.getCidade())
                .estado(endereco.getEstado())
                .complemento(endereco.getComplemento())
                .numero(endereco.getNumero()).build();

    }
    public List<TelefoneDTO> paraListaTelefoneDTO(List<Telefone> telefone){
        return telefone.stream().map(this::paraTelefoneDTO).toList();
    }
    public TelefoneDTO paraTelefoneDTO(Telefone telefone){
        return TelefoneDTO.builder()
                .id(telefone.getId())
                .numero(telefone.getNumero())
                .ddd(telefone.getDdd()).build();
    }

    public Usuario updateUsuario(UsuarioDTO usuarioDTO, Usuario usuarioEntity){
        return Usuario.builder()
                .nome(usuarioDTO.getNome()!=null ? usuarioDTO.getNome() : usuarioEntity.getNome())
                .id(usuarioEntity.getId())
                .senha(usuarioDTO.getSenha()!= null? usuarioDTO.getSenha() : usuarioEntity.getSenha())
                .email(usuarioDTO.getEmail()!=null ? usuarioDTO.getEmail() : usuarioEntity.getEmail())
                .enderecos(usuarioEntity.getEnderecos())
                .telefones(usuarioEntity.getTelefones())
                .build();

    }

    public Endereco updateEndereco(EnderecoDTO enderecoDTO, Endereco enderecoEntity){
        return Endereco.builder()
                .id(enderecoEntity.getId())
                .cep(enderecoDTO.getCep() != null ?enderecoDTO.getCep() : enderecoEntity.getCep())
                .numero(enderecoDTO.getNumero() != null ? enderecoDTO.getNumero() : enderecoEntity.getNumero())
                .rua(enderecoDTO.getRua() != null ? enderecoDTO.getRua() : enderecoEntity.getRua())
                .cidade(enderecoDTO.getCidade() != null ? enderecoDTO.getCidade() : enderecoEntity.getCidade())
                .estado(enderecoDTO.getEstado() != null ? enderecoDTO.getEstado() : enderecoEntity.getEstado())
                .complemento(enderecoDTO.getComplemento() != null ? enderecoDTO.getComplemento() : enderecoEntity.getComplemento())
                .build();
    }

    public Telefone updateTelefone(TelefoneDTO telefoneDTO, Telefone telefoneEntity){
        return Telefone.builder()
                .id(telefoneEntity.getId())
                .ddd(telefoneDTO.getDdd() != null ? telefoneDTO.getDdd() : telefoneEntity.getDdd())
                .numero(telefoneDTO.getNumero() != null ? telefoneDTO.getNumero() : telefoneEntity.getNumero())
                .build();
    }

    public Endereco paraEnderecoEntity(EnderecoDTO dto, Long idUsuario){
        return Endereco.builder()
                .rua(dto.getRua())
                .cidade(dto.getCidade())
                .cep(dto.getCep())
                .complemento(dto.getComplemento())
                .estado(dto.getEstado())
                .numero(dto.getNumero())
                .usuario_id(idUsuario)
                .build();
    }
    public  Telefone paraTelefoneEntity(TelefoneDTO dto, Long idUsuario){
        return Telefone.builder()
                .numero(dto.getNumero())
                .ddd(dto.getDdd())
                .usuario_id(idUsuario)
                .build();
    }
}
