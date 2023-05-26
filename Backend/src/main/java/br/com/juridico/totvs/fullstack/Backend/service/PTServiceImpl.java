package br.com.juridico.totvs.fullstack.Backend.service;

import br.com.juridico.totvs.fullstack.Backend.domain.PT;
import br.com.juridico.totvs.fullstack.Backend.service.dto.PTCreateUpdateDTO;
import br.com.juridico.totvs.fullstack.Backend.service.dto.PTDTO;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class PTServiceImpl implements PTService {
    List<PT> listPT = null;

    PTServiceImpl(){
        this.listPT = new ArrayList<>();
    }

    @Override
    public PTDTO create(PTCreateUpdateDTO ptCreateUpdateDTO) {
        PT novoPT = new PT(
                this.getNewId(),
                ptCreateUpdateDTO.getPais(),
                ptCreateUpdateDTO.getCidade(),
                ptCreateUpdateDTO.getNome(),
                ptCreateUpdateDTO.getEstacao(),
                ptCreateUpdateDTO.getResumo());

        this.listPT.add(novoPT);

        return new PTDTO(novoPT);
    }

    @Override
    public PTDTO update(Long id, PTCreateUpdateDTO ptCreateUpdateDTO) {
        PT pt = this.getPTById(id);
        int index = this.listPT.indexOf(pt);
        if (pt == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        
        pt.setPais(ptCreateUpdateDTO.getPais());
        pt.setCidade(ptCreateUpdateDTO.getCidade());
        pt.setNome(ptCreateUpdateDTO.getNome());
        pt.setEstacao(ptCreateUpdateDTO.getEstacao());
        pt.setResumo(ptCreateUpdateDTO.getResumo());

        this.listPT.set(index, pt);
        return new PTDTO(pt);
    }

    @Override
    public void delete(Long id) {
        PT pt = this.getPTById(id);
        this.listPT.remove(pt);
    }

    @Override
    public PTDTO getPTbyId(Long id) {
        PT pt = this.getPTById(id);
        if (pt == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return new PTDTO(pt);
    }

    @Override
    public List<PTDTO> getPTByPais(String pais) {
        return this.listPT.stream()
                .filter(x -> x.getPais().equals(pais))
                .map(pt -> new PTDTO(pt))
                .collect(Collectors.toList());
    }

    @Override
    public List<PTDTO> getAllPT() {
        return this.listPT.stream()
                .map(pt -> new PTDTO(pt))
                .collect(Collectors.toList());
    }

    private Long getNewId(){
        if (this.listPT.size() > 0){
            return this.listPT.stream().max(Comparator
                            .comparingDouble(PT::getId))
                    .get()
                    .getId()+1;
        } else {
            return Long.valueOf(1);
        }
    }

    private PT getPTById(Long id){
        return this.listPT.stream()
                .filter(x -> Objects.equals(x.getId(), id))
                .findFirst()
                .orElse(null);
    }

    private int getPaisIndexById(Long id){
        AtomicInteger index = new AtomicInteger();
        return this.listPT.stream()
                .peek(p -> index.incrementAndGet())
                .anyMatch(x -> x.getId().equals(id)) ?
                index.get() - 1 : -1;
    }
}

