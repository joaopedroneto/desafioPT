package br.com.juridico.totvs.fullstack.Backend.service;

import br.com.juridico.totvs.fullstack.Backend.domain.PT;
import br.com.juridico.totvs.fullstack.Backend.service.dto.PTCreateUpdateDTO;
import br.com.juridico.totvs.fullstack.Backend.service.dto.PTDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PTService {
    List<PT> listPT = null;
    public PTDTO create(PTCreateUpdateDTO ptCreateUpdateDTO);
    public PTDTO update(Long id, PTCreateUpdateDTO ptCreateUpdateDTO);
    public void delete(Long id);
    public PTDTO getPTbyId(Long id);
    public List<PTDTO> getPTByPais(String ptbypais);
    public List<PTDTO> getAllPT();
}
