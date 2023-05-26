package br.com.juridico.totvs.fullstack.Backend.controller;

import br.com.juridico.totvs.fullstack.Backend.domain.PT;
import br.com.juridico.totvs.fullstack.Backend.service.PTService;
import br.com.juridico.totvs.fullstack.Backend.service.PTServiceImpl;
import br.com.juridico.totvs.fullstack.Backend.service.dto.PTCreateUpdateDTO;
import br.com.juridico.totvs.fullstack.Backend.service.dto.PTDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin()
@RestController()
@RequestMapping("/ponto-turistico")
public class PTController {
    private final PTService ptService;

    public PTController(PTService ptService){
        this.ptService = ptService;
    }

    @PostMapping
    @ResponseStatus( HttpStatus.CREATED )
    public PTDTO create(@RequestBody PTCreateUpdateDTO ptCreateUpdateDTO){
        return this.ptService.create(ptCreateUpdateDTO);
    }

    @GetMapping
    public List<PTDTO> getAll(){
        return this.ptService.getAllPT();
    }

    @GetMapping("{ptbypais}/ptbypais")
    public List<PTDTO> getPTByPais(@PathVariable String ptbypais){
        return this.ptService.getPTByPais(ptbypais);
    }

    @GetMapping("{idPT}")
    public PTDTO getPTById(@PathVariable Long idPT){
        return this.ptService.getPTbyId(idPT);
    }

    @DeleteMapping("{idPT}")
    @ResponseStatus( HttpStatus.NO_CONTENT )
    public void delete(@PathVariable Long idPT){
        this.ptService.delete(idPT);
    }

    @PutMapping("{idPT}")
    public PTDTO update(@PathVariable Long idPT,
                          @RequestBody PTCreateUpdateDTO ptCreateUpdateDTO ){
        return this.ptService.update(idPT, ptCreateUpdateDTO);
    }
}
