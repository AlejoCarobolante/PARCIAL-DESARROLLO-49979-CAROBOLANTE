package Services;

import Entities.MatrizADN;
import Repositories.MatrizADNRepository;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

public class MatrizADNService{

    private MatrizADNRepository matrizADNRepository;

    public MatrizADNService(MatrizADNRepository matrizADNRepository) {
        this.matrizADNRepository = matrizADNRepository;
    }

    @Transactional
    public List<MatrizADN> findall() throws Exception {
        try {
            List<MatrizADN> entities = matrizADNRepository.findAll();
            return entities;
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Transactional
    public MatrizADN findByID(Long idADN) throws Exception {
        try {
            Optional<MatrizADN> entityOptional = matrizADNRepository.findById(idADN);
            return entityOptional.get();
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Transactional
    public MatrizADN save(MatrizADN entity) throws Exception {
        try {
            entity = matrizADNRepository.save(entity);
            return entity;
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Transactional
    public boolean delete(Long idADN) throws Exception {
        try {
            if (matrizADNRepository.existsById(idADN)){
                matrizADNRepository.deleteById(idADN);
                return true;
            }else {
                throw new Exception();
            }
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }
}