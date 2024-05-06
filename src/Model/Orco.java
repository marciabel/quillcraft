package Model;

import Business.NumberGenerator;

public class Orco extends Personaje{

    @Override
    public Integer atacar(Integer poderDefensaContrincante) {
        Integer efectividadDisparo = NumberGenerator.generateRandomPositiveInteger(1, 100);
        Integer valorAtaque = this.poderDisparo() * efectividadDisparo;

        return (int) ((((valorAtaque - poderDefensaContrincante)/500)*100)*1.1);
    }
}
