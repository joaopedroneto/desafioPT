package br.com.juridico.totvs.fullstack.Backend.service;

import br.com.juridico.totvs.fullstack.Backend.domain.Comentario;
import br.com.juridico.totvs.fullstack.Backend.service.dto.ComentarioCreateUpdateDTO;
import br.com.juridico.totvs.fullstack.Backend.service.dto.ComentarioDTO;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class ComentarioServiceImpl implements ComentarioService {
    List<Comentario> listComentario = null;
    
    ComentarioServiceImpl(){
        this.listComentario = new ArrayList<>();
    }

    @Override
    public ComentarioDTO create(ComentarioCreateUpdateDTO comentarioCreateUpdateDTO) {
        Comentario novoComentario = new Comentario(
                this.getNewId(),
                comentarioCreateUpdateDTO.getIdPT(),
                comentarioCreateUpdateDTO.getNome(),
                comentarioCreateUpdateDTO.getComen(),
                comentarioCreateUpdateDTO.getData());

        this.listComentario.add(novoComentario);

        return new ComentarioDTO(novoComentario);
    }

    @Override
    public ComentarioDTO update(Long id, ComentarioCreateUpdateDTO comentarioCreateUpdateDTO) {
        Comentario comentario = this.getComentarioById(id);
        int index = this.listComentario.indexOf(comentario);
        if (comentario == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        
        comentario.setIdPT(comentarioCreateUpdateDTO.getIdPT());
        comentario.setNome(comentarioCreateUpdateDTO.getNome());
        comentario.setComen(comentarioCreateUpdateDTO.getComen());
        comentario.setData(comentarioCreateUpdateDTO.getData());

        this.listComentario.set(index, comentario);
        return new ComentarioDTO(comentario);
    }

    @Override
    public void delete(Long id) {
        Comentario comentario = this.getComentarioById(id);
        this.listComentario.remove(comentario);
    }

    @Override
    public ComentarioDTO getComentariobyId(Long id) {
        Comentario comentario = this.getComentarioById(id);
        if (comentario == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return new ComentarioDTO(comentario);
    }

    @Override
    public List<ComentarioDTO> getComentarioByPT(Long idPT) {
        return this.listComentario.stream()
                .filter(x -> x.getIdPT().equals(idPT))
                .map(comentario -> new ComentarioDTO(comentario))
                .collect(Collectors.toList());
    }

    @Override
    public List<ComentarioDTO> getAllComentario() {
        return this.listComentario.stream()
                .map(comentario -> new ComentarioDTO(comentario))
                .collect(Collectors.toList());
    }

    private Long getNewId(){
        if (this.listComentario.size() > 0){
            return this.listComentario.stream().max(Comparator
                            .comparingDouble(Comentario::getId))
                    .get()
                    .getId()+1;
        } else {
            return Long.valueOf(1);
        }
    }

    private Comentario getComentarioById(Long id){
        return this.listComentario.stream()
                .filter(x -> Objects.equals(x.getId(), id))
                .findFirst()
                .orElse(null);
    }

    private int getComentarioIndexById(Long id){
        AtomicInteger index = new AtomicInteger();
        return this.listComentario.stream()
                .peek(p -> index.incrementAndGet())
                .anyMatch(x -> x.getId().equals(id)) ?
                index.get() - 1 : -1;
    }

}
