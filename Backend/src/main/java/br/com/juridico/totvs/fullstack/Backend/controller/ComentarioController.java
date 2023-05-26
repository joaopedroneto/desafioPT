package br.com.juridico.totvs.fullstack.Backend.controller;

import br.com.juridico.totvs.fullstack.Backend.domain.Comentario;
import br.com.juridico.totvs.fullstack.Backend.service.ComentarioService;
import br.com.juridico.totvs.fullstack.Backend.service.ComentarioServiceImpl;
import br.com.juridico.totvs.fullstack.Backend.service.dto.ComentarioCreateUpdateDTO;
import br.com.juridico.totvs.fullstack.Backend.service.dto.ComentarioDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin()
@RestController()
@RequestMapping("/comentario")
public class ComentarioController {
    private final ComentarioService comentarioService;

    public ComentarioController(ComentarioService comentarioService){
        this.comentarioService = comentarioService;
    }

    @PostMapping
    @ResponseStatus( HttpStatus.CREATED )
    public ComentarioDTO create(@RequestBody ComentarioCreateUpdateDTO comentarioCreateUpdateDTO){
        return this.comentarioService.create(comentarioCreateUpdateDTO);
    }

    @GetMapping
    public List<ComentarioDTO> getAll(){
        return this.comentarioService.getAllComentario();
    }
    
    @GetMapping("{idPT}/idPT")
    public List<ComentarioDTO> getComentarioByPT(@PathVariable Long idPT){
        return this.comentarioService.getComentarioByPT(idPT);
    }

    @GetMapping("{idComentario}")
    public ComentarioDTO getComentarioById(@PathVariable Long idComentario){
        return this.comentarioService.getComentariobyId(idComentario);
    }

    @DeleteMapping("{idComentario}")
    @ResponseStatus( HttpStatus.NO_CONTENT )
    public void delete(@PathVariable Long idComentario){
        this.comentarioService.delete(idComentario);
    }

    @PutMapping("{idComentario}")
    public ComentarioDTO update(@PathVariable Long idComentario,
                          @RequestBody ComentarioCreateUpdateDTO comentarioCreateUpdateDTO ){
        return this.comentarioService.update(idComentario, comentarioCreateUpdateDTO);
    }
}
