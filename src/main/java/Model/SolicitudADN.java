package Model;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class SolicitudADN {
        private String[] adn;

        public String[] getAdn() {
            return adn;
        }

        public void setAdn(String[] dna) {
            this.adn = adn;
        }
    }

