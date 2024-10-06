package Controllers;

import Entities.MatrizADN;
import Model.SolicitudADN;
import Services.MatrizADNService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "api/v1/matrizADN")
public class MatrizADNController {
    private MatrizADNService matrizADNService;

    public MatrizADNController(MatrizADNService matrizADNService) {
        this.matrizADNService = matrizADNService;
    }

    @GetMapping("")
    public ResponseEntity<?> getAll(){
        try {
        return ResponseEntity.status(HttpStatus.OK).body(matrizADNService.findall());
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error. Por favor intente mas tarde.\"}");
        }
    }

    @PostMapping("")
    public ResponseEntity<?> save(@RequestBody MatrizADN entity){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(matrizADNService.save(entity));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. Por favor intente mas tarde.\"}");
        }
    }

    @PostMapping("/mutant/")
    public ResponseEntity<String> isMutant(@RequestBody SolicitudADN solicitudADN) {
        boolean isMutant = MatrizADNService.isMutant(solicitudADN.getAdn());
        if (isMutant) {
            return new ResponseEntity<>("Mutante detectado", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("No es mutante", HttpStatus.FORBIDDEN);
        }
    }
}
